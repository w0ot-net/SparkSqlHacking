package jodd.typeconverter.impl;

import jodd.typeconverter.TypeConversionException;
import jodd.typeconverter.TypeConverter;
import jodd.util.StringUtil;

public class FloatConverter implements TypeConverter {
   public Float convert(Object value) {
      if (value == null) {
         return null;
      } else if (value.getClass() == Float.class) {
         return (Float)value;
      } else if (value instanceof Number) {
         return ((Number)value).floatValue();
      } else {
         try {
            String stringValue = value.toString().trim();
            if (StringUtil.startsWithChar(stringValue, '+')) {
               stringValue = stringValue.substring(1);
            }

            return Float.valueOf(stringValue);
         } catch (NumberFormatException nfex) {
            throw new TypeConversionException(value, nfex);
         }
      }
   }
}
