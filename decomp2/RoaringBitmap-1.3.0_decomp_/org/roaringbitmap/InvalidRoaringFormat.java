package org.roaringbitmap;

import java.io.IOException;

public class InvalidRoaringFormat extends RuntimeException {
   private static final long serialVersionUID = 1L;

   public InvalidRoaringFormat(String string) {
      super(string);
   }

   public IOException toIOException() {
      return new IOException(this.toString());
   }
}
