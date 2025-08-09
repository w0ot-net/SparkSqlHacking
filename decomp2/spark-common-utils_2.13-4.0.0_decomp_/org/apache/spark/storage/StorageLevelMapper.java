package org.apache.spark.storage;

public enum StorageLevelMapper {
   NONE(StorageLevel.NONE()),
   DISK_ONLY(StorageLevel.DISK_ONLY()),
   DISK_ONLY_2(StorageLevel.DISK_ONLY_2()),
   DISK_ONLY_3(StorageLevel.DISK_ONLY_3()),
   MEMORY_ONLY(StorageLevel.MEMORY_ONLY()),
   MEMORY_ONLY_2(StorageLevel.MEMORY_ONLY_2()),
   MEMORY_ONLY_SER(StorageLevel.MEMORY_ONLY_SER()),
   MEMORY_ONLY_SER_2(StorageLevel.MEMORY_ONLY_SER_2()),
   MEMORY_AND_DISK(StorageLevel.MEMORY_AND_DISK()),
   MEMORY_AND_DISK_2(StorageLevel.MEMORY_AND_DISK_2()),
   MEMORY_AND_DISK_SER(StorageLevel.MEMORY_AND_DISK_SER()),
   MEMORY_AND_DISK_SER_2(StorageLevel.MEMORY_AND_DISK_SER_2()),
   OFF_HEAP(StorageLevel.OFF_HEAP());

   private final StorageLevel storageLevel;

   private StorageLevelMapper(StorageLevel storageLevel) {
      this.storageLevel = storageLevel;
   }

   public static StorageLevel fromString(String s) throws IllegalArgumentException {
      return valueOf(s).storageLevel;
   }

   // $FF: synthetic method
   private static StorageLevelMapper[] $values() {
      return new StorageLevelMapper[]{NONE, DISK_ONLY, DISK_ONLY_2, DISK_ONLY_3, MEMORY_ONLY, MEMORY_ONLY_2, MEMORY_ONLY_SER, MEMORY_ONLY_SER_2, MEMORY_AND_DISK, MEMORY_AND_DISK_2, MEMORY_AND_DISK_SER, MEMORY_AND_DISK_SER_2, OFF_HEAP};
   }
}
