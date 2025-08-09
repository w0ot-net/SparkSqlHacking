package org.datanucleus.properties;

public class BooleanPropertyValidator implements PropertyValidator {
   public boolean validate(String name, Object value) {
      return validateValueIsBoolean(value);
   }

   public static boolean validateValueIsBoolean(Object value) {
      if (value == null) {
         return false;
      } else if (value instanceof Boolean) {
         return true;
      } else {
         if (value instanceof String) {
            String val = ((String)value).trim();
            if (val.equalsIgnoreCase("true") || val.equalsIgnoreCase("false")) {
               return true;
            }
         }

         return false;
      }
   }
}
