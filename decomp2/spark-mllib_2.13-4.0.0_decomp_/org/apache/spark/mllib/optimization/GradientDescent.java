package org.apache.spark.mllib.optimization;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.rdd.RDD;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mg\u0001B\u0014)\u0001MB\u0001\u0002\u0012\u0001\u0003\u0002\u0004%I!\u0012\u0005\t\u0013\u0002\u0011\t\u0019!C\u0005\u0015\"A\u0001\u000b\u0001B\u0001B\u0003&a\t\u0003\u0005R\u0001\t\u0005\r\u0011\"\u0003S\u0011!1\u0006A!a\u0001\n\u00139\u0006\u0002C-\u0001\u0005\u0003\u0005\u000b\u0015B*\t\ri\u0003A\u0011\u0001\u0017\\\u0011\u001dy\u0006\u00011A\u0005\n\u0001Dq\u0001\u001a\u0001A\u0002\u0013%Q\r\u0003\u0004h\u0001\u0001\u0006K!\u0019\u0005\bQ\u0002\u0001\r\u0011\"\u0003j\u0011\u001di\u0007\u00011A\u0005\n9Da\u0001\u001d\u0001!B\u0013Q\u0007bB9\u0001\u0001\u0004%I\u0001\u0019\u0005\be\u0002\u0001\r\u0011\"\u0003t\u0011\u0019)\b\u0001)Q\u0005C\"9a\u000f\u0001a\u0001\n\u0013\u0001\u0007bB<\u0001\u0001\u0004%I\u0001\u001f\u0005\u0007u\u0002\u0001\u000b\u0015B1\t\u000fm\u0004\u0001\u0019!C\u0005A\"9A\u0010\u0001a\u0001\n\u0013i\bBB@\u0001A\u0003&\u0011\rC\u0004\u0002\u0002\u0001!\t!a\u0001\t\u000f\u0005-\u0001\u0001\"\u0001\u0002\u000e!9\u00111\u0003\u0001\u0005\u0002\u0005U\u0001bBA\u000e\u0001\u0011\u0005\u0011Q\u0004\u0005\b\u0003C\u0001A\u0011AA\u0012\u0011\u001d\tI\u0003\u0001C\u0001\u0003WAq!a\f\u0001\t\u0003\t\t\u0004C\u0004\u00026\u0001!\t!a\u000e\t\u000f\u0005}\u0003\u0001\"\u0001\u0002b\u001d9\u0011q\u000e\u0015\t\u0002\u0005EdAB\u0014)\u0011\u0003\t\u0019\b\u0003\u0004[C\u0011\u0005\u0011Q\u0011\u0005\b\u0003\u000f\u000bC\u0011AAE\u0011\u001d\t9)\tC\u0001\u0003;Cq!a,\"\t\u0013\t\t\fC\u0005\u0002D\u0006\n\t\u0011\"\u0003\u0002F\nyqI]1eS\u0016tG\u000fR3tG\u0016tGO\u0003\u0002*U\u0005aq\u000e\u001d;j[&T\u0018\r^5p]*\u00111\u0006L\u0001\u0006[2d\u0017N\u0019\u0006\u0003[9\nQa\u001d9be.T!a\f\u0019\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\t\u0014aA8sO\u000e\u00011\u0003\u0002\u00015uy\u0002\"!\u000e\u001d\u000e\u0003YR\u0011aN\u0001\u0006g\u000e\fG.Y\u0005\u0003sY\u0012a!\u00118z%\u00164\u0007CA\u001e=\u001b\u0005A\u0013BA\u001f)\u0005%y\u0005\u000f^5nSj,'\u000f\u0005\u0002@\u00056\t\u0001I\u0003\u0002BY\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002D\u0001\n9Aj\\4hS:<\u0017\u0001C4sC\u0012LWM\u001c;\u0016\u0003\u0019\u0003\"aO$\n\u0005!C#\u0001C$sC\u0012LWM\u001c;\u0002\u0019\u001d\u0014\u0018\rZ5f]R|F%Z9\u0015\u0005-s\u0005CA\u001bM\u0013\tieG\u0001\u0003V]&$\bbB(\u0003\u0003\u0003\u0005\rAR\u0001\u0004q\u0012\n\u0014!C4sC\u0012LWM\u001c;!\u0003\u001d)\b\u000fZ1uKJ,\u0012a\u0015\t\u0003wQK!!\u0016\u0015\u0003\u000fU\u0003H-\u0019;fe\u0006YQ\u000f\u001d3bi\u0016\u0014x\fJ3r)\tY\u0005\fC\u0004P\u000b\u0005\u0005\t\u0019A*\u0002\u0011U\u0004H-\u0019;fe\u0002\na\u0001P5oSRtDc\u0001/^=B\u00111\b\u0001\u0005\u0006\t\u001e\u0001\rA\u0012\u0005\u0006#\u001e\u0001\raU\u0001\tgR,\u0007oU5{KV\t\u0011\r\u0005\u00026E&\u00111M\u000e\u0002\u0007\t>,(\r\\3\u0002\u0019M$X\r]*ju\u0016|F%Z9\u0015\u0005-3\u0007bB(\n\u0003\u0003\u0005\r!Y\u0001\ngR,\u0007oU5{K\u0002\nQB\\;n\u0013R,'/\u0019;j_:\u001cX#\u00016\u0011\u0005UZ\u0017B\u000177\u0005\rIe\u000e^\u0001\u0012]Vl\u0017\n^3sCRLwN\\:`I\u0015\fHCA&p\u0011\u001dyE\"!AA\u0002)\faB\\;n\u0013R,'/\u0019;j_:\u001c\b%\u0001\u0005sK\u001e\u0004\u0016M]1n\u00031\u0011Xm\u001a)be\u0006lw\fJ3r)\tYE\u000fC\u0004P\u001f\u0005\u0005\t\u0019A1\u0002\u0013I,w\rU1sC6\u0004\u0013!E7j]&\u0014\u0015\r^2i\rJ\f7\r^5p]\u0006)R.\u001b8j\u0005\u0006$8\r\u001b$sC\u000e$\u0018n\u001c8`I\u0015\fHCA&z\u0011\u001dy%#!AA\u0002\u0005\f!#\\5oS\n\u000bGo\u00195Ge\u0006\u001cG/[8oA\u0005q1m\u001c8wKJ<WM\\2f)>d\u0017AE2p]Z,'oZ3oG\u0016$v\u000e\\0%KF$\"a\u0013@\t\u000f=+\u0012\u0011!a\u0001C\u0006y1m\u001c8wKJ<WM\\2f)>d\u0007%A\u0006tKR\u001cF/\u001a9TSj,G\u0003BA\u0003\u0003\u000fi\u0011\u0001\u0001\u0005\u0007\u0003\u00139\u0002\u0019A1\u0002\tM$X\r]\u0001\u0015g\u0016$X*\u001b8j\u0005\u0006$8\r\u001b$sC\u000e$\u0018n\u001c8\u0015\t\u0005\u0015\u0011q\u0002\u0005\u0007\u0003#A\u0002\u0019A1\u0002\u0011\u0019\u0014\u0018m\u0019;j_:\f\u0001c]3u\u001dVl\u0017\n^3sCRLwN\\:\u0015\t\u0005\u0015\u0011q\u0003\u0005\u0007\u00033I\u0002\u0019\u00016\u0002\u000b%$XM]:\u0002\u0017M,GOU3h!\u0006\u0014\u0018-\u001c\u000b\u0005\u0003\u000b\ty\u0002C\u0003r5\u0001\u0007\u0011-A\ttKR\u001cuN\u001c<fe\u001e,gnY3U_2$B!!\u0002\u0002&!1\u0011qE\u000eA\u0002\u0005\f\u0011\u0002^8mKJ\fgnY3\u0002\u0017M,Go\u0012:bI&,g\u000e\u001e\u000b\u0005\u0003\u000b\ti\u0003C\u0003E9\u0001\u0007a)\u0001\u0006tKR,\u0006\u000fZ1uKJ$B!!\u0002\u00024!)\u0011+\ba\u0001'\u0006Aq\u000e\u001d;j[&TX\r\u0006\u0004\u0002:\u0005\u0015\u00131\f\t\u0005\u0003w\t\t%\u0004\u0002\u0002>)\u0019\u0011q\b\u0016\u0002\r1Lg.\u00197h\u0013\u0011\t\u0019%!\u0010\u0003\rY+7\r^8s\u0011\u001d\t9E\ba\u0001\u0003\u0013\nA\u0001Z1uCB1\u00111JA)\u0003+j!!!\u0014\u000b\u0007\u0005=C&A\u0002sI\u0012LA!a\u0015\u0002N\t\u0019!\u000b\u0012#\u0011\rU\n9&YA\u001d\u0013\r\tIF\u000e\u0002\u0007)V\u0004H.\u001a\u001a\t\u000f\u0005uc\u00041\u0001\u0002:\u0005q\u0011N\\5uS\u0006dw+Z5hQR\u001c\u0018\u0001G8qi&l\u0017N_3XSRDGj\\:t%\u0016$XO\u001d8fIR1\u00111MA6\u0003[\u0002r!NA,\u0003s\t)\u0007\u0005\u00036\u0003O\n\u0017bAA5m\t)\u0011I\u001d:bs\"9\u0011qI\u0010A\u0002\u0005%\u0003bBA/?\u0001\u0007\u0011\u0011H\u0001\u0010\u000fJ\fG-[3oi\u0012+7oY3oiB\u00111(I\n\u0006CQr\u0014Q\u000f\t\u0005\u0003o\n\t)\u0004\u0002\u0002z)!\u00111PA?\u0003\tIwN\u0003\u0002\u0002\u0000\u0005!!.\u0019<b\u0013\u0011\t\u0019)!\u001f\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\u0005E\u0014a\u0004:v]6Kg.\u001b\"bi\u000eD7k\u0012#\u0015)\u0005\r\u00141RAG\u0003\u001f\u000b\t*a%\u0002\u0016\u0006]\u0015\u0011TAN\u0011\u001d\t9e\ta\u0001\u0003\u0013BQ\u0001R\u0012A\u0002\u0019CQ!U\u0012A\u0002MCQaX\u0012A\u0002\u0005DQ\u0001[\u0012A\u0002)DQ!]\u0012A\u0002\u0005DQA^\u0012A\u0002\u0005Dq!!\u0018$\u0001\u0004\tI\u0004C\u0003|G\u0001\u0007\u0011\r\u0006\n\u0002d\u0005}\u0015\u0011UAR\u0003K\u000b9+!+\u0002,\u00065\u0006bBA$I\u0001\u0007\u0011\u0011\n\u0005\u0006\t\u0012\u0002\rA\u0012\u0005\u0006#\u0012\u0002\ra\u0015\u0005\u0006?\u0012\u0002\r!\u0019\u0005\u0006Q\u0012\u0002\rA\u001b\u0005\u0006c\u0012\u0002\r!\u0019\u0005\u0006m\u0012\u0002\r!\u0019\u0005\b\u0003;\"\u0003\u0019AA\u001d\u0003-I7oQ8om\u0016\u0014x-\u001a3\u0015\u0011\u0005M\u0016\u0011XA_\u0003\u0003\u00042!NA[\u0013\r\t9L\u000e\u0002\b\u0005>|G.Z1o\u0011\u001d\tY,\na\u0001\u0003s\tq\u0002\u001d:fm&|Wo],fS\u001eDGo\u001d\u0005\b\u0003\u007f+\u0003\u0019AA\u001d\u00039\u0019WO\u001d:f]R<V-[4iiNDQa_\u0013A\u0002\u0005\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a2\u0011\t\u0005%\u0017qZ\u0007\u0003\u0003\u0017TA!!4\u0002~\u0005!A.\u00198h\u0013\u0011\t\t.a3\u0003\r=\u0013'.Z2u\u0001"
)
public class GradientDescent implements Optimizer, Logging {
   private Gradient gradient;
   private Updater updater;
   private double stepSize;
   private int numIterations;
   private double regParam;
   private double miniBatchFraction;
   private double convergenceTol;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Tuple2 runMiniBatchSGD(final RDD data, final Gradient gradient, final Updater updater, final double stepSize, final int numIterations, final double regParam, final double miniBatchFraction, final Vector initialWeights) {
      return GradientDescent$.MODULE$.runMiniBatchSGD(data, gradient, updater, stepSize, numIterations, regParam, miniBatchFraction, initialWeights);
   }

   public static Tuple2 runMiniBatchSGD(final RDD data, final Gradient gradient, final Updater updater, final double stepSize, final int numIterations, final double regParam, final double miniBatchFraction, final Vector initialWeights, final double convergenceTol) {
      return GradientDescent$.MODULE$.runMiniBatchSGD(data, gradient, updater, stepSize, numIterations, regParam, miniBatchFraction, initialWeights, convergenceTol);
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

   private double stepSize() {
      return this.stepSize;
   }

   private void stepSize_$eq(final double x$1) {
      this.stepSize = x$1;
   }

   private int numIterations() {
      return this.numIterations;
   }

   private void numIterations_$eq(final int x$1) {
      this.numIterations = x$1;
   }

   private double regParam() {
      return this.regParam;
   }

   private void regParam_$eq(final double x$1) {
      this.regParam = x$1;
   }

   private double miniBatchFraction() {
      return this.miniBatchFraction;
   }

   private void miniBatchFraction_$eq(final double x$1) {
      this.miniBatchFraction = x$1;
   }

   private double convergenceTol() {
      return this.convergenceTol;
   }

   private void convergenceTol_$eq(final double x$1) {
      this.convergenceTol = x$1;
   }

   public GradientDescent setStepSize(final double step) {
      .MODULE$.require(step > (double)0, () -> "Initial step size must be positive but got " + step);
      this.stepSize_$eq(step);
      return this;
   }

   public GradientDescent setMiniBatchFraction(final double fraction) {
      .MODULE$.require(fraction > (double)0 && fraction <= (double)1.0F, () -> "Fraction for mini-batch SGD must be in range (0, 1] but got " + fraction);
      this.miniBatchFraction_$eq(fraction);
      return this;
   }

   public GradientDescent setNumIterations(final int iters) {
      .MODULE$.require(iters >= 0, () -> "Number of iterations must be nonnegative but got " + iters);
      this.numIterations_$eq(iters);
      return this;
   }

   public GradientDescent setRegParam(final double regParam) {
      .MODULE$.require(regParam >= (double)0, () -> "Regularization parameter must be nonnegative but got " + regParam);
      this.regParam_$eq(regParam);
      return this;
   }

   public GradientDescent setConvergenceTol(final double tolerance) {
      .MODULE$.require(tolerance >= (double)0.0F && tolerance <= (double)1.0F, () -> "Convergence tolerance must be in range [0, 1] but got " + tolerance);
      this.convergenceTol_$eq(tolerance);
      return this;
   }

   public GradientDescent setGradient(final Gradient gradient) {
      this.gradient_$eq(gradient);
      return this;
   }

   public GradientDescent setUpdater(final Updater updater) {
      this.updater_$eq(updater);
      return this;
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
      return GradientDescent$.MODULE$.runMiniBatchSGD(data, this.gradient(), this.updater(), this.stepSize(), this.numIterations(), this.regParam(), this.miniBatchFraction(), initialWeights, this.convergenceTol());
   }

   public GradientDescent(final Gradient gradient, final Updater updater) {
      this.gradient = gradient;
      this.updater = updater;
      super();
      Logging.$init$(this);
      this.stepSize = (double)1.0F;
      this.numIterations = 100;
      this.regParam = (double)0.0F;
      this.miniBatchFraction = (double)1.0F;
      this.convergenceTol = 0.001;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
