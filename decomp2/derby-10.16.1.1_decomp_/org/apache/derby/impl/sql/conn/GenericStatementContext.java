package org.apache.derby.impl.sql.conn;

import java.util.ArrayList;
import java.util.TimerTask;
import org.apache.derby.iapi.services.context.ContextImpl;
import org.apache.derby.iapi.services.timer.TimerFactory;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ParameterValueSet;
import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.conn.SQLSessionContext;
import org.apache.derby.iapi.sql.conn.StatementContext;
import org.apache.derby.iapi.sql.depend.Dependency;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.shared.common.error.StandardException;

final class GenericStatementContext extends ContextImpl implements StatementContext {
   private boolean setSavePoint;
   private String internalSavePointName;
   private ResultSet topResultSet;
   private ArrayList dependencies;
   private NoPutResultSet[] subqueryTrackingArray;
   private NoPutResultSet[] materializedSubqueries;
   private final LanguageConnectionContext lcc;
   private boolean inUse = true;
   private volatile boolean cancellationFlag = false;
   private CancelQueryTask cancelTask = null;
   private boolean parentInTrigger;
   private boolean isForReadOnly = false;
   private boolean isAtomic;
   private boolean isSystemCode;
   private boolean rollbackParentContext;
   private boolean statementWasInvalidated;
   private String stmtText;
   private ParameterValueSet pvs;
   private short sqlAllowed = -1;
   private Activation activation;
   private SQLSessionContext sqlSessionContext;

   GenericStatementContext(LanguageConnectionContext var1) {
      super(var1.getContextManager(), "StatementContext");
      this.lcc = var1;
      this.internalSavePointName = var1.getUniqueSavepointName();
   }

   private static TimerFactory getTimerFactory() {
      return GenericLanguageConnectionFactory.getMonitor().getTimerFactory();
   }

   public void setInUse(boolean var1, boolean var2, boolean var3, String var4, ParameterValueSet var5, long var6) {
      this.inUse = true;
      this.parentInTrigger = var1;
      this.isForReadOnly = var3;
      this.isAtomic = var2;
      this.stmtText = var4;
      this.pvs = var5;
      this.rollbackParentContext = false;
      if (var6 > 0L) {
         this.cancelTask = new CancelQueryTask(this);
         getTimerFactory().schedule(this.cancelTask, var6);
      }

   }

   public void clearInUse() {
      this.stuffTopResultSet((ResultSet)null, (NoPutResultSet[])null);
      this.inUse = false;
      this.parentInTrigger = false;
      this.isAtomic = false;
      this.isForReadOnly = false;
      this.stmtText = null;
      this.sqlAllowed = -1;
      this.isSystemCode = false;
      this.rollbackParentContext = false;
      this.statementWasInvalidated = false;
      if (this.cancelTask != null) {
         this.cancelTask.forgetContext();
         this.cancelTask = null;
      }

      this.cancellationFlag = false;
      this.activation = null;
      this.sqlSessionContext = null;
   }

   public void setSavePoint() throws StandardException {
      this.pleaseBeOnStack();
      this.lcc.getTransactionExecute().setSavePoint(this.internalSavePointName, (Object)null);
      this.setSavePoint = true;
   }

   public void resetSavePoint() throws StandardException {
      if (this.inUse && this.setSavePoint) {
         this.lcc.getTransactionExecute().setSavePoint(this.internalSavePointName, (Object)null);
      }

   }

   public void clearSavePoint() throws StandardException {
      this.pleaseBeOnStack();
      this.lcc.getTransactionExecute().releaseSavePoint(this.internalSavePointName, (Object)null);
      this.setSavePoint = false;
   }

   public void setTopResultSet(ResultSet var1, NoPutResultSet[] var2) throws StandardException {
      this.pleaseBeOnStack();
      if (this.materializedSubqueries != null) {
         if (var2 != null) {
            for(int var3 = 0; var3 < var2.length; ++var3) {
               if (this.materializedSubqueries[var3] != null) {
                  var2[var3] = this.materializedSubqueries[var3];
               }
            }
         } else {
            var2 = this.materializedSubqueries;
         }

         this.materializedSubqueries = null;
      }

      this.stuffTopResultSet(var1, var2);
   }

   private void stuffTopResultSet(ResultSet var1, NoPutResultSet[] var2) {
      this.topResultSet = var1;
      this.subqueryTrackingArray = var2;
      this.dependencies = null;
   }

   public void setSubqueryResultSet(int var1, NoPutResultSet var2, int var3) throws StandardException {
      this.pleaseBeOnStack();
      if (this.subqueryTrackingArray == null) {
         if (this.topResultSet == null) {
            this.subqueryTrackingArray = new NoPutResultSet[var3];
            this.materializedSubqueries = new NoPutResultSet[var3];
         } else {
            this.subqueryTrackingArray = this.topResultSet.getSubqueryTrackingArray(var3);
         }
      }

      this.subqueryTrackingArray[var1] = var2;
      if (this.materializedSubqueries != null) {
         this.materializedSubqueries[var1] = var2;
      }

   }

   public NoPutResultSet[] getSubqueryTrackingArray() throws StandardException {
      this.pleaseBeOnStack();
      return this.subqueryTrackingArray;
   }

   public void addDependency(Dependency var1) throws StandardException {
      this.pleaseBeOnStack();
      if (this.dependencies == null) {
         this.dependencies = new ArrayList();
      }

      this.dependencies.add(var1);
   }

   public boolean inTrigger() {
      return this.parentInTrigger;
   }

   public void cleanupOnError(Throwable var1) throws StandardException {
      try {
         int var2 = 40000;
         if (var1 instanceof StandardException var3) {
            var2 = var3.getSeverity();
            if ("XCL32.S".equals(var3.getMessageId())) {
               this.statementWasInvalidated = true;
            }
         }

         if (this.inUse) {
            if (this.topResultSet != null) {
               this.topResultSet.cleanUp();
            }

            if (this.subqueryTrackingArray != null) {
               for(int var7 = 0; var7 < this.subqueryTrackingArray.length; ++var7) {
                  if (this.subqueryTrackingArray[var7] != null) {
                     this.subqueryTrackingArray[var7].cleanUp();
                  }
               }
            }

            if (this.dependencies != null) {
               DependencyManager var8 = this.lcc.getDataDictionary().getDependencyManager();

               for(Dependency var5 : this.dependencies) {
                  var8.clearInMemoryDependency(var5);
               }

               this.dependencies = null;
            }

            if (var2 <= 20000 && this.setSavePoint) {
               this.lcc.internalRollbackToSavepoint(this.internalSavePointName, false, (Object)null);
               this.clearSavePoint();
            }

            if (var2 >= 30000) {
               this.setSavePoint = false;
            }

            this.lcc.popStatementContext(this, var1);
         }
      } catch (Exception var6) {
         var6.initCause(var1);
         throw StandardException.unexpectedUserException(var6);
      }
   }

   public boolean isLastHandler(int var1) {
      return this.inUse && !this.rollbackParentContext && var1 == 20000;
   }

   public boolean onStack() {
      return this.inUse;
   }

   public boolean isAtomic() {
      return this.isAtomic;
   }

   public String getStatementText() {
      return this.stmtText;
   }

   private void pleaseBeOnStack() throws StandardException {
      if (!this.inUse) {
         throw StandardException.newException("40XC0", new Object[0]);
      }
   }

   public boolean inUse() {
      return this.inUse;
   }

   public boolean isForReadOnly() {
      return this.isForReadOnly;
   }

   public boolean isCancelled() {
      return this.cancellationFlag;
   }

   public void cancel() {
      this.cancellationFlag = true;
   }

   public void setSQLAllowed(short var1, boolean var2) {
      if (var2 || var1 > this.sqlAllowed) {
         this.sqlAllowed = var1;
      }

   }

   public short getSQLAllowed() {
      return !this.inUse ? 3 : this.sqlAllowed;
   }

   public void setParentRollback() {
      this.rollbackParentContext = true;
   }

   public void setSystemCode() {
      this.isSystemCode = true;
   }

   public boolean getSystemCode() {
      return this.isSystemCode;
   }

   public StringBuffer appendErrorInfo() {
      StringBuffer var1 = ((ContextImpl)this.lcc).appendErrorInfo();
      if (var1 != null) {
         var1.append("Failed Statement is: ");
         var1.append(this.getStatementText());
         if (this.pvs != null && this.pvs.getParameterCount() > 0) {
            int var10000 = this.pvs.getParameterCount();
            String var2 = " with " + var10000 + " parameters " + this.pvs.toString();
            var1.append(var2);
         }
      }

      return var1;
   }

   public void setActivation(Activation var1) {
      this.activation = var1;
   }

   public Activation getActivation() {
      return this.activation;
   }

   public SQLSessionContext getSQLSessionContext() {
      return this.sqlSessionContext;
   }

   public void setSQLSessionContext(SQLSessionContext var1) {
      this.sqlSessionContext = var1;
   }

   public boolean getStatementWasInvalidated() {
      return this.statementWasInvalidated;
   }

   private static class CancelQueryTask extends TimerTask {
      private StatementContext statementContext;

      public CancelQueryTask(StatementContext var1) {
         this.statementContext = var1;
      }

      public void run() {
         synchronized(this) {
            if (this.statementContext != null) {
               this.statementContext.cancel();
            }

         }
      }

      public void forgetContext() {
         synchronized(this) {
            this.statementContext = null;
         }

         GenericStatementContext.getTimerFactory().cancel(this);
      }
   }
}
