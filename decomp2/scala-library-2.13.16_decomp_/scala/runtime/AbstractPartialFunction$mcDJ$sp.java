package scala.runtime;

import scala.Function1$mcDJ$sp;
import scala.PartialFunction$;

public abstract class AbstractPartialFunction$mcDJ$sp extends AbstractPartialFunction implements Function1$mcDJ$sp {
   public double apply(final long x) {
      return this.apply$mcDJ$sp(x);
   }

   public double apply$mcDJ$sp(final long x) {
      return BoxesRunTime.unboxToDouble(this.applyOrElse(x, PartialFunction$.MODULE$.empty()));
   }
}
