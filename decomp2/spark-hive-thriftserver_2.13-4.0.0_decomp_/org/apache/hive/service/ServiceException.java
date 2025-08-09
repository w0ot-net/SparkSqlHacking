package org.apache.hive.service;

public class ServiceException extends RuntimeException {
   public ServiceException(Throwable cause) {
      super(cause);
   }

   public ServiceException(String message) {
      super(message);
   }

   public ServiceException(String message, Throwable cause) {
      super(message, cause);
   }
}
