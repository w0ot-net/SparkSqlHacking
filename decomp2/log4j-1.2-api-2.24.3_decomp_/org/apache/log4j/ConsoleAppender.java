package org.apache.log4j;

import org.apache.log4j.spi.LoggingEvent;
import org.apache.logging.log4j.status.StatusLogger;

public class ConsoleAppender extends WriterAppender {
   public static final String SYSTEM_OUT = "System.out";
   public static final String SYSTEM_ERR = "System.err";
   protected String target;
   private boolean follow;

   public ConsoleAppender() {
      this.target = "System.out";
   }

   public ConsoleAppender(final Layout layout) {
      this(layout, "System.out");
   }

   public ConsoleAppender(final Layout layout, final String target) {
      this.target = "System.out";
      this.setLayout(layout);
      this.setTarget(target);
      this.activateOptions();
   }

   public void append(final LoggingEvent theEvent) {
   }

   public void close() {
   }

   public boolean getFollow() {
      return this.follow;
   }

   public String getTarget() {
      return this.target;
   }

   public boolean requiresLayout() {
      return false;
   }

   public void setFollow(final boolean follow) {
      this.follow = follow;
   }

   public void setTarget(final String value) {
      String v = value.trim();
      if ("System.out".equalsIgnoreCase(v)) {
         this.target = "System.out";
      } else if ("System.err".equalsIgnoreCase(v)) {
         this.target = "System.err";
      } else {
         this.targetWarn(value);
      }

   }

   void targetWarn(final String val) {
      StatusLogger.getLogger().warn("[" + val + "] should be System.out or System.err.");
      StatusLogger.getLogger().warn("Using previously set target, System.out by default.");
   }
}
