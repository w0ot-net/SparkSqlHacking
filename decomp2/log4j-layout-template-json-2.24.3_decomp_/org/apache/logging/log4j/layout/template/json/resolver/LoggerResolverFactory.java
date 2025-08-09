package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
   name = "LoggerResolverFactory",
   category = "JsonTemplateResolverFactory"
)
public final class LoggerResolverFactory implements EventResolverFactory {
   private static final LoggerResolverFactory INSTANCE = new LoggerResolverFactory();

   private LoggerResolverFactory() {
   }

   @PluginFactory
   public static LoggerResolverFactory getInstance() {
      return INSTANCE;
   }

   public String getName() {
      return LoggerResolver.getName();
   }

   public LoggerResolver create(final EventResolverContext context, final TemplateResolverConfig config) {
      return new LoggerResolver(config);
   }
}
