package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.Serializers;
import scala.Product;
import scala.None.;

public final class TupleSerializerResolver$ extends Serializers.Base {
   public static final TupleSerializerResolver$ MODULE$ = new TupleSerializerResolver$();
   private static final Class PRODUCT = Product.class;

   private Class PRODUCT() {
      return PRODUCT;
   }

   public TupleSerializer findSerializer(final SerializationConfig config, final JavaType javaType, final BeanDescription beanDesc) {
      Class cls = javaType.getRawClass();
      if (!this.PRODUCT().isAssignableFrom(cls)) {
         return (TupleSerializer).MODULE$.orNull(scala..less.colon.less..MODULE$.refl());
      } else {
         return !cls.getName().startsWith("scala.Tuple") ? (TupleSerializer).MODULE$.orNull(scala..less.colon.less..MODULE$.refl()) : new TupleSerializer();
      }
   }

   private TupleSerializerResolver$() {
   }
}
