package io.vertx.core.logging;

import io.vertx.core.spi.logging.LogDelegate;
import io.vertx.core.spi.logging.LogDelegateFactory;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/** @deprecated */
public class LoggerFactory {
   public static final String LOGGER_DELEGATE_FACTORY_CLASS_NAME = "vertx.logger-delegate-factory-class-name";
   private static volatile LogDelegateFactory delegateFactory;
   private static final ConcurrentMap loggers = new ConcurrentHashMap();

   public static synchronized void initialise() {
      ClassLoader loader = Thread.currentThread().getContextClassLoader();

      String className;
      try {
         className = System.getProperty("vertx.logger-delegate-factory-class-name");
      } catch (Exception var3) {
         className = null;
      }

      if (className == null || !configureWith(className, false, loader)) {
         if (loader.getResource("vertx-default-jul-logging.properties") != null || !configureWith("SLF4J", true, loader) && !configureWith("Log4j2", true, loader)) {
            delegateFactory = new JULLogDelegateFactory();
         }
      }
   }

   private static boolean configureWith(String name, boolean shortName, ClassLoader loader) {
      try {
         Class<?> clazz = Class.forName(shortName ? "io.vertx.core.logging." + name + "LogDelegateFactory" : name, true, loader);
         LogDelegateFactory factory = (LogDelegateFactory)clazz.newInstance();
         if (!factory.isAvailable()) {
            return false;
         } else {
            delegateFactory = factory;
            return true;
         }
      } catch (Throwable var5) {
         return false;
      }
   }

   /** @deprecated */
   @Deprecated
   public static Logger getLogger(Class clazz) {
      String name = clazz.isAnonymousClass() ? clazz.getEnclosingClass().getCanonicalName() : clazz.getCanonicalName();
      return getLogger(name);
   }

   /** @deprecated */
   @Deprecated
   public static Logger getLogger(String name) {
      Logger logger = (Logger)loggers.get(name);
      if (logger == null) {
         LogDelegate delegate = delegateFactory.createDelegate(name);
         logger = new Logger(delegate);
         Logger oldLogger = (Logger)loggers.putIfAbsent(name, logger);
         if (oldLogger != null) {
            logger = oldLogger;
         }
      }

      return logger;
   }

   /** @deprecated */
   @Deprecated
   public static void removeLogger(String name) {
      loggers.remove(name);
   }

   static {
      initialise();
      LogDelegate log = delegateFactory.createDelegate(LoggerFactory.class.getName());
      log.debug("Using " + delegateFactory.getClass().getName());
   }
}
