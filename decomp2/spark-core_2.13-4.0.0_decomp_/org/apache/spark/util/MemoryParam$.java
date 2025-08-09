package org.apache.spark.util;

import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.BoxesRunTime;

public final class MemoryParam$ {
   public static final MemoryParam$ MODULE$ = new MemoryParam$();

   public Option unapply(final String str) {
      Object var10000;
      try {
         var10000 = new Some(BoxesRunTime.boxToInteger(Utils$.MODULE$.memoryStringToMb(str)));
      } catch (NumberFormatException var3) {
         var10000 = .MODULE$;
      }

      return (Option)var10000;
   }

   private MemoryParam$() {
   }
}
