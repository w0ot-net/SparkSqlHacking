package org.apache.spark.status.protobuf;

import org.apache.spark.JobExecutionStatus;
import scala.MatchError;

public final class JobExecutionStatusSerializer$ {
   public static final JobExecutionStatusSerializer$ MODULE$ = new JobExecutionStatusSerializer$();

   public StoreTypes.JobExecutionStatus serialize(final JobExecutionStatus input) {
      if (JobExecutionStatus.RUNNING.equals(input)) {
         return StoreTypes.JobExecutionStatus.JOB_EXECUTION_STATUS_RUNNING;
      } else if (JobExecutionStatus.SUCCEEDED.equals(input)) {
         return StoreTypes.JobExecutionStatus.JOB_EXECUTION_STATUS_SUCCEEDED;
      } else if (JobExecutionStatus.FAILED.equals(input)) {
         return StoreTypes.JobExecutionStatus.JOB_EXECUTION_STATUS_FAILED;
      } else if (JobExecutionStatus.UNKNOWN.equals(input)) {
         return StoreTypes.JobExecutionStatus.JOB_EXECUTION_STATUS_UNKNOWN;
      } else {
         throw new MatchError(input);
      }
   }

   public JobExecutionStatus deserialize(final StoreTypes.JobExecutionStatus binary) {
      if (StoreTypes.JobExecutionStatus.JOB_EXECUTION_STATUS_RUNNING.equals(binary)) {
         return JobExecutionStatus.RUNNING;
      } else if (StoreTypes.JobExecutionStatus.JOB_EXECUTION_STATUS_SUCCEEDED.equals(binary)) {
         return JobExecutionStatus.SUCCEEDED;
      } else if (StoreTypes.JobExecutionStatus.JOB_EXECUTION_STATUS_FAILED.equals(binary)) {
         return JobExecutionStatus.FAILED;
      } else {
         return StoreTypes.JobExecutionStatus.JOB_EXECUTION_STATUS_UNKNOWN.equals(binary) ? JobExecutionStatus.UNKNOWN : null;
      }
   }

   private JobExecutionStatusSerializer$() {
   }
}
