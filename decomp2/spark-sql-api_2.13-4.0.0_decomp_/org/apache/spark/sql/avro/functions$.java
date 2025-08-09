package org.apache.spark.sql.avro;

import java.util.Map;
import org.apache.spark.annotation.Experimental;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Column$;
import scala.runtime.ScalaRunTime.;

public final class functions$ {
   public static final functions$ MODULE$ = new functions$();

   @Experimental
   public Column from_avro(final Column data, final String jsonFormatSchema) {
      return Column$.MODULE$.fn("from_avro", .MODULE$.wrapRefArray(new Column[]{data, org.apache.spark.sql.functions$.MODULE$.lit(jsonFormatSchema)}));
   }

   @Experimental
   public Column from_avro(final Column data, final String jsonFormatSchema, final Map options) {
      return Column$.MODULE$.fnWithOptions("from_avro", scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(options).asScala().iterator(), .MODULE$.wrapRefArray(new Column[]{data, org.apache.spark.sql.functions$.MODULE$.lit(jsonFormatSchema)}));
   }

   @Experimental
   public Column to_avro(final Column data) {
      return Column$.MODULE$.fn("to_avro", .MODULE$.wrapRefArray(new Column[]{data}));
   }

   @Experimental
   public Column to_avro(final Column data, final String jsonFormatSchema) {
      return Column$.MODULE$.fn("to_avro", .MODULE$.wrapRefArray(new Column[]{data, org.apache.spark.sql.functions$.MODULE$.lit(jsonFormatSchema)}));
   }

   @Experimental
   public Column schema_of_avro(final String jsonFormatSchema) {
      return Column$.MODULE$.fn("schema_of_avro", .MODULE$.wrapRefArray(new Column[]{org.apache.spark.sql.functions$.MODULE$.lit(jsonFormatSchema)}));
   }

   @Experimental
   public Column schema_of_avro(final String jsonFormatSchema, final Map options) {
      return Column$.MODULE$.fnWithOptions("schema_of_avro", scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(options).asScala().iterator(), .MODULE$.wrapRefArray(new Column[]{org.apache.spark.sql.functions$.MODULE$.lit(jsonFormatSchema)}));
   }

   private functions$() {
   }
}
