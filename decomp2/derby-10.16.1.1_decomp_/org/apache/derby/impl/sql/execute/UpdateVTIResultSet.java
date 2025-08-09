package org.apache.derby.impl.sql.execute;

import java.sql.ResultSet;
import java.util.Properties;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class UpdateVTIResultSet extends DMLVTIResultSet {
   private ResultSet rs;
   private TemporaryRowHolderImpl rowHolder;

   public UpdateVTIResultSet(NoPutResultSet var1, Activation var2) throws StandardException {
      super(var1, var2);
   }

   protected void openCore() throws StandardException {
      int var1 = -1;
      boolean var2 = true;
      this.rs = this.activation.getTargetVTI();
      ExecRow var3 = this.getNextRowCore(this.sourceResultSet);
      if (null != var3) {
         var1 = var3.nColumns();
      }

      if (this.constants.deferred) {
         this.activation.clearIndexScanInfo();
      }

      if (null == this.rowHolder && this.constants.deferred) {
         Properties var4 = new Properties();
         this.rowHolder = new TemporaryRowHolderImpl(this.activation, var4, this.resultDescription);
      }

      try {
         while(var3 != null) {
            if (this.constants.deferred) {
               if (var2) {
                  var3.getColumn(var1).setValue(this.rs.getRow());
                  var2 = false;
               } else {
                  DataValueDescriptor var16 = var3.cloneColumn(var1);
                  var16.setValue(this.rs.getRow());
                  var3.setColumn(var1, var16);
               }

               this.rowHolder.insert(var3);
            } else {
               this.updateVTI(this.rs, var3);
            }

            ++this.rowCount;
            if (this.constants.singleRowSource) {
               var3 = null;
            } else {
               var3 = this.getNextRowCore(this.sourceResultSet);
            }
         }
      } catch (StandardException var13) {
         throw var13;
      } catch (Throwable var14) {
         throw StandardException.unexpectedUserException(var14);
      }

      if (this.constants.deferred) {
         CursorResultSet var17 = this.rowHolder.getResultSet();

         try {
            var17.open();

            while((var3 = var17.getNextRow()) != null) {
               int var5 = var3.getColumn(var1).getInt();
               this.rs.absolute(var5);
               this.updateVTI(this.rs, var3);
            }
         } catch (Throwable var11) {
            throw StandardException.unexpectedUserException(var11);
         } finally {
            this.sourceResultSet.clearCurrentRow();
            var17.close();
         }
      }

      if (this.rowHolder != null) {
         this.rowHolder.close();
      }

   }

   public void close() throws StandardException {
      this.close(false);
   }

   private void updateVTI(ResultSet var1, ExecRow var2) throws StandardException {
      int[] var3 = this.constants.changedColumnIds;

      try {
         for(int var4 = 0; var4 < var3.length; ++var4) {
            int var5 = var3[var4];
            DataValueDescriptor var6 = var2.getColumn(var4 + 1);
            if (var6.isNull()) {
               var1.updateNull(var5);
            } else {
               var6.setInto(var1, var5);
            }
         }

         var1.updateRow();
      } catch (Throwable var7) {
         throw StandardException.unexpectedUserException(var7);
      }
   }
}
