package org.apache.zookeeper.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.cert.Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.jute.BinaryInputArchive;
import org.apache.jute.Record;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.proto.ConnectRequest;
import org.apache.zookeeper.proto.ReplyHeader;
import org.apache.zookeeper.proto.RequestHeader;
import org.apache.zookeeper.proto.WatcherEvent;
import org.apache.zookeeper.server.command.CommandExecutor;
import org.apache.zookeeper.server.command.FourLetterCommands;
import org.apache.zookeeper.server.command.NopCommand;
import org.apache.zookeeper.server.command.SetTraceMaskCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServerCnxn extends ServerCnxn {
   private static final Logger LOG = LoggerFactory.getLogger(NettyServerCnxn.class);
   private final Channel channel;
   private CompositeByteBuf queuedBuffer;
   private final AtomicBoolean throttled = new AtomicBoolean(false);
   private ByteBuffer bb;
   private final ByteBuffer bbLen = ByteBuffer.allocate(4);
   private long sessionId;
   private int sessionTimeout;
   private Certificate[] clientChain;
   private volatile boolean closingChannel;
   private final NettyServerCnxnFactory factory;
   private boolean initialized;
   public int readIssuedAfterReadComplete;
   private volatile HandshakeState handshakeState;
   private final GenericFutureListener onSendBufferDoneListener;

   NettyServerCnxn(Channel channel, ZooKeeperServer zks, NettyServerCnxnFactory factory) {
      super(zks);
      this.handshakeState = NettyServerCnxn.HandshakeState.NONE;
      this.onSendBufferDoneListener = (f) -> {
         if (f.isSuccess()) {
            this.packetSent();
         }

      };
      this.channel = channel;
      this.closingChannel = false;
      this.factory = factory;
      if (this.factory.login != null) {
         this.zooKeeperSaslServer = new ZooKeeperSaslServer(factory.login);
      }

      InetAddress addr = ((InetSocketAddress)channel.remoteAddress()).getAddress();
      this.addAuthInfo(new Id("ip", addr.getHostAddress()));
   }

   public void close(ServerCnxn.DisconnectReason reason) {
      this.disconnectReason = reason;
      this.close();
   }

   public void close() {
      this.closingChannel = true;
      LOG.debug("close called for session id: 0x{}", Long.toHexString(this.sessionId));
      this.setStale();
      this.factory.unregisterConnection(this);
      if (!this.factory.cnxns.remove(this)) {
         LOG.debug("cnxns size:{}", this.factory.cnxns.size());
         if (this.channel.isOpen()) {
            this.channel.close();
         }

      } else {
         LOG.debug("close in progress for session id: 0x{}", Long.toHexString(this.sessionId));
         this.factory.removeCnxnFromSessionMap(this);
         this.factory.removeCnxnFromIpMap(this, ((InetSocketAddress)this.channel.remoteAddress()).getAddress());
         if (this.zkServer != null) {
            this.zkServer.removeCnxn(this);
         }

         if (this.channel.isOpen()) {
            this.channel.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(new ChannelFutureListener() {
               public void operationComplete(ChannelFuture future) {
                  future.channel().close().addListener((f) -> NettyServerCnxn.this.releaseQueuedBuffer());
               }
            });
         } else {
            ServerMetrics.getMetrics().CONNECTION_DROP_COUNT.add(1L);
            this.channel.eventLoop().execute(this::releaseQueuedBuffer);
         }

      }
   }

   public long getSessionId() {
      return this.sessionId;
   }

   public int getSessionTimeout() {
      return this.sessionTimeout;
   }

   public void process(WatchedEvent event, List znodeAcl) {
      try {
         this.zkServer.checkACL(this, znodeAcl, 1, this.getAuthInfo(), event.getPath(), (List)null);
      } catch (KeeperException.NoAuthException var7) {
         if (LOG.isTraceEnabled()) {
            ZooTrace.logTraceMessage(LOG, 64L, "Not delivering event " + event + " to 0x" + Long.toHexString(this.sessionId) + " (filtered by ACL)");
         }

         return;
      }

      ReplyHeader h = new ReplyHeader(-1, event.getZxid(), 0);
      if (LOG.isTraceEnabled()) {
         ZooTrace.logTraceMessage(LOG, 64L, "Deliver event " + event + " to 0x" + Long.toHexString(this.sessionId) + " through " + this);
      }

      WatcherEvent e = event.getWrapper();

      try {
         int responseSize = this.sendResponse(h, e, "notification");
         ServerMetrics.getMetrics().WATCH_BYTES.add((long)responseSize);
      } catch (IOException e1) {
         LOG.debug("Problem sending to {}", this.getRemoteSocketAddress(), e1);
         this.close();
      }

   }

   public int sendResponse(ReplyHeader h, Record r, String tag, String cacheKey, Stat stat, int opCode) throws IOException {
      if (!this.closingChannel && this.channel.isOpen()) {
         ByteBuffer[] bb = this.serialize(h, r, tag, cacheKey, stat, opCode);
         int responseSize = bb[0].getInt();
         bb[0].rewind();
         this.sendBuffer(bb);
         this.decrOutstandingAndCheckThrottle(h);
         return responseSize;
      } else {
         return 0;
      }
   }

   public void setSessionId(long sessionId) {
      this.sessionId = sessionId;
      this.factory.addSession(sessionId, this);
   }

   public void sendBuffer(ByteBuffer... buffers) {
      if (buffers.length == 1 && buffers[0] == ServerCnxnFactory.closeConn) {
         this.close(ServerCnxn.DisconnectReason.CLIENT_CLOSED_CONNECTION);
      } else {
         this.channel.writeAndFlush(Unpooled.wrappedBuffer(buffers)).addListener(this.onSendBufferDoneListener);
      }
   }

   private boolean checkFourLetterWord(Channel channel, ByteBuf message, int len) {
      if (!FourLetterCommands.isKnown(len)) {
         return false;
      } else {
         String cmd = FourLetterCommands.getCommandString(len);
         channel.config().setAutoRead(false);
         this.packetReceived(4L);
         PrintWriter pwriter = new PrintWriter(new BufferedWriter(new SendBufferWriter()));
         if (!FourLetterCommands.isEnabled(cmd)) {
            LOG.debug("Command {} is not executed because it is not in the whitelist.", cmd);
            NopCommand nopCmd = new NopCommand(pwriter, this, cmd + " is not executed because it is not in the whitelist.");
            nopCmd.start();
            return true;
         } else {
            LOG.info("Processing {} command from {}", cmd, channel.remoteAddress());
            if (len == FourLetterCommands.setTraceMaskCmd) {
               ByteBuffer mask = ByteBuffer.allocate(8);
               message.readBytes(mask);
               mask.flip();
               long traceMask = mask.getLong();
               ZooTrace.setTextTraceLevel(traceMask);
               SetTraceMaskCommand setMask = new SetTraceMaskCommand(pwriter, this, traceMask);
               setMask.start();
               return true;
            } else {
               CommandExecutor commandExecutor = new CommandExecutor();
               return commandExecutor.execute(this, pwriter, len, this.zkServer, this.factory);
            }
         }
      }
   }

   private void checkIsInEventLoop(String callerMethodName) {
      if (!this.channel.eventLoop().inEventLoop()) {
         throw new IllegalStateException(callerMethodName + "() called from non-EventLoop thread");
      }
   }

   private void appendToQueuedBuffer(ByteBuf buf) {
      this.checkIsInEventLoop("appendToQueuedBuffer");
      if (this.queuedBuffer.numComponents() == this.queuedBuffer.maxNumComponents()) {
         this.queuedBuffer.consolidate();
      }

      this.queuedBuffer.addComponent(true, buf);
      ServerMetrics.getMetrics().NETTY_QUEUED_BUFFER.add((long)this.queuedBuffer.capacity());
   }

   void processMessage(ByteBuf buf) {
      this.checkIsInEventLoop("processMessage");
      LOG.debug("0x{} queuedBuffer: {}", Long.toHexString(this.sessionId), this.queuedBuffer);
      if (LOG.isTraceEnabled()) {
         LOG.trace("0x{} buf {}", Long.toHexString(this.sessionId), ByteBufUtil.hexDump(buf));
      }

      if (this.throttled.get()) {
         LOG.debug("Received message while throttled");
         if (this.queuedBuffer == null) {
            LOG.debug("allocating queue");
            this.queuedBuffer = this.channel.alloc().compositeBuffer();
         }

         this.appendToQueuedBuffer(buf.retainedDuplicate());
         if (LOG.isTraceEnabled()) {
            LOG.trace("0x{} queuedBuffer {}", Long.toHexString(this.sessionId), ByteBufUtil.hexDump(this.queuedBuffer));
         }
      } else {
         LOG.debug("not throttled");
         if (this.queuedBuffer != null) {
            this.appendToQueuedBuffer(buf.retainedDuplicate());
            this.processQueuedBuffer();
         } else {
            this.receiveMessage(buf);
            if (!this.closingChannel && buf.isReadable()) {
               if (LOG.isTraceEnabled()) {
                  LOG.trace("Before copy {}", buf);
               }

               if (this.queuedBuffer == null) {
                  this.queuedBuffer = this.channel.alloc().compositeBuffer();
               }

               this.appendToQueuedBuffer(buf.retainedSlice(buf.readerIndex(), buf.readableBytes()));
               if (LOG.isTraceEnabled()) {
                  LOG.trace("Copy is {}", this.queuedBuffer);
                  LOG.trace("0x{} queuedBuffer {}", Long.toHexString(this.sessionId), ByteBufUtil.hexDump(this.queuedBuffer));
               }
            }
         }
      }

   }

   void processQueuedBuffer() {
      this.checkIsInEventLoop("processQueuedBuffer");
      if (this.queuedBuffer != null) {
         if (LOG.isTraceEnabled()) {
            LOG.trace("processing queue 0x{} queuedBuffer {}", Long.toHexString(this.sessionId), ByteBufUtil.hexDump(this.queuedBuffer));
         }

         this.receiveMessage(this.queuedBuffer);
         if (this.closingChannel) {
            LOG.debug("Processed queue - channel closed, dropping remaining bytes");
         } else if (!this.queuedBuffer.isReadable()) {
            LOG.debug("Processed queue - no bytes remaining");
            this.releaseQueuedBuffer();
         } else {
            LOG.debug("Processed queue - bytes remaining");
            this.queuedBuffer.discardReadComponents();
         }
      } else {
         LOG.debug("queue empty");
      }

   }

   private void releaseQueuedBuffer() {
      this.checkIsInEventLoop("releaseQueuedBuffer");
      if (this.queuedBuffer != null) {
         this.queuedBuffer.release();
         this.queuedBuffer = null;
      }

   }

   private void receiveMessage(ByteBuf message) {
      this.checkIsInEventLoop("receiveMessage");

      try {
         while(message.isReadable() && !this.throttled.get()) {
            if (this.bb != null) {
               if (LOG.isTraceEnabled()) {
                  LOG.trace("message readable {} bb len {} {}", new Object[]{message.readableBytes(), this.bb.remaining(), this.bb});
                  ByteBuffer dat = this.bb.duplicate();
                  dat.flip();
                  LOG.trace("0x{} bb {}", Long.toHexString(this.sessionId), ByteBufUtil.hexDump(Unpooled.wrappedBuffer(dat)));
               }

               if (this.bb.remaining() > message.readableBytes()) {
                  int newLimit = this.bb.position() + message.readableBytes();
                  this.bb.limit(newLimit);
               }

               message.readBytes(this.bb);
               this.bb.limit(this.bb.capacity());
               if (LOG.isTraceEnabled()) {
                  LOG.trace("after readBytes message readable {} bb len {} {}", new Object[]{message.readableBytes(), this.bb.remaining(), this.bb});
                  ByteBuffer dat = this.bb.duplicate();
                  dat.flip();
                  LOG.trace("after readbytes 0x{} bb {}", Long.toHexString(this.sessionId), ByteBufUtil.hexDump(Unpooled.wrappedBuffer(dat)));
               }

               if (this.bb.remaining() == 0) {
                  this.bb.flip();
                  this.packetReceived((long)(4 + this.bb.remaining()));
                  ZooKeeperServer zks = this.zkServer;
                  if (zks == null || !zks.isRunning()) {
                     throw new IOException("ZK down");
                  }

                  if (this.initialized) {
                     RequestHeader h = new RequestHeader();
                     ByteBufferInputStream.byteBuffer2Record(this.bb, h);
                     RequestRecord request = RequestRecord.fromBytes(this.bb.slice());
                     zks.processPacket(this, h, request);
                  } else {
                     LOG.debug("got conn req request from {}", this.getRemoteSocketAddress());
                     BinaryInputArchive bia = BinaryInputArchive.getArchive(new ByteBufferInputStream(this.bb));
                     ConnectRequest request = this.protocolManager.deserializeConnectRequest(bia);
                     zks.processConnectRequest(this, request);
                     this.initialized = true;
                  }

                  this.bb = null;
               }
            } else {
               if (LOG.isTraceEnabled()) {
                  LOG.trace("message readable {} bblenrem {}", message.readableBytes(), this.bbLen.remaining());
                  ByteBuffer dat = this.bbLen.duplicate();
                  dat.flip();
                  LOG.trace("0x{} bbLen {}", Long.toHexString(this.sessionId), ByteBufUtil.hexDump(Unpooled.wrappedBuffer(dat)));
               }

               if (message.readableBytes() < this.bbLen.remaining()) {
                  this.bbLen.limit(this.bbLen.position() + message.readableBytes());
               }

               message.readBytes(this.bbLen);
               this.bbLen.limit(this.bbLen.capacity());
               if (this.bbLen.remaining() == 0) {
                  this.bbLen.flip();
                  if (LOG.isTraceEnabled()) {
                     LOG.trace("0x{} bbLen {}", Long.toHexString(this.sessionId), ByteBufUtil.hexDump(Unpooled.wrappedBuffer(this.bbLen)));
                  }

                  int len = this.bbLen.getInt();
                  if (LOG.isTraceEnabled()) {
                     LOG.trace("0x{} bbLen len is {}", Long.toHexString(this.sessionId), len);
                  }

                  this.bbLen.clear();
                  if (!this.initialized && this.checkFourLetterWord(this.channel, message, len)) {
                     return;
                  }

                  if (len >= 0 && len <= BinaryInputArchive.maxBuffer) {
                     ZooKeeperServer zks = this.zkServer;
                     if (zks != null && zks.isRunning()) {
                        zks.checkRequestSizeWhenReceivingMessage(len);
                        this.bb = ByteBuffer.allocate(len);
                        continue;
                     }

                     LOG.info("Closing connection to {} because the server is not ready", this.getRemoteSocketAddress());
                     this.close(ServerCnxn.DisconnectReason.IO_EXCEPTION);
                     return;
                  }

                  throw new IOException("Len error " + len);
               }
            }
         }
      } catch (IOException e) {
         LOG.warn("Closing connection to {}", this.getRemoteSocketAddress(), e);
         this.close(ServerCnxn.DisconnectReason.IO_EXCEPTION);
      } catch (ClientCnxnLimitException e) {
         ServerMetrics.getMetrics().CONNECTION_REJECTED.add(1L);
         LOG.debug("Closing connection to {}", this.getRemoteSocketAddress(), e);
         this.close(ServerCnxn.DisconnectReason.CLIENT_RATE_LIMIT);
      }

   }

   public void disableRecv(boolean waitDisableRecv) {
      if (this.throttled.compareAndSet(false, true)) {
         LOG.debug("Throttling - disabling recv {}", this);
         this.channel.pipeline().fireUserEventTriggered(NettyServerCnxn.ReadEvent.DISABLE);
      }

   }

   public void enableRecv() {
      if (this.throttled.compareAndSet(true, false)) {
         LOG.debug("Sending unthrottle event {}", this);
         this.channel.pipeline().fireUserEventTriggered(NettyServerCnxn.ReadEvent.ENABLE);
      }

   }

   public void setSessionTimeout(int sessionTimeout) {
      this.sessionTimeout = sessionTimeout;
   }

   public int getInterestOps() {
      if (this.channel != null && this.channel.isOpen()) {
         int interestOps = 0;
         if (!this.throttled.get()) {
            interestOps |= 1;
         }

         if (!this.channel.isWritable()) {
            interestOps |= 4;
         }

         return interestOps;
      } else {
         return 0;
      }
   }

   public InetSocketAddress getRemoteSocketAddress() {
      return (InetSocketAddress)this.channel.remoteAddress();
   }

   public void sendCloseSession() {
      this.sendBuffer(ServerCnxnFactory.closeConn);
   }

   protected ServerStats serverStats() {
      return this.zkServer == null ? null : this.zkServer.serverStats();
   }

   public boolean isSecure() {
      return this.factory.secure;
   }

   public Certificate[] getClientCertificateChain() {
      return this.clientChain == null ? null : (Certificate[])Arrays.copyOf(this.clientChain, this.clientChain.length);
   }

   public void setClientCertificateChain(Certificate[] chain) {
      if (chain == null) {
         this.clientChain = null;
      } else {
         this.clientChain = (Certificate[])Arrays.copyOf(chain, chain.length);
      }

   }

   Channel getChannel() {
      return this.channel;
   }

   public int getQueuedReadableBytes() {
      this.checkIsInEventLoop("getQueuedReadableBytes");
      return this.queuedBuffer != null ? this.queuedBuffer.readableBytes() : 0;
   }

   public void setHandshakeState(HandshakeState state) {
      this.handshakeState = state;
   }

   public HandshakeState getHandshakeState() {
      return this.handshakeState;
   }

   public static enum HandshakeState {
      NONE,
      STARTED,
      FINISHED;
   }

   private class SendBufferWriter extends Writer {
      private StringBuffer sb;

      private SendBufferWriter() {
         this.sb = new StringBuffer();
      }

      private void checkFlush(boolean force) {
         if (force && this.sb.length() > 0 || this.sb.length() > 2048) {
            NettyServerCnxn.this.sendBuffer(ByteBuffer.wrap(this.sb.toString().getBytes(StandardCharsets.UTF_8)));
            this.sb.setLength(0);
         }

      }

      public void close() throws IOException {
         if (this.sb != null) {
            this.checkFlush(true);
            this.sb = null;
         }
      }

      public void flush() throws IOException {
         this.checkFlush(true);
      }

      public void write(char[] cbuf, int off, int len) throws IOException {
         this.sb.append(cbuf, off, len);
         this.checkFlush(false);
      }
   }

   static enum ReadEvent {
      DISABLE,
      ENABLE;
   }
}
