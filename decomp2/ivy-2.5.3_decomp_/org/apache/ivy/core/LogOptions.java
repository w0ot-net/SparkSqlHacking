package org.apache.ivy.core;

public class LogOptions {
   public static final String LOG_DEFAULT = "default";
   public static final String LOG_DOWNLOAD_ONLY = "download-only";
   public static final String LOG_QUIET = "quiet";
   private String log = "default";

   public LogOptions() {
   }

   public LogOptions(LogOptions options) {
      this.log = options.log;
   }

   public String getLog() {
      return this.log;
   }

   public LogOptions setLog(String log) {
      this.log = log;
      return this;
   }
}
