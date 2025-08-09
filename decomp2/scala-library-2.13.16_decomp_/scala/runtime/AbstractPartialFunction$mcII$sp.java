package scala.runtime;

import scala.Function1$mcII$sp;
import scala.PartialFunction$;

public abstract class AbstractPartialFunction$mcII$sp extends AbstractPartialFunction implements Function1$mcII$sp {
   public int apply(final int x) {
      return this.apply$mcII$sp(x);
   }

   public int apply$mcII$sp(final int x) {
      return BoxesRunTime.unboxToInt(this.applyOrElse(x, PartialFunction$.MODULE$.empty()));
   }
}
