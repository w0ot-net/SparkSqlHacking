package org.apache.spark;

import org.apache.spark.util.EnumUtil;

public enum JobExecutionStatus {
   RUNNING,
   SUCCEEDED,
   FAILED,
   UNKNOWN;

   public static JobExecutionStatus fromString(String str) {
      return (JobExecutionStatus)EnumUtil.parseIgnoreCase(JobExecutionStatus.class, str);
   }

   // $FF: synthetic method
   private static JobExecutionStatus[] $values() {
      return new JobExecutionStatus[]{RUNNING, SUCCEEDED, FAILED, UNKNOWN};
   }
}
