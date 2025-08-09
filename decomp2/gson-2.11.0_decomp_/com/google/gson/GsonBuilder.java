package com.google.gson;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.InlineMe;
import com.google.gson.internal.$Gson$Preconditions;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.DefaultDateTypeAdapter;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.internal.sql.SqlTypesSupport;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class GsonBuilder {
   private Excluder excluder;
   private LongSerializationPolicy longSerializationPolicy;
   private FieldNamingStrategy fieldNamingPolicy;
   private final Map instanceCreators;
   private final List factories;
   private final List hierarchyFactories;
   private boolean serializeNulls;
   private String datePattern;
   private int dateStyle;
   private int timeStyle;
   private boolean complexMapKeySerialization;
   private boolean serializeSpecialFloatingPointValues;
   private boolean escapeHtmlChars;
   private FormattingStyle formattingStyle;
   private boolean generateNonExecutableJson;
   private Strictness strictness;
   private boolean useJdkUnsafe;
   private ToNumberStrategy objectToNumberStrategy;
   private ToNumberStrategy numberToNumberStrategy;
   private final ArrayDeque reflectionFilters;

   public GsonBuilder() {
      this.excluder = Excluder.DEFAULT;
      this.longSerializationPolicy = LongSerializationPolicy.DEFAULT;
      this.fieldNamingPolicy = FieldNamingPolicy.IDENTITY;
      this.instanceCreators = new HashMap();
      this.factories = new ArrayList();
      this.hierarchyFactories = new ArrayList();
      this.serializeNulls = false;
      this.datePattern = Gson.DEFAULT_DATE_PATTERN;
      this.dateStyle = 2;
      this.timeStyle = 2;
      this.complexMapKeySerialization = false;
      this.serializeSpecialFloatingPointValues = false;
      this.escapeHtmlChars = true;
      this.formattingStyle = Gson.DEFAULT_FORMATTING_STYLE;
      this.generateNonExecutableJson = false;
      this.strictness = Gson.DEFAULT_STRICTNESS;
      this.useJdkUnsafe = true;
      this.objectToNumberStrategy = Gson.DEFAULT_OBJECT_TO_NUMBER_STRATEGY;
      this.numberToNumberStrategy = Gson.DEFAULT_NUMBER_TO_NUMBER_STRATEGY;
      this.reflectionFilters = new ArrayDeque();
   }

   GsonBuilder(Gson gson) {
      this.excluder = Excluder.DEFAULT;
      this.longSerializationPolicy = LongSerializationPolicy.DEFAULT;
      this.fieldNamingPolicy = FieldNamingPolicy.IDENTITY;
      this.instanceCreators = new HashMap();
      this.factories = new ArrayList();
      this.hierarchyFactories = new ArrayList();
      this.serializeNulls = false;
      this.datePattern = Gson.DEFAULT_DATE_PATTERN;
      this.dateStyle = 2;
      this.timeStyle = 2;
      this.complexMapKeySerialization = false;
      this.serializeSpecialFloatingPointValues = false;
      this.escapeHtmlChars = true;
      this.formattingStyle = Gson.DEFAULT_FORMATTING_STYLE;
      this.generateNonExecutableJson = false;
      this.strictness = Gson.DEFAULT_STRICTNESS;
      this.useJdkUnsafe = true;
      this.objectToNumberStrategy = Gson.DEFAULT_OBJECT_TO_NUMBER_STRATEGY;
      this.numberToNumberStrategy = Gson.DEFAULT_NUMBER_TO_NUMBER_STRATEGY;
      this.reflectionFilters = new ArrayDeque();
      this.excluder = gson.excluder;
      this.fieldNamingPolicy = gson.fieldNamingStrategy;
      this.instanceCreators.putAll(gson.instanceCreators);
      this.serializeNulls = gson.serializeNulls;
      this.complexMapKeySerialization = gson.complexMapKeySerialization;
      this.generateNonExecutableJson = gson.generateNonExecutableJson;
      this.escapeHtmlChars = gson.htmlSafe;
      this.formattingStyle = gson.formattingStyle;
      this.strictness = gson.strictness;
      this.serializeSpecialFloatingPointValues = gson.serializeSpecialFloatingPointValues;
      this.longSerializationPolicy = gson.longSerializationPolicy;
      this.datePattern = gson.datePattern;
      this.dateStyle = gson.dateStyle;
      this.timeStyle = gson.timeStyle;
      this.factories.addAll(gson.builderFactories);
      this.hierarchyFactories.addAll(gson.builderHierarchyFactories);
      this.useJdkUnsafe = gson.useJdkUnsafe;
      this.objectToNumberStrategy = gson.objectToNumberStrategy;
      this.numberToNumberStrategy = gson.numberToNumberStrategy;
      this.reflectionFilters.addAll(gson.reflectionFilters);
   }

   @CanIgnoreReturnValue
   public GsonBuilder setVersion(double version) {
      if (!Double.isNaN(version) && !(version < (double)0.0F)) {
         this.excluder = this.excluder.withVersion(version);
         return this;
      } else {
         throw new IllegalArgumentException("Invalid version: " + version);
      }
   }

   @CanIgnoreReturnValue
   public GsonBuilder excludeFieldsWithModifiers(int... modifiers) {
      Objects.requireNonNull(modifiers);
      this.excluder = this.excluder.withModifiers(modifiers);
      return this;
   }

   @CanIgnoreReturnValue
   public GsonBuilder generateNonExecutableJson() {
      this.generateNonExecutableJson = true;
      return this;
   }

   @CanIgnoreReturnValue
   public GsonBuilder excludeFieldsWithoutExposeAnnotation() {
      this.excluder = this.excluder.excludeFieldsWithoutExposeAnnotation();
      return this;
   }

   @CanIgnoreReturnValue
   public GsonBuilder serializeNulls() {
      this.serializeNulls = true;
      return this;
   }

   @CanIgnoreReturnValue
   public GsonBuilder enableComplexMapKeySerialization() {
      this.complexMapKeySerialization = true;
      return this;
   }

   @CanIgnoreReturnValue
   public GsonBuilder disableInnerClassSerialization() {
      this.excluder = this.excluder.disableInnerClassSerialization();
      return this;
   }

   @CanIgnoreReturnValue
   public GsonBuilder setLongSerializationPolicy(LongSerializationPolicy serializationPolicy) {
      this.longSerializationPolicy = (LongSerializationPolicy)Objects.requireNonNull(serializationPolicy);
      return this;
   }

   @CanIgnoreReturnValue
   public GsonBuilder setFieldNamingPolicy(FieldNamingPolicy namingConvention) {
      return this.setFieldNamingStrategy(namingConvention);
   }

   @CanIgnoreReturnValue
   public GsonBuilder setFieldNamingStrategy(FieldNamingStrategy fieldNamingStrategy) {
      this.fieldNamingPolicy = (FieldNamingStrategy)Objects.requireNonNull(fieldNamingStrategy);
      return this;
   }

   @CanIgnoreReturnValue
   public GsonBuilder setObjectToNumberStrategy(ToNumberStrategy objectToNumberStrategy) {
      this.objectToNumberStrategy = (ToNumberStrategy)Objects.requireNonNull(objectToNumberStrategy);
      return this;
   }

   @CanIgnoreReturnValue
   public GsonBuilder setNumberToNumberStrategy(ToNumberStrategy numberToNumberStrategy) {
      this.numberToNumberStrategy = (ToNumberStrategy)Objects.requireNonNull(numberToNumberStrategy);
      return this;
   }

   @CanIgnoreReturnValue
   public GsonBuilder setExclusionStrategies(ExclusionStrategy... strategies) {
      Objects.requireNonNull(strategies);

      for(ExclusionStrategy strategy : strategies) {
         this.excluder = this.excluder.withExclusionStrategy(strategy, true, true);
      }

      return this;
   }

   @CanIgnoreReturnValue
   public GsonBuilder addSerializationExclusionStrategy(ExclusionStrategy strategy) {
      Objects.requireNonNull(strategy);
      this.excluder = this.excluder.withExclusionStrategy(strategy, true, false);
      return this;
   }

   @CanIgnoreReturnValue
   public GsonBuilder addDeserializationExclusionStrategy(ExclusionStrategy strategy) {
      Objects.requireNonNull(strategy);
      this.excluder = this.excluder.withExclusionStrategy(strategy, false, true);
      return this;
   }

   @CanIgnoreReturnValue
   public GsonBuilder setPrettyPrinting() {
      return this.setFormattingStyle(FormattingStyle.PRETTY);
   }

   @CanIgnoreReturnValue
   public GsonBuilder setFormattingStyle(FormattingStyle formattingStyle) {
      this.formattingStyle = (FormattingStyle)Objects.requireNonNull(formattingStyle);
      return this;
   }

   /** @deprecated */
   @Deprecated
   @InlineMe(
      replacement = "this.setStrictness(Strictness.LENIENT)",
      imports = {"com.google.gson.Strictness"}
   )
   @CanIgnoreReturnValue
   public GsonBuilder setLenient() {
      return this.setStrictness(Strictness.LENIENT);
   }

   @CanIgnoreReturnValue
   public GsonBuilder setStrictness(Strictness strictness) {
      this.strictness = (Strictness)Objects.requireNonNull(strictness);
      return this;
   }

   @CanIgnoreReturnValue
   public GsonBuilder disableHtmlEscaping() {
      this.escapeHtmlChars = false;
      return this;
   }

   @CanIgnoreReturnValue
   public GsonBuilder setDateFormat(String pattern) {
      if (pattern != null) {
         try {
            new SimpleDateFormat(pattern);
         } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The date pattern '" + pattern + "' is not valid", e);
         }
      }

      this.datePattern = pattern;
      return this;
   }

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   public GsonBuilder setDateFormat(int dateStyle) {
      this.dateStyle = checkDateFormatStyle(dateStyle);
      this.datePattern = null;
      return this;
   }

   @CanIgnoreReturnValue
   public GsonBuilder setDateFormat(int dateStyle, int timeStyle) {
      this.dateStyle = checkDateFormatStyle(dateStyle);
      this.timeStyle = checkDateFormatStyle(timeStyle);
      this.datePattern = null;
      return this;
   }

   private static int checkDateFormatStyle(int style) {
      if (style >= 0 && style <= 3) {
         return style;
      } else {
         throw new IllegalArgumentException("Invalid style: " + style);
      }
   }

   @CanIgnoreReturnValue
   public GsonBuilder registerTypeAdapter(Type type, Object typeAdapter) {
      Objects.requireNonNull(type);
      $Gson$Preconditions.checkArgument(typeAdapter instanceof JsonSerializer || typeAdapter instanceof JsonDeserializer || typeAdapter instanceof InstanceCreator || typeAdapter instanceof TypeAdapter);
      if (isTypeObjectOrJsonElement(type)) {
         throw new IllegalArgumentException("Cannot override built-in adapter for " + type);
      } else {
         if (typeAdapter instanceof InstanceCreator) {
            this.instanceCreators.put(type, (InstanceCreator)typeAdapter);
         }

         if (typeAdapter instanceof JsonSerializer || typeAdapter instanceof JsonDeserializer) {
            TypeToken<?> typeToken = TypeToken.get(type);
            this.factories.add(TreeTypeAdapter.newFactoryWithMatchRawType(typeToken, typeAdapter));
         }

         if (typeAdapter instanceof TypeAdapter) {
            TypeAdapterFactory factory = TypeAdapters.newFactory(TypeToken.get(type), (TypeAdapter)typeAdapter);
            this.factories.add(factory);
         }

         return this;
      }
   }

   private static boolean isTypeObjectOrJsonElement(Type type) {
      return type instanceof Class && (type == Object.class || JsonElement.class.isAssignableFrom((Class)type));
   }

   @CanIgnoreReturnValue
   public GsonBuilder registerTypeAdapterFactory(TypeAdapterFactory factory) {
      Objects.requireNonNull(factory);
      this.factories.add(factory);
      return this;
   }

   @CanIgnoreReturnValue
   public GsonBuilder registerTypeHierarchyAdapter(Class baseType, Object typeAdapter) {
      Objects.requireNonNull(baseType);
      $Gson$Preconditions.checkArgument(typeAdapter instanceof JsonSerializer || typeAdapter instanceof JsonDeserializer || typeAdapter instanceof TypeAdapter);
      if (JsonElement.class.isAssignableFrom(baseType)) {
         throw new IllegalArgumentException("Cannot override built-in adapter for " + baseType);
      } else {
         if (typeAdapter instanceof JsonDeserializer || typeAdapter instanceof JsonSerializer) {
            this.hierarchyFactories.add(TreeTypeAdapter.newTypeHierarchyFactory(baseType, typeAdapter));
         }

         if (typeAdapter instanceof TypeAdapter) {
            TypeAdapterFactory factory = TypeAdapters.newTypeHierarchyFactory(baseType, (TypeAdapter)typeAdapter);
            this.factories.add(factory);
         }

         return this;
      }
   }

   @CanIgnoreReturnValue
   public GsonBuilder serializeSpecialFloatingPointValues() {
      this.serializeSpecialFloatingPointValues = true;
      return this;
   }

   @CanIgnoreReturnValue
   public GsonBuilder disableJdkUnsafe() {
      this.useJdkUnsafe = false;
      return this;
   }

   @CanIgnoreReturnValue
   public GsonBuilder addReflectionAccessFilter(ReflectionAccessFilter filter) {
      Objects.requireNonNull(filter);
      this.reflectionFilters.addFirst(filter);
      return this;
   }

   public Gson create() {
      List<TypeAdapterFactory> factories = new ArrayList(this.factories.size() + this.hierarchyFactories.size() + 3);
      factories.addAll(this.factories);
      Collections.reverse(factories);
      List<TypeAdapterFactory> hierarchyFactories = new ArrayList(this.hierarchyFactories);
      Collections.reverse(hierarchyFactories);
      factories.addAll(hierarchyFactories);
      addTypeAdaptersForDate(this.datePattern, this.dateStyle, this.timeStyle, factories);
      return new Gson(this.excluder, this.fieldNamingPolicy, new HashMap(this.instanceCreators), this.serializeNulls, this.complexMapKeySerialization, this.generateNonExecutableJson, this.escapeHtmlChars, this.formattingStyle, this.strictness, this.serializeSpecialFloatingPointValues, this.useJdkUnsafe, this.longSerializationPolicy, this.datePattern, this.dateStyle, this.timeStyle, new ArrayList(this.factories), new ArrayList(this.hierarchyFactories), factories, this.objectToNumberStrategy, this.numberToNumberStrategy, new ArrayList(this.reflectionFilters));
   }

   private static void addTypeAdaptersForDate(String datePattern, int dateStyle, int timeStyle, List factories) {
      boolean sqlTypesSupported = SqlTypesSupport.SUPPORTS_SQL_TYPES;
      TypeAdapterFactory sqlTimestampAdapterFactory = null;
      TypeAdapterFactory sqlDateAdapterFactory = null;
      TypeAdapterFactory dateAdapterFactory;
      if (datePattern != null && !datePattern.trim().isEmpty()) {
         dateAdapterFactory = DefaultDateTypeAdapter.DateType.DATE.createAdapterFactory(datePattern);
         if (sqlTypesSupported) {
            sqlTimestampAdapterFactory = SqlTypesSupport.TIMESTAMP_DATE_TYPE.createAdapterFactory(datePattern);
            sqlDateAdapterFactory = SqlTypesSupport.DATE_DATE_TYPE.createAdapterFactory(datePattern);
         }
      } else {
         if (dateStyle == 2 && timeStyle == 2) {
            return;
         }

         dateAdapterFactory = DefaultDateTypeAdapter.DateType.DATE.createAdapterFactory(dateStyle, timeStyle);
         if (sqlTypesSupported) {
            sqlTimestampAdapterFactory = SqlTypesSupport.TIMESTAMP_DATE_TYPE.createAdapterFactory(dateStyle, timeStyle);
            sqlDateAdapterFactory = SqlTypesSupport.DATE_DATE_TYPE.createAdapterFactory(dateStyle, timeStyle);
         }
      }

      factories.add(dateAdapterFactory);
      if (sqlTypesSupported) {
         factories.add(sqlTimestampAdapterFactory);
         factories.add(sqlDateAdapterFactory);
      }

   }
}
