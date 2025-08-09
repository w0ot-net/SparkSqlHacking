package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import scala.Symbol;
import scala.Symbol.;
import scala.runtime.ModuleSerializationProxy;

public final class SymbolDeserializer$ extends StdDeserializer {
   public static final SymbolDeserializer$ MODULE$ = new SymbolDeserializer$();

   public Symbol deserialize(final JsonParser p, final DeserializationContext ctxt) {
      return .MODULE$.apply(p.getValueAsString());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SymbolDeserializer$.class);
   }

   private SymbolDeserializer$() {
      super(Symbol.class);
   }
}
