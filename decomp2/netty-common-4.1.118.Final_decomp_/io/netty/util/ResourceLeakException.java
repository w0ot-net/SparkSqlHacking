package io.netty.util;

import java.util.Arrays;

/** @deprecated */
@Deprecated
public class ResourceLeakException extends RuntimeException {
   private static final long serialVersionUID = 7186453858343358280L;
   private final StackTraceElement[] cachedStackTrace = this.getStackTrace();

   public ResourceLeakException() {
   }

   public ResourceLeakException(String message) {
      super(message);
   }

   public ResourceLeakException(String message, Throwable cause) {
      super(message, cause);
   }

   public ResourceLeakException(Throwable cause) {
      super(cause);
   }

   public int hashCode() {
      int hashCode = 0;

      for(StackTraceElement e : this.cachedStackTrace) {
         hashCode = hashCode * 31 + e.hashCode();
      }

      return hashCode;
   }

   public boolean equals(Object o) {
      if (!(o instanceof ResourceLeakException)) {
         return false;
      } else {
         return o == this ? true : Arrays.equals(this.cachedStackTrace, ((ResourceLeakException)o).cachedStackTrace);
      }
   }
}
