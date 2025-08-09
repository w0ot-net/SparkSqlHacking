package org.sparkproject.jetty.io;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.thread.Scheduler;

public class SocketChannelEndPoint extends SelectableChannelEndPoint {
   private static final Logger LOG = LoggerFactory.getLogger(SocketChannelEndPoint.class);

   public SocketChannelEndPoint(SocketChannel channel, ManagedSelector selector, SelectionKey key, Scheduler scheduler) {
      super(scheduler, channel, selector, key);
   }

   public SocketChannel getChannel() {
      return (SocketChannel)super.getChannel();
   }

   public SocketAddress getRemoteSocketAddress() {
      try {
         return this.getChannel().getRemoteAddress();
      } catch (Throwable x) {
         LOG.trace("Could not retrieve remote socket address", x);
         return null;
      }
   }

   protected void doShutdownOutput() {
      try {
         this.getChannel().shutdownOutput();
      } catch (Throwable x) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Could not shutdown output for {}", this.getChannel(), x);
         }
      }

   }

   public int fill(ByteBuffer buffer) throws IOException {
      if (this.isInputShutdown()) {
         return -1;
      } else {
         int pos = BufferUtil.flipToFill(buffer);

         int filled;
         try {
            filled = this.getChannel().read(buffer);
            if (filled > 0) {
               this.notIdle();
            } else if (filled == -1) {
               this.shutdownInput();
            }
         } catch (IOException e) {
            LOG.debug("Unable to shutdown output", e);
            this.shutdownInput();
            filled = -1;
         } finally {
            BufferUtil.flipToFlush(buffer, pos);
         }

         if (LOG.isDebugEnabled()) {
            LOG.debug("filled {} {}", filled, BufferUtil.toDetailString(buffer));
         }

         return filled;
      }
   }

   public boolean flush(ByteBuffer... buffers) throws IOException {
      long flushed;
      try {
         flushed = this.getChannel().write(buffers);
         if (LOG.isDebugEnabled()) {
            LOG.debug("flushed {} {}", flushed, this);
         }
      } catch (IOException e) {
         throw new EofException(e);
      }

      if (flushed > 0L) {
         this.notIdle();
      }

      for(ByteBuffer b : buffers) {
         if (!BufferUtil.isEmpty(b)) {
            return false;
         }
      }

      return true;
   }
}
