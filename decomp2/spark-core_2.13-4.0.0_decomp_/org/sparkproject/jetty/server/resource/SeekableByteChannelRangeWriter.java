package org.sparkproject.jetty.server.resource;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import org.sparkproject.jetty.util.BufferUtil;

public class SeekableByteChannelRangeWriter implements RangeWriter {
   public static final int NO_PROGRESS_LIMIT = 3;
   private final ChannelSupplier channelSupplier;
   private final int bufSize;
   private final ByteBuffer buffer;
   private SeekableByteChannel channel;
   private long pos;
   private boolean defaultSeekMode;

   public SeekableByteChannelRangeWriter(ChannelSupplier channelSupplier) {
      this((SeekableByteChannel)null, channelSupplier);
   }

   public SeekableByteChannelRangeWriter(SeekableByteChannel initialChannel, ChannelSupplier channelSupplier) {
      this.defaultSeekMode = true;
      this.channel = initialChannel;
      this.channelSupplier = channelSupplier;
      this.bufSize = 65536;
      this.buffer = BufferUtil.allocate(this.bufSize);
   }

   public void close() throws IOException {
      if (this.channel != null) {
         this.channel.close();
      }

   }

   public void writeTo(OutputStream outputStream, long skipTo, long length) throws IOException {
      this.skipTo(skipTo);

      int readLen;
      for(long readTotal = 0L; readTotal < length; this.pos += (long)readLen) {
         BufferUtil.clearToFill(this.buffer);
         int size = (int)Math.min((long)this.bufSize, length - readTotal);
         this.buffer.limit(size);
         readLen = this.channel.read(this.buffer);
         BufferUtil.flipToFlush(this.buffer, 0);
         BufferUtil.writeTo(this.buffer, outputStream);
         readTotal += (long)readLen;
      }

   }

   private void skipTo(long skipTo) throws IOException {
      if (this.channel == null) {
         this.channel = this.channelSupplier.newSeekableByteChannel();
         this.pos = 0L;
      }

      if (this.defaultSeekMode) {
         try {
            if (this.channel.position() != skipTo) {
               this.channel.position(skipTo);
               this.pos = skipTo;
               return;
            }
         } catch (UnsupportedOperationException var4) {
            this.defaultSeekMode = false;
            this.fallbackSkipTo(skipTo);
         }
      } else {
         this.fallbackSkipTo(skipTo);
      }

   }

   private void fallbackSkipTo(long skipTo) throws IOException {
      if (skipTo < this.pos) {
         this.channel.close();
         this.channel = this.channelSupplier.newSeekableByteChannel();
         this.pos = 0L;
      }

      if (this.pos < skipTo) {
         long skipSoFar = this.pos;
         int noProgressLoopLimit = 3;

         while(noProgressLoopLimit > 0 && skipSoFar < skipTo) {
            BufferUtil.clearToFill(this.buffer);
            int len = (int)Math.min((long)this.bufSize, skipTo - skipSoFar);
            this.buffer.limit(len);
            long actualSkipped = (long)this.channel.read(this.buffer);
            if (actualSkipped == 0L) {
               --noProgressLoopLimit;
            } else {
               if (actualSkipped <= 0L) {
                  throw new IOException("EOF reached before SeekableByteChannel skip destination");
               }

               skipSoFar += actualSkipped;
               noProgressLoopLimit = 3;
            }
         }

         if (noProgressLoopLimit <= 0) {
            long var10002 = skipTo - this.pos;
            throw new IOException("No progress made to reach SeekableByteChannel skip position " + var10002);
         }

         this.pos = skipTo;
      }

   }

   public interface ChannelSupplier {
      SeekableByteChannel newSeekableByteChannel() throws IOException;
   }
}
