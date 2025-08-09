package org.apache.spark.util;

public final class ArrayImplicits$ {
   public static final ArrayImplicits$ MODULE$ = new ArrayImplicits$();

   public ArrayImplicits.SparkArrayOps SparkArrayOps(final Object xs) {
      return new ArrayImplicits.SparkArrayOps(xs);
   }

   private ArrayImplicits$() {
   }
}
