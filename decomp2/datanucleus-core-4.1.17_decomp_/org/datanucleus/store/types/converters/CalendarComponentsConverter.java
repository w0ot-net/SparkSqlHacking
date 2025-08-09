package org.datanucleus.store.types.converters;

import java.util.Calendar;
import java.util.TimeZone;

public class CalendarComponentsConverter implements TypeConverter, MultiColumnConverter {
   private static final long serialVersionUID = 4725781321319221471L;

   public Calendar toMemberType(Object[] ts) {
      if (ts == null) {
         return null;
      } else {
         Calendar cal = Calendar.getInstance();
         cal.setTimeInMillis((Long)ts[0]);
         cal.setTimeZone(TimeZone.getTimeZone((String)ts[1]));
         return cal;
      }
   }

   public Object[] toDatastoreType(Calendar cal) {
      return cal == null ? null : new Object[]{cal.getTimeInMillis(), cal.getTimeZone().getID()};
   }

   public Class[] getDatastoreColumnTypes() {
      return new Class[]{Long.class, String.class};
   }
}
