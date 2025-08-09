package org.apache.zookeeper.server.quorum;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;
import javax.net.ssl.SSLSocket;
import org.apache.jute.BinaryInputArchive;
import org.apache.jute.BinaryOutputArchive;
import org.apache.jute.InputArchive;
import org.apache.jute.OutputArchive;
import org.apache.jute.Record;
import org.apache.zookeeper.common.Time;
import org.apache.zookeeper.common.X509Exception;
import org.apache.zookeeper.server.ExitCode;
import org.apache.zookeeper.server.Request;
import org.apache.zookeeper.server.ServerCnxn;
import org.apache.zookeeper.server.ServerMetrics;
import org.apache.zookeeper.server.TxnLogEntry;
import org.apache.zookeeper.server.ZooKeeperServer;
import org.apache.zookeeper.server.ZooTrace;
import org.apache.zookeeper.server.quorum.flexible.QuorumVerifier;
import org.apache.zookeeper.server.util.ConfigUtils;
import org.apache.zookeeper.server.util.MessageTracker;
import org.apache.zookeeper.server.util.SerializeUtils;
import org.apache.zookeeper.server.util.ZxidUtils;
import org.apache.zookeeper.txn.SetDataTxn;
import org.apache.zookeeper.txn.TxnDigest;
import org.apache.zookeeper.txn.TxnHeader;
import org.apache.zookeeper.util.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Learner {
   QuorumPeer self;
   LearnerZooKeeperServer zk;
   protected BufferedOutputStream bufferedOutput;
   protected Socket sock;
   protected MultipleAddresses leaderAddr;
   protected AtomicBoolean sockBeingClosed = new AtomicBoolean(false);
   LearnerSender sender = null;
   protected InputArchive leaderIs;
   protected OutputArchive leaderOs;
   protected int leaderProtocolVersion = 1;
   private static final int BUFFERED_MESSAGE_SIZE = 10;
   protected final MessageTracker messageTracker = new MessageTracker(10);
   protected static final Logger LOG = LoggerFactory.getLogger(Learner.class);
   private static final int leaderConnectDelayDuringRetryMs = Integer.getInteger("zookeeper.leaderConnectDelayDuringRetryMs", 100);
   private static final boolean nodelay = System.getProperty("follower.nodelay", "true").equals("true");
   public static final String LEARNER_ASYNC_SENDING = "zookeeper.learner.asyncSending";
   private static boolean asyncSending = Boolean.parseBoolean(ConfigUtils.getPropertyBackwardCompatibleWay("zookeeper.learner.asyncSending"));
   public static final String LEARNER_CLOSE_SOCKET_ASYNC = "zookeeper.learner.closeSocketAsync";
   public static final boolean closeSocketAsync = Boolean.parseBoolean(ConfigUtils.getPropertyBackwardCompatibleWay("zookeeper.learner.closeSocketAsync"));
   final ConcurrentHashMap pendingRevalidations = new ConcurrentHashMap();

   public Socket getSocket() {
      return this.sock;
   }

   public int getPendingRevalidationsCount() {
      return this.pendingRevalidations.size();
   }

   protected static void setAsyncSending(boolean newMode) {
      asyncSending = newMode;
      LOG.info("{} = {}", "zookeeper.learner.asyncSending", asyncSending);
   }

   protected static boolean getAsyncSending() {
      return asyncSending;
   }

   void validateSession(ServerCnxn cnxn, long clientId, int timeout) throws IOException {
      LOG.info("Revalidating client: 0x{}", Long.toHexString(clientId));
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      DataOutputStream dos = new DataOutputStream(baos);
      dos.writeLong(clientId);
      dos.writeInt(timeout);
      dos.close();
      QuorumPacket qp = new QuorumPacket(6, -1L, baos.toByteArray(), (List)null);
      this.pendingRevalidations.put(clientId, cnxn);
      if (LOG.isTraceEnabled()) {
         ZooTrace.logTraceMessage(LOG, 32L, "To validate session 0x" + Long.toHexString(clientId));
      }

      this.writePacket(qp, true);
   }

   void writePacket(QuorumPacket pp, boolean flush) throws IOException {
      if (asyncSending) {
         this.sender.queuePacket(pp);
      } else {
         this.writePacketNow(pp, flush);
      }

   }

   void writePacketNow(QuorumPacket pp, boolean flush) throws IOException {
      synchronized(this.leaderOs) {
         if (pp != null) {
            this.messageTracker.trackSent(pp.getType());
            this.leaderOs.writeRecord(pp, "packet");
         }

         if (flush) {
            this.bufferedOutput.flush();
         }

      }
   }

   protected void startSendingThread() {
      this.sender = new LearnerSender(this);
      this.sender.start();
   }

   void readPacket(QuorumPacket pp) throws IOException {
      synchronized(this.leaderIs) {
         this.leaderIs.readRecord(pp, "packet");
         this.messageTracker.trackReceived(pp.getType());
      }

      if (LOG.isTraceEnabled()) {
         long traceMask = pp.getType() == 5 ? 128L : 16L;
         ZooTrace.logQuorumPacket(LOG, traceMask, 'i', pp);
      }

   }

   void request(Request request) throws IOException {
      if (request.isThrottled()) {
         LOG.error("Throttled request sent to leader: {}. Exiting", request);
         ServiceUtils.requestSystemExit(ExitCode.UNEXPECTED_ERROR.getValue());
      }

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      DataOutputStream oa = new DataOutputStream(baos);
      oa.writeLong(request.sessionId);
      oa.writeInt(request.cxid);
      oa.writeInt(request.type);
      byte[] payload = request.readRequestBytes();
      if (payload != null) {
         oa.write(payload);
      }

      oa.close();
      QuorumPacket qp = new QuorumPacket(1, -1L, baos.toByteArray(), request.authInfo);
      this.writePacket(qp, true);
   }

   protected QuorumPeer.QuorumServer findLeader() {
      QuorumPeer.QuorumServer leaderServer = null;
      Vote current = this.self.getCurrentVote();

      for(QuorumPeer.QuorumServer s : this.self.getView().values()) {
         if (s.id == current.getId()) {
            s.recreateSocketAddresses();
            leaderServer = s;
            break;
         }
      }

      if (leaderServer == null) {
         LOG.warn("Couldn't find the leader with id = {}", current.getId());
      }

      return leaderServer;
   }

   protected long nanoTime() {
      return System.nanoTime();
   }

   protected void sockConnect(Socket sock, InetSocketAddress addr, int timeout) throws IOException {
      sock.connect(addr, timeout);
   }

   protected void connectToLeader(MultipleAddresses multiAddr, String hostname) throws IOException {
      this.leaderAddr = multiAddr;
      Set<InetSocketAddress> addresses;
      if (this.self.isMultiAddressReachabilityCheckEnabled()) {
         addresses = multiAddr.getAllReachableAddressesOrAll();
      } else {
         addresses = multiAddr.getAllAddresses();
      }

      ExecutorService executor = Executors.newFixedThreadPool(addresses.size());
      CountDownLatch latch = new CountDownLatch(addresses.size());
      AtomicReference<Socket> socket = new AtomicReference((Object)null);
      Stream var10000 = addresses.stream().map((address) -> new LeaderConnector(address, socket, latch));
      Objects.requireNonNull(executor);
      var10000.forEach(executor::submit);

      try {
         latch.await();
      } catch (InterruptedException e) {
         LOG.warn("Interrupted while trying to connect to Leader", e);
      } finally {
         executor.shutdown();

         try {
            if (!executor.awaitTermination(1L, TimeUnit.SECONDS)) {
               LOG.error("not all the LeaderConnector terminated properly");
            }
         } catch (InterruptedException ie) {
            LOG.error("Interrupted while terminating LeaderConnector executor.", ie);
         }

      }

      if (socket.get() == null) {
         throw new IOException("Failed connect to " + multiAddr);
      } else {
         this.sock = (Socket)socket.get();
         this.sockBeingClosed.set(false);
         this.self.authLearner.authenticate(this.sock, hostname);
         this.leaderIs = BinaryInputArchive.getArchive(new BufferedInputStream(this.sock.getInputStream()));
         this.bufferedOutput = new BufferedOutputStream(this.sock.getOutputStream());
         this.leaderOs = BinaryOutputArchive.getArchive(this.bufferedOutput);
         if (asyncSending) {
            this.startSendingThread();
         }

      }
   }

   protected Socket createSocket() throws X509Exception, IOException {
      Socket sock;
      if (this.self.isSslQuorum()) {
         sock = this.self.getX509Util().createSSLSocket();
      } else {
         sock = new Socket();
      }

      sock.setSoTimeout(this.self.tickTime * this.self.initLimit);
      return sock;
   }

   protected long registerWithLeader(int pktType) throws IOException {
      long lastLoggedZxid = this.self.getLastLoggedZxid();
      QuorumPacket qp = new QuorumPacket();
      qp.setType(pktType);
      qp.setZxid(ZxidUtils.makeZxid(this.self.getAcceptedEpoch(), 0L));
      LearnerInfo li = new LearnerInfo(this.self.getMyId(), 65536, this.self.getQuorumVerifier().getVersion());
      ByteArrayOutputStream bsid = new ByteArrayOutputStream();
      BinaryOutputArchive boa = BinaryOutputArchive.getArchive(bsid);
      boa.writeRecord(li, "LearnerInfo");
      qp.setData(bsid.toByteArray());
      this.writePacket(qp, true);
      this.readPacket(qp);
      long newEpoch = ZxidUtils.getEpochFromZxid(qp.getZxid());
      if (qp.getType() == 17) {
         this.leaderProtocolVersion = ByteBuffer.wrap(qp.getData()).getInt();
         byte[] epochBytes = new byte[4];
         ByteBuffer wrappedEpochBytes = ByteBuffer.wrap(epochBytes);
         if (newEpoch > this.self.getAcceptedEpoch()) {
            wrappedEpochBytes.putInt((int)this.self.getCurrentEpoch());
            this.self.setAcceptedEpoch(newEpoch);
         } else {
            if (newEpoch != this.self.getAcceptedEpoch()) {
               throw new IOException("Leaders epoch, " + newEpoch + " is less than accepted epoch, " + this.self.getAcceptedEpoch());
            }

            wrappedEpochBytes.putInt(-1);
         }

         QuorumPacket ackNewEpoch = new QuorumPacket(18, lastLoggedZxid, epochBytes, (List)null);
         this.writePacket(ackNewEpoch, true);
         return ZxidUtils.makeZxid(newEpoch, 0L);
      } else {
         if (newEpoch > this.self.getAcceptedEpoch()) {
            this.self.setAcceptedEpoch(newEpoch);
         }

         if (qp.getType() != 10) {
            LOG.error("First packet should have been NEWLEADER");
            throw new IOException("First packet should have been NEWLEADER");
         } else {
            return qp.getZxid();
         }
      }
   }

   protected void syncWithLeader(long newLeaderZxid) throws Exception {
      QuorumPacket ack = new QuorumPacket(3, 0L, (byte[])null, (List)null);
      QuorumPacket qp = new QuorumPacket();
      long newEpoch = ZxidUtils.getEpochFromZxid(newLeaderZxid);
      QuorumVerifier newLeaderQV = null;
      boolean snapshotNeeded = true;
      boolean syncSnapshot = false;
      this.readPacket(qp);
      Deque<Long> packetsCommitted = new ArrayDeque();
      Deque<PacketInFlight> packetsNotLogged = new ArrayDeque();
      synchronized(this.zk) {
         if (qp.getType() == 13) {
            LOG.info("Getting a diff from the leader 0x{}", Long.toHexString(qp.getZxid()));
            this.self.setSyncMode(QuorumPeer.SyncMode.DIFF);
            if (this.zk.shouldForceWriteInitialSnapshotAfterLeaderElection()) {
               LOG.info("Forcing a snapshot write as part of upgrading from an older Zookeeper. This should only happen while upgrading.");
               snapshotNeeded = true;
               syncSnapshot = true;
            } else {
               snapshotNeeded = false;
            }
         } else if (qp.getType() == 15) {
            this.self.setSyncMode(QuorumPeer.SyncMode.SNAP);
            LOG.info("Getting a snapshot from leader 0x{}", Long.toHexString(qp.getZxid()));
            this.zk.getZKDatabase().deserializeSnapshot(this.leaderIs);
            if (!this.self.isReconfigEnabled()) {
               LOG.debug("Reset config node content from local config after deserialization of snapshot.");
               this.zk.getZKDatabase().initConfigInZKDatabase(this.self.getQuorumVerifier());
            }

            String signature = this.leaderIs.readString("signature");
            if (!signature.equals("BenWasHere")) {
               LOG.error("Missing signature. Got {}", signature);
               throw new IOException("Missing signature");
            }

            this.zk.getZKDatabase().setlastProcessedZxid(qp.getZxid());
            syncSnapshot = true;
         } else if (qp.getType() == 14) {
            this.self.setSyncMode(QuorumPeer.SyncMode.TRUNC);
            LOG.warn("Truncating log to get in sync with the leader 0x{}", Long.toHexString(qp.getZxid()));
            boolean truncated = this.zk.getZKDatabase().truncateLog(qp.getZxid());
            if (!truncated) {
               LOG.error("Not able to truncate the log 0x{}", Long.toHexString(qp.getZxid()));
               ServiceUtils.requestSystemExit(ExitCode.QUORUM_PACKET_ERROR.getValue());
            }

            this.zk.getZKDatabase().setlastProcessedZxid(qp.getZxid());
         } else {
            LOG.error("Got unexpected packet from leader: {}, exiting ... ", LearnerHandler.packetToString(qp));
            ServiceUtils.requestSystemExit(ExitCode.QUORUM_PACKET_ERROR.getValue());
         }

         this.zk.getZKDatabase().initConfigInZKDatabase(this.self.getQuorumVerifier());
         this.zk.createSessionTracker();
         long lastQueued = 0L;
         boolean isPreZAB1_0 = true;
         boolean writeToTxnLog = !snapshotNeeded;

         label184:
         while(this.self.isRunning()) {
            this.readPacket(qp);
            switch (qp.getType()) {
               case 2:
                  PacketInFlight pif = new PacketInFlight();
                  TxnLogEntry logEntry = SerializeUtils.deserializeTxn(qp.getData());
                  pif.hdr = logEntry.getHeader();
                  pif.rec = logEntry.getTxn();
                  pif.digest = logEntry.getDigest();
                  if (pif.hdr.getZxid() != lastQueued + 1L) {
                     LOG.warn("Got zxid 0x{} expected 0x{}", Long.toHexString(pif.hdr.getZxid()), Long.toHexString(lastQueued + 1L));
                  }

                  lastQueued = pif.hdr.getZxid();
                  if (pif.hdr.getType() == 16) {
                     SetDataTxn setDataTxn = (SetDataTxn)pif.rec;
                     QuorumVerifier qv = this.self.configFromString(new String(setDataTxn.getData(), StandardCharsets.UTF_8));
                     this.self.setLastSeenQuorumVerifier(qv, true);
                  }

                  packetsNotLogged.add(pif);
               case 3:
               case 5:
               case 6:
               case 7:
               case 11:
               case 13:
               case 14:
               case 15:
               case 16:
               case 17:
               case 18:
               default:
                  break;
               case 4:
               case 9:
                  PacketInFlight pif = (PacketInFlight)packetsNotLogged.peekFirst();
                  if (pif.hdr.getZxid() == qp.getZxid() && qp.getType() == 9) {
                     QuorumVerifier qv = this.self.configFromString(new String(((SetDataTxn)pif.rec).getData(), StandardCharsets.UTF_8));
                     boolean majorChange = this.self.processReconfig(qv, ByteBuffer.wrap(qp.getData()).getLong(), qp.getZxid(), true);
                     if (majorChange) {
                        throw new Exception("changes proposed in reconfig");
                     }
                  }

                  if (!writeToTxnLog) {
                     if (pif.hdr.getZxid() != qp.getZxid()) {
                        LOG.warn("Committing 0x{}, but next proposal is 0x{}", Long.toHexString(qp.getZxid()), Long.toHexString(pif.hdr.getZxid()));
                     } else {
                        this.zk.processTxn(pif.hdr, pif.rec);
                        packetsNotLogged.remove();
                     }
                  } else {
                     packetsCommitted.add(qp.getZxid());
                  }
                  break;
               case 8:
               case 19:
                  PacketInFlight packet = new PacketInFlight();
                  if (qp.getType() == 19) {
                     ByteBuffer buffer = ByteBuffer.wrap(qp.getData());
                     long suggestedLeaderId = buffer.getLong();
                     byte[] remainingdata = new byte[buffer.remaining()];
                     buffer.get(remainingdata);
                     TxnLogEntry logEntry = SerializeUtils.deserializeTxn(remainingdata);
                     packet.hdr = logEntry.getHeader();
                     packet.rec = logEntry.getTxn();
                     packet.digest = logEntry.getDigest();
                     QuorumVerifier qv = this.self.configFromString(new String(((SetDataTxn)packet.rec).getData(), StandardCharsets.UTF_8));
                     boolean majorChange = this.self.processReconfig(qv, suggestedLeaderId, qp.getZxid(), true);
                     if (majorChange) {
                        throw new Exception("changes proposed in reconfig");
                     }
                  } else {
                     TxnLogEntry logEntry = SerializeUtils.deserializeTxn(qp.getData());
                     packet.rec = logEntry.getTxn();
                     packet.hdr = logEntry.getHeader();
                     packet.digest = logEntry.getDigest();
                     if (packet.hdr.getZxid() != lastQueued + 1L) {
                        LOG.warn("Got zxid 0x{} expected 0x{}", Long.toHexString(packet.hdr.getZxid()), Long.toHexString(lastQueued + 1L));
                     }

                     lastQueued = packet.hdr.getZxid();
                  }

                  if (!writeToTxnLog) {
                     this.zk.processTxn(packet.hdr, packet.rec);
                  } else {
                     packetsNotLogged.add(packet);
                     packetsCommitted.add(qp.getZxid());
                  }
                  break;
               case 10:
                  LOG.info("Learner received NEWLEADER message");
                  if (qp.getData() != null && qp.getData().length > 1) {
                     try {
                        QuorumVerifier qv = this.self.configFromString(new String(qp.getData(), StandardCharsets.UTF_8));
                        this.self.setLastSeenQuorumVerifier(qv, true);
                        newLeaderQV = qv;
                     } catch (Exception e) {
                        e.printStackTrace();
                     }
                  }

                  if (snapshotNeeded) {
                     this.zk.takeSnapshot(syncSnapshot);
                  }

                  writeToTxnLog = true;
                  isPreZAB1_0 = false;
                  if (this.zk instanceof FollowerZooKeeperServer && !packetsCommitted.isEmpty()) {
                     long startTime = Time.currentElapsedTime();
                     FollowerZooKeeperServer fzk = (FollowerZooKeeperServer)this.zk;

                     while(!packetsCommitted.isEmpty()) {
                        long zxid = (Long)packetsCommitted.removeFirst();
                        PacketInFlight pif = (PacketInFlight)packetsNotLogged.peekFirst();
                        if (pif == null) {
                           LOG.warn("Committing 0x{}, but got no proposal", Long.toHexString(zxid));
                        } else if (pif.hdr.getZxid() != zxid) {
                           LOG.warn("Committing 0x{}, but next proposal is 0x{}", Long.toHexString(zxid), Long.toHexString(pif.hdr.getZxid()));
                        } else {
                           packetsNotLogged.removeFirst();
                           fzk.appendRequest(pif.hdr, pif.rec, pif.digest);
                           fzk.processTxn(pif.hdr, pif.rec);
                        }
                     }

                     fzk.getZKDatabase().commit();
                     LOG.info("It took {}ms to persist and commit txns in packetsCommitted. {} outstanding txns left in packetsNotLogged", Time.currentElapsedTime() - startTime, packetsNotLogged.size());
                  }

                  this.self.setCurrentEpoch(newEpoch);
                  LOG.info("Set the current epoch to {}", newEpoch);
                  this.sock.setSoTimeout(this.self.tickTime * this.self.syncLimit);
                  this.self.setSyncMode(QuorumPeer.SyncMode.NONE);
                  this.writePacket(new QuorumPacket(3, newLeaderZxid, (byte[])null, (List)null), true);
                  LOG.info("Sent NEWLEADER ack to leader with zxid {}", Long.toHexString(newLeaderZxid));
                  break;
               case 12:
                  LOG.info("Learner received UPTODATE message");
                  if (newLeaderQV != null) {
                     boolean majorChange = this.self.processReconfig(newLeaderQV, (Long)null, (Long)null, true);
                     if (majorChange) {
                        throw new Exception("changes proposed in reconfig");
                     }
                  }

                  if (isPreZAB1_0) {
                     this.zk.takeSnapshot(syncSnapshot);
                     this.self.setCurrentEpoch(newEpoch);
                  }

                  this.self.setZooKeeperServer(this.zk);
                  this.self.adminServer.setZooKeeperServer(this.zk);
                  break label184;
            }
         }
      }

      ack.setZxid(ZxidUtils.makeZxid(newEpoch, 0L));
      this.writePacket(ack, true);
      this.zk.startup();
      this.self.updateElectionVote(newEpoch);
      if (this.zk instanceof FollowerZooKeeperServer) {
         FollowerZooKeeperServer fzk = (FollowerZooKeeperServer)this.zk;

         for(PacketInFlight p : packetsNotLogged) {
            fzk.logRequest(p.hdr, p.rec, p.digest);
         }

         LOG.info("{} txns have been logged asynchronously", packetsNotLogged.size());

         for(Long zxid : packetsCommitted) {
            fzk.commit(zxid);
         }

         LOG.info("{} txns have been committed", packetsCommitted.size());
      } else {
         if (!(this.zk instanceof ObserverZooKeeperServer)) {
            throw new UnsupportedOperationException("Unknown server type");
         }

         ObserverZooKeeperServer ozk = (ObserverZooKeeperServer)this.zk;

         for(PacketInFlight p : packetsNotLogged) {
            Long zxid = (Long)packetsCommitted.peekFirst();
            if (p.hdr.getZxid() != zxid) {
               LOG.warn("Committing 0x{}, but next proposal is 0x{}", Long.toHexString(zxid), Long.toHexString(p.hdr.getZxid()));
            } else {
               packetsCommitted.remove();
               Request request = new Request(p.hdr.getClientId(), p.hdr.getCxid(), p.hdr.getType(), p.hdr, p.rec, -1L);
               request.setTxnDigest(p.digest);
               ozk.commitRequest(request);
            }
         }
      }

   }

   protected void revalidate(QuorumPacket qp) throws IOException {
      ByteArrayInputStream bis = new ByteArrayInputStream(qp.getData());
      DataInputStream dis = new DataInputStream(bis);
      long sessionId = dis.readLong();
      boolean valid = dis.readBoolean();
      ServerCnxn cnxn = (ServerCnxn)this.pendingRevalidations.remove(sessionId);
      if (cnxn == null) {
         LOG.warn("Missing session 0x{} for validation", Long.toHexString(sessionId));
      } else {
         this.zk.finishSessionInit(cnxn, valid);
      }

      if (LOG.isTraceEnabled()) {
         ZooTrace.logTraceMessage(LOG, 32L, "Session 0x" + Long.toHexString(sessionId) + " is valid: " + valid);
      }

   }

   protected void ping(QuorumPacket qp) throws IOException {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      DataOutputStream dos = new DataOutputStream(bos);
      Map<Long, Integer> touchTable = this.zk.getTouchSnapshot();

      for(Map.Entry entry : touchTable.entrySet()) {
         dos.writeLong((Long)entry.getKey());
         dos.writeInt((Integer)entry.getValue());
      }

      QuorumPacket pingReply = new QuorumPacket(qp.getType(), qp.getZxid(), bos.toByteArray(), qp.getAuthinfo());
      this.writePacket(pingReply, true);
   }

   public void shutdown() {
      this.self.setZooKeeperServer((ZooKeeperServer)null);
      this.self.closeAllConnections();
      this.self.adminServer.setZooKeeperServer((ZooKeeperServer)null);
      if (this.sender != null) {
         this.sender.shutdown();
      }

      this.closeSocket();
      if (this.zk != null) {
         this.zk.shutdown(this.self.getSyncMode().equals(QuorumPeer.SyncMode.SNAP));
      }

   }

   boolean isRunning() {
      return this.self.isRunning() && this.zk.isRunning();
   }

   void closeSocket() {
      if (this.sockBeingClosed.compareAndSet(false, true)) {
         if (this.sock == null) {
            return;
         }

         Socket socket = this.sock;
         this.sock = null;
         if (closeSocketAsync) {
            Thread closingThread = new Thread(() -> closeSockSync(socket), "CloseSocketThread(sid:" + this.zk.getServerId());
            closingThread.setDaemon(true);
            closingThread.start();
         } else {
            closeSockSync(socket);
         }
      }

   }

   private static void closeSockSync(Socket socket) {
      try {
         long startTime = Time.currentElapsedTime();
         socket.close();
         ServerMetrics.getMetrics().SOCKET_CLOSING_TIME.add(Time.currentElapsedTime() - startTime);
      } catch (IOException e) {
         LOG.warn("Ignoring error closing connection to leader", e);
      }

   }

   static {
      LOG.info("leaderConnectDelayDuringRetryMs: {}", leaderConnectDelayDuringRetryMs);
      LOG.info("TCP NoDelay set to: {}", nodelay);
      LOG.info("{} = {}", "zookeeper.learner.asyncSending", asyncSending);
      LOG.info("{} = {}", "zookeeper.learner.closeSocketAsync", closeSocketAsync);
   }

   static class PacketInFlight {
      TxnHeader hdr;
      Record rec;
      TxnDigest digest;
   }

   class LeaderConnector implements Runnable {
      private AtomicReference socket;
      private InetSocketAddress address;
      private CountDownLatch latch;

      LeaderConnector(InetSocketAddress address, AtomicReference socket, CountDownLatch latch) {
         this.address = address;
         this.socket = socket;
         this.latch = latch;
      }

      public void run() {
         try {
            Thread.currentThread().setName("LeaderConnector-" + this.address);
            Socket sock = this.connectToLeader();
            if (sock != null && sock.isConnected()) {
               if (this.socket.compareAndSet((Object)null, sock)) {
                  Learner.LOG.info("Successfully connected to leader, using address: {}", this.address);
               } else {
                  Learner.LOG.info("Connection to the leader is already established, close the redundant connection");
                  sock.close();
               }
            }
         } catch (Exception e) {
            Learner.LOG.error("Failed connect to {}", this.address, e);
         } finally {
            this.latch.countDown();
         }

      }

      private Socket connectToLeader() throws IOException, X509Exception, InterruptedException {
         Socket sock = Learner.this.createSocket();
         int connectTimeout = Learner.this.self.tickTime * Learner.this.self.initLimit;
         if (Learner.this.self.connectToLearnerMasterLimit > 0) {
            connectTimeout = Learner.this.self.tickTime * Learner.this.self.connectToLearnerMasterLimit;
         }

         long startNanoTime = Learner.this.nanoTime();

         for(int tries = 0; tries < 5 && this.socket.get() == null; ++tries) {
            try {
               int remainingTimeout = connectTimeout - (int)((Learner.this.nanoTime() - startNanoTime) / 1000000L);
               if (remainingTimeout <= 0) {
                  Learner.LOG.error("connectToLeader exceeded on retries.");
                  throw new IOException("connectToLeader exceeded on retries.");
               }

               Learner.this.sockConnect(sock, this.address, Math.min(connectTimeout, remainingTimeout));
               if (Learner.this.self.isSslQuorum()) {
                  ((SSLSocket)sock).startHandshake();
               }

               sock.setTcpNoDelay(Learner.nodelay);
               break;
            } catch (IOException e) {
               int remainingTimeout = connectTimeout - (int)((Learner.this.nanoTime() - startNanoTime) / 1000000L);
               if (remainingTimeout <= Learner.leaderConnectDelayDuringRetryMs) {
                  Learner.LOG.error("Unexpected exception, connectToLeader exceeded. tries={}, remaining init limit={}, connecting to {}", new Object[]{tries, remainingTimeout, this.address, e});
                  throw e;
               }

               if (tries >= 4) {
                  Learner.LOG.error("Unexpected exception, retries exceeded. tries={}, remaining init limit={}, connecting to {}", new Object[]{tries, remainingTimeout, this.address, e});
                  throw e;
               }

               Learner.LOG.warn("Unexpected exception, tries={}, remaining init limit={}, connecting to {}", new Object[]{tries, remainingTimeout, this.address, e});
               sock = Learner.this.createSocket();
               Thread.sleep((long)Learner.leaderConnectDelayDuringRetryMs);
            }
         }

         return sock;
      }
   }
}
