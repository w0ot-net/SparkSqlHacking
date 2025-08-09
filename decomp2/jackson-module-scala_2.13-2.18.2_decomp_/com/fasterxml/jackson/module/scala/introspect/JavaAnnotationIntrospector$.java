package com.fasterxml.jackson.module.scala.introspect;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.fasterxml.jackson.module.scala.JacksonModule$;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import scala.Option;
import scala.None.;
import scala.collection.immutable.IndexedSeq;
import scala.runtime.ModuleSerializationProxy;

public final class JavaAnnotationIntrospector$ extends NopAnnotationIntrospector {
   public static final JavaAnnotationIntrospector$ MODULE$ = new JavaAnnotationIntrospector$();

   public PropertyName findNameForDeserialization(final Annotated a) {
      return (PropertyName).MODULE$.orNull(scala..less.colon.less..MODULE$.refl());
   }

   public String findImplicitPropertyName(final AnnotatedMember param) {
      Object var10000;
      label36: {
         if (param instanceof AnnotatedParameter) {
            AnnotatedParameter var6 = (AnnotatedParameter)param;
            if (ScalaAnnotationIntrospector$.MODULE$.isMaybeScalaBeanType(var6.getDeclaringClass())) {
               int index = var6.getIndex();
               AnnotatedWithParams owner = var6.getOwner();
               AnnotatedElement var9 = owner.getAnnotated();
               if (var9 instanceof Constructor) {
                  Constructor var10 = (Constructor)var9;
                  IndexedSeq names = JavaParameterIntrospector$.MODULE$.getCtorParamNames(var10);
                  var10000 = index < names.length() ? scala.Option..MODULE$.apply(names.apply(index)) : .MODULE$;
               } else if (var9 instanceof Method) {
                  Method var12 = (Method)var9;
                  IndexedSeq names = JavaParameterIntrospector$.MODULE$.getMethodParamNames(var12);
                  var10000 = index < names.length() ? scala.Option..MODULE$.apply(names.apply(index)) : .MODULE$;
               } else if (var9 instanceof Field) {
                  Field var14 = (Field)var9;
                  var10000 = scala.Option..MODULE$.apply(JavaParameterIntrospector$.MODULE$.getFieldName(var14));
               } else if (var9 instanceof Parameter) {
                  Parameter var15 = (Parameter)var9;
                  var10000 = scala.Option..MODULE$.apply(JavaParameterIntrospector$.MODULE$.getParameterName(var15));
               } else {
                  var10000 = .MODULE$;
               }
               break label36;
            }
         }

         var10000 = .MODULE$;
      }

      Option result = (Option)var10000;
      return (String)result.map((name0) -> scala.reflect.NameTransformer..MODULE$.decode(name0)).getOrElse(() -> (String).MODULE$.orNull(scala..less.colon.less..MODULE$.refl()));
   }

   public Version version() {
      return JacksonModule$.MODULE$.version();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JavaAnnotationIntrospector$.class);
   }

   private JavaAnnotationIntrospector$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
