package org.apache.spark.sql;

import java.io.Serializable;
import org.apache.spark.SparkContext;
import scala.runtime.ModuleSerializationProxy;

public final class SQLContext$ implements SQLContextCompanion, Serializable {
   public static final SQLContext$ MODULE$ = new SQLContext$();

   static {
      SQLContextCompanion.$init$(MODULE$);
   }

   /** @deprecated */
   public void setActive(final SQLContext sqlContext) {
      SQLContextCompanion.setActive$(this, sqlContext);
   }

   /** @deprecated */
   public void clearActive() {
      SQLContextCompanion.clearActive$(this);
   }

   /** @deprecated */
   public SQLContext getOrCreate(final SparkContext sparkContext) {
      return SparkSession$.MODULE$.builder().sparkContext(sparkContext).getOrCreate().sqlContext();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SQLContext$.class);
   }

   private SQLContext$() {
   }
}
