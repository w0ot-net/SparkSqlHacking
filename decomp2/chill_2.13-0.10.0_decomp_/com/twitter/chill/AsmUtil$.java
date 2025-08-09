package com.twitter.chill;

import java.lang.invoke.SerializedLambda;
import org.apache.xbean.asm7.ClassReader;
import scala.Option;
import scala.util.Try.;

public final class AsmUtil$ {
   public static final AsmUtil$ MODULE$ = new AsmUtil$();

   public Option classReader(final Class cls) {
      String className = (new StringBuilder(6)).append(cls.getName().replaceFirst("^.*\\.", "")).append(".class").toString();
      return .MODULE$.apply(() -> new ClassReader(cls.getResourceAsStream(className))).toOption();
   }

   private AsmUtil$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
