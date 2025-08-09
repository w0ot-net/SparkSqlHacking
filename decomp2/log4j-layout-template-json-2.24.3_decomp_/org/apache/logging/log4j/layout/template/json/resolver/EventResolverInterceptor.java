package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.LogEvent;

public interface EventResolverInterceptor extends TemplateResolverInterceptor {
   default Class getValueClass() {
      return LogEvent.class;
   }

   default Class getContextClass() {
      return EventResolverContext.class;
   }
}
