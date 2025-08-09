package shaded.parquet.com.fasterxml.jackson.databind.cfg;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonFormat;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonInclude;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonIncludeProperties;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonSetter;
import shaded.parquet.com.fasterxml.jackson.core.Base64Variant;
import shaded.parquet.com.fasterxml.jackson.core.SerializableString;
import shaded.parquet.com.fasterxml.jackson.core.io.SerializedString;
import shaded.parquet.com.fasterxml.jackson.core.type.TypeReference;
import shaded.parquet.com.fasterxml.jackson.databind.AnnotationIntrospector;
import shaded.parquet.com.fasterxml.jackson.databind.BeanDescription;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.MapperFeature;
import shaded.parquet.com.fasterxml.jackson.databind.PropertyName;
import shaded.parquet.com.fasterxml.jackson.databind.PropertyNamingStrategy;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.AccessorNamingStrategy;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.Annotated;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.DefaultBaseTypeLimitingValidator;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import shaded.parquet.com.fasterxml.jackson.databind.type.TypeFactory;
import shaded.parquet.com.fasterxml.jackson.databind.util.ClassUtil;

public abstract class MapperConfig implements ClassIntrospector.MixInResolver, Serializable {
   private static final long serialVersionUID = 2L;
   protected static final JsonInclude.Value EMPTY_INCLUDE = JsonInclude.Value.empty();
   protected static final JsonFormat.Value EMPTY_FORMAT = JsonFormat.Value.empty();
   protected final long _mapperFeatures;
   protected final BaseSettings _base;

   protected MapperConfig(BaseSettings base, long mapperFeatures) {
      this._base = base;
      this._mapperFeatures = mapperFeatures;
   }

   protected MapperConfig(MapperConfig src, long mapperFeatures) {
      this._base = src._base;
      this._mapperFeatures = mapperFeatures;
   }

   protected MapperConfig(MapperConfig src, BaseSettings base) {
      this._base = base;
      this._mapperFeatures = src._mapperFeatures;
   }

   protected MapperConfig(MapperConfig src) {
      this._base = src._base;
      this._mapperFeatures = src._mapperFeatures;
   }

   public static int collectFeatureDefaults(Class enumClass) {
      int flags = 0;

      for(Enum value : (Enum[])enumClass.getEnumConstants()) {
         if (((ConfigFeature)value).enabledByDefault()) {
            flags |= ((ConfigFeature)value).getMask();
         }
      }

      return flags;
   }

   public abstract MapperConfig with(MapperFeature... var1);

   public abstract MapperConfig without(MapperFeature... var1);

   public abstract MapperConfig with(MapperFeature var1, boolean var2);

   public final boolean isEnabled(MapperFeature f) {
      return f.enabledIn(this._mapperFeatures);
   }

   /** @deprecated */
   @Deprecated
   public final boolean hasMapperFeatures(int featureMask) {
      return (this._mapperFeatures & (long)featureMask) == (long)featureMask;
   }

   public abstract boolean isEnabled(DatatypeFeature var1);

   public abstract DatatypeFeatures getDatatypeFeatures();

   public final boolean isAnnotationProcessingEnabled() {
      return this.isEnabled(MapperFeature.USE_ANNOTATIONS);
   }

   public final boolean canOverrideAccessModifiers() {
      return this.isEnabled(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS);
   }

   public final boolean shouldSortPropertiesAlphabetically() {
      return this.isEnabled(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
   }

   public abstract boolean useRootWrapping();

   public SerializableString compileString(String src) {
      return new SerializedString(src);
   }

   public ClassIntrospector getClassIntrospector() {
      return this._base.getClassIntrospector();
   }

   public AnnotationIntrospector getAnnotationIntrospector() {
      return (AnnotationIntrospector)(this.isEnabled(MapperFeature.USE_ANNOTATIONS) ? this._base.getAnnotationIntrospector() : NopAnnotationIntrospector.instance);
   }

   public final PropertyNamingStrategy getPropertyNamingStrategy() {
      return this._base.getPropertyNamingStrategy();
   }

   public final AccessorNamingStrategy.Provider getAccessorNaming() {
      return this._base.getAccessorNaming();
   }

   public final HandlerInstantiator getHandlerInstantiator() {
      return this._base.getHandlerInstantiator();
   }

   public final TypeResolverBuilder getDefaultTyper(JavaType baseType) {
      return this._base.getTypeResolverBuilder();
   }

   public abstract SubtypeResolver getSubtypeResolver();

   public PolymorphicTypeValidator getPolymorphicTypeValidator() {
      PolymorphicTypeValidator ptv = this._base.getPolymorphicTypeValidator();
      if (ptv == LaissezFaireSubTypeValidator.instance && this.isEnabled(MapperFeature.BLOCK_UNSAFE_POLYMORPHIC_BASE_TYPES)) {
         ptv = new DefaultBaseTypeLimitingValidator();
      }

      return ptv;
   }

   public final TypeFactory getTypeFactory() {
      return this._base.getTypeFactory();
   }

   public final JavaType constructType(Class cls) {
      return this.getTypeFactory().constructType((Type)cls);
   }

   public final JavaType constructType(TypeReference valueTypeRef) {
      return this.getTypeFactory().constructType(valueTypeRef.getType());
   }

   public JavaType constructSpecializedType(JavaType baseType, Class subclass) {
      return this.getTypeFactory().constructSpecializedType(baseType, subclass, true);
   }

   public BeanDescription introspectClassAnnotations(Class cls) {
      return this.introspectClassAnnotations(this.constructType(cls));
   }

   public BeanDescription introspectClassAnnotations(JavaType type) {
      return this.getClassIntrospector().forClassAnnotations(this, type, this);
   }

   public BeanDescription introspectDirectClassAnnotations(Class cls) {
      return this.introspectDirectClassAnnotations(this.constructType(cls));
   }

   public final BeanDescription introspectDirectClassAnnotations(JavaType type) {
      return this.getClassIntrospector().forDirectClassAnnotations(this, type, this);
   }

   public abstract ConfigOverride findConfigOverride(Class var1);

   public abstract ConfigOverride getConfigOverride(Class var1);

   public abstract JsonInclude.Value getDefaultPropertyInclusion();

   public abstract JsonInclude.Value getDefaultPropertyInclusion(Class var1);

   public JsonInclude.Value getDefaultPropertyInclusion(Class baseType, JsonInclude.Value defaultIncl) {
      JsonInclude.Value v = this.getConfigOverride(baseType).getInclude();
      return v != null ? v : defaultIncl;
   }

   public abstract JsonInclude.Value getDefaultInclusion(Class var1, Class var2);

   public JsonInclude.Value getDefaultInclusion(Class baseType, Class propertyType, JsonInclude.Value defaultIncl) {
      JsonInclude.Value baseOverride = this.getConfigOverride(baseType).getInclude();
      JsonInclude.Value propOverride = this.getConfigOverride(propertyType).getIncludeAsProperty();
      JsonInclude.Value result = JsonInclude.Value.mergeAll(defaultIncl, baseOverride, propOverride);
      return result;
   }

   public abstract JsonFormat.Value getDefaultPropertyFormat(Class var1);

   public abstract JsonIgnoreProperties.Value getDefaultPropertyIgnorals(Class var1);

   public abstract JsonIgnoreProperties.Value getDefaultPropertyIgnorals(Class var1, AnnotatedClass var2);

   public abstract JsonIncludeProperties.Value getDefaultPropertyInclusions(Class var1, AnnotatedClass var2);

   public abstract VisibilityChecker getDefaultVisibilityChecker();

   public abstract VisibilityChecker getDefaultVisibilityChecker(Class var1, AnnotatedClass var2);

   public abstract JsonSetter.Value getDefaultSetterInfo();

   public abstract Boolean getDefaultMergeable();

   public abstract Boolean getDefaultMergeable(Class var1);

   public final DateFormat getDateFormat() {
      return this._base.getDateFormat();
   }

   public final Locale getLocale() {
      return this._base.getLocale();
   }

   public final TimeZone getTimeZone() {
      return this._base.getTimeZone();
   }

   public boolean hasExplicitTimeZone() {
      return this._base.hasExplicitTimeZone();
   }

   public abstract Class getActiveView();

   public Base64Variant getBase64Variant() {
      return this._base.getBase64Variant();
   }

   public abstract ContextAttributes getAttributes();

   public abstract PropertyName findRootName(JavaType var1);

   public abstract PropertyName findRootName(Class var1);

   public CacheProvider getCacheProvider() {
      return this._base.getCacheProvider();
   }

   public abstract ConstructorDetector getConstructorDetector();

   public TypeResolverBuilder typeResolverBuilderInstance(Annotated annotated, Class builderClass) {
      HandlerInstantiator hi = this.getHandlerInstantiator();
      if (hi != null) {
         TypeResolverBuilder<?> builder = hi.typeResolverBuilderInstance(this, annotated, builderClass);
         if (builder != null) {
            return builder;
         }
      }

      return (TypeResolverBuilder)ClassUtil.createInstance(builderClass, this.canOverrideAccessModifiers());
   }

   public TypeIdResolver typeIdResolverInstance(Annotated annotated, Class resolverClass) {
      HandlerInstantiator hi = this.getHandlerInstantiator();
      if (hi != null) {
         TypeIdResolver builder = hi.typeIdResolverInstance(this, annotated, resolverClass);
         if (builder != null) {
            return builder;
         }
      }

      return (TypeIdResolver)ClassUtil.createInstance(resolverClass, this.canOverrideAccessModifiers());
   }
}
