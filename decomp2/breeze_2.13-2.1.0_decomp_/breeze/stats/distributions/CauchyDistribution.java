package breeze.stats.distributions;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import org.apache.commons.math3.random.RandomGenerator;
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
   bytes = "\u0006\u0005\u0005\rf\u0001B\u0010!\u0001\u001eB\u0001\"\u0011\u0001\u0003\u0016\u0004%\tA\u0011\u0005\t\r\u0002\u0011\t\u0012)A\u0005\u0007\"Aq\t\u0001BK\u0002\u0013\u0005!\t\u0003\u0005I\u0001\tE\t\u0015!\u0003D\u0011!I\u0005A!A!\u0002\u0017Q\u0005\"B'\u0001\t\u0003q\u0005b\u0002+\u0001\u0005\u0004%\t!\u0016\u0005\u0007I\u0002\u0001\u000b\u0011\u0002,\t\u000f\u0015\u0004!\u0019!C\u0001\u0005\"1a\r\u0001Q\u0001\n\rCqa\u001a\u0001C\u0002\u0013U\u0001\u000e\u0003\u0004o\u0001\u0001\u0006i!\u001b\u0005\b_\u0002\t\t\u0011\"\u0001q\u0011\u001d)\b!%A\u0005\u0002YD\u0001\"a\u0001\u0001#\u0003%\tA\u001e\u0005\n\u0003\u000b\u0001\u0011\u0011!C!\u0003\u000fA\u0011\"!\u0007\u0001\u0003\u0003%\t!a\u0007\t\u0013\u0005\r\u0002!!A\u0005\u0002\u0005\u0015\u0002\"CA\u0019\u0001\u0005\u0005I\u0011IA\u001a\u0011%\t\t\u0005AA\u0001\n\u0003\t\u0019\u0005C\u0005\u0002N\u0001\t\t\u0011\"\u0011\u0002P!I\u00111\u000b\u0001\u0002\u0002\u0013\u0005\u0013Q\u000b\u0005\n\u0003/\u0002\u0011\u0011!C!\u00033B\u0011\"a\u0017\u0001\u0003\u0003%\t%!\u0018\b\u000f\u0005\u0005\u0004\u0005#\u0001\u0002d\u00191q\u0004\tE\u0001\u0003KBa!\u0014\u000e\u0005\u0002\u0005]\u0004\"CA=5\u0005\u0005I\u0011QA>\u0011%\t)IGA\u0001\n\u0003\u000b9\tC\u0005\u0002\u001aj\t\t\u0011\"\u0003\u0002\u001c\n\u00112)Y;dQf$\u0015n\u001d;sS\n,H/[8o\u0015\t\t#%A\u0007eSN$(/\u001b2vi&|gn\u001d\u0006\u0003G\u0011\nQa\u001d;biNT\u0011!J\u0001\u0007EJ,WM_3\u0004\u0001M)\u0001\u0001\u000b\u00183kA\u0011\u0011\u0006L\u0007\u0002U)\t1&A\u0003tG\u0006d\u0017-\u0003\u0002.U\t1\u0011I\\=SK\u001a\u0004\"a\f\u0019\u000e\u0003\u0001J!!\r\u0011\u00039\u0005\u0003\u0018m\u00195f\u0007>tG/\u001b8v_V\u001cH)[:ue&\u0014W\u000f^5p]B\u0011\u0011fM\u0005\u0003i)\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00027}9\u0011q\u0007\u0010\b\u0003qmj\u0011!\u000f\u0006\u0003u\u0019\na\u0001\u0010:p_Rt\u0014\"A\u0016\n\u0005uR\u0013a\u00029bG.\fw-Z\u0005\u0003\u007f\u0001\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!!\u0010\u0016\u0002\r5,G-[1o+\u0005\u0019\u0005CA\u0015E\u0013\t)%F\u0001\u0004E_V\u0014G.Z\u0001\b[\u0016$\u0017.\u00198!\u0003\u0015\u00198-\u00197f\u0003\u0019\u00198-\u00197fA\u0005!!/\u00198e!\ty3*\u0003\u0002MA\tI!+\u00198e\u0005\u0006\u001c\u0018n]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007=\u00136\u000b\u0006\u0002Q#B\u0011q\u0006\u0001\u0005\u0006\u0013\u001a\u0001\u001dA\u0013\u0005\u0006\u0003\u001a\u0001\ra\u0011\u0005\u0006\u000f\u001a\u0001\raQ\u0001\u0004e:<W#\u0001,\u0011\u0005]\u0013W\"\u0001-\u000b\u0005eS\u0016A\u0002:b]\u0012|WN\u0003\u0002\\9\u0006)Q.\u0019;ig)\u0011QLX\u0001\bG>lWn\u001c8t\u0015\ty\u0006-\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002C\u0006\u0019qN]4\n\u0005\rD&a\u0004*b]\u0012|WnR3oKJ\fGo\u001c:\u0002\tItw\rI\u0001\u0013S:4XM]:f\u0007Vl\u0017iY2ve\u0006\u001c\u00170A\nj]Z,'o]3Dk6\f5mY;sC\u000eL\b%A\u0003j]:,'/F\u0001j!\tQW.D\u0001l\u0015\ta',\u0001\u0007eSN$(/\u001b2vi&|g.\u0003\u0002 W\u00061\u0011N\u001c8fe\u0002\nAaY8qsR\u0019\u0011o\u001d;\u0015\u0005A\u0013\b\"B%\u000e\u0001\bQ\u0005bB!\u000e!\u0003\u0005\ra\u0011\u0005\b\u000f6\u0001\n\u00111\u0001D\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012a\u001e\u0016\u0003\u0007b\\\u0013!\u001f\t\u0003u~l\u0011a\u001f\u0006\u0003yv\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005yT\u0013AC1o]>$\u0018\r^5p]&\u0019\u0011\u0011A>\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\tI\u0001\u0005\u0003\u0002\f\u0005UQBAA\u0007\u0015\u0011\ty!!\u0005\u0002\t1\fgn\u001a\u0006\u0003\u0003'\tAA[1wC&!\u0011qCA\u0007\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u0011Q\u0004\t\u0004S\u0005}\u0011bAA\u0011U\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011qEA\u0017!\rI\u0013\u0011F\u0005\u0004\u0003WQ#aA!os\"I\u0011q\u0006\n\u0002\u0002\u0003\u0007\u0011QD\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005U\u0002CBA\u001c\u0003{\t9#\u0004\u0002\u0002:)\u0019\u00111\b\u0016\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002@\u0005e\"\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!\u0012\u0002LA\u0019\u0011&a\u0012\n\u0007\u0005%#FA\u0004C_>dW-\u00198\t\u0013\u0005=B#!AA\u0002\u0005\u001d\u0012A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!!\u0003\u0002R!I\u0011qF\u000b\u0002\u0002\u0003\u0007\u0011QD\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011QD\u0001\ti>\u001cFO]5oOR\u0011\u0011\u0011B\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005\u0015\u0013q\f\u0005\n\u0003_A\u0012\u0011!a\u0001\u0003O\t!cQ1vG\"LH)[:ue&\u0014W\u000f^5p]B\u0011qFG\n\u00075!\n9'!\u001c\u0011\u000b=\nIg\u0011)\n\u0007\u0005-\u0004EA\u0012D_:$\u0018N\\;pkN$\u0015n\u001d;sS\n,H/[8o+\u001a+hn\u0019)s_ZLG-\u001a:\u0011\t\u0005=\u0014QO\u0007\u0003\u0003cRA!a\u001d\u0002\u0012\u0005\u0011\u0011n\\\u0005\u0004\u007f\u0005EDCAA2\u0003\u0015\t\u0007\u000f\u001d7z)\u0019\ti(!!\u0002\u0004R\u0019\u0001+a \t\u000b%c\u00029\u0001&\t\u000b\u0005c\u0002\u0019A\"\t\u000b\u001dc\u0002\u0019A\"\u0002\u000fUt\u0017\r\u001d9msR!\u0011\u0011RAK!\u0015I\u00131RAH\u0013\r\tiI\u000b\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000b%\n\tjQ\"\n\u0007\u0005M%F\u0001\u0004UkBdWM\r\u0005\t\u0003/k\u0012\u0011!a\u0001!\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005u\u0005\u0003BA\u0006\u0003?KA!!)\u0002\u000e\t1qJ\u00196fGR\u0004"
)
public class CauchyDistribution implements ApacheContinuousDistribution, Product {
   private final double median;
   private final double scale;
   private final RandomGenerator rng;
   private final double inverseCumAccuracy;
   private final org.apache.commons.math3.distribution.CauchyDistribution inner;
   private double logNormalizer;
   private double normalizer;
   private volatile byte bitmap$0;

   public static Option unapply(final CauchyDistribution x$0) {
      return CauchyDistribution$.MODULE$.unapply(x$0);
   }

   public static ContinuousDistributionUFuncProvider.ContinuousDistrUFuncWrapper ContinuousDistrUFuncWrapper(final ContinuousDistr dist) {
      return CauchyDistribution$.MODULE$.ContinuousDistrUFuncWrapper(dist);
   }

   public static ContinuousDistributionUFuncProvider.basicImpl$ basicImpl() {
      return CauchyDistribution$.MODULE$.basicImpl();
   }

   public static Object withSink(final Object s) {
      return CauchyDistribution$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return CauchyDistribution$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return CauchyDistribution$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return CauchyDistribution$.MODULE$.inPlace(v, impl);
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

   public double median() {
      return this.median;
   }

   public double scale() {
      return this.scale;
   }

   public RandomGenerator rng() {
      return this.rng;
   }

   public double inverseCumAccuracy() {
      return this.inverseCumAccuracy;
   }

   public final org.apache.commons.math3.distribution.CauchyDistribution inner() {
      return this.inner;
   }

   public CauchyDistribution copy(final double median, final double scale, final RandBasis rand) {
      return new CauchyDistribution(median, scale, rand);
   }

   public double copy$default$1() {
      return this.median();
   }

   public double copy$default$2() {
      return this.scale();
   }

   public String productPrefix() {
      return "CauchyDistribution";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.median());
            break;
         case 1:
            var10000 = BoxesRunTime.boxToDouble(this.scale());
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
      return x$1 instanceof CauchyDistribution;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "median";
            break;
         case 1:
            var10000 = "scale";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.median()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.scale()));
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
            if (x$1 instanceof CauchyDistribution) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               CauchyDistribution var4 = (CauchyDistribution)x$1;
               if (this.median() == var4.median() && this.scale() == var4.scale() && var4.canEqual(this)) {
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

   public CauchyDistribution(final double median, final double scale, final RandBasis rand) {
      this.median = median;
      this.scale = scale;
      Density.$init$(this);
      Rand.$init$(this);
      ContinuousDistr.$init$(this);
      ApacheContinuousDistribution.$init$(this);
      Product.$init$(this);
      this.rng = rand.generator();
      this.inverseCumAccuracy = 1.0E-9;
      this.inner = new org.apache.commons.math3.distribution.CauchyDistribution(this.rng(), median, scale, this.inverseCumAccuracy());
   }
}
