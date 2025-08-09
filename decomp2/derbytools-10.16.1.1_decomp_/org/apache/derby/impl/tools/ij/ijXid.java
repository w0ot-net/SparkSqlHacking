package org.apache.derby.impl.tools.ij;

import java.io.Serializable;
import javax.transaction.xa.Xid;

class ijXid implements Xid, Serializable {
   private static final long serialVersionUID = 64467452100036L;
   private final int format_id;
   private final byte[] global_id;
   private final byte[] branch_id;

   ijXid(int var1, byte[] var2) {
      this.format_id = var1;
      this.global_id = var2;
      this.branch_id = var2;
   }

   public int getFormatId() {
      return this.format_id;
   }

   public byte[] getGlobalTransactionId() {
      return this.global_id;
   }

   public byte[] getBranchQualifier() {
      return this.branch_id;
   }
}
