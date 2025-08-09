package org.apache.thrift.transport;

import java.io.IOException;
import java.io.OutputStream;

class TTransportOutputStream extends OutputStream {
   private TTransport transport = null;

   public TTransportOutputStream(TTransport transport) {
      this.transport = transport;
   }

   public void write(int b) throws IOException {
      try {
         this.transport.write(new byte[]{(byte)b});
      } catch (TTransportException e) {
         throw new IOException(e);
      }
   }

   public void write(byte[] b, int off, int len) throws IOException {
      try {
         this.transport.write(b, off, len);
      } catch (TTransportException e) {
         throw new IOException(e);
      }
   }

   public void flush() throws IOException {
      try {
         this.transport.flush();
      } catch (TTransportException e) {
         throw new IOException(e);
      }
   }
}
