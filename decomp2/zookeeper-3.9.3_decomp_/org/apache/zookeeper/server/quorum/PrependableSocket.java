package org.apache.zookeeper.server.quorum;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.net.Socket;
import java.net.SocketImpl;

public class PrependableSocket extends Socket {
   private PushbackInputStream pushbackInputStream;

   public PrependableSocket(SocketImpl base) throws IOException {
      super(base);
   }

   public InputStream getInputStream() throws IOException {
      return (InputStream)(this.pushbackInputStream == null ? super.getInputStream() : this.pushbackInputStream);
   }

   public void prependToInputStream(byte[] bytes, int offset, int length) throws IOException {
      if (length != 0) {
         if (this.pushbackInputStream != null) {
            throw new IOException("prependToInputStream() called more than once");
         } else {
            PushbackInputStream pushbackInputStream = new PushbackInputStream(this.getInputStream(), length);
            pushbackInputStream.unread(bytes, offset, length);
            this.pushbackInputStream = pushbackInputStream;
         }
      }
   }
}
