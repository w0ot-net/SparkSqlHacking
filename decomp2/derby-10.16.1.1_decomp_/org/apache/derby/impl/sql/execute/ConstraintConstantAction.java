package org.apache.derby.impl.sql.execute;

import java.util.ArrayList;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.PreparedStatement;
import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.ForeignKeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.ReferencedKeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.store.access.BackingStoreHashtable;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.GroupFetchScanController;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.NumberDataValue;
import org.apache.derby.impl.store.access.heap.HeapRowLocation;
import org.apache.derby.shared.common.error.StandardException;

public abstract class ConstraintConstantAction extends DDLSingleTableConstantAction {
   protected String constraintName;
   protected int constraintType;
   protected String tableName;
   protected String schemaName;
   protected UUID schemaId;
   protected IndexConstantAction indexAction;
   protected UUID constraintId;

   ConstraintConstantAction(String var1, int var2, String var3, UUID var4, String var5, IndexConstantAction var6) {
      super(var4);
      this.constraintName = var1;
      this.constraintType = var2;
      this.tableName = var3;
      this.indexAction = var6;
      this.schemaName = var5;
   }

   public int getConstraintType() {
      return this.constraintType;
   }

   public String getConstraintName() {
      return this.constraintName;
   }

   public UUID getConstraintId() {
      return this.constraintId;
   }

   public IndexConstantAction getIndexAction() {
      return this.indexAction;
   }

   static void validateFKConstraint(Activation var0, TransactionController var1, DataDictionary var2, ForeignKeyConstraintDescriptor var3, ReferencedKeyConstraintDescriptor var4, ExecRow var5) throws StandardException {
      GroupFetchScanController var6 = null;
      GroupFetchScanController var7 = var1.openGroupFetchScan(var3.getIndexConglomerateDescriptor(var2).getConglomerateNumber(), false, 0, 7, 2, (FormatableBitSet)null, (DataValueDescriptor[])null, 1, (Qualifier[][])null, (DataValueDescriptor[])null, -1);

      try {
         if (var7.next()) {
            var7.reopenScan((DataValueDescriptor[])null, 1, (Qualifier[][])null, (DataValueDescriptor[])null, -1);
            var6 = var1.openGroupFetchScan(var4.getIndexConglomerateDescriptor(var2).getConglomerateNumber(), false, 0, 6, 2, (FormatableBitSet)null, (DataValueDescriptor[])null, 1, (Qualifier[][])null, (DataValueDescriptor[])null, -1);
            RIBulkChecker var8 = new RIBulkChecker(var0, var6, var7, var5, true, (ConglomerateController)null, (ExecRow)null, var3.getTableDescriptor().getSchemaName(), var3.getTableDescriptor().getName(), var3.getUUID(), var3.deferrable(), var3.getIndexConglomerateDescriptor(var2).getConglomerateNumber(), var4.getIndexConglomerateDescriptor(var2).getConglomerateNumber());
            int var9 = var8.doCheck();
            if (var9 <= 0) {
               return;
            }

            StandardException var10 = StandardException.newException("X0Y45.S", new Object[]{var3.getConstraintName(), var3.getTableDescriptor().getName()});
            throw var10;
         }

         var7.close();
      } finally {
         if (var7 != null) {
            var7.close();
            Object var15 = null;
         }

         if (var6 != null) {
            var6.close();
            Object var14 = null;
         }

      }

   }

   static boolean validateConstraint(String var0, String var1, UUID var2, TableDescriptor var3, LanguageConnectionContext var4, boolean var5, boolean var6) throws StandardException {
      StringBuilder var7 = new StringBuilder();
      var7.append("SELECT COUNT(*) FROM ");
      var7.append(var3.getQualifiedName());
      var7.append(" WHERE NOT(");
      var7.append(var1);
      var7.append(")");
      ResultSet var8 = null;

      boolean var17;
      try {
         PreparedStatement var9 = var4.prepareInternalStatement(var7.toString());
         var8 = var9.executeSubStatement(var4, false, 0L);
         ExecRow var10 = var8.getNextRow();
         Number var11 = (Number)((NumberDataValue)var10.getRowArray()[0]).getObject();
         if (var11 == null || var11.longValue() == 0L) {
            return true;
         }

         if (var5) {
            if (!var6) {
               throw StandardException.newException("X0Y59.S", new Object[]{var0, var3.getQualifiedName(), var11.toString()});
            }

            ArrayList var12 = new ArrayList();
            var12.add(var2);
            DeferredConstraintsMemory.CheckInfo[] var13 = new DeferredConstraintsMemory.CheckInfo[1];
            DeferredConstraintsMemory.rememberCheckViolations(var4, var3.getObjectID(), var3.getSchemaName(), var3.getName(), (BackingStoreHashtable)null, var12, new HeapRowLocation(), var13);
            var13[0].setInvalidatedRowLocations();
         }

         var17 = false;
      } finally {
         if (var8 != null) {
            var8.close();
         }

      }

      return var17;
   }
}
