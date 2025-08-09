package org.apache.avro.ipc;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.avro.Protocol;

public abstract class Transceiver implements Closeable {
   private final ReentrantLock channelLock = new ReentrantLock();

   public abstract String getRemoteName() throws IOException;

   public void lockChannel() {
      this.channelLock.lock();
   }

   public void unlockChannel() {
      if (this.channelLock.isHeldByCurrentThread()) {
         this.channelLock.unlock();
      }

   }

   public List transceive(List request) throws IOException {
      this.lockChannel();

      List var2;
      try {
         this.writeBuffers(request);
         var2 = this.readBuffers();
      } finally {
         this.unlockChannel();
      }

      return var2;
   }

   public void transceive(List request, Callback callback) throws IOException {
      try {
         List<ByteBuffer> response = this.transceive(request);
         callback.handleResult(response);
      } catch (IOException e) {
         callback.handleError(e);
      }

   }

   public abstract List readBuffers() throws IOException;

   public abstract void writeBuffers(List buffers) throws IOException;

   public boolean isConnected() {
      return false;
   }

   public void setRemote(Protocol protocol) {
   }

   public Protocol getRemote() {
      throw new IllegalStateException("Not connected.");
   }

   public void close() throws IOException {
   }
}
