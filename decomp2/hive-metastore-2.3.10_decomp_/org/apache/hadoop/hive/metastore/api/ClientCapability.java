package org.apache.hadoop.hive.metastore.api;

import org.apache.thrift.TEnum;
import org.apache.thrift.annotation.Nullable;

public enum ClientCapability implements TEnum {
   TEST_CAPABILITY(1);

   private final int value;

   private ClientCapability(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static ClientCapability findByValue(int value) {
      switch (value) {
         case 1:
            return TEST_CAPABILITY;
         default:
            return null;
      }
   }
}
