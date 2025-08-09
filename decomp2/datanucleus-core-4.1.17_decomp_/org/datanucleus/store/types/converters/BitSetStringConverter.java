package org.datanucleus.store.types.converters;

import java.util.BitSet;
import java.util.StringTokenizer;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.util.Localiser;

public class BitSetStringConverter implements TypeConverter {
   private static final long serialVersionUID = -8700033712868623346L;

   public BitSet toMemberType(String str) {
      if (str == null) {
         return null;
      } else {
         BitSet set = new BitSet();
         StringTokenizer tokeniser = new StringTokenizer(str.substring(1, str.length() - 1), ",");

         while(tokeniser.hasMoreTokens()) {
            String token = tokeniser.nextToken().trim();

            try {
               int position = Integer.valueOf(token);
               set.set(position);
            } catch (NumberFormatException nfe) {
               throw new NucleusDataStoreException(Localiser.msg("016002", str, BitSet.class.getName()), nfe);
            }
         }

         return set;
      }
   }

   public String toDatastoreType(BitSet set) {
      return set != null ? set.toString() : null;
   }
}
