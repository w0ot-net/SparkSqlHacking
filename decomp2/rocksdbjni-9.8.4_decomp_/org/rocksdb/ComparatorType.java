package org.rocksdb;

enum ComparatorType {
   JAVA_COMPARATOR((byte)0),
   JAVA_NATIVE_COMPARATOR_WRAPPER((byte)1);

   private final byte value;

   private ComparatorType(byte var3) {
      this.value = var3;
   }

   byte getValue() {
      return this.value;
   }

   static ComparatorType getComparatorType(byte var0) {
      for(ComparatorType var4 : values()) {
         if (var4.getValue() == var0) {
            return var4;
         }
      }

      throw new IllegalArgumentException("Illegal value provided for ComparatorType.");
   }
}
