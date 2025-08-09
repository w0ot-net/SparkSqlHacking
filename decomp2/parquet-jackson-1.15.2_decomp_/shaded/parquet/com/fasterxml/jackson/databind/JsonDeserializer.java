package shaded.parquet.com.fasterxml.jackson.databind;

import java.io.IOException;
import java.util.Collection;
import shaded.parquet.com.fasterxml.jackson.core.JacksonException;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.databind.deser.NullValueProvider;
import shaded.parquet.com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.type.LogicalType;
import shaded.parquet.com.fasterxml.jackson.databind.util.AccessPattern;
import shaded.parquet.com.fasterxml.jackson.databind.util.NameTransformer;

public abstract class JsonDeserializer implements NullValueProvider {
   public abstract Object deserialize(JsonParser var1, DeserializationContext var2) throws IOException, JacksonException;

   public Object deserialize(JsonParser p, DeserializationContext ctxt, Object intoValue) throws IOException, JacksonException {
      ctxt.handleBadMerge(this);
      return this.deserialize(p, ctxt);
   }

   public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException, JacksonException {
      return typeDeserializer.deserializeTypedFromAny(p, ctxt);
   }

   public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer, Object intoValue) throws IOException, JacksonException {
      ctxt.handleBadMerge(this);
      return this.deserializeWithType(p, ctxt, typeDeserializer);
   }

   public JsonDeserializer unwrappingDeserializer(NameTransformer unwrapper) {
      return this;
   }

   public JsonDeserializer replaceDelegatee(JsonDeserializer delegatee) {
      throw new UnsupportedOperationException();
   }

   public Class handledType() {
      return null;
   }

   public LogicalType logicalType() {
      return null;
   }

   public boolean isCachable() {
      return false;
   }

   public JsonDeserializer getDelegatee() {
      return null;
   }

   public Collection getKnownPropertyNames() {
      return null;
   }

   public Object getNullValue(DeserializationContext ctxt) throws JsonMappingException {
      return this.getNullValue();
   }

   public AccessPattern getNullAccessPattern() {
      return AccessPattern.CONSTANT;
   }

   public Object getAbsentValue(DeserializationContext ctxt) throws JsonMappingException {
      return this.getNullValue(ctxt);
   }

   public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
      return this.getNullValue(ctxt);
   }

   public AccessPattern getEmptyAccessPattern() {
      return AccessPattern.DYNAMIC;
   }

   public ObjectIdReader getObjectIdReader() {
      return null;
   }

   public SettableBeanProperty findBackReference(String refName) {
      throw new IllegalArgumentException("Cannot handle managed/back reference '" + refName + "': type: value deserializer of type " + this.getClass().getName() + " does not support them");
   }

   public Boolean supportsUpdate(DeserializationConfig config) {
      return null;
   }

   /** @deprecated */
   @Deprecated
   public Object getNullValue() {
      return null;
   }

   /** @deprecated */
   @Deprecated
   public Object getEmptyValue() {
      return this.getNullValue();
   }

   public abstract static class None extends JsonDeserializer {
      private None() {
      }
   }
}
