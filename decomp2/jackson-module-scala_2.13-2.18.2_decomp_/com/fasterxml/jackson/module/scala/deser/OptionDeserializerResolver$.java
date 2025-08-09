package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ReferenceType;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.None.;

public final class OptionDeserializerResolver$ extends Deserializers.Base {
   public static final OptionDeserializerResolver$ MODULE$ = new OptionDeserializerResolver$();
   private static final Class OPTION = Option.class;

   private Class OPTION() {
      return OPTION;
   }

   public JsonDeserializer findReferenceDeserializer(final ReferenceType refType, final DeserializationConfig config, final BeanDescription beanDesc, final TypeDeserializer contentTypeDeserializer, final JsonDeserializer contentDeserializer) {
      if (!this.OPTION().isAssignableFrom(refType.getRawClass())) {
         return (JsonDeserializer).MODULE$.orNull(scala..less.colon.less..MODULE$.refl());
      } else {
         JavaType elementType = refType.getContentType();
         Option typeDeser = scala.Option..MODULE$.apply(contentTypeDeserializer).orElse(() -> scala.Option..MODULE$.apply(elementType.getTypeHandler()));
         Option valDeser = scala.Option..MODULE$.apply(contentDeserializer).orElse(() -> scala.Option..MODULE$.apply(elementType.getValueHandler()));
         return new OptionDeserializer(refType, typeDeser, valDeser, OptionDeserializer$.MODULE$.$lessinit$greater$default$4());
      }
   }

   private OptionDeserializerResolver$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
