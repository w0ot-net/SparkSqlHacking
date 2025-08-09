package org.apache.spark.sql.catalyst.util;

import org.apache.spark.sql.types.DataType;

public final class SparkCharVarcharUtils$ implements SparkCharVarcharUtils {
   public static final SparkCharVarcharUtils$ MODULE$ = new SparkCharVarcharUtils$();

   static {
      SparkCharVarcharUtils.$init$(MODULE$);
   }

   public boolean hasCharVarchar(final DataType dt) {
      return SparkCharVarcharUtils.hasCharVarchar$(this, dt);
   }

   public DataType failIfHasCharVarchar(final DataType dt) {
      return SparkCharVarcharUtils.failIfHasCharVarchar$(this, dt);
   }

   public DataType replaceCharVarcharWithString(final DataType dt) {
      return SparkCharVarcharUtils.replaceCharVarcharWithString$(this, dt);
   }

   private SparkCharVarcharUtils$() {
   }
}
