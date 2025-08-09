package org.apache.hadoop.hive.metastore.api;

import org.apache.thrift.TEnum;
import org.apache.thrift.annotation.Nullable;

public enum LockLevel implements TEnum {
   DB(1),
   TABLE(2),
   PARTITION(3);

   private final int value;

   private LockLevel(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static LockLevel findByValue(int value) {
      switch (value) {
         case 1:
            return DB;
         case 2:
            return TABLE;
         case 3:
            return PARTITION;
         default:
            return null;
      }
   }
}
