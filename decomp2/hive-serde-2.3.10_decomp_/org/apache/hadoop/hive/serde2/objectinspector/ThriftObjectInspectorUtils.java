package org.apache.hadoop.hive.serde2.objectinspector;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class ThriftObjectInspectorUtils {
   public static Type getFieldType(Class containingClass, String fieldName) {
      String suffix = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

      for(String prefix : new String[]{"get", "is"}) {
         try {
            Method method = containingClass.getDeclaredMethod(prefix + suffix);
            return method.getGenericReturnType();
         } catch (NoSuchMethodException var9) {
         }
      }

      for(String prefix : new String[]{"get_", "is_"}) {
         try {
            Method method = containingClass.getDeclaredMethod(prefix + fieldName);
            return method.getGenericReturnType();
         } catch (NoSuchMethodException var8) {
         }
      }

      throw new RuntimeException("Could not find type for " + fieldName + " in " + containingClass);
   }
}
