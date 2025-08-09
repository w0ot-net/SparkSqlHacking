package org.apache.parquet.format;

import shaded.parquet.org.apache.thrift.TEnum;
import shaded.parquet.org.apache.thrift.annotation.Nullable;

public enum FieldRepetitionType implements TEnum {
   REQUIRED(0),
   OPTIONAL(1),
   REPEATED(2);

   private final int value;

   private FieldRepetitionType(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static FieldRepetitionType findByValue(int value) {
      switch (value) {
         case 0:
            return REQUIRED;
         case 1:
            return OPTIONAL;
         case 2:
            return REPEATED;
         default:
            return null;
      }
   }
}
