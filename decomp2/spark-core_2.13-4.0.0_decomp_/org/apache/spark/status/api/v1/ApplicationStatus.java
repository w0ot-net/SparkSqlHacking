package org.apache.spark.status.api.v1;

import org.apache.spark.util.EnumUtil;

public enum ApplicationStatus {
   COMPLETED,
   RUNNING;

   public static ApplicationStatus fromString(String str) {
      return (ApplicationStatus)EnumUtil.parseIgnoreCase(ApplicationStatus.class, str);
   }

   // $FF: synthetic method
   private static ApplicationStatus[] $values() {
      return new ApplicationStatus[]{COMPLETED, RUNNING};
   }
}
