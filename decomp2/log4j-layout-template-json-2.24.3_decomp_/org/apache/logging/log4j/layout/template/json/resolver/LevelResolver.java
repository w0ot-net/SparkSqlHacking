package org.apache.logging.log4j.layout.template.json.resolver;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.net.Severity;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;

public final class LevelResolver implements EventResolver {
   private static final String[] SEVERITY_CODE_RESOLUTION_BY_STANDARD_LEVEL_ORDINAL;
   private static final EventResolver SEVERITY_CODE_RESOLVER;
   private final EventResolver internalResolver;

   LevelResolver(final EventResolverContext context, final TemplateResolverConfig config) {
      this.internalResolver = createResolver(context, config);
   }

   private static EventResolver createResolver(final EventResolverContext context, final TemplateResolverConfig config) {
      JsonWriter jsonWriter = context.getJsonWriter();
      String fieldName = config.getString("field");
      if ("name".equals(fieldName)) {
         return createNameResolver(jsonWriter);
      } else if ("severity".equals(fieldName)) {
         String severityFieldName = config.getString(new String[]{"severity", "field"});
         if ("keyword".equals(severityFieldName)) {
            return createSeverityKeywordResolver(jsonWriter);
         } else if ("code".equals(severityFieldName)) {
            return SEVERITY_CODE_RESOLVER;
         } else {
            throw new IllegalArgumentException("unknown severity field: " + config);
         }
      } else {
         throw new IllegalArgumentException("unknown field: " + config);
      }
   }

   private static EventResolver createNameResolver(final JsonWriter contextJsonWriter) {
      Map<Level, String> resolutionByLevel = (Map)Arrays.stream(Level.values()).collect(Collectors.toMap(Function.identity(), (level) -> contextJsonWriter.use(() -> {
            String name = level.name();
            contextJsonWriter.writeString((CharSequence)name);
         })));
      return (logEvent, jsonWriter) -> {
         Level level = logEvent.getLevel();
         String resolution = (String)resolutionByLevel.get(level);
         if (resolution != null) {
            jsonWriter.writeRawString((CharSequence)resolution);
         } else {
            String levelName = level.name();
            jsonWriter.writeString((CharSequence)levelName);
         }

      };
   }

   private static EventResolver createSeverityKeywordResolver(final JsonWriter contextJsonWriter) {
      Map<Level, String> resolutionByLevel = (Map)Arrays.stream(Level.values()).collect(Collectors.toMap(Function.identity(), (level) -> contextJsonWriter.use(() -> {
            String severityKeyword = Severity.getSeverity(level).name();
            contextJsonWriter.writeString((CharSequence)severityKeyword);
         })));
      return (logEvent, jsonWriter) -> {
         Level level = logEvent.getLevel();
         String resolution = (String)resolutionByLevel.get(level);
         if (resolution != null) {
            jsonWriter.writeRawString((CharSequence)resolution);
         } else {
            String severityKeyword = Severity.getSeverity(level).name();
            jsonWriter.writeString((CharSequence)severityKeyword);
         }

      };
   }

   static String getName() {
      return "level";
   }

   public void resolve(final LogEvent logEvent, final JsonWriter jsonWriter) {
      this.internalResolver.resolve(logEvent, jsonWriter);
   }

   static {
      int levelCount = Level.values().length;
      String[] severityCodeResolutionByStandardLevelOrdinal = new String[levelCount + 1];

      for(Level level : Level.values()) {
         int standardLevelOrdinal = level.getStandardLevel().ordinal();
         int severityCode = Severity.getSeverity(level).getCode();
         severityCodeResolutionByStandardLevelOrdinal[standardLevelOrdinal] = String.valueOf(severityCode);
      }

      SEVERITY_CODE_RESOLUTION_BY_STANDARD_LEVEL_ORDINAL = severityCodeResolutionByStandardLevelOrdinal;
      SEVERITY_CODE_RESOLVER = (logEvent, jsonWriter) -> {
         Level level = logEvent.getLevel();
         int standardLevelOrdinal = level.getStandardLevel().ordinal();
         String severityCodeResolution = SEVERITY_CODE_RESOLUTION_BY_STANDARD_LEVEL_ORDINAL[standardLevelOrdinal];
         if (severityCodeResolution != null) {
            jsonWriter.writeRawString((CharSequence)severityCodeResolution);
         } else {
            int severityCode = Severity.getSeverity(level).getCode();
            jsonWriter.writeNumber(severityCode);
         }

      };
   }
}
