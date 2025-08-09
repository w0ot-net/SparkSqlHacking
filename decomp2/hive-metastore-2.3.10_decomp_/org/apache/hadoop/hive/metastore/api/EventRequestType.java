package org.apache.hadoop.hive.metastore.api;

import org.apache.thrift.TEnum;
import org.apache.thrift.annotation.Nullable;

public enum EventRequestType implements TEnum {
   INSERT(1),
   UPDATE(2),
   DELETE(3);

   private final int value;

   private EventRequestType(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static EventRequestType findByValue(int value) {
      switch (value) {
         case 1:
            return INSERT;
         case 2:
            return UPDATE;
         case 3:
            return DELETE;
         default:
            return null;
      }
   }
}
