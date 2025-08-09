package org.apache.zookeeper.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.security.cert.Certificate;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
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

public class NIOServerCnxn extends ServerCnxn {
   private static final Logger LOG = LoggerFactory.getLogger(NIOServerCnxn.class);
   private final NIOServerCnxnFactory factory;
   private final SocketChannel sock;
   private final NIOServerCnxnFactory.SelectorThread selectorThread;
   private final SelectionKey sk;
   private boolean initialized;
   private final ByteBuffer lenBuffer = ByteBuffer.allocate(4);
   protected ByteBuffer incomingBuffer;
   private final Queue outgoingBuffers;
   private int sessionTimeout;
   private long sessionId;
   private final boolean clientTcpKeepAlive;
   private final AtomicBoolean selectable;
   private final AtomicBoolean throttled;
   private static final ByteBuffer packetSentinel = ByteBuffer.allocate(0);

   public NIOServerCnxn(ZooKeeperServer zk, SocketChannel sock, SelectionKey sk, NIOServerCnxnFactory factory, NIOServerCnxnFactory.SelectorThread selectorThread) throws IOException {
      super(zk);
      this.incomingBuffer = this.lenBuffer;
      this.outgoingBuffers = new LinkedBlockingQueue();
      this.clientTcpKeepAlive = Boolean.getBoolean("zookeeper.clientTcpKeepAlive");
      this.selectable = new AtomicBoolean(true);
      this.throttled = new AtomicBoolean(false);
      this.sock = sock;
      this.sk = sk;
      this.factory = factory;
      this.selectorThread = selectorThread;
      if (this.factory.login != null) {
         this.zooKeeperSaslServer = new ZooKeeperSaslServer(factory.login);
      }

      sock.socket().setTcpNoDelay(true);
      sock.socket().setSoLinger(false, -1);
      sock.socket().setKeepAlive(this.clientTcpKeepAlive);
      InetAddress addr = ((InetSocketAddress)sock.socket().getRemoteSocketAddress()).getAddress();
      this.addAuthInfo(new Id("ip", addr.getHostAddress()));
      this.sessionTimeout = factory.sessionlessCnxnTimeout;
   }

   public void sendCloseSession() {
      this.sendBuffer(ServerCnxnFactory.closeConn);
   }

   void sendBufferSync(ByteBuffer bb) {
      try {
         if (bb != ServerCnxnFactory.closeConn) {
            if (this.sock.isOpen()) {
               this.sock.configureBlocking(true);
               this.sock.write(bb);
            }

            this.packetSent();
         }
      } catch (IOException ie) {
         LOG.error("Error sending data synchronously ", ie);
      }

   }

   public void sendBuffer(ByteBuffer... buffers) {
      if (LOG.isTraceEnabled()) {
         LOG.trace("Add a buffer to outgoingBuffers, sk {} is valid: {}", this.sk, this.sk.isValid());
      }

      synchronized(this.outgoingBuffers) {
         for(ByteBuffer buffer : buffers) {
            this.outgoingBuffers.add(buffer);
         }

         this.outgoingBuffers.add(packetSentinel);
      }

      this.requestInterestOpsUpdate();
   }

   private void handleFailedRead() throws ServerCnxn.EndOfStreamException {
      this.setStale();
      ServerMetrics.getMetrics().CONNECTION_DROP_COUNT.add(1L);
      throw new ServerCnxn.EndOfStreamException("Unable to read additional data from client, it probably closed the socket: address = " + this.sock.socket().getRemoteSocketAddress() + ", session = 0x" + Long.toHexString(this.sessionId), ServerCnxn.DisconnectReason.UNABLE_TO_READ_FROM_CLIENT);
   }

   private void readPayload() throws IOException, InterruptedException, ClientCnxnLimitException {
      if (this.incomingBuffer.remaining() != 0) {
         int rc = this.sock.read(this.incomingBuffer);
         if (rc < 0) {
            this.handleFailedRead();
         }
      }

      if (this.incomingBuffer.remaining() == 0) {
         this.incomingBuffer.flip();
         this.packetReceived((long)(4 + this.incomingBuffer.remaining()));
         if (!this.initialized) {
            this.readConnectRequest();
         } else {
            this.readRequest();
         }

         this.lenBuffer.clear();
         this.incomingBuffer = this.lenBuffer;
      }

   }

   public boolean isSelectable() {
      return this.sk.isValid() && this.selectable.get();
   }

   public void disableSelectable() {
      this.selectable.set(false);
   }

   public void enableSelectable() {
      this.selectable.set(true);
   }

   private void requestInterestOpsUpdate() {
      if (this.isSelectable()) {
         this.selectorThread.addInterestOpsUpdateRequest(this.sk);
      }

   }

   void handleWrite(SelectionKey k) throws IOException {
      if (!this.outgoingBuffers.isEmpty()) {
         ByteBuffer directBuffer = NIOServerCnxnFactory.getDirectBuffer();
         if (directBuffer == null) {
            ByteBuffer[] bufferList = new ByteBuffer[this.outgoingBuffers.size()];
            this.sock.write((ByteBuffer[])this.outgoingBuffers.toArray(bufferList));

            ByteBuffer bb;
            while((bb = (ByteBuffer)this.outgoingBuffers.peek()) != null) {
               if (bb == ServerCnxnFactory.closeConn) {
                  throw new ServerCnxn.CloseRequestException("close requested", ServerCnxn.DisconnectReason.CLIENT_CLOSED_CONNECTION);
               }

               if (bb == packetSentinel) {
                  this.packetSent();
               }

               if (bb.remaining() > 0) {
                  break;
               }

               this.outgoingBuffers.remove();
            }
         } else {
            directBuffer.clear();
            Iterator bufferList = this.outgoingBuffers.iterator();

            while(true) {
               if (bufferList.hasNext()) {
                  ByteBuffer b = (ByteBuffer)bufferList.next();
                  if (directBuffer.remaining() < b.remaining()) {
                     b = (ByteBuffer)b.slice().limit(directBuffer.remaining());
                  }

                  int p = b.position();
                  directBuffer.put(b);
                  b.position(p);
                  if (directBuffer.remaining() != 0) {
                     continue;
                  }
               }

               directBuffer.flip();
               int sent = this.sock.write(directBuffer);

               ByteBuffer bb;
               while((bb = (ByteBuffer)this.outgoingBuffers.peek()) != null) {
                  if (bb == ServerCnxnFactory.closeConn) {
                     throw new ServerCnxn.CloseRequestException("close requested", ServerCnxn.DisconnectReason.CLIENT_CLOSED_CONNECTION);
                  }

                  if (bb == packetSentinel) {
                     this.packetSent();
                  }

                  if (sent < bb.remaining()) {
                     bb.position(bb.position() + sent);
                     return;
                  }

                  sent -= bb.remaining();
                  this.outgoingBuffers.remove();
               }

               return;
            }
         }

      }
   }

   protected boolean isSocketOpen() {
      return this.sock.isOpen();
   }

   void doIO(SelectionKey k) throws InterruptedException {
      try {
         if (!this.isSocketOpen()) {
            LOG.warn("trying to do i/o on a null socket for session: 0x{}", Long.toHexString(this.sessionId));
            return;
         }

         if (k.isReadable()) {
            int rc = this.sock.read(this.incomingBuffer);
            if (rc < 0) {
               try {
                  this.handleFailedRead();
               } catch (ServerCnxn.EndOfStreamException e) {
                  LOG.info("{}", e.getMessage());
                  this.close(e.getReason());
                  return;
               }
            }

            if (this.incomingBuffer.remaining() == 0) {
               boolean isPayload;
               if (this.incomingBuffer == this.lenBuffer) {
                  this.incomingBuffer.flip();
                  isPayload = this.readLength(k);
                  this.incomingBuffer.clear();
               } else {
                  isPayload = true;
               }

               if (!isPayload) {
                  return;
               }

               this.readPayload();
            }
         }

         if (k.isWritable()) {
            this.handleWrite(k);
            if (!this.initialized && !this.getReadInterest() && !this.getWriteInterest()) {
               throw new ServerCnxn.CloseRequestException("responded to info probe", ServerCnxn.DisconnectReason.INFO_PROBE);
            }
         }
      } catch (CancelledKeyException e) {
         LOG.warn("CancelledKeyException causing close of session: 0x{}", Long.toHexString(this.sessionId));
         LOG.debug("CancelledKeyException stack trace", e);
         this.close(ServerCnxn.DisconnectReason.CANCELLED_KEY_EXCEPTION);
      } catch (ServerCnxn.CloseRequestException var6) {
         this.close();
      } catch (ServerCnxn.EndOfStreamException e) {
         LOG.warn("Unexpected exception", e);
         this.close(e.getReason());
      } catch (ClientCnxnLimitException e) {
         ServerMetrics.getMetrics().CONNECTION_REJECTED.add(1L);
         LOG.warn("Closing session 0x{}", Long.toHexString(this.sessionId), e);
         this.close(ServerCnxn.DisconnectReason.CLIENT_CNX_LIMIT);
      } catch (IOException e) {
         LOG.warn("Close of session 0x{}", Long.toHexString(this.sessionId), e);
         this.close(ServerCnxn.DisconnectReason.IO_EXCEPTION);
      }

   }

   protected void readRequest() throws IOException {
      RequestHeader h = new RequestHeader();
      ByteBufferInputStream.byteBuffer2Record(this.incomingBuffer, h);
      RequestRecord request = RequestRecord.fromBytes(this.incomingBuffer.slice());
      this.zkServer.processPacket(this, h, request);
   }

   private boolean getWriteInterest() {
      return !this.outgoingBuffers.isEmpty();
   }

   private boolean getReadInterest() {
      return !this.throttled.get();
   }

   public void disableRecv(boolean waitDisableRecv) {
      if (this.throttled.compareAndSet(false, true)) {
         this.requestInterestOpsUpdate();
      }

   }

   public void enableRecv() {
      if (this.throttled.compareAndSet(true, false)) {
         this.requestInterestOpsUpdate();
      }

   }

   private void readConnectRequest() throws IOException, ClientCnxnLimitException {
      if (!this.isZKServerRunning()) {
         throw new IOException("ZooKeeperServer not running");
      } else {
         BinaryInputArchive bia = BinaryInputArchive.getArchive(new ByteBufferInputStream(this.incomingBuffer));
         ConnectRequest request = this.protocolManager.deserializeConnectRequest(bia);
         this.zkServer.processConnectRequest(this, request);
         this.initialized = true;
      }
   }

   private boolean checkFourLetterWord(SelectionKey k, int len) throws IOException {
      if (!FourLetterCommands.isKnown(len)) {
         return false;
      } else {
         String cmd = FourLetterCommands.getCommandString(len);
         this.packetReceived(4L);
         if (k != null) {
            try {
               k.cancel();
            } catch (Exception e) {
               LOG.error("Error cancelling command selection key", e);
            }
         }

         PrintWriter pwriter = new PrintWriter(new BufferedWriter(new SendBufferWriter()));
         if (!FourLetterCommands.isEnabled(cmd)) {
            LOG.debug("Command {} is not executed because it is not in the whitelist.", cmd);
            NopCommand nopCmd = new NopCommand(pwriter, this, cmd + " is not executed because it is not in the whitelist.");
            nopCmd.start();
            return true;
         } else {
            LOG.info("Processing {} command from {}", cmd, this.sock.socket().getRemoteSocketAddress());
            if (len == FourLetterCommands.setTraceMaskCmd) {
               this.incomingBuffer = ByteBuffer.allocate(8);
               int rc = this.sock.read(this.incomingBuffer);
               if (rc < 0) {
                  throw new IOException("Read error");
               } else {
                  this.incomingBuffer.flip();
                  long traceMask = this.incomingBuffer.getLong();
                  ZooTrace.setTextTraceLevel(traceMask);
                  SetTraceMaskCommand setMask = new SetTraceMaskCommand(pwriter, this, traceMask);
                  setMask.start();
                  return true;
               }
            } else {
               CommandExecutor commandExecutor = new CommandExecutor();
               return commandExecutor.execute(this, pwriter, len, this.zkServer, this.factory);
            }
         }
      }
   }

   private boolean readLength(SelectionKey k) throws IOException {
      int len = this.lenBuffer.getInt();
      if (!this.initialized && this.checkFourLetterWord(this.sk, len)) {
         return false;
      } else if (len >= 0 && len <= BinaryInputArchive.maxBuffer) {
         if (!this.isZKServerRunning()) {
            throw new IOException("ZooKeeperServer not running");
         } else {
            this.zkServer.checkRequestSizeWhenReceivingMessage(len);
            this.incomingBuffer = ByteBuffer.allocate(len);
            return true;
         }
      } else {
         throw new IOException("Len error. A message from " + this.getRemoteSocketAddress() + " with advertised length of " + len + " is either a malformed message or too large to process (length is greater than jute.maxbuffer=" + BinaryInputArchive.maxBuffer + ")");
      }
   }

   public int getSessionTimeout() {
      return this.sessionTimeout;
   }

   public String toString() {
      return "ip: " + this.sock.socket().getRemoteSocketAddress() + " sessionId: 0x" + Long.toHexString(this.sessionId);
   }

   public void close(ServerCnxn.DisconnectReason reason) {
      this.disconnectReason = reason;
      this.close();
   }

   private void close() {
      this.setStale();
      if (this.factory.removeCnxn(this)) {
         if (this.zkServer != null) {
            this.zkServer.removeCnxn(this);
         }

         if (this.sk != null) {
            try {
               this.sk.cancel();
            } catch (Exception e) {
               LOG.debug("ignoring exception during selectionkey cancel", e);
            }
         }

         this.closeSock();
      }
   }

   private void closeSock() {
      if (this.sock.isOpen()) {
         String logMsg = String.format("Closed socket connection for client %s %s", this.sock.socket().getRemoteSocketAddress(), this.sessionId != 0L ? "which had sessionid 0x" + Long.toHexString(this.sessionId) : "(no session established for client)");
         LOG.debug(logMsg);
         closeSock(this.sock);
      }
   }

   public static void closeSock(SocketChannel sock) {
      if (sock.isOpen()) {
         try {
            sock.socket().shutdownOutput();
         } catch (IOException e) {
            LOG.debug("ignoring exception during output shutdown", e);
         }

         try {
            sock.socket().shutdownInput();
         } catch (IOException e) {
            LOG.debug("ignoring exception during input shutdown", e);
         }

         try {
            sock.socket().close();
         } catch (IOException e) {
            LOG.debug("ignoring exception during socket close", e);
         }

         try {
            sock.close();
         } catch (IOException e) {
            LOG.debug("ignoring exception during socketchannel close", e);
         }

      }
   }

   public int sendResponse(ReplyHeader h, Record r, String tag, String cacheKey, Stat stat, int opCode) {
      int responseSize = 0;

      try {
         ByteBuffer[] bb = this.serialize(h, r, tag, cacheKey, stat, opCode);
         responseSize = bb[0].getInt();
         bb[0].rewind();
         this.sendBuffer(bb);
         this.decrOutstandingAndCheckThrottle(h);
      } catch (Exception e) {
         LOG.warn("Unexpected exception. Destruction averted.", e);
      }

      return responseSize;
   }

   public void process(WatchedEvent event, List znodeAcl) {
      try {
         this.zkServer.checkACL(this, znodeAcl, 1, this.getAuthInfo(), event.getPath(), (List)null);
      } catch (KeeperException.NoAuthException var6) {
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
      int responseSize = this.sendResponse(h, e, "notification", (String)null, (Stat)null, -1);
      ServerMetrics.getMetrics().WATCH_BYTES.add((long)responseSize);
   }

   public long getSessionId() {
      return this.sessionId;
   }

   public void setSessionId(long sessionId) {
      this.sessionId = sessionId;
      this.factory.addSession(sessionId, this);
   }

   public void setSessionTimeout(int sessionTimeout) {
      this.sessionTimeout = sessionTimeout;
      this.factory.touchCnxn(this);
   }

   public int getInterestOps() {
      if (!this.isSelectable()) {
         return 0;
      } else {
         int interestOps = 0;
         if (this.getReadInterest()) {
            interestOps |= 1;
         }

         if (this.getWriteInterest()) {
            interestOps |= 4;
         }

         return interestOps;
      }
   }

   public InetSocketAddress getRemoteSocketAddress() {
      return !this.sock.isOpen() ? null : (InetSocketAddress)this.sock.socket().getRemoteSocketAddress();
   }

   public InetAddress getSocketAddress() {
      return !this.sock.isOpen() ? null : this.sock.socket().getInetAddress();
   }

   protected ServerStats serverStats() {
      return this.zkServer == null ? null : this.zkServer.serverStats();
   }

   public boolean isSecure() {
      return false;
   }

   public Certificate[] getClientCertificateChain() {
      throw new UnsupportedOperationException("SSL is unsupported in NIOServerCnxn");
   }

   public void setClientCertificateChain(Certificate[] chain) {
      throw new UnsupportedOperationException("SSL is unsupported in NIOServerCnxn");
   }

   private class SendBufferWriter extends Writer {
      private StringBuffer sb;

      private SendBufferWriter() {
         this.sb = new StringBuffer();
      }

      private void checkFlush(boolean force) {
         if (force && this.sb.length() > 0 || this.sb.length() > 2048) {
            NIOServerCnxn.this.sendBufferSync(ByteBuffer.wrap(this.sb.toString().getBytes(StandardCharsets.UTF_8)));
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
}
