package io.vertx.core.http.impl;

import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.http.HttpClosedException;
import io.vertx.core.http.StreamResetException;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.net.impl.ConnectionBase;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.WriteStream;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.security.cert.X509Certificate;

class HttpNetSocket implements NetSocket {
   private final ConnectionBase conn;
   private final ContextInternal context;
   private final ReadStream readStream;
   private final WriteStream writeStream;
   private Handler exceptionHandler;
   private Handler closeHandler;
   private Handler endHandler;
   private Handler dataHandler;

   static HttpNetSocket netSocket(ConnectionBase conn, ContextInternal context, ReadStream readStream, WriteStream writeStream) {
      HttpNetSocket sock = new HttpNetSocket(conn, context, readStream, writeStream);
      readStream.handler(sock::handleData);
      readStream.endHandler(sock::handleEnd);
      readStream.exceptionHandler(sock::handleException);
      return sock;
   }

   private HttpNetSocket(ConnectionBase conn, ContextInternal context, ReadStream readStream, WriteStream writeStream) {
      this.conn = conn;
      this.context = context;
      this.readStream = readStream;
      this.writeStream = writeStream;
   }

   private void handleEnd(Void v) {
      Handler<Void> endHandler = this.endHandler();
      if (endHandler != null) {
         endHandler.handle((Object)null);
      }

      Handler<Void> closeHandler = this.closeHandler();
      if (closeHandler != null) {
         closeHandler.handle((Object)null);
      }

   }

   private void handleData(Buffer buf) {
      Handler<Buffer> handler = this.handler();
      if (handler != null) {
         handler.handle(buf);
      }

   }

   private void handleException(Throwable cause) {
      Handler<Throwable> handler = this.exceptionHandler();
      if (handler != null) {
         handler.handle(cause);
      }

      if (cause instanceof HttpClosedException) {
         Handler<Void> endHandler = this.endHandler();
         if (endHandler != null) {
            endHandler.handle((Object)null);
         }
      }

      if (cause instanceof StreamResetException || cause instanceof HttpClosedException) {
         Handler<Void> closeHandler = this.closeHandler();
         if (closeHandler != null) {
            closeHandler.handle((Object)null);
         }
      }

   }

   public synchronized NetSocket exceptionHandler(Handler handler) {
      this.exceptionHandler = handler;
      return this;
   }

   synchronized Handler exceptionHandler() {
      return this.exceptionHandler;
   }

   public synchronized NetSocket handler(Handler handler) {
      this.dataHandler = handler;
      return this;
   }

   synchronized Handler handler() {
      return this.dataHandler;
   }

   public NetSocket fetch(long amount) {
      this.readStream.fetch(amount);
      return this;
   }

   public NetSocket pause() {
      this.readStream.pause();
      return this;
   }

   public NetSocket resume() {
      this.readStream.resume();
      return this;
   }

   public synchronized NetSocket endHandler(Handler handler) {
      this.endHandler = handler;
      return this;
   }

   synchronized Handler endHandler() {
      return this.endHandler;
   }

   public NetSocket setWriteQueueMaxSize(int maxSize) {
      this.writeStream.setWriteQueueMaxSize(maxSize);
      return this;
   }

   public NetSocket drainHandler(Handler handler) {
      this.writeStream.drainHandler(handler);
      return this;
   }

   public boolean writeQueueFull() {
      return this.writeStream.writeQueueFull();
   }

   public String writeHandlerID() {
      return null;
   }

   public Future write(Buffer data) {
      return this.writeStream.write(data);
   }

   public void write(Buffer data, Handler handler) {
      this.writeStream.write(data, handler);
   }

   public Future write(String str, String enc) {
      return this.write(Buffer.buffer(str, enc));
   }

   public void write(String str, String enc, Handler handler) {
      this.writeStream.write(Buffer.buffer(str, enc), handler);
   }

   public Future write(String str) {
      return this.writeStream.write(Buffer.buffer(str));
   }

   public void write(String str, Handler handler) {
      this.writeStream.write(Buffer.buffer(str), handler);
   }

   public Future end(Buffer data) {
      return this.writeStream.end((Object)data);
   }

   public void end(Buffer buffer, Handler handler) {
      this.writeStream.end(buffer, handler);
   }

   public Future end() {
      return this.writeStream.end();
   }

   public void end(Handler handler) {
      this.writeStream.end(handler);
   }

   public Future sendFile(String filename, long offset, long length) {
      Promise<Void> promise = this.context.promise();
      this.sendFile(filename, offset, length, promise);
      return promise.future();
   }

   public NetSocket sendFile(String filename, long offset, long length, Handler resultHandler) {
      VertxInternal vertx = this.conn.getContext().owner();
      Handler<AsyncResult<Void>> h;
      if (resultHandler != null) {
         Context resultCtx = vertx.getOrCreateContext();
         h = (ar) -> resultCtx.runOnContext((v) -> resultHandler.handle(ar));
      } else {
         h = (ar) -> {
         };
      }

      HttpUtils.resolveFile(vertx, filename, offset, length, (ar) -> {
         if (ar.succeeded()) {
            AsyncFile file = (AsyncFile)ar.result();
            file.pipe().endOnComplete(false).to(this, (ar1) -> file.close((ar2) -> {
                  Throwable failure = ar1.failed() ? ar1.cause() : (ar2.failed() ? ar2.cause() : null);
                  if (failure == null) {
                     h.handle(ar1);
                  } else {
                     h.handle(Future.failedFuture(failure));
                  }

               }));
         } else {
            h.handle(ar.mapEmpty());
         }

      });
      return this;
   }

   public SocketAddress remoteAddress() {
      return this.conn.remoteAddress();
   }

   public SocketAddress remoteAddress(boolean real) {
      return this.conn.remoteAddress(real);
   }

   public SocketAddress localAddress() {
      return this.conn.localAddress();
   }

   public SocketAddress localAddress(boolean real) {
      return this.conn.localAddress(real);
   }

   public Future close() {
      return this.end();
   }

   public void close(Handler handler) {
      this.end(handler);
   }

   public NetSocket closeHandler(@Nullable Handler handler) {
      synchronized(this.conn) {
         this.closeHandler = handler;
         return this;
      }
   }

   Handler closeHandler() {
      synchronized(this.conn) {
         return this.closeHandler;
      }
   }

   public NetSocket upgradeToSsl(Handler handler) {
      handler.handle(this.upgradeToSsl());
      return this;
   }

   public NetSocket upgradeToSsl(String serverName, Handler handler) {
      handler.handle(this.upgradeToSsl(serverName));
      return this;
   }

   public Future upgradeToSsl() {
      return Future.failedFuture("Cannot upgrade stream to SSL");
   }

   public Future upgradeToSsl(String serverName) {
      return Future.failedFuture("Cannot upgrade stream to SSL");
   }

   public boolean isSsl() {
      return this.conn.isSsl();
   }

   public SSLSession sslSession() {
      return this.conn.sslSession();
   }

   public X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException {
      return this.conn.peerCertificateChain();
   }

   public List peerCertificates() throws SSLPeerUnverifiedException {
      return this.conn.peerCertificates();
   }

   public String indicatedServerName() {
      return this.conn.indicatedServerName();
   }

   public String applicationLayerProtocol() {
      return null;
   }
}
