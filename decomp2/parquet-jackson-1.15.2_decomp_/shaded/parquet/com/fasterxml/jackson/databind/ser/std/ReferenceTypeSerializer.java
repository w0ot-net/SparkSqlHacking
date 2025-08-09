package shaded.parquet.com.fasterxml.jackson.databind.ser.std;

import java.io.IOException;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonInclude;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.databind.AnnotationIntrospector;
import shaded.parquet.com.fasterxml.jackson.databind.BeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.JsonSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.MapperFeature;
import shaded.parquet.com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;
import shaded.parquet.com.fasterxml.jackson.databind.annotation.JsonSerialize;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.Annotated;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.ser.ContextualSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import shaded.parquet.com.fasterxml.jackson.databind.type.ReferenceType;
import shaded.parquet.com.fasterxml.jackson.databind.util.ArrayBuilders;
import shaded.parquet.com.fasterxml.jackson.databind.util.BeanUtil;
import shaded.parquet.com.fasterxml.jackson.databind.util.NameTransformer;

public abstract class ReferenceTypeSerializer extends StdSerializer implements ContextualSerializer {
   private static final long serialVersionUID = 1L;
   public static final Object MARKER_FOR_EMPTY;
   protected final JavaType _referredType;
   protected final BeanProperty _property;
   protected final TypeSerializer _valueTypeSerializer;
   protected final JsonSerializer _valueSerializer;
   protected final NameTransformer _unwrapper;
   protected transient PropertySerializerMap _dynamicSerializers;
   protected final Object _suppressableValue;
   protected final boolean _suppressNulls;

   public ReferenceTypeSerializer(ReferenceType fullType, boolean staticTyping, TypeSerializer vts, JsonSerializer ser) {
      super((JavaType)fullType);
      this._referredType = fullType.getReferencedType();
      this._property = null;
      this._valueTypeSerializer = vts;
      this._valueSerializer = ser;
      this._unwrapper = null;
      this._suppressableValue = null;
      this._suppressNulls = false;
      this._dynamicSerializers = PropertySerializerMap.emptyForProperties();
   }

   protected ReferenceTypeSerializer(ReferenceTypeSerializer base, BeanProperty property, TypeSerializer vts, JsonSerializer valueSer, NameTransformer unwrapper, Object suppressableValue, boolean suppressNulls) {
      super((StdSerializer)base);
      this._referredType = base._referredType;
      this._dynamicSerializers = PropertySerializerMap.emptyForProperties();
      this._property = property;
      this._valueTypeSerializer = vts;
      this._valueSerializer = valueSer;
      this._unwrapper = unwrapper;
      this._suppressableValue = suppressableValue;
      this._suppressNulls = suppressNulls;
   }

   public JsonSerializer unwrappingSerializer(NameTransformer transformer) {
      JsonSerializer<Object> valueSer = this._valueSerializer;
      if (valueSer != null) {
         valueSer = valueSer.unwrappingSerializer(transformer);
         if (valueSer == this._valueSerializer) {
            return this;
         }
      }

      NameTransformer unwrapper = this._unwrapper == null ? transformer : NameTransformer.chainedTransformer(transformer, this._unwrapper);
      return this._valueSerializer == valueSer && this._unwrapper == unwrapper ? this : this.withResolved(this._property, this._valueTypeSerializer, valueSer, unwrapper);
   }

   protected abstract ReferenceTypeSerializer withResolved(BeanProperty var1, TypeSerializer var2, JsonSerializer var3, NameTransformer var4);

   public abstract ReferenceTypeSerializer withContentInclusion(Object var1, boolean var2);

   protected abstract boolean _isValuePresent(Object var1);

   protected abstract Object _getReferenced(Object var1);

   protected abstract Object _getReferencedIfPresent(Object var1);

   public JsonSerializer createContextual(SerializerProvider provider, BeanProperty property) throws JsonMappingException {
      TypeSerializer typeSer = this._valueTypeSerializer;
      if (typeSer != null) {
         typeSer = typeSer.forProperty(property);
      }

      JsonSerializer<?> ser = this.findAnnotatedContentSerializer(provider, property);
      if (ser == null) {
         ser = this._valueSerializer;
         if (ser == null) {
            if (this._useStatic(provider, property, this._referredType)) {
               ser = this._findSerializer(provider, this._referredType, property);
            }
         } else {
            ser = provider.handlePrimaryContextualization(ser, property);
         }
      }

      ser = this.findContextualConvertingSerializer(provider, property, ser);
      ReferenceTypeSerializer<?> refSer;
      if (this._property == property && this._valueTypeSerializer == typeSer && this._valueSerializer == ser) {
         refSer = this;
      } else {
         refSer = this.withResolved(property, typeSer, ser, this._unwrapper);
      }

      if (property != null) {
         JsonInclude.Value inclV = property.findPropertyInclusion(provider.getConfig(), this.handledType());
         if (inclV != null) {
            JsonInclude.Include incl = inclV.getContentInclusion();
            if (incl != JsonInclude.Include.USE_DEFAULTS) {
               Object valueToSuppress;
               boolean suppressNulls;
               switch (incl) {
                  case NON_DEFAULT:
                     valueToSuppress = BeanUtil.getDefaultValue(this._referredType);
                     suppressNulls = true;
                     if (valueToSuppress != null && valueToSuppress.getClass().isArray()) {
                        valueToSuppress = ArrayBuilders.getArrayComparator(valueToSuppress);
                     }
                     break;
                  case NON_ABSENT:
                     suppressNulls = true;
                     valueToSuppress = this._referredType.isReferenceType() ? MARKER_FOR_EMPTY : null;
                     break;
                  case NON_EMPTY:
                     suppressNulls = true;
                     valueToSuppress = MARKER_FOR_EMPTY;
                     break;
                  case CUSTOM:
                     valueToSuppress = provider.includeFilterInstance((BeanPropertyDefinition)null, inclV.getContentFilter());
                     if (valueToSuppress == null) {
                        suppressNulls = true;
                     } else {
                        suppressNulls = provider.includeFilterSuppressNulls(valueToSuppress);
                     }
                     break;
                  case NON_NULL:
                     valueToSuppress = null;
                     suppressNulls = true;
                     break;
                  case ALWAYS:
                  default:
                     valueToSuppress = null;
                     suppressNulls = false;
               }

               if (this._suppressableValue != valueToSuppress || this._suppressNulls != suppressNulls) {
                  refSer = refSer.withContentInclusion(valueToSuppress, suppressNulls);
               }
            }
         }
      }

      return refSer;
   }

   protected boolean _useStatic(SerializerProvider provider, BeanProperty property, JavaType referredType) {
      if (referredType.isJavaLangObject()) {
         return false;
      } else if (referredType.isFinal()) {
         return true;
      } else if (referredType.useStaticType()) {
         return true;
      } else {
         AnnotationIntrospector intr = provider.getAnnotationIntrospector();
         if (intr != null && property != null) {
            Annotated ann = property.getMember();
            if (ann != null) {
               JsonSerialize.Typing t = intr.findSerializationTyping(property.getMember());
               if (t == JsonSerialize.Typing.STATIC) {
                  return true;
               }

               if (t == JsonSerialize.Typing.DYNAMIC) {
                  return false;
               }
            }
         }

         return provider.isEnabled(MapperFeature.USE_STATIC_TYPING);
      }
   }

   public boolean isEmpty(SerializerProvider provider, Object value) {
      if (!this._isValuePresent(value)) {
         return true;
      } else {
         Object contents = this._getReferenced(value);
         if (contents == null) {
            return this._suppressNulls;
         } else if (this._suppressableValue == null) {
            return false;
         } else {
            JsonSerializer<Object> ser = this._valueSerializer;
            if (ser == null) {
               try {
                  ser = this._findCachedSerializer(provider, contents.getClass());
               } catch (JsonMappingException e) {
                  throw new RuntimeJsonMappingException(e);
               }
            }

            return this._suppressableValue == MARKER_FOR_EMPTY ? ser.isEmpty(provider, contents) : this._suppressableValue.equals(contents);
         }
      }
   }

   public boolean isUnwrappingSerializer() {
      return this._unwrapper != null;
   }

   public JavaType getReferredType() {
      return this._referredType;
   }

   public void serialize(Object ref, JsonGenerator g, SerializerProvider provider) throws IOException {
      Object value = this._getReferencedIfPresent(ref);
      if (value == null) {
         if (this._unwrapper == null) {
            provider.defaultSerializeNull(g);
         }

      } else {
         JsonSerializer<Object> ser = this._valueSerializer;
         if (ser == null) {
            ser = this._findCachedSerializer(provider, value.getClass());
         }

         if (this._valueTypeSerializer != null) {
            ser.serializeWithType(value, g, provider, this._valueTypeSerializer);
         } else {
            ser.serialize(value, g, provider);
         }

      }
   }

   public void serializeWithType(Object ref, JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
      Object value = this._getReferencedIfPresent(ref);
      if (value == null) {
         if (this._unwrapper == null) {
            provider.defaultSerializeNull(g);
         }

      } else {
         JsonSerializer<Object> ser = this._valueSerializer;
         if (ser == null) {
            ser = this._findCachedSerializer(provider, value.getClass());
         }

         ser.serializeWithType(value, g, provider, typeSer);
      }
   }

   public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
      JsonSerializer<?> ser = this._valueSerializer;
      if (ser == null) {
         ser = this._findSerializer(visitor.getProvider(), this._referredType, this._property);
         if (this._unwrapper != null) {
            ser = ser.unwrappingSerializer(this._unwrapper);
         }
      }

      ser.acceptJsonFormatVisitor(visitor, this._referredType);
   }

   private final JsonSerializer _findCachedSerializer(SerializerProvider provider, Class rawType) throws JsonMappingException {
      JsonSerializer<Object> ser = this._dynamicSerializers.serializerFor(rawType);
      if (ser == null) {
         if (this._referredType.hasGenericTypes()) {
            JavaType fullType = provider.constructSpecializedType(this._referredType, rawType);
            ser = provider.findPrimaryPropertySerializer(fullType, this._property);
         } else {
            ser = provider.findPrimaryPropertySerializer(rawType, this._property);
         }

         if (this._unwrapper != null) {
            ser = ser.unwrappingSerializer(this._unwrapper);
         }

         this._dynamicSerializers = this._dynamicSerializers.newWith(rawType, ser);
      }

      return ser;
   }

   private final JsonSerializer _findSerializer(SerializerProvider provider, JavaType type, BeanProperty prop) throws JsonMappingException {
      return provider.findPrimaryPropertySerializer(type, prop);
   }

   static {
      MARKER_FOR_EMPTY = JsonInclude.Include.NON_EMPTY;
   }
}
