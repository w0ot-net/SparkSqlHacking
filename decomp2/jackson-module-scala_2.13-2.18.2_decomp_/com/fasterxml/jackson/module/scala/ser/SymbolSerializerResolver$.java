package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.Serializers;
import scala.Symbol;
import scala.None.;

public final class SymbolSerializerResolver$ extends Serializers.Base {
   public static final SymbolSerializerResolver$ MODULE$ = new SymbolSerializerResolver$();
   private static final Class SYMBOL = Symbol.class;

   private Class SYMBOL() {
      return SYMBOL;
   }

   public JsonSerializer findSerializer(final SerializationConfig config, final JavaType javaType, final BeanDescription beanDesc) {
      return (JsonSerializer)(this.SYMBOL().isAssignableFrom(javaType.getRawClass()) ? SymbolSerializer$.MODULE$ : (JsonSerializer).MODULE$.orNull(scala..less.colon.less..MODULE$.refl()));
   }

   private SymbolSerializerResolver$() {
   }
}
