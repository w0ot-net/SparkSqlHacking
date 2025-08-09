package org.apache.spark.deploy.worker;

import scala.collection.immutable.Seq;
import scala.jdk.CollectionConverters.;

public final class ProcessBuilderLike$ {
   public static final ProcessBuilderLike$ MODULE$ = new ProcessBuilderLike$();

   public ProcessBuilderLike apply(final ProcessBuilder processBuilder) {
      return new ProcessBuilderLike(processBuilder) {
         private final ProcessBuilder processBuilder$1;

         public Process start() {
            return this.processBuilder$1.start();
         }

         public Seq command() {
            return .MODULE$.ListHasAsScala(this.processBuilder$1.command()).asScala().toSeq();
         }

         public {
            this.processBuilder$1 = processBuilder$1;
         }
      };
   }

   private ProcessBuilderLike$() {
   }
}
