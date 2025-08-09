package org.apache.curator.framework.imps;

import java.security.MessageDigest;
import org.apache.curator.framework.api.transaction.CuratorOp;
import org.apache.curator.framework.api.transaction.TypeAndPath;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.zookeeper.Op;

public class ExtractingCuratorOp implements CuratorOp {
   private final CuratorMultiTransactionRecord record = new CuratorMultiTransactionRecord();

   CuratorMultiTransactionRecord getRecord() {
      return this.record;
   }

   public TypeAndPath getTypeAndPath() {
      this.validate();
      return this.record.getMetadata(0);
   }

   public Op get() {
      this.validate();
      return (Op)this.record.iterator().next();
   }

   public void addToDigest(MessageDigest digest) {
      this.record.addToDigest(digest);
   }

   private void validate() {
      Preconditions.checkArgument(this.record.size() > 0, "No operation has been added");
      Preconditions.checkArgument(this.record.size() == 1, "Multiple operations added");
   }
}
