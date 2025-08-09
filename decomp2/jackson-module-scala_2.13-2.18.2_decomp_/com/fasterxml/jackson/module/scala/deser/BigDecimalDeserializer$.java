package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import scala.math.BigDecimal;
import scala.package.;
import scala.runtime.ModuleSerializationProxy;

public final class BigDecimalDeserializer$ extends StdScalarDeserializer {
   public static final BigDecimalDeserializer$ MODULE$ = new BigDecimalDeserializer$();
   private static final BigDecimal ZERO;

   static {
      ZERO = .MODULE$.BigDecimal().apply(0);
   }

   private BigDecimal ZERO() {
      return ZERO;
   }

   public BigDecimal deserialize(final JsonParser p, final DeserializationContext ctxt) {
      return scala.math.BigDecimal..MODULE$.javaBigDecimal2bigDecimal(com.fasterxml.jackson.databind.deser.std.NumberDeserializers.BigDecimalDeserializer.instance.deserialize(p, ctxt));
   }

   public Object getEmptyValue(final DeserializationContext ctxt) {
      return this.ZERO();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BigDecimalDeserializer$.class);
   }

   private BigDecimalDeserializer$() {
      super(BigDecimal.class);
   }
}
