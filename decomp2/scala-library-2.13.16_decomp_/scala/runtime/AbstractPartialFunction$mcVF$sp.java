package scala.runtime;

import scala.Function1$mcVF$sp;
import scala.PartialFunction$;

public abstract class AbstractPartialFunction$mcVF$sp extends AbstractPartialFunction implements Function1$mcVF$sp {
   public void apply(final float x) {
      this.apply$mcVF$sp(x);
   }

   public void apply$mcVF$sp(final float x) {
      this.applyOrElse(x, PartialFunction$.MODULE$.empty());
   }
}
