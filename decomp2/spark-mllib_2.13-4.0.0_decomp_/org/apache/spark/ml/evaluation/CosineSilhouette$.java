package org.apache.spark.ml.evaluation;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.rdd.RDD.;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import scala.Function1;
import scala.Function3;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple4;
import scala.collection.immutable.Map;
import scala.collection.immutable.MapOps;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public final class CosineSilhouette$ extends Silhouette {
   public static final CosineSilhouette$ MODULE$ = new CosineSilhouette$();
   private static final String normalizedFeaturesColName = "normalizedFeatures";

   public Map computeClusterStats(final Dataset df, final String featuresCol, final String predictionCol, final String weightCol) {
      int numFeatures = DatasetUtils$.MODULE$.getNumFeatures(df, featuresCol);
      RDD clustersStatsRDD = .MODULE$.rddToPairRDDFunctions(df.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(predictionCol).cast(org.apache.spark.sql.types.DoubleType..MODULE$), org.apache.spark.sql.functions..MODULE$.col(normalizedFeaturesColName), org.apache.spark.sql.functions..MODULE$.col(weightCol)}))).rdd().map((row) -> new Tuple2(BoxesRunTime.boxToDouble(row.getDouble(0)), new Tuple2(row.getAs(1), BoxesRunTime.boxToDouble(row.getDouble(2)))), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.Double(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$).aggregateByKey(new Tuple2((Object)null, BoxesRunTime.boxToDouble((double)0.0F)), (x0$1, x1$1) -> {
         Tuple2 var5 = new Tuple2(x0$1, x1$1);
         if (var5 != null) {
            if (var5 != null) {
               Tuple2 var9 = (Tuple2)var5._1();
               Tuple2 var10 = (Tuple2)var5._2();
               if (var9 != null) {
                  DenseVector normalizedFeaturesSum = (DenseVector)var9._1();
                  double weightSum = var9._2$mcD$sp();
                  if (var10 != null) {
                     Vector normalizedFeatures = (Vector)var10._1();
                     double weight = var10._2$mcD$sp();
                     Tuple4 var7 = new Tuple4(normalizedFeaturesSum, BoxesRunTime.boxToDouble(weightSum), normalizedFeatures, BoxesRunTime.boxToDouble(weight));
                     DenseVector normalizedFeaturesSumx = (DenseVector)var7._1();
                     double weightSumx = BoxesRunTime.unboxToDouble(var7._2());
                     Vector normalizedFeaturesx = (Vector)var7._3();
                     double weightx = BoxesRunTime.unboxToDouble(var7._4());
                     DenseVector theNormalizedFeaturesSum = normalizedFeaturesSumx == null ? org.apache.spark.ml.linalg.Vectors..MODULE$.zeros(numFeatures).toDense() : normalizedFeaturesSumx;
                     org.apache.spark.ml.linalg.BLAS..MODULE$.axpy(weightx, normalizedFeaturesx, theNormalizedFeaturesSum);
                     return new Tuple2(theNormalizedFeaturesSum, BoxesRunTime.boxToDouble(weightSumx + weightx));
                  }
               }
            }

            throw new MatchError(var5);
         } else {
            throw new MatchError(var5);
         }
      }, (x0$2, x1$2) -> {
         Tuple2 var4 = new Tuple2(x0$2, x1$2);
         if (var4 != null) {
            if (var4 != null) {
               Tuple2 var8 = (Tuple2)var4._1();
               Tuple2 var9 = (Tuple2)var4._2();
               if (var8 != null) {
                  DenseVector normalizedFeaturesSum1 = (DenseVector)var8._1();
                  double weightSum1 = var8._2$mcD$sp();
                  if (var9 != null) {
                     DenseVector normalizedFeaturesSum2 = (DenseVector)var9._1();
                     double weightSum2 = var9._2$mcD$sp();
                     Tuple4 var6 = new Tuple4(normalizedFeaturesSum1, BoxesRunTime.boxToDouble(weightSum1), normalizedFeaturesSum2, BoxesRunTime.boxToDouble(weightSum2));
                     DenseVector normalizedFeaturesSum1 = (DenseVector)var6._1();
                     double weightSum1x = BoxesRunTime.unboxToDouble(var6._2());
                     DenseVector normalizedFeaturesSum2x = (DenseVector)var6._3();
                     double weightSum2x = BoxesRunTime.unboxToDouble(var6._4());
                     DenseVector var10000;
                     if (normalizedFeaturesSum1 == null) {
                        var10000 = normalizedFeaturesSum2x;
                     } else if (normalizedFeaturesSum2x == null) {
                        var10000 = normalizedFeaturesSum1;
                     } else {
                        org.apache.spark.ml.linalg.BLAS..MODULE$.axpy((double)1.0F, normalizedFeaturesSum2x, normalizedFeaturesSum1);
                        var10000 = normalizedFeaturesSum1;
                     }

                     DenseVector theNormalizedFeaturesSum = var10000;
                     return new Tuple2(theNormalizedFeaturesSum, BoxesRunTime.boxToDouble(weightSum1x + weightSum2x));
                  }
               }
            }

            throw new MatchError(var4);
         } else {
            throw new MatchError(var4);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      return .MODULE$.rddToPairRDDFunctions(clustersStatsRDD, scala.reflect.ClassTag..MODULE$.Double(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$).collectAsMap().toMap(scala..less.colon.less..MODULE$.refl());
   }

   public double computeSilhouetteCoefficient(final Broadcast broadcastedClustersMap, final Vector normalizedFeatures, final double clusterId, final double weight) {
      return this.pointSilhouetteCoefficient(((MapOps)broadcastedClustersMap.value()).keySet(), clusterId, ((Tuple2)((scala.collection.MapOps)broadcastedClustersMap.value()).apply(BoxesRunTime.boxToDouble(clusterId)))._2$mcD$sp(), weight, (JFunction1.mcDD.sp)(targetClusterId) -> compute$2(targetClusterId, broadcastedClustersMap, normalizedFeatures));
   }

   public double computeSilhouetteScore(final Dataset dataset, final String predictionCol, final String featuresCol, final String weightCol) {
      functions var10000 = org.apache.spark.sql.functions..MODULE$;
      Function1 var10001 = (features) -> {
         double norm = org.apache.spark.ml.linalg.Vectors..MODULE$.norm(features, (double)2.0F);
         org.apache.spark.ml.linalg.BLAS..MODULE$.scal((double)1.0F / norm, features);
         return features;
      };
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

      final class $typecreator1$2 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator1$2() {
         }
      }

      TypeTags.TypeTag var10002 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2());
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

      final class $typecreator2$2 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator2$2() {
         }
      }

      UserDefinedFunction normalizeFeatureUDF = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$2()));
      Dataset dfWithNormalizedFeatures = dataset.withColumn(normalizedFeaturesColName, normalizeFeatureUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(featuresCol)}))));
      Map clustersStatsMap = this.computeClusterStats(dfWithNormalizedFeatures, featuresCol, predictionCol, weightCol);
      scala.Predef..MODULE$.assert(clustersStatsMap.size() > 1, () -> "Number of clusters must be greater than one.");
      Broadcast bClustersStatsMap = dataset.sparkSession().sparkContext().broadcast(clustersStatsMap, scala.reflect.ClassTag..MODULE$.apply(Map.class));
      var10000 = org.apache.spark.sql.functions..MODULE$;
      Function3 var19 = (x$11, x$12, x$13) -> BoxesRunTime.boxToDouble($anonfun$computeSilhouetteScore$6(bClustersStatsMap, x$11, BoxesRunTime.unboxToDouble(x$12), BoxesRunTime.unboxToDouble(x$13)));
      var10002 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

      final class $typecreator3$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator3$1() {
         }
      }

      UserDefinedFunction computeSilhouetteCoefficientUDF = var10000.udf(var19, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator3$1()), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double());
      double silhouetteScore = this.overallScore(dfWithNormalizedFeatures, computeSilhouetteCoefficientUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(normalizedFeaturesColName), org.apache.spark.sql.functions..MODULE$.col(predictionCol).cast(org.apache.spark.sql.types.DoubleType..MODULE$), org.apache.spark.sql.functions..MODULE$.col(weightCol)}))), org.apache.spark.sql.functions..MODULE$.col(weightCol));
      bClustersStatsMap.destroy();
      return silhouetteScore;
   }

   private static final double compute$2(final double targetClusterId, final Broadcast broadcastedClustersMap$2, final Vector normalizedFeatures$1) {
      Tuple2 var6 = (Tuple2)((scala.collection.MapOps)broadcastedClustersMap$2.value()).apply(BoxesRunTime.boxToDouble(targetClusterId));
      if (var6 != null) {
         Vector normalizedFeatureSum = (Vector)var6._1();
         double numOfPoints = var6._2$mcD$sp();
         Tuple2 var5 = new Tuple2(normalizedFeatureSum, BoxesRunTime.boxToDouble(numOfPoints));
         Vector normalizedFeatureSum = (Vector)var5._1();
         double numOfPoints = var5._2$mcD$sp();
         return (double)1 - org.apache.spark.ml.linalg.BLAS..MODULE$.dot(normalizedFeatures$1, normalizedFeatureSum) / numOfPoints;
      } else {
         throw new MatchError(var6);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$computeSilhouetteScore$6(final Broadcast bClustersStatsMap$2, final Vector x$11, final double x$12, final double x$13) {
      return MODULE$.computeSilhouetteCoefficient(bClustersStatsMap$2, x$11, x$12, x$13);
   }

   private CosineSilhouette$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
