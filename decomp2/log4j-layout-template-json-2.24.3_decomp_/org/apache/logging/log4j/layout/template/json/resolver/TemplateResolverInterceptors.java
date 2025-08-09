package org.apache.logging.log4j.layout.template.json.resolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.util.PluginType;
import org.apache.logging.log4j.core.config.plugins.util.PluginUtil;
import org.apache.logging.log4j.status.StatusLogger;

public class TemplateResolverInterceptors {
   private static final Logger LOGGER = StatusLogger.getLogger();

   private TemplateResolverInterceptors() {
   }

   public static List populateInterceptors(final List pluginPackages, final Class valueClass, final Class contextClass) {
      Map<String, PluginType<?>> pluginTypeByName = PluginUtil.collectPluginsByCategoryAndPackage("JsonTemplateResolverInterceptor", pluginPackages);
      if (LOGGER.isDebugEnabled()) {
         LOGGER.debug("found {} plugins of category \"{}\": {}", pluginTypeByName.size(), "JsonTemplateResolverFactory", pluginTypeByName.keySet());
      }

      List<I> interceptors = populateInterceptors(pluginTypeByName, valueClass, contextClass);
      LOGGER.debug("{} interceptors matched out of {} for value class {} and context class {}", interceptors.size(), pluginTypeByName.size(), valueClass, contextClass);
      return interceptors;
   }

   private static List populateInterceptors(final Map pluginTypeByName, final Class valueClass, final Class contextClass) {
      List<I> interceptors = new ArrayList();

      for(String pluginName : pluginTypeByName.keySet()) {
         PluginType<?> pluginType = (PluginType)pluginTypeByName.get(pluginName);
         Class<?> pluginClass = pluginType.getPluginClass();
         boolean pluginClassMatched = TemplateResolverInterceptor.class.isAssignableFrom(pluginClass);
         if (pluginClassMatched) {
            TemplateResolverInterceptor<?, ?> rawInterceptor = instantiateInterceptor(pluginName, pluginClass);
            I interceptor = (I)castInterceptor(valueClass, contextClass, rawInterceptor);
            if (interceptor != null) {
               interceptors.add(interceptor);
            }
         }
      }

      return interceptors;
   }

   private static TemplateResolverInterceptor instantiateInterceptor(final String pluginName, final Class pluginClass) {
      try {
         return (TemplateResolverInterceptor)PluginUtil.instantiatePlugin(pluginClass);
      } catch (Exception error) {
         String message = String.format("failed instantiating resolver interceptor plugin %s of name %s", pluginClass, pluginName);
         throw new RuntimeException(message, error);
      }
   }

   private static TemplateResolverInterceptor castInterceptor(final Class valueClass, final Class contextClass, final TemplateResolverInterceptor interceptor) {
      Class<?> interceptorValueClass = interceptor.getValueClass();
      Class<?> interceptorContextClass = interceptor.getContextClass();
      boolean interceptorValueClassMatched = valueClass.isAssignableFrom(interceptorValueClass);
      boolean interceptorContextClassMatched = contextClass.isAssignableFrom(interceptorContextClass);
      return interceptorValueClassMatched && interceptorContextClassMatched ? interceptor : null;
   }
}
