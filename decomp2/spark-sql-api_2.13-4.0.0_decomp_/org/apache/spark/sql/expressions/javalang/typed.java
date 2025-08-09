package org.apache.spark.sql.expressions.javalang;

import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.TypedColumn;
import org.apache.spark.sql.internal.TypedAverage;
import org.apache.spark.sql.internal.TypedCount;
import org.apache.spark.sql.internal.TypedSumDouble;
import org.apache.spark.sql.internal.TypedSumLong;

/** @deprecated */
@Deprecated(
   since = "3.0.0"
)
public class typed {
   public static TypedColumn avg(MapFunction f) {
      return (new TypedAverage(f)).toColumnJava();
   }

   public static TypedColumn count(MapFunction f) {
      return (new TypedCount(f)).toColumnJava();
   }

   public static TypedColumn sum(MapFunction f) {
      return (new TypedSumDouble(f)).toColumnJava();
   }

   public static TypedColumn sumLong(MapFunction f) {
      return (new TypedSumLong(f)).toColumnJava();
   }
}
