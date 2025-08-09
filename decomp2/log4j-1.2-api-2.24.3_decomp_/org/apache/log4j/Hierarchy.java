package org.apache.log4j;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.legacy.core.ContextUtil;
import org.apache.log4j.or.ObjectRenderer;
import org.apache.log4j.or.RendererMap;
import org.apache.log4j.spi.HierarchyEventListener;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.RendererSupport;
import org.apache.log4j.spi.ThrowableRenderer;
import org.apache.log4j.spi.ThrowableRendererSupport;
import org.apache.logging.log4j.spi.AbstractLoggerAdapter;
import org.apache.logging.log4j.spi.LoggerContext;
import org.apache.logging.log4j.util.StackLocatorUtil;

public class Hierarchy implements LoggerRepository, RendererSupport, ThrowableRendererSupport {
   private static final PrivateLoggerAdapter LOGGER_ADAPTER = new PrivateLoggerAdapter();
   private static final WeakHashMap CONTEXT_MAP = new WeakHashMap();
   private final LoggerFactory defaultFactory;
   private final Vector listeners = new Vector(1);
   Hashtable ht = new Hashtable();
   Logger root;
   RendererMap rendererMap;
   int thresholdInt;
   Level threshold;
   boolean emittedNoAppenderWarning;
   boolean emittedNoResourceBundleWarning;
   private ThrowableRenderer throwableRenderer;

   static LoggerContext getContext() {
      return Hierarchy.PrivateLogManager.getContext();
   }

   private Logger getInstance(final LoggerContext context, final String name) {
      return this.getInstance(context, name, LOGGER_ADAPTER);
   }

   private Logger getInstance(final LoggerContext context, final String name, final LoggerFactory factory) {
      return (Logger)getLoggersMap(context).computeIfAbsent(name, (k) -> {
         Logger logger = factory.makeNewLoggerInstance(name);
         logger.setHierarchy(this);
         return logger;
      });
   }

   private Logger getInstance(final LoggerContext context, final String name, final PrivateLoggerAdapter factory) {
      return (Logger)getLoggersMap(context).computeIfAbsent(name, (k) -> {
         Logger logger = factory.newLogger(name, context);
         logger.setHierarchy(this);
         return logger;
      });
   }

   static ConcurrentMap getLoggersMap(final LoggerContext context) {
      synchronized(CONTEXT_MAP) {
         return (ConcurrentMap)CONTEXT_MAP.computeIfAbsent(context, (k) -> new ConcurrentHashMap());
      }
   }

   public Hierarchy(final Logger root) {
      this.root = root;
      this.setThreshold(Level.ALL);
      this.root.setHierarchy(this);
      this.rendererMap = new RendererMap();
      this.defaultFactory = new DefaultCategoryFactory();
   }

   public void addHierarchyEventListener(final HierarchyEventListener listener) {
      if (this.listeners.contains(listener)) {
         LogLog.warn("Ignoring attempt to add an existent listener.");
      } else {
         this.listeners.addElement(listener);
      }

   }

   public void addRenderer(final Class classToRender, final ObjectRenderer or) {
      this.rendererMap.put(classToRender, or);
   }

   public void clear() {
      this.ht.clear();
      getLoggersMap(getContext()).clear();
   }

   public void emitNoAppenderWarning(final Category cat) {
      if (!this.emittedNoAppenderWarning) {
         LogLog.warn("No appenders could be found for logger (" + cat.getName() + ").");
         LogLog.warn("Please initialize the log4j system properly.");
         LogLog.warn("See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.");
         this.emittedNoAppenderWarning = true;
      }

   }

   public Logger exists(final String name) {
      return this.exists(name, getContext());
   }

   Logger exists(final String name, final ClassLoader classLoader) {
      return this.exists(name, this.getContext(classLoader));
   }

   Logger exists(final String name, final LoggerContext loggerContext) {
      return !loggerContext.hasLogger(name) ? null : Logger.getLogger(name);
   }

   public void fireAddAppenderEvent(final Category logger, final Appender appender) {
      if (this.listeners != null) {
         int size = this.listeners.size();

         for(int i = 0; i < size; ++i) {
            HierarchyEventListener listener = (HierarchyEventListener)this.listeners.elementAt(i);
            listener.addAppenderEvent(logger, appender);
         }
      }

   }

   void fireRemoveAppenderEvent(final Category logger, final Appender appender) {
      if (this.listeners != null) {
         int size = this.listeners.size();

         for(int i = 0; i < size; ++i) {
            HierarchyEventListener listener = (HierarchyEventListener)this.listeners.elementAt(i);
            listener.removeAppenderEvent(logger, appender);
         }
      }

   }

   LoggerContext getContext(final ClassLoader classLoader) {
      return LogManager.getContext(classLoader);
   }

   /** @deprecated */
   @Deprecated
   public Enumeration getCurrentCategories() {
      return this.getCurrentLoggers();
   }

   public Enumeration getCurrentLoggers() {
      return LogManager.getCurrentLoggers(StackLocatorUtil.getCallerClassLoader(2));
   }

   public Logger getLogger(final String name) {
      return this.getInstance(getContext(), name);
   }

   Logger getLogger(final String name, final ClassLoader classLoader) {
      return this.getInstance(this.getContext(classLoader), name);
   }

   public Logger getLogger(final String name, final LoggerFactory factory) {
      return this.getInstance(getContext(), name, factory);
   }

   Logger getLogger(final String name, final LoggerFactory factory, final ClassLoader classLoader) {
      return this.getInstance(this.getContext(classLoader), name, factory);
   }

   public RendererMap getRendererMap() {
      return this.rendererMap;
   }

   public Logger getRootLogger() {
      return this.getInstance(getContext(), "");
   }

   Logger getRootLogger(final ClassLoader classLoader) {
      return this.getInstance(this.getContext(classLoader), "");
   }

   public Level getThreshold() {
      return this.threshold;
   }

   public ThrowableRenderer getThrowableRenderer() {
      return this.throwableRenderer;
   }

   public boolean isDisabled(final int level) {
      return this.thresholdInt > level;
   }

   /** @deprecated */
   @Deprecated
   public void overrideAsNeeded(final String override) {
      LogLog.warn("The Hiearchy.overrideAsNeeded method has been deprecated.");
   }

   public void resetConfiguration() {
      this.resetConfiguration(getContext());
   }

   void resetConfiguration(final ClassLoader classLoader) {
      this.resetConfiguration(this.getContext(classLoader));
   }

   void resetConfiguration(final LoggerContext loggerContext) {
      getLoggersMap(loggerContext).clear();
      this.getRootLogger().setLevel(Level.DEBUG);
      this.root.setResourceBundle((ResourceBundle)null);
      this.setThreshold(Level.ALL);
      synchronized(this.ht) {
         this.shutdown();
         Enumeration cats = this.getCurrentLoggers();

         while(cats.hasMoreElements()) {
            Logger c = (Logger)cats.nextElement();
            c.setLevel((Level)null);
            c.setAdditivity(true);
            c.setResourceBundle((ResourceBundle)null);
         }
      }

      this.rendererMap.clear();
      this.throwableRenderer = null;
   }

   /** @deprecated */
   @Deprecated
   public void setDisableOverride(final String override) {
      LogLog.warn("The Hiearchy.setDisableOverride method has been deprecated.");
   }

   public void setRenderer(final Class renderedClass, final ObjectRenderer renderer) {
      this.rendererMap.put(renderedClass, renderer);
   }

   public void setThreshold(final Level level) {
      if (level != null) {
         this.thresholdInt = level.level;
         this.threshold = level;
      }

   }

   public void setThreshold(final String levelStr) {
      Level level = OptionConverter.toLevel(levelStr, (Level)null);
      if (level != null) {
         this.setThreshold(level);
      } else {
         LogLog.warn("Could not convert [" + levelStr + "] to Level.");
      }

   }

   public void setThrowableRenderer(final ThrowableRenderer throwableRenderer) {
      this.throwableRenderer = throwableRenderer;
   }

   public void shutdown() {
      this.shutdown(getContext());
   }

   public void shutdown(final ClassLoader classLoader) {
      this.shutdown(org.apache.logging.log4j.LogManager.getContext(classLoader, false));
   }

   void shutdown(final LoggerContext context) {
      getLoggersMap(context).clear();
      if (LogManager.isLog4jCorePresent()) {
         ContextUtil.shutdown(context);
      }

   }

   private static class PrivateLoggerAdapter extends AbstractLoggerAdapter {
      private PrivateLoggerAdapter() {
      }

      protected LoggerContext getContext() {
         return Hierarchy.PrivateLogManager.getContext();
      }

      protected Logger newLogger(final String name, final LoggerContext context) {
         return new Logger(context, name);
      }
   }

   private static class PrivateLogManager extends org.apache.logging.log4j.LogManager {
      private static final String FQCN = Hierarchy.class.getName();

      public static LoggerContext getContext() {
         return getContext(FQCN, false);
      }

      public static org.apache.logging.log4j.Logger getLogger(final String name) {
         return getLogger(FQCN, name);
      }
   }
}
