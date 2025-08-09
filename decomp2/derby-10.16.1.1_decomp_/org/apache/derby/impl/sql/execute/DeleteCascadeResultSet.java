package org.apache.derby.impl.sql.execute;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.sql.execute.TemporaryRowHolder;
import org.apache.derby.shared.common.error.StandardException;

class DeleteCascadeResultSet extends DeleteResultSet {
   ResultSet[] dependentResultSets;
   private int noDependents = 0;
   private final String resultSetId;
   private boolean mainNodeForTable = true;
   private boolean affectedRows = false;
   private int tempRowHolderId;

   public DeleteCascadeResultSet(NoPutResultSet var1, Activation var2, int var3, ResultSet[] var4, String var5) throws StandardException {
      super(var1, var3 == -1 ? var2.getConstantAction() : (ConstantAction)var2.getPreparedStatement().getSavedObject(var3), var2);
      if (var3 == -1) {
         ConstantAction var6 = var2.getConstantAction();
      } else {
         ConstantAction var10000 = (ConstantAction)var2.getPreparedStatement().getSavedObject(var3);
         this.resultDescription = this.constants.resultDescription;
      }

      this.cascadeDelete = true;
      this.resultSetId = var5;
      if (var4 != null) {
         this.noDependents = var4.length;
         this.dependentResultSets = var4;
      }

   }

   public void open() throws StandardException {
      try {
         this.setup();
         if (this.isMultipleDeletePathsExist()) {
            this.setRowHoldersTypeToUniqueStream();

            while(this.collectAffectedRows(false)) {
            }
         } else {
            this.collectAffectedRows(false);
         }

         if (!this.affectedRows) {
            this.activation.addWarning(StandardException.newWarning("02000", new Object[0]));
         }

         this.runFkChecker(true);
         HashMap var1 = new HashMap();
         this.mergeRowHolders(var1);
         this.fireBeforeTriggers(var1);
         this.deleteDeferredRows();
         this.runFkChecker(false);
         this.rowChangerFinish();
         this.fireAfterTriggers();
      } finally {
         this.cleanUp();
         this.activation.clearParentResultSets();
      }

      this.endTime = this.getCurrentTimeMillis();
   }

   void setup() throws StandardException {
      if (this.lcc.getRunTimeStatisticsMode()) {
         this.savedSource = this.source;
      }

      super.setup();
      this.activation.setParentResultSet(this.rowHolder, this.resultSetId);
      Vector var1 = this.activation.getParentResultSet(this.resultSetId);
      this.tempRowHolderId = var1.size() - 1;

      for(int var2 = 0; var2 < this.noDependents; ++var2) {
         if (this.dependentResultSets[var2] instanceof UpdateResultSet) {
            ((UpdateResultSet)this.dependentResultSets[var2]).setup();
         } else {
            ((DeleteCascadeResultSet)this.dependentResultSets[var2]).setup();
         }
      }

   }

   boolean collectAffectedRows(boolean var1) throws StandardException {
      if (super.collectAffectedRows()) {
         this.affectedRows = true;
         var1 = true;
      }

      for(int var2 = 0; var2 < this.noDependents; ++var2) {
         if (this.dependentResultSets[var2] instanceof UpdateResultSet) {
            if (((UpdateResultSet)this.dependentResultSets[var2]).collectAffectedRows()) {
               var1 = true;
            }
         } else if (((DeleteCascadeResultSet)this.dependentResultSets[var2]).collectAffectedRows(var1)) {
            var1 = true;
         }
      }

      return var1;
   }

   void fireBeforeTriggers(HashMap var1) throws StandardException {
      if (!this.mainNodeForTable && !var1.containsKey(this.resultSetId)) {
         this.mainNodeForTable = true;
         var1.put(this.resultSetId, this.resultSetId);
      }

      for(int var2 = 0; var2 < this.noDependents; ++var2) {
         if (this.dependentResultSets[var2] instanceof UpdateResultSet) {
            ((UpdateResultSet)this.dependentResultSets[var2]).fireBeforeTriggers();
         } else {
            ((DeleteCascadeResultSet)this.dependentResultSets[var2]).fireBeforeTriggers(var1);
         }
      }

      if (this.mainNodeForTable && this.constants.deferred) {
         super.fireBeforeTriggers();
      }

   }

   void fireAfterTriggers() throws StandardException {
      for(int var1 = 0; var1 < this.noDependents && this.affectedRows; ++var1) {
         if (this.dependentResultSets[var1] instanceof UpdateResultSet) {
            ((UpdateResultSet)this.dependentResultSets[var1]).fireAfterTriggers();
         } else {
            ((DeleteCascadeResultSet)this.dependentResultSets[var1]).fireAfterTriggers();
         }
      }

      if (this.mainNodeForTable && this.constants.deferred) {
         super.fireAfterTriggers();
      }

   }

   void deleteDeferredRows() throws StandardException {
      for(int var1 = 0; var1 < this.noDependents; ++var1) {
         if (this.dependentResultSets[var1] instanceof UpdateResultSet) {
            ((UpdateResultSet)this.dependentResultSets[var1]).updateDeferredRows();
         } else {
            ((DeleteCascadeResultSet)this.dependentResultSets[var1]).deleteDeferredRows();
         }
      }

      if (this.mainNodeForTable) {
         super.deleteDeferredRows();
      }

   }

   void runFkChecker(boolean var1) throws StandardException {
      for(int var2 = 0; var2 < this.noDependents; ++var2) {
         if (this.dependentResultSets[var2] instanceof UpdateResultSet) {
            ((UpdateResultSet)this.dependentResultSets[var2]).runChecker(var1);
         } else {
            ((DeleteCascadeResultSet)this.dependentResultSets[var2]).runFkChecker(var1);
         }
      }

      if (this.mainNodeForTable) {
         super.runFkChecker(var1);
      }

   }

   public void cleanUp() throws StandardException {
      super.cleanUp();

      for(int var1 = 0; var1 < this.noDependents; ++var1) {
         if (this.dependentResultSets[var1] instanceof UpdateResultSet) {
            ((UpdateResultSet)this.dependentResultSets[var1]).cleanUp();
         } else {
            ((DeleteCascadeResultSet)this.dependentResultSets[var1]).cleanUp();
         }
      }

      this.endTime = this.getCurrentTimeMillis();
   }

   private void rowChangerFinish() throws StandardException {
      this.rc.finish();

      for(int var1 = 0; var1 < this.noDependents; ++var1) {
         if (this.dependentResultSets[var1] instanceof UpdateResultSet) {
            ((UpdateResultSet)this.dependentResultSets[var1]).rowChangerFinish();
         } else {
            ((DeleteCascadeResultSet)this.dependentResultSets[var1]).rowChangerFinish();
         }
      }

   }

   private void mergeRowHolders(HashMap var1) throws StandardException {
      if (!var1.containsKey(this.resultSetId) && this.rowCount != 0L) {
         this.mergeResultSets();
         this.mainNodeForTable = true;
         var1.put(this.resultSetId, this.resultSetId);
      } else {
         this.mainNodeForTable = false;
      }

      for(int var2 = 0; var2 < this.noDependents; ++var2) {
         if (this.dependentResultSets[var2] instanceof UpdateResultSet) {
            return;
         }

         ((DeleteCascadeResultSet)this.dependentResultSets[var2]).mergeRowHolders(var1);
      }

   }

   private void mergeResultSets() throws StandardException {
      Vector var1 = this.activation.getParentResultSet(this.resultSetId);
      int var2 = var1.size();
      if (var2 > 1) {
         int var4 = 0;

         while(var4 < var2) {
            if (var4 == this.tempRowHolderId) {
               ++var4;
            } else {
               TemporaryRowHolder var5 = (TemporaryRowHolder)var1.elementAt(var4);
               CursorResultSet var6 = var5.getResultSet();
               var6.open();

               ExecRow var3;
               while((var3 = var6.getNextRow()) != null) {
                  this.rowHolder.insert(var3);
               }

               var6.close();
               ++var4;
            }
         }
      }

   }

   public void finish() throws StandardException {
      super.finish();
      this.activation.clearParentResultSets();
   }

   private boolean isMultipleDeletePathsExist() {
      Enumeration var1 = this.activation.getParentResultSetKeys();

      while(var1.hasMoreElements()) {
         String var2 = (String)var1.nextElement();
         Vector var3 = this.activation.getParentResultSet(var2);
         if (var3.size() > 1) {
            return true;
         }
      }

      return false;
   }

   private void setRowHoldersTypeToUniqueStream() {
      Enumeration var1 = this.activation.getParentResultSetKeys();

      while(var1.hasMoreElements()) {
         String var2 = (String)var1.nextElement();
         Vector var3 = this.activation.getParentResultSet(var2);
         int var4 = var3.size();

         for(int var5 = 0; var5 < var4; ++var5) {
            TemporaryRowHolder var6 = (TemporaryRowHolder)var3.elementAt(var5);
            var6.setRowHolderTypeToUniqueStream();
         }
      }

   }
}
