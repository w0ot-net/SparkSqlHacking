package org.apache.zookeeper.audit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.MultiOperationRecord;
import org.apache.zookeeper.Op;
import org.apache.zookeeper.ZKUtil;
import org.apache.zookeeper.proto.CreateRequest;
import org.apache.zookeeper.proto.DeleteRequest;
import org.apache.zookeeper.proto.SetACLRequest;
import org.apache.zookeeper.proto.SetDataRequest;
import org.apache.zookeeper.server.DataTree;
import org.apache.zookeeper.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AuditHelper {
   private static final Logger LOG = LoggerFactory.getLogger(AuditHelper.class);

   public static void addAuditLog(Request request, DataTree.ProcessTxnResult rc) {
      addAuditLog(request, rc, false);
   }

   public static void addAuditLog(Request request, DataTree.ProcessTxnResult txnResult, boolean failedTxn) {
      if (ZKAuditProvider.isAuditEnabled()) {
         String path = txnResult.path;
         String acls = null;
         String createMode = null;

         try {
            String op;
            switch (request.type) {
               case 1:
               case 15:
               case 19:
                  op = "create";
                  CreateRequest createRequest = (CreateRequest)request.readRequestRecord(CreateRequest::new);
                  createMode = getCreateMode(createRequest);
                  if (failedTxn) {
                     path = createRequest.getPath();
                  }
                  break;
               case 2:
               case 20:
                  op = "delete";
                  if (failedTxn) {
                     DeleteRequest deleteRequest = (DeleteRequest)request.readRequestRecord(DeleteRequest::new);
                     path = deleteRequest.getPath();
                  }
                  break;
               case 3:
               case 4:
               case 6:
               case 8:
               case 9:
               case 10:
               case 11:
               case 12:
               case 13:
               case 17:
               case 18:
               default:
                  return;
               case 5:
                  op = "setData";
                  if (failedTxn) {
                     SetDataRequest setDataRequest = (SetDataRequest)request.readRequestRecord(SetDataRequest::new);
                     path = setDataRequest.getPath();
                  }
                  break;
               case 7:
                  op = "setAcl";
                  SetACLRequest setACLRequest = (SetACLRequest)request.readRequestRecord(SetACLRequest::new);
                  acls = ZKUtil.aclToString(setACLRequest.getAcl());
                  if (failedTxn) {
                     path = setACLRequest.getPath();
                  }
                  break;
               case 14:
                  if (!failedTxn) {
                     logMultiOperation(request, txnResult);
                     return;
                  }

                  op = "multiOperation";
                  break;
               case 16:
                  op = "reconfig";
            }

            AuditEvent.Result result = getResult(txnResult, failedTxn);
            log(request, path, op, acls, createMode, result);
         } catch (Throwable e) {
            LOG.error("Failed to audit log request {}", request.type, e);
         }

      }
   }

   private static AuditEvent.Result getResult(DataTree.ProcessTxnResult rc, boolean failedTxn) {
      if (failedTxn) {
         return AuditEvent.Result.FAILURE;
      } else {
         return rc.err == KeeperException.Code.OK.intValue() ? AuditEvent.Result.SUCCESS : AuditEvent.Result.FAILURE;
      }
   }

   private static void logMultiOperation(Request request, DataTree.ProcessTxnResult rc) throws IOException, KeeperException {
      Map<String, String> createModes = getCreateModes(request);
      boolean multiFailed = false;

      for(DataTree.ProcessTxnResult subTxnResult : rc.multiResult) {
         switch (subTxnResult.type) {
            case -1:
               multiFailed = true;
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
            case 13:
            case 14:
            case 16:
            case 17:
            case 18:
            default:
               break;
            case 1:
            case 15:
            case 19:
            case 21:
               log(request, subTxnResult.path, "create", (String)null, (String)createModes.get(subTxnResult.path), AuditEvent.Result.SUCCESS);
               break;
            case 2:
            case 20:
               log(request, subTxnResult.path, "delete", (String)null, (String)null, AuditEvent.Result.SUCCESS);
               break;
            case 5:
               log(request, subTxnResult.path, "setData", (String)null, (String)null, AuditEvent.Result.SUCCESS);
         }
      }

      if (multiFailed) {
         log(request, rc.path, "multiOperation", (String)null, (String)null, AuditEvent.Result.FAILURE);
      }

   }

   private static void log(Request request, String path, String op, String acls, String createMode, AuditEvent.Result result) {
      log(request.getUsersForAudit(), op, path, acls, createMode, request.cnxn.getSessionIdHex(), request.cnxn.getHostAddress(), result);
   }

   private static void log(String user, String operation, String znode, String acl, String createMode, String session, String ip, AuditEvent.Result result) {
      ZKAuditProvider.log(user, operation, znode, acl, createMode, session, ip, result);
   }

   private static String getCreateMode(CreateRequest createRequest) throws KeeperException {
      return CreateMode.fromFlag(createRequest.getFlags()).toString().toLowerCase();
   }

   private static Map getCreateModes(Request request) throws IOException, KeeperException {
      Map<String, String> createModes = new HashMap();
      if (!ZKAuditProvider.isAuditEnabled()) {
         return createModes;
      } else {
         for(Op op : (MultiOperationRecord)request.readRequestRecord(MultiOperationRecord::new)) {
            if (op.getType() == 1 || op.getType() == 15 || op.getType() == 19) {
               CreateRequest requestRecord = (CreateRequest)op.toRequestRecord();
               createModes.put(requestRecord.getPath(), getCreateMode(requestRecord));
            }
         }

         return createModes;
      }
   }
}
