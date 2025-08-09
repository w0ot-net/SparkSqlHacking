package org.apache.spark.streaming.rdd;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.streaming.StateImpl;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.util.EmptyStateMap;
import org.apache.spark.streaming.util.StateMap;
import scala.Function4;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.None.;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class MapWithStateRDDRecord$ implements Serializable {
   public static final MapWithStateRDDRecord$ MODULE$ = new MapWithStateRDDRecord$();

   public MapWithStateRDDRecord updateRecordWithData(final Option prevRecord, final Iterator dataIterator, final Function4 mappingFunction, final Time batchTime, final Option timeoutThresholdTime, final boolean removeTimedoutData, final ClassTag evidence$1, final ClassTag evidence$2, final ClassTag evidence$3, final ClassTag evidence$4) {
      StateMap newStateMap = (StateMap)prevRecord.map((x$1) -> x$1.stateMap().copy()).getOrElse(() -> new EmptyStateMap());
      ArrayBuffer mappedData = new ArrayBuffer();
      StateImpl wrappedState = new StateImpl();
      dataIterator.foreach((x0$1) -> {
         if (x0$1 == null) {
            throw new MatchError(x0$1);
         } else {
            Object key = x0$1._1();
            Object value = x0$1._2();
            wrappedState.wrap(newStateMap.get(key));
            Option returned = (Option)mappingFunction.apply(batchTime, key, new Some(value), wrappedState);
            if (wrappedState.isRemoved()) {
               newStateMap.remove(key);
            } else if (wrappedState.isUpdated() || wrappedState.exists() && timeoutThresholdTime.isDefined()) {
               newStateMap.put(key, wrappedState.get(), batchTime.milliseconds());
            }

            return (ArrayBuffer)mappedData.$plus$plus$eq(returned);
         }
      });
      if (removeTimedoutData && timeoutThresholdTime.isDefined()) {
         newStateMap.getByTime(BoxesRunTime.unboxToLong(timeoutThresholdTime.get())).foreach((x0$2) -> {
            $anonfun$updateRecordWithData$4(wrappedState, mappingFunction, batchTime, mappedData, newStateMap, x0$2);
            return BoxedUnit.UNIT;
         });
      }

      return new MapWithStateRDDRecord(newStateMap, mappedData.toSeq());
   }

   public MapWithStateRDDRecord apply(final StateMap stateMap, final Seq mappedData) {
      return new MapWithStateRDDRecord(stateMap, mappedData);
   }

   public Option unapply(final MapWithStateRDDRecord x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.stateMap(), x$0.mappedData())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MapWithStateRDDRecord$.class);
   }

   // $FF: synthetic method
   public static final void $anonfun$updateRecordWithData$4(final StateImpl wrappedState$1, final Function4 mappingFunction$1, final Time batchTime$1, final ArrayBuffer mappedData$1, final StateMap newStateMap$1, final Tuple3 x0$2) {
      if (x0$2 != null) {
         Object key = x0$2._1();
         Object state = x0$2._2();
         wrappedState$1.wrapTimingOutState(state);
         Option returned = (Option)mappingFunction$1.apply(batchTime$1, key, .MODULE$, wrappedState$1);
         mappedData$1.$plus$plus$eq(returned);
         newStateMap$1.remove(key);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$2);
      }
   }

   private MapWithStateRDDRecord$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
