package scala.runtime;

import scala.Function1$mcID$sp;
import scala.PartialFunction$;

public abstract class AbstractPartialFunction$mcID$sp extends AbstractPartialFunction implements Function1$mcID$sp {
   public int apply(final double x) {
      return this.apply$mcID$sp(x);
   }

   public int apply$mcID$sp(final double x) {
      return BoxesRunTime.unboxToInt(this.applyOrElse(x, PartialFunction$.MODULE$.empty()));
   }
}
