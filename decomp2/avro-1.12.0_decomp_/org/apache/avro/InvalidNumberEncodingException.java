package org.apache.avro;

import java.io.IOException;

public class InvalidNumberEncodingException extends IOException {
   public InvalidNumberEncodingException(String message) {
      super(message);
   }
}
