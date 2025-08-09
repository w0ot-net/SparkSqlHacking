package breeze.stats.mcmc;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003$\u0001\u0011\u0005A\u0005C\u0003)\u0001\u0011\u0005\u0011\u0006C\u00032\u0001\u0011\u0005#GA\u000eTs6lW\r\u001e:jG6+GO]8q_2L7\u000fS1ti&twm\u001d\u0006\u0003\r\u001d\tA!\\2nG*\u0011\u0001\"C\u0001\u0006gR\fGo\u001d\u0006\u0002\u0015\u00051!M]3fu\u0016\u001c\u0001!\u0006\u0002\u000e5M\u0019\u0001A\u0004\u000b\u0011\u0005=\u0011R\"\u0001\t\u000b\u0003E\tQa]2bY\u0006L!a\u0005\t\u0003\r\u0005s\u0017PU3g!\r)b\u0003G\u0007\u0002\u000b%\u0011q#\u0002\u0002\u0013\u001b\u0016$(o\u001c9pY&\u001c\b*Y:uS:<7\u000f\u0005\u0002\u001a51\u0001A!B\u000e\u0001\u0005\u0004a\"!\u0001+\u0012\u0005u\u0001\u0003CA\b\u001f\u0013\ty\u0002CA\u0004O_RD\u0017N\\4\u0011\u0005=\t\u0013B\u0001\u0012\u0011\u0005\r\te._\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\u0015\u0002\"a\u0004\u0014\n\u0005\u001d\u0002\"\u0001B+oSR\f\u0001\u0004\\8h)J\fgn]5uS>t\u0007K]8cC\nLG.\u001b;z)\rQSf\f\t\u0003\u001f-J!\u0001\f\t\u0003\r\u0011{WO\u00197f\u0011\u0015q#\u00011\u0001\u0019\u0003\u0015\u0019H/\u0019:u\u0011\u0015\u0001$\u00011\u0001\u0019\u0003\r)g\u000eZ\u0001\u0013Y><G*[6fY&Dwn\u001c3SCRLw\u000eF\u0002+gQBQAL\u0002A\u0002aAQ\u0001M\u0002A\u0002a\u0001"
)
public interface SymmetricMetropolisHastings extends MetropolisHastings {
   // $FF: synthetic method
   static double logTransitionProbability$(final SymmetricMetropolisHastings $this, final Object start, final Object end) {
      return $this.logTransitionProbability(start, end);
   }

   default double logTransitionProbability(final Object start, final Object end) {
      return (double)0.0F;
   }

   // $FF: synthetic method
   static double logLikelihoodRatio$(final SymmetricMetropolisHastings $this, final Object start, final Object end) {
      return $this.logLikelihoodRatio(start, end);
   }

   default double logLikelihoodRatio(final Object start, final Object end) {
      return this.logLikelihood(end) - this.logLikelihood(start);
   }

   static void $init$(final SymmetricMetropolisHastings $this) {
   }
}
