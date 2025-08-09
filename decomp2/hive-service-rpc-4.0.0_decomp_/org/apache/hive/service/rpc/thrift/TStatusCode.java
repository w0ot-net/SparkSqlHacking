package org.apache.hive.service.rpc.thrift;

import org.apache.thrift.TEnum;
import org.apache.thrift.annotation.Nullable;

public enum TStatusCode implements TEnum {
   SUCCESS_STATUS(0),
   SUCCESS_WITH_INFO_STATUS(1),
   STILL_EXECUTING_STATUS(2),
   ERROR_STATUS(3),
   INVALID_HANDLE_STATUS(4);

   private final int value;

   private TStatusCode(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static TStatusCode findByValue(int value) {
      switch (value) {
         case 0:
            return SUCCESS_STATUS;
         case 1:
            return SUCCESS_WITH_INFO_STATUS;
         case 2:
            return STILL_EXECUTING_STATUS;
         case 3:
            return ERROR_STATUS;
         case 4:
            return INVALID_HANDLE_STATUS;
         default:
            return null;
      }
   }
}
