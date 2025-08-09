package org.apache.hive.service.auth;

public class HttpAuthenticationException extends Exception {
   private static final long serialVersionUID = 0L;

   public HttpAuthenticationException(Throwable cause) {
      super(cause);
   }

   public HttpAuthenticationException(String msg) {
      super(msg);
   }

   public HttpAuthenticationException(String msg, Throwable cause) {
      super(msg, cause);
   }
}
