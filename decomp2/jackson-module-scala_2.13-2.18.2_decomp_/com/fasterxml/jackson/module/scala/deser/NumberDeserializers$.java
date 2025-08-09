package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import scala.None.;
import scala.math.BigDecimal;
import scala.math.BigInt;

public final class NumberDeserializers$ extends Deserializers.Base {
   public static final NumberDeserializers$ MODULE$ = new NumberDeserializers$();
   private static final Class BigDecimalClass = BigDecimal.class;
   private static final Class BigIntClass = BigInt.class;

   private Class BigDecimalClass() {
      return BigDecimalClass;
   }

   private Class BigIntClass() {
      return BigIntClass;
   }

   public JsonDeserializer findBeanDeserializer(final JavaType tpe, final DeserializationConfig config, final BeanDescription beanDesc) {
      Class var5 = tpe.getRawClass();
      Class var10000 = this.BigDecimalClass();
      if (var10000 == null) {
         if (var5 == null) {
            return BigDecimalDeserializer$.MODULE$;
         }
      } else if (var10000.equals(var5)) {
         return BigDecimalDeserializer$.MODULE$;
      }

      var10000 = this.BigIntClass();
      if (var10000 == null) {
         if (var5 == null) {
            return BigIntDeserializer$.MODULE$;
         }
      } else if (var10000.equals(var5)) {
         return BigIntDeserializer$.MODULE$;
      }

      return (StdScalarDeserializer).MODULE$.orNull(scala..less.colon.less..MODULE$.refl());
   }

   private NumberDeserializers$() {
   }
}
