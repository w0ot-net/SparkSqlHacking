package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
   name = "ThreadResolverFactory",
   category = "JsonTemplateResolverFactory"
)
public final class ThreadResolverFactory implements EventResolverFactory {
   private static final ThreadResolverFactory INSTANCE = new ThreadResolverFactory();

   private ThreadResolverFactory() {
   }

   @PluginFactory
   public static ThreadResolverFactory getInstance() {
      return INSTANCE;
   }

   public String getName() {
      return ThreadResolver.getName();
   }

   public ThreadResolver create(final EventResolverContext context, final TemplateResolverConfig config) {
      return new ThreadResolver(config);
   }
}
