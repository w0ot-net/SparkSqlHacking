package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.io.InputDecorator;
import com.fasterxml.jackson.core.io.OutputDecorator;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.JsonGeneratorDecorator;
import com.fasterxml.jackson.core.util.JsonRecyclerPools;
import com.fasterxml.jackson.core.util.RecyclerPool;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class TSFBuilder {
   protected static final int DEFAULT_FACTORY_FEATURE_FLAGS = JsonFactory.Feature.collectDefaults();
   protected static final int DEFAULT_PARSER_FEATURE_FLAGS = JsonParser.Feature.collectDefaults();
   protected static final int DEFAULT_GENERATOR_FEATURE_FLAGS = JsonGenerator.Feature.collectDefaults();
   protected int _factoryFeatures;
   protected int _streamReadFeatures;
   protected int _streamWriteFeatures;
   protected RecyclerPool _recyclerPool;
   protected InputDecorator _inputDecorator;
   protected OutputDecorator _outputDecorator;
   protected StreamReadConstraints _streamReadConstraints;
   protected StreamWriteConstraints _streamWriteConstraints;
   protected ErrorReportConfiguration _errorReportConfiguration;
   protected List _generatorDecorators;

   protected TSFBuilder() {
      this(DEFAULT_FACTORY_FEATURE_FLAGS, DEFAULT_PARSER_FEATURE_FLAGS, DEFAULT_GENERATOR_FEATURE_FLAGS);
   }

   protected TSFBuilder(JsonFactory base) {
      this(base._factoryFeatures, base._parserFeatures, base._generatorFeatures);
      this._inputDecorator = base._inputDecorator;
      this._outputDecorator = base._outputDecorator;
      this._streamReadConstraints = base._streamReadConstraints;
      this._streamWriteConstraints = base._streamWriteConstraints;
      this._errorReportConfiguration = base._errorReportConfiguration;
      this._generatorDecorators = _copy(base._generatorDecorators);
   }

   protected TSFBuilder(int factoryFeatures, int parserFeatures, int generatorFeatures) {
      this._recyclerPool = JsonRecyclerPools.defaultPool();
      this._factoryFeatures = factoryFeatures;
      this._streamReadFeatures = parserFeatures;
      this._streamWriteFeatures = generatorFeatures;
      this._inputDecorator = null;
      this._outputDecorator = null;
      this._streamReadConstraints = StreamReadConstraints.defaults();
      this._streamWriteConstraints = StreamWriteConstraints.defaults();
      this._errorReportConfiguration = ErrorReportConfiguration.defaults();
      this._generatorDecorators = null;
   }

   protected static List _copy(List src) {
      return (List)(src == null ? src : new ArrayList(src));
   }

   public int factoryFeaturesMask() {
      return this._factoryFeatures;
   }

   public int streamReadFeatures() {
      return this._streamReadFeatures;
   }

   public int streamWriteFeatures() {
      return this._streamWriteFeatures;
   }

   public RecyclerPool recyclerPool() {
      return this._recyclerPool;
   }

   public InputDecorator inputDecorator() {
      return this._inputDecorator;
   }

   public OutputDecorator outputDecorator() {
      return this._outputDecorator;
   }

   public TSFBuilder enable(JsonFactory.Feature f) {
      this._factoryFeatures |= f.getMask();
      return this._this();
   }

   public TSFBuilder disable(JsonFactory.Feature f) {
      this._factoryFeatures &= ~f.getMask();
      return this._this();
   }

   public TSFBuilder configure(JsonFactory.Feature f, boolean state) {
      return state ? this.enable(f) : this.disable(f);
   }

   public TSFBuilder enable(StreamReadFeature f) {
      this._streamReadFeatures |= f.mappedFeature().getMask();
      return this._this();
   }

   public TSFBuilder enable(StreamReadFeature first, StreamReadFeature... other) {
      this._streamReadFeatures |= first.mappedFeature().getMask();

      for(StreamReadFeature f : other) {
         this._streamReadFeatures |= f.mappedFeature().getMask();
      }

      return this._this();
   }

   public TSFBuilder disable(StreamReadFeature f) {
      this._streamReadFeatures &= ~f.mappedFeature().getMask();
      return this._this();
   }

   public TSFBuilder disable(StreamReadFeature first, StreamReadFeature... other) {
      this._streamReadFeatures &= ~first.mappedFeature().getMask();

      for(StreamReadFeature f : other) {
         this._streamReadFeatures &= ~f.mappedFeature().getMask();
      }

      return this._this();
   }

   public TSFBuilder configure(StreamReadFeature f, boolean state) {
      return state ? this.enable(f) : this.disable(f);
   }

   public TSFBuilder enable(StreamWriteFeature f) {
      this._streamWriteFeatures |= f.mappedFeature().getMask();
      return this._this();
   }

   public TSFBuilder enable(StreamWriteFeature first, StreamWriteFeature... other) {
      this._streamWriteFeatures |= first.mappedFeature().getMask();

      for(StreamWriteFeature f : other) {
         this._streamWriteFeatures |= f.mappedFeature().getMask();
      }

      return this._this();
   }

   public TSFBuilder disable(StreamWriteFeature f) {
      this._streamWriteFeatures &= ~f.mappedFeature().getMask();
      return this._this();
   }

   public TSFBuilder disable(StreamWriteFeature first, StreamWriteFeature... other) {
      this._streamWriteFeatures &= ~first.mappedFeature().getMask();

      for(StreamWriteFeature f : other) {
         this._streamWriteFeatures &= ~f.mappedFeature().getMask();
      }

      return this._this();
   }

   public TSFBuilder configure(StreamWriteFeature f, boolean state) {
      return state ? this.enable(f) : this.disable(f);
   }

   public TSFBuilder enable(JsonReadFeature f) {
      return this._failNonJSON(f);
   }

   public TSFBuilder enable(JsonReadFeature first, JsonReadFeature... other) {
      return this._failNonJSON(first);
   }

   public TSFBuilder disable(JsonReadFeature f) {
      return this._failNonJSON(f);
   }

   public TSFBuilder disable(JsonReadFeature first, JsonReadFeature... other) {
      return this._failNonJSON(first);
   }

   public TSFBuilder configure(JsonReadFeature f, boolean state) {
      return this._failNonJSON(f);
   }

   private TSFBuilder _failNonJSON(Object feature) {
      throw new IllegalArgumentException("Feature " + feature.getClass().getName() + "#" + feature.toString() + " not supported for non-JSON backend");
   }

   public TSFBuilder enable(JsonWriteFeature f) {
      return this._failNonJSON(f);
   }

   public TSFBuilder enable(JsonWriteFeature first, JsonWriteFeature... other) {
      return this._failNonJSON(first);
   }

   public TSFBuilder disable(JsonWriteFeature f) {
      return this._failNonJSON(f);
   }

   public TSFBuilder disable(JsonWriteFeature first, JsonWriteFeature... other) {
      return this._failNonJSON(first);
   }

   public TSFBuilder configure(JsonWriteFeature f, boolean state) {
      return this._failNonJSON(f);
   }

   public TSFBuilder recyclerPool(RecyclerPool p) {
      this._recyclerPool = (RecyclerPool)Objects.requireNonNull(p);
      return this._this();
   }

   public TSFBuilder inputDecorator(InputDecorator dec) {
      this._inputDecorator = dec;
      return this._this();
   }

   public TSFBuilder outputDecorator(OutputDecorator dec) {
      this._outputDecorator = dec;
      return this._this();
   }

   public TSFBuilder addDecorator(JsonGeneratorDecorator decorator) {
      if (this._generatorDecorators == null) {
         this._generatorDecorators = new ArrayList();
      }

      this._generatorDecorators.add(decorator);
      return this._this();
   }

   public TSFBuilder streamReadConstraints(StreamReadConstraints streamReadConstraints) {
      this._streamReadConstraints = (StreamReadConstraints)Objects.requireNonNull(streamReadConstraints);
      return this._this();
   }

   public TSFBuilder streamWriteConstraints(StreamWriteConstraints streamWriteConstraints) {
      this._streamWriteConstraints = (StreamWriteConstraints)Objects.requireNonNull(streamWriteConstraints);
      return this._this();
   }

   public TSFBuilder errorReportConfiguration(ErrorReportConfiguration errorReportConfiguration) {
      this._errorReportConfiguration = (ErrorReportConfiguration)Objects.requireNonNull(errorReportConfiguration);
      return this._this();
   }

   public abstract JsonFactory build();

   protected final TSFBuilder _this() {
      return this;
   }

   protected void _legacyEnable(JsonParser.Feature f) {
      if (f != null) {
         this._streamReadFeatures |= f.getMask();
      }

   }

   protected void _legacyDisable(JsonParser.Feature f) {
      if (f != null) {
         this._streamReadFeatures &= ~f.getMask();
      }

   }

   protected void _legacyEnable(JsonGenerator.Feature f) {
      if (f != null) {
         this._streamWriteFeatures |= f.getMask();
      }

   }

   protected void _legacyDisable(JsonGenerator.Feature f) {
      if (f != null) {
         this._streamWriteFeatures &= ~f.getMask();
      }

   }
}
