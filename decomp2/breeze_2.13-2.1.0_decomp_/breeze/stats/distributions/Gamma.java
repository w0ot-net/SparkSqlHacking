package breeze.stats.distributions;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import breeze.numerics.package;
import breeze.numerics.package$digamma$digammaImplDouble$;
import breeze.numerics.package$lgamma$lgammaImplDouble$;
import breeze.numerics.package$log$logDoubleImpl$;
import breeze.optimize.DiffFunction;
import java.io.Serializable;
import org.apache.commons.math3.distribution.GammaDistribution;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.math.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0005h\u0001\u0002(P\u0001ZC\u0001\u0002 \u0001\u0003\u0016\u0004%\t! \u0005\t}\u0002\u0011\t\u0012)A\u0005C\"Aq\u0010\u0001BK\u0002\u0013\u0005Q\u0010C\u0005\u0002\u0002\u0001\u0011\t\u0012)A\u0005C\"Q\u00111\u0001\u0001\u0003\u0002\u0003\u0006Y!!\u0002\t\u000f\u0005-\u0001\u0001\"\u0001\u0002\u000e!9\u0011\u0011\u0004\u0001\u0005B\u0005m\u0001\"CA\u0011\u0001!\u0015\r\u0011\"\u0001~\u0011\u001d\t\u0019\u0003\u0001C!\u0003KAq!!\u000b\u0001\t\u0003\nY\u0003C\u0004\u0002>\u0001!\t!a\u0010\t\u000f\u0005\u0005\u0003\u0001\"\u0001\u0002@!1\u00111\t\u0001\u0005\u0002uDa!!\u0012\u0001\t\u0003i\bBBA$\u0001\u0011\u0005Q\u0010\u0003\u0004\u0002J\u0001!\t! \u0005\b\u0003\u0017\u0002A\u0011IA'\u0011\u001d\t)\u0006\u0001C!\u0003/Bq!!\u0018\u0001\t\u0003\ny\u0006C\u0005\u0002d\u0001\t\t\u0011\"\u0001\u0002f!I\u0011q\u000e\u0001\u0012\u0002\u0013\u0005\u0011\u0011\u000f\u0005\n\u0003\u000f\u0003\u0011\u0013!C\u0001\u0003cB\u0011\"!#\u0001\u0003\u0003%\t%a#\t\u0013\u00055\u0005!!A\u0005\u0002\u0005=\u0005\"CAL\u0001\u0005\u0005I\u0011AAM\u0011%\t)\u000bAA\u0001\n\u0003\n9\u000bC\u0005\u00026\u0002\t\t\u0011\"\u0001\u00028\"I\u0011\u0011\u0019\u0001\u0002\u0002\u0013\u0005\u00131\u0019\u0005\n\u0003\u000f\u0004\u0011\u0011!C!\u0003\u0013D\u0011\"a3\u0001\u0003\u0003%\t%!4\b\u000f\u0005Ew\n#\u0001\u0002T\u001a1aj\u0014E\u0001\u0003+Dq!a\u0003!\t\u0003\ti/\u0002\u0004\u0002p\u0002\u0002\u0011\u0011\u001f\u0004\u0007\u0003o\u0004\u0003)!?\t\u0013\t\r1E!f\u0001\n\u0003i\b\"\u0003B\u0003G\tE\t\u0015!\u0003b\u0011%\u00119a\tBK\u0002\u0013\u0005Q\u0010C\u0005\u0003\n\r\u0012\t\u0012)A\u0005C\"I\u00111I\u0012\u0003\u0016\u0004%\t! \u0005\n\u0005\u0017\u0019#\u0011#Q\u0001\n\u0005Dq!a\u0003$\t\u0003\u0011i\u0001C\u0004\u0003\u0016\r\"\tAa\u0006\t\u000f\tu1\u0005\"\u0001\u0003 !I\u00111M\u0012\u0002\u0002\u0013\u0005!Q\u0005\u0005\n\u0003_\u001a\u0013\u0013!C\u0001\u0003cB\u0011\"a\"$#\u0003%\t!!\u001d\t\u0013\t52%%A\u0005\u0002\u0005E\u0004\"CAEG\u0005\u0005I\u0011IAF\u0011%\tiiIA\u0001\n\u0003\ty\tC\u0005\u0002\u0018\u000e\n\t\u0011\"\u0001\u00030!I\u0011QU\u0012\u0002\u0002\u0013\u0005\u0013q\u0015\u0005\n\u0003k\u001b\u0013\u0011!C\u0001\u0005gA\u0011\"!1$\u0003\u0003%\tEa\u000e\t\u0013\u0005\u001d7%!A\u0005B\u0005%\u0007\"CA\u0015G\u0005\u0005I\u0011IA\u0016\u0011%\tYmIA\u0001\n\u0003\u0012YdB\u0005\u0003@\u0001\n\t\u0011#\u0001\u0003B\u0019I\u0011q\u001f\u0011\u0002\u0002#\u0005!1\t\u0005\b\u0003\u0017YD\u0011\u0001B)\u0011%\tIcOA\u0001\n\u000b\nY\u0003C\u0005\u0003Tm\n\t\u0011\"!\u0003V!I!QL\u001e\u0002\u0002\u0013\u0005%q\f\u0005\n\u0005cZ\u0014\u0011!C\u0005\u0005gBqAa\u001f!\t\u0003\u0011i\bC\u0004\u0003\u0000\u0001\"\tA!!\t\u000f\t\u0015\u0005\u0005\"\u0001\u0003\b\"9!Q\u0012\u0011\u0005\u0002\t=\u0005\"\u0003BKA\t\u0007I\u0011BAH\u0011!\u00119\n\tQ\u0001\n\u0005E\u0005b\u0002BMA\u0011%!1\u0014\u0005\n\u0005O\u0003\u0013\u0013!C\u0005\u0005SCqA!,!\t\u0003\u0012y\u000bC\u0004\u0003:\u0002\"\tAa/\t\u0013\tM\u0003%!A\u0005\u0002\nE\u0007\"\u0003B/A\u0005\u0005I\u0011\u0011Bn\u0011%\u0011\t\bIA\u0001\n\u0013\u0011\u0019HA\u0003HC6l\u0017M\u0003\u0002Q#\u0006iA-[:ue&\u0014W\u000f^5p]NT!AU*\u0002\u000bM$\u0018\r^:\u000b\u0003Q\u000baA\u0019:fKj,7\u0001A\n\t\u0001]kFm\u001a6naB\u0011\u0001lW\u0007\u00023*\t!,A\u0003tG\u0006d\u0017-\u0003\u0002]3\n1\u0011I\\=SK\u001a\u00042AX0b\u001b\u0005y\u0015B\u00011P\u0005=\u0019uN\u001c;j]V|Wo\u001d#jgR\u0014\bC\u0001-c\u0013\t\u0019\u0017L\u0001\u0004E_V\u0014G.\u001a\t\u0005=\u0016\f\u0017-\u0003\u0002g\u001f\n9Qj\\7f]R\u001c\bC\u00010i\u0013\tIwJ\u0001\u0004ICN\u001cEM\u001a\t\u0003=.L!\u0001\\(\u0003\u001b!\u000b7/\u00138wKJ\u001cXm\u00113g!\tAf.\u0003\u0002p3\n9\u0001K]8ek\u000e$\bCA9z\u001d\t\u0011xO\u0004\u0002tm6\tAO\u0003\u0002v+\u00061AH]8pizJ\u0011AW\u0005\u0003qf\u000bq\u0001]1dW\u0006<W-\u0003\u0002{w\na1+\u001a:jC2L'0\u00192mK*\u0011\u00010W\u0001\u0006g\"\f\u0007/Z\u000b\u0002C\u000611\u000f[1qK\u0002\nQa]2bY\u0016\faa]2bY\u0016\u0004\u0013\u0001\u0002:b]\u0012\u00042AXA\u0004\u0013\r\tIa\u0014\u0002\n%\u0006tGMQ1tSN\fa\u0001P5oSRtDCBA\b\u0003+\t9\u0002\u0006\u0003\u0002\u0012\u0005M\u0001C\u00010\u0001\u0011\u001d\t\u0019A\u0002a\u0002\u0003\u000bAQ\u0001 \u0004A\u0002\u0005DQa \u0004A\u0002\u0005\f1\u0001\u001d3g)\r\t\u0017Q\u0004\u0005\u0007\u0003?9\u0001\u0019A1\u0002\u0003a\fQ\u0002\\8h\u001d>\u0014X.\u00197ju\u0016\u0014\u0018AE;o]>\u0014X.\u00197ju\u0016$Gj\\4QI\u001a$2!YA\u0014\u0011\u0019\ty\"\u0003a\u0001C\u0006AAo\\*ue&tw\r\u0006\u0002\u0002.A!\u0011qFA\u001d\u001b\t\t\tD\u0003\u0003\u00024\u0005U\u0012\u0001\u00027b]\u001eT!!a\u000e\u0002\t)\fg/Y\u0005\u0005\u0003w\t\tD\u0001\u0004TiJLgnZ\u0001\bY><GI]1x)\u0005\t\u0017\u0001\u00023sC^\fA!\\3b]\u0006Aa/\u0019:jC:\u001cW-\u0001\u0003n_\u0012,\u0017aB3oiJ|\u0007/_\u0001\faJ|'-\u00192jY&$\u0018\u0010F\u0003b\u0003\u001f\n\t\u0006\u0003\u0004\u0002 E\u0001\r!\u0019\u0005\u0007\u0003'\n\u0002\u0019A1\u0002\u0003e\f!\"\u001b8wKJ\u001cXm\u00113g)\r\t\u0017\u0011\f\u0005\u0007\u00037\u0012\u0002\u0019A1\u0002\u0003A\f1a\u00193g)\r\t\u0017\u0011\r\u0005\u0007\u0003?\u0019\u0002\u0019A1\u0002\t\r|\u0007/\u001f\u000b\u0007\u0003O\nY'!\u001c\u0015\t\u0005E\u0011\u0011\u000e\u0005\b\u0003\u0007!\u00029AA\u0003\u0011\u001daH\u0003%AA\u0002\u0005Dqa \u000b\u0011\u0002\u0003\u0007\u0011-\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005M$fA1\u0002v-\u0012\u0011q\u000f\t\u0005\u0003s\n\u0019)\u0004\u0002\u0002|)!\u0011QPA@\u0003%)hn\u00195fG.,GMC\u0002\u0002\u0002f\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\t))a\u001f\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\ti#\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002\u0012B\u0019\u0001,a%\n\u0007\u0005U\u0015LA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002\u001c\u0006\u0005\u0006c\u0001-\u0002\u001e&\u0019\u0011qT-\u0003\u0007\u0005s\u0017\u0010C\u0005\u0002$f\t\t\u00111\u0001\u0002\u0012\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!+\u0011\r\u0005-\u0016\u0011WAN\u001b\t\tiKC\u0002\u00020f\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\t\u0019,!,\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003s\u000by\fE\u0002Y\u0003wK1!!0Z\u0005\u001d\u0011un\u001c7fC:D\u0011\"a)\u001c\u0003\u0003\u0005\r!a'\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003[\t)\rC\u0005\u0002$r\t\t\u00111\u0001\u0002\u0012\u0006A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002\u0012\u00061Q-];bYN$B!!/\u0002P\"I\u00111\u0015\u0010\u0002\u0002\u0003\u0007\u00111T\u0001\u0006\u000f\u0006lW.\u0019\t\u0003=\u0002\u001a\u0002\u0002I,\u0002X\u0006u\u00171\u001d\t\u0007=\u0006e\u0017\u0011C1\n\u0007\u0005mwJA\tFqB|g.\u001a8uS\u0006dg)Y7jYf\u0004bAXApC\u0006E\u0011bAAq\u001f\n\u00193i\u001c8uS:,x.^:ESN$(/\u001b2vi&|g.\u0016$v]\u000e\u0004&o\u001c<jI\u0016\u0014\b\u0003BAs\u0003Wl!!a:\u000b\t\u0005%\u0018QG\u0001\u0003S>L1A_At)\t\t\u0019NA\u0005QCJ\fW.\u001a;feB)\u0001,a=bC&\u0019\u0011Q_-\u0003\rQ+\b\u000f\\33\u0005M\u0019VO\u001a4jG&,g\u000e^*uCRL7\u000f^5d'\u0019\u0019s+a?naB)a,!@\u0002\u0000&\u0019\u0011q_(\u0011\u0007\t\u00051%D\u0001!\u0003\u0005q\u0017A\u00018!\u0003)iW-\u00198PM2{wm]\u0001\f[\u0016\fgn\u00144M_\u001e\u001c\b%A\u0003nK\u0006t\u0007\u0005\u0006\u0005\u0002\u0000\n=!\u0011\u0003B\n\u0011\u0019\u0011\u0019A\u000ba\u0001C\"1!q\u0001\u0016A\u0002\u0005Da!a\u0011+\u0001\u0004\t\u0017A\u0002\u0013uS6,7\u000f\u0006\u0003\u0002\u0000\ne\u0001B\u0002B\u000eW\u0001\u0007\u0011-\u0001\u0004xK&<\u0007\u000e^\u0001\u0006IAdWo\u001d\u000b\u0005\u0003\u007f\u0014\t\u0003C\u0004\u0003$1\u0002\r!a@\u0002\u0003Q$\u0002\"a@\u0003(\t%\"1\u0006\u0005\t\u0005\u0007i\u0003\u0013!a\u0001C\"A!qA\u0017\u0011\u0002\u0003\u0007\u0011\r\u0003\u0005\u0002D5\u0002\n\u00111\u0001b\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM\"B!a'\u00032!I\u00111U\u001a\u0002\u0002\u0003\u0007\u0011\u0011\u0013\u000b\u0005\u0003s\u0013)\u0004C\u0005\u0002$V\n\t\u00111\u0001\u0002\u001cR!\u0011Q\u0006B\u001d\u0011%\t\u0019KNA\u0001\u0002\u0004\t\t\n\u0006\u0003\u0002:\nu\u0002\"CARs\u0005\u0005\t\u0019AAN\u0003M\u0019VO\u001a4jG&,g\u000e^*uCRL7\u000f^5d!\r\u0011\taO\n\u0006w\t\u0015\u00131\u001d\t\n\u0005\u000f\u0012i%Y1b\u0003\u007fl!A!\u0013\u000b\u0007\t-\u0013,A\u0004sk:$\u0018.\\3\n\t\t=#\u0011\n\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u001cDC\u0001B!\u0003\u0015\t\u0007\u000f\u001d7z)!\tyPa\u0016\u0003Z\tm\u0003B\u0002B\u0002}\u0001\u0007\u0011\r\u0003\u0004\u0003\by\u0002\r!\u0019\u0005\u0007\u0003\u0007r\u0004\u0019A1\u0002\u000fUt\u0017\r\u001d9msR!!\u0011\rB7!\u0015A&1\rB4\u0013\r\u0011)'\u0017\u0002\u0007\u001fB$\u0018n\u001c8\u0011\ra\u0013I'Y1b\u0013\r\u0011Y'\u0017\u0002\u0007)V\u0004H.Z\u001a\t\u0013\t=t(!AA\u0002\u0005}\u0018a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011!Q\u000f\t\u0005\u0003_\u00119(\u0003\u0003\u0003z\u0005E\"AB(cU\u0016\u001cG/\u0001\rf[B$\u0018pU;gM&\u001c\u0017.\u001a8u'R\fG/[:uS\u000e,\"!a@\u0002-M,hMZ5dS\u0016tGo\u0015;bi&\u001cH/[2G_J$B!a@\u0003\u0004\"1!1\u0005\"A\u0002\u0005\f1!\u001c7f)\u0011\t\tP!#\t\u000f\t-5\t1\u0001\u0002\u0000\u0006\u00111o]\u0001\tCB\u0004(o\u001c=`WR\u0019\u0011M!%\t\r\tME\t1\u0001b\u0003\u0005\u0019\u0018aB'bq&#XM]\u0001\t\u001b\u0006D\u0018\n^3sA\u0005\u0011bj\u001e;`%BDw,\u001b;fe~3wN]0l)\u001d\t'Q\u0014BQ\u0005GCaAa(H\u0001\u0004\t\u0017!A6\t\r\tMu\t1\u0001b\u0011%\u0011)k\u0012I\u0001\u0002\u0004\t\t*\u0001\u0003ji\u0016\u0014\u0018\u0001\b(xi~\u0013\u0006\u000f[0ji\u0016\u0014xLZ8s?.$C-\u001a4bk2$HeM\u000b\u0003\u0005WSC!!%\u0002v\u0005aA-[:ue&\u0014W\u000f^5p]R!!\u0011\u0017B[)\u0011\t\tBa-\t\u000f\u0005\r\u0011\nq\u0001\u0002\u0006!9\u00111L%A\u0002\t]\u0006c\u0001B\u0001E\u0005\u0011B.[6fY&Dwn\u001c3Gk:\u001cG/[8o)\u0011\u0011iLa4\u0013\u000b\t}vKa1\u0007\r\t\u0005'\n\u0001B_\u00051a$/\u001a4j]\u0016lWM\u001c;?!\u0019\u0011)Ma3\u0002r6\u0011!q\u0019\u0006\u0004\u0005\u0013\u001c\u0016\u0001C8qi&l\u0017N_3\n\t\t5'q\u0019\u0002\r\t&4gMR;oGRLwN\u001c\u0005\u0007%*\u0003\r!a@\u0015\r\tM'q\u001bBm)\u0011\t\tB!6\t\u000f\u0005\r1\nq\u0001\u0002\u0006!)Ap\u0013a\u0001C\")qp\u0013a\u0001CR!!Q\u001cBp!\u0015A&1MAy\u0011%\u0011y\u0007TA\u0001\u0002\u0004\t\t\u0002"
)
public class Gamma implements ContinuousDistr, Moments, HasCdf, HasInverseCdf, Product {
   private double logNormalizer;
   private final double shape;
   private final double scale;
   public final RandBasis breeze$stats$distributions$Gamma$$rand;
   private double normalizer;
   private volatile byte bitmap$0;

   public static Option unapply(final Gamma x$0) {
      return Gamma$.MODULE$.unapply(x$0);
   }

   public static DiffFunction likelihoodFunction(final SufficientStatistic stats) {
      return Gamma$.MODULE$.likelihoodFunction(stats);
   }

   public static Gamma distribution(final Tuple2 p, final RandBasis rand) {
      return Gamma$.MODULE$.distribution(p, rand);
   }

   public static double approx_k(final double s) {
      return Gamma$.MODULE$.approx_k(s);
   }

   public static Tuple2 mle(final SufficientStatistic ss) {
      return Gamma$.MODULE$.mle(ss);
   }

   public static SufficientStatistic sufficientStatisticFor(final double t) {
      return Gamma$.MODULE$.sufficientStatisticFor(t);
   }

   public static SufficientStatistic emptySufficientStatistic() {
      return Gamma$.MODULE$.emptySufficientStatistic();
   }

   public static ContinuousDistributionUFuncProvider.ContinuousDistrUFuncWrapper ContinuousDistrUFuncWrapper(final ContinuousDistr dist) {
      return Gamma$.MODULE$.ContinuousDistrUFuncWrapper(dist);
   }

   public static ContinuousDistributionUFuncProvider.basicImpl$ basicImpl() {
      return Gamma$.MODULE$.basicImpl();
   }

   public static Object withSink(final Object s) {
      return Gamma$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return Gamma$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return Gamma$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return Gamma$.MODULE$.inPlace(v, impl);
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

   public double shape() {
      return this.shape;
   }

   public double scale() {
      return this.scale;
   }

   public double pdf(final double x) {
      return x < (double)0 ? (double)0.0F : (x > (double)0 ? .MODULE$.exp(this.logPdf(BoxesRunTime.boxToDouble(x))) : (this.shape() > (double)1.0F ? (double)0.0F : (this.shape() == (double)1.0F ? this.normalizer() : Double.POSITIVE_INFINITY)));
   }

   private double logNormalizer$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.logNormalizer = package.lgamma$.MODULE$.apply$mDDc$sp(this.shape(), package$lgamma$lgammaImplDouble$.MODULE$) + this.shape() * package.log$.MODULE$.apply$mDDc$sp(this.scale(), package$log$logDoubleImpl$.MODULE$);
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

   public double unnormalizedLogPdf(final double x) {
      return (this.shape() - (double)1) * package.log$.MODULE$.apply$mDDc$sp(x, package$log$logDoubleImpl$.MODULE$) - x / this.scale();
   }

   public String toString() {
      return (new StringBuilder(8)).append("Gamma(").append(this.shape()).append(",").append(this.scale()).append(")").toString();
   }

   public double logDraw() {
      return this.shape() < (double)1 ? this.rec$1() + .MODULE$.log(this.scale()) : .MODULE$.log(this.draw$mcD$sp());
   }

   public double draw() {
      return this.draw$mcD$sp();
   }

   public double mean() {
      return this.shape() * this.scale();
   }

   public double variance() {
      return this.mean() * this.scale();
   }

   public double mode() {
      scala.Predef..MODULE$.require(this.shape() >= (double)1);
      return this.mean() - this.scale();
   }

   public double entropy() {
      return this.logNormalizer() - (this.shape() - (double)1) * package.digamma$.MODULE$.apply$mDDc$sp(this.shape(), package$digamma$digammaImplDouble$.MODULE$) + this.shape();
   }

   public double probability(final double x, final double y) {
      return (new GammaDistribution(this.shape(), this.scale())).probability(x, y);
   }

   public double inverseCdf(final double p) {
      return (new GammaDistribution(this.shape(), this.scale())).inverseCumulativeProbability(p);
   }

   public double cdf(final double x) {
      return (new GammaDistribution(this.shape(), this.scale())).cumulativeProbability(x);
   }

   public Gamma copy(final double shape, final double scale, final RandBasis rand) {
      return new Gamma(shape, scale, rand);
   }

   public double copy$default$1() {
      return this.shape();
   }

   public double copy$default$2() {
      return this.scale();
   }

   public String productPrefix() {
      return "Gamma";
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
      return x$1 instanceof Gamma;
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

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label51: {
            boolean var2;
            if (x$1 instanceof Gamma) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Gamma var4 = (Gamma)x$1;
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
      double var10000;
      if (this.shape() == (double)1.0F) {
         var10000 = this.scale() * -.MODULE$.log(this.breeze$stats$distributions$Gamma$$rand.uniform().draw$mcD$sp());
      } else if (this.shape() < (double)1.0F) {
         var10000 = this.scale() * this.rec$2();
      } else {
         double d = this.shape() - 0.3333333333333333;
         double c = (double)1.0F / .MODULE$.sqrt((double)9.0F * d);
         double r = (double)0.0F;
         boolean ok = false;

         while(!ok) {
            double v = (double)0.0F;
            double x = (double)0.0F;

            for(boolean var12 = true; var12; var12 = v <= (double)0) {
               x = this.breeze$stats$distributions$Gamma$$rand.generator().nextGaussian();
               v = (double)1.0F + c * x;
            }

            v = v * v * v;
            double x2 = x * x;
            double u = this.breeze$stats$distributions$Gamma$$rand.uniform().draw$mcD$sp();
            if (u < (double)1.0F - 0.0331 * x2 * x2 || package.log$.MODULE$.apply$mDDc$sp(u, package$log$logDoubleImpl$.MODULE$) < (double)0.5F * x2 + d * ((double)1.0F - v + package.log$.MODULE$.apply$mDDc$sp(v, package$log$logDoubleImpl$.MODULE$))) {
               r = this.scale() * d * v;
               ok = true;
            }
         }

         var10000 = r;
      }

      return var10000;
   }

   private final double rec$1() {
      while(true) {
         double u = this.breeze$stats$distributions$Gamma$$rand.uniform().draw$mcD$sp();
         double v = -.MODULE$.log(this.breeze$stats$distributions$Gamma$$rand.uniform().draw$mcD$sp());
         double logU = package.log$.MODULE$.apply$mDDc$sp(u, package$log$logDoubleImpl$.MODULE$);
         double var10000;
         if (logU <= .MODULE$.log1p(-this.shape())) {
            double logV = package.log$.MODULE$.apply$mDDc$sp(v, package$log$logDoubleImpl$.MODULE$);
            double logX = logU / this.shape();
            if (!(logX <= logV)) {
               continue;
            }

            var10000 = logX;
         } else {
            double y = -package.log$.MODULE$.apply$mDDc$sp(((double)1 - u) / this.shape(), package$log$logDoubleImpl$.MODULE$);
            double logX = .MODULE$.log((double)1.0F - this.shape() + this.shape() * y) / this.shape();
            if (!(logX <= .MODULE$.log(v + y))) {
               continue;
            }

            var10000 = logX;
         }

         return var10000;
      }
   }

   private final double rec$2() {
      while(true) {
         double u = this.breeze$stats$distributions$Gamma$$rand.uniform().draw$mcD$sp();
         double v = -.MODULE$.log(this.breeze$stats$distributions$Gamma$$rand.uniform().draw$mcD$sp());
         double var10000;
         if (u <= (double)1.0F - this.shape()) {
            double x = .MODULE$.pow(u, (double)1.0F / this.shape());
            if (!(x <= v)) {
               continue;
            }

            var10000 = x;
         } else {
            double y = -package.log$.MODULE$.apply$mDDc$sp(((double)1 - u) / this.shape(), package$log$logDoubleImpl$.MODULE$);
            double x = .MODULE$.pow((double)1.0F - this.shape() + this.shape() * y, (double)1.0F / this.shape());
            if (!(x <= v + y)) {
               continue;
            }

            var10000 = x;
         }

         return var10000;
      }
   }

   public Gamma(final double shape, final double scale, final RandBasis rand) {
      this.shape = shape;
      this.scale = scale;
      this.breeze$stats$distributions$Gamma$$rand = rand;
      Density.$init$(this);
      Rand.$init$(this);
      ContinuousDistr.$init$(this);
      Product.$init$(this);
      if (shape <= (double)0.0F || scale <= (double)0.0F) {
         throw new IllegalArgumentException("Shape and scale must be positive");
      }
   }

   public static class SufficientStatistic implements breeze.stats.distributions.SufficientStatistic, Product, Serializable {
      private final double n;
      private final double meanOfLogs;
      private final double mean;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public double n() {
         return this.n;
      }

      public double meanOfLogs() {
         return this.meanOfLogs;
      }

      public double mean() {
         return this.mean;
      }

      public SufficientStatistic $times(final double weight) {
         return new SufficientStatistic(this.n() * weight, this.meanOfLogs(), this.mean());
      }

      public SufficientStatistic $plus(final SufficientStatistic t) {
         double delta = t.mean() - this.mean();
         double newMean = this.mean() + delta * (t.n() / (t.n() + this.n()));
         double logDelta = t.meanOfLogs() - this.meanOfLogs();
         double newMeanLogs = this.meanOfLogs() + logDelta * (t.n() / (t.n() + this.n()));
         return new SufficientStatistic(t.n() + this.n(), newMeanLogs, newMean);
      }

      public SufficientStatistic copy(final double n, final double meanOfLogs, final double mean) {
         return new SufficientStatistic(n, meanOfLogs, mean);
      }

      public double copy$default$1() {
         return this.n();
      }

      public double copy$default$2() {
         return this.meanOfLogs();
      }

      public double copy$default$3() {
         return this.mean();
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
               var10000 = BoxesRunTime.boxToDouble(this.meanOfLogs());
               break;
            case 2:
               var10000 = BoxesRunTime.boxToDouble(this.mean());
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
               var10000 = "meanOfLogs";
               break;
            case 2:
               var10000 = "mean";
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
         var1 = Statics.mix(var1, Statics.doubleHash(this.meanOfLogs()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.mean()));
         return Statics.finalizeHash(var1, 3);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label53: {
               boolean var2;
               if (x$1 instanceof SufficientStatistic) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  SufficientStatistic var4 = (SufficientStatistic)x$1;
                  if (this.n() == var4.n() && this.meanOfLogs() == var4.meanOfLogs() && this.mean() == var4.mean() && var4.canEqual(this)) {
                     break label53;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public SufficientStatistic(final double n, final double meanOfLogs, final double mean) {
         this.n = n;
         this.meanOfLogs = meanOfLogs;
         this.mean = mean;
         Product.$init$(this);
      }
   }

   public static class SufficientStatistic$ extends AbstractFunction3 implements Serializable {
      public static final SufficientStatistic$ MODULE$ = new SufficientStatistic$();

      public final String toString() {
         return "SufficientStatistic";
      }

      public SufficientStatistic apply(final double n, final double meanOfLogs, final double mean) {
         return new SufficientStatistic(n, meanOfLogs, mean);
      }

      public Option unapply(final SufficientStatistic x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToDouble(x$0.n()), BoxesRunTime.boxToDouble(x$0.meanOfLogs()), BoxesRunTime.boxToDouble(x$0.mean()))));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(SufficientStatistic$.class);
      }
   }
}
