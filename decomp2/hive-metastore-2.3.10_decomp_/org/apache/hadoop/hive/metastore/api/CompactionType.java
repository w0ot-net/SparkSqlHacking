package org.apache.hadoop.hive.metastore.api;

import org.apache.thrift.TEnum;
import org.apache.thrift.annotation.Nullable;

public enum CompactionType implements TEnum {
   MINOR(1),
   MAJOR(2);

   private final int value;

   private CompactionType(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static CompactionType findByValue(int value) {
      switch (value) {
         case 1:
            return MINOR;
         case 2:
            return MAJOR;
         default:
            return null;
      }
   }
}
