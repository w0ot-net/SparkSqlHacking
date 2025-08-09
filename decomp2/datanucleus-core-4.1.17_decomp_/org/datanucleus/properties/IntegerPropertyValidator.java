package org.datanucleus.properties;

public class IntegerPropertyValidator implements PropertyValidator {
   public boolean validate(String name, Object value) {
      if (value == null) {
         return false;
      } else if (value instanceof Integer) {
         return true;
      } else if (value instanceof String) {
         String val = ((String)value).trim();

         try {
            Integer.valueOf(val);
            return true;
         } catch (NumberFormatException var5) {
            return false;
         }
      } else {
         return false;
      }
   }
}
