package breeze.stats.distributions;

import scala.math.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)3qAC\u0006\u0011\u0002\u0007\u0005!\u0003C\u0003-\u0001\u0011\u0005Q\u0006C\u00032\u0001\u0011\u0005!\u0007C\u00039\u0001\u0011\u0005\u0011\bC\u0003<\u0001\u0011\u0005A\bC\u0003?\u0001\u0019\u0005q\bC\u0003B\u0001\u0019\u0005!\t\u0003\u0005D\u0001!\u0015\r\u0011\"\u0001C\u0011\u0015!\u0005\u0001\"\u0001F\u0011\u00159\u0005\u0001\"\u0011I\u0005=\u0019uN\u001c;j]V|Wo\u001d#jgR\u0014(B\u0001\u0007\u000e\u00035!\u0017n\u001d;sS\n,H/[8og*\u0011abD\u0001\u0006gR\fGo\u001d\u0006\u0002!\u00051!M]3fu\u0016\u001c\u0001!\u0006\u0002\u0014AM!\u0001\u0001\u0006\u000e*!\t)\u0002$D\u0001\u0017\u0015\u00059\u0012!B:dC2\f\u0017BA\r\u0017\u0005\u0019\te.\u001f*fMB\u00191\u0004\b\u0010\u000e\u0003-I!!H\u0006\u0003\u000f\u0011+gn]5usB\u0011q\u0004\t\u0007\u0001\t\u0015\t\u0003A1\u0001#\u0005\u0005!\u0016CA\u0012'!\t)B%\u0003\u0002&-\t9aj\u001c;iS:<\u0007CA\u000b(\u0013\tAcCA\u0002B]f\u00042a\u0007\u0016\u001f\u0013\tY3B\u0001\u0003SC:$\u0017A\u0002\u0013j]&$H\u0005F\u0001/!\t)r&\u0003\u00021-\t!QK\\5u\u0003\r\u0001HM\u001a\u000b\u0003gY\u0002\"!\u0006\u001b\n\u0005U2\"A\u0002#pk\ndW\rC\u00038\u0005\u0001\u0007a$A\u0001y\u0003\u0019awn\u001a)eMR\u00111G\u000f\u0005\u0006o\r\u0001\rAH\u0001\u0010k:twN]7bY&TX\r\u001a)eMR\u00111'\u0010\u0005\u0006o\u0011\u0001\rAH\u0001\u0013k:twN]7bY&TX\r\u001a'pOB#g\r\u0006\u00024\u0001\")q'\u0002a\u0001=\u0005iAn\\4O_Jl\u0017\r\\5{KJ,\u0012aM\u0001\u000b]>\u0014X.\u00197ju\u0016\u0014\u0018!B1qa2LHCA\u001aG\u0011\u00159\u0004\u00021\u0001\u001f\u0003!awnZ!qa2LHCA\u001aJ\u0011\u00159\u0014\u00021\u0001\u001f\u0001"
)
public interface ContinuousDistr extends Density, Rand {
   // $FF: synthetic method
   static double pdf$(final ContinuousDistr $this, final Object x) {
      return $this.pdf(x);
   }

   default double pdf(final Object x) {
      return .MODULE$.exp(this.logPdf(x));
   }

   // $FF: synthetic method
   static double logPdf$(final ContinuousDistr $this, final Object x) {
      return $this.logPdf(x);
   }

   default double logPdf(final Object x) {
      return this.unnormalizedLogPdf(x) - this.logNormalizer();
   }

   // $FF: synthetic method
   static double unnormalizedPdf$(final ContinuousDistr $this, final Object x) {
      return $this.unnormalizedPdf(x);
   }

   default double unnormalizedPdf(final Object x) {
      return .MODULE$.exp(this.unnormalizedLogPdf(x));
   }

   double unnormalizedLogPdf(final Object x);

   double logNormalizer();

   // $FF: synthetic method
   static double normalizer$(final ContinuousDistr $this) {
      return $this.normalizer();
   }

   default double normalizer() {
      return .MODULE$.exp(-this.logNormalizer());
   }

   // $FF: synthetic method
   static double apply$(final ContinuousDistr $this, final Object x) {
      return $this.apply(x);
   }

   default double apply(final Object x) {
      return this.unnormalizedPdf(x);
   }

   // $FF: synthetic method
   static double logApply$(final ContinuousDistr $this, final Object x) {
      return $this.logApply(x);
   }

   default double logApply(final Object x) {
      return this.unnormalizedLogPdf(x);
   }

   static void $init$(final ContinuousDistr $this) {
   }
}
