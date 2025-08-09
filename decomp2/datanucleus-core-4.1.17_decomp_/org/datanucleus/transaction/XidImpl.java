package org.datanucleus.transaction;

import javax.transaction.xa.Xid;

public class XidImpl implements Xid {
   byte[] branchQualifierBytes;
   int formatId;
   byte[] globalTransactionIdBytes;

   public XidImpl(int branchQualifierBytes, int formatId, byte[] globalTransactionIdBytes) {
      byte[] buf = new byte[4];
      buf[0] = (byte)(branchQualifierBytes >>> 24 & 255);
      buf[1] = (byte)(branchQualifierBytes >>> 16 & 255);
      buf[2] = (byte)(branchQualifierBytes >>> 8 & 255);
      buf[3] = (byte)(branchQualifierBytes & 255);
      this.branchQualifierBytes = buf;
      this.formatId = formatId;
      this.globalTransactionIdBytes = globalTransactionIdBytes;
   }

   public XidImpl(int branchQualifierBytes, int formatId, int globalTransactionIdBytes) {
      byte[] buf = new byte[4];
      buf[0] = (byte)(branchQualifierBytes >>> 24 & 255);
      buf[1] = (byte)(branchQualifierBytes >>> 16 & 255);
      buf[2] = (byte)(branchQualifierBytes >>> 8 & 255);
      buf[3] = (byte)(branchQualifierBytes & 255);
      this.branchQualifierBytes = buf;
      this.formatId = formatId;
      buf = new byte[]{(byte)(globalTransactionIdBytes >>> 24 & 255), (byte)(globalTransactionIdBytes >>> 16 & 255), (byte)(globalTransactionIdBytes >>> 8 & 255), (byte)(globalTransactionIdBytes & 255)};
      this.globalTransactionIdBytes = buf;
   }

   public byte[] getBranchQualifier() {
      return this.branchQualifierBytes;
   }

   public int getFormatId() {
      return this.formatId;
   }

   public byte[] getGlobalTransactionId() {
      return this.globalTransactionIdBytes;
   }

   public String toString() {
      return "Xid=" + new String(this.globalTransactionIdBytes);
   }
}
