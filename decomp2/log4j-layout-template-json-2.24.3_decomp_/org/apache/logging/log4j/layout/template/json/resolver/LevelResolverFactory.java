package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
   name = "LevelResolverFactory",
   category = "JsonTemplateResolverFactory"
)
public final class LevelResolverFactory implements EventResolverFactory {
   private static final LevelResolverFactory INSTANCE = new LevelResolverFactory();

   private LevelResolverFactory() {
   }

   @PluginFactory
   public static LevelResolverFactory getInstance() {
      return INSTANCE;
   }

   public String getName() {
      return LevelResolver.getName();
   }

   public LevelResolver create(final EventResolverContext context, final TemplateResolverConfig config) {
      return new LevelResolver(context, config);
   }
}
