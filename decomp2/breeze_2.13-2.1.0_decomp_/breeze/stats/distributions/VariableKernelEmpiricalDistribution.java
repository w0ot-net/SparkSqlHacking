package breeze.stats.distributions;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import org.apache.commons.math3.random.EmpiricalDistribution;
import scala.Function1;
import scala.Option;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-4A\u0001D\u0007\u0001)!Aq\u0004\u0001B\u0001B\u0003%\u0001\u0005\u0003\u0005'\u0001\t\u0005\t\u0015!\u0003(\u0011\u0015Q\u0003\u0001\"\u0001,\u0011\u0015Q\u0003\u0001\"\u00010\u0011\u001d9\u0004A1A\u0005\u0016aBaa\u0012\u0001!\u0002\u001bIt!\u0002%\u000e\u0011\u0003Ie!\u0002\u0007\u000e\u0011\u0003Q\u0005\"\u0002\u0016\t\t\u00031\u0006bB,\t#\u0003%\t\u0001\u0017\u0005\bG\"\t\t\u0011\"\u0003e\u0005\r2\u0016M]5bE2,7*\u001a:oK2,U\u000e]5sS\u000e\fG\u000eR5tiJL'-\u001e;j_:T!AD\b\u0002\u001b\u0011L7\u000f\u001e:jEV$\u0018n\u001c8t\u0015\t\u0001\u0012#A\u0003ti\u0006$8OC\u0001\u0013\u0003\u0019\u0011'/Z3{K\u000e\u00011c\u0001\u0001\u00167A\u0011a#G\u0007\u0002/)\t\u0001$A\u0003tG\u0006d\u0017-\u0003\u0002\u001b/\t1\u0011I\\=SK\u001a\u0004\"\u0001H\u000f\u000e\u00035I!AH\u0007\u00039\u0005\u0003\u0018m\u00195f\u0007>tG/\u001b8v_V\u001cH)[:ue&\u0014W\u000f^5p]\u0006!A-\u0019;b!\r1\u0012eI\u0005\u0003E]\u0011Q!\u0011:sCf\u0004\"A\u0006\u0013\n\u0005\u0015:\"A\u0002#pk\ndW-\u0001\u0005cS:\u001cu.\u001e8u!\t1\u0002&\u0003\u0002*/\t\u0019\u0011J\u001c;\u0002\rqJg.\u001b;?)\raSF\f\t\u00039\u0001AQaH\u0002A\u0002\u0001BqAJ\u0002\u0011\u0002\u0003\u0007q\u0005\u0006\u0002-a!)q\u0004\u0002a\u0001cA\u0019!'N\u0012\u000e\u0003MR!\u0001N\t\u0002\r1Lg.\u00197h\u0013\t14GA\u0006EK:\u001cXMV3di>\u0014\u0018!B5o]\u0016\u0014X#A\u001d\u0011\u0005i*U\"A\u001e\u000b\u0005qj\u0014A\u0002:b]\u0012|WN\u0003\u0002?\u007f\u0005)Q.\u0019;ig)\u0011\u0001)Q\u0001\bG>lWn\u001c8t\u0015\t\u00115)\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\t\u0006\u0019qN]4\n\u0005\u0019[$!F#na&\u0014\u0018nY1m\t&\u001cHO]5ckRLwN\\\u0001\u0007S:tWM\u001d\u0011\u0002GY\u000b'/[1cY\u0016\\UM\u001d8fY\u0016k\u0007/\u001b:jG\u0006dG)[:ue&\u0014W\u000f^5p]B\u0011A\u0004C\n\u0005\u0011UYe\n\u0005\u0003\u001d\u0019\u000eb\u0013BA'\u000e\u0005\r\u001auN\u001c;j]V|Wo\u001d#jgR\u0014\u0018NY;uS>tWKR;oGB\u0013xN^5eKJ\u0004\"a\u0014+\u000e\u0003AS!!\u0015*\u0002\u0005%|'\"A*\u0002\t)\fg/Y\u0005\u0003+B\u0013AbU3sS\u0006d\u0017N_1cY\u0016$\u0012!S\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003eS#a\n.,\u0003m\u0003\"\u0001X1\u000e\u0003uS!AX0\u0002\u0013Ut7\r[3dW\u0016$'B\u00011\u0018\u0003)\tgN\\8uCRLwN\\\u0005\u0003Ev\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005)\u0007C\u00014j\u001b\u00059'B\u00015S\u0003\u0011a\u0017M\\4\n\u0005)<'AB(cU\u0016\u001cG\u000f"
)
public class VariableKernelEmpiricalDistribution implements ApacheContinuousDistribution {
   private final EmpiricalDistribution inner;
   private double logNormalizer;
   private double normalizer;
   private volatile byte bitmap$0;

   public static int $lessinit$greater$default$2() {
      return VariableKernelEmpiricalDistribution$.MODULE$.$lessinit$greater$default$2();
   }

   public static ContinuousDistributionUFuncProvider.ContinuousDistrUFuncWrapper ContinuousDistrUFuncWrapper(final ContinuousDistr dist) {
      return VariableKernelEmpiricalDistribution$.MODULE$.ContinuousDistrUFuncWrapper(dist);
   }

   public static ContinuousDistributionUFuncProvider.basicImpl$ basicImpl() {
      return VariableKernelEmpiricalDistribution$.MODULE$.basicImpl();
   }

   public static Object withSink(final Object s) {
      return VariableKernelEmpiricalDistribution$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return VariableKernelEmpiricalDistribution$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return VariableKernelEmpiricalDistribution$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return VariableKernelEmpiricalDistribution$.MODULE$.inPlace(v, impl);
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

   public final EmpiricalDistribution inner() {
      return this.inner;
   }

   public VariableKernelEmpiricalDistribution(final double[] data, final int binCount) {
      Density.$init$(this);
      Rand.$init$(this);
      ContinuousDistr.$init$(this);
      ApacheContinuousDistribution.$init$(this);
      this.inner = new EmpiricalDistribution(binCount);
      this.inner().load(data);
   }

   public VariableKernelEmpiricalDistribution(final DenseVector data) {
      this(data.data$mcD$sp(), VariableKernelEmpiricalDistribution$.MODULE$.$lessinit$greater$default$2());
   }
}
