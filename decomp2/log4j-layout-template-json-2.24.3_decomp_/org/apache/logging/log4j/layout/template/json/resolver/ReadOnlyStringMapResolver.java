package org.apache.logging.log4j.layout.template.json.resolver;

import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;
import org.apache.logging.log4j.layout.template.json.util.Recycler;
import org.apache.logging.log4j.layout.template.json.util.RecyclerFactory;
import org.apache.logging.log4j.util.ReadOnlyStringMap;
import org.apache.logging.log4j.util.TriConsumer;

class ReadOnlyStringMapResolver implements EventResolver {
   private final EventResolver internalResolver;

   ReadOnlyStringMapResolver(final EventResolverContext context, final TemplateResolverConfig config, final Function mapAccessor) {
      this.internalResolver = createResolver(context, config, mapAccessor);
   }

   private static EventResolver createResolver(final EventResolverContext context, final TemplateResolverConfig config, final Function mapAccessor) {
      Object flattenObject = config.getObject("flatten");
      boolean flatten;
      if (flattenObject == null) {
         flatten = false;
      } else if (flattenObject instanceof Boolean) {
         flatten = (Boolean)flattenObject;
      } else {
         if (!(flattenObject instanceof Map)) {
            throw new IllegalArgumentException("invalid flatten option: " + config);
         }

         flatten = true;
      }

      String prefix = config.getString(new String[]{"flatten", "prefix"});
      String key = config.getString("key");
      if (key != null && flatten) {
         throw new IllegalArgumentException("key and flatten options cannot be combined: " + config);
      } else {
         String pattern = config.getString("pattern");
         if (pattern != null && key != null) {
            throw new IllegalArgumentException("pattern and key options cannot be combined: " + config);
         } else {
            String replacement = config.getString("replacement");
            if (pattern == null && replacement != null) {
               throw new IllegalArgumentException("replacement cannot be provided without a pattern: " + config);
            } else {
               boolean stringified = config.getBoolean("stringified", false);
               if (key != null) {
                  return createKeyResolver(key, stringified, mapAccessor);
               } else {
                  RecyclerFactory recyclerFactory = context.getRecyclerFactory();
                  return createResolver(recyclerFactory, flatten, prefix, pattern, replacement, stringified, mapAccessor);
               }
            }
         }
      }
   }

   private static EventResolver createKeyResolver(final String key, final boolean stringified, final Function mapAccessor) {
      return new EventResolver() {
         public boolean isResolvable(final LogEvent logEvent) {
            ReadOnlyStringMap map = (ReadOnlyStringMap)mapAccessor.apply(logEvent);
            return map != null && map.containsKey(key);
         }

         public void resolve(final LogEvent logEvent, final JsonWriter jsonWriter) {
            ReadOnlyStringMap map = (ReadOnlyStringMap)mapAccessor.apply(logEvent);
            Object value = map == null ? null : map.getValue(key);
            if (stringified) {
               String valueString = String.valueOf(value);
               jsonWriter.writeString((CharSequence)valueString);
            } else {
               jsonWriter.writeValue(value);
            }

         }
      };
   }

   private static EventResolver createResolver(final RecyclerFactory recyclerFactory, final boolean flatten, final String prefix, final String pattern, final String replacement, final boolean stringified, final Function mapAccessor) {
      Pattern compiledPattern = pattern == null ? null : Pattern.compile(pattern);
      Recycler<LoopContext> loopContextRecycler = recyclerFactory.create(() -> {
         LoopContext loopContext = new LoopContext();
         if (prefix != null) {
            loopContext.prefix = prefix;
            loopContext.prefixedKey = new StringBuilder(prefix);
         }

         loopContext.pattern = compiledPattern;
         loopContext.replacement = replacement;
         loopContext.stringified = stringified;
         return loopContext;
      });
      return createResolver(flatten, loopContextRecycler, mapAccessor);
   }

   private static EventResolver createResolver(final boolean flatten, final Recycler loopContextRecycler, final Function mapAccessor) {
      return new EventResolver() {
         public boolean isFlattening() {
            return flatten;
         }

         public boolean isResolvable(final LogEvent logEvent) {
            ReadOnlyStringMap map = (ReadOnlyStringMap)mapAccessor.apply(logEvent);
            return map != null && !map.isEmpty();
         }

         public void resolve(final LogEvent value, final JsonWriter jsonWriter) {
            this.resolve(value, jsonWriter, false);
         }

         public void resolve(final LogEvent logEvent, final JsonWriter jsonWriter, final boolean succeedingEntry) {
            ReadOnlyStringMap map = (ReadOnlyStringMap)mapAccessor.apply(logEvent);
            if (map != null && !map.isEmpty()) {
               if (!flatten) {
                  jsonWriter.writeObjectStart();
               }

               LoopContext loopContext = (LoopContext)loopContextRecycler.acquire();
               loopContext.jsonWriter = jsonWriter;
               loopContext.initJsonWriterStringBuilderLength = jsonWriter.getStringBuilder().length();
               loopContext.succeedingEntry = flatten && succeedingEntry;

               try {
                  map.forEach(ReadOnlyStringMapResolver.LoopMethod.INSTANCE, loopContext);
               } finally {
                  loopContextRecycler.release(loopContext);
               }

               if (!flatten) {
                  jsonWriter.writeObjectEnd();
               }

            } else {
               if (!flatten) {
                  jsonWriter.writeNull();
               }

            }
         }
      };
   }

   public boolean isFlattening() {
      return this.internalResolver.isFlattening();
   }

   public boolean isResolvable(final LogEvent logEvent) {
      return this.internalResolver.isResolvable(logEvent);
   }

   public void resolve(final LogEvent logEvent, final JsonWriter jsonWriter) {
      this.internalResolver.resolve(logEvent, jsonWriter);
   }

   public void resolve(final LogEvent logEvent, final JsonWriter jsonWriter, final boolean succeedingEntry) {
      this.internalResolver.resolve(logEvent, jsonWriter, succeedingEntry);
   }

   private static final class LoopContext {
      private String prefix;
      private StringBuilder prefixedKey;
      private Pattern pattern;
      private String replacement;
      private boolean stringified;
      private JsonWriter jsonWriter;
      private int initJsonWriterStringBuilderLength;
      private boolean succeedingEntry;

      private LoopContext() {
      }
   }

   private static enum LoopMethod implements TriConsumer {
      INSTANCE;

      public void accept(final String key, final Object value, final LoopContext loopContext) {
         Matcher matcher = loopContext.pattern != null ? loopContext.pattern.matcher(key) : null;
         boolean keyMatched = matcher == null || matcher.matches();
         if (keyMatched) {
            String replacedKey = matcher != null && loopContext.replacement != null ? matcher.replaceAll(loopContext.replacement) : key;
            boolean succeedingEntry = loopContext.succeedingEntry || loopContext.initJsonWriterStringBuilderLength < loopContext.jsonWriter.getStringBuilder().length();
            if (succeedingEntry) {
               loopContext.jsonWriter.writeSeparator();
            }

            if (loopContext.prefix == null) {
               loopContext.jsonWriter.writeObjectKey(replacedKey);
            } else {
               loopContext.prefixedKey.setLength(loopContext.prefix.length());
               loopContext.prefixedKey.append(replacedKey);
               loopContext.jsonWriter.writeObjectKey(loopContext.prefixedKey);
            }

            if (loopContext.stringified && !(value instanceof String)) {
               String valueString = String.valueOf(value);
               loopContext.jsonWriter.writeString((CharSequence)valueString);
            } else {
               loopContext.jsonWriter.writeValue(value);
            }
         }

      }

      // $FF: synthetic method
      private static LoopMethod[] $values() {
         return new LoopMethod[]{INSTANCE};
      }
   }
}
