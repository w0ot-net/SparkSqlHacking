package org.apache.zookeeper.server.quorum;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.security.sasl.SaslException;
import org.apache.jute.BinaryInputArchive;
import org.apache.jute.BinaryOutputArchive;
import org.apache.zookeeper.common.Time;
import org.apache.zookeeper.server.Request;
import org.apache.zookeeper.server.RequestRecord;
import org.apache.zookeeper.server.ServerCnxn;
import org.apache.zookeeper.server.ServerMetrics;
import org.apache.zookeeper.server.TxnLogProposalIterator;
import org.apache.zookeeper.server.ZKDatabase;
import org.apache.zookeeper.server.ZooKeeperThread;
import org.apache.zookeeper.server.ZooTrace;
import org.apache.zookeeper.server.quorum.auth.QuorumAuthServer;
import org.apache.zookeeper.server.util.ConfigUtils;
import org.apache.zookeeper.server.util.MessageTracker;
import org.apache.zookeeper.server.util.ZxidUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LearnerHandler extends ZooKeeperThread {
   private static final Logger LOG = LoggerFactory.getLogger(LearnerHandler.class);
   public static final String LEADER_CLOSE_SOCKET_ASYNC = "zookeeper.leader.closeSocketAsync";
   public static final boolean closeSocketAsync = Boolean.parseBoolean(ConfigUtils.getPropertyBackwardCompatibleWay("zookeeper.leader.closeSocketAsync"));
   protected final Socket sock;
   AtomicBoolean sockBeingClosed = new AtomicBoolean(false);
   final LearnerMaster learnerMaster;
   volatile long tickOfNextAckDeadline;
   protected long sid = 0L;
   protected int version = 1;
   final LinkedBlockingQueue queuedPackets = new LinkedBlockingQueue();
   private final AtomicLong queuedPacketsSize = new AtomicLong();
   protected final AtomicLong packetsReceived = new AtomicLong();
   protected final AtomicLong packetsSent = new AtomicLong();
   protected final AtomicLong requestsReceived = new AtomicLong();
   protected volatile long lastZxid = -1L;
   protected final Date established = new Date();
   private final int markerPacketInterval = 1000;
   private AtomicInteger packetCounter = new AtomicInteger();
   private SyncLimitCheck syncLimitCheck = new SyncLimitCheck();
   private BinaryInputArchive ia;
   private BinaryOutputArchive oa;
   private final BufferedInputStream bufferedInput;
   private BufferedOutputStream bufferedOutput;
   protected final MessageTracker messageTracker;
   private volatile boolean sendingThreadStarted = false;
   public static final String FORCE_SNAP_SYNC = "zookeeper.forceSnapshotSync";
   private boolean forceSnapSync = false;
   private boolean needOpPacket = true;
   private long leaderLastZxid;
   private LearnerSyncThrottler syncThrottler = null;
   final QuorumPacket proposalOfDeath = new QuorumPacket();
   private QuorumPeer.LearnerType learnerType;

   public Socket getSocket() {
      return this.sock;
   }

   long getSid() {
      return this.sid;
   }

   String getRemoteAddress() {
      return this.sock == null ? "<null>" : this.sock.getRemoteSocketAddress().toString();
   }

   int getVersion() {
      return this.version;
   }

   public synchronized long getLastZxid() {
      return this.lastZxid;
   }

   public Date getEstablished() {
      return (Date)this.established.clone();
   }

   protected void setOutputArchive(BinaryOutputArchive oa) {
      this.oa = oa;
   }

   protected void setBufferedOutput(BufferedOutputStream bufferedOutput) {
      this.bufferedOutput = bufferedOutput;
   }

   LearnerHandler(Socket sock, BufferedInputStream bufferedInput, LearnerMaster learnerMaster) throws IOException {
      super("LearnerHandler-" + sock.getRemoteSocketAddress());
      this.learnerType = QuorumPeer.LearnerType.PARTICIPANT;
      this.sock = sock;
      this.learnerMaster = learnerMaster;
      this.bufferedInput = bufferedInput;
      if (Boolean.getBoolean("zookeeper.forceSnapshotSync")) {
         this.forceSnapSync = true;
         LOG.info("Forcing snapshot sync is enabled");
      }

      try {
         QuorumAuthServer authServer = learnerMaster.getQuorumAuthServer();
         if (authServer != null) {
            authServer.authenticate(sock, new DataInputStream(bufferedInput));
         }
      } catch (IOException e) {
         LOG.error("Server failed to authenticate quorum learner, addr: {}, closing connection", sock.getRemoteSocketAddress(), e);
         this.closeSocket();
         throw new SaslException("Authentication failure: " + e.getMessage());
      }

      this.messageTracker = new MessageTracker(MessageTracker.BUFFERED_MESSAGE_SIZE);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("LearnerHandler ").append(this.sock);
      sb.append(" tickOfNextAckDeadline:").append(this.tickOfNextAckDeadline());
      sb.append(" synced?:").append(this.synced());
      sb.append(" queuedPacketLength:").append(this.queuedPackets.size());
      return sb.toString();
   }

   public QuorumPeer.LearnerType getLearnerType() {
      return this.learnerType;
   }

   private void sendPackets() throws InterruptedException {
      while(true) {
         try {
            QuorumPacket p = (QuorumPacket)this.queuedPackets.poll();
            if (p == null) {
               this.bufferedOutput.flush();
               p = (QuorumPacket)this.queuedPackets.take();
            }

            ServerMetrics.getMetrics().LEARNER_HANDLER_QP_SIZE.add(Long.toString(this.sid), (long)this.queuedPackets.size());
            if (p instanceof MarkerQuorumPacket) {
               MarkerQuorumPacket m = (MarkerQuorumPacket)p;
               ServerMetrics.getMetrics().LEARNER_HANDLER_QP_TIME.add(Long.toString(this.sid), (System.nanoTime() - m.time) / 1000000L);
               continue;
            }

            this.queuedPacketsSize.addAndGet(-packetSize(p));
            if (p != this.proposalOfDeath) {
               if (p.getType() == 2) {
                  this.syncLimitCheck.updateProposal(p.getZxid(), System.nanoTime());
               }

               if (LOG.isTraceEnabled()) {
                  long traceMask = 16L;
                  if (p.getType() == 5) {
                     traceMask = 128L;
                  }

                  ZooTrace.logQuorumPacket(LOG, traceMask, 'o', p);
               }

               if (p.getZxid() > 0L) {
                  this.lastZxid = p.getZxid();
               }

               this.oa.writeRecord(p, "packet");
               this.packetsSent.incrementAndGet();
               this.messageTracker.trackSent(p.getType());
               continue;
            }
         } catch (IOException e) {
            LOG.error("Exception while sending packets in LearnerHandler", e);
            this.closeSocket();
         }

         return;
      }
   }

   public static String packetToString(QuorumPacket p) {
      String mess = null;
      String type;
      switch (p.getType()) {
         case 1:
            type = "REQUEST";
            break;
         case 2:
            type = "PROPOSAL";
            break;
         case 3:
            type = "ACK";
            break;
         case 4:
            type = "COMMIT";
            break;
         case 5:
            type = "PING";
            break;
         case 6:
            type = "REVALIDATE";
            ByteArrayInputStream bis = new ByteArrayInputStream(p.getData());
            DataInputStream dis = new DataInputStream(bis);

            try {
               long id = dis.readLong();
               mess = " sessionid = " + id;
            } catch (IOException e) {
               LOG.warn("Unexpected exception", e);
            }
            break;
         case 7:
            type = "SYNC";
            break;
         case 8:
            type = "INFORM";
            break;
         case 9:
            type = "COMMITANDACTIVATE";
            break;
         case 10:
            type = "NEWLEADER";
            break;
         case 11:
            type = "FOLLOWERINFO";
            break;
         case 12:
            type = "UPTODATE";
            break;
         case 13:
            type = "DIFF";
            break;
         case 14:
            type = "TRUNC";
            break;
         case 15:
            type = "SNAP";
            break;
         case 16:
         case 17:
         default:
            type = "UNKNOWN" + p.getType();
            break;
         case 18:
            type = "ACKEPOCH";
            break;
         case 19:
            type = "INFORMANDACTIVATE";
      }

      String entry = null;
      if (type != null) {
         entry = type + " " + Long.toHexString(p.getZxid()) + " " + mess;
      }

      return entry;
   }

   public void run() {
      try {
         this.learnerMaster.addLearnerHandler(this);
         this.tickOfNextAckDeadline = (long)this.learnerMaster.getTickOfInitialAckDeadline();
         this.ia = BinaryInputArchive.getArchive(this.bufferedInput);
         this.bufferedOutput = new BufferedOutputStream(this.sock.getOutputStream());
         this.oa = BinaryOutputArchive.getArchive(this.bufferedOutput);
         QuorumPacket qp = new QuorumPacket();
         this.ia.readRecord(qp, "packet");
         this.messageTracker.trackReceived(qp.getType());
         if (qp.getType() == 11 || qp.getType() == 16) {
            if (this.learnerMaster instanceof ObserverMaster && qp.getType() != 16) {
               throw new IOException("Non observer attempting to connect to ObserverMaster. type = " + qp.getType());
            }

            byte[] learnerInfoData = qp.getData();
            if (learnerInfoData != null) {
               ByteBuffer bbsid = ByteBuffer.wrap(learnerInfoData);
               if (learnerInfoData.length >= 8) {
                  this.sid = bbsid.getLong();
               }

               if (learnerInfoData.length >= 12) {
                  this.version = bbsid.getInt();
               }

               if (learnerInfoData.length >= 20) {
                  long configVersion = bbsid.getLong();
                  if (configVersion > this.learnerMaster.getQuorumVerifierVersion()) {
                     throw new IOException("Follower is ahead of the leader (has a later activated configuration)");
                  }
               }
            } else {
               this.sid = this.learnerMaster.getAndDecrementFollowerCounter();
            }

            String followerInfo = this.learnerMaster.getPeerInfo(this.sid);
            if (followerInfo.isEmpty()) {
               LOG.info("Follower sid: {} not in the current config {}", this.sid, Long.toHexString(this.learnerMaster.getQuorumVerifierVersion()));
            } else {
               LOG.info("Follower sid: {} : info : {}", this.sid, followerInfo);
            }

            if (qp.getType() == 16) {
               this.learnerType = QuorumPeer.LearnerType.OBSERVER;
            }

            this.learnerMaster.registerLearnerHandlerBean(this, this.sock);
            long lastAcceptedEpoch = ZxidUtils.getEpochFromZxid(qp.getZxid());
            StateSummary ss = null;
            long zxid = qp.getZxid();
            long newEpoch = this.learnerMaster.getEpochToPropose(this.getSid(), lastAcceptedEpoch);
            long newLeaderZxid = ZxidUtils.makeZxid(newEpoch, 0L);
            if (this.getVersion() < 65536) {
               long epoch = ZxidUtils.getEpochFromZxid(zxid);
               ss = new StateSummary(epoch, zxid);
               this.learnerMaster.waitForEpochAck(this.getSid(), ss);
            } else {
               byte[] ver = new byte[4];
               ByteBuffer.wrap(ver).putInt(65536);
               QuorumPacket newEpochPacket = new QuorumPacket(17, newLeaderZxid, ver, (List)null);
               this.oa.writeRecord(newEpochPacket, "packet");
               this.messageTracker.trackSent(17);
               this.bufferedOutput.flush();
               QuorumPacket ackEpochPacket = new QuorumPacket();
               this.ia.readRecord(ackEpochPacket, "packet");
               this.messageTracker.trackReceived(ackEpochPacket.getType());
               if (ackEpochPacket.getType() != 18) {
                  LOG.error("{} is not ACKEPOCH", ackEpochPacket.toString());
                  return;
               }

               ByteBuffer bbepoch = ByteBuffer.wrap(ackEpochPacket.getData());
               ss = new StateSummary((long)bbepoch.getInt(), ackEpochPacket.getZxid());
               this.learnerMaster.waitForEpochAck(this.getSid(), ss);
            }

            long peerLastZxid = ss.getLastZxid();
            boolean needSnap = this.syncFollower(peerLastZxid, this.learnerMaster);
            boolean exemptFromThrottle = this.getLearnerType() != QuorumPeer.LearnerType.OBSERVER;
            if (needSnap) {
               this.syncThrottler = this.learnerMaster.getLearnerSnapSyncThrottler();
               this.syncThrottler.beginSync(exemptFromThrottle);
               ServerMetrics.getMetrics().INFLIGHT_SNAP_COUNT.add((long)this.syncThrottler.getSyncInProgress());

               try {
                  long zxidToSend = this.learnerMaster.getZKDatabase().getDataTreeLastProcessedZxid();
                  this.oa.writeRecord(new QuorumPacket(15, zxidToSend, (byte[])null, (List)null), "packet");
                  this.messageTracker.trackSent(15);
                  this.bufferedOutput.flush();
                  LOG.info("Sending snapshot last zxid of peer is 0x{}, zxid of leader is 0x{}, send zxid of db as 0x{}, {} concurrent snapshot sync, snapshot sync was {} from throttle", new Object[]{Long.toHexString(peerLastZxid), Long.toHexString(this.leaderLastZxid), Long.toHexString(zxidToSend), this.syncThrottler.getSyncInProgress(), exemptFromThrottle ? "exempt" : "not exempt"});
                  this.learnerMaster.getZKDatabase().serializeSnapshot(this.oa);
                  this.oa.writeString("BenWasHere", "signature");
                  this.bufferedOutput.flush();
               } finally {
                  ServerMetrics.getMetrics().SNAP_COUNT.add(1L);
               }
            } else {
               this.syncThrottler = this.learnerMaster.getLearnerDiffSyncThrottler();
               this.syncThrottler.beginSync(exemptFromThrottle);
               ServerMetrics.getMetrics().INFLIGHT_DIFF_COUNT.add((long)this.syncThrottler.getSyncInProgress());
               ServerMetrics.getMetrics().DIFF_COUNT.add(1L);
            }

            LOG.debug("Sending NEWLEADER message to {}", this.sid);
            if (this.getVersion() < 65536) {
               QuorumPacket newLeaderQP = new QuorumPacket(10, newLeaderZxid, (byte[])null, (List)null);
               this.oa.writeRecord(newLeaderQP, "packet");
            } else {
               QuorumPacket newLeaderQP = new QuorumPacket(10, newLeaderZxid, this.learnerMaster.getQuorumVerifierBytes(), (List)null);
               this.queuedPackets.add(newLeaderQP);
            }

            this.bufferedOutput.flush();
            this.startSendingPackets();
            qp = new QuorumPacket();
            this.ia.readRecord(qp, "packet");
            this.messageTracker.trackReceived(qp.getType());
            if (qp.getType() != 3) {
               LOG.error("Next packet was supposed to be an ACK, but received packet: {}", packetToString(qp));
               return;
            }

            LOG.debug("Received NEWLEADER-ACK message from {}", this.sid);
            this.learnerMaster.waitForNewLeaderAck(this.getSid(), qp.getZxid());
            this.syncLimitCheck.start();
            this.syncThrottler.endSync();
            if (needSnap) {
               ServerMetrics.getMetrics().INFLIGHT_SNAP_COUNT.add((long)this.syncThrottler.getSyncInProgress());
            } else {
               ServerMetrics.getMetrics().INFLIGHT_DIFF_COUNT.add((long)this.syncThrottler.getSyncInProgress());
            }

            this.syncThrottler = null;
            this.sock.setSoTimeout(this.learnerMaster.syncTimeout());
            this.learnerMaster.waitForStartup();
            LOG.debug("Sending UPTODATE message to {}", this.sid);
            this.queuedPackets.add(new QuorumPacket(12, -1L, (byte[])null, (List)null));

            label496:
            while(true) {
               qp = new QuorumPacket();
               this.ia.readRecord(qp, "packet");
               this.messageTracker.trackReceived(qp.getType());
               if (LOG.isTraceEnabled()) {
                  long traceMask = 16L;
                  if (qp.getType() == 5) {
                     traceMask = 128L;
                  }

                  ZooTrace.logQuorumPacket(LOG, traceMask, 'i', qp);
               }

               this.tickOfNextAckDeadline = (long)this.learnerMaster.getTickOfNextAckDeadline();
               this.packetsReceived.incrementAndGet();
               switch (qp.getType()) {
                  case 1:
                     ByteBuffer bb = ByteBuffer.wrap(qp.getData());
                     long sessionId = bb.getLong();
                     int cxid = bb.getInt();
                     int type = bb.getInt();
                     bb = bb.slice();
                     Request si;
                     if (type == 9) {
                        si = new LearnerSyncRequest(this, sessionId, cxid, type, RequestRecord.fromBytes(bb), qp.getAuthinfo());
                     } else {
                        si = new Request((ServerCnxn)null, sessionId, cxid, type, RequestRecord.fromBytes(bb), qp.getAuthinfo());
                     }

                     si.setOwner(this);
                     this.learnerMaster.submitLearnerRequest(si);
                     this.requestsReceived.incrementAndGet();
                     continue;
                  case 2:
                  case 4:
                  default:
                     LOG.warn("unexpected quorum packet, type: {}", packetToString(qp));
                     continue;
                  case 3:
                     if (this.learnerType == QuorumPeer.LearnerType.OBSERVER) {
                        LOG.debug("Received ACK from Observer {}", this.sid);
                     }

                     this.syncLimitCheck.updateAck(qp.getZxid());
                     this.learnerMaster.processAck(this.sid, qp.getZxid(), this.sock.getLocalSocketAddress());
                     continue;
                  case 5:
                     ByteArrayInputStream bis = new ByteArrayInputStream(qp.getData());
                     DataInputStream dis = new DataInputStream(bis);

                     while(true) {
                        if (dis.available() <= 0) {
                           continue label496;
                        }

                        long sess = dis.readLong();
                        int to = dis.readInt();
                        this.learnerMaster.touch(sess, to);
                     }
                  case 6:
               }

               ServerMetrics.getMetrics().REVALIDATE_COUNT.add(1L);
               this.learnerMaster.revalidateSession(qp, this);
            }
         }

         LOG.error("First packet {} is not FOLLOWERINFO or OBSERVERINFO!", qp.toString());
      } catch (IOException e) {
         LOG.error("Unexpected exception in LearnerHandler: ", e);
         this.closeSocket();
         return;
      } catch (InterruptedException e) {
         LOG.error("Unexpected exception in LearnerHandler.", e);
         return;
      } catch (SyncThrottleException e) {
         LOG.error("too many concurrent sync.", e);
         this.syncThrottler = null;
         return;
      } catch (Exception e) {
         LOG.error("Unexpected exception in LearnerHandler.", e);
         throw e;
      } finally {
         if (this.syncThrottler != null) {
            this.syncThrottler.endSync();
            this.syncThrottler = null;
         }

         String remoteAddr = this.getRemoteAddress();
         LOG.warn("******* GOODBYE {} ********", remoteAddr);
         this.messageTracker.dumpToLog(remoteAddr);
         this.shutdown();
      }

   }

   protected void startSendingPackets() {
      if (!this.sendingThreadStarted) {
         (new Thread() {
            public void run() {
               Thread.currentThread().setName("Sender-" + LearnerHandler.this.sock.getRemoteSocketAddress());

               try {
                  LearnerHandler.this.sendPackets();
               } catch (InterruptedException e) {
                  LearnerHandler.LOG.warn("Unexpected interruption", e);
               }

            }
         }).start();
         this.sendingThreadStarted = true;
      } else {
         LOG.error("Attempting to start sending thread after it already started");
      }

   }

   protected boolean shouldSendMarkerPacketForLogging() {
      return true;
   }

   boolean syncFollower(long peerLastZxid, LearnerMaster learnerMaster) {
      boolean isPeerNewEpochZxid = (peerLastZxid & 4294967295L) == 0L;
      long currentZxid = peerLastZxid;
      boolean needSnap = true;
      ZKDatabase db = learnerMaster.getZKDatabase();
      boolean txnLogSyncEnabled = db.isTxnLogSyncEnabled();
      ReentrantReadWriteLock lock = db.getLogLock();
      ReentrantReadWriteLock.ReadLock rl = lock.readLock();

      try {
         rl.lock();
         long maxCommittedLog = db.getmaxCommittedLog();
         long minCommittedLog = db.getminCommittedLog();
         long lastProcessedZxid = db.getDataTreeLastProcessedZxid();
         LOG.info("Synchronizing with Learner sid: {} maxCommittedLog=0x{} minCommittedLog=0x{} lastProcessedZxid=0x{} peerLastZxid=0x{}", new Object[]{this.getSid(), Long.toHexString(maxCommittedLog), Long.toHexString(minCommittedLog), Long.toHexString(lastProcessedZxid), Long.toHexString(peerLastZxid)});
         if (db.getCommittedLog().isEmpty()) {
            minCommittedLog = lastProcessedZxid;
            maxCommittedLog = lastProcessedZxid;
         }

         if (this.forceSnapSync) {
            LOG.warn("Forcing snapshot sync - should not see this in production");
         } else if (lastProcessedZxid == peerLastZxid) {
            LOG.info("Sending DIFF zxid=0x{} for peer sid: {}", Long.toHexString(peerLastZxid), this.getSid());
            this.queueOpPacket(13, peerLastZxid);
            this.needOpPacket = false;
            needSnap = false;
         } else if (peerLastZxid > maxCommittedLog && !isPeerNewEpochZxid) {
            LOG.debug("Sending TRUNC to follower zxidToSend=0x{} for peer sid:{}", Long.toHexString(maxCommittedLog), this.getSid());
            this.queueOpPacket(14, maxCommittedLog);
            currentZxid = maxCommittedLog;
            this.needOpPacket = false;
            needSnap = false;
         } else if (maxCommittedLog >= peerLastZxid && minCommittedLog <= peerLastZxid) {
            LOG.info("Using committedLog for peer sid: {}", this.getSid());
            Iterator<Leader.Proposal> itr = db.getCommittedLog().iterator();
            currentZxid = this.queueCommittedProposals(itr, peerLastZxid, (Long)null, maxCommittedLog);
            needSnap = false;
         } else if (peerLastZxid < minCommittedLog && txnLogSyncEnabled) {
            long sizeLimit = db.calculateTxnLogSizeLimit();
            Iterator<Leader.Proposal> txnLogItr = db.getProposalsFromTxnLog(peerLastZxid, sizeLimit);
            if (txnLogItr.hasNext()) {
               LOG.info("Use txnlog and committedLog for peer sid: {}", this.getSid());
               currentZxid = this.queueCommittedProposals(txnLogItr, peerLastZxid, minCommittedLog, maxCommittedLog);
               if (currentZxid < minCommittedLog) {
                  LOG.info("Detected gap between end of txnlog: 0x{} and start of committedLog: 0x{}", Long.toHexString(currentZxid), Long.toHexString(minCommittedLog));
                  currentZxid = peerLastZxid;
                  this.queuedPackets.clear();
                  this.needOpPacket = true;
               } else {
                  LOG.debug("Queueing committedLog 0x{}", Long.toHexString(currentZxid));
                  Iterator<Leader.Proposal> committedLogItr = db.getCommittedLog().iterator();
                  currentZxid = this.queueCommittedProposals(committedLogItr, currentZxid, (Long)null, maxCommittedLog);
                  needSnap = false;
               }
            }

            if (txnLogItr instanceof TxnLogProposalIterator) {
               TxnLogProposalIterator txnProposalItr = (TxnLogProposalIterator)txnLogItr;
               txnProposalItr.close();
            }
         } else {
            LOG.warn("Unhandled scenario for peer sid: {} maxCommittedLog=0x{} minCommittedLog=0x{} lastProcessedZxid=0x{} peerLastZxid=0x{} txnLogSyncEnabled={}", new Object[]{this.getSid(), Long.toHexString(maxCommittedLog), Long.toHexString(minCommittedLog), Long.toHexString(lastProcessedZxid), Long.toHexString(peerLastZxid), txnLogSyncEnabled});
         }

         if (needSnap) {
            currentZxid = db.getDataTreeLastProcessedZxid();
         }

         LOG.debug("Start forwarding 0x{} for peer sid: {}", Long.toHexString(currentZxid), this.getSid());
         this.leaderLastZxid = learnerMaster.startForwarding(this, currentZxid);
      } finally {
         rl.unlock();
      }

      if (this.needOpPacket && !needSnap) {
         LOG.error("Unhandled scenario for peer sid: {} fall back to use snapshot", this.getSid());
         needSnap = true;
      }

      return needSnap;
   }

   protected long queueCommittedProposals(Iterator itr, long peerLastZxid, Long maxZxid, Long lastCommittedZxid) {
      boolean isPeerNewEpochZxid = (peerLastZxid & 4294967295L) == 0L;
      long queuedZxid = peerLastZxid;
      long prevProposalZxid = -1L;

      while(itr.hasNext()) {
         Leader.Proposal propose = (Leader.Proposal)itr.next();
         long packetZxid = propose.getZxid();
         if (maxZxid != null && packetZxid > maxZxid) {
            break;
         }

         if (packetZxid < peerLastZxid) {
            prevProposalZxid = packetZxid;
         } else {
            if (this.needOpPacket) {
               if (packetZxid == peerLastZxid) {
                  LOG.info("Sending DIFF zxid=0x{}  for peer sid: {}", Long.toHexString(lastCommittedZxid), this.getSid());
                  this.queueOpPacket(13, lastCommittedZxid);
                  this.needOpPacket = false;
                  continue;
               }

               if (isPeerNewEpochZxid) {
                  LOG.info("Sending DIFF zxid=0x{}  for peer sid: {}", Long.toHexString(lastCommittedZxid), this.getSid());
                  this.queueOpPacket(13, lastCommittedZxid);
                  this.needOpPacket = false;
               } else if (packetZxid > peerLastZxid) {
                  if (ZxidUtils.getEpochFromZxid(packetZxid) != ZxidUtils.getEpochFromZxid(peerLastZxid)) {
                     LOG.warn("Cannot send TRUNC to peer sid: " + this.getSid() + " peer zxid is from different epoch");
                     return queuedZxid;
                  }

                  LOG.info("Sending TRUNC zxid=0x{}  for peer sid: {}", Long.toHexString(prevProposalZxid), this.getSid());
                  this.queueOpPacket(14, prevProposalZxid);
                  this.needOpPacket = false;
               }
            }

            if (packetZxid > queuedZxid) {
               this.queuePacket(propose.getQuorumPacket());
               this.queueOpPacket(4, packetZxid);
               queuedZxid = packetZxid;
            }
         }
      }

      if (this.needOpPacket && isPeerNewEpochZxid) {
         LOG.info("Sending DIFF zxid=0x{}  for peer sid: {}", Long.toHexString(lastCommittedZxid), this.getSid());
         this.queueOpPacket(13, lastCommittedZxid);
         this.needOpPacket = false;
      }

      return queuedZxid;
   }

   public void shutdown() {
      try {
         this.queuedPackets.clear();
         this.queuedPackets.put(this.proposalOfDeath);
      } catch (InterruptedException e) {
         LOG.warn("Ignoring unexpected exception", e);
      }

      this.closeSocket();
      this.interrupt();
      this.learnerMaster.removeLearnerHandler(this);
      this.learnerMaster.unregisterLearnerHandlerBean(this);
   }

   public long tickOfNextAckDeadline() {
      return this.tickOfNextAckDeadline;
   }

   public void ping() {
      if (this.sendingThreadStarted) {
         if (this.syncLimitCheck.check(System.nanoTime())) {
            long id = this.learnerMaster.getLastProposed();
            QuorumPacket ping = new QuorumPacket(5, id, (byte[])null, (List)null);
            this.queuePacket(ping);
         } else {
            LOG.warn("Closing connection to peer due to transaction timeout.");
            this.shutdown();
         }

      }
   }

   private void queueOpPacket(int type, long zxid) {
      QuorumPacket packet = new QuorumPacket(type, zxid, (byte[])null, (List)null);
      this.queuePacket(packet);
   }

   void queuePacket(QuorumPacket p) {
      this.queuedPackets.add(p);
      if (this.shouldSendMarkerPacketForLogging() && this.packetCounter.getAndIncrement() % 1000 == 0) {
         this.queuedPackets.add(new MarkerQuorumPacket(System.nanoTime()));
      }

      this.queuedPacketsSize.addAndGet(packetSize(p));
   }

   static long packetSize(QuorumPacket p) {
      long size = 28L;
      byte[] data = p.getData();
      if (data != null) {
         size += (long)data.length;
      }

      return size;
   }

   public boolean synced() {
      return this.isAlive() && (long)this.learnerMaster.getCurrentTick() <= this.tickOfNextAckDeadline;
   }

   public synchronized Map getLearnerHandlerInfo() {
      Map<String, Object> info = new LinkedHashMap(9);
      info.put("remote_socket_address", this.getRemoteAddress());
      info.put("sid", this.getSid());
      info.put("established", this.getEstablished());
      info.put("queued_packets", this.queuedPackets.size());
      info.put("queued_packets_size", this.queuedPacketsSize.get());
      info.put("packets_received", this.packetsReceived.longValue());
      info.put("packets_sent", this.packetsSent.longValue());
      info.put("requests", this.requestsReceived.longValue());
      info.put("last_zxid", this.getLastZxid());
      return info;
   }

   public synchronized void resetObserverConnectionStats() {
      this.packetsReceived.set(0L);
      this.packetsSent.set(0L);
      this.requestsReceived.set(0L);
      this.lastZxid = -1L;
   }

   public Queue getQueuedPackets() {
      return this.queuedPackets;
   }

   public void setFirstPacket(boolean value) {
      this.needOpPacket = value;
   }

   void closeSocket() {
      if (this.sock != null && !this.sock.isClosed() && this.sockBeingClosed.compareAndSet(false, true)) {
         if (closeSocketAsync) {
            LOG.info("Asynchronously closing socket to learner {}.", this.getSid());
            this.closeSockAsync();
         } else {
            LOG.info("Synchronously closing socket to learner {}.", this.getSid());
            this.closeSockSync();
         }
      }

   }

   void closeSockAsync() {
      Thread closingThread = new Thread(() -> this.closeSockSync(), "CloseSocketThread(sid:" + this.sid);
      closingThread.setDaemon(true);
      closingThread.start();
   }

   void closeSockSync() {
      try {
         if (this.sock != null) {
            long startTime = Time.currentElapsedTime();
            this.sock.close();
            ServerMetrics.getMetrics().SOCKET_CLOSING_TIME.add(Time.currentElapsedTime() - startTime);
         }
      } catch (IOException e) {
         LOG.warn("Ignoring error closing connection to learner {}", this.getSid(), e);
      }

   }

   static {
      LOG.info("{} = {}", "zookeeper.leader.closeSocketAsync", closeSocketAsync);
   }

   private class SyncLimitCheck {
      private boolean started;
      private long currentZxid;
      private long currentTime;
      private long nextZxid;
      private long nextTime;

      private SyncLimitCheck() {
         this.started = false;
         this.currentZxid = 0L;
         this.currentTime = 0L;
         this.nextZxid = 0L;
         this.nextTime = 0L;
      }

      public synchronized void start() {
         this.started = true;
      }

      public synchronized void updateProposal(long zxid, long time) {
         if (this.started) {
            if (this.currentTime == 0L) {
               this.currentTime = time;
               this.currentZxid = zxid;
            } else {
               this.nextTime = time;
               this.nextZxid = zxid;
            }

         }
      }

      public synchronized void updateAck(long zxid) {
         if (this.currentZxid == zxid) {
            this.currentTime = this.nextTime;
            this.currentZxid = this.nextZxid;
            this.nextTime = 0L;
            this.nextZxid = 0L;
         } else if (this.nextZxid == zxid) {
            LearnerHandler.LOG.warn("ACK for 0x{} received before ACK for 0x{}", Long.toHexString(zxid), Long.toHexString(this.currentZxid));
            this.nextTime = 0L;
            this.nextZxid = 0L;
         }

      }

      public synchronized boolean check(long time) {
         if (this.currentTime == 0L) {
            return true;
         } else {
            long msDelay = (time - this.currentTime) / 1000000L;
            return msDelay < (long)LearnerHandler.this.learnerMaster.syncTimeout();
         }
      }
   }

   private static class MarkerQuorumPacket extends QuorumPacket {
      long time;

      MarkerQuorumPacket(long time) {
         this.time = time;
      }

      public int hashCode() {
         return Objects.hash(new Object[]{this.time});
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            MarkerQuorumPacket that = (MarkerQuorumPacket)o;
            return this.time == that.time;
         } else {
            return false;
         }
      }
   }
}
