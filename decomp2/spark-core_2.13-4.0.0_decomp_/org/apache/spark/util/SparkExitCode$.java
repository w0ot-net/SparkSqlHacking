package org.apache.spark.util;

public final class SparkExitCode$ {
   public static final SparkExitCode$ MODULE$ = new SparkExitCode$();
   private static final int EXIT_SUCCESS = 0;
   private static final int EXIT_FAILURE = 1;
   private static final int ERROR_MISUSE_SHELL_BUILTIN = 2;
   private static final int ERROR_PATH_NOT_FOUND = 3;
   private static final int EXCEED_MAX_EXECUTOR_FAILURES = 11;
   private static final int UNCAUGHT_EXCEPTION = 50;
   private static final int UNCAUGHT_EXCEPTION_TWICE = 51;
   private static final int OOM = 52;
   private static final int DRIVER_TIMEOUT = 124;
   private static final int ERROR_COMMAND_NOT_FOUND = 127;

   public int EXIT_SUCCESS() {
      return EXIT_SUCCESS;
   }

   public int EXIT_FAILURE() {
      return EXIT_FAILURE;
   }

   public int ERROR_MISUSE_SHELL_BUILTIN() {
      return ERROR_MISUSE_SHELL_BUILTIN;
   }

   public int ERROR_PATH_NOT_FOUND() {
      return ERROR_PATH_NOT_FOUND;
   }

   public int EXCEED_MAX_EXECUTOR_FAILURES() {
      return EXCEED_MAX_EXECUTOR_FAILURES;
   }

   public int UNCAUGHT_EXCEPTION() {
      return UNCAUGHT_EXCEPTION;
   }

   public int UNCAUGHT_EXCEPTION_TWICE() {
      return UNCAUGHT_EXCEPTION_TWICE;
   }

   public int OOM() {
      return OOM;
   }

   public int DRIVER_TIMEOUT() {
      return DRIVER_TIMEOUT;
   }

   public int ERROR_COMMAND_NOT_FOUND() {
      return ERROR_COMMAND_NOT_FOUND;
   }

   private SparkExitCode$() {
   }
}
