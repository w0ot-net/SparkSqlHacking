package scala.runtime;

import scala.Function1$mcJI$sp;
import scala.PartialFunction$;

public abstract class AbstractPartialFunction$mcJI$sp extends AbstractPartialFunction implements Function1$mcJI$sp {
   public long apply(final int x) {
      return this.apply$mcJI$sp(x);
   }

   public long apply$mcJI$sp(final int x) {
      return BoxesRunTime.unboxToLong(this.applyOrElse(x, PartialFunction$.MODULE$.empty()));
   }
}
