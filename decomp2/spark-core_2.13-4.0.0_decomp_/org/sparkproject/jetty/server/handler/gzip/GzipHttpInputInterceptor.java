package org.sparkproject.jetty.server.handler.gzip;

import java.nio.ByteBuffer;
import org.sparkproject.jetty.http.GZIPContentDecoder;
import org.sparkproject.jetty.io.ByteBufferPool;
import org.sparkproject.jetty.server.HttpInput;
import org.sparkproject.jetty.util.component.Destroyable;
import org.sparkproject.jetty.util.compression.InflaterPool;

public class GzipHttpInputInterceptor implements HttpInput.Interceptor, Destroyable {
   private final Decoder _decoder;
   private ByteBuffer _chunk;

   public GzipHttpInputInterceptor(InflaterPool inflaterPool, ByteBufferPool pool, int bufferSize) {
      this(inflaterPool, pool, bufferSize, false);
   }

   public GzipHttpInputInterceptor(InflaterPool inflaterPool, ByteBufferPool pool, int bufferSize, boolean useDirectBuffers) {
      this._decoder = new Decoder(inflaterPool, pool, bufferSize, useDirectBuffers);
   }

   public HttpInput.Content readFrom(HttpInput.Content content) {
      if (content.isSpecial()) {
         return content;
      } else {
         this._decoder.decodeChunks(content.getByteBuffer());
         final ByteBuffer chunk = this._chunk;
         return chunk == null ? null : new HttpInput.Content(chunk) {
            public void succeeded() {
               GzipHttpInputInterceptor.this._decoder.release(chunk);
            }

            public void failed(Throwable x) {
               GzipHttpInputInterceptor.this._decoder.release(chunk);
            }
         };
      }
   }

   public void destroy() {
      this._decoder.destroy();
   }

   private class Decoder extends GZIPContentDecoder {
      private Decoder(InflaterPool inflaterPool, ByteBufferPool bufferPool, int bufferSize, boolean useDirectBuffers) {
         super(inflaterPool, bufferPool, bufferSize, useDirectBuffers);
      }

      protected boolean decodedChunk(ByteBuffer chunk) {
         GzipHttpInputInterceptor.this._chunk = chunk;
         return true;
      }

      public void decodeChunks(ByteBuffer compressed) {
         GzipHttpInputInterceptor.this._chunk = null;
         super.decodeChunks(compressed);
      }
   }
}
