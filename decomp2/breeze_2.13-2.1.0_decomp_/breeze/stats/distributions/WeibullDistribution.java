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
   bytes = "\u0006\u0005\u0005=d\u0001\u0002\u000e\u001c\u0001\nB\u0001\u0002\u0010\u0001\u0003\u0016\u0004%\t!\u0010\u0005\t\u0003\u0002\u0011\t\u0012)A\u0005}!A!\t\u0001BK\u0002\u0013\u0005Q\b\u0003\u0005D\u0001\tE\t\u0015!\u0003?\u0011\u0015!\u0005\u0001\"\u0001F\u0011\u001dI\u0005A1A\u0005\u0016)Ca\u0001\u0017\u0001!\u0002\u001bY\u0005bB-\u0001\u0003\u0003%\tA\u0017\u0005\b;\u0002\t\n\u0011\"\u0001_\u0011\u001dI\u0007!%A\u0005\u0002yCqA\u001b\u0001\u0002\u0002\u0013\u00053\u000eC\u0004u\u0001\u0005\u0005I\u0011A;\t\u000fe\u0004\u0011\u0011!C\u0001u\"I\u0011\u0011\u0001\u0001\u0002\u0002\u0013\u0005\u00131\u0001\u0005\n\u0003#\u0001\u0011\u0011!C\u0001\u0003'A\u0011\"!\b\u0001\u0003\u0003%\t%a\b\t\u0013\u0005\r\u0002!!A\u0005B\u0005\u0015\u0002\"CA\u0014\u0001\u0005\u0005I\u0011IA\u0015\u0011%\tY\u0003AA\u0001\n\u0003\nicB\u0004\u00022mA\t!a\r\u0007\riY\u0002\u0012AA\u001b\u0011\u0019!U\u0003\"\u0001\u0002H!I\u0011\u0011J\u000b\u0002\u0002\u0013\u0005\u00151\n\u0005\n\u0003#*\u0012\u0011!CA\u0003'B\u0011\"!\u001a\u0016\u0003\u0003%I!a\u001a\u0003']+\u0017NY;mY\u0012K7\u000f\u001e:jEV$\u0018n\u001c8\u000b\u0005qi\u0012!\u00043jgR\u0014\u0018NY;uS>t7O\u0003\u0002\u001f?\u0005)1\u000f^1ug*\t\u0001%\u0001\u0004ce\u0016,'0Z\u0002\u0001'\u0015\u00011%K\u00171!\t!s%D\u0001&\u0015\u00051\u0013!B:dC2\f\u0017B\u0001\u0015&\u0005\u0019\te.\u001f*fMB\u0011!fK\u0007\u00027%\u0011Af\u0007\u0002\u001d\u0003B\f7\r[3D_:$\u0018N\\;pkN$\u0015n\u001d;sS\n,H/[8o!\t!c&\u0003\u00020K\t9\u0001K]8ek\u000e$\bCA\u0019:\u001d\t\u0011tG\u0004\u00024m5\tAG\u0003\u00026C\u00051AH]8pizJ\u0011AJ\u0005\u0003q\u0015\nq\u0001]1dW\u0006<W-\u0003\u0002;w\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0001(J\u0001\u0006C2\u0004\b.Y\u000b\u0002}A\u0011AeP\u0005\u0003\u0001\u0016\u0012a\u0001R8vE2,\u0017AB1ma\"\f\u0007%\u0001\u0003cKR\f\u0017!\u00022fi\u0006\u0004\u0013A\u0002\u001fj]&$h\bF\u0002G\u000f\"\u0003\"A\u000b\u0001\t\u000bq*\u0001\u0019\u0001 \t\u000b\t+\u0001\u0019\u0001 \u0002\u000b%tg.\u001a:\u0016\u0003-\u0003\"\u0001T,\u000e\u00035S!AT(\u0002\u0019\u0011L7\u000f\u001e:jEV$\u0018n\u001c8\u000b\u0005A\u000b\u0016!B7bi\"\u001c$B\u0001*T\u0003\u001d\u0019w.\\7p]NT!\u0001V+\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00051\u0016aA8sO&\u0011!$T\u0001\u0007S:tWM\u001d\u0011\u0002\t\r|\u0007/\u001f\u000b\u0004\rnc\u0006b\u0002\u001f\t!\u0003\u0005\rA\u0010\u0005\b\u0005\"\u0001\n\u00111\u0001?\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012a\u0018\u0016\u0003}\u0001\\\u0013!\u0019\t\u0003E\u001el\u0011a\u0019\u0006\u0003I\u0016\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005\u0019,\u0013AC1o]>$\u0018\r^5p]&\u0011\u0001n\u0019\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u00031\u0004\"!\u001c:\u000e\u00039T!a\u001c9\u0002\t1\fgn\u001a\u0006\u0002c\u0006!!.\u0019<b\u0013\t\u0019hN\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002mB\u0011Ae^\u0005\u0003q\u0016\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"a\u001f@\u0011\u0005\u0011b\u0018BA?&\u0005\r\te.\u001f\u0005\b\u007f6\t\t\u00111\u0001w\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011Q\u0001\t\u0006\u0003\u000f\tia_\u0007\u0003\u0003\u0013Q1!a\u0003&\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003\u001f\tIA\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u000b\u00037\u00012\u0001JA\f\u0013\r\tI\"\n\u0002\b\u0005>|G.Z1o\u0011\u001dyx\"!AA\u0002m\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019A.!\t\t\u000f}\u0004\u0012\u0011!a\u0001m\u0006A\u0001.Y:i\u0007>$W\rF\u0001w\u0003!!xn\u0015;sS:<G#\u00017\u0002\r\u0015\fX/\u00197t)\u0011\t)\"a\f\t\u000f}\u001c\u0012\u0011!a\u0001w\u0006\u0019r+Z5ck2dG)[:ue&\u0014W\u000f^5p]B\u0011!&F\n\u0007+\r\n9$!\u0010\u0011\u000b)\nID\u0010$\n\u0007\u0005m2DA\u0012D_:$\u0018N\\;pkN$\u0015n\u001d;sS\n,H/[8o+\u001a+hn\u0019)s_ZLG-\u001a:\u0011\t\u0005}\u0012QI\u0007\u0003\u0003\u0003R1!a\u0011q\u0003\tIw.C\u0002;\u0003\u0003\"\"!a\r\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000b\u0019\u000bi%a\u0014\t\u000bq:\u0002\u0019\u0001 \t\u000b\t;\u0002\u0019\u0001 \u0002\u000fUt\u0017\r\u001d9msR!\u0011QKA1!\u0015!\u0013qKA.\u0013\r\tI&\n\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000b\u0011\niF\u0010 \n\u0007\u0005}SE\u0001\u0004UkBdWM\r\u0005\t\u0003GB\u0012\u0011!a\u0001\r\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005%\u0004cA7\u0002l%\u0019\u0011Q\u000e8\u0003\r=\u0013'.Z2u\u0001"
)
public class WeibullDistribution implements ApacheContinuousDistribution, Product {
   private final double alpha;
   private final double beta;
   private final org.apache.commons.math3.distribution.WeibullDistribution inner;
   private double logNormalizer;
   private double normalizer;
   private volatile byte bitmap$0;

   public static Option unapply(final WeibullDistribution x$0) {
      return WeibullDistribution$.MODULE$.unapply(x$0);
   }

   public static ContinuousDistributionUFuncProvider.ContinuousDistrUFuncWrapper ContinuousDistrUFuncWrapper(final ContinuousDistr dist) {
      return WeibullDistribution$.MODULE$.ContinuousDistrUFuncWrapper(dist);
   }

   public static ContinuousDistributionUFuncProvider.basicImpl$ basicImpl() {
      return WeibullDistribution$.MODULE$.basicImpl();
   }

   public static Object withSink(final Object s) {
      return WeibullDistribution$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return WeibullDistribution$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return WeibullDistribution$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return WeibullDistribution$.MODULE$.inPlace(v, impl);
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

   public double alpha() {
      return this.alpha;
   }

   public double beta() {
      return this.beta;
   }

   public final org.apache.commons.math3.distribution.WeibullDistribution inner() {
      return this.inner;
   }

   public WeibullDistribution copy(final double alpha, final double beta) {
      return new WeibullDistribution(alpha, beta);
   }

   public double copy$default$1() {
      return this.alpha();
   }

   public double copy$default$2() {
      return this.beta();
   }

   public String productPrefix() {
      return "WeibullDistribution";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.alpha());
            break;
         case 1:
            var10000 = BoxesRunTime.boxToDouble(this.beta());
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
      return x$1 instanceof WeibullDistribution;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "alpha";
            break;
         case 1:
            var10000 = "beta";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.alpha()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.beta()));
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
            if (x$1 instanceof WeibullDistribution) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               WeibullDistribution var4 = (WeibullDistribution)x$1;
               if (this.alpha() == var4.alpha() && this.beta() == var4.beta() && var4.canEqual(this)) {
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

   public WeibullDistribution(final double alpha, final double beta) {
      this.alpha = alpha;
      this.beta = beta;
      Density.$init$(this);
      Rand.$init$(this);
      ContinuousDistr.$init$(this);
      ApacheContinuousDistribution.$init$(this);
      Product.$init$(this);
      this.inner = new org.apache.commons.math3.distribution.WeibullDistribution(alpha, beta);
   }
}
