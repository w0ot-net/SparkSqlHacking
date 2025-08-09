package breeze.optimize;

import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.operators.HasOps$;
import breeze.math.MutableFiniteCoordinateField;
import breeze.numerics.package$sqrt$sqrtDoubleImpl$;
import breeze.stats.distributions.RandBasis;
import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Predef.ArrowAssoc.;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015d\u0001B\u00181\u0001UB\u0001\"\u0013\u0001\u0003\u0002\u0003\u0006IA\u0013\u0005\n\u001b\u0002\u0011\t\u0011)A\u0005\u001dFC\u0001B\u0015\u0001\u0003\u0002\u0003\u0006IA\u0013\u0005\t'\u0002\u0011\t\u0011)A\u0005\u0015\"AA\u000b\u0001B\u0001B\u0003%a\nC\u0005V\u0001\t\u0005\t\u0015a\u0003WA\"A\u0011\r\u0001B\u0001B\u0003-!\rC\u0003k\u0001\u0011\u00051\u000eC\u0004z\u0001\t\u0007I\u0011\u0001>\t\rm\u0004\u0001\u0015!\u0003K\r\u0011a\b\u0001Q?\t\u0015\u0005\u00052B!f\u0001\n\u0003\t\u0019\u0003C\u0005\u0002&-\u0011\t\u0012)A\u0005w!Q\u0011qE\u0006\u0003\u0016\u0004%\t!a\t\t\u0013\u0005%2B!E!\u0002\u0013Y\u0004B\u00026\f\t\u0003\tY\u0003C\u0005\u00026-\t\t\u0011\"\u0001\u00028!I\u0011QH\u0006\u0012\u0002\u0013\u0005\u0011q\b\u0005\n\u0003+Z\u0011\u0013!C\u0001\u0003\u007fA\u0011\"a\u0016\f\u0003\u0003%\t%!\u0017\t\u0013\u0005-4\"!A\u0005\u0002\u00055\u0004\"CA8\u0017\u0005\u0005I\u0011AA9\u0011%\t9hCA\u0001\n\u0003\nI\bC\u0005\u0002\b.\t\t\u0011\"\u0001\u0002\n\"I\u00111S\u0006\u0002\u0002\u0013\u0005\u0013Q\u0013\u0005\n\u00033[\u0011\u0011!C!\u00037C\u0011\"!(\f\u0003\u0003%\t%a(\t\u0013\u0005\u00056\"!A\u0005B\u0005\rv!CAT\u0001\u0005\u0005\t\u0012AAU\r!a\b!!A\t\u0002\u0005-\u0006B\u00026\u001f\t\u0003\t\u0019\rC\u0005\u0002\u001ez\t\t\u0011\"\u0012\u0002 \"I\u0011Q\u0019\u0010\u0002\u0002\u0013\u0005\u0015q\u0019\u0005\n\u0003\u001bt\u0012\u0011!CA\u0003\u001fDq!!9\u0001\t#\n\u0019\u000fC\u0004\u0002t\u0002!\t&!>\t\u000f\tM\u0001\u0001\"\u0015\u0003\u0016!9!1\u0005\u0001\u0005B\t\u0015\u0002b\u0002B\u0017\u0001\u0011E#qF\u0004\n\u0005s\u0001\u0014\u0011!E\u0001\u0005w1\u0001b\f\u0019\u0002\u0002#\u0005!Q\b\u0005\u0007U&\"\tAa\u0010\t\u0013\t\u0005\u0013&%A\u0005\u0002\t\r\u0003\"\u0003B&SE\u0005I\u0011\u0001B'\u0011%\u0011\t&KI\u0001\n\u0003\u0011\u0019\u0006C\u0005\u0003\\%\n\t\u0011\"\u0003\u0003^\t9\u0012\tZ1EK2$\u0018m\u0012:bI&,g\u000e\u001e#fg\u000e,g\u000e\u001e\u0006\u0003cI\n\u0001b\u001c9uS6L'0\u001a\u0006\u0002g\u00051!M]3fu\u0016\u001c\u0001!\u0006\u00027{M\u0011\u0001a\u000e\t\u0004qeZT\"\u0001\u0019\n\u0005i\u0002$!G*u_\u000eD\u0017m\u001d;jG\u001e\u0013\u0018\rZ5f]R$Um]2f]R\u0004\"\u0001P\u001f\r\u0001\u0011)a\b\u0001b\u0001\u007f\t\tA+\u0005\u0002A\rB\u0011\u0011\tR\u0007\u0002\u0005*\t1)A\u0003tG\u0006d\u0017-\u0003\u0002F\u0005\n9aj\u001c;iS:<\u0007CA!H\u0013\tA%IA\u0002B]f\f1A\u001d5p!\t\t5*\u0003\u0002M\u0005\n1Ai\\;cY\u0016\fq!\\1y\u0013R,'\u000f\u0005\u0002B\u001f&\u0011\u0001K\u0011\u0002\u0004\u0013:$\u0018BA':\u0003%!x\u000e\\3sC:\u001cW-\u0001\u000bj[B\u0014xN^3nK:$Hk\u001c7fe\u0006t7-Z\u0001\u0015[&t\u0017*\u001c9s_Z,W.\u001a8u/&tGm\\<\u0002\rY\u001c\b/Y2fa\t9f\fE\u0003Y7nj&*D\u0001Z\u0015\tQ&'\u0001\u0003nCRD\u0017B\u0001/Z\u0005qiU\u000f^1cY\u00164\u0015N\\5uK\u000e{wN\u001d3j]\u0006$XMR5fY\u0012\u0004\"\u0001\u00100\u0005\u0013}3\u0011\u0011!A\u0001\u0006\u0003y$aA0%c%\u0011Q+O\u0001\u0005e\u0006tG\r\u0005\u0002dQ6\tAM\u0003\u0002fM\u0006iA-[:ue&\u0014W\u000f^5p]NT!a\u001a\u001a\u0002\u000bM$\u0018\r^:\n\u0005%$'!\u0003*b]\u0012\u0014\u0015m]5t\u0003\u0019a\u0014N\\5u}Q1A\u000e^;wob$2!\u001c8t!\rA\u0004a\u000f\u0005\u0006+\"\u0001\u001da\u001c\u0019\u0003aJ\u0004R\u0001W.<c*\u0003\"\u0001\u0010:\u0005\u0013}s\u0017\u0011!A\u0001\u0006\u0003y\u0004\"B1\t\u0001\b\u0011\u0007\"B%\t\u0001\u0004Q\u0005\"B'\t\u0001\u0004q\u0005b\u0002*\t!\u0003\u0005\rA\u0013\u0005\b'\"\u0001\n\u00111\u0001K\u0011\u001d!\u0006\u0002%AA\u00029\u000bq!\u001a9tS2|g.F\u0001K\u0003!)\u0007o]5m_:\u0004#a\u0002%jgR|'/_\n\u0007\u0017y\f\u0019!!\u0003\u0011\u0005\u0005{\u0018bAA\u0001\u0005\n1\u0011I\\=SK\u001a\u00042!QA\u0003\u0013\r\t9A\u0011\u0002\b!J|G-^2u!\u0011\tY!a\u0007\u000f\t\u00055\u0011q\u0003\b\u0005\u0003\u001f\t)\"\u0004\u0002\u0002\u0012)\u0019\u00111\u0003\u001b\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0019\u0015bAA\r\u0005\u00069\u0001/Y2lC\u001e,\u0017\u0002BA\u000f\u0003?\u0011AbU3sS\u0006d\u0017N_1cY\u0016T1!!\u0007C\u00035\tgoZ*r\u000fJ\fG-[3oiV\t1(\u0001\bbm\u001e\u001c\u0016o\u0012:bI&,g\u000e\u001e\u0011\u0002\u0015\u00054xmU9EK2$\u0018-A\u0006bm\u001e\u001c\u0016\u000fR3mi\u0006\u0004CCBA\u0017\u0003c\t\u0019\u0004E\u0002\u00020-i\u0011\u0001\u0001\u0005\u0007\u0003C\u0001\u0002\u0019A\u001e\t\r\u0005\u001d\u0002\u00031\u0001<\u0003\u0011\u0019w\u000e]=\u0015\r\u00055\u0012\u0011HA\u001e\u0011!\t\t#\u0005I\u0001\u0002\u0004Y\u0004\u0002CA\u0014#A\u0005\t\u0019A\u001e\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011\u0011\t\u0016\u0004w\u0005\r3FAA#!\u0011\t9%!\u0015\u000e\u0005\u0005%#\u0002BA&\u0003\u001b\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005=#)\u0001\u0006b]:|G/\u0019;j_:LA!a\u0015\u0002J\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%e\u0005i\u0001O]8ek\u000e$\bK]3gSb,\"!a\u0017\u0011\t\u0005u\u0013qM\u0007\u0003\u0003?RA!!\u0019\u0002d\u0005!A.\u00198h\u0015\t\t)'\u0001\u0003kCZ\f\u0017\u0002BA5\u0003?\u0012aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#\u0001(\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0019a)a\u001d\t\u0011\u0005Ud#!AA\u00029\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA>!\u0015\ti(a!G\u001b\t\tyHC\u0002\u0002\u0002\n\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\t))a \u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u0017\u000b\t\nE\u0002B\u0003\u001bK1!a$C\u0005\u001d\u0011un\u001c7fC:D\u0001\"!\u001e\u0019\u0003\u0003\u0005\rAR\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002\\\u0005]\u0005\u0002CA;3\u0005\u0005\t\u0019\u0001(\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012AT\u0001\ti>\u001cFO]5oOR\u0011\u00111L\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005-\u0015Q\u0015\u0005\t\u0003kb\u0012\u0011!a\u0001\r\u00069\u0001*[:u_JL\bcAA\u0018=M)a$!,\u0002:BA\u0011qVA[wm\ni#\u0004\u0002\u00022*\u0019\u00111\u0017\"\u0002\u000fI,h\u000e^5nK&!\u0011qWAY\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\t\u0005\u0003w\u000b\t-\u0004\u0002\u0002>*!\u0011qXA2\u0003\tIw.\u0003\u0003\u0002\u001e\u0005uFCAAU\u0003\u0015\t\u0007\u000f\u001d7z)\u0019\ti#!3\u0002L\"1\u0011\u0011E\u0011A\u0002mBa!a\n\"\u0001\u0004Y\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003#\fi\u000eE\u0003B\u0003'\f9.C\u0002\u0002V\n\u0013aa\u00149uS>t\u0007#B!\u0002ZnZ\u0014bAAn\u0005\n1A+\u001e9mKJB\u0011\"a8#\u0003\u0003\u0005\r!!\f\u0002\u0007a$\u0003'\u0001\bj]&$\u0018.\u00197ISN$xN]=\u0015\r\u00055\u0012Q]Ax\u0011\u001d\t9o\ta\u0001\u0003S\f\u0011A\u001a\t\u0005q\u0005-8(C\u0002\u0002nB\u0012ac\u0015;pG\"\f7\u000f^5d\t&4gMR;oGRLwN\u001c\u0005\u0007\u0003c\u001c\u0003\u0019A\u001e\u0002\t%t\u0017\u000e^\u0001\u000ekB$\u0017\r^3ISN$xN]=\u0015\u0019\u00055\u0012q_A~\u0003\u007f\u0014\u0019A!\u0002\t\r\u0005eH\u00051\u0001<\u0003\u0011qWm\u001e-\t\r\u0005uH\u00051\u0001<\u0003\u001dqWm^$sC\u0012DaA!\u0001%\u0001\u0004Q\u0015A\u00028foZ\u000bG\u000eC\u0004\u0002h\u0012\u0002\r!!;\t\u000f\t\u001dA\u00051\u0001\u0003\n\u0005Aq\u000e\u001c3Ti\u0006$X\r\u0005\u0003\u00020\t-\u0011\u0002\u0002B\u0007\u0005\u001f\u0011Qa\u0015;bi\u0016L1A!\u00051\u0005M1\u0015N]:u\u001fJ$WM]'j]&l\u0017N_3s\u0003!!\u0018m[3Ti\u0016\u0004HcB\u001e\u0003\u0018\tm!q\u0004\u0005\b\u00053)\u0003\u0019\u0001B\u0005\u0003\u0015\u0019H/\u0019;f\u0011\u0019\u0011i\"\na\u0001w\u0005\u0019A-\u001b:\t\r\t\u0005R\u00051\u0001K\u0003!\u0019H/\u001a9TSj,\u0017!\u00053fi\u0016\u0014X.\u001b8f'R,\u0007oU5{KR9!Ja\n\u0003*\t-\u0002b\u0002B\rM\u0001\u0007!\u0011\u0002\u0005\b\u0003O4\u0003\u0019AAu\u0011\u0019\u0011iB\na\u0001w\u00051\u0011\r\u001a6vgR$\u0002B!\r\u00034\tU\"q\u0007\t\u0006\u0003\u0006e'j\u000f\u0005\u0007\u0003s<\u0003\u0019A\u001e\t\r\u0005ux\u00051\u0001<\u0011\u0019\u0011\ta\na\u0001\u0015\u00069\u0012\tZ1EK2$\u0018m\u0012:bI&,g\u000e\u001e#fg\u000e,g\u000e\u001e\t\u0003q%\u001aB!\u000b@\u0002:R\u0011!1H\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001a\u0016\t\t\u0015#\u0011J\u000b\u0003\u0005\u000fR3ASA\"\t\u0015q4F1\u0001@\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%iU!!Q\tB(\t\u0015qDF1\u0001@\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%kU!!Q\u000bB-+\t\u00119FK\u0002O\u0003\u0007\"QAP\u0017C\u0002}\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"Aa\u0018\u0011\t\u0005u#\u0011M\u0005\u0005\u0005G\nyF\u0001\u0004PE*,7\r\u001e"
)
public class AdaDeltaGradientDescent extends StochasticGradientDescent {
   private volatile History$ History$module;
   private final double rho;
   private final double epsilon;

   public static int $lessinit$greater$default$5() {
      return AdaDeltaGradientDescent$.MODULE$.$lessinit$greater$default$5();
   }

   public static double $lessinit$greater$default$4() {
      return AdaDeltaGradientDescent$.MODULE$.$lessinit$greater$default$4();
   }

   public static double $lessinit$greater$default$3() {
      return AdaDeltaGradientDescent$.MODULE$.$lessinit$greater$default$3();
   }

   public History$ History() {
      if (this.History$module == null) {
         this.History$lzycompute$1();
      }

      return this.History$module;
   }

   public double epsilon() {
      return this.epsilon;
   }

   public History initialHistory(final StochasticDiffFunction f, final Object init) {
      return new History(((MutableFiniteCoordinateField)super.vspace()).zeroLike().apply(init), ((MutableFiniteCoordinateField)super.vspace()).zeroLike().apply(init));
   }

   public History updateHistory(final Object newX, final Object newGrad, final double newVal, final StochasticDiffFunction f, final FirstOrderMinimizer.State oldState) {
      Object oldAvgSqGradient = ((History)oldState.history()).avgSqGradient();
      Object newAvgSqGradient = ((NumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(((ImmutableNumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(oldAvgSqGradient)).$times(BoxesRunTime.boxToDouble(this.rho), ((MutableFiniteCoordinateField)super.vspace()).mulVS_M()))).$plus(((ImmutableNumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(((ImmutableNumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(newGrad)).$times$colon$times(newGrad, ((MutableFiniteCoordinateField)super.vspace()).mulVV()))).$times(BoxesRunTime.boxToDouble((double)1 - this.rho), ((MutableFiniteCoordinateField)super.vspace()).mulVS_M()), ((MutableFiniteCoordinateField)super.vspace()).addVV());
      Object oldAvgSqDelta = ((History)oldState.history()).avgSqDelta();
      Object delta = ((ImmutableNumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(newX)).$minus(oldState.x(), ((MutableFiniteCoordinateField)super.vspace()).subVV());
      Object newAvgSqDelta = ((NumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(((ImmutableNumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(oldAvgSqDelta)).$times(BoxesRunTime.boxToDouble(this.rho), ((MutableFiniteCoordinateField)super.vspace()).mulVS_M()))).$plus(((ImmutableNumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(((ImmutableNumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(delta)).$times$colon$times(delta, ((MutableFiniteCoordinateField)super.vspace()).mulVV()))).$times(BoxesRunTime.boxToDouble((double)1 - this.rho), ((MutableFiniteCoordinateField)super.vspace()).mulVS_M()), ((MutableFiniteCoordinateField)super.vspace()).addVV());
      return new History(newAvgSqGradient, newAvgSqDelta);
   }

   public Object takeStep(final FirstOrderMinimizer.State state, final Object dir, final double stepSize) {
      Object newAvgSqGradient = ((ImmutableNumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(((ImmutableNumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(((History)state.history()).avgSqGradient())).$times(BoxesRunTime.boxToDouble(this.rho), ((MutableFiniteCoordinateField)super.vspace()).mulVS_M()))).$plus$colon$plus(((ImmutableNumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(((ImmutableNumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(state.grad())).$times$colon$times(state.grad(), ((MutableFiniteCoordinateField)super.vspace()).mulVV()))).$times(BoxesRunTime.boxToDouble((double)1 - this.rho), ((MutableFiniteCoordinateField)super.vspace()).mulVS_M()), ((MutableFiniteCoordinateField)super.vspace()).addVV());
      Object rmsGradient = breeze.numerics.package.sqrt$.MODULE$.apply(((NumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(newAvgSqGradient)).$plus(BoxesRunTime.boxToDouble(this.epsilon()), ((MutableFiniteCoordinateField)super.vspace()).addVS()), HasOps$.MODULE$.fromLowOrderCanMapActiveValues(((MutableFiniteCoordinateField)super.vspace()).scalarOf(), package$sqrt$sqrtDoubleImpl$.MODULE$, ((MutableFiniteCoordinateField)super.vspace()).mapValues()));
      Object rmsDelta = breeze.numerics.package.sqrt$.MODULE$.apply(((NumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(((History)state.history()).avgSqDelta())).$plus(BoxesRunTime.boxToDouble(this.epsilon()), ((MutableFiniteCoordinateField)super.vspace()).addVS()), HasOps$.MODULE$.fromLowOrderCanMapActiveValues(((MutableFiniteCoordinateField)super.vspace()).scalarOf(), package$sqrt$sqrtDoubleImpl$.MODULE$, ((MutableFiniteCoordinateField)super.vspace()).mapValues()));
      Object delta = ((ImmutableNumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(((ImmutableNumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(dir)).$times$colon$times(rmsDelta, ((MutableFiniteCoordinateField)super.vspace()).mulVV()))).$div$colon$div(rmsGradient, ((MutableFiniteCoordinateField)super.vspace()).divVV());
      return ((NumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(state.x())).$plus(delta, ((MutableFiniteCoordinateField)super.vspace()).addVV());
   }

   public double determineStepSize(final FirstOrderMinimizer.State state, final StochasticDiffFunction f, final Object dir) {
      return this.defaultStepSize();
   }

   public Tuple2 adjust(final Object newX, final Object newGrad, final double newVal) {
      return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToDouble(newVal)), newGrad);
   }

   private final void History$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.History$module == null) {
            this.History$module = new History$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   public AdaDeltaGradientDescent(final double rho, final int maxIter, final double tolerance, final double improvementTolerance, final int minImprovementWindow, final MutableFiniteCoordinateField vspace, final RandBasis rand) {
      super((double)1.0F, maxIter, tolerance, minImprovementWindow, vspace);
      this.rho = rho;
      this.epsilon = 1.0E-6;
   }

   public class History implements Product, Serializable {
      private final Object avgSqGradient;
      private final Object avgSqDelta;
      // $FF: synthetic field
      public final AdaDeltaGradientDescent $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Object avgSqGradient() {
         return this.avgSqGradient;
      }

      public Object avgSqDelta() {
         return this.avgSqDelta;
      }

      public History copy(final Object avgSqGradient, final Object avgSqDelta) {
         return this.breeze$optimize$AdaDeltaGradientDescent$History$$$outer().new History(avgSqGradient, avgSqDelta);
      }

      public Object copy$default$1() {
         return this.avgSqGradient();
      }

      public Object copy$default$2() {
         return this.avgSqDelta();
      }

      public String productPrefix() {
         return "History";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.avgSqGradient();
               break;
            case 1:
               var10000 = this.avgSqDelta();
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
         return x$1 instanceof History;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "avgSqGradient";
               break;
            case 1:
               var10000 = "avgSqDelta";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label56: {
               boolean var2;
               if (x$1 instanceof History && ((History)x$1).breeze$optimize$AdaDeltaGradientDescent$History$$$outer() == this.breeze$optimize$AdaDeltaGradientDescent$History$$$outer()) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  History var4 = (History)x$1;
                  if (BoxesRunTime.equals(this.avgSqGradient(), var4.avgSqGradient()) && BoxesRunTime.equals(this.avgSqDelta(), var4.avgSqDelta()) && var4.canEqual(this)) {
                     break label56;
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
      public AdaDeltaGradientDescent breeze$optimize$AdaDeltaGradientDescent$History$$$outer() {
         return this.$outer;
      }

      public History(final Object avgSqGradient, final Object avgSqDelta) {
         this.avgSqGradient = avgSqGradient;
         this.avgSqDelta = avgSqDelta;
         if (AdaDeltaGradientDescent.this == null) {
            throw null;
         } else {
            this.$outer = AdaDeltaGradientDescent.this;
            super();
            Product.$init$(this);
         }
      }
   }

   public class History$ extends AbstractFunction2 implements Serializable {
      // $FF: synthetic field
      private final AdaDeltaGradientDescent $outer;

      public final String toString() {
         return "History";
      }

      public History apply(final Object avgSqGradient, final Object avgSqDelta) {
         return this.$outer.new History(avgSqGradient, avgSqDelta);
      }

      public Option unapply(final History x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.avgSqGradient(), x$0.avgSqDelta())));
      }

      public History$() {
         if (AdaDeltaGradientDescent.this == null) {
            throw null;
         } else {
            this.$outer = AdaDeltaGradientDescent.this;
            super();
         }
      }
   }
}
