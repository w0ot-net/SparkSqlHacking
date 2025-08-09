package jodd.typeconverter.impl;

import jodd.typeconverter.TypeConversionException;
import jodd.typeconverter.TypeConverter;
import jodd.util.StringUtil;

public class ByteConverter implements TypeConverter {
   public Byte convert(Object value) {
      if (value == null) {
         return null;
      } else if (value.getClass() == Byte.class) {
         return (Byte)value;
      } else if (value instanceof Number) {
         return ((Number)value).byteValue();
      } else {
         try {
            String stringValue = value.toString().trim();
            if (StringUtil.startsWithChar(stringValue, '+')) {
               stringValue = stringValue.substring(1);
            }

            return Byte.valueOf(stringValue);
         } catch (NumberFormatException nfex) {
            throw new TypeConversionException(value, nfex);
         }
      }
   }
}
