package org.sparkproject.jetty.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.thread.Scheduler;

public class NetworkTrafficSocketChannelEndPoint extends SocketChannelEndPoint {
   private static final Logger LOG = LoggerFactory.getLogger(NetworkTrafficSocketChannelEndPoint.class);
   private final NetworkTrafficListener listener;

   public NetworkTrafficSocketChannelEndPoint(SocketChannel channel, ManagedSelector selectSet, SelectionKey key, Scheduler scheduler, long idleTimeout, NetworkTrafficListener listener) {
      super(channel, selectSet, key, scheduler);
      this.setIdleTimeout(idleTimeout);
      this.listener = listener;
   }

   public int fill(ByteBuffer buffer) throws IOException {
      int read = super.fill(buffer);
      this.notifyIncoming(buffer, read);
      return read;
   }

   public boolean flush(ByteBuffer... buffers) throws IOException {
      boolean flushed = true;

      for(ByteBuffer b : buffers) {
         if (b.hasRemaining()) {
            int position = b.position();
            ByteBuffer view = b.slice();
            flushed = super.flush(b);
            int l = b.position() - position;
            view.limit(view.position() + l);
            this.notifyOutgoing(view);
            if (!flushed) {
               break;
            }
         }
      }

      return flushed;
   }

   public void onOpen() {
      super.onOpen();
      if (this.listener != null) {
         try {
            this.listener.opened(this.getChannel().socket());
         } catch (Throwable x) {
            LOG.info("Exception while invoking listener {}", this.listener, x);
         }
      }

   }

   public void onClose(Throwable failure) {
      super.onClose(failure);
      if (this.listener != null) {
         try {
            this.listener.closed(this.getChannel().socket());
         } catch (Throwable x) {
            LOG.info("Exception while invoking listener {}", this.listener, x);
         }
      }

   }

   public void notifyIncoming(ByteBuffer buffer, int read) {
      if (this.listener != null && read > 0) {
         try {
            ByteBuffer view = buffer.asReadOnlyBuffer();
            this.listener.incoming(this.getChannel().socket(), view);
         } catch (Throwable x) {
            LOG.info("Exception while invoking listener {}", this.listener, x);
         }
      }

   }

   public void notifyOutgoing(ByteBuffer view) {
      if (this.listener != null && view.hasRemaining()) {
         try {
            this.listener.outgoing(this.getChannel().socket(), view);
         } catch (Throwable x) {
            LOG.info("Exception while invoking listener {}", this.listener, x);
         }
      }

   }
}
