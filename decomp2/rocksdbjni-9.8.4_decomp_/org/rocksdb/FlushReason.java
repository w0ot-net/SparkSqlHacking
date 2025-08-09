package org.rocksdb;

public enum FlushReason {
   OTHERS((byte)0),
   GET_LIVE_FILES((byte)1),
   SHUTDOWN((byte)2),
   EXTERNAL_FILE_INGESTION((byte)3),
   MANUAL_COMPACTION((byte)4),
   WRITE_BUFFER_MANAGER((byte)5),
   WRITE_BUFFER_FULL((byte)6),
   TEST((byte)7),
   DELETE_FILES((byte)8),
   AUTO_COMPACTION((byte)9),
   MANUAL_FLUSH((byte)10),
   ERROR_RECOVERY((byte)11);

   private final byte value;

   private FlushReason(byte var3) {
      this.value = var3;
   }

   byte getValue() {
      return this.value;
   }

   static FlushReason fromValue(byte var0) {
      for(FlushReason var4 : values()) {
         if (var4.value == var0) {
            return var4;
         }
      }

      throw new IllegalArgumentException("Illegal value provided for FlushReason: " + var0);
   }
}
