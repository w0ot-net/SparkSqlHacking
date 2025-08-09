package org.datanucleus.store.types.converters;

import java.sql.Date;

public class SqlDateLongConverter implements TypeConverter {
   private static final long serialVersionUID = 7276158028007180L;

   public Date toMemberType(Long value) {
      return value == null ? null : new Date(value);
   }

   public Long toDatastoreType(Date date) {
      return date != null ? date.getTime() : null;
   }
}
