package org.apache.ivy.plugins.latest;

import java.util.Comparator;

public class LatestLexicographicStrategy extends ComparatorLatestStrategy {
   private static final Comparator COMPARATOR = new Comparator() {
      public int compare(ArtifactInfo o1, ArtifactInfo o2) {
         String rev1 = o1.getRevision();
         String rev2 = o2.getRevision();
         if (rev1.startsWith("latest")) {
            return 1;
         } else if (rev1.endsWith("+") && rev2.startsWith(rev1.substring(0, rev1.length() - 1))) {
            return 1;
         } else if (rev2.startsWith("latest")) {
            return -1;
         } else {
            return rev2.endsWith("+") && rev1.startsWith(rev2.substring(0, rev2.length() - 1)) ? -1 : rev1.compareTo(rev2);
         }
      }
   };

   public LatestLexicographicStrategy() {
      super(COMPARATOR);
      this.setName("latest-lexico");
   }
}
