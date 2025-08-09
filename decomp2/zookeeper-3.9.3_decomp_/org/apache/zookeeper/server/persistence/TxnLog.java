package org.apache.zookeeper.server.persistence;

import java.io.Closeable;
import java.io.IOException;
import org.apache.jute.Record;
import org.apache.zookeeper.server.Request;
import org.apache.zookeeper.server.ServerStats;
import org.apache.zookeeper.txn.TxnDigest;
import org.apache.zookeeper.txn.TxnHeader;

public interface TxnLog extends Closeable {
   void setServerStats(ServerStats var1);

   void rollLog() throws IOException;

   boolean append(Request var1) throws IOException;

   TxnIterator read(long var1) throws IOException;

   long getLastLoggedZxid() throws IOException;

   boolean truncate(long var1) throws IOException;

   long getDbId() throws IOException;

   void commit() throws IOException;

   long getTxnLogSyncElapsedTime();

   void close() throws IOException;

   void setTotalLogSize(long var1);

   long getTotalLogSize();

   public interface TxnIterator extends Closeable {
      TxnHeader getHeader();

      Record getTxn();

      TxnDigest getDigest();

      boolean next() throws IOException;

      void close() throws IOException;

      long getStorageSize() throws IOException;
   }
}
