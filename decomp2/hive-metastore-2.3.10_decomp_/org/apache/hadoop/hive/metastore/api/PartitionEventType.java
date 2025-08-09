package org.apache.hadoop.hive.metastore.api;

import org.apache.thrift.TEnum;
import org.apache.thrift.annotation.Nullable;

public enum PartitionEventType implements TEnum {
   LOAD_DONE(1);

   private final int value;

   private PartitionEventType(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static PartitionEventType findByValue(int value) {
      switch (value) {
         case 1:
            return LOAD_DONE;
         default:
            return null;
      }
   }
}
