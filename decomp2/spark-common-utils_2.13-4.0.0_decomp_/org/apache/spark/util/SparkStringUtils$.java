package org.apache.spark.util;

import scala.collection.immutable.Seq;

public final class SparkStringUtils$ implements SparkStringUtils {
   public static final SparkStringUtils$ MODULE$ = new SparkStringUtils$();

   static {
      SparkStringUtils.$init$(MODULE$);
   }

   public Seq stringToSeq(final String str) {
      return SparkStringUtils.stringToSeq$(this, str);
   }

   private SparkStringUtils$() {
   }
}
