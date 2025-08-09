package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.type.ReferenceType;
import scala.Option;
import scala.None.;

public final class OptionSerializerResolver$ extends Serializers.Base {
   public static final OptionSerializerResolver$ MODULE$ = new OptionSerializerResolver$();
   private static final Class OPTION = Option.class;

   private Class OPTION() {
      return OPTION;
   }

   public JsonSerializer findReferenceSerializer(final SerializationConfig config, final ReferenceType refType, final BeanDescription beanDesc, final TypeSerializer contentTypeSerializer, final JsonSerializer contentValueSerializer) {
      if (!this.OPTION().isAssignableFrom(refType.getRawClass())) {
         return (JsonSerializer).MODULE$.orNull(scala..less.colon.less..MODULE$.refl());
      } else {
         boolean staticTyping = contentTypeSerializer == null && config.isEnabled(MapperFeature.USE_STATIC_TYPING);
         return new OptionSerializer(refType, staticTyping, contentTypeSerializer, contentValueSerializer);
      }
   }

   private OptionSerializerResolver$() {
   }
}
