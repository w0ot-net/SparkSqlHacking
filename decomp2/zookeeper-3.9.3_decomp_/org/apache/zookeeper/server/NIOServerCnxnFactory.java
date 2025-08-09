package org.apache.zookeeper.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NIOServerCnxnFactory extends ServerCnxnFactory {
   private static final Logger LOG = LoggerFactory.getLogger(NIOServerCnxnFactory.class);
   public static final String ZOOKEEPER_NIO_SESSIONLESS_CNXN_TIMEOUT = "zookeeper.nio.sessionlessCnxnTimeout";
   public static final String ZOOKEEPER_NIO_NUM_SELECTOR_THREADS = "zookeeper.nio.numSelectorThreads";
   public static final String ZOOKEEPER_NIO_NUM_WORKER_THREADS = "zookeeper.nio.numWorkerThreads";
   public static final String ZOOKEEPER_NIO_DIRECT_BUFFER_BYTES = "zookeeper.nio.directBufferBytes";
   public static final String ZOOKEEPER_NIO_SHUTDOWN_TIMEOUT = "zookeeper.nio.shutdownTimeout";
   ServerSocketChannel ss;
   private static final ThreadLocal directBuffer;
   private final ConcurrentHashMap ipMap = new ConcurrentHashMap();
   protected int maxClientCnxns = 60;
   int listenBacklog = -1;
   int sessionlessCnxnTimeout;
   private ExpiryQueue cnxnExpiryQueue;
   protected WorkerService workerPool;
   private static int directBufferBytes;
   private int numSelectorThreads;
   private int numWorkerThreads;
   private long workerShutdownTimeoutMS;
   private volatile boolean stopped = true;
   private ConnectionExpirerThread expirerThread;
   private AcceptThread acceptThread;
   private final Set selectorThreads = new HashSet();

   public static ByteBuffer getDirectBuffer() {
      return directBufferBytes > 0 ? (ByteBuffer)directBuffer.get() : null;
   }

   public void configure(InetSocketAddress addr, int maxcc, int backlog, boolean secure) throws IOException {
      if (secure) {
         throw new UnsupportedOperationException("SSL isn't supported in NIOServerCnxn");
      } else {
         this.configureSaslLogin();
         this.maxClientCnxns = maxcc;
         this.initMaxCnxns();
         this.sessionlessCnxnTimeout = Integer.getInteger("zookeeper.nio.sessionlessCnxnTimeout", 10000);
         this.cnxnExpiryQueue = new ExpiryQueue(this.sessionlessCnxnTimeout);
         this.expirerThread = new ConnectionExpirerThread();
         int numCores = Runtime.getRuntime().availableProcessors();
         this.numSelectorThreads = Integer.getInteger("zookeeper.nio.numSelectorThreads", Math.max((int)Math.sqrt((double)((float)numCores / 2.0F)), 1));
         if (this.numSelectorThreads < 1) {
            throw new IOException("numSelectorThreads must be at least 1");
         } else {
            this.numWorkerThreads = Integer.getInteger("zookeeper.nio.numWorkerThreads", 2 * numCores);
            this.workerShutdownTimeoutMS = Long.getLong("zookeeper.nio.shutdownTimeout", 5000L);
            String logMsg = "Configuring NIO connection handler with " + this.sessionlessCnxnTimeout / 1000 + "s sessionless connection timeout, " + this.numSelectorThreads + " selector thread(s), " + (this.numWorkerThreads > 0 ? this.numWorkerThreads : "no") + " worker threads, and " + (directBufferBytes == 0 ? "gathered writes." : "" + directBufferBytes / 1024 + " kB direct buffers.");
            LOG.info(logMsg);

            for(int i = 0; i < this.numSelectorThreads; ++i) {
               this.selectorThreads.add(new SelectorThread(i));
            }

            this.listenBacklog = backlog;
            this.ss = ServerSocketChannel.open();
            this.ss.socket().setReuseAddress(true);
            LOG.info("binding to port {}", addr);
            if (this.listenBacklog == -1) {
               this.ss.socket().bind(addr);
            } else {
               this.ss.socket().bind(addr, this.listenBacklog);
            }

            if (addr.getPort() == 0) {
               LOG.info("bound to port {}", this.ss.getLocalAddress());
            }

            this.ss.configureBlocking(false);
            this.acceptThread = new AcceptThread(this.ss, addr, this.selectorThreads);
         }
      }
   }

   private void tryClose(ServerSocketChannel s) {
      try {
         s.close();
      } catch (IOException sse) {
         LOG.error("Error while closing server socket.", sse);
      }

   }

   public void reconfigure(InetSocketAddress addr) {
      ServerSocketChannel oldSS = this.ss;

      try {
         this.acceptThread.setReconfiguring();
         this.tryClose(oldSS);
         this.acceptThread.wakeupSelector();

         try {
            this.acceptThread.join();
         } catch (InterruptedException e) {
            LOG.error("Error joining old acceptThread when reconfiguring client port.", e);
            Thread.currentThread().interrupt();
         }

         this.ss = ServerSocketChannel.open();
         this.ss.socket().setReuseAddress(true);
         LOG.info("binding to port {}", addr);
         this.ss.socket().bind(addr);
         this.ss.configureBlocking(false);
         this.acceptThread = new AcceptThread(this.ss, addr, this.selectorThreads);
         this.acceptThread.start();
      } catch (IOException e) {
         LOG.error("Error reconfiguring client port to {}", addr, e);
         this.tryClose(oldSS);
      }

   }

   public int getMaxClientCnxnsPerHost() {
      return this.maxClientCnxns;
   }

   public void setMaxClientCnxnsPerHost(int max) {
      this.maxClientCnxns = max;
   }

   public int getSocketListenBacklog() {
      return this.listenBacklog;
   }

   public void start() {
      this.stopped = false;
      if (this.workerPool == null) {
         this.workerPool = new WorkerService("NIOWorker", this.numWorkerThreads, false);
      }

      for(SelectorThread thread : this.selectorThreads) {
         if (thread.getState() == State.NEW) {
            thread.start();
         }
      }

      if (this.acceptThread.getState() == State.NEW) {
         this.acceptThread.start();
      }

      if (this.expirerThread.getState() == State.NEW) {
         this.expirerThread.start();
      }

   }

   public void startup(ZooKeeperServer zks, boolean startServer) throws IOException, InterruptedException {
      this.start();
      this.setZooKeeperServer(zks);
      if (startServer) {
         zks.startdata();
         zks.startup();
      }

   }

   public InetSocketAddress getLocalAddress() {
      return (InetSocketAddress)this.ss.socket().getLocalSocketAddress();
   }

   public int getLocalPort() {
      return this.ss.socket().getLocalPort();
   }

   public boolean removeCnxn(NIOServerCnxn cnxn) {
      if (!this.cnxns.remove(cnxn)) {
         return false;
      } else {
         this.cnxnExpiryQueue.remove(cnxn);
         this.removeCnxnFromSessionMap(cnxn);
         InetAddress addr = cnxn.getSocketAddress();
         if (addr != null) {
            Set<NIOServerCnxn> set = (Set)this.ipMap.get(addr);
            if (set != null) {
               set.remove(cnxn);
            }
         }

         this.unregisterConnection(cnxn);
         return true;
      }
   }

   public void touchCnxn(NIOServerCnxn cnxn) {
      this.cnxnExpiryQueue.update(cnxn, cnxn.getSessionTimeout());
   }

   private void addCnxn(NIOServerCnxn cnxn) throws IOException {
      InetAddress addr = cnxn.getSocketAddress();
      if (addr == null) {
         throw new IOException("Socket of " + cnxn + " has been closed");
      } else {
         Set<NIOServerCnxn> set = (Set)this.ipMap.get(addr);
         if (set == null) {
            set = Collections.newSetFromMap(new ConcurrentHashMap(2));
            Set<NIOServerCnxn> existingSet = (Set)this.ipMap.putIfAbsent(addr, set);
            if (existingSet != null) {
               set = existingSet;
            }
         }

         set.add(cnxn);
         this.cnxns.add(cnxn);
         this.touchCnxn(cnxn);
      }
   }

   protected NIOServerCnxn createConnection(SocketChannel sock, SelectionKey sk, SelectorThread selectorThread) throws IOException {
      return new NIOServerCnxn(this.zkServer, sock, sk, this, selectorThread);
   }

   private int getClientCnxnCount(InetAddress cl) {
      Set<NIOServerCnxn> s = (Set)this.ipMap.get(cl);
      return s == null ? 0 : s.size();
   }

   public void closeAll(ServerCnxn.DisconnectReason reason) {
      for(ServerCnxn cnxn : this.cnxns) {
         try {
            cnxn.close(reason);
         } catch (Exception e) {
            LOG.warn("Ignoring exception closing cnxn session id 0x{}", Long.toHexString(cnxn.getSessionId()), e);
         }
      }

   }

   public void stop() {
      this.stopped = true;

      try {
         this.ss.close();
      } catch (IOException e) {
         LOG.warn("Error closing listen socket", e);
      }

      if (this.acceptThread != null) {
         if (this.acceptThread.isAlive()) {
            this.acceptThread.wakeupSelector();
         } else {
            this.acceptThread.closeSelector();
         }
      }

      if (this.expirerThread != null) {
         this.expirerThread.interrupt();
      }

      for(SelectorThread thread : this.selectorThreads) {
         if (thread.isAlive()) {
            thread.wakeupSelector();
         } else {
            thread.closeSelector();
         }
      }

      if (this.workerPool != null) {
         this.workerPool.stop();
      }

   }

   public void shutdown() {
      try {
         this.stop();
         this.join();
         this.closeAll(ServerCnxn.DisconnectReason.SERVER_SHUTDOWN);
         if (this.login != null) {
            this.login.shutdown();
         }
      } catch (InterruptedException e) {
         LOG.warn("Ignoring interrupted exception during shutdown", e);
      } catch (Exception e) {
         LOG.warn("Ignoring unexpected exception during shutdown", e);
      }

      if (this.zkServer != null) {
         this.zkServer.shutdown();
      }

   }

   public void join() throws InterruptedException {
      if (this.acceptThread != null) {
         this.acceptThread.join();
      }

      for(SelectorThread thread : this.selectorThreads) {
         thread.join();
      }

      if (this.workerPool != null) {
         this.workerPool.join(this.workerShutdownTimeoutMS);
      }

   }

   public Iterable getConnections() {
      return this.cnxns;
   }

   public void dumpConnections(PrintWriter pwriter) {
      pwriter.print("Connections ");
      this.cnxnExpiryQueue.dump(pwriter);
   }

   public void resetAllConnectionStats() {
      for(ServerCnxn c : this.cnxns) {
         c.resetStats();
      }

   }

   public Iterable getAllConnectionInfo(boolean brief) {
      HashSet<Map<String, Object>> info = new HashSet();

      for(ServerCnxn c : this.cnxns) {
         info.add(c.getConnectionInfo(brief));
      }

      return info;
   }

   static {
      Thread.setDefaultUncaughtExceptionHandler((t, e) -> LOG.error("Thread {} died", t, e));
      directBufferBytes = Integer.getInteger("zookeeper.nio.directBufferBytes", 65536);
      directBuffer = new ThreadLocal() {
         protected ByteBuffer initialValue() {
            return ByteBuffer.allocateDirect(NIOServerCnxnFactory.directBufferBytes);
         }
      };
   }

   private abstract class AbstractSelectThread extends ZooKeeperThread {
      protected final Selector selector;

      public AbstractSelectThread(String name) throws IOException {
         super(name);
         this.setDaemon(true);
         this.selector = Selector.open();
      }

      public void wakeupSelector() {
         this.selector.wakeup();
      }

      protected void closeSelector() {
         try {
            this.selector.close();
         } catch (IOException e) {
            NIOServerCnxnFactory.LOG.warn("ignored exception during selector close.", e);
         }

      }

      protected void cleanupSelectionKey(SelectionKey key) {
         if (key != null) {
            try {
               key.cancel();
            } catch (Exception ex) {
               NIOServerCnxnFactory.LOG.debug("ignoring exception during selectionkey cancel", ex);
            }
         }

      }

      protected void fastCloseSock(SocketChannel sc) {
         if (sc != null) {
            try {
               sc.socket().setSoLinger(true, 0);
            } catch (SocketException e) {
               NIOServerCnxnFactory.LOG.warn("Unable to set socket linger to 0, socket close may stall in CLOSE_WAIT", e);
            }

            NIOServerCnxn.closeSock(sc);
         }

      }
   }

   private class AcceptThread extends AbstractSelectThread {
      private final ServerSocketChannel acceptSocket;
      private final SelectionKey acceptKey;
      private final RateLogger acceptErrorLogger;
      private final Collection selectorThreads;
      private Iterator selectorIterator;
      private volatile boolean reconfiguring;

      public AcceptThread(ServerSocketChannel ss, InetSocketAddress addr, Set selectorThreads) throws IOException {
         super("NIOServerCxnFactory.AcceptThread:" + addr);
         this.acceptErrorLogger = new RateLogger(NIOServerCnxnFactory.LOG);
         this.reconfiguring = false;
         this.acceptSocket = ss;
         this.acceptKey = this.acceptSocket.register(this.selector, 16);
         this.selectorThreads = Collections.unmodifiableList(new ArrayList(selectorThreads));
         this.selectorIterator = this.selectorThreads.iterator();
      }

      public void run() {
         try {
            while(!NIOServerCnxnFactory.this.stopped && !this.acceptSocket.socket().isClosed()) {
               try {
                  this.select();
               } catch (RuntimeException e) {
                  NIOServerCnxnFactory.LOG.warn("Ignoring unexpected runtime exception", e);
               } catch (Exception e) {
                  NIOServerCnxnFactory.LOG.warn("Ignoring unexpected exception", e);
               }
            }
         } finally {
            this.closeSelector();
            if (!this.reconfiguring) {
               NIOServerCnxnFactory.this.stop();
            }

            NIOServerCnxnFactory.LOG.info("accept thread exitted run method");
         }

      }

      public void setReconfiguring() {
         this.reconfiguring = true;
      }

      private void select() {
         try {
            this.selector.select();
            Iterator<SelectionKey> selectedKeys = this.selector.selectedKeys().iterator();

            while(!NIOServerCnxnFactory.this.stopped && selectedKeys.hasNext()) {
               SelectionKey key = (SelectionKey)selectedKeys.next();
               selectedKeys.remove();
               if (key.isValid()) {
                  if (key.isAcceptable()) {
                     if (!this.doAccept()) {
                        this.pauseAccept(10L);
                     }
                  } else {
                     NIOServerCnxnFactory.LOG.warn("Unexpected ops in accept select {}", key.readyOps());
                  }
               }
            }
         } catch (IOException e) {
            NIOServerCnxnFactory.LOG.warn("Ignoring IOException while selecting", e);
         }

      }

      private void pauseAccept(long millisecs) {
         this.acceptKey.interestOps(0);

         try {
            this.selector.select(millisecs);
         } catch (IOException var7) {
         } finally {
            this.acceptKey.interestOps(16);
         }

      }

      private boolean doAccept() {
         boolean accepted = false;
         SocketChannel sc = null;

         try {
            sc = this.acceptSocket.accept();
            accepted = true;
            if (NIOServerCnxnFactory.this.limitTotalNumberOfCnxns()) {
               throw new IOException("Too many connections max allowed is " + NIOServerCnxnFactory.this.maxCnxns);
            }

            InetAddress ia = sc.socket().getInetAddress();
            int cnxncount = NIOServerCnxnFactory.this.getClientCnxnCount(ia);
            if (NIOServerCnxnFactory.this.maxClientCnxns > 0 && cnxncount >= NIOServerCnxnFactory.this.maxClientCnxns) {
               throw new IOException("Too many connections from " + ia + " - max is " + NIOServerCnxnFactory.this.maxClientCnxns);
            }

            NIOServerCnxnFactory.LOG.debug("Accepted socket connection from {}", sc.socket().getRemoteSocketAddress());
            sc.configureBlocking(false);
            if (!this.selectorIterator.hasNext()) {
               this.selectorIterator = this.selectorThreads.iterator();
            }

            SelectorThread selectorThread = (SelectorThread)this.selectorIterator.next();
            if (!selectorThread.addAcceptedConnection(sc)) {
               throw new IOException("Unable to add connection to selector queue" + (NIOServerCnxnFactory.this.stopped ? " (shutdown in progress)" : ""));
            }

            this.acceptErrorLogger.flush();
         } catch (IOException e) {
            ServerMetrics.getMetrics().CONNECTION_REJECTED.add(1L);
            this.acceptErrorLogger.rateLimitLog("Error accepting new connection: " + e.getMessage());
            this.fastCloseSock(sc);
         }

         return accepted;
      }
   }

   public class SelectorThread extends AbstractSelectThread {
      private final int id;
      private final Queue acceptedQueue;
      private final Queue updateQueue;

      public SelectorThread(int id) throws IOException {
         super("NIOServerCxnFactory.SelectorThread-" + id);
         this.id = id;
         this.acceptedQueue = new LinkedBlockingQueue();
         this.updateQueue = new LinkedBlockingQueue();
      }

      public boolean addAcceptedConnection(SocketChannel accepted) {
         if (!NIOServerCnxnFactory.this.stopped && this.acceptedQueue.offer(accepted)) {
            this.wakeupSelector();
            return true;
         } else {
            return false;
         }
      }

      public boolean addInterestOpsUpdateRequest(SelectionKey sk) {
         if (!NIOServerCnxnFactory.this.stopped && this.updateQueue.offer(sk)) {
            this.wakeupSelector();
            return true;
         } else {
            return false;
         }
      }

      public void run() {
         try {
            while(!NIOServerCnxnFactory.this.stopped) {
               try {
                  this.select();
                  this.processAcceptedConnections();
                  this.processInterestOpsUpdateRequests();
               } catch (RuntimeException e) {
                  NIOServerCnxnFactory.LOG.warn("Ignoring unexpected runtime exception", e);
               } catch (Exception e) {
                  NIOServerCnxnFactory.LOG.warn("Ignoring unexpected exception", e);
               }
            }

            for(SelectionKey key : this.selector.keys()) {
               NIOServerCnxn cnxn = (NIOServerCnxn)key.attachment();
               if (cnxn.isSelectable()) {
                  cnxn.close(ServerCnxn.DisconnectReason.SERVER_SHUTDOWN);
               }

               this.cleanupSelectionKey(key);
            }

            SocketChannel accepted;
            while((accepted = (SocketChannel)this.acceptedQueue.poll()) != null) {
               this.fastCloseSock(accepted);
            }

            this.updateQueue.clear();
         } finally {
            this.closeSelector();
            NIOServerCnxnFactory.this.stop();
            NIOServerCnxnFactory.LOG.info("selector thread exitted run method");
         }
      }

      private void select() {
         try {
            this.selector.select();
            Set<SelectionKey> selected = this.selector.selectedKeys();
            ArrayList<SelectionKey> selectedList = new ArrayList(selected);
            Collections.shuffle(selectedList);
            Iterator<SelectionKey> selectedKeys = selectedList.iterator();

            while(!NIOServerCnxnFactory.this.stopped && selectedKeys.hasNext()) {
               SelectionKey key = (SelectionKey)selectedKeys.next();
               selected.remove(key);
               if (!key.isValid()) {
                  this.cleanupSelectionKey(key);
               } else if (!key.isReadable() && !key.isWritable()) {
                  NIOServerCnxnFactory.LOG.warn("Unexpected ops in select {}", key.readyOps());
               } else {
                  this.handleIO(key);
               }
            }
         } catch (IOException e) {
            NIOServerCnxnFactory.LOG.warn("Ignoring IOException while selecting", e);
         }

      }

      private void handleIO(SelectionKey key) {
         IOWorkRequest workRequest = NIOServerCnxnFactory.this.new IOWorkRequest(this, key);
         NIOServerCnxn cnxn = (NIOServerCnxn)key.attachment();
         cnxn.disableSelectable();
         key.interestOps(0);
         NIOServerCnxnFactory.this.touchCnxn(cnxn);
         NIOServerCnxnFactory.this.workerPool.schedule(workRequest);
      }

      private void processAcceptedConnections() {
         SocketChannel accepted;
         while(!NIOServerCnxnFactory.this.stopped && (accepted = (SocketChannel)this.acceptedQueue.poll()) != null) {
            SelectionKey key = null;

            try {
               key = accepted.register(this.selector, 1);
               NIOServerCnxn cnxn = NIOServerCnxnFactory.this.createConnection(accepted, key, this);
               key.attach(cnxn);
               NIOServerCnxnFactory.this.addCnxn(cnxn);
            } catch (IOException var4) {
               this.cleanupSelectionKey(key);
               this.fastCloseSock(accepted);
            }
         }

      }

      private void processInterestOpsUpdateRequests() {
         SelectionKey key;
         while(!NIOServerCnxnFactory.this.stopped && (key = (SelectionKey)this.updateQueue.poll()) != null) {
            if (!key.isValid()) {
               this.cleanupSelectionKey(key);
            }

            NIOServerCnxn cnxn = (NIOServerCnxn)key.attachment();
            if (cnxn.isSelectable()) {
               key.interestOps(cnxn.getInterestOps());
            }
         }

      }
   }

   private class IOWorkRequest extends WorkerService.WorkRequest {
      private final SelectorThread selectorThread;
      private final SelectionKey key;
      private final NIOServerCnxn cnxn;

      IOWorkRequest(SelectorThread selectorThread, SelectionKey key) {
         this.selectorThread = selectorThread;
         this.key = key;
         this.cnxn = (NIOServerCnxn)key.attachment();
      }

      public void doWork() throws InterruptedException {
         if (!this.key.isValid()) {
            this.selectorThread.cleanupSelectionKey(this.key);
         } else {
            if (this.key.isReadable() || this.key.isWritable()) {
               this.cnxn.doIO(this.key);
               if (NIOServerCnxnFactory.this.stopped) {
                  this.cnxn.close(ServerCnxn.DisconnectReason.SERVER_SHUTDOWN);
                  return;
               }

               if (!this.key.isValid()) {
                  this.selectorThread.cleanupSelectionKey(this.key);
                  return;
               }

               NIOServerCnxnFactory.this.touchCnxn(this.cnxn);
            }

            this.cnxn.enableSelectable();
            if (!this.selectorThread.addInterestOpsUpdateRequest(this.key)) {
               this.cnxn.close(ServerCnxn.DisconnectReason.CONNECTION_MODE_CHANGED);
            }

         }
      }

      public void cleanup() {
         this.cnxn.close(ServerCnxn.DisconnectReason.CLEAN_UP);
      }
   }

   private class ConnectionExpirerThread extends ZooKeeperThread {
      ConnectionExpirerThread() {
         super("ConnnectionExpirer");
      }

      public void run() {
         try {
            while(!NIOServerCnxnFactory.this.stopped) {
               long waitTime = NIOServerCnxnFactory.this.cnxnExpiryQueue.getWaitTime();
               if (waitTime > 0L) {
                  Thread.sleep(waitTime);
               } else {
                  for(NIOServerCnxn conn : NIOServerCnxnFactory.this.cnxnExpiryQueue.poll()) {
                     ServerMetrics.getMetrics().SESSIONLESS_CONNECTIONS_EXPIRED.add(1L);
                     conn.close(ServerCnxn.DisconnectReason.CONNECTION_EXPIRED);
                  }
               }
            }
         } catch (InterruptedException var5) {
            NIOServerCnxnFactory.LOG.info("ConnnectionExpirerThread interrupted");
         }

      }
   }
}
