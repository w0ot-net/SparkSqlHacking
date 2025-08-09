package org.datanucleus.store.types.converters;

import java.math.BigDecimal;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.util.Localiser;

public class BigDecimalDoubleConverter implements TypeConverter {
   private static final long serialVersionUID = 9192173072810027540L;

   public BigDecimal toMemberType(Double val) {
      if (val == null) {
         return null;
      } else {
         try {
            return BigDecimal.valueOf(val);
         } catch (NumberFormatException nfe) {
            throw new NucleusDataStoreException(Localiser.msg("016002", val, BigDecimal.class.getName()), nfe);
         }
      }
   }

   public Double toDatastoreType(BigDecimal bd) {
      return bd.doubleValue();
   }
}
