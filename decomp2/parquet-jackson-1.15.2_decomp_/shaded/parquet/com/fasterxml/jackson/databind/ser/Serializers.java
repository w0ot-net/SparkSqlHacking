package shaded.parquet.com.fasterxml.jackson.databind.ser;

import shaded.parquet.com.fasterxml.jackson.databind.BeanDescription;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.SerializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.type.ArrayType;
import shaded.parquet.com.fasterxml.jackson.databind.type.CollectionLikeType;
import shaded.parquet.com.fasterxml.jackson.databind.type.CollectionType;
import shaded.parquet.com.fasterxml.jackson.databind.type.MapLikeType;
import shaded.parquet.com.fasterxml.jackson.databind.type.MapType;
import shaded.parquet.com.fasterxml.jackson.databind.type.ReferenceType;

public interface Serializers {
   JsonSerializer findSerializer(SerializationConfig var1, JavaType var2, BeanDescription var3);

   JsonSerializer findReferenceSerializer(SerializationConfig var1, ReferenceType var2, BeanDescription var3, TypeSerializer var4, JsonSerializer var5);

   JsonSerializer findArraySerializer(SerializationConfig var1, ArrayType var2, BeanDescription var3, TypeSerializer var4, JsonSerializer var5);

   JsonSerializer findCollectionSerializer(SerializationConfig var1, CollectionType var2, BeanDescription var3, TypeSerializer var4, JsonSerializer var5);

   JsonSerializer findCollectionLikeSerializer(SerializationConfig var1, CollectionLikeType var2, BeanDescription var3, TypeSerializer var4, JsonSerializer var5);

   JsonSerializer findMapSerializer(SerializationConfig var1, MapType var2, BeanDescription var3, JsonSerializer var4, TypeSerializer var5, JsonSerializer var6);

   JsonSerializer findMapLikeSerializer(SerializationConfig var1, MapLikeType var2, BeanDescription var3, JsonSerializer var4, TypeSerializer var5, JsonSerializer var6);

   public static class Base implements Serializers {
      public JsonSerializer findSerializer(SerializationConfig config, JavaType type, BeanDescription beanDesc) {
         return null;
      }

      public JsonSerializer findReferenceSerializer(SerializationConfig config, ReferenceType type, BeanDescription beanDesc, TypeSerializer contentTypeSerializer, JsonSerializer contentValueSerializer) {
         return this.findSerializer(config, type, beanDesc);
      }

      public JsonSerializer findArraySerializer(SerializationConfig config, ArrayType type, BeanDescription beanDesc, TypeSerializer elementTypeSerializer, JsonSerializer elementValueSerializer) {
         return null;
      }

      public JsonSerializer findCollectionSerializer(SerializationConfig config, CollectionType type, BeanDescription beanDesc, TypeSerializer elementTypeSerializer, JsonSerializer elementValueSerializer) {
         return null;
      }

      public JsonSerializer findCollectionLikeSerializer(SerializationConfig config, CollectionLikeType type, BeanDescription beanDesc, TypeSerializer elementTypeSerializer, JsonSerializer elementValueSerializer) {
         return null;
      }

      public JsonSerializer findMapSerializer(SerializationConfig config, MapType type, BeanDescription beanDesc, JsonSerializer keySerializer, TypeSerializer elementTypeSerializer, JsonSerializer elementValueSerializer) {
         return null;
      }

      public JsonSerializer findMapLikeSerializer(SerializationConfig config, MapLikeType type, BeanDescription beanDesc, JsonSerializer keySerializer, TypeSerializer elementTypeSerializer, JsonSerializer elementValueSerializer) {
         return null;
      }
   }
}
