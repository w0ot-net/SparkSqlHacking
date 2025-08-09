package org.apache.orc;

import java.io.IOException;

public class FileFormatException extends IOException {
   public FileFormatException(String errMsg) {
      super(errMsg);
   }
}
