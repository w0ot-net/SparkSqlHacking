package org.datanucleus.properties;

public class StringPropertyValidator implements PropertyValidator {
   public boolean validate(String name, Object value) {
      if (value == null) {
         return true;
      } else {
         return value instanceof String;
      }
   }
}
