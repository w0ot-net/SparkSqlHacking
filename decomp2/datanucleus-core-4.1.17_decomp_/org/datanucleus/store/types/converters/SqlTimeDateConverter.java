package org.datanucleus.store.types.converters;

import java.sql.Time;
import java.util.Date;

public class SqlTimeDateConverter implements TypeConverter {
   private static final long serialVersionUID = 3817497258319725482L;

   public Time toMemberType(Date value) {
      return value == null ? null : new Time(value.getTime());
   }

   public Date toDatastoreType(Time time) {
      return time;
   }
}
