package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
   name = "CounterResolverFactory",
   category = "JsonTemplateResolverFactory"
)
public final class CounterResolverFactory implements EventResolverFactory {
   private static final CounterResolverFactory INSTANCE = new CounterResolverFactory();

   private CounterResolverFactory() {
   }

   @PluginFactory
   public static CounterResolverFactory getInstance() {
      return INSTANCE;
   }

   public String getName() {
      return CounterResolver.getName();
   }

   public CounterResolver create(final EventResolverContext context, final TemplateResolverConfig config) {
      return new CounterResolver(context, config);
   }
}
