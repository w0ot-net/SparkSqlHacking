package org.datanucleus.store.types.converters;

import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.util.Localiser;

public class BooleanYNConverter implements TypeConverter {
   private static final long serialVersionUID = 778758633106246559L;

   public Boolean toMemberType(Character chr) {
      if (chr == null) {
         return null;
      } else {
         try {
            return chr.equals('Y');
         } catch (NumberFormatException nfe) {
            throw new NucleusDataStoreException(Localiser.msg("016002", chr, Boolean.class.getName()), nfe);
         }
      }
   }

   public Character toDatastoreType(Boolean bool) {
      return bool != null ? Character.valueOf((char)(bool ? 'Y' : 'N')) : null;
   }
}
