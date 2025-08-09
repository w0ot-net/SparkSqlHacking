package org.apache.spark.mllib.optimization;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.optimize.DiffFunction;
import breeze.optimize.StochasticDiffFunction;
import breeze.util.Isomorphism;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.mllib.linalg.BLAS$;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.rdd.RDD;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eh\u0001B\u0017/\u0001eB\u0001B\u0013\u0001\u0003\u0002\u0004%Ia\u0013\u0005\t\u001f\u0002\u0011\t\u0019!C\u0005!\"Aa\u000b\u0001B\u0001B\u0003&A\n\u0003\u0005X\u0001\t\u0005\r\u0011\"\u0003Y\u0011!a\u0006A!a\u0001\n\u0013i\u0006\u0002C0\u0001\u0005\u0003\u0005\u000b\u0015B-\t\u000b\u0001\u0004A\u0011A1\t\u000f\u0015\u0004\u0001\u0019!C\u0005M\"9!\u000e\u0001a\u0001\n\u0013Y\u0007BB7\u0001A\u0003&q\rC\u0004o\u0001\u0001\u0007I\u0011B8\t\u000fM\u0004\u0001\u0019!C\u0005i\"1a\u000f\u0001Q!\nADqa\u001e\u0001A\u0002\u0013%a\rC\u0004y\u0001\u0001\u0007I\u0011B=\t\rm\u0004\u0001\u0015)\u0003h\u0011\u001da\b\u00011A\u0005\n=Dq! \u0001A\u0002\u0013%a\u0010C\u0004\u0002\u0002\u0001\u0001\u000b\u0015\u00029\t\u000f\u0005\r\u0001\u0001\"\u0001\u0002\u0006!9\u0011Q\u0002\u0001\u0005\u0002\u0005=\u0001\u0002CA\u000b\u0001\u0011\u0005\u0001'a\u0006\t\u000f\u0005e\u0001\u0001\"\u0001\u0002\u001c!A\u0011\u0011\u0005\u0001\u0005\u0002A\n\u0019\u0003C\u0004\u0002&\u0001!\t!a\n\t\u0011\u0005-\u0002\u0001\"\u00011\u0003/Aq!!\f\u0001\t\u0003\ty\u0003C\u0004\u00024\u0001!\t!!\u000e\t\u0011\u0005e\u0002\u0001\"\u00011\u0003wAq!!\u0010\u0001\t\u0003\ny\u0004C\u0004\u0002h\u0001!\t!!\u001b\b\u000f\u0005]d\u0006#\u0001\u0002z\u00191QF\fE\u0001\u0003wBa\u0001Y\u0011\u0005\u0002\u00055\u0005bBAHC\u0011\u0005\u0011\u0011\u0013\u0004\u0007\u0003G\u000bC!!*\t\u0015\u0005=CE!A!\u0002\u0013\t\t\u0006\u0003\u0005KI\t\u0005\t\u0015!\u0003M\u0011!9FE!A!\u0002\u0013I\u0006\u0002\u0003?%\u0005\u0003\u0005\u000b\u0011\u00029\t\u0015\u0005}FE!A!\u0002\u0013\t\t\r\u0003\u0004aI\u0011\u0005\u0011q\u0019\u0005\b\u0003/$C\u0011IAm\u0011%\t\t/IA\u0001\n\u0013\t\u0019OA\u0003M\u0005\u001a;5K\u0003\u00020a\u0005aq\u000e\u001d;j[&T\u0018\r^5p]*\u0011\u0011GM\u0001\u0006[2d\u0017N\u0019\u0006\u0003gQ\nQa\u001d9be.T!!\u000e\u001c\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00059\u0014aA8sO\u000e\u00011\u0003\u0002\u0001;\u0001\u0012\u0003\"a\u000f \u000e\u0003qR\u0011!P\u0001\u0006g\u000e\fG.Y\u0005\u0003\u007fq\u0012a!\u00118z%\u00164\u0007CA!C\u001b\u0005q\u0013BA\"/\u0005%y\u0005\u000f^5nSj,'\u000f\u0005\u0002F\u00116\taI\u0003\u0002He\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002J\r\n9Aj\\4hS:<\u0017\u0001C4sC\u0012LWM\u001c;\u0016\u00031\u0003\"!Q'\n\u00059s#\u0001C$sC\u0012LWM\u001c;\u0002\u0019\u001d\u0014\u0018\rZ5f]R|F%Z9\u0015\u0005E#\u0006CA\u001eS\u0013\t\u0019FH\u0001\u0003V]&$\bbB+\u0003\u0003\u0003\u0005\r\u0001T\u0001\u0004q\u0012\n\u0014!C4sC\u0012LWM\u001c;!\u0003\u001d)\b\u000fZ1uKJ,\u0012!\u0017\t\u0003\u0003jK!a\u0017\u0018\u0003\u000fU\u0003H-\u0019;fe\u0006YQ\u000f\u001d3bi\u0016\u0014x\fJ3r)\t\tf\fC\u0004V\u000b\u0005\u0005\t\u0019A-\u0002\u0011U\u0004H-\u0019;fe\u0002\na\u0001P5oSRtDc\u00012dIB\u0011\u0011\t\u0001\u0005\u0006\u0015\u001e\u0001\r\u0001\u0014\u0005\u0006/\u001e\u0001\r!W\u0001\u000f]Vl7i\u001c:sK\u000e$\u0018n\u001c8t+\u00059\u0007CA\u001ei\u0013\tIGHA\u0002J]R\f!C\\;n\u0007>\u0014(/Z2uS>t7o\u0018\u0013fcR\u0011\u0011\u000b\u001c\u0005\b+&\t\t\u00111\u0001h\u0003=qW/\\\"peJ,7\r^5p]N\u0004\u0013AD2p]Z,'oZ3oG\u0016$v\u000e\\\u000b\u0002aB\u00111(]\u0005\u0003er\u0012a\u0001R8vE2,\u0017AE2p]Z,'oZ3oG\u0016$v\u000e\\0%KF$\"!U;\t\u000fUc\u0011\u0011!a\u0001a\u0006y1m\u001c8wKJ<WM\\2f)>d\u0007%\u0001\tnCbtU/\\%uKJ\fG/[8og\u0006!R.\u0019=Ok6LE/\u001a:bi&|gn]0%KF$\"!\u0015>\t\u000fU{\u0011\u0011!a\u0001O\u0006\tR.\u0019=Ok6LE/\u001a:bi&|gn\u001d\u0011\u0002\u0011I,w\rU1sC6\fAB]3h!\u0006\u0014\u0018-\\0%KF$\"!U@\t\u000fU\u0013\u0012\u0011!a\u0001a\u0006I!/Z4QCJ\fW\u000eI\u0001\u0012g\u0016$h*^7D_J\u0014Xm\u0019;j_:\u001cH\u0003BA\u0004\u0003\u0013i\u0011\u0001\u0001\u0005\u0007\u0003\u0017!\u0002\u0019A4\u0002\u0017\r|'O]3di&|gn]\u0001\u0012g\u0016$8i\u001c8wKJ<WM\\2f)>dG\u0003BA\u0004\u0003#Aa!a\u0005\u0016\u0001\u0004\u0001\u0018!\u0003;pY\u0016\u0014\u0018M\\2f\u0003E9W\r^\"p]Z,'oZ3oG\u0016$v\u000e\u001c\u000b\u0002a\u0006\u00012/\u001a;Ok6LE/\u001a:bi&|gn\u001d\u000b\u0005\u0003\u000f\ti\u0002\u0003\u0004\u0002 ]\u0001\raZ\u0001\u0006SR,'o]\u0001\u0011O\u0016$h*^7Ji\u0016\u0014\u0018\r^5p]N$\u0012aZ\u0001\fg\u0016$(+Z4QCJ\fW\u000e\u0006\u0003\u0002\b\u0005%\u0002\"\u0002?\u001a\u0001\u0004\u0001\u0018aC4fiJ+w\rU1sC6\f1b]3u\u000fJ\fG-[3oiR!\u0011qAA\u0019\u0011\u0015Q5\u00041\u0001M\u0003)\u0019X\r^+qI\u0006$XM\u001d\u000b\u0005\u0003\u000f\t9\u0004C\u0003X9\u0001\u0007\u0011,\u0001\u0006hKR,\u0006\u000fZ1uKJ$\u0012!W\u0001\t_B$\u0018.\\5{KR1\u0011\u0011IA'\u0003G\u0002B!a\u0011\u0002J5\u0011\u0011Q\t\u0006\u0004\u0003\u000f\u0002\u0014A\u00027j]\u0006dw-\u0003\u0003\u0002L\u0005\u0015#A\u0002,fGR|'\u000fC\u0004\u0002Py\u0001\r!!\u0015\u0002\t\u0011\fG/\u0019\t\u0007\u0003'\nI&!\u0018\u000e\u0005\u0005U#bAA,e\u0005\u0019!\u000f\u001a3\n\t\u0005m\u0013Q\u000b\u0002\u0004%\u0012#\u0005CB\u001e\u0002`A\f\t%C\u0002\u0002bq\u0012a\u0001V;qY\u0016\u0014\u0004bBA3=\u0001\u0007\u0011\u0011I\u0001\u000fS:LG/[1m/\u0016Lw\r\u001b;t\u0003ay\u0007\u000f^5nSj,w+\u001b;i\u0019>\u001c8OU3ukJtW\r\u001a\u000b\u0007\u0003W\n\u0019(!\u001e\u0011\u000fm\ny&!\u0011\u0002nA!1(a\u001cq\u0013\r\t\t\b\u0010\u0002\u0006\u0003J\u0014\u0018-\u001f\u0005\b\u0003\u001fz\u0002\u0019AA)\u0011\u001d\t)g\ba\u0001\u0003\u0003\nQ\u0001\u0014\"G\u000fN\u0003\"!Q\u0011\u0014\u000b\u0005RD)! \u0011\t\u0005}\u0014\u0011R\u0007\u0003\u0003\u0003SA!a!\u0002\u0006\u0006\u0011\u0011n\u001c\u0006\u0003\u0003\u000f\u000bAA[1wC&!\u00111RAA\u00051\u0019VM]5bY&T\u0018M\u00197f)\t\tI(\u0001\u0005sk:d%IR$T)I\tY'a%\u0002\u0016\u0006]\u0015\u0011TAN\u0003;\u000by*!)\t\u000f\u0005=3\u00051\u0001\u0002R!)!j\ta\u0001\u0019\")qk\ta\u00013\")Qm\ta\u0001O\")an\ta\u0001a\")qo\ta\u0001O\")Ap\ta\u0001a\"9\u0011QM\u0012A\u0002\u0005\u0005#aB\"pgR4UO\\\n\u0005Ii\n9\u000b\u0005\u0004\u0002*\u0006E\u0016QW\u0007\u0003\u0003WSA!!\u0010\u0002.*\u0011\u0011qV\u0001\u0007EJ,WM_3\n\t\u0005M\u00161\u0016\u0002\r\t&4gMR;oGRLwN\u001c\t\u0006\u0003o\u000bY\f]\u0007\u0003\u0003sSA!a\u0012\u0002.&!\u0011QXA]\u0005-!UM\\:f-\u0016\u001cGo\u001c:\u0002\u00179,X.\u0012=b[BdWm\u001d\t\u0004w\u0005\r\u0017bAAcy\t!Aj\u001c8h)1\tI-!4\u0002P\u0006E\u00171[Ak!\r\tY\rJ\u0007\u0002C!9\u0011q\n\u0016A\u0002\u0005E\u0003\"\u0002&+\u0001\u0004a\u0005\"B,+\u0001\u0004I\u0006\"\u0002?+\u0001\u0004\u0001\bbBA`U\u0001\u0007\u0011\u0011Y\u0001\nG\u0006d7-\u001e7bi\u0016$B!a7\u0002^B11(a\u0018q\u0003kCq!a8,\u0001\u0004\t),A\u0004xK&<\u0007\u000e^:\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005\u0015\b\u0003BAt\u0003[l!!!;\u000b\t\u0005-\u0018QQ\u0001\u0005Y\u0006tw-\u0003\u0003\u0002p\u0006%(AB(cU\u0016\u001cG\u000f"
)
public class LBFGS implements Optimizer, Logging {
   private Gradient gradient;
   private Updater updater;
   private int numCorrections;
   private double convergenceTol;
   private int maxNumIterations;
   private double regParam;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Tuple2 runLBFGS(final RDD data, final Gradient gradient, final Updater updater, final int numCorrections, final double convergenceTol, final int maxNumIterations, final double regParam, final Vector initialWeights) {
      return LBFGS$.MODULE$.runLBFGS(data, gradient, updater, numCorrections, convergenceTol, maxNumIterations, regParam, initialWeights);
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private Gradient gradient() {
      return this.gradient;
   }

   private void gradient_$eq(final Gradient x$1) {
      this.gradient = x$1;
   }

   private Updater updater() {
      return this.updater;
   }

   private void updater_$eq(final Updater x$1) {
      this.updater = x$1;
   }

   private int numCorrections() {
      return this.numCorrections;
   }

   private void numCorrections_$eq(final int x$1) {
      this.numCorrections = x$1;
   }

   private double convergenceTol() {
      return this.convergenceTol;
   }

   private void convergenceTol_$eq(final double x$1) {
      this.convergenceTol = x$1;
   }

   private int maxNumIterations() {
      return this.maxNumIterations;
   }

   private void maxNumIterations_$eq(final int x$1) {
      this.maxNumIterations = x$1;
   }

   private double regParam() {
      return this.regParam;
   }

   private void regParam_$eq(final double x$1) {
      this.regParam = x$1;
   }

   public LBFGS setNumCorrections(final int corrections) {
      .MODULE$.require(corrections > 0, () -> "Number of corrections must be positive but got " + corrections);
      this.numCorrections_$eq(corrections);
      return this;
   }

   public LBFGS setConvergenceTol(final double tolerance) {
      .MODULE$.require(tolerance >= (double)0, () -> "Convergence tolerance must be nonnegative but got " + tolerance);
      this.convergenceTol_$eq(tolerance);
      return this;
   }

   public double getConvergenceTol() {
      return this.convergenceTol();
   }

   public LBFGS setNumIterations(final int iters) {
      .MODULE$.require(iters >= 0, () -> "Maximum of iterations must be nonnegative but got " + iters);
      this.maxNumIterations_$eq(iters);
      return this;
   }

   public int getNumIterations() {
      return this.maxNumIterations();
   }

   public LBFGS setRegParam(final double regParam) {
      .MODULE$.require(regParam >= (double)0, () -> "Regularization parameter must be nonnegative but got " + regParam);
      this.regParam_$eq(regParam);
      return this;
   }

   public double getRegParam() {
      return this.regParam();
   }

   public LBFGS setGradient(final Gradient gradient) {
      this.gradient_$eq(gradient);
      return this;
   }

   public LBFGS setUpdater(final Updater updater) {
      this.updater_$eq(updater);
      return this;
   }

   public Updater getUpdater() {
      return this.updater();
   }

   public Vector optimize(final RDD data, final Vector initialWeights) {
      Tuple2 var5 = this.optimizeWithLossReturned(data, initialWeights);
      if (var5 != null) {
         Vector weights = (Vector)var5._1();
         return weights;
      } else {
         throw new MatchError(var5);
      }
   }

   public Tuple2 optimizeWithLossReturned(final RDD data, final Vector initialWeights) {
      return LBFGS$.MODULE$.runLBFGS(data, this.gradient(), this.updater(), this.numCorrections(), this.convergenceTol(), this.maxNumIterations(), this.regParam(), initialWeights);
   }

   public LBFGS(final Gradient gradient, final Updater updater) {
      this.gradient = gradient;
      this.updater = updater;
      super();
      Logging.$init$(this);
      this.numCorrections = 10;
      this.convergenceTol = 1.0E-6;
      this.maxNumIterations = 100;
      this.regParam = (double)0.0F;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private static class CostFun implements DiffFunction {
      private final RDD data;
      private final Gradient gradient;
      private final Updater updater;
      private final double regParam;
      private final long numExamples;

      public DiffFunction repr() {
         return DiffFunction.repr$(this);
      }

      public DiffFunction cached(final CanCopy copy) {
         return DiffFunction.cached$(this, copy);
      }

      public DiffFunction throughLens(final Isomorphism l) {
         return DiffFunction.throughLens$(this, l);
      }

      public Object gradientAt(final Object x) {
         return StochasticDiffFunction.gradientAt$(this, x);
      }

      public double valueAt(final Object x) {
         return StochasticDiffFunction.valueAt$(this, x);
      }

      public final double apply(final Object x) {
         return StochasticDiffFunction.apply$(this, x);
      }

      public final Object $plus(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$plus$(this, b, op);
      }

      public final Object $colon$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$eq$(this, b, op);
      }

      public final Object $colon$plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$plus$eq$(this, b, op);
      }

      public final Object $colon$times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$times$eq$(this, b, op);
      }

      public final Object $plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$plus$eq$(this, b, op);
      }

      public final Object $times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$times$eq$(this, b, op);
      }

      public final Object $colon$minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$minus$eq$(this, b, op);
      }

      public final Object $colon$percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$percent$eq$(this, b, op);
      }

      public final Object $percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$percent$eq$(this, b, op);
      }

      public final Object $minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$minus$eq$(this, b, op);
      }

      public final Object $colon$div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$div$eq$(this, b, op);
      }

      public final Object $colon$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$up$eq$(this, b, op);
      }

      public final Object $div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$div$eq$(this, b, op);
      }

      public final Object $less$colon$less(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$less$colon$less$(this, b, op);
      }

      public final Object $less$colon$eq(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$less$colon$eq$(this, b, op);
      }

      public final Object $greater$colon$greater(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$greater$colon$greater$(this, b, op);
      }

      public final Object $greater$colon$eq(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$greater$colon$eq$(this, b, op);
      }

      public final Object $colon$amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$amp$eq$(this, b, op);
      }

      public final Object $colon$bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$bar$eq$(this, b, op);
      }

      public final Object $colon$up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$up$up$eq$(this, b, op);
      }

      public final Object $amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$amp$eq$(this, b, op);
      }

      public final Object $bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$bar$eq$(this, b, op);
      }

      public final Object $up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$up$up$eq$(this, b, op);
      }

      public final Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$plus$colon$plus$(this, b, op);
      }

      public final Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$times$colon$times$(this, b, op);
      }

      public final Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$colon$eq$eq$(this, b, op);
      }

      public final Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$colon$bang$eq$(this, b, op);
      }

      public final Object unary_$minus(final UFunc.UImpl op) {
         return ImmutableNumericOps.unary_$minus$(this, op);
      }

      public final Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$minus$colon$minus$(this, b, op);
      }

      public final Object $minus(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$minus$(this, b, op);
      }

      public final Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$percent$colon$percent$(this, b, op);
      }

      public final Object $percent(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$percent$(this, b, op);
      }

      public final Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$div$colon$div$(this, b, op);
      }

      public final Object $div(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$div$(this, b, op);
      }

      public final Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$up$colon$up$(this, b, op);
      }

      public final Object dot(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.dot$(this, b, op);
      }

      public final Object unary_$bang(final UFunc.UImpl op) {
         return ImmutableNumericOps.unary_$bang$(this, op);
      }

      public final Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$amp$colon$amp$(this, b, op);
      }

      public final Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$bar$colon$bar$(this, b, op);
      }

      public final Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$up$up$colon$up$up$(this, b, op);
      }

      public final Object $amp(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$amp$(this, b, op);
      }

      public final Object $bar(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$bar$(this, b, op);
      }

      public final Object $up$up(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$up$up$(this, b, op);
      }

      public final Object $times(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$times$(this, b, op);
      }

      public final Object t(final CanTranspose op) {
         return ImmutableNumericOps.t$(this, op);
      }

      public Object $bslash(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$bslash$(this, b, op);
      }

      public final Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
         return ImmutableNumericOps.t$(this, a, b, op, canSlice);
      }

      public final Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
         return ImmutableNumericOps.t$(this, a, op, canSlice);
      }

      public boolean apply$mcZD$sp(final double v1) {
         return Function1.apply$mcZD$sp$(this, v1);
      }

      public double apply$mcDD$sp(final double v1) {
         return Function1.apply$mcDD$sp$(this, v1);
      }

      public float apply$mcFD$sp(final double v1) {
         return Function1.apply$mcFD$sp$(this, v1);
      }

      public int apply$mcID$sp(final double v1) {
         return Function1.apply$mcID$sp$(this, v1);
      }

      public long apply$mcJD$sp(final double v1) {
         return Function1.apply$mcJD$sp$(this, v1);
      }

      public void apply$mcVD$sp(final double v1) {
         Function1.apply$mcVD$sp$(this, v1);
      }

      public boolean apply$mcZF$sp(final float v1) {
         return Function1.apply$mcZF$sp$(this, v1);
      }

      public double apply$mcDF$sp(final float v1) {
         return Function1.apply$mcDF$sp$(this, v1);
      }

      public float apply$mcFF$sp(final float v1) {
         return Function1.apply$mcFF$sp$(this, v1);
      }

      public int apply$mcIF$sp(final float v1) {
         return Function1.apply$mcIF$sp$(this, v1);
      }

      public long apply$mcJF$sp(final float v1) {
         return Function1.apply$mcJF$sp$(this, v1);
      }

      public void apply$mcVF$sp(final float v1) {
         Function1.apply$mcVF$sp$(this, v1);
      }

      public boolean apply$mcZI$sp(final int v1) {
         return Function1.apply$mcZI$sp$(this, v1);
      }

      public double apply$mcDI$sp(final int v1) {
         return Function1.apply$mcDI$sp$(this, v1);
      }

      public float apply$mcFI$sp(final int v1) {
         return Function1.apply$mcFI$sp$(this, v1);
      }

      public int apply$mcII$sp(final int v1) {
         return Function1.apply$mcII$sp$(this, v1);
      }

      public long apply$mcJI$sp(final int v1) {
         return Function1.apply$mcJI$sp$(this, v1);
      }

      public void apply$mcVI$sp(final int v1) {
         Function1.apply$mcVI$sp$(this, v1);
      }

      public boolean apply$mcZJ$sp(final long v1) {
         return Function1.apply$mcZJ$sp$(this, v1);
      }

      public double apply$mcDJ$sp(final long v1) {
         return Function1.apply$mcDJ$sp$(this, v1);
      }

      public float apply$mcFJ$sp(final long v1) {
         return Function1.apply$mcFJ$sp$(this, v1);
      }

      public int apply$mcIJ$sp(final long v1) {
         return Function1.apply$mcIJ$sp$(this, v1);
      }

      public long apply$mcJJ$sp(final long v1) {
         return Function1.apply$mcJJ$sp$(this, v1);
      }

      public void apply$mcVJ$sp(final long v1) {
         Function1.apply$mcVJ$sp$(this, v1);
      }

      public Function1 compose(final Function1 g) {
         return Function1.compose$(this, g);
      }

      public Function1 andThen(final Function1 g) {
         return Function1.andThen$(this, g);
      }

      public String toString() {
         return Function1.toString$(this);
      }

      public Tuple2 calculate(final DenseVector weights) {
         Vector w = Vectors$.MODULE$.fromBreeze(weights);
         int n = w.size();
         Broadcast bcW = this.data.context().broadcast(w, scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         Gradient localGradient = this.gradient;
         Function2 seqOp = (c, v) -> {
            Tuple2 var5 = new Tuple2(c, v);
            if (var5 != null) {
               Tuple2 var6 = (Tuple2)var5._1();
               Tuple2 var7 = (Tuple2)var5._2();
               if (var6 != null) {
                  Vector grad = (Vector)var6._1();
                  double loss = var6._2$mcD$sp();
                  if (var7 != null) {
                     double label = var7._1$mcD$sp();
                     Vector features = (Vector)var7._2();
                     org.apache.spark.mllib.linalg.DenseVector denseGrad = grad.toDense();
                     double l = localGradient.compute(features, label, (Vector)bcW.value(), denseGrad);
                     return new Tuple2(denseGrad, BoxesRunTime.boxToDouble(loss + l));
                  }
               }
            }

            throw new MatchError(var5);
         };
         Function2 combOp = (c1, c2) -> {
            Tuple2 var3 = new Tuple2(c1, c2);
            if (var3 != null) {
               Tuple2 var4 = (Tuple2)var3._1();
               Tuple2 var5 = (Tuple2)var3._2();
               if (var4 != null) {
                  Vector grad1 = (Vector)var4._1();
                  double loss1 = var4._2$mcD$sp();
                  if (var5 != null) {
                     Vector grad2 = (Vector)var5._1();
                     double loss2 = var5._2$mcD$sp();
                     org.apache.spark.mllib.linalg.DenseVector denseGrad1 = grad1.toDense();
                     org.apache.spark.mllib.linalg.DenseVector denseGrad2 = grad2.toDense();
                     BLAS$.MODULE$.axpy((double)1.0F, (Vector)denseGrad2, (Vector)denseGrad1);
                     return new Tuple2(denseGrad1, BoxesRunTime.boxToDouble(loss1 + loss2));
                  }
               }
            }

            throw new MatchError(var3);
         };
         Vector zeroSparseVector = Vectors$.MODULE$.sparse(n, (Seq)scala.package..MODULE$.Seq().empty());
         Tuple2 x$1 = new Tuple2(zeroSparseVector, BoxesRunTime.boxToDouble((double)0.0F));
         int x$4 = this.data.treeAggregate$default$4(x$1);
         Tuple2 var11 = (Tuple2)this.data.treeAggregate(x$1, seqOp, combOp, x$4, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
         if (var11 != null) {
            Vector gradientSum = (Vector)var11._1();
            double lossSum = var11._2$mcD$sp();
            Tuple2 var10 = new Tuple2(gradientSum, BoxesRunTime.boxToDouble(lossSum));
            Vector gradientSum = (Vector)var10._1();
            double lossSum = var10._2$mcD$sp();
            bcW.destroy();
            double regVal = this.updater.compute(w, Vectors$.MODULE$.zeros(n), (double)0.0F, 1, this.regParam)._2$mcD$sp();
            double loss = lossSum / (double)this.numExamples + regVal;
            Vector gradientTotal = w.copy();
            BLAS$.MODULE$.axpy((double)-1.0F, (Vector)this.updater.compute(w, Vectors$.MODULE$.zeros(n), (double)1.0F, 1, this.regParam)._1(), gradientTotal);
            BLAS$.MODULE$.axpy((double)1.0F / (double)this.numExamples, gradientSum, gradientTotal);
            return new Tuple2(BoxesRunTime.boxToDouble(loss), (DenseVector)gradientTotal.asBreeze());
         } else {
            throw new MatchError(var11);
         }
      }

      public CostFun(final RDD data, final Gradient gradient, final Updater updater, final double regParam, final long numExamples) {
         this.data = data;
         this.gradient = gradient;
         this.updater = updater;
         this.regParam = regParam;
         this.numExamples = numExamples;
         Function1.$init$(this);
         ImmutableNumericOps.$init$(this);
         NumericOps.$init$(this);
         StochasticDiffFunction.$init$(this);
         DiffFunction.$init$(this);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
