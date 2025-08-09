package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.util.Throwables;

public final class ExceptionRootCauseResolver extends ExceptionResolver {
   ExceptionRootCauseResolver(final EventResolverContext context, final TemplateResolverConfig config) {
      super(context, config);
   }

   static String getName() {
      return "exceptionRootCause";
   }

   Throwable extractThrowable(final LogEvent logEvent) {
      Throwable thrown = logEvent.getThrown();
      return thrown != null ? Throwables.getRootCause(thrown) : null;
   }
}
