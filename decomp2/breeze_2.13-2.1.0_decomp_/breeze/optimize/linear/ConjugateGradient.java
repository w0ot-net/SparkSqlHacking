package breeze.optimize.linear;

import breeze.generic.UFunc;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.norm$;
import breeze.linalg.package$;
import breeze.math.MutableInnerProductVectorSpace;
import breeze.util.Implicits$;
import breeze.util.LazyLogger;
import breeze.util.SerializableLogging;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple5;
import scala.Predef.ArrowAssoc.;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction5;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tue\u0001\u0002\u001d:\u0001\u0001C\u0001B\u0014\u0001\u0003\u0002\u0003\u0006Ia\u0014\u0005\t%\u0002\u0011\t\u0011)A\u0005'\"Aa\u000b\u0001B\u0001B\u0003%q\n\u0003\u0005X\u0001\t\u0005\t\u0015!\u0003P\u0011!A\u0006A!A!\u0002\u0017I\u0006\u0002\u00036\u0001\u0005\u0003\u0005\u000b1B6\t\u000bu\u0004A\u0011\u0001@\t\u000f\u0005E\u0001\u0001\"\u0001\u0002\u0014!9\u0011\u0011\u0003\u0001\u0005\u0002\u0005uaABA\u0014\u0001\u0001\u000bI\u0003\u0003\u0006\u0002J)\u0011)\u001a!C\u0001\u0003\u0017B\u0011\"!\u0014\u000b\u0005#\u0005\u000b\u0011B0\t\u0015\u0005=#B!f\u0001\n\u0003\tY\u0005C\u0005\u0002R)\u0011\t\u0012)A\u0005?\"Q\u00111\u000b\u0006\u0003\u0006#\u0005\u000b\u0011B0\t\u0015\u0005U#B!f\u0001\n\u0003\t9\u0006C\u0005\u0002Z)\u0011\t\u0012)A\u0005'\"Q\u00111\f\u0006\u0003\u0016\u0004%\t!!\u0018\t\u0015\u0005\u0015$B!E!\u0002\u0013\ty\u0006C\u0004~\u0015\u0011\u0005\u0001!a\u001a\t\u0015\u0005e$\u0002#b\u0001\n\u0003\tY\bC\u0005\u0002~)\t\t\u0011\"\u0001\u0002\u0000!I\u00111\u0012\u0006\u0012\u0002\u0013\u0005\u0011Q\u0012\u0005\n\u0003GS\u0011\u0013!C\u0001\u0003\u001bC\u0011\"!*\u000b#\u0003%\t!!$\t\u0013\u0005\u001d&\"%A\u0005\u0002\u0005%\u0006\"CAW\u0015E\u0005I\u0011AAX\u0011%\t\u0019LCF\u0001\n\u0003\tY\u0005C\u0005\u00026*\t\t\u0011\"\u0011\u00028\"I\u0011\u0011\u001a\u0006\u0002\u0002\u0013\u0005\u0011q\u000b\u0005\n\u0003\u0017T\u0011\u0011!C\u0001\u0003\u001bD\u0011\"a5\u000b\u0003\u0003%\t%!6\t\u0013\u0005\r(\"!A\u0005\u0002\u0005\u0015\b\"CAu\u0015\u0005\u0005I\u0011IAv\u0011%\tyOCA\u0001\n\u0003\n\t\u0010C\u0005\u0002t*\t\t\u0011\"\u0011\u0002v\"I\u0011q\u001f\u0006\u0002\u0002\u0013\u0005\u0013\u0011 \u0005\r\u0003{T!Q!b\u0001\n\u0003\u0001\u00111J\u0004\n\u0003\u007f\u0004\u0011\u0011!E\u0001\u0005\u00031\u0011\"a\n\u0001\u0003\u0003E\tAa\u0001\t\ruDC\u0011\u0001B\u000e\u0011%\t\u0019\u0010KA\u0001\n\u000b\n)\u0010C\u0005\u0003\u001e!\n\t\u0011\"!\u0003 !I!1\u0006\u0015\u0002\u0002\u0013\u0005%Q\u0006\u0005\b\u0005\u007f\u0001A\u0011\u0001B!\u0011\u001d\u0011y\u0005\u0001C\u0001\u0005#BqA!\u0018\u0001\t\u0013\u0011yfB\u0005\u0003he\n\t\u0011#\u0001\u0003j\u0019A\u0001(OA\u0001\u0012\u0003\u0011Y\u0007\u0003\u0004~c\u0011\u0005!Q\u000e\u0005\n\u0005_\n\u0014\u0013!C\u0001\u0005cB\u0011Ba\u001f2#\u0003%\tA! \t\u0013\t\r\u0015'%A\u0005\u0002\t\u0015\u0005\"\u0003BFcE\u0005I\u0011\u0001BG\u0011%\u0011\u0019*MA\u0001\n\u0013\u0011)JA\tD_:TWoZ1uK\u001e\u0013\u0018\rZ5f]RT!AO\u001e\u0002\r1Lg.Z1s\u0015\taT(\u0001\u0005paRLW.\u001b>f\u0015\u0005q\u0014A\u00022sK\u0016TXm\u0001\u0001\u0016\u0007\u0005\u000b7pE\u0002\u0001\u0005\"\u0003\"a\u0011$\u000e\u0003\u0011S\u0011!R\u0001\u0006g\u000e\fG.Y\u0005\u0003\u000f\u0012\u0013a!\u00118z%\u00164\u0007CA%M\u001b\u0005Q%BA&>\u0003\u0011)H/\u001b7\n\u00055S%aE*fe&\fG.\u001b>bE2,Gj\\4hS:<\u0017\u0001D7bq:{'/\u001c,bYV,\u0007CA\"Q\u0013\t\tFI\u0001\u0004E_V\u0014G.Z\u0001\u000e[\u0006D\u0018\n^3sCRLwN\\:\u0011\u0005\r#\u0016BA+E\u0005\rIe\u000e^\u0001\u0013]>\u0014XnU9vCJ,G\rU3oC2$\u00180A\u0005u_2,'/\u00198dK\u0006)1\u000f]1dKB!!,X0P\u001b\u0005Y&B\u0001/>\u0003\u0011i\u0017\r\u001e5\n\u0005y[&AH'vi\u0006\u0014G.Z%o]\u0016\u0014\bK]8ek\u000e$h+Z2u_J\u001c\u0006/Y2f!\t\u0001\u0017\r\u0004\u0001\u0005\u000b\t\u0004!\u0019A2\u0003\u0003Q\u000b\"\u0001Z4\u0011\u0005\r+\u0017B\u00014E\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"a\u00115\n\u0005%$%aA!os\u0006!Q.\u001e7u!\u0015aGO_0`\u001d\ti'/D\u0001o\u0015\ty\u0007/A\u0005pa\u0016\u0014\u0018\r^8sg*\u0011\u0011/P\u0001\u0007Y&t\u0017\r\\4\n\u0005Mt\u0017aC(q\u001bVdW*\u0019;sSbL!!\u001e<\u0003\u000b%k\u0007\u000f\u001c\u001a\n\u0005]D(!B+Gk:\u001c'BA=>\u0003\u001d9WM\\3sS\u000e\u0004\"\u0001Y>\u0005\u000bq\u0004!\u0019A2\u0003\u00035\u000ba\u0001P5oSRtD#C@\u0002\n\u0005-\u0011QBA\b)\u0019\t\t!!\u0002\u0002\bA)\u00111\u0001\u0001`u6\t\u0011\bC\u0003Y\u000f\u0001\u000f\u0011\fC\u0003k\u000f\u0001\u000f1\u000eC\u0004O\u000fA\u0005\t\u0019A(\t\u000fI;\u0001\u0013!a\u0001'\"9ak\u0002I\u0001\u0002\u0004y\u0005bB,\b!\u0003\u0005\raT\u0001\t[&t\u0017.\\5{KR)q,!\u0006\u0002\u001a!1\u0011q\u0003\u0005A\u0002}\u000b\u0011!\u0019\u0005\u0007\u00037A\u0001\u0019\u0001>\u0002\u0003\t#raXA\u0010\u0003C\t\u0019\u0003\u0003\u0004\u0002\u0018%\u0001\ra\u0018\u0005\u0007\u00037I\u0001\u0019\u0001>\t\r\u0005\u0015\u0012\u00021\u0001`\u0003\u0015Ig.\u001b;Y\u0005\u0015\u0019F/\u0019;f'\u0019Q!)a\u000b\u00022A\u00191)!\f\n\u0007\u0005=BIA\u0004Qe>$Wo\u0019;\u0011\t\u0005M\u00121\t\b\u0005\u0003k\tyD\u0004\u0003\u00028\u0005uRBAA\u001d\u0015\r\tYdP\u0001\u0007yI|w\u000e\u001e \n\u0003\u0015K1!!\u0011E\u0003\u001d\u0001\u0018mY6bO\u0016LA!!\u0012\u0002H\ta1+\u001a:jC2L'0\u00192mK*\u0019\u0011\u0011\t#\u0002\u0003a,\u0012aX\u0001\u0003q\u0002\n\u0001B]3tS\u0012,\u0018\r\\\u0001\ne\u0016\u001c\u0018\u000eZ;bY\u0002\nAG\u0019:fKj,Ge\u001c9uS6L'0\u001a\u0013mS:,\u0017M\u001d\u0013D_:TWoZ1uK\u001e\u0013\u0018\rZ5f]R$C\u0005Z5sK\u000e$\u0018n\u001c8!\u0003\u0011IG/\u001a:\u0016\u0003M\u000bQ!\u001b;fe\u0002\n\u0011bY8om\u0016\u0014x-\u001a3\u0016\u0005\u0005}\u0003cA\"\u0002b%\u0019\u00111\r#\u0003\u000f\t{w\u000e\\3b]\u0006Q1m\u001c8wKJ<W\r\u001a\u0011\u0015\u0019\u0005%\u0014QNA8\u0003c\n)(a\u001e\u0011\u0007\u0005-$\"D\u0001\u0001\u0011\u0019\tI\u0005\u0006a\u0001?\"1\u0011q\n\u000bA\u0002}Ca!a\u001d\u0015\u0001\u0004y\u0016!\u00033je\u0016\u001cG/[8o\u0011\u0019\t)\u0006\u0006a\u0001'\"9\u00111\f\u000bA\u0002\u0005}\u0013a\u0001:ueV\tq*\u0001\u0003d_BLH\u0003DA5\u0003\u0003\u000b\u0019)!\"\u0002\b\u0006%\u0005\u0002CA%-A\u0005\t\u0019A0\t\u0011\u0005=c\u0003%AA\u0002}C\u0001\"a\u001d\u0017!\u0003\u0005\ra\u0018\u0005\t\u0003+2\u0002\u0013!a\u0001'\"I\u00111\f\f\u0011\u0002\u0003\u0007\u0011qL\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\tyIK\u0002`\u0003#[#!a%\u0011\t\u0005U\u0015qT\u0007\u0003\u0003/SA!!'\u0002\u001c\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003;#\u0015AC1o]>$\u0018\r^5p]&!\u0011\u0011UAL\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM\nabY8qs\u0012\"WMZ1vYR$C'\u0006\u0002\u0002,*\u001a1+!%\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%kU\u0011\u0011\u0011\u0017\u0016\u0005\u0003?\n\t*\u0001\neSJ,7\r^5p]\u0012\n7mY3tg\u0012\u0012\u0014!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0002:B!\u00111XAc\u001b\t\tiL\u0003\u0003\u0002@\u0006\u0005\u0017\u0001\u00027b]\u001eT!!a1\u0002\t)\fg/Y\u0005\u0005\u0003\u000f\fiL\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\r9\u0017q\u001a\u0005\t\u0003#|\u0012\u0011!a\u0001'\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a6\u0011\u000b\u0005e\u0017q\\4\u000e\u0005\u0005m'bAAo\t\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005\u0005\u00181\u001c\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002`\u0005\u001d\b\u0002CAiC\u0005\u0005\t\u0019A4\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003s\u000bi\u000f\u0003\u0005\u0002R\n\n\t\u00111\u0001T\u0003!A\u0017m\u001d5D_\u0012,G#A*\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!/\u0002\r\u0015\fX/\u00197t)\u0011\ty&a?\t\u0011\u0005EW%!AA\u0002\u001d\f1G\u0019:fKj,Ge\u001c9uS6L'0\u001a\u0013mS:,\u0017M\u001d\u0013D_:TWoZ1uK\u001e\u0013\u0018\rZ5f]R$C\u0005Z5sK\u000e$\u0018n\u001c8\u0002\u000bM#\u0018\r^3\u0011\u0007\u0005-\u0004fE\u0003)\u0005\u000b\u0011\t\u0002\u0005\u0007\u0003\b\t5qlX0T\u0003?\nI'\u0004\u0002\u0003\n)\u0019!1\u0002#\u0002\u000fI,h\u000e^5nK&!!q\u0002B\u0005\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\u000e\t\u0005\u0005'\u0011I\"\u0004\u0002\u0003\u0016)!!qCAa\u0003\tIw.\u0003\u0003\u0002F\tUAC\u0001B\u0001\u0003\u0015\t\u0007\u000f\u001d7z)1\tIG!\t\u0003$\t\u0015\"q\u0005B\u0015\u0011\u0019\tIe\u000ba\u0001?\"1\u0011qJ\u0016A\u0002}Ca!a\u001d,\u0001\u0004y\u0006BBA+W\u0001\u00071\u000bC\u0004\u0002\\-\u0002\r!a\u0018\u0002\u000fUt\u0017\r\u001d9msR!!q\u0006B\u001e!\u0015\u0019%\u0011\u0007B\u001b\u0013\r\u0011\u0019\u0004\u0012\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0013\r\u00139dX0`'\u0006}\u0013b\u0001B\u001d\t\n1A+\u001e9mKVB\u0011B!\u0010-\u0003\u0003\u0005\r!!\u001b\u0002\u0007a$\u0003'A\rnS:LW.\u001b>f\u0003:$'+\u001a;ve:\u0014Vm]5ek\u0006dG\u0003\u0003B\"\u0005\u0013\u0012YE!\u0014\u0011\u000b\r\u0013)eX0\n\u0007\t\u001dCI\u0001\u0004UkBdWM\r\u0005\u0007\u0003/i\u0003\u0019A0\t\r\u0005mQ\u00061\u0001{\u0011\u0019\t)#\fa\u0001?\u0006Q\u0011\u000e^3sCRLwN\\:\u0015\u0011\tM#q\u000bB-\u00057\u0002b!a\r\u0003V\u0005%\u0014\u0002BAq\u0003\u000fBa!a\u0006/\u0001\u0004y\u0006BBA\u000e]\u0001\u0007!\u0010\u0003\u0004\u0002&9\u0002\raX\u0001\rS:LG/[1m'R\fG/\u001a\u000b\t\u0003S\u0012\tGa\u0019\u0003f!1\u0011qC\u0018A\u0002}Ca!a\u00070\u0001\u0004Q\bBBA\u0013_\u0001\u0007q,A\tD_:TWoZ1uK\u001e\u0013\u0018\rZ5f]R\u00042!a\u00012'\u0011\t$I!\u0005\u0015\u0005\t%\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0013'\u0006\u0004\u0003t\t]$\u0011P\u000b\u0003\u0005kR3aTAI\t\u0015\u00117G1\u0001d\t\u0015a8G1\u0001d\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%eU1\u0011\u0011\u0016B@\u0005\u0003#QA\u0019\u001bC\u0002\r$Q\u0001 \u001bC\u0002\r\f1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u001aTC\u0002B:\u0005\u000f\u0013I\tB\u0003ck\t\u00071\rB\u0003}k\t\u00071-A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H\u0005N\u000b\u0007\u0005g\u0012yI!%\u0005\u000b\t4$\u0019A2\u0005\u000bq4$\u0019A2\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\t]\u0005\u0003BA^\u00053KAAa'\u0002>\n1qJ\u00196fGR\u0004"
)
public class ConjugateGradient implements SerializableLogging {
   private volatile State$ State$module;
   private final double maxNormValue;
   private final int maxIterations;
   private final double normSquaredPenalty;
   private final double tolerance;
   public final MutableInnerProductVectorSpace breeze$optimize$linear$ConjugateGradient$$space;
   private final UFunc.UImpl2 mult;
   private transient volatile LazyLogger breeze$util$SerializableLogging$$_the_logger;

   public static double $lessinit$greater$default$4() {
      return ConjugateGradient$.MODULE$.$lessinit$greater$default$4();
   }

   public static double $lessinit$greater$default$3() {
      return ConjugateGradient$.MODULE$.$lessinit$greater$default$3();
   }

   public static int $lessinit$greater$default$2() {
      return ConjugateGradient$.MODULE$.$lessinit$greater$default$2();
   }

   public static double $lessinit$greater$default$1() {
      return ConjugateGradient$.MODULE$.$lessinit$greater$default$1();
   }

   public LazyLogger logger() {
      return SerializableLogging.logger$(this);
   }

   public State$ State() {
      if (this.State$module == null) {
         this.State$lzycompute$1();
      }

      return this.State$module;
   }

   public LazyLogger breeze$util$SerializableLogging$$_the_logger() {
      return this.breeze$util$SerializableLogging$$_the_logger;
   }

   public void breeze$util$SerializableLogging$$_the_logger_$eq(final LazyLogger x$1) {
      this.breeze$util$SerializableLogging$$_the_logger = x$1;
   }

   public Object minimize(final Object a, final Object B) {
      return this.minimize(a, B, this.breeze$optimize$linear$ConjugateGradient$$space.zeroLike().apply(a));
   }

   public Object minimize(final Object a, final Object B, final Object initX) {
      return this.minimizeAndReturnResidual(a, B, initX)._1();
   }

   public Tuple2 minimizeAndReturnResidual(final Object a, final Object B, final Object initX) {
      State state = (State)Implicits$.MODULE$.scEnrichIterator(this.iterations(a, B, initX)).last();
      return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(state.x()), state.residual());
   }

   public Iterator iterations(final Object a, final Object B, final Object initX) {
      return Implicits$.MODULE$.scEnrichIterator(scala.package..MODULE$.Iterator().iterate(this.initialState(a, B, initX), (state) -> {
         Object r = state.residual();
         Object d = state.breeze$optimize$linear$ConjugateGradient$$direction();
         double rtr = state.rtr();
         Object Bd = this.mult.apply(B, d);
         double dtd = BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.breeze$optimize$linear$ConjugateGradient$$space.hasOps().apply(d)).dot(d, this.breeze$optimize$linear$ConjugateGradient$$space.dotVV()));
         double alpha = scala.math.package..MODULE$.pow(BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(r, this.breeze$optimize$linear$ConjugateGradient$$space.normImpl())), (double)2.0F) / (BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.breeze$optimize$linear$ConjugateGradient$$space.hasOps().apply(d)).dot(Bd, this.breeze$optimize$linear$ConjugateGradient$$space.dotVV())) + this.normSquaredPenalty * dtd);
         Object nextX = ((NumericOps)this.breeze$optimize$linear$ConjugateGradient$$space.hasOps().apply(state.x())).$plus(((ImmutableNumericOps)this.breeze$optimize$linear$ConjugateGradient$$space.hasOps().apply(d)).$times(BoxesRunTime.boxToDouble(alpha), this.breeze$optimize$linear$ConjugateGradient$$space.mulVS_M()), this.breeze$optimize$linear$ConjugateGradient$$space.addVV());
         double xnorm = BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(nextX, this.breeze$optimize$linear$ConjugateGradient$$space.normImpl()));
         State var10000;
         if (xnorm >= this.maxNormValue) {
            this.logger().info(() -> {
               Object arg$macro$1 = BoxesRunTime.boxToInteger(state.iter());
               Object arg$macro$3 = BoxesRunTime.boxToDouble(this.maxNormValue);
               return scala.collection.StringOps..MODULE$.format$extension("%s boundary reached! norm(x): %.3f >= maxNormValue %s", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{arg$macro$1, BoxesRunTime.boxToDouble(xnorm), arg$macro$3}));
            });
            double xtd = BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.breeze$optimize$linear$ConjugateGradient$$space.hasOps().apply(state.x())).dot(d, this.breeze$optimize$linear$ConjugateGradient$$space.dotVV()));
            double xtx = BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.breeze$optimize$linear$ConjugateGradient$$space.hasOps().apply(state.x())).dot(state.x(), this.breeze$optimize$linear$ConjugateGradient$$space.dotVV()));
            double normSquare = this.maxNormValue * this.maxNormValue;
            double radius = scala.math.package..MODULE$.sqrt(xtd * xtd + dtd * (normSquare - xtx));
            double alphaNext = xtd >= (double)0 ? (normSquare - xtx) / (xtd + radius) : (radius - xtd) / dtd;
            scala.Predef..MODULE$.assert(!Double.isNaN(alphaNext), () -> (new StringBuilder(6)).append(xtd).append(" ").append(normSquare).append(" ").append(xtx).append("  ").append(xtd).append(" ").append(radius).append(" ").append(dtd).toString());
            package$.MODULE$.axpy(BoxesRunTime.boxToDouble(alphaNext), d, state.x(), this.breeze$optimize$linear$ConjugateGradient$$space.scaleAddVV());
            package$.MODULE$.axpy(BoxesRunTime.boxToDouble(-alphaNext), ((NumericOps)this.breeze$optimize$linear$ConjugateGradient$$space.hasOps().apply(Bd)).$plus(((ImmutableNumericOps)this.breeze$optimize$linear$ConjugateGradient$$space.hasOps().apply(d)).$times$colon$times(BoxesRunTime.boxToDouble(this.normSquaredPenalty), this.breeze$optimize$linear$ConjugateGradient$$space.mulVS()), this.breeze$optimize$linear$ConjugateGradient$$space.addVV()), r, this.breeze$optimize$linear$ConjugateGradient$$space.scaleAddVV());
            var10000 = this.State().apply(state.x(), r, d, state.iter() + 1, true);
         } else {
            ((NumericOps)this.breeze$optimize$linear$ConjugateGradient$$space.hasOps().apply(state.x())).$colon$eq(nextX, this.breeze$optimize$linear$ConjugateGradient$$space.setIntoVV());
            ((NumericOps)this.breeze$optimize$linear$ConjugateGradient$$space.hasOps().apply(r)).$minus$eq(((ImmutableNumericOps)this.breeze$optimize$linear$ConjugateGradient$$space.hasOps().apply(((NumericOps)this.breeze$optimize$linear$ConjugateGradient$$space.hasOps().apply(Bd)).$plus(((ImmutableNumericOps)this.breeze$optimize$linear$ConjugateGradient$$space.hasOps().apply(d)).$times$colon$times(BoxesRunTime.boxToDouble(this.normSquaredPenalty), this.breeze$optimize$linear$ConjugateGradient$$space.mulVS()), this.breeze$optimize$linear$ConjugateGradient$$space.addVV()))).$times$colon$times(BoxesRunTime.boxToDouble(alpha), this.breeze$optimize$linear$ConjugateGradient$$space.mulVS()), this.breeze$optimize$linear$ConjugateGradient$$space.subIntoVV());
            double newrtr = BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.breeze$optimize$linear$ConjugateGradient$$space.hasOps().apply(r)).dot(r, this.breeze$optimize$linear$ConjugateGradient$$space.dotVV()));
            double beta = newrtr / rtr;
            ((NumericOps)this.breeze$optimize$linear$ConjugateGradient$$space.hasOps().apply(d)).$colon$times$eq(BoxesRunTime.boxToDouble(beta), this.breeze$optimize$linear$ConjugateGradient$$space.mulIntoVS());
            ((NumericOps)this.breeze$optimize$linear$ConjugateGradient$$space.hasOps().apply(d)).$plus$eq(r, this.breeze$optimize$linear$ConjugateGradient$$space.addIntoVV());
            double normr = BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(r, this.breeze$optimize$linear$ConjugateGradient$$space.normImpl()));
            boolean converged = normr <= this.tolerance || state.iter() > this.maxIterations && this.maxIterations > 0;
            if (!converged) {
               this.logger().info(() -> {
                  Object arg$macro$10 = BoxesRunTime.boxToInteger(state.iter());
                  Object arg$macro$12 = BoxesRunTime.boxToDouble(this.tolerance);
                  return scala.collection.StringOps..MODULE$.format$extension("%s: norm(residual): %.3f > tolerance %s.", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{arg$macro$10, BoxesRunTime.boxToDouble(normr), arg$macro$12}));
               });
            } else {
               boolean done = state.iter() > this.maxIterations && this.maxIterations > 0;
               if (done) {
                  this.logger().info(() -> {
                     Object arg$macro$4 = BoxesRunTime.boxToInteger(state.iter());
                     Object arg$macro$6 = BoxesRunTime.boxToDouble(this.tolerance);
                     return scala.collection.StringOps..MODULE$.format$extension("max iteration %s reached! norm(residual): %.3f > tolerance %s.", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{arg$macro$4, BoxesRunTime.boxToDouble(normr), arg$macro$6}));
                  });
               } else {
                  this.logger().info(() -> {
                     Object arg$macro$7 = BoxesRunTime.boxToInteger(state.iter());
                     Object arg$macro$9 = BoxesRunTime.boxToDouble(this.tolerance);
                     return scala.collection.StringOps..MODULE$.format$extension("%s converged! norm(residual): %.3f <= tolerance %s.", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{arg$macro$7, BoxesRunTime.boxToDouble(normr), arg$macro$9}));
                  });
               }
            }

            var10000 = this.State().apply(state.x(), r, d, state.iter() + 1, converged);
         }

         return var10000;
      })).takeUpToWhere((x$1) -> BoxesRunTime.boxToBoolean($anonfun$iterations$7(x$1)));
   }

   private State initialState(final Object a, final Object B, final Object initX) {
      Object r = ((ImmutableNumericOps)this.breeze$optimize$linear$ConjugateGradient$$space.hasOps().apply(((ImmutableNumericOps)this.breeze$optimize$linear$ConjugateGradient$$space.hasOps().apply(a)).$minus(this.mult.apply(B, initX), this.breeze$optimize$linear$ConjugateGradient$$space.subVV()))).$minus(((ImmutableNumericOps)this.breeze$optimize$linear$ConjugateGradient$$space.hasOps().apply(initX)).$times$colon$times(BoxesRunTime.boxToDouble(this.normSquaredPenalty), this.breeze$optimize$linear$ConjugateGradient$$space.mulVS()), this.breeze$optimize$linear$ConjugateGradient$$space.subVV());
      Object d = this.breeze$optimize$linear$ConjugateGradient$$space.copy().apply(r);
      double rnorm = BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(r, this.breeze$optimize$linear$ConjugateGradient$$space.normImpl()));
      return this.State().apply(initX, r, d, 0, rnorm <= this.tolerance);
   }

   private final void State$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.State$module == null) {
            this.State$module = new State$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   // $FF: synthetic method
   public static final boolean $anonfun$iterations$7(final State x$1) {
      return x$1.converged();
   }

   public ConjugateGradient(final double maxNormValue, final int maxIterations, final double normSquaredPenalty, final double tolerance, final MutableInnerProductVectorSpace space, final UFunc.UImpl2 mult) {
      this.maxNormValue = maxNormValue;
      this.maxIterations = maxIterations;
      this.normSquaredPenalty = normSquaredPenalty;
      this.tolerance = tolerance;
      this.breeze$optimize$linear$ConjugateGradient$$space = space;
      this.mult = mult;
      SerializableLogging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class State implements Product, Serializable {
      private double rtr;
      private final Object x;
      private final Object residual;
      private final Object breeze$optimize$linear$ConjugateGradient$$direction;
      private final int iter;
      private final boolean converged;
      private volatile boolean bitmap$0;
      // $FF: synthetic field
      public final ConjugateGradient $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Object direction$access$2() {
         return this.breeze$optimize$linear$ConjugateGradient$$direction;
      }

      public Object x() {
         return this.x;
      }

      public Object residual() {
         return this.residual;
      }

      public Object breeze$optimize$linear$ConjugateGradient$$direction() {
         return this.breeze$optimize$linear$ConjugateGradient$$direction;
      }

      public int iter() {
         return this.iter;
      }

      public boolean converged() {
         return this.converged;
      }

      private double rtr$lzycompute() {
         synchronized(this){}

         try {
            if (!this.bitmap$0) {
               this.rtr = BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.breeze$optimize$linear$ConjugateGradient$State$$$outer().breeze$optimize$linear$ConjugateGradient$$space.hasOps().apply(this.residual())).dot(this.residual(), this.breeze$optimize$linear$ConjugateGradient$State$$$outer().breeze$optimize$linear$ConjugateGradient$$space.dotVV()));
               this.bitmap$0 = true;
            }
         } catch (Throwable var3) {
            throw var3;
         }

         return this.rtr;
      }

      public double rtr() {
         return !this.bitmap$0 ? this.rtr$lzycompute() : this.rtr;
      }

      public State copy(final Object x, final Object residual, final Object direction, final int iter, final boolean converged) {
         return this.breeze$optimize$linear$ConjugateGradient$State$$$outer().new State(x, residual, direction, iter, converged);
      }

      public Object copy$default$1() {
         return this.x();
      }

      public Object copy$default$2() {
         return this.residual();
      }

      public Object copy$default$3() {
         return this.breeze$optimize$linear$ConjugateGradient$$direction();
      }

      public int copy$default$4() {
         return this.iter();
      }

      public boolean copy$default$5() {
         return this.converged();
      }

      public String productPrefix() {
         return "State";
      }

      public int productArity() {
         return 5;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.x();
               break;
            case 1:
               var10000 = this.residual();
               break;
            case 2:
               var10000 = this.direction$access$2();
               break;
            case 3:
               var10000 = BoxesRunTime.boxToInteger(this.iter());
               break;
            case 4:
               var10000 = BoxesRunTime.boxToBoolean(this.converged());
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
         return x$1 instanceof State;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "x";
               break;
            case 1:
               var10000 = "residual";
               break;
            case 2:
               var10000 = "direction";
               break;
            case 3:
               var10000 = "iter";
               break;
            case 4:
               var10000 = "converged";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.anyHash(this.x()));
         var1 = Statics.mix(var1, Statics.anyHash(this.residual()));
         var1 = Statics.mix(var1, Statics.anyHash(this.direction$access$2()));
         var1 = Statics.mix(var1, this.iter());
         var1 = Statics.mix(var1, this.converged() ? 1231 : 1237);
         return Statics.finalizeHash(var1, 5);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label62: {
               boolean var2;
               if (x$1 instanceof State && ((State)x$1).breeze$optimize$linear$ConjugateGradient$State$$$outer() == this.breeze$optimize$linear$ConjugateGradient$State$$$outer()) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  State var4 = (State)x$1;
                  if (this.iter() == var4.iter() && this.converged() == var4.converged() && BoxesRunTime.equals(this.x(), var4.x()) && BoxesRunTime.equals(this.residual(), var4.residual()) && BoxesRunTime.equals(this.direction$access$2(), var4.direction$access$2()) && var4.canEqual(this)) {
                     break label62;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      // $FF: synthetic method
      public ConjugateGradient breeze$optimize$linear$ConjugateGradient$State$$$outer() {
         return this.$outer;
      }

      public State(final Object x, final Object residual, final Object direction, final int iter, final boolean converged) {
         this.x = x;
         this.residual = residual;
         this.breeze$optimize$linear$ConjugateGradient$$direction = direction;
         this.iter = iter;
         this.converged = converged;
         if (ConjugateGradient.this == null) {
            throw null;
         } else {
            this.$outer = ConjugateGradient.this;
            super();
            Product.$init$(this);
         }
      }
   }

   public class State$ extends AbstractFunction5 implements Serializable {
      // $FF: synthetic field
      private final ConjugateGradient $outer;

      public final String toString() {
         return "State";
      }

      public State apply(final Object x, final Object residual, final Object direction, final int iter, final boolean converged) {
         return this.$outer.new State(x, residual, direction, iter, converged);
      }

      public Option unapply(final State x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple5(x$0.x(), x$0.residual(), x$0.direction$access$2(), BoxesRunTime.boxToInteger(x$0.iter()), BoxesRunTime.boxToBoolean(x$0.converged()))));
      }

      public State$() {
         if (ConjugateGradient.this == null) {
            throw null;
         } else {
            this.$outer = ConjugateGradient.this;
            super();
         }
      }
   }
}
