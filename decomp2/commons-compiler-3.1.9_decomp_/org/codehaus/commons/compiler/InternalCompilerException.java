package org.codehaus.commons.compiler;

import org.codehaus.commons.nullanalysis.Nullable;

public class InternalCompilerException extends RuntimeException {
   public InternalCompilerException() {
   }

   public InternalCompilerException(@Nullable String message) {
      super(message);
   }

   public InternalCompilerException(@Nullable String message, Throwable t) {
      super(message, t);
   }

   public InternalCompilerException(@Nullable Location location, @Nullable String message) {
      super(location == null ? message : (message == null ? location.toString() : location + ": " + message));
   }

   public InternalCompilerException(@Nullable Location location, @Nullable String message, Throwable t) {
      super(location == null ? message : (message == null ? location.toString() : location + ": " + message), t);
   }
}
