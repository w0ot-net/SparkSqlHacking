package org.apache.spark.util.collection;

import org.apache.spark.SparkEnv$;
import org.apache.spark.serializer.Serializer;
import scala.None;
import scala.Option;
import scala.None.;

public final class ExternalSorter$ {
   public static final ExternalSorter$ MODULE$ = new ExternalSorter$();

   public None $lessinit$greater$default$2() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$3() {
      return .MODULE$;
   }

   public None $lessinit$greater$default$4() {
      return .MODULE$;
   }

   public Serializer $lessinit$greater$default$5() {
      return SparkEnv$.MODULE$.get().serializer();
   }

   private ExternalSorter$() {
   }
}
