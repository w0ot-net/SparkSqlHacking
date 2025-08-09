package io.vertx.ext.web.client.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpConstants;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.FileUpload;
import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder;
import io.netty.handler.codec.http.multipart.MemoryAttribute;
import io.netty.handler.codec.http.multipart.MemoryFileUpload;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.impl.headers.HeadersAdaptor;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.impl.InboundBuffer;
import io.vertx.ext.web.multipart.FormDataPart;
import io.vertx.ext.web.multipart.MultipartForm;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class MultipartFormUpload implements ReadStream {
   private static final UnpooledByteBufAllocator ALLOC = new UnpooledByteBufAllocator(false);
   private DefaultFullHttpRequest request;
   private HttpPostRequestEncoder encoder;
   private Handler exceptionHandler;
   private Handler dataHandler;
   private Handler endHandler;
   private final InboundBuffer pending;
   private boolean ended;
   private final Context context;

   public MultipartFormUpload(Context context, MultipartForm parts, boolean multipart, HttpPostRequestEncoder.EncoderMode encoderMode) throws Exception {
      this.context = context;
      this.pending = (new InboundBuffer(context)).handler(this::handleChunk).drainHandler((v) -> this.run()).pause();
      this.request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, "/");
      parts.getCharset();
      final Charset charset = parts.getCharset() != null ? parts.getCharset() : HttpConstants.DEFAULT_CHARSET;
      this.encoder = new HttpPostRequestEncoder(new DefaultHttpDataFactory(16384L, charset) {
         public Attribute createAttribute(HttpRequest request, String name, String value) {
            try {
               return new MemoryAttribute(name, value, charset);
            } catch (IOException e) {
               throw new IllegalArgumentException(e);
            }
         }

         public FileUpload createFileUpload(HttpRequest request, String name, String filename, String contentType, String contentTransferEncoding, Charset _charset, long size) {
            if (_charset == null) {
               _charset = charset;
            }

            return super.createFileUpload(request, name, filename, contentType, contentTransferEncoding, _charset, size);
         }
      }, this.request, multipart, charset, encoderMode);

      for(FormDataPart formDataPart : parts) {
         if (formDataPart.isAttribute()) {
            this.encoder.addBodyAttribute(formDataPart.name(), formDataPart.value());
         } else {
            String pathname = formDataPart.pathname();
            if (pathname != null) {
               this.encoder.addBodyFileUpload(formDataPart.name(), formDataPart.filename(), new File(formDataPart.pathname()), formDataPart.mediaType(), formDataPart.isText());
            } else {
               String contentType = formDataPart.mediaType();
               if (formDataPart.mediaType() == null) {
                  if (formDataPart.isText()) {
                     contentType = "text/plain";
                  } else {
                     contentType = "application/octet-stream";
                  }
               }

               String transferEncoding = formDataPart.isText() ? null : "binary";
               MemoryFileUpload fileUpload = new MemoryFileUpload(formDataPart.name(), formDataPart.filename(), contentType, transferEncoding, (Charset)null, (long)formDataPart.content().length());
               fileUpload.setContent(formDataPart.content().getByteBuf());
               this.encoder.addBodyHttpData(fileUpload);
            }
         }
      }

      this.encoder.finalizeRequest();
   }

   private void handleChunk(Object item) {
      Handler handler;
      synchronized(this) {
         if (item instanceof Buffer) {
            handler = this.dataHandler;
         } else if (item instanceof Throwable) {
            handler = this.exceptionHandler;
         } else {
            if (item != InboundBuffer.END_SENTINEL) {
               return;
            }

            handler = this.endHandler;
            item = null;
         }
      }

      handler.handle(item);
   }

   public void run() {
      if (Vertx.currentContext() != this.context) {
         throw new IllegalArgumentException();
      } else {
         while(!this.ended) {
            if (this.encoder.isChunked()) {
               try {
                  HttpContent chunk = this.encoder.readChunk(ALLOC);
                  ByteBuf content = chunk.content();
                  Buffer buff = Buffer.buffer(content);
                  boolean writable = this.pending.write(buff);
                  if (this.encoder.isEndOfInput()) {
                     this.ended = true;
                     this.request = null;
                     this.encoder = null;
                     this.pending.write(InboundBuffer.END_SENTINEL);
                  } else if (!writable) {
                     break;
                  }
               } catch (Exception e) {
                  this.ended = true;
                  this.request = null;
                  this.encoder = null;
                  this.pending.write(e);
                  break;
               }
            } else {
               ByteBuf content = this.request.content();
               Buffer buffer = Buffer.buffer(content);
               this.request = null;
               this.encoder = null;
               this.pending.write(buffer);
               this.ended = true;
               this.pending.write(InboundBuffer.END_SENTINEL);
            }
         }

      }
   }

   public MultiMap headers() {
      return new HeadersAdaptor(this.request.headers());
   }

   public synchronized MultipartFormUpload exceptionHandler(Handler handler) {
      this.exceptionHandler = handler;
      return this;
   }

   public synchronized MultipartFormUpload handler(Handler handler) {
      this.dataHandler = handler;
      return this;
   }

   public synchronized MultipartFormUpload pause() {
      this.pending.pause();
      return this;
   }

   public ReadStream fetch(long amount) {
      this.pending.fetch(amount);
      return this;
   }

   public synchronized MultipartFormUpload resume() {
      this.pending.resume();
      return this;
   }

   public synchronized MultipartFormUpload endHandler(Handler handler) {
      this.endHandler = handler;
      return this;
   }
}
