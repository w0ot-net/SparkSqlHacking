package org.datanucleus.store.types.converters;

import java.util.TimeZone;

public class TimeZoneStringConverter implements TypeConverter, ColumnLengthDefiningTypeConverter {
   private static final long serialVersionUID = 7364602996379282493L;

   public TimeZone toMemberType(String str) {
      return str == null ? null : TimeZone.getTimeZone(str.trim());
   }

   public String toDatastoreType(TimeZone tz) {
      return tz != null ? tz.getID() : null;
   }

   public int getDefaultColumnLength(int columnPosition) {
      return columnPosition != 0 ? -1 : 30;
   }
}
