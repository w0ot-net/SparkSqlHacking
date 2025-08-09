package org.sparkproject.jetty.util.resource;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class ResourceCollators {
   private static Comparator BY_NAME_ASCENDING = new Comparator() {
      private final Collator collator;

      {
         this.collator = Collator.getInstance(Locale.ENGLISH);
      }

      public int compare(Resource o1, Resource o2) {
         return this.collator.compare(o1.getName(), o2.getName());
      }
   };
   private static Comparator BY_NAME_DESCENDING;
   private static Comparator BY_LAST_MODIFIED_ASCENDING;
   private static Comparator BY_LAST_MODIFIED_DESCENDING;
   private static Comparator BY_SIZE_ASCENDING;
   private static Comparator BY_SIZE_DESCENDING;

   public static Comparator byLastModified(boolean sortOrderAscending) {
      return sortOrderAscending ? BY_LAST_MODIFIED_ASCENDING : BY_LAST_MODIFIED_DESCENDING;
   }

   public static Comparator byName(boolean sortOrderAscending) {
      return sortOrderAscending ? BY_NAME_ASCENDING : BY_NAME_DESCENDING;
   }

   public static Comparator bySize(boolean sortOrderAscending) {
      return sortOrderAscending ? BY_SIZE_ASCENDING : BY_SIZE_DESCENDING;
   }

   static {
      BY_NAME_DESCENDING = Collections.reverseOrder(BY_NAME_ASCENDING);
      BY_LAST_MODIFIED_ASCENDING = Comparator.comparingLong(Resource::lastModified);
      BY_LAST_MODIFIED_DESCENDING = Collections.reverseOrder(BY_LAST_MODIFIED_ASCENDING);
      BY_SIZE_ASCENDING = Comparator.comparingLong(Resource::length);
      BY_SIZE_DESCENDING = Collections.reverseOrder(BY_SIZE_ASCENDING);
   }
}
