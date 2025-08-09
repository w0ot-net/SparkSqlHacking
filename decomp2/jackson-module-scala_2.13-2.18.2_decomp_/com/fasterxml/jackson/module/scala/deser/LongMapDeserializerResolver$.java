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
import scala.collection.immutable.LongMap;

public final class LongMapDeserializerResolver$ extends Deserializers.Base {
   public static final LongMapDeserializerResolver$ MODULE$ = new LongMapDeserializerResolver$();
   private static final Class immutableLongMapClass = LongMap.class;
   private static final Class mutableLongMapClass = scala.collection.mutable.LongMap.class;

   private Class immutableLongMapClass() {
      return immutableLongMapClass;
   }

   private Class mutableLongMapClass() {
      return mutableLongMapClass;
   }

   public JsonDeserializer findMapLikeDeserializer(final MapLikeType theType, final DeserializationConfig config, final BeanDescription beanDesc, final KeyDeserializer keyDeserializer, final TypeDeserializer elementTypeDeserializer, final JsonDeserializer elementDeserializer) {
      if (this.immutableLongMapClass().isAssignableFrom(theType.getRawClass())) {
         MapDeserializer mapDeserializer = new MapDeserializer(theType, new LongMapDeserializerResolver.ImmutableLongMapInstantiator(config, theType), keyDeserializer, elementDeserializer, elementTypeDeserializer);
         return new LongMapDeserializerResolver.ImmutableLongMapDeserializer(theType, mapDeserializer);
      } else if (this.mutableLongMapClass().isAssignableFrom(theType.getRawClass())) {
         MapDeserializer mapDeserializer = new MapDeserializer(theType, new LongMapDeserializerResolver.MutableLongMapInstantiator(config, theType), keyDeserializer, elementDeserializer, elementTypeDeserializer);
         return new LongMapDeserializerResolver.MutableLongMapDeserializer(theType, mapDeserializer);
      } else {
         return (JsonDeserializer).MODULE$.orNull(scala..less.colon.less..MODULE$.refl());
      }
   }

   private LongMapDeserializerResolver$() {
   }
}
