package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
   name = "MainMapResolverFactory",
   category = "JsonTemplateResolverFactory"
)
public final class MainMapResolverFactory implements EventResolverFactory {
   private static final MainMapResolverFactory INSTANCE = new MainMapResolverFactory();

   private MainMapResolverFactory() {
   }

   @PluginFactory
   public static MainMapResolverFactory getInstance() {
      return INSTANCE;
   }

   public String getName() {
      return MainMapResolver.getName();
   }

   public MainMapResolver create(final EventResolverContext context, final TemplateResolverConfig config) {
      return new MainMapResolver(config);
   }
}
