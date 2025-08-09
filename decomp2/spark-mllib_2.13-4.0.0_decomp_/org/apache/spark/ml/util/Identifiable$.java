package org.apache.spark.ml.util;

import java.util.UUID;
import scala.collection.StringOps.;

public final class Identifiable$ {
   public static final Identifiable$ MODULE$ = new Identifiable$();

   public String randomUID(final String prefix) {
      return prefix + "_" + .MODULE$.takeRight$extension(scala.Predef..MODULE$.augmentString(UUID.randomUUID().toString()), 12);
   }

   private Identifiable$() {
   }
}
