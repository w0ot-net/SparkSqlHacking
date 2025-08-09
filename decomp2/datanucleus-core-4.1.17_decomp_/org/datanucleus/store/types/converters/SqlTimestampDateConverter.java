package org.datanucleus.store.types.converters;

import java.sql.Timestamp;
import java.util.Date;

public class SqlTimestampDateConverter implements TypeConverter {
   private static final long serialVersionUID = -6304439767120260182L;

   public Timestamp toMemberType(Date value) {
      return value == null ? null : new Timestamp(value.getTime());
   }

   public Date toDatastoreType(Timestamp time) {
      return time;
   }
}
