package org.datanucleus.store.types.converters;

public class CharacterStringConverter implements TypeConverter {
   private static final long serialVersionUID = 5510626063899761384L;

   public Character toMemberType(String str) {
      return str == null ? null : str.charAt(0);
   }

   public String toDatastoreType(Character chr) {
      return chr != null ? "" + chr : null;
   }
}
