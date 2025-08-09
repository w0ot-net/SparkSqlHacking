package org.apache.hadoop.hive.ql.exec;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;

public class SparkDefaultUDFMethodResolver implements UDFMethodResolver {
   private final Class udfClass;

   public SparkDefaultUDFMethodResolver(DefaultUDFMethodResolver wrapped) {
      try {
         Field udfClassField = wrapped.getClass().getDeclaredField("udfClass");
         udfClassField.setAccessible(true);
         this.udfClass = (Class)udfClassField.get(wrapped);
      } catch (ReflectiveOperationException rethrow) {
         throw new RuntimeException(rethrow);
      }
   }

   public Method getEvalMethod(List argClasses) throws UDFArgumentException {
      return HiveFunctionRegistryUtils.getMethodInternal(this.udfClass, "evaluate", false, argClasses);
   }
}
