package org.apache.spark.ui;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.Semaphore;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkContext$;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.package$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.rdd.RDD$;
import org.apache.spark.scheduler.SchedulingMode$;
import scala.Enumeration;
import scala.Function0;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Seq;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;
import scala.util.Random;

public final class UIWorkloadGenerator$ {
   public static final UIWorkloadGenerator$ MODULE$ = new UIWorkloadGenerator$();
   private static final int NUM_PARTITIONS = 100;
   private static final int INTER_JOB_WAIT_MS = 5000;

   public int NUM_PARTITIONS() {
      return NUM_PARTITIONS;
   }

   public int INTER_JOB_WAIT_MS() {
      return INTER_JOB_WAIT_MS;
   }

   public void main(final String[] args) {
      if (args.length < 3) {
         .MODULE$.println("Usage: ./bin/spark-class org.apache.spark.ui.UIWorkloadGenerator [master] [FIFO|FAIR] [#job set (4 jobs per set)]");
         System.exit(1);
      }

      SparkConf conf;
      Enumeration.Value schedulingMode;
      label20: {
         label19: {
            conf = (new SparkConf()).setMaster(args[0]).setAppName("Spark UI tester");
            schedulingMode = SchedulingMode$.MODULE$.withName(args[1]);
            Enumeration.Value var4 = SchedulingMode$.MODULE$.FAIR();
            if (schedulingMode == null) {
               if (var4 == null) {
                  break label19;
               }
            } else if (schedulingMode.equals(var4)) {
               break label19;
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
            break label20;
         }

         conf.set((ConfigEntry)package$.MODULE$.SCHEDULER_MODE(), (Object)"FAIR");
      }

      int nJobSet = scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(args[2]));
      SparkContext sc = new SparkContext(conf);
      RDD baseData = sc.makeRDD(scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(1), this.NUM_PARTITIONS() * 10), this.NUM_PARTITIONS(), scala.reflect.ClassTag..MODULE$.Int());
      Seq jobs = new scala.collection.immutable..colon.colon(new Tuple2("Count", (JFunction0.mcJ.sp)() -> baseData.count()), new scala.collection.immutable..colon.colon(new Tuple2("Cache and Count", (JFunction0.mcJ.sp)() -> baseData.map((JFunction1.mcII.sp)(x) -> x, scala.reflect.ClassTag..MODULE$.Int()).cache().count()), new scala.collection.immutable..colon.colon(new Tuple2("Single Shuffle", (JFunction0.mcJ.sp)() -> RDD$.MODULE$.rddToPairRDDFunctions(baseData.map((x) -> $anonfun$main$5(BoxesRunTime.unboxToInt(x)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.Int(), scala.math.Ordering.Int..MODULE$).reduceByKey((JFunction2.mcIII.sp)(x$1, x$2) -> x$1 + x$2).count()), new scala.collection.immutable..colon.colon(new Tuple2("Entirely failed phase", (JFunction0.mcJ.sp)() -> baseData.map((JFunction1.mcII.sp)(x) -> {
            throw new Exception();
         }, scala.reflect.ClassTag..MODULE$.Int()).count()), new scala.collection.immutable..colon.colon(new Tuple2("Partially failed phase", (JFunction0.mcJ.sp)() -> baseData.map((JFunction1.mcII.sp)(x) -> {
            double probFailure = (double)4.0F / (double)MODULE$.NUM_PARTITIONS();
            if ((double)nextFloat$1() < probFailure) {
               throw new Exception("This is a task failure");
            } else {
               return 1;
            }
         }, scala.reflect.ClassTag..MODULE$.Int()).count()), new scala.collection.immutable..colon.colon(new Tuple2("Partially failed phase (longer tasks)", (JFunction0.mcJ.sp)() -> baseData.map((JFunction1.mcII.sp)(x) -> {
            double probFailure = (double)4.0F / (double)MODULE$.NUM_PARTITIONS();
            if ((double)nextFloat$1() < probFailure) {
               Thread.sleep(100L);
               throw new Exception("This is a task failure");
            } else {
               return 1;
            }
         }, scala.reflect.ClassTag..MODULE$.Int()).count()), new scala.collection.immutable..colon.colon(new Tuple2("Job with delays", (JFunction0.mcJ.sp)() -> baseData.map((JFunction1.mcVI.sp)(x) -> Thread.sleep(100L), scala.reflect.ClassTag..MODULE$.Unit()).count()), scala.collection.immutable.Nil..MODULE$)))))));
      Semaphore barrier = new Semaphore(-nJobSet * jobs.size() + 1);
      scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(1), nJobSet).foreach$mVc$sp((JFunction1.mcVI.sp)(x$3) -> jobs.withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$main$16(check$ifrefutable$1))).foreach((x$4) -> {
            $anonfun$main$17(barrier, schedulingMode, sc, x$4);
            return BoxedUnit.UNIT;
         }));
      barrier.acquire();
      sc.stop();
   }

   public static final void org$apache$spark$ui$UIWorkloadGenerator$$setProperties$1(final String s, final Enumeration.Value schedulingMode$1, final SparkContext sc$1) {
      label14: {
         Enumeration.Value var3 = SchedulingMode$.MODULE$.FAIR();
         if (schedulingMode$1 == null) {
            if (var3 != null) {
               break label14;
            }
         } else if (!schedulingMode$1.equals(var3)) {
            break label14;
         }

         sc$1.setLocalProperty(SparkContext$.MODULE$.SPARK_SCHEDULER_POOL(), s);
      }

      sc$1.setLocalProperty(SparkContext$.MODULE$.SPARK_JOB_DESCRIPTION(), s);
   }

   private static final float nextFloat$1() {
      return (new Random()).nextFloat();
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$main$5(final int x) {
      return new Tuple2.mcII.sp(x % 10, x);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$main$16(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final void $anonfun$main$17(final Semaphore barrier$1, final Enumeration.Value schedulingMode$1, final SparkContext sc$1, final Tuple2 x$4) {
      if (x$4 != null) {
         String desc = (String)x$4._1();
         Function0 job = (Function0)x$4._2();
         (new Thread(desc, job, barrier$1, schedulingMode$1, sc$1) {
            private final String desc$1;
            private final Function0 job$1;
            private final Semaphore barrier$1;
            private final Enumeration.Value schedulingMode$1;
            private final SparkContext sc$1;

            public void run() {
               try {
                  UIWorkloadGenerator$.org$apache$spark$ui$UIWorkloadGenerator$$setProperties$1(this.desc$1, this.schedulingMode$1, this.sc$1);
                  this.job$1.apply$mcJ$sp();
                  .MODULE$.println("Job finished: " + this.desc$1);
               } catch (Exception var5) {
                  .MODULE$.println("Job Failed: " + this.desc$1);
               } finally {
                  this.barrier$1.release();
               }

            }

            public {
               this.desc$1 = desc$1;
               this.job$1 = job$1;
               this.barrier$1 = barrier$1;
               this.schedulingMode$1 = schedulingMode$1;
               this.sc$1 = sc$1;
            }
         }).start();
         Thread.sleep((long)MODULE$.INTER_JOB_WAIT_MS());
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$4);
      }
   }

   private UIWorkloadGenerator$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
