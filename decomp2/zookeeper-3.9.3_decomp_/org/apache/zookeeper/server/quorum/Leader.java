package org.apache.zookeeper.server.quorum;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.security.sasl.SaslException;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.common.Time;
import org.apache.zookeeper.jmx.MBeanRegistry;
import org.apache.zookeeper.server.ExitCode;
import org.apache.zookeeper.server.FinalRequestProcessor;
import org.apache.zookeeper.server.Request;
import org.apache.zookeeper.server.RequestProcessor;
import org.apache.zookeeper.server.ServerMetrics;
import org.apache.zookeeper.server.ZKDatabase;
import org.apache.zookeeper.server.ZooKeeperCriticalThread;
import org.apache.zookeeper.server.ZooKeeperServer;
import org.apache.zookeeper.server.ZooTrace;
import org.apache.zookeeper.server.quorum.auth.QuorumAuthServer;
import org.apache.zookeeper.server.quorum.flexible.QuorumVerifier;
import org.apache.zookeeper.server.util.ZxidUtils;
import org.apache.zookeeper.util.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Leader extends LearnerMaster {
   private static final Logger LOG = LoggerFactory.getLogger(Leader.class);
   private static final boolean nodelay = System.getProperty("leader.nodelay", "true").equals("true");
   private static final String ACK_LOGGING_FREQUENCY = "zookeeper.leader.ackLoggingFrequency";
   private static int ackLoggingFrequency;
   final LeaderZooKeeperServer zk;
   final QuorumPeer self;
   protected boolean quorumFormed = false;
   volatile LearnerCnxAcceptor cnxAcceptor = null;
   private final HashSet learners = new HashSet();
   private final BufferStats proposalStats;
   private final ConcurrentHashMap connectionBeans = new ConcurrentHashMap();
   private final HashSet forwardingFollowers = new HashSet();
   private final HashSet observingLearners = new HashSet();
   private final Map pendingSyncs = new HashMap();
   final AtomicLong followerCounter = new AtomicLong(-1L);
   private final List serverSockets = new LinkedList();
   static final int DIFF = 13;
   static final int TRUNC = 14;
   static final int SNAP = 15;
   static final int OBSERVERINFO = 16;
   static final int NEWLEADER = 10;
   static final int FOLLOWERINFO = 11;
   static final int UPTODATE = 12;
   public static final int LEADERINFO = 17;
   public static final int ACKEPOCH = 18;
   static final int REQUEST = 1;
   public static final int PROPOSAL = 2;
   static final int ACK = 3;
   static final int COMMIT = 4;
   static final int PING = 5;
   static final int REVALIDATE = 6;
   static final int SYNC = 7;
   static final int INFORM = 8;
   static final int COMMITANDACTIVATE = 9;
   static final int INFORMANDACTIVATE = 19;
   final ConcurrentMap outstandingProposals = new ConcurrentHashMap();
   private final ConcurrentLinkedQueue toBeApplied = new ConcurrentLinkedQueue();
   protected final Proposal newLeaderProposal = new Proposal();
   StateSummary leaderStateSummary;
   long epoch = -1L;
   boolean waitingForNewEpoch = true;
   boolean allowedToCommit = true;
   private long leaderStartTime;
   boolean isShutdown;
   long lastCommitted = -1L;
   long lastProposed;
   protected final Set connectingFollowers = new HashSet();
   private volatile boolean quitWaitForEpoch = false;
   private volatile long timeStartWaitForEpoch = -1L;
   private volatile SyncedLearnerTracker voteSet;
   public static final String MAX_TIME_TO_WAIT_FOR_EPOCH = "zookeeper.leader.maxTimeToWaitForEpoch";
   private static int maxTimeToWaitForEpoch;
   protected final Set electingFollowers = new HashSet();
   protected boolean electionFinished = false;

   public static void setAckLoggingFrequency(int frequency) {
      ackLoggingFrequency = frequency;
   }

   public static int getAckLoggingFrequency() {
      return ackLoggingFrequency;
   }

   public BufferStats getProposalStats() {
      return this.proposalStats;
   }

   public List getLearners() {
      synchronized(this.learners) {
         return new ArrayList(this.learners);
      }
   }

   public List getForwardingFollowers() {
      synchronized(this.forwardingFollowers) {
         return new ArrayList(this.forwardingFollowers);
      }
   }

   public List getNonVotingFollowers() {
      List<LearnerHandler> nonVotingFollowers = new ArrayList();
      synchronized(this.forwardingFollowers) {
         for(LearnerHandler lh : this.forwardingFollowers) {
            if (!this.isParticipant(lh.getSid())) {
               nonVotingFollowers.add(lh);
            }
         }

         return nonVotingFollowers;
      }
   }

   void addForwardingFollower(LearnerHandler lh) {
      synchronized(this.forwardingFollowers) {
         this.forwardingFollowers.add(lh);
         this.self.getQuorumVerifier().updateNeedOracle(new ArrayList(this.forwardingFollowers));
      }
   }

   public List getObservingLearners() {
      synchronized(this.observingLearners) {
         return new ArrayList(this.observingLearners);
      }
   }

   private void addObserverLearnerHandler(LearnerHandler lh) {
      synchronized(this.observingLearners) {
         this.observingLearners.add(lh);
      }
   }

   public Iterable getObservingLearnersInfo() {
      Set<Map<String, Object>> info = new HashSet();
      synchronized(this.observingLearners) {
         for(LearnerHandler lh : this.observingLearners) {
            info.add(lh.getLearnerHandlerInfo());
         }

         return info;
      }
   }

   public void resetObserverConnectionStats() {
      synchronized(this.observingLearners) {
         for(LearnerHandler lh : this.observingLearners) {
            lh.resetObserverConnectionStats();
         }

      }
   }

   public synchronized int getNumPendingSyncs() {
      return this.pendingSyncs.size();
   }

   public void addLearnerHandler(LearnerHandler learner) {
      synchronized(this.learners) {
         this.learners.add(learner);
      }
   }

   public void removeLearnerHandler(LearnerHandler peer) {
      synchronized(this.forwardingFollowers) {
         this.forwardingFollowers.remove(peer);
      }

      synchronized(this.learners) {
         this.learners.remove(peer);
      }

      synchronized(this.observingLearners) {
         this.observingLearners.remove(peer);
      }
   }

   boolean isLearnerSynced(LearnerHandler peer) {
      synchronized(this.forwardingFollowers) {
         return this.forwardingFollowers.contains(peer);
      }
   }

   public boolean isQuorumSynced(QuorumVerifier qv) {
      HashSet<Long> ids = new HashSet();
      if (qv.getVotingMembers().containsKey(this.self.getMyId())) {
         ids.add(this.self.getMyId());
      }

      synchronized(this.forwardingFollowers) {
         for(LearnerHandler learnerHandler : this.forwardingFollowers) {
            if (learnerHandler.synced() && qv.getVotingMembers().containsKey(learnerHandler.getSid())) {
               ids.add(learnerHandler.getSid());
            }
         }
      }

      return qv.containsQuorum(ids);
   }

   public Leader(QuorumPeer self, LeaderZooKeeperServer zk) throws IOException {
      this.self = self;
      this.proposalStats = new BufferStats();
      Set<InetSocketAddress> addresses;
      if (self.getQuorumListenOnAllIPs()) {
         addresses = self.getQuorumAddress().getWildcardAddresses();
      } else {
         addresses = self.getQuorumAddress().getAllAddresses();
      }

      Stream var10000 = addresses.stream().map((address) -> this.createServerSocket(address, self.shouldUsePortUnification(), self.isSslQuorum())).filter(Optional::isPresent).map(Optional::get);
      List var10001 = this.serverSockets;
      Objects.requireNonNull(var10001);
      var10000.forEach(var10001::add);
      if (this.serverSockets.isEmpty()) {
         throw new IOException("Leader failed to initialize any of the following sockets: " + addresses);
      } else {
         this.zk = zk;
      }
   }

   InetSocketAddress recreateInetSocketAddr(String hostString, int port) {
      return new InetSocketAddress(hostString, port);
   }

   Optional createServerSocket(InetSocketAddress address, boolean portUnification, boolean sslQuorum) {
      try {
         ServerSocket serverSocket;
         if (!portUnification && !sslQuorum) {
            serverSocket = new ServerSocket();
         } else {
            serverSocket = new UnifiedServerSocket(this.self.getX509Util(), portUnification);
         }

         serverSocket.setReuseAddress(true);
         serverSocket.bind(this.recreateInetSocketAddr(address.getHostString(), address.getPort()));
         return Optional.of(serverSocket);
      } catch (IOException e) {
         LOG.error("Couldn't bind to {}", address.toString(), e);
         return Optional.empty();
      }
   }

   public long getUptime() {
      return this.leaderStartTime > 0L ? Time.currentElapsedTime() - this.leaderStartTime : 0L;
   }

   void lead() throws IOException, InterruptedException {
      this.self.end_fle = Time.currentElapsedTime();
      long electionTimeTaken = this.self.end_fle - this.self.start_fle;
      this.self.setElectionTimeTaken(electionTimeTaken);
      ServerMetrics.getMetrics().ELECTION_TIME.add(electionTimeTaken);
      LOG.info("LEADING - LEADER ELECTION TOOK - {} {}", electionTimeTaken, "MS");
      this.self.start_fle = 0L;
      this.self.end_fle = 0L;
      this.zk.registerJMX(new LeaderBean(this, this.zk), this.self.jmxLocalPeerBean);

      try {
         this.self.setZabState(QuorumPeer.ZabState.DISCOVERY);
         this.self.tick.set(0);
         this.zk.loadData();
         this.leaderStateSummary = new StateSummary(this.self.getCurrentEpoch(), this.zk.getLastProcessedZxid());
         this.cnxAcceptor = new LearnerCnxAcceptor();
         this.cnxAcceptor.start();
         long epoch = this.getEpochToPropose(this.self.getMyId(), this.self.getAcceptedEpoch());
         this.zk.setZxid(ZxidUtils.makeZxid(epoch, 0L));
         synchronized(this) {
            this.lastProposed = this.zk.getZxid();
         }

         this.newLeaderProposal.packet = new QuorumPacket(10, this.zk.getZxid(), (byte[])null, (List)null);
         if ((this.newLeaderProposal.packet.getZxid() & 4294967295L) != 0L) {
            LOG.info("NEWLEADER proposal has Zxid of {}", Long.toHexString(this.newLeaderProposal.packet.getZxid()));
         }

         QuorumVerifier lastSeenQV = this.self.getLastSeenQuorumVerifier();
         QuorumVerifier curQV = this.self.getQuorumVerifier();
         if (curQV.getVersion() == 0L && curQV.getVersion() == lastSeenQV.getVersion()) {
            try {
               LOG.debug(String.format("set lastSeenQuorumVerifier to currentQuorumVerifier (%s)", curQV.toString()));
               QuorumVerifier newQV = this.self.configFromString(curQV.toString());
               newQV.setVersion(this.zk.getZxid());
               this.self.setLastSeenQuorumVerifier(newQV, true);
            } catch (Exception e) {
               throw new IOException(e);
            }
         }

         this.newLeaderProposal.addQuorumVerifier(this.self.getQuorumVerifier());
         if (this.self.getLastSeenQuorumVerifier().getVersion() > this.self.getQuorumVerifier().getVersion()) {
            this.newLeaderProposal.addQuorumVerifier(this.self.getLastSeenQuorumVerifier());
         }

         this.waitForEpochAck(this.self.getMyId(), this.leaderStateSummary);
         this.self.setCurrentEpoch(epoch);
         this.self.setLeaderAddressAndId(this.self.getQuorumAddress(), this.self.getMyId());
         this.self.setZabState(QuorumPeer.ZabState.SYNCHRONIZATION);

         try {
            this.waitForNewLeaderAck(this.self.getMyId(), this.zk.getZxid());
         } catch (InterruptedException var30) {
            this.shutdown("Waiting for a quorum of followers, only synced with sids: [ " + this.newLeaderProposal.ackSetsToString() + " ]");
            HashSet<Long> followerSet = new HashSet();

            for(LearnerHandler f : this.getLearners()) {
               if (this.self.getQuorumVerifier().getVotingMembers().containsKey(f.getSid())) {
                  followerSet.add(f.getSid());
               }
            }

            boolean initTicksShouldBeIncreased = true;

            for(SyncedLearnerTracker.QuorumVerifierAcksetPair qvAckset : this.newLeaderProposal.qvAcksetPairs) {
               if (!qvAckset.getQuorumVerifier().containsQuorum(followerSet)) {
                  initTicksShouldBeIncreased = false;
                  break;
               }
            }

            if (initTicksShouldBeIncreased) {
               LOG.warn("Enough followers present. Perhaps the initTicks need to be increased.");
            }

            return;
         }

         this.startZkServer();
         String initialZxid = System.getProperty("zookeeper.testingonly.initialZxid");
         if (initialZxid != null) {
            long zxid = Long.parseLong(initialZxid);
            this.zk.setZxid(this.zk.getZxid() & -4294967296L | zxid);
         }

         if (!System.getProperty("zookeeper.leaderServes", "yes").equals("no")) {
            this.self.setZooKeeperServer(this.zk);
         }

         this.self.setZabState(QuorumPeer.ZabState.BROADCAST);
         this.self.adminServer.setZooKeeperServer(this.zk);
         boolean tickSkip = true;
         String shutdownMessage = null;

         while(true) {
            synchronized(this) {
               long start = Time.currentElapsedTime();
               long cur = start;

               for(long end = start + (long)(this.self.tickTime / 2); cur < end; cur = Time.currentElapsedTime()) {
                  this.wait(end - cur);
               }

               if (!tickSkip) {
                  this.self.tick.incrementAndGet();
               }

               SyncedLearnerTracker syncedAckSet = new SyncedLearnerTracker();
               syncedAckSet.addQuorumVerifier(this.self.getQuorumVerifier());
               if (this.self.getLastSeenQuorumVerifier() != null && this.self.getLastSeenQuorumVerifier().getVersion() > this.self.getQuorumVerifier().getVersion()) {
                  syncedAckSet.addQuorumVerifier(this.self.getLastSeenQuorumVerifier());
               }

               syncedAckSet.addAck(this.self.getMyId());

               for(LearnerHandler f : this.getLearners()) {
                  if (f.synced()) {
                     syncedAckSet.addAck(f.getSid());
                  }
               }

               if (!this.isRunning()) {
                  shutdownMessage = "Unexpected internal error";
                  break;
               }

               if (!tickSkip && !syncedAckSet.hasAllQuorums() && (!this.self.getQuorumVerifier().overrideQuorumDecision(this.getForwardingFollowers()) || !this.self.getQuorumVerifier().revalidateOutstandingProp(this, new ArrayList(this.outstandingProposals.values()), this.lastCommitted))) {
                  shutdownMessage = "Not sufficient followers synced, only synced with sids: [ " + syncedAckSet.ackSetsToString() + " ]";
                  break;
               }

               tickSkip = !tickSkip;
            }

            for(LearnerHandler f : this.getLearners()) {
               f.ping();
            }
         }

         if (shutdownMessage != null) {
            this.shutdown(shutdownMessage);
         }

      } finally {
         this.zk.unregisterJMX(this);
      }
   }

   void shutdown(String reason) {
      LOG.info("Shutting down");
      if (!this.isShutdown) {
         LOG.info("Shutdown called. For the reason {}", reason);
         if (this.cnxAcceptor != null) {
            this.cnxAcceptor.halt();
         } else {
            this.closeSockets();
         }

         this.self.setZooKeeperServer((ZooKeeperServer)null);
         this.self.adminServer.setZooKeeperServer((ZooKeeperServer)null);
         this.self.closeAllConnections();
         if (this.zk != null) {
            this.zk.shutdown();
         }

         synchronized(this.learners) {
            Iterator<LearnerHandler> it = this.learners.iterator();

            while(it.hasNext()) {
               LearnerHandler f = (LearnerHandler)it.next();
               it.remove();
               f.shutdown();
            }
         }

         this.isShutdown = true;
      }
   }

   synchronized void closeSockets() {
      for(ServerSocket serverSocket : this.serverSockets) {
         if (!serverSocket.isClosed()) {
            try {
               serverSocket.close();
            } catch (IOException e) {
               LOG.warn("Ignoring unexpected exception during close {}", serverSocket, e);
            }
         }
      }

   }

   private long getDesignatedLeader(Proposal reconfigProposal, long zxid) {
      SyncedLearnerTracker.QuorumVerifierAcksetPair newQVAcksetPair = (SyncedLearnerTracker.QuorumVerifierAcksetPair)reconfigProposal.qvAcksetPairs.get(reconfigProposal.qvAcksetPairs.size() - 1);
      if (newQVAcksetPair.getQuorumVerifier().getVotingMembers().containsKey(this.self.getMyId()) && ((QuorumPeer.QuorumServer)newQVAcksetPair.getQuorumVerifier().getVotingMembers().get(this.self.getMyId())).addr.equals(this.self.getQuorumAddress())) {
         return this.self.getMyId();
      } else {
         HashSet<Long> candidates = new HashSet(newQVAcksetPair.getAckset());
         candidates.remove(this.self.getMyId());
         long curCandidate = (Long)candidates.iterator().next();
         long curZxid = zxid + 1L;

         for(Proposal p = (Proposal)this.outstandingProposals.get(curZxid); p != null && !candidates.isEmpty(); p = (Proposal)this.outstandingProposals.get(curZxid)) {
            for(SyncedLearnerTracker.QuorumVerifierAcksetPair qvAckset : p.qvAcksetPairs) {
               candidates.retainAll(qvAckset.getAckset());
               if (candidates.isEmpty()) {
                  return curCandidate;
               }

               curCandidate = (Long)candidates.iterator().next();
               if (candidates.size() == 1) {
                  return curCandidate;
               }
            }

            ++curZxid;
         }

         return curCandidate;
      }
   }

   public synchronized boolean tryToCommit(Proposal p, long zxid, SocketAddress followerAddr) {
      if (this.outstandingProposals.containsKey(zxid - 1L)) {
         return false;
      } else if (!p.hasAllQuorums()) {
         return false;
      } else {
         if (zxid != this.lastCommitted + 1L) {
            LOG.warn("Commiting zxid 0x{} from {} not first!", Long.toHexString(zxid), followerAddr);
            LOG.warn("First is 0x{}", Long.toHexString(this.lastCommitted + 1L));
         }

         this.outstandingProposals.remove(zxid);
         if (p.request != null) {
            this.toBeApplied.add(p);
         }

         if (p.request == null) {
            LOG.warn("Going to commit null: {}", p);
         } else if (p.request.getHdr().getType() == 16) {
            LOG.debug("Committing a reconfiguration! {}", this.outstandingProposals.size());
            Long designatedLeader = this.getDesignatedLeader(p, zxid);
            QuorumVerifier newQV = ((SyncedLearnerTracker.QuorumVerifierAcksetPair)p.qvAcksetPairs.get(p.qvAcksetPairs.size() - 1)).getQuorumVerifier();
            this.self.processReconfig(newQV, designatedLeader, this.zk.getZxid(), true);
            if (designatedLeader != this.self.getMyId()) {
               LOG.info(String.format("Committing a reconfiguration (reconfigEnabled=%s); this leader is not the designated leader anymore, setting allowedToCommit=false", this.self.isReconfigEnabled()));
               this.allowedToCommit = false;
            }

            this.commitAndActivate(zxid, designatedLeader);
            this.informAndActivate(p, designatedLeader);
         } else {
            p.request.logLatency(ServerMetrics.getMetrics().QUORUM_ACK_LATENCY);
            this.commit(zxid);
            this.inform(p);
         }

         this.zk.commitProcessor.commit(p.request);
         if (this.pendingSyncs.containsKey(zxid)) {
            for(LearnerSyncRequest r : (List)this.pendingSyncs.remove(zxid)) {
               this.sendSync(r);
            }
         }

         return true;
      }
   }

   public synchronized void processAck(long sid, long zxid, SocketAddress followerAddr) {
      if (this.allowedToCommit) {
         if (LOG.isTraceEnabled()) {
            LOG.trace("Ack zxid: 0x{}", Long.toHexString(zxid));

            for(Proposal p : this.outstandingProposals.values()) {
               long packetZxid = p.packet.getZxid();
               LOG.trace("outstanding proposal: 0x{}", Long.toHexString(packetZxid));
            }

            LOG.trace("outstanding proposals all");
         }

         if ((zxid & 4294967295L) != 0L) {
            if (this.outstandingProposals.size() == 0) {
               LOG.debug("outstanding is 0");
            } else if (this.lastCommitted >= zxid) {
               LOG.debug("proposal has already been committed, pzxid: 0x{} zxid: 0x{}", Long.toHexString(this.lastCommitted), Long.toHexString(zxid));
            } else {
               Proposal p = (Proposal)this.outstandingProposals.get(zxid);
               if (p == null) {
                  LOG.warn("Trying to commit future proposal: zxid 0x{} from {}", Long.toHexString(zxid), followerAddr);
               } else {
                  if (ackLoggingFrequency > 0 && zxid % (long)ackLoggingFrequency == 0L) {
                     p.request.logLatency(ServerMetrics.getMetrics().ACK_LATENCY, Long.toString(sid));
                  }

                  p.addAck(sid);
                  boolean hasCommitted = this.tryToCommit(p, zxid, followerAddr);
                  if (hasCommitted && p.request != null && p.request.getHdr().getType() == 16) {
                     long curZxid = zxid;

                     while(this.allowedToCommit && hasCommitted && p != null) {
                        ++curZxid;
                        p = (Proposal)this.outstandingProposals.get(curZxid);
                        if (p != null) {
                           hasCommitted = this.tryToCommit(p, curZxid, (SocketAddress)null);
                        }
                     }
                  }

               }
            }
         }
      }
   }

   void sendPacket(QuorumPacket qp) {
      synchronized(this.forwardingFollowers) {
         for(LearnerHandler f : this.forwardingFollowers) {
            f.queuePacket(qp);
         }

      }
   }

   void sendObserverPacket(QuorumPacket qp) {
      for(LearnerHandler f : this.getObservingLearners()) {
         f.queuePacket(qp);
      }

   }

   public void commit(long zxid) {
      synchronized(this) {
         this.lastCommitted = zxid;
      }

      QuorumPacket qp = new QuorumPacket(4, zxid, (byte[])null, (List)null);
      this.sendPacket(qp);
      ServerMetrics.getMetrics().COMMIT_COUNT.add(1L);
   }

   public void commitAndActivate(long zxid, long designatedLeader) {
      synchronized(this) {
         this.lastCommitted = zxid;
      }

      byte[] data = new byte[8];
      ByteBuffer buffer = ByteBuffer.wrap(data);
      buffer.putLong(designatedLeader);
      QuorumPacket qp = new QuorumPacket(9, zxid, data, (List)null);
      this.sendPacket(qp);
   }

   public void inform(Proposal proposal) {
      QuorumPacket qp = new QuorumPacket(8, proposal.request.zxid, proposal.packet.getData(), (List)null);
      this.sendObserverPacket(qp);
   }

   public static QuorumPacket buildInformAndActivePacket(long zxid, long designatedLeader, byte[] proposalData) {
      byte[] data = new byte[proposalData.length + 8];
      ByteBuffer buffer = ByteBuffer.wrap(data);
      buffer.putLong(designatedLeader);
      buffer.put(proposalData);
      return new QuorumPacket(19, zxid, data, (List)null);
   }

   public void informAndActivate(Proposal proposal, long designatedLeader) {
      this.sendObserverPacket(buildInformAndActivePacket(proposal.request.zxid, designatedLeader, proposal.packet.getData()));
   }

   public synchronized long getLastProposed() {
      return this.lastProposed;
   }

   public long getEpoch() {
      return ZxidUtils.getEpochFromZxid(this.lastProposed);
   }

   public Proposal propose(Request request) throws XidRolloverException {
      if (request.isThrottled()) {
         LOG.error("Throttled request send as proposal: {}. Exiting.", request);
         ServiceUtils.requestSystemExit(ExitCode.UNEXPECTED_ERROR.getValue());
      }

      if ((request.zxid & 4294967295L) == 4294967295L) {
         String msg = "zxid lower 32 bits have rolled over, forcing re-election, and therefore new epoch start";
         this.shutdown(msg);
         throw new XidRolloverException(msg);
      } else {
         byte[] data = request.getSerializeData();
         this.proposalStats.setLastBufferSize(data.length);
         QuorumPacket pp = new QuorumPacket(2, request.zxid, data, (List)null);
         Proposal p = new Proposal(request, pp);
         synchronized(this) {
            p.addQuorumVerifier(this.self.getQuorumVerifier());
            if (request.getHdr().getType() == 16) {
               this.self.setLastSeenQuorumVerifier(request.qv, true);
            }

            if (this.self.getQuorumVerifier().getVersion() < this.self.getLastSeenQuorumVerifier().getVersion()) {
               p.addQuorumVerifier(this.self.getLastSeenQuorumVerifier());
            }

            LOG.debug("Proposing:: {}", request);
            this.lastProposed = p.packet.getZxid();
            this.outstandingProposals.put(this.lastProposed, p);
            this.sendPacket(pp);
         }

         ServerMetrics.getMetrics().PROPOSAL_COUNT.add(1L);
         return p;
      }
   }

   public synchronized void processSync(LearnerSyncRequest r) {
      if (this.outstandingProposals.isEmpty()) {
         this.sendSync(r);
      } else {
         ((List)this.pendingSyncs.computeIfAbsent(this.lastProposed, (k) -> new ArrayList())).add(r);
      }

   }

   public void sendSync(LearnerSyncRequest r) {
      QuorumPacket qp = new QuorumPacket(7, 0L, (byte[])null, (List)null);
      r.fh.queuePacket(qp);
   }

   public synchronized long startForwarding(LearnerHandler handler, long lastSeenZxid) {
      if (this.lastProposed > lastSeenZxid) {
         for(Proposal p : this.toBeApplied) {
            if (p.packet.getZxid() > lastSeenZxid) {
               handler.queuePacket(p.packet);
               QuorumPacket qp = new QuorumPacket(4, p.packet.getZxid(), (byte[])null, (List)null);
               handler.queuePacket(qp);
            }
         }

         if (handler.getLearnerType() == QuorumPeer.LearnerType.PARTICIPANT) {
            List<Long> zxids = new ArrayList(this.outstandingProposals.keySet());
            Collections.sort(zxids);

            for(Long zxid : zxids) {
               if (zxid > lastSeenZxid) {
                  handler.queuePacket(((Proposal)this.outstandingProposals.get(zxid)).packet);
               }
            }
         }
      }

      if (handler.getLearnerType() == QuorumPeer.LearnerType.PARTICIPANT) {
         this.addForwardingFollower(handler);
      } else {
         this.addObserverLearnerHandler(handler);
      }

      return this.lastProposed;
   }

   public void waitForStartup() throws InterruptedException {
      synchronized(this.zk) {
         while(!this.zk.isRunning() && !Thread.currentThread().isInterrupted()) {
            this.zk.wait(20L);
         }

      }
   }

   public static void setMaxTimeToWaitForEpoch(int maxTimeToWaitForEpoch) {
      Leader.maxTimeToWaitForEpoch = maxTimeToWaitForEpoch;
      LOG.info("Set {} to {}ms", "zookeeper.leader.maxTimeToWaitForEpoch", Leader.maxTimeToWaitForEpoch);
   }

   private void quitLeading() {
      synchronized(this.connectingFollowers) {
         this.quitWaitForEpoch = true;
         this.connectingFollowers.notifyAll();
      }

      ServerMetrics.getMetrics().QUIT_LEADING_DUE_TO_DISLOYAL_VOTER.add(1L);
      LOG.info("Quit leading due to voter changed mind.");
   }

   public void setLeadingVoteSet(SyncedLearnerTracker voteSet) {
      this.voteSet = voteSet;
   }

   public void reportLookingSid(long sid) {
      if (maxTimeToWaitForEpoch >= 0 && this.timeStartWaitForEpoch >= 0L && this.waitingForNewEpoch) {
         if (this.voteSet != null && this.voteSet.hasSid(sid)) {
            if (Time.currentElapsedTime() - this.timeStartWaitForEpoch > (long)maxTimeToWaitForEpoch) {
               this.quitLeading();
            }

         }
      }
   }

   public long getEpochToPropose(long sid, long lastAcceptedEpoch) throws InterruptedException, IOException {
      synchronized(this.connectingFollowers) {
         if (!this.waitingForNewEpoch) {
            return this.epoch;
         } else {
            if (lastAcceptedEpoch >= this.epoch) {
               this.epoch = lastAcceptedEpoch + 1L;
            }

            if (this.isParticipant(sid)) {
               this.connectingFollowers.add(sid);
            }

            QuorumVerifier verifier = this.self.getQuorumVerifier();
            if (this.connectingFollowers.contains(this.self.getMyId()) && verifier.containsQuorum(this.connectingFollowers)) {
               this.waitingForNewEpoch = false;
               this.self.setAcceptedEpoch(this.epoch);
               this.connectingFollowers.notifyAll();
            } else {
               long start = Time.currentElapsedTime();
               if (sid == this.self.getMyId()) {
                  this.timeStartWaitForEpoch = start;
               }

               long cur = start;

               for(long end = start + (long)(this.self.getInitLimit() * this.self.getTickTime()); this.waitingForNewEpoch && cur < end && !this.quitWaitForEpoch; cur = Time.currentElapsedTime()) {
                  this.connectingFollowers.wait(end - cur);
               }

               if (this.waitingForNewEpoch) {
                  throw new InterruptedException("Timeout while waiting for epoch from quorum");
               }
            }

            return this.epoch;
         }
      }
   }

   public ZKDatabase getZKDatabase() {
      return this.zk.getZKDatabase();
   }

   public void waitForEpochAck(long id, StateSummary ss) throws IOException, InterruptedException {
      synchronized(this.electingFollowers) {
         if (!this.electionFinished) {
            if (ss.getCurrentEpoch() != -1L) {
               if (ss.isMoreRecentThan(this.leaderStateSummary)) {
                  throw new IOException("Follower is ahead of the leader, leader summary: " + this.leaderStateSummary.getCurrentEpoch() + " (current epoch), " + this.leaderStateSummary.getLastZxid() + " (last zxid)");
               }

               if (ss.getLastZxid() != -1L && this.isParticipant(id)) {
                  this.electingFollowers.add(id);
               }
            }

            QuorumVerifier verifier = this.self.getQuorumVerifier();
            if (this.electingFollowers.contains(this.self.getMyId()) && verifier.containsQuorum(this.electingFollowers)) {
               this.electionFinished = true;
               this.electingFollowers.notifyAll();
            } else {
               long start = Time.currentElapsedTime();
               long cur = start;

               for(long end = start + (long)(this.self.getInitLimit() * this.self.getTickTime()); !this.electionFinished && cur < end; cur = Time.currentElapsedTime()) {
                  this.electingFollowers.wait(end - cur);
               }

               if (!this.electionFinished) {
                  throw new InterruptedException("Timeout while waiting for epoch to be acked by quorum");
               }
            }

         }
      }
   }

   private String getSidSetString(Set sidSet) {
      StringBuilder sids = new StringBuilder();
      Iterator<Long> iter = sidSet.iterator();

      while(iter.hasNext()) {
         sids.append(iter.next());
         if (!iter.hasNext()) {
            break;
         }

         sids.append(",");
      }

      return sids.toString();
   }

   private synchronized void startZkServer() {
      this.lastCommitted = this.zk.getZxid();
      LOG.info("Have quorum of supporters, sids: [{}]; starting up and setting last processed zxid: 0x{}", this.newLeaderProposal.ackSetsToString(), Long.toHexString(this.zk.getZxid()));
      if (this.self.isReconfigEnabled()) {
         QuorumVerifier newQV = this.self.getLastSeenQuorumVerifier();
         Long designatedLeader = this.getDesignatedLeader(this.newLeaderProposal, this.zk.getZxid());
         this.self.processReconfig(newQV, designatedLeader, this.zk.getZxid(), true);
         if (designatedLeader != this.self.getMyId()) {
            LOG.warn("This leader is not the designated leader, it will be initialized with allowedToCommit = false");
            this.allowedToCommit = false;
         }
      } else {
         LOG.info("Dynamic reconfig feature is disabled, skip designatedLeader calculation and reconfig processing.");
      }

      this.leaderStartTime = Time.currentElapsedTime();
      this.zk.startup();
      this.self.updateElectionVote(this.getEpoch());
      this.zk.getZKDatabase().setlastProcessedZxid(this.zk.getZxid());
   }

   public void waitForNewLeaderAck(long sid, long zxid) throws InterruptedException {
      synchronized(this.newLeaderProposal.qvAcksetPairs) {
         if (!this.quorumFormed) {
            long currentZxid = this.newLeaderProposal.packet.getZxid();
            if (zxid != currentZxid) {
               LOG.error("NEWLEADER ACK from sid: {} is from a different epoch - current 0x{} received 0x{}", new Object[]{sid, Long.toHexString(currentZxid), Long.toHexString(zxid)});
            } else {
               this.newLeaderProposal.addAck(sid);
               if (this.newLeaderProposal.hasAllQuorums()) {
                  this.quorumFormed = true;
                  this.newLeaderProposal.qvAcksetPairs.notifyAll();
               } else {
                  long start = Time.currentElapsedTime();
                  long cur = start;

                  for(long end = start + (long)(this.self.getInitLimit() * this.self.getTickTime()); !this.quorumFormed && cur < end; cur = Time.currentElapsedTime()) {
                     this.newLeaderProposal.qvAcksetPairs.wait(end - cur);
                  }

                  if (!this.quorumFormed) {
                     throw new InterruptedException("Timeout while waiting for NEWLEADER to be acked by quorum");
                  }
               }

            }
         }
      }
   }

   public static String getPacketType(int packetType) {
      switch (packetType) {
         case 1:
            return "REQUEST";
         case 2:
            return "PROPOSAL";
         case 3:
            return "ACK";
         case 4:
            return "COMMIT";
         case 5:
            return "PING";
         case 6:
            return "REVALIDATE";
         case 7:
            return "SYNC";
         case 8:
            return "INFORM";
         case 9:
            return "COMMITANDACTIVATE";
         case 10:
            return "NEWLEADER";
         case 11:
            return "FOLLOWERINFO";
         case 12:
            return "UPTODATE";
         case 13:
            return "DIFF";
         case 14:
            return "TRUNC";
         case 15:
            return "SNAP";
         case 16:
            return "OBSERVERINFO";
         case 17:
            return "LEADERINFO";
         case 18:
            return "ACKEPOCH";
         case 19:
            return "INFORMANDACTIVATE";
         default:
            return "UNKNOWN";
      }
   }

   private boolean isRunning() {
      return this.self.isRunning() && this.zk.isRunning();
   }

   private boolean isParticipant(long sid) {
      return this.self.getQuorumVerifier().getVotingMembers().containsKey(sid);
   }

   public int getCurrentTick() {
      return this.self.tick.get();
   }

   public int syncTimeout() {
      return this.self.tickTime * this.self.syncLimit;
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

   public void touch(long sess, int to) {
      this.zk.touch(sess, to);
   }

   public void submitLearnerRequest(Request si) {
      this.zk.submitLearnerRequest(si);
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

   public void revalidateSession(QuorumPacket qp, LearnerHandler learnerHandler) throws IOException {
      ByteArrayInputStream bis = new ByteArrayInputStream(qp.getData());
      DataInputStream dis = new DataInputStream(bis);
      long id = dis.readLong();
      int to = dis.readInt();
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      DataOutputStream dos = new DataOutputStream(bos);
      dos.writeLong(id);
      boolean valid = this.zk.checkIfValidGlobalSession(id, to);
      if (valid) {
         try {
            this.zk.setOwner(id, learnerHandler);
         } catch (KeeperException.SessionExpiredException e) {
            LOG.error("Somehow session 0x{} expired right after being renewed! (impossible)", Long.toHexString(id), e);
         }
      }

      if (LOG.isTraceEnabled()) {
         ZooTrace.logTraceMessage(LOG, 32L, "Session 0x" + Long.toHexString(id) + " is valid: " + valid);
      }

      dos.writeBoolean(valid);
      qp.setData(bos.toByteArray());
      learnerHandler.queuePacket(qp);
   }

   public void registerLearnerHandlerBean(LearnerHandler learnerHandler, Socket socket) {
      LearnerHandlerBean bean = new LearnerHandlerBean(learnerHandler, socket);
      if (this.zk.registerJMX(bean)) {
         this.connectionBeans.put(learnerHandler, bean);
      }

   }

   public void unregisterLearnerHandlerBean(LearnerHandler learnerHandler) {
      LearnerHandlerBean bean = (LearnerHandlerBean)this.connectionBeans.remove(learnerHandler);
      if (bean != null) {
         MBeanRegistry.getInstance().unregister(bean);
      }

   }

   static {
      LOG.info("TCP NoDelay set to: {}", nodelay);
      ackLoggingFrequency = Integer.getInteger("zookeeper.leader.ackLoggingFrequency", 1000);
      LOG.info("{} = {}", "zookeeper.leader.ackLoggingFrequency", ackLoggingFrequency);
      maxTimeToWaitForEpoch = Integer.getInteger("zookeeper.leader.maxTimeToWaitForEpoch", -1);
      LOG.info("{} = {}ms", "zookeeper.leader.maxTimeToWaitForEpoch", maxTimeToWaitForEpoch);
   }

   public static class Proposal extends SyncedLearnerTracker {
      private QuorumPacket packet;
      protected Request request;

      public Proposal() {
      }

      public Proposal(QuorumPacket packet) {
         this.packet = packet;
      }

      public Proposal(Request request, QuorumPacket packet) {
         this.request = request;
         this.packet = packet;
      }

      public QuorumPacket getQuorumPacket() {
         return this.packet;
      }

      public Request getRequest() {
         return this.request;
      }

      public long getZxid() {
         return this.packet.getZxid();
      }

      public String toString() {
         return this.packet.getType() + ", " + this.packet.getZxid() + ", " + this.request;
      }
   }

   public static class PureRequestProposal extends Proposal {
      public PureRequestProposal(Request request) {
         this.request = request;
      }

      public QuorumPacket getQuorumPacket() {
         byte[] data = this.request.getSerializeData();
         return new QuorumPacket(2, this.request.zxid, data, (List)null);
      }

      public long getZxid() {
         return this.request.zxid;
      }

      public String toString() {
         return this.request.toString();
      }
   }

   class LearnerCnxAcceptor extends ZooKeeperCriticalThread {
      private final AtomicBoolean stop = new AtomicBoolean(false);
      private final AtomicBoolean fail = new AtomicBoolean(false);

      LearnerCnxAcceptor() {
         super("LearnerCnxAcceptor-" + (String)Leader.this.serverSockets.stream().map(ServerSocket::getLocalSocketAddress).map(Objects::toString).collect(Collectors.joining("|")), Leader.this.zk.getZooKeeperServerListener());
      }

      public void run() {
         if (!this.stop.get() && !Leader.this.serverSockets.isEmpty()) {
            ExecutorService executor = Executors.newFixedThreadPool(Leader.this.serverSockets.size());
            CountDownLatch latch = new CountDownLatch(Leader.this.serverSockets.size());
            Leader.this.serverSockets.forEach((serverSocket) -> executor.submit(new LearnerCnxAcceptorHandler(serverSocket, latch)));

            try {
               latch.await();
            } catch (InterruptedException ie) {
               Leader.LOG.error("Interrupted while sleeping in LearnerCnxAcceptor.", ie);
            } finally {
               Leader.this.closeSockets();
               executor.shutdown();

               try {
                  if (!executor.awaitTermination(1L, TimeUnit.SECONDS)) {
                     Leader.LOG.error("not all the LearnerCnxAcceptorHandler terminated properly");
                  }
               } catch (InterruptedException ie) {
                  Leader.LOG.error("Interrupted while terminating LearnerCnxAcceptor.", ie);
               }

            }
         }

      }

      public void halt() {
         this.stop.set(true);
         Leader.this.closeSockets();
      }

      class LearnerCnxAcceptorHandler implements Runnable {
         private ServerSocket serverSocket;
         private CountDownLatch latch;

         LearnerCnxAcceptorHandler(ServerSocket serverSocket, CountDownLatch latch) {
            this.serverSocket = serverSocket;
            this.latch = latch;
         }

         public void run() {
            try {
               Thread.currentThread().setName("LearnerCnxAcceptorHandler-" + this.serverSocket.getLocalSocketAddress());

               while(!LearnerCnxAcceptor.this.stop.get()) {
                  this.acceptConnections();
               }
            } catch (Exception e) {
               Leader.LOG.warn("Exception while accepting follower", e);
               if (LearnerCnxAcceptor.this.fail.compareAndSet(false, true)) {
                  LearnerCnxAcceptor.this.handleException(LearnerCnxAcceptor.this.getName(), e);
                  LearnerCnxAcceptor.this.halt();
               }
            } finally {
               this.latch.countDown();
            }

         }

         private void acceptConnections() throws IOException {
            Socket socket = null;
            boolean error = false;

            try {
               socket = this.serverSocket.accept();
               socket.setSoTimeout(Leader.this.self.tickTime * Leader.this.self.initLimit);
               socket.setTcpNoDelay(Leader.nodelay);
               BufferedInputStream is = new BufferedInputStream(socket.getInputStream());
               LearnerHandler fh = new LearnerHandler(socket, is, Leader.this);
               fh.start();
            } catch (SocketException e) {
               error = true;
               if (!LearnerCnxAcceptor.this.stop.get()) {
                  throw e;
               }

               Leader.LOG.warn("Exception while shutting down acceptor.", e);
            } catch (SaslException e) {
               Leader.LOG.error("Exception while connecting to quorum learner", e);
               error = true;
            } catch (Exception e) {
               error = true;
               throw e;
            } finally {
               if (error && socket != null && !socket.isClosed()) {
                  try {
                     socket.close();
                  } catch (IOException e) {
                     Leader.LOG.warn("Error closing socket: " + socket, e);
                  }
               }

            }

         }
      }
   }

   static class ToBeAppliedRequestProcessor implements RequestProcessor {
      private final RequestProcessor next;
      private final Leader leader;

      ToBeAppliedRequestProcessor(RequestProcessor next, Leader leader) {
         if (!(next instanceof FinalRequestProcessor)) {
            throw new RuntimeException(ToBeAppliedRequestProcessor.class.getName() + " must be connected to " + FinalRequestProcessor.class.getName() + " not " + next.getClass().getName());
         } else {
            this.leader = leader;
            this.next = next;
         }
      }

      public void processRequest(Request request) throws RequestProcessor.RequestProcessorException {
         this.next.processRequest(request);
         if (request.getHdr() != null) {
            long zxid = request.getHdr().getZxid();
            Iterator<Proposal> iter = this.leader.toBeApplied.iterator();
            if (iter.hasNext()) {
               Proposal p = (Proposal)iter.next();
               if (p.request != null && p.request.zxid == zxid) {
                  iter.remove();
                  return;
               }
            }

            Leader.LOG.error("Committed request not found on toBeApplied: {}", request);
         }

      }

      public void shutdown() {
         Leader.LOG.info("Shutting down");
         this.next.shutdown();
      }
   }

   public static class XidRolloverException extends Exception {
      public XidRolloverException(String message) {
         super(message);
      }
   }
}
