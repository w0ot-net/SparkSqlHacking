package shaded.parquet.com.fasterxml.jackson.databind.cfg;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.ServiceLoader;
import java.util.TimeZone;
import java.util.function.Consumer;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonAutoDetect;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonInclude;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonSetter;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonTypeInfo;
import shaded.parquet.com.fasterxml.jackson.annotation.PropertyAccessor;
import shaded.parquet.com.fasterxml.jackson.core.Base64Variant;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.core.PrettyPrinter;
import shaded.parquet.com.fasterxml.jackson.core.StreamReadFeature;
import shaded.parquet.com.fasterxml.jackson.core.StreamWriteFeature;
import shaded.parquet.com.fasterxml.jackson.core.TokenStreamFactory;
import shaded.parquet.com.fasterxml.jackson.databind.AnnotationIntrospector;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationFeature;
import shaded.parquet.com.fasterxml.jackson.databind.InjectableValues;
import shaded.parquet.com.fasterxml.jackson.databind.MapperFeature;
import shaded.parquet.com.fasterxml.jackson.databind.Module;
import shaded.parquet.com.fasterxml.jackson.databind.ObjectMapper;
import shaded.parquet.com.fasterxml.jackson.databind.PropertyNamingStrategy;
import shaded.parquet.com.fasterxml.jackson.databind.SerializationFeature;
import shaded.parquet.com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.AccessorNamingStrategy;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.DefaultAccessorNamingStrategy;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.NamedType;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import shaded.parquet.com.fasterxml.jackson.databind.node.JsonNodeFactory;
import shaded.parquet.com.fasterxml.jackson.databind.ser.FilterProvider;
import shaded.parquet.com.fasterxml.jackson.databind.ser.SerializerFactory;
import shaded.parquet.com.fasterxml.jackson.databind.type.LogicalType;
import shaded.parquet.com.fasterxml.jackson.databind.type.TypeFactory;

public abstract class MapperBuilder {
   protected final ObjectMapper _mapper;

   protected MapperBuilder(ObjectMapper mapper) {
      this._mapper = mapper;
   }

   public ObjectMapper build() {
      return this._mapper;
   }

   public boolean isEnabled(MapperFeature f) {
      return this._mapper.isEnabled(f);
   }

   public boolean isEnabled(DeserializationFeature f) {
      return this._mapper.isEnabled(f);
   }

   public boolean isEnabled(SerializationFeature f) {
      return this._mapper.isEnabled(f);
   }

   public boolean isEnabled(JsonParser.Feature f) {
      return this._mapper.isEnabled(f);
   }

   public boolean isEnabled(JsonGenerator.Feature f) {
      return this._mapper.isEnabled(f);
   }

   public TokenStreamFactory streamFactory() {
      return this._mapper.tokenStreamFactory();
   }

   public MapperBuilder enable(MapperFeature... features) {
      this._mapper.enable(features);
      return this._this();
   }

   public MapperBuilder disable(MapperFeature... features) {
      this._mapper.disable(features);
      return this._this();
   }

   public MapperBuilder configure(MapperFeature feature, boolean state) {
      this._mapper.configure(feature, state);
      return this._this();
   }

   public MapperBuilder enable(SerializationFeature... features) {
      for(SerializationFeature f : features) {
         this._mapper.enable(f);
      }

      return this._this();
   }

   public MapperBuilder disable(SerializationFeature... features) {
      for(SerializationFeature f : features) {
         this._mapper.disable(f);
      }

      return this._this();
   }

   public MapperBuilder configure(SerializationFeature feature, boolean state) {
      this._mapper.configure(feature, state);
      return this._this();
   }

   public MapperBuilder enable(DeserializationFeature... features) {
      for(DeserializationFeature f : features) {
         this._mapper.enable(f);
      }

      return this._this();
   }

   public MapperBuilder disable(DeserializationFeature... features) {
      for(DeserializationFeature f : features) {
         this._mapper.disable(f);
      }

      return this._this();
   }

   public MapperBuilder configure(DeserializationFeature feature, boolean state) {
      this._mapper.configure(feature, state);
      return this._this();
   }

   public MapperBuilder enable(DatatypeFeature... features) {
      for(DatatypeFeature f : features) {
         this._mapper.configure(f, true);
      }

      return this._this();
   }

   public MapperBuilder disable(DatatypeFeature... features) {
      for(DatatypeFeature f : features) {
         this._mapper.configure(f, false);
      }

      return this._this();
   }

   public MapperBuilder configure(DatatypeFeature feature, boolean state) {
      this._mapper.configure(feature, state);
      return this._this();
   }

   public MapperBuilder enable(JsonParser.Feature... features) {
      this._mapper.enable(features);
      return this._this();
   }

   public MapperBuilder disable(JsonParser.Feature... features) {
      this._mapper.disable(features);
      return this._this();
   }

   public MapperBuilder configure(JsonParser.Feature feature, boolean state) {
      this._mapper.configure(feature, state);
      return this._this();
   }

   public MapperBuilder enable(JsonGenerator.Feature... features) {
      this._mapper.enable(features);
      return this._this();
   }

   public MapperBuilder disable(JsonGenerator.Feature... features) {
      this._mapper.disable(features);
      return this._this();
   }

   public MapperBuilder configure(JsonGenerator.Feature feature, boolean state) {
      this._mapper.configure(feature, state);
      return this._this();
   }

   public MapperBuilder enable(StreamReadFeature... features) {
      for(StreamReadFeature f : features) {
         this._mapper.enable(f.mappedFeature());
      }

      return this._this();
   }

   public MapperBuilder disable(StreamReadFeature... features) {
      for(StreamReadFeature f : features) {
         this._mapper.disable(f.mappedFeature());
      }

      return this._this();
   }

   public MapperBuilder configure(StreamReadFeature feature, boolean state) {
      this._mapper.configure(feature.mappedFeature(), state);
      return this._this();
   }

   public MapperBuilder enable(StreamWriteFeature... features) {
      for(StreamWriteFeature f : features) {
         this._mapper.enable(f.mappedFeature());
      }

      return this._this();
   }

   public MapperBuilder disable(StreamWriteFeature... features) {
      for(StreamWriteFeature f : features) {
         this._mapper.disable(f.mappedFeature());
      }

      return this._this();
   }

   public MapperBuilder configure(StreamWriteFeature feature, boolean state) {
      this._mapper.configure(feature.mappedFeature(), state);
      return this._this();
   }

   public MapperBuilder withConfigOverride(Class forType, Consumer handler) {
      handler.accept(this._mapper.configOverride(forType));
      return this._this();
   }

   public MapperBuilder withCoercionConfig(LogicalType forType, Consumer handler) {
      handler.accept(this._mapper.coercionConfigFor(forType));
      return this._this();
   }

   public MapperBuilder withCoercionConfig(Class forType, Consumer handler) {
      handler.accept(this._mapper.coercionConfigFor(forType));
      return this._this();
   }

   public MapperBuilder withCoercionConfigDefaults(Consumer handler) {
      handler.accept(this._mapper.coercionConfigDefaults());
      return this._this();
   }

   public MapperBuilder addModule(Module module) {
      this._mapper.registerModule(module);
      return this._this();
   }

   public MapperBuilder addModules(Module... modules) {
      for(Module module : modules) {
         this.addModule(module);
      }

      return this._this();
   }

   public MapperBuilder addModules(Iterable modules) {
      for(Module module : modules) {
         this.addModule(module);
      }

      return this._this();
   }

   public static List findModules() {
      return findModules((ClassLoader)null);
   }

   public static List findModules(ClassLoader classLoader) {
      ArrayList<Module> modules = new ArrayList();

      for(Module module : secureGetServiceLoader(Module.class, classLoader)) {
         modules.add(module);
      }

      return modules;
   }

   private static ServiceLoader secureGetServiceLoader(final Class clazz, final ClassLoader classLoader) {
      SecurityManager sm = System.getSecurityManager();
      if (sm == null) {
         return classLoader == null ? ServiceLoader.load(clazz) : ServiceLoader.load(clazz, classLoader);
      } else {
         return (ServiceLoader)AccessController.doPrivileged(new PrivilegedAction() {
            public ServiceLoader run() {
               return classLoader == null ? ServiceLoader.load(clazz) : ServiceLoader.load(clazz, classLoader);
            }
         });
      }
   }

   public MapperBuilder findAndAddModules() {
      return this.addModules((Iterable)findModules());
   }

   public MapperBuilder annotationIntrospector(AnnotationIntrospector intr) {
      this._mapper.setAnnotationIntrospector(intr);
      return this._this();
   }

   public MapperBuilder defaultAttributes(ContextAttributes attrs) {
      this._mapper.setDefaultAttributes(attrs);
      return this._this();
   }

   public MapperBuilder typeFactory(TypeFactory f) {
      this._mapper.setTypeFactory(f);
      return this._this();
   }

   public MapperBuilder subtypeResolver(SubtypeResolver r) {
      this._mapper.setSubtypeResolver(r);
      return this._this();
   }

   public MapperBuilder visibility(VisibilityChecker vc) {
      this._mapper.setVisibility(vc);
      return this._this();
   }

   public MapperBuilder visibility(PropertyAccessor forMethod, JsonAutoDetect.Visibility visibility) {
      this._mapper.setVisibility(forMethod, visibility);
      return this._this();
   }

   public MapperBuilder handlerInstantiator(HandlerInstantiator hi) {
      this._mapper.setHandlerInstantiator(hi);
      return this._this();
   }

   public MapperBuilder propertyNamingStrategy(PropertyNamingStrategy s) {
      this._mapper.setPropertyNamingStrategy(s);
      return this._this();
   }

   public MapperBuilder accessorNaming(AccessorNamingStrategy.Provider s) {
      if (s == null) {
         s = new DefaultAccessorNamingStrategy.Provider();
      }

      this._mapper.setAccessorNaming(s);
      return this._this();
   }

   public MapperBuilder serializerFactory(SerializerFactory f) {
      this._mapper.setSerializerFactory(f);
      return this._this();
   }

   public MapperBuilder filterProvider(FilterProvider prov) {
      this._mapper.setFilterProvider(prov);
      return this._this();
   }

   public MapperBuilder defaultPrettyPrinter(PrettyPrinter pp) {
      this._mapper.setDefaultPrettyPrinter(pp);
      return this._this();
   }

   public MapperBuilder injectableValues(InjectableValues v) {
      this._mapper.setInjectableValues(v);
      return this._this();
   }

   public MapperBuilder nodeFactory(JsonNodeFactory f) {
      this._mapper.setNodeFactory(f);
      return this._this();
   }

   public MapperBuilder constructorDetector(ConstructorDetector cd) {
      this._mapper.setConstructorDetector(cd);
      return this._this();
   }

   public MapperBuilder cacheProvider(CacheProvider cacheProvider) {
      this._mapper.setCacheProvider(cacheProvider);
      return this._this();
   }

   public MapperBuilder addHandler(DeserializationProblemHandler h) {
      this._mapper.addHandler(h);
      return this._this();
   }

   public MapperBuilder clearProblemHandlers() {
      this._mapper.clearProblemHandlers();
      return this._this();
   }

   public MapperBuilder defaultSetterInfo(JsonSetter.Value v) {
      this._mapper.setDefaultSetterInfo(v);
      return this._this();
   }

   public MapperBuilder defaultMergeable(Boolean b) {
      this._mapper.setDefaultMergeable(b);
      return this._this();
   }

   public MapperBuilder defaultLeniency(Boolean b) {
      this._mapper.setDefaultLeniency(b);
      return this._this();
   }

   public MapperBuilder defaultDateFormat(DateFormat df) {
      this._mapper.setDateFormat(df);
      return this._this();
   }

   public MapperBuilder defaultTimeZone(TimeZone tz) {
      this._mapper.setTimeZone(tz);
      return this._this();
   }

   public MapperBuilder defaultLocale(Locale locale) {
      this._mapper.setLocale(locale);
      return this._this();
   }

   public MapperBuilder defaultBase64Variant(Base64Variant v) {
      this._mapper.setBase64Variant(v);
      return this._this();
   }

   public MapperBuilder serializationInclusion(JsonInclude.Include incl) {
      this._mapper.setSerializationInclusion(incl);
      return this._this();
   }

   public MapperBuilder defaultPropertyInclusion(JsonInclude.Value incl) {
      this._mapper.setDefaultPropertyInclusion(incl);
      return this._this();
   }

   public MapperBuilder addMixIn(Class target, Class mixinSource) {
      this._mapper.addMixIn(target, mixinSource);
      return this._this();
   }

   public MapperBuilder removeMixIn(Class target) {
      this._mapper.addMixIn(target, (Class)null);
      return this._this();
   }

   public MapperBuilder registerSubtypes(Class... subtypes) {
      this._mapper.registerSubtypes(subtypes);
      return this._this();
   }

   public MapperBuilder registerSubtypes(NamedType... subtypes) {
      this._mapper.registerSubtypes(subtypes);
      return this._this();
   }

   public MapperBuilder registerSubtypes(Collection subtypes) {
      this._mapper.registerSubtypes(subtypes);
      return this._this();
   }

   public MapperBuilder polymorphicTypeValidator(PolymorphicTypeValidator ptv) {
      this._mapper.setPolymorphicTypeValidator(ptv);
      return this._this();
   }

   public MapperBuilder activateDefaultTyping(PolymorphicTypeValidator subtypeValidator) {
      this._mapper.activateDefaultTyping(subtypeValidator);
      return this._this();
   }

   public MapperBuilder activateDefaultTyping(PolymorphicTypeValidator subtypeValidator, ObjectMapper.DefaultTyping dti) {
      this._mapper.activateDefaultTyping(subtypeValidator, dti);
      return this._this();
   }

   public MapperBuilder activateDefaultTyping(PolymorphicTypeValidator subtypeValidator, ObjectMapper.DefaultTyping applicability, JsonTypeInfo.As includeAs) {
      this._mapper.activateDefaultTyping(subtypeValidator, applicability, includeAs);
      return this._this();
   }

   public MapperBuilder activateDefaultTypingAsProperty(PolymorphicTypeValidator subtypeValidator, ObjectMapper.DefaultTyping applicability, String propertyName) {
      this._mapper.activateDefaultTypingAsProperty(subtypeValidator, applicability, propertyName);
      return this._this();
   }

   public MapperBuilder deactivateDefaultTyping() {
      this._mapper.deactivateDefaultTyping();
      return this._this();
   }

   public MapperBuilder setDefaultTyping(TypeResolverBuilder typer) {
      this._mapper.setDefaultTyping(typer);
      return this._this();
   }

   protected final MapperBuilder _this() {
      return this;
   }
}
