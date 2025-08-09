package org.apache.arrow.vector.extension;

public class InvalidExtensionMetadataException extends RuntimeException {
   public InvalidExtensionMetadataException(String message) {
      super(message);
   }

   public InvalidExtensionMetadataException(String message, Throwable cause) {
      super(message, cause);
   }
}
