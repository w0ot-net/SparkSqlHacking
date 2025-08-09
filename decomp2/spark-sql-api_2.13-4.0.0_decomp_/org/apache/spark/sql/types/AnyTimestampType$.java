package org.apache.spark.sql.types;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class AnyTimestampType$ extends AbstractDataType implements Serializable {
   public static final AnyTimestampType$ MODULE$ = new AnyTimestampType$();

   public DataType defaultConcreteType() {
      return TimestampType$.MODULE$;
   }

   public boolean acceptsType(final DataType other) {
      return other instanceof TimestampType || other instanceof TimestampNTZType;
   }

   public String simpleString() {
      return "(timestamp or timestamp without time zone)";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AnyTimestampType$.class);
   }

   private AnyTimestampType$() {
   }
}
