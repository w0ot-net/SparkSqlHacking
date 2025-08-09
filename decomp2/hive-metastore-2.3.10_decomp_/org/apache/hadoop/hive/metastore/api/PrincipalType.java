package org.apache.hadoop.hive.metastore.api;

import org.apache.thrift.TEnum;
import org.apache.thrift.annotation.Nullable;

public enum PrincipalType implements TEnum {
   USER(1),
   ROLE(2),
   GROUP(3);

   private final int value;

   private PrincipalType(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static PrincipalType findByValue(int value) {
      switch (value) {
         case 1:
            return USER;
         case 2:
            return ROLE;
         case 3:
            return GROUP;
         default:
            return null;
      }
   }
}
