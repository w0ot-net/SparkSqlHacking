package org.json4s;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.json4s.reflect.ScalaSigReader$;
import scala.Option;
import scala.collection.mutable.Builder;
import scala.runtime.StructuralCallSite;
import scala.runtime.ScalaRunTime.;

public final class Compat$ {
   public static final Compat$ MODULE$ = new Compat$();

   public static Method reflMethod$Method1(final Class x$1) {
      StructuralCallSite methodCache1 = apply<invokedynamic>();
      Method method1 = methodCache1.find(x$1);
      if (method1 != null) {
         return method1;
      } else {
         method1 = .MODULE$.ensureAccessible(x$1.getMethod("newBuilder", methodCache1.parameterTypes()));
         methodCache1.add(x$1, method1);
         return method1;
      }
   }

   public Option makeCollection(final Class clazz, final Object array) {
      return ScalaSigReader$.MODULE$.companions(clazz.getName(), ScalaSigReader$.MODULE$.companions$default$2(), ScalaSigReader$.MODULE$.companions$default$3()).flatMap((x$1) -> (Option)x$1._2()).map((c) -> {
         Object qual1 = c;

         Object var10000;
         try {
            var10000 = reflMethod$Method1(qual1.getClass()).invoke(qual1);
         } catch (InvocationTargetException var5) {
            throw var5.getCause();
         }

         Builder builder = (Builder)var10000;
         builder.$plus$plus$eq(scala.Predef..MODULE$.genericWrapArray(array));
         return builder.result();
      });
   }

   private Compat$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
