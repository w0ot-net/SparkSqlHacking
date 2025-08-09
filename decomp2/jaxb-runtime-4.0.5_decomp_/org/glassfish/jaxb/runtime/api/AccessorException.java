package org.glassfish.jaxb.runtime.api;

public final class AccessorException extends Exception {
   private static final long serialVersionUID = 3825830567556994999L;

   public AccessorException() {
   }

   public AccessorException(String message) {
      super(message);
   }

   public AccessorException(String message, Throwable cause) {
      super(message, cause);
   }

   public AccessorException(Throwable cause) {
      super(cause);
   }
}
