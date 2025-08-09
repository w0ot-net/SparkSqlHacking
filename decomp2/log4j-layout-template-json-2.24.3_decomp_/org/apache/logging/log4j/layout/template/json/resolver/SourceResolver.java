package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;

public final class SourceResolver implements EventResolver {
   private static final EventResolver NULL_RESOLVER = (value, jsonWriter) -> jsonWriter.writeNull();
   private static final EventResolver CLASS_NAME_RESOLVER = (logEvent, jsonWriter) -> {
      StackTraceElement logEventSource = logEvent.getSource();
      if (logEventSource == null) {
         jsonWriter.writeNull();
      } else {
         String sourceClassName = logEventSource.getClassName();
         jsonWriter.writeString((CharSequence)sourceClassName);
      }

   };
   private static final EventResolver FILE_NAME_RESOLVER = (logEvent, jsonWriter) -> {
      StackTraceElement logEventSource = logEvent.getSource();
      if (logEventSource == null) {
         jsonWriter.writeNull();
      } else {
         String sourceFileName = logEventSource.getFileName();
         jsonWriter.writeString((CharSequence)sourceFileName);
      }

   };
   private static final EventResolver LINE_NUMBER_RESOLVER = (logEvent, jsonWriter) -> {
      StackTraceElement logEventSource = logEvent.getSource();
      if (logEventSource == null) {
         jsonWriter.writeNull();
      } else {
         int sourceLineNumber = logEventSource.getLineNumber();
         jsonWriter.writeNumber(sourceLineNumber);
      }

   };
   private static final EventResolver METHOD_NAME_RESOLVER = (logEvent, jsonWriter) -> {
      StackTraceElement logEventSource = logEvent.getSource();
      if (logEventSource == null) {
         jsonWriter.writeNull();
      } else {
         String sourceMethodName = logEventSource.getMethodName();
         jsonWriter.writeString((CharSequence)sourceMethodName);
      }

   };
   private final boolean locationInfoEnabled;
   private final EventResolver internalResolver;

   SourceResolver(final EventResolverContext context, final TemplateResolverConfig config) {
      this.locationInfoEnabled = context.isLocationInfoEnabled();
      this.internalResolver = createInternalResolver(context, config);
   }

   private static EventResolver createInternalResolver(final EventResolverContext context, final TemplateResolverConfig config) {
      if (!context.isLocationInfoEnabled()) {
         return NULL_RESOLVER;
      } else {
         String fieldName = config.getString("field");
         if ("className".equals(fieldName)) {
            return CLASS_NAME_RESOLVER;
         } else if ("fileName".equals(fieldName)) {
            return FILE_NAME_RESOLVER;
         } else if ("lineNumber".equals(fieldName)) {
            return LINE_NUMBER_RESOLVER;
         } else if ("methodName".equals(fieldName)) {
            return METHOD_NAME_RESOLVER;
         } else {
            throw new IllegalArgumentException("unknown field: " + config);
         }
      }
   }

   static String getName() {
      return "source";
   }

   public boolean isResolvable() {
      return this.locationInfoEnabled;
   }

   public boolean isResolvable(final LogEvent logEvent) {
      return this.locationInfoEnabled && logEvent.getSource() != null;
   }

   public void resolve(final LogEvent logEvent, final JsonWriter jsonWriter) {
      this.internalResolver.resolve(logEvent, jsonWriter);
   }
}
