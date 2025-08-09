package org.apache.spark.deploy;

public final class InProcessSparkSubmit$ {
   public static final InProcessSparkSubmit$ MODULE$ = new InProcessSparkSubmit$();

   public void main(final String[] args) {
      SparkSubmit submit = new SparkSubmit();
      submit.doSubmit(args);
   }

   private InProcessSparkSubmit$() {
   }
}
