package jodd.typeconverter.impl;

import jodd.typeconverter.TypeConversionException;
import jodd.typeconverter.TypeConverter;
import jodd.util.StringUtil;

public class IntegerConverter implements TypeConverter {
   public Integer convert(Object value) {
      if (value == null) {
         return null;
      } else if (value.getClass() == Integer.class) {
         return (Integer)value;
      } else if (value instanceof Number) {
         return ((Number)value).intValue();
      } else {
         try {
            String stringValue = value.toString().trim();
            if (StringUtil.startsWithChar(stringValue, '+')) {
               stringValue = stringValue.substring(1);
            }

            return Integer.valueOf(stringValue);
         } catch (NumberFormatException nfex) {
            throw new TypeConversionException(value, nfex);
         }
      }
   }
}
