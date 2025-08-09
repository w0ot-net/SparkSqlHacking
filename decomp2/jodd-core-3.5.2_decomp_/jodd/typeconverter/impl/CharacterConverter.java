package jodd.typeconverter.impl;

import jodd.typeconverter.TypeConversionException;
import jodd.typeconverter.TypeConverter;
import jodd.util.StringUtil;

public class CharacterConverter implements TypeConverter {
   public Character convert(Object value) {
      if (value == null) {
         return null;
      } else if (value.getClass() == Character.class) {
         return (Character)value;
      } else if (value instanceof Number) {
         char c = (char)((Number)value).intValue();
         return c;
      } else {
         try {
            String s = value.toString();
            if (s.length() != 1) {
               s = s.trim();
               if (!StringUtil.containsOnlyDigitsAndSigns(s)) {
                  throw new TypeConversionException(value);
               } else {
                  try {
                     char c = (char)Integer.parseInt(s);
                     return c;
                  } catch (NumberFormatException nfex) {
                     throw new TypeConversionException(value, nfex);
                  }
               }
            } else {
               return s.charAt(0);
            }
         } catch (IndexOutOfBoundsException ioobex) {
            throw new TypeConversionException(value, ioobex);
         }
      }
   }
}
