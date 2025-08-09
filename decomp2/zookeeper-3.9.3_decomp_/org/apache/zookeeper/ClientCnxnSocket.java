package org.apache.zookeeper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.text.MessageFormat;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.jute.BinaryInputArchive;
import org.apache.zookeeper.client.ZKClientConfig;
import org.apache.zookeeper.common.Time;
import org.apache.zookeeper.compat.ProtocolManager;
import org.apache.zookeeper.proto.ConnectResponse;
import org.apache.zookeeper.server.ByteBufferInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class ClientCnxnSocket {
   private static final Logger LOG = LoggerFactory.getLogger(ClientCnxnSocket.class);
   private final ProtocolManager protocolManager = new ProtocolManager();
   protected boolean initialized;
   protected final ByteBuffer lenBuffer = ByteBuffer.allocateDirect(4);
   protected ByteBuffer incomingBuffer;
   protected final AtomicLong sentCount;
   protected final AtomicLong recvCount;
   protected long lastHeard;
   protected long lastSend;
   protected long now;
   protected ClientCnxn.SendThread sendThread;
   protected LinkedBlockingDeque outgoingQueue;
   protected ZKClientConfig clientConfig;
   private int packetLen;
   protected long sessionId;

   ClientCnxnSocket() {
      this.incomingBuffer = this.lenBuffer;
      this.sentCount = new AtomicLong(0L);
      this.recvCount = new AtomicLong(0L);
      this.packetLen = 1048575;
   }

   void introduce(ClientCnxn.SendThread sendThread, long sessionId, LinkedBlockingDeque outgoingQueue) {
      this.sendThread = sendThread;
      this.sessionId = sessionId;
      this.outgoingQueue = outgoingQueue;
   }

   void updateNow() {
      this.now = Time.currentElapsedTime();
   }

   int getIdleRecv() {
      return (int)(this.now - this.lastHeard);
   }

   int getIdleSend() {
      return (int)(this.now - this.lastSend);
   }

   long getSentCount() {
      return this.sentCount.get();
   }

   long getRecvCount() {
      return this.recvCount.get();
   }

   void updateLastHeard() {
      this.lastHeard = this.now;
   }

   void updateLastSend() {
      this.lastSend = this.now;
   }

   void updateLastSendAndHeard() {
      this.lastSend = this.now;
      this.lastHeard = this.now;
   }

   void readLength() throws IOException {
      int len = this.incomingBuffer.getInt();
      if (len >= 0 && len <= this.packetLen) {
         this.incomingBuffer = ByteBuffer.allocate(len);
      } else {
         throw new IOException("Packet len " + len + " is out of range!");
      }
   }

   void readConnectResult() throws IOException {
      if (LOG.isTraceEnabled()) {
         StringBuilder buf = new StringBuilder("0x[");

         for(byte b : this.incomingBuffer.array()) {
            buf.append(Integer.toHexString(b)).append(",");
         }

         buf.append("]");
         if (LOG.isTraceEnabled()) {
            LOG.trace("readConnectResult {} {}", this.incomingBuffer.remaining(), buf);
         }
      }

      ByteBufferInputStream bbis = new ByteBufferInputStream(this.incomingBuffer);
      BinaryInputArchive bbia = BinaryInputArchive.getArchive(bbis);
      ConnectResponse conRsp = this.protocolManager.deserializeConnectResponse(bbia);
      if (!this.protocolManager.isReadonlyAvailable()) {
         LOG.warn("Connected to an old server; r-o mode will be unavailable");
      }

      this.sessionId = conRsp.getSessionId();
      this.sendThread.onConnected(conRsp.getTimeOut(), this.sessionId, conRsp.getPasswd(), conRsp.getReadOnly());
   }

   abstract boolean isConnected();

   abstract void connect(InetSocketAddress var1) throws IOException;

   abstract SocketAddress getRemoteSocketAddress();

   abstract SocketAddress getLocalSocketAddress();

   abstract void cleanup();

   abstract void packetAdded();

   abstract void onClosing();

   abstract void saslCompleted();

   abstract void connectionPrimed();

   abstract void doTransport(int var1, Queue var2, ClientCnxn var3) throws IOException, InterruptedException;

   abstract void testableCloseSocket() throws IOException;

   abstract void close();

   abstract void sendPacket(ClientCnxn.Packet var1) throws IOException;

   protected void initProperties() throws IOException {
      try {
         this.packetLen = this.clientConfig.getInt("jute.maxbuffer", 1048575);
         LOG.info("{} value is {} Bytes", "jute.maxbuffer", this.packetLen);
      } catch (NumberFormatException var3) {
         String msg = MessageFormat.format("Configured value {0} for property {1} can not be parsed to int", this.clientConfig.getProperty("jute.maxbuffer"), "jute.maxbuffer");
         LOG.error(msg);
         throw new IOException(msg);
      }
   }
}
