package org.apache.spark.ui.storage;

public final class ToolTips$ {
   public static final ToolTips$ MODULE$ = new ToolTips$();
   private static final String RDD_NAME = "Name of the persisted RDD";
   private static final String STORAGE_LEVEL = "StorageLevel displays where the persisted RDD is stored, format of the persisted RDD (serialized or de-serialized) and replication factor of the persisted RDD";
   private static final String CACHED_PARTITIONS = "Number of partitions cached";
   private static final String FRACTION_CACHED = "Fraction of total partitions cached";
   private static final String SIZE_IN_MEMORY = "Total size of partitions in memory";
   private static final String SIZE_ON_DISK = "Total size of partitions on the disk";

   public String RDD_NAME() {
      return RDD_NAME;
   }

   public String STORAGE_LEVEL() {
      return STORAGE_LEVEL;
   }

   public String CACHED_PARTITIONS() {
      return CACHED_PARTITIONS;
   }

   public String FRACTION_CACHED() {
      return FRACTION_CACHED;
   }

   public String SIZE_IN_MEMORY() {
      return SIZE_IN_MEMORY;
   }

   public String SIZE_ON_DISK() {
      return SIZE_ON_DISK;
   }

   private ToolTips$() {
   }
}
