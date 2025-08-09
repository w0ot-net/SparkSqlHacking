package org.apache.zookeeper;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.net.ssl.SSLException;
import org.apache.jute.Record;
import org.apache.zookeeper.client.ZKClientConfig;
import org.apache.zookeeper.common.ClientX509Util;
import org.apache.zookeeper.common.NettyUtils;
import org.apache.zookeeper.common.X509Exception;
import org.apache.zookeeper.proto.ReplyHeader;
import org.apache.zookeeper.proto.RequestHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientCnxnSocketNetty extends ClientCnxnSocket {
   private static final Logger LOG = LoggerFactory.getLogger(ClientCnxnSocketNetty.class);
   private final EventLoopGroup eventLoopGroup;
   private Channel channel;
   private CountDownLatch firstConnect;
   private ChannelFuture connectFuture;
   private final Lock connectLock = new ReentrantLock();
   private final AtomicBoolean disconnected = new AtomicBoolean();
   private final AtomicBoolean needSasl = new AtomicBoolean();
   private final Semaphore waitSasl = new Semaphore(0);
   private static final AtomicReference TEST_ALLOCATOR = new AtomicReference((Object)null);
   private final GenericFutureListener onSendPktDoneListener = (f) -> {
      if (f.isSuccess()) {
         this.sentCount.getAndIncrement();
      }

   };

   ClientCnxnSocketNetty(ZKClientConfig clientConfig) throws IOException {
      this.clientConfig = clientConfig;
      this.eventLoopGroup = NettyUtils.newNioOrEpollEventLoopGroup(1);
      this.initProperties();
   }

   boolean isConnected() {
      this.connectLock.lock();

      boolean var1;
      try {
         var1 = this.channel != null || this.connectFuture != null;
      } finally {
         this.connectLock.unlock();
      }

      return var1;
   }

   private Bootstrap configureBootstrapAllocator(Bootstrap bootstrap) {
      ByteBufAllocator testAllocator = (ByteBufAllocator)TEST_ALLOCATOR.get();
      return testAllocator != null ? (Bootstrap)bootstrap.option(ChannelOption.ALLOCATOR, testAllocator) : bootstrap;
   }

   void connect(InetSocketAddress addr) throws IOException {
      this.firstConnect = new CountDownLatch(1);
      Bootstrap bootstrap = (Bootstrap)((Bootstrap)((Bootstrap)((Bootstrap)((Bootstrap)(new Bootstrap()).group(this.eventLoopGroup)).channel(NettyUtils.nioOrEpollSocketChannel())).option(ChannelOption.SO_LINGER, -1)).option(ChannelOption.TCP_NODELAY, true)).handler(new ZKClientPipelineFactory(addr.getHostString(), addr.getPort()));
      bootstrap = this.configureBootstrapAllocator(bootstrap);
      bootstrap.validate();
      this.connectLock.lock();

      try {
         this.connectFuture = bootstrap.connect(addr);
         this.connectFuture.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
               boolean connected = false;
               ClientCnxnSocketNetty.this.connectLock.lock();

               try {
                  if (channelFuture.isSuccess()) {
                     if (ClientCnxnSocketNetty.this.connectFuture == null) {
                        ClientCnxnSocketNetty.LOG.info("connect attempt cancelled");
                        channelFuture.channel().close();
                        return;
                     }

                     ClientCnxnSocketNetty.this.channel = channelFuture.channel();
                     ClientCnxnSocketNetty.this.disconnected.set(false);
                     ClientCnxnSocketNetty.this.initialized = false;
                     ClientCnxnSocketNetty.this.lenBuffer.clear();
                     ClientCnxnSocketNetty.this.incomingBuffer = ClientCnxnSocketNetty.this.lenBuffer;
                     ClientCnxnSocketNetty.this.sendThread.primeConnection();
                     ClientCnxnSocketNetty.this.updateNow();
                     ClientCnxnSocketNetty.this.updateLastSendAndHeard();
                     if (ClientCnxnSocketNetty.this.sendThread.tunnelAuthInProgress()) {
                        ClientCnxnSocketNetty.this.waitSasl.drainPermits();
                        ClientCnxnSocketNetty.this.needSasl.set(true);
                        ClientCnxnSocketNetty.this.sendPrimePacket();
                     } else {
                        ClientCnxnSocketNetty.this.needSasl.set(false);
                     }

                     connected = true;
                     return;
                  }

                  ClientCnxnSocketNetty.LOG.warn("future isn't success.", channelFuture.cause());
               } finally {
                  ClientCnxnSocketNetty.this.connectFuture = null;
                  ClientCnxnSocketNetty.this.connectLock.unlock();
                  if (connected) {
                     ClientCnxnSocketNetty.LOG.info("channel is connected: {}", channelFuture.channel());
                  }

                  ClientCnxnSocketNetty.this.wakeupCnxn();
                  ClientCnxnSocketNetty.this.firstConnect.countDown();
               }

            }
         });
      } finally {
         this.connectLock.unlock();
      }

   }

   void cleanup() {
      this.connectLock.lock();

      try {
         if (this.connectFuture != null) {
            this.connectFuture.cancel(false);
            this.connectFuture = null;
         }

         if (this.channel != null) {
            this.channel.close();
            this.channel = null;
         }
      } finally {
         this.connectLock.unlock();
      }

      Iterator iter = this.outgoingQueue.iterator();

      while(iter.hasNext()) {
         ClientCnxn.Packet p = (ClientCnxn.Packet)iter.next();
         if (p == ClientCnxnSocketNetty.WakeupPacket.getInstance()) {
            iter.remove();
         }
      }

   }

   void close() {
      this.eventLoopGroup.shutdownGracefully();
   }

   void saslCompleted() {
      this.needSasl.set(false);
      this.waitSasl.release();
   }

   void connectionPrimed() {
   }

   void packetAdded() {
   }

   void onClosing() {
      if (this.firstConnect != null) {
         this.firstConnect.countDown();
      }

      this.wakeupCnxn();
      LOG.info("channel is told closing");
   }

   private void wakeupCnxn() {
      if (this.needSasl.get()) {
         this.waitSasl.release();
      }

      if (this.outgoingQueue != null) {
         this.outgoingQueue.add(ClientCnxnSocketNetty.WakeupPacket.getInstance());
      }

   }

   void doTransport(int waitTimeOut, Queue pendingQueue, ClientCnxn cnxn) throws IOException, InterruptedException {
      try {
         if (this.firstConnect.await((long)waitTimeOut, TimeUnit.MILLISECONDS)) {
            ClientCnxn.Packet head = null;
            if (this.needSasl.get()) {
               if (!this.waitSasl.tryAcquire((long)waitTimeOut, TimeUnit.MILLISECONDS)) {
                  return;
               }
            } else {
               head = (ClientCnxn.Packet)this.outgoingQueue.poll((long)waitTimeOut, TimeUnit.MILLISECONDS);
            }

            if (!this.sendThread.getZkState().isAlive()) {
               this.addBack(head);
               return;
            }

            if (this.disconnected.get()) {
               this.addBack(head);
               throw new ClientCnxn.EndOfStreamException("channel for sessionid 0x" + Long.toHexString(this.sessionId) + " is lost");
            }

            if (head != null) {
               this.doWrite(pendingQueue, head, cnxn);
            }

            return;
         }
      } finally {
         this.updateNow();
      }

   }

   private void addBack(ClientCnxn.Packet head) {
      if (head != null && head != ClientCnxnSocketNetty.WakeupPacket.getInstance()) {
         this.outgoingQueue.addFirst(head);
      }

   }

   private ChannelFuture sendPktAndFlush(ClientCnxn.Packet p) throws IOException {
      return this.sendPkt(p, true);
   }

   private ChannelFuture sendPktOnly(ClientCnxn.Packet p) throws IOException {
      return this.sendPkt(p, false);
   }

   private ChannelFuture sendPkt(ClientCnxn.Packet p, boolean doFlush) throws IOException {
      if (this.channel == null) {
         throw new IOException("channel has been closed");
      } else {
         p.createBB();
         this.updateLastSend();
         ByteBuf writeBuffer = Unpooled.wrappedBuffer(p.bb);
         ChannelFuture result = doFlush ? this.channel.writeAndFlush(writeBuffer) : this.channel.write(writeBuffer);
         result.addListener(this.onSendPktDoneListener);
         return result;
      }
   }

   private void sendPrimePacket() throws IOException {
      this.sendPktAndFlush((ClientCnxn.Packet)this.outgoingQueue.remove());
   }

   private void doWrite(Queue pendingQueue, ClientCnxn.Packet p, ClientCnxn cnxn) throws IOException {
      this.updateNow();
      boolean anyPacketsSent = false;

      while(true) {
         if (p != ClientCnxnSocketNetty.WakeupPacket.getInstance()) {
            if (p.requestHeader != null && p.requestHeader.getType() != 11 && p.requestHeader.getType() != 100) {
               p.requestHeader.setXid(cnxn.getXid());
               synchronized(pendingQueue) {
                  pendingQueue.add(p);
               }
            }

            this.sendPktOnly(p);
            anyPacketsSent = true;
         }

         if (this.outgoingQueue.isEmpty()) {
            if (anyPacketsSent) {
               this.channel.flush();
            }

            return;
         }

         p = (ClientCnxn.Packet)this.outgoingQueue.remove();
      }
   }

   void sendPacket(ClientCnxn.Packet p) throws IOException {
      this.sendPktAndFlush(p);
   }

   SocketAddress getRemoteSocketAddress() {
      Channel copiedChanRef = this.channel;
      return copiedChanRef == null ? null : copiedChanRef.remoteAddress();
   }

   SocketAddress getLocalSocketAddress() {
      Channel copiedChanRef = this.channel;
      return copiedChanRef == null ? null : copiedChanRef.localAddress();
   }

   void testableCloseSocket() throws IOException {
      Channel copiedChanRef = this.channel;
      if (copiedChanRef != null) {
         copiedChanRef.disconnect().awaitUninterruptibly();
      }

   }

   static void setTestAllocator(ByteBufAllocator allocator) {
      TEST_ALLOCATOR.set(allocator);
   }

   static void clearTestAllocator() {
      TEST_ALLOCATOR.set((Object)null);
   }

   private static class WakeupPacket {
      private static final ClientCnxn.Packet instance = new ClientCnxn.Packet((RequestHeader)null, (ReplyHeader)null, (Record)null, (Record)null, (ZooKeeper.WatchRegistration)null);

      protected WakeupPacket() {
      }

      public static ClientCnxn.Packet getInstance() {
         return instance;
      }
   }

   private class ZKClientPipelineFactory extends ChannelInitializer {
      private SslContext sslContext;
      private final String host;
      private final int port;

      private ZKClientPipelineFactory(String host, int port) {
         this.sslContext = null;
         this.host = host;
         this.port = port;
      }

      protected void initChannel(SocketChannel ch) throws Exception {
         ChannelPipeline pipeline = ch.pipeline();
         if (ClientCnxnSocketNetty.this.clientConfig.getBoolean("zookeeper.client.secure")) {
            this.initSSL(pipeline);
         }

         pipeline.addLast("handler", ClientCnxnSocketNetty.this.new ZKClientHandler());
      }

      private synchronized void initSSL(ChannelPipeline pipeline) throws X509Exception.KeyManagerException, X509Exception.TrustManagerException, SSLException {
         if (this.sslContext == null) {
            ClientX509Util x509Util = new ClientX509Util();

            try {
               this.sslContext = x509Util.createNettySslContextForClient(ClientCnxnSocketNetty.this.clientConfig);
            } catch (Throwable var6) {
               try {
                  x509Util.close();
               } catch (Throwable var5) {
                  var6.addSuppressed(var5);
               }

               throw var6;
            }

            x509Util.close();
         }

         pipeline.addLast("ssl", this.sslContext.newHandler(pipeline.channel().alloc(), this.host, this.port));
         ClientCnxnSocketNetty.LOG.info("SSL handler added for channel: {}", pipeline.channel());
      }
   }

   private class ZKClientHandler extends SimpleChannelInboundHandler {
      AtomicBoolean channelClosed;

      private ZKClientHandler() {
         this.channelClosed = new AtomicBoolean(false);
      }

      public void channelInactive(ChannelHandlerContext ctx) throws Exception {
         ClientCnxnSocketNetty.LOG.info("channel is disconnected: {}", ctx.channel());
         this.cleanup();
      }

      private void cleanup() {
         if (this.channelClosed.compareAndSet(false, true)) {
            ClientCnxnSocketNetty.this.disconnected.set(true);
            ClientCnxnSocketNetty.this.onClosing();
         }
      }

      protected void channelRead0(ChannelHandlerContext ctx, ByteBuf buf) throws Exception {
         ClientCnxnSocketNetty.this.updateNow();

         while(buf.isReadable()) {
            if (ClientCnxnSocketNetty.this.incomingBuffer.remaining() > buf.readableBytes()) {
               int newLimit = ClientCnxnSocketNetty.this.incomingBuffer.position() + buf.readableBytes();
               ClientCnxnSocketNetty.this.incomingBuffer.limit(newLimit);
            }

            buf.readBytes(ClientCnxnSocketNetty.this.incomingBuffer);
            ClientCnxnSocketNetty.this.incomingBuffer.limit(ClientCnxnSocketNetty.this.incomingBuffer.capacity());
            if (!ClientCnxnSocketNetty.this.incomingBuffer.hasRemaining()) {
               ClientCnxnSocketNetty.this.incomingBuffer.flip();
               if (ClientCnxnSocketNetty.this.incomingBuffer == ClientCnxnSocketNetty.this.lenBuffer) {
                  ClientCnxnSocketNetty.this.recvCount.getAndIncrement();
                  ClientCnxnSocketNetty.this.readLength();
               } else if (!ClientCnxnSocketNetty.this.initialized) {
                  ClientCnxnSocketNetty.this.readConnectResult();
                  ClientCnxnSocketNetty.this.lenBuffer.clear();
                  ClientCnxnSocketNetty.this.incomingBuffer = ClientCnxnSocketNetty.this.lenBuffer;
                  ClientCnxnSocketNetty.this.initialized = true;
                  ClientCnxnSocketNetty.this.updateLastHeard();
               } else {
                  ClientCnxnSocketNetty.this.sendThread.readResponse(ClientCnxnSocketNetty.this.incomingBuffer);
                  ClientCnxnSocketNetty.this.lenBuffer.clear();
                  ClientCnxnSocketNetty.this.incomingBuffer = ClientCnxnSocketNetty.this.lenBuffer;
                  ClientCnxnSocketNetty.this.updateLastHeard();
               }
            }
         }

         ClientCnxnSocketNetty.this.wakeupCnxn();
      }

      public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
         ClientCnxnSocketNetty.LOG.error("Unexpected throwable", cause);
         this.cleanup();
      }
   }
}
