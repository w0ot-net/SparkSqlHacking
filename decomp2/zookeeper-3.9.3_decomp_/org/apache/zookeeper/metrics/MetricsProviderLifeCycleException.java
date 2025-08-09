package org.apache.zookeeper.metrics;

public class MetricsProviderLifeCycleException extends Exception {
   private static final long serialVersionUID = 1L;

   public MetricsProviderLifeCycleException() {
   }

   public MetricsProviderLifeCycleException(String message) {
      super(message);
   }

   public MetricsProviderLifeCycleException(String message, Throwable cause) {
      super(message, cause);
   }

   public MetricsProviderLifeCycleException(Throwable cause) {
      super(cause);
   }
}
