package jodd.typeconverter.impl;

import jodd.typeconverter.TypeConversionException;
import jodd.typeconverter.TypeConverter;

public class BooleanConverter implements TypeConverter {
   public Boolean convert(Object value) {
      if (value == null) {
         return null;
      } else if (value.getClass() == Boolean.class) {
         return (Boolean)value;
      } else {
         String stringValue = value.toString().trim().toLowerCase();
         if (!stringValue.equals("yes") && !stringValue.equals("y") && !stringValue.equals("true") && !stringValue.equals("on") && !stringValue.equals("1")) {
            if (!stringValue.equals("no") && !stringValue.equals("n") && !stringValue.equals("false") && !stringValue.equals("off") && !stringValue.equals("0")) {
               throw new TypeConversionException(value);
            } else {
               return Boolean.FALSE;
            }
         } else {
            return Boolean.TRUE;
         }
      }
   }
}
