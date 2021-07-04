package typingsJapgolly.react.mod

import japgolly.scalajs.react.CallbackTo
import org.scalablytyped.runtime.StringDictionary
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait Mixin[P, S]
  extends StObject
     with ComponentLifecycle[P, S, js.Any] {
  
  var childContextTypes: js.UndefOr[ValidationMap[js.Any]] = js.native
  
  var contextTypes: js.UndefOr[ValidationMap[js.Any]] = js.native
  
  var displayName: js.UndefOr[String] = js.native
  
  var getDefaultProps: js.UndefOr[js.Function0[P]] = js.native
  
  var getInitialState: js.UndefOr[js.Function0[S]] = js.native
  
  var mixins: js.UndefOr[js.Array[Mixin[P, S]]] = js.native
  
  var propTypes: js.UndefOr[ValidationMap[js.Any]] = js.native
  
  var statics: js.UndefOr[StringDictionary[js.Any]] = js.native
}
object Mixin {
  
  @scala.inline
  def apply[P, S](): Mixin[P, S] = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[Mixin[P, S]]
  }
  
  @scala.inline
  implicit class MixinMutableBuilder[Self <: Mixin[?, ?], P, S] (val x: Self & (Mixin[P, S])) extends AnyVal {
    
    @scala.inline
    def setChildContextTypes(value: ValidationMap[js.Any]): Self = StObject.set(x, "childContextTypes", value.asInstanceOf[js.Any])
    
    @scala.inline
    def setChildContextTypesUndefined: Self = StObject.set(x, "childContextTypes", ())
    
    @scala.inline
    def setContextTypes(value: ValidationMap[js.Any]): Self = StObject.set(x, "contextTypes", value.asInstanceOf[js.Any])
    
    @scala.inline
    def setContextTypesUndefined: Self = StObject.set(x, "contextTypes", ())
    
    @scala.inline
    def setDisplayName(value: String): Self = StObject.set(x, "displayName", value.asInstanceOf[js.Any])
    
    @scala.inline
    def setDisplayNameUndefined: Self = StObject.set(x, "displayName", ())
    
    @scala.inline
    def setGetDefaultProps(value: CallbackTo[P]): Self = StObject.set(x, "getDefaultProps", value.toJsFn)
    
    @scala.inline
    def setGetDefaultPropsUndefined: Self = StObject.set(x, "getDefaultProps", ())
    
    @scala.inline
    def setGetInitialState(value: CallbackTo[S]): Self = StObject.set(x, "getInitialState", value.toJsFn)
    
    @scala.inline
    def setGetInitialStateUndefined: Self = StObject.set(x, "getInitialState", ())
    
    @scala.inline
    def setMixins(value: js.Array[Mixin[P, S]]): Self = StObject.set(x, "mixins", value.asInstanceOf[js.Any])
    
    @scala.inline
    def setMixinsUndefined: Self = StObject.set(x, "mixins", ())
    
    @scala.inline
    def setMixinsVarargs(value: (Mixin[P, S])*): Self = StObject.set(x, "mixins", js.Array(value :_*))
    
    @scala.inline
    def setPropTypes(value: ValidationMap[js.Any]): Self = StObject.set(x, "propTypes", value.asInstanceOf[js.Any])
    
    @scala.inline
    def setPropTypesUndefined: Self = StObject.set(x, "propTypes", ())
    
    @scala.inline
    def setStatics(value: StringDictionary[js.Any]): Self = StObject.set(x, "statics", value.asInstanceOf[js.Any])
    
    @scala.inline
    def setStaticsUndefined: Self = StObject.set(x, "statics", ())
  }
}
