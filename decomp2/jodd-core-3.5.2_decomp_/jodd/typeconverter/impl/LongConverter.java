package jodd.typeconverter.impl;

import jodd.typeconverter.TypeConversionException;
import jodd.typeconverter.TypeConverter;
import jodd.util.StringUtil;

public class LongConverter implements TypeConverter {
   public Long convert(Object value) {
      if (value == null) {
         return null;
      } else if (value.getClass() == Long.class) {
         return (Long)value;
      } else if (value instanceof Number) {
         return ((Number)value).longValue();
      } else {
         try {
            String stringValue = value.toString().trim();
            if (StringUtil.startsWithChar(stringValue, '+')) {
               stringValue = stringValue.substring(1);
            }

            return Long.valueOf(stringValue);
         } catch (NumberFormatException nfex) {
            throw new TypeConversionException(value, nfex);
         }
      }
   }
}
