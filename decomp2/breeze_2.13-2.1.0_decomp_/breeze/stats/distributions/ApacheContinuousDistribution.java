package breeze.stats.distributions;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import scala.math.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054q!\u0004\b\u0011\u0002\u0007\u0005Q\u0003C\u0003*\u0001\u0011\u0005!\u0006C\u0004/\u0001\t\u0007i\u0011C\u0018\t\u000by\u0002A\u0011A \t\u000b\t\u0003A\u0011I\"\t\u0011\u0015\u0003\u0001R1A\u0005\u0002\u0019CQa\u0012\u0001\u0005\u0002!CQ!\u0013\u0001\u0005\u0002)CQa\u0015\u0001\u0005\u0002QCQ\u0001\u0017\u0001\u0005\u0002eCQ\u0001\u0018\u0001\u0005\u0002\u0019CQ!\u0018\u0001\u0005\u0002\u0019CQA\u0018\u0001\u0005B}\u0013A$\u00119bG\",7i\u001c8uS:,x.^:ESN$(/\u001b2vi&|gN\u0003\u0002\u0010!\u0005iA-[:ue&\u0014W\u000f^5p]NT!!\u0005\n\u0002\u000bM$\u0018\r^:\u000b\u0003M\taA\u0019:fKj,7\u0001A\n\u0006\u0001Ya2E\n\t\u0003/ii\u0011\u0001\u0007\u0006\u00023\u0005)1oY1mC&\u00111\u0004\u0007\u0002\u0007\u0003:L(+\u001a4\u0011\u0007uq\u0002%D\u0001\u000f\u0013\tybBA\bD_:$\u0018N\\;pkN$\u0015n\u001d;s!\t9\u0012%\u0003\u0002#1\t1Ai\\;cY\u0016\u0004\"!\b\u0013\n\u0005\u0015r!A\u0002%bg\u000e#g\r\u0005\u0002\u001eO%\u0011\u0001F\u0004\u0002\u000e\u0011\u0006\u001c\u0018J\u001c<feN,7\t\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005Y\u0003CA\f-\u0013\ti\u0003D\u0001\u0003V]&$\u0018!B5o]\u0016\u0014X#\u0001\u0019\u0011\u0005EbT\"\u0001\u001a\u000b\u0005M\"\u0014\u0001\u00043jgR\u0014\u0018NY;uS>t'BA\u001b7\u0003\u0015i\u0017\r\u001e54\u0015\t9\u0004(A\u0004d_6lwN\\:\u000b\u0005eR\u0014AB1qC\u000eDWMC\u0001<\u0003\ry'oZ\u0005\u0003{I\u0012\u0001$\u00112tiJ\f7\r\u001e*fC2$\u0015n\u001d;sS\n,H/[8o\u0003I)hN\\8s[\u0006d\u0017N_3e\u0019><\u0007\u000b\u001a4\u0015\u0005\u0001\u0002\u0005\"B!\u0004\u0001\u0004\u0001\u0013!\u0001=\u0002\u0007A$g\r\u0006\u0002!\t\")\u0011\t\u0002a\u0001A\u0005iAn\\4O_Jl\u0017\r\\5{KJ,\u0012\u0001I\u0001\u0005IJ\fw\u000fF\u0001!\u0003!!'/Y<NC:LHCA&O!\r9B\nI\u0005\u0003\u001bb\u0011Q!\u0011:sCfDQaT\u0004A\u0002A\u000b\u0011A\u001c\t\u0003/EK!A\u0015\r\u0003\u0007%sG/A\u0006qe>\u0014\u0017MY5mSRLHc\u0001\u0011V-\")\u0011\t\u0003a\u0001A!)q\u000b\u0003a\u0001A\u0005\t\u00110\u0001\u0006j]Z,'o]3DI\u001a$\"\u0001\t.\t\u000bmK\u0001\u0019\u0001\u0011\u0002\u0003A\fA!\\3b]\u0006Aa/\u0019:jC:\u001cW-A\u0002dI\u001a$\"\u0001\t1\t\u000b\u0005c\u0001\u0019\u0001\u0011"
)
public interface ApacheContinuousDistribution extends ContinuousDistr, HasCdf, HasInverseCdf {
   AbstractRealDistribution inner();

   // $FF: synthetic method
   static double unnormalizedLogPdf$(final ApacheContinuousDistribution $this, final double x) {
      return $this.unnormalizedLogPdf(x);
   }

   default double unnormalizedLogPdf(final double x) {
      return .MODULE$.log(this.inner().density(x));
   }

   // $FF: synthetic method
   static double pdf$(final ApacheContinuousDistribution $this, final double x) {
      return $this.pdf(x);
   }

   default double pdf(final double x) {
      return this.inner().density(x);
   }

   // $FF: synthetic method
   static double logNormalizer$(final ApacheContinuousDistribution $this) {
      return $this.logNormalizer();
   }

   default double logNormalizer() {
      return (double)1.0F;
   }

   // $FF: synthetic method
   static double draw$(final ApacheContinuousDistribution $this) {
      return $this.draw();
   }

   default double draw() {
      return this.draw$mcD$sp();
   }

   // $FF: synthetic method
   static double[] drawMany$(final ApacheContinuousDistribution $this, final int n) {
      return $this.drawMany(n);
   }

   default double[] drawMany(final int n) {
      return this.inner().sample(n);
   }

   // $FF: synthetic method
   static double probability$(final ApacheContinuousDistribution $this, final double x, final double y) {
      return $this.probability(x, y);
   }

   default double probability(final double x, final double y) {
      return this.inner().probability(x, y);
   }

   // $FF: synthetic method
   static double inverseCdf$(final ApacheContinuousDistribution $this, final double p) {
      return $this.inverseCdf(p);
   }

   default double inverseCdf(final double p) {
      return this.inner().inverseCumulativeProbability(p);
   }

   // $FF: synthetic method
   static double mean$(final ApacheContinuousDistribution $this) {
      return $this.mean();
   }

   default double mean() {
      return this.inner().getNumericalMean();
   }

   // $FF: synthetic method
   static double variance$(final ApacheContinuousDistribution $this) {
      return $this.variance();
   }

   default double variance() {
      return this.inner().getNumericalVariance();
   }

   // $FF: synthetic method
   static double cdf$(final ApacheContinuousDistribution $this, final double x) {
      return $this.cdf(x);
   }

   default double cdf(final double x) {
      return this.inner().cumulativeProbability(x);
   }

   // $FF: synthetic method
   static double draw$mcD$sp$(final ApacheContinuousDistribution $this) {
      return $this.draw$mcD$sp();
   }

   default double draw$mcD$sp() {
      return this.inner().sample();
   }

   static void $init$(final ApacheContinuousDistribution $this) {
   }
}
