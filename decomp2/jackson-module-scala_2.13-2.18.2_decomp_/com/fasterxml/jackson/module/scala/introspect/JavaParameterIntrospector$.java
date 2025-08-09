package com.fasterxml.jackson.module.scala.introspect;

import com.thoughtworks.paranamer.BytecodeReadingParanamer;
import com.thoughtworks.paranamer.CachingParanamer;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import scala.collection.ArrayOps.;
import scala.collection.immutable.IndexedSeq;
import scala.runtime.Null;

public final class JavaParameterIntrospector$ {
   public static final JavaParameterIntrospector$ MODULE$ = new JavaParameterIntrospector$();
   private static final CachingParanamer paranamer = new CachingParanamer(new BytecodeReadingParanamer());

   private CachingParanamer paranamer() {
      return paranamer;
   }

   public IndexedSeq getCtorParamNames(final Constructor ctor) {
      return .MODULE$.toIndexedSeq$extension(scala.Predef..MODULE$.refArrayOps(scala.util.Try..MODULE$.apply(() -> MODULE$.paranamer().lookupParameterNames(ctor, false)).getOrElse(() -> (String[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])ctor.getParameters()), (x$1) -> x$1.getName(), scala.reflect.ClassTag..MODULE$.apply(String.class)))));
   }

   public IndexedSeq getMethodParamNames(final Method mtd) {
      return .MODULE$.toIndexedSeq$extension(scala.Predef..MODULE$.refArrayOps(scala.util.Try..MODULE$.apply(() -> MODULE$.paranamer().lookupParameterNames(mtd, false)).getOrElse(() -> (String[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])mtd.getParameters()), (x$2) -> x$2.getName(), scala.reflect.ClassTag..MODULE$.apply(String.class)))));
   }

   public String getFieldName(final Field field) {
      return (String)scala.util.Try..MODULE$.apply(() -> (String).MODULE$.headOption$extension(scala.Predef..MODULE$.refArrayOps((Object[])MODULE$.paranamer().lookupParameterNames(field, false))).getOrElse(() -> (Null)scala.None..MODULE$.orNull(scala..less.colon.less..MODULE$.refl()))).getOrElse(() -> field.getName());
   }

   public String getParameterName(final Parameter parameter) {
      return parameter.getName();
   }

   private JavaParameterIntrospector$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
