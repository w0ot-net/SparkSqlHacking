package shaded.parquet.com.fasterxml.jackson.databind.deser;

import shaded.parquet.com.fasterxml.jackson.databind.BeanDescription;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.JsonNode;
import shaded.parquet.com.fasterxml.jackson.databind.KeyDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.type.ArrayType;
import shaded.parquet.com.fasterxml.jackson.databind.type.CollectionLikeType;
import shaded.parquet.com.fasterxml.jackson.databind.type.CollectionType;
import shaded.parquet.com.fasterxml.jackson.databind.type.MapLikeType;
import shaded.parquet.com.fasterxml.jackson.databind.type.MapType;
import shaded.parquet.com.fasterxml.jackson.databind.type.ReferenceType;

public interface Deserializers {
   JsonDeserializer findEnumDeserializer(Class var1, DeserializationConfig var2, BeanDescription var3) throws JsonMappingException;

   JsonDeserializer findTreeNodeDeserializer(Class var1, DeserializationConfig var2, BeanDescription var3) throws JsonMappingException;

   JsonDeserializer findBeanDeserializer(JavaType var1, DeserializationConfig var2, BeanDescription var3) throws JsonMappingException;

   JsonDeserializer findReferenceDeserializer(ReferenceType var1, DeserializationConfig var2, BeanDescription var3, TypeDeserializer var4, JsonDeserializer var5) throws JsonMappingException;

   JsonDeserializer findArrayDeserializer(ArrayType var1, DeserializationConfig var2, BeanDescription var3, TypeDeserializer var4, JsonDeserializer var5) throws JsonMappingException;

   JsonDeserializer findCollectionDeserializer(CollectionType var1, DeserializationConfig var2, BeanDescription var3, TypeDeserializer var4, JsonDeserializer var5) throws JsonMappingException;

   JsonDeserializer findCollectionLikeDeserializer(CollectionLikeType var1, DeserializationConfig var2, BeanDescription var3, TypeDeserializer var4, JsonDeserializer var5) throws JsonMappingException;

   JsonDeserializer findMapDeserializer(MapType var1, DeserializationConfig var2, BeanDescription var3, KeyDeserializer var4, TypeDeserializer var5, JsonDeserializer var6) throws JsonMappingException;

   JsonDeserializer findMapLikeDeserializer(MapLikeType var1, DeserializationConfig var2, BeanDescription var3, KeyDeserializer var4, TypeDeserializer var5, JsonDeserializer var6) throws JsonMappingException;

   default boolean hasDeserializerFor(DeserializationConfig config, Class valueType) {
      return false;
   }

   public abstract static class Base implements Deserializers {
      public JsonDeserializer findEnumDeserializer(Class type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
         return null;
      }

      public JsonDeserializer findTreeNodeDeserializer(Class nodeType, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
         return null;
      }

      public JsonDeserializer findReferenceDeserializer(ReferenceType refType, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer contentTypeDeserializer, JsonDeserializer contentDeserializer) throws JsonMappingException {
         return null;
      }

      public JsonDeserializer findBeanDeserializer(JavaType type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
         return null;
      }

      public JsonDeserializer findArrayDeserializer(ArrayType type, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer elementTypeDeserializer, JsonDeserializer elementDeserializer) throws JsonMappingException {
         return null;
      }

      public JsonDeserializer findCollectionDeserializer(CollectionType type, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer elementTypeDeserializer, JsonDeserializer elementDeserializer) throws JsonMappingException {
         return null;
      }

      public JsonDeserializer findCollectionLikeDeserializer(CollectionLikeType type, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer elementTypeDeserializer, JsonDeserializer elementDeserializer) throws JsonMappingException {
         return null;
      }

      public JsonDeserializer findMapDeserializer(MapType type, DeserializationConfig config, BeanDescription beanDesc, KeyDeserializer keyDeserializer, TypeDeserializer elementTypeDeserializer, JsonDeserializer elementDeserializer) throws JsonMappingException {
         return null;
      }

      public JsonDeserializer findMapLikeDeserializer(MapLikeType type, DeserializationConfig config, BeanDescription beanDesc, KeyDeserializer keyDeserializer, TypeDeserializer elementTypeDeserializer, JsonDeserializer elementDeserializer) throws JsonMappingException {
         return null;
      }
   }
}
