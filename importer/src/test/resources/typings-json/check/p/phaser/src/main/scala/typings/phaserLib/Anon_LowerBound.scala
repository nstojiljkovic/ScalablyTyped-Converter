package typings
package phaserLib

import scala.scalajs.js
import scala.scalajs.js.`|`
import scala.scalajs.js.annotation._

trait Anon_LowerBound extends js.Object {
  var lowerBound: js.UndefOr[js.Array[scala.Double]] = js.undefined
  var upperBound: js.UndefOr[js.Array[scala.Double]] = js.undefined
}

object Anon_LowerBound {
  @scala.inline
  def apply(lowerBound: js.Array[scala.Double] = null, upperBound: js.Array[scala.Double] = null): Anon_LowerBound = {
    val __obj = js.Dynamic.literal()
    if (lowerBound != null) __obj.updateDynamic("lowerBound")(lowerBound)
    if (upperBound != null) __obj.updateDynamic("upperBound")(upperBound)
    __obj.asInstanceOf[Anon_LowerBound]
  }
}

