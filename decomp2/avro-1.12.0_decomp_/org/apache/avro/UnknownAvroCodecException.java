package org.apache.avro;

import java.io.IOException;

public class UnknownAvroCodecException extends IOException {
   public UnknownAvroCodecException(String message) {
      super(message);
   }
}
