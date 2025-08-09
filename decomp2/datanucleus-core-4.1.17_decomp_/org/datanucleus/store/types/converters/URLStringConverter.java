package org.datanucleus.store.types.converters;

import java.net.MalformedURLException;
import java.net.URL;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.util.Localiser;

public class URLStringConverter implements TypeConverter {
   private static final long serialVersionUID = 536399905653117952L;

   public URL toMemberType(String str) {
      if (str == null) {
         return null;
      } else {
         URL url = null;

         try {
            url = new URL(str.trim());
            return url;
         } catch (MalformedURLException mue) {
            throw new NucleusDataStoreException(Localiser.msg("016002", str, URL.class.getName()), mue);
         }
      }
   }

   public String toDatastoreType(URL url) {
      return url != null ? url.toString() : null;
   }
}
