package org.datanucleus.store.types.converters;

import java.sql.Date;

public class SqlDateDateConverter implements TypeConverter {
   private static final long serialVersionUID = 3850097783909422945L;

   public Date toMemberType(java.util.Date value) {
      return value == null ? null : new Date(value.getTime());
   }

   public java.util.Date toDatastoreType(Date date) {
      return date;
   }
}
