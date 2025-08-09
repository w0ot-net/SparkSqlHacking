package org.apache.avro.ipc;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import org.apache.avro.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @deprecated */
public class SocketTransceiver extends Transceiver {
   private static final Logger LOG = LoggerFactory.getLogger(SocketTransceiver.class);
   private SocketChannel channel;
   private ByteBuffer header;
   private Protocol remote;

   public SocketTransceiver(SocketAddress address) throws IOException {
      this(SocketChannel.open(address));
   }

   public SocketTransceiver(SocketChannel channel) throws IOException {
      this.header = ByteBuffer.allocate(4);
      this.channel = channel;
      this.channel.socket().setTcpNoDelay(true);
      LOG.info("open to " + this.getRemoteName());
   }

   public String getRemoteName() {
      return this.channel.socket().getRemoteSocketAddress().toString();
   }

   public synchronized List readBuffers() throws IOException {
      List<ByteBuffer> buffers = new ArrayList();

      while(true) {
         this.header.clear();

         while(this.header.hasRemaining()) {
            if (this.channel.read(this.header) < 0) {
               throw new ClosedChannelException();
            }
         }

         this.header.flip();
         int length = this.header.getInt();
         if (length == 0) {
            return buffers;
         }

         ByteBuffer buffer = ByteBuffer.allocate(length);

         while(buffer.hasRemaining()) {
            if (this.channel.read(buffer) < 0) {
               throw new ClosedChannelException();
            }
         }

         ((Buffer)buffer).flip();
         buffers.add(buffer);
      }
   }

   public synchronized void writeBuffers(List buffers) throws IOException {
      if (buffers != null) {
         for(ByteBuffer buffer : buffers) {
            if (buffer.limit() != 0) {
               this.writeLength(buffer.limit());
               this.channel.write(buffer);
            }
         }

         this.writeLength(0);
      }
   }

   private void writeLength(int length) throws IOException {
      this.header.clear();
      this.header.putInt(length);
      this.header.flip();
      this.channel.write(this.header);
   }

   public boolean isConnected() {
      return this.remote != null;
   }

   public void setRemote(Protocol remote) {
      this.remote = remote;
   }

   public Protocol getRemote() {
      return this.remote;
   }

   public void close() throws IOException {
      if (this.channel.isOpen()) {
         LOG.info("closing to " + this.getRemoteName());
         this.channel.close();
      }

   }
}
