package org.apache.derby.impl.sql.compile;

import java.util.ArrayList;
import java.util.List;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.depend.Dependent;
import org.apache.derby.iapi.sql.depend.Provider;
import org.apache.derby.iapi.sql.depend.ProviderInfo;
import org.apache.derby.iapi.sql.dictionary.AliasDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.dictionary.ViewDescriptor;
import org.apache.derby.impl.sql.execute.PrivilegeInfo;
import org.apache.derby.impl.sql.execute.TablePrivilegeInfo;
import org.apache.derby.shared.common.error.StandardException;

class TablePrivilegesNode extends QueryTreeNode {
   private boolean[] actionAllowed = new boolean[6];
   private ResultColumnList[] columnLists = new ResultColumnList[6];
   private FormatableBitSet[] columnBitSets = new FormatableBitSet[6];
   private TableDescriptor td;
   private List descriptorList;

   TablePrivilegesNode(ContextManager var1) {
      super(var1);
   }

   void addAll() {
      for(int var1 = 0; var1 < 6; ++var1) {
         this.actionAllowed[var1] = true;
         this.columnLists[var1] = null;
      }

   }

   void addAction(int var1, ResultColumnList var2) {
      this.actionAllowed[var1] = true;
      if (var2 == null) {
         this.columnLists[var1] = null;
      } else if (this.columnLists[var1] == null) {
         this.columnLists[var1] = var2;
      } else {
         this.columnLists[var1].appendResultColumns(var2, false);
      }

   }

   void bind(TableDescriptor var1, boolean var2) throws StandardException {
      this.td = var1;

      for(int var3 = 0; var3 < 6; ++var3) {
         if (this.columnLists[var3] != null) {
            this.columnBitSets[var3] = this.columnLists[var3].bindResultColumnsByName(var1, (DMLStatementNode)null);
         }

         if (var1.getTableType() == 2 && var3 != 0 && this.actionAllowed[var3]) {
            throw StandardException.newException("42509", new Object[]{var1.getQualifiedName()});
         }
      }

      if (var2 && var1.getTableType() == 2) {
         this.bindPrivilegesForView(var1);
      }

   }

   PrivilegeInfo makePrivilegeInfo() {
      return new TablePrivilegeInfo(this.td, this.actionAllowed, this.columnBitSets, this.descriptorList);
   }

   private void bindPrivilegesForView(TableDescriptor var1) throws StandardException {
      LanguageConnectionContext var2 = this.getLanguageConnectionContext();
      DataDictionary var3 = var2.getDataDictionary();
      ViewDescriptor var4 = var3.getViewDescriptor(var1);
      DependencyManager var5 = var3.getDependencyManager();
      ProviderInfo[] var6 = var5.getPersistentProviderInfos((Dependent)var4);
      this.descriptorList = new ArrayList();
      int var7 = var6.length;

      for(int var8 = 0; var8 < var7; ++var8) {
         Provider var9 = (Provider)var6[var8].getDependableFinder().getDependable(var3, var6[var8].getObjectId());
         if (var9 instanceof TableDescriptor || var9 instanceof ViewDescriptor || var9 instanceof AliasDescriptor) {
            this.descriptorList.add(var9);
         }
      }

   }
}
