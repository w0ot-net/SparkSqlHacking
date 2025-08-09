package org.apache.parquet;

import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
   public static final Level LEVEL;
   public static final boolean DEBUG;
   public static final boolean INFO;
   public static final boolean WARN;
   public static final boolean ERROR;
   private Logger logger;

   /** @deprecated */
   public static Log getLog(Class c) {
      return new Log(c);
   }

   public Log(Class c) {
      this.logger = LoggerFactory.getLogger(c);
   }

   public void debug(Object m) {
      if (m instanceof Throwable) {
         this.logger.debug("", (Throwable)m);
      } else {
         this.logger.debug(String.valueOf(m));
      }

   }

   public void debug(Object m, Throwable t) {
      this.logger.debug(String.valueOf(m), t);
   }

   public void info(Object m) {
      if (m instanceof Throwable) {
         this.logger.info("", (Throwable)m);
      } else {
         this.logger.info(String.valueOf(m));
      }

   }

   public void info(Object m, Throwable t) {
      this.logger.info(String.valueOf(m), t);
   }

   public void warn(Object m) {
      if (m instanceof Throwable) {
         this.logger.warn("", (Throwable)m);
      } else {
         this.logger.warn(String.valueOf(m));
      }

   }

   public void warn(Object m, Throwable t) {
      this.logger.warn(String.valueOf(m), t);
   }

   public void error(Object m) {
      if (m instanceof Throwable) {
         this.logger.error("", (Throwable)m);
      } else {
         this.logger.error(String.valueOf(m));
      }

   }

   public void error(Object m, Throwable t) {
      this.logger.error(String.valueOf(m), t);
   }

   static {
      LEVEL = Level.INFO;
      DEBUG = LEVEL.intValue() <= Level.FINE.intValue();
      INFO = LEVEL.intValue() <= Level.INFO.intValue();
      WARN = LEVEL.intValue() <= Level.WARNING.intValue();
      ERROR = LEVEL.intValue() <= Level.SEVERE.intValue();
   }
}
