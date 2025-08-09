package org.apache.spark.ml.stat;

import java.lang.invoke.SerializedLambda;
import org.apache.commons.math3.distribution.FDistribution;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLImplicits;
import org.apache.spark.sql.SparkSession;
import scala.Function1;
import scala.MatchError;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;
import scala.Tuple5;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.reflect.runtime.package.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction2;

public final class FValueTest$ {
   public static final FValueTest$ MODULE$ = new FValueTest$();

   public Dataset test(final Dataset dataset, final String featuresCol, final String labelCol) {
      return this.test(dataset, featuresCol, labelCol, false);
   }

   public Dataset test(final Dataset dataset, final String featuresCol, final String labelCol, final boolean flatten) {
      SparkSession spark = dataset.sparkSession();
      SQLImplicits var10000 = spark.implicits();
      RDD var10001 = this.testRegression(dataset, featuresCol, labelCol);
      SQLImplicits var10002 = spark.implicits();
      JavaUniverse $u = .MODULE$.universe();
      JavaUniverse.JavaMirror $m = .MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

      final class $typecreator10$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple4"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Long").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)))));
         }

         public $typecreator10$1() {
         }
      }

      Dataset resultDF = var10000.rddToDatasetHolder(var10001, var10002.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator10$1()))).toDF(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"featureIndex", "pValue", "degreesOfFreedom", "fValue"})));
      if (flatten) {
         return resultDF;
      } else {
         Dataset var13 = resultDF.agg(org.apache.spark.sql.functions..MODULE$.collect_list(org.apache.spark.sql.functions..MODULE$.struct("*", scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$);
         SQLImplicits var15 = spark.implicits();
         JavaUniverse $u = .MODULE$.universe();
         JavaUniverse.JavaMirror $m = .MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator13$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("scala")), $m$untyped.staticModule("scala.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.package").asModule().moduleClass(), "Seq"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple4"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Long").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$))))), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator13$1() {
            }
         }

         var13 = var13.as(var15.newSequenceEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator13$1())));
         Function1 var16 = (seq) -> {
            Tuple4[] results = (Tuple4[])scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(seq.toArray(scala.reflect.ClassTag..MODULE$.apply(Tuple4.class))), (x$1) -> BoxesRunTime.boxToInteger($anonfun$test$2(x$1)), scala.math.Ordering.Int..MODULE$);
            Vector pValues = org.apache.spark.ml.linalg.Vectors..MODULE$.dense((double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])results), (x$2) -> BoxesRunTime.boxToDouble($anonfun$test$3(x$2)), scala.reflect.ClassTag..MODULE$.Double()));
            long[] degreesOfFreedom = (long[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])results), (x$3) -> BoxesRunTime.boxToLong($anonfun$test$4(x$3)), scala.reflect.ClassTag..MODULE$.Long());
            Vector fValues = org.apache.spark.ml.linalg.Vectors..MODULE$.dense((double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])results), (x$4) -> BoxesRunTime.boxToDouble($anonfun$test$5(x$4)), scala.reflect.ClassTag..MODULE$.Double()));
            return new Tuple3(pValues, degreesOfFreedom, fValues);
         };
         var10002 = spark.implicits();
         JavaUniverse $u = .MODULE$.universe();
         JavaUniverse.JavaMirror $m = .MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator19$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple3"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Array"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Long").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)), new scala.collection.immutable..colon.colon($m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$))));
            }

            public $typecreator19$1() {
            }
         }

         return var13.map(var16, var10002.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator19$1()))).toDF(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"pValues", "degreesOfFreedom", "fValues"})));
      }
   }

   public RDD testRegression(final Dataset dataset, final String featuresCol, final String labelCol) {
      SchemaUtils$.MODULE$.checkColumnType(dataset.schema(), featuresCol, new VectorUDT(), SchemaUtils$.MODULE$.checkColumnType$default$4());
      SchemaUtils$.MODULE$.checkNumericType(dataset.schema(), labelCol, SchemaUtils$.MODULE$.checkNumericType$default$3());
      SparkSession spark = dataset.sparkSession();
      Row var7 = (Row)dataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{Summarizer$.MODULE$.metrics((Seq)scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"mean", "std", "count"}))).summary(org.apache.spark.sql.functions..MODULE$.col(featuresCol)).as("summary"), org.apache.spark.sql.functions..MODULE$.avg(org.apache.spark.sql.functions..MODULE$.col(labelCol)).as("yMean"), org.apache.spark.sql.functions..MODULE$.stddev(org.apache.spark.sql.functions..MODULE$.col(labelCol)).as("yStd")}))).select("summary.mean", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"summary.std", "yMean", "yStd", "summary.count"}))).first();
      if (var7 != null) {
         Some var8 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var7);
         if (!var8.isEmpty() && var8.get() != null && ((SeqOps)var8.get()).lengthCompare(5) == 0) {
            Object xMeans = ((SeqOps)var8.get()).apply(0);
            Object xStd = ((SeqOps)var8.get()).apply(1);
            Object yMean = ((SeqOps)var8.get()).apply(2);
            Object yStd = ((SeqOps)var8.get()).apply(3);
            Object count = ((SeqOps)var8.get()).apply(4);
            if (xMeans instanceof Vector) {
               Vector var14 = (Vector)xMeans;
               if (xStd instanceof Vector) {
                  Vector var15 = (Vector)xStd;
                  if (yMean instanceof Double) {
                     double var16 = BoxesRunTime.unboxToDouble(yMean);
                     if (yStd instanceof Double) {
                        double var18 = BoxesRunTime.unboxToDouble(yStd);
                        if (count instanceof Long) {
                           long var20 = BoxesRunTime.unboxToLong(count);
                           Tuple5 var6 = new Tuple5(var14, var15, BoxesRunTime.boxToDouble(var16), BoxesRunTime.boxToDouble(var18), BoxesRunTime.boxToLong(var20));
                           Vector xMeans = (Vector)var6._1();
                           Vector xStd = (Vector)var6._2();
                           double yMean = BoxesRunTime.unboxToDouble(var6._3());
                           double yStd = BoxesRunTime.unboxToDouble(var6._4());
                           long count = BoxesRunTime.unboxToLong(var6._5());
                           int numFeatures = xMeans.size();
                           long degreesOfFreedom = count - 2L;
                           RDD var10000 = org.apache.spark.rdd.RDD..MODULE$;
                           Dataset var10001 = dataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(labelCol).cast("double"), org.apache.spark.sql.functions..MODULE$.col(featuresCol)})));
                           SQLImplicits var10002 = spark.implicits();
                           JavaUniverse $u = .MODULE$.universe();
                           JavaUniverse.JavaMirror $m = .MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

                           final class $typecreator5$1 extends TypeCreator {
                              public Types.TypeApi apply(final Mirror $m$untyped) {
                                 Universe $u = $m$untyped.universe();
                                 return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple2"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)));
                              }

                              public $typecreator5$1() {
                              }
                           }

                           RDD qual$1 = var10001.as(var10002.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator5$1()))).rdd();
                           Function1 x$1 = (iter) -> {
                              if (iter.hasNext()) {
                                 double[] array = (double[])scala.Array..MODULE$.ofDim(numFeatures, scala.reflect.ClassTag..MODULE$.Double());

                                 while(iter.hasNext()) {
                                    Tuple2 var8 = (Tuple2)iter.next();
                                    if (var8 == null) {
                                       throw new MatchError(var8);
                                    }

                                    double label = var8._1$mcD$sp();
                                    Vector features = (Vector)var8._2();
                                    Tuple2 var7 = new Tuple2(BoxesRunTime.boxToDouble(label), features);
                                    double label = var7._1$mcD$sp();
                                    Vector featuresx = (Vector)var7._2();
                                    double yDiff = label - yMean;
                                    if (yDiff != (double)0) {
                                       featuresx.iterator().zip(xMeans.iterator()).foreach((x0$1) -> {
                                          $anonfun$testRegression$2(array, yDiff, x0$1);
                                          return BoxedUnit.UNIT;
                                       });
                                    }
                                 }

                                 return scala.package..MODULE$.Iterator().tabulate(numFeatures, (col) -> $anonfun$testRegression$3(array, BoxesRunTime.unboxToInt(col)));
                              } else {
                                 return scala.package..MODULE$.Iterator().empty();
                              }
                           };
                           boolean x$2 = qual$1.mapPartitions$default$2();
                           RDD qual$2 = var10000.rddToPairRDDFunctions(qual$1.mapPartitions(x$1, x$2, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.Double(), scala.math.Ordering.Int..MODULE$).reduceByKey((JFunction2.mcDDD.sp)(x$7, x$8) -> x$7 + x$8);
                           Function1 x$3 = (iter) -> {
                              FDistribution fd = new FDistribution((double)1.0F, (double)degreesOfFreedom);
                              return iter.map((x0$2) -> {
                                 if (x0$2 != null) {
                                    int col = x0$2._1$mcI$sp();
                                    double sumForCov = x0$2._2$mcD$sp();
                                    double covariance = sumForCov / (double)(count - 1L);
                                    double corr = covariance / (yStd * xStd.apply(col));
                                    double fValue = corr * corr / ((double)1 - corr * corr) * (double)degreesOfFreedom;
                                    double pValue = (double)1.0F - fd.cumulativeProbability(fValue);
                                    return new Tuple4(BoxesRunTime.boxToInteger(col), BoxesRunTime.boxToDouble(pValue), BoxesRunTime.boxToLong(degreesOfFreedom), BoxesRunTime.boxToDouble(fValue));
                                 } else {
                                    throw new MatchError(x0$2);
                                 }
                              });
                           };
                           boolean x$4 = qual$2.mapPartitions$default$2();
                           return qual$2.mapPartitions(x$3, x$4, scala.reflect.ClassTag..MODULE$.apply(Tuple4.class));
                        }
                     }
                  }
               }
            }
         }
      }

      throw new MatchError(var7);
   }

   // $FF: synthetic method
   public static final int $anonfun$test$2(final Tuple4 x$1) {
      return BoxesRunTime.unboxToInt(x$1._1());
   }

   // $FF: synthetic method
   public static final double $anonfun$test$3(final Tuple4 x$2) {
      return BoxesRunTime.unboxToDouble(x$2._2());
   }

   // $FF: synthetic method
   public static final long $anonfun$test$4(final Tuple4 x$3) {
      return BoxesRunTime.unboxToLong(x$3._3());
   }

   // $FF: synthetic method
   public static final double $anonfun$test$5(final Tuple4 x$4) {
      return BoxesRunTime.unboxToDouble(x$4._4());
   }

   // $FF: synthetic method
   public static final void $anonfun$testRegression$2(final double[] array$1, final double yDiff$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Tuple2 var6 = (Tuple2)x0$1._1();
         Tuple2 var7 = (Tuple2)x0$1._2();
         if (var6 != null) {
            int col = var6._1$mcI$sp();
            double x = var6._2$mcD$sp();
            if (var7 != null) {
               double xMean = var7._2$mcD$sp();
               array$1[col] += yDiff$1 * (x - xMean);
               BoxedUnit var10000 = BoxedUnit.UNIT;
               return;
            }
         }
      }

      throw new MatchError(x0$1);
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$testRegression$3(final double[] array$1, final int col) {
      return new Tuple2.mcID.sp(col, array$1[col]);
   }

   private FValueTest$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
