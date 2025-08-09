package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import breeze.numerics.package;
import breeze.numerics.package$expm1$expm1DoubleImpl$;
import breeze.numerics.package$log$logDoubleImpl$;
import breeze.numerics.package$sqrt$sqrtDoubleImpl$;
import breeze.numerics.package$sqrt$sqrtIntImpl$;
import breeze.numerics.constants.package$;
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
   bytes = "\u0006\u0005\u0005%e\u0001\u0002\u0011\"\u0001\"B\u0001b\u0013\u0001\u0003\u0016\u0004%\t\u0001\u0014\u0005\t\u001b\u0002\u0011\t\u0012)A\u0005g!Aa\n\u0001B\u0001B\u0003-q\nC\u0003S\u0001\u0011\u00051\u000bC\u0003Y\u0001\u0011\u0005A\nC\u0003Z\u0001\u0011\u0005A\nC\u0003[\u0001\u0011\u0005A\nC\u0003\\\u0001\u0011\u0005A\nC\u0003]\u0001\u0011\u0005A\nC\u0003^\u0001\u0011\u0005a\fC\u0003`\u0001\u0011\u0005\u0001\rC\u0003d\u0001\u0011\u0005A\rC\u0003g\u0001\u0011\u0005s\rC\u0004l\u0001\u0005\u0005I\u0011\u00017\t\u000fA\u0004\u0011\u0013!C\u0001c\"9A\u0010AA\u0001\n\u0003j\b\"CA\u0007\u0001\u0005\u0005I\u0011AA\b\u0011%\t9\u0002AA\u0001\n\u0003\tI\u0002C\u0005\u0002&\u0001\t\t\u0011\"\u0011\u0002(!I\u0011Q\u0007\u0001\u0002\u0002\u0013\u0005\u0011q\u0007\u0005\n\u0003\u0003\u0002\u0011\u0011!C!\u0003\u0007B\u0011\"a\u0012\u0001\u0003\u0003%\t%!\u0013\t\u0013\u0005-\u0003!!A\u0005B\u00055\u0003\"CA(\u0001\u0005\u0005I\u0011IA)\u000f%\t)&IA\u0001\u0012\u0003\t9F\u0002\u0005!C\u0005\u0005\t\u0012AA-\u0011\u0019\u0011&\u0004\"\u0001\u0002f!I\u00111\n\u000e\u0002\u0002\u0013\u0015\u0013Q\n\u0005\n\u0003OR\u0012\u0011!CA\u0003SB\u0011\"!\u001d\u001b\u0003\u0003%\t)a\u001d\t\u0013\u0005}$$!A\u0005\n\u0005\u0005%\u0001\u0003*bs2,\u0017n\u001a5\u000b\u0005\t\u001a\u0013!\u00043jgR\u0014\u0018NY;uS>t7O\u0003\u0002%K\u0005)1\u000f^1ug*\ta%\u0001\u0004ce\u0016,'0Z\u0002\u0001'\u001d\u0001\u0011f\f\u001c:y}\u0002\"AK\u0017\u000e\u0003-R\u0011\u0001L\u0001\u0006g\u000e\fG.Y\u0005\u0003]-\u0012a!\u00118z%\u00164\u0007c\u0001\u00192g5\t\u0011%\u0003\u00023C\ty1i\u001c8uS:,x.^:ESN$(\u000f\u0005\u0002+i%\u0011Qg\u000b\u0002\u0007\t>,(\r\\3\u0011\tA:4gM\u0005\u0003q\u0005\u0012q!T8nK:$8\u000f\u0005\u00021u%\u00111(\t\u0002\u0007\u0011\u0006\u001c8\t\u001a4\u0011\u0005)j\u0014B\u0001 ,\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001\u0011%\u000f\u0005\u00053eB\u0001\"F\u001b\u0005\u0019%B\u0001#(\u0003\u0019a$o\\8u}%\tA&\u0003\u0002HW\u00059\u0001/Y2lC\u001e,\u0017BA%K\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t95&A\u0003tG\u0006dW-F\u00014\u0003\u0019\u00198-\u00197fA\u0005!!/\u00198e!\t\u0001\u0004+\u0003\u0002RC\tI!+\u00198e\u0005\u0006\u001c\u0018n]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005Q;FCA+W!\t\u0001\u0004\u0001C\u0003O\t\u0001\u000fq\nC\u0003L\t\u0001\u00071'\u0001\u0003nK\u0006t\u0017\u0001B7pI\u0016\f\u0001B^1sS\u0006t7-Z\u0001\bK:$(o\u001c9z\u00035awn\u001a(pe6\fG.\u001b>fe\u0006!AM]1x)\u0005\u0019\u0014AE;o]>\u0014X.\u00197ju\u0016$Gj\\4QI\u001a$\"aM1\t\u000b\t\\\u0001\u0019A\u001a\u0002\u0003a\f1a\u00193g)\t\u0019T\rC\u0003c\u0019\u0001\u00071'A\u0006qe>\u0014\u0017MY5mSRLHcA\u001aiS\")!-\u0004a\u0001g!)!.\u0004a\u0001g\u0005\t\u00110\u0001\u0003d_BLHCA7p)\t)f\u000eC\u0003O\u001d\u0001\u000fq\nC\u0004L\u001dA\u0005\t\u0019A\u001a\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\t!O\u000b\u00024g.\nA\u000f\u0005\u0002vu6\taO\u0003\u0002xq\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003s.\n!\"\u00198o_R\fG/[8o\u0013\tYhOA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u0001@\u0011\u0007}\fI!\u0004\u0002\u0002\u0002)!\u00111AA\u0003\u0003\u0011a\u0017M\\4\u000b\u0005\u0005\u001d\u0011\u0001\u00026bm\u0006LA!a\u0003\u0002\u0002\t11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!!\u0005\u0011\u0007)\n\u0019\"C\u0002\u0002\u0016-\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a\u0007\u0002\"A\u0019!&!\b\n\u0007\u0005}1FA\u0002B]fD\u0011\"a\t\u0013\u0003\u0003\u0005\r!!\u0005\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\tI\u0003\u0005\u0004\u0002,\u0005E\u00121D\u0007\u0003\u0003[Q1!a\f,\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003g\tiC\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u001d\u0003\u007f\u00012AKA\u001e\u0013\r\tid\u000b\u0002\b\u0005>|G.Z1o\u0011%\t\u0019\u0003FA\u0001\u0002\u0004\tY\"\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,Gc\u0001@\u0002F!I\u00111E\u000b\u0002\u0002\u0003\u0007\u0011\u0011C\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011\u0011C\u0001\ti>\u001cFO]5oOR\ta0\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003s\t\u0019\u0006C\u0005\u0002$a\t\t\u00111\u0001\u0002\u001c\u0005A!+Y=mK&<\u0007\u000e\u0005\u000215M!!$KA.!\u0011\ti&a\u0019\u000e\u0005\u0005}#\u0002BA1\u0003\u000b\t!![8\n\u0007%\u000by\u0006\u0006\u0002\u0002X\u0005)\u0011\r\u001d9msR!\u00111NA8)\r)\u0016Q\u000e\u0005\u0006\u001dv\u0001\u001da\u0014\u0005\u0006\u0017v\u0001\raM\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t)(a\u001f\u0011\t)\n9hM\u0005\u0004\u0003sZ#AB(qi&|g\u000e\u0003\u0005\u0002~y\t\t\u00111\u0001V\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u0007\u00032a`AC\u0013\u0011\t9)!\u0001\u0003\r=\u0013'.Z2u\u0001"
)
public class Rayleigh implements ContinuousDistr, Moments, HasCdf, Product {
   private final double scale;
   public final RandBasis breeze$stats$distributions$Rayleigh$$rand;
   private double normalizer;
   private volatile boolean bitmap$0;

   public static Option unapply(final Rayleigh x$0) {
      return Rayleigh$.MODULE$.unapply(x$0);
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
         if (!this.bitmap$0) {
            this.normalizer = ContinuousDistr.normalizer$(this);
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.normalizer;
   }

   public double normalizer() {
      return !this.bitmap$0 ? this.normalizer$lzycompute() : this.normalizer;
   }

   public double scale() {
      return this.scale;
   }

   public double mean() {
      return this.scale() * package.sqrt$.MODULE$.apply$mDDc$sp(package$.MODULE$.Pi() / (double)2, package$sqrt$sqrtDoubleImpl$.MODULE$);
   }

   public double mode() {
      return this.scale();
   }

   public double variance() {
      return ((double)4 - package$.MODULE$.Pi()) / (double)2 * this.scale() * this.scale();
   }

   public double entropy() {
      return package.log$.MODULE$.apply$mDDc$sp(this.scale() / package.sqrt$.MODULE$.apply$mIDc$sp(2, package$sqrt$sqrtIntImpl$.MODULE$), package$log$logDoubleImpl$.MODULE$) + package$.MODULE$.γ() / (double)2 + (double)1;
   }

   public double logNormalizer() {
      return (double)2 * .MODULE$.log(this.scale());
   }

   public double draw() {
      return this.draw$mcD$sp();
   }

   public double unnormalizedLogPdf(final double x) {
      return x <= (double)0.0F ? Double.NEGATIVE_INFINITY : package.log$.MODULE$.apply$mDDc$sp(x, package$log$logDoubleImpl$.MODULE$) - x * x / ((double)2 * this.scale() * this.scale());
   }

   public double cdf(final double x) {
      double xs = x / this.scale();
      return -package.expm1$.MODULE$.apply$mDDc$sp(-(xs * xs) / (double)2, package$expm1$expm1DoubleImpl$.MODULE$);
   }

   public double probability(final double x, final double y) {
      return this.cdf(y) - this.cdf(x);
   }

   public Rayleigh copy(final double scale, final RandBasis rand) {
      return new Rayleigh(scale, rand);
   }

   public double copy$default$1() {
      return this.scale();
   }

   public String productPrefix() {
      return "Rayleigh";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.scale());
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
      return x$1 instanceof Rayleigh;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
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
      var1 = Statics.mix(var1, Statics.doubleHash(this.scale()));
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof Rayleigh) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Rayleigh var4 = (Rayleigh)x$1;
               if (this.scale() == var4.scale() && var4.canEqual(this)) {
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
      return .MODULE$.sqrt((double)2 * (new Exponential((double)1.0F, this.breeze$stats$distributions$Rayleigh$$rand)).draw$mcD$sp() * this.scale() * this.scale());
   }

   public Rayleigh(final double scale, final RandBasis rand) {
      this.scale = scale;
      this.breeze$stats$distributions$Rayleigh$$rand = rand;
      Density.$init$(this);
      Rand.$init$(this);
      ContinuousDistr.$init$(this);
      Product.$init$(this);
   }
}
