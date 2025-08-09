package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import scala.None.;
import scala.collection.Iterator;

public final class ScalaIteratorSerializerResolver$ extends Serializers.Base {
   public static final ScalaIteratorSerializerResolver$ MODULE$ = new ScalaIteratorSerializerResolver$();
   private static final Class JSONSERIALIZABLE_CLASS = JsonSerializable.class;
   private static final Class SCALAITERATOR_CLASS = Iterator.class;

   private Class JSONSERIALIZABLE_CLASS() {
      return JSONSERIALIZABLE_CLASS;
   }

   private Class SCALAITERATOR_CLASS() {
      return SCALAITERATOR_CLASS;
   }

   public JsonSerializer findCollectionLikeSerializer(final SerializationConfig config, final CollectionLikeType collectionType, final BeanDescription beanDescription, final TypeSerializer elementTypeSerializer, final JsonSerializer elementSerializer) {
      Class rawClass = collectionType.getRawClass();
      return (JsonSerializer)(this.SCALAITERATOR_CLASS().isAssignableFrom(rawClass) && !this.JSONSERIALIZABLE_CLASS().isAssignableFrom(rawClass) ? new UnresolvedIteratorSerializer(rawClass, collectionType.getContentType(), false, elementTypeSerializer, elementSerializer) : (JsonSerializer).MODULE$.orNull(scala..less.colon.less..MODULE$.refl()));
   }

   private ScalaIteratorSerializerResolver$() {
   }
}
