package scala.runtime;

import scala.Function1$mcJD$sp;
import scala.PartialFunction$;

public abstract class AbstractPartialFunction$mcJD$sp extends AbstractPartialFunction implements Function1$mcJD$sp {
   public long apply(final double x) {
      return this.apply$mcJD$sp(x);
   }

   public long apply$mcJD$sp(final double x) {
      return BoxesRunTime.unboxToLong(this.applyOrElse(x, PartialFunction$.MODULE$.empty()));
   }
}
