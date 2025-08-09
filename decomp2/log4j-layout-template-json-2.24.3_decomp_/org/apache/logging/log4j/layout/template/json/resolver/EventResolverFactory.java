package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.LogEvent;

public interface EventResolverFactory extends TemplateResolverFactory {
   default Class getValueClass() {
      return LogEvent.class;
   }

   default Class getContextClass() {
      return EventResolverContext.class;
   }
}
