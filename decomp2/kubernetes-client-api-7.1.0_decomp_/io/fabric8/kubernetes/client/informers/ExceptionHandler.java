package io.fabric8.kubernetes.client.informers;

import com.fasterxml.jackson.core.JacksonException;

public interface ExceptionHandler {
   boolean retryAfterException(boolean var1, Throwable var2);

   static boolean isDeserializationException(Throwable t) {
      while(true) {
         if (!(t instanceof ClassCastException) && !(t instanceof JacksonException)) {
            Throwable cause = t.getCause();
            if (cause != t && cause != null) {
               t = cause;
               continue;
            }

            return false;
         }

         return true;
      }
   }
}
