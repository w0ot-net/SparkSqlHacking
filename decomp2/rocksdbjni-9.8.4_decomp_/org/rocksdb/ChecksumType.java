package org.rocksdb;

public enum ChecksumType {
   kNoChecksum((byte)0),
   kCRC32c((byte)1),
   kxxHash((byte)2),
   kxxHash64((byte)3),
   kXXH3((byte)4);

   private final byte value_;

   public byte getValue() {
      return this.value_;
   }

   private ChecksumType(byte var3) {
      this.value_ = var3;
   }
}
