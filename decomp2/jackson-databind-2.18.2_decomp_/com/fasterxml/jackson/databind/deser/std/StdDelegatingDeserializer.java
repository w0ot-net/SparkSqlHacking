package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.util.Collection;

public class StdDelegatingDeserializer extends StdDeserializer implements ContextualDeserializer, ResolvableDeserializer {
   private static final long serialVersionUID = 1L;
   protected final Converter _converter;
   protected final JavaType _delegateType;
   protected final JsonDeserializer _delegateDeserializer;

   public StdDelegatingDeserializer(Converter converter) {
      super(Object.class);
      this._converter = converter;
      this._delegateType = null;
      this._delegateDeserializer = null;
   }

   public StdDelegatingDeserializer(Converter converter, JavaType delegateType, JsonDeserializer delegateDeserializer) {
      super(delegateType);
      this._converter = converter;
      this._delegateType = delegateType;
      this._delegateDeserializer = delegateDeserializer;
   }

   protected StdDelegatingDeserializer(StdDelegatingDeserializer src) {
      super((StdDeserializer)src);
      this._converter = src._converter;
      this._delegateType = src._delegateType;
      this._delegateDeserializer = src._delegateDeserializer;
   }

   protected StdDelegatingDeserializer withDelegate(Converter converter, JavaType delegateType, JsonDeserializer delegateDeserializer) {
      ClassUtil.verifyMustOverride(StdDelegatingDeserializer.class, this, "withDelegate");
      return new StdDelegatingDeserializer(converter, delegateType, delegateDeserializer);
   }

   public JsonDeserializer unwrappingDeserializer(NameTransformer unwrapper) {
      ClassUtil.verifyMustOverride(StdDelegatingDeserializer.class, this, "unwrappingDeserializer");
      return this.replaceDelegatee(this._delegateDeserializer.unwrappingDeserializer(unwrapper));
   }

   public JsonDeserializer replaceDelegatee(JsonDeserializer delegatee) {
      ClassUtil.verifyMustOverride(StdDelegatingDeserializer.class, this, "replaceDelegatee");
      return delegatee == this._delegateDeserializer ? this : new StdDelegatingDeserializer(this._converter, this._delegateType, delegatee);
   }

   public void resolve(DeserializationContext ctxt) throws JsonMappingException {
      if (this._delegateDeserializer != null && this._delegateDeserializer instanceof ResolvableDeserializer) {
         ((ResolvableDeserializer)this._delegateDeserializer).resolve(ctxt);
      }

   }

   public JsonDeserializer createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
      if (this._delegateDeserializer != null) {
         JsonDeserializer<?> deser = ctxt.handleSecondaryContextualization(this._delegateDeserializer, property, this._delegateType);
         return deser != this._delegateDeserializer ? this.withDelegate(this._converter, this._delegateType, deser) : this;
      } else {
         JavaType delegateType = this._converter.getInputType(ctxt.getTypeFactory());
         return this.withDelegate(this._converter, delegateType, ctxt.findContextualValueDeserializer(delegateType, property));
      }
   }

   public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      Object delegateValue = this._delegateDeserializer.deserialize(p, ctxt);
      return delegateValue == null ? null : this.convertValue(delegateValue);
   }

   public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
      Object delegateValue = this._delegateDeserializer.deserialize(p, ctxt);
      return delegateValue == null ? null : this.convertValue(delegateValue);
   }

   public Object deserialize(JsonParser p, DeserializationContext ctxt, Object intoValue) throws IOException {
      return this._delegateType.getRawClass().isAssignableFrom(intoValue.getClass()) ? this._delegateDeserializer.deserialize(p, ctxt, intoValue) : this._handleIncompatibleUpdateValue(p, ctxt, intoValue);
   }

   public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer, Object intoValue) throws IOException, JacksonException {
      return !this._delegateType.getRawClass().isAssignableFrom(intoValue.getClass()) ? this._handleIncompatibleUpdateValue(p, ctxt, intoValue) : this._delegateDeserializer.deserialize(p, ctxt, intoValue);
   }

   protected Object _handleIncompatibleUpdateValue(JsonParser p, DeserializationContext ctxt, Object intoValue) throws IOException {
      throw new UnsupportedOperationException(String.format("Cannot update object of type %s (using deserializer for type %s)", intoValue.getClass().getName(), this._delegateType));
   }

   public Class handledType() {
      return this._delegateDeserializer.handledType();
   }

   public LogicalType logicalType() {
      return this._delegateDeserializer.logicalType();
   }

   public boolean isCachable() {
      return this._delegateDeserializer != null && this._delegateDeserializer.isCachable();
   }

   public JsonDeserializer getDelegatee() {
      return this._delegateDeserializer;
   }

   public Collection getKnownPropertyNames() {
      return this._delegateDeserializer.getKnownPropertyNames();
   }

   public Object getNullValue(DeserializationContext ctxt) throws JsonMappingException {
      return this._convertIfNonNull(this._delegateDeserializer.getNullValue(ctxt));
   }

   public AccessPattern getNullAccessPattern() {
      return this._delegateDeserializer.getNullAccessPattern();
   }

   public Object getAbsentValue(DeserializationContext ctxt) throws JsonMappingException {
      return this._convertIfNonNull(this._delegateDeserializer.getAbsentValue(ctxt));
   }

   public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
      return this._convertIfNonNull(this._delegateDeserializer.getEmptyValue(ctxt));
   }

   public AccessPattern getEmptyAccessPattern() {
      return this._delegateDeserializer.getEmptyAccessPattern();
   }

   public Boolean supportsUpdate(DeserializationConfig config) {
      return this._delegateDeserializer.supportsUpdate(config);
   }

   protected Object convertValue(Object delegateValue) {
      return this._converter.convert(delegateValue);
   }

   protected Object _convertIfNonNull(Object delegateValue) {
      return delegateValue == null ? null : this._converter.convert(delegateValue);
   }
}
