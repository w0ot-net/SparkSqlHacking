package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.Deserializers;
import scala.None.;

public final class UntypedObjectDeserializerResolver$ extends Deserializers.Base {
   public static final UntypedObjectDeserializerResolver$ MODULE$ = new UntypedObjectDeserializerResolver$();
   private static Class OBJECT;
   private static volatile boolean bitmap$0;

   private Class OBJECT$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            OBJECT = Object.class;
            bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return OBJECT;
   }

   public Class OBJECT() {
      return !bitmap$0 ? this.OBJECT$lzycompute() : OBJECT;
   }

   public UntypedScalaObjectDeserializer findBeanDeserializer(final JavaType javaType, final DeserializationConfig config, final BeanDescription beanDesc) {
      return !this.OBJECT().equals(javaType.getRawClass()) ? (UntypedScalaObjectDeserializer).MODULE$.orNull(scala..less.colon.less..MODULE$.refl()) : new UntypedScalaObjectDeserializer();
   }

   private UntypedObjectDeserializerResolver$() {
   }
}
