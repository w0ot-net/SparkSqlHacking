package com.fasterxml.jackson.module.scala.experimental;

import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import scala.runtime.ModuleSerializationProxy;

/** @deprecated */
public final class DefaultRequiredAnnotationIntrospector$ extends NopAnnotationIntrospector {
   public static final DefaultRequiredAnnotationIntrospector$ MODULE$ = new DefaultRequiredAnnotationIntrospector$();

   public Boolean hasRequiredMarker(final AnnotatedMember m) {
      return com.fasterxml.jackson.module.scala.DefaultRequiredAnnotationIntrospector$.MODULE$.hasRequiredMarker(m);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DefaultRequiredAnnotationIntrospector$.class);
   }

   private DefaultRequiredAnnotationIntrospector$() {
   }
}
