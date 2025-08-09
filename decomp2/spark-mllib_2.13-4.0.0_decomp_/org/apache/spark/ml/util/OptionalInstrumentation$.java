package org.apache.spark.ml.util;

import scala.Some;
import scala.None.;

public final class OptionalInstrumentation$ {
   public static final OptionalInstrumentation$ MODULE$ = new OptionalInstrumentation$();

   public OptionalInstrumentation create(final Instrumentation instr) {
      return new OptionalInstrumentation(new Some(instr), instr.prefix());
   }

   public OptionalInstrumentation create(final Class clazz) {
      return new OptionalInstrumentation(.MODULE$, scala.collection.StringOps..MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(clazz.getName()), "$"));
   }

   private OptionalInstrumentation$() {
   }
}
