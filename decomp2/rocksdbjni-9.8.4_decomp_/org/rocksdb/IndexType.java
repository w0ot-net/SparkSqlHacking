package org.rocksdb;

public enum IndexType {
   kBinarySearch((byte)0),
   kHashSearch((byte)1),
   kTwoLevelIndexSearch((byte)2),
   kBinarySearchWithFirstKey((byte)3);

   private final byte value_;

   public byte getValue() {
      return this.value_;
   }

   private IndexType(byte var3) {
      this.value_ = var3;
   }
}
