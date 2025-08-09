package org.apache.spark.status.api.v1.streaming;

import org.apache.spark.util.EnumUtil;

public enum BatchStatus {
   COMPLETED,
   QUEUED,
   PROCESSING;

   public static BatchStatus fromString(String str) {
      return (BatchStatus)EnumUtil.parseIgnoreCase(BatchStatus.class, str);
   }

   // $FF: synthetic method
   private static BatchStatus[] $values() {
      return new BatchStatus[]{COMPLETED, QUEUED, PROCESSING};
   }
}
