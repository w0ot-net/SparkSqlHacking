package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties.Value;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.introspect.AccessorNamingStrategy;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.introspect.SimpleMixInResolver;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;

public abstract class MapperConfigBase extends MapperConfig implements Serializable {
   protected static final ConfigOverride EMPTY_OVERRIDE = ConfigOverride.empty();
   private static final long DEFAULT_MAPPER_FEATURES = MapperFeature.collectLongDefaults();
   private static final long AUTO_DETECT_MASK;
   protected final SimpleMixInResolver _mixIns;
   protected final SubtypeResolver _subtypeResolver;
   protected final PropertyName _rootName;
   protected final Class _view;
   protected final ContextAttributes _attributes;
   protected final RootNameLookup _rootNames;
   protected final ConfigOverrides _configOverrides;
   protected final DatatypeFeatures _datatypeFeatures;

   protected MapperConfigBase(BaseSettings base, SubtypeResolver str, SimpleMixInResolver mixins, RootNameLookup rootNames, ConfigOverrides configOverrides, DatatypeFeatures datatypeFeatures) {
      super(base, DEFAULT_MAPPER_FEATURES);
      this._mixIns = mixins;
      this._subtypeResolver = str;
      this._rootNames = rootNames;
      this._rootName = null;
      this._view = null;
      this._attributes = ContextAttributes.getEmpty();
      this._configOverrides = configOverrides;
      this._datatypeFeatures = datatypeFeatures;
   }

   protected MapperConfigBase(MapperConfigBase src, SubtypeResolver str, SimpleMixInResolver mixins, RootNameLookup rootNames, ConfigOverrides configOverrides) {
      super(src, src._base.copy());
      this._mixIns = mixins;
      this._subtypeResolver = str;
      this._rootNames = rootNames;
      this._rootName = src._rootName;
      this._view = src._view;
      this._attributes = src._attributes;
      this._configOverrides = configOverrides;
      this._datatypeFeatures = src._datatypeFeatures;
   }

   protected MapperConfigBase(MapperConfigBase src) {
      super(src);
      this._mixIns = src._mixIns;
      this._subtypeResolver = src._subtypeResolver;
      this._rootNames = src._rootNames;
      this._rootName = src._rootName;
      this._view = src._view;
      this._attributes = src._attributes;
      this._configOverrides = src._configOverrides;
      this._datatypeFeatures = src._datatypeFeatures;
   }

   protected MapperConfigBase(MapperConfigBase src, BaseSettings base) {
      super(src, base);
      this._mixIns = src._mixIns;
      this._subtypeResolver = src._subtypeResolver;
      this._rootNames = src._rootNames;
      this._rootName = src._rootName;
      this._view = src._view;
      this._attributes = src._attributes;
      this._configOverrides = src._configOverrides;
      this._datatypeFeatures = src._datatypeFeatures;
   }

   protected MapperConfigBase(MapperConfigBase src, long mapperFeatures) {
      super((MapperConfig)src, mapperFeatures);
      this._mixIns = src._mixIns;
      this._subtypeResolver = src._subtypeResolver;
      this._rootNames = src._rootNames;
      this._rootName = src._rootName;
      this._view = src._view;
      this._attributes = src._attributes;
      this._configOverrides = src._configOverrides;
      this._datatypeFeatures = src._datatypeFeatures;
   }

   protected MapperConfigBase(MapperConfigBase src, SubtypeResolver str) {
      super(src);
      this._mixIns = src._mixIns;
      this._subtypeResolver = str;
      this._rootNames = src._rootNames;
      this._rootName = src._rootName;
      this._view = src._view;
      this._attributes = src._attributes;
      this._configOverrides = src._configOverrides;
      this._datatypeFeatures = src._datatypeFeatures;
   }

   protected MapperConfigBase(MapperConfigBase src, PropertyName rootName) {
      super(src);
      this._mixIns = src._mixIns;
      this._subtypeResolver = src._subtypeResolver;
      this._rootNames = src._rootNames;
      this._rootName = rootName;
      this._view = src._view;
      this._attributes = src._attributes;
      this._configOverrides = src._configOverrides;
      this._datatypeFeatures = src._datatypeFeatures;
   }

   protected MapperConfigBase(MapperConfigBase src, Class view) {
      super(src);
      this._mixIns = src._mixIns;
      this._subtypeResolver = src._subtypeResolver;
      this._rootNames = src._rootNames;
      this._rootName = src._rootName;
      this._view = view;
      this._attributes = src._attributes;
      this._configOverrides = src._configOverrides;
      this._datatypeFeatures = src._datatypeFeatures;
   }

   protected MapperConfigBase(MapperConfigBase src, SimpleMixInResolver mixins) {
      super(src);
      this._mixIns = mixins;
      this._subtypeResolver = src._subtypeResolver;
      this._rootNames = src._rootNames;
      this._rootName = src._rootName;
      this._view = src._view;
      this._attributes = src._attributes;
      this._configOverrides = src._configOverrides;
      this._datatypeFeatures = src._datatypeFeatures;
   }

   protected MapperConfigBase(MapperConfigBase src, ContextAttributes attr) {
      super(src);
      this._mixIns = src._mixIns;
      this._subtypeResolver = src._subtypeResolver;
      this._rootNames = src._rootNames;
      this._rootName = src._rootName;
      this._view = src._view;
      this._attributes = attr;
      this._configOverrides = src._configOverrides;
      this._datatypeFeatures = src._datatypeFeatures;
   }

   protected MapperConfigBase(MapperConfigBase src, DatatypeFeatures datatypeFeatures) {
      super(src);
      this._mixIns = src._mixIns;
      this._subtypeResolver = src._subtypeResolver;
      this._rootNames = src._rootNames;
      this._rootName = src._rootName;
      this._view = src._view;
      this._attributes = src._attributes;
      this._configOverrides = src._configOverrides;
      this._datatypeFeatures = datatypeFeatures;
   }

   protected abstract MapperConfigBase _withBase(BaseSettings var1);

   protected abstract MapperConfigBase _withMapperFeatures(long var1);

   protected abstract MapperConfigBase _with(DatatypeFeatures var1);

   protected DatatypeFeatures _datatypeFeatures() {
      return this._datatypeFeatures;
   }

   public final MapperConfigBase with(MapperFeature... features) {
      long newMapperFlags = this._mapperFeatures;

      for(MapperFeature f : features) {
         newMapperFlags |= f.getLongMask();
      }

      return newMapperFlags == this._mapperFeatures ? this : this._withMapperFeatures(newMapperFlags);
   }

   public final MapperConfigBase without(MapperFeature... features) {
      long newMapperFlags = this._mapperFeatures;

      for(MapperFeature f : features) {
         newMapperFlags &= ~f.getLongMask();
      }

      return newMapperFlags == this._mapperFeatures ? this : this._withMapperFeatures(newMapperFlags);
   }

   public final MapperConfigBase with(MapperFeature feature, boolean state) {
      long newMapperFlags;
      if (state) {
         newMapperFlags = this._mapperFeatures | feature.getLongMask();
      } else {
         newMapperFlags = this._mapperFeatures & ~feature.getLongMask();
      }

      return newMapperFlags == this._mapperFeatures ? this : this._withMapperFeatures(newMapperFlags);
   }

   public final MapperConfigBase with(DatatypeFeature feature) {
      return this._with(this._datatypeFeatures().with(feature));
   }

   public final MapperConfigBase withFeatures(DatatypeFeature... features) {
      return this._with(this._datatypeFeatures().withFeatures(features));
   }

   public final MapperConfigBase without(DatatypeFeature feature) {
      return this._with(this._datatypeFeatures().without(feature));
   }

   public final MapperConfigBase withoutFeatures(DatatypeFeature... features) {
      return this._with(this._datatypeFeatures().withoutFeatures(features));
   }

   public final MapperConfigBase with(DatatypeFeature feature, boolean state) {
      DatatypeFeatures features = this._datatypeFeatures();
      features = state ? features.with(feature) : features.without(feature);
      return this._with(features);
   }

   public final MapperConfigBase with(AnnotationIntrospector ai) {
      return this._withBase(this._base.withAnnotationIntrospector(ai));
   }

   public final MapperConfigBase withAppendedAnnotationIntrospector(AnnotationIntrospector ai) {
      return this._withBase(this._base.withAppendedAnnotationIntrospector(ai));
   }

   public final MapperConfigBase withInsertedAnnotationIntrospector(AnnotationIntrospector ai) {
      return this._withBase(this._base.withInsertedAnnotationIntrospector(ai));
   }

   public final MapperConfigBase with(ClassIntrospector ci) {
      return this._withBase(this._base.withClassIntrospector(ci));
   }

   public abstract MapperConfigBase with(ContextAttributes var1);

   public MapperConfigBase withAttributes(Map attributes) {
      return this.with(this.getAttributes().withSharedAttributes(attributes));
   }

   public MapperConfigBase withAttribute(Object key, Object value) {
      return this.with(this.getAttributes().withSharedAttribute(key, value));
   }

   public MapperConfigBase withoutAttribute(Object key) {
      return this.with(this.getAttributes().withoutSharedAttribute(key));
   }

   public final MapperConfigBase with(TypeFactory tf) {
      return this._withBase(this._base.withTypeFactory(tf));
   }

   public final MapperConfigBase with(TypeResolverBuilder trb) {
      return this._withBase(this._base.withTypeResolverBuilder(trb));
   }

   public final MapperConfigBase with(PropertyNamingStrategy pns) {
      return this._withBase(this._base.withPropertyNamingStrategy(pns));
   }

   public final MapperConfigBase with(AccessorNamingStrategy.Provider p) {
      return this._withBase(this._base.withAccessorNaming(p));
   }

   public final MapperConfigBase with(HandlerInstantiator hi) {
      return this._withBase(this._base.withHandlerInstantiator(hi));
   }

   public MapperConfigBase with(CacheProvider provider) {
      return this._withBase(this._base.with((CacheProvider)Objects.requireNonNull(provider)));
   }

   public final MapperConfigBase with(Base64Variant base64) {
      return this._withBase(this._base.with(base64));
   }

   public MapperConfigBase with(DateFormat df) {
      return this._withBase(this._base.withDateFormat(df));
   }

   public final MapperConfigBase with(Locale l) {
      return this._withBase(this._base.with(l));
   }

   public final MapperConfigBase with(TimeZone tz) {
      return this._withBase(this._base.with(tz));
   }

   public abstract MapperConfigBase withRootName(PropertyName var1);

   public MapperConfigBase withRootName(String rootName) {
      return rootName == null ? this.withRootName((PropertyName)null) : this.withRootName(PropertyName.construct(rootName));
   }

   public abstract MapperConfigBase with(SubtypeResolver var1);

   public abstract MapperConfigBase withView(Class var1);

   public final DatatypeFeatures getDatatypeFeatures() {
      return this._datatypeFeatures;
   }

   public final SubtypeResolver getSubtypeResolver() {
      return this._subtypeResolver;
   }

   /** @deprecated */
   @Deprecated
   public final String getRootName() {
      return this._rootName == null ? null : this._rootName.getSimpleName();
   }

   public final PropertyName getFullRootName() {
      return this._rootName;
   }

   public final Class getActiveView() {
      return this._view;
   }

   public final ContextAttributes getAttributes() {
      return this._attributes;
   }

   public final ConfigOverride getConfigOverride(Class type) {
      ConfigOverride override = this._configOverrides.findOverride(type);
      return override == null ? EMPTY_OVERRIDE : override;
   }

   public final ConfigOverride findConfigOverride(Class type) {
      return this._configOverrides.findOverride(type);
   }

   public final JsonInclude.Value getDefaultPropertyInclusion() {
      return this._configOverrides.getDefaultInclusion();
   }

   public final JsonInclude.Value getDefaultPropertyInclusion(Class baseType) {
      JsonInclude.Value v = this.getConfigOverride(baseType).getInclude();
      JsonInclude.Value def = this.getDefaultPropertyInclusion();
      return def == null ? v : def.withOverrides(v);
   }

   public final JsonInclude.Value getDefaultInclusion(Class baseType, Class propertyType) {
      JsonInclude.Value v = this.getConfigOverride(propertyType).getIncludeAsProperty();
      JsonInclude.Value def = this.getDefaultPropertyInclusion(baseType);
      return def == null ? v : def.withOverrides(v);
   }

   public final JsonFormat.Value getDefaultPropertyFormat(Class type) {
      return this._configOverrides.findFormatDefaults(type);
   }

   public final JsonIgnoreProperties.Value getDefaultPropertyIgnorals(Class type) {
      ConfigOverride overrides = this._configOverrides.findOverride(type);
      if (overrides != null) {
         JsonIgnoreProperties.Value v = overrides.getIgnorals();
         if (v != null) {
            return v;
         }
      }

      return null;
   }

   public final JsonIgnoreProperties.Value getDefaultPropertyIgnorals(Class baseType, AnnotatedClass actualClass) {
      AnnotationIntrospector intr = this.getAnnotationIntrospector();
      JsonIgnoreProperties.Value base = intr == null ? null : intr.findPropertyIgnoralByName(this, actualClass);
      JsonIgnoreProperties.Value overrides = this.getDefaultPropertyIgnorals(baseType);
      return Value.merge(base, overrides);
   }

   public final JsonIncludeProperties.Value getDefaultPropertyInclusions(Class baseType, AnnotatedClass actualClass) {
      AnnotationIntrospector intr = this.getAnnotationIntrospector();
      return intr == null ? null : intr.findPropertyInclusionByName(this, actualClass);
   }

   public final VisibilityChecker getDefaultVisibilityChecker() {
      VisibilityChecker<?> vchecker = this._configOverrides.getDefaultVisibility();
      if ((this._mapperFeatures & AUTO_DETECT_MASK) != AUTO_DETECT_MASK) {
         if (!this.isEnabled(MapperFeature.AUTO_DETECT_FIELDS)) {
            vchecker = vchecker.withFieldVisibility(Visibility.NONE);
         }

         if (!this.isEnabled(MapperFeature.AUTO_DETECT_GETTERS)) {
            vchecker = vchecker.withGetterVisibility(Visibility.NONE);
         }

         if (!this.isEnabled(MapperFeature.AUTO_DETECT_IS_GETTERS)) {
            vchecker = vchecker.withIsGetterVisibility(Visibility.NONE);
         }

         if (!this.isEnabled(MapperFeature.AUTO_DETECT_SETTERS)) {
            vchecker = vchecker.withSetterVisibility(Visibility.NONE);
         }

         if (!this.isEnabled(MapperFeature.AUTO_DETECT_CREATORS)) {
            vchecker = vchecker.withCreatorVisibility(Visibility.NONE);
         }
      }

      return vchecker;
   }

   public final VisibilityChecker getDefaultVisibilityChecker(Class baseType, AnnotatedClass actualClass) {
      VisibilityChecker<?> vc;
      if (ClassUtil.isJDKClass(baseType)) {
         vc = VisibilityChecker.Std.allPublicInstance();
      } else {
         vc = this.getDefaultVisibilityChecker();
         if (ClassUtil.isRecordType(baseType) && this.isEnabled(MapperFeature.AUTO_DETECT_CREATORS)) {
            vc = vc.withCreatorVisibility(Visibility.DEFAULT);
         }
      }

      AnnotationIntrospector intr = this.getAnnotationIntrospector();
      if (intr != null) {
         vc = intr.findAutoDetectVisibility(actualClass, vc);
      }

      ConfigOverride overrides = this._configOverrides.findOverride(baseType);
      if (overrides != null) {
         vc = vc.withOverrides(overrides.getVisibility());
      }

      return vc;
   }

   public final JsonSetter.Value getDefaultSetterInfo() {
      return this._configOverrides.getDefaultSetterInfo();
   }

   public Boolean getDefaultMergeable() {
      return this._configOverrides.getDefaultMergeable();
   }

   public Boolean getDefaultMergeable(Class baseType) {
      ConfigOverride cfg = this._configOverrides.findOverride(baseType);
      if (cfg != null) {
         Boolean b = cfg.getMergeable();
         if (b != null) {
            return b;
         }
      }

      return this._configOverrides.getDefaultMergeable();
   }

   public PropertyName findRootName(JavaType rootType) {
      return this._rootName != null ? this._rootName : this._rootNames.findRootName((JavaType)rootType, this);
   }

   public PropertyName findRootName(Class rawRootType) {
      return this._rootName != null ? this._rootName : this._rootNames.findRootName((Class)rawRootType, this);
   }

   public final Class findMixInClassFor(Class cls) {
      return this._mixIns.findMixInClassFor(cls);
   }

   public ClassIntrospector.MixInResolver copy() {
      throw new UnsupportedOperationException();
   }

   public final int mixInCount() {
      return this._mixIns.localSize();
   }

   static {
      AUTO_DETECT_MASK = MapperFeature.AUTO_DETECT_FIELDS.getLongMask() | MapperFeature.AUTO_DETECT_GETTERS.getLongMask() | MapperFeature.AUTO_DETECT_IS_GETTERS.getLongMask() | MapperFeature.AUTO_DETECT_SETTERS.getLongMask() | MapperFeature.AUTO_DETECT_CREATORS.getLongMask();
   }
}
