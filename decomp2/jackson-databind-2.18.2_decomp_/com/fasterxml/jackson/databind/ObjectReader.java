package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatFeature;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.filter.FilteringParserDelegate;
import com.fasterxml.jackson.core.filter.JsonPointerBasedFilter;
import com.fasterxml.jackson.core.filter.TokenFilter;
import com.fasterxml.jackson.core.filter.TokenFilter.Inclusion;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.DatatypeFeature;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.DataInput;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectReader extends ObjectCodec implements Versioned, Serializable {
   private static final long serialVersionUID = 2L;
   protected final DeserializationConfig _config;
   protected final DefaultDeserializationContext _context;
   protected final JsonFactory _parserFactory;
   protected final boolean _unwrapRoot;
   private final TokenFilter _filter;
   protected final JavaType _valueType;
   protected final JsonDeserializer _rootDeserializer;
   protected final Object _valueToUpdate;
   protected final FormatSchema _schema;
   protected final InjectableValues _injectableValues;
   protected final DataFormatReaders _dataFormatReaders;
   protected final ConcurrentHashMap _rootDeserializers;
   protected transient JavaType _jsonNodeType;

   protected ObjectReader(ObjectMapper mapper, DeserializationConfig config) {
      this(mapper, config, (JavaType)null, (Object)null, (FormatSchema)null, (InjectableValues)null);
   }

   protected ObjectReader(ObjectMapper mapper, DeserializationConfig config, JavaType valueType, Object valueToUpdate, FormatSchema schema, InjectableValues injectableValues) {
      this._config = config;
      this._context = mapper._deserializationContext;
      this._rootDeserializers = mapper._rootDeserializers;
      this._parserFactory = mapper._jsonFactory;
      this._valueType = valueType;
      this._valueToUpdate = valueToUpdate;
      this._schema = schema;
      this._injectableValues = injectableValues;
      this._unwrapRoot = config.useRootWrapping();
      this._rootDeserializer = this._prefetchRootDeserializer(valueType);
      this._dataFormatReaders = null;
      this._filter = null;
   }

   protected ObjectReader(ObjectReader base, DeserializationConfig config, JavaType valueType, JsonDeserializer rootDeser, Object valueToUpdate, FormatSchema schema, InjectableValues injectableValues, DataFormatReaders dataFormatReaders) {
      this._config = config;
      this._context = base._context;
      this._rootDeserializers = base._rootDeserializers;
      this._parserFactory = base._parserFactory;
      this._valueType = valueType;
      this._rootDeserializer = rootDeser;
      this._valueToUpdate = valueToUpdate;
      this._schema = schema;
      this._injectableValues = injectableValues;
      this._unwrapRoot = config.useRootWrapping();
      this._dataFormatReaders = dataFormatReaders;
      this._filter = base._filter;
   }

   protected ObjectReader(ObjectReader base, DeserializationConfig config) {
      this._config = config;
      this._context = base._context;
      this._rootDeserializers = base._rootDeserializers;
      this._parserFactory = base._parserFactory;
      this._valueType = base._valueType;
      this._rootDeserializer = base._rootDeserializer;
      this._valueToUpdate = base._valueToUpdate;
      this._schema = base._schema;
      this._injectableValues = base._injectableValues;
      this._unwrapRoot = config.useRootWrapping();
      this._dataFormatReaders = base._dataFormatReaders;
      this._filter = base._filter;
   }

   protected ObjectReader(ObjectReader base, JsonFactory f) {
      this._config = (DeserializationConfig)base._config.with(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, f.requiresPropertyOrdering());
      this._context = base._context;
      this._rootDeserializers = base._rootDeserializers;
      this._parserFactory = f;
      this._valueType = base._valueType;
      this._rootDeserializer = base._rootDeserializer;
      this._valueToUpdate = base._valueToUpdate;
      this._schema = base._schema;
      this._injectableValues = base._injectableValues;
      this._unwrapRoot = base._unwrapRoot;
      this._dataFormatReaders = base._dataFormatReaders;
      this._filter = base._filter;
   }

   protected ObjectReader(ObjectReader base, TokenFilter filter) {
      this._config = base._config;
      this._context = base._context;
      this._rootDeserializers = base._rootDeserializers;
      this._parserFactory = base._parserFactory;
      this._valueType = base._valueType;
      this._rootDeserializer = base._rootDeserializer;
      this._valueToUpdate = base._valueToUpdate;
      this._schema = base._schema;
      this._injectableValues = base._injectableValues;
      this._unwrapRoot = base._unwrapRoot;
      this._dataFormatReaders = base._dataFormatReaders;
      this._filter = filter;
   }

   public Version version() {
      return PackageVersion.VERSION;
   }

   protected ObjectReader _new(ObjectReader base, JsonFactory f) {
      return new ObjectReader(base, f);
   }

   protected ObjectReader _new(ObjectReader base, DeserializationConfig config) {
      return new ObjectReader(base, config);
   }

   protected ObjectReader _new(ObjectReader base, DeserializationConfig config, JavaType valueType, JsonDeserializer rootDeser, Object valueToUpdate, FormatSchema schema, InjectableValues injectableValues, DataFormatReaders dataFormatReaders) {
      return new ObjectReader(base, config, valueType, rootDeser, valueToUpdate, schema, injectableValues, dataFormatReaders);
   }

   protected MappingIterator _newIterator(JsonParser p, DeserializationContext ctxt, JsonDeserializer deser, boolean parserManaged) {
      return new MappingIterator(this._valueType, p, ctxt, deser, parserManaged, this._valueToUpdate);
   }

   protected JsonToken _initForReading(DeserializationContext ctxt, JsonParser p) throws IOException {
      this._config.initialize(p, this._schema);
      JsonToken t = p.currentToken();
      if (t == null) {
         t = p.nextToken();
         if (t == null) {
            ctxt.reportInputMismatch(this._valueType, "No content to map due to end-of-input");
         }
      }

      return t;
   }

   protected void _initForMultiRead(DeserializationContext ctxt, JsonParser p) throws IOException {
      this._config.initialize(p, this._schema);
   }

   public ObjectReader with(DeserializationFeature feature) {
      return this._with(this._config.with(feature));
   }

   public ObjectReader with(DeserializationFeature first, DeserializationFeature... other) {
      return this._with(this._config.with(first, other));
   }

   public ObjectReader withFeatures(DeserializationFeature... features) {
      return this._with(this._config.withFeatures(features));
   }

   public ObjectReader without(DeserializationFeature feature) {
      return this._with(this._config.without(feature));
   }

   public ObjectReader without(DeserializationFeature first, DeserializationFeature... other) {
      return this._with(this._config.without(first, other));
   }

   public ObjectReader withoutFeatures(DeserializationFeature... features) {
      return this._with(this._config.withoutFeatures(features));
   }

   public ObjectReader with(DatatypeFeature feature) {
      return this._with((DeserializationConfig)this._config.with((DatatypeFeature)feature));
   }

   public ObjectReader withFeatures(DatatypeFeature... features) {
      return this._with((DeserializationConfig)this._config.withFeatures((DatatypeFeature[])features));
   }

   public ObjectReader without(DatatypeFeature feature) {
      return this._with((DeserializationConfig)this._config.without((DatatypeFeature)feature));
   }

   public ObjectReader withoutFeatures(DatatypeFeature... features) {
      return this._with((DeserializationConfig)this._config.withoutFeatures((DatatypeFeature[])features));
   }

   public ObjectReader with(JsonParser.Feature feature) {
      return this._with(this._config.with(feature));
   }

   public ObjectReader withFeatures(JsonParser.Feature... features) {
      return this._with(this._config.withFeatures(features));
   }

   public ObjectReader without(JsonParser.Feature feature) {
      return this._with(this._config.without(feature));
   }

   public ObjectReader withoutFeatures(JsonParser.Feature... features) {
      return this._with(this._config.withoutFeatures(features));
   }

   public ObjectReader with(StreamReadFeature feature) {
      return this._with(this._config.with(feature.mappedFeature()));
   }

   public ObjectReader without(StreamReadFeature feature) {
      return this._with(this._config.without(feature.mappedFeature()));
   }

   public ObjectReader with(FormatFeature feature) {
      return this._with(this._config.with(feature));
   }

   public ObjectReader withFeatures(FormatFeature... features) {
      return this._with(this._config.withFeatures(features));
   }

   public ObjectReader without(FormatFeature feature) {
      return this._with(this._config.without(feature));
   }

   public ObjectReader withoutFeatures(FormatFeature... features) {
      return this._with(this._config.withoutFeatures(features));
   }

   public ObjectReader at(String pointerExpr) {
      this._assertNotNull("pointerExpr", pointerExpr);
      return new ObjectReader(this, new JsonPointerBasedFilter(pointerExpr));
   }

   public ObjectReader at(JsonPointer pointer) {
      this._assertNotNull("pointer", pointer);
      return new ObjectReader(this, new JsonPointerBasedFilter(pointer));
   }

   public ObjectReader with(DeserializationConfig config) {
      return this._with(config);
   }

   public ObjectReader with(InjectableValues injectableValues) {
      return this._injectableValues == injectableValues ? this : this._new(this, this._config, this._valueType, this._rootDeserializer, this._valueToUpdate, this._schema, injectableValues, this._dataFormatReaders);
   }

   public ObjectReader with(JsonNodeFactory f) {
      return this._with(this._config.with(f));
   }

   public ObjectReader with(JsonFactory f) {
      if (f == this._parserFactory) {
         return this;
      } else {
         ObjectReader r = this._new(this, f);
         if (f.getCodec() == null) {
            f.setCodec(r);
         }

         return r;
      }
   }

   public ObjectReader withRootName(String rootName) {
      return this._with((DeserializationConfig)this._config.withRootName((String)rootName));
   }

   public ObjectReader withRootName(PropertyName rootName) {
      return this._with(this._config.withRootName(rootName));
   }

   public ObjectReader withoutRootName() {
      return this._with(this._config.withRootName(PropertyName.NO_NAME));
   }

   public ObjectReader with(FormatSchema schema) {
      if (this._schema == schema) {
         return this;
      } else {
         this._verifySchemaType(schema);
         return this._new(this, this._config, this._valueType, this._rootDeserializer, this._valueToUpdate, schema, this._injectableValues, this._dataFormatReaders);
      }
   }

   public ObjectReader forType(JavaType valueType) {
      if (valueType != null && valueType.equals(this._valueType)) {
         return this;
      } else {
         JsonDeserializer<Object> rootDeser = this._prefetchRootDeserializer(valueType);
         DataFormatReaders det = this._dataFormatReaders;
         if (det != null) {
            det = det.withType(valueType);
         }

         return this._new(this, this._config, valueType, rootDeser, this._valueToUpdate, this._schema, this._injectableValues, det);
      }
   }

   public ObjectReader forType(Class valueType) {
      return this.forType(this._config.constructType(valueType));
   }

   public ObjectReader forType(Type valueType) {
      return this.forType(this._config.getTypeFactory().constructType(valueType));
   }

   public ObjectReader forType(TypeReference valueTypeRef) {
      return this.forType(this._config.getTypeFactory().constructType(valueTypeRef.getType()));
   }

   /** @deprecated */
   @Deprecated
   public ObjectReader withType(JavaType valueType) {
      return this.forType(valueType);
   }

   /** @deprecated */
   @Deprecated
   public ObjectReader withType(Class valueType) {
      return this.forType(this._config.constructType(valueType));
   }

   /** @deprecated */
   @Deprecated
   public ObjectReader withType(Type valueType) {
      return this.forType(this._config.getTypeFactory().constructType(valueType));
   }

   /** @deprecated */
   @Deprecated
   public ObjectReader withType(TypeReference valueTypeRef) {
      return this.forType(this._config.getTypeFactory().constructType(valueTypeRef.getType()));
   }

   public ObjectReader withValueToUpdate(Object value) {
      if (value == this._valueToUpdate) {
         return this;
      } else if (value == null) {
         return this._new(this, this._config, this._valueType, this._rootDeserializer, (Object)null, this._schema, this._injectableValues, this._dataFormatReaders);
      } else {
         JavaType t;
         if (this._valueType == null) {
            t = this._config.constructType(value.getClass());
         } else {
            t = this._valueType;
         }

         return this._new(this, this._config, t, this._rootDeserializer, value, this._schema, this._injectableValues, this._dataFormatReaders);
      }
   }

   public ObjectReader withView(Class activeView) {
      return this._with(this._config.withView(activeView));
   }

   public ObjectReader with(Locale l) {
      return this._with((DeserializationConfig)this._config.with((Locale)l));
   }

   public ObjectReader with(TimeZone tz) {
      return this._with((DeserializationConfig)this._config.with((TimeZone)tz));
   }

   public ObjectReader withHandler(DeserializationProblemHandler h) {
      return this._with(this._config.withHandler(h));
   }

   public ObjectReader with(Base64Variant defaultBase64) {
      return this._with((DeserializationConfig)this._config.with((Base64Variant)defaultBase64));
   }

   public ObjectReader withFormatDetection(ObjectReader... readers) {
      return this.withFormatDetection(new DataFormatReaders(readers));
   }

   public ObjectReader withFormatDetection(DataFormatReaders readers) {
      return this._new(this, this._config, this._valueType, this._rootDeserializer, this._valueToUpdate, this._schema, this._injectableValues, readers);
   }

   public ObjectReader with(ContextAttributes attrs) {
      return this._with(this._config.with(attrs));
   }

   public ObjectReader withAttributes(Map attrs) {
      return this._with((DeserializationConfig)this._config.withAttributes(attrs));
   }

   public ObjectReader withAttribute(Object key, Object value) {
      return this._with((DeserializationConfig)this._config.withAttribute(key, value));
   }

   public ObjectReader withoutAttribute(Object key) {
      return this._with((DeserializationConfig)this._config.withoutAttribute(key));
   }

   protected ObjectReader _with(DeserializationConfig newConfig) {
      if (newConfig == this._config) {
         return this;
      } else {
         ObjectReader r = this._new(this, newConfig);
         if (this._dataFormatReaders != null) {
            r = r.withFormatDetection(this._dataFormatReaders.with(newConfig));
         }

         return r;
      }
   }

   public boolean isEnabled(DeserializationFeature f) {
      return this._config.isEnabled(f);
   }

   public boolean isEnabled(MapperFeature f) {
      return this._config.isEnabled((MapperFeature)f);
   }

   public boolean isEnabled(DatatypeFeature f) {
      return this._config.isEnabled(f);
   }

   public boolean isEnabled(JsonParser.Feature f) {
      return this._config.isEnabled(f, this._parserFactory);
   }

   public boolean isEnabled(StreamReadFeature f) {
      return this._config.isEnabled(f.mappedFeature(), this._parserFactory);
   }

   public DeserializationConfig getConfig() {
      return this._config;
   }

   public JsonFactory getFactory() {
      return this._parserFactory;
   }

   public TypeFactory getTypeFactory() {
      return this._config.getTypeFactory();
   }

   public ContextAttributes getAttributes() {
      return this._config.getAttributes();
   }

   public InjectableValues getInjectableValues() {
      return this._injectableValues;
   }

   public JavaType getValueType() {
      return this._valueType;
   }

   public JsonParser createParser(File src) throws IOException {
      this._assertNotNull("src", src);
      return this._config.initialize(this._parserFactory.createParser(src), this._schema);
   }

   public JsonParser createParser(URL src) throws IOException {
      this._assertNotNull("src", src);
      return this._config.initialize(this._parserFactory.createParser(src), this._schema);
   }

   public JsonParser createParser(InputStream in) throws IOException {
      this._assertNotNull("in", in);
      return this._config.initialize(this._parserFactory.createParser(in), this._schema);
   }

   public JsonParser createParser(Reader r) throws IOException {
      this._assertNotNull("r", r);
      return this._config.initialize(this._parserFactory.createParser(r), this._schema);
   }

   public JsonParser createParser(byte[] content) throws IOException {
      this._assertNotNull("content", content);
      return this._config.initialize(this._parserFactory.createParser(content), this._schema);
   }

   public JsonParser createParser(byte[] content, int offset, int len) throws IOException {
      this._assertNotNull("content", content);
      return this._config.initialize(this._parserFactory.createParser(content, offset, len), this._schema);
   }

   public JsonParser createParser(String content) throws IOException {
      this._assertNotNull("content", content);
      return this._config.initialize(this._parserFactory.createParser(content), this._schema);
   }

   public JsonParser createParser(char[] content) throws IOException {
      this._assertNotNull("content", content);
      return this._config.initialize(this._parserFactory.createParser(content), this._schema);
   }

   public JsonParser createParser(char[] content, int offset, int len) throws IOException {
      this._assertNotNull("content", content);
      return this._config.initialize(this._parserFactory.createParser(content, offset, len), this._schema);
   }

   public JsonParser createParser(DataInput content) throws IOException {
      this._assertNotNull("content", content);
      return this._config.initialize(this._parserFactory.createParser(content), this._schema);
   }

   public JsonParser createNonBlockingByteArrayParser() throws IOException {
      return this._config.initialize(this._parserFactory.createNonBlockingByteArrayParser(), this._schema);
   }

   public Object readValue(JsonParser p) throws IOException {
      this._assertNotNull("p", p);
      return this._bind(p, this._valueToUpdate);
   }

   public Object readValue(JsonParser p, Class valueType) throws IOException {
      this._assertNotNull("p", p);
      return this.forType(valueType).readValue(p);
   }

   public Object readValue(JsonParser p, TypeReference valueTypeRef) throws IOException {
      this._assertNotNull("p", p);
      return this.forType(valueTypeRef).readValue(p);
   }

   public Object readValue(JsonParser p, ResolvedType valueType) throws IOException {
      this._assertNotNull("p", p);
      return this.forType((JavaType)valueType).readValue(p);
   }

   public Object readValue(JsonParser p, JavaType valueType) throws IOException {
      this._assertNotNull("p", p);
      return this.forType(valueType).readValue(p);
   }

   public Iterator readValues(JsonParser p, Class valueType) throws IOException {
      this._assertNotNull("p", p);
      return this.forType(valueType).readValues(p);
   }

   public Iterator readValues(JsonParser p, TypeReference valueTypeRef) throws IOException {
      this._assertNotNull("p", p);
      return this.forType(valueTypeRef).readValues(p);
   }

   public Iterator readValues(JsonParser p, ResolvedType valueType) throws IOException {
      this._assertNotNull("p", p);
      return this.readValues(p, (JavaType)valueType);
   }

   public Iterator readValues(JsonParser p, JavaType valueType) throws IOException {
      this._assertNotNull("p", p);
      return this.forType(valueType).readValues(p);
   }

   public JsonNode createArrayNode() {
      return this._config.getNodeFactory().arrayNode();
   }

   public JsonNode createObjectNode() {
      return this._config.getNodeFactory().objectNode();
   }

   public JsonNode missingNode() {
      return this._config.getNodeFactory().missingNode();
   }

   public JsonNode nullNode() {
      return this._config.getNodeFactory().nullNode();
   }

   public JsonParser treeAsTokens(TreeNode n) {
      this._assertNotNull("n", n);
      ObjectReader codec = this.withValueToUpdate((Object)null);
      return new TreeTraversingParser((JsonNode)n, codec);
   }

   public TreeNode readTree(JsonParser p) throws IOException {
      this._assertNotNull("p", p);
      return this._bindAsTreeOrNull(p);
   }

   public void writeTree(JsonGenerator g, TreeNode rootNode) {
      throw new UnsupportedOperationException();
   }

   public Object readValue(InputStream src) throws IOException {
      return this._dataFormatReaders != null ? this._detectBindAndClose(this._dataFormatReaders.findFormat(src), false) : this._bindAndClose(this._considerFilter(this.createParser(src), false));
   }

   public Object readValue(InputStream src, Class valueType) throws IOException {
      return this.forType(valueType).readValue(src);
   }

   public Object readValue(Reader src) throws IOException {
      if (this._dataFormatReaders != null) {
         this._reportUndetectableSource(src);
      }

      return this._bindAndClose(this._considerFilter(this.createParser(src), false));
   }

   public Object readValue(Reader src, Class valueType) throws IOException {
      return this.forType(valueType).readValue(src);
   }

   public Object readValue(String src) throws JsonProcessingException, JsonMappingException {
      if (this._dataFormatReaders != null) {
         this._reportUndetectableSource(src);
      }

      try {
         return this._bindAndClose(this._considerFilter(this.createParser(src), false));
      } catch (JsonProcessingException e) {
         throw e;
      } catch (IOException e) {
         throw JsonMappingException.fromUnexpectedIOE(e);
      }
   }

   public Object readValue(String src, Class valueType) throws IOException {
      return this.forType(valueType).readValue(src);
   }

   public Object readValue(byte[] content) throws IOException {
      return this._dataFormatReaders != null ? this._detectBindAndClose(content, 0, content.length) : this._bindAndClose(this._considerFilter(this.createParser(content), false));
   }

   public Object readValue(byte[] content, Class valueType) throws IOException {
      return this.forType(valueType).readValue(content);
   }

   public Object readValue(byte[] buffer, int offset, int length) throws IOException {
      return this._dataFormatReaders != null ? this._detectBindAndClose(buffer, offset, length) : this._bindAndClose(this._considerFilter(this.createParser(buffer, offset, length), false));
   }

   public Object readValue(byte[] buffer, int offset, int length, Class valueType) throws IOException {
      return this.forType(valueType).readValue(buffer, offset, length);
   }

   public Object readValue(File src) throws IOException {
      return this._dataFormatReaders != null ? this._detectBindAndClose(this._dataFormatReaders.findFormat(this._inputStream(src)), true) : this._bindAndClose(this._considerFilter(this.createParser(src), false));
   }

   public Object readValue(File src, Class valueType) throws IOException {
      return this.forType(valueType).readValue(src);
   }

   public Object readValue(URL src) throws IOException {
      return this._dataFormatReaders != null ? this._detectBindAndClose(this._dataFormatReaders.findFormat(this._inputStream(src)), true) : this._bindAndClose(this._considerFilter(this.createParser(src), false));
   }

   public Object readValue(URL src, Class valueType) throws IOException {
      return this.forType(valueType).readValue(src);
   }

   public Object readValue(JsonNode content) throws IOException {
      this._assertNotNull("content", content);
      if (this._dataFormatReaders != null) {
         this._reportUndetectableSource(content);
      }

      return this._bindAndClose(this._considerFilter(this.treeAsTokens(content), false));
   }

   public Object readValue(JsonNode content, Class valueType) throws IOException {
      return this.forType(valueType).readValue(content);
   }

   public Object readValue(DataInput src) throws IOException {
      if (this._dataFormatReaders != null) {
         this._reportUndetectableSource(src);
      }

      return this._bindAndClose(this._considerFilter(this.createParser(src), false));
   }

   public Object readValue(DataInput content, Class valueType) throws IOException {
      return this.forType(valueType).readValue(content);
   }

   public JsonNode readTree(InputStream src) throws IOException {
      return this._dataFormatReaders != null ? this._detectBindAndCloseAsTree(src) : this._bindAndCloseAsTree(this._considerFilter(this.createParser(src), false));
   }

   public JsonNode readTree(Reader src) throws IOException {
      if (this._dataFormatReaders != null) {
         this._reportUndetectableSource(src);
      }

      return this._bindAndCloseAsTree(this._considerFilter(this.createParser(src), false));
   }

   public JsonNode readTree(String json) throws JsonProcessingException, JsonMappingException {
      if (this._dataFormatReaders != null) {
         this._reportUndetectableSource(json);
      }

      try {
         return this._bindAndCloseAsTree(this._considerFilter(this.createParser(json), false));
      } catch (JsonProcessingException e) {
         throw e;
      } catch (IOException e) {
         throw JsonMappingException.fromUnexpectedIOE(e);
      }
   }

   public JsonNode readTree(byte[] json) throws IOException {
      this._assertNotNull("json", json);
      if (this._dataFormatReaders != null) {
         this._reportUndetectableSource(json);
      }

      return this._bindAndCloseAsTree(this._considerFilter(this.createParser(json), false));
   }

   public JsonNode readTree(byte[] json, int offset, int len) throws IOException {
      if (this._dataFormatReaders != null) {
         this._reportUndetectableSource(json);
      }

      return this._bindAndCloseAsTree(this._considerFilter(this.createParser(json, offset, len), false));
   }

   public JsonNode readTree(DataInput src) throws IOException {
      if (this._dataFormatReaders != null) {
         this._reportUndetectableSource(src);
      }

      return this._bindAndCloseAsTree(this._considerFilter(this.createParser(src), false));
   }

   public MappingIterator readValues(JsonParser p) throws IOException {
      this._assertNotNull("p", p);
      DeserializationContext ctxt = this.createDeserializationContext(p);
      return this._newIterator(p, ctxt, this._findRootDeserializer(ctxt), false);
   }

   public MappingIterator readValues(InputStream src) throws IOException {
      return this._dataFormatReaders != null ? this._detectBindAndReadValues(this._dataFormatReaders.findFormat(src), false) : this._bindAndReadValues(this._considerFilter(this.createParser(src), true));
   }

   public MappingIterator readValues(Reader src) throws IOException {
      if (this._dataFormatReaders != null) {
         this._reportUndetectableSource(src);
      }

      JsonParser p = this._considerFilter(this.createParser(src), true);
      DeserializationContext ctxt = this.createDeserializationContext(p);
      this._initForMultiRead(ctxt, p);
      p.nextToken();
      return this._newIterator(p, ctxt, this._findRootDeserializer(ctxt), true);
   }

   public MappingIterator readValues(String json) throws IOException {
      if (this._dataFormatReaders != null) {
         this._reportUndetectableSource(json);
      }

      JsonParser p = this._considerFilter(this.createParser(json), true);
      DeserializationContext ctxt = this.createDeserializationContext(p);
      this._initForMultiRead(ctxt, p);
      p.nextToken();
      return this._newIterator(p, ctxt, this._findRootDeserializer(ctxt), true);
   }

   public MappingIterator readValues(byte[] src, int offset, int length) throws IOException {
      return this._dataFormatReaders != null ? this._detectBindAndReadValues(this._dataFormatReaders.findFormat(src, offset, length), false) : this._bindAndReadValues(this._considerFilter(this.createParser(src, offset, length), true));
   }

   public final MappingIterator readValues(byte[] src) throws IOException {
      this._assertNotNull("src", src);
      return this.readValues(src, 0, src.length);
   }

   public MappingIterator readValues(File src) throws IOException {
      return this._dataFormatReaders != null ? this._detectBindAndReadValues(this._dataFormatReaders.findFormat(this._inputStream(src)), false) : this._bindAndReadValues(this._considerFilter(this.createParser(src), true));
   }

   public MappingIterator readValues(URL src) throws IOException {
      return this._dataFormatReaders != null ? this._detectBindAndReadValues(this._dataFormatReaders.findFormat(this._inputStream(src)), true) : this._bindAndReadValues(this._considerFilter(this.createParser(src), true));
   }

   public MappingIterator readValues(DataInput src) throws IOException {
      if (this._dataFormatReaders != null) {
         this._reportUndetectableSource(src);
      }

      return this._bindAndReadValues(this._considerFilter(this.createParser(src), true));
   }

   public Object treeToValue(TreeNode n, Class valueType) throws JsonProcessingException {
      this._assertNotNull("n", n);

      try {
         return this.readValue(this.treeAsTokens(n), valueType);
      } catch (JsonProcessingException e) {
         throw e;
      } catch (IOException e) {
         throw JsonMappingException.fromUnexpectedIOE(e);
      }
   }

   public Object treeToValue(TreeNode n, JavaType valueType) throws JsonProcessingException {
      this._assertNotNull("n", n);

      try {
         return this.readValue(this.treeAsTokens(n), valueType);
      } catch (JsonProcessingException e) {
         throw e;
      } catch (IOException e) {
         throw JsonMappingException.fromUnexpectedIOE(e);
      }
   }

   public void writeValue(JsonGenerator gen, Object value) throws IOException {
      throw new UnsupportedOperationException("Not implemented for ObjectReader");
   }

   protected Object _bind(JsonParser p, Object valueToUpdate) throws IOException {
      DefaultDeserializationContext ctxt = this.createDeserializationContext(p);
      JsonToken t = this._initForReading(ctxt, p);
      Object result;
      if (t == JsonToken.VALUE_NULL) {
         if (valueToUpdate == null) {
            result = this._findRootDeserializer(ctxt).getNullValue(ctxt);
         } else {
            result = valueToUpdate;
         }
      } else if (t != JsonToken.END_ARRAY && t != JsonToken.END_OBJECT) {
         result = ctxt.readRootValue(p, this._valueType, this._findRootDeserializer(ctxt), this._valueToUpdate);
      } else {
         result = valueToUpdate;
      }

      p.clearCurrentToken();
      if (this._config.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
         this._verifyNoTrailingTokens(p, ctxt, this._valueType);
      }

      return result;
   }

   protected Object _bindAndClose(JsonParser p0) throws IOException {
      JsonParser p = p0;
      Throwable var3 = null;

      Object var7;
      try {
         DefaultDeserializationContext ctxt = this.createDeserializationContext(p);
         JsonToken t = this._initForReading(ctxt, p);
         Object result;
         if (t == JsonToken.VALUE_NULL) {
            if (this._valueToUpdate == null) {
               result = this._findRootDeserializer(ctxt).getNullValue(ctxt);
            } else {
               result = this._valueToUpdate;
            }
         } else if (t != JsonToken.END_ARRAY && t != JsonToken.END_OBJECT) {
            result = ctxt.readRootValue(p, this._valueType, this._findRootDeserializer(ctxt), this._valueToUpdate);
         } else {
            result = this._valueToUpdate;
         }

         if (this._config.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
            this._verifyNoTrailingTokens(p, ctxt, this._valueType);
         }

         var7 = result;
      } catch (Throwable var16) {
         var3 = var16;
         throw var16;
      } finally {
         if (p0 != null) {
            if (var3 != null) {
               try {
                  p.close();
               } catch (Throwable var15) {
                  var3.addSuppressed(var15);
               }
            } else {
               p0.close();
            }
         }

      }

      return var7;
   }

   protected final JsonNode _bindAndCloseAsTree(JsonParser p0) throws IOException {
      JsonParser p = p0;
      Throwable var3 = null;

      JsonNode var4;
      try {
         var4 = this._bindAsTree(p);
      } catch (Throwable var13) {
         var3 = var13;
         throw var13;
      } finally {
         if (p0 != null) {
            if (var3 != null) {
               try {
                  p.close();
               } catch (Throwable var12) {
                  var3.addSuppressed(var12);
               }
            } else {
               p0.close();
            }
         }

      }

      return var4;
   }

   protected final JsonNode _bindAsTree(JsonParser p) throws IOException {
      if (this._valueToUpdate != null) {
         return (JsonNode)this._bind(p, this._valueToUpdate);
      } else {
         this._config.initialize(p);
         if (this._schema != null) {
            p.setSchema(this._schema);
         }

         JsonToken t = p.currentToken();
         if (t == null) {
            t = p.nextToken();
            if (t == null) {
               return this._config.getNodeFactory().missingNode();
            }
         }

         DefaultDeserializationContext ctxt = this.createDeserializationContext(p);
         JsonNode resultNode;
         if (t == JsonToken.VALUE_NULL) {
            resultNode = this._config.getNodeFactory().nullNode();
         } else {
            resultNode = (JsonNode)ctxt.readRootValue(p, this._jsonNodeType(), this._findTreeDeserializer(ctxt), (Object)null);
         }

         p.clearCurrentToken();
         if (this._config.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
            this._verifyNoTrailingTokens(p, ctxt, this._jsonNodeType());
         }

         return resultNode;
      }
   }

   protected final JsonNode _bindAsTreeOrNull(JsonParser p) throws IOException {
      if (this._valueToUpdate != null) {
         return (JsonNode)this._bind(p, this._valueToUpdate);
      } else {
         this._config.initialize(p);
         if (this._schema != null) {
            p.setSchema(this._schema);
         }

         JsonToken t = p.currentToken();
         if (t == null) {
            t = p.nextToken();
            if (t == null) {
               return null;
            }
         }

         DefaultDeserializationContext ctxt = this.createDeserializationContext(p);
         JsonNode resultNode;
         if (t == JsonToken.VALUE_NULL) {
            resultNode = this._config.getNodeFactory().nullNode();
         } else {
            resultNode = (JsonNode)ctxt.readRootValue(p, this._jsonNodeType(), this._findTreeDeserializer(ctxt), (Object)null);
         }

         p.clearCurrentToken();
         if (this._config.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
            this._verifyNoTrailingTokens(p, ctxt, this._jsonNodeType());
         }

         return resultNode;
      }
   }

   protected MappingIterator _bindAndReadValues(JsonParser p) throws IOException {
      DeserializationContext ctxt = this.createDeserializationContext(p);
      this._initForMultiRead(ctxt, p);
      p.nextToken();
      return this._newIterator(p, ctxt, this._findRootDeserializer(ctxt), true);
   }

   protected JsonParser _considerFilter(JsonParser p, boolean multiValue) {
      return (JsonParser)(this._filter != null && !FilteringParserDelegate.class.isInstance(p) ? new FilteringParserDelegate(p, this._filter, Inclusion.ONLY_INCLUDE_ALL, multiValue) : p);
   }

   protected final void _verifyNoTrailingTokens(JsonParser p, DeserializationContext ctxt, JavaType bindType) throws IOException {
      JsonToken t = p.nextToken();
      if (t != null) {
         Class<?> bt = ClassUtil.rawClass(bindType);
         if (bt == null && this._valueToUpdate != null) {
            bt = this._valueToUpdate.getClass();
         }

         ctxt.reportTrailingTokens(bt, p, t);
      }

   }

   protected Object _detectBindAndClose(byte[] src, int offset, int length) throws IOException {
      DataFormatReaders.Match match = this._dataFormatReaders.findFormat(src, offset, length);
      if (!match.hasMatch()) {
         this._reportUnkownFormat(this._dataFormatReaders, match);
      }

      JsonParser p = match.createParserWithMatch();
      return match.getReader()._bindAndClose(p);
   }

   protected Object _detectBindAndClose(DataFormatReaders.Match match, boolean forceClosing) throws IOException {
      if (!match.hasMatch()) {
         this._reportUnkownFormat(this._dataFormatReaders, match);
      }

      JsonParser p = match.createParserWithMatch();
      if (forceClosing) {
         p.enable(Feature.AUTO_CLOSE_SOURCE);
      }

      return match.getReader()._bindAndClose(p);
   }

   protected MappingIterator _detectBindAndReadValues(DataFormatReaders.Match match, boolean forceClosing) throws IOException {
      if (!match.hasMatch()) {
         this._reportUnkownFormat(this._dataFormatReaders, match);
      }

      JsonParser p = match.createParserWithMatch();
      if (forceClosing) {
         p.enable(Feature.AUTO_CLOSE_SOURCE);
      }

      return match.getReader()._bindAndReadValues(p);
   }

   protected JsonNode _detectBindAndCloseAsTree(InputStream in) throws IOException {
      DataFormatReaders.Match match = this._dataFormatReaders.findFormat(in);
      if (!match.hasMatch()) {
         this._reportUnkownFormat(this._dataFormatReaders, match);
      }

      JsonParser p = match.createParserWithMatch();
      p.enable(Feature.AUTO_CLOSE_SOURCE);
      return match.getReader()._bindAndCloseAsTree(p);
   }

   protected void _reportUnkownFormat(DataFormatReaders detector, DataFormatReaders.Match match) throws IOException {
      throw new JsonParseException((JsonParser)null, "Cannot detect format from input, does not look like any of detectable formats " + detector.toString());
   }

   protected void _verifySchemaType(FormatSchema schema) {
      if (schema != null && !this._parserFactory.canUseSchema(schema)) {
         throw new IllegalArgumentException("Cannot use FormatSchema of type " + schema.getClass().getName() + " for format " + this._parserFactory.getFormatName());
      }
   }

   protected DefaultDeserializationContext createDeserializationContext(JsonParser p) {
      return this._context.createInstance(this._config, p, this._injectableValues);
   }

   protected DefaultDeserializationContext createDummyDeserializationContext() {
      return this._context.createDummyInstance(this._config);
   }

   protected InputStream _inputStream(URL src) throws IOException {
      return src.openStream();
   }

   protected InputStream _inputStream(File f) throws IOException {
      return new FileInputStream(f);
   }

   protected void _reportUndetectableSource(Object src) throws StreamReadException {
      throw new JsonParseException((JsonParser)null, "Cannot use source of type " + src.getClass().getName() + " with format auto-detection: must be byte- not char-based");
   }

   protected JsonDeserializer _findRootDeserializer(DeserializationContext ctxt) throws DatabindException {
      if (this._rootDeserializer != null) {
         return this._rootDeserializer;
      } else {
         JavaType t = this._valueType;
         if (t == null) {
            ctxt.reportBadDefinition((JavaType)null, "No value type configured for ObjectReader");
         }

         JsonDeserializer<Object> deser = (JsonDeserializer)this._rootDeserializers.get(t);
         if (deser != null) {
            return deser;
         } else {
            deser = ctxt.findRootValueDeserializer(t);
            if (deser == null) {
               ctxt.reportBadDefinition(t, "Cannot find a deserializer for type " + t);
            }

            this._rootDeserializers.put(t, deser);
            return deser;
         }
      }
   }

   protected JsonDeserializer _findTreeDeserializer(DeserializationContext ctxt) throws DatabindException {
      JavaType nodeType = this._jsonNodeType();
      JsonDeserializer<Object> deser = (JsonDeserializer)this._rootDeserializers.get(nodeType);
      if (deser == null) {
         deser = ctxt.findRootValueDeserializer(nodeType);
         if (deser == null) {
            ctxt.reportBadDefinition(nodeType, "Cannot find a deserializer for type " + nodeType);
         }

         this._rootDeserializers.put(nodeType, deser);
      }

      return deser;
   }

   protected JsonDeserializer _prefetchRootDeserializer(JavaType valueType) {
      if (valueType != null && this._config.isEnabled(DeserializationFeature.EAGER_DESERIALIZER_FETCH)) {
         JsonDeserializer<Object> deser = (JsonDeserializer)this._rootDeserializers.get(valueType);
         if (deser == null) {
            try {
               DeserializationContext ctxt = this.createDummyDeserializationContext();
               deser = ctxt.findRootValueDeserializer(valueType);
               if (deser != null) {
                  this._rootDeserializers.put(valueType, deser);
               }

               return deser;
            } catch (JacksonException var4) {
            }
         }

         return deser;
      } else {
         return null;
      }
   }

   protected final JavaType _jsonNodeType() {
      JavaType t = this._jsonNodeType;
      if (t == null) {
         t = this.getTypeFactory().constructType((Type)JsonNode.class);
         this._jsonNodeType = t;
      }

      return t;
   }

   protected final void _assertNotNull(String paramName, Object src) {
      if (src == null) {
         throw new IllegalArgumentException(String.format("argument \"%s\" is null", paramName));
      }
   }
}
