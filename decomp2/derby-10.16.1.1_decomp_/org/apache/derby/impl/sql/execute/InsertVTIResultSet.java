package org.apache.derby.impl.sql.execute;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.vti.DeferModification;

class InsertVTIResultSet extends DMLVTIResultSet {
   private PreparedStatement ps;
   private VTIResultSet vtiRS;
   private ResultSet rs;
   private TemporaryRowHolderImpl rowHolder;

   public InsertVTIResultSet(NoPutResultSet var1, NoPutResultSet var2, Activation var3) throws StandardException {
      super(var1, var3);
      this.vtiRS = (VTIResultSet)var2;
   }

   protected void openCore() throws StandardException {
      if (this.ps == null) {
         this.ps = (PreparedStatement)this.vtiRS.getVTIConstructor().invoke(this.activation);
      }

      if (this.ps instanceof DeferModification) {
         try {
            ((DeferModification)this.ps).modificationNotify(1, this.constants.deferred);
         } catch (Throwable var8) {
            throw StandardException.unexpectedUserException(var8);
         }
      }

      ExecRow var1 = this.getNextRowCore(this.sourceResultSet);

      try {
         this.rs = this.ps.executeQuery();
      } catch (Throwable var7) {
         throw StandardException.unexpectedUserException(var7);
      }

      if (this.constants.deferred) {
         this.activation.clearIndexScanInfo();
      }

      if (this.firstExecute && this.constants.deferred) {
         Properties var2 = new Properties();
         this.rowHolder = new TemporaryRowHolderImpl(this.activation, var2, this.resultDescription);
      }

      while(var1 != null) {
         if (this.constants.deferred) {
            this.rowHolder.insert(var1);
         } else {
            this.insertIntoVTI(this.rs, var1);
         }

         ++this.rowCount;
         if (this.constants.singleRowSource) {
            var1 = null;
         } else {
            var1 = this.getNextRowCore(this.sourceResultSet);
         }
      }

      if (this.constants.deferred) {
         CursorResultSet var11 = this.rowHolder.getResultSet();

         try {
            var11.open();

            while((var1 = var11.getNextRow()) != null) {
               this.insertIntoVTI(this.rs, var1);
            }
         } finally {
            this.sourceResultSet.clearCurrentRow();
            var11.close();
         }
      }

      if (this.rowHolder != null) {
         this.rowHolder.close();
      }

   }

   private void insertIntoVTI(ResultSet var1, ExecRow var2) throws StandardException {
      try {
         var1.moveToInsertRow();
         DataValueDescriptor[] var3 = var2.getRowArray();

         for(int var4 = 0; var4 < var3.length; ++var4) {
            DataValueDescriptor var5 = var3[var4];

            try {
               if (var5.isNull()) {
                  var1.updateNull(var4 + 1);
               } else {
                  var5.setInto(var1, var4 + 1);
               }
            } catch (Throwable var7) {
               var1.updateObject(var4 + 1, var5.getObject());
            }
         }

         var1.insertRow();
      } catch (Throwable var8) {
         throw StandardException.unexpectedUserException(var8);
      }
   }

   public void cleanUp() throws StandardException {
      if (this.rowHolder != null) {
         this.rowHolder.close();
      }

      if (this.rs != null) {
         try {
            this.rs.close();
         } catch (Throwable var3) {
            throw StandardException.unexpectedUserException(var3);
         }

         this.rs = null;
      }

      if (!this.vtiRS.isReuseablePs() && this.ps != null) {
         try {
            this.ps.close();
            this.ps = null;
         } catch (Throwable var2) {
            throw StandardException.unexpectedUserException(var2);
         }
      }

      super.cleanUp();
   }

   public void close() throws StandardException {
      this.close(false);
   }

   public void finish() throws StandardException {
      if (this.ps != null && !this.vtiRS.isReuseablePs()) {
         try {
            this.ps.close();
            this.ps = null;
         } catch (Throwable var2) {
            throw StandardException.unexpectedUserException(var2);
         }
      }

      super.finish();
   }
}
