package org.datanucleus.store.types.converters;

import java.sql.Timestamp;

public class SqlTimestampStringConverter implements TypeConverter {
   private static final long serialVersionUID = -6875184505578535496L;

   public Timestamp toMemberType(String str) {
      return str == null ? null : Timestamp.valueOf(str);
   }

   public String toDatastoreType(Timestamp ts) {
      return ts != null ? ts.toString() : null;
   }
}
