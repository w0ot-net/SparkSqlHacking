package shaded.parquet.com.fasterxml.jackson.databind.deser.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.core.JsonToken;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationFeature;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.util.TokenBuffer;

public class ExternalTypeHandler {
   private final JavaType _beanType;
   private final ExtTypedProperty[] _properties;
   private final Map _nameToPropertyIndex;
   private final String[] _typeIds;
   private final TokenBuffer[] _tokens;

   protected ExternalTypeHandler(JavaType beanType, ExtTypedProperty[] properties, Map nameToPropertyIndex, String[] typeIds, TokenBuffer[] tokens) {
      this._beanType = beanType;
      this._properties = properties;
      this._nameToPropertyIndex = nameToPropertyIndex;
      this._typeIds = typeIds;
      this._tokens = tokens;
   }

   protected ExternalTypeHandler(ExternalTypeHandler h) {
      this._beanType = h._beanType;
      this._properties = h._properties;
      this._nameToPropertyIndex = h._nameToPropertyIndex;
      int len = this._properties.length;
      this._typeIds = new String[len];
      this._tokens = new TokenBuffer[len];
   }

   public static Builder builder(JavaType beanType) {
      return new Builder(beanType);
   }

   public ExternalTypeHandler start() {
      return new ExternalTypeHandler(this);
   }

   public boolean handleTypePropertyValue(JsonParser p, DeserializationContext ctxt, String propName, Object bean) throws IOException {
      Object ob = this._nameToPropertyIndex.get(propName);
      if (ob == null) {
         return false;
      } else {
         String typeId = p.getText();
         if (ob instanceof List) {
            boolean result = false;

            for(Integer index : (List)ob) {
               if (this._handleTypePropertyValue(p, ctxt, propName, bean, typeId, index)) {
                  result = true;
               }
            }

            return result;
         } else {
            return this._handleTypePropertyValue(p, ctxt, propName, bean, typeId, (Integer)ob);
         }
      }
   }

   private final boolean _handleTypePropertyValue(JsonParser p, DeserializationContext ctxt, String propName, Object bean, String typeId, int index) throws IOException {
      ExtTypedProperty prop = this._properties[index];
      if (!prop.hasTypePropertyName(propName)) {
         return false;
      } else {
         boolean canDeserialize = bean != null && this._tokens[index] != null;
         if (canDeserialize) {
            this._deserializeAndSet(p, ctxt, bean, index, typeId);
            this._tokens[index] = null;
         } else {
            this._typeIds[index] = typeId;
         }

         return true;
      }
   }

   public boolean handlePropertyValue(JsonParser p, DeserializationContext ctxt, String propName, Object bean) throws IOException {
      Object ob = this._nameToPropertyIndex.get(propName);
      if (ob == null) {
         return false;
      } else if (ob instanceof List) {
         Iterator<Integer> it = ((List)ob).iterator();
         Integer index = (Integer)it.next();
         ExtTypedProperty prop = this._properties[index];
         if (prop.hasTypePropertyName(propName)) {
            String typeId = p.getText();
            p.skipChildren();

            for(this._typeIds[index] = typeId; it.hasNext(); this._typeIds[(Integer)it.next()] = typeId) {
            }
         } else {
            TokenBuffer tokens = ctxt.bufferAsCopyOfValue(p);

            for(this._tokens[index] = tokens; it.hasNext(); this._tokens[(Integer)it.next()] = tokens) {
            }
         }

         return true;
      } else {
         int index = (Integer)ob;
         ExtTypedProperty prop = this._properties[index];
         boolean canDeserialize;
         if (prop.hasTypePropertyName(propName)) {
            this._typeIds[index] = p.getValueAsString();
            p.skipChildren();
            canDeserialize = bean != null && this._tokens[index] != null;
         } else {
            TokenBuffer tokens = ctxt.bufferAsCopyOfValue(p);
            this._tokens[index] = tokens;
            canDeserialize = bean != null && this._typeIds[index] != null;
         }

         if (canDeserialize) {
            String typeId = this._typeIds[index];
            this._typeIds[index] = null;
            this._deserializeAndSet(p, ctxt, bean, index, typeId);
            this._tokens[index] = null;
         }

         return true;
      }
   }

   public Object complete(JsonParser p, DeserializationContext ctxt, Object bean) throws IOException {
      int i = 0;

      for(int len = this._properties.length; i < len; ++i) {
         String typeId = this._typeIds[i];
         ExtTypedProperty extProp = this._properties[i];
         if (typeId == null) {
            TokenBuffer tokens = this._tokens[i];
            if (tokens == null) {
               continue;
            }

            JsonToken t = tokens.firstToken();
            if (t.isScalarValue()) {
               JsonParser buffered = tokens.asParser(p);
               buffered.nextToken();
               SettableBeanProperty prop = extProp.getProperty();
               Object result = TypeDeserializer.deserializeIfNatural(buffered, ctxt, prop.getType());
               if (result != null) {
                  prop.set(bean, result);
                  continue;
               }
            }

            if (!extProp.hasDefaultType()) {
               ctxt.reportPropertyInputMismatch(this._beanType, extProp.getProperty().getName(), "Missing external type id property '%s' (and no 'defaultImpl' specified)", extProp.getTypePropertyName());
            } else {
               typeId = extProp.getDefaultTypeId();
               if (typeId == null) {
                  ctxt.reportPropertyInputMismatch(this._beanType, extProp.getProperty().getName(), "Invalid default type id for property '%s': `null` returned by TypeIdResolver", extProp.getTypePropertyName());
               }
            }
         } else if (this._tokens[i] == null) {
            SettableBeanProperty prop = extProp.getProperty();
            if (prop.isRequired() || ctxt.isEnabled(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY)) {
               ctxt.reportPropertyInputMismatch(bean.getClass(), prop.getName(), "Missing property '%s' for external type id '%s'", prop.getName(), extProp.getTypePropertyName());
            }

            return bean;
         }

         this._deserializeAndSet(p, ctxt, bean, i, typeId);
      }

      return bean;
   }

   public Object complete(JsonParser p, DeserializationContext ctxt, PropertyValueBuffer buffer, PropertyBasedCreator creator) throws IOException {
      int len = this._properties.length;
      Object[] values = new Object[len];

      for(int i = 0; i < len; ++i) {
         String typeId = this._typeIds[i];
         ExtTypedProperty extProp = this._properties[i];
         if (typeId == null) {
            TokenBuffer tb = this._tokens[i];
            if (tb == null || tb.firstToken() == JsonToken.VALUE_NULL) {
               continue;
            }

            if (!extProp.hasDefaultType()) {
               ctxt.reportPropertyInputMismatch(this._beanType, extProp.getProperty().getName(), "Missing external type id property '%s'", extProp.getTypePropertyName());
            } else {
               typeId = extProp.getDefaultTypeId();
            }
         }

         if (this._tokens[i] != null) {
            values[i] = this._deserialize(p, ctxt, i, typeId);
         } else {
            if (ctxt.isEnabled(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY)) {
               SettableBeanProperty prop = extProp.getProperty();
               ctxt.reportPropertyInputMismatch(this._beanType, prop.getName(), "Missing property '%s' for external type id '%s'", prop.getName(), this._properties[i].getTypePropertyName());
            }

            values[i] = this._deserializeMissingToken(p, ctxt, i, typeId);
         }

         SettableBeanProperty prop = extProp.getProperty();
         if (prop.getCreatorIndex() >= 0) {
            buffer.assignParameter(prop, values[i]);
            SettableBeanProperty typeProp = extProp.getTypeProperty();
            if (typeProp != null && typeProp.getCreatorIndex() >= 0) {
               Object v;
               if (typeProp.getType().hasRawClass(String.class)) {
                  v = typeId;
               } else {
                  TokenBuffer tb = ctxt.bufferForInputBuffering(p);
                  tb.writeString(typeId);
                  v = typeProp.getValueDeserializer().deserialize(tb.asParserOnFirstToken(), ctxt);
                  tb.close();
               }

               buffer.assignParameter(typeProp, v);
            }
         }
      }

      Object bean = creator.build(ctxt, buffer);

      for(int i = 0; i < len; ++i) {
         SettableBeanProperty prop = this._properties[i].getProperty();
         if (prop.getCreatorIndex() < 0) {
            prop.set(bean, values[i]);
         }
      }

      return bean;
   }

   protected final Object _deserialize(JsonParser p, DeserializationContext ctxt, int index, String typeId) throws IOException {
      JsonParser p2 = this._tokens[index].asParser(p);
      JsonToken t = p2.nextToken();
      if (t == JsonToken.VALUE_NULL) {
         return null;
      } else {
         TokenBuffer merged = ctxt.bufferForInputBuffering(p);
         merged.writeStartArray();
         merged.writeString(typeId);
         merged.copyCurrentStructure(p2);
         merged.writeEndArray();
         JsonParser mp = merged.asParser(p);
         mp.nextToken();
         return this._properties[index].getProperty().deserialize(mp, ctxt);
      }
   }

   protected final Object _deserializeMissingToken(JsonParser p, DeserializationContext ctxt, int index, String typeId) throws IOException {
      TokenBuffer merged = ctxt.bufferForInputBuffering(p);
      merged.writeStartArray();
      merged.writeString(typeId);
      merged.writeEndArray();
      JsonParser mp = merged.asParser(p);
      mp.nextToken();
      return this._properties[index].getProperty().deserialize(mp, ctxt);
   }

   protected final void _deserializeAndSet(JsonParser p, DeserializationContext ctxt, Object bean, int index, String typeId) throws IOException {
      if (typeId == null) {
         ctxt.reportInputMismatch(this._beanType, "Internal error in external Type Id handling: `null` type id passed");
      }

      JsonParser p2 = this._tokens[index].asParser(p);
      JsonToken t = p2.nextToken();
      if (t == JsonToken.VALUE_NULL) {
         this._properties[index].getProperty().set(bean, (Object)null);
      } else {
         TokenBuffer merged = ctxt.bufferForInputBuffering(p);
         merged.writeStartArray();
         merged.writeString(typeId);
         merged.copyCurrentStructure(p2);
         merged.writeEndArray();
         JsonParser mp = merged.asParser(p);
         mp.nextToken();
         this._properties[index].getProperty().deserializeAndSet(mp, ctxt, bean);
      }
   }

   public static class Builder {
      private final JavaType _beanType;
      private final List _properties = new ArrayList();
      private final Map _nameToPropertyIndex = new HashMap();

      protected Builder(JavaType t) {
         this._beanType = t;
      }

      public void addExternal(SettableBeanProperty property, TypeDeserializer typeDeser) {
         Integer index = this._properties.size();
         this._properties.add(new ExtTypedProperty(property, typeDeser));
         this._addPropertyIndex(property.getName(), index);
         this._addPropertyIndex(typeDeser.getPropertyName(), index);
      }

      private void _addPropertyIndex(String name, Integer index) {
         Object ob = this._nameToPropertyIndex.get(name);
         if (ob == null) {
            this._nameToPropertyIndex.put(name, index);
         } else if (ob instanceof List) {
            List<Object> list = (List)ob;
            list.add(index);
         } else {
            List<Object> list = new LinkedList();
            list.add(ob);
            list.add(index);
            this._nameToPropertyIndex.put(name, list);
         }

      }

      public ExternalTypeHandler build(BeanPropertyMap otherProps) {
         int len = this._properties.size();
         ExtTypedProperty[] extProps = new ExtTypedProperty[len];

         for(int i = 0; i < len; ++i) {
            ExtTypedProperty extProp = (ExtTypedProperty)this._properties.get(i);
            String typePropId = extProp.getTypePropertyName();
            SettableBeanProperty typeProp = otherProps.find(typePropId);
            if (typeProp != null) {
               extProp.linkTypeProperty(typeProp);
            }

            extProps[i] = extProp;
         }

         return new ExternalTypeHandler(this._beanType, extProps, this._nameToPropertyIndex, (String[])null, (TokenBuffer[])null);
      }
   }

   private static final class ExtTypedProperty {
      private final SettableBeanProperty _property;
      private final TypeDeserializer _typeDeserializer;
      private final String _typePropertyName;
      private SettableBeanProperty _typeProperty;

      public ExtTypedProperty(SettableBeanProperty property, TypeDeserializer typeDeser) {
         this._property = property;
         this._typeDeserializer = typeDeser;
         this._typePropertyName = typeDeser.getPropertyName();
      }

      public void linkTypeProperty(SettableBeanProperty p) {
         this._typeProperty = p;
      }

      public boolean hasTypePropertyName(String n) {
         return n.equals(this._typePropertyName);
      }

      public boolean hasDefaultType() {
         return this._typeDeserializer.hasDefaultImpl();
      }

      public String getDefaultTypeId() {
         Class<?> defaultType = this._typeDeserializer.getDefaultImpl();
         return defaultType == null ? null : this._typeDeserializer.getTypeIdResolver().idFromValueAndType((Object)null, defaultType);
      }

      public String getTypePropertyName() {
         return this._typePropertyName;
      }

      public SettableBeanProperty getProperty() {
         return this._property;
      }

      public SettableBeanProperty getTypeProperty() {
         return this._typeProperty;
      }
   }
}
