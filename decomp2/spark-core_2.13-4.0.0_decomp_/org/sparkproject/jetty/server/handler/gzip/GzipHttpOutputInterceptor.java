package org.sparkproject.jetty.server.handler.gzip;

import java.nio.ByteBuffer;
import java.nio.channels.WritePendingException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.CompressedContentFormat;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpFields;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.PreEncodedHttpField;
import org.sparkproject.jetty.server.HttpChannel;
import org.sparkproject.jetty.server.HttpOutput;
import org.sparkproject.jetty.server.Response;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.IteratingCallback;
import org.sparkproject.jetty.util.IteratingNestedCallback;
import org.sparkproject.jetty.util.compression.CompressionPool;

public class GzipHttpOutputInterceptor implements HttpOutput.Interceptor {
   public static Logger LOG = LoggerFactory.getLogger(GzipHttpOutputInterceptor.class);
   private static final byte[] GZIP_HEADER = new byte[]{31, -117, 8, 0, 0, 0, 0, 0, 0, 0};
   public static final HttpField VARY_ACCEPT_ENCODING;
   private final AtomicReference _state;
   private final CRC32 _crc;
   private final GzipFactory _factory;
   private final HttpOutput.Interceptor _interceptor;
   private final HttpChannel _channel;
   private final HttpField _vary;
   private final int _bufferSize;
   private final boolean _syncFlush;
   private CompressionPool.Entry _deflaterEntry;
   private ByteBuffer _buffer;

   public GzipHttpOutputInterceptor(GzipFactory factory, HttpChannel channel, HttpOutput.Interceptor next, boolean syncFlush) {
      this(factory, VARY_ACCEPT_ENCODING, channel.getHttpConfiguration().getOutputBufferSize(), channel, next, syncFlush);
   }

   public GzipHttpOutputInterceptor(GzipFactory factory, HttpField vary, HttpChannel channel, HttpOutput.Interceptor next, boolean syncFlush) {
      this(factory, vary, channel.getHttpConfiguration().getOutputBufferSize(), channel, next, syncFlush);
   }

   public GzipHttpOutputInterceptor(GzipFactory factory, HttpField vary, int bufferSize, HttpChannel channel, HttpOutput.Interceptor next, boolean syncFlush) {
      this._state = new AtomicReference(GzipHttpOutputInterceptor.GZState.MIGHT_COMPRESS);
      this._crc = new CRC32();
      this._factory = factory;
      this._channel = channel;
      this._interceptor = next;
      this._vary = vary;
      this._bufferSize = bufferSize;
      this._syncFlush = syncFlush;
   }

   public HttpOutput.Interceptor getNextInterceptor() {
      return this._interceptor;
   }

   public void write(ByteBuffer content, boolean complete, Callback callback) {
      switch (((GZState)this._state.get()).ordinal()) {
         case 0:
            this.commit(content, complete, callback);
            break;
         case 1:
            this._interceptor.write(content, complete, callback);
            return;
         case 2:
            callback.failed(new WritePendingException());
            break;
         case 3:
            this.gzip(content, complete, callback);
            break;
         default:
            callback.failed(new IllegalStateException("state=" + String.valueOf(this._state.get())));
      }

   }

   private void addTrailer() {
      BufferUtil.putIntLittleEndian(this._buffer, (int)this._crc.getValue());
      BufferUtil.putIntLittleEndian(this._buffer, ((Deflater)this._deflaterEntry.get()).getTotalIn());
   }

   private void gzip(ByteBuffer content, boolean complete, Callback callback) {
      if (!content.hasRemaining() && !complete) {
         callback.succeeded();
      } else {
         (new GzipBufferCB(content, complete, callback)).iterate();
      }

   }

   protected void commit(ByteBuffer content, boolean complete, Callback callback) {
      Response response = this._channel.getResponse();
      int sc = response.getStatus();
      if (sc <= 0 || sc >= 200 && sc != 204 && sc != 205 && sc < 300) {
         String ct = response.getContentType();
         if (ct != null) {
            String baseType = HttpField.valueParameters(ct, (Map)null);
            if (!this._factory.isMimeTypeGzipable(baseType)) {
               LOG.debug("{} exclude by mimeType {}", this, ct);
               this.noCompression();
               this._interceptor.write(content, complete, callback);
               return;
            }
         }

         HttpFields.Mutable fields = response.getHttpFields();
         String ce = fields.get(HttpHeader.CONTENT_ENCODING);
         if (ce != null) {
            LOG.debug("{} exclude by content-encoding {}", this, ce);
            this.noCompression();
            this._interceptor.write(content, complete, callback);
         } else {
            if (this._state.compareAndSet(GzipHttpOutputInterceptor.GZState.MIGHT_COMPRESS, GzipHttpOutputInterceptor.GZState.COMMITTING)) {
               if (this._vary != null) {
                  fields.ensureField(this._vary);
               }

               long contentLength = response.getContentLength();
               if (contentLength < 0L && complete) {
                  contentLength = (long)content.remaining();
               }

               this._deflaterEntry = this._factory.getDeflaterEntry(this._channel.getRequest(), contentLength);
               if (this._deflaterEntry == null) {
                  LOG.debug("{} exclude no deflater", this);
                  this._state.set(GzipHttpOutputInterceptor.GZState.NOT_COMPRESSING);
                  this._interceptor.write(content, complete, callback);
                  return;
               }

               fields.put(CompressedContentFormat.GZIP.getContentEncoding());
               this._crc.reset();
               response.setContentLength(-1);
               String etag = fields.get(HttpHeader.ETAG);
               if (etag != null) {
                  fields.put(HttpHeader.ETAG, this.etagGzip(etag));
               }

               LOG.debug("{} compressing {}", this, this._deflaterEntry);
               this._state.set(GzipHttpOutputInterceptor.GZState.COMPRESSING);
               if (BufferUtil.isEmpty(content)) {
                  this._interceptor.write(BufferUtil.EMPTY_BUFFER, complete, callback);
               } else {
                  this.gzip(content, complete, callback);
               }
            } else {
               callback.failed(new WritePendingException());
            }

         }
      } else {
         LOG.debug("{} exclude by status {}", this, sc);
         this.noCompression();
         if (sc == 304) {
            String requestEtags = (String)this._channel.getRequest().getAttribute("o.e.j.s.h.gzip.GzipHandler.etag");
            String responseEtag = response.getHttpFields().get(HttpHeader.ETAG);
            if (requestEtags != null && responseEtag != null) {
               String responseEtagGzip = this.etagGzip(responseEtag);
               if (requestEtags.contains(responseEtagGzip)) {
                  response.getHttpFields().put(HttpHeader.ETAG, responseEtagGzip);
               }

               if (this._vary != null) {
                  response.getHttpFields().ensureField(this._vary);
               }
            }
         }

         this._interceptor.write(content, complete, callback);
      }
   }

   private String etagGzip(String etag) {
      return CompressedContentFormat.GZIP.etag(etag);
   }

   public void noCompression() {
      while(true) {
         switch (((GZState)this._state.get()).ordinal()) {
            case 0:
               if (!this._state.compareAndSet(GzipHttpOutputInterceptor.GZState.MIGHT_COMPRESS, GzipHttpOutputInterceptor.GZState.NOT_COMPRESSING)) {
                  break;
               }

               return;
            case 1:
               return;
            default:
               throw new IllegalStateException(((GZState)this._state.get()).toString());
         }
      }
   }

   public boolean mightCompress() {
      return this._state.get() == GzipHttpOutputInterceptor.GZState.MIGHT_COMPRESS;
   }

   static {
      VARY_ACCEPT_ENCODING = new PreEncodedHttpField(HttpHeader.VARY, HttpHeader.ACCEPT_ENCODING.asString());
   }

   private static enum GZState {
      MIGHT_COMPRESS,
      NOT_COMPRESSING,
      COMMITTING,
      COMPRESSING,
      FINISHED;

      // $FF: synthetic method
      private static GZState[] $values() {
         return new GZState[]{MIGHT_COMPRESS, NOT_COMPRESSING, COMMITTING, COMPRESSING, FINISHED};
      }
   }

   private class GzipBufferCB extends IteratingNestedCallback {
      private final ByteBuffer _content;
      private final boolean _last;

      public GzipBufferCB(ByteBuffer content, boolean complete, Callback callback) {
         super(callback);
         this._content = content;
         this._last = complete;
         GzipHttpOutputInterceptor.this._crc.update(this._content.slice());
         Deflater deflater = (Deflater)GzipHttpOutputInterceptor.this._deflaterEntry.get();
         deflater.setInput(this._content);
         if (this._last) {
            deflater.finish();
         }

      }

      protected void onCompleteFailure(Throwable x) {
         if (GzipHttpOutputInterceptor.this._deflaterEntry != null) {
            GzipHttpOutputInterceptor.this._deflaterEntry.release();
            GzipHttpOutputInterceptor.this._deflaterEntry = null;
         }

         super.onCompleteFailure(x);
      }

      protected IteratingCallback.Action process() throws Exception {
         if (GzipHttpOutputInterceptor.this._deflaterEntry == null) {
            if (GzipHttpOutputInterceptor.this._buffer != null) {
               GzipHttpOutputInterceptor.this._channel.getByteBufferPool().release(GzipHttpOutputInterceptor.this._buffer);
               GzipHttpOutputInterceptor.this._buffer = null;
            }

            return IteratingCallback.Action.SUCCEEDED;
         } else {
            if (GzipHttpOutputInterceptor.this._buffer == null) {
               GzipHttpOutputInterceptor.this._buffer = GzipHttpOutputInterceptor.this._channel.getByteBufferPool().acquire(GzipHttpOutputInterceptor.this._bufferSize, GzipHttpOutputInterceptor.this._channel.isUseOutputDirectByteBuffers());
               BufferUtil.fill(GzipHttpOutputInterceptor.this._buffer, GzipHttpOutputInterceptor.GZIP_HEADER, 0, GzipHttpOutputInterceptor.GZIP_HEADER.length);
            } else {
               BufferUtil.clear(GzipHttpOutputInterceptor.this._buffer);
            }

            Deflater deflater = (Deflater)GzipHttpOutputInterceptor.this._deflaterEntry.get();
            if (!deflater.finished()) {
               if (deflater.needsInput() && !this._last) {
                  return IteratingCallback.Action.SUCCEEDED;
               }

               int pos = BufferUtil.flipToFill(GzipHttpOutputInterceptor.this._buffer);
               deflater.deflate(GzipHttpOutputInterceptor.this._buffer, GzipHttpOutputInterceptor.this._syncFlush ? 2 : 0);
               BufferUtil.flipToFlush(GzipHttpOutputInterceptor.this._buffer, pos);
            }

            if (deflater.finished() && BufferUtil.space(GzipHttpOutputInterceptor.this._buffer) >= 8) {
               GzipHttpOutputInterceptor.this.addTrailer();
               GzipHttpOutputInterceptor.this._deflaterEntry.release();
               GzipHttpOutputInterceptor.this._deflaterEntry = null;
            }

            GzipHttpOutputInterceptor.this._interceptor.write(GzipHttpOutputInterceptor.this._buffer, GzipHttpOutputInterceptor.this._deflaterEntry == null, this);
            return IteratingCallback.Action.SCHEDULED;
         }
      }

      public String toString() {
         return String.format("%s[content=%s last=%b buffer=%s deflate=%s %s]", super.toString(), BufferUtil.toDetailString(this._content), this._last, BufferUtil.toDetailString(GzipHttpOutputInterceptor.this._buffer), GzipHttpOutputInterceptor.this._deflaterEntry, GzipHttpOutputInterceptor.this._deflaterEntry != null && ((Deflater)GzipHttpOutputInterceptor.this._deflaterEntry.get()).finished() ? "(finished)" : "");
      }
   }
}
