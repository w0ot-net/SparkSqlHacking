package org.apache.avro;

import java.io.IOException;

public class InvalidAvroMagicException extends IOException {
   public InvalidAvroMagicException(String message) {
      super(message);
   }
}
