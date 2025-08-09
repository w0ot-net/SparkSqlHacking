package org.apache.spark.util;

import java.lang.invoke.SerializedLambda;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import scala.Function1;
import scala.MatchError;
import scala.Tuple3;
import scala.Tuple4;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.ArrayOps.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

public final class MetricUtils$ {
   public static final MetricUtils$ MODULE$ = new MetricUtils$();
   private static final String SUM_METRIC = "sum";
   private static final String SIZE_METRIC = "size";
   private static final String TIMING_METRIC = "timing";
   private static final String NS_TIMING_METRIC = "nsTiming";
   private static final String AVERAGE_METRIC = "average";
   private static final int baseForAvgMetric = 10;
   private static final String METRICS_NAME_SUFFIX = "(min, med, max (stageId: taskId))";

   public String SUM_METRIC() {
      return SUM_METRIC;
   }

   public String SIZE_METRIC() {
      return SIZE_METRIC;
   }

   public String TIMING_METRIC() {
      return TIMING_METRIC;
   }

   public String NS_TIMING_METRIC() {
      return NS_TIMING_METRIC;
   }

   public String AVERAGE_METRIC() {
      return AVERAGE_METRIC;
   }

   private int baseForAvgMetric() {
      return baseForAvgMetric;
   }

   private String METRICS_NAME_SUFFIX() {
      return METRICS_NAME_SUFFIX;
   }

   private String toNumberFormat(final long value) {
      NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
      return numberFormat.format((double)value / (double)this.baseForAvgMetric());
   }

   public boolean metricNeedsMax(final String metricsType) {
      boolean var10000;
      label23: {
         String var2 = this.SUM_METRIC();
         if (metricsType == null) {
            if (var2 != null) {
               break label23;
            }
         } else if (!metricsType.equals(var2)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   public String stringValue(final String metricsType, final long[] values, final long[] maxMetrics) {
      label106: {
         String taskInfo = .MODULE$.isEmpty$extension(scala.Predef..MODULE$.longArrayOps(maxMetrics)) ? "(driver)" : "(stage " + maxMetrics[1] + "." + maxMetrics[2] + ": task " + maxMetrics[3] + ")";
         String var7 = this.SUM_METRIC();
         if (metricsType == null) {
            if (var7 == null) {
               break label106;
            }
         } else if (metricsType.equals(var7)) {
            break label106;
         }

         label117: {
            String var9 = this.AVERAGE_METRIC();
            if (metricsType == null) {
               if (var9 == null) {
                  break label117;
               }
            } else if (metricsType.equals(var9)) {
               break label117;
            }

            Function1 var10000;
            label87: {
               label109: {
                  String var21 = this.SIZE_METRIC();
                  if (metricsType == null) {
                     if (var21 == null) {
                        break label109;
                     }
                  } else if (metricsType.equals(var21)) {
                     break label109;
                  }

                  label110: {
                     String var22 = this.TIMING_METRIC();
                     if (metricsType == null) {
                        if (var22 == null) {
                           break label110;
                        }
                     } else if (metricsType.equals(var22)) {
                        break label110;
                     }

                     String var23 = this.NS_TIMING_METRIC();
                     if (metricsType == null) {
                        if (var23 != null) {
                           throw org.apache.spark.SparkException..MODULE$.internalError("unexpected metrics type: " + metricsType);
                        }
                     } else if (!metricsType.equals(var23)) {
                        throw org.apache.spark.SparkException..MODULE$.internalError("unexpected metrics type: " + metricsType);
                     }

                     var10000 = (duration) -> $anonfun$stringValue$5(BoxesRunTime.unboxToLong(duration));
                     break label87;
                  }

                  var10000 = (ms) -> $anonfun$stringValue$4(BoxesRunTime.unboxToLong(ms));
                  break label87;
               }

               var10000 = (size) -> $anonfun$stringValue$3(BoxesRunTime.unboxToLong(size));
            }

            Function1 strFormat = var10000;
            long[] validValues = (long[]).MODULE$.filter$extension(scala.Predef..MODULE$.longArrayOps(values), (JFunction1.mcZJ.sp)(x$3) -> x$3 >= 0L);
            if (validValues.length <= 1) {
               return (String)strFormat.apply(.MODULE$.headOption$extension(scala.Predef..MODULE$.longArrayOps(validValues)).getOrElse((JFunction0.mcJ.sp)() -> 0L));
            }

            Arrays.sort(validValues);
            scala.collection.immutable..colon.colon var26 = new scala.collection.immutable..colon.colon((String)strFormat.apply(scala.Predef..MODULE$.wrapLongArray(validValues).sum(scala.math.Numeric.LongIsIntegral..MODULE$)), new scala.collection.immutable..colon.colon((String)strFormat.apply(BoxesRunTime.boxToLong(validValues[0])), new scala.collection.immutable..colon.colon((String)strFormat.apply(BoxesRunTime.boxToLong(validValues[validValues.length / 2])), new scala.collection.immutable..colon.colon((String)strFormat.apply(BoxesRunTime.boxToLong(validValues[validValues.length - 1])), scala.collection.immutable.Nil..MODULE$))));
            if (var26 != null) {
               SeqOps var27 = scala.package..MODULE$.Seq().unapplySeq(var26);
               if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var27) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var27)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var27), 4) == 0) {
                  String sum = (String)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var27), 0);
                  String min = (String)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var27), 1);
                  String med = (String)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var27), 2);
                  String max = (String)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var27), 3);
                  Tuple4 var25 = new Tuple4(sum, min, med, max);
                  String sum = (String)var25._1();
                  String min = (String)var25._2();
                  String med = (String)var25._3();
                  String max = (String)var25._4();
                  return "total " + this.METRICS_NAME_SUFFIX() + "\n" + sum + " (" + min + ", " + med + ", " + max + " " + taskInfo + ")";
               }
            }

            throw new MatchError(var26);
         }

         long[] validValues = (long[]).MODULE$.filter$extension(scala.Predef..MODULE$.longArrayOps(values), (JFunction1.mcZJ.sp)(x$1) -> x$1 > 0L);
         if (validValues.length <= 1) {
            return this.toNumberFormat(BoxesRunTime.unboxToLong(.MODULE$.headOption$extension(scala.Predef..MODULE$.longArrayOps(validValues)).getOrElse((JFunction0.mcJ.sp)() -> 0L)));
         }

         Arrays.sort(validValues);
         scala.collection.immutable..colon.colon var12 = new scala.collection.immutable..colon.colon(this.toNumberFormat(validValues[0]), new scala.collection.immutable..colon.colon(this.toNumberFormat(validValues[validValues.length / 2]), new scala.collection.immutable..colon.colon(this.toNumberFormat(validValues[validValues.length - 1]), scala.collection.immutable.Nil..MODULE$)));
         if (var12 != null) {
            SeqOps var13 = scala.package..MODULE$.Seq().unapplySeq(var12);
            if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var13) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var13)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var13), 3) == 0) {
               String min = (String)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var13), 0);
               String med = (String)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var13), 1);
               String max = (String)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var13), 2);
               Tuple3 var11 = new Tuple3(min, med, max);
               String min = (String)var11._1();
               String med = (String)var11._2();
               String max = (String)var11._3();
               return this.METRICS_NAME_SUFFIX() + ":\n(" + min + ", " + med + ", " + max + " " + taskInfo + ")";
            }
         }

         throw new MatchError(var12);
      }

      NumberFormat numberFormat = NumberFormat.getIntegerInstance(Locale.US);
      return numberFormat.format(BoxesRunTime.unboxToLong(scala.Predef..MODULE$.wrapLongArray(values).sum(scala.math.Numeric.LongIsIntegral..MODULE$)));
   }

   // $FF: synthetic method
   public static final String $anonfun$stringValue$3(final long size) {
      return Utils$.MODULE$.bytesToString(size);
   }

   // $FF: synthetic method
   public static final String $anonfun$stringValue$4(final long ms) {
      return Utils$.MODULE$.msDurationToString(ms);
   }

   // $FF: synthetic method
   public static final String $anonfun$stringValue$5(final long duration) {
      return Utils$.MODULE$.msDurationToString((new scala.concurrent.duration.package.DurationLong(scala.concurrent.duration.package..MODULE$.DurationLong(duration))).nanos().toMillis());
   }

   private MetricUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
