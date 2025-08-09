package org.apache.hive.common.util;

import org.apache.hadoop.hive.common.ValidWriteIdList;

public class TxnIdUtils {
   public static boolean checkEquivalentWriteIds(ValidWriteIdList a, ValidWriteIdList b) {
      return compare(a, b) == 0;
   }

   public static int compare(ValidWriteIdList a, ValidWriteIdList b) {
      if (!a.getTableName().equalsIgnoreCase(b.getTableName())) {
         return a.getTableName().toLowerCase().compareTo(b.getTableName().toLowerCase());
      } else {
         int minLen = Math.min(a.getInvalidWriteIds().length, b.getInvalidWriteIds().length);

         for(int i = 0; i < minLen; ++i) {
            if (a.getInvalidWriteIds()[i] != b.getInvalidWriteIds()[i]) {
               return a.getInvalidWriteIds()[i] > b.getInvalidWriteIds()[i] ? 1 : -1;
            }
         }

         if (a.getInvalidWriteIds().length == b.getInvalidWriteIds().length) {
            return Long.signum(a.getHighWatermark() - b.getHighWatermark());
         } else if (a.getInvalidWriteIds().length == minLen) {
            if (a.getHighWatermark() != b.getInvalidWriteIds()[minLen] - 1L) {
               return Long.signum(a.getHighWatermark() - (b.getInvalidWriteIds()[minLen] - 1L));
            } else if (allInvalidFrom(b.getInvalidWriteIds(), minLen, b.getHighWatermark())) {
               return 0;
            } else {
               return -1;
            }
         } else if (b.getHighWatermark() != a.getInvalidWriteIds()[minLen] - 1L) {
            return Long.signum(b.getHighWatermark() - (a.getInvalidWriteIds()[minLen] - 1L));
         } else if (allInvalidFrom(a.getInvalidWriteIds(), minLen, a.getHighWatermark())) {
            return 0;
         } else {
            return 1;
         }
      }
   }

   private static boolean allInvalidFrom(long[] invalidIds, int start, long hwm) {
      for(int i = start + 1; i < invalidIds.length; ++i) {
         if (invalidIds[i] != invalidIds[i - 1] + 1L) {
            return false;
         }
      }

      return invalidIds[invalidIds.length - 1] == hwm;
   }
}
