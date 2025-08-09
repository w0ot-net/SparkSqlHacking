package org.apache.commons.io.input;

import java.io.IOException;

class Input {
   static void checkOpen(boolean isOpen) throws IOException {
      if (!isOpen) {
         throw new IOException("Closed");
      }
   }
}
