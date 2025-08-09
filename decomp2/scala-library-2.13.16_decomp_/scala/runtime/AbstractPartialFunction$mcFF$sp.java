package scala.runtime;

import scala.Function1$mcFF$sp;
import scala.PartialFunction$;

public abstract class AbstractPartialFunction$mcFF$sp extends AbstractPartialFunction implements Function1$mcFF$sp {
   public float apply(final float x) {
      return this.apply$mcFF$sp(x);
   }

   public float apply$mcFF$sp(final float x) {
      return BoxesRunTime.unboxToFloat(this.applyOrElse(x, PartialFunction$.MODULE$.empty()));
   }
}
