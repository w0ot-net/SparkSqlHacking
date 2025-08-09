package org.apache.spark.util;

import scala.collection.Iterable;
import scala.collection.immutable.Map;

public final class SparkCollectionUtils$ implements SparkCollectionUtils {
   public static final SparkCollectionUtils$ MODULE$ = new SparkCollectionUtils$();

   static {
      SparkCollectionUtils.$init$(MODULE$);
   }

   public Map toMapWithIndex(final Iterable keys) {
      return SparkCollectionUtils.toMapWithIndex$(this, keys);
   }

   private SparkCollectionUtils$() {
   }
}
