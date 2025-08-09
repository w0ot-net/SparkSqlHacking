package org.apache.spark.streaming.scheduler;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.scheduler.ExecutorCacheTaskLocation;
import org.apache.spark.scheduler.TaskLocation;
import org.apache.spark.streaming.receiver.Receiver;
import scala.Enumeration;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Map;
import scala.collection.MapOps;
import scala.collection.Map.;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005Y4Q!\u0002\u0004\u0001\u0011AAQa\u0006\u0001\u0005\u0002eAQ\u0001\b\u0001\u0005\u0002uAQa\u0015\u0001\u0005\u0002QCQa\u001b\u0001\u0005\n1\u0014\u0001DU3dK&4XM]*dQ\u0016$W\u000f\\5oOB{G.[2z\u0015\t9\u0001\"A\u0005tG\",G-\u001e7fe*\u0011\u0011BC\u0001\ngR\u0014X-Y7j]\u001eT!a\u0003\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00055q\u0011AB1qC\u000eDWMC\u0001\u0010\u0003\ry'oZ\n\u0003\u0001E\u0001\"AE\u000b\u000e\u0003MQ\u0011\u0001F\u0001\u0006g\u000e\fG.Y\u0005\u0003-M\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003i\u0001\"a\u0007\u0001\u000e\u0003\u0019\t\u0011c]2iK\u0012,H.\u001a*fG\u0016Lg/\u001a:t)\rq\u0002(\u0014\t\u0005?\t\"s%D\u0001!\u0015\t\t3#\u0001\u0006d_2dWm\u0019;j_:L!a\t\u0011\u0003\u00075\u000b\u0007\u000f\u0005\u0002\u0013K%\u0011ae\u0005\u0002\u0004\u0013:$\bc\u0001\u00151g9\u0011\u0011F\f\b\u0003U5j\u0011a\u000b\u0006\u0003Ya\ta\u0001\u0010:p_Rt\u0014\"\u0001\u000b\n\u0005=\u001a\u0012a\u00029bG.\fw-Z\u0005\u0003cI\u00121aU3r\u0015\ty3\u0003\u0005\u00025m5\tQG\u0003\u0002\b\u0015%\u0011q'\u000e\u0002\r)\u0006\u001c8\u000eT8dCRLwN\u001c\u0005\u0006s\t\u0001\rAO\u0001\ne\u0016\u001cW-\u001b<feN\u00042\u0001\u000b\u0019<a\taD\tE\u0002>\u0001\nk\u0011A\u0010\u0006\u0003\u007f!\t\u0001B]3dK&4XM]\u0005\u0003\u0003z\u0012\u0001BU3dK&4XM\u001d\t\u0003\u0007\u0012c\u0001\u0001B\u0005Fq\u0005\u0005\t\u0011!B\u0001\r\n\u0019q\fJ\u0019\u0012\u0005\u001dS\u0005C\u0001\nI\u0013\tI5CA\u0004O_RD\u0017N\\4\u0011\u0005IY\u0015B\u0001'\u0014\u0005\r\te.\u001f\u0005\u0006\u001d\n\u0001\raT\u0001\nKb,7-\u001e;peN\u00042\u0001\u000b\u0019Q!\t!\u0014+\u0003\u0002Sk\tIR\t_3dkR|'oQ1dQ\u0016$\u0016m]6M_\u000e\fG/[8o\u0003I\u0011Xm]2iK\u0012,H.\u001a*fG\u0016Lg/\u001a:\u0015\u000b\u001d*v\u000b\u001a6\t\u000bY\u001b\u0001\u0019\u0001\u0013\u0002\u0015I,7-Z5wKJLE\rC\u0003Y\u0007\u0001\u0007\u0011,A\tqe\u00164WM\u001d:fI2{7-\u0019;j_:\u00042A\u0005.]\u0013\tY6C\u0001\u0004PaRLwN\u001c\t\u0003;\u0006t!AX0\u0011\u0005)\u001a\u0012B\u00011\u0014\u0003\u0019\u0001&/\u001a3fM&\u0011!m\u0019\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0001\u001c\u0002\"B3\u0004\u0001\u00041\u0017a\u0006:fG\u0016Lg/\u001a:Ue\u0006\u001c7.\u001b8h\u0013:4w.T1q!\u0011y\"\u0005J4\u0011\u0005mA\u0017BA5\u0007\u0005Q\u0011VmY3jm\u0016\u0014HK]1dW&tw-\u00138g_\")aj\u0001a\u0001\u001f\u0006a3m\u001c8wKJ$(+Z2fSZ,'\u000f\u0016:bG.LgnZ%oM>$v.\u0012=fGV$xN],fS\u001eDGo\u001d\u000b\u0003[R\u00042\u0001\u000b\u0019o!\u0011\u0011r\u000eU9\n\u0005A\u001c\"A\u0002+va2,'\u0007\u0005\u0002\u0013e&\u00111o\u0005\u0002\u0007\t>,(\r\\3\t\u000bU$\u0001\u0019A4\u0002)I,7-Z5wKJ$&/Y2lS:<\u0017J\u001c4p\u0001"
)
public class ReceiverSchedulingPolicy {
   public Map scheduleReceivers(final Seq receivers, final Seq executors) {
      if (receivers.isEmpty()) {
         return (Map).MODULE$.empty();
      } else if (executors.isEmpty()) {
         return ((IterableOnceOps)receivers.map((x$1) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(x$1.streamId())), scala.package..MODULE$.Seq().empty()))).toMap(scala..less.colon.less..MODULE$.refl());
      } else {
         scala.collection.immutable.Map hostToExecutors = executors.groupBy((x$2) -> x$2.host());
         ArrayBuffer[] scheduledLocations = (ArrayBuffer[])scala.Array..MODULE$.fill(receivers.length(), () -> new ArrayBuffer(), scala.reflect.ClassTag..MODULE$.apply(ArrayBuffer.class));
         HashMap numReceiversOnExecutor = (HashMap)scala.collection.mutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
         executors.foreach((e) -> {
            $anonfun$scheduleReceivers$4(numReceiversOnExecutor, e);
            return BoxedUnit.UNIT;
         });
         receivers.indices().foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> ((Receiver)receivers.apply(i)).preferredLocation().foreach((host) -> {
               Option var6 = hostToExecutors.get(host);
               if (var6 instanceof Some var7) {
                  Seq executorsOnHost = (Seq)var7.value();
                  ExecutorCacheTaskLocation leastScheduledExecutor = (ExecutorCacheTaskLocation)executorsOnHost.minBy((executor) -> BoxesRunTime.boxToInteger($anonfun$scheduleReceivers$7(numReceiversOnExecutor, executor)), scala.math.Ordering.Int..MODULE$);
                  scheduledLocations[i].$plus$eq(leastScheduledExecutor);
                  numReceiversOnExecutor.update(leastScheduledExecutor, BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(numReceiversOnExecutor.apply(leastScheduledExecutor)) + 1));
                  return BoxedUnit.UNIT;
               } else if (scala.None..MODULE$.equals(var6)) {
                  return scheduledLocations[i].$plus$eq(org.apache.spark.scheduler.TaskLocation..MODULE$.apply(host));
               } else {
                  throw new MatchError(var6);
               }
            }));
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])scheduledLocations), (x$3) -> BoxesRunTime.boxToBoolean($anonfun$scheduleReceivers$8(x$3)))), (scheduledLocationsForOneReceiver) -> {
            $anonfun$scheduleReceivers$9(numReceiversOnExecutor, scheduledLocationsForOneReceiver);
            return BoxedUnit.UNIT;
         });
         Iterable idleExecutors = ((MapOps)numReceiversOnExecutor.filter((x$6) -> BoxesRunTime.boxToBoolean($anonfun$scheduleReceivers$11(x$6)))).keys();
         idleExecutors.foreach((executor) -> {
            ArrayBuffer leastScheduledExecutors = (ArrayBuffer)scala.Predef..MODULE$.wrapRefArray((Object[])scheduledLocations).minBy((x$7) -> BoxesRunTime.boxToInteger($anonfun$scheduleReceivers$13(x$7)), scala.math.Ordering.Int..MODULE$);
            return (ArrayBuffer)leastScheduledExecutors.$plus$eq(executor);
         });
         return org.apache.spark.util.collection.Utils..MODULE$.toMap((Iterable)receivers.map((x$8) -> BoxesRunTime.boxToInteger($anonfun$scheduleReceivers$14(x$8))), scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scheduledLocations), (x$9) -> x$9.toSeq(), scala.reflect.ClassTag..MODULE$.apply(Seq.class))));
      }
   }

   public Seq rescheduleReceiver(final int receiverId, final Option preferredLocation, final Map receiverTrackingInfoMap, final Seq executors) {
      if (executors.isEmpty()) {
         return (Seq)scala.package..MODULE$.Seq().empty();
      } else {
         Set scheduledLocations = (Set)scala.collection.mutable.Set..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
         scheduledLocations.$plus$plus$eq(preferredLocation.map((x$10) -> org.apache.spark.scheduler.TaskLocation..MODULE$.apply(x$10)));
         Map executorWeights = (Map)((IterableOps)receiverTrackingInfoMap.values().flatMap((receiverTrackingInfo) -> this.convertReceiverTrackingInfoToExecutorWeights(receiverTrackingInfo))).groupBy((x$11) -> (ExecutorCacheTaskLocation)x$11._1()).transform((x$12, v) -> BoxesRunTime.boxToDouble($anonfun$rescheduleReceiver$4(x$12, v)));
         scala.collection.immutable.Set idleExecutors = (scala.collection.immutable.Set)executors.toSet().$minus$minus(executorWeights.keys());
         if (idleExecutors.nonEmpty()) {
            scheduledLocations.$plus$plus$eq(idleExecutors);
         } else {
            Seq sortedExecutors = (Seq)executorWeights.toSeq().sortBy((x$14) -> BoxesRunTime.boxToDouble($anonfun$rescheduleReceiver$6(x$14)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
            if (sortedExecutors.nonEmpty()) {
               double minWeight = ((Tuple2)sortedExecutors.apply(0))._2$mcD$sp();
               scheduledLocations.$plus$plus$eq((IterableOnce)((IterableOps)sortedExecutors.takeWhile((x$15) -> BoxesRunTime.boxToBoolean($anonfun$rescheduleReceiver$7(minWeight, x$15)))).map((x$16) -> (ExecutorCacheTaskLocation)x$16._1()));
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }
         }

         return scheduledLocations.toSeq();
      }
   }

   private Seq convertReceiverTrackingInfoToExecutorWeights(final ReceiverTrackingInfo receiverTrackingInfo) {
      Enumeration.Value var3 = receiverTrackingInfo.state();
      Enumeration.Value var10000 = ReceiverState$.MODULE$.INACTIVE();
      if (var10000 == null) {
         if (var3 == null) {
            return scala.collection.immutable.Nil..MODULE$;
         }
      } else if (var10000.equals(var3)) {
         return scala.collection.immutable.Nil..MODULE$;
      }

      label53: {
         var10000 = ReceiverState$.MODULE$.SCHEDULED();
         if (var10000 == null) {
            if (var3 == null) {
               break label53;
            }
         } else if (var10000.equals(var3)) {
            break label53;
         }

         var10000 = ReceiverState$.MODULE$.ACTIVE();
         if (var10000 == null) {
            if (var3 == null) {
               return new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(receiverTrackingInfo.runningExecutor().get()), BoxesRunTime.boxToDouble((double)1.0F)), scala.collection.immutable.Nil..MODULE$);
            }
         } else if (var10000.equals(var3)) {
            return new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(receiverTrackingInfo.runningExecutor().get()), BoxesRunTime.boxToDouble((double)1.0F)), scala.collection.immutable.Nil..MODULE$);
         }

         throw new MatchError(var3);
      }

      Seq scheduledLocations = (Seq)receiverTrackingInfo.scheduledLocations().get();
      return (Seq)((IterableOps)scheduledLocations.filter((x$17) -> BoxesRunTime.boxToBoolean($anonfun$convertReceiverTrackingInfoToExecutorWeights$1(x$17)))).map((location) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc((ExecutorCacheTaskLocation)location), BoxesRunTime.boxToDouble((double)1.0F / (double)scheduledLocations.size())));
   }

   // $FF: synthetic method
   public static final void $anonfun$scheduleReceivers$4(final HashMap numReceiversOnExecutor$1, final ExecutorCacheTaskLocation e) {
      numReceiversOnExecutor$1.update(e, BoxesRunTime.boxToInteger(0));
   }

   // $FF: synthetic method
   public static final int $anonfun$scheduleReceivers$7(final HashMap numReceiversOnExecutor$1, final ExecutorCacheTaskLocation executor) {
      return BoxesRunTime.unboxToInt(numReceiversOnExecutor$1.apply(executor));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$scheduleReceivers$8(final ArrayBuffer x$3) {
      return x$3.isEmpty();
   }

   // $FF: synthetic method
   public static final int $anonfun$scheduleReceivers$10(final Tuple2 x$4) {
      return x$4._2$mcI$sp();
   }

   // $FF: synthetic method
   public static final void $anonfun$scheduleReceivers$9(final HashMap numReceiversOnExecutor$1, final ArrayBuffer scheduledLocationsForOneReceiver) {
      Tuple2 var4 = (Tuple2)numReceiversOnExecutor$1.minBy((x$4) -> BoxesRunTime.boxToInteger($anonfun$scheduleReceivers$10(x$4)), scala.math.Ordering.Int..MODULE$);
      if (var4 != null) {
         ExecutorCacheTaskLocation leastScheduledExecutor = (ExecutorCacheTaskLocation)var4._1();
         int numReceivers = var4._2$mcI$sp();
         Tuple2 var3 = new Tuple2(leastScheduledExecutor, BoxesRunTime.boxToInteger(numReceivers));
         ExecutorCacheTaskLocation leastScheduledExecutor = (ExecutorCacheTaskLocation)var3._1();
         int numReceivers = var3._2$mcI$sp();
         scheduledLocationsForOneReceiver.$plus$eq(leastScheduledExecutor);
         numReceiversOnExecutor$1.update(leastScheduledExecutor, BoxesRunTime.boxToInteger(numReceivers + 1));
      } else {
         throw new MatchError(var4);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$scheduleReceivers$11(final Tuple2 x$6) {
      return x$6._2$mcI$sp() == 0;
   }

   // $FF: synthetic method
   public static final int $anonfun$scheduleReceivers$13(final ArrayBuffer x$7) {
      return x$7.size();
   }

   // $FF: synthetic method
   public static final int $anonfun$scheduleReceivers$14(final Receiver x$8) {
      return x$8.streamId();
   }

   // $FF: synthetic method
   public static final double $anonfun$rescheduleReceiver$5(final Tuple2 x$13) {
      return x$13._2$mcD$sp();
   }

   // $FF: synthetic method
   public static final double $anonfun$rescheduleReceiver$4(final ExecutorCacheTaskLocation x$12, final Iterable v) {
      return BoxesRunTime.unboxToDouble(((IterableOnceOps)v.map((x$13) -> BoxesRunTime.boxToDouble($anonfun$rescheduleReceiver$5(x$13)))).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
   }

   // $FF: synthetic method
   public static final double $anonfun$rescheduleReceiver$6(final Tuple2 x$14) {
      return x$14._2$mcD$sp();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$rescheduleReceiver$7(final double minWeight$1, final Tuple2 x$15) {
      return x$15._2$mcD$sp() == minWeight$1;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$convertReceiverTrackingInfoToExecutorWeights$1(final TaskLocation x$17) {
      return x$17 instanceof ExecutorCacheTaskLocation;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
