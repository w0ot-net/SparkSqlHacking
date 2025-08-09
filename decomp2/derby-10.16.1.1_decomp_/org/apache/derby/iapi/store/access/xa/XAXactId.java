package org.apache.derby.iapi.store.access.xa;

import java.util.Arrays;
import javax.transaction.xa.XAException;
import javax.transaction.xa.Xid;
import org.apache.derby.iapi.store.access.GlobalXact;
import org.apache.derby.iapi.util.StringUtil;

public class XAXactId extends GlobalXact implements Xid {
   private static final char COLON = ':';

   private void copy_init_xid(int var1, byte[] var2, byte[] var3) {
      this.format_id = var1;
      this.global_id = (byte[])(([B)var2).clone();
      this.branch_id = (byte[])(([B)var3).clone();
   }

   public XAXactId(int var1, byte[] var2, byte[] var3) {
      this.copy_init_xid(var1, var2, var3);
   }

   public XAXactId(Xid var1) throws XAException {
      if (var1 == null) {
         throw new XAException(-4);
      } else {
         this.copy_init_xid(var1.getFormatId(), var1.getGlobalTransactionId(), var1.getBranchQualifier());
      }
   }

   public String toHexString() {
      int var1 = 20 + (this.global_id.length + this.branch_id.length) * 2;
      StringBuffer var2 = new StringBuffer(var1);
      var2.append(':').append(Integer.toString(this.global_id.length)).append(':').append(Integer.toString(this.branch_id.length)).append(':').append(Integer.toString(this.format_id, 16)).append(':').append(StringUtil.toHexString(this.global_id, 0, this.global_id.length)).append(':').append(StringUtil.toHexString(this.branch_id, 0, this.branch_id.length)).append(':');
      return var2.toString();
   }

   public XAXactId(String var1) {
      int var2 = 1;
      int var3 = var1.indexOf(58, var2);
      String var5 = var1.substring(var2, var3);
      int var6 = Integer.parseInt(var5);
      var2 = var3 + 1;
      var3 = var1.indexOf(58, var2);
      String var7 = var1.substring(var2, var3);
      int var8 = Integer.parseInt(var7);
      var2 = var3 + 1;
      var3 = var1.indexOf(58, var2);
      String var9 = var1.substring(var2, var3);
      this.format_id = Integer.parseInt(var9, 16);
      var2 = var3 + 1;
      var3 = var1.indexOf(58, var2);
      this.global_id = StringUtil.fromHexString(var1, var2, var3 - var2);
      var2 = var3 + 1;
      var3 = var1.indexOf(58, var2);
      this.branch_id = StringUtil.fromHexString(var1, var2, var3 - var2);
   }

   public int getFormatId() {
      return this.format_id;
   }

   public byte[] getGlobalTransactionId() {
      return (byte[])this.global_id.clone();
   }

   public byte[] getBranchQualifier() {
      return (byte[])this.branch_id.clone();
   }

   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (var1 == null) {
         return false;
      } else {
         try {
            if (var1 instanceof GlobalXact) {
               return super.equals(var1);
            } else {
               Xid var2 = (Xid)var1;
               return Arrays.equals(var2.getGlobalTransactionId(), this.global_id) && Arrays.equals(var2.getBranchQualifier(), this.branch_id) && var2.getFormatId() == this.format_id;
            }
         } catch (ClassCastException var3) {
            return false;
         }
      }
   }
}
