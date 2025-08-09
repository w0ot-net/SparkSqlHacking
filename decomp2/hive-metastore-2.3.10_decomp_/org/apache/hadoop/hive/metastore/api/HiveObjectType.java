package org.apache.hadoop.hive.metastore.api;

import org.apache.thrift.TEnum;
import org.apache.thrift.annotation.Nullable;

public enum HiveObjectType implements TEnum {
   GLOBAL(1),
   DATABASE(2),
   TABLE(3),
   PARTITION(4),
   COLUMN(5);

   private final int value;

   private HiveObjectType(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static HiveObjectType findByValue(int value) {
      switch (value) {
         case 1:
            return GLOBAL;
         case 2:
            return DATABASE;
         case 3:
            return TABLE;
         case 4:
            return PARTITION;
         case 5:
            return COLUMN;
         default:
            return null;
      }
   }
}
