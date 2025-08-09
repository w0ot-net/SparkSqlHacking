package org.sparkproject.jetty.client.util;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;

public class BytesRequestContent extends AbstractRequestContent {
   private final byte[][] bytes;
   private final long length;

   public BytesRequestContent(byte[]... bytes) {
      this("application/octet-stream", bytes);
   }

   public BytesRequestContent(String contentType, byte[]... bytes) {
      super(contentType);
      this.bytes = bytes;
      this.length = Arrays.stream(bytes).mapToLong((a) -> (long)a.length).sum();
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
            if (this.index < BytesRequestContent.this.bytes.length) {
               buffer = ByteBuffer.wrap(BytesRequestContent.this.bytes[this.index++]);
            }

            boolean lastContent = this.index == BytesRequestContent.this.bytes.length;
            if (lastContent) {
               this.index = -1;
            }

            return producer.produce(buffer, lastContent, Callback.NOOP);
         }
      }
   }
}
