package breeze.stats.distributions;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Md\u0001B\u000e\u001d\u0001\u000eB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005\u007f!A1\t\u0001BK\u0002\u0013\u0005a\b\u0003\u0005E\u0001\tE\t\u0015!\u0003@\u0011\u0015)\u0005\u0001\"\u0001G\u0011\u001dQ\u0005A1A\u0005\u0016-Ca!\u0017\u0001!\u0002\u001ba\u0005\"\u0002.\u0001\t\u0003q\u0004bB.\u0001\u0003\u0003%\t\u0001\u0018\u0005\b?\u0002\t\n\u0011\"\u0001a\u0011\u001dY\u0007!%A\u0005\u0002\u0001Dq\u0001\u001c\u0001\u0002\u0002\u0013\u0005S\u000eC\u0004w\u0001\u0005\u0005I\u0011A<\t\u000fm\u0004\u0011\u0011!C\u0001y\"I\u0011Q\u0001\u0001\u0002\u0002\u0013\u0005\u0013q\u0001\u0005\n\u0003+\u0001\u0011\u0011!C\u0001\u0003/A\u0011\"!\t\u0001\u0003\u0003%\t%a\t\t\u0013\u0005\u001d\u0002!!A\u0005B\u0005%\u0002\"CA\u0016\u0001\u0005\u0005I\u0011IA\u0017\u0011%\ty\u0003AA\u0001\n\u0003\n\tdB\u0004\u00026qA\t!a\u000e\u0007\rma\u0002\u0012AA\u001d\u0011\u0019)e\u0003\"\u0001\u0002L!I\u0011Q\n\f\u0002\u0002\u0013\u0005\u0015q\n\u0005\n\u0003+2\u0012\u0011!CA\u0003/B\u0011\"!\u001b\u0017\u0003\u0003%I!a\u001b\u0003\u001b\u0019#\u0015n\u001d;sS\n,H/[8o\u0015\tib$A\u0007eSN$(/\u001b2vi&|gn\u001d\u0006\u0003?\u0001\nQa\u001d;biNT\u0011!I\u0001\u0007EJ,WM_3\u0004\u0001M)\u0001\u0001\n\u0016/cA\u0011Q\u0005K\u0007\u0002M)\tq%A\u0003tG\u0006d\u0017-\u0003\u0002*M\t1\u0011I\\=SK\u001a\u0004\"a\u000b\u0017\u000e\u0003qI!!\f\u000f\u00039\u0005\u0003\u0018m\u00195f\u0007>tG/\u001b8v_V\u001cH)[:ue&\u0014W\u000f^5p]B\u0011QeL\u0005\u0003a\u0019\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00023u9\u00111\u0007\u000f\b\u0003i]j\u0011!\u000e\u0006\u0003m\t\na\u0001\u0010:p_Rt\u0014\"A\u0014\n\u0005e2\u0013a\u00029bG.\fw-Z\u0005\u0003wq\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!!\u000f\u0014\u000239,X.\u001a:bi>\u0014H)Z4sK\u0016\u001cxJ\u001a$sK\u0016$w.\\\u000b\u0002\u007fA\u0011Q\u0005Q\u0005\u0003\u0003\u001a\u0012a\u0001R8vE2,\u0017A\u00078v[\u0016\u0014\u0018\r^8s\t\u0016<'/Z3t\u001f\u001a4%/Z3e_6\u0004\u0013a\u00073f]>l\u0017N\\1u_J$Um\u001a:fKN|eM\u0012:fK\u0012|W.\u0001\u000feK:|W.\u001b8bi>\u0014H)Z4sK\u0016\u001cxJ\u001a$sK\u0016$w.\u001c\u0011\u0002\rqJg.\u001b;?)\r9\u0005*\u0013\t\u0003W\u0001AQ!P\u0003A\u0002}BQaQ\u0003A\u0002}\nQ!\u001b8oKJ,\u0012\u0001\u0014\t\u0003\u001bbk\u0011A\u0014\u0006\u0003\u001fB\u000bA\u0002Z5tiJL'-\u001e;j_:T!!\u0015*\u0002\u000b5\fG\u000f[\u001a\u000b\u0005M#\u0016aB2p[6|gn\u001d\u0006\u0003+Z\u000ba!\u00199bG\",'\"A,\u0002\u0007=\u0014x-\u0003\u0002\u001c\u001d\u00061\u0011N\u001c8fe\u0002\nA!\\8eK\u0006!1m\u001c9z)\r9UL\u0018\u0005\b{%\u0001\n\u00111\u0001@\u0011\u001d\u0019\u0015\u0002%AA\u0002}\nabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001bU\ty$mK\u0001d!\t!\u0017.D\u0001f\u0015\t1w-A\u0005v]\u000eDWmY6fI*\u0011\u0001NJ\u0001\u000bC:tw\u000e^1uS>t\u0017B\u00016f\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\ta\u000e\u0005\u0002pi6\t\u0001O\u0003\u0002re\u0006!A.\u00198h\u0015\u0005\u0019\u0018\u0001\u00026bm\u0006L!!\u001e9\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005A\bCA\u0013z\u0013\tQhEA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000fF\u0002~\u0003\u0003\u0001\"!\n@\n\u0005}4#aA!os\"A\u00111\u0001\b\u0002\u0002\u0003\u0007\u00010A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u0013\u0001R!a\u0003\u0002\u0012ul!!!\u0004\u000b\u0007\u0005=a%\u0001\u0006d_2dWm\u0019;j_:LA!a\u0005\u0002\u000e\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\tI\"a\b\u0011\u0007\u0015\nY\"C\u0002\u0002\u001e\u0019\u0012qAQ8pY\u0016\fg\u000e\u0003\u0005\u0002\u0004A\t\t\u00111\u0001~\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u00079\f)\u0003\u0003\u0005\u0002\u0004E\t\t\u00111\u0001y\u0003!A\u0017m\u001d5D_\u0012,G#\u0001=\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012A\\\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005e\u00111\u0007\u0005\t\u0003\u0007!\u0012\u0011!a\u0001{\u0006ia\tR5tiJL'-\u001e;j_:\u0004\"a\u000b\f\u0014\rY!\u00131HA!!\u0015Y\u0013QH H\u0013\r\ty\u0004\b\u0002$\u0007>tG/\u001b8v_V\u001cH)[:ue&\u0014W\u000f^5p]V3UO\\2Qe>4\u0018\u000eZ3s!\u0011\t\u0019%!\u0013\u000e\u0005\u0005\u0015#bAA$e\u0006\u0011\u0011n\\\u0005\u0004w\u0005\u0015CCAA\u001c\u0003\u0015\t\u0007\u000f\u001d7z)\u00159\u0015\u0011KA*\u0011\u0015i\u0004\u00041\u0001@\u0011\u0015\u0019\u0005\u00041\u0001@\u0003\u001d)h.\u00199qYf$B!!\u0017\u0002fA)Q%a\u0017\u0002`%\u0019\u0011Q\f\u0014\u0003\r=\u0003H/[8o!\u0015)\u0013\u0011M @\u0013\r\t\u0019G\n\u0002\u0007)V\u0004H.\u001a\u001a\t\u0011\u0005\u001d\u0014$!AA\u0002\u001d\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\ti\u0007E\u0002p\u0003_J1!!\u001dq\u0005\u0019y%M[3di\u0002"
)
public class FDistribution implements ApacheContinuousDistribution, Product {
   private final double numeratorDegreesOfFreedom;
   private final double denominatorDegreesOfFreedom;
   private final org.apache.commons.math3.distribution.FDistribution inner;
   private double logNormalizer;
   private double normalizer;
   private volatile byte bitmap$0;

   public static Option unapply(final FDistribution x$0) {
      return FDistribution$.MODULE$.unapply(x$0);
   }

   public static ContinuousDistributionUFuncProvider.ContinuousDistrUFuncWrapper ContinuousDistrUFuncWrapper(final ContinuousDistr dist) {
      return FDistribution$.MODULE$.ContinuousDistrUFuncWrapper(dist);
   }

   public static ContinuousDistributionUFuncProvider.basicImpl$ basicImpl() {
      return FDistribution$.MODULE$.basicImpl();
   }

   public static Object withSink(final Object s) {
      return FDistribution$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return FDistribution$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return FDistribution$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return FDistribution$.MODULE$.inPlace(v, impl);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
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

   public double numeratorDegreesOfFreedom() {
      return this.numeratorDegreesOfFreedom;
   }

   public double denominatorDegreesOfFreedom() {
      return this.denominatorDegreesOfFreedom;
   }

   public final org.apache.commons.math3.distribution.FDistribution inner() {
      return this.inner;
   }

   public double mode() {
      return (this.numeratorDegreesOfFreedom() - (double)2) / this.numeratorDegreesOfFreedom() * (this.denominatorDegreesOfFreedom() / (this.denominatorDegreesOfFreedom() + (double)2));
   }

   public FDistribution copy(final double numeratorDegreesOfFreedom, final double denominatorDegreesOfFreedom) {
      return new FDistribution(numeratorDegreesOfFreedom, denominatorDegreesOfFreedom);
   }

   public double copy$default$1() {
      return this.numeratorDegreesOfFreedom();
   }

   public double copy$default$2() {
      return this.denominatorDegreesOfFreedom();
   }

   public String productPrefix() {
      return "FDistribution";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.numeratorDegreesOfFreedom());
            break;
         case 1:
            var10000 = BoxesRunTime.boxToDouble(this.denominatorDegreesOfFreedom());
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof FDistribution;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "numeratorDegreesOfFreedom";
            break;
         case 1:
            var10000 = "denominatorDegreesOfFreedom";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.numeratorDegreesOfFreedom()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.denominatorDegreesOfFreedom()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label51: {
            boolean var2;
            if (x$1 instanceof FDistribution) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               FDistribution var4 = (FDistribution)x$1;
               if (this.numeratorDegreesOfFreedom() == var4.numeratorDegreesOfFreedom() && this.denominatorDegreesOfFreedom() == var4.denominatorDegreesOfFreedom() && var4.canEqual(this)) {
                  break label51;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public FDistribution(final double numeratorDegreesOfFreedom, final double denominatorDegreesOfFreedom) {
      this.numeratorDegreesOfFreedom = numeratorDegreesOfFreedom;
      this.denominatorDegreesOfFreedom = denominatorDegreesOfFreedom;
      Density.$init$(this);
      Rand.$init$(this);
      ContinuousDistr.$init$(this);
      ApacheContinuousDistribution.$init$(this);
      Product.$init$(this);
      this.inner = new org.apache.commons.math3.distribution.FDistribution(numeratorDegreesOfFreedom, denominatorDegreesOfFreedom);
   }
}
