package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
   name = "ThreadContextStackResolverFactory",
   category = "JsonTemplateResolverFactory"
)
public final class ThreadContextStackResolverFactory implements EventResolverFactory {
   private static final ThreadContextStackResolverFactory INSTANCE = new ThreadContextStackResolverFactory();

   private ThreadContextStackResolverFactory() {
   }

   @PluginFactory
   public static ThreadContextStackResolverFactory getInstance() {
      return INSTANCE;
   }

   public String getName() {
      return ThreadContextStackResolver.getName();
   }

   public ThreadContextStackResolver create(final EventResolverContext context, final TemplateResolverConfig config) {
      return new ThreadContextStackResolver(config);
   }
}
