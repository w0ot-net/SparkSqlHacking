package org.apache.spark.sql.types;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class UserDefinedType$ implements Serializable {
   public static final UserDefinedType$ MODULE$ = new UserDefinedType$();

   public DataType sqlType(final DataType dt) {
      if (dt instanceof UserDefinedType var4) {
         return var4.sqlType();
      } else {
         return dt;
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(UserDefinedType$.class);
   }

   private UserDefinedType$() {
   }
}
