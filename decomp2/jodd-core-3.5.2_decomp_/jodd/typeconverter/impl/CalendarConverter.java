package jodd.typeconverter.impl;

import java.util.Calendar;
import java.util.Date;
import jodd.datetime.JDateTime;
import jodd.typeconverter.TypeConversionException;
import jodd.typeconverter.TypeConverter;
import jodd.util.StringUtil;

public class CalendarConverter implements TypeConverter {
   public Calendar convert(Object value) {
      if (value == null) {
         return null;
      } else if (value instanceof Calendar) {
         return (Calendar)value;
      } else if (value instanceof Date) {
         Calendar calendar = Calendar.getInstance();
         calendar.setTime((Date)value);
         return calendar;
      } else if (value instanceof JDateTime) {
         return ((JDateTime)value).convertToCalendar();
      } else if (value instanceof Number) {
         Calendar calendar = Calendar.getInstance();
         calendar.setTimeInMillis(((Number)value).longValue());
         return calendar;
      } else {
         String stringValue = value.toString().trim();
         if (!StringUtil.containsOnlyDigits(stringValue)) {
            JDateTime jdt = new JDateTime(stringValue, "YYYY-MM-DD hh:mm:ss.mss");
            return jdt.convertToCalendar();
         } else {
            try {
               long milliseconds = Long.parseLong(stringValue);
               Calendar calendar = Calendar.getInstance();
               calendar.setTimeInMillis(milliseconds);
               return calendar;
            } catch (NumberFormatException nfex) {
               throw new TypeConversionException(value, nfex);
            }
         }
      }
   }
}
