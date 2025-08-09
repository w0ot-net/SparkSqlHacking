package org.apache.commons.compress.compressors.deflate64;

enum HuffmanState {
   INITIAL,
   STORED,
   DYNAMIC_CODES,
   FIXED_CODES;

   // $FF: synthetic method
   private static HuffmanState[] $values() {
      return new HuffmanState[]{INITIAL, STORED, DYNAMIC_CODES, FIXED_CODES};
   }
}
