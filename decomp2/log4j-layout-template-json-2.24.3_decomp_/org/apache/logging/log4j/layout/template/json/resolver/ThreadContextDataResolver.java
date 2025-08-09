package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.LogEvent;

public final class ThreadContextDataResolver extends ReadOnlyStringMapResolver {
   ThreadContextDataResolver(final EventResolverContext context, final TemplateResolverConfig config) {
      super(context, config, LogEvent::getContextData);
   }

   static String getName() {
      return "mdc";
   }
}
