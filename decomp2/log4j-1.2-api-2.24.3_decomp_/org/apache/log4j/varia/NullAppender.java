package org.apache.log4j.varia;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

public class NullAppender extends AppenderSkeleton {
   private static final NullAppender INSTANCE = new NullAppender();

   public static NullAppender getNullAppender() {
      return INSTANCE;
   }

   public void activateOptions() {
   }

   protected void append(final LoggingEvent event) {
   }

   public void close() {
   }

   public void doAppend(final LoggingEvent event) {
   }

   /** @deprecated */
   @Deprecated
   public NullAppender getInstance() {
      return INSTANCE;
   }

   public boolean requiresLayout() {
      return false;
   }
}
