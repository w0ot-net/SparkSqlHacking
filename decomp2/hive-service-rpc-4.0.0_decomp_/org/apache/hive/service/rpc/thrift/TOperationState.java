package org.apache.hive.service.rpc.thrift;

import org.apache.thrift.TEnum;
import org.apache.thrift.annotation.Nullable;

public enum TOperationState implements TEnum {
   INITIALIZED_STATE(0),
   RUNNING_STATE(1),
   FINISHED_STATE(2),
   CANCELED_STATE(3),
   CLOSED_STATE(4),
   ERROR_STATE(5),
   UKNOWN_STATE(6),
   PENDING_STATE(7),
   TIMEDOUT_STATE(8);

   private final int value;

   private TOperationState(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static TOperationState findByValue(int value) {
      switch (value) {
         case 0:
            return INITIALIZED_STATE;
         case 1:
            return RUNNING_STATE;
         case 2:
            return FINISHED_STATE;
         case 3:
            return CANCELED_STATE;
         case 4:
            return CLOSED_STATE;
         case 5:
            return ERROR_STATE;
         case 6:
            return UKNOWN_STATE;
         case 7:
            return PENDING_STATE;
         case 8:
            return TIMEDOUT_STATE;
         default:
            return null;
      }
   }
}
