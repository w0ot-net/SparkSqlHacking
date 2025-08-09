package io.fabric8.kubernetes.client;

public class ResourceNotFoundException extends RuntimeException {
   public ResourceNotFoundException(String s) {
      super(s);
   }

   public ResourceNotFoundException(String s, Throwable cause) {
      super(s, cause);
   }
}
