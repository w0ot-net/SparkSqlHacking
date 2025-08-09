package org.apache.derby.impl.sql;

import java.sql.SQLWarning;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.derby.catalog.DependableFinder;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.cache.Cacheable;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.loader.GeneratedClass;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ParameterValueSet;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.iapi.sql.Statement;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.conn.StatementContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.depend.Provider;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SPSDescriptor;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.sql.execute.ExecCursorTableReference;
import org.apache.derby.iapi.sql.execute.ExecPreparedStatement;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataTypeUtilities;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.impl.sql.compile.StatementNode;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.stream.HeaderPrintWriter;
import org.apache.derby.shared.common.util.ArrayUtil;

public class GenericPreparedStatement implements ExecPreparedStatement {
   public Statement statement;
   protected GeneratedClass activationClass;
   protected ResultDescription resultDesc;
   protected DataTypeDescriptor[] paramTypeDescriptors;
   private String spsName;
   private SQLWarning warnings;
   private boolean referencesSessionSchema;
   protected ExecCursorTableReference targetTable;
   protected List updateColumns;
   protected int updateMode;
   protected ConstantAction executionConstants;
   protected Object[] savedObjects;
   protected List requiredPermissionsList;
   protected String UUIDString;
   protected UUID UUIDValue;
   private boolean needsSavepoint;
   private String execStmtName;
   private String execSchemaName;
   protected boolean isAtomic;
   protected String sourceTxt;
   private int inUseCount;
   private boolean compilingStatement;
   boolean invalidatedWhileCompiling;
   protected long parseTime;
   protected long bindTime;
   protected long optimizeTime;
   protected long generateTime;
   protected long compileTime;
   protected Timestamp beginCompileTimestamp;
   protected Timestamp endCompileTimestamp;
   protected boolean isValid;
   protected boolean spsAction;
   private Cacheable cacheHolder;
   private long versionCounter;
   private RowCountStatistics rowCountStats;

   GenericPreparedStatement() {
      this.rowCountStats = new RowCountStatistics();
      UUIDFactory var1 = getMonitor().getUUIDFactory();
      this.UUIDValue = var1.createUUID();
      this.UUIDString = this.UUIDValue.toString();
      this.spsAction = false;
   }

   public GenericPreparedStatement(Statement var1) {
      this();
      this.statement = var1;
   }

   public synchronized boolean upToDate() throws StandardException {
      return this.isUpToDate();
   }

   public synchronized boolean upToDate(GeneratedClass var1) {
      return this.activationClass == var1 && this.isUpToDate();
   }

   private boolean isUpToDate() {
      return this.isValid && this.activationClass != null && !this.compilingStatement;
   }

   final synchronized boolean isCompiling() {
      return this.compilingStatement;
   }

   final synchronized void beginCompiling() {
      this.compilingStatement = true;
      this.setActivationClass((GeneratedClass)null);
   }

   final synchronized void endCompiling() {
      this.compilingStatement = false;
      this.notifyAll();
   }

   public void rePrepare(LanguageConnectionContext var1) throws StandardException {
      this.rePrepare(var1, false);
   }

   public void rePrepare(LanguageConnectionContext var1, boolean var2) throws StandardException {
      if (!this.upToDate()) {
         this.statement.prepare(var1, var2);
      }

   }

   public Activation getActivation(LanguageConnectionContext var1, boolean var2) throws StandardException {
      GenericActivationHolder var3;
      synchronized(this) {
         GeneratedClass var5 = this.getActivationClass();
         if (var5 == null) {
            this.rePrepare(var1);
            var5 = this.getActivationClass();
         }

         var3 = new GenericActivationHolder(var1, var5, this, var2);
         ++this.inUseCount;
      }

      var1.closeUnusedActivations();
      Activation var4 = null;
      StatementContext var8 = var1.getStatementContext();
      if (var8 != null) {
         var4 = var8.getActivation();
      }

      var3.setParentActivation(var4);
      return var3;
   }

   public ResultSet executeSubStatement(LanguageConnectionContext var1, boolean var2, long var3) throws StandardException {
      Activation var5 = var1.getLastActivation();
      Activation var6 = this.getActivation(var1, false);
      var6.setSingleExecution();
      var1.setupSubStatementSessionContext(var5);
      return this.executeStmt(var6, var2, false, var3);
   }

   public ResultSet executeSubStatement(Activation var1, Activation var2, boolean var3, long var4) throws StandardException {
      var1.getLanguageConnectionContext().setupSubStatementSessionContext(var1);
      return this.executeStmt(var2, var3, false, var4);
   }

   public ResultSet execute(Activation var1, boolean var2, long var3) throws StandardException {
      return this.executeStmt(var1, false, var2, var3);
   }

   private ResultSet executeStmt(Activation var1, boolean var2, boolean var3, long var4) throws StandardException {
      boolean var6 = false;
      if (var1 != null && var1.getPreparedStatement() == this) {
         LanguageConnectionContext var7;
         StatementContext var14;
         ResultSet var15;
         while(true) {
            var7 = var1.getLanguageConnectionContext();
            if (var7.getLogStatementText()) {
               HeaderPrintWriter var8 = Monitor.getStream();
               String var9 = var7.getTransactionExecute().getActiveStateTxIdString();
               String var10 = "";
               ParameterValueSet var11 = var1.getParameterValueSet();
               if (var11 != null && var11.getParameterCount() > 0) {
                  int var10000 = var11.getParameterCount();
                  var10 = " with " + var10000 + " parameters " + var11.toString();
               }

               var8.printlnWithHeader("(XID = " + var9 + "), (SESSIONID = " + var7.getInstanceNumber() + "), (DATABASE = " + var7.getDbname() + "), (DRDAID = " + var7.getDrdaID() + "), Executing prepared statement: " + this.getSource() + " :End prepared statement" + var10);
            }

            ParameterValueSet var13 = var1.getParameterValueSet();
            if (!this.spsAction) {
               this.rePrepare(var7, var3);
            }

            var14 = var7.pushStatementContext(this.isAtomic, this.updateMode == 1, this.getSource(), var13, var2, var4);
            var14.setActivation(var1);
            if (this.needsSavepoint()) {
               var14.setSavePoint();
               var6 = true;
            }

            if (this.executionConstants != null) {
               var7.validateStmtExecution(this.executionConstants);
            }

            try {
               var15 = var1.execute();
               var15.open();
               break;
            } catch (StandardException var12) {
               if (!var12.getMessageId().equals("XCL32.S") || this.spsAction) {
                  throw var12;
               }

               var14.cleanupOnError(var12);
            }
         }

         if (var6) {
            var14.clearSavePoint();
         }

         var7.popStatementContext(var14, (Throwable)null);
         if (var1.getSQLSessionContextForChildren() != null) {
            var7.popNestedSessionContext(var1);
         }

         if (var1.isSingleExecution() && var15.isClosed()) {
            var1.close();
         }

         return var15;
      } else {
         throw StandardException.newException("XCL09.S", new Object[]{"execute"});
      }
   }

   public ResultDescription getResultDescription() {
      return this.resultDesc;
   }

   public DataTypeDescriptor[] getParameterTypes() {
      return (DataTypeDescriptor[])ArrayUtil.copy(this.paramTypeDescriptors);
   }

   public DataTypeDescriptor getParameterType(int var1) throws StandardException {
      if (this.paramTypeDescriptors == null) {
         throw StandardException.newException("07009", new Object[0]);
      } else if (var1 >= 0 && var1 < this.paramTypeDescriptors.length) {
         return this.paramTypeDescriptors[var1];
      } else {
         throw StandardException.newException("XCL13.S", new Object[]{var1 + 1, this.paramTypeDescriptors.length});
      }
   }

   public String getSource() {
      return this.sourceTxt != null ? this.sourceTxt : (this.statement == null ? "null" : this.statement.getSource());
   }

   public void setSource(String var1) {
      this.sourceTxt = var1;
   }

   public final void setSPSName(String var1) {
      this.spsName = var1;
   }

   public String getSPSName() {
      return this.spsName;
   }

   public long getCompileTimeInMillis() {
      return this.compileTime;
   }

   public long getParseTimeInMillis() {
      return this.parseTime;
   }

   public long getBindTimeInMillis() {
      return this.bindTime;
   }

   public long getOptimizeTimeInMillis() {
      return this.optimizeTime;
   }

   public long getGenerateTimeInMillis() {
      return this.generateTime;
   }

   public Timestamp getBeginCompileTimestamp() {
      return DataTypeUtilities.clone(this.beginCompileTimestamp);
   }

   public Timestamp getEndCompileTimestamp() {
      return DataTypeUtilities.clone(this.endCompileTimestamp);
   }

   void setCompileTimeWarnings(SQLWarning var1) {
      this.warnings = var1;
   }

   public final SQLWarning getCompileTimeWarnings() {
      return this.warnings;
   }

   protected void setCompileTimeMillis(long var1, long var3, long var5, long var7, long var9, Timestamp var11, Timestamp var12) {
      this.parseTime = var1;
      this.bindTime = var3;
      this.optimizeTime = var5;
      this.generateTime = var7;
      this.compileTime = var9;
      this.beginCompileTimestamp = var11;
      this.endCompileTimestamp = var12;
   }

   public void finish(LanguageConnectionContext var1) {
      synchronized(this) {
         --this.inUseCount;
         if (this.cacheHolder != null) {
            return;
         }

         if (this.inUseCount != 0) {
            return;
         }
      }

      try {
         this.makeInvalid(11, var1);
      } catch (StandardException var4) {
      }

   }

   final void setConstantAction(ConstantAction var1) {
      this.executionConstants = var1;
   }

   public final ConstantAction getConstantAction() {
      return this.executionConstants;
   }

   final void setSavedObjects(Object[] var1) {
      this.savedObjects = var1;
   }

   public final Object getSavedObject(int var1) {
      return this.savedObjects[var1];
   }

   public final List getSavedObjects() {
      return ArrayUtil.asReadOnlyList(this.savedObjects);
   }

   public boolean isValid() {
      return this.isValid;
   }

   public void setValid() {
      this.isValid = true;
   }

   public void setSPSAction() {
      this.spsAction = true;
   }

   public void prepareToInvalidate(Provider var1, int var2, LanguageConnectionContext var3) throws StandardException {
      switch (var2) {
         case 3:
         case 5:
         case 48:
            return;
         default:
            var3.verifyNoOpenResultSets(this, var1, var2);
      }
   }

   public void makeInvalid(int var1, LanguageConnectionContext var2) throws StandardException {
      switch (var1) {
         case 48:
            return;
         default:
            synchronized(this) {
               if (this.compilingStatement) {
                  this.invalidatedWhileCompiling = true;
                  return;
               }

               boolean var3 = !this.isValid;
               this.isValid = false;
               this.beginCompiling();
            }

            try {
               DependencyManager var4 = var2.getDataDictionary().getDependencyManager();
               var4.clearDependencies(var2, this);
               if (this.execStmtName != null) {
                  switch (var1) {
                     case 5:
                     case 23:
                        DataDictionary var5 = var2.getDataDictionary();
                        SchemaDescriptor var6 = var5.getSchemaDescriptor(this.execSchemaName, var2.getTransactionCompile(), true);
                        SPSDescriptor var7 = var5.getSPSDescriptor(this.execStmtName, var6);
                        var7.makeInvalid(var1, var2);
                  }
               }
            } finally {
               this.endCompiling();
            }

      }
   }

   public boolean isPersistent() {
      return false;
   }

   public DependableFinder getDependableFinder() {
      return null;
   }

   public String getObjectName() {
      return this.UUIDString;
   }

   public UUID getObjectID() {
      return this.UUIDValue;
   }

   public String getClassType() {
      return "PreparedStatement";
   }

   public boolean referencesSessionSchema() {
      return this.referencesSessionSchema;
   }

   public boolean referencesSessionSchema(StatementNode var1) throws StandardException {
      this.referencesSessionSchema = var1.referencesSessionSchema();
      return this.referencesSessionSchema;
   }

   void completeCompile(StatementNode var1) throws StandardException {
      this.paramTypeDescriptors = var1.getParameterTypes();
      if (this.targetTable != null) {
         this.targetTable = null;
         this.updateMode = 0;
         this.updateColumns = null;
      }

      this.resultDesc = var1.makeResultDescription();
      if (this.resultDesc != null) {
         this.setCursorInfo((CursorInfo)var1.getCursorInfo());
      }

      this.isValid = true;
      this.rowCountStats.reset();
   }

   public GeneratedClass getActivationClass() throws StandardException {
      return this.activationClass;
   }

   void setActivationClass(GeneratedClass var1) {
      this.activationClass = var1;
   }

   public int getUpdateMode() {
      return this.updateMode;
   }

   public ExecCursorTableReference getTargetTable() {
      return this.targetTable;
   }

   public boolean hasUpdateColumns() {
      return this.updateColumns != null && !this.updateColumns.isEmpty();
   }

   public boolean isUpdateColumn(String var1) {
      return this.updateColumns != null && this.updateColumns.contains(var1);
   }

   public Object getCursorInfo() {
      return new CursorInfo(this.updateMode, this.targetTable, this.updateColumns);
   }

   void setCursorInfo(CursorInfo var1) {
      if (var1 != null) {
         this.updateMode = var1.updateMode;
         this.targetTable = var1.targetTable;
         this.updateColumns = var1.updateColumns;
      }

   }

   ByteArray getByteCodeSaver() {
      return null;
   }

   public boolean needsSavepoint() {
      return this.needsSavepoint;
   }

   void setNeedsSavepoint(boolean var1) {
      this.needsSavepoint = var1;
   }

   void setIsAtomic(boolean var1) {
      this.isAtomic = var1;
   }

   public boolean isAtomic() {
      return this.isAtomic;
   }

   void setExecuteStatementNameAndSchema(String var1, String var2) {
      this.execStmtName = var1;
      this.execSchemaName = var2;
   }

   public ExecPreparedStatement getClone() throws StandardException {
      GenericPreparedStatement var1 = new GenericPreparedStatement(this.statement);
      var1.activationClass = this.getActivationClass();
      var1.resultDesc = this.resultDesc;
      var1.paramTypeDescriptors = this.paramTypeDescriptors;
      var1.executionConstants = this.executionConstants;
      var1.UUIDString = this.UUIDString;
      var1.UUIDValue = this.UUIDValue;
      var1.savedObjects = this.savedObjects;
      var1.execStmtName = this.execStmtName;
      var1.execSchemaName = this.execSchemaName;
      var1.isAtomic = this.isAtomic;
      var1.sourceTxt = this.sourceTxt;
      var1.targetTable = this.targetTable;
      var1.updateColumns = this.updateColumns;
      var1.updateMode = this.updateMode;
      var1.needsSavepoint = this.needsSavepoint;
      var1.rowCountStats = this.rowCountStats;
      return var1;
   }

   public void setCacheHolder(Cacheable var1) {
      this.cacheHolder = var1;
      if (var1 == null) {
         if (!this.isValid || this.inUseCount != 0) {
            return;
         }

         ContextManager var2 = getContextService().getCurrentContextManager();
         LanguageConnectionContext var3 = (LanguageConnectionContext)var2.getContext("LanguageConnectionContext");

         try {
            this.makeInvalid(11, var3);
         } catch (StandardException var5) {
         }
      }

   }

   public String toString() {
      return this.getObjectName();
   }

   public boolean isStorable() {
      return false;
   }

   public void setRequiredPermissionsList(List var1) {
      this.requiredPermissionsList = var1;
   }

   public List getRequiredPermissionsList() {
      return this.requiredPermissionsList;
   }

   public final long getVersionCounter() {
      return this.versionCounter;
   }

   public final void incrementVersionCounter() {
      ++this.versionCounter;
   }

   public int incrementExecutionCount() {
      return this.rowCountStats.incrementExecutionCount();
   }

   public void setStalePlanCheckInterval(int var1) {
      this.rowCountStats.setStalePlanCheckInterval(var1);
   }

   public int getStalePlanCheckInterval() {
      return this.rowCountStats.getStalePlanCheckInterval();
   }

   public long getInitialRowCount(int var1, long var2) {
      return this.rowCountStats.getInitialRowCount(var1, var2);
   }

   private static ContextService getContextService() {
      return ContextService.getFactory();
   }

   private static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }

   private static class RowCountStatistics {
      private int stalePlanCheckInterval;
      private int executionCount;
      private ArrayList rowCounts;

      int incrementExecutionCount() {
         return ++this.executionCount;
      }

      synchronized long getInitialRowCount(int var1, long var2) {
         if (this.rowCounts == null) {
            this.rowCounts = new ArrayList();
         }

         if (var1 >= this.rowCounts.size()) {
            int var4 = var1 + 1;
            this.rowCounts.addAll(Collections.nCopies(var4 - this.rowCounts.size(), (Long)null));
         }

         Long var5 = (Long)this.rowCounts.get(var1);
         if (var5 == null) {
            this.rowCounts.set(var1, var2);
            return var2;
         } else {
            return var5;
         }
      }

      void setStalePlanCheckInterval(int var1) {
         this.stalePlanCheckInterval = var1;
      }

      int getStalePlanCheckInterval() {
         return this.stalePlanCheckInterval;
      }

      synchronized void reset() {
         this.stalePlanCheckInterval = 0;
         this.executionCount = 0;
         this.rowCounts = null;
      }
   }
}
