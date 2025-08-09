package org.apache.derby.iapi.sql.compile;

import java.sql.SQLWarning;
import java.util.List;
import org.apache.derby.iapi.services.compiler.JavaFactory;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.sql.depend.Dependent;
import org.apache.derby.iapi.sql.depend.Provider;
import org.apache.derby.iapi.sql.depend.ProviderList;
import org.apache.derby.iapi.sql.dictionary.AliasDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.PrivilegedSQLObject;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.SequenceDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.store.access.SortCostController;
import org.apache.derby.iapi.store.access.StoreCostController;
import org.apache.derby.shared.common.error.StandardException;

public interface CompilerContext extends Context {
   String CONTEXT_ID = "CompilerContext";
   int DATETIME_ILLEGAL = 1;
   int CURRENT_CONNECTION_ILLEGAL = 2;
   int FUNCTION_CALL_ILLEGAL = 4;
   int UNNAMED_PARAMETER_ILLEGAL = 8;
   int DIAGNOSTICS_ILLEGAL = 16;
   int SUBQUERY_ILLEGAL = 32;
   int USER_ILLEGAL = 64;
   int COLUMN_REFERENCE_ILLEGAL = 128;
   int IGNORE_MISSING_CLASSES = 256;
   int SCHEMA_ILLEGAL = 512;
   int INTERNAL_SQL_ILLEGAL = 1024;
   int MODIFIES_SQL_DATA_PROCEDURE_ILLEGAL = 2048;
   int NON_DETERMINISTIC_ILLEGAL = 4096;
   int SQL_IN_ROUTINES_ILLEGAL = 8192;
   int NEXT_VALUE_FOR_ILLEGAL = 16384;
   int SQL_LEGAL = 1024;
   int INTERNAL_SQL_LEGAL = 0;
   int CHECK_CONSTRAINT = 18041;
   int DEFAULT_RESTRICTION = 1192;
   int GENERATION_CLAUSE_RESTRICTION = 30329;
   int WHERE_CLAUSE_RESTRICTION = 16384;
   int HAVING_CLAUSE_RESTRICTION = 16384;
   int ON_CLAUSE_RESTRICTION = 16384;
   int AGGREGATE_RESTRICTION = 16384;
   int CONDITIONAL_RESTRICTION = 16384;
   int GROUP_BY_RESTRICTION = 16384;
   int CASE_OPERAND_RESTRICTION = 22528;
   String WHERE_SCOPE = "whereScope";

   Parser getParser();

   OptimizerFactory getOptimizerFactory();

   TypeCompilerFactory getTypeCompilerFactory();

   ClassFactory getClassFactory();

   JavaFactory getJavaFactory();

   int getNextColumnNumber();

   void resetContext();

   int getNextTableNumber();

   int getNumTables();

   int getNextSubqueryNumber();

   int getNumSubquerys();

   int getNextResultSetNumber();

   void resetNextResultSetNumber();

   int getNumResultSets();

   String getUniqueClassName();

   void setCurrentDependent(Dependent var1);

   ProviderList getCurrentAuxiliaryProviderList();

   void setCurrentAuxiliaryProviderList(ProviderList var1);

   void createDependency(Provider var1) throws StandardException;

   void createDependency(Dependent var1, Provider var2) throws StandardException;

   int addSavedObject(Object var1);

   Object[] getSavedObjects();

   void setSavedObjects(List var1);

   void setInUse(boolean var1);

   boolean getInUse();

   void firstOnStack();

   boolean isFirstOnStack();

   void setReliability(int var1);

   int getReliability();

   SchemaDescriptor getCompilationSchema();

   SchemaDescriptor setCompilationSchema(SchemaDescriptor var1);

   void pushCompilationSchema(SchemaDescriptor var1);

   void popCompilationSchema();

   StoreCostController getStoreCostController(long var1) throws StandardException;

   SortCostController getSortCostController() throws StandardException;

   void setParameterList(List var1);

   List getParameterList();

   void setReturnParameterFlag();

   boolean getReturnParameterFlag();

   Object getCursorInfo();

   void setCursorInfo(Object var1);

   void setScanIsolationLevel(int var1);

   int getScanIsolationLevel();

   int getNextEquivalenceClass();

   void addWarning(SQLWarning var1);

   SQLWarning getWarnings();

   void pushCurrentPrivType(int var1);

   void popCurrentPrivType();

   void addRequiredColumnPriv(ColumnDescriptor var1);

   void addRequiredTablePriv(TableDescriptor var1);

   void addRequiredSchemaPriv(String var1, String var2, int var3);

   void addRequiredRoutinePriv(AliasDescriptor var1);

   void addRequiredUsagePriv(PrivilegedSQLObject var1);

   void addRequiredRolePriv(String var1, int var2);

   List getRequiredPermissionsList();

   void addReferencedSequence(SequenceDescriptor var1);

   boolean isReferenced(SequenceDescriptor var1);

   void addPrivilegeFilter(VisitableFilter var1);

   void removePrivilegeFilter(VisitableFilter var1);

   boolean passesPrivilegeFilters(Visitable var1) throws StandardException;

   void beginScope(String var1);

   void endScope(String var1);

   int scopeDepth(String var1);

   boolean skipTypePrivileges(boolean var1);

   boolean skippingTypePrivileges();
}
