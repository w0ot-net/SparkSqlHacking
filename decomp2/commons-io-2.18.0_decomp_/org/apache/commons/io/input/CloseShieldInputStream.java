package org.apache.commons.io.input;

import java.io.InputStream;

public class CloseShieldInputStream extends ProxyInputStream {
   public static InputStream systemIn(InputStream inputStream) {
      return (InputStream)(inputStream == System.in ? wrap(inputStream) : inputStream);
   }

   public static CloseShieldInputStream wrap(InputStream inputStream) {
      return new CloseShieldInputStream(inputStream);
   }

   /** @deprecated */
   @Deprecated
   public CloseShieldInputStream(InputStream inputStream) {
      super(inputStream);
   }

   public void close() {
      this.in = ClosedInputStream.INSTANCE;
   }
}
