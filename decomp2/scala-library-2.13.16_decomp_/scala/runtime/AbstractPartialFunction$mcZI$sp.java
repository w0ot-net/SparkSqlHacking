package scala.runtime;

import scala.Function1$mcZI$sp;
import scala.PartialFunction$;

public abstract class AbstractPartialFunction$mcZI$sp extends AbstractPartialFunction implements Function1$mcZI$sp {
   public boolean apply(final int x) {
      return this.apply$mcZI$sp(x);
   }

   public boolean apply$mcZI$sp(final int x) {
      return BoxesRunTime.unboxToBoolean(this.applyOrElse(x, PartialFunction$.MODULE$.empty()));
   }
}
