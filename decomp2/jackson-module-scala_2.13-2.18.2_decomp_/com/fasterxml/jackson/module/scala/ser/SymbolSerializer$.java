package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import scala.Symbol;

public final class SymbolSerializer$ extends JsonSerializer {
   public static final SymbolSerializer$ MODULE$ = new SymbolSerializer$();

   public void serialize(final Symbol value, final JsonGenerator jgen, final SerializerProvider provider) {
      jgen.writeString(value.name());
   }

   private SymbolSerializer$() {
   }
}
