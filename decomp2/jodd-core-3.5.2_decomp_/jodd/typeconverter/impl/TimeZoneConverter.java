package jodd.typeconverter.impl;

import java.util.TimeZone;
import jodd.typeconverter.TypeConverter;

public class TimeZoneConverter implements TypeConverter {
   public TimeZone convert(Object value) {
      if (value == null) {
         return null;
      } else {
         return value.getClass() == TimeZone.class ? (TimeZone)value : TimeZone.getTimeZone(value.toString());
      }
   }
}
