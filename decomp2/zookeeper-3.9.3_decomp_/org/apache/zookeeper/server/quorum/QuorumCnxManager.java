package org.apache.zookeeper.server.quorum;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.channels.UnresolvedAddressException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.net.ssl.SSLSocket;
import org.apache.zookeeper.common.NetUtils;
import org.apache.zookeeper.common.X509Exception;
import org.apache.zookeeper.server.ExitCode;
import org.apache.zookeeper.server.ZooKeeperThread;
import org.apache.zookeeper.server.quorum.auth.QuorumAuthLearner;
import org.apache.zookeeper.server.quorum.auth.QuorumAuthServer;
import org.apache.zookeeper.server.quorum.flexible.QuorumVerifier;
import org.apache.zookeeper.server.util.ConfigUtils;
import org.apache.zookeeper.util.CircularBlockingQueue;
import org.apache.zookeeper.util.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuorumCnxManager {
   private static final Logger LOG = LoggerFactory.getLogger(QuorumCnxManager.class);
   static final int RECV_CAPACITY = 100;
   static final int SEND_CAPACITY = 1;
   static final int PACKETMAXSIZE = 524288;
   private AtomicLong observerCounter = new AtomicLong(-1L);
   public static final long PROTOCOL_VERSION_V1 = -65536L;
   public static final long PROTOCOL_VERSION_V2 = -65535L;
   public static final int maxBuffer = 2048;
   private int cnxTO = 5000;
   final QuorumPeer self;
   final long mySid;
   final int socketTimeout;
   final Map view;
   final boolean listenOnAllIPs;
   private ThreadPoolExecutor connectionExecutor;
   private final Set inprogressConnections = Collections.synchronizedSet(new HashSet());
   private QuorumAuthServer authServer;
   private QuorumAuthLearner authLearner;
   private boolean quorumSaslAuthEnabled;
   private AtomicInteger connectionThreadCnt = new AtomicInteger(0);
   final ConcurrentHashMap senderWorkerMap = new ConcurrentHashMap();
   final ConcurrentHashMap queueSendMap = new ConcurrentHashMap();
   final ConcurrentHashMap lastMessageSent = new ConcurrentHashMap();
   public final BlockingQueue recvQueue = new CircularBlockingQueue(100);
   volatile boolean shutdown = false;
   public final Listener listener;
   private AtomicInteger threadCnt = new AtomicInteger(0);
   private final boolean tcpKeepAlive = Boolean.getBoolean("zookeeper.tcpKeepAlive");
   static final Supplier DEFAULT_SOCKET_FACTORY = () -> new Socket();
   private static Supplier SOCKET_FACTORY;

   static void setSocketFactory(Supplier factory) {
      SOCKET_FACTORY = factory;
   }

   public QuorumCnxManager(QuorumPeer self, long mySid, Map view, QuorumAuthServer authServer, QuorumAuthLearner authLearner, int socketTimeout, boolean listenOnAllIPs, int quorumCnxnThreadsSize, boolean quorumSaslAuthEnabled) {
      String cnxToValue = System.getProperty("zookeeper.cnxTimeout");
      if (cnxToValue != null) {
         this.cnxTO = Integer.parseInt(cnxToValue);
      }

      this.self = self;
      this.mySid = mySid;
      this.socketTimeout = socketTimeout;
      this.view = view;
      this.listenOnAllIPs = listenOnAllIPs;
      this.authServer = authServer;
      this.authLearner = authLearner;
      this.quorumSaslAuthEnabled = quorumSaslAuthEnabled;
      this.initializeConnectionExecutor(mySid, quorumCnxnThreadsSize);
      this.listener = new Listener();
      this.listener.setName("QuorumPeerListener");
   }

   private void initializeConnectionExecutor(long mySid, int quorumCnxnThreadsSize) {
      AtomicInteger threadIndex = new AtomicInteger(1);
      SecurityManager s = System.getSecurityManager();
      ThreadGroup group = s != null ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
      ThreadFactory daemonThFactory = (runnable) -> new Thread(group, runnable, String.format("QuorumConnectionThread-[myid=%d]-%d", mySid, threadIndex.getAndIncrement()));
      this.connectionExecutor = new ThreadPoolExecutor(3, quorumCnxnThreadsSize, 60L, TimeUnit.SECONDS, new SynchronousQueue(), daemonThFactory);
      this.connectionExecutor.allowCoreThreadTimeOut(true);
   }

   public void testInitiateConnection(long sid) {
      LOG.debug("Opening channel to server {}", sid);
      this.initiateConnection(((QuorumPeer.QuorumServer)this.self.getVotingView().get(sid)).electionAddr, sid);
   }

   public void initiateConnection(MultipleAddresses electionAddr, Long sid) {
      Socket sock = null;

      try {
         LOG.debug("Opening channel to server {}", sid);
         if (this.self.isSslQuorum()) {
            sock = this.self.getX509Util().createSSLSocket();
         } else {
            sock = (Socket)SOCKET_FACTORY.get();
         }

         this.setSockOpts(sock);
         sock.connect(electionAddr.getReachableOrOne(), this.cnxTO);
         if (sock instanceof SSLSocket) {
            SSLSocket sslSock = (SSLSocket)sock;
            sslSock.startHandshake();
            LOG.info("SSL handshake complete with {} - {} - {}", new Object[]{sslSock.getRemoteSocketAddress(), sslSock.getSession().getProtocol(), sslSock.getSession().getCipherSuite()});
         }

         LOG.debug("Connected to server {} using election address: {}:{}", new Object[]{sid, sock.getInetAddress(), sock.getPort()});
      } catch (X509Exception e) {
         LOG.warn("Cannot open secure channel to {} at election address {}", new Object[]{sid, electionAddr, e});
         this.closeSocket(sock);
         return;
      } catch (IOException | UnresolvedAddressException e) {
         LOG.warn("Cannot open channel to {} at election address {}", new Object[]{sid, electionAddr, e});
         this.closeSocket(sock);
         return;
      }

      try {
         this.startConnection(sock, sid);
      } catch (IOException e) {
         LOG.error("Exception while connecting, id: {}, addr: {}, closing learner connection", new Object[]{sid, sock.getRemoteSocketAddress(), e});
         this.closeSocket(sock);
      }

   }

   public boolean initiateConnectionAsync(MultipleAddresses electionAddr, Long sid) {
      if (!this.inprogressConnections.add(sid)) {
         LOG.debug("Connection request to server id: {} is already in progress, so skipping this request", sid);
         return true;
      } else {
         try {
            this.connectionExecutor.execute(new QuorumConnectionReqThread(electionAddr, sid));
            this.connectionThreadCnt.incrementAndGet();
            return true;
         } catch (Throwable e) {
            this.inprogressConnections.remove(sid);
            LOG.error("Exception while submitting quorum connection request", e);
            return false;
         }
      }
   }

   private boolean startConnection(Socket sock, Long sid) throws IOException {
      DataOutputStream dout = null;
      DataInputStream din = null;
      LOG.debug("startConnection (myId:{} --> sid:{})", this.self.getMyId(), sid);

      try {
         BufferedOutputStream buf = new BufferedOutputStream(sock.getOutputStream());
         dout = new DataOutputStream(buf);
         long protocolVersion = this.self.isMultiAddressEnabled() ? -65535L : -65536L;
         dout.writeLong(protocolVersion);
         dout.writeLong(this.self.getMyId());
         Collection<InetSocketAddress> addressesToSend = (Collection<InetSocketAddress>)(protocolVersion == -65535L ? this.self.getElectionAddress().getAllAddresses() : Arrays.asList(this.self.getElectionAddress().getOne()));
         String addr = (String)addressesToSend.stream().map(NetUtils::formatInetAddr).collect(Collectors.joining("|"));
         byte[] addr_bytes = addr.getBytes();
         dout.writeInt(addr_bytes.length);
         dout.write(addr_bytes);
         dout.flush();
         din = new DataInputStream(new BufferedInputStream(sock.getInputStream()));
      } catch (IOException e) {
         LOG.warn("Ignoring exception reading or writing challenge: ", e);
         this.closeSocket(sock);
         return false;
      }

      QuorumPeer.QuorumServer qps = (QuorumPeer.QuorumServer)this.self.getVotingView().get(sid);
      if (qps != null) {
         this.authLearner.authenticate(sock, qps.hostname);
      }

      if (sid > this.self.getMyId()) {
         LOG.info("Have smaller server identifier, so dropping the connection: (myId:{} --> sid:{})", this.self.getMyId(), sid);
         this.closeSocket(sock);
         return false;
      } else {
         LOG.debug("Have larger server identifier, so keeping the connection: (myId:{} --> sid:{})", this.self.getMyId(), sid);
         SendWorker sw = new SendWorker(sock, sid);
         RecvWorker rw = new RecvWorker(sock, din, sid, sw);
         sw.setRecv(rw);
         SendWorker vsw = (SendWorker)this.senderWorkerMap.get(sid);
         if (vsw != null) {
            vsw.finish();
         }

         this.senderWorkerMap.put(sid, sw);
         this.queueSendMap.putIfAbsent(sid, new CircularBlockingQueue(1));
         sw.start();
         rw.start();
         return true;
      }
   }

   public void receiveConnection(Socket sock) {
      DataInputStream din = null;

      try {
         din = new DataInputStream(new BufferedInputStream(sock.getInputStream()));
         LOG.debug("Sync handling of connection request received from: {}", sock.getRemoteSocketAddress());
         this.handleConnection(sock, din);
      } catch (IOException e) {
         LOG.error("Exception handling connection, addr: {}, closing server connection", sock.getRemoteSocketAddress());
         LOG.debug("Exception details: ", e);
         this.closeSocket(sock);
      }

   }

   public void receiveConnectionAsync(Socket sock) {
      try {
         LOG.debug("Async handling of connection request received from: {}", sock.getRemoteSocketAddress());
         this.connectionExecutor.execute(new QuorumConnectionReceiverThread(sock));
         this.connectionThreadCnt.incrementAndGet();
      } catch (Throwable e) {
         LOG.error("Exception handling connection, addr: {}, closing server connection", sock.getRemoteSocketAddress());
         LOG.debug("Exception details: ", e);
         this.closeSocket(sock);
      }

   }

   private void handleConnection(Socket sock, DataInputStream din) throws IOException {
      Long sid = null;
      Long protocolVersion = null;
      MultipleAddresses electionAddr = null;

      try {
         protocolVersion = din.readLong();
         if (protocolVersion >= 0L) {
            sid = protocolVersion;
         } else {
            try {
               InitialMessage init = QuorumCnxManager.InitialMessage.parse(protocolVersion, din);
               sid = init.sid;
               if (!init.electionAddr.isEmpty()) {
                  electionAddr = new MultipleAddresses(init.electionAddr, Duration.ofMillis((long)this.self.getMultiAddressReachabilityCheckTimeoutMs()));
               }

               LOG.debug("Initial message parsed by {}: {}", this.self.getMyId(), init.toString());
            } catch (InitialMessage.InitialMessageException ex) {
               LOG.error("Initial message parsing error!", ex);
               this.closeSocket(sock);
               return;
            }
         }

         if (sid == Long.MAX_VALUE) {
            sid = this.observerCounter.getAndDecrement();
            LOG.info("Setting arbitrary identifier to observer: {}", sid);
         }
      } catch (IOException e) {
         LOG.warn("Exception reading or writing challenge", e);
         this.closeSocket(sock);
         return;
      }

      this.authServer.authenticate(sock, din);
      if (sid < this.self.getMyId()) {
         SendWorker sw = (SendWorker)this.senderWorkerMap.get(sid);
         if (sw != null) {
            sw.finish();
         }

         LOG.debug("Create new connection to server: {}", sid);
         this.closeSocket(sock);
         if (electionAddr != null) {
            this.connectOne(sid, electionAddr);
         } else {
            this.connectOne(sid);
         }
      } else if (sid == this.self.getMyId()) {
         LOG.warn("We got a connection request from a server with our own ID. This should be either a configuration error, or a bug.");
      } else {
         SendWorker sw = new SendWorker(sock, sid);
         RecvWorker rw = new RecvWorker(sock, din, sid, sw);
         sw.setRecv(rw);
         SendWorker vsw = (SendWorker)this.senderWorkerMap.get(sid);
         if (vsw != null) {
            vsw.finish();
         }

         this.senderWorkerMap.put(sid, sw);
         this.queueSendMap.putIfAbsent(sid, new CircularBlockingQueue(1));
         sw.start();
         rw.start();
      }

   }

   public void toSend(Long sid, ByteBuffer b) {
      if (this.mySid == sid) {
         b.position(0);
         this.addToRecvQueue(new Message(b.duplicate(), sid));
      } else {
         BlockingQueue<ByteBuffer> bq = (BlockingQueue)this.queueSendMap.computeIfAbsent(sid, (serverId) -> new CircularBlockingQueue(1));
         this.addToSendQueue(bq, b);
         this.connectOne(sid);
      }

   }

   synchronized boolean connectOne(long sid, MultipleAddresses electionAddr) {
      if (this.senderWorkerMap.get(sid) != null) {
         LOG.debug("There is a connection already for server {}", sid);
         if (this.self.isMultiAddressEnabled() && electionAddr.size() > 1 && this.self.isMultiAddressReachabilityCheckEnabled()) {
            ((SendWorker)this.senderWorkerMap.get(sid)).asyncValidateIfSocketIsStillReachable();
         }

         return true;
      } else {
         return this.initiateConnectionAsync(electionAddr, sid);
      }
   }

   synchronized void connectOne(long sid) {
      if (this.senderWorkerMap.get(sid) != null) {
         LOG.debug("There is a connection already for server {}", sid);
         if (this.self.isMultiAddressEnabled() && this.self.isMultiAddressReachabilityCheckEnabled()) {
            ((SendWorker)this.senderWorkerMap.get(sid)).asyncValidateIfSocketIsStillReachable();
         }

      } else {
         synchronized(this.self.QV_LOCK) {
            boolean knownId = false;
            this.self.recreateSocketAddresses(sid);
            Map<Long, QuorumPeer.QuorumServer> lastCommittedView = this.self.getView();
            QuorumVerifier lastSeenQV = this.self.getLastSeenQuorumVerifier();
            Map<Long, QuorumPeer.QuorumServer> lastProposedView = lastSeenQV.getAllMembers();
            if (lastCommittedView.containsKey(sid)) {
               knownId = true;
               LOG.debug("Server {} knows {} already, it is in the lastCommittedView", this.self.getMyId(), sid);
               if (this.connectOne(sid, ((QuorumPeer.QuorumServer)lastCommittedView.get(sid)).electionAddr)) {
                  return;
               }
            }

            if (lastSeenQV != null && lastProposedView.containsKey(sid) && (!knownId || !((QuorumPeer.QuorumServer)lastProposedView.get(sid)).electionAddr.equals(((QuorumPeer.QuorumServer)lastCommittedView.get(sid)).electionAddr))) {
               knownId = true;
               LOG.debug("Server {} knows {} already, it is in the lastProposedView", this.self.getMyId(), sid);
               if (this.connectOne(sid, ((QuorumPeer.QuorumServer)lastProposedView.get(sid)).electionAddr)) {
                  return;
               }
            }

            if (!knownId) {
               LOG.warn("Invalid server id: {} ", sid);
            }

         }
      }
   }

   public void connectAll() {
      Enumeration<Long> en = this.queueSendMap.keys();

      while(en.hasMoreElements()) {
         long sid = (Long)en.nextElement();
         this.connectOne(sid);
      }

   }

   boolean haveDelivered() {
      for(BlockingQueue queue : this.queueSendMap.values()) {
         int queueSize = queue.size();
         LOG.debug("Queue size: {}", queueSize);
         if (queueSize == 0) {
            return true;
         }
      }

      return false;
   }

   public void halt() {
      this.shutdown = true;
      LOG.debug("Halting listener");
      this.listener.halt();

      try {
         this.listener.join();
      } catch (InterruptedException ex) {
         LOG.warn("Got interrupted before joining the listener", ex);
      }

      this.softHalt();
      if (this.connectionExecutor != null) {
         this.connectionExecutor.shutdown();
      }

      this.inprogressConnections.clear();
      this.resetConnectionThreadCount();
   }

   public void softHalt() {
      for(SendWorker sw : this.senderWorkerMap.values()) {
         LOG.debug("Server {} is soft-halting sender towards: {}", this.self.getMyId(), sw);
         sw.finish();
      }

   }

   private void setSockOpts(Socket sock) throws SocketException {
      sock.setTcpNoDelay(true);
      sock.setKeepAlive(this.tcpKeepAlive);
      sock.setSoTimeout(this.socketTimeout);
   }

   private void closeSocket(Socket sock) {
      if (sock != null) {
         try {
            sock.close();
         } catch (IOException ie) {
            LOG.error("Exception while closing", ie);
         }

      }
   }

   public long getThreadCount() {
      return (long)this.threadCnt.get();
   }

   public long getConnectionThreadCount() {
      return (long)this.connectionThreadCnt.get();
   }

   private void resetConnectionThreadCount() {
      this.connectionThreadCnt.set(0);
   }

   private void addToSendQueue(BlockingQueue queue, ByteBuffer buffer) {
      boolean success = queue.offer(buffer);
      if (!success) {
         throw new RuntimeException("Could not insert into receive queue");
      }
   }

   private boolean isSendQueueEmpty(BlockingQueue queue) {
      return queue.isEmpty();
   }

   private ByteBuffer pollSendQueue(BlockingQueue queue, long timeout, TimeUnit unit) throws InterruptedException {
      return (ByteBuffer)queue.poll(timeout, unit);
   }

   public void addToRecvQueue(Message msg) {
      boolean success = this.recvQueue.offer(msg);
      if (!success) {
         throw new RuntimeException("Could not insert into receive queue");
      }
   }

   public Message pollRecvQueue(long timeout, TimeUnit unit) throws InterruptedException {
      return (Message)this.recvQueue.poll(timeout, unit);
   }

   public boolean connectedToPeer(long peerSid) {
      return this.senderWorkerMap.get(peerSid) != null;
   }

   public boolean isReconfigEnabled() {
      return this.self.isReconfigEnabled();
   }

   static {
      SOCKET_FACTORY = DEFAULT_SOCKET_FACTORY;
   }

   public static class Message {
      ByteBuffer buffer;
      long sid;

      Message(ByteBuffer buffer, long sid) {
         this.buffer = buffer;
         this.sid = sid;
      }
   }

   public static class InitialMessage {
      public Long sid;
      public List electionAddr;

      InitialMessage(Long sid, List addresses) {
         this.sid = sid;
         this.electionAddr = addresses;
      }

      public static InitialMessage parse(Long protocolVersion, DataInputStream din) throws InitialMessageException, IOException {
         if (protocolVersion != -65536L && protocolVersion != -65535L) {
            throw new InitialMessageException("Got unrecognized protocol version %s", new Object[]{protocolVersion});
         } else {
            Long sid = din.readLong();
            int remaining = din.readInt();
            if (remaining > 0 && remaining <= 2048) {
               byte[] b = new byte[remaining];
               int num_read = din.read(b);
               if (num_read != remaining) {
                  throw new InitialMessageException("Read only %s bytes out of %s sent by server %s", new Object[]{num_read, remaining, sid});
               } else {
                  String[] addressStrings = (new String(b, StandardCharsets.UTF_8)).split("\\|");
                  List<InetSocketAddress> addresses = new ArrayList(addressStrings.length);

                  for(String addr : addressStrings) {
                     String[] host_port;
                     try {
                        host_port = ConfigUtils.getHostAndPort(addr);
                     } catch (QuorumPeerConfig.ConfigException var17) {
                        throw new InitialMessageException("Badly formed address: %s", new Object[]{addr});
                     }

                     if (host_port.length != 2) {
                        throw new InitialMessageException("Badly formed address: %s", new Object[]{addr});
                     }

                     int port;
                     try {
                        port = Integer.parseInt(host_port[1]);
                     } catch (NumberFormatException var15) {
                        throw new InitialMessageException("Bad port number: %s", new Object[]{host_port[1]});
                     } catch (ArrayIndexOutOfBoundsException var16) {
                        throw new InitialMessageException("No port number in: %s", new Object[]{addr});
                     }

                     if (!isWildcardAddress(host_port[0])) {
                        addresses.add(new InetSocketAddress(host_port[0], port));
                     }
                  }

                  return new InitialMessage(sid, addresses);
               }
            } else {
               throw new InitialMessageException("Unreasonable buffer length: %s", new Object[]{remaining});
            }
         }
      }

      static boolean isWildcardAddress(String hostname) {
         try {
            return InetAddress.getByName(hostname).isAnyLocalAddress();
         } catch (UnknownHostException var2) {
            return false;
         }
      }

      public String toString() {
         return "InitialMessage{sid=" + this.sid + ", electionAddr=" + this.electionAddr + '}';
      }

      public static class InitialMessageException extends Exception {
         InitialMessageException(String message, Object... args) {
            super(String.format(message, args));
         }
      }
   }

   private class QuorumConnectionReqThread extends ZooKeeperThread {
      final MultipleAddresses electionAddr;
      final Long sid;

      QuorumConnectionReqThread(MultipleAddresses electionAddr, Long sid) {
         super("QuorumConnectionReqThread-" + sid);
         this.electionAddr = electionAddr;
         this.sid = sid;
      }

      public void run() {
         try {
            QuorumCnxManager.this.initiateConnection(this.electionAddr, this.sid);
         } finally {
            QuorumCnxManager.this.inprogressConnections.remove(this.sid);
         }

      }
   }

   private class QuorumConnectionReceiverThread extends ZooKeeperThread {
      private final Socket sock;

      QuorumConnectionReceiverThread(Socket sock) {
         super("QuorumConnectionReceiverThread-" + sock.getRemoteSocketAddress());
         this.sock = sock;
      }

      public void run() {
         QuorumCnxManager.this.receiveConnection(this.sock);
      }
   }

   public class Listener extends ZooKeeperThread {
      private static final String ELECTION_PORT_BIND_RETRY = "zookeeper.electionPortBindRetry";
      private static final int DEFAULT_PORT_BIND_MAX_RETRY = 3;
      private final int portBindMaxRetry;
      private Runnable socketBindErrorHandler = () -> ServiceUtils.requestSystemExit(ExitCode.UNABLE_TO_BIND_QUORUM_PORT.getValue());
      private List listenerHandlers;
      private final AtomicBoolean socketException = new AtomicBoolean(false);

      public Listener() {
         super("ListenerThread");
         Integer maxRetry = Integer.getInteger("zookeeper.electionPortBindRetry", 3);
         if (maxRetry >= 0) {
            QuorumCnxManager.LOG.info("Election port bind maximum retries is {}", maxRetry == 0 ? "infinite" : maxRetry);
            this.portBindMaxRetry = maxRetry;
         } else {
            QuorumCnxManager.LOG.info("'{}' contains invalid value: {}(must be >= 0). Use default value of {} instead.", new Object[]{"zookeeper.electionPortBindRetry", maxRetry, 3});
            this.portBindMaxRetry = 3;
         }

      }

      void setSocketBindErrorHandler(Runnable errorHandler) {
         this.socketBindErrorHandler = errorHandler;
      }

      public void run() {
         if (!QuorumCnxManager.this.shutdown) {
            QuorumCnxManager.LOG.debug("Listener thread started, myId: {}", QuorumCnxManager.this.self.getMyId());
            Set<InetSocketAddress> addresses;
            if (QuorumCnxManager.this.self.getQuorumListenOnAllIPs()) {
               addresses = QuorumCnxManager.this.self.getElectionAddress().getWildcardAddresses();
            } else {
               addresses = QuorumCnxManager.this.self.getElectionAddress().getAllAddresses();
            }

            CountDownLatch latch = new CountDownLatch(addresses.size());
            this.listenerHandlers = (List)addresses.stream().map((address) -> new ListenerHandler(address, QuorumCnxManager.this.self.shouldUsePortUnification(), QuorumCnxManager.this.self.isSslQuorum(), latch)).collect(Collectors.toList());
            ExecutorService executor = Executors.newFixedThreadPool(addresses.size());

            try {
               List var10000 = this.listenerHandlers;
               Objects.requireNonNull(executor);
               var10000.forEach(executor::submit);
            } finally {
               executor.shutdown();
            }

            try {
               latch.await();
            } catch (InterruptedException ie) {
               QuorumCnxManager.LOG.error("Interrupted while sleeping. Ignoring exception", ie);
            } finally {
               for(ListenerHandler handler : this.listenerHandlers) {
                  try {
                     handler.close();
                  } catch (IOException ie) {
                     QuorumCnxManager.LOG.debug("Error closing server socket", ie);
                  }
               }

            }
         }

         QuorumCnxManager.LOG.info("Leaving listener");
         if (!QuorumCnxManager.this.shutdown) {
            QuorumCnxManager.LOG.error("As I'm leaving the listener thread, I won't be able to participate in leader election any longer: {}", QuorumCnxManager.this.self.getElectionAddress().getAllAddresses().stream().map(NetUtils::formatInetAddr).collect(Collectors.joining("|")));
            if (this.socketException.get()) {
               this.socketBindErrorHandler.run();
            }
         }

      }

      void halt() {
         QuorumCnxManager.LOG.debug("Halt called: Trying to close listeners");
         if (this.listenerHandlers != null) {
            QuorumCnxManager.LOG.debug("Closing listener: {}", QuorumCnxManager.this.mySid);

            for(ListenerHandler handler : this.listenerHandlers) {
               try {
                  handler.close();
               } catch (IOException e) {
                  QuorumCnxManager.LOG.warn("Exception when shutting down listener: ", e);
               }
            }
         }

      }

      class ListenerHandler implements Runnable, Closeable {
         private ServerSocket serverSocket;
         private InetSocketAddress address;
         private boolean portUnification;
         private boolean sslQuorum;
         private CountDownLatch latch;

         ListenerHandler(InetSocketAddress address, boolean portUnification, boolean sslQuorum, CountDownLatch latch) {
            this.address = address;
            this.portUnification = portUnification;
            this.sslQuorum = sslQuorum;
            this.latch = latch;
         }

         public void run() {
            try {
               Thread.currentThread().setName("ListenerHandler-" + this.address);
               this.acceptConnections();

               try {
                  this.close();
               } catch (IOException e) {
                  QuorumCnxManager.LOG.warn("Exception when shutting down listener: ", e);
               }
            } catch (Exception e) {
               QuorumCnxManager.LOG.error("Unexpected error ", e);
            } finally {
               this.latch.countDown();
            }

         }

         public synchronized void close() throws IOException {
            if (this.serverSocket != null && !this.serverSocket.isClosed()) {
               QuorumCnxManager.LOG.debug("Trying to close listeners: {}", this.serverSocket);
               this.serverSocket.close();
            }

         }

         private void acceptConnections() {
            int numRetries = 0;
            Socket client = null;

            while(!QuorumCnxManager.this.shutdown && (Listener.this.portBindMaxRetry == 0 || numRetries < Listener.this.portBindMaxRetry)) {
               try {
                  this.serverSocket = this.createNewServerSocket();
                  QuorumCnxManager.LOG.info("{} is accepting connections now, my election bind port: {}", QuorumCnxManager.this.mySid, this.address.toString());

                  while(!QuorumCnxManager.this.shutdown) {
                     try {
                        client = this.serverSocket.accept();
                        QuorumCnxManager.this.setSockOpts(client);
                        QuorumCnxManager.LOG.info("Received connection request from {}", client.getRemoteSocketAddress());
                        if (QuorumCnxManager.this.quorumSaslAuthEnabled) {
                           QuorumCnxManager.this.receiveConnectionAsync(client);
                        } else {
                           QuorumCnxManager.this.receiveConnection(client);
                        }

                        numRetries = 0;
                     } catch (SocketTimeoutException var7) {
                        QuorumCnxManager.LOG.warn("The socket is listening for the election accepted and it timed out unexpectedly, but will retry.see ZOOKEEPER-2836");
                     }
                  }
               } catch (IOException e) {
                  if (QuorumCnxManager.this.shutdown) {
                     break;
                  }

                  QuorumCnxManager.LOG.error("Exception while listening to address {}", this.address, e);
                  if (e instanceof SocketException) {
                     Listener.this.socketException.set(true);
                  }

                  ++numRetries;

                  try {
                     this.close();
                     Thread.sleep(1000L);
                  } catch (IOException ie) {
                     QuorumCnxManager.LOG.error("Error closing server socket", ie);
                  } catch (InterruptedException ie) {
                     QuorumCnxManager.LOG.error("Interrupted while sleeping. Ignoring exception", ie);
                  }

                  QuorumCnxManager.this.closeSocket(client);
               }
            }

            if (!QuorumCnxManager.this.shutdown) {
               QuorumCnxManager.LOG.error("Leaving listener thread for address {} after {} errors. Use {} property to increase retry count.", new Object[]{NetUtils.formatInetAddr(this.address), numRetries, "zookeeper.electionPortBindRetry"});
            }

         }

         private ServerSocket createNewServerSocket() throws IOException {
            ServerSocket socket;
            if (this.portUnification) {
               QuorumCnxManager.LOG.info("Creating TLS-enabled quorum server socket");
               socket = new UnifiedServerSocket(QuorumCnxManager.this.self.getX509Util(), true);
            } else if (this.sslQuorum) {
               QuorumCnxManager.LOG.info("Creating TLS-only quorum server socket");
               socket = new UnifiedServerSocket(QuorumCnxManager.this.self.getX509Util(), false);
            } else {
               socket = new ServerSocket();
            }

            socket.setReuseAddress(true);
            this.address = new InetSocketAddress(this.address.getHostString(), this.address.getPort());
            socket.bind(this.address);
            return socket;
         }
      }
   }

   class SendWorker extends ZooKeeperThread {
      Long sid;
      Socket sock;
      RecvWorker recvWorker;
      volatile boolean running = true;
      DataOutputStream dout;
      AtomicBoolean ongoingAsyncValidation = new AtomicBoolean(false);

      SendWorker(Socket sock, Long sid) {
         super("SendWorker:" + sid);
         this.sid = sid;
         this.sock = sock;
         this.recvWorker = null;

         try {
            this.dout = new DataOutputStream(sock.getOutputStream());
         } catch (IOException e) {
            QuorumCnxManager.LOG.error("Unable to access socket output stream", e);
            QuorumCnxManager.this.closeSocket(sock);
            this.running = false;
         }

         QuorumCnxManager.LOG.debug("Address of remote peer: {}", this.sid);
      }

      synchronized void setRecv(RecvWorker recvWorker) {
         this.recvWorker = recvWorker;
      }

      synchronized RecvWorker getRecvWorker() {
         return this.recvWorker;
      }

      synchronized boolean finish() {
         QuorumCnxManager.LOG.debug("Calling SendWorker.finish for {}", this.sid);
         if (!this.running) {
            return this.running;
         } else {
            this.running = false;
            QuorumCnxManager.this.closeSocket(this.sock);
            this.interrupt();
            if (this.recvWorker != null) {
               this.recvWorker.finish();
            }

            QuorumCnxManager.LOG.debug("Removing entry from senderWorkerMap sid={}", this.sid);
            QuorumCnxManager.this.senderWorkerMap.remove(this.sid, this);
            QuorumCnxManager.this.threadCnt.decrementAndGet();
            return this.running;
         }
      }

      synchronized void send(ByteBuffer b) throws IOException {
         byte[] msgBytes = new byte[b.capacity()];

         try {
            b.position(0);
            b.get(msgBytes);
         } catch (BufferUnderflowException be) {
            QuorumCnxManager.LOG.error("BufferUnderflowException ", be);
            return;
         }

         this.dout.writeInt(b.capacity());
         this.dout.write(b.array());
         this.dout.flush();
      }

      public void run() {
         QuorumCnxManager.this.threadCnt.incrementAndGet();

         try {
            BlockingQueue<ByteBuffer> bq = (BlockingQueue)QuorumCnxManager.this.queueSendMap.get(this.sid);
            if (bq == null || QuorumCnxManager.this.isSendQueueEmpty(bq)) {
               ByteBuffer b = (ByteBuffer)QuorumCnxManager.this.lastMessageSent.get(this.sid);
               if (b != null) {
                  QuorumCnxManager.LOG.debug("Attempting to send lastMessage to sid={}", this.sid);
                  this.send(b);
               }
            }
         } catch (IOException e) {
            QuorumCnxManager.LOG.error("Failed to send last message. Shutting down thread.", e);
            this.finish();
         }

         QuorumCnxManager.LOG.debug("SendWorker thread started towards {}. myId: {}", this.sid, QuorumCnxManager.this.mySid);

         try {
            while(this.running && !QuorumCnxManager.this.shutdown && this.sock != null) {
               ByteBuffer b = null;

               try {
                  BlockingQueue<ByteBuffer> bq = (BlockingQueue)QuorumCnxManager.this.queueSendMap.get(this.sid);
                  if (bq == null) {
                     QuorumCnxManager.LOG.error("No queue of incoming messages for server {}", this.sid);
                     break;
                  }

                  b = QuorumCnxManager.this.pollSendQueue(bq, 1000L, TimeUnit.MILLISECONDS);
                  if (b != null) {
                     QuorumCnxManager.this.lastMessageSent.put(this.sid, b);
                     this.send(b);
                  }
               } catch (InterruptedException e) {
                  QuorumCnxManager.LOG.warn("Interrupted while waiting for message on queue", e);
               }
            }
         } catch (Exception e) {
            QuorumCnxManager.LOG.warn("Exception when using channel: for id {} my id = {}", new Object[]{this.sid, QuorumCnxManager.this.mySid, e});
         }

         this.finish();
         QuorumCnxManager.LOG.warn("Send worker leaving thread id {} my id = {}", this.sid, QuorumCnxManager.this.self.getMyId());
      }

      public void asyncValidateIfSocketIsStillReachable() {
         if (this.ongoingAsyncValidation.compareAndSet(false, true)) {
            (new Thread(() -> {
               QuorumCnxManager.LOG.debug("validate if destination address is reachable for sid {}", this.sid);
               if (this.sock != null) {
                  InetAddress address = this.sock.getInetAddress();

                  try {
                     if (address.isReachable(500)) {
                        QuorumCnxManager.LOG.debug("destination address {} is reachable for sid {}", address.toString(), this.sid);
                        this.ongoingAsyncValidation.set(false);
                        return;
                     }
                  } catch (IOException | NullPointerException var3) {
                  }

                  QuorumCnxManager.LOG.warn("destination address {} not reachable anymore, shutting down the SendWorker for sid {}", address.toString(), this.sid);
                  this.finish();
               }

            })).start();
         } else {
            QuorumCnxManager.LOG.debug("validation of destination address for sid {} is skipped (it is already running)", this.sid);
         }

      }
   }

   class RecvWorker extends ZooKeeperThread {
      Long sid;
      Socket sock;
      volatile boolean running = true;
      final DataInputStream din;
      final SendWorker sw;

      RecvWorker(Socket sock, DataInputStream din, Long sid, SendWorker sw) {
         super("RecvWorker:" + sid);
         this.sid = sid;
         this.sock = sock;
         this.sw = sw;
         this.din = din;

         try {
            sock.setSoTimeout(0);
         } catch (IOException e) {
            QuorumCnxManager.LOG.error("Error while accessing socket for {}", sid, e);
            QuorumCnxManager.this.closeSocket(sock);
            this.running = false;
         }

      }

      synchronized boolean finish() {
         QuorumCnxManager.LOG.debug("RecvWorker.finish called. sid: {}. myId: {}", this.sid, QuorumCnxManager.this.mySid);
         if (!this.running) {
            return this.running;
         } else {
            this.running = false;
            this.interrupt();
            QuorumCnxManager.this.threadCnt.decrementAndGet();
            return this.running;
         }
      }

      public void run() {
         QuorumCnxManager.this.threadCnt.incrementAndGet();

         try {
            QuorumCnxManager.LOG.debug("RecvWorker thread towards {} started. myId: {}", this.sid, QuorumCnxManager.this.mySid);

            while(this.running && !QuorumCnxManager.this.shutdown && this.sock != null) {
               int length = this.din.readInt();
               if (length <= 0 || length > 524288) {
                  throw new IOException("Received packet with invalid packet: " + length);
               }

               byte[] msgArray = new byte[length];
               this.din.readFully(msgArray, 0, length);
               QuorumCnxManager.this.addToRecvQueue(new Message(ByteBuffer.wrap(msgArray), this.sid));
            }
         } catch (Exception e) {
            QuorumCnxManager.LOG.warn("Connection broken for id {}, my id = {}", new Object[]{this.sid, QuorumCnxManager.this.mySid, e});
         } finally {
            QuorumCnxManager.LOG.warn("Interrupting SendWorker thread from RecvWorker. sid: {}. myId: {}", this.sid, QuorumCnxManager.this.mySid);
            this.sw.finish();
            QuorumCnxManager.this.closeSocket(this.sock);
         }

      }
   }
}
