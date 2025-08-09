package org.apache.hive.service.rpc.thrift;

import org.apache.thrift.TEnum;
import org.apache.thrift.annotation.Nullable;

public enum TJobExecutionStatus implements TEnum {
   IN_PROGRESS(0),
   COMPLETE(1),
   NOT_AVAILABLE(2);

   private final int value;

   private TJobExecutionStatus(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static TJobExecutionStatus findByValue(int value) {
      switch (value) {
         case 0:
            return IN_PROGRESS;
         case 1:
            return COMPLETE;
         case 2:
            return NOT_AVAILABLE;
         default:
            return null;
      }
   }
}
