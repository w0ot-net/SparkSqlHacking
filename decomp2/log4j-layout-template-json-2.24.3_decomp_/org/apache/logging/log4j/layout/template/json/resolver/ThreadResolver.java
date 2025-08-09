package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;

public final class ThreadResolver implements EventResolver {
   private static final EventResolver NAME_RESOLVER = (logEvent, jsonWriter) -> {
      String threadName = logEvent.getThreadName();
      jsonWriter.writeString((CharSequence)threadName);
   };
   private static final EventResolver ID_RESOLVER = (logEvent, jsonWriter) -> {
      long threadId = logEvent.getThreadId();
      jsonWriter.writeNumber(threadId);
   };
   private static final EventResolver PRIORITY_RESOLVER = (logEvent, jsonWriter) -> {
      int threadPriority = logEvent.getThreadPriority();
      jsonWriter.writeNumber(threadPriority);
   };
   private final EventResolver internalResolver;

   ThreadResolver(final TemplateResolverConfig config) {
      this.internalResolver = createInternalResolver(config);
   }

   private static EventResolver createInternalResolver(final TemplateResolverConfig config) {
      String fieldName = config.getString("field");
      if ("name".equals(fieldName)) {
         return NAME_RESOLVER;
      } else if ("id".equals(fieldName)) {
         return ID_RESOLVER;
      } else if ("priority".equals(fieldName)) {
         return PRIORITY_RESOLVER;
      } else {
         throw new IllegalArgumentException("unknown field: " + config);
      }
   }

   static String getName() {
      return "thread";
   }

   public void resolve(final LogEvent logEvent, final JsonWriter jsonWriter) {
      this.internalResolver.resolve(logEvent, jsonWriter);
   }
}
