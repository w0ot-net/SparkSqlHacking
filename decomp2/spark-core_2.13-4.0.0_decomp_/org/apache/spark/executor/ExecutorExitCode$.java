package org.apache.spark.executor;

import org.apache.spark.util.SparkExitCode$;

public final class ExecutorExitCode$ {
   public static final ExecutorExitCode$ MODULE$ = new ExecutorExitCode$();
   private static final int DISK_STORE_FAILED_TO_CREATE_DIR = 53;
   private static final int EXTERNAL_BLOCK_STORE_FAILED_TO_INITIALIZE = 54;
   private static final int EXTERNAL_BLOCK_STORE_FAILED_TO_CREATE_DIR = 55;
   private static final int HEARTBEAT_FAILURE = 56;
   private static final int KILLED_BY_TASK_REAPER = 57;
   private static final int BLOCK_MANAGER_REREGISTRATION_FAILED = 58;

   public int DISK_STORE_FAILED_TO_CREATE_DIR() {
      return DISK_STORE_FAILED_TO_CREATE_DIR;
   }

   public int EXTERNAL_BLOCK_STORE_FAILED_TO_INITIALIZE() {
      return EXTERNAL_BLOCK_STORE_FAILED_TO_INITIALIZE;
   }

   public int EXTERNAL_BLOCK_STORE_FAILED_TO_CREATE_DIR() {
      return EXTERNAL_BLOCK_STORE_FAILED_TO_CREATE_DIR;
   }

   public int HEARTBEAT_FAILURE() {
      return HEARTBEAT_FAILURE;
   }

   public int KILLED_BY_TASK_REAPER() {
      return KILLED_BY_TASK_REAPER;
   }

   public int BLOCK_MANAGER_REREGISTRATION_FAILED() {
      return BLOCK_MANAGER_REREGISTRATION_FAILED;
   }

   public String explainExitCode(final int exitCode) {
      if (SparkExitCode$.MODULE$.UNCAUGHT_EXCEPTION() == exitCode) {
         return "Uncaught exception";
      } else if (SparkExitCode$.MODULE$.UNCAUGHT_EXCEPTION_TWICE() == exitCode) {
         return "Uncaught exception, and logging the exception failed";
      } else if (SparkExitCode$.MODULE$.OOM() == exitCode) {
         return "OutOfMemoryError";
      } else if (this.DISK_STORE_FAILED_TO_CREATE_DIR() == exitCode) {
         return "Failed to create local directory (bad spark.local.dir?)";
      } else if (this.EXTERNAL_BLOCK_STORE_FAILED_TO_INITIALIZE() == exitCode) {
         return "ExternalBlockStore failed to initialize.";
      } else if (this.EXTERNAL_BLOCK_STORE_FAILED_TO_CREATE_DIR() == exitCode) {
         return "ExternalBlockStore failed to create a local temporary directory.";
      } else if (this.HEARTBEAT_FAILURE() == exitCode) {
         return "Unable to send heartbeats to driver.";
      } else if (this.BLOCK_MANAGER_REREGISTRATION_FAILED() == exitCode) {
         return "Executor killed due to a failure of block manager re-registration.";
      } else {
         return this.KILLED_BY_TASK_REAPER() == exitCode ? "Executor killed by TaskReaper." : "Unknown executor exit code (" + exitCode + ")" + (exitCode > 128 ? " (died from signal " + (exitCode - 128) + "?)" : "");
      }
   }

   private ExecutorExitCode$() {
   }
}
