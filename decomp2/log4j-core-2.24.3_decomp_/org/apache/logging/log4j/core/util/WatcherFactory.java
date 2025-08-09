package org.apache.logging.log4j.core.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFileWatcher;
import org.apache.logging.log4j.core.config.ConfigurationListener;
import org.apache.logging.log4j.core.config.Reconfigurable;
import org.apache.logging.log4j.core.config.plugins.util.PluginManager;
import org.apache.logging.log4j.core.config.plugins.util.PluginType;
import org.apache.logging.log4j.status.StatusLogger;

public class WatcherFactory {
   private static final Logger LOGGER = StatusLogger.getLogger();
   private static final PluginManager pluginManager = new PluginManager("Watcher");
   private static volatile WatcherFactory factory;
   private final Map plugins;

   private WatcherFactory(final List packages) {
      pluginManager.collectPlugins(packages);
      this.plugins = pluginManager.getPlugins();
   }

   public static WatcherFactory getInstance(final List packages) {
      if (factory == null) {
         synchronized(pluginManager) {
            if (factory == null) {
               factory = new WatcherFactory(packages);
            }
         }
      }

      return factory;
   }

   public Watcher newWatcher(final Source source, final Configuration configuration, final Reconfigurable reconfigurable, final List configurationListeners, final long lastModifiedMillis) {
      if (source.getFile() != null) {
         return new ConfigurationFileWatcher(configuration, reconfigurable, configurationListeners, lastModifiedMillis);
      } else {
         String name = source.getURI().getScheme();
         PluginType<?> pluginType = (PluginType)this.plugins.get(name);
         if (pluginType != null) {
            return instantiate(name, pluginType.getPluginClass(), configuration, reconfigurable, configurationListeners, lastModifiedMillis);
         } else {
            LOGGER.info("No Watcher plugin is available for protocol '{}'", name);
            return null;
         }
      }
   }

   public static Watcher instantiate(final String name, final Class clazz, final Configuration configuration, final Reconfigurable reconfigurable, final List listeners, final long lastModifiedMillis) {
      Objects.requireNonNull(clazz, "No class provided");

      try {
         Constructor<T> constructor = clazz.getConstructor(Configuration.class, Reconfigurable.class, List.class, Long.TYPE);
         return (Watcher)constructor.newInstance(configuration, reconfigurable, listeners, lastModifiedMillis);
      } catch (NoSuchMethodException ex) {
         throw new IllegalArgumentException("No valid constructor for Watcher plugin " + name, ex);
      } catch (InstantiationException | LinkageError e) {
         throw new IllegalArgumentException(e);
      } catch (IllegalAccessException e) {
         throw new IllegalStateException(e);
      } catch (InvocationTargetException e) {
         Throwables.rethrow(e.getCause());
         throw new InternalError("Unreachable");
      }
   }
}
