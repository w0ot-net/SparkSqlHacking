package org.apache.logging.log4j.core.config.plugins.util;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAliases;
import org.apache.logging.log4j.core.config.plugins.processor.PluginCache;
import org.apache.logging.log4j.core.config.plugins.processor.PluginEntry;
import org.apache.logging.log4j.core.util.Loader;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.Strings;

public class PluginRegistry {
   private static final Logger LOGGER = StatusLogger.getLogger();
   private static volatile PluginRegistry INSTANCE;
   private static final Object INSTANCE_LOCK = new Object();
   private final AtomicReference pluginsByCategoryRef = new AtomicReference();
   private final ConcurrentMap pluginsByCategoryByBundleId = new ConcurrentHashMap();
   private final ConcurrentMap pluginsByCategoryByPackage = new ConcurrentHashMap();

   private PluginRegistry() {
   }

   public static PluginRegistry getInstance() {
      PluginRegistry result = INSTANCE;
      if (result == null) {
         synchronized(INSTANCE_LOCK) {
            result = INSTANCE;
            if (result == null) {
               INSTANCE = result = new PluginRegistry();
            }
         }
      }

      return result;
   }

   public void clear() {
      this.pluginsByCategoryRef.set((Object)null);
      this.pluginsByCategoryByPackage.clear();
      this.pluginsByCategoryByBundleId.clear();
   }

   public Map getPluginsByCategoryByBundleId() {
      return this.pluginsByCategoryByBundleId;
   }

   public Map loadFromMainClassLoader() {
      Map<String, List<PluginType<?>>> existing = (Map)this.pluginsByCategoryRef.get();
      if (existing != null) {
         return existing;
      } else {
         Map<String, List<PluginType<?>>> newPluginsByCategory = this.decodeCacheFiles(Loader.getClassLoader());
         return this.pluginsByCategoryRef.compareAndSet((Object)null, newPluginsByCategory) ? newPluginsByCategory : (Map)this.pluginsByCategoryRef.get();
      }
   }

   public void clearBundlePlugins(final long bundleId) {
      this.pluginsByCategoryByBundleId.remove(bundleId);
   }

   public Map loadFromBundle(final long bundleId, final ClassLoader loader) {
      Map<String, List<PluginType<?>>> existing = (Map)this.pluginsByCategoryByBundleId.get(bundleId);
      if (existing != null) {
         return existing;
      } else {
         Map<String, List<PluginType<?>>> newPluginsByCategory = this.decodeCacheFiles(loader);
         existing = (Map)this.pluginsByCategoryByBundleId.putIfAbsent(bundleId, newPluginsByCategory);
         return existing != null ? existing : newPluginsByCategory;
      }
   }

   private Map decodeCacheFiles(final ClassLoader loader) {
      long startTime = System.nanoTime();
      PluginCache cache = new PluginCache();

      try {
         Enumeration<URL> resources = loader.getResources("META-INF/org/apache/logging/log4j/core/config/plugins/Log4j2Plugins.dat");
         if (!resources.hasMoreElements()) {
            LOGGER.debug("Plugin descriptors not available from class loader {}", loader);
         } else {
            cache.loadCacheFiles(resources);
         }
      } catch (IOException ioe) {
         LOGGER.warn("Unable to preload plugins", ioe);
      }

      Map<String, List<PluginType<?>>> newPluginsByCategory = new HashMap();
      int pluginCount = 0;

      for(Map.Entry outer : cache.getAllCategories().entrySet()) {
         String categoryLowerCase = (String)outer.getKey();
         List<PluginType<?>> types = new ArrayList(((Map)outer.getValue()).size());
         newPluginsByCategory.put(categoryLowerCase, types);

         for(Map.Entry inner : ((Map)outer.getValue()).entrySet()) {
            PluginEntry entry = (PluginEntry)inner.getValue();
            String className = entry.getClassName();

            try {
               Class<?> clazz = loader.loadClass(className);
               PluginType<?> type = new PluginType(entry, clazz, entry.getName());
               types.add(type);
               ++pluginCount;
            } catch (ClassNotFoundException e) {
               LOGGER.info("Plugin [{}] could not be loaded due to missing classes.", className, e);
            } catch (LinkageError e) {
               LOGGER.info("Plugin [{}] could not be loaded due to linkage error.", className, e);
            }
         }
      }

      LOGGER.debug(() -> {
         long endTime = System.nanoTime();
         DecimalFormat numFormat = new DecimalFormat("#0.000000");
         return "Took " + numFormat.format((double)(endTime - startTime) * 1.0E-9) + " seconds to load " + pluginCount + " plugins from " + loader;
      });
      return newPluginsByCategory;
   }

   public Map loadFromPackage(final String pkg) {
      if (Strings.isBlank(pkg)) {
         return Collections.emptyMap();
      } else {
         Map<String, List<PluginType<?>>> existing = (Map)this.pluginsByCategoryByPackage.get(pkg);
         if (existing != null) {
            return existing;
         } else {
            long startTime = System.nanoTime();
            ResolverUtil resolver = new ResolverUtil();
            ClassLoader classLoader = Loader.getClassLoader();
            if (classLoader != null) {
               resolver.setClassLoader(classLoader);
            }

            resolver.findInPackage(new PluginTest(), pkg);
            Map<String, List<PluginType<?>>> newPluginsByCategory = new HashMap();

            for(Class clazz : resolver.getClasses()) {
               Plugin plugin = (Plugin)clazz.getAnnotation(Plugin.class);
               String categoryLowerCase = Strings.toRootLowerCase(plugin.category());
               List<PluginType<?>> list = (List)newPluginsByCategory.get(categoryLowerCase);
               if (list == null) {
                  newPluginsByCategory.put(categoryLowerCase, list = new ArrayList());
               }

               PluginEntry mainEntry = new PluginEntry();
               String mainElementName = plugin.elementType().equals("") ? plugin.name() : plugin.elementType();
               mainEntry.setKey(Strings.toRootLowerCase(plugin.name()));
               mainEntry.setName(plugin.name());
               mainEntry.setCategory(plugin.category());
               mainEntry.setClassName(clazz.getName());
               mainEntry.setPrintable(plugin.printObject());
               mainEntry.setDefer(plugin.deferChildren());
               PluginType<?> mainType = new PluginType(mainEntry, clazz, mainElementName);
               list.add(mainType);
               PluginAliases pluginAliases = (PluginAliases)clazz.getAnnotation(PluginAliases.class);
               if (pluginAliases != null) {
                  for(String alias : pluginAliases.value()) {
                     PluginEntry aliasEntry = new PluginEntry();
                     String aliasElementName = plugin.elementType().equals("") ? alias.trim() : plugin.elementType();
                     aliasEntry.setKey(Strings.toRootLowerCase(alias.trim()));
                     aliasEntry.setName(plugin.name());
                     aliasEntry.setCategory(plugin.category());
                     aliasEntry.setClassName(clazz.getName());
                     aliasEntry.setPrintable(plugin.printObject());
                     aliasEntry.setDefer(plugin.deferChildren());
                     PluginType<?> aliasType = new PluginType(aliasEntry, clazz, aliasElementName);
                     list.add(aliasType);
                  }
               }
            }

            LOGGER.debug(() -> {
               long endTime = System.nanoTime();
               StringBuilder sb = new StringBuilder("Took ");
               DecimalFormat numFormat = new DecimalFormat("#0.000000");
               sb.append(numFormat.format((double)(endTime - startTime) * 1.0E-9));
               sb.append(" seconds to load ").append(resolver.getClasses().size());
               sb.append(" plugins from package ").append(pkg);
               return sb.toString();
            });
            existing = (Map)this.pluginsByCategoryByPackage.putIfAbsent(pkg, newPluginsByCategory);
            if (existing != null) {
               return existing;
            } else {
               return newPluginsByCategory;
            }
         }
      }
   }

   public static class PluginTest implements ResolverUtil.Test {
      public boolean matches(final Class type) {
         return type != null && type.isAnnotationPresent(Plugin.class);
      }

      public String toString() {
         return "annotated with @" + Plugin.class.getSimpleName();
      }

      public boolean matches(final URI resource) {
         throw new UnsupportedOperationException();
      }

      public boolean doesMatchClass() {
         return true;
      }

      public boolean doesMatchResource() {
         return false;
      }
   }
}
