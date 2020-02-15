package org.scalablytyped.converter
package plugin

import java.time.Instant

import com.olvind.logging
import com.olvind.logging.LogLevel
import org.scalablytyped.converter.internal.importer.Json
import org.scalablytyped.converter.internal.importer.jsonCodecs.{FileDecoder, FileEncoder}
import org.scalablytyped.converter.internal.scalajs.QualifiedName
import org.scalablytyped.converter.internal.ts.TsIdentLibrary
import org.scalablytyped.converter.internal.{BuildInfo, Digest, IArray, ImportTypings, InFolder, WrapSbtLogger}
import sbt.Keys._
import sbt._
import scalajsbundler.sbtplugin.ScalaJSBundlerPlugin

import scala.util.Try

object ScalablyTypedConverterPlugin extends AutoPlugin {
  override def requires = ScalablyTypedPluginBase && ScalaJSBundlerPlugin

  lazy val stScalaJsBundlerIntegration: Seq[Def.Setting[_]] = {
    import ScalablyTypedPluginBase.autoImport._
    import scalajsbundler.sbtplugin.ScalaJSBundlerPlugin.autoImport._

    Seq(
      //Make sure we always include the stdlib
      npmDependencies in Compile ++= {
        //Make sure it doesn't already exist
        (npmDependencies in Compile).value
          .find(_._1 == "typescript")
          .fold(Seq("typescript" -> (stTypescriptVersion).value))(_ => Nil)
      },
      stImport := {
        val projectName          = name.value
        val cacheDirectory       = streams.value.cacheDirectory
        val flavour              = stInternalFlavour.value
        val enableScalaJsDefined = stEnableScalaJsDefined.value.map(TsIdentLibrary.apply)
        val packageJson          = (crossTarget in npmUpdate).value / "package.json"
        val nodeModules          = InFolder(os.Path((npmInstallDependencies in Compile).value / "node_modules"))
        val stdLib               = stStdlib.value
        val targetFolder         = os.Path((sourceManaged in Compile).value / "scalablytyped")
        val npmDeps              = (npmDependencies in Compile).value ++ (npmDependencies in Test).value
        val ignored              = stIgnore.value.to[Set]
        val minimize             = stMinimize.value.map(TsIdentLibrary.apply)
        val quiet                = (Global / stQuiet).value
        val sbtLog               = streams.value.log
        val cacheDir             = (Global / stCacheDir).value.map(os.Path(_))
        val expandTypeMappings   = stInternalExpandTypeMappings.value

        val stLogger: logging.Logger[Unit] =
          if (quiet) logging.Logger.DevNull
          else WrapSbtLogger(sbtLog, Instant.now).filter(LogLevel.warn).void.withContext("project", projectName)

        val minimizeKeep = IArray
          .fromTraversable(stMinimizeKeep.value)
          .map(str => QualifiedName(flavour.outputPkg +: QualifiedName(str).parts))

        val config = ImportTypings.Input(
          BuildInfo.version,
          Digest.of(List(os.read.bytes(os.Path(packageJson)))).hexString,
          IArray.fromTraversable(npmDeps),
          nodeModules,
          targetFolder,
          flavour,
          enableScalaJsDefined,
          IArray.fromTraversable(stdLib),
          ignored,
          minimize,
          minimizeKeep,
          expandTypeMappings.map(TsIdentLibrary.apply),
        )

        val inputPath  = os.Path(cacheDirectory / "scalablytyped" / "input.json")
        val outputPath = os.Path(cacheDirectory / "scalablytyped" / "output.json")

        (Try(Json[ImportTypings.Input](inputPath)).toOption, Json.opt[Seq[File]](outputPath)) match {
          case (Some(`config`), Some(output)) =>
            stLogger.warn("Nothing to do")
            output
          case _ =>
            ImportTypings(config, stLogger, cacheDir) match {
              case Right(files) =>
                val seqFiles = files.to[Seq]
                Json.persist(inputPath)(config)
                Json.persist(outputPath)(seqFiles)
                seqFiles
              case Left(errors) =>
                sys.error(errors.mkString("\n"))
            }
        }
      },
    )
  }

  override lazy val projectSettings: scala.Seq[Def.Setting[_]] =
    inConfig(Compile)(stScalaJsBundlerIntegration)
}
