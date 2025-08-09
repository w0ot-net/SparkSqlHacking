package org.datanucleus.store.types.converters;

import java.math.BigDecimal;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.util.Localiser;

public class BigDecimalStringConverter implements TypeConverter {
   private static final long serialVersionUID = 7218240846316253232L;

   public BigDecimal toMemberType(String str) {
      if (str == null) {
         return null;
      } else {
         try {
            return new BigDecimal(str.trim());
         } catch (NumberFormatException nfe) {
            throw new NucleusDataStoreException(Localiser.msg("016002", str, BigDecimal.class.getName()), nfe);
         }
      }
   }

   public String toDatastoreType(BigDecimal bd) {
      return bd != null ? bd.toString() : null;
   }
}
