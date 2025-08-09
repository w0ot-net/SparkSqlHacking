package org.apache.spark.sql.hive.thriftserver.ui;

public final class ToolTips$ {
   public static final ToolTips$ MODULE$ = new ToolTips$();
   private static final String THRIFT_SERVER_FINISH_TIME = "Execution finish time, before fetching the results";
   private static final String THRIFT_SERVER_CLOSE_TIME = "Operation close time after fetching the results";
   private static final String THRIFT_SERVER_EXECUTION = "Difference between start time and finish time";
   private static final String THRIFT_SERVER_DURATION = "Difference between start time and close time";
   private static final String THRIFT_SESSION_TOTAL_EXECUTE = "Number of operations submitted in this session";
   private static final String THRIFT_SESSION_DURATION = "Elapsed time since session start, or until closed if the session was closed";

   public String THRIFT_SERVER_FINISH_TIME() {
      return THRIFT_SERVER_FINISH_TIME;
   }

   public String THRIFT_SERVER_CLOSE_TIME() {
      return THRIFT_SERVER_CLOSE_TIME;
   }

   public String THRIFT_SERVER_EXECUTION() {
      return THRIFT_SERVER_EXECUTION;
   }

   public String THRIFT_SERVER_DURATION() {
      return THRIFT_SERVER_DURATION;
   }

   public String THRIFT_SESSION_TOTAL_EXECUTE() {
      return THRIFT_SESSION_TOTAL_EXECUTE;
   }

   public String THRIFT_SESSION_DURATION() {
      return THRIFT_SESSION_DURATION;
   }

   private ToolTips$() {
   }
}
