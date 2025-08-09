package org.apache.zookeeper.server.util;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.jute.BinaryInputArchive;
import org.apache.jute.InputArchive;
import org.apache.jute.OutputArchive;
import org.apache.jute.Record;
import org.apache.zookeeper.server.DataTree;
import org.apache.zookeeper.server.TxnLogEntry;
import org.apache.zookeeper.server.ZooKeeperServer;
import org.apache.zookeeper.server.ZooTrace;
import org.apache.zookeeper.txn.CloseSessionTxn;
import org.apache.zookeeper.txn.CreateContainerTxn;
import org.apache.zookeeper.txn.CreateSessionTxn;
import org.apache.zookeeper.txn.CreateTTLTxn;
import org.apache.zookeeper.txn.CreateTxn;
import org.apache.zookeeper.txn.CreateTxnV0;
import org.apache.zookeeper.txn.DeleteTxn;
import org.apache.zookeeper.txn.ErrorTxn;
import org.apache.zookeeper.txn.MultiTxn;
import org.apache.zookeeper.txn.SetACLTxn;
import org.apache.zookeeper.txn.SetDataTxn;
import org.apache.zookeeper.txn.TxnDigest;
import org.apache.zookeeper.txn.TxnHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerializeUtils {
   private static final Logger LOG = LoggerFactory.getLogger(SerializeUtils.class);

   public static TxnLogEntry deserializeTxn(byte[] txnBytes) throws IOException {
      TxnHeader hdr = new TxnHeader();
      ByteArrayInputStream bais = new ByteArrayInputStream(txnBytes);
      InputArchive ia = BinaryInputArchive.getArchive(bais);
      hdr.deserialize(ia, "hdr");
      bais.mark(bais.available());
      Record txn = null;
      switch (hdr.getType()) {
         case -11:
            txn = ZooKeeperServer.isCloseSessionTxnEnabled() ? new CloseSessionTxn() : null;
            break;
         case -10:
            txn = new CreateSessionTxn();
            break;
         case -9:
         case -8:
         case -7:
         case -6:
         case -5:
         case -4:
         case -3:
         case -2:
         case 0:
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
            throw new IOException("Unsupported Txn with type=" + hdr.getType());
         case -1:
            txn = new ErrorTxn();
            break;
         case 1:
         case 15:
            txn = new CreateTxn();
            break;
         case 2:
         case 20:
            txn = new DeleteTxn();
            break;
         case 5:
         case 16:
            txn = new SetDataTxn();
            break;
         case 7:
            txn = new SetACLTxn();
            break;
         case 14:
            txn = new MultiTxn();
            break;
         case 19:
            txn = new CreateContainerTxn();
            break;
         case 21:
            txn = new CreateTTLTxn();
      }

      if (txn != null) {
         try {
            txn.deserialize(ia, "txn");
         } catch (EOFException e) {
            if (hdr.getType() == 1) {
               CreateTxn create = (CreateTxn)txn;
               bais.reset();
               CreateTxnV0 createv0 = new CreateTxnV0();
               createv0.deserialize(ia, "txn");
               create.setPath(createv0.getPath());
               create.setData(createv0.getData());
               create.setAcl(createv0.getAcl());
               create.setEphemeral(createv0.getEphemeral());
               create.setParentCVersion(-1);
            } else {
               if (hdr.getType() != -11) {
                  throw e;
               }

               txn = null;
            }
         }
      }

      TxnDigest digest = null;
      if (ZooKeeperServer.isDigestEnabled()) {
         digest = new TxnDigest();

         try {
            digest.deserialize(ia, "digest");
         } catch (EOFException var8) {
            digest = null;
         }
      }

      return new TxnLogEntry(txn, hdr, digest);
   }

   public static void deserializeSnapshot(DataTree dt, InputArchive ia, Map sessions) throws IOException {
      for(int count = ia.readInt("count"); count > 0; --count) {
         long id = ia.readLong("id");
         int to = ia.readInt("timeout");
         sessions.put(id, to);
         if (LOG.isTraceEnabled()) {
            ZooTrace.logTraceMessage(LOG, 32L, "loadData --- session in archive: " + id + " with timeout: " + to);
         }
      }

      dt.deserialize(ia, "tree");
   }

   public static void serializeSnapshot(DataTree dt, OutputArchive oa, Map sessions) throws IOException {
      HashMap<Long, Integer> sessSnap = new HashMap(sessions);
      oa.writeInt(sessSnap.size(), "count");

      for(Map.Entry entry : sessSnap.entrySet()) {
         oa.writeLong((Long)entry.getKey(), "id");
         oa.writeInt((Integer)entry.getValue(), "timeout");
      }

      dt.serialize(oa, "tree");
   }
}
