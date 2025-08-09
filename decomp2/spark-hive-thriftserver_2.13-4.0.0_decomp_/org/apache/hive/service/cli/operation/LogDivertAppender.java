package org.apache.hive.service.cli.operation;

import java.io.CharArrayWriter;
import java.io.Serializable;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.hadoop.hive.ql.exec.Task;
import org.apache.hadoop.hive.ql.log.PerfLogger;
import org.apache.hadoop.hive.ql.session.OperationLog;
import org.apache.hadoop.hive.ql.session.OperationLog.LoggingLevel;
import org.apache.hive.service.cli.CLIServiceUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LifeCycle;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.StringLayout;
import org.apache.logging.log4j.core.Filter.Result;
import org.apache.logging.log4j.core.LifeCycle.State;
import org.apache.logging.log4j.core.appender.AbstractWriterAppender;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.WriterManager;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.message.Message;
import org.sparkproject.guava.base.Joiner;

public class LogDivertAppender extends AbstractWriterAppender {
   private static final Logger LOG = LogManager.getLogger(LogDivertAppender.class.getName());
   private final OperationManager operationManager;
   private boolean isVerbose;
   private final CharArrayWriter writer;

   private static StringLayout getLayout(boolean isVerbose, StringLayout lo) {
      if (isVerbose) {
         if (lo == null) {
            lo = CLIServiceUtils.verboseLayout;
            LOG.info("Cannot find a Layout from a ConsoleAppender. Using default Layout pattern.");
         }
      } else {
         lo = CLIServiceUtils.nonVerboseLayout;
      }

      return lo;
   }

   private static StringLayout initLayout(OperationLog.LoggingLevel loggingMode) {
      boolean isVerbose = loggingMode == LoggingLevel.VERBOSE;
      org.apache.logging.log4j.core.Logger root = (org.apache.logging.log4j.core.Logger)LogManager.getRootLogger();
      StringLayout layout = null;
      Map<String, Appender> appenders = root.getAppenders();

      for(Appender ap : appenders.values()) {
         if (ap.getClass().equals(ConsoleAppender.class)) {
            Layout<? extends Serializable> l = ap.getLayout();
            if (l instanceof StringLayout) {
               layout = (StringLayout)l;
               break;
            }
         }
      }

      return getLayout(isVerbose, layout);
   }

   public static LogDivertAppender create(OperationManager operationManager, OperationLog.LoggingLevel loggingMode) {
      CharArrayWriter writer = new CharArrayWriter();
      return new LogDivertAppender(operationManager, loggingMode, writer);
   }

   private LogDivertAppender(OperationManager operationManager, OperationLog.LoggingLevel loggingMode, CharArrayWriter writer) {
      super("LogDivertAppender", initLayout(loggingMode), (Filter)null, false, true, Property.EMPTY_ARRAY, new WriterManager(writer, "LogDivertAppender", initLayout(loggingMode), true));
      this.writer = writer;
      this.isVerbose = loggingMode == LoggingLevel.VERBOSE;
      this.operationManager = operationManager;
      this.addFilter(new NameFilter(loggingMode, operationManager));
   }

   public void append(LogEvent event) {
      OperationLog log = this.operationManager.getOperationLogByThread();
      if (log != null) {
         boolean isCurrModeVerbose = log.getOpLoggingLevel() == LoggingLevel.VERBOSE;
         if (isCurrModeVerbose != this.isVerbose) {
            this.isVerbose = isCurrModeVerbose;
         }
      }

      super.append(event);
      String logOutput = this.writer.toString();
      this.writer.reset();
      if (log == null) {
         LOG.debug(" ---+++=== Dropped log event from thread " + event.getThreadName());
      } else {
         log.writeOperationLog(logOutput);
      }
   }

   private static class NameFilter implements Filter {
      private Pattern namePattern;
      private OperationLog.LoggingLevel loggingMode;
      private OperationManager operationManager;
      private LifeCycle.State state;
      private static final Pattern verboseExcludeNamePattern;
      private static final Pattern executionIncludeNamePattern;
      private static final Pattern performanceIncludeNamePattern;

      private void setCurrentNamePattern(OperationLog.LoggingLevel mode) {
         if (mode == LoggingLevel.VERBOSE) {
            this.namePattern = verboseExcludeNamePattern;
         } else if (mode == LoggingLevel.EXECUTION) {
            this.namePattern = executionIncludeNamePattern;
         } else if (mode == LoggingLevel.PERFORMANCE) {
            this.namePattern = performanceIncludeNamePattern;
         }

      }

      NameFilter(OperationLog.LoggingLevel loggingMode, OperationManager op) {
         this.operationManager = op;
         this.loggingMode = loggingMode;
         this.state = State.INITIALIZING;
         this.setCurrentNamePattern(loggingMode);
      }

      public Filter.Result getOnMismatch() {
         return Result.NEUTRAL;
      }

      public Filter.Result getOnMatch() {
         return Result.NEUTRAL;
      }

      public Filter.Result filter(org.apache.logging.log4j.core.Logger logger, Level level, Marker marker, String s, Object... objects) {
         return Result.NEUTRAL;
      }

      public Filter.Result filter(org.apache.logging.log4j.core.Logger logger, Level level, Marker marker, String s, Object o) {
         return Result.NEUTRAL;
      }

      public Filter.Result filter(org.apache.logging.log4j.core.Logger logger, Level level, Marker marker, String s, Object o, Object o1) {
         return Result.NEUTRAL;
      }

      public Filter.Result filter(org.apache.logging.log4j.core.Logger logger, Level level, Marker marker, String s, Object o, Object o1, Object o2) {
         return Result.NEUTRAL;
      }

      public Filter.Result filter(org.apache.logging.log4j.core.Logger logger, Level level, Marker marker, String s, Object o, Object o1, Object o2, Object o3) {
         return Result.NEUTRAL;
      }

      public Filter.Result filter(org.apache.logging.log4j.core.Logger logger, Level level, Marker marker, String s, Object o, Object o1, Object o2, Object o3, Object o4) {
         return Result.NEUTRAL;
      }

      public Filter.Result filter(org.apache.logging.log4j.core.Logger logger, Level level, Marker marker, String s, Object o, Object o1, Object o2, Object o3, Object o4, Object o5) {
         return Result.NEUTRAL;
      }

      public Filter.Result filter(org.apache.logging.log4j.core.Logger logger, Level level, Marker marker, String s, Object o, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6) {
         return Result.NEUTRAL;
      }

      public Filter.Result filter(org.apache.logging.log4j.core.Logger logger, Level level, Marker marker, String s, Object o, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7) {
         return Result.NEUTRAL;
      }

      public Filter.Result filter(org.apache.logging.log4j.core.Logger logger, Level level, Marker marker, String s, Object o, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7, Object o8) {
         return Result.NEUTRAL;
      }

      public Filter.Result filter(org.apache.logging.log4j.core.Logger logger, Level level, Marker marker, String s, Object o, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7, Object o8, Object o9) {
         return Result.NEUTRAL;
      }

      public Filter.Result filter(org.apache.logging.log4j.core.Logger logger, Level level, Marker marker, Object o, Throwable throwable) {
         return Result.NEUTRAL;
      }

      public Filter.Result filter(org.apache.logging.log4j.core.Logger logger, Level level, Marker marker, Message message, Throwable throwable) {
         return Result.NEUTRAL;
      }

      public Filter.Result filter(LogEvent logEvent) {
         OperationLog log = this.operationManager.getOperationLogByThread();
         boolean excludeMatches = this.loggingMode == LoggingLevel.VERBOSE;
         if (log == null) {
            return Result.DENY;
         } else {
            OperationLog.LoggingLevel currentLoggingMode = log.getOpLoggingLevel();
            if (currentLoggingMode == LoggingLevel.NONE) {
               return Result.DENY;
            } else {
               if (currentLoggingMode != this.loggingMode) {
                  this.loggingMode = currentLoggingMode;
                  this.setCurrentNamePattern(this.loggingMode);
               }

               boolean isMatch = this.namePattern.matcher(logEvent.getLoggerName()).matches();
               return excludeMatches == isMatch ? Result.DENY : Result.NEUTRAL;
            }
         }
      }

      public LifeCycle.State getState() {
         return this.state;
      }

      public void initialize() {
         this.state = State.INITIALIZED;
      }

      public void start() {
         this.state = State.STARTED;
      }

      public void stop() {
         this.state = State.STOPPED;
      }

      public boolean isStarted() {
         return this.state == State.STARTED;
      }

      public boolean isStopped() {
         return this.state == State.STOPPED;
      }

      static {
         verboseExcludeNamePattern = Pattern.compile(Joiner.on("|").join(new String[]{LogDivertAppender.LOG.getName(), OperationLog.class.getName(), OperationManager.class.getName()}));
         executionIncludeNamePattern = Pattern.compile(Joiner.on("|").join(new String[]{"org.apache.hadoop.mapreduce.JobSubmitter", "org.apache.hadoop.mapreduce.Job", "SessionState", Task.class.getName(), "org.apache.hadoop.hive.ql.exec.spark.status.SparkJobMonitor"}));
         String var10000 = executionIncludeNamePattern.pattern();
         performanceIncludeNamePattern = Pattern.compile(var10000 + "|" + PerfLogger.class.getName());
      }
   }
}
