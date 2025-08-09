package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
   name = "MapResolverFactory",
   category = "JsonTemplateResolverFactory"
)
public final class MapResolverFactory implements EventResolverFactory {
   private static final MapResolverFactory INSTANCE = new MapResolverFactory();

   private MapResolverFactory() {
   }

   @PluginFactory
   public static MapResolverFactory getInstance() {
      return INSTANCE;
   }

   public String getName() {
      return MapResolver.getName();
   }

   public MapResolver create(final EventResolverContext context, final TemplateResolverConfig config) {
      return new MapResolver(context, config);
   }
}
