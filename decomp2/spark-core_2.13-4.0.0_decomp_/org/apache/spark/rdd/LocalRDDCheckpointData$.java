package org.apache.spark.rdd;

import java.io.Serializable;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.storage.StorageLevel.;
import scala.runtime.ModuleSerializationProxy;

public final class LocalRDDCheckpointData$ implements Serializable {
   public static final LocalRDDCheckpointData$ MODULE$ = new LocalRDDCheckpointData$();
   private static final StorageLevel DEFAULT_STORAGE_LEVEL;

   static {
      DEFAULT_STORAGE_LEVEL = .MODULE$.MEMORY_AND_DISK();
   }

   public StorageLevel DEFAULT_STORAGE_LEVEL() {
      return DEFAULT_STORAGE_LEVEL;
   }

   public StorageLevel transformStorageLevel(final StorageLevel level) {
      return .MODULE$.apply(true, level.useMemory(), level.deserialized(), level.replication());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LocalRDDCheckpointData$.class);
   }

   private LocalRDDCheckpointData$() {
   }
}
