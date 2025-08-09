package scala.runtime;

import scala.Function1$mcFJ$sp;
import scala.PartialFunction$;

public abstract class AbstractPartialFunction$mcFJ$sp extends AbstractPartialFunction implements Function1$mcFJ$sp {
   public float apply(final long x) {
      return this.apply$mcFJ$sp(x);
   }

   public float apply$mcFJ$sp(final long x) {
      return BoxesRunTime.unboxToFloat(this.applyOrElse(x, PartialFunction$.MODULE$.empty()));
   }
}
