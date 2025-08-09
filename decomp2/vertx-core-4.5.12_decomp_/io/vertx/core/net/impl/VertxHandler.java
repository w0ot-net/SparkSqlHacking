package io.vertx.core.net.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.timeout.IdleStateEvent;
import io.vertx.core.Handler;
import io.vertx.core.buffer.impl.VertxByteBufAllocator;
import java.util.function.Function;

public final class VertxHandler extends ChannelDuplexHandler {
   private final Function connectionFactory;
   private ConnectionBase conn;
   private Handler addHandler;
   private Handler removeHandler;

   public static VertxHandler create(Function connectionFactory) {
      return new VertxHandler(connectionFactory);
   }

   private VertxHandler(Function connectionFactory) {
      this.connectionFactory = connectionFactory;
   }

   public static ByteBuf safeBuffer(ByteBuf buf) {
      if (buf != Unpooled.EMPTY_BUFFER && (buf.alloc() instanceof PooledByteBufAllocator || buf instanceof CompositeByteBuf)) {
         ByteBuf var2;
         try {
            if (!buf.isReadable()) {
               ByteBuf var6 = Unpooled.EMPTY_BUFFER;
               return var6;
            }

            ByteBuf buffer = VertxByteBufAllocator.DEFAULT.heapBuffer(buf.readableBytes());
            buffer.writeBytes(buf, buf.readerIndex(), buf.readableBytes());
            var2 = buffer;
         } finally {
            buf.release();
         }

         return var2;
      } else {
         return buf;
      }
   }

   private void setConnection(ConnectionBase connection) {
      this.conn = connection;
      if (this.addHandler != null) {
         this.addHandler.handle(connection);
      }

   }

   public void handlerAdded(ChannelHandlerContext ctx) {
      this.setConnection((ConnectionBase)this.connectionFactory.apply(ctx));
   }

   public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
      if (this.removeHandler != null) {
         Handler<C> handler = this.removeHandler;
         this.removeHandler = null;
         handler.handle(this.conn);
      }

   }

   public VertxHandler addHandler(Handler handler) {
      this.addHandler = handler;
      return this;
   }

   public VertxHandler removeHandler(Handler handler) {
      this.removeHandler = handler;
      return this;
   }

   public ConnectionBase getConnection() {
      return this.conn;
   }

   public void channelWritabilityChanged(ChannelHandlerContext ctx) {
      C conn = (C)this.getConnection();
      conn.handleInterestedOpsChanged();
   }

   public void exceptionCaught(ChannelHandlerContext chctx, Throwable t) {
      C connection = (C)this.getConnection();
      if (connection != null) {
         connection.handleException(t);
      }

      chctx.close();
   }

   public void channelInactive(ChannelHandlerContext chctx) {
      this.conn.handleClosed();
   }

   public void channelReadComplete(ChannelHandlerContext ctx) {
      this.conn.endReadAndFlush();
   }

   public void channelRead(ChannelHandlerContext chctx, Object msg) {
      this.conn.read(msg);
   }

   public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
      this.conn.close(promise);
   }

   public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
      if (evt instanceof IdleStateEvent) {
         this.conn.handleIdle((IdleStateEvent)evt);
      }

      this.conn.handleEvent(evt);
   }
}
