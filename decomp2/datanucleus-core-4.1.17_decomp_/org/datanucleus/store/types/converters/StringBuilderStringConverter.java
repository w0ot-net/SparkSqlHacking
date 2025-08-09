package org.datanucleus.store.types.converters;

public class StringBuilderStringConverter implements TypeConverter {
   private static final long serialVersionUID = -6443349700077274745L;

   public StringBuilder toMemberType(String str) {
      return str == null ? null : new StringBuilder(str);
   }

   public String toDatastoreType(StringBuilder str) {
      return str != null ? str.toString() : null;
   }
}
