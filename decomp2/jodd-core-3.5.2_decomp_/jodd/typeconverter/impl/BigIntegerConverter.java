package jodd.typeconverter.impl;

import java.math.BigInteger;
import jodd.typeconverter.TypeConversionException;
import jodd.typeconverter.TypeConverter;

public class BigIntegerConverter implements TypeConverter {
   public BigInteger convert(Object value) {
      if (value == null) {
         return null;
      } else if (value instanceof BigInteger) {
         return (BigInteger)value;
      } else if (value instanceof Number) {
         return new BigInteger(String.valueOf(((Number)value).longValue()));
      } else {
         try {
            return new BigInteger(value.toString().trim());
         } catch (NumberFormatException nfex) {
            throw new TypeConversionException(value, nfex);
         }
      }
   }
}
