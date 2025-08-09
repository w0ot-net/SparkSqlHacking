package org.sparkproject.dmg.pmml.adapters;

import org.sparkproject.dmg.pmml.ComplexValue;
import org.sparkproject.dmg.pmml.Entity;

public class ObjectUtil {
   private ObjectUtil() {
   }

   public static Object toSimpleValue(Object value) {
      if (value instanceof Entity) {
         Entity<?> entity = (Entity)value;
         return entity.getId();
      } else if (value instanceof ComplexValue) {
         ComplexValue complexValue = (ComplexValue)value;
         return complexValue.toSimpleValue();
      } else {
         return value;
      }
   }
}
