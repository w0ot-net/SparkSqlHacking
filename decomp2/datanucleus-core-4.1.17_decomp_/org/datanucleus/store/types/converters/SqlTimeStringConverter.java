package org.datanucleus.store.types.converters;

import java.sql.Time;

public class SqlTimeStringConverter implements TypeConverter {
   private static final long serialVersionUID = -201061110824623751L;

   public Time toMemberType(String str) {
      return str == null ? null : Time.valueOf(str);
   }

   public String toDatastoreType(Time time) {
      return time != null ? time.toString() : null;
   }
}
