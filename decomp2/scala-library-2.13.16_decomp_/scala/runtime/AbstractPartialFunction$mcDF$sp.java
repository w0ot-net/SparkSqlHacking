package scala.runtime;

import scala.Function1$mcDF$sp;
import scala.PartialFunction$;

public abstract class AbstractPartialFunction$mcDF$sp extends AbstractPartialFunction implements Function1$mcDF$sp {
   public double apply(final float x) {
      return this.apply$mcDF$sp(x);
   }

   public double apply$mcDF$sp(final float x) {
      return BoxesRunTime.unboxToDouble(this.applyOrElse(x, PartialFunction$.MODULE$.empty()));
   }
}
