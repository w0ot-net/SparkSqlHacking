package org.datanucleus.store.types.converters;

public class LongStringConverter implements TypeConverter {
   private static final long serialVersionUID = -4708086231754476616L;

   public Long toMemberType(String str) {
      if (str == null) {
         return null;
      } else {
         try {
            return Long.getLong(str);
         } catch (NumberFormatException var3) {
            return null;
         }
      }
   }

   public String toDatastoreType(Long val) {
      return val == null ? null : "" + val;
   }
}
