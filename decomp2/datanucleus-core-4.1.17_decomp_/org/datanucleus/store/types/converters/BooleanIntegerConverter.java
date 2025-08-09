package org.datanucleus.store.types.converters;

import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.util.Localiser;

public class BooleanIntegerConverter implements TypeConverter {
   private static final long serialVersionUID = -6180650436706210421L;

   public Boolean toMemberType(Integer val) {
      if (val == null) {
         return null;
      } else {
         try {
            return val == 1;
         } catch (NumberFormatException nfe) {
            throw new NucleusDataStoreException(Localiser.msg("016002", val, Boolean.class.getName()), nfe);
         }
      }
   }

   public Integer toDatastoreType(Boolean bool) {
      return bool != null ? bool ? 1 : 0 : null;
   }
}
