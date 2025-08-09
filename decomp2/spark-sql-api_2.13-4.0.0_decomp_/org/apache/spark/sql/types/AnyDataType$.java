package org.apache.spark.sql.types;

import java.io.Serializable;
import org.apache.spark.sql.errors.DataTypeErrors$;
import scala.runtime.ModuleSerializationProxy;

public final class AnyDataType$ extends AbstractDataType implements Serializable {
   public static final AnyDataType$ MODULE$ = new AnyDataType$();

   public DataType defaultConcreteType() {
      throw DataTypeErrors$.MODULE$.unsupportedOperationExceptionError();
   }

   public String simpleString() {
      return "any";
   }

   public boolean acceptsType(final DataType other) {
      return true;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AnyDataType$.class);
   }

   private AnyDataType$() {
   }
}
