package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.KeyDeserializers;
import scala.Enumeration;
import scala.None.;

public final class EnumerationKeyDeserializers$ implements KeyDeserializers {
   public static final EnumerationKeyDeserializers$ MODULE$ = new EnumerationKeyDeserializers$();
   private static final Class ENUMERATION = Enumeration.Value.class;

   private Class ENUMERATION() {
      return ENUMERATION;
   }

   public KeyDeserializer findKeyDeserializer(final JavaType tp, final DeserializationConfig cfg, final BeanDescription desc) {
      return (KeyDeserializer)(this.ENUMERATION().isAssignableFrom(tp.getRawClass()) ? new EnumerationKeyDeserializer(.MODULE$) : (KeyDeserializer).MODULE$.orNull(scala..less.colon.less..MODULE$.refl()));
   }

   private EnumerationKeyDeserializers$() {
   }
}
