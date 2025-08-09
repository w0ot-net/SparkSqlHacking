package org.codehaus.commons.compiler;

import org.codehaus.commons.nullanalysis.Nullable;

public class CompileException extends LocatedException {
   public CompileException(String message, @Nullable Location location) {
      super(message, location);
   }

   public CompileException(String message, @Nullable Location location, Throwable cause) {
      super(message, location, cause);
   }
}
