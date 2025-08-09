package org.apache.hive.service.cli;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hive.service.rpc.thrift.TStatus;
import org.apache.hive.service.rpc.thrift.TStatusCode;

public class HiveSQLException extends SQLException {
   private static final long serialVersionUID = -6095254671958748094L;

   public HiveSQLException() {
   }

   public HiveSQLException(String reason) {
      super(reason);
   }

   public HiveSQLException(Throwable cause) {
      super(cause);
   }

   public HiveSQLException(String reason, String sqlState) {
      super(reason, sqlState);
   }

   public HiveSQLException(String reason, Throwable cause) {
      super(reason, cause);
   }

   public HiveSQLException(String reason, String sqlState, int vendorCode) {
      super(reason, sqlState, vendorCode);
   }

   public HiveSQLException(String reason, String sqlState, Throwable cause) {
      super(reason, sqlState, cause);
   }

   public HiveSQLException(String reason, String sqlState, int vendorCode, Throwable cause) {
      super(reason, sqlState, vendorCode, cause);
   }

   public HiveSQLException(TStatus status) {
      super(status.getErrorMessage(), status.getSqlState(), status.getErrorCode());
      if (status.getInfoMessages() != null) {
         this.initCause(toCause(status.getInfoMessages()));
      }

   }

   public TStatus toTStatus() {
      TStatus tStatus = new TStatus(TStatusCode.ERROR_STATUS);
      tStatus.setSqlState(this.getSQLState());
      tStatus.setErrorCode(this.getErrorCode());
      tStatus.setErrorMessage(this.getMessage());
      tStatus.setInfoMessages(toString(this));
      return tStatus;
   }

   public static TStatus toTStatus(Exception e) {
      if (e instanceof HiveSQLException) {
         return ((HiveSQLException)e).toTStatus();
      } else {
         TStatus tStatus = new TStatus(TStatusCode.ERROR_STATUS);
         tStatus.setErrorMessage(e.getMessage());
         tStatus.setInfoMessages(toString(e));
         return tStatus;
      }
   }

   public static List toString(Throwable ex) {
      return toString(ex, (StackTraceElement[])null);
   }

   private static List toString(Throwable cause, StackTraceElement[] parent) {
      StackTraceElement[] trace = cause.getStackTrace();
      int m = trace.length - 1;
      if (parent != null) {
         for(int n = parent.length - 1; m >= 0 && n >= 0 && trace[m].equals(parent[n]); --n) {
            --m;
         }
      }

      List<String> detail = enroll(cause, trace, m);
      cause = cause.getCause();
      if (cause != null) {
         detail.addAll(toString(cause, trace));
      }

      return detail;
   }

   private static List enroll(Throwable ex, StackTraceElement[] trace, int max) {
      List<String> details = new ArrayList();
      StringBuilder builder = new StringBuilder();
      builder.append('*').append(ex.getClass().getName()).append(':');
      builder.append(ex.getMessage()).append(':');
      builder.append(trace.length).append(':').append(max);
      details.add(builder.toString());

      for(int i = 0; i <= max; ++i) {
         builder.setLength(0);
         builder.append(trace[i].getClassName()).append(':');
         builder.append(trace[i].getMethodName()).append(':');
         String fileName = trace[i].getFileName();
         builder.append(fileName == null ? "" : fileName).append(':');
         builder.append(trace[i].getLineNumber());
         details.add(builder.toString());
      }

      return details;
   }

   public static Throwable toCause(List details) {
      return toStackTrace(details, (StackTraceElement[])null, 0);
   }

   private static Throwable toStackTrace(List details, StackTraceElement[] parent, int index) {
      String detail = (String)details.get(index++);
      if (!detail.startsWith("*")) {
         return null;
      } else {
         int i1 = detail.indexOf(58);
         int i3 = detail.lastIndexOf(58);
         int i2 = detail.substring(0, i3).lastIndexOf(58);
         String exceptionClass = detail.substring(1, i1);
         String exceptionMessage = detail.substring(i1 + 1, i2);
         Throwable ex = newInstance(exceptionClass, exceptionMessage);
         Integer length = Integer.valueOf(detail.substring(i2 + 1, i3));
         Integer unique = Integer.valueOf(detail.substring(i3 + 1));
         int i = 0;

         StackTraceElement[] trace;
         for(trace = new StackTraceElement[length]; i <= unique; ++i) {
            detail = (String)details.get(index++);
            int j1 = detail.indexOf(58);
            int j3 = detail.lastIndexOf(58);
            int j2 = detail.substring(0, j3).lastIndexOf(58);
            String className = detail.substring(0, j1);
            String methodName = detail.substring(j1 + 1, j2);
            String fileName = detail.substring(j2 + 1, j3);
            if (fileName.isEmpty()) {
               fileName = null;
            }

            int lineNumber = Integer.valueOf(detail.substring(j3 + 1));
            trace[i] = new StackTraceElement(className, methodName, fileName, lineNumber);
         }

         int common = trace.length - i;
         if (common > 0) {
            System.arraycopy(parent, parent.length - common, trace, trace.length - common, common);
         }

         if (details.size() > index) {
            ex.initCause(toStackTrace(details, trace, index));
         }

         ex.setStackTrace(trace);
         return ex;
      }
   }

   private static Throwable newInstance(String className, String message) {
      try {
         return (Throwable)Class.forName(className).getConstructor(String.class).newInstance(message);
      } catch (Exception var3) {
         return new RuntimeException(className + ":" + message);
      }
   }
}
