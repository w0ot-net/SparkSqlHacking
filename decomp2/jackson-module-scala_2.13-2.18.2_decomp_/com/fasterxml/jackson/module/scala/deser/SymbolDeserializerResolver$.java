package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.Deserializers;
import scala.Symbol;

public final class SymbolDeserializerResolver$ extends Deserializers.Base {
   public static final SymbolDeserializerResolver$ MODULE$ = new SymbolDeserializerResolver$();
   private static final Class SYMBOL = Symbol.class;

   private Class SYMBOL() {
      return SYMBOL;
   }

   public JsonDeserializer findBeanDeserializer(final JavaType javaType, final DeserializationConfig config, final BeanDescription beanDesc) {
      return this.SYMBOL().isAssignableFrom(javaType.getRawClass()) ? SymbolDeserializer$.MODULE$ : null;
   }

   private SymbolDeserializerResolver$() {
   }
}
