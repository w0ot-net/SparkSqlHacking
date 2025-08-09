package org.apache.hadoop.hive.metastore.api;

import org.apache.thrift.TEnum;
import org.apache.thrift.annotation.Nullable;

public enum ResourceType implements TEnum {
   JAR(1),
   FILE(2),
   ARCHIVE(3);

   private final int value;

   private ResourceType(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static ResourceType findByValue(int value) {
      switch (value) {
         case 1:
            return JAR;
         case 2:
            return FILE;
         case 3:
            return ARCHIVE;
         default:
            return null;
      }
   }
}
