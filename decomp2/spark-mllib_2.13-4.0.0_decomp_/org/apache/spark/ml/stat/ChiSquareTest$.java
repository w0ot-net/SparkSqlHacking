package org.apache.spark.ml.stat;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.stat.test.ChiSqTest$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SQLImplicits;
import org.apache.spark.sql.SparkSession;
import scala.Function1;
import scala.MatchError;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;
import scala.collection.SeqOps;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

public final class ChiSquareTest$ {
   public static final ChiSquareTest$ MODULE$ = new ChiSquareTest$();

   public Dataset test(final Dataset dataset, final String featuresCol, final String labelCol) {
      return this.test(dataset, featuresCol, labelCol, false);
   }

   public Dataset test(final Dataset dataset, final String featuresCol, final String labelCol, final boolean flatten) {
      SchemaUtils$.MODULE$.checkColumnType(dataset.schema(), featuresCol, new VectorUDT(), SchemaUtils$.MODULE$.checkColumnType$default$4());
      SchemaUtils$.MODULE$.checkNumericType(dataset.schema(), labelCol, SchemaUtils$.MODULE$.checkNumericType$default$3());
      SparkSession spark = dataset.sparkSession();
      RDD data = dataset.select(.MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(labelCol).cast("double"), org.apache.spark.sql.functions..MODULE$.col(featuresCol)}))).rdd().map((x0$1) -> {
         if (x0$1 != null) {
            Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
            if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(2) == 0) {
               Object label = ((SeqOps)var3.get()).apply(0);
               Object vec = ((SeqOps)var3.get()).apply(1);
               if (label instanceof Double) {
                  double var6 = BoxesRunTime.unboxToDouble(label);
                  if (vec instanceof Vector) {
                     Vector var8 = (Vector)vec;
                     return new Tuple2(BoxesRunTime.boxToDouble(var6), Vectors$.MODULE$.fromML(var8));
                  }
               }
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      SQLImplicits var10000 = spark.implicits();
      RDD var10001 = ChiSqTest$.MODULE$.computeChiSquared(data, ChiSqTest$.MODULE$.computeChiSquared$default$2()).map((x0$2) -> {
         if (x0$2 != null) {
            int col = BoxesRunTime.unboxToInt(x0$2._1());
            double pValue = BoxesRunTime.unboxToDouble(x0$2._2());
            int degreesOfFreedom = BoxesRunTime.unboxToInt(x0$2._3());
            double statistic = BoxesRunTime.unboxToDouble(x0$2._4());
            return new Tuple4(BoxesRunTime.boxToInteger(col), BoxesRunTime.boxToDouble(pValue), BoxesRunTime.boxToInteger(degreesOfFreedom), BoxesRunTime.boxToDouble(statistic));
         } else {
            throw new MatchError(x0$2);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple4.class));
      SQLImplicits var10002 = spark.implicits();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

      final class $typecreator10$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple4"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)))));
         }

         public $typecreator10$1() {
         }
      }

      Dataset resultDF = var10000.rddToDatasetHolder(var10001, var10002.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator10$1()))).toDF(.MODULE$.wrapRefArray((Object[])(new String[]{"featureIndex", "pValue", "degreesOfFreedom", "statistic"})));
      if (flatten) {
         return resultDF;
      } else {
         Dataset var14 = resultDF.agg(org.apache.spark.sql.functions..MODULE$.collect_list(org.apache.spark.sql.functions..MODULE$.struct("*", scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$);
         SQLImplicits var16 = spark.implicits();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator13$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("scala")), $m$untyped.staticModule("scala.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.package").asModule().moduleClass(), "Seq"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple4"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$))))), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator13$1() {
            }
         }

         var14 = var14.as(var16.newSequenceEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator13$1())));
         Function1 var17 = (seq) -> {
            Tuple4[] results = (Tuple4[])scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(seq.toArray(scala.reflect.ClassTag..MODULE$.apply(Tuple4.class))), (x$1) -> BoxesRunTime.boxToInteger($anonfun$test$4(x$1)), scala.math.Ordering.Int..MODULE$);
            Vector pValues = org.apache.spark.ml.linalg.Vectors..MODULE$.dense((double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])results), (x$2) -> BoxesRunTime.boxToDouble($anonfun$test$5(x$2)), scala.reflect.ClassTag..MODULE$.Double()));
            int[] degreesOfFreedom = (int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])results), (x$3) -> BoxesRunTime.boxToInteger($anonfun$test$6(x$3)), scala.reflect.ClassTag..MODULE$.Int());
            Vector statistics = org.apache.spark.ml.linalg.Vectors..MODULE$.dense((double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])results), (x$4) -> BoxesRunTime.boxToDouble($anonfun$test$7(x$4)), scala.reflect.ClassTag..MODULE$.Double()));
            return new Tuple3(pValues, degreesOfFreedom, statistics);
         };
         var10002 = spark.implicits();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator19$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple3"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Array"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)), new scala.collection.immutable..colon.colon($m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$))));
            }

            public $typecreator19$1() {
            }
         }

         return var14.map(var17, var10002.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator19$1()))).toDF(.MODULE$.wrapRefArray((Object[])(new String[]{"pValues", "degreesOfFreedom", "statistics"})));
      }
   }

   // $FF: synthetic method
   public static final int $anonfun$test$4(final Tuple4 x$1) {
      return BoxesRunTime.unboxToInt(x$1._1());
   }

   // $FF: synthetic method
   public static final double $anonfun$test$5(final Tuple4 x$2) {
      return BoxesRunTime.unboxToDouble(x$2._2());
   }

   // $FF: synthetic method
   public static final int $anonfun$test$6(final Tuple4 x$3) {
      return BoxesRunTime.unboxToInt(x$3._3());
   }

   // $FF: synthetic method
   public static final double $anonfun$test$7(final Tuple4 x$4) {
      return BoxesRunTime.unboxToDouble(x$4._4());
   }

   private ChiSquareTest$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
