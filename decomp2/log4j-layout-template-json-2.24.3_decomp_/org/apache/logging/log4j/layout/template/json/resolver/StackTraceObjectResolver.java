package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.layout.template.json.util.JsonWriter;

final class StackTraceObjectResolver implements StackTraceResolver {
   private final TemplateResolver stackTraceElementResolver;

   StackTraceObjectResolver(final TemplateResolver stackTraceElementResolver) {
      this.stackTraceElementResolver = stackTraceElementResolver;
   }

   public void resolve(final Throwable throwable, final JsonWriter jsonWriter) {
      StackTraceElement[] stackTraceElements = throwable.getStackTrace();
      if (stackTraceElements.length == 0) {
         jsonWriter.writeNull();
      } else {
         jsonWriter.writeArrayStart();

         for(int stackTraceElementIndex = 0; stackTraceElementIndex < stackTraceElements.length; ++stackTraceElementIndex) {
            if (stackTraceElementIndex > 0) {
               jsonWriter.writeSeparator();
            }

            StackTraceElement stackTraceElement = stackTraceElements[stackTraceElementIndex];
            this.stackTraceElementResolver.resolve(stackTraceElement, jsonWriter);
         }

         jsonWriter.writeArrayEnd();
      }

   }
}
