package org.apache.spark.mllib.evaluation;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.rdd.RDD;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.;
import scala.collection.immutable.Set;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ef\u0001\u0002\n\u0014\u0001yA\u0001\u0002\u000f\u0001\u0003\u0002\u0003\u0006I!\u000f\u0005\t\u0017\u0002\u0011\u0019\u0011)A\u0006\u0019\")\u0011\f\u0001C\u00015\"9Q\b\u0001b\u0001\n\u0013i\u0007BB=\u0001A\u0003%a\u000eC\u0003{\u0001\u0011\u00051\u0010\u0003\u0006\u0002\u0006\u0001A)\u0019!C\u0001\u0003\u000fAq!a\u0003\u0001\t\u0003\ti\u0001C\u0004\u0002\u0018\u0001!I!!\u0007\t\u000f\u0005U\u0002\u0001\"\u0001\u00028!9\u0011Q\b\u0001\u0005\u0002\u0005}\u0002bBA#\u0001\u0011%\u0011qI\u0004\b\u0003+\u001a\u0002\u0012AA,\r\u0019\u00112\u0003#\u0001\u0002Z!1\u0011L\u0004C\u0001\u0003SBq!a\u001b\u000f\t\u0003\ti\u0007C\u0005\u00020:\t\t\u0011\"\u0003\u00022\nq!+\u00198lS:<W*\u001a;sS\u000e\u001c(B\u0001\u000b\u0016\u0003))g/\u00197vCRLwN\u001c\u0006\u0003-]\tQ!\u001c7mS\nT!\u0001G\r\u0002\u000bM\u0004\u0018M]6\u000b\u0005iY\u0012AB1qC\u000eDWMC\u0001\u001d\u0003\ry'oZ\u0002\u0001+\ty2k\u0005\u0003\u0001A\u0019b\u0003CA\u0011%\u001b\u0005\u0011#\"A\u0012\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0015\u0012#AB!osJ+g\r\u0005\u0002(U5\t\u0001F\u0003\u0002*/\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002,Q\t9Aj\\4hS:<\u0007CA\u00176\u001d\tq3G\u0004\u00020e5\t\u0001G\u0003\u00022;\u00051AH]8pizJ\u0011aI\u0005\u0003i\t\nq\u0001]1dW\u0006<W-\u0003\u00027o\ta1+\u001a:jC2L'0\u00192mK*\u0011AGI\u0001\u0014aJ,G-[2uS>t\u0017I\u001c3MC\n,Gn\u001d\u0019\u0003u\t\u00032a\u000f A\u001b\u0005a$BA\u001f\u0018\u0003\r\u0011H\rZ\u0005\u0003\u007fq\u00121A\u0015#E!\t\t%\t\u0004\u0001\u0005\u0013\r\u000b\u0011\u0011!A\u0001\u0006\u0003!%aA0%cE\u0011Q\t\u0013\t\u0003C\u0019K!a\u0012\u0012\u0003\u000f9{G\u000f[5oOB\u0011\u0011%S\u0005\u0003\u0015\n\u0012q\u0001\u0015:pIV\u001cG/\u0001\u0006fm&$WM\\2fIE\u00022!\u0014)S\u001b\u0005q%BA(#\u0003\u001d\u0011XM\u001a7fGRL!!\u0015(\u0003\u0011\rc\u0017m]:UC\u001e\u0004\"!Q*\u0005\u000bQ\u0003!\u0019A+\u0003\u0003Q\u000b\"!\u0012,\u0011\u0005\u0005:\u0016B\u0001-#\u0005\r\te._\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005m{FC\u0001/_!\ri\u0006AU\u0007\u0002'!)1j\u0001a\u0002\u0019\")\u0001h\u0001a\u0001AB\u0012\u0011m\u0019\t\u0004wy\u0012\u0007CA!d\t%\u0019u,!A\u0001\u0002\u000b\u0005A\tK\u0002\u0004K.\u0004\"AZ5\u000e\u0003\u001dT!\u0001[\f\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002kO\n)1+\u001b8dK\u0006\nA.A\u00032]Ir\u0003'F\u0001o!\rYdh\u001c\t\u0006CA\u0014(/^\u0005\u0003c\n\u0012a\u0001V;qY\u0016\u001c\u0004cA\u0011t%&\u0011AO\t\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0004CM4\bCA\u0011x\u0013\tA(E\u0001\u0004E_V\u0014G.Z\u0001\u0005e\u0012$\u0007%A\u0006qe\u0016\u001c\u0017n]5p]\u0006#HC\u0001<}\u0011\u0015ih\u00011\u0001\u007f\u0003\u0005Y\u0007CA\u0011\u0000\u0013\r\t\tA\t\u0002\u0004\u0013:$\bf\u0001\u0004fW\u0006!R.Z1o\u0003Z,'/Y4f!J,7-[:j_:,\u0012A\u001e\u0015\u0004\u000f\u0015\\\u0017AF7fC:\fe/\u001a:bO\u0016\u0004&/Z2jg&|g.\u0011;\u0015\u0007Y\fy\u0001C\u0003~\u0011\u0001\u0007a\u0010\u000b\u0003\tK\u0006M\u0011EAA\u000b\u0003\u0015\u0019d\u0006\r\u00181\u0003A\tg/\u001a:bO\u0016\u0004&/Z2jg&|g\u000eF\u0004w\u00037\ty\"a\r\t\r\u0005u\u0011\u00021\u0001s\u0003\u0011\u0001(/\u001a3\t\u000f\u0005\u0005\u0012\u00021\u0001\u0002$\u0005\u0019A.\u00192\u0011\u000b\u0005\u0015\u0012Q\u0006*\u000f\t\u0005\u001d\u0012\u0011\u0006\t\u0003_\tJ1!a\u000b#\u0003\u0019\u0001&/\u001a3fM&!\u0011qFA\u0019\u0005\r\u0019V\r\u001e\u0006\u0004\u0003W\u0011\u0003\"B?\n\u0001\u0004q\u0018A\u00028eG\u001e\fE\u000fF\u0002w\u0003sAQ! \u0006A\u0002yD3AC3l\u0003!\u0011XmY1mY\u0006#Hc\u0001<\u0002B!)Qp\u0003a\u0001}\"\"1\"ZA\n\u0003Y\u0019w.\u001e8u%\u0016dWM^1oi&#X-\u001c*bi&|G#\u0003<\u0002J\u0005-\u0013QJA(\u0011\u0019\ti\u0002\u0004a\u0001e\"1\u0011\u0011\u0005\u0007A\u0002IDQ! \u0007A\u0002yDa!!\u0015\r\u0001\u0004q\u0018a\u00033f]>l\u0017N\\1u_JD3\u0001A3l\u00039\u0011\u0016M\\6j]\u001elU\r\u001e:jGN\u0004\"!\u0018\b\u0014\t9\u0001\u00131\f\t\u0005\u0003;\n9'\u0004\u0002\u0002`)!\u0011\u0011MA2\u0003\tIwN\u0003\u0002\u0002f\u0005!!.\u0019<b\u0013\r1\u0014q\f\u000b\u0003\u0003/\n!a\u001c4\u0016\u0011\u0005=\u0014QOAI\u0003C#B!!\u001d\u0002zA!Q\fAA:!\r\t\u0015Q\u000f\u0003\u0007\u0003o\u0002\"\u0019A+\u0003\u0003\u0015Ca\u0001\u000f\tA\u0002\u0005m\u0004\u0007BA?\u0003\u001b\u0003b!a \u0002\b\u0006-UBAAA\u0015\u0011\t)'a!\u000b\u0007\u0005\u0015u#A\u0002ba&LA!!#\u0002\u0002\n9!*\u0019<b%\u0012#\u0005cA!\u0002\u000e\u0012Y\u0011qRA=\u0003\u0003\u0005\tQ!\u0001E\u0005\ryFE\r\u0003\u0007)B\u0011\r!a%\u0012\u0007\u0015\u000b)\n\u0005\u0004\u0002\u0018\u0006u\u00151O\u0007\u0003\u00033SA!a'\u0002d\u0005!A.\u00198h\u0013\u0011\ty*!'\u0003\u0011%#XM]1cY\u0016$q!a)\u0011\u0005\u0004\t)KA\u0001B#\r)\u0015q\u0015\t\u0006\u0003/\u000biJ\u001e\u0015\u0005!\u0015\fY+\t\u0002\u0002.\u0006)\u0011G\f\u001b/a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u0017\t\u0005\u0003/\u000b),\u0003\u0003\u00028\u0006e%AB(cU\u0016\u001cG\u000f"
)
public class RankingMetrics implements Logging, Serializable {
   private double meanAveragePrecision;
   private final RDD rdd;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile boolean bitmap$0;

   public static RankingMetrics of(final JavaRDD predictionAndLabels) {
      return RankingMetrics$.MODULE$.of(predictionAndLabels);
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

   private RDD rdd() {
      return this.rdd;
   }

   public double precisionAt(final int k) {
      .MODULE$.require(k > 0, () -> "ranking position k should be positive");
      return org.apache.spark.rdd.RDD..MODULE$.doubleRDDToDoubleRDDFunctions(this.rdd().map((x0$1) -> BoxesRunTime.boxToDouble($anonfun$precisionAt$2(this, k, x0$1)), scala.reflect.ClassTag..MODULE$.Double())).mean();
   }

   private double meanAveragePrecision$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.meanAveragePrecision = org.apache.spark.rdd.RDD..MODULE$.doubleRDDToDoubleRDDFunctions(this.rdd().map((x0$1) -> BoxesRunTime.boxToDouble($anonfun$meanAveragePrecision$1(this, x0$1)), scala.reflect.ClassTag..MODULE$.Double())).mean();
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.meanAveragePrecision;
   }

   public double meanAveragePrecision() {
      return !this.bitmap$0 ? this.meanAveragePrecision$lzycompute() : this.meanAveragePrecision;
   }

   public double meanAveragePrecisionAt(final int k) {
      .MODULE$.require(k > 0, () -> "ranking position k should be positive");
      return org.apache.spark.rdd.RDD..MODULE$.doubleRDDToDoubleRDDFunctions(this.rdd().map((x0$1) -> BoxesRunTime.boxToDouble($anonfun$meanAveragePrecisionAt$2(this, k, x0$1)), scala.reflect.ClassTag..MODULE$.Double())).mean();
   }

   private double averagePrecision(final Object pred, final Set lab, final int k) {
      if (lab.nonEmpty()) {
         int i = 0;
         int cnt = 0;
         double precSum = (double)0.0F;

         for(int n = scala.math.package..MODULE$.min(k, scala.runtime.ScalaRunTime..MODULE$.array_length(pred)); i < n; ++i) {
            if (lab.contains(scala.runtime.ScalaRunTime..MODULE$.array_apply(pred, i))) {
               ++cnt;
               precSum += (double)cnt / (double)(i + 1);
            }
         }

         return precSum / (double)scala.math.package..MODULE$.min(lab.size(), k);
      } else {
         this.logWarning((Function0)(() -> "Empty ground truth set, check input data"));
         return (double)0.0F;
      }
   }

   public double ndcgAt(final int k) {
      .MODULE$.require(k > 0, () -> "ranking position k should be positive");
      return org.apache.spark.rdd.RDD..MODULE$.doubleRDDToDoubleRDDFunctions(this.rdd().map((x0$1) -> BoxesRunTime.boxToDouble($anonfun$ndcgAt$2(this, k, x0$1)), scala.reflect.ClassTag..MODULE$.Double())).mean();
   }

   public double recallAt(final int k) {
      .MODULE$.require(k > 0, () -> "ranking position k should be positive");
      return org.apache.spark.rdd.RDD..MODULE$.doubleRDDToDoubleRDDFunctions(this.rdd().map((x0$1) -> BoxesRunTime.boxToDouble($anonfun$recallAt$2(this, k, x0$1)), scala.reflect.ClassTag..MODULE$.Double())).mean();
   }

   private double countRelevantItemRatio(final Object pred, final Object lab, final int k, final int denominator) {
      Set labSet = .MODULE$.genericWrapArray(lab).toSet();
      if (labSet.nonEmpty()) {
         int n = scala.math.package..MODULE$.min(scala.runtime.ScalaRunTime..MODULE$.array_length(pred), k);
         int i = 0;

         int cnt;
         for(cnt = 0; i < n; ++i) {
            if (labSet.contains(scala.runtime.ScalaRunTime..MODULE$.array_apply(pred, i))) {
               ++cnt;
            }
         }

         return (double)cnt / (double)denominator;
      } else {
         this.logWarning((Function0)(() -> "Empty ground truth set, check input data"));
         return (double)0.0F;
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$precisionAt$2(final RankingMetrics $this, final int k$1, final Tuple3 x0$1) {
      if (x0$1 != null) {
         Object pred = x0$1._1();
         Object lab = x0$1._2();
         return $this.countRelevantItemRatio(pred, lab, k$1, k$1);
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$meanAveragePrecision$1(final RankingMetrics $this, final Tuple3 x0$1) {
      if (x0$1 != null) {
         Object pred = x0$1._1();
         Object lab = x0$1._2();
         Set labSet = .MODULE$.genericWrapArray(lab).toSet();
         int k = scala.math.package..MODULE$.max(scala.runtime.ScalaRunTime..MODULE$.array_length(pred), labSet.size());
         return $this.averagePrecision(pred, labSet, k);
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$meanAveragePrecisionAt$2(final RankingMetrics $this, final int k$2, final Tuple3 x0$1) {
      if (x0$1 != null) {
         Object pred = x0$1._1();
         Object lab = x0$1._2();
         return $this.averagePrecision(pred, .MODULE$.genericWrapArray(lab).toSet(), k$2);
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$ndcgAt$2(final RankingMetrics $this, final int k$3, final Tuple3 x0$1) {
      if (x0$1 != null) {
         Object pred = x0$1._1();
         Object lab = x0$1._2();
         double[] rel = (double[])x0$1._3();
         boolean useBinary = scala.collection.ArrayOps..MODULE$.isEmpty$extension(.MODULE$.doubleArrayOps(rel));
         Set labSet = .MODULE$.genericWrapArray(lab).toSet();
         scala.collection.immutable.Map relMap = org.apache.spark.util.collection.Utils..MODULE$.toMap(.MODULE$.genericWrapArray(lab), .MODULE$.wrapDoubleArray(rel));
         if (!useBinary && scala.runtime.ScalaRunTime..MODULE$.array_length(lab) != rel.length) {
            $this.logWarning((Function0)(() -> "# of ground truth set and # of relevance value set should be equal, check input data"));
         }

         if (labSet.nonEmpty()) {
            int labSetSize = labSet.size();
            int n = scala.math.package..MODULE$.min(scala.math.package..MODULE$.max(scala.runtime.ScalaRunTime..MODULE$.array_length(pred), labSetSize), k$3);
            double maxDcg = (double)0.0F;
            double dcg = (double)0.0F;

            for(int i = 0; i < n; ++i) {
               if (useBinary) {
                  double gain = (double)1.0F / scala.math.package..MODULE$.log((double)(i + 2));
                  if (i < scala.runtime.ScalaRunTime..MODULE$.array_length(pred) && labSet.contains(scala.runtime.ScalaRunTime..MODULE$.array_apply(pred, i))) {
                     dcg += gain;
                  }

                  if (i < labSetSize) {
                     maxDcg += gain;
                  }
               } else {
                  if (i < scala.runtime.ScalaRunTime..MODULE$.array_length(pred)) {
                     dcg += (scala.math.package..MODULE$.pow((double)2.0F, BoxesRunTime.unboxToDouble(relMap.getOrElse(scala.runtime.ScalaRunTime..MODULE$.array_apply(pred, i), (JFunction0.mcD.sp)() -> (double)0.0F))) - (double)1) / scala.math.package..MODULE$.log((double)(i + 2));
                  }

                  if (i < labSetSize) {
                     maxDcg += (scala.math.package..MODULE$.pow((double)2.0F, BoxesRunTime.unboxToDouble(relMap.getOrElse(scala.runtime.ScalaRunTime..MODULE$.array_apply(lab, i), (JFunction0.mcD.sp)() -> (double)0.0F))) - (double)1) / scala.math.package..MODULE$.log((double)(i + 2));
                  }
               }
            }

            if (maxDcg == (double)0.0F) {
               $this.logWarning((Function0)(() -> "Maximum of relevance of ground truth set is zero, check input data"));
               return (double)0.0F;
            } else {
               return dcg / maxDcg;
            }
         } else {
            $this.logWarning((Function0)(() -> "Empty ground truth set, check input data"));
            return (double)0.0F;
         }
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$recallAt$2(final RankingMetrics $this, final int k$4, final Tuple3 x0$1) {
      if (x0$1 != null) {
         Object pred = x0$1._1();
         Object lab = x0$1._2();
         return $this.countRelevantItemRatio(pred, lab, k$4, .MODULE$.genericWrapArray(lab).toSet().size());
      } else {
         throw new MatchError(x0$1);
      }
   }

   public RankingMetrics(final RDD predictionAndLabels, final ClassTag evidence$1) {
      Logging.$init$(this);
      this.rdd = predictionAndLabels.map((x0$1) -> {
         if (x0$1 instanceof Tuple2 var3) {
            Object pred = var3._1();
            Object lab = var3._2();
            if (scala.runtime.ScalaRunTime..MODULE$.isArray(pred, 1) && scala.runtime.ScalaRunTime..MODULE$.isArray(lab, 1)) {
               return new Tuple3(pred, lab, scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Double()));
            }
         }

         if (x0$1 instanceof Tuple3 var8) {
            Object pred = var8._1();
            Object lab = var8._2();
            Object rel = var8._3();
            if (scala.runtime.ScalaRunTime..MODULE$.isArray(pred, 1) && scala.runtime.ScalaRunTime..MODULE$.isArray(lab, 1) && rel instanceof double[] var14) {
               return new Tuple3(pred, lab, var14);
            }
         }

         throw new IllegalArgumentException("Expected RDD of tuples or triplets");
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple3.class));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
