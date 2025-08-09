package org.sparkproject.jetty.util;

public class StaticException extends Exception {
   public StaticException(String message) {
      this(message, false);
   }

   public StaticException(String message, boolean writableStackTrace) {
      super(message, (Throwable)null, false, writableStackTrace);
   }
}
