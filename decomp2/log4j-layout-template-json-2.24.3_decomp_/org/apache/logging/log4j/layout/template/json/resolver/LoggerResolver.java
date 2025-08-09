package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;

public final class LoggerResolver implements EventResolver {
   private static final EventResolver NAME_RESOLVER = (logEvent, jsonWriter) -> {
      String loggerName = logEvent.getLoggerName();
      jsonWriter.writeString((CharSequence)loggerName);
   };
   private static final EventResolver FQCN_RESOLVER = (logEvent, jsonWriter) -> {
      String loggerFqcn = logEvent.getLoggerFqcn();
      jsonWriter.writeString((CharSequence)loggerFqcn);
   };
   private final EventResolver internalResolver;

   LoggerResolver(final TemplateResolverConfig config) {
      this.internalResolver = createInternalResolver(config);
   }

   private static EventResolver createInternalResolver(final TemplateResolverConfig config) {
      String fieldName = config.getString("field");
      if ("name".equals(fieldName)) {
         return NAME_RESOLVER;
      } else if ("fqcn".equals(fieldName)) {
         return FQCN_RESOLVER;
      } else {
         throw new IllegalArgumentException("unknown field: " + config);
      }
   }

   static String getName() {
      return "logger";
   }

   public void resolve(final LogEvent logEvent, final JsonWriter jsonWriter) {
      this.internalResolver.resolve(logEvent, jsonWriter);
   }
}
