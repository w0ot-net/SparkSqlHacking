package org.codehaus.commons.compiler;

import org.codehaus.commons.nullanalysis.Nullable;

public class LocatedException extends Exception {
   @Nullable
   private final Location location;

   public LocatedException(String message, @Nullable Location location) {
      super(message);
      this.location = location;
   }

   public LocatedException(String message, @Nullable Location location, @Nullable Throwable cause) {
      super(message, cause);
      this.location = location;
   }

   public String getMessage() {
      return this.location != null ? this.location.toString() + ": " + super.getMessage() : super.getMessage();
   }

   @Nullable
   public Location getLocation() {
      return this.location;
   }
}
