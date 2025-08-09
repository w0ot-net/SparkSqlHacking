package org.apache.parquet.column.values;

public interface RequiresFallback {
   boolean shouldFallBack();

   boolean isCompressionSatisfying(long var1, long var3);

   void fallBackAllValuesTo(ValuesWriter var1);
}
