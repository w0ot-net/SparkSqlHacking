package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
   name = "MessageResolverFactory",
   category = "JsonTemplateResolverFactory"
)
public final class MessageResolverFactory implements EventResolverFactory {
   private static final MessageResolverFactory INSTANCE = new MessageResolverFactory();

   private MessageResolverFactory() {
   }

   @PluginFactory
   public static MessageResolverFactory getInstance() {
      return INSTANCE;
   }

   public String getName() {
      return MessageResolver.getName();
   }

   public MessageResolver create(final EventResolverContext context, final TemplateResolverConfig config) {
      return new MessageResolver(config);
   }
}
