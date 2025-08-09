package org.apache.hive.service.rpc.thrift;

import org.apache.thrift.TEnum;
import org.apache.thrift.annotation.Nullable;

public enum TFetchOrientation implements TEnum {
   FETCH_NEXT(0),
   FETCH_PRIOR(1),
   FETCH_RELATIVE(2),
   FETCH_ABSOLUTE(3),
   FETCH_FIRST(4),
   FETCH_LAST(5);

   private final int value;

   private TFetchOrientation(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static TFetchOrientation findByValue(int value) {
      switch (value) {
         case 0:
            return FETCH_NEXT;
         case 1:
            return FETCH_PRIOR;
         case 2:
            return FETCH_RELATIVE;
         case 3:
            return FETCH_ABSOLUTE;
         case 4:
            return FETCH_FIRST;
         case 5:
            return FETCH_LAST;
         default:
            return null;
      }
   }
}
