package org.apache.derby.diag;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import org.apache.derby.iapi.services.locks.Latch;
import org.apache.derby.iapi.services.locks.LockFactory;
import org.apache.derby.iapi.services.locks.LockOwner;
import org.apache.derby.iapi.services.locks.Lockable;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.sql.conn.ConnectionUtil;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.impl.jdbc.EmbedResultSetMetaData;
import org.apache.derby.impl.services.locks.ActiveLock;
import org.apache.derby.impl.services.locks.TableNameInfo;
import org.apache.derby.shared.common.error.PublicAPI;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.vti.VTICosting;
import org.apache.derby.vti.VTIEnvironment;
import org.apache.derby.vti.VTITemplate;

public class LockTable extends VTITemplate implements VTICosting {
   public static final int LATCH = 1;
   public static final int TABLE_AND_ROWLOCK = 2;
   public static final int ALL = -1;
   private TransactionController tc;
   private Hashtable currentRow;
   private Enumeration lockTable;
   private boolean wasNull;
   private boolean initialized;
   private final int flag;
   private TableNameInfo tabInfo;
   private static final ResultColumnDescriptor[] columnInfo = new ResultColumnDescriptor[]{EmbedResultSetMetaData.getResultColumnDescriptor("XID", 12, false, 15), EmbedResultSetMetaData.getResultColumnDescriptor("TYPE", 12, true, 5), EmbedResultSetMetaData.getResultColumnDescriptor("MODE", 12, false, 4), EmbedResultSetMetaData.getResultColumnDescriptor("TABLENAME", 12, false, 128), EmbedResultSetMetaData.getResultColumnDescriptor("LOCKNAME", 12, false, 20), EmbedResultSetMetaData.getResultColumnDescriptor("STATE", 12, true, 5), EmbedResultSetMetaData.getResultColumnDescriptor("TABLETYPE", 12, false, 9), EmbedResultSetMetaData.getResultColumnDescriptor("LOCKCOUNT", 12, false, 5), EmbedResultSetMetaData.getResultColumnDescriptor("INDEXNAME", 12, true, 128)};
   private static final ResultSetMetaData metadata;

   public LockTable() {
      this.flag = 2;
   }

   public LockTable(int var1) {
      this.flag = var1;
   }

   public ResultSetMetaData getMetaData() {
      return metadata;
   }

   public boolean next() throws SQLException {
      try {
         if (!this.initialized) {
            LanguageConnectionContext var1 = ConnectionUtil.getCurrentLCC();
            this.tc = var1.getTransactionExecute();
            LockFactory var2 = this.tc.getAccessManager().getLockFactory();
            this.lockTable = var2.makeVirtualLockTable();
            this.initialized = true;
            this.tabInfo = new TableNameInfo(var1, true);
         }

         this.currentRow = null;
         if (this.lockTable != null) {
            while(this.lockTable.hasMoreElements() && this.currentRow == null) {
               this.currentRow = this.dumpLock((Latch)this.lockTable.nextElement());
            }
         }
      } catch (StandardException var3) {
         throw PublicAPI.wrapStandardException(var3);
      }

      return this.currentRow != null;
   }

   public void close() {
      this.lockTable = null;
   }

   public String getString(int var1) {
      String var2 = (String)this.currentRow.get(columnInfo[var1 - 1].getName());
      this.wasNull = var2 == null;
      return var2;
   }

   public boolean wasNull() {
      return this.wasNull;
   }

   public double getEstimatedRowCount(VTIEnvironment var1) {
      return (double)10000.0F;
   }

   public double getEstimatedCostPerInstantiation(VTIEnvironment var1) {
      return (double)100000.0F;
   }

   public boolean supportsMultipleInstantiations(VTIEnvironment var1) {
      return false;
   }

   private Hashtable dumpLock(Latch var1) throws StandardException {
      Hashtable var2 = new Hashtable(17);
      Object var3 = var1.getQualifier();
      Lockable var4 = var1.getLockable();
      if (!var4.lockAttributes(this.flag, var2)) {
         return null;
      } else if (var2.get("LOCKNAME") != null && var2.get("TYPE") != null) {
         int var5 = var1.getCount();
         String var6;
         if (var5 != 0) {
            var6 = "GRANT";
         } else {
            if (!(var1 instanceof ActiveLock)) {
               return null;
            }

            var6 = "WAIT";
         }

         Long var7 = (Long)var2.get("CONGLOMID");
         if (var7 == null) {
            if (var2.get("CONTAINERID") == null) {
               return null;
            }

            Long var8 = (Long)var2.get("CONTAINERID");
            var7 = this.tc.findConglomid(var8);
            var2.put("CONGLOMID", var7);
         }

         var2.put("LOCKOBJ", var1);
         LockOwner var12 = var1.getCompatabilitySpace().getOwner();
         var2.put("XID", var12 == null ? "<null>" : var12.toString());
         var2.put("MODE", var3.toString());
         var2.put("LOCKCOUNT", Integer.toString(var5));
         var2.put("STATE", var6);
         String var9 = this.tabInfo.getTableName(var7);
         var2.put("TABLENAME", var9);
         String var10 = this.tabInfo.getIndexName(var7);
         if (var10 != null) {
            var2.put("INDEXNAME", var10);
         }

         String var11 = this.tabInfo.getTableType(var7);
         var2.put("TABLETYPE", var11);
         return var2;
      } else {
         return null;
      }
   }

   static {
      metadata = new EmbedResultSetMetaData(columnInfo);
   }
}
