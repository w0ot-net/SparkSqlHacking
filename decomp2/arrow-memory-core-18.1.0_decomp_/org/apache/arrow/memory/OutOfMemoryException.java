package org.apache.arrow.memory;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OutOfMemoryException extends RuntimeException {
   static final Logger logger = LoggerFactory.getLogger(OutOfMemoryException.class);
   private static final long serialVersionUID = -6858052345185793382L;
   private Optional outcomeDetails = Optional.empty();

   public OutOfMemoryException() {
   }

   public OutOfMemoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }

   public OutOfMemoryException(String message, Throwable cause) {
      super(message, cause);
   }

   public OutOfMemoryException(String message) {
      super(message);
   }

   public OutOfMemoryException(String message, Optional details) {
      super(message);
      this.outcomeDetails = details;
   }

   public OutOfMemoryException(Throwable cause) {
      super(cause);
   }

   public Optional getOutcomeDetails() {
      return this.outcomeDetails;
   }
}
