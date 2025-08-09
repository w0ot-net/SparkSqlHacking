package org.datanucleus.store.types.converters;

public class IntegerStringConverter implements TypeConverter {
   private static final long serialVersionUID = 3043444938844407097L;

   public Integer toMemberType(String str) {
      if (str == null) {
         return null;
      } else {
         try {
            return Integer.getInteger(str);
         } catch (NumberFormatException var3) {
            return null;
         }
      }
   }

   public String toDatastoreType(Integer val) {
      return val == null ? null : "" + val;
   }
}
