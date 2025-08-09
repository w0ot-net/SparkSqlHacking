package org.apache.zookeeper.server;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.apache.jute.Record;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.MultiOperationRecord;
import org.apache.zookeeper.MultiResponse;
import org.apache.zookeeper.Op;
import org.apache.zookeeper.OpResult;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.audit.AuditHelper;
import org.apache.zookeeper.common.Time;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.proto.AddWatchRequest;
import org.apache.zookeeper.proto.CheckWatchesRequest;
import org.apache.zookeeper.proto.Create2Response;
import org.apache.zookeeper.proto.CreateResponse;
import org.apache.zookeeper.proto.ErrorResponse;
import org.apache.zookeeper.proto.ExistsRequest;
import org.apache.zookeeper.proto.ExistsResponse;
import org.apache.zookeeper.proto.GetACLRequest;
import org.apache.zookeeper.proto.GetACLResponse;
import org.apache.zookeeper.proto.GetAllChildrenNumberRequest;
import org.apache.zookeeper.proto.GetAllChildrenNumberResponse;
import org.apache.zookeeper.proto.GetChildren2Request;
import org.apache.zookeeper.proto.GetChildren2Response;
import org.apache.zookeeper.proto.GetChildrenRequest;
import org.apache.zookeeper.proto.GetChildrenResponse;
import org.apache.zookeeper.proto.GetDataRequest;
import org.apache.zookeeper.proto.GetDataResponse;
import org.apache.zookeeper.proto.GetEphemeralsRequest;
import org.apache.zookeeper.proto.GetEphemeralsResponse;
import org.apache.zookeeper.proto.RemoveWatchesRequest;
import org.apache.zookeeper.proto.ReplyHeader;
import org.apache.zookeeper.proto.SetACLResponse;
import org.apache.zookeeper.proto.SetDataResponse;
import org.apache.zookeeper.proto.SetWatches;
import org.apache.zookeeper.proto.SetWatches2;
import org.apache.zookeeper.proto.SyncRequest;
import org.apache.zookeeper.proto.SyncResponse;
import org.apache.zookeeper.proto.WhoAmIResponse;
import org.apache.zookeeper.server.quorum.QuorumZooKeeperServer;
import org.apache.zookeeper.server.util.AuthUtil;
import org.apache.zookeeper.server.util.RequestPathMetricsCollector;
import org.apache.zookeeper.txn.ErrorTxn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FinalRequestProcessor implements RequestProcessor {
   private static final Logger LOG = LoggerFactory.getLogger(FinalRequestProcessor.class);
   private final RequestPathMetricsCollector requestPathMetricsCollector;
   ZooKeeperServer zks;

   public FinalRequestProcessor(ZooKeeperServer zks) {
      this.zks = zks;
      this.requestPathMetricsCollector = zks.getRequestPathMetricsCollector();
   }

   private DataTree.ProcessTxnResult applyRequest(Request request) {
      DataTree.ProcessTxnResult rc = this.zks.processTxn(request);
      if (request.type != -11 || !this.connClosedByClient(request) || !this.closeSession(this.zks.serverCnxnFactory, request.sessionId) && !this.closeSession(this.zks.secureServerCnxnFactory, request.sessionId)) {
         if (request.getHdr() != null) {
            long propagationLatency = Time.currentWallTime() - request.getHdr().getTime();
            if (propagationLatency >= 0L) {
               ServerMetrics.getMetrics().PROPAGATION_LATENCY.add(propagationLatency);
            }
         }

         return rc;
      } else {
         return rc;
      }
   }

   public void processRequest(Request request) {
      LOG.debug("Processing request:: {}", request);
      if (LOG.isTraceEnabled()) {
         long traceMask = 2L;
         if (request.type == 11) {
            traceMask = 128L;
         }

         ZooTrace.logRequest(LOG, traceMask, 'E', request, "");
      }

      DataTree.ProcessTxnResult rc = null;
      if (!request.isThrottled()) {
         rc = this.applyRequest(request);
      }

      if (request.cnxn != null) {
         ServerCnxn cnxn = request.cnxn;
         long lastZxid = this.zks.getZKDatabase().getDataTreeLastProcessedZxid();
         String lastOp = "NA";
         this.zks.decInProcess();
         this.zks.requestFinished(request);
         KeeperException.Code err = KeeperException.Code.OK;
         Record rsp = null;
         String path = null;
         int responseSize = 0;

         try {
            if (request.getHdr() != null && request.getHdr().getType() == -1) {
               AuditHelper.addAuditLog(request, rc, true);
               if (request.getException() != null) {
                  throw request.getException();
               }

               throw KeeperException.create(KeeperException.Code.get(((ErrorTxn)request.getTxn()).getErr()));
            }

            KeeperException ke = request.getException();
            if (ke instanceof KeeperException.SessionMovedException) {
               throw ke;
            }

            if (ke != null && request.type != 14) {
               throw ke;
            }

            LOG.debug("{}", request);
            if (request.isStale()) {
               ServerMetrics.getMetrics().STALE_REPLIES.add(1L);
            }

            if (request.isThrottled()) {
               throw KeeperException.create(KeeperException.Code.THROTTLEDOP);
            }

            AuditHelper.addAuditLog(request, rc);
            switch (request.type) {
               case -11:
                  lastOp = "CLOS";
                  err = KeeperException.Code.get(rc.err);
                  break;
               case -10:
                  lastOp = "SESS";
                  this.updateStats(request, lastOp, lastZxid);
                  this.zks.finishSessionInit(request.cnxn, true);
                  return;
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
                  break;
               case 1:
                  lastOp = "CREA";
                  rsp = new CreateResponse(rc.path);
                  err = KeeperException.Code.get(rc.err);
                  this.requestPathMetricsCollector.registerRequest(request.type, rc.path);
                  break;
               case 2:
               case 20:
                  lastOp = "DELE";
                  err = KeeperException.Code.get(rc.err);
                  this.requestPathMetricsCollector.registerRequest(request.type, rc.path);
                  break;
               case 3:
                  lastOp = "EXIS";
                  ExistsRequest existsRequest = (ExistsRequest)request.readRequestRecord(ExistsRequest::new);
                  path = existsRequest.getPath();
                  if (path.indexOf(0) != -1) {
                     throw new KeeperException.BadArgumentsException();
                  }

                  DataNode n = this.zks.getZKDatabase().getNode(path);
                  if (n != null) {
                     this.zks.checkACL(request.cnxn, this.zks.getZKDatabase().aclForNode(n), 1, request.authInfo, path, (List)null);
                  }

                  Stat stat = this.zks.getZKDatabase().statNode(path, existsRequest.getWatch() ? cnxn : null);
                  rsp = new ExistsResponse(stat);
                  this.requestPathMetricsCollector.registerRequest(request.type, path);
                  break;
               case 4:
                  lastOp = "GETD";
                  GetDataRequest getDataRequest = (GetDataRequest)request.readRequestRecord(GetDataRequest::new);
                  path = getDataRequest.getPath();
                  rsp = this.handleGetDataRequest(getDataRequest, cnxn, request.authInfo);
                  this.requestPathMetricsCollector.registerRequest(request.type, path);
                  break;
               case 5:
                  lastOp = "SETD";
                  rsp = new SetDataResponse(rc.stat);
                  err = KeeperException.Code.get(rc.err);
                  this.requestPathMetricsCollector.registerRequest(request.type, rc.path);
                  break;
               case 6:
                  lastOp = "GETA";
                  GetACLRequest getACLRequest = (GetACLRequest)request.readRequestRecord(GetACLRequest::new);
                  path = getACLRequest.getPath();
                  DataNode n = this.zks.getZKDatabase().getNode(path);
                  if (n == null) {
                     throw new KeeperException.NoNodeException();
                  }

                  this.zks.checkACL(request.cnxn, this.zks.getZKDatabase().aclForNode(n), 17, request.authInfo, path, (List)null);
                  Stat stat = new Stat();
                  List<ACL> acl = this.zks.getZKDatabase().getACL(path, stat);
                  this.requestPathMetricsCollector.registerRequest(request.type, getACLRequest.getPath());

                  try {
                     this.zks.checkACL(request.cnxn, this.zks.getZKDatabase().aclForNode(n), 16, request.authInfo, path, (List)null);
                     rsp = new GetACLResponse(acl, stat);
                  } catch (KeeperException.NoAuthException var33) {
                     List<ACL> acl1 = new ArrayList(acl.size());

                     for(ACL a : acl) {
                        if ("digest".equals(a.getId().getScheme())) {
                           Id id = a.getId();
                           Id id1 = new Id(id.getScheme(), id.getId().replaceAll(":.*", ":x"));
                           acl1.add(new ACL(a.getPerms(), id1));
                        } else {
                           acl1.add(a);
                        }
                     }

                     rsp = new GetACLResponse(acl1, stat);
                  }
                  break;
               case 7:
                  lastOp = "SETA";
                  rsp = new SetACLResponse(rc.stat);
                  err = KeeperException.Code.get(rc.err);
                  this.requestPathMetricsCollector.registerRequest(request.type, rc.path);
                  break;
               case 8:
                  lastOp = "GETC";
                  GetChildrenRequest getChildrenRequest = (GetChildrenRequest)request.readRequestRecord(GetChildrenRequest::new);
                  path = getChildrenRequest.getPath();
                  rsp = this.handleGetChildrenRequest(getChildrenRequest, cnxn, request.authInfo);
                  this.requestPathMetricsCollector.registerRequest(request.type, path);
                  break;
               case 9:
                  lastOp = "SYNC";
                  SyncRequest syncRequest = (SyncRequest)request.readRequestRecord(SyncRequest::new);
                  rsp = new SyncResponse(syncRequest.getPath());
                  this.requestPathMetricsCollector.registerRequest(request.type, syncRequest.getPath());
                  break;
               case 11:
                  lastOp = "PING";
                  this.updateStats(request, lastOp, lastZxid);
                  cnxn.sendResponse(new ReplyHeader(-2, lastZxid, 0), (Record)null, "response");
                  return;
               case 12:
                  lastOp = "GETC";
                  GetChildren2Request getChildren2Request = (GetChildren2Request)request.readRequestRecord(GetChildren2Request::new);
                  Stat stat = new Stat();
                  path = getChildren2Request.getPath();
                  DataNode n = this.zks.getZKDatabase().getNode(path);
                  if (n == null) {
                     throw new KeeperException.NoNodeException();
                  }

                  this.zks.checkACL(request.cnxn, this.zks.getZKDatabase().aclForNode(n), 1, request.authInfo, path, (List)null);
                  List<String> children = this.zks.getZKDatabase().getChildren(path, stat, getChildren2Request.getWatch() ? cnxn : null);
                  rsp = new GetChildren2Response(children, stat);
                  this.requestPathMetricsCollector.registerRequest(request.type, path);
                  break;
               case 13:
                  lastOp = "CHEC";
                  rsp = new SetDataResponse(rc.stat);
                  err = KeeperException.Code.get(rc.err);
                  break;
               case 14:
                  lastOp = "MULT";
                  rsp = new MultiResponse();

                  for(DataTree.ProcessTxnResult subTxnResult : rc.multiResult) {
                     OpResult subResult;
                     switch (subTxnResult.type) {
                        case -1:
                           subResult = new OpResult.ErrorResult(subTxnResult.err);
                           if (subTxnResult.err == KeeperException.Code.SESSIONMOVED.intValue()) {
                              throw new KeeperException.SessionMovedException();
                           }
                           break;
                        case 0:
                        case 3:
                        case 4:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 14:
                        case 16:
                        case 17:
                        case 18:
                        default:
                           throw new IOException("Invalid type of op");
                        case 1:
                           subResult = new OpResult.CreateResult(subTxnResult.path);
                           break;
                        case 2:
                        case 20:
                           subResult = new OpResult.DeleteResult();
                           break;
                        case 5:
                           subResult = new OpResult.SetDataResult(subTxnResult.stat);
                           break;
                        case 13:
                           subResult = new OpResult.CheckResult();
                           break;
                        case 15:
                        case 19:
                        case 21:
                           subResult = new OpResult.CreateResult(subTxnResult.path, subTxnResult.stat);
                     }

                     ((MultiResponse)rsp).add(subResult);
                  }
                  break;
               case 15:
               case 19:
               case 21:
                  lastOp = "CREA";
                  rsp = new Create2Response(rc.path, rc.stat);
                  err = KeeperException.Code.get(rc.err);
                  this.requestPathMetricsCollector.registerRequest(request.type, rc.path);
                  break;
               case 16:
                  lastOp = "RECO";
                  rsp = new GetDataResponse(((QuorumZooKeeperServer)this.zks).self.getQuorumVerifier().toString().getBytes(StandardCharsets.UTF_8), rc.stat);
                  err = KeeperException.Code.get(rc.err);
                  break;
               case 17:
                  lastOp = "CHKW";
                  CheckWatchesRequest checkWatches = (CheckWatchesRequest)request.readRequestRecord(CheckWatchesRequest::new);
                  Watcher.WatcherType type = Watcher.WatcherType.fromInt(checkWatches.getType());
                  path = checkWatches.getPath();
                  boolean containsWatcher = this.zks.getZKDatabase().containsWatcher(path, type, cnxn);
                  if (!containsWatcher) {
                     String msg = String.format(Locale.ENGLISH, "%s (type: %s)", path, type);
                     throw new KeeperException.NoWatcherException(msg);
                  }

                  this.requestPathMetricsCollector.registerRequest(request.type, checkWatches.getPath());
                  break;
               case 18:
                  lastOp = "REMW";
                  RemoveWatchesRequest removeWatches = (RemoveWatchesRequest)request.readRequestRecord(RemoveWatchesRequest::new);
                  Watcher.WatcherType type = Watcher.WatcherType.fromInt(removeWatches.getType());
                  path = removeWatches.getPath();
                  boolean removed = this.zks.getZKDatabase().removeWatch(path, type, cnxn);
                  if (!removed) {
                     String msg = String.format(Locale.ENGLISH, "%s (type: %s)", path, type);
                     throw new KeeperException.NoWatcherException(msg);
                  }

                  this.requestPathMetricsCollector.registerRequest(request.type, removeWatches.getPath());
                  break;
               case 22:
                  lastOp = "MLTR";
                  MultiOperationRecord multiReadRecord = (MultiOperationRecord)request.readRequestRecord(MultiOperationRecord::new);
                  rsp = new MultiResponse();

                  for(Op readOp : multiReadRecord) {
                     OpResult subResult;
                     try {
                        switch (readOp.getType()) {
                           case 4:
                              Record rec = this.handleGetDataRequest(readOp.toRequestRecord(), cnxn, request.authInfo);
                              GetDataResponse gdr = (GetDataResponse)rec;
                              subResult = new OpResult.GetDataResult(gdr.getData(), gdr.getStat());
                              break;
                           case 8:
                              Record rec = this.handleGetChildrenRequest(readOp.toRequestRecord(), cnxn, request.authInfo);
                              subResult = new OpResult.GetChildrenResult(((GetChildrenResponse)rec).getChildren());
                              break;
                           default:
                              throw new IOException("Invalid type of readOp");
                        }
                     } catch (KeeperException e) {
                        subResult = new OpResult.ErrorResult(e.code().intValue());
                     }

                     ((MultiResponse)rsp).add(subResult);
                  }
                  break;
               case 101:
                  lastOp = "SETW";
                  SetWatches setWatches = (SetWatches)request.readRequestRecord(SetWatches::new);
                  long relativeZxid = setWatches.getRelativeZxid();
                  this.zks.getZKDatabase().setWatches(relativeZxid, setWatches.getDataWatches(), setWatches.getExistWatches(), setWatches.getChildWatches(), Collections.emptyList(), Collections.emptyList(), cnxn);
                  break;
               case 103:
                  lastOp = "GETE";
                  GetEphemeralsRequest getEphemerals = (GetEphemeralsRequest)request.readRequestRecord(GetEphemeralsRequest::new);
                  String prefixPath = getEphemerals.getPrefixPath();
                  Set<String> allEphems = this.zks.getZKDatabase().getDataTree().getEphemerals(request.sessionId);
                  List<String> ephemerals = new ArrayList();
                  if (prefixPath != null && !prefixPath.trim().isEmpty() && !"/".equals(prefixPath.trim())) {
                     for(String p : allEphems) {
                        if (p.startsWith(prefixPath)) {
                           ephemerals.add(p);
                        }
                     }
                  } else {
                     ephemerals.addAll(allEphems);
                  }

                  rsp = new GetEphemeralsResponse(ephemerals);
                  break;
               case 104:
                  lastOp = "GETACN";
                  GetAllChildrenNumberRequest getAllChildrenNumberRequest = (GetAllChildrenNumberRequest)request.readRequestRecord(GetAllChildrenNumberRequest::new);
                  path = getAllChildrenNumberRequest.getPath();
                  DataNode n = this.zks.getZKDatabase().getNode(path);
                  if (n == null) {
                     throw new KeeperException.NoNodeException();
                  }

                  this.zks.checkACL(request.cnxn, this.zks.getZKDatabase().aclForNode(n), 1, request.authInfo, path, (List)null);
                  int number = this.zks.getZKDatabase().getAllChildrenNumber(path);
                  rsp = new GetAllChildrenNumberResponse(number);
                  break;
               case 105:
                  lastOp = "STW2";
                  SetWatches2 setWatches = (SetWatches2)request.readRequestRecord(SetWatches2::new);
                  long relativeZxid = setWatches.getRelativeZxid();
                  this.zks.getZKDatabase().setWatches(relativeZxid, setWatches.getDataWatches(), setWatches.getExistWatches(), setWatches.getChildWatches(), setWatches.getPersistentWatches(), setWatches.getPersistentRecursiveWatches(), cnxn);
                  break;
               case 106:
                  lastOp = "ADDW";
                  AddWatchRequest addWatcherRequest = (AddWatchRequest)request.readRequestRecord(AddWatchRequest::new);
                  this.zks.getZKDatabase().addWatch(addWatcherRequest.getPath(), cnxn, addWatcherRequest.getMode());
                  rsp = new ErrorResponse(0);
                  break;
               case 107:
                  lastOp = "HOMI";
                  rsp = new WhoAmIResponse(AuthUtil.getClientInfos(request.authInfo));
            }
         } catch (KeeperException.SessionMovedException var34) {
            cnxn.sendCloseSession();
            return;
         } catch (KeeperException e) {
            err = e.code();
         } catch (Exception e) {
            LOG.error("Failed to process {}", request, e);
            String digest = request.requestDigest();
            LOG.error("Dumping request buffer for request type {}: 0x{}", Request.op2String(request.type), digest);
            err = KeeperException.Code.MARSHALLINGERROR;
         }

         ReplyHeader hdr = new ReplyHeader(request.cxid, lastZxid, err.intValue());
         this.updateStats(request, lastOp, lastZxid);

         try {
            if (path != null && rsp != null) {
               int opCode = request.type;
               Stat stat = null;
               switch (opCode) {
                  case 4:
                     GetDataResponse getDataResponse = (GetDataResponse)rsp;
                     stat = getDataResponse.getStat();
                     responseSize = cnxn.sendResponse(hdr, rsp, "response", path, stat, opCode);
                     break;
                  case 12:
                     GetChildren2Response getChildren2Response = (GetChildren2Response)rsp;
                     stat = getChildren2Response.getStat();
                     responseSize = cnxn.sendResponse(hdr, rsp, "response", path, stat, opCode);
                     break;
                  default:
                     responseSize = cnxn.sendResponse(hdr, rsp, "response");
               }
            } else {
               responseSize = cnxn.sendResponse(hdr, rsp, "response");
            }

            if (request.type == -11) {
               cnxn.sendCloseSession();
            }
         } catch (IOException e) {
            LOG.error("FIXMSG", e);
         } finally {
            ServerMetrics.getMetrics().RESPONSE_BYTES.add((long)responseSize);
         }

      }
   }

   private Record handleGetChildrenRequest(Record request, ServerCnxn cnxn, List authInfo) throws KeeperException, IOException {
      GetChildrenRequest getChildrenRequest = (GetChildrenRequest)request;
      String path = getChildrenRequest.getPath();
      DataNode n = this.zks.getZKDatabase().getNode(path);
      if (n == null) {
         throw new KeeperException.NoNodeException();
      } else {
         this.zks.checkACL(cnxn, this.zks.getZKDatabase().aclForNode(n), 1, authInfo, path, (List)null);
         List<String> children = this.zks.getZKDatabase().getChildren(path, (Stat)null, getChildrenRequest.getWatch() ? cnxn : null);
         return new GetChildrenResponse(children);
      }
   }

   private Record handleGetDataRequest(Record request, ServerCnxn cnxn, List authInfo) throws KeeperException, IOException {
      GetDataRequest getDataRequest = (GetDataRequest)request;
      String path = getDataRequest.getPath();
      DataNode n = this.zks.getZKDatabase().getNode(path);
      if (n == null) {
         throw new KeeperException.NoNodeException();
      } else {
         this.zks.checkACL(cnxn, this.zks.getZKDatabase().aclForNode(n), 1, authInfo, path, (List)null);
         Stat stat = new Stat();
         byte[] b = this.zks.getZKDatabase().getData(path, stat, getDataRequest.getWatch() ? cnxn : null);
         return new GetDataResponse(b, stat);
      }
   }

   private boolean closeSession(ServerCnxnFactory serverCnxnFactory, long sessionId) {
      return serverCnxnFactory == null ? false : serverCnxnFactory.closeSession(sessionId, ServerCnxn.DisconnectReason.CLIENT_CLOSED_SESSION);
   }

   private boolean connClosedByClient(Request request) {
      return request.cnxn == null;
   }

   public void shutdown() {
      LOG.info("shutdown of request processor complete");
   }

   private void updateStats(Request request, String lastOp, long lastZxid) {
      if (request.cnxn != null) {
         long currentTime = Time.currentElapsedTime();
         this.zks.serverStats().updateLatency(request, currentTime);
         request.cnxn.updateStatsForResponse((long)request.cxid, lastZxid, lastOp, request.createTime, currentTime);
      }
   }
}
