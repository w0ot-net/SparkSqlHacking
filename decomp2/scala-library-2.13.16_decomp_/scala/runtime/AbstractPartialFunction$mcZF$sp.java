package scala.runtime;

import scala.Function1$mcZF$sp;
import scala.PartialFunction$;

public abstract class AbstractPartialFunction$mcZF$sp extends AbstractPartialFunction implements Function1$mcZF$sp {
   public boolean apply(final float x) {
      return this.apply$mcZF$sp(x);
   }

   public boolean apply$mcZF$sp(final float x) {
      return BoxesRunTime.unboxToBoolean(this.applyOrElse(x, PartialFunction$.MODULE$.empty()));
   }
}
