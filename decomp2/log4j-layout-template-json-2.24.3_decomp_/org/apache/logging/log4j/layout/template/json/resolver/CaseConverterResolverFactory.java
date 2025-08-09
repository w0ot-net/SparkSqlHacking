package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
   name = "CaseConverterResolverFactory",
   category = "JsonTemplateResolverFactory"
)
public final class CaseConverterResolverFactory implements EventResolverFactory {
   private static final CaseConverterResolverFactory INSTANCE = new CaseConverterResolverFactory();

   private CaseConverterResolverFactory() {
   }

   @PluginFactory
   public static CaseConverterResolverFactory getInstance() {
      return INSTANCE;
   }

   public String getName() {
      return CaseConverterResolver.getName();
   }

   public CaseConverterResolver create(final EventResolverContext context, final TemplateResolverConfig config) {
      return new CaseConverterResolver(context, config);
   }
}
