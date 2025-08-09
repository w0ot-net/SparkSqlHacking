package scala.runtime;

import scala.Function1$mcFD$sp;
import scala.PartialFunction$;

public abstract class AbstractPartialFunction$mcFD$sp extends AbstractPartialFunction implements Function1$mcFD$sp {
   public float apply(final double x) {
      return this.apply$mcFD$sp(x);
   }

   public float apply$mcFD$sp(final double x) {
      return BoxesRunTime.unboxToFloat(this.applyOrElse(x, PartialFunction$.MODULE$.empty()));
   }
}
