package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.type.ReferenceType;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.None.;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

public final class EitherSerializerResolver$ extends Serializers.Base {
   public static final EitherSerializerResolver$ MODULE$ = new EitherSerializerResolver$();
   private static final Class EITHER = Either.class;
   private static final Class LEFT = Left.class;
   private static final Class RIGHT = Right.class;

   private Class EITHER() {
      return EITHER;
   }

   private Class LEFT() {
      return LEFT;
   }

   private Class RIGHT() {
      return RIGHT;
   }

   public JsonSerializer findReferenceSerializer(final SerializationConfig config, final ReferenceType refType, final BeanDescription beanDesc, final TypeSerializer contentTypeSerializer, final JsonSerializer contentValueSerializer) {
      if (!this.EITHER().isAssignableFrom(refType.getRawClass())) {
         return (JsonSerializer).MODULE$.orNull(scala..less.colon.less..MODULE$.refl());
      } else {
         JavaType javaType = !this.LEFT().isAssignableFrom(refType.getRawClass()) && !this.RIGHT().isAssignableFrom(refType.getRawClass()) ? refType.getReferencedType() : refType.getReferencedType().getSuperClass();
         JavaType leftType = javaType.containedType(0);
         JavaType rightType = javaType.containedType(1);
         Option typeSer = scala.Option..MODULE$.apply(contentTypeSerializer).orElse(() -> scala.Option..MODULE$.apply(javaType.getTypeHandler()));
         Option valSer = scala.Option..MODULE$.apply(contentValueSerializer).orElse(() -> scala.Option..MODULE$.apply(javaType.getValueHandler()));
         EitherDetails left = new EitherDetails(scala.Option..MODULE$.apply(leftType), typeSer, valSer);
         EitherDetails right = new EitherDetails(scala.Option..MODULE$.apply(rightType), typeSer, valSer);
         return new EitherSerializer(left.withHandlers(typeSer, valSer), right.withHandlers(typeSer, valSer), .MODULE$, EitherSerializer$.MODULE$.$lessinit$greater$default$4(), EitherSerializer$.MODULE$.$lessinit$greater$default$5());
      }
   }

   private EitherSerializerResolver$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
