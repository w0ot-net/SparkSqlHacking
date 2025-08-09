package org.apache.avro.ipc;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatagramTransceiver extends Transceiver {
   private static final Logger LOG = LoggerFactory.getLogger(DatagramTransceiver.class);
   private static final int MAX_SIZE = 16384;
   private DatagramChannel channel;
   private SocketAddress remote;
   private ByteBuffer buffer;

   public String getRemoteName() {
      return this.remote.toString();
   }

   public DatagramTransceiver(SocketAddress remote) throws IOException {
      this(DatagramChannel.open());
      this.remote = remote;
   }

   public DatagramTransceiver(DatagramChannel channel) {
      this.buffer = ByteBuffer.allocate(16384);
      this.channel = channel;
   }

   public synchronized List readBuffers() throws IOException {
      this.buffer.clear();
      this.remote = this.channel.receive(this.buffer);
      LOG.info("received from " + String.valueOf(this.remote));
      this.buffer.flip();
      List<ByteBuffer> buffers = new ArrayList();

      while(true) {
         int length = this.buffer.getInt();
         if (length == 0) {
            return buffers;
         }

         ByteBuffer chunk = this.buffer.slice();
         ((Buffer)chunk).limit(length);
         this.buffer.position(this.buffer.position() + length);
         buffers.add(chunk);
      }
   }

   public synchronized void writeBuffers(List buffers) throws IOException {
      this.buffer.clear();

      for(ByteBuffer b : buffers) {
         this.buffer.putInt(b.remaining());
         this.buffer.put(b);
      }

      this.buffer.putInt(0);
      this.buffer.flip();
      this.channel.send(this.buffer, this.remote);
      LOG.info("sent to " + String.valueOf(this.remote));
   }
}
