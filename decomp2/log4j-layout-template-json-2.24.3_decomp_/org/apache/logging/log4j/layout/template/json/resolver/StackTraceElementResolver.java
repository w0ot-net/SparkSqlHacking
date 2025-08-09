package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.layout.template.json.util.JsonWriter;

final class StackTraceElementResolver implements TemplateResolver {
   private static final TemplateResolver CLASS_NAME_RESOLVER = (stackTraceElement, jsonWriter) -> jsonWriter.writeString((CharSequence)stackTraceElement.getClassName());
   private static final TemplateResolver METHOD_NAME_RESOLVER = (stackTraceElement, jsonWriter) -> jsonWriter.writeString((CharSequence)stackTraceElement.getMethodName());
   private static final TemplateResolver FILE_NAME_RESOLVER = (stackTraceElement, jsonWriter) -> jsonWriter.writeString((CharSequence)stackTraceElement.getFileName());
   private static final TemplateResolver LINE_NUMBER_RESOLVER = (stackTraceElement, jsonWriter) -> jsonWriter.writeNumber(stackTraceElement.getLineNumber());
   private final TemplateResolver internalResolver;

   StackTraceElementResolver(final TemplateResolverConfig config) {
      this.internalResolver = this.createInternalResolver(config);
   }

   static String getName() {
      return "stackTraceElement";
   }

   private TemplateResolver createInternalResolver(final TemplateResolverConfig config) {
      String fieldName = config.getString("field");
      if ("className".equals(fieldName)) {
         return CLASS_NAME_RESOLVER;
      } else if ("methodName".equals(fieldName)) {
         return METHOD_NAME_RESOLVER;
      } else if ("fileName".equals(fieldName)) {
         return FILE_NAME_RESOLVER;
      } else if ("lineNumber".equals(fieldName)) {
         return LINE_NUMBER_RESOLVER;
      } else {
         throw new IllegalArgumentException("unknown field: " + config);
      }
   }

   public void resolve(final StackTraceElement stackTraceElement, final JsonWriter jsonWriter) {
      this.internalResolver.resolve(stackTraceElement, jsonWriter);
   }
}
