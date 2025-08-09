package shaded.parquet.com.fasterxml.jackson.databind.module;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import shaded.parquet.com.fasterxml.jackson.databind.BeanDescription;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.JsonNode;
import shaded.parquet.com.fasterxml.jackson.databind.KeyDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.deser.Deserializers;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.type.ArrayType;
import shaded.parquet.com.fasterxml.jackson.databind.type.ClassKey;
import shaded.parquet.com.fasterxml.jackson.databind.type.CollectionLikeType;
import shaded.parquet.com.fasterxml.jackson.databind.type.CollectionType;
import shaded.parquet.com.fasterxml.jackson.databind.type.MapLikeType;
import shaded.parquet.com.fasterxml.jackson.databind.type.MapType;
import shaded.parquet.com.fasterxml.jackson.databind.type.ReferenceType;

public class SimpleDeserializers extends Deserializers.Base implements Serializable {
   private static final long serialVersionUID = 1L;
   protected HashMap _classMappings = null;
   protected boolean _hasEnumDeserializer = false;

   public SimpleDeserializers() {
   }

   public SimpleDeserializers(Map desers) {
      this.addDeserializers(desers);
   }

   public void addDeserializer(Class forClass, JsonDeserializer deser) {
      ClassKey key = new ClassKey(forClass);
      if (this._classMappings == null) {
         this._classMappings = new HashMap();
      }

      this._classMappings.put(key, deser);
      if (forClass == Enum.class) {
         this._hasEnumDeserializer = true;
      }

   }

   public void addDeserializers(Map desers) {
      for(Map.Entry entry : desers.entrySet()) {
         Class<?> cls = (Class)entry.getKey();
         JsonDeserializer<Object> deser = (JsonDeserializer)entry.getValue();
         this.addDeserializer(cls, deser);
      }

   }

   public JsonDeserializer findArrayDeserializer(ArrayType type, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer elementTypeDeserializer, JsonDeserializer elementDeserializer) throws JsonMappingException {
      return this._find(type);
   }

   public JsonDeserializer findBeanDeserializer(JavaType type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
      return this._find(type);
   }

   public JsonDeserializer findCollectionDeserializer(CollectionType type, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer elementTypeDeserializer, JsonDeserializer elementDeserializer) throws JsonMappingException {
      return this._find(type);
   }

   public JsonDeserializer findCollectionLikeDeserializer(CollectionLikeType type, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer elementTypeDeserializer, JsonDeserializer elementDeserializer) throws JsonMappingException {
      return this._find(type);
   }

   public JsonDeserializer findEnumDeserializer(Class type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
      if (this._classMappings == null) {
         return null;
      } else {
         JsonDeserializer<?> deser = (JsonDeserializer)this._classMappings.get(new ClassKey(type));
         if (deser == null && this._hasEnumDeserializer && type.isEnum()) {
            deser = (JsonDeserializer)this._classMappings.get(new ClassKey(Enum.class));
         }

         return deser;
      }
   }

   public JsonDeserializer findTreeNodeDeserializer(Class nodeType, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
      return this._classMappings == null ? null : (JsonDeserializer)this._classMappings.get(new ClassKey(nodeType));
   }

   public JsonDeserializer findReferenceDeserializer(ReferenceType refType, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer contentTypeDeserializer, JsonDeserializer contentDeserializer) throws JsonMappingException {
      return this._find(refType);
   }

   public JsonDeserializer findMapDeserializer(MapType type, DeserializationConfig config, BeanDescription beanDesc, KeyDeserializer keyDeserializer, TypeDeserializer elementTypeDeserializer, JsonDeserializer elementDeserializer) throws JsonMappingException {
      return this._find(type);
   }

   public JsonDeserializer findMapLikeDeserializer(MapLikeType type, DeserializationConfig config, BeanDescription beanDesc, KeyDeserializer keyDeserializer, TypeDeserializer elementTypeDeserializer, JsonDeserializer elementDeserializer) throws JsonMappingException {
      return this._find(type);
   }

   public boolean hasDeserializerFor(DeserializationConfig config, Class valueType) {
      return this._classMappings != null && this._classMappings.containsKey(new ClassKey(valueType));
   }

   private final JsonDeserializer _find(JavaType type) {
      return this._classMappings == null ? null : (JsonDeserializer)this._classMappings.get(new ClassKey(type.getRawClass()));
   }
}
