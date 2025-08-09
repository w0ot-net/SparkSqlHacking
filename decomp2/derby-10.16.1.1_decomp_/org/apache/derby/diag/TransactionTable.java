package org.apache.derby.diag;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.sql.conn.ConnectionUtil;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.store.access.TransactionInfo;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.impl.jdbc.EmbedResultSetMetaData;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.vti.VTICosting;
import org.apache.derby.vti.VTIEnvironment;
import org.apache.derby.vti.VTITemplate;

public class TransactionTable extends VTITemplate implements VTICosting {
   private TransactionInfo[] transactionTable;
   boolean initialized;
   int currentRow;
   private boolean wasNull;
   private static final ResultColumnDescriptor[] columnInfo = new ResultColumnDescriptor[]{EmbedResultSetMetaData.getResultColumnDescriptor("XID", 12, false, 15), EmbedResultSetMetaData.getResultColumnDescriptor("GLOBAL_XID", 12, true, 140), EmbedResultSetMetaData.getResultColumnDescriptor("USERNAME", 12, true, 128), EmbedResultSetMetaData.getResultColumnDescriptor("TYPE", 12, false, 30), EmbedResultSetMetaData.getResultColumnDescriptor("STATUS", 12, false, 8), EmbedResultSetMetaData.getResultColumnDescriptor("FIRST_INSTANT", 12, true, 20), EmbedResultSetMetaData.getResultColumnDescriptor("SQL_TEXT", 12, true, 32672)};
   private static final ResultSetMetaData metadata;

   public TransactionTable() throws StandardException {
      DiagUtil.checkAccess();
   }

   public ResultSetMetaData getMetaData() {
      return metadata;
   }

   public boolean next() throws SQLException {
      if (!this.initialized) {
         LanguageConnectionContext var1 = ConnectionUtil.getCurrentLCC();
         this.transactionTable = var1.getTransactionExecute().getAccessManager().getTransactionInfo();
         this.initialized = true;
         this.currentRow = -1;
      }

      if (this.transactionTable == null) {
         return false;
      } else {
         ++this.currentRow;

         while(this.currentRow < this.transactionTable.length) {
            TransactionInfo var2 = this.transactionTable[this.currentRow];
            if (var2 != null) {
               return true;
            }

            ++this.currentRow;
         }

         this.transactionTable = null;
         return false;
      }
   }

   public void close() {
      this.transactionTable = null;
   }

   public String getString(int var1) {
      TransactionInfo var2 = this.transactionTable[this.currentRow];
      Object var3 = null;
      String var5;
      switch (var1) {
         case 1:
            var5 = var2.getTransactionIdString();
            break;
         case 2:
            var5 = var2.getGlobalTransactionIdString();
            break;
         case 3:
            var5 = var2.getUsernameString();
            break;
         case 4:
            var5 = var2.getTransactionTypeString();
            break;
         case 5:
            var5 = var2.getTransactionStatusString();
            break;
         case 6:
            var5 = var2.getFirstLogInstantString();
            break;
         case 7:
            var5 = var2.getStatementTextString();
            var5 = StringUtil.truncate(var5, 32672);
            break;
         default:
            var5 = null;
      }

      this.wasNull = var5 == null;
      return var5;
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

   static {
      metadata = new EmbedResultSetMetaData(columnInfo);
   }
}
