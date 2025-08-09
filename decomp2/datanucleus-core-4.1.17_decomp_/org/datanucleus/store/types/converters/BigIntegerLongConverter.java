package org.datanucleus.store.types.converters;

import java.math.BigInteger;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.util.Localiser;

public class BigIntegerLongConverter implements TypeConverter {
   private static final long serialVersionUID = -946874444222779197L;

   public BigInteger toMemberType(Long val) {
      if (val == null) {
         return null;
      } else {
         try {
            return BigInteger.valueOf(val);
         } catch (NumberFormatException nfe) {
            throw new NucleusDataStoreException(Localiser.msg("016002", val, BigInteger.class.getName()), nfe);
         }
      }
   }

   public Long toDatastoreType(BigInteger bi) {
      return bi.longValue();
   }
}
