package shaded.parquet.com.fasterxml.jackson.databind;

import java.io.Serializable;
import java.util.Collection;
import shaded.parquet.com.fasterxml.jackson.core.FormatFeature;
import shaded.parquet.com.fasterxml.jackson.core.FormatSchema;
import shaded.parquet.com.fasterxml.jackson.core.JsonFactory;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.core.json.JsonReadFeature;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.BaseSettings;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.CoercionAction;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.CoercionConfigs;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.ConfigOverrides;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.ConstructorDetector;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.ContextAttributes;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.DatatypeFeature;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.DatatypeFeatures;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.MapperConfigBase;
import shaded.parquet.com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.SimpleMixInResolver;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.NamedType;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import shaded.parquet.com.fasterxml.jackson.databind.node.JsonNodeFactory;
import shaded.parquet.com.fasterxml.jackson.databind.type.LogicalType;
import shaded.parquet.com.fasterxml.jackson.databind.util.LinkedNode;
import shaded.parquet.com.fasterxml.jackson.databind.util.RootNameLookup;

public final class DeserializationConfig extends MapperConfigBase implements Serializable {
   private static final long serialVersionUID = 2L;
   private static final int DESER_FEATURE_DEFAULTS = collectFeatureDefaults(DeserializationFeature.class);
   protected final LinkedNode _problemHandlers;
   protected final JsonNodeFactory _nodeFactory;
   protected final CoercionConfigs _coercionConfigs;
   protected final ConstructorDetector _ctorDetector;
   protected final int _deserFeatures;
   protected final int _parserFeatures;
   protected final int _parserFeaturesToChange;
   protected final int _formatReadFeatures;
   protected final int _formatReadFeaturesToChange;

   public DeserializationConfig(BaseSettings base, SubtypeResolver str, SimpleMixInResolver mixins, RootNameLookup rootNames, ConfigOverrides configOverrides, CoercionConfigs coercionConfigs, DatatypeFeatures datatypeFeatures) {
      super(base, str, mixins, rootNames, configOverrides, datatypeFeatures);
      this._deserFeatures = DESER_FEATURE_DEFAULTS;
      this._problemHandlers = null;
      this._nodeFactory = JsonNodeFactory.instance;
      this._ctorDetector = null;
      this._coercionConfigs = coercionConfigs;
      this._parserFeatures = 0;
      this._parserFeaturesToChange = 0;
      this._formatReadFeatures = 0;
      this._formatReadFeaturesToChange = 0;
   }

   protected DeserializationConfig(DeserializationConfig src, SubtypeResolver str, SimpleMixInResolver mixins, RootNameLookup rootNames, ConfigOverrides configOverrides, CoercionConfigs coercionConfigs) {
      super(src, str, mixins, rootNames, configOverrides);
      this._deserFeatures = src._deserFeatures;
      this._problemHandlers = src._problemHandlers;
      this._nodeFactory = src._nodeFactory;
      this._ctorDetector = src._ctorDetector;
      this._coercionConfigs = coercionConfigs;
      this._parserFeatures = src._parserFeatures;
      this._parserFeaturesToChange = src._parserFeaturesToChange;
      this._formatReadFeatures = src._formatReadFeatures;
      this._formatReadFeaturesToChange = src._formatReadFeaturesToChange;
   }

   private DeserializationConfig(DeserializationConfig src, long mapperFeatures, int deserFeatures, int parserFeatures, int parserFeatureMask, int formatFeatures, int formatFeatureMask) {
      super(src, mapperFeatures);
      this._deserFeatures = deserFeatures;
      this._problemHandlers = src._problemHandlers;
      this._nodeFactory = src._nodeFactory;
      this._coercionConfigs = src._coercionConfigs;
      this._ctorDetector = src._ctorDetector;
      this._parserFeatures = parserFeatures;
      this._parserFeaturesToChange = parserFeatureMask;
      this._formatReadFeatures = formatFeatures;
      this._formatReadFeaturesToChange = formatFeatureMask;
   }

   private DeserializationConfig(DeserializationConfig src, SubtypeResolver str) {
      super(src, (SubtypeResolver)str);
      this._deserFeatures = src._deserFeatures;
      this._problemHandlers = src._problemHandlers;
      this._nodeFactory = src._nodeFactory;
      this._coercionConfigs = src._coercionConfigs;
      this._ctorDetector = src._ctorDetector;
      this._parserFeatures = src._parserFeatures;
      this._parserFeaturesToChange = src._parserFeaturesToChange;
      this._formatReadFeatures = src._formatReadFeatures;
      this._formatReadFeaturesToChange = src._formatReadFeaturesToChange;
   }

   private DeserializationConfig(DeserializationConfig src, BaseSettings base) {
      super(src, (BaseSettings)base);
      this._deserFeatures = src._deserFeatures;
      this._problemHandlers = src._problemHandlers;
      this._nodeFactory = src._nodeFactory;
      this._coercionConfigs = src._coercionConfigs;
      this._ctorDetector = src._ctorDetector;
      this._parserFeatures = src._parserFeatures;
      this._parserFeaturesToChange = src._parserFeaturesToChange;
      this._formatReadFeatures = src._formatReadFeatures;
      this._formatReadFeaturesToChange = src._formatReadFeaturesToChange;
   }

   private DeserializationConfig(DeserializationConfig src, JsonNodeFactory f) {
      super(src);
      this._deserFeatures = src._deserFeatures;
      this._problemHandlers = src._problemHandlers;
      this._nodeFactory = f;
      this._coercionConfigs = src._coercionConfigs;
      this._ctorDetector = src._ctorDetector;
      this._parserFeatures = src._parserFeatures;
      this._parserFeaturesToChange = src._parserFeaturesToChange;
      this._formatReadFeatures = src._formatReadFeatures;
      this._formatReadFeaturesToChange = src._formatReadFeaturesToChange;
   }

   private DeserializationConfig(DeserializationConfig src, ConstructorDetector ctorDetector) {
      super(src);
      this._deserFeatures = src._deserFeatures;
      this._problemHandlers = src._problemHandlers;
      this._nodeFactory = src._nodeFactory;
      this._coercionConfigs = src._coercionConfigs;
      this._ctorDetector = ctorDetector;
      this._parserFeatures = src._parserFeatures;
      this._parserFeaturesToChange = src._parserFeaturesToChange;
      this._formatReadFeatures = src._formatReadFeatures;
      this._formatReadFeaturesToChange = src._formatReadFeaturesToChange;
   }

   private DeserializationConfig(DeserializationConfig src, LinkedNode problemHandlers) {
      super(src);
      this._deserFeatures = src._deserFeatures;
      this._problemHandlers = problemHandlers;
      this._nodeFactory = src._nodeFactory;
      this._coercionConfigs = src._coercionConfigs;
      this._ctorDetector = src._ctorDetector;
      this._parserFeatures = src._parserFeatures;
      this._parserFeaturesToChange = src._parserFeaturesToChange;
      this._formatReadFeatures = src._formatReadFeatures;
      this._formatReadFeaturesToChange = src._formatReadFeaturesToChange;
   }

   private DeserializationConfig(DeserializationConfig src, PropertyName rootName) {
      super(src, (PropertyName)rootName);
      this._deserFeatures = src._deserFeatures;
      this._problemHandlers = src._problemHandlers;
      this._nodeFactory = src._nodeFactory;
      this._coercionConfigs = src._coercionConfigs;
      this._ctorDetector = src._ctorDetector;
      this._parserFeatures = src._parserFeatures;
      this._parserFeaturesToChange = src._parserFeaturesToChange;
      this._formatReadFeatures = src._formatReadFeatures;
      this._formatReadFeaturesToChange = src._formatReadFeaturesToChange;
   }

   private DeserializationConfig(DeserializationConfig src, Class view) {
      super(src, (Class)view);
      this._deserFeatures = src._deserFeatures;
      this._problemHandlers = src._problemHandlers;
      this._nodeFactory = src._nodeFactory;
      this._coercionConfigs = src._coercionConfigs;
      this._ctorDetector = src._ctorDetector;
      this._parserFeatures = src._parserFeatures;
      this._parserFeaturesToChange = src._parserFeaturesToChange;
      this._formatReadFeatures = src._formatReadFeatures;
      this._formatReadFeaturesToChange = src._formatReadFeaturesToChange;
   }

   protected DeserializationConfig(DeserializationConfig src, ContextAttributes attrs) {
      super(src, (ContextAttributes)attrs);
      this._deserFeatures = src._deserFeatures;
      this._problemHandlers = src._problemHandlers;
      this._nodeFactory = src._nodeFactory;
      this._coercionConfigs = src._coercionConfigs;
      this._ctorDetector = src._ctorDetector;
      this._parserFeatures = src._parserFeatures;
      this._parserFeaturesToChange = src._parserFeaturesToChange;
      this._formatReadFeatures = src._formatReadFeatures;
      this._formatReadFeaturesToChange = src._formatReadFeaturesToChange;
   }

   protected DeserializationConfig(DeserializationConfig src, SimpleMixInResolver mixins) {
      super(src, (SimpleMixInResolver)mixins);
      this._deserFeatures = src._deserFeatures;
      this._problemHandlers = src._problemHandlers;
      this._nodeFactory = src._nodeFactory;
      this._coercionConfigs = src._coercionConfigs;
      this._ctorDetector = src._ctorDetector;
      this._parserFeatures = src._parserFeatures;
      this._parserFeaturesToChange = src._parserFeaturesToChange;
      this._formatReadFeatures = src._formatReadFeatures;
      this._formatReadFeaturesToChange = src._formatReadFeaturesToChange;
   }

   protected DeserializationConfig(DeserializationConfig src, DatatypeFeatures datatypeFeatures) {
      super(src, (DatatypeFeatures)datatypeFeatures);
      this._deserFeatures = src._deserFeatures;
      this._problemHandlers = src._problemHandlers;
      this._nodeFactory = src._nodeFactory;
      this._coercionConfigs = src._coercionConfigs;
      this._ctorDetector = src._ctorDetector;
      this._parserFeatures = src._parserFeatures;
      this._parserFeaturesToChange = src._parserFeaturesToChange;
      this._formatReadFeatures = src._formatReadFeatures;
      this._formatReadFeaturesToChange = src._formatReadFeaturesToChange;
   }

   protected BaseSettings getBaseSettings() {
      return this._base;
   }

   protected final DeserializationConfig _withBase(BaseSettings newBase) {
      return this._base == newBase ? this : new DeserializationConfig(this, newBase);
   }

   protected final DeserializationConfig _withMapperFeatures(long mapperFeatures) {
      return new DeserializationConfig(this, mapperFeatures, this._deserFeatures, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
   }

   protected final DeserializationConfig _with(DatatypeFeatures dtFeatures) {
      return new DeserializationConfig(this, dtFeatures);
   }

   public DeserializationConfig with(SubtypeResolver str) {
      return this._subtypeResolver == str ? this : new DeserializationConfig(this, str);
   }

   public DeserializationConfig withRootName(PropertyName rootName) {
      if (rootName == null) {
         if (this._rootName == null) {
            return this;
         }
      } else if (rootName.equals(this._rootName)) {
         return this;
      }

      return new DeserializationConfig(this, rootName);
   }

   public DeserializationConfig withView(Class view) {
      return this._view == view ? this : new DeserializationConfig(this, view);
   }

   public DeserializationConfig with(ContextAttributes attrs) {
      return attrs == this._attributes ? this : new DeserializationConfig(this, attrs);
   }

   public DeserializationConfig with(DeserializationFeature feature) {
      int newDeserFeatures = this._deserFeatures | feature.getMask();
      return newDeserFeatures == this._deserFeatures ? this : new DeserializationConfig(this, this._mapperFeatures, newDeserFeatures, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
   }

   public DeserializationConfig with(DeserializationFeature first, DeserializationFeature... features) {
      int newDeserFeatures = this._deserFeatures | first.getMask();

      for(DeserializationFeature f : features) {
         newDeserFeatures |= f.getMask();
      }

      return newDeserFeatures == this._deserFeatures ? this : new DeserializationConfig(this, this._mapperFeatures, newDeserFeatures, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
   }

   public DeserializationConfig withFeatures(DeserializationFeature... features) {
      int newDeserFeatures = this._deserFeatures;

      for(DeserializationFeature f : features) {
         newDeserFeatures |= f.getMask();
      }

      return newDeserFeatures == this._deserFeatures ? this : new DeserializationConfig(this, this._mapperFeatures, newDeserFeatures, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
   }

   public DeserializationConfig without(DeserializationFeature feature) {
      int newDeserFeatures = this._deserFeatures & ~feature.getMask();
      return newDeserFeatures == this._deserFeatures ? this : new DeserializationConfig(this, this._mapperFeatures, newDeserFeatures, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
   }

   public DeserializationConfig without(DeserializationFeature first, DeserializationFeature... features) {
      int newDeserFeatures = this._deserFeatures & ~first.getMask();

      for(DeserializationFeature f : features) {
         newDeserFeatures &= ~f.getMask();
      }

      return newDeserFeatures == this._deserFeatures ? this : new DeserializationConfig(this, this._mapperFeatures, newDeserFeatures, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
   }

   public DeserializationConfig withoutFeatures(DeserializationFeature... features) {
      int newDeserFeatures = this._deserFeatures;

      for(DeserializationFeature f : features) {
         newDeserFeatures &= ~f.getMask();
      }

      return newDeserFeatures == this._deserFeatures ? this : new DeserializationConfig(this, this._mapperFeatures, newDeserFeatures, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
   }

   public DeserializationConfig with(JsonParser.Feature feature) {
      int newSet = this._parserFeatures | feature.getMask();
      int newMask = this._parserFeaturesToChange | feature.getMask();
      return this._parserFeatures == newSet && this._parserFeaturesToChange == newMask ? this : new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, newSet, newMask, this._formatReadFeatures, this._formatReadFeaturesToChange);
   }

   public DeserializationConfig withFeatures(JsonParser.Feature... features) {
      int newSet = this._parserFeatures;
      int newMask = this._parserFeaturesToChange;

      for(JsonParser.Feature f : features) {
         int mask = f.getMask();
         newSet |= mask;
         newMask |= mask;
      }

      return this._parserFeatures == newSet && this._parserFeaturesToChange == newMask ? this : new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, newSet, newMask, this._formatReadFeatures, this._formatReadFeaturesToChange);
   }

   public DeserializationConfig without(JsonParser.Feature feature) {
      int newSet = this._parserFeatures & ~feature.getMask();
      int newMask = this._parserFeaturesToChange | feature.getMask();
      return this._parserFeatures == newSet && this._parserFeaturesToChange == newMask ? this : new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, newSet, newMask, this._formatReadFeatures, this._formatReadFeaturesToChange);
   }

   public DeserializationConfig withoutFeatures(JsonParser.Feature... features) {
      int newSet = this._parserFeatures;
      int newMask = this._parserFeaturesToChange;

      for(JsonParser.Feature f : features) {
         int mask = f.getMask();
         newSet &= ~mask;
         newMask |= mask;
      }

      return this._parserFeatures == newSet && this._parserFeaturesToChange == newMask ? this : new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, newSet, newMask, this._formatReadFeatures, this._formatReadFeaturesToChange);
   }

   public DeserializationConfig with(FormatFeature feature) {
      if (feature instanceof JsonReadFeature) {
         return this._withJsonReadFeatures(feature);
      } else {
         int newSet = this._formatReadFeatures | feature.getMask();
         int newMask = this._formatReadFeaturesToChange | feature.getMask();
         return this._formatReadFeatures == newSet && this._formatReadFeaturesToChange == newMask ? this : new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, this._parserFeatures, this._parserFeaturesToChange, newSet, newMask);
      }
   }

   public DeserializationConfig withFeatures(FormatFeature... features) {
      if (features.length > 0 && features[0] instanceof JsonReadFeature) {
         return this._withJsonReadFeatures(features);
      } else {
         int newSet = this._formatReadFeatures;
         int newMask = this._formatReadFeaturesToChange;

         for(FormatFeature f : features) {
            int mask = f.getMask();
            newSet |= mask;
            newMask |= mask;
         }

         return this._formatReadFeatures == newSet && this._formatReadFeaturesToChange == newMask ? this : new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, this._parserFeatures, this._parserFeaturesToChange, newSet, newMask);
      }
   }

   public DeserializationConfig without(FormatFeature feature) {
      if (feature instanceof JsonReadFeature) {
         return this._withoutJsonReadFeatures(feature);
      } else {
         int newSet = this._formatReadFeatures & ~feature.getMask();
         int newMask = this._formatReadFeaturesToChange | feature.getMask();
         return this._formatReadFeatures == newSet && this._formatReadFeaturesToChange == newMask ? this : new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, this._parserFeatures, this._parserFeaturesToChange, newSet, newMask);
      }
   }

   public DeserializationConfig withoutFeatures(FormatFeature... features) {
      if (features.length > 0 && features[0] instanceof JsonReadFeature) {
         return this._withoutJsonReadFeatures(features);
      } else {
         int newSet = this._formatReadFeatures;
         int newMask = this._formatReadFeaturesToChange;

         for(FormatFeature f : features) {
            int mask = f.getMask();
            newSet &= ~mask;
            newMask |= mask;
         }

         return this._formatReadFeatures == newSet && this._formatReadFeaturesToChange == newMask ? this : new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, this._parserFeatures, this._parserFeaturesToChange, newSet, newMask);
      }
   }

   private DeserializationConfig _withJsonReadFeatures(FormatFeature... features) {
      int parserSet = this._parserFeatures;
      int parserMask = this._parserFeaturesToChange;
      int newSet = this._formatReadFeatures;
      int newMask = this._formatReadFeaturesToChange;

      for(FormatFeature f : features) {
         int mask = f.getMask();
         newSet |= mask;
         newMask |= mask;
         if (f instanceof JsonReadFeature) {
            JsonParser.Feature oldF = ((JsonReadFeature)f).mappedFeature();
            if (oldF != null) {
               int pmask = oldF.getMask();
               parserSet |= pmask;
               parserMask |= pmask;
            }
         }
      }

      return this._formatReadFeatures == newSet && this._formatReadFeaturesToChange == newMask && this._parserFeatures == parserSet && this._parserFeaturesToChange == parserMask ? this : new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, parserSet, parserMask, newSet, newMask);
   }

   private DeserializationConfig _withoutJsonReadFeatures(FormatFeature... features) {
      int parserSet = this._parserFeatures;
      int parserMask = this._parserFeaturesToChange;
      int newSet = this._formatReadFeatures;
      int newMask = this._formatReadFeaturesToChange;

      for(FormatFeature f : features) {
         int mask = f.getMask();
         newSet &= ~mask;
         newMask |= mask;
         if (f instanceof JsonReadFeature) {
            JsonParser.Feature oldF = ((JsonReadFeature)f).mappedFeature();
            if (oldF != null) {
               int pmask = oldF.getMask();
               parserSet &= ~pmask;
               parserMask |= pmask;
            }
         }
      }

      return this._formatReadFeatures == newSet && this._formatReadFeaturesToChange == newMask && this._parserFeatures == parserSet && this._parserFeaturesToChange == parserMask ? this : new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, parserSet, parserMask, newSet, newMask);
   }

   public DeserializationConfig with(JsonNodeFactory f) {
      return this._nodeFactory == f ? this : new DeserializationConfig(this, f);
   }

   public DeserializationConfig with(ConstructorDetector ctorDetector) {
      return this._ctorDetector == ctorDetector ? this : new DeserializationConfig(this, ctorDetector);
   }

   public DeserializationConfig withHandler(DeserializationProblemHandler h) {
      return LinkedNode.contains(this._problemHandlers, h) ? this : new DeserializationConfig(this, new LinkedNode(h, this._problemHandlers));
   }

   public DeserializationConfig withNoProblemHandlers() {
      return this._problemHandlers == null ? this : new DeserializationConfig(this, (LinkedNode)null);
   }

   public JsonParser initialize(JsonParser p) {
      if (this._parserFeaturesToChange != 0) {
         p.overrideStdFeatures(this._parserFeatures, this._parserFeaturesToChange);
      }

      if (this._formatReadFeaturesToChange != 0) {
         p.overrideFormatFeatures(this._formatReadFeatures, this._formatReadFeaturesToChange);
      }

      return p;
   }

   public JsonParser initialize(JsonParser p, FormatSchema schema) {
      if (this._parserFeaturesToChange != 0) {
         p.overrideStdFeatures(this._parserFeatures, this._parserFeaturesToChange);
      }

      if (this._formatReadFeaturesToChange != 0) {
         p.overrideFormatFeatures(this._formatReadFeatures, this._formatReadFeaturesToChange);
      }

      if (schema != null) {
         p.setSchema(schema);
      }

      return p;
   }

   public boolean useRootWrapping() {
      if (this._rootName != null) {
         return !this._rootName.isEmpty();
      } else {
         return this.isEnabled(DeserializationFeature.UNWRAP_ROOT_VALUE);
      }
   }

   public final boolean isEnabled(DeserializationFeature feature) {
      return (this._deserFeatures & feature.getMask()) != 0;
   }

   public final boolean isEnabled(JsonParser.Feature f, JsonFactory factory) {
      int mask = f.getMask();
      if ((this._parserFeaturesToChange & mask) != 0) {
         return (this._parserFeatures & f.getMask()) != 0;
      } else {
         return factory.isEnabled(f);
      }
   }

   public final boolean hasDeserializationFeatures(int featureMask) {
      return (this._deserFeatures & featureMask) == featureMask;
   }

   public final boolean hasSomeOfFeatures(int featureMask) {
      return (this._deserFeatures & featureMask) != 0;
   }

   public final int getDeserializationFeatures() {
      return this._deserFeatures;
   }

   public final boolean requiresFullValue() {
      return DeserializationFeature.FAIL_ON_TRAILING_TOKENS.enabledIn(this._deserFeatures);
   }

   public final boolean isEnabled(DatatypeFeature feature) {
      return this._datatypeFeatures.isEnabled(feature);
   }

   public LinkedNode getProblemHandlers() {
      return this._problemHandlers;
   }

   public final JsonNodeFactory getNodeFactory() {
      return this._nodeFactory;
   }

   public ConstructorDetector getConstructorDetector() {
      return this._ctorDetector == null ? ConstructorDetector.DEFAULT : this._ctorDetector;
   }

   public BeanDescription introspect(JavaType type) {
      return this.getClassIntrospector().forDeserialization(this, type, this);
   }

   public BeanDescription introspectForCreation(JavaType type) {
      return this.getClassIntrospector().forCreation(this, type, this);
   }

   public BeanDescription introspectForBuilder(JavaType builderType, BeanDescription valueTypeDesc) {
      return this.getClassIntrospector().forDeserializationWithBuilder(this, builderType, this, valueTypeDesc);
   }

   public TypeDeserializer findTypeDeserializer(JavaType baseType) throws JsonMappingException {
      BeanDescription bean = this.introspectClassAnnotations(baseType.getRawClass());
      AnnotatedClass ac = bean.getClassInfo();
      TypeResolverBuilder<?> b = this.getAnnotationIntrospector().findTypeResolver(this, ac, baseType);
      Collection<NamedType> subtypes = null;
      if (b == null) {
         b = this.getDefaultTyper(baseType);
         if (b == null) {
            return null;
         }
      } else {
         subtypes = this.getSubtypeResolver().collectAndResolveSubtypesByTypeId(this, ac);
      }

      return b.buildTypeDeserializer(this, baseType, subtypes);
   }

   public CoercionAction findCoercionAction(LogicalType targetType, Class targetClass, CoercionInputShape inputShape) {
      return this._coercionConfigs.findCoercion(this, targetType, targetClass, inputShape);
   }

   public CoercionAction findCoercionFromBlankString(LogicalType targetType, Class targetClass, CoercionAction actionIfBlankNotAllowed) {
      return this._coercionConfigs.findCoercionFromBlankString(this, targetType, targetClass, actionIfBlankNotAllowed);
   }
}
