package breeze.stats.distributions;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import breeze.numerics.package;
import breeze.numerics.package$logI$logIBoolImpl$;
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
   bytes = "\u0006\u0005\u0005Uf\u0001B\u0012%\u0001.B\u0001\"\u0015\u0001\u0003\u0016\u0004%\tA\u0015\u0005\t'\u0002\u0011\t\u0012)A\u0005m!AA\u000b\u0001BK\u0002\u0013\u0005!\u000b\u0003\u0005V\u0001\tE\t\u0015!\u00037\u0011!1\u0006A!A!\u0002\u00179\u0006\"\u0002.\u0001\t\u0003Y\u0006\"B1\u0001\t\u0003\u0011\u0007\"B2\u0001\t\u0003!\u0007\u0002C4\u0001\u0011\u000b\u0007I\u0011\u0001*\t\u000b!\u0004A\u0011\u0001*\t\u000b%\u0004A\u0011\u0001*\t\u000b)\u0004A\u0011\u0001*\t\u000b-\u0004A\u0011\u0001*\t\u000b1\u0004A\u0011A7\t\u000b=\u0004A\u0011\t9\t\u000bQ\u0004A\u0011I;\t\u000fa\u0004\u0011\u0011!C\u0001s\"9a\u0010AI\u0001\n\u0003y\b\u0002CA\u000b\u0001E\u0005I\u0011A@\t\u0013\u0005]\u0001!!A\u0005B\u0005e\u0001\"CA\u0016\u0001\u0005\u0005I\u0011AA\u0017\u0011%\t)\u0004AA\u0001\n\u0003\t9\u0004C\u0005\u0002D\u0001\t\t\u0011\"\u0011\u0002F!I\u00111\u000b\u0001\u0002\u0002\u0013\u0005\u0011Q\u000b\u0005\n\u0003?\u0002\u0011\u0011!C!\u0003CB\u0011\"!\u001a\u0001\u0003\u0003%\t%a\u001a\t\u0013\u0005%\u0004!!A\u0005B\u0005-\u0004\"CA7\u0001\u0005\u0005I\u0011IA8\u000f\u001d\t\u0019\b\nE\u0001\u0003k2aa\t\u0013\t\u0002\u0005]\u0004B\u0002.\u001f\t\u0003\tI\tC\u0005\u0002\fz\t\t\u0011\"!\u0002\u000e\"I\u0011q\u0013\u0010\u0002\u0002\u0013\u0005\u0015\u0011\u0014\u0005\n\u0003Ws\u0012\u0011!C\u0005\u0003[\u0013q!\u00168jM>\u0014XN\u0003\u0002&M\u0005iA-[:ue&\u0014W\u000f^5p]NT!a\n\u0015\u0002\u000bM$\u0018\r^:\u000b\u0003%\naA\u0019:fKj,7\u0001A\n\t\u00011\u0012\u0014\bP C\u000bB\u0011Q\u0006M\u0007\u0002])\tq&A\u0003tG\u0006d\u0017-\u0003\u00022]\t1\u0011I\\=SK\u001a\u00042a\r\u001b7\u001b\u0005!\u0013BA\u001b%\u0005=\u0019uN\u001c;j]V|Wo\u001d#jgR\u0014\bCA\u00178\u0013\tAdF\u0001\u0004E_V\u0014G.\u001a\t\u0005gi2d'\u0003\u0002<I\t9Qj\\7f]R\u001c\bCA\u001a>\u0013\tqDE\u0001\u0004ICN\u001cEM\u001a\t\u0003g\u0001K!!\u0011\u0013\u0003\u001b!\u000b7/\u00138wKJ\u001cXm\u00113g!\ti3)\u0003\u0002E]\t9\u0001K]8ek\u000e$\bC\u0001$O\u001d\t9EJ\u0004\u0002I\u00176\t\u0011J\u0003\u0002KU\u00051AH]8pizJ\u0011aL\u0005\u0003\u001b:\nq\u0001]1dW\u0006<W-\u0003\u0002P!\na1+\u001a:jC2L'0\u00192mK*\u0011QJL\u0001\u0004Y><X#\u0001\u001c\u0002\t1|w\u000fI\u0001\u0005Q&<\u0007.A\u0003iS\u001eD\u0007%\u0001\u0003sC:$\u0007CA\u001aY\u0013\tIFEA\u0005SC:$')Y:jg\u00061A(\u001b8jiz\"2\u0001X0a)\tif\f\u0005\u00024\u0001!)aK\u0002a\u0002/\")\u0011K\u0002a\u0001m!)AK\u0002a\u0001m\u0005!AM]1x)\u00051\u0014AE;o]>\u0014X.\u00197ju\u0016$Gj\\4QI\u001a$\"AN3\t\u000b\u0019D\u0001\u0019\u0001\u001c\u0002\u0003a\fQ\u0002\\8h\u001d>\u0014X.\u00197ju\u0016\u0014\u0018\u0001B7pI\u0016\fA!\\3b]\u0006Aa/\u0019:jC:\u001cW-A\u0004f]R\u0014x\u000e]=\u0002\u0007\r$g\r\u0006\u00027]\")aM\u0004a\u0001m\u0005Y\u0001O]8cC\nLG.\u001b;z)\r1\u0014O\u001d\u0005\u0006M>\u0001\rA\u000e\u0005\u0006g>\u0001\rAN\u0001\u0002s\u0006Q\u0011N\u001c<feN,7\t\u001a4\u0015\u0005Y2\b\"B<\u0011\u0001\u00041\u0014!\u00019\u0002\t\r|\u0007/\u001f\u000b\u0004urlHCA/|\u0011\u00151\u0016\u0003q\u0001X\u0011\u001d\t\u0016\u0003%AA\u0002YBq\u0001V\t\u0011\u0002\u0003\u0007a'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005\u0005!f\u0001\u001c\u0002\u0004-\u0012\u0011Q\u0001\t\u0005\u0003\u000f\t\t\"\u0004\u0002\u0002\n)!\u00111BA\u0007\u0003%)hn\u00195fG.,GMC\u0002\u0002\u00109\n!\"\u00198o_R\fG/[8o\u0013\u0011\t\u0019\"!\u0003\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\tY\u0002\u0005\u0003\u0002\u001e\u0005\u001dRBAA\u0010\u0015\u0011\t\t#a\t\u0002\t1\fgn\u001a\u0006\u0003\u0003K\tAA[1wC&!\u0011\u0011FA\u0010\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u0011q\u0006\t\u0004[\u0005E\u0012bAA\u001a]\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011\u0011HA !\ri\u00131H\u0005\u0004\u0003{q#aA!os\"I\u0011\u0011\t\f\u0002\u0002\u0003\u0007\u0011qF\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005\u001d\u0003CBA%\u0003\u001f\nI$\u0004\u0002\u0002L)\u0019\u0011Q\n\u0018\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002R\u0005-#\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\u0016\u0002^A\u0019Q&!\u0017\n\u0007\u0005mcFA\u0004C_>dW-\u00198\t\u0013\u0005\u0005\u0003$!AA\u0002\u0005e\u0012A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!a\u0007\u0002d!I\u0011\u0011I\r\u0002\u0002\u0003\u0007\u0011qF\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011qF\u0001\ti>\u001cFO]5oOR\u0011\u00111D\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005]\u0013\u0011\u000f\u0005\n\u0003\u0003b\u0012\u0011!a\u0001\u0003s\tq!\u00168jM>\u0014X\u000e\u0005\u00024=M1a\u0004LA=\u0003\u007f\u0002RaMA>muK1!! %\u0005\r\u001auN\u001c;j]V|Wo\u001d#jgR\u0014\u0018NY;uS>tWKR;oGB\u0013xN^5eKJ\u0004B!!!\u0002\b6\u0011\u00111\u0011\u0006\u0005\u0003\u000b\u000b\u0019#\u0001\u0002j_&\u0019q*a!\u0015\u0005\u0005U\u0014!B1qa2LHCBAH\u0003'\u000b)\nF\u0002^\u0003#CQA\u0016\u0011A\u0004]CQ!\u0015\u0011A\u0002YBQ\u0001\u0016\u0011A\u0002Y\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002\u001c\u0006\u001d\u0006#B\u0017\u0002\u001e\u0006\u0005\u0016bAAP]\t1q\n\u001d;j_:\u0004R!LARmYJ1!!*/\u0005\u0019!V\u000f\u001d7fe!A\u0011\u0011V\u0011\u0002\u0002\u0003\u0007Q,A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a,\u0011\t\u0005u\u0011\u0011W\u0005\u0005\u0003g\u000byB\u0001\u0004PE*,7\r\u001e"
)
public class Uniform implements ContinuousDistr, Moments, HasCdf, HasInverseCdf, Product {
   private double logNormalizer;
   private final double low;
   private final double high;
   public final RandBasis breeze$stats$distributions$Uniform$$rand;
   private double normalizer;
   private volatile byte bitmap$0;

   public static Option unapply(final Uniform x$0) {
      return Uniform$.MODULE$.unapply(x$0);
   }

   public static ContinuousDistributionUFuncProvider.ContinuousDistrUFuncWrapper ContinuousDistrUFuncWrapper(final ContinuousDistr dist) {
      return Uniform$.MODULE$.ContinuousDistrUFuncWrapper(dist);
   }

   public static ContinuousDistributionUFuncProvider.basicImpl$ basicImpl() {
      return Uniform$.MODULE$.basicImpl();
   }

   public static Object withSink(final Object s) {
      return Uniform$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return Uniform$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return Uniform$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return Uniform$.MODULE$.inPlace(v, impl);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double pdf(final Object x) {
      return ContinuousDistr.pdf$(this, x);
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

   public double low() {
      return this.low;
   }

   public double high() {
      return this.high;
   }

   public double draw() {
      return this.draw$mcD$sp();
   }

   public double unnormalizedLogPdf(final double x) {
      return BoxesRunTime.unboxToDouble(package.logI$.MODULE$.apply(BoxesRunTime.boxToBoolean(x >= this.low() && x <= this.high()), package$logI$logIBoolImpl$.MODULE$));
   }

   private double logNormalizer$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.logNormalizer = this.entropy();
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

   public double mode() {
      return this.mean();
   }

   public double mean() {
      return (this.low() + this.high()) / (double)2;
   }

   public double variance() {
      return .MODULE$.pow(this.high() - this.low(), (double)2.0F) / (double)12;
   }

   public double entropy() {
      return .MODULE$.log(this.high() - this.low());
   }

   public double cdf(final double x) {
      return x <= this.low() ? (double)0.0F : (x >= this.high() ? (double)1.0F : (x - this.low()) / (this.high() - this.low()));
   }

   public double probability(final double x, final double y) {
      return (y - x) / (this.high() - this.low());
   }

   public double inverseCdf(final double p) {
      return (this.high() - this.low()) * p + this.low();
   }

   public Uniform copy(final double low, final double high, final RandBasis rand) {
      return new Uniform(low, high, rand);
   }

   public double copy$default$1() {
      return this.low();
   }

   public double copy$default$2() {
      return this.high();
   }

   public String productPrefix() {
      return "Uniform";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.low());
            break;
         case 1:
            var10000 = BoxesRunTime.boxToDouble(this.high());
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
      return x$1 instanceof Uniform;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "low";
            break;
         case 1:
            var10000 = "high";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.low()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.high()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label51: {
            boolean var2;
            if (x$1 instanceof Uniform) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Uniform var4 = (Uniform)x$1;
               if (this.low() == var4.low() && this.high() == var4.high() && var4.canEqual(this)) {
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

   public double draw$mcD$sp() {
      return this.breeze$stats$distributions$Uniform$$rand.uniform().draw$mcD$sp() * (this.high() - this.low()) + this.low();
   }

   public Uniform(final double low, final double high, final RandBasis rand) {
      this.low = low;
      this.high = high;
      this.breeze$stats$distributions$Uniform$$rand = rand;
      Density.$init$(this);
      Rand.$init$(this);
      ContinuousDistr.$init$(this);
      Product.$init$(this);
      boolean cond$macro$1 = low < high;
      if (!cond$macro$1) {
         throw new IllegalArgumentException("requirement failed: Uniform.this.low.<(Uniform.this.high)");
      }
   }
}
