package org.sparkproject.jetty.client;

import java.nio.ByteBuffer;
import java.util.ListIterator;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.io.ByteBufferPool;

public class GZIPContentDecoder extends org.sparkproject.jetty.http.GZIPContentDecoder implements ContentDecoder {
   public static final int DEFAULT_BUFFER_SIZE = 8192;
   private long decodedLength;

   public GZIPContentDecoder() {
      this(8192);
   }

   public GZIPContentDecoder(int bufferSize) {
      this((ByteBufferPool)null, bufferSize);
   }

   public GZIPContentDecoder(ByteBufferPool byteBufferPool, int bufferSize) {
      super(byteBufferPool, bufferSize);
   }

   public void beforeDecoding(HttpExchange exchange) {
      exchange.getResponse().headers((headers) -> {
         ListIterator<HttpField> iterator = headers.listIterator();

         while(iterator.hasNext()) {
            HttpField field = (HttpField)iterator.next();
            HttpHeader header = field.getHeader();
            if (header == HttpHeader.CONTENT_LENGTH) {
               iterator.remove();
            } else if (header == HttpHeader.CONTENT_ENCODING) {
               String value = field.getValue();
               int comma = value.lastIndexOf(",");
               if (comma < 0) {
                  iterator.remove();
               } else {
                  iterator.set(new HttpField(HttpHeader.CONTENT_ENCODING, value.substring(0, comma)));
               }
            }
         }

      });
   }

   protected boolean decodedChunk(ByteBuffer chunk) {
      this.decodedLength += (long)chunk.remaining();
      super.decodedChunk(chunk);
      return true;
   }

   public void afterDecoding(HttpExchange exchange) {
      exchange.getResponse().headers((headers) -> {
         headers.remove(HttpHeader.TRANSFER_ENCODING);
         headers.putLongField(HttpHeader.CONTENT_LENGTH, this.decodedLength);
      });
   }

   public static class Factory extends ContentDecoder.Factory {
      private final int bufferSize;
      private final ByteBufferPool byteBufferPool;

      public Factory() {
         this(8192);
      }

      public Factory(int bufferSize) {
         this((ByteBufferPool)null, bufferSize);
      }

      public Factory(ByteBufferPool byteBufferPool) {
         this(byteBufferPool, 8192);
      }

      public Factory(ByteBufferPool byteBufferPool, int bufferSize) {
         super("gzip");
         this.byteBufferPool = byteBufferPool;
         this.bufferSize = bufferSize;
      }

      public ContentDecoder newContentDecoder() {
         return new GZIPContentDecoder(this.byteBufferPool, this.bufferSize);
      }
   }
}
