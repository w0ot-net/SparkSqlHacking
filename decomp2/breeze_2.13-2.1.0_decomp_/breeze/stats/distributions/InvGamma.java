package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import breeze.numerics.package;
import breeze.numerics.package$digamma$digammaImplDouble$;
import breeze.numerics.package$lgamma$lgammaImplDouble$;
import breeze.numerics.package$lgamma$lgammaImplDoubleDouble$;
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
   bytes = "\u0006\u0005\u0005]f\u0001\u0002\u0014(\u0001:B\u0001\"\u0015\u0001\u0003\u0016\u0004%\tA\u0015\u0005\t'\u0002\u0011\t\u0012)A\u0005s!AA\u000b\u0001BK\u0002\u0013\u0005!\u000b\u0003\u0005V\u0001\tE\t\u0015!\u0003:\u0011!1\u0006A!b\u0001\n\u00079\u0006\u0002C.\u0001\u0005\u0003\u0005\u000b\u0011\u0002-\t\u000bq\u0003A\u0011A/\t\u000b\r\u0004A\u0011\u00013\t\u0011\u001d\u0004\u0001R1A\u0005\u0002ICQ\u0001\u001b\u0001\u0005\u0002ICQ!\u001b\u0001\u0005\u0002ICQA\u001b\u0001\u0005\u0002ICQa\u001b\u0001\u0005\u0002ICQ\u0001\u001c\u0001\u0005\u00025DQ!\u001d\u0001\u0005\u0002IDq\u0001\u001e\u0001C\u0002\u0013%Q\u000f\u0003\u0004z\u0001\u0001\u0006IA\u001e\u0005\u0006u\u0002!\ta\u001f\u0005\by\u0002\t\t\u0011\"\u0001~\u0011%\t)\u0001AI\u0001\n\u0003\t9\u0001C\u0005\u0002\u001e\u0001\t\n\u0011\"\u0001\u0002\b!I\u0011q\u0004\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0005\u0005\n\u0003g\u0001\u0011\u0011!C\u0001\u0003kA\u0011\"!\u0010\u0001\u0003\u0003%\t!a\u0010\t\u0013\u0005-\u0003!!A\u0005B\u00055\u0003\"CA.\u0001\u0005\u0005I\u0011AA/\u0011%\t9\u0007AA\u0001\n\u0003\nI\u0007C\u0005\u0002n\u0001\t\t\u0011\"\u0011\u0002p!I\u0011\u0011\u000f\u0001\u0002\u0002\u0013\u0005\u00131\u000f\u0005\n\u0003k\u0002\u0011\u0011!C!\u0003o:\u0011\"a\u001f(\u0003\u0003E\t!! \u0007\u0011\u0019:\u0013\u0011!E\u0001\u0003\u007fBa\u0001\u0018\u0011\u0005\u0002\u0005-\u0005\"CA9A\u0005\u0005IQIA:\u0011%\ti\tIA\u0001\n\u0003\u000by\tC\u0005\u0002\u001a\u0002\n\t\u0011\"!\u0002\u001c\"I\u0011Q\u0016\u0011\u0002\u0002\u0013%\u0011q\u0016\u0002\t\u0013:4x)Y7nC*\u0011\u0001&K\u0001\u000eI&\u001cHO]5ckRLwN\\:\u000b\u0005)Z\u0013!B:uCR\u001c(\"\u0001\u0017\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0019r\u0001A\u00186y}\u0012U\t\u0005\u00021g5\t\u0011GC\u00013\u0003\u0015\u00198-\u00197b\u0013\t!\u0014G\u0001\u0004B]f\u0014VM\u001a\t\u0004m]JT\"A\u0014\n\u0005a:#aD\"p]RLg.^8vg\u0012K7\u000f\u001e:\u0011\u0005AR\u0014BA\u001e2\u0005\u0019!u.\u001e2mKB!a'P\u001d:\u0013\tqtEA\u0004N_6,g\u000e^:\u0011\u0005Y\u0002\u0015BA!(\u0005\u0019A\u0015m]\"eMB\u0011\u0001gQ\u0005\u0003\tF\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002G\u001d:\u0011q\t\u0014\b\u0003\u0011.k\u0011!\u0013\u0006\u0003\u00156\na\u0001\u0010:p_Rt\u0014\"\u0001\u001a\n\u00055\u000b\u0014a\u00029bG.\fw-Z\u0005\u0003\u001fB\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!!T\u0019\u0002\u000bMD\u0017\r]3\u0016\u0003e\naa\u001d5ba\u0016\u0004\u0013!B:dC2,\u0017AB:dC2,\u0007%A\u0003cCNL7/F\u0001Y!\t1\u0014,\u0003\u0002[O\tI!+\u00198e\u0005\u0006\u001c\u0018n]\u0001\u0007E\u0006\u001c\u0018n\u001d\u0011\u0002\rqJg.\u001b;?)\rq\u0016M\u0019\u000b\u0003?\u0002\u0004\"A\u000e\u0001\t\u000bY;\u00019\u0001-\t\u000bE;\u0001\u0019A\u001d\t\u000bQ;\u0001\u0019A\u001d\u0002%Utgn\u001c:nC2L'0\u001a3M_\u001e\u0004FM\u001a\u000b\u0003s\u0015DQA\u001a\u0005A\u0002e\n\u0011\u0001_\u0001\u000eY><gj\u001c:nC2L'0\u001a:\u0002\t5,\u0017M\\\u0001\tm\u0006\u0014\u0018.\u00198dK\u00069QM\u001c;s_BL\u0018\u0001B7pI\u0016\f1\u0002\u001d:pE\u0006\u0014\u0017\u000e\\5usR\u0019\u0011H\\8\t\u000b\u0019t\u0001\u0019A\u001d\t\u000bAt\u0001\u0019A\u001d\u0002\u0003e\f1a\u00193g)\tI4\u000fC\u0003g\u001f\u0001\u0007\u0011(\u0001\u0006j]:,'oR1n[\u0006,\u0012A\u001e\t\u0003m]L!\u0001_\u0014\u0003\u000b\u001d\u000bW.\\1\u0002\u0017%tg.\u001a:HC6l\u0017\rI\u0001\u0005IJ\fw\u000fF\u0001:\u0003\u0011\u0019w\u000e]=\u0015\u000by\f\t!a\u0001\u0015\u0005}{\b\"\u0002,\u0014\u0001\bA\u0006bB)\u0014!\u0003\u0005\r!\u000f\u0005\b)N\u0001\n\u00111\u0001:\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"!!\u0003+\u0007e\nYa\u000b\u0002\u0002\u000eA!\u0011qBA\r\u001b\t\t\tB\u0003\u0003\u0002\u0014\u0005U\u0011!C;oG\",7m[3e\u0015\r\t9\"M\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\u000e\u0003#\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA\u0012!\u0011\t)#a\f\u000e\u0005\u0005\u001d\"\u0002BA\u0015\u0003W\tA\u0001\\1oO*\u0011\u0011QF\u0001\u0005U\u00064\u0018-\u0003\u0003\u00022\u0005\u001d\"AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u00028A\u0019\u0001'!\u000f\n\u0007\u0005m\u0012GA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002B\u0005\u001d\u0003c\u0001\u0019\u0002D%\u0019\u0011QI\u0019\u0003\u0007\u0005s\u0017\u0010C\u0005\u0002Ja\t\t\u00111\u0001\u00028\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a\u0014\u0011\r\u0005E\u0013qKA!\u001b\t\t\u0019FC\u0002\u0002VE\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\tI&a\u0015\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003?\n)\u0007E\u00021\u0003CJ1!a\u00192\u0005\u001d\u0011un\u001c7fC:D\u0011\"!\u0013\u001b\u0003\u0003\u0005\r!!\u0011\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003G\tY\u0007C\u0005\u0002Jm\t\t\u00111\u0001\u00028\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u00028\u0005AAo\\*ue&tw\r\u0006\u0002\u0002$\u00051Q-];bYN$B!a\u0018\u0002z!I\u0011\u0011\n\u0010\u0002\u0002\u0003\u0007\u0011\u0011I\u0001\t\u0013:4x)Y7nCB\u0011a\u0007I\n\u0005A=\n\t\t\u0005\u0003\u0002\u0004\u0006%UBAAC\u0015\u0011\t9)a\u000b\u0002\u0005%|\u0017bA(\u0002\u0006R\u0011\u0011QP\u0001\u0006CB\u0004H.\u001f\u000b\u0007\u0003#\u000b)*a&\u0015\u0007}\u000b\u0019\nC\u0003WG\u0001\u000f\u0001\fC\u0003RG\u0001\u0007\u0011\bC\u0003UG\u0001\u0007\u0011(A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005u\u0015\u0011\u0016\t\u0006a\u0005}\u00151U\u0005\u0004\u0003C\u000b$AB(qi&|g\u000eE\u00031\u0003KK\u0014(C\u0002\u0002(F\u0012a\u0001V;qY\u0016\u0014\u0004\u0002CAVI\u0005\u0005\t\u0019A0\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u00022B!\u0011QEAZ\u0013\u0011\t),a\n\u0003\r=\u0013'.Z2u\u0001"
)
public class InvGamma implements ContinuousDistr, Moments, HasCdf, Product {
   private double logNormalizer;
   private final double shape;
   private final double scale;
   private final RandBasis basis;
   private final Gamma breeze$stats$distributions$InvGamma$$innerGamma;
   private double normalizer;
   private volatile byte bitmap$0;

   public static Option unapply(final InvGamma x$0) {
      return InvGamma$.MODULE$.unapply(x$0);
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

   public double shape() {
      return this.shape;
   }

   public double scale() {
      return this.scale;
   }

   public RandBasis basis() {
      return this.basis;
   }

   public double unnormalizedLogPdf(final double x) {
      return .MODULE$.log(x) * (-this.shape() - (double)1) - this.scale() / x;
   }

   private double logNormalizer$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.logNormalizer = this.shape() * .MODULE$.log(this.scale()) - package.lgamma$.MODULE$.apply$mDDc$sp(this.shape(), package$lgamma$lgammaImplDouble$.MODULE$);
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
      scala.Predef..MODULE$.require(this.shape() > (double)1);
      return this.scale() / (this.shape() - (double)1);
   }

   public double variance() {
      scala.Predef..MODULE$.require(this.shape() > (double)2);
      return this.scale() * this.scale() / ((this.shape() - (double)1) * (this.shape() - (double)1) * (this.shape() - (double)2));
   }

   public double entropy() {
      return this.shape() + .MODULE$.log(this.scale()) + package.lgamma$.MODULE$.apply$mDDc$sp(this.shape(), package$lgamma$lgammaImplDouble$.MODULE$) - ((double)1 + this.shape()) * package.digamma$.MODULE$.apply$mDDc$sp(this.shape(), package$digamma$digammaImplDouble$.MODULE$);
   }

   public double mode() {
      return this.scale() / (this.shape() + (double)1);
   }

   public double probability(final double x, final double y) {
      return this.cdf(y) - this.cdf(x);
   }

   public double cdf(final double x) {
      return (double)1 - .MODULE$.exp(package.lgamma$.MODULE$.apply$mDDDc$sp(this.shape(), this.scale() / x, package$lgamma$lgammaImplDoubleDouble$.MODULE$) - package.lgamma$.MODULE$.apply$mDDc$sp(this.shape(), package$lgamma$lgammaImplDouble$.MODULE$));
   }

   public Gamma breeze$stats$distributions$InvGamma$$innerGamma() {
      return this.breeze$stats$distributions$InvGamma$$innerGamma;
   }

   public double draw() {
      return this.draw$mcD$sp();
   }

   public InvGamma copy(final double shape, final double scale, final RandBasis basis) {
      return new InvGamma(shape, scale, basis);
   }

   public double copy$default$1() {
      return this.shape();
   }

   public double copy$default$2() {
      return this.scale();
   }

   public String productPrefix() {
      return "InvGamma";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.shape());
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
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof InvGamma;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "shape";
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
      var1 = Statics.mix(var1, Statics.doubleHash(this.shape()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.scale()));
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
            if (x$1 instanceof InvGamma) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               InvGamma var4 = (InvGamma)x$1;
               if (this.shape() == var4.shape() && this.scale() == var4.scale() && var4.canEqual(this)) {
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
      return (double)1.0F / this.breeze$stats$distributions$InvGamma$$innerGamma().draw$mcD$sp();
   }

   public InvGamma(final double shape, final double scale, final RandBasis basis) {
      this.shape = shape;
      this.scale = scale;
      this.basis = basis;
      Density.$init$(this);
      Rand.$init$(this);
      ContinuousDistr.$init$(this);
      Product.$init$(this);
      this.breeze$stats$distributions$InvGamma$$innerGamma = new Gamma(shape, (double)1 / scale, basis);
   }
}
