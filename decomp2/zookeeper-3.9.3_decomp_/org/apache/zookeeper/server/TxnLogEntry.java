package org.apache.zookeeper.server;

import org.apache.jute.Record;
import org.apache.zookeeper.txn.TxnDigest;
import org.apache.zookeeper.txn.TxnHeader;

public final class TxnLogEntry {
   private final Record txn;
   private final TxnHeader header;
   private final TxnDigest digest;

   public TxnLogEntry(Record txn, TxnHeader header, TxnDigest digest) {
      this.txn = txn;
      this.header = header;
      this.digest = digest;
   }

   public Record getTxn() {
      return this.txn;
   }

   public TxnHeader getHeader() {
      return this.header;
   }

   public TxnDigest getDigest() {
      return this.digest;
   }
}
