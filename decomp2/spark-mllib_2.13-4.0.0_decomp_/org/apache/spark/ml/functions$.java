package org.apache.spark.ml;

import org.apache.spark.sql.Column;
import org.apache.spark.sql.Column.;

public final class functions$ {
   public static final functions$ MODULE$ = new functions$();

   public Column vector_to_array(final Column v, final String dtype) {
      return .MODULE$.internalFn("vector_to_array", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{v, org.apache.spark.sql.functions..MODULE$.lit(dtype)})));
   }

   public String vector_to_array$default$2() {
      return "float64";
   }

   public Column array_to_vector(final Column v) {
      return .MODULE$.internalFn("array_to_vector", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{v})));
   }

   private functions$() {
   }
}
