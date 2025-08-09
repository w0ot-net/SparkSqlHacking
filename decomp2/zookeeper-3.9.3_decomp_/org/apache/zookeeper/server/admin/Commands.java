package org.apache.zookeeper.server.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.apache.zookeeper.Environment;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Version;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.metrics.MetricsProvider;
import org.apache.zookeeper.server.DataNode;
import org.apache.zookeeper.server.DataTree;
import org.apache.zookeeper.server.ServerCnxn;
import org.apache.zookeeper.server.ServerCnxnFactory;
import org.apache.zookeeper.server.ServerMetrics;
import org.apache.zookeeper.server.ZooKeeperServer;
import org.apache.zookeeper.server.ZooTrace;
import org.apache.zookeeper.server.auth.ProviderRegistry;
import org.apache.zookeeper.server.auth.ServerAuthenticationProvider;
import org.apache.zookeeper.server.persistence.SnapshotInfo;
import org.apache.zookeeper.server.persistence.Util;
import org.apache.zookeeper.server.quorum.Follower;
import org.apache.zookeeper.server.quorum.FollowerZooKeeperServer;
import org.apache.zookeeper.server.quorum.Leader;
import org.apache.zookeeper.server.quorum.LeaderZooKeeperServer;
import org.apache.zookeeper.server.quorum.MultipleAddresses;
import org.apache.zookeeper.server.quorum.QuorumPeer;
import org.apache.zookeeper.server.quorum.QuorumZooKeeperServer;
import org.apache.zookeeper.server.quorum.ReadOnlyZooKeeperServer;
import org.apache.zookeeper.server.quorum.flexible.QuorumVerifier;
import org.apache.zookeeper.server.util.RateLimiter;
import org.apache.zookeeper.server.util.ZxidUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Commands {
   static final Logger LOG = LoggerFactory.getLogger(Commands.class);
   static final String ADMIN_RATE_LIMITER_INTERVAL = "zookeeper.admin.rateLimiterIntervalInMS";
   private static final long rateLimiterInterval = (long)Integer.parseInt(System.getProperty("zookeeper.admin.rateLimiterIntervalInMS", "300000"));
   static final String AUTH_INFO_SEPARATOR = " ";
   static final String ROOT_PATH = "/";
   private static Map commands = new HashMap();
   private static Set primaryNames = new HashSet();

   public static void registerCommand(Command command) {
      for(String name : command.getNames()) {
         Command prev = (Command)commands.put(name, command);
         if (prev != null) {
            LOG.warn("Re-registering command {} (primary name = {})", name, command.getPrimaryName());
         }
      }

      primaryNames.add(command.getPrimaryName());
   }

   public static CommandResponse runGetCommand(String cmdName, ZooKeeperServer zkServer, Map kwargs, String authInfo, HttpServletRequest request) {
      return runCommand(cmdName, zkServer, kwargs, (InputStream)null, authInfo, request, true);
   }

   public static CommandResponse runPostCommand(String cmdName, ZooKeeperServer zkServer, InputStream inputStream, String authInfo, HttpServletRequest request) {
      return runCommand(cmdName, zkServer, (Map)null, inputStream, authInfo, request, false);
   }

   private static CommandResponse runCommand(String cmdName, ZooKeeperServer zkServer, Map kwargs, InputStream inputStream, String authInfo, HttpServletRequest request, boolean isGet) {
      Command command = getCommand(cmdName);
      if (command == null) {
         LOG.warn("Unknown command");
         return new CommandResponse(cmdName, "Unknown command: " + cmdName, 200);
      } else if (!command.isServerRequired() || zkServer != null && zkServer.isRunning()) {
         AuthRequest authRequest = command.getAuthRequest();
         if (authRequest != null) {
            if (authInfo == null) {
               LOG.warn("Auth info is missing for command");
               return new CommandResponse(cmdName, "Auth info is missing for the command", 401);
            }

            try {
               List<Id> ids = handleAuthentication(request, authInfo);
               handleAuthorization(zkServer, ids, authRequest.getPermission(), authRequest.getPath());
            } catch (KeeperException.AuthFailedException var10) {
               return new CommandResponse(cmdName, "Not authenticated", 401);
            } catch (KeeperException.NoAuthException var11) {
               return new CommandResponse(cmdName, "Not authorized", 403);
            } catch (Exception e) {
               LOG.warn("Error occurred during auth for command", e);
               return new CommandResponse(cmdName, "Error occurred during auth", 500);
            }
         }

         return isGet ? command.runGet(zkServer, kwargs) : command.runPost(zkServer, inputStream);
      } else {
         LOG.warn("This ZooKeeper instance is not currently serving requests for command");
         return new CommandResponse(cmdName, "This ZooKeeper instance is not currently serving requests", 200);
      }
   }

   private static List handleAuthentication(HttpServletRequest request, String authInfo) throws KeeperException.AuthFailedException {
      String[] authData = authInfo.split(" ");
      if (authData.length != 1 && authData.length != 2) {
         LOG.warn("Invalid auth info length");
         throw new KeeperException.AuthFailedException();
      } else {
         String schema = authData[0];
         ServerAuthenticationProvider authProvider = ProviderRegistry.getServerProvider(schema);
         if (authProvider != null) {
            try {
               byte[] auth = authData.length == 2 ? authData[1].getBytes(StandardCharsets.UTF_8) : null;
               List<Id> ids = authProvider.handleAuthentication((HttpServletRequest)request, auth);
               if (ids.isEmpty()) {
                  LOG.warn("Auth Id list is empty");
                  throw new KeeperException.AuthFailedException();
               } else {
                  return ids;
               }
            } catch (RuntimeException e) {
               LOG.warn("Caught runtime exception from AuthenticationProvider", e);
               throw new KeeperException.AuthFailedException();
            }
         } else {
            LOG.warn("Auth provider not found for schema");
            throw new KeeperException.AuthFailedException();
         }
      }
   }

   private static void handleAuthorization(ZooKeeperServer zkServer, List ids, int perm, String path) throws KeeperException.NoNodeException, KeeperException.NoAuthException {
      DataNode dataNode = zkServer.getZKDatabase().getNode(path);
      if (dataNode == null) {
         throw new KeeperException.NoNodeException(path);
      } else {
         List<ACL> acls = zkServer.getZKDatabase().aclForNode(dataNode);
         zkServer.checkACL((ServerCnxn)null, acls, perm, ids, path, (List)null);
      }
   }

   public static Set getPrimaryNames() {
      return primaryNames;
   }

   public static Command getCommand(String cmdName) {
      return (Command)commands.get(cmdName);
   }

   private Commands() {
   }

   static {
      registerCommand(new CnxnStatResetCommand());
      registerCommand(new ConfCommand());
      registerCommand(new ConsCommand());
      registerCommand(new DigestCommand());
      registerCommand(new DirsCommand());
      registerCommand(new DumpCommand());
      registerCommand(new EnvCommand());
      registerCommand(new GetTraceMaskCommand());
      registerCommand(new InitialConfigurationCommand());
      registerCommand(new IsroCommand());
      registerCommand(new LastSnapshotCommand());
      registerCommand(new LeaderCommand());
      registerCommand(new MonitorCommand());
      registerCommand(new ObserverCnxnStatResetCommand());
      registerCommand(new RestoreCommand());
      registerCommand(new RuokCommand());
      registerCommand(new SetTraceMaskCommand());
      registerCommand(new SnapshotCommand());
      registerCommand(new SrvrCommand());
      registerCommand(new StatCommand());
      registerCommand(new StatResetCommand());
      registerCommand(new SyncedObserverConsCommand());
      registerCommand(new SystemPropertiesCommand());
      registerCommand(new VotingViewCommand());
      registerCommand(new WatchCommand());
      registerCommand(new WatchesByPathCommand());
      registerCommand(new WatchSummaryCommand());
      registerCommand(new ZabStateCommand());
   }

   public static class CnxnStatResetCommand extends GetCommand {
      public CnxnStatResetCommand() {
         super(Arrays.asList("connection_stat_reset", "crst"));
      }

      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         CommandResponse response = this.initializeResponse();
         zkServer.getServerCnxnFactory().resetAllConnectionStats();
         return response;
      }
   }

   public static class ConfCommand extends GetCommand {
      public ConfCommand() {
         super(Arrays.asList("configuration", "conf", "config"));
      }

      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         CommandResponse response = this.initializeResponse();
         response.putAll(zkServer.getConf().toMap());
         return response;
      }
   }

   public static class ConsCommand extends GetCommand {
      public ConsCommand() {
         super(Arrays.asList("connections", "cons"));
      }

      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         CommandResponse response = this.initializeResponse();
         ServerCnxnFactory serverCnxnFactory = zkServer.getServerCnxnFactory();
         if (serverCnxnFactory != null) {
            response.put("connections", serverCnxnFactory.getAllConnectionInfo(false));
         } else {
            response.put("connections", Collections.emptyList());
         }

         ServerCnxnFactory secureServerCnxnFactory = zkServer.getSecureServerCnxnFactory();
         if (secureServerCnxnFactory != null) {
            response.put("secure_connections", secureServerCnxnFactory.getAllConnectionInfo(false));
         } else {
            response.put("secure_connections", Collections.emptyList());
         }

         return response;
      }
   }

   public static class DirsCommand extends GetCommand {
      public DirsCommand() {
         super(Arrays.asList("dirs"));
      }

      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         CommandResponse response = this.initializeResponse();
         response.put("datadir_size", zkServer.getDataDirSize());
         response.put("logdir_size", zkServer.getLogDirSize());
         return response;
      }
   }

   public static class DumpCommand extends GetCommand {
      public DumpCommand() {
         super(Arrays.asList("dump"));
      }

      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         CommandResponse response = this.initializeResponse();
         response.put("expiry_time_to_session_ids", zkServer.getSessionExpiryMap());
         response.put("session_id_to_ephemeral_paths", zkServer.getEphemerals());
         return response;
      }
   }

   public static class EnvCommand extends GetCommand {
      public EnvCommand() {
         super(Arrays.asList("environment", "env", "envi"), false);
      }

      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         CommandResponse response = this.initializeResponse();

         for(Environment.Entry e : Environment.list()) {
            response.put(e.getKey(), e.getValue());
         }

         return response;
      }
   }

   public static class DigestCommand extends GetCommand {
      public DigestCommand() {
         super(Arrays.asList("hash"));
      }

      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         CommandResponse response = this.initializeResponse();
         response.put("digests", zkServer.getZKDatabase().getDataTree().getDigestLog());
         return response;
      }
   }

   public static class GetTraceMaskCommand extends GetCommand {
      public GetTraceMaskCommand() {
         super(Arrays.asList("get_trace_mask", "gtmk"), false);
      }

      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         CommandResponse response = this.initializeResponse();
         response.put("tracemask", ZooTrace.getTextTraceLevel());
         return response;
      }
   }

   public static class InitialConfigurationCommand extends GetCommand {
      public InitialConfigurationCommand() {
         super(Arrays.asList("initial_configuration", "icfg"));
      }

      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         CommandResponse response = this.initializeResponse();
         response.put("initial_configuration", zkServer.getInitialConfig());
         return response;
      }
   }

   public static class IsroCommand extends GetCommand {
      public IsroCommand() {
         super(Arrays.asList("is_read_only", "isro"));
      }

      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         CommandResponse response = this.initializeResponse();
         response.put("read_only", zkServer instanceof ReadOnlyZooKeeperServer);
         return response;
      }
   }

   public static class LastSnapshotCommand extends GetCommand {
      public LastSnapshotCommand() {
         super(Arrays.asList("last_snapshot", "lsnp"));
      }

      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         CommandResponse response = this.initializeResponse();
         SnapshotInfo info = zkServer.getTxnLogFactory().getLastSnapshotInfo();
         response.put("zxid", Long.toHexString(info == null ? -1L : info.zxid));
         response.put("timestamp", info == null ? -1L : info.timestamp);
         return response;
      }
   }

   public static class LeaderCommand extends GetCommand {
      public LeaderCommand() {
         super(Arrays.asList("leader", "lead"));
      }

      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         CommandResponse response = this.initializeResponse();
         if (zkServer instanceof QuorumZooKeeperServer) {
            response.put("is_leader", zkServer instanceof LeaderZooKeeperServer);
            QuorumPeer peer = ((QuorumZooKeeperServer)zkServer).self;
            response.put("leader_id", peer.getLeaderId());
            String leaderAddress = peer.getLeaderAddress();
            response.put("leader_ip", leaderAddress != null ? leaderAddress : "");
         } else {
            response.put("error", "server is not initialized");
         }

         return response;
      }
   }

   public static class MonitorCommand extends GetCommand {
      public MonitorCommand() {
         super(Arrays.asList("monitor", "mntr"), false);
      }

      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         CommandResponse response = this.initializeResponse();
         Objects.requireNonNull(response);
         zkServer.dumpMonitorValues(response::put);
         MetricsProvider var10000 = ServerMetrics.getMetrics().getMetricsProvider();
         Objects.requireNonNull(response);
         var10000.dump(response::put);
         return response;
      }
   }

   public static class ObserverCnxnStatResetCommand extends GetCommand {
      public ObserverCnxnStatResetCommand() {
         super(Arrays.asList("observer_connection_stat_reset", "orst"));
      }

      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         CommandResponse response = this.initializeResponse();
         if (zkServer instanceof LeaderZooKeeperServer) {
            Leader leader = ((LeaderZooKeeperServer)zkServer).getLeader();
            leader.resetObserverConnectionStats();
         } else if (zkServer instanceof FollowerZooKeeperServer) {
            Follower follower = ((FollowerZooKeeperServer)zkServer).getFollower();
            follower.resetObserverConnectionStats();
         }

         return response;
      }
   }

   public static class RestoreCommand extends PostCommand {
      static final String RESPONSE_DATA_LAST_ZXID = "last_zxid";
      static final String ADMIN_RESTORE_ENABLED = "zookeeper.admin.restore.enabled";
      private RateLimiter rateLimiter;

      public RestoreCommand() {
         super(Arrays.asList("restore", "rest"), true, new AuthRequest(31, "/"));
         this.rateLimiter = new RateLimiter(1, Commands.rateLimiterInterval, TimeUnit.MILLISECONDS);
      }

      public CommandResponse runPost(ZooKeeperServer zkServer, InputStream inputStream) {
         CommandResponse response = this.initializeResponse();
         boolean restoreEnabled = Boolean.parseBoolean(System.getProperty("zookeeper.admin.restore.enabled", "true"));
         if (!restoreEnabled) {
            response.setStatusCode(503);
            Commands.LOG.warn("Restore command is disabled");
            return response;
         } else if (!ZooKeeperServer.isSerializeLastProcessedZxidEnabled()) {
            response.setStatusCode(500);
            Commands.LOG.warn("Restore command requires serializeLastProcessedZxidEnable flag is set to true");
            return response;
         } else if (inputStream == null) {
            response.setStatusCode(400);
            Commands.LOG.warn("InputStream from restore request is null");
            return response;
         } else if (!this.rateLimiter.allow()) {
            response.setStatusCode(429);
            ServerMetrics.getMetrics().RESTORE_RATE_LIMITED_COUNT.add(1L);
            Commands.LOG.warn("Restore request was rate limited");
            return response;
         } else {
            try {
               long lastZxid = zkServer.restoreFromSnapshot(inputStream);
               response.put("last_zxid", lastZxid);
            } catch (Exception e) {
               response.setStatusCode(500);
               ServerMetrics.getMetrics().RESTORE_ERROR_COUNT.add(1L);
               Commands.LOG.warn("Exception occurred when restore snapshot via the restore command", e);
            }

            return response;
         }
      }
   }

   public static class RuokCommand extends GetCommand {
      public RuokCommand() {
         super(Arrays.asList("ruok"));
      }

      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         return this.initializeResponse();
      }
   }

   public static class SetTraceMaskCommand extends GetCommand {
      public SetTraceMaskCommand() {
         super(Arrays.asList("set_trace_mask", "stmk"), false);
      }

      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         CommandResponse response = this.initializeResponse();
         if (!kwargs.containsKey("traceMask")) {
            response.put("error", "setTraceMask requires long traceMask argument");
            return response;
         } else {
            long traceMask;
            try {
               traceMask = Long.parseLong((String)kwargs.get("traceMask"));
            } catch (NumberFormatException var7) {
               response.put("error", "setTraceMask requires long traceMask argument, got " + (String)kwargs.get("traceMask"));
               return response;
            }

            ZooTrace.setTextTraceLevel(traceMask);
            response.put("tracemask", traceMask);
            return response;
         }
      }
   }

   public static class SnapshotCommand extends GetCommand {
      static final String REQUEST_QUERY_PARAM_STREAMING = "streaming";
      static final String RESPONSE_HEADER_LAST_ZXID = "last_zxid";
      static final String RESPONSE_HEADER_SNAPSHOT_SIZE = "snapshot_size";
      static final String ADMIN_SNAPSHOT_ENABLED = "zookeeper.admin.snapshot.enabled";
      private final RateLimiter rateLimiter;

      public SnapshotCommand() {
         super(Arrays.asList("snapshot", "snap"), true, new AuthRequest(31, "/"));
         this.rateLimiter = new RateLimiter(1, Commands.rateLimiterInterval, TimeUnit.MICROSECONDS);
      }

      @SuppressFBWarnings(
         value = {"OBL_UNSATISFIED_OBLIGATION"},
         justification = "FileInputStream is passed to CommandResponse and closed in StreamOutputter"
      )
      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         CommandResponse response = this.initializeResponse();
         boolean snapshotEnabled = Boolean.parseBoolean(System.getProperty("zookeeper.admin.snapshot.enabled", "true"));
         if (!snapshotEnabled) {
            response.setStatusCode(503);
            Commands.LOG.warn("Snapshot command is disabled");
            return response;
         } else if (!ZooKeeperServer.isSerializeLastProcessedZxidEnabled()) {
            response.setStatusCode(500);
            Commands.LOG.warn("Snapshot command requires serializeLastProcessedZxidEnable flag is set to true");
            return response;
         } else if (!this.rateLimiter.allow()) {
            response.setStatusCode(429);
            ServerMetrics.getMetrics().SNAPSHOT_RATE_LIMITED_COUNT.add(1L);
            Commands.LOG.warn("Snapshot request was rate limited");
            return response;
         } else {
            boolean streaming = true;
            if (kwargs.containsKey("streaming")) {
               streaming = Boolean.parseBoolean((String)kwargs.get("streaming"));
            }

            try {
               File snapshotFile = zkServer.takeSnapshot(false, false, true);
               long lastZxid = Util.getZxidFromName(snapshotFile.getName(), "snapshot");
               response.addHeader("last_zxid", "0x" + ZxidUtils.zxidToString(lastZxid));
               long size = snapshotFile.length();
               response.addHeader("snapshot_size", String.valueOf(size));
               if (size == 0L) {
                  response.setStatusCode(500);
                  ServerMetrics.getMetrics().SNAPSHOT_ERROR_COUNT.add(1L);
                  Commands.LOG.warn("Snapshot file {} is empty", snapshotFile);
               } else if (streaming) {
                  response.setInputStream(new FileInputStream(snapshotFile));
               }
            } catch (Exception e) {
               response.setStatusCode(500);
               ServerMetrics.getMetrics().SNAPSHOT_ERROR_COUNT.add(1L);
               Commands.LOG.warn("Exception occurred when taking the snapshot via the snapshot admin command", e);
            }

            return response;
         }
      }
   }

   public static class SrvrCommand extends GetCommand {
      public SrvrCommand() {
         super(Arrays.asList("server_stats", "srvr"));
      }

      protected SrvrCommand(List names) {
         super(names);
      }

      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         CommandResponse response = this.initializeResponse();
         Commands.LOG.info("running stat");
         response.put("version", Version.getFullVersion());
         response.put("read_only", zkServer instanceof ReadOnlyZooKeeperServer);
         response.put("server_stats", zkServer.serverStats());
         response.put("client_response", zkServer.serverStats().getClientResponseStats());
         if (zkServer instanceof LeaderZooKeeperServer) {
            Leader leader = ((LeaderZooKeeperServer)zkServer).getLeader();
            response.put("proposal_stats", leader.getProposalStats());
         }

         response.put("node_count", zkServer.getZKDatabase().getNodeCount());
         return response;
      }
   }

   public static class StatCommand extends SrvrCommand {
      public StatCommand() {
         super(Arrays.asList("stats", "stat"));
      }

      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         CommandResponse response = super.runGet(zkServer, kwargs);
         Iterable<Map<String, Object>> connections;
         if (zkServer.getServerCnxnFactory() != null) {
            connections = zkServer.getServerCnxnFactory().getAllConnectionInfo(true);
         } else {
            connections = Collections.emptyList();
         }

         response.put("connections", connections);
         Iterable<Map<String, Object>> secureConnections;
         if (zkServer.getSecureServerCnxnFactory() != null) {
            secureConnections = zkServer.getSecureServerCnxnFactory().getAllConnectionInfo(true);
         } else {
            secureConnections = Collections.emptyList();
         }

         response.put("secure_connections", secureConnections);
         return response;
      }
   }

   public static class StatResetCommand extends GetCommand {
      public StatResetCommand() {
         super(Arrays.asList("stat_reset", "srst"));
      }

      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         CommandResponse response = this.initializeResponse();
         zkServer.serverStats().reset();
         return response;
      }
   }

   public static class SyncedObserverConsCommand extends GetCommand {
      public SyncedObserverConsCommand() {
         super(Arrays.asList("observers", "obsr"));
      }

      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         CommandResponse response = this.initializeResponse();
         if (zkServer instanceof LeaderZooKeeperServer) {
            Leader leader = ((LeaderZooKeeperServer)zkServer).getLeader();
            response.put("synced_observers", leader.getObservingLearners().size());
            response.put("observers", leader.getObservingLearnersInfo());
            return response;
         } else {
            if (zkServer instanceof FollowerZooKeeperServer) {
               Follower follower = ((FollowerZooKeeperServer)zkServer).getFollower();
               Integer syncedObservers = follower.getSyncedObserverSize();
               if (syncedObservers != null) {
                  response.put("synced_observers", syncedObservers);
                  response.put("observers", follower.getSyncedObserversInfo());
                  return response;
               }
            }

            response.put("synced_observers", 0);
            response.put("observers", Collections.emptySet());
            return response;
         }
      }
   }

   public static class SystemPropertiesCommand extends GetCommand {
      public SystemPropertiesCommand() {
         super(Arrays.asList("system_properties", "sysp"), false);
      }

      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         CommandResponse response = this.initializeResponse();
         Properties systemProperties = System.getProperties();
         SortedMap<String, String> sortedSystemProperties = new TreeMap();
         systemProperties.forEach((k, v) -> sortedSystemProperties.put(k.toString(), v.toString()));
         response.putAll(sortedSystemProperties);
         return response;
      }
   }

   public static class VotingViewCommand extends GetCommand {
      public VotingViewCommand() {
         super(Arrays.asList("voting_view"));
      }

      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         CommandResponse response = this.initializeResponse();
         if (zkServer instanceof QuorumZooKeeperServer) {
            QuorumPeer peer = ((QuorumZooKeeperServer)zkServer).self;
            Map<Long, QuorumServerView> votingView = (Map)peer.getVotingView().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, (e) -> new QuorumServerView((QuorumPeer.QuorumServer)e.getValue())));
            response.put("current_config", votingView);
         } else {
            response.put("current_config", Collections.emptyMap());
         }

         return response;
      }

      @SuppressFBWarnings(
         value = {"URF_UNREAD_FIELD"},
         justification = "class is used only for JSON serialization"
      )
      private static class QuorumServerView {
         @JsonProperty
         private List serverAddresses;
         @JsonProperty
         private List electionAddresses;
         @JsonProperty
         private String clientAddress;
         @JsonProperty
         private String learnerType;

         public QuorumServerView(QuorumPeer.QuorumServer quorumServer) {
            this.serverAddresses = getMultiAddressString(quorumServer.addr);
            this.electionAddresses = getMultiAddressString(quorumServer.electionAddr);
            this.learnerType = quorumServer.type.equals(QuorumPeer.LearnerType.PARTICIPANT) ? "participant" : "observer";
            this.clientAddress = getAddressString(quorumServer.clientAddr);
         }

         private static List getMultiAddressString(MultipleAddresses multipleAddresses) {
            return multipleAddresses == null ? Collections.emptyList() : (List)multipleAddresses.getAllAddresses().stream().map(QuorumServerView::getAddressString).collect(Collectors.toList());
         }

         private static String getAddressString(InetSocketAddress address) {
            return address == null ? "" : String.format("%s:%d", QuorumPeer.QuorumServer.delimitedHostString(address), address.getPort());
         }
      }
   }

   public static class WatchCommand extends GetCommand {
      public WatchCommand() {
         super(Arrays.asList("watches", "wchc"));
      }

      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         DataTree dt = zkServer.getZKDatabase().getDataTree();
         CommandResponse response = this.initializeResponse();
         response.put("session_id_to_watched_paths", dt.getWatches().toMap());
         return response;
      }
   }

   public static class WatchesByPathCommand extends GetCommand {
      public WatchesByPathCommand() {
         super(Arrays.asList("watches_by_path", "wchp"));
      }

      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         DataTree dt = zkServer.getZKDatabase().getDataTree();
         CommandResponse response = this.initializeResponse();
         response.put("path_to_session_ids", dt.getWatchesByPath().toMap());
         return response;
      }
   }

   public static class WatchSummaryCommand extends GetCommand {
      public WatchSummaryCommand() {
         super(Arrays.asList("watch_summary", "wchs"));
      }

      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         DataTree dt = zkServer.getZKDatabase().getDataTree();
         CommandResponse response = this.initializeResponse();
         response.putAll(dt.getWatchesSummary().toMap());
         return response;
      }
   }

   public static class ZabStateCommand extends GetCommand {
      public ZabStateCommand() {
         super(Arrays.asList("zabstate"), false);
      }

      public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
         CommandResponse response = this.initializeResponse();
         if (zkServer instanceof QuorumZooKeeperServer) {
            QuorumPeer peer = ((QuorumZooKeeperServer)zkServer).self;
            QuorumPeer.ZabState zabState = peer.getZabState();
            QuorumVerifier qv = peer.getQuorumVerifier();
            QuorumPeer.QuorumServer voter = (QuorumPeer.QuorumServer)qv.getVotingMembers().get(peer.getMyId());
            boolean voting = voter != null && voter.addr.equals(peer.getQuorumAddress()) && voter.electionAddr.equals(peer.getElectionAddress());
            response.put("myid", zkServer.getConf().getServerId());
            response.put("is_leader", zkServer instanceof LeaderZooKeeperServer);
            response.put("quorum_address", peer.getQuorumAddress());
            response.put("election_address", peer.getElectionAddress());
            response.put("client_address", peer.getClientAddress());
            response.put("voting", voting);
            long lastProcessedZxid = zkServer.getZKDatabase().getDataTreeLastProcessedZxid();
            response.put("last_zxid", "0x" + ZxidUtils.zxidToString(lastProcessedZxid));
            response.put("zab_epoch", ZxidUtils.getEpochFromZxid(lastProcessedZxid));
            response.put("zab_counter", ZxidUtils.getCounterFromZxid(lastProcessedZxid));
            response.put("zabstate", zabState.name().toLowerCase());
         } else {
            response.put("voting", false);
            response.put("zabstate", "");
         }

         return response;
      }
   }
}
