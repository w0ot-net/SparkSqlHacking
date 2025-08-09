package org.apache.datasketches.cpc;

enum Format {
   EMPTY_MERGED,
   EMPTY_HIP,
   SPARSE_HYBRID_MERGED,
   SPARSE_HYBRID_HIP,
   PINNED_SLIDING_MERGED_NOSV,
   PINNED_SLIDING_HIP_NOSV,
   PINNED_SLIDING_MERGED,
   PINNED_SLIDING_HIP;

   private static Format[] fmtArr = (Format[])Format.class.getEnumConstants();

   static Format ordinalToFormat(int ordinal) {
      return fmtArr[ordinal];
   }
}
