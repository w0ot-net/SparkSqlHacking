package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import scala.None.;
import scala.collection.Iterable;
import scala.collection.Map;

public final class IterableSerializerResolver$ extends Serializers.Base {
   public static final IterableSerializerResolver$ MODULE$ = new IterableSerializerResolver$();
   private static final Class JSONSERIALIZABLE_CLASS = JsonSerializable.class;
   private static final Class ITERABLE_CLASS = Iterable.class;
   private static final Class MAP_CLASS = Map.class;

   private Class JSONSERIALIZABLE_CLASS() {
      return JSONSERIALIZABLE_CLASS;
   }

   private Class ITERABLE_CLASS() {
      return ITERABLE_CLASS;
   }

   private Class MAP_CLASS() {
      return MAP_CLASS;
   }

   public JsonSerializer findCollectionLikeSerializer(final SerializationConfig config, final CollectionLikeType collectionType, final BeanDescription beanDescription, final TypeSerializer elementTypeSerializer, final JsonSerializer elementSerializer) {
      Class rawClass = collectionType.getRawClass();
      if (!this.ITERABLE_CLASS().isAssignableFrom(rawClass)) {
         return (JsonSerializer).MODULE$.orNull(scala..less.colon.less..MODULE$.refl());
      } else if (this.MAP_CLASS().isAssignableFrom(rawClass)) {
         return (JsonSerializer).MODULE$.orNull(scala..less.colon.less..MODULE$.refl());
      } else {
         return (JsonSerializer)(this.JSONSERIALIZABLE_CLASS().isAssignableFrom(rawClass) ? (JsonSerializer).MODULE$.orNull(scala..less.colon.less..MODULE$.refl()) : new UnresolvedIterableSerializer(rawClass, config.constructType(Object.class), false, elementTypeSerializer, elementSerializer));
      }
   }

   private IterableSerializerResolver$() {
   }
}
