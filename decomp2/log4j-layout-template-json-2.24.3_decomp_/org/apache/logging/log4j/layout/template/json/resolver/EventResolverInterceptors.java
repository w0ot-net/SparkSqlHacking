package org.apache.logging.log4j.layout.template.json.resolver;

import java.util.List;
import org.apache.logging.log4j.core.LogEvent;

public final class EventResolverInterceptors {
   private EventResolverInterceptors() {
   }

   public static List populateInterceptors(final List pluginPackages) {
      return TemplateResolverInterceptors.populateInterceptors(pluginPackages, LogEvent.class, EventResolverContext.class);
   }
}
