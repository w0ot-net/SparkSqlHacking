package org.apache.commons.compress.archivers.sevenz;

final class BindPair {
   final long inIndex;
   final long outIndex;

   BindPair(long inIndex, long outIndex) {
      this.inIndex = inIndex;
      this.outIndex = outIndex;
   }

   public String toString() {
      return "BindPair binding input " + this.inIndex + " to output " + this.outIndex;
   }
}
