package org.apache.logging.log4j.layout.template.json.resolver;

import java.util.Locale;
import java.util.function.Function;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.layout.template.json.util.JsonReader;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;

public final class CaseConverterResolver implements EventResolver {
   private final TemplateResolver inputResolver;
   private final Function converter;
   private final ErrorHandlingStrategy errorHandlingStrategy;
   private final TemplateResolver replacementResolver;

   CaseConverterResolver(final EventResolverContext context, final TemplateResolverConfig config) {
      this.inputResolver = createDelegate(context, config);
      this.converter = createConverter(config);
      this.errorHandlingStrategy = readErrorHandlingStrategy(config);
      this.replacementResolver = createReplacement(context, config);
   }

   private static TemplateResolver createDelegate(final EventResolverContext context, final TemplateResolverConfig config) {
      Object delegateObject = config.getObject("input");
      return TemplateResolvers.ofObject(context, delegateObject);
   }

   private static Function createConverter(final TemplateResolverConfig config) {
      Locale locale = config.getLocale("locale");
      String _case = config.getString("case");
      if ("upper".equals(_case)) {
         return (input) -> input.toUpperCase(locale);
      } else if ("lower".equals(_case)) {
         return (input) -> input.toLowerCase(locale);
      } else {
         throw new IllegalArgumentException("invalid case: " + config);
      }
   }

   private static ErrorHandlingStrategy readErrorHandlingStrategy(final TemplateResolverConfig config) {
      String strategyName = config.getString("errorHandlingStrategy");
      if (strategyName == null) {
         return CaseConverterResolver.ErrorHandlingStrategy.REPLACE;
      } else {
         for(ErrorHandlingStrategy strategy : CaseConverterResolver.ErrorHandlingStrategy.values()) {
            if (strategy.name.equals(strategyName)) {
               return strategy;
            }
         }

         throw new IllegalArgumentException("illegal error handling strategy: " + config);
      }
   }

   private static TemplateResolver createReplacement(final EventResolverContext context, final TemplateResolverConfig config) {
      Object replacementObject = config.getObject("replacement");
      return TemplateResolvers.ofObject(context, replacementObject);
   }

   static String getName() {
      return "caseConverter";
   }

   public boolean isFlattening() {
      return this.inputResolver.isFlattening();
   }

   public boolean isResolvable() {
      return this.inputResolver.isResolvable();
   }

   public boolean isResolvable(final LogEvent logEvent) {
      return this.inputResolver.isResolvable(logEvent);
   }

   public void resolve(final LogEvent logEvent, final JsonWriter jsonWriter) {
      int startIndex = jsonWriter.getStringBuilder().length();
      this.inputResolver.resolve(logEvent, jsonWriter);
      this.convertCase(logEvent, jsonWriter, startIndex);
   }

   public void resolve(final LogEvent logEvent, final JsonWriter jsonWriter, final boolean succeedingEntry) {
      int startIndex = jsonWriter.getStringBuilder().length();
      this.inputResolver.resolve(logEvent, jsonWriter, succeedingEntry);
      this.convertCase(logEvent, jsonWriter, startIndex);
   }

   private void convertCase(final LogEvent logEvent, final JsonWriter jsonWriter, final int startIndex) {
      StringBuilder jsonWriterStringBuilder = jsonWriter.getStringBuilder();
      int endIndex = jsonWriterStringBuilder.length();
      boolean stringTyped = startIndex + 1 < endIndex && jsonWriterStringBuilder.charAt(startIndex) == '"' && jsonWriterStringBuilder.charAt(endIndex - 1) == '"';
      if (stringTyped) {
         String json = jsonWriterStringBuilder.substring(startIndex, endIndex);
         this.convertCase(logEvent, jsonWriter, startIndex, json);
      } else {
         if (CaseConverterResolver.ErrorHandlingStrategy.FAIL.equals(this.errorHandlingStrategy)) {
            String json = jsonWriterStringBuilder.substring(startIndex, endIndex);
            throw new RuntimeException("was expecting a string value, found: " + json);
         }

         if (!CaseConverterResolver.ErrorHandlingStrategy.PASS.equals(this.errorHandlingStrategy)) {
            if (!CaseConverterResolver.ErrorHandlingStrategy.REPLACE.equals(this.errorHandlingStrategy)) {
               throw new AssertionError("should not have reached here");
            }

            jsonWriterStringBuilder.setLength(startIndex);
            this.replacementResolver.resolve(logEvent, jsonWriter);
         }
      }

   }

   private void convertCase(final LogEvent logEvent, final JsonWriter jsonWriter, final int startIndex, final String json) {
      StringBuilder jsonWriterStringBuilder = jsonWriter.getStringBuilder();
      String string = (String)JsonReader.read(json);

      String convertedString;
      try {
         convertedString = (String)this.converter.apply(string);
      } catch (Exception error) {
         if (CaseConverterResolver.ErrorHandlingStrategy.FAIL.equals(this.errorHandlingStrategy)) {
            throw new RuntimeException("case conversion failure for string: " + string, error);
         }

         if (CaseConverterResolver.ErrorHandlingStrategy.PASS.equals(this.errorHandlingStrategy)) {
            return;
         }

         if (CaseConverterResolver.ErrorHandlingStrategy.REPLACE.equals(this.errorHandlingStrategy)) {
            jsonWriterStringBuilder.setLength(startIndex);
            this.replacementResolver.resolve(logEvent, jsonWriter);
            return;
         }

         throw new AssertionError("should not have reached here");
      }

      jsonWriterStringBuilder.setLength(startIndex);
      jsonWriter.writeString((CharSequence)convertedString);
   }

   private static enum ErrorHandlingStrategy {
      FAIL("fail"),
      PASS("pass"),
      REPLACE("replace");

      private final String name;

      private ErrorHandlingStrategy(final String name) {
         this.name = name;
      }

      // $FF: synthetic method
      private static ErrorHandlingStrategy[] $values() {
         return new ErrorHandlingStrategy[]{FAIL, PASS, REPLACE};
      }
   }
}
