package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ReferenceType;
import scala.util.Either;

public final class EitherDeserializerResolver$ extends Deserializers.Base {
   public static final EitherDeserializerResolver$ MODULE$ = new EitherDeserializerResolver$();
   private static final Class EITHER = Either.class;

   private Class EITHER() {
      return EITHER;
   }

   public JsonDeserializer findBeanDeserializer(final JavaType type, final DeserializationConfig config, final BeanDescription beanDesc) {
      Class rawClass = type.getRawClass();
      return (JsonDeserializer)(!this.EITHER().isAssignableFrom(rawClass) ? super.findBeanDeserializer(type, config, beanDesc) : new EitherDeserializer(type, config, EitherDeserializer.ElementDeserializerConfig$.MODULE$.empty(), EitherDeserializer.ElementDeserializerConfig$.MODULE$.empty()));
   }

   public JsonDeserializer findReferenceDeserializer(final ReferenceType refType, final DeserializationConfig config, final BeanDescription beanDesc, final TypeDeserializer contentTypeDeserializer, final JsonDeserializer contentDeserializer) {
      Class rawClass = refType.getRawClass();
      return (JsonDeserializer)(!this.EITHER().isAssignableFrom(rawClass) ? super.findReferenceDeserializer(refType, config, beanDesc, contentTypeDeserializer, contentDeserializer) : new EitherDeserializer(refType, config, EitherDeserializer.ElementDeserializerConfig$.MODULE$.empty(), EitherDeserializer.ElementDeserializerConfig$.MODULE$.empty()));
   }

   private EitherDeserializerResolver$() {
   }
}
