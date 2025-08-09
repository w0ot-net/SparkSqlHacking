package org.apache.zookeeper.server.quorum;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.zookeeper.jmx.MBeanRegistry;
import org.apache.zookeeper.server.Request;
import org.apache.zookeeper.server.ZKDatabase;
import org.apache.zookeeper.server.quorum.auth.QuorumAuthServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObserverMaster extends LearnerMaster implements Runnable {
   private static final Logger LOG = LoggerFactory.getLogger(ObserverMaster.class);
   private final AtomicLong followerCounter = new AtomicLong(-1L);
   private QuorumPeer self;
   private FollowerZooKeeperServer zks;
   private int port;
   private Set activeObservers = Collections.newSetFromMap(new ConcurrentHashMap());
   private final ConcurrentHashMap connectionBeans = new ConcurrentHashMap();
   private static final int PKTS_SIZE_LIMIT = 33554432;
   private static volatile int pktsSizeLimit = Integer.getInteger("zookeeper.observerMaster.sizeLimit", 33554432);
   private ConcurrentLinkedQueue proposedPkts = new ConcurrentLinkedQueue();
   private ConcurrentLinkedQueue committedPkts = new ConcurrentLinkedQueue();
   private int pktsSize = 0;
   private long lastProposedZxid;
   private final Object revalidateSessionLock = new Object();
   private final ConcurrentLinkedQueue pendingRevalidations = new ConcurrentLinkedQueue();
   private Thread thread;
   private ServerSocket ss;
   private boolean listenerRunning;
   private ScheduledExecutorService pinger;
   Runnable ping = new Runnable() {
      public void run() {
         for(LearnerHandler lh : ObserverMaster.this.activeObservers) {
            lh.ping();
         }

      }
   };

   ObserverMaster(QuorumPeer self, FollowerZooKeeperServer zks, int port) {
      this.self = self;
      this.zks = zks;
      this.port = port;
   }

   public void addLearnerHandler(LearnerHandler learnerHandler) {
      if (!this.listenerRunning) {
         throw new RuntimeException("ObserverMaster is not running");
      }
   }

   public void removeLearnerHandler(LearnerHandler learnerHandler) {
      this.activeObservers.remove(learnerHandler);
   }

   public int syncTimeout() {
      return this.self.getSyncLimit() * this.self.getTickTime();
   }

   public int getTickOfNextAckDeadline() {
      return this.self.tick.get() + this.self.syncLimit;
   }

   public int getTickOfInitialAckDeadline() {
      return this.self.tick.get() + this.self.initLimit + this.self.syncLimit;
   }

   public long getAndDecrementFollowerCounter() {
      return this.followerCounter.getAndDecrement();
   }

   public void waitForEpochAck(long sid, StateSummary ss) throws IOException, InterruptedException {
   }

   public void waitForStartup() throws InterruptedException {
   }

   public synchronized long getLastProposed() {
      return this.lastProposedZxid;
   }

   public long getEpochToPropose(long sid, long lastAcceptedEpoch) throws InterruptedException, IOException {
      return this.self.getCurrentEpoch();
   }

   public ZKDatabase getZKDatabase() {
      return this.zks.getZKDatabase();
   }

   public void waitForNewLeaderAck(long sid, long zxid) throws InterruptedException {
   }

   public int getCurrentTick() {
      return this.self.tick.get();
   }

   public void processAck(long sid, long zxid, SocketAddress localSocketAddress) {
      if ((zxid & 4294967295L) != 0L) {
         throw new RuntimeException("Observers shouldn't send ACKS ack = " + Long.toHexString(zxid));
      }
   }

   public void touch(long sess, int to) {
      this.zks.getSessionTracker().touchSession(sess, to);
   }

   boolean revalidateLearnerSession(QuorumPacket qp) throws IOException {
      ByteArrayInputStream bis = new ByteArrayInputStream(qp.getData());
      DataInputStream dis = new DataInputStream(bis);
      long id = dis.readLong();
      boolean valid = dis.readBoolean();
      Iterator<Revalidation> itr = this.pendingRevalidations.iterator();
      if (!itr.hasNext()) {
         return false;
      } else {
         Revalidation revalidation = (Revalidation)itr.next();
         if (revalidation.sessionId != id) {
            return false;
         } else {
            itr.remove();
            LearnerHandler learnerHandler = revalidation.handler;
            QuorumPacket deepCopy = new QuorumPacket(qp.getType(), qp.getZxid(), Arrays.copyOf(qp.getData(), qp.getData().length), qp.getAuthinfo() == null ? null : new ArrayList(qp.getAuthinfo()));
            learnerHandler.queuePacket(deepCopy);
            if (valid) {
               this.touch(revalidation.sessionId, revalidation.timeout);
            }

            return true;
         }
      }
   }

   public void revalidateSession(QuorumPacket qp, LearnerHandler learnerHandler) throws IOException {
      ByteArrayInputStream bis = new ByteArrayInputStream(qp.getData());
      DataInputStream dis = new DataInputStream(bis);
      long id = dis.readLong();
      int to = dis.readInt();
      synchronized(this.revalidateSessionLock) {
         this.pendingRevalidations.add(new Revalidation(id, to, learnerHandler));
         Learner learner = this.zks.getLearner();
         if (learner != null) {
            learner.writePacket(qp, true);
         }

      }
   }

   public void submitLearnerRequest(Request si) {
      this.zks.processObserverRequest(si);
   }

   public synchronized long startForwarding(LearnerHandler learnerHandler, long lastSeenZxid) {
      Iterator<QuorumPacket> itr = this.committedPkts.iterator();
      if (itr.hasNext()) {
         QuorumPacket packet = (QuorumPacket)itr.next();
         if (packet.getZxid() > lastSeenZxid + 1L) {
            LOG.error("LearnerHandler is too far behind (0x{} < 0x{}), disconnecting {} at {}", new Object[]{Long.toHexString(lastSeenZxid + 1L), Long.toHexString(packet.getZxid()), learnerHandler.getSid(), learnerHandler.getRemoteAddress()});
            learnerHandler.shutdown();
            return -1L;
         }

         if (packet.getZxid() == lastSeenZxid + 1L) {
            learnerHandler.queuePacket(packet);
         }

         long queueHeadZxid = packet.getZxid();
         long queueBytesUsed = LearnerHandler.packetSize(packet);

         while(itr.hasNext()) {
            packet = (QuorumPacket)itr.next();
            if (packet.getZxid() > lastSeenZxid) {
               learnerHandler.queuePacket(packet);
               queueBytesUsed += LearnerHandler.packetSize(packet);
            }
         }

         LOG.info("finished syncing observer from retained commit queue: sid {}, queue head 0x{}, queue tail 0x{}, sync position 0x{}, num packets used {}, num bytes used {}", new Object[]{learnerHandler.getSid(), Long.toHexString(queueHeadZxid), Long.toHexString(packet.getZxid()), Long.toHexString(lastSeenZxid), packet.getZxid() - lastSeenZxid, queueBytesUsed});
      }

      this.activeObservers.add(learnerHandler);
      return this.lastProposedZxid;
   }

   public long getQuorumVerifierVersion() {
      return this.self.getQuorumVerifier().getVersion();
   }

   public String getPeerInfo(long sid) {
      QuorumPeer.QuorumServer server = (QuorumPeer.QuorumServer)this.self.getView().get(sid);
      return server == null ? "" : server.toString();
   }

   public byte[] getQuorumVerifierBytes() {
      return this.self.getLastSeenQuorumVerifier().toString().getBytes(StandardCharsets.UTF_8);
   }

   public QuorumAuthServer getQuorumAuthServer() {
      return this.self == null ? null : this.self.authServer;
   }

   void proposalReceived(QuorumPacket qp) {
      this.proposedPkts.add(new QuorumPacket(8, qp.getZxid(), qp.getData(), (List)null));
   }

   private synchronized QuorumPacket removeProposedPacket(long zxid) {
      QuorumPacket pkt = (QuorumPacket)this.proposedPkts.peek();
      if (pkt != null && pkt.getZxid() <= zxid) {
         if (pkt.getZxid() != zxid) {
            String m = String.format("Unexpected proposal packet on commit ack, expected zxid 0x%d got zxid 0x%d", zxid, pkt.getZxid());
            LOG.error(m);
            throw new RuntimeException(m);
         } else {
            this.proposedPkts.remove();
            return pkt;
         }
      } else {
         LOG.debug("ignore missing proposal packet for {}", Long.toHexString(zxid));
         return null;
      }
   }

   private synchronized void cacheCommittedPacket(QuorumPacket pkt) {
      this.committedPkts.add(pkt);
      this.pktsSize = (int)((long)this.pktsSize + LearnerHandler.packetSize(pkt));

      for(int i = 0; (double)this.pktsSize > (double)pktsSizeLimit * 0.8 && i < 5; ++i) {
         QuorumPacket oldPkt = (QuorumPacket)this.committedPkts.poll();
         if (oldPkt == null) {
            this.pktsSize = 0;
            break;
         }

         this.pktsSize = (int)((long)this.pktsSize - LearnerHandler.packetSize(oldPkt));
      }

      while(this.pktsSize > pktsSizeLimit) {
         QuorumPacket oldPkt = (QuorumPacket)this.committedPkts.poll();
         if (oldPkt == null) {
            this.pktsSize = 0;
            break;
         }

         this.pktsSize = (int)((long)this.pktsSize - LearnerHandler.packetSize(oldPkt));
      }

   }

   private synchronized void sendPacket(QuorumPacket pkt) {
      for(LearnerHandler lh : this.activeObservers) {
         lh.queuePacket(pkt);
      }

      this.lastProposedZxid = pkt.getZxid();
   }

   synchronized void proposalCommitted(long zxid) {
      QuorumPacket pkt = this.removeProposedPacket(zxid);
      if (pkt != null) {
         this.cacheCommittedPacket(pkt);
         this.sendPacket(pkt);
      }
   }

   synchronized void informAndActivate(long zxid, long suggestedLeaderId) {
      QuorumPacket pkt = this.removeProposedPacket(zxid);
      if (pkt != null) {
         QuorumPacket informAndActivateQP = Leader.buildInformAndActivePacket(zxid, suggestedLeaderId, pkt.getData());
         this.cacheCommittedPacket(informAndActivateQP);
         this.sendPacket(informAndActivateQP);
      }
   }

   public synchronized void start() throws IOException {
      if (this.thread == null || !this.thread.isAlive()) {
         this.listenerRunning = true;
         int backlog = 10;
         InetAddress address = this.self.getQuorumAddress().getReachableOrOne().getAddress();
         if (!this.self.shouldUsePortUnification() && !this.self.isSslQuorum()) {
            if (this.self.getQuorumListenOnAllIPs()) {
               this.ss = new ServerSocket(this.port, backlog);
            } else {
               this.ss = new ServerSocket(this.port, backlog, address);
            }
         } else {
            boolean allowInsecureConnection = this.self.shouldUsePortUnification();
            if (this.self.getQuorumListenOnAllIPs()) {
               this.ss = new UnifiedServerSocket(this.self.getX509Util(), allowInsecureConnection, this.port, backlog);
            } else {
               this.ss = new UnifiedServerSocket(this.self.getX509Util(), allowInsecureConnection, this.port, backlog, address);
            }
         }

         this.thread = new Thread(this, "ObserverMaster");
         this.thread.start();
         this.pinger = Executors.newSingleThreadScheduledExecutor();
         this.pinger.scheduleAtFixedRate(this.ping, (long)(this.self.tickTime / 2), (long)(this.self.tickTime / 2), TimeUnit.MILLISECONDS);
      }
   }

   public void run() {
      ServerSocket ss;
      synchronized(this) {
         ss = this.ss;
      }

      while(this.listenerRunning) {
         try {
            Socket s = ss.accept();
            s.setSoTimeout(this.self.tickTime * this.self.initLimit);
            BufferedInputStream is = new BufferedInputStream(s.getInputStream());
            LearnerHandler lh = new LearnerHandler(s, is, this);
            lh.start();
         } catch (Exception e) {
            if (this.listenerRunning) {
               LOG.debug("Ignoring accept exception (maybe shutting down)", e);
            } else {
               LOG.debug("Ignoring accept exception (maybe client closed)", e);
            }
         }
      }

   }

   public synchronized void stop() {
      this.listenerRunning = false;
      if (this.pinger != null) {
         this.pinger.shutdownNow();
      }

      if (this.ss != null) {
         try {
            this.ss.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }

      for(LearnerHandler lh : this.activeObservers) {
         lh.shutdown();
      }

   }

   int getNumActiveObservers() {
      return this.activeObservers.size();
   }

   public Iterable getActiveObservers() {
      Set<Map<String, Object>> info = new HashSet();

      for(LearnerHandler lh : this.activeObservers) {
         info.add(lh.getLearnerHandlerInfo());
      }

      return info;
   }

   public void resetObserverConnectionStats() {
      for(LearnerHandler lh : this.activeObservers) {
         lh.resetObserverConnectionStats();
      }

   }

   int getPktsSizeLimit() {
      return pktsSizeLimit;
   }

   static void setPktsSizeLimit(int sizeLimit) {
      pktsSizeLimit = sizeLimit;
   }

   public void registerLearnerHandlerBean(LearnerHandler learnerHandler, Socket socket) {
      LearnerHandlerBean bean = new LearnerHandlerBean(learnerHandler, socket);
      if (this.zks.registerJMX(bean)) {
         this.connectionBeans.put(learnerHandler, bean);
      }

   }

   public void unregisterLearnerHandlerBean(LearnerHandler learnerHandler) {
      LearnerHandlerBean bean = (LearnerHandlerBean)this.connectionBeans.remove(learnerHandler);
      if (bean != null) {
         MBeanRegistry.getInstance().unregister(bean);
      }

   }

   static class Revalidation {
      public final long sessionId;
      public final int timeout;
      public final LearnerHandler handler;

      Revalidation(Long sessionId, int timeout, LearnerHandler handler) {
         this.sessionId = sessionId;
         this.timeout = timeout;
         this.handler = handler;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            Revalidation that = (Revalidation)o;
            return this.sessionId == that.sessionId && this.timeout == that.timeout && this.handler.equals(that.handler);
         } else {
            return false;
         }
      }

      public int hashCode() {
         int result = (int)(this.sessionId ^ this.sessionId >>> 32);
         result = 31 * result + this.timeout;
         result = 31 * result + this.handler.hashCode();
         return result;
      }
   }
}
