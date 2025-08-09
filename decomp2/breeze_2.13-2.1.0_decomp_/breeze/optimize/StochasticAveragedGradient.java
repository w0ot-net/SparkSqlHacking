package breeze.optimize;

import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.norm$;
import breeze.math.MutableInnerProductModule;
import breeze.stats.distributions.Rand$;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple5;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction5;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tee\u0001B\u001c9\u0001uB\u0001\u0002\u0016\u0001\u0003\u0002\u0003\u0006I!\u0016\u0005\t1\u0002\u0011\t\u0011)A\u00053\"AA\f\u0001B\u0001B\u0003%Q\u000b\u0003\u0005^\u0001\t\u0005\t\u0015!\u0003Z\u0011!q\u0006A!A!\u0002\u0017y\u0006\"B3\u0001\t\u00031g\u0001\u00028\u0001\u0001>D!\"!\u0002\b\u0005+\u0007I\u0011AA\u0004\u0011%\tIa\u0002B\tB\u0003%\u0011\f\u0003\u0006\u0002\f\u001d\u0011)\u001a!C\u0001\u0003\u001bA!\"!\u0006\b\u0005#\u0005\u000b\u0011BA\b\u0011)\t9b\u0002BK\u0002\u0013\u0005\u0011\u0011\u0004\u0005\n\u000379!\u0011#Q\u0001\n\rC!\"!\b\b\u0005+\u0007I\u0011AA\u0010\u0011)\t\u0019c\u0002B\tB\u0003%\u0011\u0011\u0005\u0005\u000b\u0003K9!Q3A\u0005\u0002\u0005\u001d\u0002\"CA\u0015\u000f\tE\t\u0015!\u0003V\u0011\u0019)w\u0001\"\u0001\u0002,!I\u00111H\u0004\u0002\u0002\u0013\u0005\u0011Q\b\u0005\n\u0003\u0013:\u0011\u0013!C\u0001\u0003\u0017B\u0011\"!\u0019\b#\u0003%\t!a\u0019\t\u0013\u0005\u001dt!%A\u0005\u0002\u0005%\u0004\"CA7\u000fE\u0005I\u0011AA8\u0011%\t\u0019hBI\u0001\n\u0003\t)\bC\u0005\u0002z\u001d\t\t\u0011\"\u0011\u0002|!I\u0011QR\u0004\u0002\u0002\u0013\u0005\u0011q\u0005\u0005\n\u0003\u001f;\u0011\u0011!C\u0001\u0003#C\u0011\"a&\b\u0003\u0003%\t%!'\t\u0013\u0005\u001dv!!A\u0005\u0002\u0005%\u0006\"CAZ\u000f\u0005\u0005I\u0011IA[\u0011%\tIlBA\u0001\n\u0003\nY\fC\u0005\u0002>\u001e\t\t\u0011\"\u0011\u0002@\"I\u0011\u0011Y\u0004\u0002\u0002\u0013\u0005\u00131Y\u0004\n\u0003\u000f\u0004\u0011\u0011!E\u0001\u0003\u00134\u0001B\u001c\u0001\u0002\u0002#\u0005\u00111\u001a\u0005\u0007K\u000e\"\t!a9\t\u0013\u0005u6%!A\u0005F\u0005}\u0006\"CAsG\u0005\u0005I\u0011QAt\u0011%\t\u0019pIA\u0001\n\u0003\u000b)\u0010C\u0004\u0003\b\u0001!\tB!\u0003\t\u000f\tM\u0001\u0001\"\u0005\u0003\u0016!9!1\u0005\u0001\u0005\u0012\t\u0015\u0002b\u0002B\u0018\u0001\u0011E#\u0011\u0007\u0005\b\u0005\u0007\u0002A\u0011\u000bB#\u0011\u001d\u0011\u0019\u0006\u0001C\t\u0005+BqAa\u0018\u0001\t#\u0011\tgB\u0005\u0003pa\n\t\u0011#\u0001\u0003r\u0019Aq\u0007OA\u0001\u0012\u0003\u0011\u0019\b\u0003\u0004fa\u0011\u0005!Q\u000f\u0005\n\u0005o\u0002\u0014\u0013!C\u0001\u0005sB\u0011B! 1#\u0003%\tAa \t\u0013\t\r\u0005'%A\u0005\u0002\t\u0015\u0005\"\u0003BEaE\u0005I\u0011\u0001BF\u0011%\u0011y\tMA\u0001\n\u0013\u0011\tJ\u0001\u000eTi>\u001c\u0007.Y:uS\u000e\fe/\u001a:bO\u0016$wI]1eS\u0016tGO\u0003\u0002:u\u0005Aq\u000e\u001d;j[&TXMC\u0001<\u0003\u0019\u0011'/Z3{K\u000e\u0001QC\u0001 F'\t\u0001q\b\u0005\u0003A\u0003\u000e\u000bV\"\u0001\u001d\n\u0005\tC$a\u0005$jeN$xJ\u001d3fe6Kg.[7ju\u0016\u0014\bC\u0001#F\u0019\u0001!QA\u0012\u0001C\u0002\u001d\u0013\u0011\u0001V\t\u0003\u0011:\u0003\"!\u0013'\u000e\u0003)S\u0011aS\u0001\u0006g\u000e\fG.Y\u0005\u0003\u001b*\u0013qAT8uQ&tw\r\u0005\u0002J\u001f&\u0011\u0001K\u0013\u0002\u0004\u0003:L\bc\u0001!S\u0007&\u00111\u000b\u000f\u0002\u0012\u0005\u0006$8\r\u001b#jM\u001a4UO\\2uS>t\u0017aB7bq&#XM\u001d\t\u0003\u0013ZK!a\u0016&\u0003\u0007%sG/A\bj]&$\u0018.\u00197Ti\u0016\u00048+\u001b>f!\tI%,\u0003\u0002\\\u0015\n1Ai\\;cY\u0016\f\u0011\u0003^;oKN#X\r\u001d$sKF,XM\\2z\u0003Aa'GU3hk2\f'/\u001b>bi&|g.\u0001\u0002wgB!\u0001mY\"Z\u001b\u0005\t'B\u00012;\u0003\u0011i\u0017\r\u001e5\n\u0005\u0011\f'!G'vi\u0006\u0014G.Z%o]\u0016\u0014\bK]8ek\u000e$Xj\u001c3vY\u0016\fa\u0001P5oSRtD#B4kW2lGC\u00015j!\r\u0001\u0005a\u0011\u0005\u0006=\u001a\u0001\u001da\u0018\u0005\b)\u001a\u0001\n\u00111\u0001V\u0011\u001dAf\u0001%AA\u0002eCq\u0001\u0018\u0004\u0011\u0002\u0003\u0007Q\u000bC\u0004^\rA\u0005\t\u0019A-\u0003\u000f!K7\u000f^8ssN!q\u0001]:w!\tI\u0015/\u0003\u0002s\u0015\n1\u0011I\\=SK\u001a\u0004\"!\u0013;\n\u0005UT%a\u0002)s_\u0012,8\r\u001e\t\u0003o~t!\u0001_?\u000f\u0005edX\"\u0001>\u000b\u0005md\u0014A\u0002\u001fs_>$h(C\u0001L\u0013\tq(*A\u0004qC\u000e\\\u0017mZ3\n\t\u0005\u0005\u00111\u0001\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003}*\u000b\u0001b\u001d;faNK'0Z\u000b\u00023\u0006I1\u000f^3q'&TX\rI\u0001\u0006e\u0006tw-Z\u000b\u0003\u0003\u001f\u0001Ba^A\t+&!\u00111CA\u0002\u0005)Ie\u000eZ3yK\u0012\u001cV-]\u0001\u0007e\u0006tw-\u001a\u0011\u0002\u0015\r,(O]3oiN+X.F\u0001D\u0003-\u0019WO\u001d:f]R\u001cV/\u001c\u0011\u0002#A\u0014XM^5pkN<%/\u00193jK:$8/\u0006\u0002\u0002\"A!q/!\u0005D\u0003I\u0001(/\u001a<j_V\u001cxI]1eS\u0016tGo\u001d\u0011\u0002\u000f9,\u0007\u0010\u001e)pgV\tQ+\u0001\u0005oKb$\bk\\:!)1\ti#!\r\u00024\u0005U\u0012qGA\u001d!\r\tycB\u0007\u0002\u0001!1\u0011Q\u0001\nA\u0002eCq!a\u0003\u0013\u0001\u0004\ty\u0001\u0003\u0004\u0002\u0018I\u0001\ra\u0011\u0005\b\u0003;\u0011\u0002\u0019AA\u0011\u0011\u0019\t)C\u0005a\u0001+\u0006!1m\u001c9z)1\ti#a\u0010\u0002B\u0005\r\u0013QIA$\u0011!\t)a\u0005I\u0001\u0002\u0004I\u0006\"CA\u0006'A\u0005\t\u0019AA\b\u0011!\t9b\u0005I\u0001\u0002\u0004\u0019\u0005\"CA\u000f'A\u0005\t\u0019AA\u0011\u0011!\t)c\u0005I\u0001\u0002\u0004)\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0003\u001bR3!WA(W\t\t\t\u0006\u0005\u0003\u0002T\u0005uSBAA+\u0015\u0011\t9&!\u0017\u0002\u0013Ut7\r[3dW\u0016$'bAA.\u0015\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005}\u0013Q\u000b\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u0003KRC!a\u0004\u0002P\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u001aTCAA6U\r\u0019\u0015qJ\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00135+\t\t\tH\u000b\u0003\u0002\"\u0005=\u0013AD2paf$C-\u001a4bk2$H%N\u000b\u0003\u0003oR3!VA(\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011Q\u0010\t\u0005\u0003\u007f\nI)\u0004\u0002\u0002\u0002*!\u00111QAC\u0003\u0011a\u0017M\\4\u000b\u0005\u0005\u001d\u0015\u0001\u00026bm\u0006LA!a#\u0002\u0002\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000fF\u0002O\u0003'C\u0001\"!&\u001c\u0003\u0003\u0005\r!V\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005m\u0005#BAO\u0003GsUBAAP\u0015\r\t\tKS\u0001\u000bG>dG.Z2uS>t\u0017\u0002BAS\u0003?\u0013\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u00111VAY!\rI\u0015QV\u0005\u0004\u0003_S%a\u0002\"p_2,\u0017M\u001c\u0005\t\u0003+k\u0012\u0011!a\u0001\u001d\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\ti(a.\t\u0011\u0005Ue$!AA\u0002U\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002+\u0006AAo\\*ue&tw\r\u0006\u0002\u0002~\u00051Q-];bYN$B!a+\u0002F\"A\u0011QS\u0011\u0002\u0002\u0003\u0007a*A\u0004ISN$xN]=\u0011\u0007\u0005=2eE\u0003$\u0003\u001b\fI\u000eE\u0007\u0002P\u0006U\u0017,a\u0004D\u0003C)\u0016QF\u0007\u0003\u0003#T1!a5K\u0003\u001d\u0011XO\u001c;j[\u0016LA!a6\u0002R\n\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u001b\u0011\t\u0005m\u0017\u0011]\u0007\u0003\u0003;TA!a8\u0002\u0006\u0006\u0011\u0011n\\\u0005\u0005\u0003\u0003\ti\u000e\u0006\u0002\u0002J\u0006)\u0011\r\u001d9msRa\u0011QFAu\u0003W\fi/a<\u0002r\"1\u0011Q\u0001\u0014A\u0002eCq!a\u0003'\u0001\u0004\ty\u0001\u0003\u0004\u0002\u0018\u0019\u0002\ra\u0011\u0005\b\u0003;1\u0003\u0019AA\u0011\u0011\u0019\t)C\na\u0001+\u00069QO\\1qa2LH\u0003BA|\u0005\u0007\u0001R!SA}\u0003{L1!a?K\u0005\u0019y\u0005\u000f^5p]BQ\u0011*a@Z\u0003\u001f\u0019\u0015\u0011E+\n\u0007\t\u0005!J\u0001\u0004UkBdW-\u000e\u0005\n\u0005\u000b9\u0013\u0011!a\u0001\u0003[\t1\u0001\u001f\u00131\u00039Ig.\u001b;jC2D\u0015n\u001d;pef$b!!\f\u0003\f\t=\u0001B\u0002B\u0007Q\u0001\u0007\u0011+A\u0001g\u0011\u0019\u0011\t\u0002\u000ba\u0001\u0007\u0006!\u0011N\\5u\u0003Y\u0019\u0007n\\8tK\u0012+7oY3oi\u0012K'/Z2uS>tG#B\"\u0003\u0018\t\u0005\u0002b\u0002B\rS\u0001\u0007!1D\u0001\u0006gR\fG/\u001a\t\u0005\u0003_\u0011i\"C\u0002\u0003 \u0005\u0013Qa\u0015;bi\u0016DaA!\u0004*\u0001\u0004\t\u0016!\u00053fi\u0016\u0014X.\u001b8f'R,\u0007oU5{KR9\u0011La\n\u0003*\t-\u0002b\u0002B\rU\u0001\u0007!1\u0004\u0005\u0007\u0005\u001bQ\u0003\u0019A)\t\r\t5\"\u00061\u0001D\u0003%!\u0017N]3di&|g.\u0001\ndC2\u001cW\u000f\\1uK>\u0013'.Z2uSZ,G\u0003\u0003B\u001a\u0005s\u0011YDa\u0010\u0011\u000b%\u0013)$W\"\n\u0007\t]\"J\u0001\u0004UkBdWM\r\u0005\u0007\u0005\u001bY\u0003\u0019A)\t\r\tu2\u00061\u0001D\u0003\u0005A\bb\u0002B!W\u0001\u0007\u0011QF\u0001\bQ&\u001cHo\u001c:z\u0003\u0019\tGM[;tiRA!1\u0007B$\u0005\u0017\u0012y\u0005\u0003\u0004\u0003J1\u0002\raQ\u0001\u0005]\u0016<\b\f\u0003\u0004\u0003N1\u0002\raQ\u0001\b]\u0016<xI]1e\u0011\u0019\u0011\t\u0006\fa\u00013\u00061a.Z<WC2\f\u0001\u0002^1lKN#X\r\u001d\u000b\b\u0007\n]#\u0011\fB/\u0011\u001d\u0011I\"\fa\u0001\u00057AaAa\u0017.\u0001\u0004\u0019\u0015a\u00013je\"1\u0011QA\u0017A\u0002e\u000bQ\"\u001e9eCR,\u0007*[:u_JLH\u0003DA\u0017\u0005G\u0012)Ga\u001a\u0003j\t-\u0004B\u0002B%]\u0001\u00071\t\u0003\u0004\u0003N9\u0002\ra\u0011\u0005\u0007\u0005#r\u0003\u0019A-\t\r\t5a\u00061\u0001R\u0011\u001d\u0011iG\fa\u0001\u00057\t\u0001b\u001c7e'R\fG/Z\u0001\u001b'R|7\r[1ti&\u001c\u0017I^3sC\u001e,Gm\u0012:bI&,g\u000e\u001e\t\u0003\u0001B\u001aB\u0001\r9\u0002ZR\u0011!\u0011O\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u0019\u0016\t\u0005U$1\u0010\u0003\u0006\rJ\u0012\raR\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001a\u0016\t\u0005-#\u0011\u0011\u0003\u0006\rN\u0012\raR\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001a\u0016\t\u0005U$q\u0011\u0003\u0006\rR\u0012\raR\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001b\u0016\t\u0005-#Q\u0012\u0003\u0006\rV\u0012\raR\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005'\u0003B!a \u0003\u0016&!!qSAA\u0005\u0019y%M[3di\u0002"
)
public class StochasticAveragedGradient extends FirstOrderMinimizer {
   private volatile History$ History$module;
   private final double initialStepSize;
   private final int tuneStepFrequency;
   private final double l2Regularization;
   private final MutableInnerProductModule vs;

   public static double $lessinit$greater$default$4() {
      return StochasticAveragedGradient$.MODULE$.$lessinit$greater$default$4();
   }

   public static int $lessinit$greater$default$3() {
      return StochasticAveragedGradient$.MODULE$.$lessinit$greater$default$3();
   }

   public static double $lessinit$greater$default$2() {
      return StochasticAveragedGradient$.MODULE$.$lessinit$greater$default$2();
   }

   public static int $lessinit$greater$default$1() {
      return StochasticAveragedGradient$.MODULE$.$lessinit$greater$default$1();
   }

   public History$ History() {
      if (this.History$module == null) {
         this.History$lzycompute$1();
      }

      return this.History$module;
   }

   public History initialHistory(final BatchDiffFunction f, final Object init) {
      Object zero = this.vs.zeroLike().apply(init);
      return new History(this.initialStepSize, f.fullRange(), this.vs.zeroLike().apply(init), (IndexedSeq).MODULE$.IndexedSeq().fill(f.fullRange().length(), () -> zero), 0);
   }

   public Object chooseDescentDirection(final FirstOrderMinimizer.State state, final BatchDiffFunction f) {
      return ((ImmutableNumericOps)this.vs.hasOps().apply(((History)state.history()).currentSum())).$times(BoxesRunTime.boxToDouble((double)-1.0F / (double)f.fullRange().size()), this.vs.mulVS_M());
   }

   public double determineStepSize(final FirstOrderMinimizer.State state, final BatchDiffFunction f, final Object direction) {
      return ((History)state.history()).stepSize();
   }

   public Tuple2 calculateObjective(final BatchDiffFunction f, final Object x, final History history) {
      return f.calculate(x, (IndexedSeq).MODULE$.IndexedSeq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{history.nextPos()})));
   }

   public Tuple2 adjust(final Object newX, final Object newGrad, final double newVal) {
      double av = newVal + BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.vs.hasOps().apply(newX)).dot(newX, this.vs.dotVV())) * this.l2Regularization / (double)2.0F;
      Object ag = ((NumericOps)this.vs.hasOps().apply(newGrad)).$plus(((ImmutableNumericOps)this.vs.hasOps().apply(newX)).$times(BoxesRunTime.boxToDouble(this.l2Regularization), this.vs.mulVS_M()), this.vs.addVV());
      return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToDouble(av)), ag);
   }

   public Object takeStep(final FirstOrderMinimizer.State state, final Object dir, final double stepSize) {
      Object newx = ((ImmutableNumericOps)this.vs.hasOps().apply(state.x())).$times(BoxesRunTime.boxToDouble((double)1 - stepSize * this.l2Regularization), this.vs.mulVS_M());
      breeze.linalg.package$.MODULE$.axpy(BoxesRunTime.boxToDouble(stepSize), dir, newx, this.vs.scaleAddVV());
      return newx;
   }

   public History updateHistory(final Object newX, final Object newGrad, final double newVal, final BatchDiffFunction f, final FirstOrderMinimizer.State oldState) {
      Object d = ((ImmutableNumericOps)this.vs.hasOps().apply(((History)oldState.history()).currentSum())).$minus(((History)oldState.history()).previousGradients().apply(((History)oldState.history()).nextPos()), this.vs.subVV());
      double var10000;
      if (this.tuneStepFrequency > 0 && oldState.iter() % this.tuneStepFrequency == 0) {
         Object xdiff = ((ImmutableNumericOps)this.vs.hasOps().apply(newX)).$minus(oldState.x(), this.vs.subVV());
         var10000 = f.valueAt(newX, (IndexedSeq).MODULE$.IndexedSeq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{((History)oldState.history()).nextPos()}))) + this.l2Regularization / (double)2 * BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(newX, this.vs.normImpl())) - oldState.adjustedValue() > BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.vs.hasOps().apply(oldState.adjustedGradient())).dot(xdiff, this.vs.dotVV())) + BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.vs.hasOps().apply(xdiff)).dot(xdiff, this.vs.dotVV())) / ((double)2 * ((History)oldState.history()).stepSize()) ? ((History)oldState.history()).stepSize() / (double)2 : ((History)oldState.history()).stepSize() * (double)1.5F;
      } else {
         var10000 = ((History)oldState.history()).stepSize();
      }

      double newStepSize = var10000;
      ((NumericOps)this.vs.hasOps().apply(d)).$plus$eq(newGrad, this.vs.addIntoVV());
      return new History(newStepSize, ((History)oldState.history()).range(), d, (IndexedSeq)((History)oldState.history()).previousGradients().updated(((History)oldState.history()).nextPos(), newGrad), oldState.iter() < ((History)oldState.history()).previousGradients().length() - 1 ? oldState.iter() + 1 : Rand$.MODULE$.choose(((History)oldState.history()).range()).draw$mcI$sp());
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

   public StochasticAveragedGradient(final int maxIter, final double initialStepSize, final int tuneStepFrequency, final double l2Regularization, final MutableInnerProductModule vs) {
      super(maxIter, FirstOrderMinimizer$.MODULE$.$lessinit$greater$default$2(), FirstOrderMinimizer$.MODULE$.$lessinit$greater$default$3(), FirstOrderMinimizer$.MODULE$.$lessinit$greater$default$4(), vs);
      this.initialStepSize = initialStepSize;
      this.tuneStepFrequency = tuneStepFrequency;
      this.l2Regularization = l2Regularization;
      this.vs = vs;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public class History implements Product, Serializable {
      private final double stepSize;
      private final IndexedSeq range;
      private final Object currentSum;
      private final IndexedSeq previousGradients;
      private final int nextPos;
      // $FF: synthetic field
      public final StochasticAveragedGradient $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public double stepSize() {
         return this.stepSize;
      }

      public IndexedSeq range() {
         return this.range;
      }

      public Object currentSum() {
         return this.currentSum;
      }

      public IndexedSeq previousGradients() {
         return this.previousGradients;
      }

      public int nextPos() {
         return this.nextPos;
      }

      public History copy(final double stepSize, final IndexedSeq range, final Object currentSum, final IndexedSeq previousGradients, final int nextPos) {
         return this.breeze$optimize$StochasticAveragedGradient$History$$$outer().new History(stepSize, range, currentSum, previousGradients, nextPos);
      }

      public double copy$default$1() {
         return this.stepSize();
      }

      public IndexedSeq copy$default$2() {
         return this.range();
      }

      public Object copy$default$3() {
         return this.currentSum();
      }

      public IndexedSeq copy$default$4() {
         return this.previousGradients();
      }

      public int copy$default$5() {
         return this.nextPos();
      }

      public String productPrefix() {
         return "History";
      }

      public int productArity() {
         return 5;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToDouble(this.stepSize());
               break;
            case 1:
               var10000 = this.range();
               break;
            case 2:
               var10000 = this.currentSum();
               break;
            case 3:
               var10000 = this.previousGradients();
               break;
            case 4:
               var10000 = BoxesRunTime.boxToInteger(this.nextPos());
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
               var10000 = "stepSize";
               break;
            case 1:
               var10000 = "range";
               break;
            case 2:
               var10000 = "currentSum";
               break;
            case 3:
               var10000 = "previousGradients";
               break;
            case 4:
               var10000 = "nextPos";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.doubleHash(this.stepSize()));
         var1 = Statics.mix(var1, Statics.anyHash(this.range()));
         var1 = Statics.mix(var1, Statics.anyHash(this.currentSum()));
         var1 = Statics.mix(var1, Statics.anyHash(this.previousGradients()));
         var1 = Statics.mix(var1, this.nextPos());
         return Statics.finalizeHash(var1, 5);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var9;
         if (this != x$1) {
            label73: {
               boolean var2;
               if (x$1 instanceof History && ((History)x$1).breeze$optimize$StochasticAveragedGradient$History$$$outer() == this.breeze$optimize$StochasticAveragedGradient$History$$$outer()) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label50: {
                     History var4 = (History)x$1;
                     if (this.stepSize() == var4.stepSize() && this.nextPos() == var4.nextPos()) {
                        label47: {
                           IndexedSeq var10000 = this.range();
                           IndexedSeq var5 = var4.range();
                           if (var10000 == null) {
                              if (var5 != null) {
                                 break label47;
                              }
                           } else if (!var10000.equals(var5)) {
                              break label47;
                           }

                           if (BoxesRunTime.equals(this.currentSum(), var4.currentSum())) {
                              label41: {
                                 var10000 = this.previousGradients();
                                 IndexedSeq var6 = var4.previousGradients();
                                 if (var10000 == null) {
                                    if (var6 != null) {
                                       break label41;
                                    }
                                 } else if (!var10000.equals(var6)) {
                                    break label41;
                                 }

                                 if (var4.canEqual(this)) {
                                    var9 = true;
                                    break label50;
                                 }
                              }
                           }
                        }
                     }

                     var9 = false;
                  }

                  if (var9) {
                     break label73;
                  }
               }

               var9 = false;
               return var9;
            }
         }

         var9 = true;
         return var9;
      }

      // $FF: synthetic method
      public StochasticAveragedGradient breeze$optimize$StochasticAveragedGradient$History$$$outer() {
         return this.$outer;
      }

      public History(final double stepSize, final IndexedSeq range, final Object currentSum, final IndexedSeq previousGradients, final int nextPos) {
         this.stepSize = stepSize;
         this.range = range;
         this.currentSum = currentSum;
         this.previousGradients = previousGradients;
         this.nextPos = nextPos;
         if (StochasticAveragedGradient.this == null) {
            throw null;
         } else {
            this.$outer = StochasticAveragedGradient.this;
            super();
            Product.$init$(this);
         }
      }
   }

   public class History$ extends AbstractFunction5 implements Serializable {
      // $FF: synthetic field
      private final StochasticAveragedGradient $outer;

      public final String toString() {
         return "History";
      }

      public History apply(final double stepSize, final IndexedSeq range, final Object currentSum, final IndexedSeq previousGradients, final int nextPos) {
         return this.$outer.new History(stepSize, range, currentSum, previousGradients, nextPos);
      }

      public Option unapply(final History x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple5(BoxesRunTime.boxToDouble(x$0.stepSize()), x$0.range(), x$0.currentSum(), x$0.previousGradients(), BoxesRunTime.boxToInteger(x$0.nextPos()))));
      }

      public History$() {
         if (StochasticAveragedGradient.this == null) {
            throw null;
         } else {
            this.$outer = StochasticAveragedGradient.this;
            super();
         }
      }
   }
}
