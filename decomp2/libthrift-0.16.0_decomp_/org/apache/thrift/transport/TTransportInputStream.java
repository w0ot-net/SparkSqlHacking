package org.apache.thrift.transport;

import java.io.IOException;
import java.io.InputStream;

class TTransportInputStream extends InputStream {
   private TTransport transport = null;

   public TTransportInputStream(TTransport transport) {
      this.transport = transport;
   }

   public int read() throws IOException {
      try {
         byte[] buf = new byte[1];
         this.transport.read(buf, 0, 1);
         return buf[0];
      } catch (TTransportException e) {
         throw new IOException(e);
      }
   }

   public int read(byte[] b, int off, int len) throws IOException {
      try {
         return this.transport.read(b, off, len);
      } catch (TTransportException e) {
         throw new IOException(e);
      }
   }
}
