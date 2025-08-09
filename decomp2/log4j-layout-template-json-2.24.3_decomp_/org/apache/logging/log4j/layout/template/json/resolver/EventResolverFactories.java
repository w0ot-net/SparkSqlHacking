package org.apache.logging.log4j.layout.template.json.resolver;

import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.core.LogEvent;

public final class EventResolverFactories {
   private EventResolverFactories() {
   }

   public static Map populateResolverFactoryByName(final List pluginPackages) {
      return TemplateResolverFactories.populateFactoryByName(pluginPackages, LogEvent.class, EventResolverContext.class);
   }
}
