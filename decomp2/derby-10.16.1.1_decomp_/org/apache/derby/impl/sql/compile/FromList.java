package org.apache.derby.impl.sql.compile;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.compile.OptimizableList;
import org.apache.derby.iapi.sql.compile.Optimizer;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.error.StandardException;

class FromList extends QueryTreeNodeVector implements OptimizableList {
   Properties properties;
   boolean fixedJoinOrder = true;
   boolean useStatistics = true;
   private boolean referencesSessionSchema;
   private boolean isTransparent;
   private WindowList windows;

   FromList(ContextManager var1) {
      super(ResultSetNode.class, var1);
      this.isTransparent = false;
   }

   FromList(boolean var1, ContextManager var2) {
      super(ResultSetNode.class, var2);
      this.constructorMinion(var1);
   }

   FromList(boolean var1, FromTable var2, ContextManager var3) throws StandardException {
      super(ResultSetNode.class, var3);
      this.constructorMinion(var1);
      this.addFromTable(var2);
   }

   private void constructorMinion(boolean var1) {
      this.fixedJoinOrder = !var1;
      this.isTransparent = false;
   }

   public Optimizable getOptimizable(int var1) {
      return (Optimizable)this.elementAt(var1);
   }

   public void setOptimizable(int var1, Optimizable var2) {
      this.setElementAt((FromTable)var2, var1);
   }

   public void verifyProperties(DataDictionary var1) throws StandardException {
      int var2 = this.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         ((Optimizable)this.elementAt(var3)).verifyProperties(var1);
      }

   }

   final void addFromTable(FromTable var1) throws StandardException {
      if (!(var1 instanceof TableOperatorNode)) {
         int var4 = this.size();

         for(int var5 = 0; var5 < var4; ++var5) {
            TableName var2 = var1.getTableName();
            if (!((FromTable)this.elementAt(var5) instanceof TableOperatorNode)) {
               TableName var3 = ((FromTable)this.elementAt(var5)).getTableName();
               if (var2.equals(var3)) {
                  throw StandardException.newException("42X09", new Object[]{var1.getExposedName()});
               }
            }
         }
      }

      this.addElement(var1);
   }

   boolean referencesTarget(String var1, boolean var2) throws StandardException {
      boolean var4 = false;
      int var5 = this.size();

      for(int var6 = 0; var6 < var5; ++var6) {
         FromTable var3 = (FromTable)this.elementAt(var6);
         if (var3.referencesTarget(var1, var2)) {
            var4 = true;
            break;
         }
      }

      return var4;
   }

   public boolean referencesSessionSchema() throws StandardException {
      boolean var2 = false;
      if (this.referencesSessionSchema) {
         return true;
      } else {
         int var3 = this.size();

         for(int var4 = 0; var4 < var3; ++var4) {
            FromTable var1 = (FromTable)this.elementAt(var4);
            if (var1.referencesSessionSchema()) {
               var2 = true;
               break;
            }
         }

         return var2;
      }
   }

   FromTable getFromTableByName(String var1, String var2, boolean var3) throws StandardException {
      FromTable var5 = null;
      int var6 = this.size();

      for(int var7 = 0; var7 < var6; ++var7) {
         FromTable var4 = (FromTable)this.elementAt(var7);
         var5 = var4.getFromTableByName(var1, var2, var3);
         if (var5 != null) {
            return var5;
         }
      }

      return var5;
   }

   void isJoinColumnForRightOuterJoin(ResultColumn var1) {
      int var3 = this.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         FromTable var2 = (FromTable)this.elementAt(var4);
         var2.isJoinColumnForRightOuterJoin(var1);
      }

   }

   void bindTables(DataDictionary var1, FromList var2) throws StandardException {
      boolean var3 = this.getCompilerContext().skipTypePrivileges(true);
      int var5 = this.size();

      for(int var6 = 0; var6 < var5; ++var6) {
         FromTable var4 = (FromTable)this.elementAt(var6);
         FromTable var7 = (FromTable)var4.bindNonVTITables(var1, var2);
         if (var4.referencesSessionSchema()) {
            this.referencesSessionSchema = true;
         }

         var7.setMergeTableID(var4.getMergeTableID());
         this.setElementAt(var7, var6);
      }

      for(int var10 = 0; var10 < var5; ++var10) {
         FromTable var8 = (FromTable)this.elementAt(var10);
         FromTable var12 = (FromTable)var8.bindVTITables(var2);
         if (var8.referencesSessionSchema()) {
            this.referencesSessionSchema = true;
         }

         var12.setMergeTableID(var8.getMergeTableID());
         this.setElementAt(var12, var10);
      }

      CompilerContext var11 = this.getCompilerContext();
      var11.pushCurrentPrivType(8);

      for(int var13 = 0; var13 < var5; ++var13) {
         FromTable var9 = (FromTable)this.elementAt(var13);
         if (var9.isPrivilegeCollectionRequired() && var9.isBaseTable() && !var9.forUpdate()) {
            var11.addRequiredColumnPriv(var9.getTableDescriptor().getColumnDescriptor(1));
         }
      }

      var11.popCurrentPrivType();
      this.getCompilerContext().skipTypePrivileges(var3);
   }

   void bindExpressions(FromList var1) throws StandardException {
      int var3 = this.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         FromTable var2 = (FromTable)this.elementAt(var4);
         var2.bindExpressions(this.isTransparent ? var1 : this);
      }

   }

   void bindResultColumns(FromList var1) throws StandardException {
      int var3 = var1.size();
      int var4 = this.size();

      for(int var5 = 0; var5 < var4; ++var5) {
         FromTable var2 = (FromTable)this.elementAt(var5);
         if (var2.needsSpecialRCLBinding()) {
            var2.bindResultColumns(var1);
         }

         var1.insertElementAt(var2, 0);
      }

      while(var1.size() > var3) {
         var1.removeElementAt(0);
      }

   }

   ResultColumnList expandAll(TableName var1) throws StandardException {
      ResultColumnList var2 = null;
      boolean var4 = false;
      int var6 = ((FromTable)this.elementAt(0)).getLevel();
      int var7 = this.size();

      for(int var8 = 0; var8 < var7; ++var8) {
         FromTable var5 = (FromTable)this.elementAt(var8);
         if (var6 != var5.getLevel()) {
            break;
         }

         ResultColumnList var3 = var5.getAllResultColumns(var1);
         if (var3 != null) {
            if (var2 == null) {
               var2 = var3;
            } else {
               var2.nondestructiveAppend(var3);
            }

            if (var1 != null) {
               var4 = true;
            }
         }
      }

      if (var2 == null) {
         throw StandardException.newException("42X10", new Object[]{var1});
      } else {
         return var2;
      }
   }

   ResultColumn bindColumnReference(ColumnReference var1) throws StandardException {
      boolean var2 = false;
      boolean var3 = false;
      FromTable var4 = null;
      FromTable var5 = null;
      int var7 = -1;
      ResultColumn var8 = null;
      String var10 = var1.getTableName();
      int var11 = this.size();

      for(int var12 = 0; var12 < var11; ++var12) {
         var4 = (FromTable)this.elementAt(var12);
         if (var4.getMergeTableID() == 0 || var1.getMergeTableID() == 0 || var4.getMergeTableID() == var1.getMergeTableID()) {
            int var6 = var4.getLevel();
            if (var7 != var6 && (var2 || var3)) {
               break;
            }

            var7 = var6;
            ResultColumn var9 = var4.getMatchingColumn(var1);
            if (var9 != null) {
               if (var2) {
                  throw StandardException.newException("42X03", new Object[]{var1.getSQLColumnName()});
               }

               var8 = var9;
               var1.setSource(var9);
               var1.setNestingLevel(((FromTable)this.elementAt(0)).getLevel());
               var1.setSourceLevel(var6);
               var2 = true;
               if (var4.isPrivilegeCollectionRequired()) {
                  this.getCompilerContext().addRequiredColumnPriv(var9.getTableColumnDescriptor());
               }

               var5 = var4;
            }

            var3 = var3 || var10 != null && var10.equals(var4.getExposedName());
         }
      }

      if (var5 != null && var8 != null && var1.getTableName() == null) {
         TableName var15 = var5.getTableName();
         if (var5 instanceof FromBaseTable) {
            FromBaseTable var13 = (FromBaseTable)var5;
            if (var13.getExposedTableName() != null) {
               var15 = var13.getExposedTableName();
            }
         }

         var1.setQualifiedTableName(var15);
      }

      return var8;
   }

   void rejectParameters() throws StandardException {
      int var2 = this.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         FromTable var1 = (FromTable)this.elementAt(var3);
         var1.rejectParameters();
      }

   }

   boolean LOJ_reorderable(int var1) throws StandardException {
      boolean var2 = false;
      if (this.size() > 1) {
         return var2;
      } else {
         FromTable var3 = (FromTable)this.elementAt(0);
         var2 = var3.LOJ_reorderable(var1);
         return var2;
      }
   }

   void preprocess(int var1, GroupByList var2, ValueNode var3) throws StandardException {
      int var4 = this.size();

      for(int var5 = 0; var5 < var4; ++var5) {
         FromTable var6 = (FromTable)this.elementAt(var5);
         var6 = var6.transformOuterJoins(var3, var1);
         this.setElementAt(var6.preprocess(var1, var2, this), var5);
      }

   }

   void flattenFromTables(ResultColumnList var1, PredicateList var2, SubqueryList var3, GroupByList var4, ValueNode var5) throws StandardException {
      boolean var6 = true;
      ArrayList var7 = new ArrayList();

      while(var6) {
         var6 = false;

         for(int var8 = 0; var8 < this.size() && !var6; ++var8) {
            FromTable var9 = (FromTable)this.elementAt(var8);
            if (var9 instanceof FromSubquery || var9.isFlattenableJoinNode()) {
               var7.add(var9.getTableNumber());
               FromList var10 = var9.flatten(var1, var2, var3, var4, var5);
               if (var10 != null) {
                  this.setElementAt((ResultSetNode)var10.elementAt(0), var8);
                  int var11 = var10.size();

                  for(int var12 = 1; var12 < var11; ++var12) {
                     this.insertElementAt((ResultSetNode)var10.elementAt(var12), var8 + var12);
                  }
               } else {
                  this.removeElementAt(var8);
               }

               var6 = true;
            }
         }
      }

      if (!var7.isEmpty()) {
         for(int var13 = 0; var13 < this.size(); ++var13) {
            FromTable var14 = (FromTable)this.elementAt(var13);
            if (var14 instanceof ProjectRestrictNode) {
               ResultSetNode var15 = ((ProjectRestrictNode)var14).getChildResult();
               if (var15 instanceof FromBaseTable) {
                  ((FromBaseTable)var15).clearDependency(var7);
               }
            }
         }
      }

   }

   void pushPredicates(PredicateList var1) throws StandardException {
      var1.categorize();
      int var2 = this.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         FromTable var4 = (FromTable)this.elementAt(var3);
         var4.pushExpressions(var1);
      }

   }

   void setLevel(int var1) {
      int var2 = this.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         FromTable var4 = (FromTable)this.elementAt(var3);
         var4.setLevel(var1);
      }

   }

   FromTable getFromTableByResultColumn(ResultColumn var1) {
      FromTable var2 = null;
      int var3 = this.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         var2 = (FromTable)this.elementAt(var4);
         if (var2.getResultColumns().indexOf(var1) != -1) {
            break;
         }
      }

      return var2;
   }

   void setProperties(Properties var1) throws StandardException {
      this.properties = var1;
      Enumeration var2 = this.properties.keys();

      while(var2.hasMoreElements()) {
         String var3 = (String)var2.nextElement();
         String var4 = (String)this.properties.get(var3);
         if (var3.equals("joinOrder")) {
            if (StringUtil.SQLEqualsIgnoreCase(var4, "fixed")) {
               this.fixedJoinOrder = true;
            } else {
               if (!StringUtil.SQLEqualsIgnoreCase(var4, "unfixed")) {
                  throw StandardException.newException("42X17", new Object[]{var4});
               }

               this.fixedJoinOrder = false;
            }
         } else {
            if (!var3.equals("useStatistics")) {
               throw StandardException.newException("42X41", new Object[]{var3, var4});
            }

            if (StringUtil.SQLEqualsIgnoreCase(var4, "true")) {
               this.useStatistics = true;
            } else {
               if (!StringUtil.SQLEqualsIgnoreCase(var4, "false")) {
                  throw StandardException.newException("42X64", new Object[]{var4});
               }

               this.useStatistics = false;
            }
         }
      }

   }

   public void reOrder(int[] var1) {
      FromTable[] var3 = new FromTable[var1.length];

      for(int var2 = 0; var2 < var1.length; ++var2) {
         var3[var2] = (ResultSetNode)this.elementAt(var1[var2]);
      }

      for(int var4 = 0; var4 < var1.length; ++var4) {
         this.setElementAt(var3[var4], var4);
      }

   }

   public boolean useStatistics() {
      return this.useStatistics;
   }

   public boolean optimizeJoinOrder() {
      return !this.fixedJoinOrder;
   }

   public boolean legalJoinOrder(int var1) {
      JBitSet var2 = new JBitSet(var1);
      int var3 = this.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         FromTable var5 = (FromTable)this.elementAt(var4);
         var2.or(var5.getReferencedTableMap());
         if (!var5.legalJoinOrder(var2)) {
            return false;
         }
      }

      return true;
   }

   public void initAccessPaths(Optimizer var1) {
      int var2 = this.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         FromTable var4 = (FromTable)this.elementAt(var3);
         var4.initAccessPaths(var1);
      }

   }

   void bindUntypedNullsToResultColumns(ResultColumnList var1) throws StandardException {
      int var2 = this.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         FromTable var4 = (FromTable)this.elementAt(var3);
         var4.bindUntypedNullsToResultColumns(var1);
      }

   }

   void decrementLevel(int var1) {
      int var2 = this.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         FromTable var4 = (FromTable)this.elementAt(var3);
         var4.decrementLevel(var1);
         ProjectRestrictNode var5 = (ProjectRestrictNode)var4;
         PredicateList var6 = var5.getRestrictionList();
         if (var6 != null) {
            var6.decrementLevel(this, var1);
         }
      }

   }

   boolean returnsAtMostSingleRow(ResultColumnList var1, ValueNode var2, PredicateList var3, DataDictionary var4) throws StandardException {
      boolean var5 = false;
      ColumnReference var7 = null;
      PredicateList var8 = new PredicateList(this.getContextManager());

      for(Predicate var10 : var3) {
         var8.addPredicate(var10);
      }

      if (var1 != null) {
         ResultColumn var21 = (ResultColumn)var1.elementAt(0);
         if (var21.getExpression() instanceof ColumnReference) {
            var7 = (ColumnReference)var21.getExpression();
         }
      }

      int var22 = this.size();

      for(int var23 = 0; var23 < var22; ++var23) {
         FromTable var11 = (FromTable)this.elementAt(var23);
         if (!(var11 instanceof ProjectRestrictNode)) {
            return false;
         }

         ProjectRestrictNode var12 = (ProjectRestrictNode)var11;
         if (!(var12.getChildResult() instanceof FromBaseTable)) {
            return false;
         }

         FromBaseTable var13 = (FromBaseTable)var12.getChildResult();
         if (var13.getExistsBaseTable()) {
            int var14 = var13.getTableNumber();

            for(int var15 = var8.size() - 1; var15 >= 0; --var15) {
               AndNode var16 = ((Predicate)var8.elementAt(var15)).getAndNode();

               for(Object var17 = var16; var17 instanceof AndNode; var17 = ((AndNode)var17).getRightOperand()) {
                  AndNode var18 = (AndNode)var17;
                  if (var18.getLeftOperand().isRelationalOperator() && ((RelationalOperator)var18.getLeftOperand()).getOperator() == 1) {
                     JBitSet var19 = var18.getLeftOperand().getTablesReferenced();
                     if (var19.get(var14)) {
                        var8.removeElementAt(var15);
                        break;
                     }
                  }
               }
            }
         }
      }

      int[] var6 = this.getTableNumbers();
      JBitSet[][] var24 = new JBitSet[var22][var22];
      boolean[] var25 = new boolean[var22];

      for(int var27 = 0; var27 < var22; ++var27) {
         ProjectRestrictNode var29 = (ProjectRestrictNode)this.elementAt(var27);
         FromBaseTable var32 = (FromBaseTable)var29.getChildResult();
         if (var32.getExistsBaseTable()) {
            var25[var27] = true;
         } else {
            int var34 = var32.getTableDescriptor().getNumberOfColumns();
            boolean[] var35 = new boolean[var34 + 1];
            int var36 = var32.getTableNumber();
            boolean var37 = false;

            for(int var20 = 0; var20 < var22; ++var20) {
               var24[var27][var20] = new JBitSet(var34 + 1);
            }

            if (var7 != null && var7.getTableNumber() == var36) {
               var1.recordColumnReferences(var35, var24[var27], var27);
               var37 = true;
            }

            if (var2 != null) {
               var2.checkTopPredicatesForEqualsConditions(var36, var35, var6, var24[var27], var37);
            }

            var8.checkTopPredicatesForEqualsConditions(var36, var35, var6, var24[var27], var37);
            if (var29.getRestrictionList() != null) {
               var29.getRestrictionList().checkTopPredicatesForEqualsConditions(var36, var35, var6, var24[var27], var37);
            }

            if (!var32.supersetOfUniqueIndex(var24[var27])) {
               return false;
            }

            boolean var26 = var32.supersetOfUniqueIndex(var35);
            if (var26) {
               var25[var27] = true;
               var5 = true;
            }
         }
      }

      if (var5) {
         boolean var28 = true;

         while(var28) {
            var28 = false;

            for(int var30 = 0; var30 < var22; ++var30) {
               if (var25[var30]) {
                  for(int var33 = 0; var33 < var22; ++var33) {
                     if (!var25[var33] && var24[var33][var30].get(0)) {
                        var25[var33] = true;
                        var28 = true;
                     }
                  }
               }
            }
         }

         for(int var31 = 0; var31 < var22; ++var31) {
            if (!var25[var31]) {
               var5 = false;
               break;
            }
         }
      }

      return var5;
   }

   int[] getTableNumbers() {
      int var1 = this.size();
      int[] var2 = new int[var1];

      for(int var3 = 0; var3 < var1; ++var3) {
         ProjectRestrictNode var4 = (ProjectRestrictNode)this.elementAt(var3);
         if (var4.getChildResult() instanceof FromTable) {
            FromTable var5 = (FromTable)var4.getChildResult();
            var2[var3] = var5.getTableNumber();
         }
      }

      return var2;
   }

   void genExistsBaseTables(JBitSet var1, FromList var2, boolean var3) throws StandardException {
      JBitSet var4 = (JBitSet)var1.clone();
      int var5 = this.size();

      for(int var6 = 0; var6 < var5; ++var6) {
         ResultSetNode var7 = ((ProjectRestrictNode)this.elementAt(var6)).getChildResult();
         if (var7 instanceof FromTable) {
            var4.clear(((FromTable)var7).getTableNumber());
         }
      }

      if (var4.getFirstSetBit() == -1) {
         int var10 = var2.size();

         for(int var12 = 0; var12 < var10; ++var12) {
            var4.or(((FromTable)var2.elementAt(var12)).getReferencedTableMap());
         }
      }

      for(int var11 = 0; var11 < var5; ++var11) {
         FromTable var13 = (FromTable)this.elementAt(var11);
         if (var13 instanceof ProjectRestrictNode var8) {
            if (var8.getChildResult() instanceof FromBaseTable) {
               FromBaseTable var9 = (FromBaseTable)var8.getChildResult();
               var9.setExistsBaseTable(true, (JBitSet)var4.clone(), var3);
            }
         }
      }

   }

   boolean tableNumberIsNotExists(int var1) throws StandardException {
      int var2 = this.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         ProjectRestrictNode var4 = (ProjectRestrictNode)this.elementAt(var3);
         if (var4.getChildResult() instanceof FromTable) {
            FromTable var5 = (FromTable)var4.getChildResult();
            if (var5.getTableNumber() == var1) {
               return var5.isNotExists();
            }
         }
      }

      return false;
   }

   int updateTargetLockMode() {
      return ((ResultSetNode)this.elementAt(0)).updateTargetLockMode();
   }

   boolean hashJoinSpecified() {
      int var1 = this.size();

      for(int var2 = 0; var2 < var1; ++var2) {
         FromTable var3 = (FromTable)this.elementAt(var2);
         String var4 = var3.getUserSpecifiedJoinStrategy();
         if (var4 != null && StringUtil.SQLToUpperCase(var4).equals("HASH")) {
            return true;
         }
      }

      return false;
   }

   void markAsTransparent() {
      this.isTransparent = true;
   }

   void setWindows(WindowList var1) {
      this.windows = var1;
   }

   WindowList getWindows() {
      return this.windows;
   }
}
