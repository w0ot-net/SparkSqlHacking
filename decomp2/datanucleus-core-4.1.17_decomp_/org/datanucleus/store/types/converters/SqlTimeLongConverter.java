package org.datanucleus.store.types.converters;

import java.sql.Time;

public class SqlTimeLongConverter implements TypeConverter {
   private static final long serialVersionUID = -4450441515073250228L;

   public Time toMemberType(Long value) {
      return value == null ? null : new Time(value);
   }

   public Long toDatastoreType(Time time) {
      return time != null ? time.getTime() : null;
   }
}
