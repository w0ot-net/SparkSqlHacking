package breeze.stats.distributions;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import breeze.optimize.DiffFunction;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.math.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tua\u0001\u0002\u0016,\u0001JB\u0001\u0002\u0017\u0001\u0003\u0016\u0004%\t!\u0017\u0005\t5\u0002\u0011\t\u0012)A\u0005{!A1\f\u0001B\u0001B\u0003-A\fC\u0003`\u0001\u0011\u0005\u0001\rC\u0004f\u0001\t\u0007I\u0011\u00024\t\r)\u0004\u0001\u0015!\u0003h\u0011\u0015Y\u0007\u0001\"\u0001m\u0011\u0015i\u0007\u0001\"\u0011o\u0011\u0015\t\b\u0001\"\u0001s\u0011!!\b\u0001#b\u0001\n\u0003I\u0006\"B;\u0001\t\u0003I\u0006\"\u0002<\u0001\t\u0003I\u0006\"B<\u0001\t\u0003I\u0006\"\u0002=\u0001\t\u0003I\u0006\"B=\u0001\t\u0003R\bbBA\u0004\u0001\u0011\u0005\u0013\u0011\u0002\u0005\b\u0003#\u0001A\u0011IA\n\u0011\u001d\tI\u0002\u0001C!\u00037A\u0011\"a\b\u0001\u0003\u0003%\t!!\t\t\u0013\u0005%\u0002!%A\u0005\u0002\u0005-\u0002\"CA!\u0001\u0005\u0005I\u0011IA\"\u0011%\t\u0019\u0006AA\u0001\n\u0003\t)\u0006C\u0005\u0002^\u0001\t\t\u0011\"\u0001\u0002`!I\u00111\u000e\u0001\u0002\u0002\u0013\u0005\u0013Q\u000e\u0005\n\u0003w\u0002\u0011\u0011!C\u0001\u0003{B\u0011\"a\"\u0001\u0003\u0003%\t%!#\t\u0013\u00055\u0005!!A\u0005B\u0005=\u0005\"CAI\u0001\u0005\u0005I\u0011IAJ\u000f\u001d\t9j\u000bE\u0001\u000333aAK\u0016\t\u0002\u0005m\u0005BB0\u001f\t\u0003\t\u0019,B\u0003\u00026z\u0001Q(\u0002\u0004\u00028z\u0001\u0011\u0011\u0018\u0005\b\u0003\u000btB\u0011AAd\u0011\u001d\tiM\bC\u0001\u0003\u001fDq!!6\u001f\t\u0003\t9\u000eC\u0004\u0002jz!\t!a;\t\u000f\u0005Eh\u0004\"\u0011\u0002t\"I\u00111 \u0010\u0002\u0002\u0013\u0005\u0015Q \u0005\n\u0005\u000bq\u0012\u0011!CA\u0005\u000fA\u0011Ba\u0005\u001f\u0003\u0003%IA!\u0006\u0003\u0015\rC\u0017nU9vCJ,GM\u0003\u0002-[\u0005iA-[:ue&\u0014W\u000f^5p]NT!AL\u0018\u0002\u000bM$\u0018\r^:\u000b\u0003A\naA\u0019:fKj,7\u0001A\n\t\u0001MJ\u0004i\u0011$J\u0019B\u0011AgN\u0007\u0002k)\ta'A\u0003tG\u0006d\u0017-\u0003\u00029k\t1\u0011I\\=SK\u001a\u00042AO\u001e>\u001b\u0005Y\u0013B\u0001\u001f,\u0005=\u0019uN\u001c;j]V|Wo\u001d#jgR\u0014\bC\u0001\u001b?\u0013\tyTG\u0001\u0004E_V\u0014G.\u001a\t\u0005u\u0005kT(\u0003\u0002CW\t9Qj\\7f]R\u001c\bC\u0001\u001eE\u0013\t)5F\u0001\u0004ICN\u001cEM\u001a\t\u0003u\u001dK!\u0001S\u0016\u0003\u001b!\u000b7/\u00138wKJ\u001cXm\u00113g!\t!$*\u0003\u0002Lk\t9\u0001K]8ek\u000e$\bCA'V\u001d\tq5K\u0004\u0002P%6\t\u0001K\u0003\u0002Rc\u00051AH]8pizJ\u0011AN\u0005\u0003)V\nq\u0001]1dW\u0006<W-\u0003\u0002W/\na1+\u001a:jC2L'0\u00192mK*\u0011A+N\u0001\u0002WV\tQ(\u0001\u0002lA\u0005!!/\u00198e!\tQT,\u0003\u0002_W\tI!+\u00198e\u0005\u0006\u001c\u0018n]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u0005$GC\u00012d!\tQ\u0004\u0001C\u0003\\\t\u0001\u000fA\fC\u0003Y\t\u0001\u0007Q(\u0001\u0006j]:,'oR1n[\u0006,\u0012a\u001a\t\u0003u!L!![\u0016\u0003\u000b\u001d\u000bW.\\1\u0002\u0017%tg.\u001a:HC6l\u0017\rI\u0001\u0005IJ\fw\u000fF\u0001>\u0003\r\u0001HM\u001a\u000b\u0003{=DQ\u0001\u001d\u0005A\u0002u\n\u0011\u0001_\u0001\u0013k:twN]7bY&TX\r\u001a'pOB#g\r\u0006\u0002>g\")\u0001/\u0003a\u0001{\u0005iAn\\4O_Jl\u0017\r\\5{KJ\fA!\\3b]\u0006Aa/\u0019:jC:\u001cW-\u0001\u0003n_\u0012,\u0017aB3oiJ|\u0007/_\u0001\ti>\u001cFO]5oOR\t1\u0010E\u0002}\u0003\u0003q!! @\u0011\u0005=+\u0014BA@6\u0003\u0019\u0001&/\u001a3fM&!\u00111AA\u0003\u0005\u0019\u0019FO]5oO*\u0011q0N\u0001\faJ|'-\u00192jY&$\u0018\u0010F\u0003>\u0003\u0017\ti\u0001C\u0003q!\u0001\u0007Q\b\u0003\u0004\u0002\u0010A\u0001\r!P\u0001\u0002s\u0006Q\u0011N\u001c<feN,7\t\u001a4\u0015\u0007u\n)\u0002\u0003\u0004\u0002\u0018E\u0001\r!P\u0001\u0002a\u0006\u00191\r\u001a4\u0015\u0007u\ni\u0002C\u0003q%\u0001\u0007Q(\u0001\u0003d_BLH\u0003BA\u0012\u0003O!2AYA\u0013\u0011\u0015Y6\u0003q\u0001]\u0011\u001dA6\u0003%AA\u0002u\nabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002.)\u001aQ(a\f,\u0005\u0005E\u0002\u0003BA\u001a\u0003{i!!!\u000e\u000b\t\u0005]\u0012\u0011H\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a\u000f6\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003\u007f\t)DA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA#!\u0011\t9%!\u0015\u000e\u0005\u0005%#\u0002BA&\u0003\u001b\nA\u0001\\1oO*\u0011\u0011qJ\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002\u0004\u0005%\u0013\u0001\u00049s_\u0012,8\r^!sSRLXCAA,!\r!\u0014\u0011L\u0005\u0004\u00037*$aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA1\u0003O\u00022\u0001NA2\u0013\r\t)'\u000e\u0002\u0004\u0003:L\b\"CA5/\u0005\u0005\t\u0019AA,\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011q\u000e\t\u0007\u0003c\n9(!\u0019\u000e\u0005\u0005M$bAA;k\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005e\u00141\u000f\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002\u0000\u0005\u0015\u0005c\u0001\u001b\u0002\u0002&\u0019\u00111Q\u001b\u0003\u000f\t{w\u000e\\3b]\"I\u0011\u0011N\r\u0002\u0002\u0003\u0007\u0011\u0011M\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002F\u0005-\u0005\"CA55\u0005\u0005\t\u0019AA,\u0003!A\u0017m\u001d5D_\u0012,GCAA,\u0003\u0019)\u0017/^1mgR!\u0011qPAK\u0011%\tI\u0007HA\u0001\u0002\u0004\t\t'\u0001\u0006DQ&\u001c\u0016/^1sK\u0012\u0004\"A\u000f\u0010\u0014\u0011y\u0019\u0014QTAR\u0003S\u0003RAOAPEvJ1!!),\u0005E)\u0005\u0010]8oK:$\u0018.\u00197GC6LG.\u001f\t\u0006u\u0005\u0015VHY\u0005\u0004\u0003O[#aI\"p]RLg.^8vg\u0012K7\u000f\u001e:jEV$\u0018n\u001c8V\rVt7\r\u0015:pm&$WM\u001d\t\u0005\u0003W\u000b\t,\u0004\u0002\u0002.*!\u0011qVA'\u0003\tIw.C\u0002W\u0003[#\"!!'\u0003\u0013A\u000b'/Y7fi\u0016\u0014(aE*vM\u001aL7-[3oiN#\u0018\r^5ti&\u001c\u0007\u0003BA^\u0003\u0003t1AOA_\u0013\r\tylK\u0001\u0006\u000f\u0006lW.Y\u0005\u0005\u0003o\u000b\u0019MC\u0002\u0002@.\n\u0001$Z7qif\u001cVO\u001a4jG&,g\u000e^*uCRL7\u000f^5d+\t\tI\rE\u0002\u0002L\u0006r!AO\u000f\u0002-M,hMZ5dS\u0016tGo\u0015;bi&\u001cH/[2G_J$B!!3\u0002R\"1\u00111[\u0012A\u0002u\n\u0011\u0001^\u0001\u0013Y&\\W\r\\5i_>$g)\u001e8di&|g\u000e\u0006\u0003\u0002Z\u0006\u001d\bCBAn\u0003C\f)/\u0004\u0002\u0002^*\u0019\u0011q\\\u0018\u0002\u0011=\u0004H/[7ju\u0016LA!a9\u0002^\naA)\u001b4g\rVt7\r^5p]B\u0019\u00111\u001a\u0011\t\r9\"\u0003\u0019AAe\u0003\riG.\u001a\u000b\u0005\u0003K\fi\u000fC\u0004\u0002p\u0016\u0002\r!!3\u0002\u0005M\u001c\u0018\u0001\u00043jgR\u0014\u0018NY;uS>tG\u0003BA{\u0003s$2AYA|\u0011\u0015Yf\u0005q\u0001]\u0011\u001d\t9B\na\u0001\u0003K\fQ!\u00199qYf$B!a@\u0003\u0004Q\u0019!M!\u0001\t\u000bm;\u00039\u0001/\t\u000ba;\u0003\u0019A\u001f\u0002\u000fUt\u0017\r\u001d9msR!!\u0011\u0002B\b!\u0011!$1B\u001f\n\u0007\t5QG\u0001\u0004PaRLwN\u001c\u0005\t\u0005#A\u0013\u0011!a\u0001E\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\t]\u0001\u0003BA$\u00053IAAa\u0007\u0002J\t1qJ\u00196fGR\u0004"
)
public class ChiSquared implements ContinuousDistr, Moments, HasCdf, HasInverseCdf, Product {
   private double logNormalizer;
   private final double k;
   private final Gamma breeze$stats$distributions$ChiSquared$$innerGamma;
   private double normalizer;
   private volatile byte bitmap$0;

   public static Option unapply(final ChiSquared x$0) {
      return ChiSquared$.MODULE$.unapply(x$0);
   }

   public static ChiSquared distribution(final double p, final RandBasis rand) {
      return ChiSquared$.MODULE$.distribution(p, rand);
   }

   public static double mle(final Gamma.SufficientStatistic ss) {
      return ChiSquared$.MODULE$.mle(ss);
   }

   public static DiffFunction likelihoodFunction(final Gamma.SufficientStatistic stats) {
      return ChiSquared$.MODULE$.likelihoodFunction(stats);
   }

   public static Gamma.SufficientStatistic sufficientStatisticFor(final double t) {
      return ChiSquared$.MODULE$.sufficientStatisticFor(t);
   }

   public static Gamma.SufficientStatistic emptySufficientStatistic() {
      return ChiSquared$.MODULE$.emptySufficientStatistic();
   }

   public static ContinuousDistributionUFuncProvider.ContinuousDistrUFuncWrapper ContinuousDistrUFuncWrapper(final ContinuousDistr dist) {
      return ChiSquared$.MODULE$.ContinuousDistrUFuncWrapper(dist);
   }

   public static ContinuousDistributionUFuncProvider.basicImpl$ basicImpl() {
      return ChiSquared$.MODULE$.basicImpl();
   }

   public static Object withSink(final Object s) {
      return ChiSquared$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return ChiSquared$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return ChiSquared$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return ChiSquared$.MODULE$.inPlace(v, impl);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
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

   public double k() {
      return this.k;
   }

   public Gamma breeze$stats$distributions$ChiSquared$$innerGamma() {
      return this.breeze$stats$distributions$ChiSquared$$innerGamma;
   }

   public double draw() {
      return this.draw$mcD$sp();
   }

   public double pdf(final double x) {
      double var10000;
      if (x > (double)0.0F) {
         var10000 = .MODULE$.exp(this.logPdf(BoxesRunTime.boxToDouble(x)));
      } else {
         if (x != (double)0.0F) {
            throw new IllegalArgumentException((new StringBuilder(64)).append("Domain of ChiSquared.pdf is [0,Infinity), you tried to apply to ").append(x).toString());
         }

         var10000 = this.k() > (double)2.0F ? (double)0.0F : (this.k() == (double)2.0F ? (double)0.5F : Double.POSITIVE_INFINITY);
      }

      return var10000;
   }

   public double unnormalizedLogPdf(final double x) {
      return this.breeze$stats$distributions$ChiSquared$$innerGamma().unnormalizedLogPdf(x);
   }

   private double logNormalizer$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.logNormalizer = this.breeze$stats$distributions$ChiSquared$$innerGamma().logNormalizer();
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

   public double mean() {
      return this.breeze$stats$distributions$ChiSquared$$innerGamma().mean();
   }

   public double variance() {
      return this.breeze$stats$distributions$ChiSquared$$innerGamma().variance();
   }

   public double mode() {
      return this.breeze$stats$distributions$ChiSquared$$innerGamma().mode();
   }

   public double entropy() {
      return this.breeze$stats$distributions$ChiSquared$$innerGamma().entropy();
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public double probability(final double x, final double y) {
      return this.breeze$stats$distributions$ChiSquared$$innerGamma().probability(x, y);
   }

   public double inverseCdf(final double p) {
      return this.breeze$stats$distributions$ChiSquared$$innerGamma().inverseCdf(p);
   }

   public double cdf(final double x) {
      return this.breeze$stats$distributions$ChiSquared$$innerGamma().cdf(x);
   }

   public ChiSquared copy(final double k, final RandBasis rand) {
      return new ChiSquared(k, rand);
   }

   public double copy$default$1() {
      return this.k();
   }

   public String productPrefix() {
      return "ChiSquared";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.k());
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ChiSquared;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "k";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.k()));
      return Statics.finalizeHash(var1, 1);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof ChiSquared) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               ChiSquared var4 = (ChiSquared)x$1;
               if (this.k() == var4.k() && var4.canEqual(this)) {
                  break label49;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public double draw$mcD$sp() {
      return this.breeze$stats$distributions$ChiSquared$$innerGamma().draw();
   }

   public ChiSquared(final double k, final RandBasis rand) {
      this.k = k;
      Density.$init$(this);
      Rand.$init$(this);
      ContinuousDistr.$init$(this);
      Product.$init$(this);
      this.breeze$stats$distributions$ChiSquared$$innerGamma = new Gamma(k / (double)2, (double)2.0F, rand);
   }
}
