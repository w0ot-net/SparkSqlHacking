package jodd.typeconverter.impl;

import java.util.Calendar;
import java.util.Date;
import jodd.datetime.JDateTime;
import jodd.typeconverter.TypeConversionException;
import jodd.typeconverter.TypeConverter;
import jodd.util.StringUtil;

public class DateConverter implements TypeConverter {
   public Date convert(Object value) {
      if (value == null) {
         return null;
      } else if (value instanceof Date) {
         return (Date)value;
      } else if (value instanceof Calendar) {
         return new Date(((Calendar)value).getTimeInMillis());
      } else if (value instanceof JDateTime) {
         return ((JDateTime)value).convertToDate();
      } else if (value instanceof Number) {
         return new Date(((Number)value).longValue());
      } else {
         String stringValue = value.toString().trim();
         if (!StringUtil.containsOnlyDigits(stringValue)) {
            JDateTime jdt = new JDateTime(stringValue, "YYYY-MM-DD hh:mm:ss.mss");
            return jdt.convertToDate();
         } else {
            try {
               long milliseconds = Long.parseLong(stringValue);
               return new Date(milliseconds);
            } catch (NumberFormatException nfex) {
               throw new TypeConversionException(value, nfex);
            }
         }
      }
   }
}
