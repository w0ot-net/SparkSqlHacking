package scala.runtime;

import scala.Function1$mcJF$sp;
import scala.PartialFunction$;

public abstract class AbstractPartialFunction$mcJF$sp extends AbstractPartialFunction implements Function1$mcJF$sp {
   public long apply(final float x) {
      return this.apply$mcJF$sp(x);
   }

   public long apply$mcJF$sp(final float x) {
      return BoxesRunTime.unboxToLong(this.applyOrElse(x, PartialFunction$.MODULE$.empty()));
   }
}
