package org.apache.parquet.format;

import shaded.parquet.org.apache.thrift.TEnum;
import shaded.parquet.org.apache.thrift.annotation.Nullable;

public enum PageType implements TEnum {
   DATA_PAGE(0),
   INDEX_PAGE(1),
   DICTIONARY_PAGE(2),
   DATA_PAGE_V2(3);

   private final int value;

   private PageType(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static PageType findByValue(int value) {
      switch (value) {
         case 0:
            return DATA_PAGE;
         case 1:
            return INDEX_PAGE;
         case 2:
            return DICTIONARY_PAGE;
         case 3:
            return DATA_PAGE_V2;
         default:
            return null;
      }
   }
}
