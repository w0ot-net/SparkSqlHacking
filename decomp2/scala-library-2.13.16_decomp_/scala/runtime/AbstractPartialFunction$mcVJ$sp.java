package scala.runtime;

import scala.Function1$mcVJ$sp;
import scala.PartialFunction$;

public abstract class AbstractPartialFunction$mcVJ$sp extends AbstractPartialFunction implements Function1$mcVJ$sp {
   public void apply(final long x) {
      this.apply$mcVJ$sp(x);
   }

   public void apply$mcVJ$sp(final long x) {
      this.applyOrElse(x, PartialFunction$.MODULE$.empty());
   }
}
