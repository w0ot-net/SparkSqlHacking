package org.apache.spark.storage;

import java.io.ObjectInput;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.spark.annotation.DeveloperApi;
import scala.runtime.ModuleSerializationProxy;

public final class StorageLevel$ implements Serializable {
   public static final StorageLevel$ MODULE$ = new StorageLevel$();
   private static final StorageLevel NONE;
   private static final StorageLevel DISK_ONLY;
   private static final StorageLevel DISK_ONLY_2;
   private static final StorageLevel DISK_ONLY_3;
   private static final StorageLevel MEMORY_ONLY;
   private static final StorageLevel MEMORY_ONLY_2;
   private static final StorageLevel MEMORY_ONLY_SER;
   private static final StorageLevel MEMORY_ONLY_SER_2;
   private static final StorageLevel MEMORY_AND_DISK;
   private static final StorageLevel MEMORY_AND_DISK_2;
   private static final StorageLevel MEMORY_AND_DISK_SER;
   private static final StorageLevel MEMORY_AND_DISK_SER_2;
   private static final StorageLevel OFF_HEAP;
   private static final ConcurrentHashMap storageLevelCache;

   static {
      NONE = new StorageLevel(false, false, false, false, MODULE$.org$apache$spark$storage$StorageLevel$$$lessinit$greater$default$5());
      DISK_ONLY = new StorageLevel(true, false, false, false, MODULE$.org$apache$spark$storage$StorageLevel$$$lessinit$greater$default$5());
      DISK_ONLY_2 = new StorageLevel(true, false, false, false, 2);
      DISK_ONLY_3 = new StorageLevel(true, false, false, false, 3);
      MEMORY_ONLY = new StorageLevel(false, true, false, true, MODULE$.org$apache$spark$storage$StorageLevel$$$lessinit$greater$default$5());
      MEMORY_ONLY_2 = new StorageLevel(false, true, false, true, 2);
      MEMORY_ONLY_SER = new StorageLevel(false, true, false, false, MODULE$.org$apache$spark$storage$StorageLevel$$$lessinit$greater$default$5());
      MEMORY_ONLY_SER_2 = new StorageLevel(false, true, false, false, 2);
      MEMORY_AND_DISK = new StorageLevel(true, true, false, true, MODULE$.org$apache$spark$storage$StorageLevel$$$lessinit$greater$default$5());
      MEMORY_AND_DISK_2 = new StorageLevel(true, true, false, true, 2);
      MEMORY_AND_DISK_SER = new StorageLevel(true, true, false, false, MODULE$.org$apache$spark$storage$StorageLevel$$$lessinit$greater$default$5());
      MEMORY_AND_DISK_SER_2 = new StorageLevel(true, true, false, false, 2);
      OFF_HEAP = new StorageLevel(true, true, true, false, 1);
      storageLevelCache = new ConcurrentHashMap();
   }

   public int org$apache$spark$storage$StorageLevel$$$lessinit$greater$default$5() {
      return 1;
   }

   public StorageLevel NONE() {
      return NONE;
   }

   public StorageLevel DISK_ONLY() {
      return DISK_ONLY;
   }

   public StorageLevel DISK_ONLY_2() {
      return DISK_ONLY_2;
   }

   public StorageLevel DISK_ONLY_3() {
      return DISK_ONLY_3;
   }

   public StorageLevel MEMORY_ONLY() {
      return MEMORY_ONLY;
   }

   public StorageLevel MEMORY_ONLY_2() {
      return MEMORY_ONLY_2;
   }

   public StorageLevel MEMORY_ONLY_SER() {
      return MEMORY_ONLY_SER;
   }

   public StorageLevel MEMORY_ONLY_SER_2() {
      return MEMORY_ONLY_SER_2;
   }

   public StorageLevel MEMORY_AND_DISK() {
      return MEMORY_AND_DISK;
   }

   public StorageLevel MEMORY_AND_DISK_2() {
      return MEMORY_AND_DISK_2;
   }

   public StorageLevel MEMORY_AND_DISK_SER() {
      return MEMORY_AND_DISK_SER;
   }

   public StorageLevel MEMORY_AND_DISK_SER_2() {
      return MEMORY_AND_DISK_SER_2;
   }

   public StorageLevel OFF_HEAP() {
      return OFF_HEAP;
   }

   @DeveloperApi
   public StorageLevel fromString(final String s) {
      try {
         return StorageLevelMapper.fromString(s);
      } catch (IllegalArgumentException var2) {
         throw new IllegalArgumentException("Invalid StorageLevel: " + s);
      }
   }

   @DeveloperApi
   public StorageLevel apply(final boolean useDisk, final boolean useMemory, final boolean useOffHeap, final boolean deserialized, final int replication) {
      return this.getCachedStorageLevel(new StorageLevel(useDisk, useMemory, useOffHeap, deserialized, replication));
   }

   @DeveloperApi
   public StorageLevel apply(final boolean useDisk, final boolean useMemory, final boolean deserialized, final int replication) {
      return this.getCachedStorageLevel(new StorageLevel(useDisk, useMemory, false, deserialized, replication));
   }

   @DeveloperApi
   public StorageLevel apply(final int flags, final int replication) {
      return this.getCachedStorageLevel(new StorageLevel(flags, replication));
   }

   @DeveloperApi
   public StorageLevel apply(final ObjectInput in) {
      StorageLevel obj = new StorageLevel();
      obj.readExternal(in);
      return this.getCachedStorageLevel(obj);
   }

   public int apply$default$4() {
      return 1;
   }

   public ConcurrentHashMap storageLevelCache() {
      return storageLevelCache;
   }

   public StorageLevel getCachedStorageLevel(final StorageLevel level) {
      this.storageLevelCache().putIfAbsent(level, level);
      return (StorageLevel)this.storageLevelCache().get(level);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StorageLevel$.class);
   }

   private StorageLevel$() {
   }
}
