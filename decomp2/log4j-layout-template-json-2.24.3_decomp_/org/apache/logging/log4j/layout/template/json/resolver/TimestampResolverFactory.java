package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
   name = "TimestampResolverFactory",
   category = "JsonTemplateResolverFactory"
)
public final class TimestampResolverFactory implements EventResolverFactory {
   private static final TimestampResolverFactory INSTANCE = new TimestampResolverFactory();

   private TimestampResolverFactory() {
   }

   @PluginFactory
   public static TimestampResolverFactory getInstance() {
      return INSTANCE;
   }

   public String getName() {
      return TimestampResolver.getName();
   }

   public TimestampResolver create(final EventResolverContext context, final TemplateResolverConfig config) {
      return new TimestampResolver(config);
   }
}
