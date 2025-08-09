package jodd.typeconverter.impl;

import jodd.typeconverter.TypeConversionException;
import jodd.typeconverter.TypeConverter;
import jodd.util.StringUtil;

public class DoubleConverter implements TypeConverter {
   public Double convert(Object value) {
      if (value == null) {
         return null;
      } else if (value.getClass() == Double.class) {
         return (Double)value;
      } else if (value instanceof Number) {
         return ((Number)value).doubleValue();
      } else {
         try {
            String stringValue = value.toString().trim();
            if (StringUtil.startsWithChar(stringValue, '+')) {
               stringValue = stringValue.substring(1);
            }

            return Double.valueOf(stringValue);
         } catch (NumberFormatException nfex) {
            throw new TypeConversionException(value, nfex);
         }
      }
   }
}
