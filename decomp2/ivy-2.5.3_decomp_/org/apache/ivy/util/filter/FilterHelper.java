package org.apache.ivy.util.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class FilterHelper {
   public static final Filter NO_FILTER = NoFilter.instance();

   private FilterHelper() {
   }

   public static Filter getArtifactTypeFilter(String types) {
      if (types != null && !types.trim().equals("*")) {
         String[] t = types.split(",");
         return getArtifactTypeFilter(t);
      } else {
         return NO_FILTER;
      }
   }

   public static Filter getArtifactTypeFilter(String[] types) {
      if (types != null && types.length != 0) {
         List<String> acceptedTypes = new ArrayList(types.length);

         for(String type : types) {
            String current = type.trim();
            if ("*".equals(current)) {
               return NO_FILTER;
            }

            acceptedTypes.add(current);
         }

         return new ArtifactTypeFilter(acceptedTypes);
      } else {
         return NO_FILTER;
      }
   }

   public static Collection filter(Collection col, Filter filter) {
      if (filter == null) {
         return col;
      } else {
         Collection<T> ret = new ArrayList(col);
         Iterator<T> iter = ret.iterator();

         while(iter.hasNext()) {
            if (!filter.accept(iter.next())) {
               iter.remove();
            }
         }

         return ret;
      }
   }
}
