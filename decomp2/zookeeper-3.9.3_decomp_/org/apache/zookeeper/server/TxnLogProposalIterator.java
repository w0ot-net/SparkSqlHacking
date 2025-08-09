package org.apache.zookeeper.server;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.zookeeper.server.persistence.TxnLog;
import org.apache.zookeeper.server.persistence.Util;
import org.apache.zookeeper.server.quorum.Leader;
import org.apache.zookeeper.server.quorum.QuorumPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TxnLogProposalIterator implements Iterator {
   private static final Logger LOG = LoggerFactory.getLogger(TxnLogProposalIterator.class);
   public static final TxnLogProposalIterator EMPTY_ITERATOR = new TxnLogProposalIterator();
   private boolean hasNext = false;
   private TxnLog.TxnIterator itr;

   public boolean hasNext() {
      return this.hasNext;
   }

   public Leader.Proposal next() {
      Leader.Proposal p;
      try {
         byte[] serializedData = Util.marshallTxnEntry(this.itr.getHeader(), this.itr.getTxn(), this.itr.getDigest());
         QuorumPacket pp = new QuorumPacket(2, this.itr.getHeader().getZxid(), serializedData, (List)null);
         p = new Leader.Proposal(pp);
         this.hasNext = this.itr.next();
      } catch (IOException e) {
         LOG.error("Unable to read txnlog from disk", e);
         this.hasNext = false;
         p = new Leader.Proposal();
      }

      return p;
   }

   public void remove() {
      throw new UnsupportedOperationException();
   }

   public void close() {
      if (this.itr != null) {
         try {
            this.itr.close();
         } catch (IOException ioe) {
            LOG.warn("Error closing file iterator", ioe);
         }
      }

   }

   private TxnLogProposalIterator() {
   }

   public TxnLogProposalIterator(TxnLog.TxnIterator itr) {
      if (itr != null) {
         this.itr = itr;
         this.hasNext = itr.getHeader() != null;
      }

   }
}
