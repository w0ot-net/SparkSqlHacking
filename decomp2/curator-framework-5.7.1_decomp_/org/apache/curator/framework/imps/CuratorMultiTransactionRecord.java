package org.apache.curator.framework.imps;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.curator.framework.api.transaction.OperationType;
import org.apache.curator.framework.api.transaction.TypeAndPath;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.apache.zookeeper.Op;

class CuratorMultiTransactionRecord implements Iterable {
   private final List metadata = Lists.newArrayList();
   private final List ops = new ArrayList();

   void add(Op op, OperationType type, String forPath) {
      this.ops.add(op);
      this.metadata.add(new TypeAndPath(type, forPath));
   }

   TypeAndPath getMetadata(int index) {
      return (TypeAndPath)this.metadata.get(index);
   }

   int metadataSize() {
      return this.metadata.size();
   }

   void addToDigest(MessageDigest digest) {
      for(Op op : this.ops) {
         digest.update(op.getPath().getBytes());
         digest.update(Integer.toString(op.getType()).getBytes());
         digest.update(op.toRequestRecord().toString().getBytes());
      }

   }

   public Iterator iterator() {
      return this.ops.iterator();
   }

   int size() {
      return this.ops.size();
   }
}
