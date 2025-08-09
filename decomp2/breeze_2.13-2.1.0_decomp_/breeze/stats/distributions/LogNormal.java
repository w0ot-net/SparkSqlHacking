package breeze.stats.distributions;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import breeze.numerics.package;
import breeze.numerics.package$expm1$expm1DoubleImpl$;
import breeze.optimize.DiffFunction;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.math.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t%b\u0001\u0002\u0017.\u0001RB\u0001B\u0017\u0001\u0003\u0016\u0004%\ta\u0017\u0005\t9\u0002\u0011\t\u0012)A\u0005\u007f!AQ\f\u0001BK\u0002\u0013\u00051\f\u0003\u0005_\u0001\tE\t\u0015!\u0003@\u0011!y\u0006A!A!\u0002\u0017\u0001\u0007\"B2\u0001\t\u0003!\u0007b\u00026\u0001\u0005\u0004%Ia\u001b\u0005\u0007_\u0002\u0001\u000b\u0011\u00027\t\u000bA\u0004A\u0011A9\t\u000bI\u0004A\u0011A:\t\u0011Y\u0004\u0001R1A\u0005\u0002mCQa\u001e\u0001\u0005\u0002aDQa\u001f\u0001\u0005\u0002qDQA \u0001\u0005B}Dq!a\u0002\u0001\t\u0003\nI\u0001\u0003\u0004\u0002\u001c\u0001!\ta\u0017\u0005\u0007\u0003;\u0001A\u0011A.\t\r\u0005}\u0001\u0001\"\u0001\\\u0011\u0019\t\t\u0003\u0001C\u00017\"I\u00111\u0005\u0001\u0002\u0002\u0013\u0005\u0011Q\u0005\u0005\n\u0003_\u0001\u0011\u0013!C\u0001\u0003cA\u0011\"a\u0012\u0001#\u0003%\t!!\r\t\u0013\u0005%\u0003!!A\u0005B\u0005-\u0003\"CA.\u0001\u0005\u0005I\u0011AA/\u0011%\t)\u0007AA\u0001\n\u0003\t9\u0007C\u0005\u0002t\u0001\t\t\u0011\"\u0011\u0002v!I\u00111\u0011\u0001\u0002\u0002\u0013\u0005\u0011Q\u0011\u0005\n\u0003\u001f\u0003\u0011\u0011!C!\u0003#C\u0011\"!&\u0001\u0003\u0003%\t%a&\t\u0013\u0005e\u0005!!A\u0005B\u0005muaBAP[!\u0005\u0011\u0011\u0015\u0004\u0007Y5B\t!a)\t\r\r\u0004C\u0011AA^\u000b\u0019\ti\f\t\u0001\u0002@\u00161\u0011Q\u0019\u0011\u0001\u0003\u000fDq!a5!\t\u0003\t)\u000eC\u0004\u0002X\u0002\"\t!!7\t\u000f\u0005}\u0007\u0005\"\u0001\u0002b\"9\u0011\u0011\u001e\u0011\u0005B\u0005-\bbBAzA\u0011\u0005\u0011Q\u001f\u0005\n\u0005\u000b\u0001\u0013\u0011!CA\u0005\u000fA\u0011B!\u0005!\u0003\u0003%\tIa\u0005\t\u0013\t}\u0001%!A\u0005\n\t\u0005\"!\u0003'pO:{'/\\1m\u0015\tqs&A\u0007eSN$(/\u001b2vi&|gn\u001d\u0006\u0003aE\nQa\u001d;biNT\u0011AM\u0001\u0007EJ,WM_3\u0004\u0001MA\u0001!N\u001eC\u000b\"[e\n\u0005\u00027s5\tqGC\u00019\u0003\u0015\u00198-\u00197b\u0013\tQtG\u0001\u0004B]f\u0014VM\u001a\t\u0004yuzT\"A\u0017\n\u0005yj#aD\"p]RLg.^8vg\u0012K7\u000f\u001e:\u0011\u0005Y\u0002\u0015BA!8\u0005\u0019!u.\u001e2mKB!AhQ @\u0013\t!UFA\u0004N_6,g\u000e^:\u0011\u0005q2\u0015BA$.\u0005\u0019A\u0015m]\"eMB\u0011A(S\u0005\u0003\u00156\u0012Q\u0002S1t\u0013:4XM]:f\u0007\u00124\u0007C\u0001\u001cM\u0013\tiuGA\u0004Qe>$Wo\u0019;\u0011\u0005=;fB\u0001)V\u001d\t\tF+D\u0001S\u0015\t\u00196'\u0001\u0004=e>|GOP\u0005\u0002q%\u0011akN\u0001\ba\u0006\u001c7.Y4f\u0013\tA\u0016L\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002Wo\u0005\u0011Q.^\u000b\u0002\u007f\u0005\u0019Q.\u001e\u0011\u0002\u000bMLw-\\1\u0002\rMLw-\\1!\u0003\u0011\u0011\u0018M\u001c3\u0011\u0005q\n\u0017B\u00012.\u0005%\u0011\u0016M\u001c3CCNL7/\u0001\u0004=S:LGO\u0010\u000b\u0004K\"LGC\u00014h!\ta\u0004\u0001C\u0003`\r\u0001\u000f\u0001\rC\u0003[\r\u0001\u0007q\bC\u0003^\r\u0001\u0007q(\u0001\u0006ns\u001e\u000bWo]:jC:,\u0012\u0001\u001c\t\u0003y5L!A\\\u0017\u0003\u0011\u001d\u000bWo]:jC:\f1\"\\=HCV\u001c8/[1oA\u0005!AM]1x)\u0005y\u0014AE;o]>\u0014X.\u00197ju\u0016$Gj\\4QI\u001a$\"a\u0010;\t\u000bUT\u0001\u0019A \u0002\u0003a\fQ\u0002\\8h\u001d>\u0014X.\u00197ju\u0016\u0014\u0018AC5om\u0016\u00148/Z\"eMR\u0011q(\u001f\u0005\u0006u2\u0001\raP\u0001\u0002a\u0006\u00191\r\u001a4\u0015\u0005}j\b\"B;\u000e\u0001\u0004y\u0014a\u00039s_\n\f'-\u001b7jif$RaPA\u0001\u0003\u0007AQ!\u001e\bA\u0002}Ba!!\u0002\u000f\u0001\u0004y\u0014!A=\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a\u0003\u0011\t\u00055\u0011Q\u0003\b\u0005\u0003\u001f\t\t\u0002\u0005\u0002Ro%\u0019\u00111C\u001c\u0002\rA\u0013X\rZ3g\u0013\u0011\t9\"!\u0007\u0003\rM#(/\u001b8h\u0015\r\t\u0019bN\u0001\u0005[\u0016\fg.\u0001\u0005wCJL\u0017M\\2f\u0003\u001d)g\u000e\u001e:paf\fA!\\8eK\u0006!1m\u001c9z)\u0019\t9#a\u000b\u0002.Q\u0019a-!\u000b\t\u000b}#\u00029\u00011\t\u000fi#\u0002\u0013!a\u0001\u007f!9Q\f\u0006I\u0001\u0002\u0004y\u0014AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0003gQ3aPA\u001bW\t\t9\u0004\u0005\u0003\u0002:\u0005\rSBAA\u001e\u0015\u0011\ti$a\u0010\u0002\u0013Ut7\r[3dW\u0016$'bAA!o\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005\u0015\u00131\b\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u00055\u0003\u0003BA(\u00033j!!!\u0015\u000b\t\u0005M\u0013QK\u0001\u0005Y\u0006twM\u0003\u0002\u0002X\u0005!!.\u0019<b\u0013\u0011\t9\"!\u0015\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005}\u0003c\u0001\u001c\u0002b%\u0019\u00111M\u001c\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005%\u0014q\u000e\t\u0004m\u0005-\u0014bAA7o\t\u0019\u0011I\\=\t\u0013\u0005E\u0014$!AA\u0002\u0005}\u0013a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002xA1\u0011\u0011PA@\u0003Sj!!a\u001f\u000b\u0007\u0005ut'\u0001\u0006d_2dWm\u0019;j_:LA!!!\u0002|\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t9)!$\u0011\u0007Y\nI)C\u0002\u0002\f^\u0012qAQ8pY\u0016\fg\u000eC\u0005\u0002rm\t\t\u00111\u0001\u0002j\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\ti%a%\t\u0013\u0005ED$!AA\u0002\u0005}\u0013\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005}\u0013AB3rk\u0006d7\u000f\u0006\u0003\u0002\b\u0006u\u0005\"CA9=\u0005\u0005\t\u0019AA5\u0003%aun\u001a(pe6\fG\u000e\u0005\u0002=AMA\u0001%NAS\u0003W\u000b\t\fE\u0003=\u0003O3w(C\u0002\u0002*6\u0012\u0011#\u0012=q_:,g\u000e^5bY\u001a\u000bW.\u001b7z!\u0015a\u0014QV g\u0013\r\ty+\f\u0002$\u0007>tG/\u001b8v_V\u001cH)[:ue&\u0014W\u000f^5p]V3UO\\2Qe>4\u0018\u000eZ3s!\u0011\t\u0019,!/\u000e\u0005\u0005U&\u0002BA\\\u0003+\n!![8\n\u0007a\u000b)\f\u0006\u0002\u0002\"\nI\u0001+\u0019:b[\u0016$XM\u001d\t\u0006m\u0005\u0005whP\u0005\u0004\u0003\u0007<$A\u0002+va2,'GA\nTk\u001a4\u0017nY5f]R\u001cF/\u0019;jgRL7\r\u0005\u0003\u0002J\u0006=gb\u0001\u001f\u0002L&\u0019\u0011QZ\u0017\u0002\u0011\u001d\u000bWo]:jC:LA!!2\u0002R*\u0019\u0011QZ\u0017\u00021\u0015l\u0007\u000f^=Tk\u001a4\u0017nY5f]R\u001cF/\u0019;jgRL7-\u0006\u0002\u0002H\u000612/\u001e4gS\u000eLWM\u001c;Ti\u0006$\u0018n\u001d;jG\u001a{'\u000f\u0006\u0003\u0002H\u0006m\u0007BBAoK\u0001\u0007q(A\u0001u\u0003\riG.\u001a\u000b\u0005\u0003\u007f\u000b\u0019\u000f\u0003\u00041M\u0001\u0007\u0011Q\u001d\t\u0004\u0003O\u001cS\"\u0001\u0011\u0002\u0019\u0011L7\u000f\u001e:jEV$\u0018n\u001c8\u0015\t\u00055\u0018\u0011\u001f\u000b\u0004M\u0006=\b\"B0(\u0001\b\u0001\u0007B\u0002>(\u0001\u0004\ty,\u0001\nmS.,G.\u001b5p_\u00124UO\\2uS>tG\u0003BA|\u0005\u0007\u0001b!!?\u0002\u0000\u0006}VBAA~\u0015\r\ti0M\u0001\t_B$\u0018.\\5{K&!!\u0011AA~\u00051!\u0015N\u001a4Gk:\u001cG/[8o\u0011\u0019\u0001\u0004\u00061\u0001\u0002f\u0006)\u0011\r\u001d9msR1!\u0011\u0002B\u0007\u0005\u001f!2A\u001aB\u0006\u0011\u0015y\u0016\u0006q\u0001a\u0011\u0015Q\u0016\u00061\u0001@\u0011\u0015i\u0016\u00061\u0001@\u0003\u001d)h.\u00199qYf$BA!\u0006\u0003\u001cA)aGa\u0006\u0002@&\u0019!\u0011D\u001c\u0003\r=\u0003H/[8o\u0011!\u0011iBKA\u0001\u0002\u00041\u0017a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011!1\u0005\t\u0005\u0003\u001f\u0012)#\u0003\u0003\u0003(\u0005E#AB(cU\u0016\u001cG\u000f"
)
public class LogNormal implements ContinuousDistr, Moments, HasCdf, HasInverseCdf, Product {
   private double logNormalizer;
   private final double mu;
   private final double sigma;
   private final Gaussian breeze$stats$distributions$LogNormal$$myGaussian;
   private double normalizer;
   private volatile byte bitmap$0;

   public static Option unapply(final LogNormal x$0) {
      return LogNormal$.MODULE$.unapply(x$0);
   }

   public static DiffFunction likelihoodFunction(final Gaussian.SufficientStatistic stats) {
      return LogNormal$.MODULE$.likelihoodFunction(stats);
   }

   public static LogNormal distribution(final Tuple2 p, final RandBasis rand) {
      return LogNormal$.MODULE$.distribution(p, rand);
   }

   public static Tuple2 mle(final Gaussian.SufficientStatistic stats) {
      return LogNormal$.MODULE$.mle(stats);
   }

   public static Gaussian.SufficientStatistic sufficientStatisticFor(final double t) {
      return LogNormal$.MODULE$.sufficientStatisticFor(t);
   }

   public static Gaussian.SufficientStatistic emptySufficientStatistic() {
      return LogNormal$.MODULE$.emptySufficientStatistic();
   }

   public static ContinuousDistributionUFuncProvider.ContinuousDistrUFuncWrapper ContinuousDistrUFuncWrapper(final ContinuousDistr dist) {
      return LogNormal$.MODULE$.ContinuousDistrUFuncWrapper(dist);
   }

   public static ContinuousDistributionUFuncProvider.basicImpl$ basicImpl() {
      return LogNormal$.MODULE$.basicImpl();
   }

   public static Object withSink(final Object s) {
      return LogNormal$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return LogNormal$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return LogNormal$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return LogNormal$.MODULE$.inPlace(v, impl);
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

   public double mu() {
      return this.mu;
   }

   public double sigma() {
      return this.sigma;
   }

   public Gaussian breeze$stats$distributions$LogNormal$$myGaussian() {
      return this.breeze$stats$distributions$LogNormal$$myGaussian;
   }

   public double draw() {
      return this.draw$mcD$sp();
   }

   public double unnormalizedLogPdf(final double x) {
      if (x <= (double)0.0F) {
         return Double.NEGATIVE_INFINITY;
      } else {
         double logx = .MODULE$.log(x);
         double rad = (logx - this.mu()) / this.sigma();
         return -(rad * rad / (double)2) - logx;
      }
   }

   private double logNormalizer$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.logNormalizer = .MODULE$.log(.MODULE$.sqrt((Math.PI * 2D))) + .MODULE$.log(this.sigma());
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

   public double inverseCdf(final double p) {
      return .MODULE$.exp(this.breeze$stats$distributions$LogNormal$$myGaussian().inverseCdf(p));
   }

   public double cdf(final double x) {
      return this.breeze$stats$distributions$LogNormal$$myGaussian().cdf(.MODULE$.log(x));
   }

   public double probability(final double x, final double y) {
      return this.breeze$stats$distributions$LogNormal$$myGaussian().probability(.MODULE$.log(x), .MODULE$.log(y));
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public double mean() {
      return .MODULE$.exp(this.mu() + this.sigma() * this.sigma() / (double)2);
   }

   public double variance() {
      return package.expm1$.MODULE$.apply$mDDc$sp(this.sigma() * this.sigma(), package$expm1$expm1DoubleImpl$.MODULE$) * .MODULE$.exp((double)2 * this.mu() + this.sigma() * this.sigma());
   }

   public double entropy() {
      return (double)0.5F + (double)0.5F * .MODULE$.log((Math.PI * 2D) * this.sigma() * this.sigma()) + this.mu();
   }

   public double mode() {
      return .MODULE$.exp(this.mu() - this.sigma() * this.sigma());
   }

   public LogNormal copy(final double mu, final double sigma, final RandBasis rand) {
      return new LogNormal(mu, sigma, rand);
   }

   public double copy$default$1() {
      return this.mu();
   }

   public double copy$default$2() {
      return this.sigma();
   }

   public String productPrefix() {
      return "LogNormal";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.mu());
            break;
         case 1:
            var10000 = BoxesRunTime.boxToDouble(this.sigma());
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
      return x$1 instanceof LogNormal;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "mu";
            break;
         case 1:
            var10000 = "sigma";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.mu()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.sigma()));
      return Statics.finalizeHash(var1, 2);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label51: {
            boolean var2;
            if (x$1 instanceof LogNormal) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               LogNormal var4 = (LogNormal)x$1;
               if (this.mu() == var4.mu() && this.sigma() == var4.sigma() && var4.canEqual(this)) {
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
      return .MODULE$.exp(this.breeze$stats$distributions$LogNormal$$myGaussian().draw$mcD$sp());
   }

   public LogNormal(final double mu, final double sigma, final RandBasis rand) {
      this.mu = mu;
      this.sigma = sigma;
      Density.$init$(this);
      Rand.$init$(this);
      ContinuousDistr.$init$(this);
      Product.$init$(this);
      this.breeze$stats$distributions$LogNormal$$myGaussian = new Gaussian(mu, sigma, rand);
      scala.Predef..MODULE$.require(sigma > (double)0, () -> (new StringBuilder(32)).append("Sigma must be positive, but got ").append(this.sigma()).toString());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
