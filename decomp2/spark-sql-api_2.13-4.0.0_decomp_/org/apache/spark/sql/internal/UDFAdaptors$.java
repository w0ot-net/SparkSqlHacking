package org.apache.spark.sql.internal;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.ForeachFunction;
import org.apache.spark.api.java.function.MapFunction;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple1;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.jdk.CollectionConverters.;
import scala.runtime.BoxedUnit;
import scala.runtime.ModuleSerializationProxy;

public final class UDFAdaptors$ implements Serializable {
   public static final UDFAdaptors$ MODULE$ = new UDFAdaptors$();
   private static final long serialVersionUID = 0L;

   public Function1 flatMapToMapPartitions(final Function1 f) {
      return (values) -> values.flatMap(f);
   }

   public Function1 flatMapToMapPartitions(final FlatMapFunction f) {
      return (values) -> values.flatMap((v) -> .MODULE$.IteratorHasAsScala(f.call(v)).asScala());
   }

   public Function1 mapToMapPartitions(final Function1 f) {
      return (values) -> values.map(f);
   }

   public Function1 mapToMapPartitions(final MapFunction f) {
      return (values) -> values.map((x$1) -> f.call(x$1));
   }

   public Function1 mapValues(final Function1 vFunc, final boolean ivIsStruct, final boolean vIsStruct) {
      Function1 ivFunc = (iv) -> scala.Predef..MODULE$.identity(iv);
      Function1 wrappedIvFunc = ivIsStruct ? ivFunc : ivFunc.andThen((x$254) -> new Tuple1(x$254));
      Function1 wrappedVFunc = vIsStruct ? vFunc : vFunc.andThen((x$255) -> new Tuple1(x$255));
      return (input) -> input.map((i) -> new Tuple2(wrappedIvFunc.apply(i), wrappedVFunc.apply(i)));
   }

   public Function1 foreachToForeachPartition(final Function1 f) {
      return (values) -> {
         $anonfun$foreachToForeachPartition$1(f, values);
         return BoxedUnit.UNIT;
      };
   }

   public Function1 foreachToForeachPartition(final ForeachFunction f) {
      return (values) -> {
         $anonfun$foreachToForeachPartition$2(f, values);
         return BoxedUnit.UNIT;
      };
   }

   public Function1 foreachPartitionToMapPartitions(final Function1 f) {
      return (values) -> {
         f.apply(values);
         return scala.package..MODULE$.Iterator().empty();
      };
   }

   public Function1 iterableOnceToSeq(final Function1 f) {
      return (value) -> ((IterableOnce)f.apply(value)).iterator().toSeq();
   }

   public Function2 mapGroupsToFlatMapGroups(final Function2 f) {
      return (key, values) -> scala.package..MODULE$.Iterator().single(f.apply(key, values));
   }

   public Function3 mapGroupsWithStateToFlatMapWithState(final Function3 f) {
      return (key, values, state) -> scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{f.apply(key, values, state)}));
   }

   public Function3 coGroupWithMappedValues(final Function3 f, final Option leftValueMapFunc, final Option rightValueMapFunc) {
      Tuple2 var5 = new Tuple2(leftValueMapFunc, rightValueMapFunc);
      if (var5 != null) {
         Option var6 = (Option)var5._1();
         Option var7 = (Option)var5._2();
         if (scala.None..MODULE$.equals(var6) && scala.None..MODULE$.equals(var7)) {
            return f;
         }
      }

      if (var5 != null) {
         Option var8 = (Option)var5._1();
         Option var9 = (Option)var5._2();
         if (var8 instanceof Some) {
            Some var10 = (Some)var8;
            Function1 mapLeft = (Function1)var10.value();
            if (scala.None..MODULE$.equals(var9)) {
               return (k, left, right) -> (IterableOnce)f.apply(k, left.map(mapLeft), right);
            }
         }
      }

      if (var5 != null) {
         Option var12 = (Option)var5._1();
         Option var13 = (Option)var5._2();
         if (scala.None..MODULE$.equals(var12) && var13 instanceof Some) {
            Some var14 = (Some)var13;
            Function1 mapRight = (Function1)var14.value();
            return (k, left, right) -> (IterableOnce)f.apply(k, left, right.map(mapRight));
         }
      }

      if (var5 != null) {
         Option var16 = (Option)var5._1();
         Option var17 = (Option)var5._2();
         if (var16 instanceof Some) {
            Some var18 = (Some)var16;
            Function1 mapLeft = (Function1)var18.value();
            if (var17 instanceof Some) {
               Some var20 = (Some)var17;
               Function1 mapRight = (Function1)var20.value();
               return (k, left, right) -> (IterableOnce)f.apply(k, left.map(mapLeft), right.map(mapRight));
            }
         }
      }

      throw new MatchError(var5);
   }

   public Function2 flatMapGroupsWithMappedValues(final Function2 f, final Option valueMapFunc) {
      if (valueMapFunc instanceof Some var5) {
         Function1 mapValue = (Function1)var5.value();
         return (k, values) -> (IterableOnce)f.apply(k, values.map(mapValue));
      } else if (scala.None..MODULE$.equals(valueMapFunc)) {
         return f;
      } else {
         throw new MatchError(valueMapFunc);
      }
   }

   public Function3 flatMapGroupsWithStateWithMappedValues(final Function3 f, final Option valueMapFunc) {
      if (valueMapFunc instanceof Some var5) {
         Function1 mapValue = (Function1)var5.value();
         return (k, values, state) -> (Iterator)f.apply(k, values.map(mapValue), state);
      } else if (scala.None..MODULE$.equals(valueMapFunc)) {
         return f;
      } else {
         throw new MatchError(valueMapFunc);
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(UDFAdaptors$.class);
   }

   // $FF: synthetic method
   public static final void $anonfun$foreachToForeachPartition$1(final Function1 f$39, final Iterator values) {
      values.foreach(f$39);
   }

   // $FF: synthetic method
   public static final void $anonfun$foreachToForeachPartition$3(final ForeachFunction f$40, final Object x$1) {
      f$40.call(x$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$foreachToForeachPartition$2(final ForeachFunction f$40, final Iterator values) {
      values.foreach((x$1) -> {
         $anonfun$foreachToForeachPartition$3(f$40, x$1);
         return BoxedUnit.UNIT;
      });
   }

   private UDFAdaptors$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
