package org.apache.hadoop.hive.metastore.api;

import org.apache.thrift.TEnum;
import org.apache.thrift.annotation.Nullable;

public enum LockType implements TEnum {
   SHARED_READ(1),
   SHARED_WRITE(2),
   EXCLUSIVE(3);

   private final int value;

   private LockType(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static LockType findByValue(int value) {
      switch (value) {
         case 1:
            return SHARED_READ;
         case 2:
            return SHARED_WRITE;
         case 3:
            return EXCLUSIVE;
         default:
            return null;
      }
   }
}
