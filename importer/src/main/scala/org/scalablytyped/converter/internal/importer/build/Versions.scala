package org.scalablytyped.converter.internal
package importer.build

import org.scalablytyped.converter.internal.stringUtils.quote

object Versions {
  val sbtVersion = "1.3.3"

  object `scala 2.12 with scala.js 0.6` extends Versions {
    val scalaOrganization   = "org.scala-lang"
    val scalaVersion        = "2.12.10"
    val binVersion          = "2.12"
    val scalaJsOrganization = "org.scala-js"
    val scalaJsVersion      = "0.6.31"
    val scalaJsBinVersion   = "0.6"
    val scalacOptions       = List("-P:scalajs:sjsDefinedByDefault", "-g:notailcalls")
  }

  object `scala 2.13 with scala.js 1` extends Versions {
    val scalaOrganization   = "org.scala-lang"
    val scalaVersion        = "2.13.0"
    val binVersion          = "2.13"
    val scalaJsOrganization = "org.scala-js"
    val scalaJsVersion      = "1.0.0-RC1"
    val scalaJsBinVersion   = "1.0.0-RC1"
    val scalacOptions       = List("-g:notailcalls")
  }
}

trait Versions {
  val scalaOrganization:   String
  val scalaVersion:        String
  val binVersion:          String
  val scalaJsOrganization: String
  val scalaJsVersion:      String
  val scalaJsBinVersion:   String
  val scalacOptions:       List[String]

  val sbtBintray = %("org.foundweekends", "sbt-bintray", "0.5.4")

  def s(artifact: String): String =
    s"${artifact}_$binVersion"

  def sjs(artifact: String): String =
    s"${artifact}_sjs${scalaJsBinVersion}_$binVersion"

  def %%%(org: String, artifact: String, version: String): String =
    s"${quote(org)} %%% ${quote(artifact)} % ${quote(version)}"

  def %%(org: String, artifact: String, version: String): String =
    s"${quote(org)} %% ${quote(artifact)} % ${quote(version)}"

  def %(org: String, artifact: String, version: String): String =
    s"${quote(org)} % ${quote(artifact)} % ${quote(version)}"
}