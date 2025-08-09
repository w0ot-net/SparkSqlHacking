package org.apache.derby.impl.sql.execute;

import java.sql.ResultSet;
import java.util.Properties;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.types.SQLInteger;
import org.apache.derby.shared.common.error.StandardException;

class DeleteVTIResultSet extends DMLVTIResultSet {
   private ResultSet rs;
   private TemporaryRowHolderImpl rowHolder;

   public DeleteVTIResultSet(NoPutResultSet var1, Activation var2) throws StandardException {
      super(var1, var2);
   }

   protected void openCore() throws StandardException {
      ExecRow var1 = this.getNextRowCore(this.sourceResultSet);
      if (var1 != null) {
         this.rs = this.activation.getTargetVTI();
      }

      if (this.constants.deferred) {
         this.activation.clearIndexScanInfo();
         if (null == this.rowHolder) {
            this.rowHolder = new TemporaryRowHolderImpl(this.activation, new Properties(), (ResultDescription)null);
         }
      }

      try {
         while(var1 != null) {
            if (!this.constants.deferred) {
               this.rs.deleteRow();
            } else {
               ValueRow var2 = new ValueRow(1);
               var2.setColumn(1, new SQLInteger(this.rs.getRow()));
               this.rowHolder.insert(var2);
            }

            ++this.rowCount;
            if (this.constants.singleRowSource) {
               var1 = null;
            } else {
               var1 = this.getNextRowCore(this.sourceResultSet);
            }
         }
      } catch (StandardException var12) {
         throw var12;
      } catch (Throwable var13) {
         throw StandardException.unexpectedUserException(var13);
      }

      if (this.constants.deferred) {
         CursorResultSet var14 = this.rowHolder.getResultSet();

         try {
            Object var3 = null;
            var14.open();

            while((var15 = var14.getNextRow()) != null) {
               int var4 = var15.getColumn(1).getInt();
               this.rs.absolute(var4);
               this.rs.deleteRow();
            }
         } catch (Throwable var10) {
            throw StandardException.unexpectedUserException(var10);
         } finally {
            this.sourceResultSet.clearCurrentRow();
            var14.close();
         }
      }

      if (this.rowHolder != null) {
         this.rowHolder.close();
      }

   }

   public void close() throws StandardException {
      this.close(false);
   }
}
