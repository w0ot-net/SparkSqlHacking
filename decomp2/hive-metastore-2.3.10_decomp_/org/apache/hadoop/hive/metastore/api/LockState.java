package org.apache.hadoop.hive.metastore.api;

import org.apache.thrift.TEnum;
import org.apache.thrift.annotation.Nullable;

public enum LockState implements TEnum {
   ACQUIRED(1),
   WAITING(2),
   ABORT(3),
   NOT_ACQUIRED(4);

   private final int value;

   private LockState(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static LockState findByValue(int value) {
      switch (value) {
         case 1:
            return ACQUIRED;
         case 2:
            return WAITING;
         case 3:
            return ABORT;
         case 4:
            return NOT_ACQUIRED;
         default:
            return null;
      }
   }
}
