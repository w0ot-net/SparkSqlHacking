package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
   name = "MarkerResolverFactory",
   category = "JsonTemplateResolverFactory"
)
public final class MarkerResolverFactory implements EventResolverFactory {
   private static final MarkerResolverFactory INSTANCE = new MarkerResolverFactory();

   private MarkerResolverFactory() {
   }

   @PluginFactory
   public static MarkerResolverFactory getInstance() {
      return INSTANCE;
   }

   public String getName() {
      return MarkerResolver.getName();
   }

   public MarkerResolver create(final EventResolverContext context, final TemplateResolverConfig config) {
      return new MarkerResolver(config);
   }
}
