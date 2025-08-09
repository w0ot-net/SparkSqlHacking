package scala.runtime;

import scala.Function1$mcJJ$sp;
import scala.PartialFunction$;

public abstract class AbstractPartialFunction$mcJJ$sp extends AbstractPartialFunction implements Function1$mcJJ$sp {
   public long apply(final long x) {
      return this.apply$mcJJ$sp(x);
   }

   public long apply$mcJJ$sp(final long x) {
      return BoxesRunTime.unboxToLong(this.applyOrElse(x, PartialFunction$.MODULE$.empty()));
   }
}
