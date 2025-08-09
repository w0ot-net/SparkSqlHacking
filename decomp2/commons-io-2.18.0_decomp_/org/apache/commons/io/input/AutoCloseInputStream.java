package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;

public class AutoCloseInputStream extends ProxyInputStream {
   public static Builder builder() {
      return new Builder();
   }

   private AutoCloseInputStream(Builder builder) throws IOException {
      super((ProxyInputStream.AbstractBuilder)builder);
   }

   /** @deprecated */
   @Deprecated
   public AutoCloseInputStream(InputStream in) {
      super(ClosedInputStream.ifNull(in));
   }

   protected void afterRead(int n) throws IOException {
      if (n == -1) {
         this.close();
      }

      super.afterRead(n);
   }

   public void close() throws IOException {
      super.close();
      this.in = ClosedInputStream.INSTANCE;
   }

   protected void finalize() throws Throwable {
      this.close();
      super.finalize();
   }

   public static class Builder extends ProxyInputStream.AbstractBuilder {
      public AutoCloseInputStream get() throws IOException {
         return new AutoCloseInputStream(this);
      }
   }
}
