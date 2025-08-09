package com.fasterxml.jackson.module.scala;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

public final class ScalaObjectMapper$ {
   public static final ScalaObjectMapper$ MODULE$ = new ScalaObjectMapper$();

   public JsonMapper $colon$colon(final JsonMapper o) {
      return new ScalaObjectMapper.Mixin(o);
   }

   public ObjectMapper $colon$colon(final ObjectMapper o) {
      return new ScalaObjectMapper.ObjectMapperMixin(o);
   }

   private ScalaObjectMapper$() {
   }
}
