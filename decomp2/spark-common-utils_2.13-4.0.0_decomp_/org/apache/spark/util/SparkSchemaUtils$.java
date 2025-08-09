package org.apache.spark.util;

public final class SparkSchemaUtils$ {
   public static final SparkSchemaUtils$ MODULE$ = new SparkSchemaUtils$();

   public String escapeMetaCharacters(final String str) {
      return str.replaceAll("\n", "\\\\n").replaceAll("\r", "\\\\r").replaceAll("\t", "\\\\t").replaceAll("\f", "\\\\f").replaceAll("\b", "\\\\b").replaceAll("\u000b", "\\\\v").replaceAll("\u0007", "\\\\a");
   }

   private SparkSchemaUtils$() {
   }
}
