package org.sparkproject.jetty.server;

import jakarta.servlet.ServletException;
import org.sparkproject.jetty.io.QuietException;

public class QuietServletException extends ServletException implements QuietException {
   public QuietServletException() {
   }

   public QuietServletException(String message, Throwable rootCause) {
      super(message, rootCause);
   }

   public QuietServletException(String message) {
      super(message);
   }

   public QuietServletException(Throwable rootCause) {
      super(rootCause);
   }
}
