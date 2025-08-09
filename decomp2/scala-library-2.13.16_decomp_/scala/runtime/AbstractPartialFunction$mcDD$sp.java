package scala.runtime;

import scala.Function1$mcDD$sp;
import scala.PartialFunction$;

public abstract class AbstractPartialFunction$mcDD$sp extends AbstractPartialFunction implements Function1$mcDD$sp {
   public double apply(final double x) {
      return this.apply$mcDD$sp(x);
   }

   public double apply$mcDD$sp(final double x) {
      return BoxesRunTime.unboxToDouble(this.applyOrElse(x, PartialFunction$.MODULE$.empty()));
   }
}
