package org.apache.log4j.builders;

import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Function;
import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.bridge.AppenderWrapper;
import org.apache.log4j.bridge.FilterWrapper;
import org.apache.log4j.bridge.LayoutWrapper;
import org.apache.log4j.bridge.RewritePolicyWrapper;
import org.apache.log4j.builders.appender.AppenderBuilder;
import org.apache.log4j.config.PropertiesConfiguration;
import org.apache.log4j.rewrite.RewritePolicy;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.xml.XmlConfiguration;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.rolling.TriggeringPolicy;
import org.apache.logging.log4j.core.config.plugins.util.PluginManager;
import org.apache.logging.log4j.core.config.plugins.util.PluginType;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.LoaderUtil;
import org.apache.logging.log4j.util.Strings;
import org.w3c.dom.Element;

public class BuilderManager {
   public static final String CATEGORY = "Log4j Builder";
   public static final Appender INVALID_APPENDER = new AppenderWrapper((org.apache.logging.log4j.core.Appender)null);
   public static final Filter INVALID_FILTER = new FilterWrapper((org.apache.logging.log4j.core.Filter)null);
   public static final Layout INVALID_LAYOUT = new LayoutWrapper((org.apache.logging.log4j.core.Layout)null);
   public static final RewritePolicy INVALID_REWRITE_POLICY = new RewritePolicyWrapper((org.apache.logging.log4j.core.appender.rewrite.RewritePolicy)null);
   private static final Logger LOGGER = StatusLogger.getLogger();
   private static final Class[] CONSTRUCTOR_PARAMS = new Class[]{String.class, Properties.class};
   private final Map plugins;

   public BuilderManager() {
      PluginManager manager = new PluginManager("Log4j Builder");
      manager.collectPlugins();
      this.plugins = manager.getPlugins();
   }

   private Builder createBuilder(final PluginType plugin, final String prefix, final Properties props) {
      if (plugin == null) {
         return null;
      } else {
         try {
            Class<T> clazz = plugin.getPluginClass();
            if (AbstractBuilder.class.isAssignableFrom(clazz)) {
               return (Builder)clazz.getConstructor(CONSTRUCTOR_PARAMS).newInstance(prefix, props);
            } else {
               T builder = (T)((Builder)LoaderUtil.newInstanceOf(clazz));
               if (!Builder.class.isAssignableFrom(clazz)) {
                  LOGGER.warn("Unable to load plugin: builder {} does not implement {}", clazz, Builder.class);
                  return null;
               } else {
                  return builder;
               }
            }
         } catch (ReflectiveOperationException ex) {
            LOGGER.warn("Unable to load plugin: {} due to: {}", plugin.getKey(), ex.getMessage());
            return null;
         }
      }
   }

   private PluginType getPlugin(final String className) {
      Objects.requireNonNull(this.plugins, "plugins");
      Objects.requireNonNull(className, "className");
      String key = Strings.toRootLowerCase(className).trim();
      PluginType<?> pluginType = (PluginType)this.plugins.get(key);
      if (pluginType == null) {
         LOGGER.warn("Unable to load plugin class name {} with key {}", className, key);
      }

      return pluginType;
   }

   private Object newInstance(final PluginType plugin, final Function consumer, final Object invalidValue) {
      if (plugin != null) {
         try {
            T builder = (T)((Builder)LoaderUtil.newInstanceOf(plugin.getPluginClass()));
            if (builder != null) {
               U result = (U)consumer.apply(builder);
               return result != null ? result : invalidValue;
            }
         } catch (ReflectiveOperationException ex) {
            LOGGER.warn("Unable to load plugin: {} due to: {}", plugin.getKey(), ex.getMessage());
         }
      }

      return null;
   }

   public Object parse(final String className, final String prefix, final Properties props, final PropertiesConfiguration config, final Object invalidValue) {
      P parser = (P)((Parser)this.createBuilder(this.getPlugin(className), prefix, props));
      if (parser != null) {
         T value = (T)parser.parse(config);
         return value != null ? value : invalidValue;
      } else {
         return null;
      }
   }

   public Appender parseAppender(final String className, final Element appenderElement, final XmlConfiguration config) {
      return (Appender)this.newInstance(this.getPlugin(className), (b) -> b.parseAppender(appenderElement, config), INVALID_APPENDER);
   }

   public Appender parseAppender(final String name, final String className, final String prefix, final String layoutPrefix, final String filterPrefix, final Properties props, final PropertiesConfiguration config) {
      AppenderBuilder<Appender> builder = (AppenderBuilder)this.createBuilder(this.getPlugin(className), prefix, props);
      if (builder != null) {
         Appender appender = builder.parseAppender(name, prefix, layoutPrefix, filterPrefix, props, config);
         return appender != null ? appender : INVALID_APPENDER;
      } else {
         return null;
      }
   }

   public Filter parseFilter(final String className, final Element filterElement, final XmlConfiguration config) {
      return (Filter)this.newInstance(this.getPlugin(className), (b) -> (Filter)b.parse(filterElement, config), INVALID_FILTER);
   }

   public Layout parseLayout(final String className, final Element layoutElement, final XmlConfiguration config) {
      return (Layout)this.newInstance(this.getPlugin(className), (b) -> (Layout)b.parse(layoutElement, config), INVALID_LAYOUT);
   }

   public RewritePolicy parseRewritePolicy(final String className, final Element rewriteElement, final XmlConfiguration config) {
      return (RewritePolicy)this.newInstance(this.getPlugin(className), (b) -> (RewritePolicy)b.parse(rewriteElement, config), INVALID_REWRITE_POLICY);
   }

   public TriggeringPolicy parseTriggeringPolicy(final String className, final Element policyElement, final XmlConfiguration config) {
      return (TriggeringPolicy)this.newInstance(this.getPlugin(className), (b) -> (TriggeringPolicy)b.parse(policyElement, config), (TriggeringPolicy)null);
   }
}
