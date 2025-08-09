package org.apache.hadoop.hive.serde2.thrift.test;

import org.apache.thrift.TEnum;
import org.apache.thrift.annotation.Nullable;

public enum MyEnum implements TEnum {
   LLAMA(1),
   ALPACA(2);

   private final int value;

   private MyEnum(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static MyEnum findByValue(int value) {
      switch (value) {
         case 1:
            return LLAMA;
         case 2:
            return ALPACA;
         default:
            return null;
      }
   }
}
