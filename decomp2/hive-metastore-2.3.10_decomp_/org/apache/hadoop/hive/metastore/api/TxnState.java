package org.apache.hadoop.hive.metastore.api;

import org.apache.thrift.TEnum;
import org.apache.thrift.annotation.Nullable;

public enum TxnState implements TEnum {
   COMMITTED(1),
   ABORTED(2),
   OPEN(3);

   private final int value;

   private TxnState(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static TxnState findByValue(int value) {
      switch (value) {
         case 1:
            return COMMITTED;
         case 2:
            return ABORTED;
         case 3:
            return OPEN;
         default:
            return null;
      }
   }
}
