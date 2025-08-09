package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.shared.common.error.StandardException;

class GroupByList extends OrderedColumnList {
   int numGroupingColsAdded = 0;
   boolean rollup = false;

   public GroupByList(ContextManager var1) {
      super(GroupByColumn.class, var1);
   }

   void addGroupByColumn(GroupByColumn var1) {
      this.addElement(var1);
   }

   GroupByColumn getGroupByColumn(int var1) {
      return (GroupByColumn)this.elementAt(var1);
   }

   void setRollup() {
      this.rollup = true;
   }

   boolean isRollup() {
      return this.rollup;
   }

   int getNumNeedToAddGroupingCols() {
      return this.numGroupingColsAdded;
   }

   void bindGroupByColumns(SelectNode var1, List var2) throws StandardException {
      FromList var3 = var1.getFromList();
      ResultColumnList var4 = var1.getResultColumns();
      SubqueryList var5 = new SubqueryList(this.getContextManager());
      int var6 = 0;
      if (this.size() > 32677) {
         throw StandardException.newException("54004", new Object[0]);
      } else {
         for(GroupByColumn var8 : this) {
            var8.bindExpression(var3, var5, var2);
         }

         int var14 = var4.size();

         for(GroupByColumn var9 : this) {
            boolean var10 = false;

            for(int var11 = 0; var11 < var14; ++var11) {
               ResultColumn var12 = (ResultColumn)var4.elementAt(var11);
               if (var12.getExpression() instanceof ColumnReference) {
                  ColumnReference var13 = (ColumnReference)var12.getExpression();
                  if (var13.isEquivalent(var9.getColumnExpression())) {
                     var9.setColumnPosition(var11 + 1);
                     var12.markAsGroupingColumn();
                     var10 = true;
                     break;
                  }
               }
            }

            if (!var10 && !var1.hasDistinct() && var9.getColumnExpression() instanceof ColumnReference) {
               ResultColumn var16 = new ResultColumn(var9.getColumnName(), var9.getColumnExpression().getClone(), this.getContextManager());
               var16.setVirtualColumnId(var4.size() + 1);
               var16.markGenerated();
               var16.markAsGroupingColumn();
               var4.addElement(var16);
               var9.setColumnPosition(var4.size());
               var4.setCountMismatchAllowed(true);
               ++var6;
            }

            if (var9.getColumnExpression() instanceof JavaToSQLValueNode) {
               throw StandardException.newException("42Y30", new Object[0]);
            }
         }

         if (var5.size() != 0) {
            throw StandardException.newException("42Y26.S.1", new Object[0]);
         } else {
            this.numGroupingColsAdded += var6;
         }
      }
   }

   GroupByColumn findGroupingColumn(ValueNode var1) throws StandardException {
      for(GroupByColumn var3 : this) {
         if (var3.getColumnExpression().isEquivalent(var1)) {
            return var3;
         }
      }

      return null;
   }

   void remapColumnReferencesToExpressions() throws StandardException {
      for(GroupByColumn var2 : this) {
         var2.setColumnExpression(var2.getColumnExpression().remapColumnReferencesToExpressions());
      }

   }

   public String toString() {
      return "";
   }

   void preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
      for(GroupByColumn var6 : this) {
         var6.setColumnExpression(var6.getColumnExpression().preprocess(var1, var2, var3, var4));
      }

   }
}
