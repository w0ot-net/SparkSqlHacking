package org.apache.spark.mllib.stat.correlation;

import breeze.linalg.DenseMatrix;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.mllib.linalg.Matrices$;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.distributed.RowMatrix;
import org.apache.spark.rdd.RDD;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.math.package.;

public final class PearsonCorrelation$ implements Correlation, Logging {
   public static final PearsonCorrelation$ MODULE$ = new PearsonCorrelation$();
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Correlation.$init$(MODULE$);
      Logging.$init$(MODULE$);
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

   public double computeCorrelationWithMatrixImpl(final RDD x, final RDD y) {
      return Correlation.computeCorrelationWithMatrixImpl$(this, x, y);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public double computeCorrelation(final RDD x, final RDD y) {
      return this.computeCorrelationWithMatrixImpl(x, y);
   }

   public Matrix computeCorrelationMatrix(final RDD X) {
      RowMatrix rowMatrix = new RowMatrix(X);
      Matrix cov = rowMatrix.computeCovariance();
      return this.computeCorrelationMatrixFromCovariance(cov);
   }

   public Matrix computeCorrelationMatrixFromCovariance(final Matrix covarianceMatrix) {
      DenseMatrix cov = (DenseMatrix)covarianceMatrix.asBreeze();
      int n = cov.cols();

      for(int i = 0; i < n; ++i) {
         cov.update$mcD$sp(i, i, this.closeToZero(cov.apply$mcD$sp(i, i), this.closeToZero$default$2()) ? (double)0.0F : .MODULE$.sqrt(cov.apply$mcD$sp(i, i)));
      }

      int j = 0;
      double sigma = (double)0.0F;

      boolean containNaN;
      for(containNaN = false; j < n; ++j) {
         sigma = cov.apply$mcD$sp(j, j);

         for(int var11 = 0; var11 < j; ++var11) {
            double var10000;
            if (sigma != (double)0.0F && cov.apply$mcD$sp(var11, var11) != (double)0.0F) {
               var10000 = cov.apply$mcD$sp(var11, j) / (sigma * cov.apply$mcD$sp(var11, var11));
            } else {
               containNaN = true;
               var10000 = Double.NaN;
            }

            double corr = var10000;
            cov.update$mcD$sp(var11, j, corr);
            cov.update$mcD$sp(j, var11, corr);
         }
      }

      for(int var12 = 0; var12 < n; ++var12) {
         cov.update$mcD$sp(var12, var12, (double)1.0F);
      }

      if (containNaN) {
         this.logWarning((Function0)(() -> "Pearson correlation matrix contains NaN values."));
      }

      return Matrices$.MODULE$.fromBreeze(cov);
   }

   private boolean closeToZero(final double value, final double threshold) {
      return .MODULE$.abs(value) <= threshold;
   }

   private double closeToZero$default$2() {
      return 1.0E-12;
   }

   private PearsonCorrelation$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
