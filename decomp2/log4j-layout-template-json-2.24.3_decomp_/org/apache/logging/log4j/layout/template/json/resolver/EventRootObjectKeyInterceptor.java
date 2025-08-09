package org.apache.logging.log4j.layout.template.json.resolver;

import java.util.Collections;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
   name = "EventRootObjectKeyInterceptor",
   category = "JsonTemplateResolverInterceptor"
)
public class EventRootObjectKeyInterceptor implements EventResolverInterceptor {
   private static final EventRootObjectKeyInterceptor INSTANCE = new EventRootObjectKeyInterceptor();

   private EventRootObjectKeyInterceptor() {
   }

   @PluginFactory
   public static EventRootObjectKeyInterceptor getInstance() {
      return INSTANCE;
   }

   public Object processTemplateBeforeResolverInjection(final EventResolverContext context, final Object node) {
      String eventTemplateRootObjectKey = context.getEventTemplateRootObjectKey();
      return eventTemplateRootObjectKey != null ? Collections.singletonMap(eventTemplateRootObjectKey, node) : node;
   }
}
