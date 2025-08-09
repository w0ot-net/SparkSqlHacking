package org.apache.thrift.partial;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.apache.thrift.TEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnumCache {
   private static Logger LOG = LoggerFactory.getLogger(EnumCache.class);
   private Map classMap = new HashMap();

   public TEnum get(Class enumClass, int value) {
      Validate.checkNotNull(enumClass, "enumClass");
      Map<Integer, TEnum> valueMap = (Map)this.classMap.get(enumClass);
      if (valueMap == null) {
         valueMap = this.addClass(enumClass);
         if (valueMap == null) {
            return null;
         }
      }

      return (TEnum)valueMap.get(value);
   }

   private Map addClass(Class enumClass) {
      try {
         Method valuesMethod = enumClass.getMethod("values");
         TEnum[] enumValues = (TEnum[])valuesMethod.invoke((Object)null);
         Map<Integer, TEnum> valueMap = new HashMap();

         for(TEnum enumValue : enumValues) {
            valueMap.put(enumValue.getValue(), enumValue);
         }

         this.classMap.put(enumClass, valueMap);
         return valueMap;
      } catch (NoSuchMethodException e) {
         LOG.error("enum class does not have values() method", e);
         return null;
      } catch (IllegalAccessException e) {
         LOG.error("Enum.values() method should be public!", e);
         return null;
      } catch (InvocationTargetException e) {
         LOG.error("Enum.values() threw exception", e);
         return null;
      }
   }
}
