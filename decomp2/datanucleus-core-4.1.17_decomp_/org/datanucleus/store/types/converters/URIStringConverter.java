package org.datanucleus.store.types.converters;

import java.net.URI;

public class URIStringConverter implements TypeConverter {
   private static final long serialVersionUID = -3784990025093845546L;

   public URI toMemberType(String str) {
      return str == null ? null : URI.create(str.trim());
   }

   public String toDatastoreType(URI uri) {
      return uri != null ? uri.toString() : null;
   }
}
