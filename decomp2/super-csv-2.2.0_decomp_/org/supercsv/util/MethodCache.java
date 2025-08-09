package org.supercsv.util;

import java.lang.reflect.Method;

public class MethodCache {
   private final ThreeDHashMap setMethodsCache = new ThreeDHashMap();
   private final TwoDHashMap getCache = new TwoDHashMap();

   public Method getGetMethod(Object object, String fieldName) {
      if (object == null) {
         throw new NullPointerException("object should not be null");
      } else if (fieldName == null) {
         throw new NullPointerException("fieldName should not be null");
      } else {
         Method method = (Method)this.getCache.get(object.getClass().getName(), fieldName);
         if (method == null) {
            method = ReflectionUtils.findGetter(object, fieldName);
            this.getCache.set(object.getClass().getName(), fieldName, method);
         }

         return method;
      }
   }

   public Method getSetMethod(Object object, String fieldName, Class argumentType) {
      if (object == null) {
         throw new NullPointerException("object should not be null");
      } else if (fieldName == null) {
         throw new NullPointerException("fieldName should not be null");
      } else if (argumentType == null) {
         throw new NullPointerException("argumentType should not be null");
      } else {
         Method method = (Method)this.setMethodsCache.get(object.getClass(), argumentType, fieldName);
         if (method == null) {
            method = ReflectionUtils.findSetter(object, fieldName, argumentType);
            this.setMethodsCache.set(object.getClass(), argumentType, fieldName, method);
         }

         return method;
      }
   }
}
