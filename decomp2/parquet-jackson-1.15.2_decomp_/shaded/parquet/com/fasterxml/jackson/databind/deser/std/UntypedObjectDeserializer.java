package shaded.parquet.com.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.core.JsonToken;
import shaded.parquet.com.fasterxml.jackson.core.StreamReadCapability;
import shaded.parquet.com.fasterxml.jackson.databind.BeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationFeature;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import shaded.parquet.com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.type.LogicalType;
import shaded.parquet.com.fasterxml.jackson.databind.type.TypeFactory;
import shaded.parquet.com.fasterxml.jackson.databind.util.ClassUtil;
import shaded.parquet.com.fasterxml.jackson.databind.util.ObjectBuffer;

@JacksonStdImpl
public class UntypedObjectDeserializer extends StdDeserializer implements ResolvableDeserializer, ContextualDeserializer {
   private static final long serialVersionUID = 1L;
   protected static final Object[] NO_OBJECTS = new Object[0];
   protected JsonDeserializer _mapDeserializer;
   protected JsonDeserializer _listDeserializer;
   protected JsonDeserializer _stringDeserializer;
   protected JsonDeserializer _numberDeserializer;
   protected JavaType _listType;
   protected JavaType _mapType;
   protected final boolean _nonMerging;

   /** @deprecated */
   @Deprecated
   public UntypedObjectDeserializer() {
      this((JavaType)null, (JavaType)null);
   }

   public UntypedObjectDeserializer(JavaType listType, JavaType mapType) {
      super(Object.class);
      this._listType = listType;
      this._mapType = mapType;
      this._nonMerging = false;
   }

   public UntypedObjectDeserializer(UntypedObjectDeserializer base, JsonDeserializer mapDeser, JsonDeserializer listDeser, JsonDeserializer stringDeser, JsonDeserializer numberDeser) {
      super(Object.class);
      this._mapDeserializer = mapDeser;
      this._listDeserializer = listDeser;
      this._stringDeserializer = stringDeser;
      this._numberDeserializer = numberDeser;
      this._listType = base._listType;
      this._mapType = base._mapType;
      this._nonMerging = base._nonMerging;
   }

   protected UntypedObjectDeserializer(UntypedObjectDeserializer base, boolean nonMerging) {
      super(Object.class);
      this._mapDeserializer = base._mapDeserializer;
      this._listDeserializer = base._listDeserializer;
      this._stringDeserializer = base._stringDeserializer;
      this._numberDeserializer = base._numberDeserializer;
      this._listType = base._listType;
      this._mapType = base._mapType;
      this._nonMerging = nonMerging;
   }

   public void resolve(DeserializationContext ctxt) throws JsonMappingException {
      JavaType obType = ctxt.constructType(Object.class);
      JavaType stringType = ctxt.constructType(String.class);
      TypeFactory tf = ctxt.getTypeFactory();
      if (this._listType == null) {
         this._listDeserializer = this._clearIfStdImpl(this._findCustomDeser(ctxt, tf.constructCollectionType(List.class, obType)));
      } else {
         this._listDeserializer = this._findCustomDeser(ctxt, this._listType);
      }

      if (this._mapType == null) {
         this._mapDeserializer = this._clearIfStdImpl(this._findCustomDeser(ctxt, tf.constructMapType(Map.class, stringType, obType)));
      } else {
         this._mapDeserializer = this._findCustomDeser(ctxt, this._mapType);
      }

      this._stringDeserializer = this._clearIfStdImpl(this._findCustomDeser(ctxt, stringType));
      this._numberDeserializer = this._clearIfStdImpl(this._findCustomDeser(ctxt, tf.constructType((Type)Number.class)));
      JavaType unknown = TypeFactory.unknownType();
      this._mapDeserializer = ctxt.handleSecondaryContextualization(this._mapDeserializer, (BeanProperty)null, unknown);
      this._listDeserializer = ctxt.handleSecondaryContextualization(this._listDeserializer, (BeanProperty)null, unknown);
      this._stringDeserializer = ctxt.handleSecondaryContextualization(this._stringDeserializer, (BeanProperty)null, unknown);
      this._numberDeserializer = ctxt.handleSecondaryContextualization(this._numberDeserializer, (BeanProperty)null, unknown);
   }

   protected JsonDeserializer _findCustomDeser(DeserializationContext ctxt, JavaType type) throws JsonMappingException {
      return ctxt.findNonContextualValueDeserializer(type);
   }

   protected JsonDeserializer _clearIfStdImpl(JsonDeserializer deser) {
      return ClassUtil.isJacksonStdImpl((Object)deser) ? null : deser;
   }

   public JsonDeserializer createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
      boolean preventMerge = property == null && Boolean.FALSE.equals(ctxt.getConfig().getDefaultMergeable(Object.class));
      if (this._stringDeserializer == null && this._numberDeserializer == null && this._mapDeserializer == null && this._listDeserializer == null && this.getClass() == UntypedObjectDeserializer.class) {
         return UntypedObjectDeserializerNR.instance(preventMerge);
      } else {
         return preventMerge != this._nonMerging ? new UntypedObjectDeserializer(this, preventMerge) : this;
      }
   }

   public boolean isCachable() {
      return true;
   }

   public LogicalType logicalType() {
      return LogicalType.Untyped;
   }

   public Boolean supportsUpdate(DeserializationConfig config) {
      return null;
   }

   public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      switch (p.currentTokenId()) {
         case 1:
         case 2:
         case 5:
            if (this._mapDeserializer != null) {
               return this._mapDeserializer.deserialize(p, ctxt);
            }

            return this.mapObject(p, ctxt);
         case 3:
            if (ctxt.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
               return this.mapArrayToArray(p, ctxt);
            } else {
               if (this._listDeserializer != null) {
                  return this._listDeserializer.deserialize(p, ctxt);
               }

               return this.mapArray(p, ctxt);
            }
         case 4:
         default:
            return ctxt.handleUnexpectedToken(Object.class, p);
         case 6:
            if (this._stringDeserializer != null) {
               return this._stringDeserializer.deserialize(p, ctxt);
            }

            return p.getText();
         case 7:
            if (this._numberDeserializer != null) {
               return this._numberDeserializer.deserialize(p, ctxt);
            } else {
               if (ctxt.hasSomeOfFeatures(F_MASK_INT_COERCIONS)) {
                  return this._coerceIntegral(p, ctxt);
               }

               return p.getNumberValue();
            }
         case 8:
            if (this._numberDeserializer != null) {
               return this._numberDeserializer.deserialize(p, ctxt);
            }

            return this._deserializeFP(p, ctxt);
         case 9:
            return Boolean.TRUE;
         case 10:
            return Boolean.FALSE;
         case 11:
            return null;
         case 12:
            return p.getEmbeddedObject();
      }
   }

   public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
      switch (p.currentTokenId()) {
         case 1:
         case 3:
         case 5:
            return typeDeserializer.deserializeTypedFromAny(p, ctxt);
         case 2:
         case 4:
         default:
            return ctxt.handleUnexpectedToken(Object.class, p);
         case 6:
            if (this._stringDeserializer != null) {
               return this._stringDeserializer.deserialize(p, ctxt);
            }

            return p.getText();
         case 7:
            if (this._numberDeserializer != null) {
               return this._numberDeserializer.deserialize(p, ctxt);
            } else {
               if (ctxt.hasSomeOfFeatures(F_MASK_INT_COERCIONS)) {
                  return this._coerceIntegral(p, ctxt);
               }

               return p.getNumberValue();
            }
         case 8:
            if (this._numberDeserializer != null) {
               return this._numberDeserializer.deserialize(p, ctxt);
            }

            return this._deserializeFP(p, ctxt);
         case 9:
            return Boolean.TRUE;
         case 10:
            return Boolean.FALSE;
         case 11:
            return null;
         case 12:
            return p.getEmbeddedObject();
      }
   }

   public Object deserialize(JsonParser p, DeserializationContext ctxt, Object intoValue) throws IOException {
      if (this._nonMerging) {
         return this.deserialize(p, ctxt);
      } else {
         switch (p.currentTokenId()) {
            case 1:
            case 2:
            case 5:
               if (this._mapDeserializer != null) {
                  return this._mapDeserializer.deserialize(p, ctxt, intoValue);
               } else {
                  if (intoValue instanceof Map) {
                     return this.mapObject(p, ctxt, (Map)intoValue);
                  }

                  return this.mapObject(p, ctxt);
               }
            case 3:
               if (this._listDeserializer != null) {
                  return this._listDeserializer.deserialize(p, ctxt, intoValue);
               } else if (intoValue instanceof Collection) {
                  return this.mapArray(p, ctxt, (Collection)intoValue);
               } else {
                  if (ctxt.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                     return this.mapArrayToArray(p, ctxt);
                  }

                  return this.mapArray(p, ctxt);
               }
            case 4:
            default:
               return this.deserialize(p, ctxt);
            case 6:
               if (this._stringDeserializer != null) {
                  return this._stringDeserializer.deserialize(p, ctxt, intoValue);
               }

               return p.getText();
            case 7:
               if (this._numberDeserializer != null) {
                  return this._numberDeserializer.deserialize(p, ctxt, intoValue);
               } else {
                  if (ctxt.hasSomeOfFeatures(F_MASK_INT_COERCIONS)) {
                     return this._coerceIntegral(p, ctxt);
                  }

                  return p.getNumberValue();
               }
            case 8:
               if (this._numberDeserializer != null) {
                  return this._numberDeserializer.deserialize(p, ctxt, intoValue);
               }

               return this._deserializeFP(p, ctxt);
            case 9:
               return Boolean.TRUE;
            case 10:
               return Boolean.FALSE;
            case 11:
               return null;
            case 12:
               return p.getEmbeddedObject();
         }
      }
   }

   protected Object mapArray(JsonParser p, DeserializationContext ctxt) throws IOException {
      if (p.nextToken() == JsonToken.END_ARRAY) {
         return new ArrayList(2);
      } else {
         Object value = this.deserialize(p, ctxt);
         if (p.nextToken() == JsonToken.END_ARRAY) {
            ArrayList<Object> l = new ArrayList(2);
            l.add(value);
            return l;
         } else {
            Object value2 = this.deserialize(p, ctxt);
            if (p.nextToken() == JsonToken.END_ARRAY) {
               ArrayList<Object> l = new ArrayList(2);
               l.add(value);
               l.add(value2);
               return l;
            } else {
               ObjectBuffer buffer = ctxt.leaseObjectBuffer();
               Object[] values = buffer.resetAndStart();
               int ptr = 0;
               values[ptr++] = value;
               values[ptr++] = value2;
               int totalSize = ptr;

               do {
                  value = this.deserialize(p, ctxt);
                  ++totalSize;
                  if (ptr >= values.length) {
                     values = buffer.appendCompletedChunk(values);
                     ptr = 0;
                  }

                  values[ptr++] = value;
               } while(p.nextToken() != JsonToken.END_ARRAY);

               ArrayList<Object> result = new ArrayList(totalSize);
               buffer.completeAndClearBuffer(values, ptr, (List)result);
               ctxt.returnObjectBuffer(buffer);
               return result;
            }
         }
      }
   }

   protected Object mapArray(JsonParser p, DeserializationContext ctxt, Collection result) throws IOException {
      while(p.nextToken() != JsonToken.END_ARRAY) {
         result.add(this.deserialize(p, ctxt));
      }

      return result;
   }

   protected Object mapObject(JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonToken t = p.currentToken();
      String key1;
      if (t == JsonToken.START_OBJECT) {
         key1 = p.nextFieldName();
      } else if (t == JsonToken.FIELD_NAME) {
         key1 = p.currentName();
      } else {
         if (t != JsonToken.END_OBJECT) {
            return ctxt.handleUnexpectedToken(this.handledType(), p);
         }

         key1 = null;
      }

      if (key1 == null) {
         return new LinkedHashMap(2);
      } else {
         p.nextToken();
         Object value1 = this.deserialize(p, ctxt);
         String key2 = p.nextFieldName();
         if (key2 == null) {
            LinkedHashMap<String, Object> result = new LinkedHashMap(2);
            result.put(key1, value1);
            return result;
         } else {
            p.nextToken();
            Object value2 = this.deserialize(p, ctxt);
            String key = p.nextFieldName();
            if (key == null) {
               LinkedHashMap<String, Object> result = new LinkedHashMap(4);
               result.put(key1, value1);
               return result.put(key2, value2) != null ? this._mapObjectWithDups(p, ctxt, result, key1, value1, value2, key) : result;
            } else {
               LinkedHashMap<String, Object> result = new LinkedHashMap();
               result.put(key1, value1);
               if (result.put(key2, value2) != null) {
                  return this._mapObjectWithDups(p, ctxt, result, key1, value1, value2, key);
               } else {
                  do {
                     p.nextToken();
                     Object newValue = this.deserialize(p, ctxt);
                     Object oldValue = result.put(key, newValue);
                     if (oldValue != null) {
                        return this._mapObjectWithDups(p, ctxt, result, key, oldValue, newValue, p.nextFieldName());
                     }
                  } while((key = p.nextFieldName()) != null);

                  return result;
               }
            }
         }
      }
   }

   protected Object _mapObjectWithDups(JsonParser p, DeserializationContext ctxt, Map result, String key, Object oldValue, Object newValue, String nextKey) throws IOException {
      boolean squashDups = ctxt.isEnabled(StreamReadCapability.DUPLICATE_PROPERTIES);
      if (squashDups) {
         this._squashDups(result, key, oldValue, newValue);
      }

      for(; nextKey != null; nextKey = p.nextFieldName()) {
         p.nextToken();
         newValue = this.deserialize(p, ctxt);
         oldValue = result.put(nextKey, newValue);
         if (oldValue != null && squashDups) {
            this._squashDups(result, key, oldValue, newValue);
         }
      }

      return result;
   }

   private void _squashDups(Map result, String key, Object oldValue, Object newValue) {
      if (oldValue instanceof List) {
         ((List)oldValue).add(newValue);
         result.put(key, oldValue);
      } else {
         ArrayList<Object> l = new ArrayList();
         l.add(oldValue);
         l.add(newValue);
         result.put(key, l);
      }

   }

   protected Object[] mapArrayToArray(JsonParser p, DeserializationContext ctxt) throws IOException {
      if (p.nextToken() == JsonToken.END_ARRAY) {
         return NO_OBJECTS;
      } else {
         ObjectBuffer buffer = ctxt.leaseObjectBuffer();
         Object[] values = buffer.resetAndStart();
         int ptr = 0;

         do {
            Object value = this.deserialize(p, ctxt);
            if (ptr >= values.length) {
               values = buffer.appendCompletedChunk(values);
               ptr = 0;
            }

            values[ptr++] = value;
         } while(p.nextToken() != JsonToken.END_ARRAY);

         Object[] result = buffer.completeAndClearBuffer(values, ptr);
         ctxt.returnObjectBuffer(buffer);
         return result;
      }
   }

   protected Object mapObject(JsonParser p, DeserializationContext ctxt, Map m) throws IOException {
      JsonToken t = p.currentToken();
      if (t == JsonToken.START_OBJECT) {
         t = p.nextToken();
      }

      if (t == JsonToken.END_OBJECT) {
         return m;
      } else {
         String key = p.currentName();

         do {
            p.nextToken();
            Object old = m.get(key);
            Object newV;
            if (old != null) {
               newV = this.deserialize(p, ctxt, old);
            } else {
               newV = this.deserialize(p, ctxt);
            }

            if (newV != old) {
               m.put(key, newV);
            }
         } while((key = p.nextFieldName()) != null);

         return m;
      }
   }

   protected Object _deserializeFP(JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonParser.NumberTypeFP nt = p.getNumberTypeFP();
      if (nt == JsonParser.NumberTypeFP.BIG_DECIMAL) {
         return p.getDecimalValue();
      } else if (!p.isNaN() && ctxt.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
         return p.getDecimalValue();
      } else {
         return nt == JsonParser.NumberTypeFP.FLOAT32 ? p.getFloatValue() : p.getDoubleValue();
      }
   }

   /** @deprecated */
   @JacksonStdImpl
   @Deprecated
   public static class Vanilla extends StdDeserializer {
      private static final long serialVersionUID = 1L;
      public static final Vanilla std = new Vanilla();
      protected final boolean _nonMerging;

      public Vanilla() {
         this(false);
      }

      protected Vanilla(boolean nonMerging) {
         super(Object.class);
         this._nonMerging = nonMerging;
      }

      public static Vanilla instance(boolean nonMerging) {
         return nonMerging ? new Vanilla(true) : std;
      }

      public LogicalType logicalType() {
         return LogicalType.Untyped;
      }

      public Boolean supportsUpdate(DeserializationConfig config) {
         return this._nonMerging ? Boolean.FALSE : null;
      }

      public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
         switch (p.currentTokenId()) {
            case 1:
               JsonToken t = p.nextToken();
               if (t == JsonToken.END_OBJECT) {
                  return new LinkedHashMap(2);
               }
            case 5:
               return this.mapObject(p, ctxt);
            case 2:
               return new LinkedHashMap(2);
            case 3:
               JsonToken t = p.nextToken();
               if (t == JsonToken.END_ARRAY) {
                  if (ctxt.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                     return UntypedObjectDeserializer.NO_OBJECTS;
                  }

                  return new ArrayList(2);
               } else {
                  if (ctxt.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                     return this.mapArrayToArray(p, ctxt);
                  }

                  return this.mapArray(p, ctxt);
               }
            case 4:
            default:
               return ctxt.handleUnexpectedToken(Object.class, p);
            case 6:
               return p.getText();
            case 7:
               if (ctxt.hasSomeOfFeatures(F_MASK_INT_COERCIONS)) {
                  return this._coerceIntegral(p, ctxt);
               }

               return p.getNumberValue();
            case 8:
               return this._deserializeFP(p, ctxt);
            case 9:
               return Boolean.TRUE;
            case 10:
               return Boolean.FALSE;
            case 11:
               return null;
            case 12:
               return p.getEmbeddedObject();
         }
      }

      public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
         switch (p.currentTokenId()) {
            case 1:
            case 3:
            case 5:
               return typeDeserializer.deserializeTypedFromAny(p, ctxt);
            case 2:
            case 4:
            default:
               return ctxt.handleUnexpectedToken(Object.class, p);
            case 6:
               return p.getText();
            case 7:
               if (ctxt.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
                  return p.getBigIntegerValue();
               }

               return p.getNumberValue();
            case 8:
               return this._deserializeFP(p, ctxt);
            case 9:
               return Boolean.TRUE;
            case 10:
               return Boolean.FALSE;
            case 11:
               return null;
            case 12:
               return p.getEmbeddedObject();
         }
      }

      public Object deserialize(JsonParser p, DeserializationContext ctxt, Object intoValue) throws IOException {
         if (this._nonMerging) {
            return this.deserialize(p, ctxt);
         } else {
            switch (p.currentTokenId()) {
               case 1:
                  JsonToken t = p.nextToken();
                  if (t == JsonToken.END_OBJECT) {
                     return intoValue;
                  }
               case 5:
                  if (intoValue instanceof Map) {
                     Map<Object, Object> m = (Map)intoValue;
                     String key = p.currentName();

                     do {
                        p.nextToken();
                        Object old = m.get(key);
                        Object newV;
                        if (old != null) {
                           newV = this.deserialize(p, ctxt, old);
                        } else {
                           newV = this.deserialize(p, ctxt);
                        }

                        if (newV != old) {
                           m.put(key, newV);
                        }
                     } while((key = p.nextFieldName()) != null);

                     return intoValue;
                  }
                  break;
               case 2:
               case 4:
                  return intoValue;
               case 3:
                  JsonToken t = p.nextToken();
                  if (t == JsonToken.END_ARRAY) {
                     return intoValue;
                  }

                  if (intoValue instanceof Collection) {
                     Collection<Object> c = (Collection)intoValue;

                     do {
                        c.add(this.deserialize(p, ctxt));
                     } while(p.nextToken() != JsonToken.END_ARRAY);

                     return intoValue;
                  }
            }

            return this.deserialize(p, ctxt);
         }
      }

      protected Object _deserializeFP(JsonParser p, DeserializationContext ctxt) throws IOException {
         JsonParser.NumberTypeFP nt = p.getNumberTypeFP();
         if (nt == JsonParser.NumberTypeFP.BIG_DECIMAL) {
            return p.getDecimalValue();
         } else if (!p.isNaN() && ctxt.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
            return p.getDecimalValue();
         } else {
            return nt == JsonParser.NumberTypeFP.FLOAT32 ? p.getFloatValue() : p.getDoubleValue();
         }
      }

      protected Object mapArray(JsonParser p, DeserializationContext ctxt) throws IOException {
         Object value = this.deserialize(p, ctxt);
         if (p.nextToken() == JsonToken.END_ARRAY) {
            ArrayList<Object> l = new ArrayList(2);
            l.add(value);
            return l;
         } else {
            ObjectBuffer buffer = ctxt.leaseObjectBuffer();
            Object[] values = buffer.resetAndStart();
            int ptr = 0;
            values[ptr++] = value;
            int totalSize = ptr;

            do {
               value = this.deserialize(p, ctxt);
               ++totalSize;
               if (ptr >= values.length) {
                  values = buffer.appendCompletedChunk(values);
                  ptr = 0;
               }

               values[ptr++] = value;
            } while(p.nextToken() != JsonToken.END_ARRAY);

            ArrayList<Object> result = new ArrayList(totalSize);
            buffer.completeAndClearBuffer(values, ptr, (List)result);
            ctxt.returnObjectBuffer(buffer);
            return result;
         }
      }

      protected Object[] mapArrayToArray(JsonParser p, DeserializationContext ctxt) throws IOException {
         ObjectBuffer buffer = ctxt.leaseObjectBuffer();
         Object[] values = buffer.resetAndStart();
         int ptr = 0;

         do {
            Object value = this.deserialize(p, ctxt);
            if (ptr >= values.length) {
               values = buffer.appendCompletedChunk(values);
               ptr = 0;
            }

            values[ptr++] = value;
         } while(p.nextToken() != JsonToken.END_ARRAY);

         Object[] result = buffer.completeAndClearBuffer(values, ptr);
         ctxt.returnObjectBuffer(buffer);
         return result;
      }

      protected Object mapObject(JsonParser p, DeserializationContext ctxt) throws IOException {
         String key1 = p.currentName();
         p.nextToken();
         Object value1 = this.deserialize(p, ctxt);
         String key = p.nextFieldName();
         if (key == null) {
            LinkedHashMap<String, Object> result = new LinkedHashMap(2);
            result.put(key1, value1);
            return result;
         } else {
            LinkedHashMap<String, Object> result = new LinkedHashMap();
            result.put(key1, value1);

            do {
               p.nextToken();
               Object newValue = this.deserialize(p, ctxt);
               Object oldValue = result.put(key, newValue);
               if (oldValue != null) {
                  return this._mapObjectWithDups(p, ctxt, result, key, oldValue, newValue, p.nextFieldName());
               }
            } while((key = p.nextFieldName()) != null);

            return result;
         }
      }

      protected Object _mapObjectWithDups(JsonParser p, DeserializationContext ctxt, Map result, String initialKey, Object oldValue, Object newValue, String nextKey) throws IOException {
         boolean squashDups = ctxt.isEnabled(StreamReadCapability.DUPLICATE_PROPERTIES);
         if (squashDups) {
            this._squashDups(result, initialKey, oldValue, newValue);
         }

         for(; nextKey != null; nextKey = p.nextFieldName()) {
            p.nextToken();
            newValue = this.deserialize(p, ctxt);
            oldValue = result.put(nextKey, newValue);
            if (oldValue != null && squashDups) {
               this._squashDups(result, nextKey, oldValue, newValue);
            }
         }

         return result;
      }

      private void _squashDups(Map result, String key, Object oldValue, Object newValue) {
         if (oldValue instanceof List) {
            ((List)oldValue).add(newValue);
            result.put(key, oldValue);
         } else {
            ArrayList<Object> l = new ArrayList();
            l.add(oldValue);
            l.add(newValue);
            result.put(key, l);
         }

      }
   }
}
