package org.apache.zookeeper.server.quorum;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.zookeeper.common.Time;
import org.apache.zookeeper.jmx.MBeanRegistry;
import org.apache.zookeeper.server.ZooKeeperThread;
import org.apache.zookeeper.server.quorum.flexible.QuorumOracleMaj;
import org.apache.zookeeper.server.quorum.flexible.QuorumVerifier;
import org.apache.zookeeper.server.util.ZxidUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FastLeaderElection implements Election {
   private static final Logger LOG = LoggerFactory.getLogger(FastLeaderElection.class);
   static final int finalizeWait = 200;
   private static int maxNotificationInterval = 60000;
   private static int minNotificationInterval = 200;
   public static final String MIN_NOTIFICATION_INTERVAL = "zookeeper.fastleader.minNotificationInterval";
   public static final String MAX_NOTIFICATION_INTERVAL = "zookeeper.fastleader.maxNotificationInterval";
   QuorumCnxManager manager;
   private SyncedLearnerTracker leadingVoteSet;
   static byte[] dummyData;
   LinkedBlockingQueue sendqueue;
   LinkedBlockingQueue recvqueue;
   QuorumPeer self;
   Messenger messenger;
   AtomicLong logicalclock = new AtomicLong();
   long proposedLeader;
   long proposedZxid;
   long proposedEpoch;
   volatile boolean stop = false;

   public long getLogicalClock() {
      return this.logicalclock.get();
   }

   static ByteBuffer buildMsg(int state, long leader, long zxid, long electionEpoch, long epoch) {
      byte[] requestBytes = new byte[40];
      ByteBuffer requestBuffer = ByteBuffer.wrap(requestBytes);
      requestBuffer.clear();
      requestBuffer.putInt(state);
      requestBuffer.putLong(leader);
      requestBuffer.putLong(zxid);
      requestBuffer.putLong(electionEpoch);
      requestBuffer.putLong(epoch);
      requestBuffer.putInt(1);
      return requestBuffer;
   }

   static ByteBuffer buildMsg(int state, long leader, long zxid, long electionEpoch, long epoch, byte[] configData) {
      byte[] requestBytes = new byte[44 + configData.length];
      ByteBuffer requestBuffer = ByteBuffer.wrap(requestBytes);
      requestBuffer.clear();
      requestBuffer.putInt(state);
      requestBuffer.putLong(leader);
      requestBuffer.putLong(zxid);
      requestBuffer.putLong(electionEpoch);
      requestBuffer.putLong(epoch);
      requestBuffer.putInt(2);
      requestBuffer.putInt(configData.length);
      requestBuffer.put(configData);
      return requestBuffer;
   }

   public FastLeaderElection(QuorumPeer self, QuorumCnxManager manager) {
      this.manager = manager;
      this.starter(self, manager);
   }

   private void starter(QuorumPeer self, QuorumCnxManager manager) {
      this.self = self;
      this.proposedLeader = -1L;
      this.proposedZxid = -1L;
      this.sendqueue = new LinkedBlockingQueue();
      this.recvqueue = new LinkedBlockingQueue();
      this.messenger = new Messenger(manager);
   }

   public void start() {
      this.messenger.start();
   }

   private void leaveInstance(Vote v) {
      LOG.debug("About to leave FLE instance: leader={}, zxid=0x{}, my id={}, my state={}", new Object[]{v.getId(), Long.toHexString(v.getZxid()), this.self.getMyId(), this.self.getPeerState()});
      this.recvqueue.clear();
   }

   public QuorumCnxManager getCnxManager() {
      return this.manager;
   }

   public void shutdown() {
      this.stop = true;
      this.proposedLeader = -1L;
      this.proposedZxid = -1L;
      this.leadingVoteSet = null;
      LOG.debug("Shutting down connection manager");
      this.manager.halt();
      LOG.debug("Shutting down messenger");
      this.messenger.halt();
      LOG.debug("FLE is down");
   }

   private void sendNotifications() {
      for(long sid : this.self.getCurrentAndNextConfigVoters()) {
         QuorumVerifier qv = this.self.getQuorumVerifier();
         ToSend notmsg = new ToSend(FastLeaderElection.ToSend.mType.notification, this.proposedLeader, this.proposedZxid, this.logicalclock.get(), QuorumPeer.ServerState.LOOKING, sid, this.proposedEpoch, qv.toString().getBytes(StandardCharsets.UTF_8));
         LOG.debug("Sending Notification: {} (n.leader), 0x{} (n.zxid), 0x{} (n.peerEpoch), 0x{} (n.round), {} (recipient), {} (myid) ", new Object[]{this.proposedLeader, Long.toHexString(this.proposedZxid), Long.toHexString(this.proposedEpoch), Long.toHexString(this.logicalclock.get()), sid, this.self.getMyId()});
         this.sendqueue.offer(notmsg);
      }

   }

   protected boolean totalOrderPredicate(long newId, long newZxid, long newEpoch, long curId, long curZxid, long curEpoch) {
      LOG.debug("id: {}, proposed id: {}, zxid: 0x{}, proposed zxid: 0x{}, epoch: 0x{}, proposed epoch: 0x{}", new Object[]{newId, curId, Long.toHexString(newZxid), Long.toHexString(curZxid), Long.toHexString(newEpoch), Long.toHexString(curEpoch)});
      if (this.self.getQuorumVerifier().getWeight(newId) == 0L) {
         return false;
      } else {
         return newEpoch > curEpoch || newEpoch == curEpoch && (newZxid > curZxid || newZxid == curZxid && newId > curId);
      }
   }

   protected SyncedLearnerTracker getVoteTracker(Map votes, Vote vote) {
      SyncedLearnerTracker voteSet = new SyncedLearnerTracker();
      voteSet.addQuorumVerifier(this.self.getQuorumVerifier());
      if (this.self.getLastSeenQuorumVerifier() != null && this.self.getLastSeenQuorumVerifier().getVersion() > this.self.getQuorumVerifier().getVersion()) {
         voteSet.addQuorumVerifier(this.self.getLastSeenQuorumVerifier());
      }

      for(Map.Entry entry : votes.entrySet()) {
         if (vote.equals(entry.getValue())) {
            voteSet.addAck((Long)entry.getKey());
         }
      }

      return voteSet;
   }

   protected boolean checkLeader(Map votes, long leader, long electionEpoch) {
      boolean predicate = true;
      if (leader != this.self.getMyId()) {
         if (votes.get(leader) == null) {
            predicate = false;
         } else if (((Vote)votes.get(leader)).getState() != QuorumPeer.ServerState.LEADING) {
            predicate = false;
         }
      } else if (this.logicalclock.get() != electionEpoch) {
         predicate = false;
      }

      return predicate;
   }

   synchronized void updateProposal(long leader, long zxid, long epoch) {
      LOG.debug("Updating proposal: {} (newleader), 0x{} (newzxid), {} (oldleader), 0x{} (oldzxid)", new Object[]{leader, Long.toHexString(zxid), this.proposedLeader, Long.toHexString(this.proposedZxid)});
      this.proposedLeader = leader;
      this.proposedZxid = zxid;
      this.proposedEpoch = epoch;
   }

   public synchronized Vote getVote() {
      return new Vote(this.proposedLeader, this.proposedZxid, this.proposedEpoch);
   }

   private QuorumPeer.ServerState learningState() {
      if (this.self.getLearnerType() == QuorumPeer.LearnerType.PARTICIPANT) {
         LOG.debug("I am a participant: {}", this.self.getMyId());
         return QuorumPeer.ServerState.FOLLOWING;
      } else {
         LOG.debug("I am an observer: {}", this.self.getMyId());
         return QuorumPeer.ServerState.OBSERVING;
      }
   }

   private long getInitId() {
      return this.self.getQuorumVerifier().getVotingMembers().containsKey(this.self.getMyId()) ? this.self.getMyId() : Long.MIN_VALUE;
   }

   private long getInitLastLoggedZxid() {
      return this.self.getLearnerType() == QuorumPeer.LearnerType.PARTICIPANT ? this.self.getLastLoggedZxid() : Long.MIN_VALUE;
   }

   private long getPeerEpoch() {
      if (this.self.getLearnerType() == QuorumPeer.LearnerType.PARTICIPANT) {
         try {
            return this.self.getCurrentEpoch();
         } catch (IOException e) {
            RuntimeException re = new RuntimeException(e.getMessage());
            re.setStackTrace(e.getStackTrace());
            throw re;
         }
      } else {
         return Long.MIN_VALUE;
      }
   }

   private void setPeerState(long proposedLeader, SyncedLearnerTracker voteSet) {
      QuorumPeer.ServerState ss = proposedLeader == this.self.getMyId() ? QuorumPeer.ServerState.LEADING : this.learningState();
      this.self.setPeerState(ss);
      if (ss == QuorumPeer.ServerState.LEADING) {
         this.leadingVoteSet = voteSet;
      }

   }

   public Vote lookForLeader() throws InterruptedException {
      try {
         this.self.jmxLeaderElectionBean = new LeaderElectionBean();
         MBeanRegistry.getInstance().register(this.self.jmxLeaderElectionBean, this.self.jmxLocalPeerBean);
      } catch (Exception e) {
         LOG.warn("Failed to register with JMX", e);
         this.self.jmxLeaderElectionBean = null;
      }

      this.self.start_fle = Time.currentElapsedTime();

      try {
         Map<Long, Vote> recvset = new HashMap();
         Map<Long, Vote> outofelection = new HashMap();
         int notTimeout = minNotificationInterval;
         synchronized(this) {
            this.logicalclock.incrementAndGet();
            this.updateProposal(this.getInitId(), this.getInitLastLoggedZxid(), this.getPeerEpoch());
         }

         LOG.info("New election. My id = {}, proposed zxid=0x{}", this.self.getMyId(), Long.toHexString(this.proposedZxid));
         this.sendNotifications();
         SyncedLearnerTracker voteSet = null;

         while(this.self.getPeerState() == QuorumPeer.ServerState.LOOKING && !this.stop) {
            Notification n = (Notification)this.recvqueue.poll((long)notTimeout, TimeUnit.MILLISECONDS);
            if (n == null) {
               if (this.manager.haveDelivered()) {
                  this.sendNotifications();
               } else {
                  this.manager.connectAll();
               }

               notTimeout = Math.min(notTimeout << 1, maxNotificationInterval);
               if (this.self.getQuorumVerifier() instanceof QuorumOracleMaj && this.self.getQuorumVerifier().revalidateVoteset(voteSet, notTimeout != minNotificationInterval)) {
                  this.setPeerState(this.proposedLeader, voteSet);
                  Vote endVote = new Vote(this.proposedLeader, this.proposedZxid, this.logicalclock.get(), this.proposedEpoch);
                  this.leaveInstance(endVote);
                  Vote var31 = endVote;
                  return var31;
               }

               LOG.info("Notification time out: {} ms", notTimeout);
            } else if (this.validVoter(n.sid) && this.validVoter(n.leader)) {
               switch (n.state) {
                  case LOOKING:
                     if (this.getInitLastLoggedZxid() == -1L) {
                        LOG.debug("Ignoring notification as our zxid is -1");
                     } else if (n.zxid == -1L) {
                        LOG.debug("Ignoring notification from member with -1 zxid {}", n.sid);
                     } else {
                        if (n.electionEpoch > this.logicalclock.get()) {
                           this.logicalclock.set(n.electionEpoch);
                           recvset.clear();
                           if (this.totalOrderPredicate(n.leader, n.zxid, n.peerEpoch, this.getInitId(), this.getInitLastLoggedZxid(), this.getPeerEpoch())) {
                              this.updateProposal(n.leader, n.zxid, n.peerEpoch);
                           } else {
                              this.updateProposal(this.getInitId(), this.getInitLastLoggedZxid(), this.getPeerEpoch());
                           }

                           this.sendNotifications();
                        } else {
                           if (n.electionEpoch < this.logicalclock.get()) {
                              LOG.debug("Notification election epoch is smaller than logicalclock. n.electionEpoch = 0x{}, logicalclock=0x{}", Long.toHexString(n.electionEpoch), Long.toHexString(this.logicalclock.get()));
                              continue;
                           }

                           if (this.totalOrderPredicate(n.leader, n.zxid, n.peerEpoch, this.proposedLeader, this.proposedZxid, this.proposedEpoch)) {
                              this.updateProposal(n.leader, n.zxid, n.peerEpoch);
                              this.sendNotifications();
                           }
                        }

                        LOG.debug("Adding vote: from={}, proposed leader={}, proposed zxid=0x{}, proposed election epoch=0x{}", new Object[]{n.sid, n.leader, Long.toHexString(n.zxid), Long.toHexString(n.electionEpoch)});
                        recvset.put(n.sid, new Vote(n.leader, n.zxid, n.electionEpoch, n.peerEpoch));
                        voteSet = this.getVoteTracker(recvset, new Vote(this.proposedLeader, this.proposedZxid, this.logicalclock.get(), this.proposedEpoch));
                        if (!voteSet.hasAllQuorums()) {
                           continue;
                        }

                        while((n = (Notification)this.recvqueue.poll(200L, TimeUnit.MILLISECONDS)) != null) {
                           if (this.totalOrderPredicate(n.leader, n.zxid, n.peerEpoch, this.proposedLeader, this.proposedZxid, this.proposedEpoch)) {
                              this.recvqueue.put(n);
                              break;
                           }
                        }

                        if (n == null) {
                           this.setPeerState(this.proposedLeader, voteSet);
                           Vote endVote = new Vote(this.proposedLeader, this.proposedZxid, this.logicalclock.get(), this.proposedEpoch);
                           this.leaveInstance(endVote);
                           Vote var30 = endVote;
                           return var30;
                        }
                     }
                     break;
                  case OBSERVING:
                     LOG.debug("Notification from observer: {}", n.sid);
                     break;
                  case FOLLOWING:
                     Vote resultFN = this.receivedFollowingNotification(recvset, outofelection, voteSet, n);
                     if (resultFN != null) {
                        Vote var29 = resultFN;
                        return var29;
                     }
                     break;
                  case LEADING:
                     Vote resultLN = this.receivedLeadingNotification(recvset, outofelection, voteSet, n);
                     if (resultLN != null) {
                        Vote e = resultLN;
                        return e;
                     }
                     break;
                  default:
                     LOG.warn("Notification state unrecognized: {} (n.state), {}(n.sid)", n.state, n.sid);
               }
            } else {
               if (!this.validVoter(n.leader)) {
                  LOG.warn("Ignoring notification for non-cluster member sid {} from sid {}", n.leader, n.sid);
               }

               if (!this.validVoter(n.sid)) {
                  LOG.warn("Ignoring notification for sid {} from non-quorum member sid {}", n.leader, n.sid);
               }
            }
         }

         Object var26 = null;
         return (Vote)var26;
      } finally {
         try {
            if (this.self.jmxLeaderElectionBean != null) {
               MBeanRegistry.getInstance().unregister(this.self.jmxLeaderElectionBean);
            }
         } catch (Exception e) {
            LOG.warn("Failed to unregister with JMX", e);
         }

         this.self.jmxLeaderElectionBean = null;
         LOG.debug("Number of connection processing threads: {}", this.manager.getConnectionThreadCount());
      }
   }

   private Vote receivedFollowingNotification(Map recvset, Map outofelection, SyncedLearnerTracker voteSet, Notification n) {
      if (n.electionEpoch == this.logicalclock.get()) {
         recvset.put(n.sid, new Vote(n.leader, n.zxid, n.electionEpoch, n.peerEpoch, n.state));
         voteSet = this.getVoteTracker(recvset, new Vote(n.version, n.leader, n.zxid, n.electionEpoch, n.peerEpoch, n.state));
         if (voteSet.hasAllQuorums() && this.checkLeader(recvset, n.leader, n.electionEpoch)) {
            this.setPeerState(n.leader, voteSet);
            Vote endVote = new Vote(n.leader, n.zxid, n.electionEpoch, n.peerEpoch);
            this.leaveInstance(endVote);
            return endVote;
         }
      }

      outofelection.put(n.sid, new Vote(n.version, n.leader, n.zxid, n.electionEpoch, n.peerEpoch, n.state));
      voteSet = this.getVoteTracker(outofelection, new Vote(n.version, n.leader, n.zxid, n.electionEpoch, n.peerEpoch, n.state));
      if (voteSet.hasAllQuorums() && this.checkLeader(outofelection, n.leader, n.electionEpoch)) {
         synchronized(this) {
            this.logicalclock.set(n.electionEpoch);
            this.setPeerState(n.leader, voteSet);
         }

         Vote endVote = new Vote(n.leader, n.zxid, n.electionEpoch, n.peerEpoch);
         this.leaveInstance(endVote);
         return endVote;
      } else {
         return null;
      }
   }

   private Vote receivedLeadingNotification(Map recvset, Map outofelection, SyncedLearnerTracker voteSet, Notification n) {
      Vote result = this.receivedFollowingNotification(recvset, outofelection, voteSet, n);
      if (result == null) {
         if (this.self.getQuorumVerifier().getNeedOracle() && !this.self.getQuorumVerifier().askOracle()) {
            LOG.info("Oracle indicates to follow");
            this.setPeerState(n.leader, voteSet);
            Vote endVote = new Vote(n.leader, n.zxid, n.electionEpoch, n.peerEpoch);
            this.leaveInstance(endVote);
            return endVote;
         } else {
            LOG.info("Oracle indicates not to follow");
            return null;
         }
      } else {
         return result;
      }
   }

   private boolean validVoter(long sid) {
      return this.self.getCurrentAndNextConfigVoters().contains(sid);
   }

   static {
      minNotificationInterval = Integer.getInteger("zookeeper.fastleader.minNotificationInterval", minNotificationInterval);
      LOG.info("{} = {} ms", "zookeeper.fastleader.minNotificationInterval", minNotificationInterval);
      maxNotificationInterval = Integer.getInteger("zookeeper.fastleader.maxNotificationInterval", maxNotificationInterval);
      LOG.info("{} = {} ms", "zookeeper.fastleader.maxNotificationInterval", maxNotificationInterval);
      dummyData = new byte[0];
   }

   public static class Notification {
      public static final int CURRENTVERSION = 2;
      int version;
      long leader;
      long zxid;
      long electionEpoch;
      QuorumPeer.ServerState state;
      long sid;
      QuorumVerifier qv;
      long peerEpoch;
   }

   public static class ToSend {
      long leader;
      long zxid;
      long electionEpoch;
      QuorumPeer.ServerState state;
      long sid;
      byte[] configData;
      long peerEpoch;

      ToSend(mType type, long leader, long zxid, long electionEpoch, QuorumPeer.ServerState state, long sid, long peerEpoch, byte[] configData) {
         this.configData = FastLeaderElection.dummyData;
         this.leader = leader;
         this.zxid = zxid;
         this.electionEpoch = electionEpoch;
         this.state = state;
         this.sid = sid;
         this.peerEpoch = peerEpoch;
         this.configData = configData;
      }

      static enum mType {
         crequest,
         challenge,
         notification,
         ack;
      }
   }

   protected class Messenger {
      WorkerSender ws;
      WorkerReceiver wr;
      Thread wsThread = null;
      Thread wrThread = null;

      Messenger(QuorumCnxManager manager) {
         this.ws = new WorkerSender(manager);
         this.wsThread = new Thread(this.ws, "WorkerSender[myid=" + FastLeaderElection.this.self.getMyId() + "]");
         this.wsThread.setDaemon(true);
         this.wr = new WorkerReceiver(manager);
         this.wrThread = new Thread(this.wr, "WorkerReceiver[myid=" + FastLeaderElection.this.self.getMyId() + "]");
         this.wrThread.setDaemon(true);
      }

      void start() {
         this.wsThread.start();
         this.wrThread.start();
      }

      void halt() {
         this.ws.stop = true;
         this.wr.stop = true;
      }

      class WorkerReceiver extends ZooKeeperThread {
         volatile boolean stop = false;
         QuorumCnxManager manager;

         WorkerReceiver(QuorumCnxManager manager) {
            super("WorkerReceiver");
            this.manager = manager;
         }

         public void run() {
            while(!this.stop) {
               try {
                  QuorumCnxManager.Message response = this.manager.pollRecvQueue(3000L, TimeUnit.MILLISECONDS);
                  if (response != null) {
                     int capacity = response.buffer.capacity();
                     if (capacity < 28) {
                        FastLeaderElection.LOG.error("Got a short response from server {}: {}", response.sid, capacity);
                     } else {
                        boolean backCompatibility28 = capacity == 28;
                        boolean backCompatibility40 = capacity == 40;
                        response.buffer.clear();
                        Notification n = new Notification();
                        int rstate = response.buffer.getInt();
                        long rleader = response.buffer.getLong();
                        long rzxid = response.buffer.getLong();
                        long relectionEpoch = response.buffer.getLong();
                        int version = 0;
                        QuorumVerifier rqv = null;

                        long rpeerepoch;
                        try {
                           if (!backCompatibility28) {
                              rpeerepoch = response.buffer.getLong();
                              if (!backCompatibility40) {
                                 version = response.buffer.getInt();
                              } else {
                                 FastLeaderElection.LOG.info("Backward compatibility mode (36 bits), server id: {}", response.sid);
                              }
                           } else {
                              FastLeaderElection.LOG.info("Backward compatibility mode (28 bits), server id: {}", response.sid);
                              rpeerepoch = ZxidUtils.getEpochFromZxid(rzxid);
                           }

                           if (version <= 1) {
                              FastLeaderElection.LOG.info("Backward compatibility mode (before reconfig), server id: {}", response.sid);
                           } else {
                              int configLength = response.buffer.getInt();
                              if (configLength < 0 || configLength > capacity) {
                                 throw new IOException(String.format("Invalid configLength in notification message! sid=%d, capacity=%d, version=%d, configLength=%d", response.sid, capacity, version, configLength));
                              }

                              byte[] b = new byte[configLength];
                              response.buffer.get(b);
                              synchronized(FastLeaderElection.this.self) {
                                 try {
                                    rqv = FastLeaderElection.this.self.configFromString(new String(b, StandardCharsets.UTF_8));
                                    QuorumVerifier curQV = FastLeaderElection.this.self.getQuorumVerifier();
                                    if (rqv.getVersion() > curQV.getVersion()) {
                                       FastLeaderElection.LOG.info("{} Received version: {} my version: {}", new Object[]{FastLeaderElection.this.self.getMyId(), Long.toHexString(rqv.getVersion()), Long.toHexString(FastLeaderElection.this.self.getQuorumVerifier().getVersion())});
                                       if (FastLeaderElection.this.self.getPeerState() == QuorumPeer.ServerState.LOOKING) {
                                          FastLeaderElection.LOG.debug("Invoking processReconfig(), state: {}", FastLeaderElection.this.self.getServerState());
                                          FastLeaderElection.this.self.processReconfig(rqv, (Long)null, (Long)null, false);
                                          if (!rqv.equals(curQV)) {
                                             FastLeaderElection.LOG.info("restarting leader election");
                                             FastLeaderElection.this.self.shuttingDownLE = true;
                                             FastLeaderElection.this.self.getElectionAlg().shutdown();
                                             break;
                                          }
                                       } else {
                                          FastLeaderElection.LOG.debug("Skip processReconfig(), state: {}", FastLeaderElection.this.self.getServerState());
                                       }
                                    }
                                 } catch (QuorumPeerConfig.ConfigException | IOException var22) {
                                    FastLeaderElection.LOG.error("Something went wrong while processing config received from {}", response.sid);
                                 }
                              }
                           }
                        } catch (IOException | BufferUnderflowException e) {
                           FastLeaderElection.LOG.warn("Skipping the processing of a partial / malformed response message sent by sid={} (message length: {})", new Object[]{response.sid, capacity, e});
                           continue;
                        }

                        if (!FastLeaderElection.this.validVoter(response.sid)) {
                           Vote current = FastLeaderElection.this.self.getCurrentVote();
                           QuorumVerifier qv = FastLeaderElection.this.self.getQuorumVerifier();
                           ToSend notmsg = new ToSend(FastLeaderElection.ToSend.mType.notification, current.getId(), current.getZxid(), FastLeaderElection.this.logicalclock.get(), FastLeaderElection.this.self.getPeerState(), response.sid, current.getPeerEpoch(), qv.toString().getBytes(StandardCharsets.UTF_8));
                           FastLeaderElection.this.sendqueue.offer(notmsg);
                        } else {
                           FastLeaderElection.LOG.debug("Receive new notification message. My id = {}", FastLeaderElection.this.self.getMyId());
                           QuorumPeer.ServerState ackstate = QuorumPeer.ServerState.LOOKING;
                           switch (rstate) {
                              case 0:
                                 ackstate = QuorumPeer.ServerState.LOOKING;
                                 break;
                              case 1:
                                 ackstate = QuorumPeer.ServerState.FOLLOWING;
                                 break;
                              case 2:
                                 ackstate = QuorumPeer.ServerState.LEADING;
                                 break;
                              case 3:
                                 ackstate = QuorumPeer.ServerState.OBSERVING;
                                 break;
                              default:
                                 continue;
                           }

                           n.leader = rleader;
                           n.zxid = rzxid;
                           n.electionEpoch = relectionEpoch;
                           n.state = ackstate;
                           n.sid = response.sid;
                           n.peerEpoch = rpeerepoch;
                           n.version = version;
                           n.qv = rqv;
                           FastLeaderElection.LOG.info("Notification: my state:{}; n.sid:{}, n.state:{}, n.leader:{}, n.round:0x{}, n.peerEpoch:0x{}, n.zxid:0x{}, message format version:0x{}, n.config version:0x{}", new Object[]{FastLeaderElection.this.self.getPeerState(), n.sid, n.state, n.leader, Long.toHexString(n.electionEpoch), Long.toHexString(n.peerEpoch), Long.toHexString(n.zxid), Long.toHexString((long)n.version), n.qv != null ? Long.toHexString(n.qv.getVersion()) : "0"});
                           if (FastLeaderElection.this.self.getPeerState() == QuorumPeer.ServerState.LOOKING) {
                              FastLeaderElection.this.recvqueue.offer(n);
                              if (ackstate == QuorumPeer.ServerState.LOOKING && n.electionEpoch < FastLeaderElection.this.logicalclock.get()) {
                                 Vote v = FastLeaderElection.this.getVote();
                                 QuorumVerifier qv = FastLeaderElection.this.self.getQuorumVerifier();
                                 ToSend notmsg = new ToSend(FastLeaderElection.ToSend.mType.notification, v.getId(), v.getZxid(), FastLeaderElection.this.logicalclock.get(), FastLeaderElection.this.self.getPeerState(), response.sid, v.getPeerEpoch(), qv.toString().getBytes());
                                 FastLeaderElection.this.sendqueue.offer(notmsg);
                              }
                           } else {
                              Vote current = FastLeaderElection.this.self.getCurrentVote();
                              if (ackstate == QuorumPeer.ServerState.LOOKING) {
                                 if (FastLeaderElection.this.self.leader != null) {
                                    if (FastLeaderElection.this.leadingVoteSet != null) {
                                       FastLeaderElection.this.self.leader.setLeadingVoteSet(FastLeaderElection.this.leadingVoteSet);
                                       FastLeaderElection.this.leadingVoteSet = null;
                                    }

                                    FastLeaderElection.this.self.leader.reportLookingSid(response.sid);
                                 }

                                 FastLeaderElection.LOG.debug("Sending new notification. My id ={} recipient={} zxid=0x{} leader={} config version = {}", new Object[]{FastLeaderElection.this.self.getMyId(), response.sid, Long.toHexString(current.getZxid()), current.getId(), Long.toHexString(FastLeaderElection.this.self.getQuorumVerifier().getVersion())});
                                 QuorumVerifier qv = FastLeaderElection.this.self.getQuorumVerifier();
                                 ToSend notmsg = new ToSend(FastLeaderElection.ToSend.mType.notification, current.getId(), current.getZxid(), current.getElectionEpoch(), FastLeaderElection.this.self.getPeerState(), response.sid, current.getPeerEpoch(), qv.toString().getBytes());
                                 FastLeaderElection.this.sendqueue.offer(notmsg);
                              }
                           }
                        }
                     }
                  }
               } catch (InterruptedException e) {
                  FastLeaderElection.LOG.warn("Interrupted Exception while waiting for new message", e);
               }
            }

            FastLeaderElection.LOG.info("WorkerReceiver is down");
         }
      }

      class WorkerSender extends ZooKeeperThread {
         volatile boolean stop = false;
         QuorumCnxManager manager;

         WorkerSender(QuorumCnxManager manager) {
            super("WorkerSender");
            this.manager = manager;
         }

         public void run() {
            while(true) {
               if (!this.stop) {
                  try {
                     ToSend m = (ToSend)FastLeaderElection.this.sendqueue.poll(3000L, TimeUnit.MILLISECONDS);
                     if (m != null) {
                        this.process(m);
                     }
                     continue;
                  } catch (InterruptedException var2) {
                  }
               }

               FastLeaderElection.LOG.info("WorkerSender is down");
               return;
            }
         }

         void process(ToSend m) {
            ByteBuffer requestBuffer = FastLeaderElection.buildMsg(m.state.ordinal(), m.leader, m.zxid, m.electionEpoch, m.peerEpoch, m.configData);
            this.manager.toSend(m.sid, requestBuffer);
         }
      }
   }
}
