package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
   name = "MessageParameterResolverFactory",
   category = "JsonTemplateResolverFactory"
)
public final class MessageParameterResolverFactory implements EventResolverFactory {
   private static final MessageParameterResolverFactory INSTANCE = new MessageParameterResolverFactory();

   private MessageParameterResolverFactory() {
   }

   @PluginFactory
   public static MessageParameterResolverFactory getInstance() {
      return INSTANCE;
   }

   public String getName() {
      return MessageParameterResolver.getName();
   }

   public MessageParameterResolver create(final EventResolverContext context, final TemplateResolverConfig config) {
      return new MessageParameterResolver(context, config);
   }
}
