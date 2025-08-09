package org.apache.spark.status.api.v1;

import org.apache.spark.util.EnumUtil;

public enum StageStatus {
   ACTIVE,
   COMPLETE,
   FAILED,
   PENDING,
   SKIPPED;

   public static StageStatus fromString(String str) {
      return (StageStatus)EnumUtil.parseIgnoreCase(StageStatus.class, str);
   }

   // $FF: synthetic method
   private static StageStatus[] $values() {
      return new StageStatus[]{ACTIVE, COMPLETE, FAILED, PENDING, SKIPPED};
   }
}
