package org.sparkproject.jetty.client.util;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.IO;

public class InputStreamRequestContent extends AbstractRequestContent {
   private static final int DEFAULT_BUFFER_SIZE = 4096;
   private final InputStream stream;
   private final int bufferSize;
   private Request.Content.Subscription subscription;

   public InputStreamRequestContent(InputStream stream) {
      this(stream, 4096);
   }

   public InputStreamRequestContent(String contentType, InputStream stream) {
      this(contentType, stream, 4096);
   }

   public InputStreamRequestContent(InputStream stream, int bufferSize) {
      this("application/octet-stream", stream, bufferSize);
   }

   public InputStreamRequestContent(String contentType, InputStream stream, int bufferSize) {
      super(contentType);
      this.stream = stream;
      this.bufferSize = bufferSize;
   }

   protected Request.Content.Subscription newSubscription(Request.Content.Consumer consumer, boolean emitInitialContent) {
      if (this.subscription != null) {
         throw new IllegalStateException("Multiple subscriptions not supported on " + String.valueOf(this));
      } else {
         return this.subscription = new SubscriptionImpl(consumer, emitInitialContent);
      }
   }

   public void fail(Throwable failure) {
      super.fail(failure);
      this.close();
   }

   protected ByteBuffer onRead(byte[] buffer, int offset, int length) {
      return ByteBuffer.wrap(buffer, offset, length);
   }

   protected void onReadFailure(Throwable failure) {
   }

   private void close() {
      IO.close(this.stream);
   }

   private class SubscriptionImpl extends AbstractRequestContent.AbstractSubscription {
      private boolean terminated;

      private SubscriptionImpl(Request.Content.Consumer consumer, boolean emitInitialContent) {
         super(consumer, emitInitialContent);
      }

      protected boolean produceContent(AbstractRequestContent.Producer producer) throws IOException {
         if (this.terminated) {
            throw new EOFException("Demand after last content");
         } else {
            byte[] bytes = new byte[InputStreamRequestContent.this.bufferSize];
            int read = this.read(bytes);
            ByteBuffer buffer = BufferUtil.EMPTY_BUFFER;
            boolean last = true;
            if (read < 0) {
               InputStreamRequestContent.this.close();
               this.terminated = true;
            } else {
               buffer = InputStreamRequestContent.this.onRead(bytes, 0, read);
               last = false;
            }

            return producer.produce(buffer, last, Callback.NOOP);
         }
      }

      private int read(byte[] bytes) throws IOException {
         try {
            return InputStreamRequestContent.this.stream.read(bytes);
         } catch (Throwable x) {
            InputStreamRequestContent.this.onReadFailure(x);
            throw x;
         }
      }

      public void fail(Throwable failure) {
         super.fail(failure);
         InputStreamRequestContent.this.close();
      }
   }
}
