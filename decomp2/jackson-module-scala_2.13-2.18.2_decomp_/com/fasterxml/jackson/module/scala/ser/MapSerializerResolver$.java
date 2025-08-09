package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.ser.std.StdDelegatingSerializer;
import com.fasterxml.jackson.databind.type.MapLikeType;
import scala.None.;
import scala.collection.Map;

public final class MapSerializerResolver$ extends Serializers.Base {
   public static final MapSerializerResolver$ MODULE$ = new MapSerializerResolver$();
   private static final Class BASE_CLASS = Map.class;
   private static final Class JSONSERIALIZABLE_CLASS = JsonSerializable.class;

   private Class BASE_CLASS() {
      return BASE_CLASS;
   }

   private Class JSONSERIALIZABLE_CLASS() {
      return JSONSERIALIZABLE_CLASS;
   }

   public JsonSerializer findMapLikeSerializer(final SerializationConfig config, final MapLikeType mapLikeType, final BeanDescription beanDesc, final JsonSerializer keySerializer, final TypeSerializer elementTypeSerializer, final JsonSerializer elementValueSerializer) {
      Class rawClass = mapLikeType.getRawClass();
      return (JsonSerializer)(this.BASE_CLASS().isAssignableFrom(rawClass) && !this.JSONSERIALIZABLE_CLASS().isAssignableFrom(rawClass) ? new StdDelegatingSerializer(new MapConverter(mapLikeType, config)) : (JsonSerializer).MODULE$.orNull(scala..less.colon.less..MODULE$.refl()));
   }

   private MapSerializerResolver$() {
   }
}
