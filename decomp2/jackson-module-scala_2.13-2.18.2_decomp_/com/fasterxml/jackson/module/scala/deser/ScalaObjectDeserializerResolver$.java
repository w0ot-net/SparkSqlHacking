package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.module.scala.util.ClassW$;
import java.lang.invoke.SerializedLambda;
import scala.Option.;

public final class ScalaObjectDeserializerResolver$ extends Deserializers.Base {
   public static final ScalaObjectDeserializerResolver$ MODULE$ = new ScalaObjectDeserializerResolver$();

   public JsonDeserializer findBeanDeserializer(final JavaType javaType, final DeserializationConfig config, final BeanDescription beanDesc) {
      return (JsonDeserializer)ClassW$.MODULE$.apply(() -> javaType.getRawClass()).getModuleField().flatMap((field) -> .MODULE$.apply(field.get((Object)null))).map((x$1) -> new ScalaObjectDeserializer(x$1)).orNull(scala..less.colon.less..MODULE$.refl());
   }

   private ScalaObjectDeserializerResolver$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
