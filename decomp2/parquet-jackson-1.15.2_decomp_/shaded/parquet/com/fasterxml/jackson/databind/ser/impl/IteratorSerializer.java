package shaded.parquet.com.fasterxml.jackson.databind.ser.impl;

import java.io.IOException;
import java.util.Iterator;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.databind.BeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;
import shaded.parquet.com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.ser.ContainerSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;

@JacksonStdImpl
public class IteratorSerializer extends AsArraySerializerBase {
   public IteratorSerializer(JavaType elemType, boolean staticTyping, TypeSerializer vts) {
      super(Iterator.class, elemType, staticTyping, vts, (JsonSerializer)null);
   }

   public IteratorSerializer(IteratorSerializer src, BeanProperty property, TypeSerializer vts, JsonSerializer valueSerializer, Boolean unwrapSingle) {
      super(src, property, vts, valueSerializer, unwrapSingle);
   }

   public boolean isEmpty(SerializerProvider prov, Iterator value) {
      return !value.hasNext();
   }

   public boolean hasSingleElement(Iterator value) {
      return false;
   }

   public ContainerSerializer _withValueTypeSerializer(TypeSerializer vts) {
      return new IteratorSerializer(this, this._property, vts, this._elementSerializer, this._unwrapSingle);
   }

   public IteratorSerializer withResolved(BeanProperty property, TypeSerializer vts, JsonSerializer elementSerializer, Boolean unwrapSingle) {
      return new IteratorSerializer(this, property, vts, elementSerializer, unwrapSingle);
   }

   public final void serialize(Iterator value, JsonGenerator gen, SerializerProvider provider) throws IOException {
      gen.writeStartArray(value);
      this.serializeContents(value, gen, provider);
      gen.writeEndArray();
   }

   public void serializeContents(Iterator value, JsonGenerator g, SerializerProvider provider) throws IOException {
      if (value.hasNext()) {
         JsonSerializer<Object> serializer = this._elementSerializer;
         if (serializer == null) {
            this._serializeDynamicContents(value, g, provider);
         } else {
            TypeSerializer typeSer = this._valueTypeSerializer;

            do {
               Object elem = value.next();
               if (elem == null) {
                  provider.defaultSerializeNull(g);
               } else if (typeSer == null) {
                  serializer.serialize(elem, g, provider);
               } else {
                  serializer.serializeWithType(elem, g, provider, typeSer);
               }
            } while(value.hasNext());

         }
      }
   }

   protected void _serializeDynamicContents(Iterator value, JsonGenerator g, SerializerProvider provider) throws IOException {
      TypeSerializer typeSer = this._valueTypeSerializer;
      PropertySerializerMap serializers = this._dynamicSerializers;

      do {
         Object elem = value.next();
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

            if (typeSer == null) {
               serializer.serialize(elem, g, provider);
            } else {
               serializer.serializeWithType(elem, g, provider, typeSer);
            }
         }
      } while(value.hasNext());

   }
}
