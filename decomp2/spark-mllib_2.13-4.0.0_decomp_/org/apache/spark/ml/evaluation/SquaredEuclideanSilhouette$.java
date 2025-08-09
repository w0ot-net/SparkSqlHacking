package org.apache.spark.ml.evaluation;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
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
import scala.Function4;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple6;
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

public final class SquaredEuclideanSilhouette$ extends Silhouette {
   public static final SquaredEuclideanSilhouette$ MODULE$ = new SquaredEuclideanSilhouette$();
   private static boolean kryoRegistrationPerformed = false;

   public void registerKryoClasses(final SparkContext sc) {
      if (!kryoRegistrationPerformed) {
         sc.getConf().registerKryoClasses((Class[])((Object[])(new Class[]{SquaredEuclideanSilhouette.ClusterStats.class})));
         kryoRegistrationPerformed = true;
      }
   }

   public Map computeClusterStats(final Dataset df, final String predictionCol, final String featuresCol, final String weightCol) {
      int numFeatures = DatasetUtils$.MODULE$.getNumFeatures(df, featuresCol);
      RDD clustersStatsRDD = .MODULE$.rddToPairRDDFunctions(df.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(predictionCol).cast(org.apache.spark.sql.types.DoubleType..MODULE$), org.apache.spark.sql.functions..MODULE$.col(featuresCol), org.apache.spark.sql.functions..MODULE$.col("squaredNorm"), org.apache.spark.sql.functions..MODULE$.col(weightCol)}))).rdd().map((row) -> new Tuple2(BoxesRunTime.boxToDouble(row.getDouble(0)), new Tuple3(row.getAs(1), BoxesRunTime.boxToDouble(row.getDouble(2)), BoxesRunTime.boxToDouble(row.getDouble(3)))), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.Double(), scala.reflect.ClassTag..MODULE$.apply(Tuple3.class), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$).aggregateByKey(new Tuple3((Object)null, BoxesRunTime.boxToDouble((double)0.0F), BoxesRunTime.boxToDouble((double)0.0F)), (x0$1, x1$1) -> {
         Tuple2 var5 = new Tuple2(x0$1, x1$1);
         if (var5 != null) {
            if (var5 != null) {
               Tuple3 var9 = (Tuple3)var5._1();
               Tuple3 var10 = (Tuple3)var5._2();
               if (var9 != null) {
                  DenseVector featureSum = (DenseVector)var9._1();
                  double squaredNormSum = BoxesRunTime.unboxToDouble(var9._2());
                  double weightSum = BoxesRunTime.unboxToDouble(var9._3());
                  if (var10 != null) {
                     Vector features = (Vector)var10._1();
                     double squaredNorm = BoxesRunTime.unboxToDouble(var10._2());
                     double weight = BoxesRunTime.unboxToDouble(var10._3());
                     if (features != null) {
                        Tuple6 var7 = new Tuple6(featureSum, BoxesRunTime.boxToDouble(squaredNormSum), BoxesRunTime.boxToDouble(weightSum), features, BoxesRunTime.boxToDouble(squaredNorm), BoxesRunTime.boxToDouble(weight));
                        DenseVector featureSumx = (DenseVector)var7._1();
                        double squaredNormSumx = BoxesRunTime.unboxToDouble(var7._2());
                        double weightSumx = BoxesRunTime.unboxToDouble(var7._3());
                        Vector featuresx = (Vector)var7._4();
                        double squaredNormx = BoxesRunTime.unboxToDouble(var7._5());
                        double weightx = BoxesRunTime.unboxToDouble(var7._6());
                        DenseVector theFeatureSum = featureSumx == null ? org.apache.spark.ml.linalg.Vectors..MODULE$.zeros(numFeatures).toDense() : featureSumx;
                        org.apache.spark.ml.linalg.BLAS..MODULE$.axpy(weightx, featuresx, theFeatureSum);
                        return new Tuple3(theFeatureSum, BoxesRunTime.boxToDouble(squaredNormSumx + squaredNormx * weightx), BoxesRunTime.boxToDouble(weightSumx + weightx));
                     }
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
               Tuple3 var8 = (Tuple3)var4._1();
               Tuple3 var9 = (Tuple3)var4._2();
               if (var8 != null) {
                  DenseVector featureSum1 = (DenseVector)var8._1();
                  double squaredNormSum1 = BoxesRunTime.unboxToDouble(var8._2());
                  double weightSum1 = BoxesRunTime.unboxToDouble(var8._3());
                  if (var9 != null) {
                     DenseVector featureSum2 = (DenseVector)var9._1();
                     double squaredNormSum2 = BoxesRunTime.unboxToDouble(var9._2());
                     double weightSum2 = BoxesRunTime.unboxToDouble(var9._3());
                     Tuple6 var6 = new Tuple6(featureSum1, BoxesRunTime.boxToDouble(squaredNormSum1), BoxesRunTime.boxToDouble(weightSum1), featureSum2, BoxesRunTime.boxToDouble(squaredNormSum2), BoxesRunTime.boxToDouble(weightSum2));
                     DenseVector featureSum1 = (DenseVector)var6._1();
                     double squaredNormSum1x = BoxesRunTime.unboxToDouble(var6._2());
                     double weightSum1 = BoxesRunTime.unboxToDouble(var6._3());
                     DenseVector featureSum2 = (DenseVector)var6._4();
                     double squaredNormSum2 = BoxesRunTime.unboxToDouble(var6._5());
                     double weightSum2 = BoxesRunTime.unboxToDouble(var6._6());
                     DenseVector var10000;
                     if (featureSum1 == null) {
                        var10000 = featureSum2;
                     } else if (featureSum2 == null) {
                        var10000 = featureSum1;
                     } else {
                        org.apache.spark.ml.linalg.BLAS..MODULE$.axpy((double)1.0F, featureSum2, featureSum1);
                        var10000 = featureSum1;
                     }

                     DenseVector theFeatureSum = var10000;
                     return new Tuple3(theFeatureSum, BoxesRunTime.boxToDouble(squaredNormSum1x + squaredNormSum2), BoxesRunTime.boxToDouble(weightSum1 + weightSum2));
                  }
               }
            }

            throw new MatchError(var4);
         } else {
            throw new MatchError(var4);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple3.class));
      return (Map).MODULE$.rddToPairRDDFunctions(clustersStatsRDD, scala.reflect.ClassTag..MODULE$.Double(), scala.reflect.ClassTag..MODULE$.apply(Tuple3.class), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$).collectAsMap().toMap(scala..less.colon.less..MODULE$.refl()).transform((x0$3, x1$3) -> $anonfun$computeClusterStats$4(BoxesRunTime.unboxToDouble(x0$3), x1$3));
   }

   public double computeSilhouetteCoefficient(final Broadcast broadcastedClustersMap, final Vector point, final double clusterId, final double weight, final double squaredNorm) {
      return this.pointSilhouetteCoefficient(((MapOps)broadcastedClustersMap.value()).keySet(), clusterId, ((SquaredEuclideanSilhouette.ClusterStats)((scala.collection.MapOps)broadcastedClustersMap.value()).apply(BoxesRunTime.boxToDouble(clusterId))).weightSum(), weight, (JFunction1.mcDD.sp)(targetClusterId) -> compute$1(targetClusterId, broadcastedClustersMap, point, squaredNorm));
   }

   public double computeSilhouetteScore(final Dataset dataset, final String predictionCol, final String featuresCol, final String weightCol) {
      this.registerKryoClasses(dataset.sparkSession().sparkContext());
      functions var10000 = org.apache.spark.sql.functions..MODULE$;
      Function1 var10001 = (features) -> BoxesRunTime.boxToDouble($anonfun$computeSilhouetteScore$1(features));
      TypeTags.TypeTag var10002 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator1$1() {
         }
      }

      UserDefinedFunction squaredNormUDF = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1()));
      Dataset dfWithSquaredNorm = dataset.withColumn("squaredNorm", squaredNormUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(featuresCol)}))));
      Map clustersStatsMap = this.computeClusterStats(dfWithSquaredNorm, predictionCol, featuresCol, weightCol);
      scala.Predef..MODULE$.assert(clustersStatsMap.size() > 1, () -> "Number of clusters must be greater than one.");
      Broadcast bClustersStatsMap = dataset.sparkSession().sparkContext().broadcast(clustersStatsMap, scala.reflect.ClassTag..MODULE$.apply(Map.class));
      var10000 = org.apache.spark.sql.functions..MODULE$;
      Function4 var17 = (x$4, x$5, x$6, x$7) -> BoxesRunTime.boxToDouble($anonfun$computeSilhouetteScore$3(bClustersStatsMap, x$4, BoxesRunTime.unboxToDouble(x$5), BoxesRunTime.unboxToDouble(x$6), BoxesRunTime.unboxToDouble(x$7)));
      var10002 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

      final class $typecreator2$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator2$1() {
         }
      }

      UserDefinedFunction computeSilhouetteCoefficientUDF = var10000.udf(var17, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double());
      double silhouetteScore = this.overallScore(dfWithSquaredNorm, computeSilhouetteCoefficientUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(featuresCol), org.apache.spark.sql.functions..MODULE$.col(predictionCol).cast(org.apache.spark.sql.types.DoubleType..MODULE$), org.apache.spark.sql.functions..MODULE$.col(weightCol), org.apache.spark.sql.functions..MODULE$.col("squaredNorm")}))), org.apache.spark.sql.functions..MODULE$.col(weightCol));
      bClustersStatsMap.destroy();
      return silhouetteScore;
   }

   // $FF: synthetic method
   public static final SquaredEuclideanSilhouette.ClusterStats $anonfun$computeClusterStats$4(final double x0$3, final Tuple3 x1$3) {
      Tuple2 var4 = new Tuple2(BoxesRunTime.boxToDouble(x0$3), x1$3);
      if (var4 != null) {
         Tuple3 var5 = (Tuple3)var4._2();
         if (var5 != null) {
            DenseVector featureSum = (DenseVector)var5._1();
            double squaredNormSum = BoxesRunTime.unboxToDouble(var5._2());
            double weightSum = BoxesRunTime.unboxToDouble(var5._3());
            if (featureSum != null && true && true) {
               return new SquaredEuclideanSilhouette.ClusterStats(featureSum, squaredNormSum, weightSum);
            }
         }
      }

      throw new MatchError(var4);
   }

   private static final double compute$1(final double targetClusterId, final Broadcast broadcastedClustersMap$1, final Vector point$1, final double squaredNorm$1) {
      SquaredEuclideanSilhouette.ClusterStats clusterStats = (SquaredEuclideanSilhouette.ClusterStats)((scala.collection.MapOps)broadcastedClustersMap$1.value()).apply(BoxesRunTime.boxToDouble(targetClusterId));
      double pointDotClusterFeaturesSum = org.apache.spark.ml.linalg.BLAS..MODULE$.dot(point$1, clusterStats.featureSum());
      return squaredNorm$1 + clusterStats.squaredNormSum() / clusterStats.weightSum() - (double)2 * pointDotClusterFeaturesSum / clusterStats.weightSum();
   }

   // $FF: synthetic method
   public static final double $anonfun$computeSilhouetteScore$1(final Vector features) {
      return scala.math.package..MODULE$.pow(org.apache.spark.ml.linalg.Vectors..MODULE$.norm(features, (double)2.0F), (double)2.0F);
   }

   // $FF: synthetic method
   public static final double $anonfun$computeSilhouetteScore$3(final Broadcast bClustersStatsMap$1, final Vector x$4, final double x$5, final double x$6, final double x$7) {
      return MODULE$.computeSilhouetteCoefficient(bClustersStatsMap$1, x$4, x$5, x$6, x$7);
   }

   private SquaredEuclideanSilhouette$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
