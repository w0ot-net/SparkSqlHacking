package scala.runtime;

import scala.Function1$mcVD$sp;
import scala.PartialFunction$;

public abstract class AbstractPartialFunction$mcVD$sp extends AbstractPartialFunction implements Function1$mcVD$sp {
   public void apply(final double x) {
      this.apply$mcVD$sp(x);
   }

   public void apply$mcVD$sp(final double x) {
      this.applyOrElse(x, PartialFunction$.MODULE$.empty());
   }
}
