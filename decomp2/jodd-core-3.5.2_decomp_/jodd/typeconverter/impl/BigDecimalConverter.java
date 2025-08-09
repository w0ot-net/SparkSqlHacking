package jodd.typeconverter.impl;

import java.math.BigDecimal;
import jodd.typeconverter.TypeConversionException;
import jodd.typeconverter.TypeConverter;

public class BigDecimalConverter implements TypeConverter {
   public BigDecimal convert(Object value) {
      if (value == null) {
         return null;
      } else if (value instanceof BigDecimal) {
         return (BigDecimal)value;
      } else {
         try {
            return new BigDecimal(value.toString().trim());
         } catch (NumberFormatException nfex) {
            throw new TypeConversionException(value, nfex);
         }
      }
   }
}
