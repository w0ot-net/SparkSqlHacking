package org.iq80.leveldb;

public enum CompressionType {
   NONE(0),
   SNAPPY(1);

   private final int persistentId;

   public static CompressionType getCompressionTypeByPersistentId(int persistentId) {
      for(CompressionType compressionType : values()) {
         if (compressionType.persistentId == persistentId) {
            return compressionType;
         }
      }

      throw new IllegalArgumentException("Unknown persistentId " + persistentId);
   }

   private CompressionType(int persistentId) {
      this.persistentId = persistentId;
   }

   public int persistentId() {
      return this.persistentId;
   }
}
