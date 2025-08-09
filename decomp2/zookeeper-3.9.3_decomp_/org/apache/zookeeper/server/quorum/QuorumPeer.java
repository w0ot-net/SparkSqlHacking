package org.apache.zookeeper.server.quorum;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.security.sasl.SaslException;
import org.apache.yetus.audience.InterfaceAudience.Private;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.common.AtomicFileWritingIdiom;
import org.apache.zookeeper.common.NetUtils;
import org.apache.zookeeper.common.QuorumX509Util;
import org.apache.zookeeper.common.Time;
import org.apache.zookeeper.common.X509Exception;
import org.apache.zookeeper.jmx.MBeanRegistry;
import org.apache.zookeeper.jmx.ZKMBeanInfo;
import org.apache.zookeeper.server.ServerCnxn;
import org.apache.zookeeper.server.ServerCnxnFactory;
import org.apache.zookeeper.server.ServerMetrics;
import org.apache.zookeeper.server.ZKDatabase;
import org.apache.zookeeper.server.ZooKeeperServer;
import org.apache.zookeeper.server.ZooKeeperThread;
import org.apache.zookeeper.server.admin.AdminServer;
import org.apache.zookeeper.server.admin.AdminServerFactory;
import org.apache.zookeeper.server.persistence.FileTxnSnapLog;
import org.apache.zookeeper.server.quorum.auth.NullQuorumAuthLearner;
import org.apache.zookeeper.server.quorum.auth.NullQuorumAuthServer;
import org.apache.zookeeper.server.quorum.auth.QuorumAuthLearner;
import org.apache.zookeeper.server.quorum.auth.QuorumAuthServer;
import org.apache.zookeeper.server.quorum.auth.SaslQuorumAuthLearner;
import org.apache.zookeeper.server.quorum.auth.SaslQuorumAuthServer;
import org.apache.zookeeper.server.quorum.flexible.QuorumMaj;
import org.apache.zookeeper.server.quorum.flexible.QuorumOracleMaj;
import org.apache.zookeeper.server.quorum.flexible.QuorumVerifier;
import org.apache.zookeeper.server.util.ConfigUtils;
import org.apache.zookeeper.server.util.JvmPauseMonitor;
import org.apache.zookeeper.server.util.ZxidUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuorumPeer extends ZooKeeperThread implements QuorumStats.Provider {
   private static final Logger LOG = LoggerFactory.getLogger(QuorumPeer.class);
   public static final String CONFIG_KEY_KERBEROS_CANONICALIZE_HOST_NAMES = "zookeeper.kerberos.canonicalizeHostNames";
   public static final String CONFIG_DEFAULT_KERBEROS_CANONICALIZE_HOST_NAMES = "false";
   private QuorumBean jmxQuorumBean;
   LocalPeerBean jmxLocalPeerBean;
   private Map jmxRemotePeerBean;
   LeaderElectionBean jmxLeaderElectionBean;
   private AtomicReference qcmRef;
   QuorumAuthServer authServer;
   QuorumAuthLearner authLearner;
   private ZKDatabase zkDb;
   private JvmPauseMonitor jvmPauseMonitor;
   private final AtomicBoolean suspended;
   private int observerMasterPort;
   public static final String CONFIG_KEY_MULTI_ADDRESS_ENABLED = "zookeeper.multiAddress.enabled";
   public static final String CONFIG_DEFAULT_MULTI_ADDRESS_ENABLED = "false";
   private boolean multiAddressEnabled;
   public static final String CONFIG_KEY_MULTI_ADDRESS_REACHABILITY_CHECK_TIMEOUT_MS = "zookeeper.multiAddress.reachabilityCheckTimeoutMs";
   private int multiAddressReachabilityCheckTimeoutMs;
   public static final String CONFIG_KEY_MULTI_ADDRESS_REACHABILITY_CHECK_ENABLED = "zookeeper.multiAddress.reachabilityCheckEnabled";
   private boolean multiAddressReachabilityCheckEnabled;
   static final long OBSERVER_ID = Long.MAX_VALUE;
   public long start_fle;
   public long end_fle;
   public static final String FLE_TIME_UNIT = "MS";
   private long unavailableStartTime;
   private LearnerType learnerType;
   private String configFilename;
   private QuorumVerifier quorumVerifier;
   private QuorumVerifier lastSeenQuorumVerifier;
   final Object QV_LOCK;
   private long myid;
   private boolean sslQuorum;
   private boolean shouldUsePortUnification;
   private final QuorumX509Util x509Util;
   private volatile Vote currentVote;
   private volatile boolean running;
   private String initialConfig;
   protected int tickTime;
   protected boolean localSessionsEnabled;
   protected boolean localSessionsUpgradingEnabled;
   protected int minSessionTimeout;
   protected int maxSessionTimeout;
   protected int clientPortListenBacklog;
   protected volatile int initLimit;
   protected volatile int syncLimit;
   protected volatile int connectToLearnerMasterLimit;
   protected boolean syncEnabled;
   protected AtomicInteger tick;
   protected boolean quorumListenOnAllIPs;
   private long electionTimeTaken;
   protected boolean quorumSaslEnableAuth;
   protected boolean quorumServerSaslAuthRequired;
   protected boolean quorumLearnerSaslAuthRequired;
   protected String quorumServicePrincipal;
   protected String quorumLearnerLoginContext;
   protected String quorumServerLoginContext;
   private static final int QUORUM_CNXN_THREADS_SIZE_DEFAULT_VALUE = 20;
   protected int quorumCnxnThreadsSize;
   public static final String QUORUM_CNXN_TIMEOUT_MS = "zookeeper.quorumCnxnTimeoutMs";
   private static int quorumCnxnTimeoutMs = Integer.getInteger("zookeeper.quorumCnxnTimeoutMs", -1);
   private ServerState state;
   private AtomicReference zabState;
   private AtomicReference syncMode;
   private AtomicReference leaderAddress;
   private AtomicLong leaderId;
   private boolean reconfigFlag;
   DatagramSocket udpSocket;
   private final AtomicReference myAddrs;
   private int electionType;
   Election electionAlg;
   ServerCnxnFactory cnxnFactory;
   ServerCnxnFactory secureCnxnFactory;
   private FileTxnSnapLog logFactory;
   private final QuorumStats quorumStats;
   AdminServer adminServer;
   private final boolean reconfigEnabled;
   ResponderThread responder;
   public Follower follower;
   public Leader leader;
   public Observer observer;
   boolean shuttingDownLE;
   public static final String SYNC_ENABLED = "zookeeper.observer.syncEnabled";
   private long acceptedEpoch;
   private long currentEpoch;
   public static final String CURRENT_EPOCH_FILENAME = "currentEpoch";
   public static final String ACCEPTED_EPOCH_FILENAME = "acceptedEpoch";
   private ArrayList observerMasters;
   private int nextObserverMaster;

   public int getObserverMasterPort() {
      return this.observerMasterPort;
   }

   public void setObserverMasterPort(int observerMasterPort) {
      this.observerMasterPort = observerMasterPort;
   }

   public boolean isMultiAddressEnabled() {
      return this.multiAddressEnabled;
   }

   public void setMultiAddressEnabled(boolean multiAddressEnabled) {
      this.multiAddressEnabled = multiAddressEnabled;
      LOG.info("multiAddress.enabled set to {}", multiAddressEnabled);
   }

   public int getMultiAddressReachabilityCheckTimeoutMs() {
      return this.multiAddressReachabilityCheckTimeoutMs;
   }

   public void setMultiAddressReachabilityCheckTimeoutMs(int multiAddressReachabilityCheckTimeoutMs) {
      this.multiAddressReachabilityCheckTimeoutMs = multiAddressReachabilityCheckTimeoutMs;
      LOG.info("multiAddress.reachabilityCheckTimeoutMs set to {}", multiAddressReachabilityCheckTimeoutMs);
   }

   public boolean isMultiAddressReachabilityCheckEnabled() {
      return this.multiAddressReachabilityCheckEnabled;
   }

   public void setMultiAddressReachabilityCheckEnabled(boolean multiAddressReachabilityCheckEnabled) {
      this.multiAddressReachabilityCheckEnabled = multiAddressReachabilityCheckEnabled;
      LOG.info("multiAddress.reachabilityCheckEnabled set to {}", multiAddressReachabilityCheckEnabled);
   }

   public LearnerType getLearnerType() {
      return this.learnerType;
   }

   public void setLearnerType(LearnerType p) {
      this.learnerType = p;
   }

   protected synchronized void setConfigFileName(String s) {
      this.configFilename = s;
   }

   public int getQuorumSize() {
      return this.getVotingView().size();
   }

   public void setJvmPauseMonitor(JvmPauseMonitor jvmPauseMonitor) {
      this.jvmPauseMonitor = jvmPauseMonitor;
   }

   public long getMyId() {
      return this.myid;
   }

   void setId(long id) {
      this.myid = id;
   }

   public boolean isSslQuorum() {
      return this.sslQuorum;
   }

   public boolean shouldUsePortUnification() {
      return this.shouldUsePortUnification;
   }

   QuorumX509Util getX509Util() {
      return this.x509Util;
   }

   public synchronized Vote getCurrentVote() {
      return this.currentVote;
   }

   public synchronized void setCurrentVote(Vote v) {
      this.currentVote = v;
   }

   public synchronized void setPeerState(ServerState newState) {
      this.state = newState;
      if (newState == QuorumPeer.ServerState.LOOKING) {
         this.setLeaderAddressAndId((MultipleAddresses)null, -1L);
         this.setZabState(QuorumPeer.ZabState.ELECTION);
      } else {
         LOG.info("Peer state changed: {}", this.getDetailedPeerState());
      }

   }

   public void setZabState(ZabState zabState) {
      if (zabState == QuorumPeer.ZabState.BROADCAST && this.unavailableStartTime != 0L) {
         long unavailableTime = Time.currentElapsedTime() - this.unavailableStartTime;
         ServerMetrics.getMetrics().UNAVAILABLE_TIME.add(unavailableTime);
         if (this.getPeerState() == QuorumPeer.ServerState.LEADING) {
            ServerMetrics.getMetrics().LEADER_UNAVAILABLE_TIME.add(unavailableTime);
         }

         this.unavailableStartTime = 0L;
      }

      this.zabState.set(zabState);
      LOG.info("Peer state changed: {}", this.getDetailedPeerState());
   }

   public void setSyncMode(SyncMode syncMode) {
      this.syncMode.set(syncMode);
      LOG.info("Peer state changed: {}", this.getDetailedPeerState());
   }

   public ZabState getZabState() {
      return (ZabState)this.zabState.get();
   }

   public SyncMode getSyncMode() {
      return (SyncMode)this.syncMode.get();
   }

   public void setLeaderAddressAndId(MultipleAddresses addr, long newId) {
      if (addr != null) {
         this.leaderAddress.set(String.join("|", addr.getAllHostStrings()));
      } else {
         this.leaderAddress.set((Object)null);
      }

      this.leaderId.set(newId);
   }

   public String getLeaderAddress() {
      return (String)this.leaderAddress.get();
   }

   public long getLeaderId() {
      return this.leaderId.get();
   }

   public String getDetailedPeerState() {
      StringBuilder sb = new StringBuilder(this.getPeerState().toString().toLowerCase());
      ZabState zabState = this.getZabState();
      if (!QuorumPeer.ZabState.ELECTION.equals(zabState)) {
         sb.append(" - ").append(zabState.toString().toLowerCase());
      }

      SyncMode syncMode = this.getSyncMode();
      if (!QuorumPeer.SyncMode.NONE.equals(syncMode)) {
         sb.append(" - ").append(syncMode.toString().toLowerCase());
      }

      return sb.toString();
   }

   public synchronized void reconfigFlagSet() {
      this.reconfigFlag = true;
   }

   public synchronized void reconfigFlagClear() {
      this.reconfigFlag = false;
   }

   public synchronized boolean isReconfigStateChange() {
      return this.reconfigFlag;
   }

   public synchronized ServerState getPeerState() {
      return this.state;
   }

   public void recreateSocketAddresses(long id) {
      QuorumVerifier qv = this.getQuorumVerifier();
      if (qv != null) {
         QuorumServer qs = (QuorumServer)qv.getAllMembers().get(id);
         if (qs != null) {
            qs.recreateSocketAddresses();
            if (id == this.getMyId()) {
               this.setAddrs(qs.addr, qs.electionAddr, qs.clientAddr);
            }
         }
      }

      qv = this.getLastSeenQuorumVerifier();
      if (qv != null) {
         QuorumServer qs = (QuorumServer)qv.getAllMembers().get(id);
         if (qs != null) {
            qs.recreateSocketAddresses();
         }
      }

   }

   private AddressTuple getAddrs() {
      AddressTuple addrs = (AddressTuple)this.myAddrs.get();
      if (addrs != null) {
         return addrs;
      } else {
         try {
            synchronized(this.QV_LOCK) {
               for(addrs = (AddressTuple)this.myAddrs.get(); addrs == null; addrs = (AddressTuple)this.myAddrs.get()) {
                  this.QV_LOCK.wait();
               }

               return addrs;
            }
         } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
         }
      }
   }

   public MultipleAddresses getQuorumAddress() {
      return this.getAddrs().quorumAddr;
   }

   public MultipleAddresses getElectionAddress() {
      return this.getAddrs().electionAddr;
   }

   public InetSocketAddress getClientAddress() {
      AddressTuple addrs = (AddressTuple)this.myAddrs.get();
      return addrs == null ? null : addrs.clientAddr;
   }

   private void setAddrs(MultipleAddresses quorumAddr, MultipleAddresses electionAddr, InetSocketAddress clientAddr) {
      synchronized(this.QV_LOCK) {
         this.myAddrs.set(new AddressTuple(quorumAddr, electionAddr, clientAddr));
         this.QV_LOCK.notifyAll();
      }
   }

   public static QuorumPeer testingQuorumPeer() throws SaslException {
      return new QuorumPeer();
   }

   public QuorumPeer() throws SaslException {
      super("QuorumPeer");
      this.qcmRef = new AtomicReference();
      this.suspended = new AtomicBoolean(false);
      this.multiAddressEnabled = true;
      this.multiAddressReachabilityCheckTimeoutMs = (int)MultipleAddresses.DEFAULT_TIMEOUT.toMillis();
      this.multiAddressReachabilityCheckEnabled = true;
      this.learnerType = QuorumPeer.LearnerType.PARTICIPANT;
      this.configFilename = null;
      this.lastSeenQuorumVerifier = null;
      this.QV_LOCK = new Object();
      this.running = true;
      this.localSessionsEnabled = false;
      this.localSessionsUpgradingEnabled = true;
      this.minSessionTimeout = -1;
      this.maxSessionTimeout = -1;
      this.clientPortListenBacklog = -1;
      this.syncEnabled = true;
      this.tick = new AtomicInteger();
      this.quorumListenOnAllIPs = false;
      this.electionTimeTaken = -1L;
      this.quorumCnxnThreadsSize = 20;
      this.state = QuorumPeer.ServerState.LOOKING;
      this.zabState = new AtomicReference(QuorumPeer.ZabState.ELECTION);
      this.syncMode = new AtomicReference(QuorumPeer.SyncMode.NONE);
      this.leaderAddress = new AtomicReference("");
      this.leaderId = new AtomicLong(-1L);
      this.reconfigFlag = false;
      this.myAddrs = new AtomicReference();
      this.logFactory = null;
      this.shuttingDownLE = false;
      this.acceptedEpoch = -1L;
      this.currentEpoch = -1L;
      this.observerMasters = new ArrayList();
      this.nextObserverMaster = 0;
      this.quorumStats = new QuorumStats(this);
      this.jmxRemotePeerBean = new HashMap();
      this.adminServer = AdminServerFactory.createAdminServer();
      this.x509Util = this.createX509Util();
      this.initialize();
      this.reconfigEnabled = QuorumPeerConfig.isReconfigEnabled();
   }

   QuorumX509Util createX509Util() {
      return new QuorumX509Util();
   }

   public QuorumPeer(Map quorumPeers, File dataDir, File dataLogDir, int electionType, long myid, int tickTime, int initLimit, int syncLimit, int connectToLearnerMasterLimit, ServerCnxnFactory cnxnFactory) throws IOException {
      this(quorumPeers, dataDir, dataLogDir, electionType, myid, tickTime, initLimit, syncLimit, connectToLearnerMasterLimit, false, cnxnFactory, new QuorumMaj(quorumPeers));
   }

   public QuorumPeer(Map quorumPeers, File dataDir, File dataLogDir, int electionType, long myid, int tickTime, int initLimit, int syncLimit, int connectToLearnerMasterLimit, boolean quorumListenOnAllIPs, ServerCnxnFactory cnxnFactory, QuorumVerifier quorumConfig) throws IOException {
      this();
      this.cnxnFactory = cnxnFactory;
      this.electionType = electionType;
      this.myid = myid;
      this.tickTime = tickTime;
      this.initLimit = initLimit;
      this.syncLimit = syncLimit;
      this.connectToLearnerMasterLimit = connectToLearnerMasterLimit;
      this.quorumListenOnAllIPs = quorumListenOnAllIPs;
      this.logFactory = new FileTxnSnapLog(dataLogDir, dataDir);
      this.zkDb = new ZKDatabase(this.logFactory);
      if (quorumConfig == null) {
         quorumConfig = new QuorumMaj(quorumPeers);
      }

      this.setQuorumVerifier(quorumConfig, false);
      this.adminServer = AdminServerFactory.createAdminServer();
   }

   public void initialize() throws SaslException {
      if (this.isQuorumSaslAuthEnabled()) {
         Set<String> authzHosts = new HashSet();

         for(QuorumServer qs : this.getView().values()) {
            authzHosts.add(qs.hostname);
         }

         this.authServer = new SaslQuorumAuthServer(this.isQuorumServerSaslAuthRequired(), this.quorumServerLoginContext, authzHosts);
         this.authLearner = new SaslQuorumAuthLearner(this.isQuorumLearnerSaslAuthRequired(), this.quorumServicePrincipal, this.quorumLearnerLoginContext);
      } else {
         this.authServer = new NullQuorumAuthServer();
         this.authLearner = new NullQuorumAuthLearner();
      }

   }

   QuorumStats quorumStats() {
      return this.quorumStats;
   }

   public synchronized void start() {
      if (!this.getView().containsKey(this.myid)) {
         throw new RuntimeException("My id " + this.myid + " not in the peer list");
      } else {
         this.loadDataBase();
         this.startServerCnxnFactory();

         try {
            this.adminServer.start();
         } catch (AdminServer.AdminServerException e) {
            LOG.warn("Problem starting AdminServer", e);
         }

         this.startLeaderElection();
         this.startJvmPauseMonitor();
         super.start();
      }
   }

   private void loadDataBase() {
      try {
         this.zkDb.loadDataBase();
         long lastProcessedZxid = this.zkDb.getDataTree().lastProcessedZxid;
         long epochOfZxid = ZxidUtils.getEpochFromZxid(lastProcessedZxid);

         try {
            this.currentEpoch = this.readLongFromFile("currentEpoch");
         } catch (FileNotFoundException var9) {
            this.currentEpoch = epochOfZxid;
            LOG.info("{} not found! Creating with a reasonable default of {}. This should only happen when you are upgrading your installation", "currentEpoch", this.currentEpoch);
            this.writeLongToFile("currentEpoch", this.currentEpoch);
         }

         if (epochOfZxid > this.currentEpoch) {
            File currentTmp = new File(this.getTxnFactory().getSnapDir(), "currentEpoch.tmp");
            if (!currentTmp.exists()) {
               throw new IOException("The current epoch, " + ZxidUtils.zxidToString(this.currentEpoch) + ", is older than the last zxid, " + lastProcessedZxid);
            }

            long epochOfTmp = this.readLongFromFile(currentTmp.getName());
            LOG.info("{} found. Setting current epoch to {}.", currentTmp, epochOfTmp);
            this.setCurrentEpoch(epochOfTmp);
         }

         try {
            this.acceptedEpoch = this.readLongFromFile("acceptedEpoch");
         } catch (FileNotFoundException var8) {
            this.acceptedEpoch = epochOfZxid;
            LOG.info("{} not found! Creating with a reasonable default of {}. This should only happen when you are upgrading your installation", "acceptedEpoch", this.acceptedEpoch);
            this.writeLongToFile("acceptedEpoch", this.acceptedEpoch);
         }

         if (this.acceptedEpoch < this.currentEpoch) {
            throw new IOException("The accepted epoch, " + ZxidUtils.zxidToString(this.acceptedEpoch) + " is less than the current epoch, " + ZxidUtils.zxidToString(this.currentEpoch));
         }
      } catch (IOException ie) {
         LOG.error("Unable to load database on disk", ie);
         throw new RuntimeException("Unable to run quorum server ", ie);
      }
   }

   public synchronized void stopLeaderElection() {
      this.responder.running = false;
      this.responder.interrupt();
   }

   public synchronized void startLeaderElection() {
      try {
         if (this.getPeerState() == QuorumPeer.ServerState.LOOKING) {
            this.currentVote = new Vote(this.myid, this.getLastLoggedZxid(), this.getCurrentEpoch());
         }
      } catch (IOException e) {
         RuntimeException re = new RuntimeException(e.getMessage());
         re.setStackTrace(e.getStackTrace());
         throw re;
      }

      this.electionAlg = this.createElectionAlgorithm(this.electionType);
   }

   private void startJvmPauseMonitor() {
      if (this.jvmPauseMonitor != null) {
         this.jvmPauseMonitor.serviceStart();
      }

   }

   protected static int countParticipants(Map peers) {
      int count = 0;

      for(QuorumServer q : peers.values()) {
         if (q.type == QuorumPeer.LearnerType.PARTICIPANT) {
            ++count;
         }
      }

      return count;
   }

   public QuorumPeer(Map quorumPeers, File snapDir, File logDir, int clientPort, int electionAlg, long myid, int tickTime, int initLimit, int syncLimit, int connectToLearnerMasterLimit) throws IOException {
      this(quorumPeers, snapDir, logDir, electionAlg, myid, tickTime, initLimit, syncLimit, connectToLearnerMasterLimit, false, ServerCnxnFactory.createFactory(getClientAddress(quorumPeers, myid, clientPort), -1), new QuorumMaj(quorumPeers));
   }

   public QuorumPeer(Map quorumPeers, File snapDir, File logDir, int clientPort, int electionAlg, long myid, int tickTime, int initLimit, int syncLimit, int connectToLearnerMasterLimit, String oraclePath) throws IOException {
      this(quorumPeers, snapDir, logDir, electionAlg, myid, tickTime, initLimit, syncLimit, connectToLearnerMasterLimit, false, ServerCnxnFactory.createFactory(getClientAddress(quorumPeers, myid, clientPort), -1), new QuorumOracleMaj(quorumPeers, oraclePath));
   }

   public QuorumPeer(Map quorumPeers, File snapDir, File logDir, int clientPort, int electionAlg, long myid, int tickTime, int initLimit, int syncLimit, int connectToLearnerMasterLimit, QuorumVerifier quorumConfig) throws IOException {
      this(quorumPeers, snapDir, logDir, electionAlg, myid, tickTime, initLimit, syncLimit, connectToLearnerMasterLimit, false, ServerCnxnFactory.createFactory(getClientAddress(quorumPeers, myid, clientPort), -1), quorumConfig);
   }

   private static InetSocketAddress getClientAddress(Map quorumPeers, long myid, int clientPort) throws IOException {
      QuorumServer quorumServer = (QuorumServer)quorumPeers.get(myid);
      if (null == quorumServer) {
         throw new IOException("No QuorumServer correspoding to myid " + myid);
      } else if (null == quorumServer.clientAddr) {
         return new InetSocketAddress(clientPort);
      } else if (quorumServer.clientAddr.getPort() != clientPort) {
         throw new IOException("QuorumServer port " + quorumServer.clientAddr.getPort() + " does not match with given port " + clientPort);
      } else {
         return quorumServer.clientAddr;
      }
   }

   public long getLastLoggedZxid() {
      if (!this.zkDb.isInitialized()) {
         this.loadDataBase();
      }

      return this.zkDb.getDataTreeLastProcessedZxid();
   }

   protected Follower makeFollower(FileTxnSnapLog logFactory) throws IOException {
      return new Follower(this, new FollowerZooKeeperServer(logFactory, this, this.zkDb));
   }

   protected Leader makeLeader(FileTxnSnapLog logFactory) throws IOException, X509Exception {
      return new Leader(this, new LeaderZooKeeperServer(logFactory, this, this.zkDb));
   }

   protected Observer makeObserver(FileTxnSnapLog logFactory) throws IOException {
      return new Observer(this, new ObserverZooKeeperServer(logFactory, this, this.zkDb));
   }

   protected Election createElectionAlgorithm(int electionAlgorithm) {
      Election le = null;
      switch (electionAlgorithm) {
         case 1:
            throw new UnsupportedOperationException("Election Algorithm 1 is not supported.");
         case 2:
            throw new UnsupportedOperationException("Election Algorithm 2 is not supported.");
         case 3:
            QuorumCnxManager qcm = this.createCnxnManager();
            QuorumCnxManager oldQcm = (QuorumCnxManager)this.qcmRef.getAndSet(qcm);
            if (oldQcm != null) {
               LOG.warn("Clobbering already-set QuorumCnxManager (restarting leader election?)");
               oldQcm.halt();
            }

            QuorumCnxManager.Listener listener = qcm.listener;
            if (listener != null) {
               listener.start();
               FastLeaderElection fle = new FastLeaderElection(this, qcm);
               fle.start();
               le = fle;
            } else {
               LOG.error("Null listener when initializing cnx manager");
            }
            break;
         default:
            assert false;
      }

      return le;
   }

   protected Election makeLEStrategy() {
      LOG.debug("Initializing leader election protocol...");
      return this.electionAlg;
   }

   protected synchronized void setLeader(Leader newLeader) {
      this.leader = newLeader;
   }

   protected synchronized void setFollower(Follower newFollower) {
      this.follower = newFollower;
   }

   protected synchronized void setObserver(Observer newObserver) {
      this.observer = newObserver;
   }

   public synchronized ZooKeeperServer getActiveServer() {
      if (this.leader != null) {
         return this.leader.zk;
      } else if (this.follower != null) {
         return this.follower.zk;
      } else {
         return this.observer != null ? this.observer.zk : null;
      }
   }

   public void setSuspended(boolean suspended) {
      this.suspended.set(suspended);
   }

   private void checkSuspended() {
      try {
         while(this.suspended.get()) {
            Thread.sleep(10L);
         }
      } catch (InterruptedException var2) {
         Thread.currentThread().interrupt();
      }

   }

   public void run() {
      this.updateThreadName();
      LOG.debug("Starting quorum peer");

      try {
         this.jmxQuorumBean = new QuorumBean(this);
         MBeanRegistry.getInstance().register(this.jmxQuorumBean, (ZKMBeanInfo)null);

         for(QuorumServer s : this.getView().values()) {
            if (this.getMyId() == s.id) {
               ZKMBeanInfo p = this.jmxLocalPeerBean = new LocalPeerBean(this);

               try {
                  MBeanRegistry.getInstance().register(p, this.jmxQuorumBean);
               } catch (Exception e) {
                  LOG.warn("Failed to register with JMX", e);
                  this.jmxLocalPeerBean = null;
               }
            } else {
               RemotePeerBean rBean = new RemotePeerBean(this, s);

               try {
                  MBeanRegistry.getInstance().register(rBean, this.jmxQuorumBean);
                  this.jmxRemotePeerBean.put(s.id, rBean);
               } catch (Exception e) {
                  LOG.warn("Failed to register with JMX", e);
               }
            }
         }
      } catch (Exception e) {
         LOG.warn("Failed to register with JMX", e);
         this.jmxQuorumBean = null;
      }

      try {
         while(this.running) {
            if (this.unavailableStartTime == 0L) {
               this.unavailableStartTime = Time.currentElapsedTime();
            }

            switch (this.getPeerState()) {
               case LOOKING:
                  LOG.info("LOOKING");
                  ServerMetrics.getMetrics().LOOKING_COUNT.add(1L);
                  if (Boolean.getBoolean("readonlymode.enabled")) {
                     LOG.info("Attempting to start ReadOnlyZooKeeperServer");
                     final ReadOnlyZooKeeperServer roZk = new ReadOnlyZooKeeperServer(this.logFactory, this, this.zkDb);
                     Thread roZkMgr = new Thread() {
                        public void run() {
                           try {
                              sleep((long)Math.max(2000, QuorumPeer.this.tickTime));
                              if (QuorumPeer.ServerState.LOOKING.equals(QuorumPeer.this.getPeerState())) {
                                 roZk.startup();
                              }
                           } catch (InterruptedException var2) {
                              QuorumPeer.LOG.info("Interrupted while attempting to start ReadOnlyZooKeeperServer, not started");
                           } catch (Exception e) {
                              QuorumPeer.LOG.error("FAILED to start ReadOnlyZooKeeperServer", e);
                           }

                        }
                     };

                     try {
                        roZkMgr.start();
                        this.reconfigFlagClear();
                        if (this.shuttingDownLE) {
                           this.shuttingDownLE = false;
                           this.startLeaderElection();
                        }

                        this.setCurrentVote(this.makeLEStrategy().lookForLeader());
                        this.checkSuspended();
                     } catch (Exception e) {
                        LOG.warn("Unexpected exception", e);
                        this.setPeerState(QuorumPeer.ServerState.LOOKING);
                     } finally {
                        roZkMgr.interrupt();
                        roZk.shutdown();
                     }
                  } else {
                     try {
                        this.reconfigFlagClear();
                        if (this.shuttingDownLE) {
                           this.shuttingDownLE = false;
                           this.startLeaderElection();
                        }

                        this.setCurrentVote(this.makeLEStrategy().lookForLeader());
                     } catch (Exception e) {
                        LOG.warn("Unexpected exception", e);
                        this.setPeerState(QuorumPeer.ServerState.LOOKING);
                     }
                  }
                  break;
               case LEADING:
                  LOG.info("LEADING");

                  try {
                     try {
                        this.setLeader(this.makeLeader(this.logFactory));
                        this.leader.lead();
                        this.setLeader((Leader)null);
                     } catch (Exception e) {
                        LOG.warn("Unexpected exception", e);
                     }
                     break;
                  } finally {
                     if (this.leader != null) {
                        this.leader.shutdown("Forcing shutdown");
                        this.setLeader((Leader)null);
                     }

                     this.updateServerState();
                  }
               case FOLLOWING:
                  try {
                     try {
                        LOG.info("FOLLOWING");
                        this.setFollower(this.makeFollower(this.logFactory));
                        this.follower.followLeader();
                     } catch (Exception e) {
                        LOG.warn("Unexpected exception", e);
                     }
                     break;
                  } finally {
                     this.follower.shutdown();
                     this.setFollower((Follower)null);
                     this.updateServerState();
                  }
               case OBSERVING:
                  try {
                     LOG.info("OBSERVING");
                     this.setObserver(this.makeObserver(this.logFactory));
                     this.observer.observeLeader();
                  } catch (Exception e) {
                     LOG.warn("Unexpected exception", e);
                  } finally {
                     this.observer.shutdown();
                     this.setObserver((Observer)null);
                     this.updateServerState();
                     if (this.isRunning()) {
                        Observer.waitForObserverElectionDelay();
                     }

                  }
            }
         }
      } finally {
         LOG.warn("QuorumPeer main thread exited");
         MBeanRegistry instance = MBeanRegistry.getInstance();
         instance.unregister(this.jmxQuorumBean);
         instance.unregister(this.jmxLocalPeerBean);

         for(RemotePeerBean remotePeerBean : this.jmxRemotePeerBean.values()) {
            instance.unregister(remotePeerBean);
         }

         this.jmxQuorumBean = null;
         this.jmxLocalPeerBean = null;
         this.jmxRemotePeerBean = null;
      }

   }

   private synchronized void updateServerState() {
      if (!this.reconfigFlag) {
         this.setPeerState(QuorumPeer.ServerState.LOOKING);
         LOG.warn("PeerState set to LOOKING");
      } else {
         if (this.getMyId() == this.getCurrentVote().getId()) {
            this.setPeerState(QuorumPeer.ServerState.LEADING);
            LOG.debug("PeerState set to LEADING");
         } else if (this.getLearnerType() == QuorumPeer.LearnerType.PARTICIPANT) {
            this.setPeerState(QuorumPeer.ServerState.FOLLOWING);
            LOG.debug("PeerState set to FOLLOWING");
         } else if (this.getLearnerType() == QuorumPeer.LearnerType.OBSERVER) {
            this.setPeerState(QuorumPeer.ServerState.OBSERVING);
            LOG.debug("PeerState set to OBSERVER");
         } else {
            this.setPeerState(QuorumPeer.ServerState.LOOKING);
            LOG.debug("Should not be here");
         }

         this.reconfigFlag = false;
      }
   }

   public void shutdown() {
      this.running = false;
      this.x509Util.close();
      if (this.leader != null) {
         this.leader.shutdown("quorum Peer shutdown");
      }

      if (this.follower != null) {
         this.follower.shutdown();
      }

      this.shutdownServerCnxnFactory();
      if (this.udpSocket != null) {
         this.udpSocket.close();
      }

      if (this.jvmPauseMonitor != null) {
         this.jvmPauseMonitor.serviceStop();
      }

      try {
         this.adminServer.shutdown();
      } catch (AdminServer.AdminServerException e) {
         LOG.warn("Problem stopping AdminServer", e);
      }

      if (this.getElectionAlg() != null) {
         this.interrupt();
         this.getElectionAlg().shutdown();
      }

      try {
         this.zkDb.close();
      } catch (IOException ie) {
         LOG.warn("Error closing logs ", ie);
      }

   }

   public Map getView() {
      return Collections.unmodifiableMap(this.getQuorumVerifier().getAllMembers());
   }

   public Map getVotingView() {
      return this.getQuorumVerifier().getVotingMembers();
   }

   public Map getObservingView() {
      return this.getQuorumVerifier().getObservingMembers();
   }

   public synchronized Set getCurrentAndNextConfigVoters() {
      Set<Long> voterIds = new HashSet(this.getQuorumVerifier().getVotingMembers().keySet());
      if (this.getLastSeenQuorumVerifier() != null) {
         voterIds.addAll(this.getLastSeenQuorumVerifier().getVotingMembers().keySet());
      }

      return voterIds;
   }

   public boolean viewContains(Long sid) {
      return this.getView().containsKey(sid);
   }

   public String[] getQuorumPeers() {
      List<String> l = new ArrayList();
      synchronized(this) {
         if (this.leader != null) {
            for(LearnerHandler fh : this.leader.getLearners()) {
               if (fh.getSocket() != null) {
                  String s = NetUtils.formatInetAddr((InetSocketAddress)fh.getSocket().getRemoteSocketAddress());
                  if (this.leader.isLearnerSynced(fh)) {
                     s = s + "*";
                  }

                  l.add(s);
               }
            }
         } else if (this.follower != null) {
            l.add(NetUtils.formatInetAddr((InetSocketAddress)this.follower.sock.getRemoteSocketAddress()));
         }
      }

      return (String[])l.toArray(new String[0]);
   }

   public String getServerState() {
      switch (this.getPeerState()) {
         case LOOKING:
            return "leaderelection";
         case LEADING:
            return "leading";
         case FOLLOWING:
            return "following";
         case OBSERVING:
            return "observing";
         default:
            return "unknown";
      }
   }

   public void setMyid(long myid) {
      this.myid = myid;
   }

   public void setInitialConfig(String initialConfig) {
      this.initialConfig = initialConfig;
   }

   public String getInitialConfig() {
      return this.initialConfig;
   }

   public int getTickTime() {
      return this.tickTime;
   }

   public void setTickTime(int tickTime) {
      LOG.info("tickTime set to {}", tickTime);
      this.tickTime = tickTime;
   }

   public int getMaxClientCnxnsPerHost() {
      if (this.cnxnFactory != null) {
         return this.cnxnFactory.getMaxClientCnxnsPerHost();
      } else {
         return this.secureCnxnFactory != null ? this.secureCnxnFactory.getMaxClientCnxnsPerHost() : -1;
      }
   }

   public boolean areLocalSessionsEnabled() {
      return this.localSessionsEnabled;
   }

   public void enableLocalSessions(boolean flag) {
      LOG.info("Local sessions {}", flag ? "enabled" : "disabled");
      this.localSessionsEnabled = flag;
   }

   public boolean isLocalSessionsUpgradingEnabled() {
      return this.localSessionsUpgradingEnabled;
   }

   public void enableLocalSessionsUpgrading(boolean flag) {
      LOG.info("Local session upgrading {}", flag ? "enabled" : "disabled");
      this.localSessionsUpgradingEnabled = flag;
   }

   public int getMinSessionTimeout() {
      return this.minSessionTimeout;
   }

   public void setMinSessionTimeout(int min) {
      LOG.info("minSessionTimeout set to {}", min);
      this.minSessionTimeout = min;
   }

   public int getMaxSessionTimeout() {
      return this.maxSessionTimeout;
   }

   public void setMaxSessionTimeout(int max) {
      LOG.info("maxSessionTimeout set to {}", max);
      this.maxSessionTimeout = max;
   }

   public int getClientPortListenBacklog() {
      return this.clientPortListenBacklog;
   }

   public void setClientPortListenBacklog(int backlog) {
      this.clientPortListenBacklog = backlog;
   }

   public int getInitLimit() {
      return this.initLimit;
   }

   public void setInitLimit(int initLimit) {
      LOG.info("initLimit set to {}", initLimit);
      this.initLimit = initLimit;
   }

   public int getTick() {
      return this.tick.get();
   }

   public QuorumVerifier configFromString(String s) throws IOException, QuorumPeerConfig.ConfigException {
      Properties props = new Properties();
      props.load(new StringReader(s));
      return QuorumPeerConfig.parseDynamicConfig(props, this.electionType, false, false, this.getQuorumVerifier().getOraclePath());
   }

   public QuorumVerifier getQuorumVerifier() {
      synchronized(this.QV_LOCK) {
         return this.quorumVerifier;
      }
   }

   public QuorumVerifier getLastSeenQuorumVerifier() {
      synchronized(this.QV_LOCK) {
         return this.lastSeenQuorumVerifier;
      }
   }

   public synchronized void restartLeaderElection(QuorumVerifier qvOLD, QuorumVerifier qvNEW) {
      if (qvOLD == null || !qvOLD.equals(qvNEW)) {
         LOG.warn("Restarting Leader Election");
         this.getElectionAlg().shutdown();
         this.shuttingDownLE = false;
         this.startLeaderElection();
      }

   }

   public String getNextDynamicConfigFilename() {
      if (this.configFilename == null) {
         LOG.warn("configFilename is null! This should only happen in tests.");
         return null;
      } else {
         return this.configFilename + ".dynamic.next";
      }
   }

   private void connectNewPeers(QuorumCnxManager qcm) {
      if (this.quorumVerifier != null && this.lastSeenQuorumVerifier != null) {
         Map<Long, QuorumServer> committedView = this.quorumVerifier.getAllMembers();

         for(Map.Entry e : this.lastSeenQuorumVerifier.getAllMembers().entrySet()) {
            if ((Long)e.getKey() != this.getMyId() && !committedView.containsKey(e.getKey())) {
               qcm.connectOne((Long)e.getKey());
            }
         }
      }

   }

   public void setLastSeenQuorumVerifier(QuorumVerifier qv, boolean writeToDisk) {
      if (!this.isReconfigEnabled()) {
         LOG.info("Dynamic reconfig is disabled, we don't store the last seen config.");
      } else {
         QuorumCnxManager qcm = (QuorumCnxManager)this.qcmRef.get();
         Object outerLockObject = qcm != null ? qcm : this.QV_LOCK;
         synchronized(outerLockObject) {
            synchronized(this.QV_LOCK) {
               if (this.lastSeenQuorumVerifier != null && this.lastSeenQuorumVerifier.getVersion() > qv.getVersion()) {
                  LOG.error("setLastSeenQuorumVerifier called with stale config " + qv.getVersion() + ". Current version: " + this.quorumVerifier.getVersion());
               }

               if (this.lastSeenQuorumVerifier != null && this.lastSeenQuorumVerifier.getVersion() == qv.getVersion()) {
                  return;
               }

               this.lastSeenQuorumVerifier = qv;
               if (qcm != null) {
                  this.connectNewPeers(qcm);
               }

               if (writeToDisk) {
                  try {
                     String fileName = this.getNextDynamicConfigFilename();
                     if (fileName != null) {
                        QuorumPeerConfig.writeDynamicConfig(fileName, qv, true);
                     }
                  } catch (IOException e) {
                     LOG.error("Error writing next dynamic config file to disk", e);
                  }
               }
            }

         }
      }
   }

   public QuorumVerifier setQuorumVerifier(QuorumVerifier qv, boolean writeToDisk) {
      synchronized(this.QV_LOCK) {
         if (this.quorumVerifier != null && this.quorumVerifier.getVersion() >= qv.getVersion()) {
            LOG.debug("{} setQuorumVerifier called with known or old config {}. Current version: {}", new Object[]{this.getMyId(), qv.getVersion(), this.quorumVerifier.getVersion()});
            return this.quorumVerifier;
         } else {
            QuorumVerifier prevQV = this.quorumVerifier;
            this.quorumVerifier = qv;
            if (this.lastSeenQuorumVerifier == null || qv.getVersion() > this.lastSeenQuorumVerifier.getVersion()) {
               this.lastSeenQuorumVerifier = qv;
            }

            if (writeToDisk) {
               if (this.configFilename != null) {
                  try {
                     String dynamicConfigFilename = this.makeDynamicConfigFilename(qv.getVersion());
                     QuorumPeerConfig.writeDynamicConfig(dynamicConfigFilename, qv, false);
                     QuorumPeerConfig.editStaticConfig(this.configFilename, dynamicConfigFilename, this.needEraseClientInfoFromStaticConfig());
                  } catch (IOException e) {
                     LOG.error("Error closing file", e);
                  }
               } else {
                  LOG.info("writeToDisk == true but configFilename == null");
               }
            }

            if (qv.getVersion() == this.lastSeenQuorumVerifier.getVersion()) {
               QuorumPeerConfig.deleteFile(this.getNextDynamicConfigFilename());
            }

            QuorumServer qs = (QuorumServer)qv.getAllMembers().get(this.getMyId());
            if (qs != null) {
               this.setAddrs(qs.addr, qs.electionAddr, qs.clientAddr);
            }

            this.updateObserverMasterList();
            return prevQV;
         }
      }
   }

   private String makeDynamicConfigFilename(long version) {
      return this.configFilename + ".dynamic." + Long.toHexString(version);
   }

   private boolean needEraseClientInfoFromStaticConfig() {
      QuorumServer server = (QuorumServer)this.quorumVerifier.getAllMembers().get(this.getMyId());
      return server != null && server.clientAddr != null && !server.isClientAddrFromStatic;
   }

   public Election getElectionAlg() {
      return this.electionAlg;
   }

   public int getSyncLimit() {
      return this.syncLimit;
   }

   public void setSyncLimit(int syncLimit) {
      LOG.info("syncLimit set to {}", syncLimit);
      this.syncLimit = syncLimit;
   }

   public int getConnectToLearnerMasterLimit() {
      return this.connectToLearnerMasterLimit;
   }

   public void setConnectToLearnerMasterLimit(int connectToLearnerMasterLimit) {
      LOG.info("connectToLearnerMasterLimit set to {}", connectToLearnerMasterLimit);
      this.connectToLearnerMasterLimit = connectToLearnerMasterLimit;
   }

   public boolean getSyncEnabled() {
      if (System.getProperty("zookeeper.observer.syncEnabled") != null) {
         LOG.info("{}={}", "zookeeper.observer.syncEnabled", Boolean.getBoolean("zookeeper.observer.syncEnabled"));
         return Boolean.getBoolean("zookeeper.observer.syncEnabled");
      } else {
         return this.syncEnabled;
      }
   }

   public void setSyncEnabled(boolean syncEnabled) {
      this.syncEnabled = syncEnabled;
   }

   public int getElectionType() {
      return this.electionType;
   }

   public void setElectionType(int electionType) {
      this.electionType = electionType;
   }

   public boolean getQuorumListenOnAllIPs() {
      return this.quorumListenOnAllIPs;
   }

   public void setQuorumListenOnAllIPs(boolean quorumListenOnAllIPs) {
      this.quorumListenOnAllIPs = quorumListenOnAllIPs;
   }

   public void setCnxnFactory(ServerCnxnFactory cnxnFactory) {
      this.cnxnFactory = cnxnFactory;
   }

   public void setSecureCnxnFactory(ServerCnxnFactory secureCnxnFactory) {
      this.secureCnxnFactory = secureCnxnFactory;
   }

   public void setSslQuorum(boolean sslQuorum) {
      if (sslQuorum) {
         LOG.info("Using TLS encrypted quorum communication");
      } else {
         LOG.info("Using insecure (non-TLS) quorum communication");
      }

      this.sslQuorum = sslQuorum;
   }

   public void setUsePortUnification(boolean shouldUsePortUnification) {
      LOG.info("Port unification {}", shouldUsePortUnification ? "enabled" : "disabled");
      this.shouldUsePortUnification = shouldUsePortUnification;
   }

   private void startServerCnxnFactory() {
      if (this.cnxnFactory != null) {
         this.cnxnFactory.start();
      }

      if (this.secureCnxnFactory != null) {
         this.secureCnxnFactory.start();
      }

   }

   private void shutdownServerCnxnFactory() {
      if (this.cnxnFactory != null) {
         this.cnxnFactory.shutdown();
      }

      if (this.secureCnxnFactory != null) {
         this.secureCnxnFactory.shutdown();
      }

   }

   public void setZooKeeperServer(ZooKeeperServer zks) {
      if (this.cnxnFactory != null) {
         this.cnxnFactory.setZooKeeperServer(zks);
      }

      if (this.secureCnxnFactory != null) {
         this.secureCnxnFactory.setZooKeeperServer(zks);
      }

   }

   public void closeAllConnections() {
      if (this.cnxnFactory != null) {
         this.cnxnFactory.closeAll(ServerCnxn.DisconnectReason.SERVER_SHUTDOWN);
      }

      if (this.secureCnxnFactory != null) {
         this.secureCnxnFactory.closeAll(ServerCnxn.DisconnectReason.SERVER_SHUTDOWN);
      }

   }

   public int getClientPort() {
      return this.cnxnFactory != null ? this.cnxnFactory.getLocalPort() : -1;
   }

   public int getSecureClientPort() {
      return this.secureCnxnFactory != null ? this.secureCnxnFactory.getLocalPort() : -1;
   }

   public void setTxnFactory(FileTxnSnapLog factory) {
      this.logFactory = factory;
   }

   public FileTxnSnapLog getTxnFactory() {
      return this.logFactory;
   }

   public void setZKDatabase(ZKDatabase database) {
      this.zkDb = database;
   }

   protected ZKDatabase getZkDb() {
      return this.zkDb;
   }

   public synchronized void initConfigInZKDatabase() {
      if (this.zkDb != null) {
         this.zkDb.initConfigInZKDatabase(this.getQuorumVerifier());
      }

   }

   public boolean isRunning() {
      return this.running;
   }

   public QuorumCnxManager getQuorumCnxManager() {
      return (QuorumCnxManager)this.qcmRef.get();
   }

   private long readLongFromFile(String name) throws IOException {
      File file = new File(this.logFactory.getSnapDir(), name);
      BufferedReader br = new BufferedReader(new FileReader(file));
      String line = "";

      long var5;
      try {
         line = br.readLine();
         var5 = Long.parseLong(line);
      } catch (NumberFormatException var10) {
         throw new IOException("Found " + line + " in " + file);
      } finally {
         br.close();
      }

      return var5;
   }

   void writeLongToFile(String name, final long value) throws IOException {
      File file = new File(this.logFactory.getSnapDir(), name);
      new AtomicFileWritingIdiom(file, new AtomicFileWritingIdiom.WriterStatement() {
         public void write(Writer bw) throws IOException {
            bw.write(Long.toString(value));
         }
      });
   }

   public long getCurrentEpoch() throws IOException {
      if (this.currentEpoch == -1L) {
         this.currentEpoch = this.readLongFromFile("currentEpoch");
      }

      return this.currentEpoch;
   }

   public long getAcceptedEpoch() throws IOException {
      if (this.acceptedEpoch == -1L) {
         this.acceptedEpoch = this.readLongFromFile("acceptedEpoch");
      }

      return this.acceptedEpoch;
   }

   public void setCurrentEpoch(long e) throws IOException {
      this.writeLongToFile("currentEpoch", e);
      this.currentEpoch = e;
   }

   public void setAcceptedEpoch(long e) throws IOException {
      this.writeLongToFile("acceptedEpoch", e);
      this.acceptedEpoch = e;
   }

   public boolean processReconfig(QuorumVerifier qv, Long suggestedLeaderId, Long zxid, boolean restartLE) {
      if (!this.isReconfigEnabled()) {
         LOG.debug("Reconfig feature is disabled, skip reconfig processing.");
         return false;
      } else {
         InetSocketAddress oldClientAddr = this.getClientAddress();
         QuorumVerifier prevQV = this.setQuorumVerifier(qv, true);
         this.initConfigInZKDatabase();
         if (prevQV.getVersion() < qv.getVersion() && !prevQV.equals(qv)) {
            Map<Long, QuorumServer> newMembers = qv.getAllMembers();
            this.updateRemotePeerMXBeans(newMembers);
            if (restartLE) {
               this.restartLeaderElection(prevQV, qv);
            }

            QuorumServer myNewQS = (QuorumServer)newMembers.get(this.getMyId());
            if (myNewQS != null && myNewQS.clientAddr != null && !myNewQS.clientAddr.equals(oldClientAddr)) {
               this.cnxnFactory.reconfigure(myNewQS.clientAddr);
               this.updateThreadName();
            }

            boolean roleChange = this.updateLearnerType(qv);
            boolean leaderChange = false;
            if (suggestedLeaderId != null) {
               leaderChange = this.updateVote(suggestedLeaderId, zxid);
            } else {
               long currentLeaderId = this.getCurrentVote().getId();
               QuorumServer myleaderInCurQV = (QuorumServer)prevQV.getVotingMembers().get(currentLeaderId);
               QuorumServer myleaderInNewQV = (QuorumServer)qv.getVotingMembers().get(currentLeaderId);
               leaderChange = myleaderInCurQV == null || myleaderInCurQV.addr == null || myleaderInNewQV == null || !myleaderInCurQV.addr.equals(myleaderInNewQV.addr);
               this.reconfigFlagClear();
            }

            return roleChange || leaderChange;
         } else {
            return false;
         }
      }
   }

   private void updateRemotePeerMXBeans(Map newMembers) {
      Set<Long> existingMembers = new HashSet(newMembers.keySet());
      existingMembers.retainAll(this.jmxRemotePeerBean.keySet());

      for(Long id : existingMembers) {
         RemotePeerBean rBean = (RemotePeerBean)this.jmxRemotePeerBean.get(id);
         rBean.setQuorumServer((QuorumServer)newMembers.get(id));
      }

      Set<Long> joiningMembers = new HashSet(newMembers.keySet());
      joiningMembers.removeAll(this.jmxRemotePeerBean.keySet());
      joiningMembers.remove(this.getMyId());

      for(Long id : joiningMembers) {
         QuorumServer qs = (QuorumServer)newMembers.get(id);
         RemotePeerBean rBean = new RemotePeerBean(this, qs);

         try {
            MBeanRegistry.getInstance().register(rBean, this.jmxQuorumBean);
            this.jmxRemotePeerBean.put(qs.id, rBean);
         } catch (Exception e) {
            LOG.warn("Failed to register with JMX", e);
         }
      }

      Set<Long> leavingMembers = new HashSet(this.jmxRemotePeerBean.keySet());
      leavingMembers.removeAll(newMembers.keySet());

      for(Long id : leavingMembers) {
         RemotePeerBean rBean = (RemotePeerBean)this.jmxRemotePeerBean.remove(id);

         try {
            MBeanRegistry.getInstance().unregister(rBean);
         } catch (Exception e) {
            LOG.warn("Failed to unregister with JMX", e);
         }
      }

   }

   private void updateObserverMasterList() {
      if (this.observerMasterPort > 0) {
         this.observerMasters.clear();
         StringBuilder sb = new StringBuilder();

         for(QuorumServer server : this.quorumVerifier.getVotingMembers().values()) {
            InetAddress address = server.addr.getReachableOrOne().getAddress();
            InetSocketAddress addr = new InetSocketAddress(address, this.observerMasterPort);
            this.observerMasters.add(new QuorumServer(server.id, addr));
            sb.append(addr).append(",");
         }

         LOG.info("Updated learner master list to be {}", sb.toString());
         Collections.shuffle(this.observerMasters);
         this.nextObserverMaster = 0;
      }
   }

   private boolean useObserverMasters() {
      return this.getLearnerType() == QuorumPeer.LearnerType.OBSERVER && this.observerMasters.size() > 0;
   }

   private QuorumServer nextObserverMaster() {
      if (this.nextObserverMaster >= this.observerMasters.size()) {
         this.nextObserverMaster = 0;
         if (this.isRunning()) {
            Observer.waitForReconnectDelay();
         }
      }

      return (QuorumServer)this.observerMasters.get(this.nextObserverMaster++);
   }

   QuorumServer findLearnerMaster(QuorumServer leader) {
      if (this.useObserverMasters()) {
         return this.nextObserverMaster();
      } else {
         if (this.isRunning()) {
            Observer.waitForReconnectDelay();
         }

         return leader;
      }
   }

   QuorumServer validateLearnerMaster(String desiredMaster) {
      if (this.useObserverMasters()) {
         Long sid;
         try {
            sid = Long.parseLong(desiredMaster);
         } catch (NumberFormatException var8) {
            sid = null;
         }

         for(QuorumServer server : this.observerMasters) {
            if (sid == null) {
               for(InetSocketAddress address : server.addr.getAllAddresses()) {
                  String serverAddr = address.getAddress().getHostAddress() + ':' + address.getPort();
                  if (serverAddr.startsWith(desiredMaster)) {
                     return server;
                  }
               }
            } else if (sid.equals(server.id)) {
               return server;
            }
         }

         if (sid == null) {
            LOG.info("could not find learner master address={}", desiredMaster);
         } else {
            LOG.warn("could not find learner master sid={}", sid);
         }
      } else {
         LOG.info("cannot validate request, observer masters not enabled");
      }

      return null;
   }

   private boolean updateLearnerType(QuorumVerifier newQV) {
      if (newQV.getObservingMembers().containsKey(this.getMyId())) {
         if (this.getLearnerType() != QuorumPeer.LearnerType.OBSERVER) {
            this.setLearnerType(QuorumPeer.LearnerType.OBSERVER);
            LOG.info("Becoming an observer");
            this.reconfigFlagSet();
            return true;
         } else {
            return false;
         }
      } else if (newQV.getVotingMembers().containsKey(this.getMyId())) {
         if (this.getLearnerType() != QuorumPeer.LearnerType.PARTICIPANT) {
            this.setLearnerType(QuorumPeer.LearnerType.PARTICIPANT);
            LOG.info("Becoming a voting participant");
            this.reconfigFlagSet();
            return true;
         } else {
            return false;
         }
      } else if (this.getLearnerType() != QuorumPeer.LearnerType.PARTICIPANT) {
         this.setLearnerType(QuorumPeer.LearnerType.PARTICIPANT);
         LOG.info("Becoming a non-voting participant");
         this.reconfigFlagSet();
         return true;
      } else {
         return false;
      }
   }

   private boolean updateVote(long designatedLeader, long zxid) {
      Vote currentVote = this.getCurrentVote();
      if (currentVote != null && designatedLeader != currentVote.getId()) {
         this.setCurrentVote(new Vote(designatedLeader, zxid));
         this.reconfigFlagSet();
         LOG.warn("Suggested leader: {}", designatedLeader);
         return true;
      } else {
         return false;
      }
   }

   protected void updateElectionVote(long newEpoch) {
      Vote currentVote = this.getCurrentVote();
      if (currentVote != null) {
         this.setCurrentVote(new Vote(currentVote.getId(), currentVote.getZxid(), currentVote.getElectionEpoch(), newEpoch, currentVote.getState()));
      }

   }

   private void updateThreadName() {
      String plain = this.cnxnFactory != null ? (this.cnxnFactory.getLocalAddress() != null ? NetUtils.formatInetAddr(this.cnxnFactory.getLocalAddress()) : "disabled") : "disabled";
      String secure = this.secureCnxnFactory != null ? NetUtils.formatInetAddr(this.secureCnxnFactory.getLocalAddress()) : "disabled";
      this.setName(String.format("QuorumPeer[myid=%d](plain=%s)(secure=%s)", this.getMyId(), plain, secure));
   }

   void setElectionTimeTaken(long electionTimeTaken) {
      this.electionTimeTaken = electionTimeTaken;
   }

   long getElectionTimeTaken() {
      return this.electionTimeTaken;
   }

   void setQuorumServerSaslRequired(boolean serverSaslRequired) {
      this.quorumServerSaslAuthRequired = serverSaslRequired;
      LOG.info("{} set to {}", "quorum.auth.serverRequireSasl", serverSaslRequired);
   }

   void setQuorumLearnerSaslRequired(boolean learnerSaslRequired) {
      this.quorumLearnerSaslAuthRequired = learnerSaslRequired;
      LOG.info("{} set to {}", "quorum.auth.learnerRequireSasl", learnerSaslRequired);
   }

   void setQuorumSaslEnabled(boolean enableAuth) {
      this.quorumSaslEnableAuth = enableAuth;
      if (!this.quorumSaslEnableAuth) {
         LOG.info("QuorumPeer communication is not secured! (SASL auth disabled)");
      } else {
         LOG.info("{} set to {}", "quorum.auth.enableSasl", enableAuth);
      }

   }

   void setQuorumServicePrincipal(String servicePrincipal) {
      this.quorumServicePrincipal = servicePrincipal;
      LOG.info("{} set to {}", "quorum.auth.kerberos.servicePrincipal", this.quorumServicePrincipal);
   }

   void setQuorumLearnerLoginContext(String learnerContext) {
      this.quorumLearnerLoginContext = learnerContext;
      LOG.info("{} set to {}", "quorum.auth.learner.saslLoginContext", this.quorumLearnerLoginContext);
   }

   void setQuorumServerLoginContext(String serverContext) {
      this.quorumServerLoginContext = serverContext;
      LOG.info("{} set to {}", "quorum.auth.server.saslLoginContext", this.quorumServerLoginContext);
   }

   void setQuorumCnxnThreadsSize(int qCnxnThreadsSize) {
      if (qCnxnThreadsSize > 20) {
         this.quorumCnxnThreadsSize = qCnxnThreadsSize;
      }

      LOG.info("quorum.cnxn.threads.size set to {}", this.quorumCnxnThreadsSize);
   }

   boolean isQuorumSaslAuthEnabled() {
      return this.quorumSaslEnableAuth;
   }

   private boolean isQuorumServerSaslAuthRequired() {
      return this.quorumServerSaslAuthRequired;
   }

   private boolean isQuorumLearnerSaslAuthRequired() {
      return this.quorumLearnerSaslAuthRequired;
   }

   public QuorumCnxManager createCnxnManager() {
      int timeout = quorumCnxnTimeoutMs > 0 ? quorumCnxnTimeoutMs : this.tickTime * this.syncLimit;
      LOG.info("Using {}ms as the quorum cnxn socket timeout", timeout);
      return new QuorumCnxManager(this, this.getMyId(), this.getView(), this.authServer, this.authLearner, timeout, this.getQuorumListenOnAllIPs(), this.quorumCnxnThreadsSize, this.isQuorumSaslAuthEnabled());
   }

   boolean isLeader(long id) {
      Vote vote = this.getCurrentVote();
      return vote != null && id == vote.getId();
   }

   public boolean isReconfigEnabled() {
      return this.reconfigEnabled;
   }

   @Private
   public Integer getSynced_observers_metric() {
      if (this.leader != null) {
         return this.leader.getObservingLearners().size();
      } else {
         return this.follower != null ? this.follower.getSyncedObserverSize() : null;
      }
   }

   public static QuorumPeer createFromConfig(QuorumPeerConfig config) throws IOException {
      QuorumPeer quorumPeer = new QuorumPeer();
      quorumPeer.setTxnFactory(new FileTxnSnapLog(config.getDataLogDir(), config.getDataDir()));
      quorumPeer.enableLocalSessions(config.areLocalSessionsEnabled());
      quorumPeer.enableLocalSessionsUpgrading(config.isLocalSessionsUpgradingEnabled());
      quorumPeer.setElectionType(config.getElectionAlg());
      quorumPeer.setMyid(config.getServerId());
      quorumPeer.setTickTime(config.getTickTime());
      quorumPeer.setMinSessionTimeout(config.getMinSessionTimeout());
      quorumPeer.setMaxSessionTimeout(config.getMaxSessionTimeout());
      quorumPeer.setInitLimit(config.getInitLimit());
      quorumPeer.setSyncLimit(config.getSyncLimit());
      quorumPeer.setConnectToLearnerMasterLimit(config.getConnectToLearnerMasterLimit());
      quorumPeer.setObserverMasterPort(config.getObserverMasterPort());
      quorumPeer.setConfigFileName(config.getConfigFilename());
      quorumPeer.setClientPortListenBacklog(config.getClientPortListenBacklog());
      quorumPeer.setZKDatabase(new ZKDatabase(quorumPeer.getTxnFactory()));
      quorumPeer.setQuorumVerifier(config.getQuorumVerifier(), false);
      if (config.getLastSeenQuorumVerifier() != null) {
         quorumPeer.setLastSeenQuorumVerifier(config.getLastSeenQuorumVerifier(), false);
      }

      quorumPeer.initConfigInZKDatabase();
      quorumPeer.setSslQuorum(config.isSslQuorum());
      quorumPeer.setUsePortUnification(config.shouldUsePortUnification());
      quorumPeer.setLearnerType(config.getPeerType());
      quorumPeer.setSyncEnabled(config.getSyncEnabled());
      quorumPeer.setQuorumListenOnAllIPs(config.getQuorumListenOnAllIPs());
      if (config.sslQuorumReloadCertFiles) {
         quorumPeer.getX509Util().enableCertFileReloading();
      }

      quorumPeer.setMultiAddressEnabled(config.isMultiAddressEnabled());
      quorumPeer.setMultiAddressReachabilityCheckEnabled(config.isMultiAddressReachabilityCheckEnabled());
      quorumPeer.setMultiAddressReachabilityCheckTimeoutMs(config.getMultiAddressReachabilityCheckTimeoutMs());
      quorumPeer.setQuorumSaslEnabled(config.quorumEnableSasl);
      if (quorumPeer.isQuorumSaslAuthEnabled()) {
         quorumPeer.setQuorumServerSaslRequired(config.quorumServerRequireSasl);
         quorumPeer.setQuorumLearnerSaslRequired(config.quorumLearnerRequireSasl);
         quorumPeer.setQuorumServicePrincipal(config.quorumServicePrincipal);
         quorumPeer.setQuorumServerLoginContext(config.quorumServerLoginContext);
         quorumPeer.setQuorumLearnerLoginContext(config.quorumLearnerLoginContext);
      }

      quorumPeer.setQuorumCnxnThreadsSize(config.quorumCnxnThreadsSize);
      if (config.jvmPauseMonitorToRun) {
         quorumPeer.setJvmPauseMonitor(new JvmPauseMonitor(config));
      }

      return quorumPeer;
   }

   static {
      LOG.info("{}={}", "zookeeper.quorumCnxnTimeoutMs", quorumCnxnTimeoutMs);
   }

   public static final class AddressTuple {
      public final MultipleAddresses quorumAddr;
      public final MultipleAddresses electionAddr;
      public final InetSocketAddress clientAddr;

      public AddressTuple(MultipleAddresses quorumAddr, MultipleAddresses electionAddr, InetSocketAddress clientAddr) {
         this.quorumAddr = quorumAddr;
         this.electionAddr = electionAddr;
         this.clientAddr = clientAddr;
      }
   }

   public static class QuorumServer {
      public MultipleAddresses addr;
      public MultipleAddresses electionAddr;
      public InetSocketAddress clientAddr;
      public long id;
      public String hostname;
      public LearnerType type;
      public boolean isClientAddrFromStatic;
      private List myAddrs;
      private static final String wrongFormat = " does not have the form server_config or server_config;client_config where server_config is the pipe separated list of host:port:port or host:port:port:type and client_config is port or host:port";

      public QuorumServer(long id, InetSocketAddress addr, InetSocketAddress electionAddr, InetSocketAddress clientAddr) {
         this(id, addr, electionAddr, clientAddr, QuorumPeer.LearnerType.PARTICIPANT);
      }

      public QuorumServer(long id, InetSocketAddress addr, InetSocketAddress electionAddr) {
         this(id, addr, electionAddr, (InetSocketAddress)null, QuorumPeer.LearnerType.PARTICIPANT);
      }

      public QuorumServer(long id, InetSocketAddress addr) {
         this(id, addr, (InetSocketAddress)null, (InetSocketAddress)null, QuorumPeer.LearnerType.PARTICIPANT);
      }

      public long getId() {
         return this.id;
      }

      public void recreateSocketAddresses() {
         if (this.addr.isEmpty()) {
            QuorumPeer.LOG.warn("Server address has not been initialized");
         } else if (this.electionAddr.isEmpty()) {
            QuorumPeer.LOG.warn("Election address has not been initialized");
         } else {
            this.addr.recreateSocketAddresses();
            this.electionAddr.recreateSocketAddresses();
         }
      }

      private LearnerType getType(String s) throws QuorumPeerConfig.ConfigException {
         switch (s.trim().toLowerCase()) {
            case "observer":
               return QuorumPeer.LearnerType.OBSERVER;
            case "participant":
               return QuorumPeer.LearnerType.PARTICIPANT;
            default:
               throw new QuorumPeerConfig.ConfigException("Unrecognised peertype: " + s);
         }
      }

      public QuorumServer(long sid, String addressStr) throws QuorumPeerConfig.ConfigException {
         this(sid, addressStr, QuorumServer::getInetAddress);
      }

      QuorumServer(long sid, String addressStr, Function getInetAddress) throws QuorumPeerConfig.ConfigException {
         this.addr = new MultipleAddresses();
         this.electionAddr = new MultipleAddresses();
         this.clientAddr = null;
         this.type = QuorumPeer.LearnerType.PARTICIPANT;
         this.isClientAddrFromStatic = false;
         this.id = sid;
         this.initializeWithAddressString(addressStr, getInetAddress);
      }

      public QuorumServer(long id, InetSocketAddress addr, InetSocketAddress electionAddr, LearnerType type) {
         this(id, addr, electionAddr, (InetSocketAddress)null, type);
      }

      public QuorumServer(long id, InetSocketAddress addr, InetSocketAddress electionAddr, InetSocketAddress clientAddr, LearnerType type) {
         this.addr = new MultipleAddresses();
         this.electionAddr = new MultipleAddresses();
         this.clientAddr = null;
         this.type = QuorumPeer.LearnerType.PARTICIPANT;
         this.isClientAddrFromStatic = false;
         this.id = id;
         if (addr != null) {
            this.addr.addAddress(addr);
         }

         if (electionAddr != null) {
            this.electionAddr.addAddress(electionAddr);
         }

         this.type = type;
         this.clientAddr = clientAddr;
         this.setMyAddrs();
      }

      private void initializeWithAddressString(String addressStr, Function getInetAddress) throws QuorumPeerConfig.ConfigException {
         LearnerType newType = null;
         String[] serverClientParts = addressStr.split(";");
         String[] serverAddresses = serverClientParts[0].split("\\|");
         if (serverClientParts.length == 2) {
            String[] clientParts = ConfigUtils.getHostAndPort(serverClientParts[1]);
            if (clientParts.length > 2) {
               throw new QuorumPeerConfig.ConfigException(addressStr + " does not have the form server_config or server_config;client_config where server_config is the pipe separated list of host:port:port or host:port:port:type and client_config is port or host:port");
            }

            String clientHostName = clientParts.length == 2 ? clientParts[0] : "0.0.0.0";

            try {
               this.clientAddr = new InetSocketAddress(clientHostName, Integer.parseInt(clientParts[clientParts.length - 1]));
            } catch (NumberFormatException var20) {
               throw new QuorumPeerConfig.ConfigException("Address unresolved: " + this.hostname + ":" + clientParts[clientParts.length - 1]);
            }
         }

         boolean multiAddressEnabled = Boolean.parseBoolean(System.getProperty("zookeeper.multiAddress.enabled", "false"));
         if (!multiAddressEnabled && serverAddresses.length > 1) {
            throw new QuorumPeerConfig.ConfigException("Multiple address feature is disabled, but multiple addresses were specified for sid " + this.id);
         } else {
            boolean canonicalize = Boolean.parseBoolean(System.getProperty("zookeeper.kerberos.canonicalizeHostNames", "false"));

            for(String serverAddress : serverAddresses) {
               String[] serverParts = ConfigUtils.getHostAndPort(serverAddress);
               if (serverClientParts.length > 2 || serverParts.length < 3 || serverParts.length > 4) {
                  throw new QuorumPeerConfig.ConfigException(addressStr + " does not have the form server_config or server_config;client_config where server_config is the pipe separated list of host:port:port or host:port:port:type and client_config is port or host:port");
               }

               String serverHostName = serverParts[0];

               InetSocketAddress tempAddress;
               try {
                  tempAddress = new InetSocketAddress(serverHostName, Integer.parseInt(serverParts[1]));
                  this.addr.addAddress(tempAddress);
               } catch (NumberFormatException var19) {
                  throw new QuorumPeerConfig.ConfigException("Address unresolved: " + serverHostName + ":" + serverParts[1]);
               }

               InetSocketAddress tempElectionAddress;
               try {
                  tempElectionAddress = new InetSocketAddress(serverHostName, Integer.parseInt(serverParts[2]));
                  this.electionAddr.addAddress(tempElectionAddress);
               } catch (NumberFormatException var18) {
                  throw new QuorumPeerConfig.ConfigException("Address unresolved: " + serverHostName + ":" + serverParts[2]);
               }

               if (tempAddress.getPort() == tempElectionAddress.getPort()) {
                  throw new QuorumPeerConfig.ConfigException("Client and election port must be different! Please update the configuration file on server." + this.id);
               }

               if (canonicalize) {
                  InetAddress ia = (InetAddress)getInetAddress.apply(tempAddress);
                  if (ia == null) {
                     throw new QuorumPeerConfig.ConfigException("Unable to canonicalize address " + serverHostName + " because it's not resolvable");
                  }

                  String canonicalHostName = ia.getCanonicalHostName();
                  if (!canonicalHostName.equals(serverHostName) && !canonicalHostName.equals(ia.getHostAddress())) {
                     QuorumPeer.LOG.info("Host name for quorum server {} canonicalized from {} to {}", new Object[]{this.id, serverHostName, canonicalHostName});
                     serverHostName = canonicalHostName;
                  }
               }

               if (serverParts.length == 4) {
                  LearnerType tempType = this.getType(serverParts[3]);
                  if (newType == null) {
                     newType = tempType;
                  }

                  if (newType != tempType) {
                     throw new QuorumPeerConfig.ConfigException("Multiple addresses should have similar roles: " + this.type + " vs " + tempType);
                  }
               }

               this.hostname = serverHostName;
            }

            if (newType != null) {
               this.type = newType;
            }

            this.setMyAddrs();
         }
      }

      private static InetAddress getInetAddress(InetSocketAddress addr) {
         return addr.getAddress();
      }

      private void setMyAddrs() {
         this.myAddrs = new ArrayList();
         this.myAddrs.addAll(this.addr.getAllAddresses());
         this.myAddrs.add(this.clientAddr);
         this.myAddrs.addAll(this.electionAddr.getAllAddresses());
         this.myAddrs = this.excludedSpecialAddresses(this.myAddrs);
      }

      public static String delimitedHostString(InetSocketAddress addr) {
         String host = addr.getHostString();
         return host.contains(":") ? "[" + host + "]" : host;
      }

      public String toString() {
         StringWriter sw = new StringWriter();
         List<InetSocketAddress> addrList = new LinkedList(this.addr.getAllAddresses());
         List<InetSocketAddress> electionAddrList = new LinkedList(this.electionAddr.getAllAddresses());
         if (addrList.size() > 0 && electionAddrList.size() > 0) {
            addrList.sort(Comparator.comparing(InetSocketAddress::getHostString));
            electionAddrList.sort(Comparator.comparing(InetSocketAddress::getHostString));
            sw.append((CharSequence)IntStream.range(0, addrList.size()).mapToObj((i) -> String.format("%s:%d:%d", delimitedHostString((InetSocketAddress)addrList.get(i)), ((InetSocketAddress)addrList.get(i)).getPort(), ((InetSocketAddress)electionAddrList.get(i)).getPort())).collect(Collectors.joining("|")));
         }

         if (this.type == QuorumPeer.LearnerType.OBSERVER) {
            sw.append(":observer");
         } else if (this.type == QuorumPeer.LearnerType.PARTICIPANT) {
            sw.append(":participant");
         }

         if (this.clientAddr != null && !this.isClientAddrFromStatic) {
            sw.append(";");
            sw.append(delimitedHostString(this.clientAddr));
            sw.append(":");
            sw.append(String.valueOf(this.clientAddr.getPort()));
         }

         return sw.toString();
      }

      public int hashCode() {
         assert false : "hashCode not designed";

         return 42;
      }

      private boolean checkAddressesEqual(InetSocketAddress addr1, InetSocketAddress addr2) {
         return (addr1 != null || addr2 == null) && (addr1 == null || addr2 != null) && (addr1 == null || addr2 == null || addr1.equals(addr2));
      }

      public boolean equals(Object o) {
         if (!(o instanceof QuorumServer)) {
            return false;
         } else {
            QuorumServer qs = (QuorumServer)o;
            if (qs.id == this.id && qs.type == this.type) {
               if (!this.addr.equals(qs.addr)) {
                  return false;
               } else {
                  return !this.electionAddr.equals(qs.electionAddr) ? false : this.checkAddressesEqual(this.clientAddr, qs.clientAddr);
               }
            } else {
               return false;
            }
         }
      }

      public void checkAddressDuplicate(QuorumServer s) throws KeeperException.BadArgumentsException {
         List<InetSocketAddress> otherAddrs = new ArrayList(s.addr.getAllAddresses());
         otherAddrs.add(s.clientAddr);
         otherAddrs.addAll(s.electionAddr.getAllAddresses());
         otherAddrs = this.excludedSpecialAddresses(otherAddrs);

         for(InetSocketAddress my : this.myAddrs) {
            for(InetSocketAddress other : otherAddrs) {
               if (my.equals(other)) {
                  String error = String.format("%s of server.%d conflicts %s of server.%d", my, this.id, other, s.id);
                  throw new KeeperException.BadArgumentsException(error);
               }
            }
         }

      }

      private List excludedSpecialAddresses(List addrs) {
         List<InetSocketAddress> included = new ArrayList();

         for(InetSocketAddress addr : addrs) {
            if (addr != null) {
               InetAddress inetaddr = addr.getAddress();
               if (inetaddr != null && !inetaddr.isAnyLocalAddress() && !inetaddr.isLoopbackAddress()) {
                  included.add(addr);
               }
            }
         }

         return included;
      }
   }

   public static enum ServerState {
      LOOKING,
      FOLLOWING,
      LEADING,
      OBSERVING;
   }

   public static enum ZabState {
      ELECTION,
      DISCOVERY,
      SYNCHRONIZATION,
      BROADCAST;
   }

   public static enum SyncMode {
      NONE,
      DIFF,
      SNAP,
      TRUNC;
   }

   public static enum LearnerType {
      PARTICIPANT,
      OBSERVER;
   }

   /** @deprecated */
   @Deprecated
   class ResponderThread extends ZooKeeperThread {
      volatile boolean running = true;

      ResponderThread() {
         super("ResponderThread");
      }

      public void run() {
         try {
            byte[] b = new byte[36];
            ByteBuffer responseBuffer = ByteBuffer.wrap(b);

            for(DatagramPacket packet = new DatagramPacket(b, b.length); this.running; packet.setLength(b.length)) {
               QuorumPeer.this.udpSocket.receive(packet);
               if (packet.getLength() != 4) {
                  QuorumPeer.LOG.warn("Got more than just an xid! Len = {}", packet.getLength());
               } else {
                  responseBuffer.clear();
                  responseBuffer.getInt();
                  responseBuffer.putLong(QuorumPeer.this.myid);
                  Vote current = QuorumPeer.this.getCurrentVote();
                  switch (QuorumPeer.this.getPeerState()) {
                     case LOOKING:
                        responseBuffer.putLong(current.getId());
                        responseBuffer.putLong(current.getZxid());
                        break;
                     case LEADING:
                        responseBuffer.putLong(QuorumPeer.this.myid);

                        try {
                           long proposed;
                           synchronized(QuorumPeer.this.leader) {
                              proposed = QuorumPeer.this.leader.lastProposed;
                           }

                           responseBuffer.putLong(proposed);
                        } catch (NullPointerException var18) {
                        }
                        break;
                     case FOLLOWING:
                        responseBuffer.putLong(current.getId());

                        try {
                           responseBuffer.putLong(QuorumPeer.this.follower.getZxid());
                        } catch (NullPointerException var16) {
                        }
                     case OBSERVING:
                  }

                  packet.setData(b);
                  QuorumPeer.this.udpSocket.send(packet);
               }
            }
         } catch (RuntimeException e) {
            QuorumPeer.LOG.warn("Unexpected runtime exception in ResponderThread", e);
         } catch (IOException e) {
            QuorumPeer.LOG.warn("Unexpected IO exception in ResponderThread", e);
         } finally {
            QuorumPeer.LOG.warn("QuorumPeer responder thread exited");
         }

      }
   }
}
