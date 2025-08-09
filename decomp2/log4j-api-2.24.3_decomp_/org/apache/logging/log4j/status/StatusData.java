package org.apache.logging.log4j.status;

import edu.umd.cs.findbugs.annotations.Nullable;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.message.Message;

public class StatusData implements Serializable {
   private static final long serialVersionUID = -4341916115118014017L;
   private final Instant instant;
   private final DateTimeFormatter instantFormatter;
   @Nullable
   private final StackTraceElement caller;
   private final Level level;
   private final Message message;
   private final String threadName;
   @Nullable
   private final Throwable throwable;

   public StatusData(@Nullable final StackTraceElement caller, final Level level, final Message message, @Nullable final Throwable throwable, @Nullable final String threadName) {
      this(caller, level, message, throwable, threadName, (DateTimeFormatter)null, Instant.now());
   }

   StatusData(@Nullable final StackTraceElement caller, final Level level, final Message message, @Nullable final Throwable throwable, @Nullable final String threadName, @Nullable final DateTimeFormatter instantFormatter, final Instant instant) {
      this.instantFormatter = instantFormatter != null ? instantFormatter : DateTimeFormatter.ISO_INSTANT;
      this.instant = instant;
      this.caller = caller;
      this.level = (Level)Objects.requireNonNull(level, "level");
      this.message = (Message)Objects.requireNonNull(message, "message");
      this.throwable = throwable;
      this.threadName = threadName != null ? threadName : Thread.currentThread().getName();
   }

   public Instant getInstant() {
      return this.instant;
   }

   /** @deprecated */
   @Deprecated
   public long getTimestamp() {
      return this.instant.toEpochMilli();
   }

   @Nullable
   public StackTraceElement getStackTraceElement() {
      return this.caller;
   }

   public Level getLevel() {
      return this.level;
   }

   public Message getMessage() {
      return this.message;
   }

   public String getThreadName() {
      return this.threadName;
   }

   @Nullable
   public Throwable getThrowable() {
      return this.throwable;
   }

   @SuppressFBWarnings(
      value = {"INFORMATION_EXPOSURE_THROUGH_AN_ERROR_MESSAGE"},
      justification = "Log4j prints stacktraces only to logs, which should be private."
   )
   public String getFormattedStatus() {
      StringBuilder sb = new StringBuilder();
      this.instantFormatter.formatTo(this.instant, sb);
      sb.append(' ');
      sb.append(this.getThreadName());
      sb.append(' ');
      sb.append(this.level.toString());
      sb.append(' ');
      sb.append(this.message.getFormattedMessage());
      Object[] parameters = this.message.getParameters();
      Throwable effectiveThrowable;
      if (this.throwable == null && parameters != null && parameters[parameters.length - 1] instanceof Throwable) {
         effectiveThrowable = (Throwable)parameters[parameters.length - 1];
      } else {
         effectiveThrowable = this.throwable;
      }

      if (effectiveThrowable != null) {
         sb.append(' ');
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         effectiveThrowable.printStackTrace(new PrintStream(baos));
         sb.append(baos);
      }

      return sb.toString();
   }

   public String toString() {
      return this.getMessage().getFormattedMessage();
   }
}
