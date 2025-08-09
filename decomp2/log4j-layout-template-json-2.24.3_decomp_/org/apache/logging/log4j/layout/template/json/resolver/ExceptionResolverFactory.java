package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
   name = "ExceptionResolverFactory",
   category = "JsonTemplateResolverFactory"
)
public final class ExceptionResolverFactory implements EventResolverFactory {
   private static final ExceptionResolverFactory INSTANCE = new ExceptionResolverFactory();

   private ExceptionResolverFactory() {
   }

   @PluginFactory
   public static ExceptionResolverFactory getInstance() {
      return INSTANCE;
   }

   public String getName() {
      return ExceptionResolver.getName();
   }

   public ExceptionResolver create(final EventResolverContext context, final TemplateResolverConfig config) {
      return new ExceptionResolver(context, config);
   }
}
