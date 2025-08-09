package org.apache.logging.log4j.layout.template.json.resolver;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;
import org.apache.logging.log4j.status.StatusLogger;

public class ExceptionResolver implements EventResolver {
   private static final Logger LOGGER = StatusLogger.getLogger();
   private static final EventResolver NULL_RESOLVER = (ignored, jsonGenerator) -> jsonGenerator.writeNull();
   private final boolean stackTraceEnabled;
   private final EventResolver internalResolver;
   private static final Map STACK_TRACE_ELEMENT_RESOLVER_FACTORY_BY_NAME;

   ExceptionResolver(final EventResolverContext context, final TemplateResolverConfig config) {
      this.stackTraceEnabled = context.isStackTraceEnabled();
      this.internalResolver = this.createInternalResolver(context, config);
   }

   EventResolver createInternalResolver(final EventResolverContext context, final TemplateResolverConfig config) {
      String fieldName = config.getString("field");
      if ("className".equals(fieldName)) {
         return this.createClassNameResolver();
      } else if ("message".equals(fieldName)) {
         return this.createMessageResolver();
      } else if ("stackTrace".equals(fieldName)) {
         return this.createStackTraceResolver(context, config);
      } else {
         throw new IllegalArgumentException("unknown field: " + config);
      }
   }

   private EventResolver createClassNameResolver() {
      return (logEvent, jsonWriter) -> {
         Throwable exception = this.extractThrowable(logEvent);
         if (exception == null) {
            jsonWriter.writeNull();
         } else {
            String exceptionClassName = exception.getClass().getCanonicalName();
            jsonWriter.writeString((CharSequence)exceptionClassName);
         }

      };
   }

   private EventResolver createMessageResolver() {
      return (logEvent, jsonWriter) -> {
         Throwable exception = this.extractThrowable(logEvent);
         if (exception == null) {
            jsonWriter.writeNull();
         } else {
            String exceptionMessage = exception.getMessage();
            jsonWriter.writeString((CharSequence)exceptionMessage);
         }

      };
   }

   private EventResolver createStackTraceResolver(final EventResolverContext context, final TemplateResolverConfig config) {
      if (!context.isStackTraceEnabled()) {
         return NULL_RESOLVER;
      } else {
         boolean stringified = isStackTraceStringified(config);
         return stringified ? this.createStackTraceStringResolver(context, config) : this.createStackTraceObjectResolver(context, config);
      }
   }

   private static boolean isStackTraceStringified(final TemplateResolverConfig config) {
      Boolean stringifiedOld = config.getBoolean("stringified");
      if (stringifiedOld != null) {
         LOGGER.warn("\"stringified\" flag at the root level of an exception [root cause] resolver is deprecated in favor of \"stackTrace.stringified\"");
      }

      Object stringifiedNew = config.getObject(new String[]{"stackTrace", "stringified"});
      if (stringifiedOld == null && stringifiedNew == null) {
         return false;
      } else if (stringifiedNew == null) {
         return stringifiedOld;
      } else {
         return !(stringifiedNew instanceof Boolean) || (Boolean)stringifiedNew;
      }
   }

   private EventResolver createStackTraceStringResolver(final EventResolverContext context, final TemplateResolverConfig config) {
      String truncationSuffix = readTruncationSuffix(context, config);
      List<String> truncationPointMatcherStrings = readTruncationPointMatcherStrings(config);
      List<String> truncationPointMatcherRegexes = readTruncationPointMatcherRegexes(config);
      StackTraceStringResolver resolver = new StackTraceStringResolver(context, truncationSuffix, truncationPointMatcherStrings, truncationPointMatcherRegexes);
      return (logEvent, jsonWriter) -> {
         Throwable exception = this.extractThrowable(logEvent);
         if (exception == null) {
            jsonWriter.writeNull();
         } else {
            resolver.resolve(exception, jsonWriter);
         }

      };
   }

   private static String readTruncationSuffix(final EventResolverContext context, final TemplateResolverConfig config) {
      String suffix = config.getString(new String[]{"stackTrace", "stringified", "truncation", "suffix"});
      return suffix != null ? suffix : context.getTruncatedStringSuffix();
   }

   private static List readTruncationPointMatcherStrings(final TemplateResolverConfig config) {
      List<String> strings = config.getList(new String[]{"stackTrace", "stringified", "truncation", "pointMatcherStrings"}, String.class);
      if (strings == null) {
         strings = Collections.emptyList();
      }

      return strings;
   }

   private static List readTruncationPointMatcherRegexes(final TemplateResolverConfig config) {
      List<String> regexes = config.getList(new String[]{"stackTrace", "stringified", "truncation", "pointMatcherRegexes"}, String.class);
      if (regexes == null) {
         regexes = Collections.emptyList();
      }

      for(int i = 0; i < regexes.size(); ++i) {
         String regex = (String)regexes.get(i);

         try {
            Pattern.compile(regex);
         } catch (PatternSyntaxException error) {
            String message = String.format("invalid truncation point matcher regex at index %d: %s", i, regex);
            throw new IllegalArgumentException(message, error);
         }
      }

      return regexes;
   }

   private EventResolver createStackTraceObjectResolver(final EventResolverContext context, final TemplateResolverConfig config) {
      TemplateResolver<StackTraceElement> stackTraceElementResolver = createStackTraceElementResolver(context, config);
      StackTraceObjectResolver stackTraceResolver = new StackTraceObjectResolver(stackTraceElementResolver);
      return (logEvent, jsonWriter) -> {
         Throwable throwable = this.extractThrowable(logEvent);
         if (throwable == null) {
            jsonWriter.writeNull();
         } else {
            stackTraceResolver.resolve(throwable, jsonWriter);
         }

      };
   }

   private static TemplateResolver createStackTraceElementResolver(final EventResolverContext context, final TemplateResolverConfig config) {
      StackTraceElementResolverStringSubstitutor substitutor = new StackTraceElementResolverStringSubstitutor(context.getSubstitutor().getInternalSubstitutor());
      StackTraceElementResolverContext stackTraceElementResolverContext = StackTraceElementResolverContext.newBuilder().setResolverFactoryByName(STACK_TRACE_ELEMENT_RESOLVER_FACTORY_BY_NAME).setSubstitutor(substitutor).setJsonWriter(context.getJsonWriter()).build();
      String stackTraceElementTemplate = findEffectiveStackTraceElementTemplate(context, config);
      return TemplateResolvers.ofTemplate(stackTraceElementResolverContext, stackTraceElementTemplate);
   }

   private static String findEffectiveStackTraceElementTemplate(final EventResolverContext context, final TemplateResolverConfig config) {
      Object stackTraceElementTemplateObject = config.getObject(new String[]{"stackTrace", "elementTemplate"});
      if (stackTraceElementTemplateObject != null) {
         JsonWriter jsonWriter = context.getJsonWriter();
         return jsonWriter.use(() -> jsonWriter.writeValue(stackTraceElementTemplateObject));
      } else {
         return context.getStackTraceElementTemplate();
      }
   }

   Throwable extractThrowable(final LogEvent logEvent) {
      return logEvent.getThrown();
   }

   static String getName() {
      return "exception";
   }

   public boolean isResolvable() {
      return this.stackTraceEnabled;
   }

   public boolean isResolvable(final LogEvent logEvent) {
      return this.stackTraceEnabled && logEvent.getThrown() != null;
   }

   public void resolve(final LogEvent logEvent, final JsonWriter jsonWriter) {
      this.internalResolver.resolve(logEvent, jsonWriter);
   }

   static {
      StackTraceElementResolverFactory stackTraceElementResolverFactory = StackTraceElementResolverFactory.getInstance();
      STACK_TRACE_ELEMENT_RESOLVER_FACTORY_BY_NAME = Collections.singletonMap(stackTraceElementResolverFactory.getName(), stackTraceElementResolverFactory);
   }
}
