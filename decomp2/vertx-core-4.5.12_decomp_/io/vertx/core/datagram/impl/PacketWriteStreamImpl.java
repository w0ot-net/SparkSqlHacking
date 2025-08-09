package io.vertx.core.datagram.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.WriteStream;

class PacketWriteStreamImpl implements WriteStream, Handler {
   private DatagramSocketImpl datagramSocket;
   private Handler exceptionHandler;
   private final int port;
   private final String host;

   PacketWriteStreamImpl(DatagramSocketImpl datagramSocket, int port, String host) {
      this.datagramSocket = datagramSocket;
      this.port = port;
      this.host = host;
   }

   public void handle(AsyncResult event) {
      if (event.failed() && this.exceptionHandler != null) {
         this.exceptionHandler.handle(event.cause());
      }

   }

   public PacketWriteStreamImpl exceptionHandler(Handler handler) {
      this.exceptionHandler = handler;
      return this;
   }

   public Future write(Buffer data) {
      Promise<Void> promise = Promise.promise();
      this.write((Buffer)data, promise);
      return promise.future();
   }

   public void write(Buffer data, Handler handler) {
      this.datagramSocket.send((Buffer)data, this.port, this.host, (ar) -> {
         this.handle(ar);
         if (handler != null) {
            handler.handle(ar.mapEmpty());
         }

      });
   }

   public PacketWriteStreamImpl setWriteQueueMaxSize(int maxSize) {
      return this;
   }

   public boolean writeQueueFull() {
      return false;
   }

   public PacketWriteStreamImpl drainHandler(Handler handler) {
      return this;
   }

   public Future end() {
      Promise<Void> promide = Promise.promise();
      this.end(promide);
      return promide.future();
   }

   public void end(Handler handler) {
      this.datagramSocket.close(handler);
   }
}
