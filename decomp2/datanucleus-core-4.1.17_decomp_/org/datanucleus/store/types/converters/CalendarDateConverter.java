package org.datanucleus.store.types.converters;

import java.util.Calendar;
import java.util.Date;

public class CalendarDateConverter implements TypeConverter {
   private static final long serialVersionUID = -1285232696965546003L;

   public Calendar toMemberType(Date date) {
      if (date == null) {
         return null;
      } else {
         Calendar cal = Calendar.getInstance();
         cal.setTime(date);
         return cal;
      }
   }

   public Date toDatastoreType(Calendar cal) {
      return cal != null ? cal.getTime() : null;
   }
}
