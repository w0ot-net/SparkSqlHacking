package com.twitter.chill;

import scala.Option;
import scala.Some;
import scala.collection.Iterable;
import scala.collection.JavaConverters.;

public final class JavaIterableWrapperSerializer$ {
   public static final JavaIterableWrapperSerializer$ MODULE$ = new JavaIterableWrapperSerializer$();
   private static final Class wrapperClass;
   private static final Option com$twitter$chill$JavaIterableWrapperSerializer$$underlyingMethodOpt;

   static {
      wrapperClass = .MODULE$.asJavaIterableConverter((Iterable)scala.package..MODULE$.Seq().empty()).asJava().getClass();
      com$twitter$chill$JavaIterableWrapperSerializer$$underlyingMethodOpt = MODULE$.liftedTree1$1();
   }

   public Class wrapperClass() {
      return wrapperClass;
   }

   public Option com$twitter$chill$JavaIterableWrapperSerializer$$underlyingMethodOpt() {
      return com$twitter$chill$JavaIterableWrapperSerializer$$underlyingMethodOpt;
   }

   // $FF: synthetic method
   private final Option liftedTree1$1() {
      Object var10000;
      try {
         var10000 = new Some(this.wrapperClass().getDeclaredMethod("underlying"));
      } catch (Exception var2) {
         var10000 = scala.None..MODULE$;
      }

      return (Option)var10000;
   }

   private JavaIterableWrapperSerializer$() {
   }
}
