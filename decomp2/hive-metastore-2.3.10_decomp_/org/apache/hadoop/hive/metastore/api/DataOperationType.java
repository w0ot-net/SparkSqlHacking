package org.apache.hadoop.hive.metastore.api;

import org.apache.thrift.TEnum;
import org.apache.thrift.annotation.Nullable;

public enum DataOperationType implements TEnum {
   SELECT(1),
   INSERT(2),
   UPDATE(3),
   DELETE(4),
   UNSET(5),
   NO_TXN(6);

   private final int value;

   private DataOperationType(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static DataOperationType findByValue(int value) {
      switch (value) {
         case 1:
            return SELECT;
         case 2:
            return INSERT;
         case 3:
            return UPDATE;
         case 4:
            return DELETE;
         case 5:
            return UNSET;
         case 6:
            return NO_TXN;
         default:
            return null;
      }
   }
}
