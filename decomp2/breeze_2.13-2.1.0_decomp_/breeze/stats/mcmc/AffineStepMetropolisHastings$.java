package breeze.stats.mcmc;

import breeze.math.VectorSpace;
import breeze.stats.distributions.Rand;
import breeze.stats.distributions.Rand$;
import breeze.stats.distributions.RandBasis;
import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.Tuple5;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class AffineStepMetropolisHastings$ implements Serializable {
   public static final AffineStepMetropolisHastings$ MODULE$ = new AffineStepMetropolisHastings$();

   public int $lessinit$greater$default$4() {
      return 0;
   }

   public int $lessinit$greater$default$5() {
      return 0;
   }

   public RandBasis $lessinit$greater$default$6(final Function1 logLikelihood, final Rand proposalStep, final Object init, final int burnIn, final int dropCount) {
      return Rand$.MODULE$;
   }

   public final String toString() {
      return "AffineStepMetropolisHastings";
   }

   public AffineStepMetropolisHastings apply(final Function1 logLikelihood, final Rand proposalStep, final Object init, final int burnIn, final int dropCount, final RandBasis rand, final VectorSpace vectorSpace) {
      return new AffineStepMetropolisHastings(logLikelihood, proposalStep, init, burnIn, dropCount, rand, vectorSpace);
   }

   public int apply$default$4() {
      return 0;
   }

   public int apply$default$5() {
      return 0;
   }

   public RandBasis apply$default$6(final Function1 logLikelihood, final Rand proposalStep, final Object init, final int burnIn, final int dropCount) {
      return Rand$.MODULE$;
   }

   public Option unapply(final AffineStepMetropolisHastings x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(x$0.logLikelihood(), x$0.proposalStep(), x$0.init(), BoxesRunTime.boxToInteger(x$0.burnIn()), BoxesRunTime.boxToInteger(x$0.dropCount()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AffineStepMetropolisHastings$.class);
   }

   private AffineStepMetropolisHastings$() {
   }
}
