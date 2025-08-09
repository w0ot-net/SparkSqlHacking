package scala.runtime;

import scala.Function1$mcZD$sp;
import scala.PartialFunction$;

public abstract class AbstractPartialFunction$mcZD$sp extends AbstractPartialFunction implements Function1$mcZD$sp {
   public boolean apply(final double x) {
      return this.apply$mcZD$sp(x);
   }

   public boolean apply$mcZD$sp(final double x) {
      return BoxesRunTime.unboxToBoolean(this.applyOrElse(x, PartialFunction$.MODULE$.empty()));
   }
}
