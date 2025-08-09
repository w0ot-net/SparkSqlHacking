package org.apache.zookeeper.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.jute.BinaryOutputArchive;
import org.apache.jute.Record;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.DeleteContainerRequest;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.MultiOperationRecord;
import org.apache.zookeeper.Op;
import org.apache.zookeeper.common.PathUtils;
import org.apache.zookeeper.common.StringUtils;
import org.apache.zookeeper.common.Time;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.StatPersisted;
import org.apache.zookeeper.proto.CheckVersionRequest;
import org.apache.zookeeper.proto.CreateRequest;
import org.apache.zookeeper.proto.CreateTTLRequest;
import org.apache.zookeeper.proto.DeleteRequest;
import org.apache.zookeeper.proto.ReconfigRequest;
import org.apache.zookeeper.proto.SetACLRequest;
import org.apache.zookeeper.proto.SetDataRequest;
import org.apache.zookeeper.server.auth.ProviderRegistry;
import org.apache.zookeeper.server.auth.ServerAuthenticationProvider;
import org.apache.zookeeper.server.quorum.LeaderZooKeeperServer;
import org.apache.zookeeper.server.quorum.QuorumPeer;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;
import org.apache.zookeeper.server.quorum.flexible.QuorumMaj;
import org.apache.zookeeper.server.quorum.flexible.QuorumOracleMaj;
import org.apache.zookeeper.server.quorum.flexible.QuorumVerifier;
import org.apache.zookeeper.txn.CheckVersionTxn;
import org.apache.zookeeper.txn.CloseSessionTxn;
import org.apache.zookeeper.txn.CreateContainerTxn;
import org.apache.zookeeper.txn.CreateSessionTxn;
import org.apache.zookeeper.txn.CreateTTLTxn;
import org.apache.zookeeper.txn.CreateTxn;
import org.apache.zookeeper.txn.DeleteTxn;
import org.apache.zookeeper.txn.ErrorTxn;
import org.apache.zookeeper.txn.MultiTxn;
import org.apache.zookeeper.txn.SetACLTxn;
import org.apache.zookeeper.txn.SetDataTxn;
import org.apache.zookeeper.txn.Txn;
import org.apache.zookeeper.txn.TxnDigest;
import org.apache.zookeeper.txn.TxnHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrepRequestProcessor extends ZooKeeperCriticalThread implements RequestProcessor {
   private static final Logger LOG = LoggerFactory.getLogger(PrepRequestProcessor.class);
   private static boolean failCreate = false;
   LinkedBlockingQueue submittedRequests = new LinkedBlockingQueue();
   private final RequestProcessor nextProcessor;
   private final boolean digestEnabled;
   private DigestCalculator digestCalculator;
   ZooKeeperServer zks;

   public PrepRequestProcessor(ZooKeeperServer zks, RequestProcessor nextProcessor) {
      super("ProcessThread(sid:" + zks.getServerId() + " cport:" + zks.getClientPort() + "):", zks.getZooKeeperServerListener());
      this.nextProcessor = nextProcessor;
      this.zks = zks;
      this.digestEnabled = ZooKeeperServer.isDigestEnabled();
      if (this.digestEnabled) {
         this.digestCalculator = new DigestCalculator();
      }

   }

   public static void setFailCreate(boolean b) {
      failCreate = b;
   }

   public void run() {
      LOG.info(String.format("PrepRequestProcessor (sid:%d) started, reconfigEnabled=%s", this.zks.getServerId(), this.zks.reconfigEnabled));

      try {
         while(true) {
            ServerMetrics.getMetrics().PREP_PROCESSOR_QUEUE_SIZE.add((long)this.submittedRequests.size());
            Request request = (Request)this.submittedRequests.take();
            ServerMetrics.getMetrics().PREP_PROCESSOR_QUEUE_TIME.add(Time.currentElapsedTime() - request.prepQueueStartTime);
            if (LOG.isTraceEnabled()) {
               long traceMask = 2L;
               if (request.type == 11) {
                  traceMask = 8L;
               }

               ZooTrace.logRequest(LOG, traceMask, 'P', request, "");
            }

            if (Request.requestOfDeath == request) {
               break;
            }

            request.prepStartTime = Time.currentElapsedTime();
            this.pRequest(request);
         }
      } catch (Exception e) {
         this.handleException(this.getName(), e);
      }

      LOG.info("PrepRequestProcessor exited loop!");
   }

   private ZooKeeperServer.ChangeRecord getRecordForPath(String path) throws KeeperException.NoNodeException {
      ZooKeeperServer.ChangeRecord lastChange = null;
      synchronized(this.zks.outstandingChanges) {
         lastChange = (ZooKeeperServer.ChangeRecord)this.zks.outstandingChangesForPath.get(path);
         if (lastChange == null) {
            DataNode n = this.zks.getZKDatabase().getNode(path);
            if (n != null) {
               Set<String> children;
               synchronized(n) {
                  children = n.getChildren();
               }

               lastChange = new ZooKeeperServer.ChangeRecord(-1L, path, n.stat, children.size(), this.zks.getZKDatabase().aclForNode(n));
               if (this.digestEnabled) {
                  lastChange.precalculatedDigest = new ZooKeeperServer.PrecalculatedDigest(this.digestCalculator.calculateDigest(path, n), 0L);
               }

               lastChange.data = n.getData();
            }
         }
      }

      if (lastChange != null && lastChange.stat != null) {
         return lastChange;
      } else {
         throw new KeeperException.NoNodeException(path);
      }
   }

   private ZooKeeperServer.ChangeRecord getOutstandingChange(String path) {
      synchronized(this.zks.outstandingChanges) {
         return (ZooKeeperServer.ChangeRecord)this.zks.outstandingChangesForPath.get(path);
      }
   }

   protected void addChangeRecord(ZooKeeperServer.ChangeRecord c) {
      synchronized(this.zks.outstandingChanges) {
         this.zks.outstandingChanges.add(c);
         this.zks.outstandingChangesForPath.put(c.path, c);
         ServerMetrics.getMetrics().OUTSTANDING_CHANGES_QUEUED.add(1L);
      }
   }

   private Map getPendingChanges(MultiOperationRecord multiRequest) {
      Map<String, ZooKeeperServer.ChangeRecord> pendingChangeRecords = new HashMap();

      for(Op op : multiRequest) {
         String path = op.getPath();
         ZooKeeperServer.ChangeRecord cr = this.getOutstandingChange(path);
         if (cr != null) {
            pendingChangeRecords.put(path, cr);
         }

         int lastSlash = path.lastIndexOf(47);
         if (lastSlash != -1 && path.indexOf(0) == -1) {
            String parentPath = path.substring(0, lastSlash);
            ZooKeeperServer.ChangeRecord parentCr = this.getOutstandingChange(parentPath);
            if (parentCr != null) {
               pendingChangeRecords.put(parentPath, parentCr);
            }
         }
      }

      return pendingChangeRecords;
   }

   void rollbackPendingChanges(long zxid, Map pendingChangeRecords) {
      synchronized(this.zks.outstandingChanges) {
         Iterator<ZooKeeperServer.ChangeRecord> iter = this.zks.outstandingChanges.descendingIterator();

         while(iter.hasNext()) {
            ZooKeeperServer.ChangeRecord c = (ZooKeeperServer.ChangeRecord)iter.next();
            if (c.zxid != zxid) {
               break;
            }

            iter.remove();
            this.zks.outstandingChangesForPath.remove(c.path);
         }

         if (!this.zks.outstandingChanges.isEmpty()) {
            long firstZxid = ((ZooKeeperServer.ChangeRecord)this.zks.outstandingChanges.peek()).zxid;

            for(ZooKeeperServer.ChangeRecord c : pendingChangeRecords.values()) {
               if (c.zxid >= firstZxid) {
                  this.zks.outstandingChangesForPath.put(c.path, c);
               }
            }

         }
      }
   }

   private String validatePathForCreate(String path, long sessionId) throws KeeperException.BadArgumentsException {
      int lastSlash = path.lastIndexOf(47);
      if (lastSlash != -1 && path.indexOf(0) == -1 && !failCreate) {
         return path.substring(0, lastSlash);
      } else {
         LOG.info("Invalid path {} with session 0x{}", path, Long.toHexString(sessionId));
         throw new KeeperException.BadArgumentsException(path);
      }
   }

   protected void pRequest2Txn(int type, long zxid, Request request, Record record) throws KeeperException, IOException, RequestProcessor.RequestProcessorException {
      if (request.getHdr() == null) {
         request.setHdr(new TxnHeader(request.sessionId, request.cxid, zxid, Time.currentWallTime(), type));
      }

      switch (type) {
         case -11:
            long startTime = Time.currentElapsedTime();
            synchronized(this.zks.outstandingChanges) {
               Set<String> es = this.zks.getZKDatabase().getEphemerals(request.sessionId);

               for(ZooKeeperServer.ChangeRecord c : this.zks.outstandingChanges) {
                  if (c.stat == null) {
                     es.remove(c.path);
                  } else if (c.stat.getEphemeralOwner() == request.sessionId) {
                     es.add(c.path);
                  }
               }

               for(String path2Delete : es) {
                  if (this.digestEnabled) {
                     String parentPath = this.getParentPathAndValidate(path2Delete);
                     ZooKeeperServer.ChangeRecord parentRecord = this.getRecordForPath(parentPath);
                     parentRecord = parentRecord.duplicate(request.getHdr().getZxid());
                     parentRecord.stat.setPzxid(request.getHdr().getZxid());
                     parentRecord.precalculatedDigest = this.precalculateDigest(PrepRequestProcessor.DigestOpCode.UPDATE, parentPath, parentRecord.data, parentRecord.stat);
                     this.addChangeRecord(parentRecord);
                  }

                  ZooKeeperServer.ChangeRecord nodeRecord = new ZooKeeperServer.ChangeRecord(request.getHdr().getZxid(), path2Delete, (StatPersisted)null, 0, (List)null);
                  nodeRecord.precalculatedDigest = this.precalculateDigest(PrepRequestProcessor.DigestOpCode.REMOVE, path2Delete);
                  this.addChangeRecord(nodeRecord);
               }

               if (ZooKeeperServer.isCloseSessionTxnEnabled()) {
                  request.setTxn(new CloseSessionTxn(new ArrayList(es)));
               }

               this.zks.sessionTracker.setSessionClosing(request.sessionId);
            }

            ServerMetrics.getMetrics().CLOSE_SESSION_PREP_TIME.add(Time.currentElapsedTime() - startTime);
            break;
         case -10:
            CreateSessionTxn createSessionTxn = (CreateSessionTxn)request.readRequestRecord(CreateSessionTxn::new);
            request.setTxn(createSessionTxn);
            this.zks.sessionTracker.trackSession(request.sessionId, createSessionTxn.getTimeOut());
            this.zks.setOwner(request.sessionId, request.getOwner());
            break;
         case -9:
         case -8:
         case -7:
         case -6:
         case -5:
         case -4:
         case -3:
         case -2:
         case -1:
         case 0:
         case 3:
         case 4:
         case 6:
         case 8:
         case 9:
         case 10:
         case 11:
         case 12:
         case 14:
         case 17:
         case 18:
         default:
            LOG.warn("unknown type {}", type);
            break;
         case 1:
         case 15:
         case 19:
         case 21:
            this.pRequest2TxnCreate(type, request, record);
            break;
         case 2:
            this.zks.sessionTracker.checkSession(request.sessionId, request.getOwner());
            DeleteRequest deleteRequest = (DeleteRequest)record;
            String path = deleteRequest.getPath();
            String parentPath = this.getParentPathAndValidate(path);
            ZooKeeperServer.ChangeRecord parentRecord = this.getRecordForPath(parentPath);
            this.zks.checkACL(request.cnxn, parentRecord.acl, 8, request.authInfo, path, (List)null);
            ZooKeeperServer.ChangeRecord nodeRecord = this.getRecordForPath(path);
            checkAndIncVersion(nodeRecord.stat.getVersion(), deleteRequest.getVersion(), path);
            if (nodeRecord.childCount > 0) {
               throw new KeeperException.NotEmptyException(path);
            }

            request.setTxn(new DeleteTxn(path));
            parentRecord = parentRecord.duplicate(request.getHdr().getZxid());
            --parentRecord.childCount;
            parentRecord.stat.setPzxid(request.getHdr().getZxid());
            parentRecord.precalculatedDigest = this.precalculateDigest(PrepRequestProcessor.DigestOpCode.UPDATE, parentPath, parentRecord.data, parentRecord.stat);
            this.addChangeRecord(parentRecord);
            nodeRecord = new ZooKeeperServer.ChangeRecord(request.getHdr().getZxid(), path, (StatPersisted)null, -1, (List)null);
            nodeRecord.precalculatedDigest = this.precalculateDigest(PrepRequestProcessor.DigestOpCode.REMOVE, path);
            this.setTxnDigest(request, nodeRecord.precalculatedDigest);
            this.addChangeRecord(nodeRecord);
            break;
         case 5:
            this.zks.sessionTracker.checkSession(request.sessionId, request.getOwner());
            SetDataRequest setDataRequest = (SetDataRequest)record;
            String path = setDataRequest.getPath();
            this.validatePath(path, request.sessionId);
            ZooKeeperServer.ChangeRecord nodeRecord = this.getRecordForPath(path);
            this.zks.checkACL(request.cnxn, nodeRecord.acl, 2, request.authInfo, path, (List)null);
            this.zks.checkQuota(path, nodeRecord.data, setDataRequest.getData(), 5);
            int newVersion = checkAndIncVersion(nodeRecord.stat.getVersion(), setDataRequest.getVersion(), path);
            request.setTxn(new SetDataTxn(path, setDataRequest.getData(), newVersion));
            nodeRecord = nodeRecord.duplicate(request.getHdr().getZxid());
            nodeRecord.stat.setVersion(newVersion);
            nodeRecord.stat.setMtime(request.getHdr().getTime());
            nodeRecord.stat.setMzxid(zxid);
            nodeRecord.data = setDataRequest.getData();
            nodeRecord.precalculatedDigest = this.precalculateDigest(PrepRequestProcessor.DigestOpCode.UPDATE, path, nodeRecord.data, nodeRecord.stat);
            this.setTxnDigest(request, nodeRecord.precalculatedDigest);
            this.addChangeRecord(nodeRecord);
            break;
         case 7:
            this.zks.sessionTracker.checkSession(request.sessionId, request.getOwner());
            SetACLRequest setAclRequest = (SetACLRequest)record;
            String path = setAclRequest.getPath();
            this.validatePath(path, request.sessionId);
            List<ACL> listACL = fixupACL(path, request.authInfo, setAclRequest.getAcl());
            ZooKeeperServer.ChangeRecord nodeRecord = this.getRecordForPath(path);
            this.zks.checkACL(request.cnxn, nodeRecord.acl, 16, request.authInfo, path, listACL);
            int newVersion = checkAndIncVersion(nodeRecord.stat.getAversion(), setAclRequest.getVersion(), path);
            request.setTxn(new SetACLTxn(path, listACL, newVersion));
            nodeRecord = nodeRecord.duplicate(request.getHdr().getZxid());
            nodeRecord.stat.setAversion(newVersion);
            nodeRecord.precalculatedDigest = this.precalculateDigest(PrepRequestProcessor.DigestOpCode.UPDATE, path, nodeRecord.data, nodeRecord.stat);
            this.setTxnDigest(request, nodeRecord.precalculatedDigest);
            this.addChangeRecord(nodeRecord);
            break;
         case 13:
            this.zks.sessionTracker.checkSession(request.sessionId, request.getOwner());
            CheckVersionRequest checkVersionRequest = (CheckVersionRequest)record;
            String path = checkVersionRequest.getPath();
            this.validatePath(path, request.sessionId);
            ZooKeeperServer.ChangeRecord nodeRecord = this.getRecordForPath(path);
            this.zks.checkACL(request.cnxn, nodeRecord.acl, 1, request.authInfo, path, (List)null);
            request.setTxn(new CheckVersionTxn(path, checkAndIncVersion(nodeRecord.stat.getVersion(), checkVersionRequest.getVersion(), path)));
            break;
         case 16:
            if (!this.zks.isReconfigEnabled()) {
               LOG.error("Reconfig operation requested but reconfig feature is disabled.");
               throw new KeeperException.ReconfigDisabledException();
            }

            if (ZooKeeperServer.skipACL) {
               LOG.warn("skipACL is set, reconfig operation will skip ACL checks!");
            }

            this.zks.sessionTracker.checkSession(request.sessionId, request.getOwner());

            LeaderZooKeeperServer lzks;
            try {
               lzks = (LeaderZooKeeperServer)this.zks;
            } catch (ClassCastException var33) {
               throw new KeeperException.UnimplementedException();
            }

            QuorumVerifier lastSeenQV = lzks.self.getLastSeenQuorumVerifier();
            if (lastSeenQV.getVersion() != lzks.self.getQuorumVerifier().getVersion()) {
               throw new KeeperException.ReconfigInProgress();
            }

            ReconfigRequest reconfigRequest = (ReconfigRequest)record;
            long configId = reconfigRequest.getCurConfigId();
            if (configId != -1L && configId != lzks.self.getLastSeenQuorumVerifier().getVersion()) {
               String msg = "Reconfiguration from version " + configId + " failed -- last seen version is " + lzks.self.getLastSeenQuorumVerifier().getVersion();
               throw new KeeperException.BadVersionException(msg);
            }

            String newMembers = reconfigRequest.getNewMembers();
            if (newMembers != null) {
               LOG.info("Non-incremental reconfig");
               newMembers = newMembers.replaceAll(",", "\n");

               try {
                  Properties props = new Properties();
                  props.load(new StringReader(newMembers));
                  request.qv = QuorumPeerConfig.parseDynamicConfig(props, lzks.self.getElectionType(), true, false, lastSeenQV.getOraclePath());
                  request.qv.setVersion(request.getHdr().getZxid());
               } catch (QuorumPeerConfig.ConfigException | IOException e) {
                  throw new KeeperException.BadArgumentsException(((Exception)e).getMessage());
               }
            } else {
               LOG.info("Incremental reconfig");
               List<String> joiningServers = null;
               String joiningServersString = reconfigRequest.getJoiningServers();
               if (joiningServersString != null) {
                  joiningServers = StringUtils.split(joiningServersString, ",");
               }

               List<String> leavingServers = null;
               String leavingServersString = reconfigRequest.getLeavingServers();
               if (leavingServersString != null) {
                  leavingServers = StringUtils.split(leavingServersString, ",");
               }

               if (!(lastSeenQV instanceof QuorumMaj) && !(lastSeenQV instanceof QuorumOracleMaj)) {
                  String msg = "Incremental reconfiguration requested but last configuration seen has a non-majority quorum system";
                  LOG.warn(msg);
                  throw new KeeperException.BadArgumentsException(msg);
               }

               Map<Long, QuorumPeer.QuorumServer> nextServers = new HashMap(lastSeenQV.getAllMembers());

               try {
                  if (leavingServers != null) {
                     for(String leaving : leavingServers) {
                        long sid = Long.parseLong(leaving);
                        nextServers.remove(sid);
                     }
                  }

                  if (joiningServers != null) {
                     for(String joiner : joiningServers) {
                        String[] parts = (String[])StringUtils.split(joiner, "=").toArray(new String[0]);
                        if (parts.length != 2) {
                           throw new KeeperException.BadArgumentsException("Wrong format of server string");
                        }

                        Long sid = Long.parseLong(parts[0].substring(parts[0].lastIndexOf(46) + 1));
                        QuorumPeer.QuorumServer qs = new QuorumPeer.QuorumServer(sid, parts[1]);
                        if (qs.clientAddr == null || qs.electionAddr == null || qs.addr == null) {
                           throw new KeeperException.BadArgumentsException("Wrong format of server string - each server should have 3 ports specified");
                        }

                        for(QuorumPeer.QuorumServer nqs : nextServers.values()) {
                           if (qs.id != nqs.id) {
                              qs.checkAddressDuplicate(nqs);
                           }
                        }

                        nextServers.remove(qs.id);
                        nextServers.put(qs.id, qs);
                     }
                  }
               } catch (QuorumPeerConfig.ConfigException var35) {
                  throw new KeeperException.BadArgumentsException("Reconfiguration failed");
               }

               if (lastSeenQV instanceof QuorumMaj) {
                  request.qv = new QuorumMaj(nextServers);
               } else {
                  request.qv = new QuorumOracleMaj(nextServers, lastSeenQV.getOraclePath());
               }

               request.qv.setVersion(request.getHdr().getZxid());
            }

            if (QuorumPeerConfig.isStandaloneEnabled() && request.qv.getVotingMembers().size() < 2) {
               String msg = "Reconfig failed - new configuration must include at least 2 followers";
               LOG.warn(msg);
               throw new KeeperException.BadArgumentsException(msg);
            }

            if (request.qv.getVotingMembers().size() < 1) {
               String msg = "Reconfig failed - new configuration must include at least 1 follower";
               LOG.warn(msg);
               throw new KeeperException.BadArgumentsException(msg);
            }

            if (!lzks.getLeader().isQuorumSynced(request.qv)) {
               String msg2 = "Reconfig failed - there must be a connected and synced quorum in new configuration";
               LOG.warn(msg2);
               throw new KeeperException.NewConfigNoQuorum();
            }

            ZooKeeperServer.ChangeRecord nodeRecord = this.getRecordForPath("/zookeeper/config");
            this.zks.checkACL(request.cnxn, nodeRecord.acl, 2, request.authInfo, (String)null, (List)null);
            SetDataTxn setDataTxn = new SetDataTxn("/zookeeper/config", request.qv.toString().getBytes(), -1);
            request.setTxn(setDataTxn);
            nodeRecord = nodeRecord.duplicate(request.getHdr().getZxid());
            nodeRecord.stat.setVersion(-1);
            nodeRecord.stat.setMtime(request.getHdr().getTime());
            nodeRecord.stat.setMzxid(zxid);
            nodeRecord.data = setDataTxn.getData();
            nodeRecord.precalculatedDigest = this.precalculateDigest(PrepRequestProcessor.DigestOpCode.NOOP, "/zookeeper/config", nodeRecord.data, nodeRecord.stat);
            this.setTxnDigest(request, nodeRecord.precalculatedDigest);
            this.addChangeRecord(nodeRecord);
            break;
         case 20:
            DeleteContainerRequest txn = (DeleteContainerRequest)record;
            String path = txn.getPath();
            String parentPath = this.getParentPathAndValidate(path);
            ZooKeeperServer.ChangeRecord nodeRecord = this.getRecordForPath(path);
            if (nodeRecord.childCount > 0) {
               throw new KeeperException.NotEmptyException(path);
            }

            if (EphemeralType.get(nodeRecord.stat.getEphemeralOwner()) == EphemeralType.NORMAL) {
               throw new KeeperException.BadVersionException(path);
            }

            ZooKeeperServer.ChangeRecord parentRecord = this.getRecordForPath(parentPath);
            request.setTxn(new DeleteTxn(path));
            parentRecord = parentRecord.duplicate(request.getHdr().getZxid());
            --parentRecord.childCount;
            parentRecord.stat.setPzxid(request.getHdr().getZxid());
            parentRecord.precalculatedDigest = this.precalculateDigest(PrepRequestProcessor.DigestOpCode.UPDATE, parentPath, parentRecord.data, parentRecord.stat);
            this.addChangeRecord(parentRecord);
            nodeRecord = new ZooKeeperServer.ChangeRecord(request.getHdr().getZxid(), path, (StatPersisted)null, -1, (List)null);
            nodeRecord.precalculatedDigest = this.precalculateDigest(PrepRequestProcessor.DigestOpCode.REMOVE, path);
            this.setTxnDigest(request, nodeRecord.precalculatedDigest);
            this.addChangeRecord(nodeRecord);
      }

      if (request.getTxnDigest() == null && this.digestEnabled) {
         this.setTxnDigest(request);
      }

   }

   private void pRequest2TxnCreate(int type, Request request, Record record) throws IOException, KeeperException {
      int flags;
      String path;
      List<ACL> acl;
      byte[] data;
      long ttl;
      if (type == 21) {
         CreateTTLRequest createTtlRequest = (CreateTTLRequest)record;
         flags = createTtlRequest.getFlags();
         path = createTtlRequest.getPath();
         acl = createTtlRequest.getAcl();
         data = createTtlRequest.getData();
         ttl = createTtlRequest.getTtl();
      } else {
         CreateRequest createRequest = (CreateRequest)record;
         flags = createRequest.getFlags();
         path = createRequest.getPath();
         acl = createRequest.getAcl();
         data = createRequest.getData();
         ttl = -1L;
      }

      CreateMode createMode = CreateMode.fromFlag(flags);
      this.validateCreateRequest(path, createMode, request, ttl);
      String parentPath = this.validatePathForCreate(path, request.sessionId);
      List<ACL> listACL = fixupACL(path, request.authInfo, acl);
      ZooKeeperServer.ChangeRecord parentRecord = this.getRecordForPath(parentPath);
      this.zks.checkACL(request.cnxn, parentRecord.acl, 4, request.authInfo, path, listACL);
      int parentCVersion = parentRecord.stat.getCversion();
      if (createMode.isSequential()) {
         path = path + String.format(Locale.ENGLISH, "%010d", parentCVersion);
      }

      this.validatePath(path, request.sessionId);

      try {
         if (this.getRecordForPath(path) != null) {
            throw new KeeperException.NodeExistsException(path);
         }
      } catch (KeeperException.NoNodeException var22) {
      }

      boolean ephemeralParent = EphemeralType.get(parentRecord.stat.getEphemeralOwner()) == EphemeralType.NORMAL;
      if (ephemeralParent) {
         throw new KeeperException.NoChildrenForEphemeralsException(path);
      } else {
         int newCversion = parentRecord.stat.getCversion() + 1;
         this.zks.checkQuota(path, (byte[])null, data, 1);
         if (type == 19) {
            request.setTxn(new CreateContainerTxn(path, data, listACL, newCversion));
         } else if (type == 21) {
            request.setTxn(new CreateTTLTxn(path, data, listACL, newCversion, ttl));
         } else {
            request.setTxn(new CreateTxn(path, data, listACL, createMode.isEphemeral(), newCversion));
         }

         TxnHeader hdr = request.getHdr();
         long ephemeralOwner = 0L;
         if (createMode.isContainer()) {
            ephemeralOwner = Long.MIN_VALUE;
         } else if (createMode.isTTL()) {
            ephemeralOwner = EphemeralType.TTL.toEphemeralOwner(ttl);
         } else if (createMode.isEphemeral()) {
            ephemeralOwner = request.sessionId;
         }

         StatPersisted s = DataTree.createStat(hdr.getZxid(), hdr.getTime(), ephemeralOwner);
         parentRecord = parentRecord.duplicate(request.getHdr().getZxid());
         ++parentRecord.childCount;
         parentRecord.stat.setCversion(newCversion);
         parentRecord.stat.setPzxid(request.getHdr().getZxid());
         parentRecord.precalculatedDigest = this.precalculateDigest(PrepRequestProcessor.DigestOpCode.UPDATE, parentPath, parentRecord.data, parentRecord.stat);
         this.addChangeRecord(parentRecord);
         ZooKeeperServer.ChangeRecord nodeRecord = new ZooKeeperServer.ChangeRecord(request.getHdr().getZxid(), path, s, 0, listACL);
         nodeRecord.data = data;
         nodeRecord.precalculatedDigest = this.precalculateDigest(PrepRequestProcessor.DigestOpCode.ADD, path, nodeRecord.data, s);
         this.setTxnDigest(request, nodeRecord.precalculatedDigest);
         this.addChangeRecord(nodeRecord);
      }
   }

   private void validatePath(String path, long sessionId) throws KeeperException.BadArgumentsException {
      try {
         PathUtils.validatePath(path);
      } catch (IllegalArgumentException ie) {
         LOG.info("Invalid path {} with session 0x{}, reason: {}", new Object[]{path, Long.toHexString(sessionId), ie.getMessage()});
         throw new KeeperException.BadArgumentsException(path);
      }
   }

   private String getParentPathAndValidate(String path) throws KeeperException.BadArgumentsException {
      int lastSlash = path.lastIndexOf(47);
      if (lastSlash != -1 && path.indexOf(0) == -1 && !this.zks.getZKDatabase().isSpecialPath(path)) {
         return path.substring(0, lastSlash);
      } else {
         throw new KeeperException.BadArgumentsException(path);
      }
   }

   private static int checkAndIncVersion(int currentVersion, int expectedVersion, String path) throws KeeperException.BadVersionException {
      if (expectedVersion != -1 && expectedVersion != currentVersion) {
         throw new KeeperException.BadVersionException(path);
      } else {
         return currentVersion + 1;
      }
   }

   protected void pRequest(Request request) throws RequestProcessor.RequestProcessorException {
      request.setHdr((TxnHeader)null);
      request.setTxn((Record)null);
      if (!request.isThrottled()) {
         this.pRequestHelper(request);
      }

      request.zxid = this.zks.getZxid();
      long timeFinishedPrepare = Time.currentElapsedTime();
      long var10001 = timeFinishedPrepare - request.prepStartTime;
      ServerMetrics.getMetrics().PREP_PROCESS_TIME.add(var10001);
      this.nextProcessor.processRequest(request);
      ServerMetrics.getMetrics().PROPOSAL_PROCESS_TIME.add(Time.currentElapsedTime() - timeFinishedPrepare);
   }

   private void pRequestHelper(Request request) {
      try {
         switch (request.type) {
            case -11:
            case -10:
               if (!request.isLocalSession()) {
                  this.pRequest2Txn(request.type, this.zks.getNextZxid(), request, (Record)null);
               }
               break;
            case -9:
            case -8:
            case -7:
            case -6:
            case -5:
            case -4:
            case -3:
            case -2:
            case -1:
            case 0:
            case 10:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 102:
            default:
               LOG.warn("unknown type {}", request.type);
               break;
            case 1:
            case 15:
            case 19:
               CreateRequest create2Request = (CreateRequest)request.readRequestRecord(CreateRequest::new);
               this.pRequest2Txn(request.type, this.zks.getNextZxid(), request, create2Request);
               break;
            case 2:
               DeleteRequest deleteRequest = (DeleteRequest)request.readRequestRecord(DeleteRequest::new);
               this.pRequest2Txn(request.type, this.zks.getNextZxid(), request, deleteRequest);
               break;
            case 3:
            case 4:
            case 6:
            case 8:
            case 9:
            case 11:
            case 12:
            case 17:
            case 18:
            case 22:
            case 101:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
               this.zks.sessionTracker.checkSession(request.sessionId, request.getOwner());
               break;
            case 5:
               SetDataRequest setDataRequest = (SetDataRequest)request.readRequestRecord(SetDataRequest::new);
               this.pRequest2Txn(request.type, this.zks.getNextZxid(), request, setDataRequest);
               break;
            case 7:
               SetACLRequest setAclRequest = (SetACLRequest)request.readRequestRecord(SetACLRequest::new);
               this.pRequest2Txn(request.type, this.zks.getNextZxid(), request, setAclRequest);
               break;
            case 13:
               CheckVersionRequest checkRequest = (CheckVersionRequest)request.readRequestRecord(CheckVersionRequest::new);
               this.pRequest2Txn(request.type, this.zks.getNextZxid(), request, checkRequest);
               break;
            case 14:
               MultiOperationRecord multiRequest;
               try {
                  multiRequest = (MultiOperationRecord)request.readRequestRecord(MultiOperationRecord::new);
               } catch (IOException e) {
                  request.setHdr(new TxnHeader(request.sessionId, request.cxid, this.zks.getNextZxid(), Time.currentWallTime(), 14));
                  throw e;
               }

               List<Txn> txns = new ArrayList();
               long zxid = this.zks.getNextZxid();
               KeeperException ke = null;
               Map<String, ZooKeeperServer.ChangeRecord> pendingChanges = this.getPendingChanges(multiRequest);
               request.setHdr(new TxnHeader(request.sessionId, request.cxid, zxid, Time.currentWallTime(), request.type));

               for(Op op : multiRequest) {
                  Record subrequest = op.toRequestRecord();
                  int type;
                  Record txn;
                  if (ke != null) {
                     type = -1;
                     txn = new ErrorTxn(KeeperException.Code.RUNTIMEINCONSISTENCY.intValue());
                  } else {
                     try {
                        this.pRequest2Txn(op.getType(), zxid, request, subrequest);
                        type = op.getType();
                        txn = request.getTxn();
                     } catch (KeeperException var27) {
                        ke = var27;
                        type = -1;
                        txn = new ErrorTxn(var27.code().intValue());
                        if (var27.code().intValue() > KeeperException.Code.APIERROR.intValue()) {
                           LOG.info("Got user-level KeeperException when processing {} aborting remaining multi ops. Error Path:{} Error:{}", new Object[]{request.toString(), var27.getPath(), var27.getMessage()});
                        }

                        request.setException(var27);
                        this.rollbackPendingChanges(zxid, pendingChanges);
                     }
                  }

                  ByteArrayOutputStream baos = new ByteArrayOutputStream();

                  try {
                     BinaryOutputArchive boa = BinaryOutputArchive.getArchive(baos);
                     txn.serialize(boa, "request");
                     ByteBuffer bb = ByteBuffer.wrap(baos.toByteArray());
                     txns.add(new Txn(type, bb.array()));
                  } catch (Throwable var25) {
                     try {
                        baos.close();
                     } catch (Throwable var24) {
                        var25.addSuppressed(var24);
                     }

                     throw var25;
                  }

                  baos.close();
               }

               request.setTxn(new MultiTxn(txns));
               if (this.digestEnabled) {
                  this.setTxnDigest(request);
               }
               break;
            case 16:
               ReconfigRequest reconfigRequest = (ReconfigRequest)request.readRequestRecord(ReconfigRequest::new);
               this.pRequest2Txn(request.type, this.zks.getNextZxid(), request, reconfigRequest);
               break;
            case 20:
               DeleteContainerRequest deleteContainerRequest = (DeleteContainerRequest)request.readRequestRecord(DeleteContainerRequest::new);
               this.pRequest2Txn(request.type, this.zks.getNextZxid(), request, deleteContainerRequest);
               break;
            case 21:
               CreateTTLRequest createTtlRequest = (CreateTTLRequest)request.readRequestRecord(CreateTTLRequest::new);
               this.pRequest2Txn(request.type, this.zks.getNextZxid(), request, createTtlRequest);
         }
      } catch (KeeperException var28) {
         if (request.getHdr() != null) {
            request.getHdr().setType(-1);
            request.setTxn(new ErrorTxn(var28.code().intValue()));
         }

         if (var28.code().intValue() > KeeperException.Code.APIERROR.intValue()) {
            LOG.info("Got user-level KeeperException when processing {} Error Path:{} Error:{}", new Object[]{request.toString(), var28.getPath(), var28.getMessage()});
         }

         request.setException(var28);
      } catch (Exception e) {
         LOG.error("Failed to process {}", request, e);
         String digest = request.requestDigest();
         LOG.error("Dumping request buffer for request type {}: 0x{}", Request.op2String(request.type), digest);
         if (request.getHdr() == null) {
            request.setHdr(new TxnHeader(request.sessionId, request.cxid, this.zks.getZxid(), Time.currentWallTime(), request.type));
         }

         request.getHdr().setType(-1);
         request.setTxn(new ErrorTxn(KeeperException.Code.MARSHALLINGERROR.intValue()));
      }

   }

   private static List removeDuplicates(List acls) {
      if (acls != null && !acls.isEmpty()) {
         ArrayList<ACL> retval = new ArrayList(acls.size());

         for(ACL acl : acls) {
            if (!retval.contains(acl)) {
               retval.add(acl);
            }
         }

         return retval;
      } else {
         return Collections.emptyList();
      }
   }

   private void validateCreateRequest(String path, CreateMode createMode, Request request, long ttl) throws KeeperException {
      if (createMode.isTTL() && !EphemeralType.extendedEphemeralTypesEnabled()) {
         throw new KeeperException.UnimplementedException();
      } else {
         try {
            EphemeralType.validateTTL(createMode, ttl);
         } catch (IllegalArgumentException var7) {
            throw new KeeperException.BadArgumentsException(path);
         }

         if (createMode.isEphemeral()) {
            if (request.getException() != null) {
               throw request.getException();
            }

            this.zks.sessionTracker.checkGlobalSession(request.sessionId, request.getOwner());
         } else {
            this.zks.sessionTracker.checkSession(request.sessionId, request.getOwner());
         }

      }
   }

   public static List fixupACL(String path, List authInfo, List acls) throws KeeperException.InvalidACLException {
      List<ACL> uniqacls = removeDuplicates(acls);
      if (uniqacls != null && uniqacls.size() != 0) {
         List<ACL> rv = new ArrayList();

         for(ACL a : uniqacls) {
            LOG.debug("Processing ACL: {}", a);
            if (a == null) {
               throw new KeeperException.InvalidACLException(path);
            }

            Id id = a.getId();
            if (id == null || id.getScheme() == null) {
               throw new KeeperException.InvalidACLException(path);
            }

            if (id.getScheme().equals("world") && id.getId().equals("anyone")) {
               rv.add(a);
            } else if (!id.getScheme().equals("auth")) {
               ServerAuthenticationProvider ap = ProviderRegistry.getServerProvider(id.getScheme());
               if (ap == null || !ap.isValid(id.getId())) {
                  throw new KeeperException.InvalidACLException(path);
               }

               rv.add(a);
            } else {
               boolean authIdValid = false;

               for(Id cid : authInfo) {
                  ServerAuthenticationProvider ap = ProviderRegistry.getServerProvider(cid.getScheme());
                  if (ap == null) {
                     LOG.error("Missing AuthenticationProvider for {}", cid.getScheme());
                  } else if (ap.isAuthenticated()) {
                     authIdValid = true;
                     rv.add(new ACL(a.getPerms(), cid));
                  }
               }

               if (!authIdValid) {
                  throw new KeeperException.InvalidACLException(path);
               }
            }
         }

         return rv;
      } else {
         throw new KeeperException.InvalidACLException(path);
      }
   }

   public void processRequest(Request request) {
      request.prepQueueStartTime = Time.currentElapsedTime();
      this.submittedRequests.add(request);
      ServerMetrics.getMetrics().PREP_PROCESSOR_QUEUED.add(1L);
   }

   public void shutdown() {
      LOG.info("Shutting down");
      this.submittedRequests.clear();
      this.submittedRequests.add(Request.requestOfDeath);
      this.nextProcessor.shutdown();
   }

   private ZooKeeperServer.PrecalculatedDigest precalculateDigest(DigestOpCode type, String path, byte[] data, StatPersisted s) throws KeeperException.NoNodeException {
      if (!this.digestEnabled) {
         return null;
      } else {
         long prevNodeDigest;
         long newNodeDigest;
         switch (type) {
            case ADD:
               prevNodeDigest = 0L;
               newNodeDigest = this.digestCalculator.calculateDigest(path, data, s);
               break;
            case REMOVE:
               prevNodeDigest = this.getRecordForPath(path).precalculatedDigest.nodeDigest;
               newNodeDigest = 0L;
               break;
            case UPDATE:
               prevNodeDigest = this.getRecordForPath(path).precalculatedDigest.nodeDigest;
               newNodeDigest = this.digestCalculator.calculateDigest(path, data, s);
               break;
            case NOOP:
               prevNodeDigest = 0L;
               newNodeDigest = 0L;
               break;
            default:
               return null;
         }

         long treeDigest = this.getCurrentTreeDigest() - prevNodeDigest + newNodeDigest;
         return new ZooKeeperServer.PrecalculatedDigest(newNodeDigest, treeDigest);
      }
   }

   private ZooKeeperServer.PrecalculatedDigest precalculateDigest(DigestOpCode type, String path) throws KeeperException.NoNodeException {
      return this.precalculateDigest(type, path, (byte[])null, (StatPersisted)null);
   }

   private long getCurrentTreeDigest() {
      synchronized(this.zks.outstandingChanges) {
         long digest;
         if (this.zks.outstandingChanges.isEmpty()) {
            digest = this.zks.getZKDatabase().getDataTree().getTreeDigest();
            LOG.debug("Digest got from data tree is: {}", digest);
         } else {
            digest = ((ZooKeeperServer.ChangeRecord)this.zks.outstandingChanges.peekLast()).precalculatedDigest.treeDigest;
            LOG.debug("Digest got from outstandingChanges is: {}", digest);
         }

         return digest;
      }
   }

   private void setTxnDigest(Request request) {
      request.setTxnDigest(new TxnDigest(this.digestCalculator.getDigestVersion(), this.getCurrentTreeDigest()));
   }

   private void setTxnDigest(Request request, ZooKeeperServer.PrecalculatedDigest preCalculatedDigest) {
      if (preCalculatedDigest != null) {
         request.setTxnDigest(new TxnDigest(this.digestCalculator.getDigestVersion(), preCalculatedDigest.treeDigest));
      }
   }

   public static enum DigestOpCode {
      NOOP,
      ADD,
      REMOVE,
      UPDATE;
   }
}
