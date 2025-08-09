package org.apache.spark.metrics.source;

import scala.collection.immutable.;
import scala.collection.immutable.Seq;

public final class StaticSources$ {
   public static final StaticSources$ MODULE$ = new StaticSources$();
   private static final Seq allSources;

   static {
      allSources = new .colon.colon(CodegenMetrics$.MODULE$, new .colon.colon(HiveCatalogMetrics$.MODULE$, scala.collection.immutable.Nil..MODULE$));
   }

   public Seq allSources() {
      return allSources;
   }

   private StaticSources$() {
   }
}
