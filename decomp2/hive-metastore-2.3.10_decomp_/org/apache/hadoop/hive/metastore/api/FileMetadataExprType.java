package org.apache.hadoop.hive.metastore.api;

import org.apache.thrift.TEnum;
import org.apache.thrift.annotation.Nullable;

public enum FileMetadataExprType implements TEnum {
   ORC_SARG(1);

   private final int value;

   private FileMetadataExprType(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static FileMetadataExprType findByValue(int value) {
      switch (value) {
         case 1:
            return ORC_SARG;
         default:
            return null;
      }
   }
}
