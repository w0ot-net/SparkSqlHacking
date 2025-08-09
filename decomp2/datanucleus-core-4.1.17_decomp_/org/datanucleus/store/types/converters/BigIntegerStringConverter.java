package org.datanucleus.store.types.converters;

import java.math.BigInteger;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.util.Localiser;

public class BigIntegerStringConverter implements TypeConverter {
   private static final long serialVersionUID = 2695605770119124000L;

   public BigInteger toMemberType(String str) {
      if (str == null) {
         return null;
      } else {
         try {
            return new BigInteger(str.trim());
         } catch (NumberFormatException nfe) {
            throw new NucleusDataStoreException(Localiser.msg("016002", str, BigInteger.class.getName()), nfe);
         }
      }
   }

   public String toDatastoreType(BigInteger bi) {
      return bi != null ? bi.toString() : null;
   }
}
