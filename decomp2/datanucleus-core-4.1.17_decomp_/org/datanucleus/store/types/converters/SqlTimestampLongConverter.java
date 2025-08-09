package org.datanucleus.store.types.converters;

import java.sql.Timestamp;

public class SqlTimestampLongConverter implements TypeConverter {
   private static final long serialVersionUID = 1415324665726138972L;

   public Timestamp toMemberType(Long value) {
      return value == null ? null : new Timestamp(value);
   }

   public Long toDatastoreType(Timestamp ts) {
      return ts != null ? ts.getTime() : null;
   }
}
