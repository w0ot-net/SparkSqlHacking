package jodd.typeconverter.impl;

import java.util.Calendar;
import java.util.Date;
import jodd.datetime.DateTimeStamp;
import jodd.datetime.JDateTime;
import jodd.datetime.JulianDateStamp;
import jodd.typeconverter.TypeConversionException;
import jodd.typeconverter.TypeConverter;
import jodd.util.StringUtil;

public class JDateTimeConverter implements TypeConverter {
   public JDateTime convert(Object value) {
      if (value == null) {
         return null;
      } else if (value instanceof JDateTime) {
         return (JDateTime)value;
      } else if (value instanceof Calendar) {
         return new JDateTime((Calendar)value);
      } else if (value instanceof Date) {
         return new JDateTime((Date)value);
      } else if (value instanceof Number) {
         return new JDateTime(((Number)value).longValue());
      } else if (value instanceof JulianDateStamp) {
         return new JDateTime((JulianDateStamp)value);
      } else if (value instanceof DateTimeStamp) {
         return new JDateTime((DateTimeStamp)value);
      } else {
         String stringValue = value.toString().trim();
         if (!StringUtil.containsOnlyDigits(stringValue)) {
            return new JDateTime(stringValue, "YYYY-MM-DD hh:mm:ss.mss");
         } else {
            try {
               long milliseconds = Long.parseLong(stringValue);
               return new JDateTime(milliseconds);
            } catch (NumberFormatException nfex) {
               throw new TypeConversionException(value, nfex);
            }
         }
      }
   }
}
