package org.apache.spark.status.api.v1;

import org.apache.spark.util.EnumUtil;

public enum TaskStatus {
   RUNNING,
   KILLED,
   FAILED,
   SUCCESS,
   UNKNOWN;

   public static TaskStatus fromString(String str) {
      return (TaskStatus)EnumUtil.parseIgnoreCase(TaskStatus.class, str);
   }

   // $FF: synthetic method
   private static TaskStatus[] $values() {
      return new TaskStatus[]{RUNNING, KILLED, FAILED, SUCCESS, UNKNOWN};
   }
}
