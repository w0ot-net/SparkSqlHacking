package org.apache.zookeeper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.UnresolvedAddressException;
import java.nio.channels.UnsupportedAddressTypeException;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;
import org.apache.zookeeper.client.ZKClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientCnxnSocketNIO extends ClientCnxnSocket {
   private static final Logger LOG = LoggerFactory.getLogger(ClientCnxnSocketNIO.class);
   private final Selector selector = Selector.open();
   private SelectionKey sockKey;
   private SocketAddress localSocketAddress;
   private SocketAddress remoteSocketAddress;

   ClientCnxnSocketNIO(ZKClientConfig clientConfig) throws IOException {
      this.clientConfig = clientConfig;
      this.initProperties();
   }

   boolean isConnected() {
      return this.sockKey != null;
   }

   void doIO(Queue pendingQueue, ClientCnxn cnxn) throws InterruptedException, IOException {
      SocketChannel sock = (SocketChannel)this.sockKey.channel();
      if (sock == null) {
         throw new IOException("Socket is null!");
      } else {
         if (this.sockKey.isReadable()) {
            int rc = sock.read(this.incomingBuffer);
            if (rc < 0) {
               throw new ClientCnxn.EndOfStreamException("Unable to read additional data from server sessionid 0x" + Long.toHexString(this.sessionId) + ", likely server has closed socket");
            }

            if (!this.incomingBuffer.hasRemaining()) {
               this.incomingBuffer.flip();
               if (this.incomingBuffer == this.lenBuffer) {
                  this.recvCount.getAndIncrement();
                  this.readLength();
               } else if (!this.initialized) {
                  this.readConnectResult();
                  this.enableRead();
                  if (this.findSendablePacket(this.outgoingQueue, this.sendThread.tunnelAuthInProgress()) != null) {
                     this.enableWrite();
                  }

                  this.lenBuffer.clear();
                  this.incomingBuffer = this.lenBuffer;
                  this.updateLastHeard();
                  this.initialized = true;
               } else {
                  this.sendThread.readResponse(this.incomingBuffer);
                  this.lenBuffer.clear();
                  this.incomingBuffer = this.lenBuffer;
                  this.updateLastHeard();
               }
            }
         }

         if (this.sockKey.isWritable()) {
            ClientCnxn.Packet p = this.findSendablePacket(this.outgoingQueue, this.sendThread.tunnelAuthInProgress());
            if (p != null) {
               this.updateLastSend();
               if (p.bb == null) {
                  if (p.requestHeader != null && p.requestHeader.getType() != 11 && p.requestHeader.getType() != 100) {
                     p.requestHeader.setXid(cnxn.getXid());
                  }

                  p.createBB();
               }

               sock.write(p.bb);
               if (!p.bb.hasRemaining()) {
                  this.sentCount.getAndIncrement();
                  this.outgoingQueue.removeFirstOccurrence(p);
                  if (p.requestHeader != null && p.requestHeader.getType() != 11 && p.requestHeader.getType() != 100) {
                     synchronized(pendingQueue) {
                        pendingQueue.add(p);
                     }
                  }
               }
            }

            if (this.outgoingQueue.isEmpty()) {
               this.disableWrite();
            } else if (!this.initialized && p != null && !p.bb.hasRemaining()) {
               this.disableWrite();
            } else {
               this.enableWrite();
            }
         }

      }
   }

   private ClientCnxn.Packet findSendablePacket(LinkedBlockingDeque outgoingQueue, boolean tunneledAuthInProgres) {
      if (outgoingQueue.isEmpty()) {
         return null;
      } else if (((ClientCnxn.Packet)outgoingQueue.getFirst()).bb == null && tunneledAuthInProgres) {
         Iterator<ClientCnxn.Packet> iter = outgoingQueue.iterator();

         while(iter.hasNext()) {
            ClientCnxn.Packet p = (ClientCnxn.Packet)iter.next();
            if (p.requestHeader == null) {
               iter.remove();
               outgoingQueue.addFirst(p);
               return p;
            }

            LOG.debug("Deferring non-priming packet {} until SASL authentication completes.", p);
         }

         return null;
      } else {
         return (ClientCnxn.Packet)outgoingQueue.getFirst();
      }
   }

   void cleanup() {
      if (this.sockKey != null) {
         SocketChannel sock = (SocketChannel)this.sockKey.channel();
         this.sockKey.cancel();

         try {
            sock.socket().shutdownInput();
         } catch (IOException e) {
            LOG.debug("Ignoring exception during shutdown input", e);
         }

         try {
            sock.socket().shutdownOutput();
         } catch (IOException e) {
            LOG.debug("Ignoring exception during shutdown output", e);
         }

         try {
            sock.socket().close();
         } catch (IOException e) {
            LOG.debug("Ignoring exception during socket close", e);
         }

         try {
            sock.close();
         } catch (IOException e) {
            LOG.debug("Ignoring exception during channel close", e);
         }
      }

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var3) {
         LOG.debug("SendThread interrupted during sleep, ignoring");
      }

      this.sockKey = null;
   }

   void close() {
      try {
         if (LOG.isTraceEnabled()) {
            LOG.trace("Doing client selector close");
         }

         this.selector.close();
         if (LOG.isTraceEnabled()) {
            LOG.trace("Closed client selector");
         }
      } catch (IOException e) {
         LOG.warn("Ignoring exception during selector close", e);
      }

   }

   SocketChannel createSock() throws IOException {
      SocketChannel sock = SocketChannel.open();
      sock.configureBlocking(false);
      sock.socket().setSoLinger(false, -1);
      sock.socket().setTcpNoDelay(true);
      return sock;
   }

   void registerAndConnect(SocketChannel sock, InetSocketAddress addr) throws IOException {
      this.sockKey = sock.register(this.selector, 8);
      boolean immediateConnect = sock.connect(addr);
      if (immediateConnect) {
         this.sendThread.primeConnection();
      }

   }

   void connect(InetSocketAddress addr) throws IOException {
      SocketChannel sock = this.createSock();

      try {
         this.registerAndConnect(sock, addr);
      } catch (UnsupportedAddressTypeException | SecurityException | IOException | UnresolvedAddressException e) {
         LOG.error("Unable to open socket to {}", addr);
         sock.close();
         throw e;
      }

      this.initialized = false;
      this.lenBuffer.clear();
      this.incomingBuffer = this.lenBuffer;
   }

   SocketAddress getRemoteSocketAddress() {
      return this.remoteSocketAddress;
   }

   SocketAddress getLocalSocketAddress() {
      return this.localSocketAddress;
   }

   private void updateSocketAddresses() {
      Socket socket = ((SocketChannel)this.sockKey.channel()).socket();
      this.localSocketAddress = socket.getLocalSocketAddress();
      this.remoteSocketAddress = socket.getRemoteSocketAddress();
   }

   void packetAdded() {
      this.wakeupCnxn();
   }

   void onClosing() {
      this.wakeupCnxn();
   }

   private synchronized void wakeupCnxn() {
      this.selector.wakeup();
   }

   void doTransport(int waitTimeOut, Queue pendingQueue, ClientCnxn cnxn) throws IOException, InterruptedException {
      this.selector.select((long)waitTimeOut);
      Set<SelectionKey> selected;
      synchronized(this) {
         selected = this.selector.selectedKeys();
      }

      this.updateNow();

      for(SelectionKey k : selected) {
         SocketChannel sc = (SocketChannel)k.channel();
         if (k.isConnectable()) {
            if (sc.finishConnect()) {
               this.updateLastSendAndHeard();
               this.updateSocketAddresses();
               this.sendThread.primeConnection();
            }
         } else if (k.isReadable() || k.isWritable()) {
            this.doIO(pendingQueue, cnxn);
         }
      }

      if (this.sendThread.getZkState().isConnected() && this.findSendablePacket(this.outgoingQueue, this.sendThread.tunnelAuthInProgress()) != null) {
         this.enableWrite();
      }

      selected.clear();
   }

   void testableCloseSocket() throws IOException {
      LOG.info("testableCloseSocket() called");
      SelectionKey tmp = this.sockKey;
      if (tmp != null) {
         ((SocketChannel)tmp.channel()).socket().close();
      }

   }

   void saslCompleted() {
      this.enableWrite();
   }

   synchronized void enableWrite() {
      int i = this.sockKey.interestOps();
      if ((i & 4) == 0) {
         this.sockKey.interestOps(i | 4);
      }

   }

   private synchronized void disableWrite() {
      int i = this.sockKey.interestOps();
      if ((i & 4) != 0) {
         this.sockKey.interestOps(i & -5);
      }

   }

   private synchronized void enableRead() {
      int i = this.sockKey.interestOps();
      if ((i & 1) == 0) {
         this.sockKey.interestOps(i | 1);
      }

   }

   void connectionPrimed() {
      this.sockKey.interestOps(5);
   }

   Selector getSelector() {
      return this.selector;
   }

   void sendPacket(ClientCnxn.Packet p) throws IOException {
      SocketChannel sock = (SocketChannel)this.sockKey.channel();
      if (sock == null) {
         throw new IOException("Socket is null!");
      } else {
         p.createBB();
         ByteBuffer pbb = p.bb;
         sock.write(pbb);
      }
   }
}
