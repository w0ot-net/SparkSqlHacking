package org.apache.log4j;

import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.log4j.bridge.AppenderAdapter;
import org.apache.log4j.bridge.AppenderWrapper;
import org.apache.log4j.bridge.LogEventWrapper;
import org.apache.log4j.helpers.AppenderAttachableImpl;
import org.apache.log4j.helpers.NullEnumeration;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.legacy.core.CategoryUtil;
import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.HierarchyEventListener;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.LocalizedMessage;
import org.apache.logging.log4j.message.MapMessage;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.ObjectMessage;
import org.apache.logging.log4j.message.SimpleMessage;
import org.apache.logging.log4j.spi.ExtendedLogger;
import org.apache.logging.log4j.spi.LoggerContext;
import org.apache.logging.log4j.util.StackLocatorUtil;
import org.apache.logging.log4j.util.Strings;

public class Category implements AppenderAttachable {
   private static final String FQCN = Category.class.getName();
   protected String name;
   protected boolean additive;
   protected volatile Level level;
   protected volatile Category parent;
   protected ResourceBundle bundle;
   private final org.apache.logging.log4j.Logger logger;
   protected LoggerRepository repository;
   AppenderAttachableImpl aai;

   /** @deprecated */
   @Deprecated
   public static Logger exists(final String name) {
      return LogManager.exists(name, StackLocatorUtil.getCallerClassLoader(2));
   }

   /** @deprecated */
   @Deprecated
   public static Enumeration getCurrentCategories() {
      return LogManager.getCurrentLoggers(StackLocatorUtil.getCallerClassLoader(2));
   }

   /** @deprecated */
   @Deprecated
   public static LoggerRepository getDefaultHierarchy() {
      return LogManager.getLoggerRepository();
   }

   public static Category getInstance(final Class clazz) {
      return LogManager.getLogger(clazz.getName(), StackLocatorUtil.getCallerClassLoader(2));
   }

   public static Category getInstance(final String name) {
      return LogManager.getLogger(name, StackLocatorUtil.getCallerClassLoader(2));
   }

   public static Category getRoot() {
      return LogManager.getRootLogger(StackLocatorUtil.getCallerClassLoader(2));
   }

   private static String getSubName(final String name) {
      if (Strings.isEmpty(name)) {
         return null;
      } else {
         int i = name.lastIndexOf(46);
         return i > 0 ? name.substring(0, i) : "";
      }
   }

   public static void shutdown() {
      LogManager.shutdown(StackLocatorUtil.getCallerClassLoader(2));
   }

   protected Category(final LoggerContext context, final String name) {
      this.additive = true;
      this.name = name;
      this.logger = context.getLogger(name);
   }

   Category(final org.apache.logging.log4j.Logger logger) {
      this.additive = true;
      this.logger = logger;
   }

   protected Category(final String name) {
      this(Hierarchy.getContext(), name);
   }

   public void addAppender(final Appender appender) {
      if (appender != null) {
         if (LogManager.isLog4jCorePresent()) {
            CategoryUtil.addAppender(this.logger, AppenderAdapter.adapt(appender));
         } else {
            synchronized(this) {
               if (this.aai == null) {
                  this.aai = new AppenderAttachableImpl();
               }

               this.aai.addAppender(appender);
            }
         }

         this.repository.fireAddAppenderEvent(this, appender);
      }

   }

   public void assertLog(final boolean assertion, final String msg) {
      if (!assertion) {
         this.error(msg);
      }

   }

   public void callAppenders(final LoggingEvent event) {
      if (LogManager.isLog4jCorePresent()) {
         CategoryUtil.log(this.logger, new LogEventWrapper(event));
      } else {
         int writes = 0;

         for(Category c = this; c != null; c = c.parent) {
            synchronized(c) {
               if (c.aai != null) {
                  writes += c.aai.appendLoopOnAppenders(event);
               }

               if (!c.additive) {
                  break;
               }
            }
         }

         if (writes == 0) {
            this.repository.emitNoAppenderWarning(this);
         }

      }
   }

   synchronized void closeNestedAppenders() {
      Enumeration enumeration = this.getAllAppenders();
      if (enumeration != null) {
         while(enumeration.hasMoreElements()) {
            Appender a = (Appender)enumeration.nextElement();
            if (a instanceof AppenderAttachable) {
               a.close();
            }
         }
      }

   }

   public void debug(final Object message) {
      this.maybeLog(FQCN, org.apache.logging.log4j.Level.DEBUG, message, (Throwable)null);
   }

   public void debug(final Object message, final Throwable t) {
      this.maybeLog(FQCN, org.apache.logging.log4j.Level.DEBUG, message, t);
   }

   public void error(final Object message) {
      this.maybeLog(FQCN, org.apache.logging.log4j.Level.ERROR, message, (Throwable)null);
   }

   public void error(final Object message, final Throwable t) {
      this.maybeLog(FQCN, org.apache.logging.log4j.Level.ERROR, message, t);
   }

   public void fatal(final Object message) {
      this.maybeLog(FQCN, org.apache.logging.log4j.Level.FATAL, message, (Throwable)null);
   }

   public void fatal(final Object message, final Throwable t) {
      this.maybeLog(FQCN, org.apache.logging.log4j.Level.FATAL, message, t);
   }

   private void fireRemoveAppenderEvent(final Appender appender) {
      if (appender != null) {
         if (this.repository instanceof Hierarchy) {
            ((Hierarchy)this.repository).fireRemoveAppenderEvent(this, appender);
         } else if (this.repository instanceof HierarchyEventListener) {
            ((HierarchyEventListener)this.repository).removeAppenderEvent(this, appender);
         }
      }

   }

   private static Message createMessage(final Object message) {
      if (message instanceof String) {
         return new SimpleMessage((String)message);
      } else if (message instanceof CharSequence) {
         return new SimpleMessage((CharSequence)message);
      } else if (message instanceof Map) {
         return new MapMessage((Map)message);
      } else {
         return (Message)(message instanceof Message ? (Message)message : new ObjectMessage(message));
      }
   }

   public void forcedLog(final String fqcn, final Priority level, final Object message, final Throwable t) {
      org.apache.logging.log4j.Level lvl = level.getVersion2Level();
      Message msg = createMessage(message);
      if (this.logger instanceof ExtendedLogger) {
         ((ExtendedLogger)this.logger).logMessage(fqcn, lvl, (Marker)null, msg, t);
      } else {
         this.logger.log(lvl, msg, t);
      }

   }

   public boolean getAdditivity() {
      return LogManager.isLog4jCorePresent() ? CategoryUtil.isAdditive(this.logger) : false;
   }

   public Enumeration getAllAppenders() {
      if (LogManager.isLog4jCorePresent()) {
         Collection<org.apache.logging.log4j.core.Appender> appenders = CategoryUtil.getAppenders(this.logger).values();
         Stream var10000 = appenders.stream();
         Objects.requireNonNull(AppenderAdapter.Adapter.class);
         return Collections.enumeration((Collection)var10000.filter(AppenderAdapter.Adapter.class::isInstance).map(AppenderWrapper::adapt).collect(Collectors.toSet()));
      } else {
         return (Enumeration)(this.aai == null ? NullEnumeration.getInstance() : this.aai.getAllAppenders());
      }
   }

   public Appender getAppender(final String name) {
      if (LogManager.isLog4jCorePresent()) {
         return AppenderWrapper.adapt((org.apache.logging.log4j.core.Appender)CategoryUtil.getAppenders(this.logger).get(name));
      } else {
         return this.aai != null ? this.aai.getAppender(name) : null;
      }
   }

   public Priority getChainedPriority() {
      return this.getEffectiveLevel();
   }

   public Level getEffectiveLevel() {
      return OptionConverter.convertLevel(this.logger.getLevel());
   }

   /** @deprecated */
   @Deprecated
   public LoggerRepository getHierarchy() {
      return this.repository;
   }

   public final Level getLevel() {
      org.apache.logging.log4j.Level v2Level = CategoryUtil.getExplicitLevel(this.logger);
      return v2Level != null ? OptionConverter.convertLevel(v2Level) : null;
   }

   private String getLevelStr(final Priority priority) {
      return priority == null ? null : priority.levelStr;
   }

   org.apache.logging.log4j.Logger getLogger() {
      return this.logger;
   }

   public LoggerRepository getLoggerRepository() {
      return this.repository;
   }

   public final String getName() {
      return this.logger.getName();
   }

   public final Category getParent() {
      if (!LogManager.isLog4jCorePresent()) {
         return null;
      } else {
         org.apache.logging.log4j.Logger parent = CategoryUtil.getParent(this.logger);
         LoggerContext loggerContext = CategoryUtil.getLoggerContext(this.logger);
         if (parent != null && loggerContext != null) {
            ConcurrentMap<String, Logger> loggers = Hierarchy.getLoggersMap(loggerContext);
            Category parentLogger = (Category)loggers.get(parent.getName());
            if (parentLogger == null) {
               parentLogger = new Category(parent);
               parentLogger.setHierarchy(this.getLoggerRepository());
            }

            return parentLogger;
         } else {
            return null;
         }
      }
   }

   public final Level getPriority() {
      return this.getLevel();
   }

   public ResourceBundle getResourceBundle() {
      if (this.bundle != null) {
         return this.bundle;
      } else {
         String name = this.logger.getName();
         if (LogManager.isLog4jCorePresent()) {
            LoggerContext ctx = CategoryUtil.getLoggerContext(this.logger);
            if (ctx != null) {
               ConcurrentMap<String, Logger> loggers = Hierarchy.getLoggersMap(ctx);

               while((name = getSubName(name)) != null) {
                  Logger subLogger = (Logger)loggers.get(name);
                  if (subLogger != null) {
                     ResourceBundle rb = subLogger.bundle;
                     if (rb != null) {
                        return rb;
                     }
                  }
               }
            }
         }

         return null;
      }
   }

   public void info(final Object message) {
      this.maybeLog(FQCN, org.apache.logging.log4j.Level.INFO, message, (Throwable)null);
   }

   public void info(final Object message, final Throwable t) {
      this.maybeLog(FQCN, org.apache.logging.log4j.Level.INFO, message, t);
   }

   public boolean isAttached(final Appender appender) {
      return this.aai == null ? false : this.aai.isAttached(appender);
   }

   public boolean isDebugEnabled() {
      return this.logger.isDebugEnabled();
   }

   private boolean isEnabledFor(final org.apache.logging.log4j.Level level) {
      return this.logger.isEnabled(level);
   }

   public boolean isEnabledFor(final Priority level) {
      return this.isEnabledFor(level.getVersion2Level());
   }

   public boolean isErrorEnabled() {
      return this.logger.isErrorEnabled();
   }

   public boolean isFatalEnabled() {
      return this.logger.isFatalEnabled();
   }

   public boolean isInfoEnabled() {
      return this.logger.isInfoEnabled();
   }

   public boolean isWarnEnabled() {
      return this.logger.isWarnEnabled();
   }

   public void l7dlog(final Priority priority, final String key, final Object[] params, final Throwable t) {
      if (this.isEnabledFor(priority)) {
         Message msg = new LocalizedMessage(this.bundle, key, params);
         this.forcedLog(FQCN, priority, msg, t);
      }

   }

   public void l7dlog(final Priority priority, final String key, final Throwable t) {
      if (this.isEnabledFor(priority)) {
         Message msg = new LocalizedMessage(this.bundle, key, (Object[])null);
         this.forcedLog(FQCN, priority, msg, t);
      }

   }

   public void log(final Priority priority, final Object message) {
      if (this.isEnabledFor(priority)) {
         this.forcedLog(FQCN, priority, message, (Throwable)null);
      }

   }

   public void log(final Priority priority, final Object message, final Throwable t) {
      if (this.isEnabledFor(priority)) {
         this.forcedLog(FQCN, priority, message, t);
      }

   }

   public void log(final String fqcn, final Priority priority, final Object message, final Throwable t) {
      if (this.isEnabledFor(priority)) {
         this.forcedLog(fqcn, priority, message, t);
      }

   }

   void maybeLog(final String fqcn, final org.apache.logging.log4j.Level level, final Object message, final Throwable throwable) {
      if (this.logger.isEnabled(level)) {
         Message msg = createMessage(message);
         if (this.logger instanceof ExtendedLogger) {
            ((ExtendedLogger)this.logger).logMessage(fqcn, level, (Marker)null, msg, throwable);
         } else {
            this.logger.log(level, msg, throwable);
         }
      }

   }

   public void removeAllAppenders() {
      if (this.aai != null) {
         Vector appenders = new Vector();
         Enumeration iter = this.aai.getAllAppenders();

         while(iter != null && iter.hasMoreElements()) {
            appenders.add(iter.nextElement());
         }

         this.aai.removeAllAppenders();

         for(Object appender : appenders) {
            this.fireRemoveAppenderEvent((Appender)appender);
         }

         this.aai = null;
      }

   }

   public void removeAppender(final Appender appender) {
      if (appender != null && this.aai != null) {
         boolean wasAttached = this.aai.isAttached(appender);
         this.aai.removeAppender(appender);
         if (wasAttached) {
            this.fireRemoveAppenderEvent(appender);
         }

      }
   }

   public void removeAppender(final String name) {
      if (name != null && this.aai != null) {
         Appender appender = this.aai.getAppender(name);
         this.aai.removeAppender(name);
         if (appender != null) {
            this.fireRemoveAppenderEvent(appender);
         }

      }
   }

   public void setAdditivity(final boolean additivity) {
      if (LogManager.isLog4jCorePresent()) {
         CategoryUtil.setAdditivity(this.logger, additivity);
      }

   }

   final void setHierarchy(final LoggerRepository repository) {
      this.repository = repository;
   }

   public void setLevel(final Level level) {
      this.setLevel(level != null ? level.getVersion2Level() : null);
   }

   private void setLevel(final org.apache.logging.log4j.Level level) {
      if (LogManager.isLog4jCorePresent()) {
         CategoryUtil.setLevel(this.logger, level);
      }

   }

   public void setPriority(final Priority priority) {
      this.setLevel(priority != null ? priority.getVersion2Level() : null);
   }

   public void setResourceBundle(final ResourceBundle bundle) {
      this.bundle = bundle;
   }

   public void warn(final Object message) {
      this.maybeLog(FQCN, org.apache.logging.log4j.Level.WARN, message, (Throwable)null);
   }

   public void warn(final Object message, final Throwable t) {
      this.maybeLog(FQCN, org.apache.logging.log4j.Level.WARN, message, t);
   }
}
