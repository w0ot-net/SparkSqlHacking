package org.apache.logging.log4j.layout.template.json.resolver;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.util.PluginType;
import org.apache.logging.log4j.core.config.plugins.util.PluginUtil;
import org.apache.logging.log4j.status.StatusLogger;

public final class TemplateResolverFactories {
   private static final Logger LOGGER = StatusLogger.getLogger();

   private TemplateResolverFactories() {
   }

   public static Map populateFactoryByName(final List pluginPackages, final Class valueClass, final Class contextClass) {
      Map<String, PluginType<?>> pluginTypeByName = PluginUtil.collectPluginsByCategoryAndPackage("JsonTemplateResolverFactory", pluginPackages);
      if (LOGGER.isDebugEnabled()) {
         LOGGER.debug("found {} plugins of category \"{}\": {}", pluginTypeByName.size(), "JsonTemplateResolverFactory", pluginTypeByName.keySet());
      }

      Map<String, F> factoryByName = populateFactoryByName(pluginTypeByName, valueClass, contextClass);
      if (LOGGER.isDebugEnabled()) {
         LOGGER.debug("matched {} resolver factories out of {} for value class {} and context class {}: {}", factoryByName.size(), pluginTypeByName.size(), valueClass, contextClass, factoryByName.keySet());
      }

      return factoryByName;
   }

   private static Map populateFactoryByName(final Map pluginTypeByName, final Class valueClass, final Class contextClass) {
      Map<String, F> factoryByName = new LinkedHashMap();

      for(String pluginName : pluginTypeByName.keySet()) {
         PluginType<?> pluginType = (PluginType)pluginTypeByName.get(pluginName);
         Class<?> pluginClass = pluginType.getPluginClass();
         boolean pluginClassMatched = TemplateResolverFactory.class.isAssignableFrom(pluginClass);
         if (pluginClassMatched) {
            TemplateResolverFactory<?, ?> rawFactory = instantiateFactory(pluginName, pluginClass);
            F factory = (F)castFactory(valueClass, contextClass, rawFactory);
            if (factory != null) {
               addFactory(factoryByName, factory);
            }
         }
      }

      return factoryByName;
   }

   private static TemplateResolverFactory instantiateFactory(final String pluginName, final Class pluginClass) {
      try {
         return (TemplateResolverFactory)PluginUtil.instantiatePlugin(pluginClass);
      } catch (Exception error) {
         String message = String.format("failed instantiating resolver factory plugin %s of name %s", pluginClass, pluginName);
         throw new RuntimeException(message, error);
      }
   }

   private static TemplateResolverFactory castFactory(final Class valueClass, final Class contextClass, final TemplateResolverFactory factory) {
      Class<?> factoryValueClass = factory.getValueClass();
      Class<?> factoryContextClass = factory.getContextClass();
      boolean factoryValueClassMatched = valueClass.isAssignableFrom(factoryValueClass);
      boolean factoryContextClassMatched = contextClass.isAssignableFrom(factoryContextClass);
      return factoryValueClassMatched && factoryContextClassMatched ? factory : null;
   }

   private static void addFactory(final Map factoryByName, final TemplateResolverFactory factory) {
      String factoryName = factory.getName();
      F conflictingFactory = (F)((TemplateResolverFactory)factoryByName.putIfAbsent(factoryName, factory));
      if (conflictingFactory != null) {
         String message = String.format("found resolver factories with overlapping names: %s (%s and %s)", factoryName, conflictingFactory, factory);
         throw new IllegalArgumentException(message);
      }
   }
}
