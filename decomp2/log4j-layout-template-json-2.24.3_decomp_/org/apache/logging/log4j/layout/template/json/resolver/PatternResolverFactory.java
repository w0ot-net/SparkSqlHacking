package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
   name = "PatternResolverFactory",
   category = "JsonTemplateResolverFactory"
)
public final class PatternResolverFactory implements EventResolverFactory {
   private static final PatternResolverFactory INSTANCE = new PatternResolverFactory();

   private PatternResolverFactory() {
   }

   @PluginFactory
   public static PatternResolverFactory getInstance() {
      return INSTANCE;
   }

   public String getName() {
      return PatternResolver.getName();
   }

   public PatternResolver create(final EventResolverContext context, final TemplateResolverConfig config) {
      return new PatternResolver(context, config);
   }
}
