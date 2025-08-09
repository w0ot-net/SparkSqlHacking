package org.glassfish.jersey.process;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.internal.LocalizationMessages;

public class JerseyProcessingUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
   private static final Logger LOGGER = Logger.getLogger(JerseyProcessingUncaughtExceptionHandler.class.getName());
   private final Level logLevel;

   public JerseyProcessingUncaughtExceptionHandler() {
      this(Level.WARNING);
   }

   public JerseyProcessingUncaughtExceptionHandler(Level logLevel) {
      this.logLevel = logLevel;
   }

   public void uncaughtException(Thread t, Throwable e) {
      LOGGER.log(this.logLevel, LocalizationMessages.UNHANDLED_EXCEPTION_DETECTED(t.getName()), e);
   }
}
