package io.vertx.core.file;

import io.vertx.core.VertxException;

public class FileSystemException extends VertxException {
   public FileSystemException(String message) {
      super(message);
   }

   public FileSystemException(String message, Throwable cause) {
      super(message, cause);
   }

   public FileSystemException(Throwable cause) {
      super(cause);
   }
}
