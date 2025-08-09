package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.deser.std.MapDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.MapLikeType;
import scala.None.;
import scala.collection.immutable.IntMap;

public final class IntMapDeserializerResolver$ extends Deserializers.Base {
   public static final IntMapDeserializerResolver$ MODULE$ = new IntMapDeserializerResolver$();
   private static final Class intMapClass = IntMap.class;

   private Class intMapClass() {
      return intMapClass;
   }

   public JsonDeserializer findMapLikeDeserializer(final MapLikeType theType, final DeserializationConfig config, final BeanDescription beanDesc, final KeyDeserializer keyDeserializer, final TypeDeserializer elementTypeDeserializer, final JsonDeserializer elementDeserializer) {
      if (!this.intMapClass().isAssignableFrom(theType.getRawClass())) {
         return (JsonDeserializer).MODULE$.orNull(scala..less.colon.less..MODULE$.refl());
      } else {
         MapDeserializer mapDeserializer = new MapDeserializer(theType, new IntMapDeserializerResolver.IntMapInstantiator(config, theType), keyDeserializer, elementDeserializer, elementTypeDeserializer);
         return new IntMapDeserializerResolver.IntMapDeserializer(theType, mapDeserializer);
      }
   }

   private IntMapDeserializerResolver$() {
   }
}
