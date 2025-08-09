package org.apache.spark.scheduler;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ExecutorAllocationClient;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.resource.ResourceProfile;
import org.apache.spark.resource.ResourceProfile$;
import org.apache.spark.resource.TaskResourceRequest;
import org.apache.spark.util.Clock;
import org.apache.spark.util.SystemClock;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.SeqOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.HashMap;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Null;
import scala.runtime.java8.JFunction0;

public final class TaskSchedulerImpl$ {
   public static final TaskSchedulerImpl$ MODULE$ = new TaskSchedulerImpl$();
   private static final String SCHEDULER_MODE_PROPERTY;

   static {
      SCHEDULER_MODE_PROPERTY = org.apache.spark.internal.config.package$.MODULE$.SCHEDULER_MODE().key();
   }

   public boolean $lessinit$greater$default$3() {
      return false;
   }

   public Clock $lessinit$greater$default$4() {
      return new SystemClock();
   }

   public String SCHEDULER_MODE_PROPERTY() {
      return SCHEDULER_MODE_PROPERTY;
   }

   public int calculateAvailableSlots(final TaskSchedulerImpl scheduler, final SparkConf conf, final int rpId, final int[] availableRPIds, final int[] availableCpus, final Map[] availableResources) {
      ResourceProfile resourceProfile;
      boolean coresKnown;
      Tuple2 var10000;
      label24: {
         label23: {
            resourceProfile = scheduler.sc().resourceProfileManager().resourceProfileFromId(rpId);
            coresKnown = resourceProfile.isCoresLimitKnown();
            String limiting = resourceProfile.limitingResource(conf);
            String var13 = ResourceProfile$.MODULE$.CPUS();
            if (limiting == null) {
               if (var13 == null) {
                  break label23;
               }
            } else if (limiting.equals(var13)) {
               break label23;
            }

            if (!limiting.isEmpty()) {
               var10000 = new Tuple2(limiting, BoxesRunTime.boxToBoolean(false));
               break label24;
            }
         }

         var10000 = new Tuple2(ResourceProfile$.MODULE$.CPUS(), BoxesRunTime.boxToBoolean(true));
      }

      Tuple2 var11 = var10000;
      if (var11 != null) {
         String limitingResource = (String)var11._1();
         boolean limitedByCpu = var11._2$mcZ$sp();
         Tuple2 var10 = new Tuple2(limitingResource, BoxesRunTime.boxToBoolean(limitedByCpu));
         String limitingResource = (String)var10._1();
         boolean limitedByCpu = var10._2$mcZ$sp();
         int cpusPerTask = ResourceProfile$.MODULE$.getTaskCpusOrDefaultForProfile(resourceProfile, conf);
         double taskLimit = BoxesRunTime.unboxToDouble(resourceProfile.taskResources().get(limitingResource).map((x$28) -> BoxesRunTime.boxToDouble($anonfun$calculateAvailableSlots$1(x$28))).get());
         return BoxesRunTime.unboxToInt(.MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(.MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(.MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(.MODULE$.intArrayOps(availableCpus), .MODULE$.wrapRefArray((Object[])availableResources))), .MODULE$.wrapIntArray(availableRPIds))), (x0$1) -> BoxesRunTime.boxToBoolean($anonfun$calculateAvailableSlots$2(scheduler, rpId, x0$1)))), (x0$2) -> BoxesRunTime.boxToInteger($anonfun$calculateAvailableSlots$3(cpusPerTask, limitedByCpu, limitingResource, taskLimit, coresKnown, x0$2)), scala.reflect.ClassTag..MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
      } else {
         throw new MatchError(var11);
      }
   }

   public List prioritizeContainers(final HashMap map) {
      ArrayBuffer _keyList = new ArrayBuffer(map.size());
      _keyList.$plus$plus$eq(map.keys());
      ArrayBuffer keyList = (ArrayBuffer)_keyList.sortWith((left, right) -> BoxesRunTime.boxToBoolean($anonfun$prioritizeContainers$1(map, left, right)));
      ArrayBuffer retval = new ArrayBuffer(keyList.size() * 2);
      IntRef index = IntRef.create(0);

      for(BooleanRef found = BooleanRef.create(true); found.elem; ++index.elem) {
         found.elem = false;
         keyList.foreach((key) -> {
            $anonfun$prioritizeContainers$2(map, index, retval, found, key);
            return BoxedUnit.UNIT;
         });
      }

      return retval.toList();
   }

   public Option org$apache$spark$scheduler$TaskSchedulerImpl$$maybeCreateHealthTracker(final SparkContext sc) {
      if (HealthTracker$.MODULE$.isExcludeOnFailureEnabled(sc.conf())) {
         SchedulerBackend var4 = sc.schedulerBackend();
         Option executorAllocClient = (Option)(var4 instanceof ExecutorAllocationClient ? new Some(var4) : scala.None..MODULE$);
         return new Some(new HealthTracker(sc, executorAllocClient));
      } else {
         return scala.None..MODULE$;
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$calculateAvailableSlots$1(final TaskResourceRequest x$28) {
      return x$28.amount();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$calculateAvailableSlots$2(final TaskSchedulerImpl scheduler$1, final int rpId$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         int id = x0$1._2$mcI$sp();
         return scheduler$1.sc().resourceProfileManager().canBeScheduled(rpId$1, id);
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final int $anonfun$calculateAvailableSlots$3(final int cpusPerTask$1, final boolean limitedByCpu$1, final String limitingResource$1, final double taskLimit$1, final boolean coresKnown$1, final Tuple2 x0$2) {
      if (x0$2 != null) {
         Tuple2 var9 = (Tuple2)x0$2._1();
         if (var9 != null) {
            int cpu = var9._1$mcI$sp();
            Map resources = (Map)var9._2();
            int numTasksPerExecCores = cpu / cpusPerTask$1;
            if (limitedByCpu$1) {
               return numTasksPerExecCores;
            }

            int availAddrs = BoxesRunTime.unboxToInt(resources.getOrElse(limitingResource$1, (JFunction0.mcI.sp)() -> 0));
            int resourceLimit = taskLimit$1 >= (double)1.0F ? availAddrs / (int)scala.runtime.RichDouble..MODULE$.ceil$extension(.MODULE$.doubleWrapper(taskLimit$1)) : availAddrs * (int)Math.floor((double)1.0F / taskLimit$1);
            if (!coresKnown$1 && numTasksPerExecCores <= resourceLimit) {
               return numTasksPerExecCores;
            }

            return resourceLimit;
         }
      }

      throw new MatchError(x0$2);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$prioritizeContainers$1(final HashMap map$1, final Object left, final Object right) {
      return ((SeqOps)map$1.apply(left)).size() > ((SeqOps)map$1.apply(right)).size();
   }

   // $FF: synthetic method
   public static final void $anonfun$prioritizeContainers$2(final HashMap map$1, final IntRef index$2, final ArrayBuffer retval$1, final BooleanRef found$1, final Object key) {
      ArrayBuffer containerList = (ArrayBuffer)map$1.getOrElse(key, () -> null);
      .MODULE$.assert(containerList != null);
      if (index$2.elem < containerList.size()) {
         retval$1.$plus$eq(containerList.apply(index$2.elem));
         found$1.elem = true;
      }
   }

   private TaskSchedulerImpl$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
