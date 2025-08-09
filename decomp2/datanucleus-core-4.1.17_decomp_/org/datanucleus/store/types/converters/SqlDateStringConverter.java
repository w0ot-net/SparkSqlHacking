package org.datanucleus.store.types.converters;

import java.sql.Date;

public class SqlDateStringConverter implements TypeConverter {
   private static final long serialVersionUID = 2261633191458773325L;

   public Date toMemberType(String str) {
      return str == null ? null : Date.valueOf(str);
   }

   public String toDatastoreType(Date date) {
      return date != null ? date.toString() : null;
   }
}
