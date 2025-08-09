package org.apache.derby.impl.sql.execute;

import java.util.Iterator;
import java.util.List;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.AliasDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColPermsDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.dictionary.TablePermsDescriptor;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.dictionary.ViewDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.util.ArrayUtil;

public class TablePrivilegeInfo extends PrivilegeInfo {
   public static final int SELECT_ACTION = 0;
   public static final int DELETE_ACTION = 1;
   public static final int INSERT_ACTION = 2;
   public static final int UPDATE_ACTION = 3;
   public static final int REFERENCES_ACTION = 4;
   public static final int TRIGGER_ACTION = 5;
   public static final int ACTION_COUNT = 6;
   private static final String YES_WITH_GRANT_OPTION = "Y";
   private static final String YES_WITHOUT_GRANT_OPTION = "y";
   private static final String NO = "N";
   private static final String[][] actionString = new String[][]{{"s", "S"}, {"d", "D"}, {"i", "I"}, {"u", "U"}, {"r", "R"}, {"t", "T"}};
   private final TableDescriptor td;
   private final boolean[] actionAllowed;
   private final FormatableBitSet[] columnBitSets;
   private final List descriptorList;

   public TablePrivilegeInfo(TableDescriptor var1, boolean[] var2, FormatableBitSet[] var3, List var4) {
      this.actionAllowed = ArrayUtil.copy(var2);
      this.columnBitSets = new FormatableBitSet[var3.length];

      for(int var5 = 0; var5 < var3.length; ++var5) {
         if (var3[var5] != null) {
            this.columnBitSets[var5] = new FormatableBitSet(var3[var5]);
         }
      }

      this.td = var1;
      this.descriptorList = var4;
   }

   protected void checkOwnership(String var1, TableDescriptor var2, SchemaDescriptor var3, DataDictionary var4, LanguageConnectionContext var5, boolean var6) throws StandardException {
      super.checkOwnership(var1, var2, var3, var4);
      if (var6) {
         this.checkPrivileges(var1, var2, var3, var4, var5);
      }

   }

   private void checkPrivileges(String var1, TableDescriptor var2, SchemaDescriptor var3, DataDictionary var4, LanguageConnectionContext var5) throws StandardException {
      if (!var1.equals(var4.getAuthorizationDatabaseOwner())) {
         if (var2.getTableType() == 2 && this.descriptorList != null) {
            TransactionController var6 = var5.getTransactionExecute();
            int var7 = this.descriptorList.size();

            for(int var8 = 0; var8 < var7; ++var8) {
               SchemaDescriptor var10 = null;
               TupleDescriptor var9 = (TupleDescriptor)this.descriptorList.get(var8);
               if (var9 instanceof TableDescriptor) {
                  TableDescriptor var11 = (TableDescriptor)var9;
                  var10 = var11.getSchemaDescriptor();
               } else if (var9 instanceof ViewDescriptor) {
                  ViewDescriptor var12 = (ViewDescriptor)var9;
                  var10 = var4.getSchemaDescriptor(var12.getCompSchemaId(), var6);
               } else if (var9 instanceof AliasDescriptor) {
                  AliasDescriptor var13 = (AliasDescriptor)var9;
                  var10 = var4.getSchemaDescriptor(var13.getSchemaUUID(), var6);
               }

               if (var10 != null && !var1.equals(var10.getAuthorizationId())) {
                  throw StandardException.newException("4250A", new Object[]{var1, "grant", var3.getSchemaName(), var2.getName()});
               }
            }
         }

      }
   }

   public void executeGrantRevoke(Activation var1, boolean var2, List var3) throws StandardException {
      LanguageConnectionContext var4 = var1.getLanguageConnectionContext();
      DataDictionary var5 = var4.getDataDictionary();
      String var6 = var4.getCurrentUserId(var1);
      TransactionController var7 = var4.getTransactionExecute();
      SchemaDescriptor var8 = this.td.getSchemaDescriptor();
      this.checkOwnership(var6, this.td, var8, var5, var4, var2);
      DataDescriptorGenerator var9 = var5.getDataDescriptorGenerator();
      TablePermsDescriptor var10 = var9.newTablePermsDescriptor(this.td, this.getPermString(0, false), this.getPermString(1, false), this.getPermString(2, false), this.getPermString(3, false), this.getPermString(4, false), this.getPermString(5, false), var6);
      ColPermsDescriptor[] var11 = new ColPermsDescriptor[this.columnBitSets.length];

      for(int var12 = 0; var12 < this.columnBitSets.length; ++var12) {
         if (this.columnBitSets[var12] != null || !var2 && this.hasColumnPermissions(var12) && this.actionAllowed[var12]) {
            var11[var12] = var9.newColPermsDescriptor(this.td, this.getActionString(var12, false), this.columnBitSets[var12], var6);
         }
      }

      var5.startWriting(var4);
      Iterator var16 = var3.iterator();

      while(var16.hasNext()) {
         boolean var13 = false;
         String var14 = (String)var16.next();
         if (var10 != null && var5.addRemovePermissionsDescriptor(var2, var10, var14, var7)) {
            var13 = true;
            var5.getDependencyManager().invalidateFor(var10, 44, var4);
            var5.getDependencyManager().invalidateFor(this.td, 23, var4);
         }

         for(int var15 = 0; var15 < this.columnBitSets.length; ++var15) {
            if (var11[var15] != null && var5.addRemovePermissionsDescriptor(var2, var11[var15], var14, var7)) {
               var13 = true;
               var5.getDependencyManager().invalidateFor(var11[var15], 44, var4);
               var5.getDependencyManager().invalidateFor(this.td, 23, var4);
            }
         }

         this.addWarningIfPrivilegeNotRevoked(var1, var2, var13, var14);
      }

   }

   private String getPermString(int var1, boolean var2) {
      if (this.actionAllowed[var1] && this.columnBitSets[var1] == null) {
         return var2 ? "Y" : "y";
      } else {
         return "N";
      }
   }

   private String getActionString(int var1, boolean var2) {
      return actionString[var1][var2 ? 1 : 0];
   }

   private boolean hasColumnPermissions(int var1) {
      return var1 == 0 || var1 == 3 || var1 == 4;
   }
}
