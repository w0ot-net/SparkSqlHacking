package org.apache.commons.text.lookup;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.ClassUtils;

class ConstantStringLookup extends AbstractStringLookup {
   private static final ConcurrentHashMap CONSTANT_CACHE = new ConcurrentHashMap();
   private static final char FIELD_SEPARATOR = '.';
   static final ConstantStringLookup INSTANCE = new ConstantStringLookup();

   static void clear() {
      CONSTANT_CACHE.clear();
   }

   protected Class fetchClass(String className) throws ClassNotFoundException {
      return ClassUtils.getClass(className);
   }

   public synchronized String lookup(String key) {
      if (key == null) {
         return null;
      } else {
         String result = (String)CONSTANT_CACHE.get(key);
         if (result != null) {
            return result;
         } else {
            int fieldPos = key.lastIndexOf(46);
            if (fieldPos < 0) {
               return null;
            } else {
               try {
                  Object value = this.resolveField(key.substring(0, fieldPos), key.substring(fieldPos + 1));
                  if (value != null) {
                     String string = Objects.toString(value, (String)null);
                     CONSTANT_CACHE.put(key, string);
                     result = string;
                  }

                  return result;
               } catch (Exception var6) {
                  return null;
               }
            }
         }
      }
   }

   protected Object resolveField(String className, String fieldName) throws ReflectiveOperationException {
      Class<?> clazz = this.fetchClass(className);
      return clazz == null ? null : clazz.getField(fieldName).get((Object)null);
   }
}
