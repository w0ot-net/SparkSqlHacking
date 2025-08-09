package org.apache.spark.network.client;

import io.netty.buffer.ByteBuf;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import org.apache.spark.network.server.MessageHandler;
import org.apache.spark.network.util.TransportFrameDecoder;

public class StreamInterceptor implements TransportFrameDecoder.Interceptor {
   private final MessageHandler handler;
   private final String streamId;
   private final long byteCount;
   private final StreamCallback callback;
   private long bytesRead;

   public StreamInterceptor(MessageHandler handler, String streamId, long byteCount, StreamCallback callback) {
      this.handler = handler;
      this.streamId = streamId;
      this.byteCount = byteCount;
      this.callback = callback;
      this.bytesRead = 0L;
   }

   public void exceptionCaught(Throwable cause) throws Exception {
      this.deactivateStream();
      this.callback.onFailure(this.streamId, cause);
   }

   public void channelInactive() throws Exception {
      this.deactivateStream();
      this.callback.onFailure(this.streamId, new ClosedChannelException());
   }

   private void deactivateStream() {
      MessageHandler var2 = this.handler;
      if (var2 instanceof TransportResponseHandler transportResponseHandler) {
         transportResponseHandler.deactivateStream();
      }

   }

   public boolean handle(ByteBuf buf) throws Exception {
      int toRead = (int)Math.min((long)buf.readableBytes(), this.byteCount - this.bytesRead);
      ByteBuffer nioBuffer = buf.readSlice(toRead).nioBuffer();
      int available = nioBuffer.remaining();
      this.callback.onData(this.streamId, nioBuffer);
      this.bytesRead += (long)available;
      if (this.bytesRead > this.byteCount) {
         RuntimeException re = new IllegalStateException(String.format("Read too many bytes? Expected %d, but read %d.", this.byteCount, this.bytesRead));
         this.callback.onFailure(this.streamId, re);
         this.deactivateStream();
         throw re;
      } else {
         if (this.bytesRead == this.byteCount) {
            this.deactivateStream();
            this.callback.onComplete(this.streamId);
         }

         return this.bytesRead != this.byteCount;
      }
   }
}
