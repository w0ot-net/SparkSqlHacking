package org.apache.spark.api.java;

import org.apache.spark.SparkContext;
import org.apache.spark.SparkContext$;
import scala.Option.;
import scala.reflect.ClassTag;

public final class JavaSparkContext$ {
   public static final JavaSparkContext$ MODULE$ = new JavaSparkContext$();

   public JavaSparkContext fromSparkContext(final SparkContext sc) {
      return new JavaSparkContext(sc);
   }

   public SparkContext toSparkContext(final JavaSparkContext jsc) {
      return jsc.sc();
   }

   public String[] jarOfClass(final Class cls) {
      return (String[]).MODULE$.option2Iterable(SparkContext$.MODULE$.jarOfClass(cls)).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   public String[] jarOfObject(final Object obj) {
      return (String[]).MODULE$.option2Iterable(SparkContext$.MODULE$.jarOfObject(obj)).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   public ClassTag fakeClassTag() {
      return scala.reflect.ClassTag..MODULE$.AnyRef();
   }

   private JavaSparkContext$() {
   }
}
