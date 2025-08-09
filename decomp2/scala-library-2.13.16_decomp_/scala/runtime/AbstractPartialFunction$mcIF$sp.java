package scala.runtime;

import scala.Function1$mcIF$sp;
import scala.PartialFunction$;

public abstract class AbstractPartialFunction$mcIF$sp extends AbstractPartialFunction implements Function1$mcIF$sp {
   public int apply(final float x) {
      return this.apply$mcIF$sp(x);
   }

   public int apply$mcIF$sp(final float x) {
      return BoxesRunTime.unboxToInt(this.applyOrElse(x, PartialFunction$.MODULE$.empty()));
   }
}
