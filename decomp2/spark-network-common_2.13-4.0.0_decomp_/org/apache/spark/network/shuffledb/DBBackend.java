package org.apache.spark.network.shuffledb;

import java.util.Locale;

public enum DBBackend {
   LEVELDB(".ldb"),
   ROCKSDB(".rdb");

   private final String fileSuffix;

   private DBBackend(String fileSuffix) {
      this.fileSuffix = fileSuffix;
   }

   public String fileName(String prefix) {
      return prefix + this.fileSuffix;
   }

   public static DBBackend byName(String value) {
      return valueOf(value.toUpperCase(Locale.ROOT));
   }

   // $FF: synthetic method
   private static DBBackend[] $values() {
      return new DBBackend[]{LEVELDB, ROCKSDB};
   }
}
