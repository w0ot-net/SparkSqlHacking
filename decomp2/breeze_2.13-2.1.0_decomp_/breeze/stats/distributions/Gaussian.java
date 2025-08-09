package breeze.stats.distributions;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import breeze.numerics.package;
import breeze.numerics.package$erf$erfImplDouble$;
import breeze.numerics.package$erfinv$erfinvImplDouble$;
import breeze.numerics.package$log$logDoubleImpl$;
import breeze.numerics.package$sqrt$sqrtDoubleImpl$;
import breeze.optimize.DiffFunction;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\rg\u0001\u0002(P\u0001ZC\u0001\u0002 \u0001\u0003\u0016\u0004%\t! \u0005\t}\u0002\u0011\t\u0012)A\u0005C\"Aq\u0010\u0001BK\u0002\u0013\u0005Q\u0010C\u0005\u0002\u0002\u0001\u0011\t\u0012)A\u0005C\"Q\u00111\u0001\u0001\u0003\u0002\u0003\u0006Y!!\u0002\t\u000f\u0005-\u0001\u0001\"\u0001\u0002\u000e!I\u0011\u0011\u0004\u0001C\u0002\u0013%\u00111\u0004\u0005\t\u0003G\u0001\u0001\u0015!\u0003\u0002\u001e!9\u0011Q\u0005\u0001\u0005\u0002\u0005\u001d\u0002bBA\u0015\u0001\u0011\u0005\u00131\u0006\u0005\b\u0003{\u0001A\u0011IA \u0011\u001d\tI\u0005\u0001C\u0001\u0003\u0017Bq!!\u0015\u0001\t\u0003\t\u0019\u0006C\u0004\u0002X\u0001!\t%!\u0017\t\u0013\u0005}\u0003\u0001#b\u0001\n\u0003j\b\"CA1\u0001!\u0015\r\u0011\"\u0001~\u0011\u0019\t\u0019\u0007\u0001C\u0001{\"1\u0011Q\r\u0001\u0005\u0002uDa!a\u001a\u0001\t\u0003i\bBBA5\u0001\u0011\u0005Q\u0010C\u0005\u0002l\u0001\t\t\u0011\"\u0001\u0002n!I\u0011q\u000f\u0001\u0012\u0002\u0013\u0005\u0011\u0011\u0010\u0005\n\u0003\u001f\u0003\u0011\u0013!C\u0001\u0003sB\u0011\"!%\u0001\u0003\u0003%\t%a%\t\u0013\u0005U\u0005!!A\u0005\u0002\u0005]\u0005\"CAP\u0001\u0005\u0005I\u0011AAQ\u0011%\ti\u000bAA\u0001\n\u0003\ny\u000bC\u0005\u0002>\u0002\t\t\u0011\"\u0001\u0002@\"I\u0011\u0011\u001a\u0001\u0002\u0002\u0013\u0005\u00131\u001a\u0005\n\u0003\u001f\u0004\u0011\u0011!C!\u0003#D\u0011\"a5\u0001\u0003\u0003%\t%!6\b\u000f\u0005ew\n#\u0001\u0002\\\u001a1aj\u0014E\u0001\u0003;Dq!a\u0003\"\t\u0003\t)\u0010\u0003\u0005\u0002x\u0006\u0012\r\u0011\"\u0003~\u0011\u001d\tI0\tQ\u0001\n\u0005,a!a?\"\u0001\u0005uhA\u0002B\u0002C\t\u0013)\u0001C\u0005\u0003\u0010\u0019\u0012)\u001a!C\u0001{\"I!\u0011\u0003\u0014\u0003\u0012\u0003\u0006I!\u0019\u0005\n\u0003G2#Q3A\u0005\u0002uD\u0011Ba\u0005'\u0005#\u0005\u000b\u0011B1\t\u0013\tUaE!f\u0001\n\u0003i\b\"\u0003B\fM\tE\t\u0015!\u0003b\u0011\u001d\tYA\nC\u0001\u00053AqA!\t'\t\u0003\u0011\u0019\u0003C\u0004\u0003*\u0019\"\tAa\u000b\t\r\u0005\u0015d\u0005\"\u0001~\u0011%\tYGJA\u0001\n\u0003\u0011y\u0003C\u0005\u0002x\u0019\n\n\u0011\"\u0001\u0002z!I\u0011q\u0012\u0014\u0012\u0002\u0013\u0005\u0011\u0011\u0010\u0005\n\u0005o1\u0013\u0013!C\u0001\u0003sB\u0011\"!%'\u0003\u0003%\t%a%\t\u0013\u0005Ue%!A\u0005\u0002\u0005]\u0005\"CAPM\u0005\u0005I\u0011\u0001B\u001d\u0011%\tiKJA\u0001\n\u0003\ny\u000bC\u0005\u0002>\u001a\n\t\u0011\"\u0001\u0003>!I\u0011\u0011\u001a\u0014\u0002\u0002\u0013\u0005#\u0011\t\u0005\n\u0003\u001f4\u0013\u0011!C!\u0003#D\u0011\"!\u000b'\u0003\u0003%\t%a\u000b\t\u0013\u0005Mg%!A\u0005B\t\u0015s!\u0003B%C\u0005\u0005\t\u0012\u0001B&\r%\u0011\u0019!IA\u0001\u0012\u0003\u0011i\u0005C\u0004\u0002\f}\"\tAa\u0017\t\u0013\u0005%r(!A\u0005F\u0005-\u0002\"\u0003B/\u007f\u0005\u0005I\u0011\u0011B0\u0011%\u00119gPA\u0001\n\u0003\u0013I\u0007C\u0005\u0003|}\n\t\u0011\"\u0003\u0003~!I!QQ\u0011C\u0002\u0013\u0005!q\u0011\u0005\t\u0005\u0013\u000b\u0003\u0015!\u0003\u0003\f!9!1R\u0011\u0005\u0002\t5\u0005b\u0002BIC\u0011\u0005!1\u0013\u0005\b\u0005/\u000bC\u0011\tBM\u0011\u001d\u0011\t+\tC\u0001\u0005GC\u0011B!\u0018\"\u0003\u0003%\tIa-\t\u0013\t\u001d\u0014%!A\u0005\u0002\nu\u0006\"\u0003B>C\u0005\u0005I\u0011\u0002B?\u0005!9\u0015-^:tS\u0006t'B\u0001)R\u00035!\u0017n\u001d;sS\n,H/[8og*\u0011!kU\u0001\u0006gR\fGo\u001d\u0006\u0002)\u00061!M]3fu\u0016\u001c\u0001a\u0005\u0005\u0001/v#wM[7q!\tA6,D\u0001Z\u0015\u0005Q\u0016!B:dC2\f\u0017B\u0001/Z\u0005\u0019\te.\u001f*fMB\u0019alX1\u000e\u0003=K!\u0001Y(\u0003\u001f\r{g\u000e^5ok>,8\u000fR5tiJ\u0004\"\u0001\u00172\n\u0005\rL&A\u0002#pk\ndW\r\u0005\u0003_K\u0006\f\u0017B\u00014P\u0005\u001diu.\\3oiN\u0004\"A\u00185\n\u0005%|%A\u0002%bg\u000e#g\r\u0005\u0002_W&\u0011An\u0014\u0002\u000e\u0011\u0006\u001c\u0018J\u001c<feN,7\t\u001a4\u0011\u0005as\u0017BA8Z\u0005\u001d\u0001&o\u001c3vGR\u0004\"!]=\u000f\u0005I<hBA:w\u001b\u0005!(BA;V\u0003\u0019a$o\\8u}%\t!,\u0003\u0002y3\u00069\u0001/Y2lC\u001e,\u0017B\u0001>|\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tA\u0018,\u0001\u0002nkV\t\u0011-A\u0002nk\u0002\nQa]5h[\u0006\faa]5h[\u0006\u0004\u0013\u0001\u0002:b]\u0012\u00042AXA\u0004\u0013\r\tIa\u0014\u0002\n%\u0006tGMQ1tSN\fa\u0001P5oSRtDCBA\b\u0003+\t9\u0002\u0006\u0003\u0002\u0012\u0005M\u0001C\u00010\u0001\u0011\u001d\t\u0019A\u0002a\u0002\u0003\u000bAQ\u0001 \u0004A\u0002\u0005DQa \u0004A\u0002\u0005\fQ!\u001b8oKJ,\"!!\b\u0011\ty\u000by\"Y\u0005\u0004\u0003Cy%\u0001\u0002*b]\u0012\fa!\u001b8oKJ\u0004\u0013\u0001\u00023sC^$\u0012!Y\u0001\ti>\u001cFO]5oOR\u0011\u0011Q\u0006\t\u0005\u0003_\tI$\u0004\u0002\u00022)!\u00111GA\u001b\u0003\u0011a\u0017M\\4\u000b\u0005\u0005]\u0012\u0001\u00026bm\u0006LA!a\u000f\u00022\t11\u000b\u001e:j]\u001e\f1\u0002\u001d:pE\u0006\u0014\u0017\u000e\\5usR)\u0011-!\u0011\u0002F!1\u00111I\u0006A\u0002\u0005\f\u0011\u0001\u001f\u0005\u0007\u0003\u000fZ\u0001\u0019A1\u0002\u0003e\f!\"\u001b8wKJ\u001cXm\u00113g)\r\t\u0017Q\n\u0005\u0007\u0003\u001fb\u0001\u0019A1\u0002\u0003A\f1a\u00193g)\r\t\u0017Q\u000b\u0005\u0007\u0003\u0007j\u0001\u0019A1\u0002%Utgn\u001c:nC2L'0\u001a3M_\u001e\u0004FM\u001a\u000b\u0004C\u0006m\u0003BBA/\u001d\u0001\u0007\u0011-A\u0001u\u0003)qwN]7bY&TXM]\u0001\u000eY><gj\u001c:nC2L'0\u001a:\u0002\t5,\u0017M\\\u0001\tm\u0006\u0014\u0018.\u00198dK\u0006!Qn\u001c3f\u0003\u001d)g\u000e\u001e:paf\fAaY8qsR1\u0011qNA:\u0003k\"B!!\u0005\u0002r!9\u00111A\u000bA\u0004\u0005\u0015\u0001b\u0002?\u0016!\u0003\u0005\r!\u0019\u0005\b\u007fV\u0001\n\u00111\u0001b\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"!a\u001f+\u0007\u0005\fih\u000b\u0002\u0002\u0000A!\u0011\u0011QAF\u001b\t\t\u0019I\u0003\u0003\u0002\u0006\u0006\u001d\u0015!C;oG\",7m[3e\u0015\r\tI)W\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BAG\u0003\u0007\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA\u0017\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\tI\nE\u0002Y\u00037K1!!(Z\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\t\u0019+!+\u0011\u0007a\u000b)+C\u0002\u0002(f\u00131!\u00118z\u0011%\tYKGA\u0001\u0002\u0004\tI*A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003c\u0003b!a-\u0002:\u0006\rVBAA[\u0015\r\t9,W\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA^\u0003k\u0013\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011\u0011YAd!\rA\u00161Y\u0005\u0004\u0003\u000bL&a\u0002\"p_2,\u0017M\u001c\u0005\n\u0003Wc\u0012\u0011!a\u0001\u0003G\u000b!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u0011QFAg\u0011%\tY+HA\u0001\u0002\u0004\tI*\u0001\u0005iCND7i\u001c3f)\t\tI*\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003\u0003\f9\u000eC\u0005\u0002,~\t\t\u00111\u0001\u0002$\u0006Aq)Y;tg&\fg\u000e\u0005\u0002_CMA\u0011eVAp\u0003K\fY\u000f\u0005\u0004_\u0003C\f\t\"Y\u0005\u0004\u0003G|%!E#ya>tWM\u001c;jC24\u0015-\\5msB1a,a:b\u0003#I1!!;P\u0005\r\u001auN\u001c;j]V|Wo\u001d#jgR\u0014\u0018NY;uS>tWKR;oGB\u0013xN^5eKJ\u0004B!!<\u0002t6\u0011\u0011q\u001e\u0006\u0005\u0003c\f)$\u0001\u0002j_&\u0019!0a<\u0015\u0005\u0005m\u0017!B:reR\u0014\u0014AB:reR\u0014\u0004EA\u0005QCJ\fW.\u001a;feB)\u0001,a@bC&\u0019!\u0011A-\u0003\rQ+\b\u000f\\33\u0005M\u0019VO\u001a4jG&,g\u000e^*uCRL7\u000f^5d'\u00191sKa\u0002naB)aL!\u0003\u0003\f%\u0019!1A(\u0011\u0007\t5a%D\u0001\"\u0003\u0005q\u0017A\u00018!\u0003\u0015iW-\u00198!\u0003\ti%'A\u0002Ne\u0001\"\u0002Ba\u0003\u0003\u001c\tu!q\u0004\u0005\u0007\u0005\u001fi\u0003\u0019A1\t\r\u0005\rT\u00061\u0001b\u0011\u0019\u0011)\"\fa\u0001C\u00061A\u0005^5nKN$BAa\u0003\u0003&!1!q\u0005\u0018A\u0002\u0005\faa^3jO\"$\u0018!\u0002\u0013qYV\u001cH\u0003\u0002B\u0006\u0005[Aq!!\u00180\u0001\u0004\u0011Y\u0001\u0006\u0005\u0003\f\tE\"1\u0007B\u001b\u0011!\u0011y!\rI\u0001\u0002\u0004\t\u0007\u0002CA2cA\u0005\t\u0019A1\t\u0011\tU\u0011\u0007%AA\u0002\u0005\fabY8qs\u0012\"WMZ1vYR$3\u0007\u0006\u0003\u0002$\nm\u0002\"CAVo\u0005\u0005\t\u0019AAM)\u0011\t\tMa\u0010\t\u0013\u0005-\u0016(!AA\u0002\u0005\rF\u0003BA\u0017\u0005\u0007B\u0011\"a+;\u0003\u0003\u0005\r!!'\u0015\t\u0005\u0005'q\t\u0005\n\u0003Wk\u0014\u0011!a\u0001\u0003G\u000b1cU;gM&\u001c\u0017.\u001a8u'R\fG/[:uS\u000e\u00042A!\u0004@'\u0015y$qJAv!%\u0011\tFa\u0016bC\u0006\u0014Y!\u0004\u0002\u0003T)\u0019!QK-\u0002\u000fI,h\u000e^5nK&!!\u0011\fB*\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gn\r\u000b\u0003\u0005\u0017\nQ!\u00199qYf$\u0002Ba\u0003\u0003b\t\r$Q\r\u0005\u0007\u0005\u001f\u0011\u0005\u0019A1\t\r\u0005\r$\t1\u0001b\u0011\u0019\u0011)B\u0011a\u0001C\u00069QO\\1qa2LH\u0003\u0002B6\u0005o\u0002R\u0001\u0017B7\u0005cJ1Aa\u001cZ\u0005\u0019y\u0005\u000f^5p]B1\u0001La\u001dbC\u0006L1A!\u001eZ\u0005\u0019!V\u000f\u001d7fg!I!\u0011P\"\u0002\u0002\u0003\u0007!1B\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001B@!\u0011\tyC!!\n\t\t\r\u0015\u0011\u0007\u0002\u0007\u001f\nTWm\u0019;\u00021\u0015l\u0007\u000f^=Tk\u001a4\u0017nY5f]R\u001cF/\u0019;jgRL7-\u0006\u0002\u0003\f\u0005IR-\u001c9usN+hMZ5dS\u0016tGo\u0015;bi&\u001cH/[2!\u0003Y\u0019XO\u001a4jG&,g\u000e^*uCRL7\u000f^5d\r>\u0014H\u0003\u0002B\u0006\u0005\u001fCa!!\u0018H\u0001\u0004\t\u0017aA7mKR!\u0011Q BK\u0011\u0019\u0011\u0006\n1\u0001\u0003\f\u0005aA-[:ue&\u0014W\u000f^5p]R!!1\u0014BP)\u0011\t\tB!(\t\u000f\u0005\r\u0011\nq\u0001\u0002\u0006!9\u0011qJ%A\u0002\u0005u\u0018A\u00057jW\u0016d\u0017\u000e[8pI\u001a+hn\u0019;j_:$BA!*\u00032B1!q\u0015BW\u0003{l!A!+\u000b\u0007\t-6+\u0001\u0005paRLW.\u001b>f\u0013\u0011\u0011yK!+\u0003\u0019\u0011KgM\u001a$v]\u000e$\u0018n\u001c8\t\rIS\u0005\u0019\u0001B\u0006)\u0019\u0011)L!/\u0003<R!\u0011\u0011\u0003B\\\u0011\u001d\t\u0019a\u0013a\u0002\u0003\u000bAQ\u0001`&A\u0002\u0005DQa`&A\u0002\u0005$BAa0\u0003BB)\u0001L!\u001c\u0002~\"I!\u0011\u0010'\u0002\u0002\u0003\u0007\u0011\u0011\u0003"
)
public class Gaussian implements ContinuousDistr, Moments, HasCdf, HasInverseCdf, Product {
   private double normalizer;
   private double logNormalizer;
   private final double mu;
   private final double sigma;
   private final Rand breeze$stats$distributions$Gaussian$$inner;
   private volatile byte bitmap$0;

   public static Option unapply(final Gaussian x$0) {
      return Gaussian$.MODULE$.unapply(x$0);
   }

   public static DiffFunction likelihoodFunction(final SufficientStatistic stats) {
      return Gaussian$.MODULE$.likelihoodFunction(stats);
   }

   public static Gaussian distribution(final Tuple2 p, final RandBasis rand) {
      return Gaussian$.MODULE$.distribution(p, rand);
   }

   public static Tuple2 mle(final SufficientStatistic stats) {
      return Gaussian$.MODULE$.mle(stats);
   }

   public static SufficientStatistic sufficientStatisticFor(final double t) {
      return Gaussian$.MODULE$.sufficientStatisticFor(t);
   }

   public static SufficientStatistic emptySufficientStatistic() {
      return Gaussian$.MODULE$.emptySufficientStatistic();
   }

   public static ContinuousDistributionUFuncProvider.ContinuousDistrUFuncWrapper ContinuousDistrUFuncWrapper(final ContinuousDistr dist) {
      return Gaussian$.MODULE$.ContinuousDistrUFuncWrapper(dist);
   }

   public static ContinuousDistributionUFuncProvider.basicImpl$ basicImpl() {
      return Gaussian$.MODULE$.basicImpl();
   }

   public static Object withSink(final Object s) {
      return Gaussian$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return Gaussian$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return Gaussian$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return Gaussian$.MODULE$.inPlace(v, impl);
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

   public double mu() {
      return this.mu;
   }

   public double sigma() {
      return this.sigma;
   }

   public Rand breeze$stats$distributions$Gaussian$$inner() {
      return this.breeze$stats$distributions$Gaussian$$inner;
   }

   public double draw() {
      return this.draw$mcD$sp();
   }

   public String toString() {
      return (new StringBuilder(12)).append("Gaussian(").append(this.mu()).append(", ").append(this.sigma()).append(")").toString();
   }

   public double probability(final double x, final double y) {
      .MODULE$.require(x <= y, () -> "Undefined probability: lower-end of the interval should be smaller than its upper-end");
      return this.cdf(y) - this.cdf(x);
   }

   public double inverseCdf(final double p) {
      .MODULE$.require(p >= (double)0);
      .MODULE$.require(p <= (double)1);
      return this.mu() + this.sigma() * Gaussian$.MODULE$.breeze$stats$distributions$Gaussian$$sqrt2() * package.erfinv$.MODULE$.apply$mDDc$sp((double)2 * p - (double)1, package$erfinv$erfinvImplDouble$.MODULE$);
   }

   public double cdf(final double x) {
      return (double)0.5F * ((double)1 + package.erf$.MODULE$.apply$mDDc$sp((x - this.mu()) / (Gaussian$.MODULE$.breeze$stats$distributions$Gaussian$$sqrt2() * this.sigma()), package$erf$erfImplDouble$.MODULE$));
   }

   public double unnormalizedLogPdf(final double t) {
      double d = (t - this.mu()) / this.sigma();
      return -d * d / (double)2.0F;
   }

   private double normalizer$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.normalizer = (double)1.0F / package.sqrt$.MODULE$.apply$mDDc$sp((Math.PI * 2D), package$sqrt$sqrtDoubleImpl$.MODULE$) / this.sigma();
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.normalizer;
   }

   public double normalizer() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.normalizer$lzycompute() : this.normalizer;
   }

   private double logNormalizer$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.logNormalizer = package.log$.MODULE$.apply$mDDc$sp(package.sqrt$.MODULE$.apply$mDDc$sp((Math.PI * 2D), package$sqrt$sqrtDoubleImpl$.MODULE$), package$log$logDoubleImpl$.MODULE$) + package.log$.MODULE$.apply$mDDc$sp(this.sigma(), package$log$logDoubleImpl$.MODULE$);
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.logNormalizer;
   }

   public double logNormalizer() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.logNormalizer$lzycompute() : this.logNormalizer;
   }

   public double mean() {
      return this.mu();
   }

   public double variance() {
      return this.sigma() * this.sigma();
   }

   public double mode() {
      return this.mean();
   }

   public double entropy() {
      return package.log$.MODULE$.apply$mDDc$sp(this.sigma(), package$log$logDoubleImpl$.MODULE$) + (double)0.5F * scala.math.package..MODULE$.log1p(package.log$.MODULE$.apply$mDDc$sp((Math.PI * 2D), package$log$logDoubleImpl$.MODULE$));
   }

   public Gaussian copy(final double mu, final double sigma, final RandBasis rand) {
      return new Gaussian(mu, sigma, rand);
   }

   public double copy$default$1() {
      return this.mu();
   }

   public double copy$default$2() {
      return this.sigma();
   }

   public String productPrefix() {
      return "Gaussian";
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
      return x$1 instanceof Gaussian;
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
            if (x$1 instanceof Gaussian) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Gaussian var4 = (Gaussian)x$1;
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
      return this.breeze$stats$distributions$Gaussian$$inner().get$mcD$sp();
   }

   public Gaussian(final double mu, final double sigma, final RandBasis rand) {
      this.mu = mu;
      this.sigma = sigma;
      Density.$init$(this);
      Rand.$init$(this);
      ContinuousDistr.$init$(this);
      Product.$init$(this);
      this.breeze$stats$distributions$Gaussian$$inner = rand.gaussian(mu, sigma);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static final class SufficientStatistic implements breeze.stats.distributions.SufficientStatistic, Product, Serializable {
      private final double n;
      private final double mean;
      private final double M2;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public double n() {
         return this.n;
      }

      public double mean() {
         return this.mean;
      }

      public double M2() {
         return this.M2;
      }

      public SufficientStatistic $times(final double weight) {
         return new SufficientStatistic(this.n() * weight, this.mean(), this.M2() * weight);
      }

      public SufficientStatistic $plus(final SufficientStatistic t) {
         double delta = t.mean() - this.mean();
         double newMean = this.mean() + delta * (t.n() / (t.n() + this.n()));
         double newM2 = this.M2() + t.M2() + delta * delta * t.n() * this.n() / (t.n() + this.n());
         return new SufficientStatistic(t.n() + this.n(), newMean, newM2);
      }

      public double variance() {
         return this.M2() / this.n();
      }

      public SufficientStatistic copy(final double n, final double mean, final double M2) {
         return new SufficientStatistic(n, mean, M2);
      }

      public double copy$default$1() {
         return this.n();
      }

      public double copy$default$2() {
         return this.mean();
      }

      public double copy$default$3() {
         return this.M2();
      }

      public String productPrefix() {
         return "SufficientStatistic";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToDouble(this.n());
               break;
            case 1:
               var10000 = BoxesRunTime.boxToDouble(this.mean());
               break;
            case 2:
               var10000 = BoxesRunTime.boxToDouble(this.M2());
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
         return x$1 instanceof SufficientStatistic;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "n";
               break;
            case 1:
               var10000 = "mean";
               break;
            case 2:
               var10000 = "M2";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.doubleHash(this.n()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.mean()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.M2()));
         return Statics.finalizeHash(var1, 3);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label51: {
               boolean var2;
               if (x$1 instanceof SufficientStatistic) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  SufficientStatistic var4 = (SufficientStatistic)x$1;
                  if (this.n() == var4.n() && this.mean() == var4.mean() && this.M2() == var4.M2()) {
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

      public SufficientStatistic(final double n, final double mean, final double M2) {
         this.n = n;
         this.mean = mean;
         this.M2 = M2;
         Product.$init$(this);
      }
   }

   public static class SufficientStatistic$ extends AbstractFunction3 implements Serializable {
      public static final SufficientStatistic$ MODULE$ = new SufficientStatistic$();

      public final String toString() {
         return "SufficientStatistic";
      }

      public SufficientStatistic apply(final double n, final double mean, final double M2) {
         return new SufficientStatistic(n, mean, M2);
      }

      public Option unapply(final SufficientStatistic x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToDouble(x$0.n()), BoxesRunTime.boxToDouble(x$0.mean()), BoxesRunTime.boxToDouble(x$0.M2()))));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(SufficientStatistic$.class);
      }
   }
}
