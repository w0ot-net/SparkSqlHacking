package org.apache.commons.compress.compressors.pack200;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

abstract class AbstractStreamBridge extends FilterOutputStream {
   private InputStream inputStream;
   private final Object inputStreamLock;

   protected AbstractStreamBridge() {
      this((OutputStream)null);
   }

   protected AbstractStreamBridge(OutputStream outputStream) {
      super(outputStream);
      this.inputStreamLock = new Object();
   }

   abstract InputStream createInputStream() throws IOException;

   InputStream getInputStream() throws IOException {
      synchronized(this.inputStreamLock) {
         if (this.inputStream == null) {
            this.inputStream = this.createInputStream();
         }
      }

      return this.inputStream;
   }

   void stop() throws IOException {
      this.close();
      synchronized(this.inputStreamLock) {
         if (this.inputStream != null) {
            this.inputStream.close();
            this.inputStream = null;
         }

      }
   }
}
