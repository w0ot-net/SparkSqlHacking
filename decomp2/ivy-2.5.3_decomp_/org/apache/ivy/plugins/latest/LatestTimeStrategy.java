package org.apache.ivy.plugins.latest;

import java.util.Comparator;

public class LatestTimeStrategy extends ComparatorLatestStrategy {
   private static final Comparator COMPARATOR = new Comparator() {
      public int compare(ArtifactInfo o1, ArtifactInfo o2) {
         return Long.compare(o1.getLastModified(), o2.getLastModified());
      }
   };

   public LatestTimeStrategy() {
      super(COMPARATOR);
      this.setName("latest-time");
   }
}
