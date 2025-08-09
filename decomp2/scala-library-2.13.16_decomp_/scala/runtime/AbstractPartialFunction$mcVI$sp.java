package scala.runtime;

import scala.Function1$mcVI$sp;
import scala.PartialFunction$;

public abstract class AbstractPartialFunction$mcVI$sp extends AbstractPartialFunction implements Function1$mcVI$sp {
   public void apply(final int x) {
      this.apply$mcVI$sp(x);
   }

   public void apply$mcVI$sp(final int x) {
      this.applyOrElse(x, PartialFunction$.MODULE$.empty());
   }
}
