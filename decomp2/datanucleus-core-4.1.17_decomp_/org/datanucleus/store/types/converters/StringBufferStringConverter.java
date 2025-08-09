package org.datanucleus.store.types.converters;

public class StringBufferStringConverter implements TypeConverter {
   private static final long serialVersionUID = -4453706060312496022L;

   public StringBuffer toMemberType(String str) {
      return str == null ? null : new StringBuffer(str);
   }

   public String toDatastoreType(StringBuffer str) {
      return str != null ? str.toString() : null;
   }
}
