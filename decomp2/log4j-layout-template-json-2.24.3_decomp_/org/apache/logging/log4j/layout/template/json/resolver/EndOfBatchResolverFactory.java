package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
   name = "EndOfBatchResolverFactory",
   category = "JsonTemplateResolverFactory"
)
public final class EndOfBatchResolverFactory implements EventResolverFactory {
   private static final EndOfBatchResolverFactory INSTANCE = new EndOfBatchResolverFactory();

   private EndOfBatchResolverFactory() {
   }

   @PluginFactory
   public static EndOfBatchResolverFactory getInstance() {
      return INSTANCE;
   }

   public String getName() {
      return EndOfBatchResolver.getName();
   }

   public EndOfBatchResolver create(final EventResolverContext context, final TemplateResolverConfig config) {
      return EndOfBatchResolver.getInstance();
   }
}
