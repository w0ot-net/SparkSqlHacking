package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
   name = "ExceptionRootCauseResolverFactory",
   category = "JsonTemplateResolverFactory"
)
public final class ExceptionRootCauseResolverFactory implements EventResolverFactory {
   private static final ExceptionRootCauseResolverFactory INSTANCE = new ExceptionRootCauseResolverFactory();

   private ExceptionRootCauseResolverFactory() {
   }

   @PluginFactory
   public static ExceptionRootCauseResolverFactory getInstance() {
      return INSTANCE;
   }

   public String getName() {
      return ExceptionRootCauseResolver.getName();
   }

   public ExceptionRootCauseResolver create(final EventResolverContext context, final TemplateResolverConfig config) {
      return new ExceptionRootCauseResolver(context, config);
   }
}
