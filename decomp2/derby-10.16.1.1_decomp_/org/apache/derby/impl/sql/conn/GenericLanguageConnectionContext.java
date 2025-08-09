package org.apache.derby.impl.sql.conn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.WeakHashMap;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.db.Database;
import org.apache.derby.iapi.db.TriggerExecutionContext;
import org.apache.derby.iapi.services.cache.CacheManager;
import org.apache.derby.iapi.services.cache.Cacheable;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextImpl;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.loader.GeneratedClass;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.LanguageFactory;
import org.apache.derby.iapi.sql.ParameterValueSet;
import org.apache.derby.iapi.sql.PreparedStatement;
import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.iapi.sql.compile.ASTVisitor;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.OptTrace;
import org.apache.derby.iapi.sql.compile.OptimizerFactory;
import org.apache.derby.iapi.sql.compile.TypeCompilerFactory;
import org.apache.derby.iapi.sql.conn.Authorizer;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.conn.LanguageConnectionFactory;
import org.apache.derby.iapi.sql.conn.SQLSessionContext;
import org.apache.derby.iapi.sql.conn.StatementContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.depend.Dependent;
import org.apache.derby.iapi.sql.depend.Provider;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptorList;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.IndexRowGenerator;
import org.apache.derby.iapi.sql.dictionary.RoleGrantDescriptor;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.sql.execute.CursorActivation;
import org.apache.derby.iapi.sql.execute.ExecPreparedStatement;
import org.apache.derby.iapi.sql.execute.ExecutionStmtValidator;
import org.apache.derby.iapi.sql.execute.RunTimeStatistics;
import org.apache.derby.iapi.store.access.ColumnOrdering;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.store.access.XATransactionController;
import org.apache.derby.iapi.transaction.TransactionControl;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.iapi.util.InterruptStatus;
import org.apache.derby.impl.sql.GenericPreparedStatement;
import org.apache.derby.impl.sql.GenericStatement;
import org.apache.derby.impl.sql.compile.CompilerContextImpl;
import org.apache.derby.impl.sql.execute.AutoincrementCounter;
import org.apache.derby.impl.sql.execute.DeferredConstraintsMemory;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.stream.HeaderPrintWriter;

public class GenericLanguageConnectionContext extends ContextImpl implements LanguageConnectionContext {
   private static final int NON_XA = 0;
   private static final int XA_ONE_PHASE = 1;
   private static final int XA_TWO_PHASE = 2;
   private final ArrayList acts;
   private volatile boolean unusedActs = false;
   private int maxActsSize;
   protected int bindCount;
   private boolean ddWriteMode;
   private boolean runTimeStatisticsSetting;
   private boolean statisticsTiming;
   private boolean xplainOnlyMode = false;
   private String xplain_schema = null;
   private Map xplain_statements = new HashMap();
   private ArrayList allDeclaredGlobalTempTables;
   private int currentSavepointLevel = 0;
   protected long nextCursorId;
   protected int nextSavepointId;
   private RunTimeStatistics runTimeStatisticsObject;
   private StringBuffer sb;
   private Database db;
   private final int instanceNumber;
   private String drdaID;
   private String dbname;
   private Object lastQueryTree;
   private final TransactionController tran;
   private TransactionController readOnlyNestedTransaction;
   private int queryNestingDepth;
   protected DataValueFactory dataFactory;
   protected LanguageFactory langFactory;
   protected TypeCompilerFactory tcf;
   protected OptimizerFactory of;
   protected LanguageConnectionFactory connFactory;
   private final StatementContext[] statementContexts = new StatementContext[2];
   private int statementDepth;
   protected int outermostTrigger = -1;
   protected Authorizer authorizer;
   protected String userName = null;
   private SQLSessionContext topLevelSSC;
   private SchemaDescriptor cachedInitialDefaultSchemaDescr = null;
   private int defaultIsolationLevel = 2;
   protected int isolationLevel;
   private boolean isolationLevelExplicitlySet;
   private boolean isolationLevelSetUsingSQLorJDBC;
   protected int prepareIsolationLevel;
   private boolean logStatementText;
   private boolean logQueryPlan;
   private HeaderPrintWriter istream;
   private int lockEscalationThreshold;
   private ArrayList stmtValidators;
   private ArrayList triggerExecutionContexts;
   private ArrayList triggerTables;
   private OptTrace optimizerTracer;
   private HashMap autoincrementHT;
   private boolean autoincrementUpdate;
   private long identityVal;
   private boolean identityNotNull;
   private HashMap autoincrementCacheHashtable;
   private ASTVisitor astWalker;
   private StandardException interruptedException;
   private WeakHashMap referencedColumnMap;
   private HashMap deferredHashTables;
   private String sessionUser;
   Map printedObjectsMap;

   public GenericLanguageConnectionContext(ContextManager var1, TransactionController var2, LanguageFactory var3, LanguageConnectionFactory var4, Database var5, String var6, int var7, String var8, String var9) throws StandardException {
      super(var1, "LanguageConnectionContext");
      this.isolationLevel = this.defaultIsolationLevel;
      this.isolationLevelExplicitlySet = false;
      this.isolationLevelSetUsingSQLorJDBC = false;
      this.prepareIsolationLevel = 0;
      this.sessionUser = null;
      this.printedObjectsMap = null;
      this.acts = new ArrayList();
      this.tran = var2;
      this.dataFactory = var4.getDataValueFactory();
      this.tcf = var4.getTypeCompilerFactory();
      this.of = var4.getOptimizerFactory();
      this.langFactory = var3;
      this.connFactory = var4;
      this.db = var5;
      this.userName = var6;
      this.instanceNumber = var7;
      this.drdaID = var8;
      this.dbname = var9;
      String var10 = PropertyUtil.getServiceProperty(this.getTransactionCompile(), "derby.language.logStatementText");
      this.logStatementText = Boolean.valueOf(var10);
      String var11 = PropertyUtil.getServiceProperty(this.getTransactionCompile(), "derby.language.logQueryPlan");
      this.logQueryPlan = Boolean.valueOf(var11);
      this.setRunTimeStatisticsMode(this.logQueryPlan);
      this.lockEscalationThreshold = PropertyUtil.getServiceInt(var2, "derby.locks.escalationThreshold", 100, Integer.MAX_VALUE, 5000);
      this.stmtValidators = new ArrayList();
      this.triggerExecutionContexts = new ArrayList();
      this.triggerTables = new ArrayList();
   }

   public void initialize() throws StandardException {
      this.interruptedException = null;
      this.sessionUser = IdUtil.getUserAuthorizationId(this.userName);
      this.authorizer = new GenericAuthorizer(this);
      this.setDefaultSchema(this.initDefaultSchemaDescriptor());
      this.referencedColumnMap = new WeakHashMap();
   }

   protected SchemaDescriptor initDefaultSchemaDescriptor() throws StandardException {
      if (this.cachedInitialDefaultSchemaDescr == null) {
         DataDictionary var1 = this.getDataDictionary();
         SchemaDescriptor var2 = var1.getSchemaDescriptor(this.getSessionUserId(), this.getTransactionCompile(), false);
         if (var2 == null) {
            var2 = new SchemaDescriptor(var1, this.getSessionUserId(), this.getSessionUserId(), (UUID)null, false);
         }

         this.cachedInitialDefaultSchemaDescr = var2;
      }

      return this.cachedInitialDefaultSchemaDescr;
   }

   private SchemaDescriptor getInitialDefaultSchemaDescriptor() {
      return this.cachedInitialDefaultSchemaDescr;
   }

   public boolean getLogStatementText() {
      return this.logStatementText;
   }

   public void setLogStatementText(boolean var1) {
      this.logStatementText = var1;
   }

   public boolean getLogQueryPlan() {
      return this.logQueryPlan;
   }

   public boolean usesSqlAuthorization() {
      return this.getDataDictionary().usesSqlAuthorization();
   }

   public int getLockEscalationThreshold() {
      return this.lockEscalationThreshold;
   }

   public void addActivation(Activation var1) throws StandardException {
      this.acts.add(var1);
      if (this.acts.size() > this.maxActsSize) {
         this.maxActsSize = this.acts.size();
      }

   }

   public void closeUnusedActivations() throws StandardException {
      if (this.unusedActs && this.acts.size() > 20) {
         this.unusedActs = false;

         for(int var1 = this.acts.size() - 1; var1 >= 0; --var1) {
            if (var1 < this.acts.size()) {
               Activation var2 = (Activation)this.acts.get(var1);
               if (!var2.isInUse()) {
                  var2.close();
               }
            }
         }
      }

   }

   public void notifyUnusedActivation() {
      this.unusedActs = true;
   }

   public boolean checkIfAnyDeclaredGlobalTempTablesForThisConnection() {
      return this.allDeclaredGlobalTempTables != null;
   }

   public void addDeclaredGlobalTempTable(TableDescriptor var1) throws StandardException {
      if (this.findDeclaredGlobalTempTable(var1.getName()) != null) {
         throw StandardException.newException("X0Y32.S", new Object[]{"Declared global temporary table", var1.getName(), "Schema", "SESSION"});
      } else {
         TempTableInfo var2 = new TempTableInfo(var1, this.currentSavepointLevel);
         if (this.allDeclaredGlobalTempTables == null) {
            this.allDeclaredGlobalTempTables = new ArrayList();
         }

         this.allDeclaredGlobalTempTables.add(var2);
      }
   }

   public boolean dropDeclaredGlobalTempTable(String var1) {
      TempTableInfo var2 = this.findDeclaredGlobalTempTable(var1);
      if (var2 != null) {
         if (var2.getDeclaredInSavepointLevel() == this.currentSavepointLevel) {
            this.allDeclaredGlobalTempTables.remove(this.allDeclaredGlobalTempTables.indexOf(var2));
            if (this.allDeclaredGlobalTempTables.isEmpty()) {
               this.allDeclaredGlobalTempTables = null;
            }
         } else {
            var2.setDroppedInSavepointLevel(this.currentSavepointLevel);
         }

         return true;
      } else {
         return false;
      }
   }

   private void tempTablesReleaseSavepointLevels() {
      for(int var1 = 0; var1 < this.allDeclaredGlobalTempTables.size(); ++var1) {
         TempTableInfo var2 = (TempTableInfo)this.allDeclaredGlobalTempTables.get(var1);
         if (var2.getDroppedInSavepointLevel() > this.currentSavepointLevel) {
            var2.setDroppedInSavepointLevel(this.currentSavepointLevel);
         }

         if (var2.getDeclaredInSavepointLevel() > this.currentSavepointLevel) {
            var2.setDeclaredInSavepointLevel(this.currentSavepointLevel);
         }

         if (var2.getModifiedInSavepointLevel() > this.currentSavepointLevel) {
            var2.setModifiedInSavepointLevel(this.currentSavepointLevel);
         }
      }

   }

   private void tempTablesAndCommit(boolean var1) throws StandardException {
      for(int var2 = this.allDeclaredGlobalTempTables.size() - 1; var2 >= 0; --var2) {
         TempTableInfo var3 = (TempTableInfo)this.allDeclaredGlobalTempTables.get(var2);
         if (var3.getDroppedInSavepointLevel() != -1) {
            this.allDeclaredGlobalTempTables.remove(var2);
         } else {
            var3.setDeclaredInSavepointLevel(-1);
            var3.setModifiedInSavepointLevel(-1);
         }
      }

      for(int var4 = 0; var4 < this.allDeclaredGlobalTempTables.size(); ++var4) {
         TableDescriptor var5 = ((TempTableInfo)this.allDeclaredGlobalTempTables.get(var4)).getTableDescriptor();
         if (var5.isOnCommitDeleteRows() && !this.checkIfAnyActivationHasHoldCursor(var5.getName())) {
            this.getDataDictionary().getDependencyManager().invalidateFor(var5, 1, this);
            if (!var1) {
               this.cleanupTempTableOnCommitOrRollback(var5, true);
            }
         }
      }

   }

   private void tempTablesXApostCommit() throws StandardException {
      TransactionController var1 = this.getTransactionExecute();

      for(int var2 = 0; var2 < this.allDeclaredGlobalTempTables.size(); ++var2) {
         TableDescriptor var3 = ((TempTableInfo)this.allDeclaredGlobalTempTables.get(var2)).getTableDescriptor();
         var1.dropConglomerate(var3.getHeapConglomerateId());
         this.allDeclaredGlobalTempTables.remove(var2);
      }

      var1.commit();
   }

   public void resetFromPool() throws StandardException {
      this.interruptedException = null;
      this.identityNotNull = false;
      this.dropAllDeclaredGlobalTempTables();
      this.setDefaultSchema((SchemaDescriptor)null);
      this.getCurrentSQLSessionContext().setRole((String)null);
      this.getCurrentSQLSessionContext().setUser(this.getSessionUserId());
      this.referencedColumnMap = new WeakHashMap();
   }

   public void setLastQueryTree(Object var1) {
      this.lastQueryTree = var1;
   }

   public Object getLastQueryTree() {
      return this.lastQueryTree;
   }

   private void dropAllDeclaredGlobalTempTables() throws StandardException {
      if (this.allDeclaredGlobalTempTables != null) {
         DependencyManager var1 = this.getDataDictionary().getDependencyManager();
         StandardException var2 = null;

         for(int var3 = 0; var3 < this.allDeclaredGlobalTempTables.size(); ++var3) {
            try {
               TempTableInfo var11 = (TempTableInfo)this.allDeclaredGlobalTempTables.get(var3);
               TableDescriptor var5 = var11.getTableDescriptor();
               var1.invalidateFor(var5, 1, this);
               this.tran.dropConglomerate(var5.getHeapConglomerateId());
            } catch (StandardException var9) {
               StandardException var4 = var9;
               if (var2 == null) {
                  var2 = var9;
               } else {
                  try {
                     var4.initCause(var2);
                     var2 = var4;
                  } catch (IllegalStateException var7) {
                  }
               }
            }
         }

         this.allDeclaredGlobalTempTables = null;

         try {
            this.internalCommit(true);
         } catch (StandardException var8) {
            StandardException var10 = var8;
            if (var2 == null) {
               var2 = var8;
            } else {
               try {
                  var10.initCause(var2);
                  var2 = var10;
               } catch (IllegalStateException var6) {
               }
            }
         }

         if (var2 != null) {
            throw var2;
         }
      }
   }

   private void tempTablesAndRollback() throws StandardException {
      for(int var1 = this.allDeclaredGlobalTempTables.size() - 1; var1 >= 0; --var1) {
         TempTableInfo var2 = (TempTableInfo)this.allDeclaredGlobalTempTables.get(var1);
         if (var2.getDeclaredInSavepointLevel() >= this.currentSavepointLevel) {
            if (var2.getDroppedInSavepointLevel() == -1) {
               TableDescriptor var3 = var2.getTableDescriptor();
               this.invalidateCleanupDroppedTable(var3);
               this.tran.dropConglomerate(var3.getHeapConglomerateId());
               this.allDeclaredGlobalTempTables.remove(var1);
            } else if (var2.getDroppedInSavepointLevel() >= this.currentSavepointLevel) {
               this.allDeclaredGlobalTempTables.remove(var1);
            }
         } else if (var2.getDroppedInSavepointLevel() >= this.currentSavepointLevel) {
            TableDescriptor var4 = var2.getTableDescriptor();
            var4 = this.cleanupTempTableOnCommitOrRollback(var4, false);
            var2.setTableDescriptor(var4);
            var2.setDroppedInSavepointLevel(-1);
            var2.setModifiedInSavepointLevel(-1);
            this.allDeclaredGlobalTempTables.set(var1, var2);
         } else if (var2.getModifiedInSavepointLevel() >= this.currentSavepointLevel) {
            var2.setModifiedInSavepointLevel(-1);
            TableDescriptor var6 = var2.getTableDescriptor();
            this.invalidateCleanupDroppedTable(var6);
         }
      }

      if (this.allDeclaredGlobalTempTables.isEmpty()) {
         this.allDeclaredGlobalTempTables = null;
      }

   }

   private void invalidateCleanupDroppedTable(TableDescriptor var1) throws StandardException {
      this.getDataDictionary().getDependencyManager().invalidateFor(var1, 1, this);
      this.cleanupTempTableOnCommitOrRollback(var1, true);
   }

   private void replaceDeclaredGlobalTempTable(String var1, TableDescriptor var2) {
      TempTableInfo var3 = this.findDeclaredGlobalTempTable(var1);
      var3.setDroppedInSavepointLevel(-1);
      var3.setDeclaredInSavepointLevel(-1);
      var3.setTableDescriptor(var2);
      this.allDeclaredGlobalTempTables.set(this.allDeclaredGlobalTempTables.indexOf(var3), var3);
   }

   public TableDescriptor getTableDescriptorForDeclaredGlobalTempTable(String var1) {
      TempTableInfo var2 = this.findDeclaredGlobalTempTable(var1);
      return var2 == null ? null : var2.getTableDescriptor();
   }

   private TempTableInfo findDeclaredGlobalTempTable(String var1) {
      if (this.allDeclaredGlobalTempTables == null) {
         return null;
      } else {
         for(int var2 = 0; var2 < this.allDeclaredGlobalTempTables.size(); ++var2) {
            if (((TempTableInfo)this.allDeclaredGlobalTempTables.get(var2)).matches(var1)) {
               return (TempTableInfo)this.allDeclaredGlobalTempTables.get(var2);
            }
         }

         return null;
      }
   }

   public void markTempTableAsModifiedInUnitOfWork(String var1) {
      TempTableInfo var2 = this.findDeclaredGlobalTempTable(var1);
      var2.setModifiedInSavepointLevel(this.currentSavepointLevel);
   }

   public PreparedStatement prepareInternalStatement(SchemaDescriptor var1, String var2, boolean var3, boolean var4) throws StandardException {
      if (var4) {
         var1 = this.getDataDictionary().getSystemSchemaDescriptor();
      }

      return this.connFactory.getStatement(var1, var2, var3).prepare(this, var4);
   }

   public PreparedStatement prepareInternalStatement(String var1) throws StandardException {
      return this.connFactory.getStatement(this.getDefaultSchema(), var1, true).prepare(this);
   }

   public void removeActivation(Activation var1) {
      this.acts.remove(var1);
      if (this.maxActsSize > 20 && this.maxActsSize > 2 * this.acts.size()) {
         this.acts.trimToSize();
         this.maxActsSize = this.acts.size();
      }

   }

   public int getActivationCount() {
      return this.acts.size();
   }

   public CursorActivation lookupCursorActivation(String var1) {
      int var2 = this.acts.size();
      if (var2 > 0) {
         int var3 = var1.hashCode();

         for(int var4 = 0; var4 < var2; ++var4) {
            Activation var5 = (Activation)this.acts.get(var4);
            if (var5.isInUse()) {
               String var6 = var5.getCursorName();
               if (var6 != null && var6.hashCode() == var3 && var1.equals(var6)) {
                  ResultSet var7 = var5.getResultSet();
                  if (var7 != null && !var7.isClosed()) {
                     return (CursorActivation)var5;
                  }
               }
            }
         }
      }

      return null;
   }

   public void removeStatement(GenericStatement var1) throws StandardException {
      CacheManager var2 = this.getLanguageConnectionFactory().getStatementCache();
      if (var2 != null) {
         Cacheable var3 = var2.findCached(var1);
         if (var3 != null) {
            CachedStatement var4 = (CachedStatement)var3;
            if (var1.getPreparedStatement() != var4.getPreparedStatement()) {
               var2.release(var3);
            } else {
               var2.remove(var3);
            }
         }

      }
   }

   public PreparedStatement lookupStatement(GenericStatement var1) throws StandardException {
      CacheManager var2 = this.getLanguageConnectionFactory().getStatementCache();
      if (var2 == null) {
         return null;
      } else if (this.dataDictionaryInWriteMode()) {
         return null;
      } else {
         Cacheable var3 = var2.find(var1);
         CachedStatement var4 = (CachedStatement)var3;
         GenericPreparedStatement var5 = var4.getPreparedStatement();
         synchronized(var5) {
            if (var5.upToDate()) {
               GeneratedClass var7 = var5.getActivationClass();
               int var8 = this.getLanguageConnectionFactory().getClassFactory().getClassLoaderVersion();
               if (var7.getClassLoaderVersion() != var8) {
                  var5.makeInvalid(23, this);
               }
            }
         }

         var2.release(var3);
         return var5;
      }
   }

   public String getUniqueCursorName() {
      return this.getNameString("SQLCUR", (long)(this.nextCursorId++));
   }

   public String getUniqueSavepointName() {
      return this.getNameString("SAVEPT", (long)(this.nextSavepointId++));
   }

   public int getUniqueSavepointID() {
      return this.nextSavepointId - 1;
   }

   private String getNameString(String var1, long var2) {
      if (this.sb != null) {
         this.sb.setLength(0);
      } else {
         this.sb = new StringBuffer();
      }

      this.sb.append(var1).append(var2);
      return this.sb.toString();
   }

   public void internalCommit(boolean var1) throws StandardException {
      this.doCommit(var1, true, 0, false);
   }

   public void userCommit() throws StandardException {
      this.doCommit(true, true, 0, true);
   }

   public final void internalCommitNoSync(int var1) throws StandardException {
      this.doCommit(true, false, var1, false);
   }

   public final void xaCommit(boolean var1) throws StandardException {
      this.doCommit(true, true, var1 ? 1 : 2, true);
   }

   protected void doCommit(boolean var1, boolean var2, int var3, boolean var4) throws StandardException {
      StatementContext var5 = this.getStatementContext();
      if (var4 && var5 != null && var5.inUse() && var5.isAtomic()) {
         throw StandardException.newException("X0Y66.S", new Object[0]);
      } else {
         this.checkIntegrity();
         if (this.logStatementText) {
            if (this.istream == null) {
               this.istream = Monitor.getStream();
            }

            String var6 = this.tran.getTransactionIdString();
            this.istream.printlnWithHeader("(XID = " + var6 + "), (SESSIONID = " + this.instanceNumber + "), (DATABASE = " + this.dbname + "), (DRDAID = " + this.drdaID + "), Committing");
         }

         this.endTransactionActivationHandling(false);
         if (this.allDeclaredGlobalTempTables != null) {
            this.tempTablesAndCommit(var3 != 0);
         }

         this.currentSavepointLevel = 0;
         if (var2) {
            this.finishDDTransaction();
         }

         TransactionController var7 = this.getTransactionExecute();
         if (var7 != null && var1) {
            if (var2) {
               if (var3 == 0) {
                  var7.commit();
               } else {
                  ((XATransactionController)var7).xa_commit(var3 == 1);
               }
            } else {
               var7.commitNoSync(var3);
            }

            this.resetSavepoints();
            if (this.allDeclaredGlobalTempTables != null && var3 != 0) {
               this.tempTablesXApostCommit();
            }
         }

      }
   }

   private TableDescriptor cleanupTempTableOnCommitOrRollback(TableDescriptor var1, boolean var2) throws StandardException {
      TransactionController var3 = this.getTransactionExecute();
      long var4 = var3.createConglomerate("heap", var1.getEmptyExecRow().getRowArray(), (ColumnOrdering[])null, var1.getColumnCollationIds(), (Properties)null, 3);
      long var6 = var1.getHeapConglomerateId();
      ConglomerateDescriptor var8 = var1.getConglomerateDescriptor(var6);
      var1.getConglomerateDescriptorList().dropConglomerateDescriptorByUUID(var8.getUUID());
      var8 = this.getDataDictionary().getDataDescriptorGenerator().newConglomerateDescriptor(var4, (String)null, false, (IndexRowGenerator)null, false, (UUID)null, var1.getUUID(), var1.getSchemaDescriptor().getUUID());
      ConglomerateDescriptorList var9 = var1.getConglomerateDescriptorList();
      var9.add(var8);
      var1.resetHeapConglomNumber();
      if (var2) {
         var3.dropConglomerate(var6);
         this.replaceDeclaredGlobalTempTable(var1.getName(), var1);
      }

      return var1;
   }

   public void internalRollback() throws StandardException {
      this.doRollback(false, false);
   }

   public void userRollback() throws StandardException {
      this.doRollback(false, true);
   }

   public void xaRollback() throws StandardException {
      this.doRollback(true, true);
   }

   private void doRollback(boolean var1, boolean var2) throws StandardException {
      StatementContext var3 = this.getStatementContext();
      if (var2 && var3 != null && var3.inUse() && var3.isAtomic()) {
         throw StandardException.newException("X0Y67.S", new Object[0]);
      } else {
         this.clearDeferreds();
         if (this.logStatementText) {
            if (this.istream == null) {
               this.istream = Monitor.getStream();
            }

            String var4 = this.tran.getTransactionIdString();
            this.istream.printlnWithHeader("(XID = " + var4 + "), (SESSIONID = " + this.instanceNumber + "), (DATABASE = " + this.dbname + "), (DRDAID = " + this.drdaID + "), Rolling back");
         }

         this.endTransactionActivationHandling(true);
         this.currentSavepointLevel = 0;
         if (this.allDeclaredGlobalTempTables != null) {
            this.tempTablesAndRollback();
         }

         this.finishDDTransaction();
         if (this.readOnlyNestedTransaction != null) {
            this.readOnlyNestedTransaction.destroy();
            this.readOnlyNestedTransaction = null;
            this.queryNestingDepth = 0;
         }

         TransactionController var5 = this.getTransactionExecute();
         if (var5 != null) {
            if (var1) {
               ((XATransactionController)var5).xa_rollback();
            } else {
               var5.abort();
            }

            this.resetSavepoints();
         }

      }
   }

   private void resetSavepoints() throws StandardException {
      ContextManager var1 = this.getContextManager();

      for(Context var4 : var1.getContextStack("StatementContext")) {
         ((StatementContext)var4).resetSavePoint();
      }

   }

   public void internalRollbackToSavepoint(String var1, boolean var2, Object var3) throws StandardException {
      TransactionController var4 = this.getTransactionExecute();
      if (var4 != null) {
         boolean var5;
         if (var2) {
            var5 = true;
            this.endTransactionActivationHandling(true);
         } else {
            var5 = false;
         }

         this.currentSavepointLevel = var4.rollbackToSavePoint(var1, var5, var3);
      }

      if (var4 != null && var2 && this.allDeclaredGlobalTempTables != null) {
         this.tempTablesAndRollback();
      }

   }

   public void releaseSavePoint(String var1, Object var2) throws StandardException {
      TransactionController var3 = this.getTransactionExecute();
      if (var3 != null) {
         this.currentSavepointLevel = var3.releaseSavePoint(var1, var2);
         if (this.allDeclaredGlobalTempTables != null) {
            this.tempTablesReleaseSavepointLevels();
         }
      }

   }

   public void languageSetSavePoint(String var1, Object var2) throws StandardException {
      TransactionController var3 = this.getTransactionExecute();
      if (var3 != null) {
         this.currentSavepointLevel = var3.setSavePoint(var1, var2);
      }

   }

   public void beginNestedTransaction(boolean var1) throws StandardException {
      if (this.readOnlyNestedTransaction == null) {
         this.readOnlyNestedTransaction = this.tran.startNestedUserTransaction(var1, true);
      }

      ++this.queryNestingDepth;
   }

   public void commitNestedTransaction() throws StandardException {
      if (--this.queryNestingDepth == 0) {
         this.readOnlyNestedTransaction.commit();
         this.readOnlyNestedTransaction.destroy();
         this.readOnlyNestedTransaction = null;
      }

   }

   public final TransactionController getTransactionCompile() {
      return this.readOnlyNestedTransaction != null ? this.readOnlyNestedTransaction : this.tran;
   }

   public TransactionController getTransactionExecute() {
      return this.tran;
   }

   public DataValueFactory getDataValueFactory() {
      return this.dataFactory;
   }

   public LanguageFactory getLanguageFactory() {
      return this.langFactory;
   }

   public OptimizerFactory getOptimizerFactory() {
      return this.of;
   }

   public LanguageConnectionFactory getLanguageConnectionFactory() {
      return this.connFactory;
   }

   private boolean checkIfAnyActivationHasHoldCursor(String var1) throws StandardException {
      for(int var2 = this.acts.size() - 1; var2 >= 0; --var2) {
         Activation var3 = (Activation)this.acts.get(var2);
         if (var3.checkIfThisActivationHasHoldCursor(var1)) {
            return true;
         }
      }

      return false;
   }

   public boolean verifyAllHeldResultSetsAreClosed() throws StandardException {
      boolean var1 = false;

      for(int var2 = this.acts.size() - 1; var2 >= 0; --var2) {
         Activation var3 = (Activation)this.acts.get(var2);
         if (var3.isInUse() && var3.getResultSetHoldability()) {
            ResultSet var4 = ((CursorActivation)var3).getResultSet();
            if (var4 != null && !var4.isClosed() && var4.returnsRows()) {
               var1 = true;
               break;
            }
         }
      }

      if (!var1) {
         return true;
      } else {
         System.gc();
         System.runFinalization();

         for(int var5 = this.acts.size() - 1; var5 >= 0; --var5) {
            Activation var6 = (Activation)this.acts.get(var5);
            if (var6.isInUse() && var6.getResultSetHoldability()) {
               ResultSet var7 = ((CursorActivation)var6).getResultSet();
               if (var7 != null && !var7.isClosed() && var7.returnsRows()) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   public boolean verifyNoOpenResultSets(PreparedStatement var1, Provider var2, int var3) throws StandardException {
      boolean var4 = false;

      for(int var5 = this.acts.size() - 1; var5 >= 0; --var5) {
         Activation var6 = (Activation)this.acts.get(var5);
         if (var6.isInUse() && var1 == var6.getPreparedStatement()) {
            ResultSet var7 = var6.getResultSet();
            if (var7 != null && !var7.isClosed() && var7.returnsRows()) {
               var4 = true;
               break;
            }
         }
      }

      if (!var4) {
         return false;
      } else {
         System.gc();
         System.runFinalization();

         for(int var9 = this.acts.size() - 1; var9 >= 0; --var9) {
            Activation var10 = (Activation)this.acts.get(var9);
            if (var10.isInUse() && var1 == var10.getPreparedStatement()) {
               ResultSet var11 = var10.getResultSet();
               if (var11 != null && !var11.isClosed()) {
                  if (var2 != null && var11.returnsRows()) {
                     DependencyManager var8 = this.getDataDictionary().getDependencyManager();
                     throw StandardException.newException("X0X95.S", new Object[]{var8.getActionString(var3), var2.getObjectName()});
                  }

                  return true;
               }
            }
         }

         return false;
      }
   }

   public String getSessionUserId() {
      return this.sessionUser;
   }

   public SchemaDescriptor getDefaultSchema() {
      return this.getCurrentSQLSessionContext().getDefaultSchema();
   }

   public SchemaDescriptor getDefaultSchema(Activation var1) {
      return this.getCurrentSQLSessionContext(var1).getDefaultSchema();
   }

   public String getCurrentSchemaName() {
      SchemaDescriptor var1 = this.getDefaultSchema();
      return null == var1 ? null : var1.getSchemaName();
   }

   public String getCurrentSchemaName(Activation var1) {
      SchemaDescriptor var2 = this.getDefaultSchema(var1);
      return null == var2 ? null : var2.getSchemaName();
   }

   public boolean isInitialDefaultSchema(String var1) {
      return this.cachedInitialDefaultSchemaDescr.getSchemaName().equals(var1);
   }

   public void setDefaultSchema(SchemaDescriptor var1) throws StandardException {
      if (var1 == null) {
         var1 = this.getInitialDefaultSchemaDescriptor();
      }

      this.getCurrentSQLSessionContext().setDefaultSchema(var1);
   }

   public void setDefaultSchema(Activation var1, SchemaDescriptor var2) throws StandardException {
      if (var2 == null) {
         var2 = this.getInitialDefaultSchemaDescriptor();
      }

      this.getCurrentSQLSessionContext(var1).setDefaultSchema(var2);
   }

   public void resetSchemaUsages(Activation var1, String var2) throws StandardException {
      Activation var3 = var1.getParentActivation();

      SchemaDescriptor var4;
      for(var4 = this.getInitialDefaultSchemaDescriptor(); var3 != null; var3 = var3.getParentActivation()) {
         SQLSessionContext var5 = var3.getSQLSessionContextForChildren();
         SchemaDescriptor var6 = var5.getDefaultSchema();
         if (var2.equals(var6.getSchemaName())) {
            var5.setDefaultSchema(var4);
         }
      }

      SQLSessionContext var7 = this.getTopLevelSQLSessionContext();
      SchemaDescriptor var8 = var7.getDefaultSchema();
      if (var2.equals(var8.getSchemaName())) {
         var7.setDefaultSchema(var4);
      }

   }

   public Long getIdentityValue() {
      return this.identityNotNull ? this.identityVal : null;
   }

   public void setIdentityValue(long var1) {
      this.identityVal = var1;
      this.identityNotNull = true;
   }

   public final CompilerContext pushCompilerContext() {
      return this.pushCompilerContext((SchemaDescriptor)null);
   }

   public CompilerContext pushCompilerContext(SchemaDescriptor var1) {
      boolean var3 = false;
      Object var2 = (CompilerContext)this.getContextManager().getContext("CompilerContext");
      if (var2 == null) {
         var3 = true;
      }

      if (var2 != null && !((CompilerContext)var2).getInUse()) {
         ((CompilerContext)var2).resetContext();
      } else {
         var2 = new CompilerContextImpl(this.getContextManager(), this, this.tcf);
         if (var3) {
            ((CompilerContext)var2).firstOnStack();
         }
      }

      ((CompilerContext)var2).setInUse(true);
      StatementContext var4 = this.getStatementContext();
      if (var4.getSystemCode()) {
         ((CompilerContext)var2).setReliability(0);
      }

      if (var1 != null && var1.getUUID() != null) {
         ((CompilerContext)var2).setCompilationSchema(var1);
      }

      return (CompilerContext)var2;
   }

   public void popCompilerContext(CompilerContext var1) {
      var1.setCurrentDependent((Dependent)null);
      var1.setInUse(false);
      if (!var1.isFirstOnStack()) {
         var1.popMe();
      } else {
         var1.setCompilationSchema((SchemaDescriptor)null);
      }

   }

   public StatementContext pushStatementContext(boolean var1, boolean var2, String var3, ParameterValueSet var4, boolean var5, long var6) {
      int var8 = this.statementDepth;
      boolean var9 = false;
      boolean var10 = false;
      Object var11 = this.statementContexts[0];
      if (var11 == null) {
         var11 = this.statementContexts[0] = new GenericStatementContext(this);
         ((StatementContext)var11).setSQLSessionContext(this.getTopLevelSQLSessionContext());
      } else if (this.statementDepth > 0) {
         StatementContext var12;
         if (this.statementDepth == 1) {
            var11 = this.statementContexts[1];
            if (var11 == null) {
               var11 = this.statementContexts[1] = new GenericStatementContext(this);
            } else {
               ((StatementContext)var11).pushMe();
            }

            var12 = this.statementContexts[0];
         } else {
            var12 = this.getStatementContext();
            var11 = new GenericStatementContext(this);
         }

         ((StatementContext)var11).setSQLSessionContext(var12.getSQLSessionContext());
         var9 = var12.inTrigger() || this.outermostTrigger == var8;
         var10 = var12.isAtomic();
         ((StatementContext)var11).setSQLAllowed(var12.getSQLAllowed(), false);
         if (var12.getSystemCode()) {
            ((StatementContext)var11).setSystemCode();
         }
      } else {
         ((StatementContext)var11).setSQLSessionContext(this.getTopLevelSQLSessionContext());
      }

      this.incrementStatementDepth();
      ((StatementContext)var11).setInUse(var9, var1 || var10, var2, var3, var4, var6);
      if (var5) {
         ((StatementContext)var11).setParentRollback();
      }

      return (StatementContext)var11;
   }

   public void popStatementContext(StatementContext var1, Throwable var2) {
      if (var1 != null) {
         if (!var1.inUse()) {
            return;
         }

         var1.clearInUse();
      }

      this.decrementStatementDepth();
      if (this.statementDepth == -1) {
         this.resetStatementDepth();
      } else if (this.statementDepth != 0) {
         var1.popMe();
      }

   }

   public void pushExecutionStmtValidator(ExecutionStmtValidator var1) {
      this.stmtValidators.add(var1);
   }

   public void popExecutionStmtValidator(ExecutionStmtValidator var1) throws StandardException {
      this.stmtValidators.remove(var1);
   }

   public void pushTriggerExecutionContext(TriggerExecutionContext var1) throws StandardException {
      if (this.outermostTrigger == -1) {
         this.outermostTrigger = this.statementDepth;
      }

      if (this.triggerExecutionContexts.size() >= 16) {
         throw StandardException.newException("54038", new Object[0]);
      } else {
         this.triggerExecutionContexts.add(var1);
      }
   }

   public void popTriggerExecutionContext(TriggerExecutionContext var1) throws StandardException {
      if (this.outermostTrigger == this.statementDepth) {
         this.outermostTrigger = -1;
      }

      this.triggerExecutionContexts.remove(var1);
   }

   public TriggerExecutionContext getTriggerExecutionContext() {
      return this.triggerExecutionContexts.isEmpty() ? null : (TriggerExecutionContext)this.triggerExecutionContexts.get(this.triggerExecutionContexts.size() - 1);
   }

   public void validateStmtExecution(ConstantAction var1) throws StandardException {
      if (this.stmtValidators.size() > 0) {
         Iterator var2 = this.stmtValidators.iterator();

         while(var2.hasNext()) {
            ((ExecutionStmtValidator)var2.next()).validateStatement(var1);
         }
      }

   }

   public void pushTriggerTable(TableDescriptor var1) {
      this.triggerTables.add(var1);
   }

   public void popTriggerTable(TableDescriptor var1) {
      this.triggerTables.remove(var1);
   }

   public TableDescriptor getTriggerTable() {
      return this.triggerTables.isEmpty() ? (TableDescriptor)null : (TableDescriptor)this.triggerTables.get(this.triggerTables.size() - 1);
   }

   public Database getDatabase() {
      return this.db;
   }

   public int incrementBindCount() {
      ++this.bindCount;
      return this.bindCount;
   }

   public int decrementBindCount() {
      --this.bindCount;
      return this.bindCount;
   }

   public int getBindCount() {
      return this.bindCount;
   }

   public final void setDataDictionaryWriteMode() {
      this.ddWriteMode = true;
   }

   public final boolean dataDictionaryInWriteMode() {
      return this.ddWriteMode;
   }

   public final void setRunTimeStatisticsMode(boolean var1) {
      this.runTimeStatisticsSetting = var1;
   }

   public boolean getRunTimeStatisticsMode() {
      return this.runTimeStatisticsSetting;
   }

   public void setStatisticsTiming(boolean var1) {
      this.statisticsTiming = var1;
   }

   public boolean getStatisticsTiming() {
      return this.statisticsTiming;
   }

   public void setRunTimeStatisticsObject(RunTimeStatistics var1) {
      this.runTimeStatisticsObject = var1;
   }

   public RunTimeStatistics getRunTimeStatisticsObject() {
      return this.runTimeStatisticsObject;
   }

   public int getStatementDepth() {
      return this.statementDepth;
   }

   public boolean isIsolationLevelSetUsingSQLorJDBC() {
      return this.isolationLevelSetUsingSQLorJDBC;
   }

   public void resetIsolationLevelFlagUsedForSQLandJDBC() {
      this.isolationLevelSetUsingSQLorJDBC = false;
   }

   public void setIsolationLevel(int var1) throws StandardException {
      StatementContext var2 = this.getStatementContext();
      if (var2 != null && var2.inTrigger()) {
         throw StandardException.newException("X0Y71.S", new Object[]{this.getTriggerExecutionContext().toString()});
      } else if (this.isolationLevel != var1 && !this.verifyAllHeldResultSetsAreClosed()) {
         throw StandardException.newException("X0X03.S", new Object[0]);
      } else {
         TransactionController var3 = this.getTransactionExecute();
         if (!var3.isIdle()) {
            if (var3.isGlobal()) {
               throw StandardException.newException("X0Y77.S", new Object[0]);
            }

            this.userCommit();
         }

         this.isolationLevel = var1;
         this.isolationLevelExplicitlySet = true;
         this.isolationLevelSetUsingSQLorJDBC = true;
      }
   }

   public int getCurrentIsolationLevel() {
      return this.isolationLevel == 0 ? this.defaultIsolationLevel : this.isolationLevel;
   }

   public String getCurrentIsolationLevelStr() {
      return this.isolationLevel >= 0 && this.isolationLevel < TransactionControl.isolationMapCount() ? TransactionControl.isolationTextNames(this.isolationLevel)[0] : TransactionControl.isolationTextNames(0)[0];
   }

   public void setPrepareIsolationLevel(int var1) {
      this.prepareIsolationLevel = var1;
   }

   public int getPrepareIsolationLevel() {
      return !this.isolationLevelExplicitlySet ? this.prepareIsolationLevel : 0;
   }

   public StatementContext getStatementContext() {
      return (StatementContext)this.getContextManager().getContext("StatementContext");
   }

   public void setOptimizerTracer(OptTrace var1) {
      this.optimizerTracer = var1;
   }

   public OptTrace getOptimizerTracer() {
      return this.optimizerTracer;
   }

   public boolean optimizerTracingIsOn() {
      return this.optimizerTracer != null;
   }

   public boolean isTransactionPristine() {
      return this.getTransactionExecute().isPristine();
   }

   public void cleanupOnError(Throwable var1) throws StandardException {
      int var2 = var1 instanceof StandardException ? ((StandardException)var1).getSeverity() : '鱀';
      if (this.statementContexts[0] != null) {
         this.statementContexts[0].clearInUse();
         if (var2 >= 40000) {
            this.statementContexts[0].popMe();
         }
      }

      if (this.statementContexts[1] != null) {
         this.statementContexts[1].clearInUse();
      }

      if (var2 >= 40000) {
         for(int var3 = this.acts.size() - 1; var3 >= 0; --var3) {
            if (var3 < this.acts.size()) {
               Activation var4 = (Activation)this.acts.get(var3);
               var4.reset();
               var4.close();
            }
         }

         this.popMe();
         InterruptStatus.saveInfoFromLcc(this);
      } else if (var2 >= 30000) {
         this.internalRollback();
      }

   }

   public boolean isLastHandler(int var1) {
      return false;
   }

   private void endTransactionActivationHandling(boolean var1) throws StandardException {
      for(int var2 = this.acts.size() - 1; var2 >= 0; --var2) {
         if (var2 < this.acts.size()) {
            Activation var3 = (Activation)this.acts.get(var2);
            if (!var3.isInUse()) {
               var3.close();
            } else {
               ResultSet var4 = var3.getResultSet();
               boolean var5 = var4 != null && var4.returnsRows();
               if (var1) {
                  if (var5) {
                     var3.reset();
                  }

                  if (this.dataDictionaryInWriteMode()) {
                     ExecPreparedStatement var6 = var3.getPreparedStatement();
                     if (var6 != null) {
                        var6.makeInvalid(4, this);
                     }
                  }
               } else {
                  if (var5) {
                     if (!var3.getResultSetHoldability()) {
                        var4.close();
                     } else {
                        var4.clearCurrentRow();
                     }
                  }

                  var3.clearHeapConglomerateController();
               }
            }
         }
      }

   }

   private void finishDDTransaction() throws StandardException {
      if (this.ddWriteMode) {
         DataDictionary var1 = this.getDataDictionary();
         var1.transactionFinished();
         this.ddWriteMode = false;
      }

   }

   private void incrementStatementDepth() {
      ++this.statementDepth;
   }

   private void decrementStatementDepth() {
      --this.statementDepth;
   }

   protected void resetStatementDepth() {
      this.statementDepth = 0;
   }

   public DataDictionary getDataDictionary() {
      return this.getDatabase().getDataDictionary();
   }

   public void setReadOnly(boolean var1) throws StandardException {
      if (!this.tran.isPristine()) {
         throw StandardException.newException("25501", new Object[0]);
      } else {
         this.authorizer.setReadOnlyConnection(var1, true);
      }
   }

   public boolean isReadOnly() {
      return this.authorizer.isReadOnlyConnection();
   }

   public Authorizer getAuthorizer() {
      return this.authorizer;
   }

   public Long lastAutoincrementValue(String var1, String var2, String var3) {
      String var4 = AutoincrementCounter.makeIdentity(var1, var2, var3);
      int var5 = this.triggerExecutionContexts.size();

      for(int var6 = var5 - 1; var6 >= 0; --var6) {
         TriggerExecutionContext var7 = (TriggerExecutionContext)this.triggerExecutionContexts.get(var6);
         Long var8 = var7.getAutoincrementValue(var4);
         if (var8 != null) {
            return var8;
         }
      }

      if (this.autoincrementHT == null) {
         return null;
      } else {
         return (Long)this.autoincrementHT.get(var4);
      }
   }

   public void setAutoincrementUpdate(boolean var1) {
      this.autoincrementUpdate = var1;
   }

   public boolean getAutoincrementUpdate() {
      return this.autoincrementUpdate;
   }

   public void autoincrementCreateCounter(String var1, String var2, String var3, Long var4, long var5, int var7) {
      String var8 = AutoincrementCounter.makeIdentity(var1, var2, var3);
      if (this.autoincrementCacheHashtable == null) {
         this.autoincrementCacheHashtable = new HashMap();
      }

      AutoincrementCounter var9 = (AutoincrementCounter)this.autoincrementCacheHashtable.get(var8);
      if (var9 == null) {
         var9 = new AutoincrementCounter(var4, var5, 0L, var1, var2, var3, var7);
         this.autoincrementCacheHashtable.put(var8, var9);
      }
   }

   public long nextAutoincrementValue(String var1, String var2, String var3) throws StandardException {
      String var4 = AutoincrementCounter.makeIdentity(var1, var2, var3);
      AutoincrementCounter var5 = (AutoincrementCounter)this.autoincrementCacheHashtable.get(var4);
      return var5 == null ? 0L : var5.update();
   }

   public void autoincrementFlushCache(UUID var1) throws StandardException {
      if (this.autoincrementCacheHashtable != null) {
         if (this.autoincrementHT == null) {
            this.autoincrementHT = new HashMap();
         }

         DataDictionary var2 = this.getDataDictionary();

         for(String var4 : this.autoincrementCacheHashtable.keySet()) {
            AutoincrementCounter var5 = (AutoincrementCounter)this.autoincrementCacheHashtable.get(var4);
            Long var6 = var5.getCurrentValue();
            var5.flushToDisk(this.getTransactionExecute(), var2, var1);
            if (var6 != null) {
               this.autoincrementHT.put(var4, var6);
            }
         }

         this.autoincrementCacheHashtable.clear();
      }
   }

   public void copyHashtableToAIHT(Map var1) {
      if (!var1.isEmpty()) {
         if (this.autoincrementHT == null) {
            this.autoincrementHT = new HashMap();
         }

         this.autoincrementHT.putAll(var1);
      }
   }

   public int getInstanceNumber() {
      return this.instanceNumber;
   }

   public String getDrdaID() {
      return this.drdaID;
   }

   public void setDrdaID(String var1) {
      this.drdaID = var1;
   }

   public String getDbname() {
      return this.dbname;
   }

   public Activation getLastActivation() {
      return (Activation)this.acts.get(this.acts.size() - 1);
   }

   public StringBuffer appendErrorInfo() {
      TransactionController var1 = this.getTransactionExecute();
      if (var1 == null) {
         return null;
      } else {
         StringBuffer var2 = new StringBuffer(200);
         var2.append("(XID = ");
         var2.append(var1.getTransactionIdString());
         var2.append("), ");
         var2.append("(SESSIONID = ");
         var2.append(Integer.toString(this.getInstanceNumber()));
         var2.append("), ");
         var2.append("(DATABASE = ");
         var2.append(this.getDbname());
         var2.append("), ");
         var2.append("(DRDAID = ");
         var2.append(this.getDrdaID());
         var2.append("), ");
         return var2;
      }
   }

   public void setCurrentRole(Activation var1, String var2) {
      this.getCurrentSQLSessionContext(var1).setRole(var2);
   }

   public String getCurrentRoleId(Activation var1) {
      return this.getCurrentSQLSessionContext(var1).getRole();
   }

   public String getCurrentUserId(Activation var1) {
      return this.getCurrentSQLSessionContext(var1).getCurrentUser();
   }

   public String getCurrentRoleIdDelimited(Activation var1) throws StandardException {
      String var2 = this.getCurrentSQLSessionContext(var1).getRole();
      if (var2 != null) {
         this.beginNestedTransaction(true);

         try {
            if (!this.roleIsSettable(var1, var2)) {
               this.setCurrentRole(var1, (String)null);
               var2 = null;
            }
         } finally {
            this.commitNestedTransaction();
         }
      }

      if (var2 != null) {
         var2 = IdUtil.normalToDelimited(var2);
      }

      return var2;
   }

   public boolean roleIsSettable(Activation var1, String var2) throws StandardException {
      DataDictionary var3 = this.getDataDictionary();
      String var4 = var3.getAuthorizationDatabaseOwner();
      String var6 = this.getCurrentUserId(var1);
      RoleGrantDescriptor var5;
      if (var6.equals(var4)) {
         var5 = var3.getRoleDefinitionDescriptor(var2);
      } else {
         var5 = var3.getRoleGrantDescriptor(var2, var6, var4);
         if (var5 == null) {
            var5 = var3.getRoleGrantDescriptor(var2, "PUBLIC", var4);
         }
      }

      return var5 != null;
   }

   public SQLSessionContext getCurrentSQLSessionContext(Activation var1) {
      Activation var3 = var1.getParentActivation();
      SQLSessionContext var2;
      if (var3 == null) {
         var2 = this.getTopLevelSQLSessionContext();
      } else {
         var2 = var3.getSQLSessionContextForChildren();
      }

      return var2;
   }

   private SQLSessionContext getCurrentSQLSessionContext() {
      StatementContext var1 = this.getStatementContext();
      SQLSessionContext var2;
      if (var1 != null && var1.inUse()) {
         var2 = var1.getSQLSessionContext();
      } else {
         var2 = this.getTopLevelSQLSessionContext();
      }

      return var2;
   }

   public void pushNestedSessionContext(Activation var1, boolean var2, String var3) throws StandardException {
      this.setupSessionContextMinion(var1, true, var2, var3);
   }

   private void setupSessionContextMinion(Activation var1, boolean var2, boolean var3, String var4) throws StandardException {
      SQLSessionContext var5 = var1.setupSQLSessionContextForChildren(var2);
      if (var3) {
         var5.setUser(var4);
      } else {
         var5.setUser(this.getCurrentUserId(var1));
      }

      if (var3) {
         var5.setRole((String)null);
      } else {
         var5.setRole(this.getCurrentRoleId(var1));
      }

      if (var3) {
         SchemaDescriptor var6 = this.getDataDictionary().getSchemaDescriptor(var4, this.getTransactionExecute(), false);
         if (var6 == null) {
            var6 = new SchemaDescriptor(this.getDataDictionary(), var4, var4, (UUID)null, false);
         }

         var5.setDefaultSchema(var6);
      } else {
         var5.setDefaultSchema(this.getDefaultSchema(var1));
      }

      SQLSessionContext var8 = this.getCurrentSQLSessionContext(var1);
      var5.setDeferredAll(var8.getDeferredAll());
      var5.setConstraintModes(var8.getConstraintModes());
      StatementContext var7 = this.getStatementContext();
      var7.setSQLSessionContext(var5);
   }

   public void popNestedSessionContext(Activation var1) throws StandardException {
      SQLSessionContext var2 = var1.getSQLSessionContextForChildren();
      SQLSessionContext var3 = this.getCurrentSQLSessionContext(var1);
      this.compareConstraintModes(var2, var3);
   }

   private void compareConstraintModes(SQLSessionContext var1, SQLSessionContext var2) throws StandardException {
      if (this.deferredHashTables != null) {
         for(DeferredConstraintsMemory.ValidationInfo var4 : this.deferredHashTables.values()) {
            var4.possiblyValidateOnReturn(this, var1, var2);
         }

      }
   }

   public boolean isEffectivelyDeferred(SQLSessionContext var1, UUID var2) throws StandardException {
      Boolean var3 = var1.isDeferred(var2);
      DataDictionary var5 = this.getDataDictionary();
      boolean var4;
      if (var3 != null) {
         var4 = var3;
      } else {
         ConstraintDescriptor var6 = var5.getConstraintDescriptor(var2);
         var4 = var6.initiallyDeferred();
      }

      return var4;
   }

   public void setupSubStatementSessionContext(Activation var1) throws StandardException {
      this.setupSessionContextMinion(var1, false, false, (String)null);
   }

   public SQLSessionContext getTopLevelSQLSessionContext() {
      if (this.topLevelSSC == null) {
         this.topLevelSSC = new SQLSessionContextImpl(this.getInitialDefaultSchemaDescriptor(), this.getSessionUserId());
      }

      return this.topLevelSSC;
   }

   public SQLSessionContext createSQLSessionContext() {
      return new SQLSessionContextImpl(this.getInitialDefaultSchemaDescriptor(), this.getSessionUserId());
   }

   public Map getPrintedObjectsMap() {
      if (this.printedObjectsMap == null) {
         this.printedObjectsMap = new IdentityHashMap();
      }

      return this.printedObjectsMap;
   }

   public boolean getXplainOnlyMode() {
      return this.xplainOnlyMode;
   }

   public void setXplainOnlyMode(boolean var1) {
      this.xplainOnlyMode = var1;
   }

   public String getXplainSchema() {
      return this.xplain_schema;
   }

   public void setXplainSchema(String var1) {
      this.xplain_schema = var1;
   }

   public void setXplainStatement(Object var1, Object var2) {
      this.xplain_statements.put(var1, var2);
   }

   public Object getXplainStatement(Object var1) {
      return this.xplain_statements.get(var1);
   }

   public void setASTVisitor(ASTVisitor var1) {
      this.astWalker = var1;
   }

   public ASTVisitor getASTVisitor() {
      return this.astWalker;
   }

   public void setInterruptedException(StandardException var1) {
      this.interruptedException = var1;
   }

   public StandardException getInterruptedException() {
      return this.interruptedException;
   }

   public FormatableBitSet getReferencedColumnMap(TableDescriptor var1) {
      return (FormatableBitSet)this.referencedColumnMap.get(var1);
   }

   public void setReferencedColumnMap(TableDescriptor var1, FormatableBitSet var2) {
      this.referencedColumnMap.put(var1, var2);
   }

   public void setConstraintDeferred(Activation var1, ConstraintDescriptor var2, boolean var3) throws StandardException {
      if (!var3) {
         this.validateDeferredConstraint(var2);
      }

      this.getCurrentSQLSessionContext(var1).setDeferred(var2.getUUID(), var3);
   }

   public void checkIntegrity() throws StandardException {
      this.validateDeferredConstraints(true);
      this.clearDeferreds();
   }

   private void clearDeferreds() {
      this.deferredHashTables = null;
      this.getCurrentSQLSessionContext().resetConstraintModes();
   }

   public void setDeferredAll(Activation var1, boolean var2) throws StandardException {
      if (!var2) {
         this.validateDeferredConstraints(false);
      }

      this.getCurrentSQLSessionContext(var1).setDeferredAll(var2);
   }

   public HashMap getDeferredHashTables() {
      if (this.deferredHashTables == null) {
         this.deferredHashTables = new HashMap();
      }

      return this.deferredHashTables;
   }

   private void validateDeferredConstraints(boolean var1) throws StandardException {
      if (this.deferredHashTables != null) {
         for(DeferredConstraintsMemory.ValidationInfo var3 : this.deferredHashTables.values()) {
            var3.validateConstraint(this, (UUID)null, var1);
         }

      }
   }

   private void validateDeferredConstraint(ConstraintDescriptor var1) throws StandardException {
      if (this.deferredHashTables != null) {
         UUID var2 = var1.hasBackingIndex() ? var1.getUUID() : var1.getTableId();
         DeferredConstraintsMemory.ValidationInfo var3 = (DeferredConstraintsMemory.ValidationInfo)this.deferredHashTables.get(var2);
         if (var3 != null) {
            var3.validateConstraint(this, var1.getUUID(), false);
         }
      }
   }
}
