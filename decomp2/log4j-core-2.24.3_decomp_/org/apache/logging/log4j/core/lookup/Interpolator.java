package org.apache.logging.log4j.core.lookup;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationAware;
import org.apache.logging.log4j.core.config.LoggerContextAware;
import org.apache.logging.log4j.core.config.plugins.util.PluginManager;
import org.apache.logging.log4j.core.config.plugins.util.PluginType;
import org.apache.logging.log4j.core.net.JndiManager;
import org.apache.logging.log4j.core.util.ReflectionUtil;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.Strings;

public class Interpolator extends AbstractConfigurationAwareLookup implements LoggerContextAware {
   public static final char PREFIX_SEPARATOR = ':';
   private static final String LOOKUP_KEY_WEB = "web";
   private static final String LOOKUP_KEY_DOCKER = "docker";
   private static final String LOOKUP_KEY_KUBERNETES = "kubernetes";
   private static final String LOOKUP_KEY_SPRING = "spring";
   private static final String LOOKUP_KEY_JNDI = "jndi";
   private static final String LOOKUP_KEY_JVMRUNARGS = "jvmrunargs";
   private static final Logger LOGGER = StatusLogger.getLogger();
   private final Map strLookupMap;
   private final StrLookup defaultLookup;
   protected WeakReference loggerContext;

   public Interpolator(final StrLookup defaultLookup) {
      this(defaultLookup, (List)null);
   }

   public Interpolator(final StrLookup defaultLookup, final List pluginPackages) {
      this.strLookupMap = new HashMap();
      this.loggerContext = new WeakReference((Object)null);
      this.defaultLookup = (StrLookup)(defaultLookup == null ? new PropertiesLookup(new HashMap()) : defaultLookup);
      PluginManager manager = new PluginManager("Lookup");
      manager.collectPlugins(pluginPackages);
      Map<String, PluginType<?>> plugins = manager.getPlugins();

      for(Map.Entry entry : plugins.entrySet()) {
         try {
            Class<? extends StrLookup> clazz = ((PluginType)entry.getValue()).getPluginClass().asSubclass(StrLookup.class);
            if (!clazz.getName().equals("org.apache.logging.log4j.core.lookup.JndiLookup") || JndiManager.isJndiLookupEnabled()) {
               this.strLookupMap.put(Strings.toRootLowerCase((String)entry.getKey()), (StrLookup)ReflectionUtil.instantiate(clazz));
            }
         } catch (Throwable t) {
            this.handleError((String)entry.getKey(), t);
         }
      }

   }

   public Interpolator() {
      this((Map)null);
   }

   public Interpolator(final Map properties) {
      this(new PropertiesLookup(properties), Collections.emptyList());
   }

   public StrLookup getDefaultLookup() {
      return this.defaultLookup;
   }

   public Map getStrLookupMap() {
      return this.strLookupMap;
   }

   private void handleError(final String lookupKey, final Throwable t) {
      switch (lookupKey) {
         case "jndi":
            LOGGER.warn("JNDI lookup class is not available because this JRE does not support JNDI. JNDI string lookups will not be available, continuing configuration. Ignoring " + t);
            break;
         case "jvmrunargs":
            LOGGER.warn("JMX runtime input lookup class is not available because this JRE does not support JMX. JMX lookups will not be available, continuing configuration. Ignoring " + t);
            break;
         case "web":
            LOGGER.info("Log4j appears to be running in a Servlet environment, but there's no log4j-web module available. If you want better web container support, please add the log4j-web JAR to your web archive or server lib directory.");
         case "docker":
         case "spring":
            break;
         case "kubernetes":
            if (t instanceof NoClassDefFoundError) {
               LOGGER.warn("Unable to create Kubernetes lookup due to missing dependency: {}", t.getMessage());
            }
            break;
         default:
            LOGGER.error("Unable to create Lookup for {}", lookupKey, t);
      }

   }

   public String lookup(final LogEvent event, String var) {
      LookupResult result = this.evaluate(event, var);
      return result == null ? null : result.value();
   }

   public LookupResult evaluate(final LogEvent event, String var) {
      if (var == null) {
         return null;
      } else {
         int prefixPos = var.indexOf(58);
         if (prefixPos >= 0) {
            String prefix = Strings.toRootLowerCase(var.substring(0, prefixPos));
            String name = var.substring(prefixPos + 1);
            StrLookup lookup = (StrLookup)this.strLookupMap.get(prefix);
            LookupResult value = null;
            if (lookup != null) {
               value = event == null ? lookup.evaluate(name) : lookup.evaluate(event, name);
            }

            if (value != null) {
               return value;
            }

            var = var.substring(prefixPos + 1);
         }

         if (this.defaultLookup != null) {
            return event == null ? this.defaultLookup.evaluate(var) : this.defaultLookup.evaluate(event, var);
         } else {
            return null;
         }
      }
   }

   public void setConfiguration(final Configuration configuration) {
      super.setConfiguration(configuration);

      for(StrLookup lookup : this.strLookupMap.values()) {
         if (lookup instanceof ConfigurationAware) {
            ((ConfigurationAware)lookup).setConfiguration(configuration);
         }
      }

   }

   public void setLoggerContext(final LoggerContext loggerContext) {
      this.loggerContext = new WeakReference(loggerContext);

      for(StrLookup lookup : this.strLookupMap.values()) {
         if (lookup instanceof LoggerContextAware) {
            ((LoggerContextAware)lookup).setLoggerContext(loggerContext);
         }
      }

   }

   public String toString() {
      StringBuilder sb = new StringBuilder();

      for(String name : this.strLookupMap.keySet()) {
         if (sb.length() == 0) {
            sb.append('{');
         } else {
            sb.append(", ");
         }

         sb.append(name);
      }

      if (sb.length() > 0) {
         sb.append('}');
      }

      return sb.toString();
   }
}
