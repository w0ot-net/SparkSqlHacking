package scala.runtime;

import scala.Function1$mcZJ$sp;
import scala.PartialFunction$;

public abstract class AbstractPartialFunction$mcZJ$sp extends AbstractPartialFunction implements Function1$mcZJ$sp {
   public boolean apply(final long x) {
      return this.apply$mcZJ$sp(x);
   }

   public boolean apply$mcZJ$sp(final long x) {
      return BoxesRunTime.unboxToBoolean(this.applyOrElse(x, PartialFunction$.MODULE$.empty()));
   }
}
