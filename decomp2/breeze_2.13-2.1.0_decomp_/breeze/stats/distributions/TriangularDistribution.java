package breeze.stats.distributions;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import scala.Function1;
import scala.Option;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.math.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e3A!\u0004\b\u0001+!Aa\u0005\u0001B\u0001B\u0003%1\u0005\u0003\u0005(\u0001\t\u0005\t\u0015!\u0003$\u0011!A\u0003A!A!\u0002\u0013\u0019\u0003\"B\u0015\u0001\t\u0003Q\u0003bB\u0018\u0001\u0005\u0004%)\u0002\r\u0005\u0007}\u0001\u0001\u000bQB\u0019\t\u000b}\u0002A\u0011\u0001!\t\u000b\u0005\u0003A\u0011\u0001!\b\u000b\ts\u0001\u0012A\"\u0007\u000b5q\u0001\u0012\u0001#\t\u000b%RA\u0011\u0001)\t\u000fES\u0011\u0011!C\u0005%\n1BK]5b]\u001e,H.\u0019:ESN$(/\u001b2vi&|gN\u0003\u0002\u0010!\u0005iA-[:ue&\u0014W\u000f^5p]NT!!\u0005\n\u0002\u000bM$\u0018\r^:\u000b\u0003M\taA\u0019:fKj,7\u0001A\n\u0005\u0001Ya\u0002\u0005\u0005\u0002\u001855\t\u0001DC\u0001\u001a\u0003\u0015\u00198-\u00197b\u0013\tY\u0002D\u0001\u0004B]f\u0014VM\u001a\t\u0003;yi\u0011AD\u0005\u0003?9\u0011A$\u00119bG\",7i\u001c8uS:,x.^:ESN$(/\u001b2vi&|g\u000e\u0005\u0003\u001eC\r\u001a\u0013B\u0001\u0012\u000f\u0005\u001diu.\\3oiN\u0004\"a\u0006\u0013\n\u0005\u0015B\"A\u0002#pk\ndW-A\u0001b\u0003\u0005\u0019\u0017!\u00012\u0002\rqJg.\u001b;?)\u0011YC&\f\u0018\u0011\u0005u\u0001\u0001\"\u0002\u0014\u0005\u0001\u0004\u0019\u0003\"B\u0014\u0005\u0001\u0004\u0019\u0003\"\u0002\u0015\u0005\u0001\u0004\u0019\u0013!B5o]\u0016\u0014X#A\u0019\u0011\u0005IjT\"A\u001a\u000b\u0005Q*\u0014\u0001\u00043jgR\u0014\u0018NY;uS>t'B\u0001\u001c8\u0003\u0015i\u0017\r\u001e54\u0015\tA\u0014(A\u0004d_6lwN\\:\u000b\u0005iZ\u0014AB1qC\u000eDWMC\u0001=\u0003\ry'oZ\u0005\u0003\u001bM\na!\u001b8oKJ\u0004\u0013\u0001B7pI\u0016,\u0012aI\u0001\bK:$(o\u001c9z\u0003Y!&/[1oOVd\u0017M\u001d#jgR\u0014\u0018NY;uS>t\u0007CA\u000f\u000b'\u0011Qa#\u0012%\u0011\tu15eK\u0005\u0003\u000f:\u00111eQ8oi&tWo\\;t\t&\u001cHO]5ckRLwN\\+Gk:\u001c\u0007K]8wS\u0012,'\u000f\u0005\u0002J\u001d6\t!J\u0003\u0002L\u0019\u0006\u0011\u0011n\u001c\u0006\u0002\u001b\u0006!!.\u0019<b\u0013\ty%J\u0001\u0007TKJL\u0017\r\\5{C\ndW\rF\u0001D\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005\u0019\u0006C\u0001+X\u001b\u0005)&B\u0001,M\u0003\u0011a\u0017M\\4\n\u0005a+&AB(cU\u0016\u001cG\u000f"
)
public class TriangularDistribution implements ApacheContinuousDistribution, Moments {
   private final double a;
   private final double c;
   private final double b;
   private final org.apache.commons.math3.distribution.TriangularDistribution inner;
   private double logNormalizer;
   private double normalizer;
   private volatile byte bitmap$0;

   public static ContinuousDistributionUFuncProvider.ContinuousDistrUFuncWrapper ContinuousDistrUFuncWrapper(final ContinuousDistr dist) {
      return TriangularDistribution$.MODULE$.ContinuousDistrUFuncWrapper(dist);
   }

   public static ContinuousDistributionUFuncProvider.basicImpl$ basicImpl() {
      return TriangularDistribution$.MODULE$.basicImpl();
   }

   public static Object withSink(final Object s) {
      return TriangularDistribution$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return TriangularDistribution$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return TriangularDistribution$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return TriangularDistribution$.MODULE$.inPlace(v, impl);
   }

   public double unnormalizedLogPdf(final double x) {
      return ApacheContinuousDistribution.unnormalizedLogPdf$(this, x);
   }

   public double pdf(final double x) {
      return ApacheContinuousDistribution.pdf$(this, x);
   }

   public double draw() {
      return ApacheContinuousDistribution.draw$(this);
   }

   public double[] drawMany(final int n) {
      return ApacheContinuousDistribution.drawMany$(this, n);
   }

   public double probability(final double x, final double y) {
      return ApacheContinuousDistribution.probability$(this, x, y);
   }

   public double inverseCdf(final double p) {
      return ApacheContinuousDistribution.inverseCdf$(this, p);
   }

   public double mean() {
      return ApacheContinuousDistribution.mean$(this);
   }

   public double variance() {
      return ApacheContinuousDistribution.variance$(this);
   }

   public double cdf(final double x) {
      return ApacheContinuousDistribution.cdf$(this, x);
   }

   public double draw$mcD$sp() {
      return ApacheContinuousDistribution.draw$mcD$sp$(this);
   }

   public double logPdf(final Object x) {
      return ContinuousDistr.logPdf$(this, x);
   }

   public double unnormalizedPdf(final Object x) {
      return ContinuousDistr.unnormalizedPdf$(this, x);
   }

   public double apply(final Object x) {
      return ContinuousDistr.apply$(this, x);
   }

   public double logApply(final Object x) {
      return ContinuousDistr.logApply$(this, x);
   }

   public int draw$mcI$sp() {
      return Rand.draw$mcI$sp$(this);
   }

   public Object get() {
      return Rand.get$(this);
   }

   public double get$mcD$sp() {
      return Rand.get$mcD$sp$(this);
   }

   public int get$mcI$sp() {
      return Rand.get$mcI$sp$(this);
   }

   public Option drawOpt() {
      return Rand.drawOpt$(this);
   }

   public Object sample() {
      return Rand.sample$(this);
   }

   public double sample$mcD$sp() {
      return Rand.sample$mcD$sp$(this);
   }

   public int sample$mcI$sp() {
      return Rand.sample$mcI$sp$(this);
   }

   public IndexedSeq sample(final int n) {
      return Rand.sample$(this, n);
   }

   public Iterator samples() {
      return Rand.samples$(this);
   }

   public DenseVector samplesVector(final int size, final ClassTag m) {
      return Rand.samplesVector$(this, size, m);
   }

   public DenseVector samplesVector$mcD$sp(final int size, final ClassTag m) {
      return Rand.samplesVector$mcD$sp$(this, size, m);
   }

   public DenseVector samplesVector$mcI$sp(final int size, final ClassTag m) {
      return Rand.samplesVector$mcI$sp$(this, size, m);
   }

   public Rand flatMap(final Function1 f) {
      return Rand.flatMap$(this, f);
   }

   public Rand flatMap$mcD$sp(final Function1 f) {
      return Rand.flatMap$mcD$sp$(this, f);
   }

   public Rand flatMap$mcI$sp(final Function1 f) {
      return Rand.flatMap$mcI$sp$(this, f);
   }

   public Rand map(final Function1 f) {
      return Rand.map$(this, f);
   }

   public Rand map$mcD$sp(final Function1 f) {
      return Rand.map$mcD$sp$(this, f);
   }

   public Rand map$mcI$sp(final Function1 f) {
      return Rand.map$mcI$sp$(this, f);
   }

   public void foreach(final Function1 f) {
      Rand.foreach$(this, f);
   }

   public void foreach$mcD$sp(final Function1 f) {
      Rand.foreach$mcD$sp$(this, f);
   }

   public void foreach$mcI$sp(final Function1 f) {
      Rand.foreach$mcI$sp$(this, f);
   }

   public Rand filter(final Function1 p) {
      return Rand.filter$(this, p);
   }

   public Rand filter$mcD$sp(final Function1 p) {
      return Rand.filter$mcD$sp$(this, p);
   }

   public Rand filter$mcI$sp(final Function1 p) {
      return Rand.filter$mcI$sp$(this, p);
   }

   public Rand withFilter(final Function1 p) {
      return Rand.withFilter$(this, p);
   }

   public Rand withFilter$mcD$sp(final Function1 p) {
      return Rand.withFilter$mcD$sp$(this, p);
   }

   public Rand withFilter$mcI$sp(final Function1 p) {
      return Rand.withFilter$mcI$sp$(this, p);
   }

   public Rand condition(final Function1 p) {
      return Rand.condition$(this, p);
   }

   public Rand condition$mcD$sp(final Function1 p) {
      return Rand.condition$mcD$sp$(this, p);
   }

   public Rand condition$mcI$sp(final Function1 p) {
      return Rand.condition$mcI$sp$(this, p);
   }

   private double logNormalizer$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.logNormalizer = ApacheContinuousDistribution.logNormalizer$(this);
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.logNormalizer;
   }

   public double logNormalizer() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.logNormalizer$lzycompute() : this.logNormalizer;
   }

   private double normalizer$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.normalizer = ContinuousDistr.normalizer$(this);
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.normalizer;
   }

   public double normalizer() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.normalizer$lzycompute() : this.normalizer;
   }

   public final org.apache.commons.math3.distribution.TriangularDistribution inner() {
      return this.inner;
   }

   public double mode() {
      return this.c;
   }

   public double entropy() {
      return (double)0.5F + .MODULE$.log((this.b - this.a) / (double)2);
   }

   public TriangularDistribution(final double a, final double c, final double b) {
      this.a = a;
      this.c = c;
      this.b = b;
      Density.$init$(this);
      Rand.$init$(this);
      ContinuousDistr.$init$(this);
      ApacheContinuousDistribution.$init$(this);
      this.inner = new org.apache.commons.math3.distribution.TriangularDistribution(a, c, b);
   }
}
