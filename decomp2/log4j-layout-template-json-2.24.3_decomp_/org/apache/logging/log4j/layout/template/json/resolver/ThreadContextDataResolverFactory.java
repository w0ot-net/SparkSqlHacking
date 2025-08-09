package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
   name = "ThreadContextDataResolverFactory",
   category = "JsonTemplateResolverFactory"
)
public final class ThreadContextDataResolverFactory implements EventResolverFactory {
   private static final ThreadContextDataResolverFactory INSTANCE = new ThreadContextDataResolverFactory();

   private ThreadContextDataResolverFactory() {
   }

   @PluginFactory
   public static ThreadContextDataResolverFactory getInstance() {
      return INSTANCE;
   }

   public String getName() {
      return ThreadContextDataResolver.getName();
   }

   public ThreadContextDataResolver create(final EventResolverContext context, final TemplateResolverConfig config) {
      return new ThreadContextDataResolver(context, config);
   }
}
