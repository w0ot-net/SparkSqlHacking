package org.datanucleus.store.types.converters;

import java.util.Date;

public class DateLongConverter implements TypeConverter {
   private static final long serialVersionUID = -3378521433435793058L;

   public Date toMemberType(Long value) {
      return value == null ? null : new Date(value);
   }

   public Long toDatastoreType(Date date) {
      return date != null ? date.getTime() : null;
   }
}
