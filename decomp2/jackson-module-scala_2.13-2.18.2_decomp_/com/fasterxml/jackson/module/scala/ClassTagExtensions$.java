package com.fasterxml.jackson.module.scala;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

public final class ClassTagExtensions$ {
   public static final ClassTagExtensions$ MODULE$ = new ClassTagExtensions$();

   public JsonMapper $colon$colon(final JsonMapper o) {
      return new ClassTagExtensions.Mixin(o);
   }

   public ObjectMapper $colon$colon(final ObjectMapper o) {
      return new ClassTagExtensions.ObjectMapperMixin(o);
   }

   private ClassTagExtensions$() {
   }
}
