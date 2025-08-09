package org.apache.spark.streaming.rdd;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partitioner;
import org.apache.spark.rdd.RDD;
import org.apache.spark.rdd.RDD.;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.util.StateMap;
import org.apache.spark.streaming.util.StateMap$;
import scala.Function4;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Null;

public final class MapWithStateRDD$ implements Serializable {
   public static final MapWithStateRDD$ MODULE$ = new MapWithStateRDD$();

   public MapWithStateRDD createFromPairRDD(final RDD pairRDD, final Partitioner partitioner, final Time updateTime, final ClassTag evidence$9, final ClassTag evidence$10, final ClassTag evidence$11, final ClassTag evidence$12) {
      Null x$4 = .MODULE$.rddToPairRDDFunctions$default$4(pairRDD);
      RDD stateRDD = .MODULE$.rddToPairRDDFunctions(pairRDD, evidence$9, evidence$11, (Ordering)null).partitionBy(partitioner).mapPartitions((iterator) -> {
         StateMap stateMap = StateMap$.MODULE$.create(org.apache.spark.SparkEnv..MODULE$.get().conf(), evidence$9, evidence$11);
         iterator.foreach((x0$1) -> {
            $anonfun$createFromPairRDD$2(stateMap, updateTime, x0$1);
            return BoxedUnit.UNIT;
         });
         return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new MapWithStateRDDRecord[]{new MapWithStateRDDRecord(stateMap, (Seq)scala.package..MODULE$.Seq().empty())}));
      }, true, scala.reflect.ClassTag..MODULE$.apply(MapWithStateRDDRecord.class));
      RDD x$5 = pairRDD.sparkContext().emptyRDD(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      Null x$8 = .MODULE$.rddToPairRDDFunctions$default$4(x$5);
      RDD emptyDataRDD = .MODULE$.rddToPairRDDFunctions(x$5, evidence$9, evidence$10, (Ordering)null).partitionBy(partitioner);
      Function4 noOpFunc = (time, key, value, state) -> scala.None..MODULE$;
      return new MapWithStateRDD(stateRDD, emptyDataRDD, noOpFunc, updateTime, scala.None..MODULE$, evidence$9, evidence$10, evidence$11, evidence$12);
   }

   public MapWithStateRDD createFromRDD(final RDD rdd, final Partitioner partitioner, final Time updateTime, final ClassTag evidence$13, final ClassTag evidence$14, final ClassTag evidence$15, final ClassTag evidence$16) {
      RDD pairRDD = rdd.map((x) -> new Tuple2(x._1(), new Tuple2(x._2(), x._3())), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      ClassTag x$3 = scala.reflect.ClassTag..MODULE$.apply(Tuple2.class);
      Null x$4 = .MODULE$.rddToPairRDDFunctions$default$4(pairRDD);
      RDD stateRDD = .MODULE$.rddToPairRDDFunctions(pairRDD, evidence$13, x$3, (Ordering)null).partitionBy(partitioner).mapPartitions((iterator) -> {
         StateMap stateMap = StateMap$.MODULE$.create(org.apache.spark.SparkEnv..MODULE$.get().conf(), evidence$13, evidence$15);
         iterator.foreach((x0$1) -> {
            $anonfun$createFromRDD$3(stateMap, x0$1);
            return BoxedUnit.UNIT;
         });
         return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new MapWithStateRDDRecord[]{new MapWithStateRDDRecord(stateMap, (Seq)scala.package..MODULE$.Seq().empty())}));
      }, true, scala.reflect.ClassTag..MODULE$.apply(MapWithStateRDDRecord.class));
      RDD x$5 = pairRDD.sparkContext().emptyRDD(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      Null x$8 = .MODULE$.rddToPairRDDFunctions$default$4(x$5);
      RDD emptyDataRDD = .MODULE$.rddToPairRDDFunctions(x$5, evidence$13, evidence$14, (Ordering)null).partitionBy(partitioner);
      Function4 noOpFunc = (time, key, value, state) -> scala.None..MODULE$;
      return new MapWithStateRDD(stateRDD, emptyDataRDD, noOpFunc, updateTime, scala.None..MODULE$, evidence$13, evidence$14, evidence$15, evidence$16);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MapWithStateRDD$.class);
   }

   // $FF: synthetic method
   public static final void $anonfun$createFromPairRDD$2(final StateMap stateMap$1, final Time updateTime$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Object key = x0$1._1();
         Object state = x0$1._2();
         stateMap$1.put(key, state, updateTime$1.milliseconds());
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$createFromRDD$3(final StateMap stateMap$2, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Object key = x0$1._1();
         Tuple2 var5 = (Tuple2)x0$1._2();
         if (var5 != null) {
            Object state = var5._1();
            long updateTime = var5._2$mcJ$sp();
            stateMap$2.put(key, state, updateTime);
            BoxedUnit var10000 = BoxedUnit.UNIT;
            return;
         }
      }

      throw new MatchError(x0$1);
   }

   private MapWithStateRDD$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
