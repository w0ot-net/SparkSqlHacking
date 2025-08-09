package io.fabric8.kubernetes.client;

import java.util.Optional;

public class WatcherException extends Exception {
   private final String rawWatchMessage;

   public WatcherException(String message, Throwable cause) {
      this(message, cause, (String)null);
   }

   public WatcherException(String message) {
      super(message);
      this.rawWatchMessage = null;
   }

   public WatcherException(String message, Throwable cause, String rawWatchMessage) {
      super(message, cause);
      this.rawWatchMessage = rawWatchMessage;
   }

   public KubernetesClientException asClientException() {
      Throwable cause = this.getCause();
      return cause instanceof KubernetesClientException ? (KubernetesClientException)cause : new KubernetesClientException(this.getMessage(), cause);
   }

   public boolean isHttpGone() {
      KubernetesClientException cause = this.asClientException();
      return cause != null && (cause.getCode() == 410 || cause.getStatus() != null && cause.getStatus().getCode() == 410);
   }

   public Optional getRawWatchMessage() {
      return Optional.ofNullable(this.rawWatchMessage);
   }
}
