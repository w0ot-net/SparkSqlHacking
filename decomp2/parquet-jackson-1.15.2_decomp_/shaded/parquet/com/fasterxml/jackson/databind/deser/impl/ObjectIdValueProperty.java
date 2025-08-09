package shaded.parquet.com.fasterxml.jackson.databind.deser.impl;

import java.io.IOException;
import java.lang.annotation.Annotation;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.core.JsonToken;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.JsonDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.PropertyMetadata;
import shaded.parquet.com.fasterxml.jackson.databind.PropertyName;
import shaded.parquet.com.fasterxml.jackson.databind.deser.NullValueProvider;
import shaded.parquet.com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.AnnotatedMember;

public final class ObjectIdValueProperty extends SettableBeanProperty {
   private static final long serialVersionUID = 1L;
   protected final ObjectIdReader _objectIdReader;

   public ObjectIdValueProperty(ObjectIdReader objectIdReader, PropertyMetadata metadata) {
      super(objectIdReader.propertyName, objectIdReader.getIdType(), metadata, objectIdReader.getDeserializer());
      this._objectIdReader = objectIdReader;
   }

   protected ObjectIdValueProperty(ObjectIdValueProperty src, JsonDeserializer deser, NullValueProvider nva) {
      super(src, deser, nva);
      this._objectIdReader = src._objectIdReader;
   }

   protected ObjectIdValueProperty(ObjectIdValueProperty src, PropertyName newName) {
      super(src, newName);
      this._objectIdReader = src._objectIdReader;
   }

   public SettableBeanProperty withName(PropertyName newName) {
      return new ObjectIdValueProperty(this, newName);
   }

   public SettableBeanProperty withValueDeserializer(JsonDeserializer deser) {
      if (this._valueDeserializer == deser) {
         return this;
      } else {
         NullValueProvider nvp = (NullValueProvider)(this._valueDeserializer == this._nullProvider ? deser : this._nullProvider);
         return new ObjectIdValueProperty(this, deser, nvp);
      }
   }

   public SettableBeanProperty withNullProvider(NullValueProvider nva) {
      return new ObjectIdValueProperty(this, this._valueDeserializer, nva);
   }

   public Annotation getAnnotation(Class acls) {
      return null;
   }

   public AnnotatedMember getMember() {
      return null;
   }

   public void deserializeAndSet(JsonParser p, DeserializationContext ctxt, Object instance) throws IOException {
      this.deserializeSetAndReturn(p, ctxt, instance);
   }

   public Object deserializeSetAndReturn(JsonParser p, DeserializationContext ctxt, Object instance) throws IOException {
      if (p.hasToken(JsonToken.VALUE_NULL)) {
         return null;
      } else {
         Object id = this._valueDeserializer.deserialize(p, ctxt);
         ReadableObjectId roid = ctxt.findObjectId(id, this._objectIdReader.generator, this._objectIdReader.resolver);
         roid.bindItem(instance);
         SettableBeanProperty idProp = this._objectIdReader.idProperty;
         return idProp != null ? idProp.setAndReturn(instance, id) : instance;
      }
   }

   public void set(Object instance, Object value) throws IOException {
      this.setAndReturn(instance, value);
   }

   public Object setAndReturn(Object instance, Object value) throws IOException {
      SettableBeanProperty idProp = this._objectIdReader.idProperty;
      if (idProp == null) {
         throw new UnsupportedOperationException("Should not call set() on ObjectIdProperty that has no SettableBeanProperty");
      } else {
         return idProp.setAndReturn(instance, value);
      }
   }
}
