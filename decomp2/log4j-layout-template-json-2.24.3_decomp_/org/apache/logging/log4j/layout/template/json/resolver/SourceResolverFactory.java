package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
   name = "SourceResolverFactory",
   category = "JsonTemplateResolverFactory"
)
public final class SourceResolverFactory implements EventResolverFactory {
   private static final SourceResolverFactory INSTANCE = new SourceResolverFactory();

   private SourceResolverFactory() {
   }

   @PluginFactory
   public static SourceResolverFactory getInstance() {
      return INSTANCE;
   }

   public String getName() {
      return SourceResolver.getName();
   }

   public SourceResolver create(final EventResolverContext context, final TemplateResolverConfig config) {
      return new SourceResolver(context, config);
   }
}
