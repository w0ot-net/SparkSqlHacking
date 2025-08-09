package org.apache.logging.log4j.layout.template.json.resolver;

import java.util.Optional;
import java.util.regex.Pattern;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;

public final class ThreadContextStackResolver implements EventResolver {
   private final Pattern itemPattern;

   ThreadContextStackResolver(final TemplateResolverConfig config) {
      this.itemPattern = (Pattern)Optional.ofNullable(config.getString("pattern")).map(Pattern::compile).orElse((Object)null);
   }

   static String getName() {
      return "ndc";
   }

   public boolean isResolvable(final LogEvent logEvent) {
      ThreadContext.ContextStack contextStack = logEvent.getContextStack();
      return contextStack.getDepth() > 0;
   }

   public void resolve(final LogEvent logEvent, final JsonWriter jsonWriter) {
      ThreadContext.ContextStack contextStack = logEvent.getContextStack();
      if (contextStack.getDepth() == 0) {
         jsonWriter.writeNull();
      } else {
         boolean arrayStarted = false;

         for(String contextStackItem : contextStack.asList()) {
            boolean matched = this.itemPattern == null || this.itemPattern.matcher(contextStackItem).matches();
            if (matched) {
               if (arrayStarted) {
                  jsonWriter.writeSeparator();
               } else {
                  jsonWriter.writeArrayStart();
                  arrayStarted = true;
               }

               jsonWriter.writeString((CharSequence)contextStackItem);
            }
         }

         if (arrayStarted) {
            jsonWriter.writeArrayEnd();
         } else {
            jsonWriter.writeNull();
         }

      }
   }
}
