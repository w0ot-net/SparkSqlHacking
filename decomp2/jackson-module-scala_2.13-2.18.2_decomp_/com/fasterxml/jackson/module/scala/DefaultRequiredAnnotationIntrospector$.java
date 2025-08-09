package com.fasterxml.jackson.module.scala;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Predef.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction0;

/** @deprecated */
public final class DefaultRequiredAnnotationIntrospector$ extends NopAnnotationIntrospector {
   public static final DefaultRequiredAnnotationIntrospector$ MODULE$ = new DefaultRequiredAnnotationIntrospector$();
   private static final Class OPTION = Option.class;
   private static final Class JSON_PROPERTY = JsonProperty.class;

   private Class OPTION() {
      return OPTION;
   }

   private Class JSON_PROPERTY() {
      return JSON_PROPERTY;
   }

   private boolean isOptionType(final Class cls) {
      return this.OPTION().isAssignableFrom(cls);
   }

   public Boolean hasRequiredMarker(final AnnotatedMember m) {
      return .MODULE$.boolean2Boolean(BoxesRunTime.unboxToBoolean(scala.Option..MODULE$.apply(m.getAnnotation(this.JSON_PROPERTY())).map((x$1) -> BoxesRunTime.boxToBoolean($anonfun$hasRequiredMarker$1(x$1))).getOrElse((JFunction0.mcZ.sp)() -> !MODULE$.isOptionType(m.getRawType()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DefaultRequiredAnnotationIntrospector$.class);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$hasRequiredMarker$1(final JsonProperty x$1) {
      return x$1.required();
   }

   private DefaultRequiredAnnotationIntrospector$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
