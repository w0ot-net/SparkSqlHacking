package org.apache.spark.sql.expressions.scalalang;

import org.apache.spark.sql.TypedColumn;
import org.apache.spark.sql.internal.TypedAverage;
import org.apache.spark.sql.internal.TypedCount;
import org.apache.spark.sql.internal.TypedSumDouble;
import org.apache.spark.sql.internal.TypedSumLong;
import scala.Function1;

/** @deprecated */
public final class typed$ {
   public static final typed$ MODULE$ = new typed$();

   public TypedColumn avg(final Function1 f) {
      return (new TypedAverage(f)).toColumn();
   }

   public TypedColumn count(final Function1 f) {
      return (new TypedCount(f)).toColumn();
   }

   public TypedColumn sum(final Function1 f) {
      return (new TypedSumDouble(f)).toColumn();
   }

   public TypedColumn sumLong(final Function1 f) {
      return (new TypedSumLong(f)).toColumn();
   }

   private typed$() {
   }
}
