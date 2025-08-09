package org.apache.spark.sql.types;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.runtime.AbstractFunction4;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class StructField$ extends AbstractFunction4 implements Serializable {
   public static final StructField$ MODULE$ = new StructField$();

   public boolean $lessinit$greater$default$3() {
      return true;
   }

   public Metadata $lessinit$greater$default$4() {
      return Metadata$.MODULE$.empty();
   }

   public final String toString() {
      return "StructField";
   }

   public StructField apply(final String name, final DataType dataType, final boolean nullable, final Metadata metadata) {
      return new StructField(name, dataType, nullable, metadata);
   }

   public boolean apply$default$3() {
      return true;
   }

   public Metadata apply$default$4() {
      return Metadata$.MODULE$.empty();
   }

   public Option unapply(final StructField x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.name(), x$0.dataType(), BoxesRunTime.boxToBoolean(x$0.nullable()), x$0.metadata())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StructField$.class);
   }

   private StructField$() {
   }
}
