package org.apache.hadoop.hive.metastore.api;

import org.apache.thrift.TEnum;
import org.apache.thrift.annotation.Nullable;

public enum GrantRevokeType implements TEnum {
   GRANT(1),
   REVOKE(2);

   private final int value;

   private GrantRevokeType(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static GrantRevokeType findByValue(int value) {
      switch (value) {
         case 1:
            return GRANT;
         case 2:
            return REVOKE;
         default:
            return null;
      }
   }
}
