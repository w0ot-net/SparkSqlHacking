package org.apache.spark.deploy.k8s.submit;

public final class Client$ {
   public static final Client$ MODULE$ = new Client$();

   public String submissionId(final String namespace, final String driverPodName) {
      return namespace + ":" + driverPodName;
   }

   private Client$() {
   }
}
