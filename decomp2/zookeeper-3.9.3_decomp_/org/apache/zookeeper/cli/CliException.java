package org.apache.zookeeper.cli;

public class CliException extends Exception {
   protected int exitCode;
   protected static final int DEFAULT_EXCEPTION_EXIT_CODE = 1;

   public CliException(String message) {
      this((String)message, 1);
   }

   public CliException(String message, int exitCode) {
      super(message);
      this.exitCode = exitCode;
   }

   public CliException(Throwable cause) {
      this((Throwable)cause, 1);
   }

   public CliException(Throwable cause, int exitCode) {
      super(cause);
      this.exitCode = exitCode;
   }

   public CliException(String message, Throwable cause) {
      this(message, cause, 1);
   }

   public CliException(String message, Throwable cause, int exitCode) {
      super(message, cause);
      this.exitCode = exitCode;
   }

   public int getExitCode() {
      return this.exitCode;
   }
}
