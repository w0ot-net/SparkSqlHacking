package org.apache.log4j;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.log4j.bridge.FilterAdapter;
import org.apache.log4j.config.Log4j1Configuration;
import org.apache.log4j.config.PropertiesConfiguration;
import org.apache.log4j.config.PropertySetter;
import org.apache.log4j.helpers.FileWatchdog;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.or.RendererMap;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.OptionHandler;
import org.apache.log4j.spi.RendererSupport;
import org.apache.log4j.spi.ThrowableRenderer;
import org.apache.log4j.spi.ThrowableRendererSupport;
import org.apache.logging.log4j.core.net.UrlConnectionFactory;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.StackLocatorUtil;

public class PropertyConfigurator implements Configurator {
   private static final String CATEGORY_PREFIX = "log4j.category.";
   private static final String LOGGER_PREFIX = "log4j.logger.";
   private static final String FACTORY_PREFIX = "log4j.factory";
   private static final String ADDITIVITY_PREFIX = "log4j.additivity.";
   private static final String APPENDER_PREFIX = "log4j.appender.";
   private static final String RENDERER_PREFIX = "log4j.renderer.";
   private static final String THROWABLE_RENDERER_PREFIX = "log4j.throwableRenderer";
   private static final String LOGGER_REF = "logger-ref";
   private static final String ROOT_REF = "root-ref";
   private static final String APPENDER_REF_TAG = "appender-ref";
   public static final String LOGGER_FACTORY_KEY = "log4j.loggerFactory";
   private static final String RESET_KEY = "log4j.reset";
   private static final String INTERNAL_ROOT_NAME = "root";
   protected Hashtable registry = new Hashtable(11);
   private LoggerRepository repository;
   protected LoggerFactory loggerFactory = new DefaultCategoryFactory();

   private static boolean isFullCompatibilityEnabled() {
      return PropertiesUtil.getProperties().getBooleanProperty("log4j1.compatibility");
   }

   private static void warnFullCompatibilityDisabled() {
      LogLog.warn("Ignoring `PropertyConfigurator` call, since `log4j1.compatibility` is not enabled.\nSee https://logging.staged.apache.org/log4j/2.x/migrate-from-log4j1.html#log4j1.compatibility for details.");
   }

   public static void configure(final InputStream inputStream) {
      (new PropertyConfigurator()).doConfigure(inputStream, LogManager.getLoggerRepository(), StackLocatorUtil.getCallerClassLoader(2));
   }

   public static void configure(final Properties properties) {
      (new PropertyConfigurator()).doConfigure(properties, LogManager.getLoggerRepository(), StackLocatorUtil.getCallerClassLoader(2));
   }

   public static void configure(final String fileName) {
      (new PropertyConfigurator()).doConfigure(fileName, LogManager.getLoggerRepository(), StackLocatorUtil.getCallerClassLoader(2));
   }

   public static void configure(final URL configURL) {
      (new PropertyConfigurator()).doConfigure(configURL, LogManager.getLoggerRepository(), StackLocatorUtil.getCallerClassLoader(2));
   }

   public static void configureAndWatch(final String configFilename) {
      configureAndWatch(configFilename, 60000L, StackLocatorUtil.getCallerClassLoader(2));
   }

   public static void configureAndWatch(final String configFilename, final long delayMillis) {
      configureAndWatch(configFilename, delayMillis, StackLocatorUtil.getCallerClassLoader(2));
   }

   private static void configureAndWatch(final String configFilename, final long delay, final ClassLoader classLoader) {
      if (isFullCompatibilityEnabled()) {
         PropertyWatchdog watchdog = new PropertyWatchdog(configFilename, classLoader);
         watchdog.setDelay(delay);
         watchdog.start();
      } else {
         warnFullCompatibilityDisabled();
      }

   }

   protected void configureLoggerFactory(final Properties properties) {
      if (isFullCompatibilityEnabled()) {
         String factoryClassName = OptionConverter.findAndSubst("log4j.loggerFactory", properties);
         if (factoryClassName != null) {
            LogLog.debug("Setting category factory to [" + factoryClassName + "].");
            this.loggerFactory = (LoggerFactory)OptionConverter.instantiateByClassName(factoryClassName, LoggerFactory.class, this.loggerFactory);
            PropertySetter.setProperties(this.loggerFactory, properties, "log4j.factory.");
         }
      } else {
         warnFullCompatibilityDisabled();
      }

   }

   public void doConfigure(final InputStream inputStream, final LoggerRepository loggerRepository) {
      this.doConfigure(inputStream, loggerRepository, StackLocatorUtil.getCallerClassLoader(2));
   }

   private void doConfigure(final InputStream inputStream, final LoggerRepository loggerRepository, final ClassLoader classLoader) {
      this.doConfigure(this.loadProperties(inputStream), loggerRepository, classLoader);
   }

   public void doConfigure(final Properties properties, final LoggerRepository loggerRepository) {
      this.doConfigure(properties, loggerRepository, StackLocatorUtil.getCallerClassLoader(2));
   }

   private void doConfigure(final Properties properties, final LoggerRepository loggerRepository, final ClassLoader classLoader) {
      if (isFullCompatibilityEnabled()) {
         PropertiesConfiguration configuration = new PropertiesConfiguration(LogManager.getContext(classLoader), properties);
         configuration.doConfigure();
         this.repository = loggerRepository;
         this.registry.clear();
         org.apache.logging.log4j.core.config.Configurator.reconfigure(configuration);
      } else {
         warnFullCompatibilityDisabled();
      }

   }

   public void doConfigure(final String fileName, final LoggerRepository loggerRepository) {
      this.doConfigure(fileName, loggerRepository, StackLocatorUtil.getCallerClassLoader(2));
   }

   @SuppressFBWarnings(
      value = {"PATH_TRAVERSAL_IN"},
      justification = "The filename comes from a system property."
   )
   private void doConfigure(final String fileName, final LoggerRepository loggerRepository, final ClassLoader classLoader) {
      if (isFullCompatibilityEnabled()) {
         try {
            InputStream inputStream = Files.newInputStream(Paths.get(fileName));

            try {
               this.doConfigure(inputStream, loggerRepository, classLoader);
            } catch (Throwable var8) {
               if (inputStream != null) {
                  try {
                     inputStream.close();
                  } catch (Throwable var7) {
                     var8.addSuppressed(var7);
                  }
               }

               throw var8;
            }

            if (inputStream != null) {
               inputStream.close();
            }
         } catch (Exception e) {
            if (e instanceof InterruptedIOException) {
               Thread.currentThread().interrupt();
            }

            LogLog.error("Could not read configuration file [" + fileName + "].", e);
            LogLog.error("Ignoring configuration file [" + fileName + "].");
         }
      } else {
         warnFullCompatibilityDisabled();
      }

   }

   public void doConfigure(final URL url, final LoggerRepository loggerRepository) {
      this.doConfigure(url, loggerRepository, StackLocatorUtil.getCallerClassLoader(2));
   }

   private void doConfigure(final URL url, final LoggerRepository loggerRepository, final ClassLoader classLoader) {
      if (isFullCompatibilityEnabled()) {
         LogLog.debug("Reading configuration from URL " + url);

         try {
            URLConnection urlConnection = UrlConnectionFactory.createConnection(url);
            InputStream inputStream = urlConnection.getInputStream();

            try {
               this.doConfigure(inputStream, loggerRepository, classLoader);
            } catch (Throwable var9) {
               if (inputStream != null) {
                  try {
                     inputStream.close();
                  } catch (Throwable var8) {
                     var9.addSuppressed(var8);
                  }
               }

               throw var9;
            }

            if (inputStream != null) {
               inputStream.close();
            }
         } catch (IOException e) {
            LogLog.error("Could not read configuration file from URL [" + url + "].", e);
            LogLog.error("Ignoring configuration file [" + url + "].");
         }
      } else {
         warnFullCompatibilityDisabled();
      }

   }

   private Properties loadProperties(final InputStream inputStream) {
      Properties loaded = new Properties();

      try {
         loaded.load(inputStream);
         return loaded;
      } catch (IllegalArgumentException | IOException e) {
         if (e instanceof InterruptedIOException) {
            Thread.currentThread().interrupt();
         }

         LogLog.error("Could not read configuration file from InputStream [" + inputStream + "].", e);
         LogLog.error("Ignoring configuration InputStream [" + inputStream + "].");
         return null;
      }
   }

   private void parseAdditivityForLogger(final Properties properties, final Logger logger, final String loggerName) {
      String value = OptionConverter.findAndSubst("log4j.additivity." + loggerName, properties);
      LogLog.debug("Handling log4j.additivity." + loggerName + "=[" + value + "]");
      if (value != null && !value.isEmpty()) {
         boolean additivity = OptionConverter.toBoolean(value, true);
         LogLog.debug("Setting additivity for \"" + loggerName + "\" to " + additivity);
         logger.setAdditivity(additivity);
      }

   }

   private Appender parseAppender(final Properties properties, final String appenderName) {
      Appender appender = (Appender)this.registry.get(appenderName);
      if (appender != null) {
         LogLog.debug("Appender \"" + appenderName + "\" was already parsed.");
         return appender;
      } else {
         String prefix = "log4j.appender." + appenderName;
         String layoutPrefix = prefix + ".layout";
         appender = (Appender)OptionConverter.instantiateByKey(properties, prefix, Appender.class, (Object)null);
         if (appender == null) {
            LogLog.error("Could not instantiate appender named \"" + appenderName + "\".");
            return null;
         } else {
            appender.setName(appenderName);
            if (appender instanceof OptionHandler) {
               if (appender.requiresLayout()) {
                  Layout layout = (Layout)OptionConverter.instantiateByKey(properties, layoutPrefix, Layout.class, (Object)null);
                  if (layout != null) {
                     appender.setLayout(layout);
                     LogLog.debug("Parsing layout options for \"" + appenderName + "\".");
                     PropertySetter.setProperties(layout, properties, layoutPrefix + ".");
                     LogLog.debug("End of parsing for \"" + appenderName + "\".");
                  }
               }

               String errorHandlerPrefix = prefix + ".errorhandler";
               String errorHandlerClass = OptionConverter.findAndSubst(errorHandlerPrefix, properties);
               if (errorHandlerClass != null) {
                  ErrorHandler eh = (ErrorHandler)OptionConverter.instantiateByKey(properties, errorHandlerPrefix, ErrorHandler.class, (Object)null);
                  if (eh != null) {
                     appender.setErrorHandler(eh);
                     LogLog.debug("Parsing errorhandler options for \"" + appenderName + "\".");
                     this.parseErrorHandler(eh, errorHandlerPrefix, properties, this.repository);
                     Properties edited = new Properties();
                     String[] keys = new String[]{errorHandlerPrefix + "." + "root-ref", errorHandlerPrefix + "." + "logger-ref", errorHandlerPrefix + "." + "appender-ref"};

                     for(Object element : properties.entrySet()) {
                        Map.Entry entry = (Map.Entry)element;

                        int i;
                        for(i = 0; i < keys.length && !keys[i].equals(entry.getKey()); ++i) {
                        }

                        if (i == keys.length) {
                           edited.put(entry.getKey(), entry.getValue());
                        }
                     }

                     PropertySetter.setProperties(eh, edited, errorHandlerPrefix + ".");
                     LogLog.debug("End of errorhandler parsing for \"" + appenderName + "\".");
                  }
               }

               PropertySetter.setProperties(appender, properties, prefix + ".");
               LogLog.debug("Parsed \"" + appenderName + "\" options.");
            }

            this.parseAppenderFilters(properties, appenderName, appender);
            this.registry.put(appender.getName(), appender);
            return appender;
         }
      }
   }

   private void parseAppenderFilters(final Properties properties, final String appenderName, final Appender appender) {
      String filterPrefix = "log4j.appender." + appenderName + ".filter.";
      int fIdx = filterPrefix.length();
      Hashtable filters = new Hashtable();
      Enumeration e = properties.keys();
      String name = "";

      while(e.hasMoreElements()) {
         String key = (String)e.nextElement();
         if (key.startsWith(filterPrefix)) {
            int dotIdx = key.indexOf(46, fIdx);
            String filterKey = key;
            if (dotIdx != -1) {
               filterKey = key.substring(0, dotIdx);
               name = key.substring(dotIdx + 1);
            }

            Vector filterOpts = (Vector)filters.get(filterKey);
            if (filterOpts == null) {
               filterOpts = new Vector();
               filters.put(filterKey, filterOpts);
            }

            if (dotIdx != -1) {
               String value = OptionConverter.findAndSubst(key, properties);
               filterOpts.add(new NameValue(name, value));
            }
         }
      }

      Enumeration g = new SortedKeyEnumeration(filters);
      Filter head = null;

      while(g.hasMoreElements()) {
         String key = (String)g.nextElement();
         String clazz = properties.getProperty(key);
         if (clazz != null) {
            LogLog.debug("Filter key: [" + key + "] class: [" + properties.getProperty(key) + "] props: " + filters.get(key));
            Filter filter = (Filter)OptionConverter.instantiateByClassName(clazz, Filter.class, (Object)null);
            if (filter != null) {
               PropertySetter propSetter = new PropertySetter(filter);
               Vector v = (Vector)filters.get(key);
               Enumeration filterProps = v.elements();

               while(filterProps.hasMoreElements()) {
                  NameValue kv = (NameValue)filterProps.nextElement();
                  propSetter.setProperty(kv.key, kv.value);
               }

               propSetter.activate();
               LogLog.debug("Adding filter of type [" + filter.getClass() + "] to appender named [" + appender.getName() + "].");
               head = FilterAdapter.addFilter(head, filter);
            }
         } else {
            LogLog.warn("Missing class definition for filter: [" + key + "]");
         }
      }

      appender.addFilter(head);
   }

   private void parseCategory(final Properties properties, final Logger logger, final String optionKey, final String loggerName, final String value) {
      LogLog.debug("Parsing for [" + loggerName + "] with value=[" + value + "].");
      StringTokenizer st = new StringTokenizer(value, ",");
      if (!value.startsWith(",") && !value.isEmpty()) {
         if (!st.hasMoreTokens()) {
            return;
         }

         String levelStr = st.nextToken();
         LogLog.debug("Level token is [" + levelStr + "].");
         if (!"inherited".equalsIgnoreCase(levelStr) && !"null".equalsIgnoreCase(levelStr)) {
            logger.setLevel(OptionConverter.toLevel(levelStr, Log4j1Configuration.DEFAULT_LEVEL));
         } else if (loggerName.equals("root")) {
            LogLog.warn("The root logger cannot be set to null.");
         } else {
            logger.setLevel((Level)null);
         }

         LogLog.debug("Category " + loggerName + " set to " + logger.getLevel());
      }

      logger.removeAllAppenders();

      while(st.hasMoreTokens()) {
         String appenderName = st.nextToken().trim();
         if (appenderName != null && !appenderName.equals(",")) {
            LogLog.debug("Parsing appender named \"" + appenderName + "\".");
            Appender appender = this.parseAppender(properties, appenderName);
            if (appender != null) {
               logger.addAppender(appender);
            }
         }
      }

   }

   protected void parseCatsAndRenderers(final Properties properties, final LoggerRepository loggerRepository) {
      if (!isFullCompatibilityEnabled()) {
         warnFullCompatibilityDisabled();
      } else {
         Enumeration enumeration = properties.propertyNames();

         while(enumeration.hasMoreElements()) {
            String key = (String)enumeration.nextElement();
            if (!key.startsWith("log4j.category.") && !key.startsWith("log4j.logger.")) {
               if (key.startsWith("log4j.renderer.")) {
                  String renderedClass = key.substring("log4j.renderer.".length());
                  String renderingClass = OptionConverter.findAndSubst(key, properties);
                  if (loggerRepository instanceof RendererSupport) {
                     RendererMap.addRenderer((RendererSupport)loggerRepository, renderedClass, renderingClass);
                  }
               } else if (key.equals("log4j.throwableRenderer") && loggerRepository instanceof ThrowableRendererSupport) {
                  ThrowableRenderer tr = (ThrowableRenderer)OptionConverter.instantiateByKey(properties, "log4j.throwableRenderer", ThrowableRenderer.class, (Object)null);
                  if (tr == null) {
                     LogLog.error("Could not instantiate throwableRenderer.");
                  } else {
                     PropertySetter setter = new PropertySetter(tr);
                     setter.setProperties(properties, "log4j.throwableRenderer.");
                     ((ThrowableRendererSupport)loggerRepository).setThrowableRenderer(tr);
                  }
               }
            } else {
               String loggerName = null;
               if (key.startsWith("log4j.category.")) {
                  loggerName = key.substring("log4j.category.".length());
               } else if (key.startsWith("log4j.logger.")) {
                  loggerName = key.substring("log4j.logger.".length());
               }

               String value = OptionConverter.findAndSubst(key, properties);
               Logger logger = loggerRepository.getLogger(loggerName, this.loggerFactory);
               synchronized(logger) {
                  this.parseCategory(properties, logger, key, loggerName, value);
                  this.parseAdditivityForLogger(properties, logger, loggerName);
               }
            }
         }

      }
   }

   private void parseErrorHandler(final ErrorHandler errorHandler, final String errorHandlerPrefix, final Properties props, final LoggerRepository loggerRepository) {
      if (errorHandler != null && loggerRepository != null) {
         boolean rootRef = OptionConverter.toBoolean(OptionConverter.findAndSubst(errorHandlerPrefix + "root-ref", props), false);
         if (rootRef) {
            errorHandler.setLogger(loggerRepository.getRootLogger());
         }

         String loggerName = OptionConverter.findAndSubst(errorHandlerPrefix + "logger-ref", props);
         if (loggerName != null) {
            Logger logger = this.loggerFactory == null ? loggerRepository.getLogger(loggerName) : loggerRepository.getLogger(loggerName, this.loggerFactory);
            errorHandler.setLogger(logger);
         }

         String appenderName = OptionConverter.findAndSubst(errorHandlerPrefix + "appender-ref", props);
         if (appenderName != null) {
            Appender backup = this.parseAppender(props, appenderName);
            if (backup != null) {
               errorHandler.setBackupAppender(backup);
            }
         }
      }

   }

   static class NameValue {
      String key;
      String value;

      public NameValue(final String key, final String value) {
         this.key = key;
         this.value = value;
      }

      public String toString() {
         return this.key + "=" + this.value;
      }
   }

   static class PropertyWatchdog extends FileWatchdog {
      private final ClassLoader classLoader;

      PropertyWatchdog(final String fileName, final ClassLoader classLoader) {
         super(fileName);
         this.classLoader = classLoader;
      }

      public void doOnChange() {
         (new PropertyConfigurator()).doConfigure(this.filename, LogManager.getLoggerRepository(), this.classLoader);
      }
   }

   class SortedKeyEnumeration implements Enumeration {
      private final Enumeration e;

      public SortedKeyEnumeration(final Hashtable ht) {
         Enumeration f = ht.keys();
         Vector keys = new Vector(ht.size());

         for(int last = 0; f.hasMoreElements(); ++last) {
            String key = (String)f.nextElement();

            int i;
            for(i = 0; i < last; ++i) {
               String s = (String)keys.get(i);
               if (key.compareTo(s) <= 0) {
                  break;
               }
            }

            keys.add(i, key);
         }

         this.e = keys.elements();
      }

      public boolean hasMoreElements() {
         return this.e.hasMoreElements();
      }

      public Object nextElement() {
         return this.e.nextElement();
      }
   }
}
