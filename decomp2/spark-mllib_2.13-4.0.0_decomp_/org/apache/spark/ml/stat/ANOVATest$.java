package org.apache.spark.ml.stat;

import java.lang.invoke.SerializedLambda;
import org.apache.commons.math3.distribution.FDistribution;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.SparseVector;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SQLImplicits;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.util.collection.OpenHashMap;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.reflect.runtime.package.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

public final class ANOVATest$ {
   public static final ANOVATest$ MODULE$ = new ANOVATest$();

   public Dataset test(final Dataset dataset, final String featuresCol, final String labelCol) {
      return this.test(dataset, featuresCol, labelCol, false);
   }

   public Dataset test(final Dataset dataset, final String featuresCol, final String labelCol, final boolean flatten) {
      SparkSession spark = dataset.sparkSession();
      SQLImplicits var10000 = spark.implicits();
      RDD var10001 = this.testClassification(dataset, featuresCol, labelCol);
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

   public RDD testClassification(final Dataset dataset, final String featuresCol, final String labelCol) {
      SparkSession spark = dataset.sparkSession();
      SchemaUtils$.MODULE$.checkColumnType(dataset.schema(), featuresCol, new VectorUDT(), SchemaUtils$.MODULE$.checkColumnType$default$4());
      SchemaUtils$.MODULE$.checkNumericType(dataset.schema(), labelCol, SchemaUtils$.MODULE$.checkNumericType$default$3());
      Dataset var10000 = dataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(labelCol).cast("double"), org.apache.spark.sql.functions..MODULE$.col(featuresCol)})));
      SQLImplicits var10001 = spark.implicits();
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

      RDD points = var10000.as(var10001.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator5$1()))).rdd();
      Vector var9 = (Vector)((Tuple2)points.first())._2();
      if (var9 instanceof DenseVector var10) {
         return this.testClassificationDenseFeatures(points, var10.size());
      } else if (var9 instanceof SparseVector var11) {
         return this.testClassificationSparseFeatures(points, var11.size());
      } else {
         throw new MatchError(var9);
      }
   }

   private RDD testClassificationDenseFeatures(final RDD points, final int numFeatures) {
      return org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(points.flatMap((x0$1) -> {
         if (x0$1 != null) {
            double label = x0$1._1$mcD$sp();
            Vector features = (Vector)x0$1._2();
            scala.Predef..MODULE$.require(features.size() == numFeatures, () -> "Number of features must be " + numFeatures + " but got " + features.size());
            return features.iterator().map((x0$2) -> {
               if (x0$2 != null) {
                  int col = x0$2._1$mcI$sp();
                  double value = x0$2._2$mcD$sp();
                  return new Tuple2(BoxesRunTime.boxToInteger(col), new Tuple2.mcDD.sp(label, value));
               } else {
                  throw new MatchError(x0$2);
               }
            });
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.math.Ordering.Int..MODULE$).aggregateByKey(new Tuple4(BoxesRunTime.boxToDouble((double)0.0F), BoxesRunTime.boxToDouble((double)0.0F), new OpenHashMap.mcD.sp(scala.reflect.ClassTag..MODULE$.Double(), scala.reflect.ClassTag..MODULE$.Double()), new OpenHashMap.mcJ.sp(scala.reflect.ClassTag..MODULE$.Double(), scala.reflect.ClassTag..MODULE$.Long())), (x0$3, x1$1) -> {
         Tuple2 var3 = new Tuple2(x0$3, x1$1);
         if (var3 != null) {
            Tuple4 var4 = (Tuple4)var3._1();
            Tuple2 var5 = (Tuple2)var3._2();
            if (var4 != null) {
               double sum = BoxesRunTime.unboxToDouble(var4._1());
               double sumOfSq = BoxesRunTime.unboxToDouble(var4._2());
               OpenHashMap sums = (OpenHashMap)var4._3();
               OpenHashMap counts = (OpenHashMap)var4._4();
               if (var5 != null) {
                  double label = var5._1$mcD$sp();
                  double value = var5._2$mcD$sp();
                  sums.changeValue$mcD$sp(BoxesRunTime.boxToDouble(label), (JFunction0.mcD.sp)() -> value, (JFunction1.mcDD.sp)(x$5) -> x$5 + value);
                  counts.changeValue$mcJ$sp(BoxesRunTime.boxToDouble(label), (JFunction0.mcJ.sp)() -> 1L, (JFunction1.mcJJ.sp)(x$6) -> x$6 + 1L);
                  return new Tuple4(BoxesRunTime.boxToDouble(sum + value), BoxesRunTime.boxToDouble(sumOfSq + value * value), sums, counts);
               }
            }
         }

         throw new MatchError(var3);
      }, (x0$4, x1$2) -> {
         Tuple2 var3 = new Tuple2(x0$4, x1$2);
         if (var3 != null) {
            Tuple4 var4 = (Tuple4)var3._1();
            Tuple4 var5 = (Tuple4)var3._2();
            if (var4 != null) {
               double sum1 = BoxesRunTime.unboxToDouble(var4._1());
               double sumOfSq1 = BoxesRunTime.unboxToDouble(var4._2());
               OpenHashMap sums1 = (OpenHashMap)var4._3();
               OpenHashMap counts1 = (OpenHashMap)var4._4();
               if (var5 != null) {
                  double sum2 = BoxesRunTime.unboxToDouble(var5._1());
                  double sumOfSq2 = BoxesRunTime.unboxToDouble(var5._2());
                  OpenHashMap sums2 = (OpenHashMap)var5._3();
                  OpenHashMap counts2 = (OpenHashMap)var5._4();
                  sums2.foreach((x0$5) -> BoxesRunTime.boxToDouble($anonfun$testClassificationDenseFeatures$10(sums1, x0$5)));
                  counts2.foreach((x0$6) -> BoxesRunTime.boxToLong($anonfun$testClassificationDenseFeatures$13(counts1, x0$6)));
                  return new Tuple4(BoxesRunTime.boxToDouble(sum1 + sum2), BoxesRunTime.boxToDouble(sumOfSq1 + sumOfSq2), sums1, counts1);
               }
            }
         }

         throw new MatchError(var3);
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple4.class)).map((x0$7) -> {
         if (x0$7 != null) {
            int col = x0$7._1$mcI$sp();
            Tuple4 var5 = (Tuple4)x0$7._2();
            if (var5 != null) {
               double sum = BoxesRunTime.unboxToDouble(var5._1());
               double sumOfSq = BoxesRunTime.unboxToDouble(var5._2());
               OpenHashMap sums = (OpenHashMap)var5._3();
               OpenHashMap counts = (OpenHashMap)var5._4();
               Tuple3 var13 = MODULE$.computeANOVA(sum, sumOfSq, sums.toMap(scala..less.colon.less..MODULE$.refl()), counts.toMap(scala..less.colon.less..MODULE$.refl()));
               if (var13 != null) {
                  double pValue = BoxesRunTime.unboxToDouble(var13._1());
                  long degreesOfFreedom = BoxesRunTime.unboxToLong(var13._2());
                  double fValue = BoxesRunTime.unboxToDouble(var13._3());
                  Tuple3 var12 = new Tuple3(BoxesRunTime.boxToDouble(pValue), BoxesRunTime.boxToLong(degreesOfFreedom), BoxesRunTime.boxToDouble(fValue));
                  double pValuex = BoxesRunTime.unboxToDouble(var12._1());
                  long degreesOfFreedom = BoxesRunTime.unboxToLong(var12._2());
                  double fValue = BoxesRunTime.unboxToDouble(var12._3());
                  return new Tuple4(BoxesRunTime.boxToInteger(col), BoxesRunTime.boxToDouble(pValuex), BoxesRunTime.boxToLong(degreesOfFreedom), BoxesRunTime.boxToDouble(fValue));
               }

               throw new MatchError(var13);
            }
         }

         throw new MatchError(x0$7);
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple4.class));
   }

   private RDD testClassificationSparseFeatures(final RDD points, final int numFeatures) {
      Map counts = points.map((x$10) -> BoxesRunTime.boxToDouble($anonfun$testClassificationSparseFeatures$1(x$10)), scala.reflect.ClassTag..MODULE$.Double()).countByValue(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$).toMap(scala..less.colon.less..MODULE$.refl());
      int numParts = points.getNumPartitions();
      return org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(points.mapPartitionsWithIndex((x0$1, x1$1) -> $anonfun$testClassificationSparseFeatures$2(numFeatures, numParts, BoxesRunTime.unboxToInt(x0$1), x1$1), points.mapPartitionsWithIndex$default$2(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.math.Ordering.Int..MODULE$).aggregateByKey(new Tuple3(BoxesRunTime.boxToDouble((double)0.0F), BoxesRunTime.boxToDouble((double)0.0F), new OpenHashMap.mcD.sp(scala.reflect.ClassTag..MODULE$.Double(), scala.reflect.ClassTag..MODULE$.Double())), (x0$4, x1$2) -> {
         Tuple2 var4 = new Tuple2(x0$4, x1$2);
         if (var4 != null) {
            Tuple3 var5 = (Tuple3)var4._1();
            Tuple2 labelAndValue = (Tuple2)var4._2();
            if (var5 != null) {
               double sum = BoxesRunTime.unboxToDouble(var5._1());
               double sumOfSq = BoxesRunTime.unboxToDouble(var5._2());
               OpenHashMap sums = (OpenHashMap)var5._3();
               if (labelAndValue != null) {
                  if (labelAndValue != null) {
                     double label = labelAndValue._1$mcD$sp();
                     double value = labelAndValue._2$mcD$sp();
                     Tuple2.mcDD.sp var12 = new Tuple2.mcDD.sp(label, value);
                     double labelx = ((Tuple2)var12)._1$mcD$sp();
                     double valuex = ((Tuple2)var12)._2$mcD$sp();
                     sums.changeValue$mcD$sp(BoxesRunTime.boxToDouble(labelx), (JFunction0.mcD.sp)() -> valuex, (JFunction1.mcDD.sp)(x$12) -> x$12 + valuex);
                     return new Tuple3(BoxesRunTime.boxToDouble(sum + valuex), BoxesRunTime.boxToDouble(sumOfSq + valuex * valuex), sums);
                  }

                  throw new MatchError(labelAndValue);
               }

               return new Tuple3(BoxesRunTime.boxToDouble(sum), BoxesRunTime.boxToDouble(sumOfSq), sums);
            }
         }

         throw new MatchError(var4);
      }, (x0$5, x1$3) -> {
         Tuple2 var3 = new Tuple2(x0$5, x1$3);
         if (var3 != null) {
            Tuple3 var4 = (Tuple3)var3._1();
            Tuple3 var5 = (Tuple3)var3._2();
            if (var4 != null) {
               double sum1 = BoxesRunTime.unboxToDouble(var4._1());
               double sumOfSq1 = BoxesRunTime.unboxToDouble(var4._2());
               OpenHashMap sums1 = (OpenHashMap)var4._3();
               if (var5 != null) {
                  double sum2 = BoxesRunTime.unboxToDouble(var5._1());
                  double sumOfSq2 = BoxesRunTime.unboxToDouble(var5._2());
                  OpenHashMap sums2 = (OpenHashMap)var5._3();
                  sums2.foreach((x0$6) -> BoxesRunTime.boxToDouble($anonfun$testClassificationSparseFeatures$12(sums1, x0$6)));
                  return new Tuple3(BoxesRunTime.boxToDouble(sum1 + sum2), BoxesRunTime.boxToDouble(sumOfSq1 + sumOfSq2), sums1);
               }
            }
         }

         throw new MatchError(var3);
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple3.class)).map((x0$7) -> {
         if (x0$7 != null) {
            int col = x0$7._1$mcI$sp();
            Tuple3 var6 = (Tuple3)x0$7._2();
            if (var6 != null) {
               double sum = BoxesRunTime.unboxToDouble(var6._1());
               double sumOfSq = BoxesRunTime.unboxToDouble(var6._2());
               OpenHashMap sums = (OpenHashMap)var6._3();
               counts.keysIterator().foreach((JFunction1.mcVD.sp)(label) -> {
                  if (!sums.contains(BoxesRunTime.boxToDouble(label))) {
                     sums.update$mcD$sp(BoxesRunTime.boxToDouble(label), (double)0.0F);
                  }
               });
               Tuple3 var13 = MODULE$.computeANOVA(sum, sumOfSq, sums.toMap(scala..less.colon.less..MODULE$.refl()), counts);
               if (var13 != null) {
                  double pValue = BoxesRunTime.unboxToDouble(var13._1());
                  long degreesOfFreedom = BoxesRunTime.unboxToLong(var13._2());
                  double fValue = BoxesRunTime.unboxToDouble(var13._3());
                  Tuple3 var12 = new Tuple3(BoxesRunTime.boxToDouble(pValue), BoxesRunTime.boxToLong(degreesOfFreedom), BoxesRunTime.boxToDouble(fValue));
                  double pValuex = BoxesRunTime.unboxToDouble(var12._1());
                  long degreesOfFreedom = BoxesRunTime.unboxToLong(var12._2());
                  double fValue = BoxesRunTime.unboxToDouble(var12._3());
                  return new Tuple4(BoxesRunTime.boxToInteger(col), BoxesRunTime.boxToDouble(pValuex), BoxesRunTime.boxToLong(degreesOfFreedom), BoxesRunTime.boxToDouble(fValue));
               }

               throw new MatchError(var13);
            }
         }

         throw new MatchError(x0$7);
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple4.class));
   }

   private Tuple3 computeANOVA(final double sum, final double sumOfSq, final Map sums, final Map counts) {
      long numSamples = BoxesRunTime.unboxToLong(counts.valuesIterator().sum(scala.math.Numeric.LongIsIntegral..MODULE$));
      int numClasses = counts.size();
      double sqSum = sum * sum;
      double ssTot = sumOfSq - sqSum / (double)numSamples;
      double totalSqSum = BoxesRunTime.unboxToDouble(sums.iterator().map((x0$1) -> BoxesRunTime.boxToDouble($anonfun$computeANOVA$1(counts, x0$1))).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
      double ssbn = totalSqSum - sqSum / (double)numSamples;
      double sswn = ssTot - ssbn;
      int dfbn = numClasses - 1;
      long dfwn = numSamples - (long)numClasses;
      double msb = ssbn / (double)dfbn;
      double msw = sswn / (double)dfwn;
      double fValue = msb / msw;
      double pValue = (double)1 - (new FDistribution((double)dfbn, (double)dfwn)).cumulativeProbability(fValue);
      long degreesOfFreedom = (long)dfbn + dfwn;
      return new Tuple3(BoxesRunTime.boxToDouble(pValue), BoxesRunTime.boxToLong(degreesOfFreedom), BoxesRunTime.boxToDouble(fValue));
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
   public static final double $anonfun$testClassificationDenseFeatures$10(final OpenHashMap sums1$1, final Tuple2 x0$5) {
      if (x0$5 != null) {
         double v = x0$5._1$mcD$sp();
         double w = x0$5._2$mcD$sp();
         return sums1$1.changeValue$mcD$sp(BoxesRunTime.boxToDouble(v), (JFunction0.mcD.sp)() -> w, (JFunction1.mcDD.sp)(x$7) -> x$7 + w);
      } else {
         throw new MatchError(x0$5);
      }
   }

   // $FF: synthetic method
   public static final long $anonfun$testClassificationDenseFeatures$13(final OpenHashMap counts1$1, final Tuple2 x0$6) {
      if (x0$6 != null) {
         double v = x0$6._1$mcD$sp();
         long w = x0$6._2$mcJ$sp();
         return counts1$1.changeValue$mcJ$sp(BoxesRunTime.boxToDouble(v), (JFunction0.mcJ.sp)() -> w, (JFunction1.mcJJ.sp)(x$8) -> x$8 + w);
      } else {
         throw new MatchError(x0$6);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$testClassificationSparseFeatures$1(final Tuple2 x$10) {
      return x$10._1$mcD$sp();
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$testClassificationSparseFeatures$7(final int col) {
      return new Tuple2(BoxesRunTime.boxToInteger(col), (Object)null);
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$testClassificationSparseFeatures$2(final int numFeatures$2, final int numParts$1, final int x0$1, final Iterator x1$1) {
      Tuple2 var5 = new Tuple2(BoxesRunTime.boxToInteger(x0$1), x1$1);
      if (var5 != null) {
         int pid = var5._1$mcI$sp();
         Iterator iter = (Iterator)var5._2();
         return iter.flatMap((x0$2) -> {
            if (x0$2 != null) {
               double label = x0$2._1$mcD$sp();
               Vector features = (Vector)x0$2._2();
               scala.Predef..MODULE$.require(features.size() == numFeatures$2, () -> "Number of features must be " + numFeatures$2 + " but got " + features.size());
               return features.nonZeroIterator().map((x0$3) -> {
                  if (x0$3 != null) {
                     int col = x0$3._1$mcI$sp();
                     double value = x0$3._2$mcD$sp();
                     return new Tuple2(BoxesRunTime.boxToInteger(col), new Tuple2.mcDD.sp(label, value));
                  } else {
                     throw new MatchError(x0$3);
                  }
               });
            } else {
               throw new MatchError(x0$2);
            }
         }).$plus$plus(() -> scala.package..MODULE$.Iterator().range(pid, numFeatures$2, numParts$1).map((col) -> $anonfun$testClassificationSparseFeatures$7(BoxesRunTime.unboxToInt(col))));
      } else {
         throw new MatchError(var5);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$testClassificationSparseFeatures$12(final OpenHashMap sums1$2, final Tuple2 x0$6) {
      if (x0$6 != null) {
         double v = x0$6._1$mcD$sp();
         double w = x0$6._2$mcD$sp();
         return sums1$2.changeValue$mcD$sp(BoxesRunTime.boxToDouble(v), (JFunction0.mcD.sp)() -> w, (JFunction1.mcDD.sp)(x$13) -> x$13 + w);
      } else {
         throw new MatchError(x0$6);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$computeANOVA$1(final Map counts$2, final Tuple2 x0$1) {
      if (x0$1 != null) {
         double label = x0$1._1$mcD$sp();
         double sum = x0$1._2$mcD$sp();
         return sum * sum / (double)BoxesRunTime.unboxToLong(counts$2.apply(BoxesRunTime.boxToDouble(label)));
      } else {
         throw new MatchError(x0$1);
      }
   }

   private ANOVATest$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
