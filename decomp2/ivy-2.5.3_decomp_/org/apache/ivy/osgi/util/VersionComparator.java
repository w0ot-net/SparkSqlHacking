package org.apache.ivy.osgi.util;

import java.util.Comparator;

public class VersionComparator implements Comparator {
   public static final Comparator ASCENDING = new VersionComparator(false);
   public static final Comparator DESCENDING = new VersionComparator(true);
   public final boolean reverse;

   private VersionComparator(boolean reverse) {
      this.reverse = reverse;
   }

   public int compare(Version objA, Version objB) {
      int val = objA.compareTo(objB);
      return this.reverse ? -val : val;
   }
}
