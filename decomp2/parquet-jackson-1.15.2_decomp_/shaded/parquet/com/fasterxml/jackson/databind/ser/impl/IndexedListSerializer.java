package shaded.parquet.com.fasterxml.jackson.databind.ser.impl;

import java.io.IOException;
import java.util.List;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.databind.BeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.SerializationFeature;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;
import shaded.parquet.com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.ser.ContainerSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;

@JacksonStdImpl
public final class IndexedListSerializer extends AsArraySerializerBase {
   private static final long serialVersionUID = 1L;

   public IndexedListSerializer(JavaType elemType, boolean staticTyping, TypeSerializer vts, JsonSerializer valueSerializer) {
      super(List.class, elemType, staticTyping, vts, valueSerializer);
   }

   public IndexedListSerializer(IndexedListSerializer src, BeanProperty property, TypeSerializer vts, JsonSerializer valueSerializer, Boolean unwrapSingle) {
      super(src, property, vts, valueSerializer, unwrapSingle);
   }

   public IndexedListSerializer withResolved(BeanProperty property, TypeSerializer vts, JsonSerializer elementSerializer, Boolean unwrapSingle) {
      return new IndexedListSerializer(this, property, vts, elementSerializer, unwrapSingle);
   }

   public boolean isEmpty(SerializerProvider prov, List value) {
      return value.isEmpty();
   }

   public boolean hasSingleElement(List value) {
      return value.size() == 1;
   }

   public ContainerSerializer _withValueTypeSerializer(TypeSerializer vts) {
      return new IndexedListSerializer(this, this._property, vts, this._elementSerializer, this._unwrapSingle);
   }

   public final void serialize(List value, JsonGenerator gen, SerializerProvider provider) throws IOException {
      int len = value.size();
      if (len != 1 || (this._unwrapSingle != null || !provider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) && this._unwrapSingle != Boolean.TRUE) {
         gen.writeStartArray(value, len);
         this.serializeContents(value, gen, provider);
         gen.writeEndArray();
      } else {
         this.serializeContents(value, gen, provider);
      }
   }

   public void serializeContents(List value, JsonGenerator g, SerializerProvider provider) throws IOException {
      if (this._elementSerializer != null) {
         this.serializeContentsUsing(value, g, provider, this._elementSerializer);
      } else if (this._valueTypeSerializer != null) {
         this.serializeTypedContents(value, g, provider);
      } else {
         int len = value.size();
         if (len != 0) {
            int i = 0;

            try {
               for(PropertySerializerMap serializers = this._dynamicSerializers; i < len; ++i) {
                  Object elem = value.get(i);
                  if (elem == null) {
                     provider.defaultSerializeNull(g);
                  } else {
                     Class<?> cc = elem.getClass();
                     JsonSerializer<Object> serializer = serializers.serializerFor(cc);
                     if (serializer == null) {
                        if (this._elementType.hasGenericTypes()) {
                           serializer = this._findAndAddDynamic(serializers, provider.constructSpecializedType(this._elementType, cc), provider);
                        } else {
                           serializer = this._findAndAddDynamic(serializers, cc, provider);
                        }

                        serializers = this._dynamicSerializers;
                     }

                     serializer.serialize(elem, g, provider);
                  }
               }
            } catch (Exception e) {
               this.wrapAndThrow(provider, e, value, i);
            }

         }
      }
   }

   public void serializeContentsUsing(List value, JsonGenerator jgen, SerializerProvider provider, JsonSerializer ser) throws IOException {
      int len = value.size();
      if (len != 0) {
         TypeSerializer typeSer = this._valueTypeSerializer;

         for(int i = 0; i < len; ++i) {
            Object elem = value.get(i);

            try {
               if (elem == null) {
                  provider.defaultSerializeNull(jgen);
               } else if (typeSer == null) {
                  ser.serialize(elem, jgen, provider);
               } else {
                  ser.serializeWithType(elem, jgen, provider, typeSer);
               }
            } catch (Exception e) {
               this.wrapAndThrow(provider, e, value, i);
            }
         }

      }
   }

   public void serializeTypedContents(List value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
      int len = value.size();
      if (len != 0) {
         int i = 0;

         try {
            TypeSerializer typeSer = this._valueTypeSerializer;

            for(PropertySerializerMap serializers = this._dynamicSerializers; i < len; ++i) {
               Object elem = value.get(i);
               if (elem == null) {
                  provider.defaultSerializeNull(jgen);
               } else {
                  Class<?> cc = elem.getClass();
                  JsonSerializer<Object> serializer = serializers.serializerFor(cc);
                  if (serializer == null) {
                     if (this._elementType.hasGenericTypes()) {
                        serializer = this._findAndAddDynamic(serializers, provider.constructSpecializedType(this._elementType, cc), provider);
                     } else {
                        serializer = this._findAndAddDynamic(serializers, cc, provider);
                     }

                     serializers = this._dynamicSerializers;
                  }

                  serializer.serializeWithType(elem, jgen, provider, typeSer);
               }
            }
         } catch (Exception e) {
            this.wrapAndThrow(provider, e, value, i);
         }

      }
   }
}
