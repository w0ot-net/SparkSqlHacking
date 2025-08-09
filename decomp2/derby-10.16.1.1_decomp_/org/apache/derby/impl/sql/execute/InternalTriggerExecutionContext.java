package org.apache.derby.impl.sql.execute;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.db.TriggerExecutionContext;
import org.apache.derby.iapi.jdbc.ConnectionContext;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.TriggerDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionStmtValidator;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.PublicAPI;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

class InternalTriggerExecutionContext implements TriggerExecutionContext, ExecutionStmtValidator {
   protected int dmlType;
   protected String statementText;
   protected ConnectionContext cc;
   protected UUID targetTableId;
   protected String targetTableName;
   protected LanguageConnectionContext lcc;
   protected CursorResultSet beforeResultSet;
   protected CursorResultSet afterResultSet;
   protected ExecRow afterRow;
   protected boolean cleanupCalled;
   protected TriggerEvent event;
   protected TriggerDescriptor triggerd;
   private Vector resultSetVector;
   private Vector aiCounters;
   private Hashtable aiHT;

   InternalTriggerExecutionContext(LanguageConnectionContext var1, ConnectionContext var2, String var3, int var4, UUID var5, String var6, Vector var7) throws StandardException {
      this.dmlType = var4;
      this.statementText = var3;
      this.cc = var2;
      this.lcc = var1;
      this.targetTableId = var5;
      this.targetTableName = var6;
      this.resultSetVector = new Vector();
      this.aiCounters = var7;
      var1.pushTriggerExecutionContext(this);
   }

   void setBeforeResultSet(CursorResultSet var1) {
      this.beforeResultSet = var1;
   }

   void setAfterResultSet(CursorResultSet var1) throws StandardException {
      this.afterResultSet = var1;
      if (this.aiCounters != null) {
         if (this.triggerd.isRowTrigger()) {
            var1.open();
            this.afterRow = var1.getNextRow();
            var1.close();
         } else if (!this.triggerd.isBeforeTrigger()) {
            this.resetAICounters(false);
         }
      }

   }

   void setCurrentTriggerEvent(TriggerEvent var1) {
      this.event = var1;
   }

   void clearCurrentTriggerEvent() {
      this.event = null;
   }

   void setTrigger(TriggerDescriptor var1) {
      this.triggerd = var1;
   }

   void clearTrigger() throws StandardException {
      this.event = null;
      this.triggerd = null;
      if (this.afterResultSet != null) {
         this.afterResultSet.close();
         this.afterResultSet = null;
      }

      if (this.beforeResultSet != null) {
         this.beforeResultSet.close();
         this.beforeResultSet = null;
      }

   }

   protected void cleanup() throws StandardException {
      if (this.lcc != null) {
         this.lcc.popTriggerExecutionContext(this);
      }

      if (this.resultSetVector != null) {
         for(ResultSet var2 : this.resultSetVector) {
            try {
               var2.close();
            } catch (SQLException var4) {
            }
         }
      }

      this.resultSetVector = null;
      if (this.afterResultSet != null) {
         this.afterResultSet.close();
         this.afterResultSet = null;
      }

      if (this.beforeResultSet != null) {
         this.beforeResultSet.close();
         this.beforeResultSet = null;
      }

      this.lcc = null;
      this.cleanupCalled = true;
   }

   private void ensureProperContext() throws SQLException {
      if (this.cleanupCalled) {
         throw new SQLException(MessageService.getTextMessage("XCL31.S", new Object[0]), "XCL31", 20000);
      }
   }

   public void validateStatement(ConstantAction var1) throws StandardException {
      if (var1 instanceof DDLConstantAction) {
         throw StandardException.newException("X0Y69.S", new Object[]{this.triggerd.getName()});
      }
   }

   public String getTargetTableName() {
      return this.targetTableName;
   }

   public UUID getTargetTableId() {
      return this.targetTableId;
   }

   public int getEventType() {
      return this.dmlType;
   }

   public String getEventStatementText() {
      return this.statementText;
   }

   public ResultSet getOldRowSet() throws SQLException {
      this.ensureProperContext();
      if (this.beforeResultSet == null) {
         return null;
      } else {
         try {
            CursorResultSet var1 = this.beforeResultSet;
            if (var1 instanceof TemporaryRowHolderResultSet) {
               var1 = (CursorResultSet)((TemporaryRowHolderResultSet)var1).clone();
            } else if (var1 instanceof TableScanResultSet) {
               var1 = (CursorResultSet)((TableScanResultSet)var1).clone();
            }

            var1.open();
            ResultSet var2 = this.cc.getResultSet(var1);
            this.resultSetVector.addElement(var2);
            return var2;
         } catch (StandardException var3) {
            throw PublicAPI.wrapStandardException(var3);
         }
      }
   }

   public ResultSet getNewRowSet() throws SQLException {
      this.ensureProperContext();
      if (this.afterResultSet == null) {
         return null;
      } else {
         try {
            CursorResultSet var1 = this.afterResultSet;
            if (var1 instanceof TemporaryRowHolderResultSet) {
               var1 = (CursorResultSet)((TemporaryRowHolderResultSet)var1).clone();
            } else if (var1 instanceof TableScanResultSet) {
               var1 = (CursorResultSet)((TableScanResultSet)var1).clone();
            }

            var1.open();
            ResultSet var2 = this.cc.getResultSet(var1);
            this.resultSetVector.addElement(var2);
            return var2;
         } catch (StandardException var3) {
            throw PublicAPI.wrapStandardException(var3);
         }
      }
   }

   public ResultSet getOldRow() throws SQLException {
      ResultSet var1 = this.getOldRowSet();
      if (var1 != null) {
         var1.next();
      }

      return var1;
   }

   public ResultSet getNewRow() throws SQLException {
      ResultSet var1 = this.getNewRowSet();
      if (var1 != null) {
         var1.next();
      }

      return var1;
   }

   public Long getAutoincrementValue(String var1) {
      if (this.aiHT != null) {
         Long var2 = (Long)this.aiHT.get(var1);
         if (var2 != null) {
            return var2;
         }
      }

      if (this.aiCounters != null) {
         for(AutoincrementCounter var3 : this.aiCounters) {
            if (var1.equals(var3.getIdentity())) {
               return var3.getCurrentValue();
            }
         }
      }

      return null;
   }

   public void copyHashtableToAIHT(Map var1) {
      if (var1 != null) {
         if (this.aiHT == null) {
            this.aiHT = new Hashtable();
         }

         this.aiHT.putAll(var1);
      }
   }

   public void resetAICounters(boolean var1) {
      if (this.aiCounters != null) {
         this.afterRow = null;

         for(AutoincrementCounter var3 : this.aiCounters) {
            var3.reset(var1);
         }

      }
   }

   public void updateAICounters() throws StandardException {
      if (this.aiCounters != null) {
         for(AutoincrementCounter var2 : this.aiCounters) {
            DataValueDescriptor var3 = this.afterRow.getColumn(var2.getColumnPosition());
            var2.update(var3.getLong());
         }

      }
   }

   public String toString() {
      return this.triggerd.getName();
   }
}
