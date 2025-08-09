package scala.runtime;

import scala.Function1$mcDI$sp;
import scala.PartialFunction$;

public abstract class AbstractPartialFunction$mcDI$sp extends AbstractPartialFunction implements Function1$mcDI$sp {
   public double apply(final int x) {
      return this.apply$mcDI$sp(x);
   }

   public double apply$mcDI$sp(final int x) {
      return BoxesRunTime.unboxToDouble(this.applyOrElse(x, PartialFunction$.MODULE$.empty()));
   }
}
