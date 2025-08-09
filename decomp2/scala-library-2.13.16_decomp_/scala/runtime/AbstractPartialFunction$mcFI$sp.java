package scala.runtime;

import scala.Function1$mcFI$sp;
import scala.PartialFunction$;

public abstract class AbstractPartialFunction$mcFI$sp extends AbstractPartialFunction implements Function1$mcFI$sp {
   public float apply(final int x) {
      return this.apply$mcFI$sp(x);
   }

   public float apply$mcFI$sp(final int x) {
      return BoxesRunTime.unboxToFloat(this.applyOrElse(x, PartialFunction$.MODULE$.empty()));
   }
}
