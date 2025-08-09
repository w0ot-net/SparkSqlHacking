package org.apache.spark.util;

import scala.Option;
import scala.Some;
import scala.collection.StringOps.;
import scala.runtime.BoxesRunTime;

public final class IntParam$ {
   public static final IntParam$ MODULE$ = new IntParam$();

   public Option unapply(final String str) {
      Object var10000;
      try {
         var10000 = new Some(BoxesRunTime.boxToInteger(.MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(str))));
      } catch (NumberFormatException var3) {
         var10000 = scala.None..MODULE$;
      }

      return (Option)var10000;
   }

   private IntParam$() {
   }
}
