package com.fasterxml.jackson.module.scala.util;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.module.scala.JsonScalaEnumeration;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import scala.Enumeration;
import scala.Option;
import scala.Tuple2;
import scala.Option.;
import scala.collection.immutable.Map;

public final class EnumResolver$ {
   public static final EnumResolver$ MODULE$ = new EnumResolver$();

   public Option apply(final BeanProperty property) {
      return .MODULE$.apply(property).flatMap((p) -> .MODULE$.apply(p.getAnnotation(JsonScalaEnumeration.class))).map((a) -> MODULE$.apply(a));
   }

   public EnumResolver apply(final JsonScalaEnumeration a) {
      ParameterizedType pt = (ParameterizedType)a.value().getGenericSuperclass();
      Type[] args = pt.getActualTypeArguments();
      return this.apply((Class)args[0]);
   }

   public EnumResolver apply(final Class cls) {
      Enumeration enumInstance = (Enumeration)cls.getField("MODULE$").get((Object)null);
      return this.apply(enumInstance);
   }

   public EnumResolver apply(final Enumeration e) {
      Enumeration.ValueSet valueSet = e.values();
      Map map = valueSet.iterator().map((v) -> new Tuple2(v.toString(), v)).toMap(scala..less.colon.less..MODULE$.refl());
      return new EnumResolver(e.getClass(), valueSet, map);
   }

   private EnumResolver$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
