package org.sparkproject.jetty.client.util;

import java.io.EOFException;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;

public class ByteBufferRequestContent extends AbstractRequestContent {
   private final ByteBuffer[] buffers;
   private final long length;

   public ByteBufferRequestContent(ByteBuffer... buffers) {
      this("application/octet-stream", buffers);
   }

   public ByteBufferRequestContent(String contentType, ByteBuffer... buffers) {
      super(contentType);
      this.buffers = buffers;
      this.length = Arrays.stream(buffers).mapToLong(Buffer::remaining).sum();
   }

   public long getLength() {
      return this.length;
   }

   public boolean isReproducible() {
      return true;
   }

   protected Request.Content.Subscription newSubscription(Request.Content.Consumer consumer, boolean emitInitialContent) {
      return new SubscriptionImpl(consumer, emitInitialContent);
   }

   private class SubscriptionImpl extends AbstractRequestContent.AbstractSubscription {
      private int index;

      private SubscriptionImpl(Request.Content.Consumer consumer, boolean emitInitialContent) {
         super(consumer, emitInitialContent);
      }

      protected boolean produceContent(AbstractRequestContent.Producer producer) throws IOException {
         if (this.index < 0) {
            throw new EOFException("Demand after last content");
         } else {
            ByteBuffer buffer = BufferUtil.EMPTY_BUFFER;
            if (this.index < ByteBufferRequestContent.this.buffers.length) {
               buffer = ByteBufferRequestContent.this.buffers[this.index++];
            }

            boolean lastContent = this.index == ByteBufferRequestContent.this.buffers.length;
            if (lastContent) {
               this.index = -1;
            }

            return producer.produce(buffer.slice(), lastContent, Callback.NOOP);
         }
      }
   }
}
