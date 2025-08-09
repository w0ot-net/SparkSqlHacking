package org.apache.spark.mllib.stat.test;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.spark.SparkException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.mllib.linalg.DenseMatrix;
import org.apache.spark.mllib.linalg.DenseVector;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.SparseVector;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.util.collection.OpenHashMap;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple5;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

public final class ChiSqTest$ implements Logging {
   public static final ChiSqTest$ MODULE$ = new ChiSqTest$();
   private static final ChiSqTest.Method PEARSON;
   private static final int maxCategories;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      PEARSON = new ChiSqTest.Method("pearson", (JFunction2.mcDDD.sp)(observed, expected) -> {
         double dev = observed - expected;
         return dev * dev / expected;
      });
      maxCategories = 10000;
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
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public ChiSqTest.Method PEARSON() {
      return PEARSON;
   }

   private ChiSqTest.Method methodFromString(final String methodName) {
      String var10000 = this.PEARSON().name();
      if (var10000 == null) {
         if (methodName == null) {
            return this.PEARSON();
         }
      } else if (var10000.equals(methodName)) {
         return this.PEARSON();
      }

      throw new IllegalArgumentException("Unrecognized method for Chi squared test.");
   }

   public int maxCategories() {
      return maxCategories;
   }

   public ChiSqTestResult[] chiSquaredFeatures(final RDD data, final String methodName) {
      return (ChiSqTestResult[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(this.computeChiSquared(data.map((l) -> new Tuple2(BoxesRunTime.boxToDouble(l.label()), l.features()), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), methodName).collect()), (x$1) -> BoxesRunTime.boxToInteger($anonfun$chiSquaredFeatures$2(x$1)), scala.math.Ordering.Int..MODULE$)), (x0$1) -> {
         if (x0$1 != null) {
            double pValue = BoxesRunTime.unboxToDouble(x0$1._2());
            int degreesOfFreedom = BoxesRunTime.unboxToInt(x0$1._3());
            double statistic = BoxesRunTime.unboxToDouble(x0$1._4());
            String nullHypothesis = (String)x0$1._5();
            return new ChiSqTestResult(pValue, degreesOfFreedom, statistic, methodName, nullHypothesis);
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(ChiSqTestResult.class));
   }

   public RDD computeChiSquared(final RDD data, final String methodName) {
      Vector var4 = (Vector)((Tuple2)data.first())._2();
      if (var4 instanceof DenseVector var5) {
         return this.chiSquaredDenseFeatures(data, var5.size(), methodName);
      } else if (var4 instanceof SparseVector var6) {
         return this.chiSquaredSparseFeatures(data, var6.size(), methodName);
      } else {
         throw new MatchError(var4);
      }
   }

   private RDD chiSquaredDenseFeatures(final RDD data, final int numFeatures, final String methodName) {
      return org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(data.flatMap((x0$1) -> {
         if (x0$1 != null) {
            double label = x0$1._1$mcD$sp();
            Vector features = (Vector)x0$1._2();
            scala.Predef..MODULE$.require(features.size() == numFeatures, () -> "Number of features must be " + numFeatures + " but got " + features.size());
            return features.iterator().map((x0$2) -> {
               if (x0$2 != null) {
                  int col = x0$2._1$mcI$sp();
                  double value = x0$2._2$mcD$sp();
                  return new Tuple2(BoxesRunTime.boxToInteger(col), new Tuple2.mcDD.sp(label, value));
               } else {
                  throw new MatchError(x0$2);
               }
            });
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.math.Ordering.Int..MODULE$).aggregateByKey(new OpenHashMap.mcJ.sp(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.Long()), (x0$3, x1$1) -> {
         Tuple2 var3 = new Tuple2(x0$3, x1$1);
         if (var3 != null) {
            OpenHashMap counts = (OpenHashMap)var3._1();
            Tuple2 t = (Tuple2)var3._2();
            counts.changeValue$mcJ$sp(t, (JFunction0.mcJ.sp)() -> 1L, (JFunction1.mcJJ.sp)(x$2) -> x$2 + 1L);
            return counts;
         } else {
            throw new MatchError(var3);
         }
      }, (x0$4, x1$2) -> {
         Tuple2 var3 = new Tuple2(x0$4, x1$2);
         if (var3 != null) {
            OpenHashMap counts1 = (OpenHashMap)var3._1();
            OpenHashMap counts2 = (OpenHashMap)var3._2();
            counts2.foreach((x0$5) -> BoxesRunTime.boxToLong($anonfun$chiSquaredDenseFeatures$8(counts1, x0$5)));
            return counts1;
         } else {
            throw new MatchError(var3);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(OpenHashMap.class)).map((x0$6) -> {
         if (x0$6 != null) {
            int col = x0$6._1$mcI$sp();
            OpenHashMap counts = (OpenHashMap)x0$6._2();
            ChiSqTestResult result = MODULE$.computeChiSq(counts.toMap(scala..less.colon.less..MODULE$.refl()), methodName, col);
            return new Tuple5(BoxesRunTime.boxToInteger(col), BoxesRunTime.boxToDouble(result.pValue()), BoxesRunTime.boxToInteger(result.degreesOfFreedom()), BoxesRunTime.boxToDouble(result.statistic()), result.nullHypothesis());
         } else {
            throw new MatchError(x0$6);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple5.class));
   }

   private RDD chiSquaredSparseFeatures(final RDD data, final int numFeatures, final String methodName) {
      scala.collection.immutable.Map labelCounts = data.map((x$4) -> BoxesRunTime.boxToDouble($anonfun$chiSquaredSparseFeatures$1(x$4)), scala.reflect.ClassTag..MODULE$.Double()).countByValue(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$).toMap(scala..less.colon.less..MODULE$.refl());
      long numInstances = BoxesRunTime.unboxToLong(labelCounts.valuesIterator().sum(scala.math.Numeric.LongIsIntegral..MODULE$));
      int numLabels = labelCounts.size();
      if (numLabels > this.maxCategories()) {
         throw new SparkException("Chi-square test expect factors (categorical values) but found more than " + this.maxCategories() + " distinct label values.");
      } else {
         int numParts = data.getNumPartitions();
         return org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(data.mapPartitionsWithIndex((x0$1, x1$1) -> $anonfun$chiSquaredSparseFeatures$2(numFeatures, numParts, BoxesRunTime.unboxToInt(x0$1), x1$1), data.mapPartitionsWithIndex$default$2(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.math.Ordering.Int..MODULE$).aggregateByKey(new OpenHashMap.mcJ.sp(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.Long()), (x0$4, x1$2) -> {
            Tuple2 var3 = new Tuple2(x0$4, x1$2);
            if (var3 != null) {
               OpenHashMap counts = (OpenHashMap)var3._1();
               Tuple2 labelAndValue = (Tuple2)var3._2();
               if (labelAndValue != null) {
                  BoxesRunTime.boxToLong(counts.changeValue$mcJ$sp(labelAndValue, (JFunction0.mcJ.sp)() -> 1L, (JFunction1.mcJJ.sp)(x$5) -> x$5 + 1L));
               } else {
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }

               return counts;
            } else {
               throw new MatchError(var3);
            }
         }, (x0$5, x1$3) -> {
            Tuple2 var3 = new Tuple2(x0$5, x1$3);
            if (var3 != null) {
               OpenHashMap counts1 = (OpenHashMap)var3._1();
               OpenHashMap counts2 = (OpenHashMap)var3._2();
               counts2.foreach((x0$6) -> BoxesRunTime.boxToLong($anonfun$chiSquaredSparseFeatures$12(counts1, x0$6)));
               return counts1;
            } else {
               throw new MatchError(var3);
            }
         }, scala.reflect.ClassTag..MODULE$.apply(OpenHashMap.class)).map((x0$7) -> {
            if (x0$7 != null) {
               int col = x0$7._1$mcI$sp();
               OpenHashMap counts = (OpenHashMap)x0$7._2();
               long nnz = BoxesRunTime.unboxToLong(counts.iterator().map((x$7) -> BoxesRunTime.boxToLong($anonfun$chiSquaredSparseFeatures$16(x$7))).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
               scala.Predef..MODULE$.require(numInstances >= nnz);
               if (numInstances > nnz) {
                  scala.collection.immutable.Map labelNNZ = (scala.collection.immutable.Map).MODULE$.groupBy$extension(scala.Predef..MODULE$.refArrayOps(counts.iterator().map((x0$8) -> {
                     if (x0$8 != null) {
                        Tuple2 var3 = (Tuple2)x0$8._1();
                        long c = x0$8._2$mcJ$sp();
                        if (var3 != null) {
                           double label = var3._1$mcD$sp();
                           return new Tuple2.mcDJ.sp(label, c);
                        }
                     }

                     throw new MatchError(x0$8);
                  }).toArray(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))), (x$8) -> BoxesRunTime.boxToDouble($anonfun$chiSquaredSparseFeatures$18(x$8))).transform((x$9, v) -> BoxesRunTime.boxToLong($anonfun$chiSquaredSparseFeatures$19(BoxesRunTime.unboxToDouble(x$9), v)));
                  labelCounts.foreach((x0$9) -> {
                     $anonfun$chiSquaredSparseFeatures$21(labelNNZ, counts, x0$9);
                     return BoxedUnit.UNIT;
                  });
               }

               ChiSqTestResult result = MODULE$.computeChiSq(counts.toMap(scala..less.colon.less..MODULE$.refl()), methodName, col);
               return new Tuple5(BoxesRunTime.boxToInteger(col), BoxesRunTime.boxToDouble(result.pValue()), BoxesRunTime.boxToInteger(result.degreesOfFreedom()), BoxesRunTime.boxToDouble(result.statistic()), result.nullHypothesis());
            } else {
               throw new MatchError(x0$7);
            }
         }, scala.reflect.ClassTag..MODULE$.apply(Tuple5.class));
      }
   }

   private ChiSqTestResult computeChiSq(final scala.collection.immutable.Map counts, final String methodName, final int col) {
      scala.collection.immutable.Map label2Index = org.apache.spark.util.collection.Utils..MODULE$.toMapWithIndex(scala.Predef..MODULE$.wrapDoubleArray((double[]).MODULE$.sorted$extension(scala.Predef..MODULE$.doubleArrayOps((double[]).MODULE$.distinct$extension(scala.Predef..MODULE$.doubleArrayOps((double[])counts.iterator().map((x$11) -> BoxesRunTime.boxToDouble($anonfun$computeChiSq$1(x$11))).toArray(scala.reflect.ClassTag..MODULE$.Double())))), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$)));
      int numLabels = label2Index.size();
      if (numLabels > this.maxCategories()) {
         throw new SparkException("Chi-square test expect factors (categorical values) but found more than " + this.maxCategories() + " distinct label values.");
      } else {
         scala.collection.immutable.Map value2Index = org.apache.spark.util.collection.Utils..MODULE$.toMapWithIndex(scala.Predef..MODULE$.wrapDoubleArray((double[]).MODULE$.sorted$extension(scala.Predef..MODULE$.doubleArrayOps((double[]).MODULE$.distinct$extension(scala.Predef..MODULE$.doubleArrayOps((double[])counts.iterator().map((x$12) -> BoxesRunTime.boxToDouble($anonfun$computeChiSq$2(x$12))).toArray(scala.reflect.ClassTag..MODULE$.Double())))), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$)));
         int numValues = value2Index.size();
         if (numValues > this.maxCategories()) {
            int var10002 = this.maxCategories();
            throw new SparkException("Chi-square test expect factors (categorical values) but found more than " + var10002 + " distinct values in column " + col + ".");
         } else {
            DenseMatrix contingency = new DenseMatrix(numValues, numLabels, (double[])scala.Array..MODULE$.ofDim(numValues * numLabels, scala.reflect.ClassTag..MODULE$.Double()));
            counts.foreach((x0$1) -> {
               $anonfun$computeChiSq$3(value2Index, label2Index, contingency, x0$1);
               return BoxedUnit.UNIT;
            });
            return this.chiSquaredMatrix(contingency, methodName);
         }
      }
   }

   public String computeChiSquared$default$2() {
      return this.PEARSON().name();
   }

   public ChiSqTestResult chiSquared(final Vector observed, final Vector expected, final String methodName) {
      Object var4 = new Object();

      ChiSqTestResult var10000;
      try {
         ChiSqTest.Method method = this.methodFromString(methodName);
         if (expected.size() != 0 && observed.size() != expected.size()) {
            throw new IllegalArgumentException("observed and expected must be of the same size.");
         }

         int size = observed.size();
         if (size > 1000) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Chi-squared approximation may not be accurate due to low expected "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"frequencies as a result of a large number of categories: "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_CATEGORIES..MODULE$, BoxesRunTime.boxToInteger(size))}))))));
         }

         double[] obsArr = observed.toArray();
         double[] expArr = expected.size() == 0 ? (double[])scala.Array..MODULE$.tabulate(size, (JFunction1.mcDI.sp)(x$13) -> (double)1.0F / (double)size, scala.reflect.ClassTag..MODULE$.Double()) : expected.toArray();
         if (!.MODULE$.forall$extension(scala.Predef..MODULE$.doubleArrayOps(obsArr), (JFunction1.mcZD.sp)(x$14) -> x$14 >= (double)0.0F)) {
            throw new IllegalArgumentException("Negative entries disallowed in the observed vector.");
         }

         if (expected.size() != 0 && !.MODULE$.forall$extension(scala.Predef..MODULE$.doubleArrayOps(expArr), (JFunction1.mcZD.sp)(x$15) -> x$15 >= (double)0.0F)) {
            throw new IllegalArgumentException("Negative entries disallowed in the expected vector.");
         }

         double obsSum = BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray(obsArr).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
         double expSum = (double)expected.size() == (double)0.0F ? (double)1.0F : BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray(expArr).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
         double scale = scala.math.package..MODULE$.abs(obsSum - expSum) < 1.0E-7 ? (double)1.0F : obsSum / expSum;
         double statistic = BoxesRunTime.unboxToDouble(.MODULE$.foldLeft$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zip$extension(scala.Predef..MODULE$.doubleArrayOps(obsArr), scala.Predef..MODULE$.wrapDoubleArray(expArr))), BoxesRunTime.boxToDouble((double)0.0F), (x0$1, x1$1) -> BoxesRunTime.boxToDouble($anonfun$chiSquared$5(var4, size, scale, method, BoxesRunTime.unboxToDouble(x0$1), x1$1))));
         int df = size - 1;
         double pValue = (double)1.0F - (new ChiSquaredDistribution((double)df)).cumulativeProbability(statistic);
         var10000 = new ChiSqTestResult(pValue, df, statistic, this.PEARSON().name(), ChiSqTest.NullHypothesis$.MODULE$.goodnessOfFit().toString());
      } catch (NonLocalReturnControl var21) {
         if (var21.key() != var4) {
            throw var21;
         }

         var10000 = (ChiSqTestResult)var21.value();
      }

      return var10000;
   }

   public String chiSquaredFeatures$default$2() {
      return this.PEARSON().name();
   }

   private String chiSquaredDenseFeatures$default$3() {
      return this.PEARSON().name();
   }

   private String chiSquaredSparseFeatures$default$3() {
      return this.PEARSON().name();
   }

   public Vector chiSquared$default$2() {
      return Vectors$.MODULE$.dense(scala.Array..MODULE$.emptyDoubleArray());
   }

   public String chiSquared$default$3() {
      return this.PEARSON().name();
   }

   public ChiSqTestResult chiSquaredMatrix(final Matrix counts, final String methodName) {
      ChiSqTest.Method method = this.methodFromString(methodName);
      int numRows = counts.numRows();
      int numCols = counts.numCols();
      double[] colSums = new double[numCols];
      double[] rowSums = new double[numRows];
      double[] colMajorArr = counts.toArray();
      int colMajorArrLen = colMajorArr.length;

      for(int i = 0; i < colMajorArrLen; ++i) {
         double elem = colMajorArr[i];
         if (elem < (double)0.0F) {
            throw new IllegalArgumentException("Contingency table cannot contain negative entries.");
         }

         int var13 = i / numRows;
         colSums[var13] += elem;
         int var14 = i % numRows;
         rowSums[var14] += elem;
      }

      double total = BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray(colSums).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
      double statistic = (double)0.0F;

      for(int j = 0; j < colMajorArrLen; ++j) {
         int col = j / numRows;
         double colSum = colSums[col];
         if (colSum == (double)0.0F) {
            throw new IllegalArgumentException("Chi-squared statistic undefined for input matrix due to0 sum in column [" + col + "].");
         }

         int row = j % numRows;
         double rowSum = rowSums[row];
         if (rowSum == (double)0.0F) {
            throw new IllegalArgumentException("Chi-squared statistic undefined for input matrix due to0 sum in row [" + row + "].");
         }

         double expected = colSum * rowSum / total;
         statistic += method.chiSqFunc().apply$mcDDD$sp(colMajorArr[j], expected);
      }

      int df = (numCols - 1) * (numRows - 1);
      if (df == 0) {
         return new ChiSqTestResult((double)1.0F, 0, (double)0.0F, methodName, ChiSqTest.NullHypothesis$.MODULE$.independence().toString());
      } else {
         double pValue = (double)1.0F - (new ChiSquaredDistribution((double)df)).cumulativeProbability(statistic);
         return new ChiSqTestResult(pValue, df, statistic, methodName, ChiSqTest.NullHypothesis$.MODULE$.independence().toString());
      }
   }

   public String chiSquaredMatrix$default$2() {
      return this.PEARSON().name();
   }

   // $FF: synthetic method
   public static final int $anonfun$chiSquaredFeatures$2(final Tuple5 x$1) {
      return BoxesRunTime.unboxToInt(x$1._1());
   }

   // $FF: synthetic method
   public static final long $anonfun$chiSquaredDenseFeatures$8(final OpenHashMap counts1$1, final Tuple2 x0$5) {
      if (x0$5 != null) {
         Tuple2 t = (Tuple2)x0$5._1();
         long c = x0$5._2$mcJ$sp();
         return counts1$1.changeValue$mcJ$sp(t, (JFunction0.mcJ.sp)() -> c, (JFunction1.mcJJ.sp)(x$3) -> x$3 + c);
      } else {
         throw new MatchError(x0$5);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$chiSquaredSparseFeatures$1(final Tuple2 x$4) {
      return x$4._1$mcD$sp();
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$chiSquaredSparseFeatures$7(final int col) {
      return new Tuple2(BoxesRunTime.boxToInteger(col), (Object)null);
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$chiSquaredSparseFeatures$2(final int numFeatures$2, final int numParts$1, final int x0$1, final Iterator x1$1) {
      Tuple2 var5 = new Tuple2(BoxesRunTime.boxToInteger(x0$1), x1$1);
      if (var5 != null) {
         int pid = var5._1$mcI$sp();
         Iterator iter = (Iterator)var5._2();
         return iter.flatMap((x0$2) -> {
            if (x0$2 != null) {
               double label = x0$2._1$mcD$sp();
               Vector features = (Vector)x0$2._2();
               scala.Predef..MODULE$.require(features.size() == numFeatures$2, () -> "Number of features must be " + numFeatures$2 + " but got " + features.size());
               return features.nonZeroIterator().map((x0$3) -> {
                  if (x0$3 != null) {
                     int col = x0$3._1$mcI$sp();
                     double value = x0$3._2$mcD$sp();
                     return new Tuple2(BoxesRunTime.boxToInteger(col), new Tuple2.mcDD.sp(label, value));
                  } else {
                     throw new MatchError(x0$3);
                  }
               });
            } else {
               throw new MatchError(x0$2);
            }
         }).$plus$plus(() -> scala.package..MODULE$.Iterator().range(pid, numFeatures$2, numParts$1).map((col) -> $anonfun$chiSquaredSparseFeatures$7(BoxesRunTime.unboxToInt(col))));
      } else {
         throw new MatchError(var5);
      }
   }

   // $FF: synthetic method
   public static final long $anonfun$chiSquaredSparseFeatures$12(final OpenHashMap counts1$2, final Tuple2 x0$6) {
      if (x0$6 != null) {
         Tuple2 t = (Tuple2)x0$6._1();
         long c = x0$6._2$mcJ$sp();
         return counts1$2.changeValue$mcJ$sp(t, (JFunction0.mcJ.sp)() -> c, (JFunction1.mcJJ.sp)(x$6) -> x$6 + c);
      } else {
         throw new MatchError(x0$6);
      }
   }

   // $FF: synthetic method
   public static final long $anonfun$chiSquaredSparseFeatures$16(final Tuple2 x$7) {
      return x$7._2$mcJ$sp();
   }

   // $FF: synthetic method
   public static final double $anonfun$chiSquaredSparseFeatures$18(final Tuple2 x$8) {
      return x$8._1$mcD$sp();
   }

   // $FF: synthetic method
   public static final long $anonfun$chiSquaredSparseFeatures$20(final Tuple2 x$10) {
      return x$10._2$mcJ$sp();
   }

   // $FF: synthetic method
   public static final long $anonfun$chiSquaredSparseFeatures$19(final double x$9, final Tuple2[] v) {
      return BoxesRunTime.unboxToLong(scala.Predef..MODULE$.wrapLongArray((long[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])v), (x$10) -> BoxesRunTime.boxToLong($anonfun$chiSquaredSparseFeatures$20(x$10)), scala.reflect.ClassTag..MODULE$.Long())).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
   }

   // $FF: synthetic method
   public static final void $anonfun$chiSquaredSparseFeatures$21(final scala.collection.immutable.Map labelNNZ$1, final OpenHashMap counts$1, final Tuple2 x0$9) {
      if (x0$9 != null) {
         double label = x0$9._1$mcD$sp();
         long countByLabel = x0$9._2$mcJ$sp();
         long nnzByLabel = BoxesRunTime.unboxToLong(labelNNZ$1.getOrElse(BoxesRunTime.boxToDouble(label), (JFunction0.mcJ.sp)() -> 0L));
         long nzByLabel = countByLabel - nnzByLabel;
         if (nzByLabel > 0L) {
            counts$1.update$mcJ$sp(new Tuple2.mcDD.sp(label, (double)0.0F), nzByLabel);
            BoxedUnit var13 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x0$9);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$computeChiSq$1(final Tuple2 x$11) {
      return ((Tuple2)x$11._1())._1$mcD$sp();
   }

   // $FF: synthetic method
   public static final double $anonfun$computeChiSq$2(final Tuple2 x$12) {
      return ((Tuple2)x$12._1())._2$mcD$sp();
   }

   // $FF: synthetic method
   public static final void $anonfun$computeChiSq$3(final scala.collection.immutable.Map value2Index$1, final scala.collection.immutable.Map label2Index$1, final DenseMatrix contingency$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Tuple2 var6 = (Tuple2)x0$1._1();
         long c = x0$1._2$mcJ$sp();
         if (var6 != null) {
            double label = var6._1$mcD$sp();
            double value = var6._2$mcD$sp();
            int i = BoxesRunTime.unboxToInt(value2Index$1.apply(BoxesRunTime.boxToDouble(value)));
            int j = BoxesRunTime.unboxToInt(label2Index$1.apply(BoxesRunTime.boxToDouble(label)));
            contingency$1.update(i, j, (double)c);
            BoxedUnit var10000 = BoxedUnit.UNIT;
            return;
         }
      }

      throw new MatchError(x0$1);
   }

   // $FF: synthetic method
   public static final double $anonfun$chiSquared$5(final Object nonLocalReturnKey1$1, final int size$1, final double scale$1, final ChiSqTest.Method method$1, final double x0$1, final Tuple2 x1$1) {
      Tuple2 var10 = new Tuple2(BoxesRunTime.boxToDouble(x0$1), x1$1);
      if (var10 != null) {
         double stat = var10._1$mcD$sp();
         Tuple2 var13 = (Tuple2)var10._2();
         if (var13 != null) {
            double obs = var13._1$mcD$sp();
            double exp = var13._2$mcD$sp();
            if (exp == (double)0.0F) {
               if (obs == (double)0.0F) {
                  throw new IllegalArgumentException("Chi-squared statistic undefined for input vectors due to 0.0 values in both observed and expected.");
               }

               throw new NonLocalReturnControl(nonLocalReturnKey1$1, new ChiSqTestResult((double)0.0F, size$1 - 1, Double.POSITIVE_INFINITY, MODULE$.PEARSON().name(), ChiSqTest.NullHypothesis$.MODULE$.goodnessOfFit().toString()));
            }

            if (scale$1 == (double)1.0F) {
               return stat + method$1.chiSqFunc().apply$mcDDD$sp(obs, exp);
            }

            return stat + method$1.chiSqFunc().apply$mcDDD$sp(obs, exp * scale$1);
         }
      }

      throw new MatchError(var10);
   }

   private ChiSqTest$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
