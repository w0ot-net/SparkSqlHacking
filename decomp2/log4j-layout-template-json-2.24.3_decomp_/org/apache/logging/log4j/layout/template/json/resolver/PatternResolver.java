package org.apache.logging.log4j.layout.template.json.resolver;

import java.util.Optional;
import java.util.function.BiConsumer;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;
import org.apache.logging.log4j.util.Strings;

public final class PatternResolver implements EventResolver {
   private final BiConsumer emitter;

   PatternResolver(final EventResolverContext context, final TemplateResolverConfig config) {
      String pattern = config.getString("pattern");
      if (Strings.isBlank(pattern)) {
         throw new IllegalArgumentException("blank pattern: " + config);
      } else {
         boolean stackTraceEnabled = (Boolean)Optional.ofNullable(config.getBoolean("stackTraceEnabled")).orElse(context.isStackTraceEnabled());
         PatternLayout patternLayout = PatternLayout.newBuilder().withConfiguration(context.getConfiguration()).withCharset(context.getCharset()).withPattern(pattern).withAlwaysWriteExceptions(stackTraceEnabled).build();
         this.emitter = (stringBuilder, logEvent) -> patternLayout.serialize(logEvent, stringBuilder);
      }
   }

   static String getName() {
      return "pattern";
   }

   public void resolve(final LogEvent logEvent, final JsonWriter jsonWriter) {
      jsonWriter.writeString(this.emitter, logEvent);
   }
}
