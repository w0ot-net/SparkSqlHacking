package org.apache.spark.ml.regression;

import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.Vector;
import breeze.linalg.package;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.mllib.linalg.VectorImplicits$;
import org.apache.spark.mllib.optimization.Updater;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005M4Q\u0001F\u000b\u0001/}A\u0001B\f\u0001\u0003\u0002\u0003\u0006I\u0001\r\u0005\u0006m\u0001!\ta\u000e\u0005\bw\u0001\u0011\r\u0011\"\u0001=\u0011\u0019\u0001\u0005\u0001)A\u0005{!9\u0011\t\u0001b\u0001\n\u0003a\u0004B\u0002\"\u0001A\u0003%Q\bC\u0004D\u0001\t\u0007I\u0011\u0001\u001f\t\r\u0011\u0003\u0001\u0015!\u0003>\u0011\u001d)\u0005A1A\u0005\u0002\u0019Caa\u0014\u0001!\u0002\u00139\u0005b\u0002)\u0001\u0005\u0004%\tA\u0012\u0005\u0007#\u0002\u0001\u000b\u0011B$\t\u000fI\u0003\u0001\u0019!C\u0001y!91\u000b\u0001a\u0001\n\u0003!\u0006B\u0002.\u0001A\u0003&Q\bC\u0004\\\u0001\u0001\u0007I\u0011\u0001\u001f\t\u000fq\u0003\u0001\u0019!C\u0001;\"1q\f\u0001Q!\nuBQ\u0001\u0019\u0001\u0005B\u0005\u0014A\"\u00113b[^+\u0006\u000fZ1uKJT!AF\f\u0002\u0015I,wM]3tg&|gN\u0003\u0002\u00193\u0005\u0011Q\u000e\u001c\u0006\u00035m\tQa\u001d9be.T!\u0001H\u000f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0012aA8sON\u0019\u0001\u0001\t\u0015\u0011\u0005\u00052S\"\u0001\u0012\u000b\u0005\r\"\u0013\u0001D8qi&l\u0017N_1uS>t'BA\u0013\u001a\u0003\u0015iG\u000e\\5c\u0013\t9#EA\u0004Va\u0012\fG/\u001a:\u0011\u0005%bS\"\u0001\u0016\u000b\u0005-J\u0012\u0001C5oi\u0016\u0014h.\u00197\n\u00055R#a\u0002'pO\u001eLgnZ\u0001\u000bo\u0016Lw\r\u001b;TSj,7\u0001\u0001\t\u0003cQj\u0011A\r\u0006\u0002g\u0005)1oY1mC&\u0011QG\r\u0002\u0004\u0013:$\u0018A\u0002\u001fj]&$h\b\u0006\u00029uA\u0011\u0011\bA\u0007\u0002+!)aF\u0001a\u0001a\u0005)!-\u001a;bcU\tQ\b\u0005\u00022}%\u0011qH\r\u0002\u0007\t>,(\r\\3\u0002\r\t,G/Y\u0019!\u0003\u0015\u0011W\r^13\u0003\u0019\u0011W\r^13A\u00059Q\r]:jY>t\u0017\u0001C3qg&dwN\u001c\u0011\u0002\u00035,\u0012a\u0012\t\u0004\u00116kT\"A%\u000b\u0005)[\u0015A\u00027j]\u0006dwMC\u0001M\u0003\u0019\u0011'/Z3{K&\u0011a*\u0013\u0002\u0007-\u0016\u001cGo\u001c:\u0002\u00055\u0004\u0013!\u0001<\u0002\u0005Y\u0004\u0013A\u00022fi\u0006\fD+\u0001\u0006cKR\f\u0017\u0007V0%KF$\"!\u0016-\u0011\u0005E2\u0016BA,3\u0005\u0011)f.\u001b;\t\u000fes\u0011\u0011!a\u0001{\u0005\u0019\u0001\u0010J\u0019\u0002\u000f\t,G/Y\u0019UA\u00051!-\u001a;beQ\u000b!BY3uCJ\"v\fJ3r)\t)f\fC\u0004Z#\u0005\u0005\t\u0019A\u001f\u0002\u000f\t,G/\u0019\u001aUA\u000591m\\7qkR,GC\u00022jW6|\u0017\u000f\u0005\u00032G\u0016l\u0014B\u000133\u0005\u0019!V\u000f\u001d7feA\u0011a\r[\u0007\u0002O*\u0011!\nJ\u0005\u0003\u001d\u001eDQA[\nA\u0002\u0015\f!b^3jO\"$8o\u00147e\u0011\u0015a7\u00031\u0001f\u0003!9'/\u00193jK:$\b\"\u00028\u0014\u0001\u0004i\u0014\u0001C:uKB\u001c\u0016N_3\t\u000bA\u001c\u0002\u0019\u0001\u0019\u0002\t%$XM\u001d\u0005\u0006eN\u0001\r!P\u0001\te\u0016<\u0007+\u0019:b[\u0002"
)
public class AdamWUpdater extends Updater implements Logging {
   private final double beta1;
   private final double beta2;
   private final double epsilon;
   private final Vector m;
   private final Vector v;
   private double beta1T;
   private double beta2T;
   private transient Logger org$apache$spark$internal$Logging$$log_;

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

   public double beta1() {
      return this.beta1;
   }

   public double beta2() {
      return this.beta2;
   }

   public double epsilon() {
      return this.epsilon;
   }

   public Vector m() {
      return this.m;
   }

   public Vector v() {
      return this.v;
   }

   public double beta1T() {
      return this.beta1T;
   }

   public void beta1T_$eq(final double x$1) {
      this.beta1T = x$1;
   }

   public double beta2T() {
      return this.beta2T;
   }

   public void beta2T_$eq(final double x$1) {
      this.beta2T = x$1;
   }

   public Tuple2 compute(final org.apache.spark.mllib.linalg.Vector weightsOld, final org.apache.spark.mllib.linalg.Vector gradient, final double stepSize, final int iter, final double regParam) {
      Vector w = weightsOld.asBreeze().toDenseVector$mcD$sp(.MODULE$.Double());
      if (stepSize > (double)0) {
         Vector g = gradient.asBreeze().toDenseVector$mcD$sp(.MODULE$.Double());
         this.m().$times$eq(BoxesRunTime.boxToDouble(this.beta1()), breeze.linalg.operators.HasOps..MODULE$.impl_Op_InPlace_V_S_Double_OpMulScalar());
         breeze.linalg.package..MODULE$.axpy(BoxesRunTime.boxToDouble((double)1 - this.beta1()), g, this.m(), breeze.linalg.operators.HasOps..MODULE$.impl_scaleAdd_InPlace_V_S_V_Double());
         this.v().$times$eq(BoxesRunTime.boxToDouble(this.beta2()), breeze.linalg.operators.HasOps..MODULE$.impl_Op_InPlace_V_S_Double_OpMulScalar());
         breeze.linalg.package..MODULE$.axpy(BoxesRunTime.boxToDouble((double)1 - this.beta2()), g.$times(g, breeze.linalg.operators.HasOps..MODULE$.impl_T_S_eq_U_from_ZipMap(breeze.linalg.Vector..MODULE$.scalarOf(), breeze.linalg.operators.OpMulMatrix..MODULE$.opMulMatrixFromSemiring(breeze.math.Semiring..MODULE$.semiringD()), breeze.linalg.operators.HasOps..MODULE$.canZipMapValues_V(.MODULE$.Double()))), this.v(), breeze.linalg.operators.HasOps..MODULE$.impl_scaleAdd_InPlace_V_S_V_Double());
         this.beta1T_$eq(this.beta1T() * this.beta1());
         this.beta2T_$eq(this.beta2T() * this.beta2());
         Vector mHat = (Vector)this.m().$div(BoxesRunTime.boxToDouble((double)1 - this.beta1T()), breeze.linalg.operators.HasOps..MODULE$.impl_Op_V_S_eq_V_Double_OpDiv());
         Vector vHat = (Vector)this.v().$div(BoxesRunTime.boxToDouble((double)1 - this.beta2T()), breeze.linalg.operators.HasOps..MODULE$.impl_Op_V_S_eq_V_Double_OpDiv());
         w.$minus$eq(((NumericOps)((ImmutableNumericOps)(new package.InjectNumericOps(breeze.linalg.package..MODULE$.InjectNumericOps(BoxesRunTime.boxToDouble(stepSize)))).$times(mHat, breeze.linalg.operators.HasOps..MODULE$.impl_Op_S_V_eq_V_Double_OpMulMatrix())).$div(((NumericOps)breeze.numerics.package.sqrt..MODULE$.apply(vHat, breeze.linalg.operators.HasOps..MODULE$.fromLowOrderCanMapActiveValues(breeze.linalg.Vector..MODULE$.scalarOf(), breeze.numerics.package.sqrt.sqrtDoubleImpl..MODULE$, breeze.linalg.operators.HasOps..MODULE$.canMapValues_V(breeze.storage.Zero..MODULE$.DoubleZero(), .MODULE$.Double())))).$plus(BoxesRunTime.boxToDouble(this.epsilon()), breeze.linalg.operators.HasOps..MODULE$.impl_Op_V_S_eq_V_Double_OpAdd()), breeze.linalg.operators.HasOps..MODULE$.impl_Op_V_V_eq_V_Double_OpDiv())).$plus((new package.InjectNumericOps(breeze.linalg.package..MODULE$.InjectNumericOps(BoxesRunTime.boxToDouble(regParam)))).$times(w, breeze.linalg.operators.HasOps..MODULE$.impl_Op_S_V_eq_V_Double_OpMulMatrix()), breeze.linalg.operators.HasOps..MODULE$.impl_Op_V_V_eq_V_idempotent_Double_OpAdd()), breeze.linalg.operators.HasOps..MODULE$.impl_Op_InPlace_V_V_Idempotent_Double_OpSub());
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      double norm = BoxesRunTime.unboxToDouble(breeze.linalg.norm..MODULE$.apply(w, BoxesRunTime.boxToDouble((double)2.0F), breeze.linalg.norm..MODULE$.canNorm(breeze.linalg.operators.HasOps..MODULE$.canIterateValues_V(), breeze.linalg.norm..MODULE$.scalarNorm_Double())));
      return new Tuple2(VectorImplicits$.MODULE$.mlVectorToMLlibVector(org.apache.spark.ml.linalg.Vectors..MODULE$.fromBreeze(w)), BoxesRunTime.boxToDouble((double)0.5F * regParam * norm * norm));
   }

   public AdamWUpdater(final int weightSize) {
      Logging.$init$(this);
      this.beta1 = 0.9;
      this.beta2 = 0.999;
      this.epsilon = 1.0E-8;
      this.m = breeze.linalg.Vector..MODULE$.zeros(weightSize, .MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero()).toDenseVector$mcD$sp(.MODULE$.Double());
      this.v = breeze.linalg.Vector..MODULE$.zeros(weightSize, .MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero()).toDenseVector$mcD$sp(.MODULE$.Double());
      this.beta1T = (double)1.0F;
      this.beta2T = (double)1.0F;
   }
}
