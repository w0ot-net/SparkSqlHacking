package org.apache.logging.log4j;

import java.net.URI;
import org.apache.logging.log4j.internal.LogManagerStatus;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;
import org.apache.logging.log4j.simple.SimpleLoggerContextFactory;
import org.apache.logging.log4j.spi.LoggerContext;
import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.apache.logging.log4j.spi.Terminable;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.ProviderUtil;
import org.apache.logging.log4j.util.StackLocatorUtil;

public class LogManager {
   /** @deprecated */
   @Deprecated
   public static final String FACTORY_PROPERTY_NAME = "log4j2.loggerContextFactory";
   public static final String ROOT_LOGGER_NAME = "";
   private static final Logger LOGGER = StatusLogger.getLogger();
   private static final String FQCN = LogManager.class.getName();
   private static volatile LoggerContextFactory factory = ProviderUtil.getProvider().getLoggerContextFactory();

   protected LogManager() {
   }

   public static boolean exists(final String name) {
      return getContext().hasLogger(name);
   }

   public static LoggerContext getContext() {
      try {
         return factory.getContext(FQCN, (ClassLoader)null, (Object)null, true);
      } catch (IllegalStateException ex) {
         LOGGER.warn((String)"{} Using SimpleLogger", (Object)ex.getMessage());
         return SimpleLoggerContextFactory.INSTANCE.getContext(FQCN, (ClassLoader)null, (Object)null, true);
      }
   }

   public static LoggerContext getContext(final boolean currentContext) {
      try {
         return factory.getContext(FQCN, (ClassLoader)null, (Object)null, currentContext, (URI)null, (String)null);
      } catch (IllegalStateException ex) {
         LOGGER.warn((String)"{} Using SimpleLogger", (Object)ex.getMessage());
         return SimpleLoggerContextFactory.INSTANCE.getContext(FQCN, (ClassLoader)null, (Object)null, currentContext, (URI)null, (String)null);
      }
   }

   public static LoggerContext getContext(final ClassLoader loader, final boolean currentContext) {
      try {
         return factory.getContext(FQCN, loader, (Object)null, currentContext);
      } catch (IllegalStateException ex) {
         LOGGER.warn((String)"{} Using SimpleLogger", (Object)ex.getMessage());
         return SimpleLoggerContextFactory.INSTANCE.getContext(FQCN, loader, (Object)null, currentContext);
      }
   }

   public static LoggerContext getContext(final ClassLoader loader, final boolean currentContext, final Object externalContext) {
      try {
         return factory.getContext(FQCN, loader, externalContext, currentContext);
      } catch (IllegalStateException ex) {
         LOGGER.warn((String)"{} Using SimpleLogger", (Object)ex.getMessage());
         return SimpleLoggerContextFactory.INSTANCE.getContext(FQCN, loader, externalContext, currentContext);
      }
   }

   public static LoggerContext getContext(final ClassLoader loader, final boolean currentContext, final URI configLocation) {
      try {
         return factory.getContext(FQCN, loader, (Object)null, currentContext, configLocation, (String)null);
      } catch (IllegalStateException ex) {
         LOGGER.warn((String)"{} Using SimpleLogger", (Object)ex.getMessage());
         return SimpleLoggerContextFactory.INSTANCE.getContext(FQCN, loader, (Object)null, currentContext, configLocation, (String)null);
      }
   }

   public static LoggerContext getContext(final ClassLoader loader, final boolean currentContext, final Object externalContext, final URI configLocation) {
      try {
         return factory.getContext(FQCN, loader, externalContext, currentContext, configLocation, (String)null);
      } catch (IllegalStateException ex) {
         LOGGER.warn((String)"{} Using SimpleLogger", (Object)ex.getMessage());
         return SimpleLoggerContextFactory.INSTANCE.getContext(FQCN, loader, externalContext, currentContext, configLocation, (String)null);
      }
   }

   public static LoggerContext getContext(final ClassLoader loader, final boolean currentContext, final Object externalContext, final URI configLocation, final String name) {
      try {
         return factory.getContext(FQCN, loader, externalContext, currentContext, configLocation, name);
      } catch (IllegalStateException ex) {
         LOGGER.warn((String)"{} Using SimpleLogger", (Object)ex.getMessage());
         return SimpleLoggerContextFactory.INSTANCE.getContext(FQCN, loader, externalContext, currentContext, configLocation, name);
      }
   }

   protected static LoggerContext getContext(final String fqcn, final boolean currentContext) {
      try {
         return factory.getContext(fqcn, (ClassLoader)null, (Object)null, currentContext);
      } catch (IllegalStateException ex) {
         LOGGER.warn((String)"{} Using SimpleLogger", (Object)ex.getMessage());
         return SimpleLoggerContextFactory.INSTANCE.getContext(fqcn, (ClassLoader)null, (Object)null, currentContext);
      }
   }

   protected static LoggerContext getContext(final String fqcn, final ClassLoader loader, final boolean currentContext) {
      try {
         return factory.getContext(fqcn, loader, (Object)null, currentContext);
      } catch (IllegalStateException ex) {
         LOGGER.warn((String)"{} Using SimpleLogger", (Object)ex.getMessage());
         return SimpleLoggerContextFactory.INSTANCE.getContext(fqcn, loader, (Object)null, currentContext);
      }
   }

   protected static LoggerContext getContext(final String fqcn, final ClassLoader loader, final boolean currentContext, final URI configLocation, final String name) {
      try {
         return factory.getContext(fqcn, loader, (Object)null, currentContext, configLocation, name);
      } catch (IllegalStateException ex) {
         LOGGER.warn((String)"{} Using SimpleLogger", (Object)ex.getMessage());
         return SimpleLoggerContextFactory.INSTANCE.getContext(fqcn, loader, (Object)null, currentContext);
      }
   }

   public static void shutdown() {
      shutdown(false);
   }

   public static void shutdown(final boolean currentContext) {
      factory.shutdown(FQCN, (ClassLoader)null, currentContext, false);
   }

   public static void shutdown(final boolean currentContext, final boolean allContexts) {
      factory.shutdown(FQCN, (ClassLoader)null, currentContext, allContexts);
   }

   public static void shutdown(final LoggerContext context) {
      if (context instanceof Terminable) {
         ((Terminable)context).terminate();
      }

   }

   public static LoggerContextFactory getFactory() {
      return factory;
   }

   public static void setFactory(final LoggerContextFactory factory) {
      LogManager.factory = factory;
   }

   public static Logger getFormatterLogger() {
      return getFormatterLogger(StackLocatorUtil.getCallerClass(2));
   }

   public static Logger getFormatterLogger(final Class clazz) {
      return getLogger((Class)(clazz != null ? clazz : StackLocatorUtil.getCallerClass(2)), (MessageFactory)StringFormatterMessageFactory.INSTANCE);
   }

   public static Logger getFormatterLogger(final Object value) {
      return getLogger((Class)(value != null ? value.getClass() : StackLocatorUtil.getCallerClass(2)), (MessageFactory)StringFormatterMessageFactory.INSTANCE);
   }

   public static Logger getFormatterLogger(final String name) {
      return name == null ? getFormatterLogger(StackLocatorUtil.getCallerClass(2)) : getLogger((String)name, (MessageFactory)StringFormatterMessageFactory.INSTANCE);
   }

   private static Class callerClass(final Class clazz) {
      if (clazz != null) {
         return clazz;
      } else {
         Class<?> candidate = StackLocatorUtil.getCallerClass(3);
         if (candidate == null) {
            throw new UnsupportedOperationException("No class provided, and an appropriate one cannot be found.");
         } else {
            return candidate;
         }
      }
   }

   public static Logger getLogger() {
      return getLogger(StackLocatorUtil.getCallerClass(2));
   }

   public static Logger getLogger(final Class clazz) {
      Class<?> cls = callerClass(clazz);
      return getContext(cls.getClassLoader(), false).getLogger(cls);
   }

   public static Logger getLogger(final Class clazz, final MessageFactory messageFactory) {
      Class<?> cls = callerClass(clazz);
      return getContext(cls.getClassLoader(), false).getLogger(cls, messageFactory);
   }

   public static Logger getLogger(final MessageFactory messageFactory) {
      return getLogger(StackLocatorUtil.getCallerClass(2), messageFactory);
   }

   public static Logger getLogger(final Object value) {
      return getLogger(value != null ? value.getClass() : StackLocatorUtil.getCallerClass(2));
   }

   public static Logger getLogger(final Object value, final MessageFactory messageFactory) {
      return getLogger(value != null ? value.getClass() : StackLocatorUtil.getCallerClass(2), messageFactory);
   }

   public static Logger getLogger(final String name) {
      return (Logger)(name != null ? getContext(false).getLogger(name) : getLogger(StackLocatorUtil.getCallerClass(2)));
   }

   public static Logger getLogger(final String name, final MessageFactory messageFactory) {
      return (Logger)(name != null ? getContext(false).getLogger(name, messageFactory) : getLogger(StackLocatorUtil.getCallerClass(2), messageFactory));
   }

   protected static Logger getLogger(final String fqcn, final String name) {
      return factory.getContext(fqcn, (ClassLoader)null, (Object)null, false).getLogger(name);
   }

   public static Logger getRootLogger() {
      return getLogger("");
   }

   static {
      LogManagerStatus.setInitialized(true);
   }
}
