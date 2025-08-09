package shaded.parquet.com.fasterxml.jackson.databind.ser;

import java.io.Serializable;
import java.util.List;
import shaded.parquet.com.fasterxml.jackson.databind.BeanDescription;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.SerializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.type.ArrayType;
import shaded.parquet.com.fasterxml.jackson.databind.type.CollectionLikeType;
import shaded.parquet.com.fasterxml.jackson.databind.type.CollectionType;
import shaded.parquet.com.fasterxml.jackson.databind.type.MapLikeType;
import shaded.parquet.com.fasterxml.jackson.databind.type.MapType;

public abstract class BeanSerializerModifier implements Serializable {
   private static final long serialVersionUID = 1L;

   public List changeProperties(SerializationConfig config, BeanDescription beanDesc, List beanProperties) {
      return beanProperties;
   }

   public List orderProperties(SerializationConfig config, BeanDescription beanDesc, List beanProperties) {
      return beanProperties;
   }

   public BeanSerializerBuilder updateBuilder(SerializationConfig config, BeanDescription beanDesc, BeanSerializerBuilder builder) {
      return builder;
   }

   public JsonSerializer modifySerializer(SerializationConfig config, BeanDescription beanDesc, JsonSerializer serializer) {
      return serializer;
   }

   public JsonSerializer modifyArraySerializer(SerializationConfig config, ArrayType valueType, BeanDescription beanDesc, JsonSerializer serializer) {
      return serializer;
   }

   public JsonSerializer modifyCollectionSerializer(SerializationConfig config, CollectionType valueType, BeanDescription beanDesc, JsonSerializer serializer) {
      return serializer;
   }

   public JsonSerializer modifyCollectionLikeSerializer(SerializationConfig config, CollectionLikeType valueType, BeanDescription beanDesc, JsonSerializer serializer) {
      return serializer;
   }

   public JsonSerializer modifyMapSerializer(SerializationConfig config, MapType valueType, BeanDescription beanDesc, JsonSerializer serializer) {
      return serializer;
   }

   public JsonSerializer modifyMapLikeSerializer(SerializationConfig config, MapLikeType valueType, BeanDescription beanDesc, JsonSerializer serializer) {
      return serializer;
   }

   public JsonSerializer modifyEnumSerializer(SerializationConfig config, JavaType valueType, BeanDescription beanDesc, JsonSerializer serializer) {
      return serializer;
   }

   public JsonSerializer modifyKeySerializer(SerializationConfig config, JavaType valueType, BeanDescription beanDesc, JsonSerializer serializer) {
      return serializer;
   }
}
