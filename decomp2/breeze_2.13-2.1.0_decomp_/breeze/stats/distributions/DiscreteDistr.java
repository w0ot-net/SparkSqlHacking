package breeze.stats.distributions;

import scala.math.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00153q\u0001C\u0005\u0011\u0002\u0007\u0005\u0001\u0003C\u0003+\u0001\u0011\u00051\u0006C\u00030\u0001\u0019\u0005\u0001\u0007C\u00037\u0001\u0011\u0005q\u0007C\u0003:\u0001\u0011\u0005!\bC\u0003=\u0001\u0011\u0005Q\bC\u0003@\u0001\u0011\u0005\u0001\tC\u0003C\u0001\u0011\u00053IA\u0007ESN\u001c'/\u001a;f\t&\u001cHO\u001d\u0006\u0003\u0015-\tQ\u0002Z5tiJL'-\u001e;j_:\u001c(B\u0001\u0007\u000e\u0003\u0015\u0019H/\u0019;t\u0015\u0005q\u0011A\u00022sK\u0016TXm\u0001\u0001\u0016\u0005Eq2\u0003\u0002\u0001\u00131\u001d\u0002\"a\u0005\f\u000e\u0003QQ\u0011!F\u0001\u0006g\u000e\fG.Y\u0005\u0003/Q\u0011a!\u00118z%\u00164\u0007cA\r\u001b95\t\u0011\"\u0003\u0002\u001c\u0013\t9A)\u001a8tSRL\bCA\u000f\u001f\u0019\u0001!Qa\b\u0001C\u0002\u0001\u0012\u0011\u0001V\t\u0003C\u0011\u0002\"a\u0005\u0012\n\u0005\r\"\"a\u0002(pi\"Lgn\u001a\t\u0003'\u0015J!A\n\u000b\u0003\u0007\u0005s\u0017\u0010E\u0002\u001aQqI!!K\u0005\u0003\tI\u000bg\u000eZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u00031\u0002\"aE\u0017\n\u00059\"\"\u0001B+oSR\fQ\u0002\u001d:pE\u0006\u0014\u0017\u000e\\5us>3GCA\u00195!\t\u0019\"'\u0003\u00024)\t1Ai\\;cY\u0016DQ!\u000e\u0002A\u0002q\t\u0011\u0001_\u0001\u0011Y><\u0007K]8cC\nLG.\u001b;z\u001f\u001a$\"!\r\u001d\t\u000bU\u001a\u0001\u0019\u0001\u000f\u00023Utgn\u001c:nC2L'0\u001a3Qe>\u0014\u0017MY5mSRLxJ\u001a\u000b\u0003cmBQ!\u000e\u0003A\u0002q\tA$\u001e8o_Jl\u0017\r\\5{K\u0012dun\u001a)s_\n\f'-\u001b7jif|e\r\u0006\u00022}!)Q'\u0002a\u00019\u0005)\u0011\r\u001d9msR\u0011\u0011'\u0011\u0005\u0006k\u0019\u0001\r\u0001H\u0001\tY><\u0017\t\u001d9msR\u0011\u0011\u0007\u0012\u0005\u0006k\u001d\u0001\r\u0001\b"
)
public interface DiscreteDistr extends Density, Rand {
   double probabilityOf(final Object x);

   // $FF: synthetic method
   static double logProbabilityOf$(final DiscreteDistr $this, final Object x) {
      return $this.logProbabilityOf(x);
   }

   default double logProbabilityOf(final Object x) {
      return .MODULE$.log(this.probabilityOf(x));
   }

   // $FF: synthetic method
   static double unnormalizedProbabilityOf$(final DiscreteDistr $this, final Object x) {
      return $this.unnormalizedProbabilityOf(x);
   }

   default double unnormalizedProbabilityOf(final Object x) {
      return this.probabilityOf(x);
   }

   // $FF: synthetic method
   static double unnormalizedLogProbabilityOf$(final DiscreteDistr $this, final Object x) {
      return $this.unnormalizedLogProbabilityOf(x);
   }

   default double unnormalizedLogProbabilityOf(final Object x) {
      return .MODULE$.log(this.unnormalizedProbabilityOf(x));
   }

   // $FF: synthetic method
   static double apply$(final DiscreteDistr $this, final Object x) {
      return $this.apply(x);
   }

   default double apply(final Object x) {
      return this.unnormalizedProbabilityOf(x);
   }

   // $FF: synthetic method
   static double logApply$(final DiscreteDistr $this, final Object x) {
      return $this.logApply(x);
   }

   default double logApply(final Object x) {
      return this.unnormalizedLogProbabilityOf(x);
   }

   static void $init$(final DiscreteDistr $this) {
   }
}
