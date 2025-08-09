package org.apache.derby.iapi.sql.conn;

import java.util.HashMap;
import java.util.Map;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.db.Database;
import org.apache.derby.iapi.db.TriggerExecutionContext;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.LanguageFactory;
import org.apache.derby.iapi.sql.ParameterValueSet;
import org.apache.derby.iapi.sql.PreparedStatement;
import org.apache.derby.iapi.sql.compile.ASTVisitor;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.OptTrace;
import org.apache.derby.iapi.sql.compile.OptimizerFactory;
import org.apache.derby.iapi.sql.depend.Provider;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.sql.execute.CursorActivation;
import org.apache.derby.iapi.sql.execute.ExecutionStmtValidator;
import org.apache.derby.iapi.sql.execute.RunTimeStatistics;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.shared.common.error.StandardException;

public interface LanguageConnectionContext extends Context {
   String CONTEXT_ID = "LanguageConnectionContext";
   int OUTERMOST_STATEMENT = 1;
   int SQL92_SCHEMAS = 0;
   int USER_NAME_SCHEMA = 1;
   int NO_SCHEMAS = 2;
   String xidStr = "(XID = ";
   String lccStr = "(SESSIONID = ";
   String dbnameStr = "(DATABASE = ";
   String drdaStr = "(DRDAID = ";
   int SINGLE_TRANSACTION_LOCK = 1;
   int MULTI_TRANSACTION_LOCK = 2;
   int UNKNOWN_CASING = -1;
   int ANSI_CASING = 0;
   int ANTI_ANSI_CASING = 1;

   void initialize() throws StandardException;

   boolean getLogStatementText();

   void setLogStatementText(boolean var1);

   boolean getLogQueryPlan();

   int getLockEscalationThreshold();

   void addActivation(Activation var1) throws StandardException;

   void notifyUnusedActivation();

   void removeActivation(Activation var1) throws StandardException;

   int getActivationCount();

   CursorActivation lookupCursorActivation(String var1);

   Activation getLastActivation();

   String getUniqueCursorName();

   String getUniqueSavepointName();

   int getUniqueSavepointID();

   boolean checkIfAnyDeclaredGlobalTempTablesForThisConnection();

   void markTempTableAsModifiedInUnitOfWork(String var1);

   void addDeclaredGlobalTempTable(TableDescriptor var1) throws StandardException;

   boolean dropDeclaredGlobalTempTable(String var1);

   TableDescriptor getTableDescriptorForDeclaredGlobalTempTable(String var1);

   void resetFromPool() throws StandardException;

   void internalCommit(boolean var1) throws StandardException;

   void internalCommitNoSync(int var1) throws StandardException;

   void userCommit() throws StandardException;

   void xaCommit(boolean var1) throws StandardException;

   void internalRollback() throws StandardException;

   void userRollback() throws StandardException;

   void internalRollbackToSavepoint(String var1, boolean var2, Object var3) throws StandardException;

   void releaseSavePoint(String var1, Object var2) throws StandardException;

   void xaRollback() throws StandardException;

   void languageSetSavePoint(String var1, Object var2) throws StandardException;

   void beginNestedTransaction(boolean var1) throws StandardException;

   void commitNestedTransaction() throws StandardException;

   TransactionController getTransactionCompile();

   TransactionController getTransactionExecute();

   DataDictionary getDataDictionary();

   DataValueFactory getDataValueFactory();

   LanguageFactory getLanguageFactory();

   OptimizerFactory getOptimizerFactory();

   LanguageConnectionFactory getLanguageConnectionFactory();

   String getCurrentUserId(Activation var1);

   String getSessionUserId();

   SchemaDescriptor getDefaultSchema();

   SchemaDescriptor getDefaultSchema(Activation var1);

   void setDefaultSchema(SchemaDescriptor var1) throws StandardException;

   void setDefaultSchema(Activation var1, SchemaDescriptor var2) throws StandardException;

   void resetSchemaUsages(Activation var1, String var2) throws StandardException;

   String getCurrentSchemaName();

   String getCurrentSchemaName(Activation var1);

   boolean isInitialDefaultSchema(String var1);

   Long getIdentityValue();

   void setIdentityValue(long var1);

   boolean verifyNoOpenResultSets(PreparedStatement var1, Provider var2, int var3) throws StandardException;

   boolean verifyAllHeldResultSetsAreClosed() throws StandardException;

   CompilerContext pushCompilerContext();

   CompilerContext pushCompilerContext(SchemaDescriptor var1);

   void popCompilerContext(CompilerContext var1);

   StatementContext pushStatementContext(boolean var1, boolean var2, String var3, ParameterValueSet var4, boolean var5, long var6);

   void popStatementContext(StatementContext var1, Throwable var2);

   void pushExecutionStmtValidator(ExecutionStmtValidator var1);

   void popExecutionStmtValidator(ExecutionStmtValidator var1) throws StandardException;

   void validateStmtExecution(ConstantAction var1) throws StandardException;

   void pushTriggerExecutionContext(TriggerExecutionContext var1) throws StandardException;

   void popTriggerExecutionContext(TriggerExecutionContext var1) throws StandardException;

   TriggerExecutionContext getTriggerExecutionContext();

   void pushTriggerTable(TableDescriptor var1);

   void popTriggerTable(TableDescriptor var1);

   TableDescriptor getTriggerTable();

   int incrementBindCount();

   int decrementBindCount();

   int getBindCount();

   void setDataDictionaryWriteMode();

   boolean dataDictionaryInWriteMode();

   void setRunTimeStatisticsMode(boolean var1);

   boolean getRunTimeStatisticsMode();

   void setStatisticsTiming(boolean var1);

   boolean getStatisticsTiming();

   void setRunTimeStatisticsObject(RunTimeStatistics var1);

   RunTimeStatistics getRunTimeStatisticsObject();

   int getStatementDepth();

   Database getDatabase();

   boolean isIsolationLevelSetUsingSQLorJDBC();

   void resetIsolationLevelFlagUsedForSQLandJDBC();

   void setIsolationLevel(int var1) throws StandardException;

   int getCurrentIsolationLevel();

   String getCurrentIsolationLevelStr();

   void setPrepareIsolationLevel(int var1);

   int getPrepareIsolationLevel();

   void setReadOnly(boolean var1) throws StandardException;

   boolean isReadOnly();

   Authorizer getAuthorizer();

   StatementContext getStatementContext();

   PreparedStatement prepareInternalStatement(SchemaDescriptor var1, String var2, boolean var3, boolean var4) throws StandardException;

   PreparedStatement prepareInternalStatement(String var1) throws StandardException;

   void setOptimizerTracer(OptTrace var1);

   OptTrace getOptimizerTracer();

   boolean optimizerTracingIsOn();

   boolean isTransactionPristine();

   Long lastAutoincrementValue(String var1, String var2, String var3);

   void setAutoincrementUpdate(boolean var1);

   boolean getAutoincrementUpdate();

   void copyHashtableToAIHT(Map var1);

   long nextAutoincrementValue(String var1, String var2, String var3) throws StandardException;

   void autoincrementFlushCache(UUID var1) throws StandardException;

   void autoincrementCreateCounter(String var1, String var2, String var3, Long var4, long var5, int var7);

   int getInstanceNumber();

   String getDrdaID();

   void setDrdaID(String var1);

   String getDbname();

   boolean usesSqlAuthorization();

   void closeUnusedActivations() throws StandardException;

   void setCurrentRole(Activation var1, String var2);

   String getCurrentRoleId(Activation var1);

   String getCurrentRoleIdDelimited(Activation var1) throws StandardException;

   boolean roleIsSettable(Activation var1, String var2) throws StandardException;

   void pushNestedSessionContext(Activation var1, boolean var2, String var3) throws StandardException;

   void popNestedSessionContext(Activation var1) throws StandardException;

   SQLSessionContext getTopLevelSQLSessionContext();

   void setupSubStatementSessionContext(Activation var1) throws StandardException;

   SQLSessionContext createSQLSessionContext();

   void setLastQueryTree(Object var1);

   Object getLastQueryTree();

   Map getPrintedObjectsMap();

   void setXplainOnlyMode(boolean var1);

   boolean getXplainOnlyMode();

   void setXplainSchema(String var1);

   String getXplainSchema();

   void setXplainStatement(Object var1, Object var2);

   Object getXplainStatement(Object var1);

   void setASTVisitor(ASTVisitor var1);

   ASTVisitor getASTVisitor();

   void setInterruptedException(StandardException var1);

   StandardException getInterruptedException();

   FormatableBitSet getReferencedColumnMap(TableDescriptor var1);

   void setReferencedColumnMap(TableDescriptor var1, FormatableBitSet var2);

   void setConstraintDeferred(Activation var1, ConstraintDescriptor var2, boolean var3) throws StandardException;

   boolean isEffectivelyDeferred(SQLSessionContext var1, UUID var2) throws StandardException;

   void setDeferredAll(Activation var1, boolean var2) throws StandardException;

   HashMap getDeferredHashTables();

   void checkIntegrity() throws StandardException;

   SQLSessionContext getCurrentSQLSessionContext(Activation var1);
}
