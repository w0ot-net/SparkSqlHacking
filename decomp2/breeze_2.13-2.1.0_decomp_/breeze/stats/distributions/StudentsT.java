package breeze.stats.distributions;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import breeze.numerics.package;
import breeze.numerics.package$digamma$digammaImplDouble$;
import breeze.numerics.package$lbeta$impl2Double$;
import breeze.numerics.package$lgamma$lgammaImplDouble$;
import breeze.numerics.package$log$logDoubleImpl$;
import breeze.numerics.package$sqrt$sqrtDoubleImpl$;
import java.lang.invoke.SerializedLambda;
import org.apache.commons.math3.distribution.TDistribution;
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
   bytes = "\u0006\u0005\u0005\u0005g\u0001B\u0011#\u0001&B\u0001\u0002\u0014\u0001\u0003\u0016\u0004%\t!\u0014\u0005\t\u001d\u0002\u0011\t\u0012)A\u0005i!Aq\n\u0001B\u0001B\u0003-\u0001\u000bC\u0003T\u0001\u0011\u0005A\u000bC\u0003Z\u0001\u0011\u0005#\fC\u0004d\u0001\t\u0007I\u0011\u00023\t\rM\u0004\u0001\u0015!\u0003f\u0011\u0015!\b\u0001\"\u0001v\u0011\u00151\b\u0001\"\u0011x\u0011\u0015a\b\u0001\"\u0001~\u0011\u0019y\b\u0001\"\u0001\u0002\u0002!I\u0011Q\u0001\u0001\t\u0006\u0004%\t!\u0014\u0005\u0007\u0003\u000f\u0001A\u0011A'\t\r\u0005%\u0001\u0001\"\u0001N\u0011\u0019\tY\u0001\u0001C\u0001\u001b\"1\u0011Q\u0002\u0001\u0005\u00025C\u0011\"a\u0004\u0001\u0003\u0003%\t!!\u0005\t\u0013\u0005e\u0001!%A\u0005\u0002\u0005m\u0001\"CA\u0019\u0001\u0005\u0005I\u0011IA\u001a\u0011%\t\u0019\u0005AA\u0001\n\u0003\t)\u0005C\u0005\u0002N\u0001\t\t\u0011\"\u0001\u0002P!I\u00111\f\u0001\u0002\u0002\u0013\u0005\u0013Q\f\u0005\n\u0003W\u0002\u0011\u0011!C\u0001\u0003[B\u0011\"a\u001e\u0001\u0003\u0003%\t%!\u001f\t\u0013\u0005u\u0004!!A\u0005B\u0005}\u0004\"CAA\u0001\u0005\u0005I\u0011IAB\u000f\u001d\t9I\tE\u0001\u0003\u00133a!\t\u0012\t\u0002\u0005-\u0005BB*\u001d\t\u0003\ti\nC\u0005\u0002 r\t\t\u0011\"!\u0002\"\"I\u0011\u0011\u0016\u000f\u0002\u0002\u0013\u0005\u00151\u0016\u0005\n\u0003oc\u0012\u0011!C\u0005\u0003s\u0013\u0011b\u0015;vI\u0016tGo\u001d+\u000b\u0005\r\"\u0013!\u00043jgR\u0014\u0018NY;uS>t7O\u0003\u0002&M\u0005)1\u000f^1ug*\tq%\u0001\u0004ce\u0016,'0Z\u0002\u0001'\u001d\u0001!\u0006M\u001c;{\u0001\u0003\"a\u000b\u0018\u000e\u00031R\u0011!L\u0001\u0006g\u000e\fG.Y\u0005\u0003_1\u0012a!\u00118z%\u00164\u0007cA\u00193i5\t!%\u0003\u00024E\ty1i\u001c8uS:,x.^:ESN$(\u000f\u0005\u0002,k%\u0011a\u0007\f\u0002\u0007\t>,(\r\\3\u0011\tEBD\u0007N\u0005\u0003s\t\u0012q!T8nK:$8\u000f\u0005\u00022w%\u0011AH\t\u0002\u0007\u0011\u0006\u001c8\t\u001a4\u0011\u0005-r\u0014BA -\u0005\u001d\u0001&o\u001c3vGR\u0004\"!Q%\u000f\u0005\t;eBA\"G\u001b\u0005!%BA#)\u0003\u0019a$o\\8u}%\tQ&\u0003\u0002IY\u00059\u0001/Y2lC\u001e,\u0017B\u0001&L\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tAE&\u0001\teK\u001e\u0014X-Z:PM\u001a\u0013X-\u001a3p[V\tA'A\teK\u001e\u0014X-Z:PM\u001a\u0013X-\u001a3p[\u0002\n\u0011B]1oI\n\u000b7/[:\u0011\u0005E\n\u0016B\u0001*#\u0005%\u0011\u0016M\u001c3CCNL7/\u0001\u0004=S:LGO\u0010\u000b\u0003+b#\"AV,\u0011\u0005E\u0002\u0001\"B(\u0005\u0001\b\u0001\u0006\"\u0002'\u0005\u0001\u0004!\u0014\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003m\u0003\"\u0001\u00181\u000f\u0005us\u0006CA\"-\u0013\tyF&\u0001\u0004Qe\u0016$WMZ\u0005\u0003C\n\u0014aa\u0015;sS:<'BA0-\u00035IgN\\3s\u0013:\u001cH/\u00198dKV\tQ\r\u0005\u0002gc6\tqM\u0003\u0002iS\u0006aA-[:ue&\u0014W\u000f^5p]*\u0011!n[\u0001\u0006[\u0006$\bn\r\u0006\u0003Y6\fqaY8n[>t7O\u0003\u0002o_\u00061\u0011\r]1dQ\u0016T\u0011\u0001]\u0001\u0004_J<\u0017B\u0001:h\u00055!F)[:ue&\u0014W\u000f^5p]\u0006q\u0011N\u001c8fe&s7\u000f^1oG\u0016\u0004\u0013\u0001\u00023sC^$\u0012\u0001N\u0001\faJ|'-\u00192jY&$\u0018\u0010F\u00025qjDQ!_\u0005A\u0002Q\n\u0011\u0001\u001f\u0005\u0006w&\u0001\r\u0001N\u0001\u0002s\u0006\u00191\r\u001a4\u0015\u0005Qr\b\"B=\u000b\u0001\u0004!\u0014AE;o]>\u0014X.\u00197ju\u0016$Gj\\4QI\u001a$2\u0001NA\u0002\u0011\u0015I8\u00021\u00015\u00035awn\u001a(pe6\fG.\u001b>fe\u0006!Q.Z1o\u0003!1\u0018M]5b]\u000e,\u0017aB3oiJ|\u0007/_\u0001\u0005[>$W-\u0001\u0003d_BLH\u0003BA\n\u0003/!2AVA\u000b\u0011\u0015y\u0015\u0003q\u0001Q\u0011\u001da\u0015\u0003%AA\u0002Q\nabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002\u001e)\u001aA'a\b,\u0005\u0005\u0005\u0002\u0003BA\u0012\u0003[i!!!\n\u000b\t\u0005\u001d\u0012\u0011F\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a\u000b-\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003_\t)CA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA\u001b!\u0011\t9$!\u0011\u000e\u0005\u0005e\"\u0002BA\u001e\u0003{\tA\u0001\\1oO*\u0011\u0011qH\u0001\u0005U\u00064\u0018-C\u0002b\u0003s\tA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!a\u0012\u0011\u0007-\nI%C\u0002\u0002L1\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!!\u0015\u0002XA\u00191&a\u0015\n\u0007\u0005UCFA\u0002B]fD\u0011\"!\u0017\u0016\u0003\u0003\u0005\r!a\u0012\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\ty\u0006\u0005\u0004\u0002b\u0005\u001d\u0014\u0011K\u0007\u0003\u0003GR1!!\u001a-\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003S\n\u0019G\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA8\u0003k\u00022aKA9\u0013\r\t\u0019\b\f\u0002\b\u0005>|G.Z1o\u0011%\tIfFA\u0001\u0002\u0004\t\t&\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA\u001b\u0003wB\u0011\"!\u0017\u0019\u0003\u0003\u0005\r!a\u0012\u0002\u0011!\f7\u000f[\"pI\u0016$\"!a\u0012\u0002\r\u0015\fX/\u00197t)\u0011\ty'!\"\t\u0013\u0005e#$!AA\u0002\u0005E\u0013!C*uk\u0012,g\u000e^:U!\t\tDd\u0005\u0004\u001dU\u00055\u00151\u0013\t\u0006c\u0005=EGV\u0005\u0004\u0003#\u0013#aI\"p]RLg.^8vg\u0012K7\u000f\u001e:jEV$\u0018n\u001c8V\rVt7\r\u0015:pm&$WM\u001d\t\u0005\u0003+\u000bY*\u0004\u0002\u0002\u0018*!\u0011\u0011TA\u001f\u0003\tIw.C\u0002K\u0003/#\"!!#\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\t\u0005\r\u0016q\u0015\u000b\u0004-\u0006\u0015\u0006\"B(\u001f\u0001\b\u0001\u0006\"\u0002'\u001f\u0001\u0004!\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003[\u000b\u0019\f\u0005\u0003,\u0003_#\u0014bAAYY\t1q\n\u001d;j_:D\u0001\"!. \u0003\u0003\u0005\rAV\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA^!\u0011\t9$!0\n\t\u0005}\u0016\u0011\b\u0002\u0007\u001f\nTWm\u0019;"
)
public class StudentsT implements ContinuousDistr, Moments, HasCdf, Product {
   private double logNormalizer;
   private final double degreesOfFreedom;
   public final RandBasis breeze$stats$distributions$StudentsT$$randBasis;
   private final TDistribution innerInstance;
   private double normalizer;
   private volatile byte bitmap$0;

   public static Option unapply(final StudentsT x$0) {
      return StudentsT$.MODULE$.unapply(x$0);
   }

   public static ContinuousDistributionUFuncProvider.ContinuousDistrUFuncWrapper ContinuousDistrUFuncWrapper(final ContinuousDistr dist) {
      return StudentsT$.MODULE$.ContinuousDistrUFuncWrapper(dist);
   }

   public static ContinuousDistributionUFuncProvider.basicImpl$ basicImpl() {
      return StudentsT$.MODULE$.basicImpl();
   }

   public static Object withSink(final Object s) {
      return StudentsT$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return StudentsT$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return StudentsT$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return StudentsT$.MODULE$.inPlace(v, impl);
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

   public double degreesOfFreedom() {
      return this.degreesOfFreedom;
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   private TDistribution innerInstance() {
      return this.innerInstance;
   }

   public double draw() {
      return this.draw$mcD$sp();
   }

   public double probability(final double x, final double y) {
      TDistribution t = new TDistribution(this.degreesOfFreedom());
      return t.probability(x, y);
   }

   public double cdf(final double x) {
      TDistribution t = new TDistribution(this.degreesOfFreedom());
      return t.cumulativeProbability(x);
   }

   public double unnormalizedLogPdf(final double x) {
      return -(this.degreesOfFreedom() + (double)1) / (double)2 * scala.math.package..MODULE$.log((double)1 + x * x / this.degreesOfFreedom());
   }

   private double logNormalizer$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.logNormalizer = (double)0.5F * scala.math.package..MODULE$.log(Math.PI * this.degreesOfFreedom()) + package.lgamma$.MODULE$.apply$mDDc$sp(this.degreesOfFreedom() / (double)2, package$lgamma$lgammaImplDouble$.MODULE$) - package.lgamma$.MODULE$.apply$mDDc$sp((this.degreesOfFreedom() + (double)1) / (double)2, package$lgamma$lgammaImplDouble$.MODULE$);
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
      return this.innerInstance().getNumericalMean();
   }

   public double variance() {
      return this.innerInstance().getNumericalVariance();
   }

   public double entropy() {
      return (this.degreesOfFreedom() + (double)1) / (double)2 * (package.digamma$.MODULE$.apply$mDDc$sp((this.degreesOfFreedom() + (double)1) / (double)2, package$digamma$digammaImplDouble$.MODULE$) - package.digamma$.MODULE$.apply$mDDc$sp(this.degreesOfFreedom(), package$digamma$digammaImplDouble$.MODULE$)) - (double)0.5F * package.log$.MODULE$.apply$mDDc$sp(this.degreesOfFreedom(), package$log$logDoubleImpl$.MODULE$) + package.lbeta$.MODULE$.apply$mDDDc$sp(this.degreesOfFreedom() / (double)2, (double)0.5F, package$lbeta$impl2Double$.MODULE$);
   }

   public double mode() {
      return this.mean();
   }

   public StudentsT copy(final double degreesOfFreedom, final RandBasis randBasis) {
      return new StudentsT(degreesOfFreedom, randBasis);
   }

   public double copy$default$1() {
      return this.degreesOfFreedom();
   }

   public String productPrefix() {
      return "StudentsT";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.degreesOfFreedom());
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
      return x$1 instanceof StudentsT;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "degreesOfFreedom";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.degreesOfFreedom()));
      return Statics.finalizeHash(var1, 1);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof StudentsT) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               StudentsT var4 = (StudentsT)x$1;
               if (this.degreesOfFreedom() == var4.degreesOfFreedom() && var4.canEqual(this)) {
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
      double N = this.breeze$stats$distributions$StudentsT$$randBasis.gaussian().draw$mcD$sp();
      double G = (new Gamma(this.degreesOfFreedom() / (double)2, (double)1.0F, this.breeze$stats$distributions$StudentsT$$randBasis)).draw$mcD$sp();
      double X = package.sqrt$.MODULE$.apply$mDDc$sp(this.degreesOfFreedom() / (double)2, package$sqrt$sqrtDoubleImpl$.MODULE$) * N / package.sqrt$.MODULE$.apply$mDDc$sp(G, package$sqrt$sqrtDoubleImpl$.MODULE$);
      return X;
   }

   public StudentsT(final double degreesOfFreedom, final RandBasis randBasis) {
      this.degreesOfFreedom = degreesOfFreedom;
      this.breeze$stats$distributions$StudentsT$$randBasis = randBasis;
      Density.$init$(this);
      Rand.$init$(this);
      ContinuousDistr.$init$(this);
      Product.$init$(this);
      scala.Predef..MODULE$.require(degreesOfFreedom > (double)0, () -> (new StringBuilder(43)).append("degreesOfFreedom must be positive, but got ").append(this.degreesOfFreedom()).toString());
      this.innerInstance = new TDistribution(randBasis.generator(), degreesOfFreedom, 1.0E-9);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
