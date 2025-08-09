package org.datanucleus.store.types.converters;

import java.sql.Timestamp;
import java.util.Calendar;

public class CalendarTimestampConverter implements TypeConverter {
   private static final long serialVersionUID = 8751571323606648248L;

   public Calendar toMemberType(Timestamp ts) {
      if (ts == null) {
         return null;
      } else {
         Calendar cal = Calendar.getInstance();
         cal.setTime(ts);
         return cal;
      }
   }

   public Timestamp toDatastoreType(Calendar cal) {
      return cal == null ? null : new Timestamp(cal.getTimeInMillis());
   }
}
