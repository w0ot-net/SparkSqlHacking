package org.apache.hadoop.hive.metastore.api;

import org.apache.thrift.TEnum;
import org.apache.thrift.annotation.Nullable;

public enum FunctionType implements TEnum {
   JAVA(1);

   private final int value;

   private FunctionType(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static FunctionType findByValue(int value) {
      switch (value) {
         case 1:
            return JAVA;
         default:
            return null;
      }
   }
}
