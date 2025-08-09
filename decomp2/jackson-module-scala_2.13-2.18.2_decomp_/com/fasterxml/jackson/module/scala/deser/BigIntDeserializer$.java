package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers.BigIntegerDeserializer;
import scala.math.BigInt;
import scala.package.;
import scala.runtime.ModuleSerializationProxy;

public final class BigIntDeserializer$ extends StdScalarDeserializer {
   public static final BigIntDeserializer$ MODULE$ = new BigIntDeserializer$();
   private static final BigInt ZERO;

   static {
      ZERO = .MODULE$.BigInt().apply(0);
   }

   private BigInt ZERO() {
      return ZERO;
   }

   public BigInt deserialize(final JsonParser p, final DeserializationContext ctxt) {
      return scala.math.BigInt..MODULE$.javaBigInteger2bigInt(BigIntegerDeserializer.instance.deserialize(p, ctxt));
   }

   public Object getEmptyValue(final DeserializationContext ctxt) {
      return this.ZERO();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BigIntDeserializer$.class);
   }

   private BigIntDeserializer$() {
      super(BigInt.class);
   }
}
