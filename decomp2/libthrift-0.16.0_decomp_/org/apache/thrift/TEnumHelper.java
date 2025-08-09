package org.apache.thrift;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TEnumHelper {
   public static TEnum getByValue(Class enumClass, int value) {
      try {
         Method method = enumClass.getMethod("findByValue", Integer.TYPE);
         return (TEnum)method.invoke((Object)null, value);
      } catch (NoSuchMethodException var3) {
         return null;
      } catch (IllegalAccessException var4) {
         return null;
      } catch (InvocationTargetException var5) {
         return null;
      }
   }
}
