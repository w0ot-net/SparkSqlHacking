package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.factories.ReflectionSerializerFactory;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

final class FieldSerializerAnnotationsUtil {
   public FieldSerializerAnnotationsUtil(FieldSerializer serializer) {
   }

   public void processAnnotatedFields(FieldSerializer fieldSerializer) {
      FieldSerializer.CachedField[] fields = fieldSerializer.getFields();
      int i = 0;

      for(int n = fields.length; i < n; ++i) {
         Field field = fields[i].getField();
         if (field.isAnnotationPresent(FieldSerializer.Bind.class)) {
            Class<? extends Serializer> serializerClass = ((FieldSerializer.Bind)field.getAnnotation(FieldSerializer.Bind.class)).value();
            Serializer s = ReflectionSerializerFactory.makeSerializer(fieldSerializer.getKryo(), serializerClass, field.getClass());
            fields[i].setSerializer(s);
         }

         if (field.isAnnotationPresent(CollectionSerializer.BindCollection.class) && field.isAnnotationPresent(MapSerializer.BindMap.class)) {
         }

         if (field.isAnnotationPresent(CollectionSerializer.BindCollection.class)) {
            if (fields[i].serializer != null) {
               throw new RuntimeException("CollectionSerialier.Bind cannot be used with field " + fields[i].getField().getDeclaringClass().getName() + "." + fields[i].getField().getName() + ", because it has a serializer already.");
            }

            CollectionSerializer.BindCollection annotation = (CollectionSerializer.BindCollection)field.getAnnotation(CollectionSerializer.BindCollection.class);
            if (!Collection.class.isAssignableFrom(fields[i].field.getType())) {
               throw new RuntimeException("CollectionSerialier.Bind should be used only with fields implementing java.util.Collection, but field " + fields[i].getField().getDeclaringClass().getName() + "." + fields[i].getField().getName() + " does not implement it.");
            }

            Class<? extends Serializer> elementSerializerClass = annotation.elementSerializer();
            if (elementSerializerClass == Serializer.class) {
               elementSerializerClass = null;
            }

            Serializer elementSerializer = elementSerializerClass == null ? null : ReflectionSerializerFactory.makeSerializer(fieldSerializer.getKryo(), elementSerializerClass, field.getClass());
            boolean elementsCanBeNull = annotation.elementsCanBeNull();
            Class<?> elementClass = annotation.elementClass();
            if (elementClass == Object.class) {
               elementClass = null;
            }

            CollectionSerializer serializer = new CollectionSerializer();
            serializer.setElementsCanBeNull(elementsCanBeNull);
            serializer.setElementClass(elementClass, elementSerializer);
            fields[i].setSerializer(serializer);
         }

         if (field.isAnnotationPresent(MapSerializer.BindMap.class)) {
            if (fields[i].serializer != null) {
               throw new RuntimeException("MapSerialier.Bind cannot be used with field " + fields[i].getField().getDeclaringClass().getName() + "." + fields[i].getField().getName() + ", because it has a serializer already.");
            }

            MapSerializer.BindMap annotation = (MapSerializer.BindMap)field.getAnnotation(MapSerializer.BindMap.class);
            if (!Map.class.isAssignableFrom(fields[i].field.getType())) {
               throw new RuntimeException("MapSerialier.Bind should be used only with fields implementing java.util.Map, but field " + fields[i].getField().getDeclaringClass().getName() + "." + fields[i].getField().getName() + " does not implement it.");
            }

            Class<? extends Serializer> valueSerializerClass = annotation.valueSerializer();
            Class<? extends Serializer> keySerializerClass = annotation.keySerializer();
            if (valueSerializerClass == Serializer.class) {
               valueSerializerClass = null;
            }

            if (keySerializerClass == Serializer.class) {
               keySerializerClass = null;
            }

            Serializer valueSerializer = valueSerializerClass == null ? null : ReflectionSerializerFactory.makeSerializer(fieldSerializer.getKryo(), valueSerializerClass, field.getClass());
            Serializer keySerializer = keySerializerClass == null ? null : ReflectionSerializerFactory.makeSerializer(fieldSerializer.getKryo(), keySerializerClass, field.getClass());
            boolean valuesCanBeNull = annotation.valuesCanBeNull();
            boolean keysCanBeNull = annotation.keysCanBeNull();
            Class<?> keyClass = annotation.keyClass();
            Class<?> valueClass = annotation.valueClass();
            if (keyClass == Object.class) {
               keyClass = null;
            }

            if (valueClass == Object.class) {
               valueClass = null;
            }

            MapSerializer serializer = new MapSerializer();
            serializer.setKeysCanBeNull(keysCanBeNull);
            serializer.setValuesCanBeNull(valuesCanBeNull);
            serializer.setKeyClass(keyClass, keySerializer);
            serializer.setValueClass(valueClass, valueSerializer);
            fields[i].setSerializer(serializer);
         }
      }

   }
}
