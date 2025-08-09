package scala.runtime;

import scala.Function1$mcIJ$sp;
import scala.PartialFunction$;

public abstract class AbstractPartialFunction$mcIJ$sp extends AbstractPartialFunction implements Function1$mcIJ$sp {
   public int apply(final long x) {
      return this.apply$mcIJ$sp(x);
   }

   public int apply$mcIJ$sp(final long x) {
      return BoxesRunTime.unboxToInt(this.applyOrElse(x, PartialFunction$.MODULE$.empty()));
   }
}
