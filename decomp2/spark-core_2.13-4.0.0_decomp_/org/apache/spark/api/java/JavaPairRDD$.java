package org.apache.spark.api.java;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.rdd.RDD;
import org.apache.spark.rdd.RDD$;
import scala.Function1;
import scala.Function2;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;
import scala.collection.Iterable;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag.;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Null;

public final class JavaPairRDD$ implements Serializable {
   public static final JavaPairRDD$ MODULE$ = new JavaPairRDD$();

   public RDD groupByResultToJava(final RDD rdd, final ClassTag evidence$1) {
      ClassTag x$3 = .MODULE$.apply(Iterable.class);
      Null x$4 = RDD$.MODULE$.rddToPairRDDFunctions$default$4(rdd);
      return RDD$.MODULE$.rddToPairRDDFunctions(rdd, evidence$1, x$3, (Ordering)null).mapValues((x$5) -> scala.jdk.CollectionConverters..MODULE$.IterableHasAsJava(x$5).asJava());
   }

   public RDD cogroupResultToJava(final RDD rdd, final ClassTag evidence$2) {
      ClassTag x$3 = .MODULE$.apply(Tuple2.class);
      Null x$4 = RDD$.MODULE$.rddToPairRDDFunctions$default$4(rdd);
      return RDD$.MODULE$.rddToPairRDDFunctions(rdd, evidence$2, x$3, (Ordering)null).mapValues((x) -> new Tuple2(scala.jdk.CollectionConverters..MODULE$.IterableHasAsJava((Iterable)x._1()).asJava(), scala.jdk.CollectionConverters..MODULE$.IterableHasAsJava((Iterable)x._2()).asJava()));
   }

   public RDD cogroupResult2ToJava(final RDD rdd, final ClassTag evidence$3) {
      ClassTag x$3 = .MODULE$.apply(Tuple3.class);
      Null x$4 = RDD$.MODULE$.rddToPairRDDFunctions$default$4(rdd);
      return RDD$.MODULE$.rddToPairRDDFunctions(rdd, evidence$3, x$3, (Ordering)null).mapValues((x) -> new Tuple3(scala.jdk.CollectionConverters..MODULE$.IterableHasAsJava((Iterable)x._1()).asJava(), scala.jdk.CollectionConverters..MODULE$.IterableHasAsJava((Iterable)x._2()).asJava(), scala.jdk.CollectionConverters..MODULE$.IterableHasAsJava((Iterable)x._3()).asJava()));
   }

   public RDD cogroupResult3ToJava(final RDD rdd, final ClassTag evidence$4) {
      ClassTag x$3 = .MODULE$.apply(Tuple4.class);
      Null x$4 = RDD$.MODULE$.rddToPairRDDFunctions$default$4(rdd);
      return RDD$.MODULE$.rddToPairRDDFunctions(rdd, evidence$4, x$3, (Ordering)null).mapValues((x) -> new Tuple4(scala.jdk.CollectionConverters..MODULE$.IterableHasAsJava((Iterable)x._1()).asJava(), scala.jdk.CollectionConverters..MODULE$.IterableHasAsJava((Iterable)x._2()).asJava(), scala.jdk.CollectionConverters..MODULE$.IterableHasAsJava((Iterable)x._3()).asJava(), scala.jdk.CollectionConverters..MODULE$.IterableHasAsJava((Iterable)x._4()).asJava()));
   }

   public JavaPairRDD fromRDD(final RDD rdd, final ClassTag evidence$5, final ClassTag evidence$6) {
      return new JavaPairRDD(rdd, evidence$5, evidence$6);
   }

   public RDD toRDD(final JavaPairRDD rdd) {
      return rdd.rdd();
   }

   public Function2 toScalaFunction2(final org.apache.spark.api.java.function.Function2 fun) {
      return (x, x1) -> fun.call(x, x1);
   }

   public Function1 toScalaFunction(final Function fun) {
      return (x) -> fun.call(x);
   }

   public Function1 pairFunToScalaFun(final PairFunction x) {
      return (y) -> x.call(y);
   }

   public JavaPairRDD fromJavaRDD(final JavaRDD rdd) {
      ClassTag ctagK = JavaSparkContext$.MODULE$.fakeClassTag();
      ClassTag ctagV = JavaSparkContext$.MODULE$.fakeClassTag();
      return new JavaPairRDD(rdd.rdd(), ctagK, ctagV);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JavaPairRDD$.class);
   }

   private JavaPairRDD$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
