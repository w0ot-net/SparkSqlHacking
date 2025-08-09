package org.apache.spark.mllib.evaluation;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext.;
import org.apache.spark.rdd.RDD;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.reflect.ClassTag;
import scala.runtime.ModuleSerializationProxy;

public final class RankingMetrics$ implements Serializable {
   public static final RankingMetrics$ MODULE$ = new RankingMetrics$();

   public RankingMetrics of(final JavaRDD predictionAndLabels) {
      ClassTag tag = .MODULE$.fakeClassTag();
      RDD rdd = predictionAndLabels.rdd().map((x0$1) -> {
         if (x0$1 instanceof Tuple2 var4) {
            Object predictions = var4._1();
            Object labels = var4._2();
            return new Tuple2(scala.jdk.CollectionConverters..MODULE$.IterableHasAsScala((Iterable)predictions).asScala().toArray(tag), scala.jdk.CollectionConverters..MODULE$.IterableHasAsScala((Iterable)labels).asScala().toArray(tag));
         } else if (x0$1 instanceof Tuple3 var7) {
            Object predictions = var7._1();
            Object labels = var7._2();
            Object rels = var7._3();
            return new Tuple3(scala.jdk.CollectionConverters..MODULE$.IterableHasAsScala((Iterable)predictions).asScala().toArray(tag), scala.jdk.CollectionConverters..MODULE$.IterableHasAsScala((Iterable)labels).asScala().toArray(tag), scala.jdk.CollectionConverters..MODULE$.IterableHasAsScala((Iterable)rels).asScala().toArray(scala.reflect.ClassTag..MODULE$.Double()));
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Object.class));
      return new RankingMetrics(rdd, tag);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RankingMetrics$.class);
   }

   private RankingMetrics$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
