package org.apache.datasketches.common;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public enum Family {
   ALPHA(1, "Alpha", 3, 3),
   QUICKSELECT(2, "QuickSelect", 3, 3),
   COMPACT(3, "Compact", 1, 3),
   UNION(4, "Union", 4, 4),
   INTERSECTION(5, "Intersection", 3, 3),
   A_NOT_B(6, "AnotB", 3, 3),
   HLL(7, "HLL", 1, 1),
   QUANTILES(8, "QUANTILES", 1, 2),
   TUPLE(9, "TUPLE", 1, 3),
   FREQUENCY(10, "FREQUENCY", 1, 4),
   RESERVOIR(11, "RESERVOIR", 1, 2),
   RESERVOIR_UNION(12, "RESERVOIR_UNION", 1, 1),
   VAROPT(13, "VAROPT", 1, 4),
   VAROPT_UNION(14, "VAROPT_UNION", 1, 4),
   KLL(15, "KLL", 1, 2),
   CPC(16, "CPC", 1, 5),
   REQ(17, "REQ", 1, 2),
   COUNTMIN(18, "COUNTMIN", 2, 2),
   EBPPS(19, "EBPPS", 1, 5),
   TDIGEST(20, "TDigest", 1, 2),
   BLOOMFILTER(21, "BLOOMFILTER", 3, 4);

   private static final Map lookupID = new HashMap();
   private static final Map lookupFamName = new HashMap();
   private int id_;
   private String famName_;
   private int minPreLongs_;
   private int maxPreLongs_;

   private Family(int id, String famName, int minPreLongs, int maxPreLongs) {
      this.id_ = id;
      this.famName_ = famName.toUpperCase(Locale.US);
      this.minPreLongs_ = minPreLongs;
      this.maxPreLongs_ = maxPreLongs;
   }

   @SuppressFBWarnings(
      value = {"NM_CONFUSING"},
      justification = "Harmless, will not fix"
   )
   public int getID() {
      return this.id_;
   }

   public void checkFamilyID(int id) {
      if (id != this.id_) {
         throw new SketchesArgumentException("Possible Corruption: This Family " + this.toString() + " does not match the ID of the given Family: " + idToFamily(id).toString());
      }
   }

   public String getFamilyName() {
      return this.famName_;
   }

   public int getMinPreLongs() {
      return this.minPreLongs_;
   }

   public int getMaxPreLongs() {
      return this.maxPreLongs_;
   }

   public String toString() {
      return this.famName_;
   }

   public static Family idToFamily(int id) {
      Family f = (Family)lookupID.get(id);
      if (f == null) {
         throw new SketchesArgumentException("Possible Corruption: Illegal Family ID: " + id);
      } else {
         return f;
      }
   }

   public static Family stringToFamily(String famName) {
      Family f = (Family)lookupFamName.get(famName.toUpperCase(Locale.US));
      if (f == null) {
         throw new SketchesArgumentException("Possible Corruption: Illegal Family Name: " + famName);
      } else {
         return f;
      }
   }

   static {
      for(Family f : values()) {
         lookupID.put(f.getID(), f);
         lookupFamName.put(f.getFamilyName().toUpperCase(Locale.US), f);
      }

   }
}
