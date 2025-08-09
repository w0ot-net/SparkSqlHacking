package org.apache.spark.ml.stat;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.mllib.stat.Statistics$;
import org.apache.spark.mllib.stat.test.KolmogorovSmirnovTestResult;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import scala.Function1;
import scala.collection.immutable.Seq;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.ScalaRunTime.;

public final class KolmogorovSmirnovTest$ {
   public static final KolmogorovSmirnovTest$ MODULE$ = new KolmogorovSmirnovTest$();

   public Dataset test(final Dataset dataset, final String sampleCol, final String distName, final double... params) {
      return this.test(dataset, sampleCol, distName, (Seq).MODULE$.wrapDoubleArray(params));
   }

   private RDD getSampleRDD(final Dataset dataset, final String sampleCol) {
      SchemaUtils$.MODULE$.checkNumericType(dataset.schema(), sampleCol, SchemaUtils$.MODULE$.checkNumericType$default$3());
      return dataset.select(.MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(sampleCol).cast("double")}))).as(dataset.sparkSession().implicits().newDoubleEncoder()).rdd();
   }

   public Dataset test(final Dataset dataset, final String sampleCol, final Function1 cdf) {
      SparkSession spark = dataset.sparkSession();
      RDD rdd = this.getSampleRDD(dataset.toDF(), sampleCol);
      KolmogorovSmirnovTestResult testResult = Statistics$.MODULE$.kolmogorovSmirnovTest(rdd, cdf);
      scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new KolmogorovSmirnovTest.KolmogorovSmirnovTestResult(testResult.pValue(), testResult.statistic()), scala.collection.immutable.Nil..MODULE$);
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.stat.KolmogorovSmirnovTest.KolmogorovSmirnovTestResult").asType().toTypeConstructor();
         }

         public $typecreator1$1() {
         }
      }

      return spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1()));
   }

   public Dataset test(final Dataset dataset, final String sampleCol, final Function cdf) {
      return this.test(dataset, sampleCol, (Function1)((x) -> scala.Predef..MODULE$.Double2double((Double)cdf.call(scala.Predef..MODULE$.double2Double(x)))));
   }

   public Dataset test(final Dataset dataset, final String sampleCol, final String distName, final Seq params) {
      SparkSession spark = dataset.sparkSession();
      RDD rdd = this.getSampleRDD(dataset.toDF(), sampleCol);
      KolmogorovSmirnovTestResult testResult = Statistics$.MODULE$.kolmogorovSmirnovTest(rdd, distName, params);
      scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new KolmogorovSmirnovTest.KolmogorovSmirnovTestResult(testResult.pValue(), testResult.statistic()), scala.collection.immutable.Nil..MODULE$);
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

      final class $typecreator1$2 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.stat.KolmogorovSmirnovTest.KolmogorovSmirnovTestResult").asType().toTypeConstructor();
         }

         public $typecreator1$2() {
         }
      }

      return spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2()));
   }

   private KolmogorovSmirnovTest$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
