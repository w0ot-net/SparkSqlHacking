package org.sparkproject.jetty.util.log;

import org.slf4j.LoggerFactory;

/** @deprecated */
public class Log {
   /** @deprecated */
   @Deprecated
   public static final String EXCEPTION = "EXCEPTION";

   /** @deprecated */
   @Deprecated
   public static Logger getLogger(Class clazz) {
      return new Slf4jLogger(LoggerFactory.getLogger(clazz));
   }

   /** @deprecated */
   @Deprecated
   public static Logger getLogger(String name) {
      return new Slf4jLogger(LoggerFactory.getLogger(name));
   }

   /** @deprecated */
   @Deprecated
   public static Logger getRootLogger() {
      return new Slf4jLogger(LoggerFactory.getLogger(""));
   }

   /** @deprecated */
   @Deprecated
   public static Logger getLog() {
      return getRootLogger();
   }

   /** @deprecated */
   @Deprecated
   public static void setLog(Logger log) {
   }
}
