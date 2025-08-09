package breeze.stats.mcmc;

import breeze.stats.distributions.RandBasis;
import java.io.Serializable;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Some;
import scala.Tuple6;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ArbitraryMetropolisHastings$ implements Serializable {
   public static final ArbitraryMetropolisHastings$ MODULE$ = new ArbitraryMetropolisHastings$();

   public int $lessinit$greater$default$5() {
      return 0;
   }

   public int $lessinit$greater$default$6() {
      return 0;
   }

   public final String toString() {
      return "ArbitraryMetropolisHastings";
   }

   public ArbitraryMetropolisHastings apply(final Function1 logLikelihood, final Function1 proposal, final Function2 logProposalDensity, final Object init, final int burnIn, final int dropCount, final RandBasis rand) {
      return new ArbitraryMetropolisHastings(logLikelihood, proposal, logProposalDensity, init, burnIn, dropCount, rand);
   }

   public int apply$default$5() {
      return 0;
   }

   public int apply$default$6() {
      return 0;
   }

   public Option unapply(final ArbitraryMetropolisHastings x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple6(x$0.logLikelihood(), x$0.proposal(), x$0.logProposalDensity(), x$0.init(), BoxesRunTime.boxToInteger(x$0.burnIn()), BoxesRunTime.boxToInteger(x$0.dropCount()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ArbitraryMetropolisHastings$.class);
   }

   private ArbitraryMetropolisHastings$() {
   }
}
