package jodd.typeconverter.impl;

import jodd.typeconverter.TypeConversionException;
import jodd.typeconverter.TypeConverter;
import jodd.util.StringUtil;

public class ShortConverter implements TypeConverter {
   public Short convert(Object value) {
      if (value == null) {
         return null;
      } else if (value.getClass() == Short.class) {
         return (Short)value;
      } else if (value instanceof Number) {
         return ((Number)value).shortValue();
      } else {
         try {
            String stringValue = value.toString().trim();
            if (StringUtil.startsWithChar(stringValue, '+')) {
               stringValue = stringValue.substring(1);
            }

            return Short.valueOf(stringValue);
         } catch (NumberFormatException nfex) {
            throw new TypeConversionException(value, nfex);
         }
      }
   }
}
