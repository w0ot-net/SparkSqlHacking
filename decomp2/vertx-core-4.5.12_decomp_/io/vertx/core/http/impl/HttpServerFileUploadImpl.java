package io.vertx.core.http.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.http.HttpServerFileUpload;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.streams.Pipe;
import io.vertx.core.streams.ReadStream;
import java.nio.charset.Charset;

class HttpServerFileUploadImpl implements HttpServerFileUpload {
   private final ReadStream stream;
   private final ContextInternal context;
   private final String name;
   private final String filename;
   private final String contentType;
   private final String contentTransferEncoding;
   private final Charset charset;
   private Handler dataHandler;
   private Handler endHandler;
   private Handler exceptionHandler;
   private long size;
   private boolean lazyCalculateSize;
   private AsyncFile file;
   private Pipe pipe;
   private boolean cancelled;

   HttpServerFileUploadImpl(ContextInternal context, ReadStream stream, String name, String filename, String contentType, String contentTransferEncoding, Charset charset, long size) {
      this.context = context;
      this.stream = stream;
      this.name = name;
      this.filename = filename;
      this.contentType = contentType;
      this.contentTransferEncoding = contentTransferEncoding;
      this.charset = charset;
      this.size = size;
      this.lazyCalculateSize = size == 0L;
      stream.handler(this::handleData);
      stream.exceptionHandler(this::handleException);
      stream.endHandler((v) -> this.handleEnd());
   }

   private void handleData(Buffer data) {
      Handler<Buffer> handler;
      synchronized(this) {
         handler = this.dataHandler;
         if (this.lazyCalculateSize) {
            this.size += (long)data.length();
         }
      }

      if (handler != null) {
         this.context.dispatch(data, handler);
      }

   }

   private void handleException(Throwable cause) {
      Handler<Throwable> handler;
      synchronized(this) {
         handler = this.exceptionHandler;
      }

      if (handler != null) {
         this.context.dispatch(cause, handler);
      }

   }

   private void handleEnd() {
      Handler<Void> handler;
      synchronized(this) {
         this.lazyCalculateSize = false;
         handler = this.endHandler;
      }

      if (handler != null) {
         this.context.dispatch(handler);
      }

   }

   public String filename() {
      return this.filename;
   }

   public String name() {
      return this.name;
   }

   public String contentType() {
      return this.contentType;
   }

   public String contentTransferEncoding() {
      return this.contentTransferEncoding;
   }

   public String charset() {
      return this.charset.toString();
   }

   public synchronized long size() {
      return this.size;
   }

   public synchronized HttpServerFileUpload handler(Handler handler) {
      this.dataHandler = handler;
      return this;
   }

   public HttpServerFileUpload pause() {
      this.stream.pause();
      return this;
   }

   public HttpServerFileUpload fetch(long amount) {
      this.stream.fetch(amount);
      return this;
   }

   public HttpServerFileUpload resume() {
      this.stream.resume();
      return this;
   }

   public synchronized HttpServerFileUpload exceptionHandler(Handler exceptionHandler) {
      this.exceptionHandler = exceptionHandler;
      return this;
   }

   public synchronized HttpServerFileUpload endHandler(Handler handler) {
      this.endHandler = handler;
      return this;
   }

   public void streamToFileSystem(String filename, Handler handler) {
      Future<Void> fut = this.streamToFileSystem(filename);
      if (handler != null) {
         fut.onComplete(handler);
      }

   }

   public Future streamToFileSystem(String filename) {
      synchronized(this) {
         if (this.pipe != null) {
            return this.context.failedFuture("Already streaming");
         }

         this.pipe = this.pipe().endOnComplete(true);
      }

      FileSystem fs = this.context.owner().fileSystem();
      Future<AsyncFile> fut = fs.open(filename, new OpenOptions());
      fut.onFailure((err) -> this.pipe.close());
      return fut.compose((f) -> {
         Future<Void> to = this.pipe.to(f);
         return to.compose((v) -> {
            synchronized(this) {
               if (!this.cancelled) {
                  this.file = f;
                  return this.context.succeededFuture();
               } else {
                  fs.delete(filename);
                  return this.context.failedFuture("Streaming aborted");
               }
            }
         }, (err) -> {
            fs.delete(filename);
            return this.context.failedFuture(err);
         });
      });
   }

   public boolean cancelStreamToFileSystem() {
      synchronized(this) {
         if (this.pipe == null) {
            throw new IllegalStateException("Not a streaming upload");
         }

         if (this.file != null) {
            return false;
         }

         this.cancelled = true;
      }

      this.pipe.close();
      return true;
   }

   public synchronized boolean isSizeAvailable() {
      return !this.lazyCalculateSize;
   }

   public synchronized AsyncFile file() {
      return this.file;
   }
}
