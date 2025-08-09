package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.Deserializers;
import scala.Product;
import scala.None.;

public final class TupleDeserializerResolver$ extends Deserializers.Base {
   public static final TupleDeserializerResolver$ MODULE$ = new TupleDeserializerResolver$();
   private static final Class PRODUCT = Product.class;

   private Class PRODUCT() {
      return PRODUCT;
   }

   public JsonDeserializer findBeanDeserializer(final JavaType javaType, final DeserializationConfig config, final BeanDescription beanDesc) {
      Class cls = javaType.getRawClass();
      if (!this.PRODUCT().isAssignableFrom(cls)) {
         return (JsonDeserializer).MODULE$.orNull(scala..less.colon.less..MODULE$.refl());
      } else {
         return (JsonDeserializer)(!cls.getName().startsWith("scala.Tuple") ? (JsonDeserializer).MODULE$.orNull(scala..less.colon.less..MODULE$.refl()) : new TupleDeserializer(javaType, config, TupleDeserializer$.MODULE$.$lessinit$greater$default$3(), TupleDeserializer$.MODULE$.$lessinit$greater$default$4()));
      }
   }

   private TupleDeserializerResolver$() {
   }
}
