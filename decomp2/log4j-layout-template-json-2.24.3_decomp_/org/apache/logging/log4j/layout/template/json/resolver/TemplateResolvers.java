package org.apache.logging.log4j.layout.template.json.resolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.logging.log4j.layout.template.json.util.JsonReader;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;

public final class TemplateResolvers {
   private static final String RESOLVER_FIELD_NAME = "$resolver";
   private static final TemplateResolver EMPTY_ARRAY_RESOLVER = new EmptyArrayResolver();
   private static final TemplateResolver EMPTY_OBJECT_RESOLVER = new EmptyObjectResolver();
   private static final TemplateResolver NULL_RESOLVER = new NullResolver();

   private TemplateResolvers() {
   }

   public static TemplateResolver ofTemplate(final TemplateResolverContext context, final String template) {
      Objects.requireNonNull(context, "context");
      Objects.requireNonNull(template, "template");

      Object node;
      try {
         node = JsonReader.read(template);
      } catch (Exception error) {
         String message = String.format("failed parsing template (template=%s)", template);
         throw new RuntimeException(message, error);
      }

      List<? extends TemplateResolverInterceptor<V, C>> interceptors = context.getResolverInterceptors();

      for(int interceptorIndex = 0; interceptorIndex < interceptors.size(); ++interceptorIndex) {
         TemplateResolverInterceptor<V, C> interceptor = (TemplateResolverInterceptor)interceptors.get(interceptorIndex);
         node = interceptor.processTemplateBeforeResolverInjection(context, node);
      }

      return ofObject(context, node);
   }

   static TemplateResolver ofObject(final TemplateResolverContext context, final Object object) {
      if (object == null) {
         TemplateResolver<V> nullResolver = NULL_RESOLVER;
         return nullResolver;
      } else if (object instanceof List) {
         List<Object> list = (List)object;
         return ofList(context, list);
      } else if (object instanceof Map) {
         Map<String, Object> map = (Map)object;
         return ofMap(context, map);
      } else if (object instanceof String) {
         String string = (String)object;
         return ofString(context, string);
      } else if (object instanceof Number) {
         Number number = (Number)object;
         return ofNumber(number);
      } else if (object instanceof Boolean) {
         boolean value = (Boolean)object;
         return ofBoolean(value);
      } else {
         String message = String.format("invalid JSON node type (class=%s)", object.getClass().getName());
         throw new IllegalArgumentException(message);
      }
   }

   private static TemplateResolver ofList(final TemplateResolverContext context, final List list) {
      List<TemplateResolver<V>> itemResolvers = (List)list.stream().map((item) -> {
         TemplateResolver<V> itemResolver = ofObject(context, item);
         if (itemResolver.isFlattening()) {
            throw new IllegalArgumentException("flattening resolvers are not allowed in lists");
         } else {
            return itemResolver;
         }
      }).collect(Collectors.toList());
      if (itemResolvers.isEmpty()) {
         TemplateResolver<V> emptyArrayResolver = EMPTY_ARRAY_RESOLVER;
         return emptyArrayResolver;
      } else {
         return new ArrayResolver(itemResolvers);
      }
   }

   private static TemplateResolver ofMap(final TemplateResolverContext context, final Map map) {
      if (map.containsKey("$resolver")) {
         return ofResolver(context, map);
      } else {
         List<FieldResolverContext<V>> fieldResolverContexts = populateFieldResolverMethods(context, map);
         int fieldCount = fieldResolverContexts.size();
         if (fieldCount == 0) {
            TemplateResolver<V> emptyObjectResolver = EMPTY_OBJECT_RESOLVER;
            return emptyObjectResolver;
         } else {
            return new MapResolver(fieldResolverContexts);
         }
      }
   }

   private static List populateFieldResolverMethods(final TemplateResolverContext context, final Map map) {
      List<String> fieldNames = new ArrayList();
      List<TemplateResolver<V>> fieldResolvers = new ArrayList();
      map.forEach((fieldName, fieldValue) -> {
         TemplateResolver<V> fieldResolver = ofObject(context, fieldValue);
         boolean resolvable = fieldResolver.isResolvable();
         if (resolvable) {
            fieldNames.add(fieldName);
            fieldResolvers.add(fieldResolver);
         }

      });
      List<String> fieldPrefixes = (List)fieldNames.stream().map((fieldName) -> {
         JsonWriter jsonWriter = context.getJsonWriter();

         String var3;
         try {
            jsonWriter.writeString((CharSequence)fieldName);
            jsonWriter.getStringBuilder().append(':');
            var3 = jsonWriter.getStringBuilder().toString();
         } catch (Throwable var6) {
            if (jsonWriter != null) {
               try {
                  jsonWriter.close();
               } catch (Throwable var5) {
                  var6.addSuppressed(var5);
               }
            }

            throw var6;
         }

         if (jsonWriter != null) {
            jsonWriter.close();
         }

         return var3;
      }).collect(Collectors.toList());
      int fieldCount = fieldNames.size();
      return (List)IntStream.range(0, fieldCount).mapToObj((fieldIndex) -> {
         TemplateResolver<V> fieldResolver = (TemplateResolver)fieldResolvers.get(fieldIndex);
         boolean flattening = fieldResolver.isFlattening();
         FieldResolverMethod<V> fieldResolverMethod;
         if (flattening) {
            fieldResolverMethod = new FlatteningFieldResolverMethod(fieldResolver);
         } else {
            String fieldPrefix = (String)fieldPrefixes.get(fieldIndex);
            fieldResolverMethod = new PrefixedFieldResolverMethod(fieldPrefix, fieldResolver);
         }

         return new FieldResolverContext(fieldResolver, fieldResolverMethod);
      }).collect(Collectors.toList());
   }

   private static TemplateResolver ofResolver(final TemplateResolverContext context, final Map configMap) {
      Objects.requireNonNull(context, "context");
      Objects.requireNonNull(configMap, "configMap");
      Object resolverNameObject = configMap.get("$resolver");
      if (!(resolverNameObject instanceof String)) {
         throw new IllegalArgumentException("invalid resolver name: " + resolverNameObject);
      } else {
         String resolverName = (String)resolverNameObject;
         TemplateResolverFactory<V, C> resolverFactory = (TemplateResolverFactory)context.getResolverFactoryByName().get(resolverName);
         if (resolverFactory == null) {
            throw new IllegalArgumentException("unknown resolver: " + resolverName);
         } else {
            TemplateResolverConfig resolverConfig = new TemplateResolverConfig(configMap);
            return resolverFactory.create(context, resolverConfig);
         }
      }
   }

   private static TemplateResolver ofString(final TemplateResolverContext context, final String fieldValue) {
      boolean substitutionNeeded = fieldValue.contains("${");
      JsonWriter contextJsonWriter = context.getJsonWriter();
      if (substitutionNeeded) {
         TemplateResolverStringSubstitutor<V> substitutor = context.getSubstitutor();
         if (substitutor.isStable()) {
            String replacedText = substitutor.replace((Object)null, fieldValue);
            if (replacedText == null) {
               TemplateResolver<V> resolver = NULL_RESOLVER;
               return resolver;
            } else {
               String escapedReplacedText = contextJsonWriter.use(() -> contextJsonWriter.writeString((CharSequence)replacedText));
               return new RawStringResolver(escapedReplacedText);
            }
         } else {
            return new SubstitutingStringResolver(substitutor, fieldValue);
         }
      } else {
         String escapedFieldValue = contextJsonWriter.use(() -> contextJsonWriter.writeString((CharSequence)fieldValue));
         return new RawStringResolver(escapedFieldValue);
      }
   }

   private static TemplateResolver ofNumber(final Number number) {
      return new NumberResolver(number);
   }

   private static TemplateResolver ofBoolean(final boolean value) {
      return new BooleanResolver(value);
   }

   private abstract static class UnresolvableTemplateResolver implements TemplateResolver {
      private UnresolvableTemplateResolver() {
      }

      public final boolean isResolvable() {
         return false;
      }

      public final boolean isResolvable(final Object value) {
         return false;
      }
   }

   private static final class EmptyArrayResolver extends UnresolvableTemplateResolver {
      private EmptyArrayResolver() {
      }

      public void resolve(final Object value, final JsonWriter jsonWriter) {
         jsonWriter.writeArrayStart();
         jsonWriter.writeArrayEnd();
      }
   }

   private static final class EmptyObjectResolver extends UnresolvableTemplateResolver {
      private EmptyObjectResolver() {
      }

      public void resolve(final Object value, final JsonWriter jsonWriter) {
         jsonWriter.writeObjectStart();
         jsonWriter.writeObjectEnd();
      }
   }

   private static final class NullResolver extends UnresolvableTemplateResolver {
      private NullResolver() {
      }

      public void resolve(final Object value, final JsonWriter jsonWriter) {
         jsonWriter.writeNull();
      }
   }

   private static final class ArrayResolver implements TemplateResolver {
      private final List itemResolvers;

      private ArrayResolver(final List itemResolvers) {
         this.itemResolvers = itemResolvers;
      }

      public void resolve(final Object value, final JsonWriter jsonWriter) {
         jsonWriter.writeArrayStart();

         for(int itemResolverIndex = 0; itemResolverIndex < this.itemResolvers.size(); ++itemResolverIndex) {
            if (itemResolverIndex > 0) {
               jsonWriter.writeSeparator();
            }

            TemplateResolver<V> itemResolver = (TemplateResolver)this.itemResolvers.get(itemResolverIndex);
            itemResolver.resolve(value, jsonWriter);
         }

         jsonWriter.writeArrayEnd();
      }
   }

   private static final class FieldResolverContext {
      private final TemplateResolver resolver;
      private final FieldResolverMethod resolverMethod;

      private FieldResolverContext(final TemplateResolver resolver, final FieldResolverMethod resolverMethod) {
         this.resolver = resolver;
         this.resolverMethod = resolverMethod;
      }
   }

   private static final class FlatteningFieldResolverMethod implements FieldResolverMethod {
      private final TemplateResolver fieldResolver;

      private FlatteningFieldResolverMethod(final TemplateResolver fieldResolver) {
         this.fieldResolver = fieldResolver;
      }

      public boolean resolve(final Object value, final JsonWriter jsonWriter, final boolean succeedingEntry) {
         boolean resolvable = this.fieldResolver.isResolvable(value);
         if (!resolvable) {
            return false;
         } else {
            StringBuilder jsonWriterStringBuilder = jsonWriter.getStringBuilder();
            int initLength = jsonWriterStringBuilder.length();
            this.fieldResolver.resolve(value, jsonWriter, succeedingEntry);
            return jsonWriterStringBuilder.length() > initLength;
         }
      }
   }

   private static final class PrefixedFieldResolverMethod implements FieldResolverMethod {
      private final String fieldPrefix;
      private final TemplateResolver fieldResolver;

      private PrefixedFieldResolverMethod(final String fieldPrefix, final TemplateResolver fieldResolver) {
         this.fieldPrefix = fieldPrefix;
         this.fieldResolver = fieldResolver;
      }

      public boolean resolve(final Object value, final JsonWriter jsonWriter, final boolean succeedingEntry) {
         boolean resolvable = this.fieldResolver.isResolvable(value);
         if (!resolvable) {
            return false;
         } else {
            if (succeedingEntry) {
               jsonWriter.writeSeparator();
            }

            jsonWriter.writeRawString((CharSequence)this.fieldPrefix);
            this.fieldResolver.resolve(value, jsonWriter, succeedingEntry);
            return true;
         }
      }
   }

   private static final class MapResolver implements TemplateResolver {
      private final List fieldResolverContexts;

      private MapResolver(final List fieldResolverContexts) {
         this.fieldResolverContexts = fieldResolverContexts;
      }

      public boolean isResolvable() {
         return true;
      }

      public boolean isResolvable(final Object value) {
         int fieldCount = this.fieldResolverContexts.size();

         for(int fieldIndex = 0; fieldIndex < fieldCount; ++fieldIndex) {
            TemplateResolver<V> fieldResolver = ((FieldResolverContext)this.fieldResolverContexts.get(fieldIndex)).resolver;
            boolean resolvable = fieldResolver.isResolvable(value);
            if (resolvable) {
               return true;
            }
         }

         return false;
      }

      public void resolve(final Object value, final JsonWriter jsonWriter) {
         jsonWriter.writeObjectStart();
         int fieldCount = this.fieldResolverContexts.size();
         int resolvedFieldCount = 0;

         for(int fieldIndex = 0; fieldIndex < fieldCount; ++fieldIndex) {
            FieldResolverContext<V> fieldResolverContext = (FieldResolverContext)this.fieldResolverContexts.get(fieldIndex);
            boolean resolvable = fieldResolverContext.resolver.isResolvable(value);
            if (resolvable) {
               boolean succeedingEntry = resolvedFieldCount > 0;
               boolean resolved = fieldResolverContext.resolverMethod.resolve(value, jsonWriter, succeedingEntry);
               if (resolved) {
                  ++resolvedFieldCount;
               }
            }
         }

         jsonWriter.writeObjectEnd();
      }
   }

   private static final class SubstitutingStringResolver implements TemplateResolver {
      private final TemplateResolverStringSubstitutor substitutor;
      private final String string;

      private SubstitutingStringResolver(final TemplateResolverStringSubstitutor substitutor, final String string) {
         this.substitutor = substitutor;
         this.string = string;
      }

      public void resolve(final Object value, final JsonWriter jsonWriter) {
         String replacedString = this.substitutor.replace(value, this.string);
         jsonWriter.writeString((CharSequence)replacedString);
      }
   }

   private static final class RawStringResolver implements TemplateResolver {
      private final String rawString;

      private RawStringResolver(final String rawString) {
         this.rawString = rawString;
      }

      public void resolve(final Object ignored, final JsonWriter jsonWriter) {
         jsonWriter.writeRawString((CharSequence)this.rawString);
      }
   }

   private static final class NumberResolver implements TemplateResolver {
      private final String numberString;

      private NumberResolver(final Number number) {
         this.numberString = String.valueOf(number);
      }

      public void resolve(final Object ignored, final JsonWriter jsonWriter) {
         jsonWriter.writeRawString((CharSequence)this.numberString);
      }
   }

   private static final class BooleanResolver implements TemplateResolver {
      private final boolean value;

      private BooleanResolver(final boolean value) {
         this.value = value;
      }

      public void resolve(final Object ignored, final JsonWriter jsonWriter) {
         jsonWriter.writeBoolean(this.value);
      }
   }

   @FunctionalInterface
   private interface FieldResolverMethod {
      boolean resolve(Object value, JsonWriter jsonWriter, boolean succeedingEntry);
   }
}
