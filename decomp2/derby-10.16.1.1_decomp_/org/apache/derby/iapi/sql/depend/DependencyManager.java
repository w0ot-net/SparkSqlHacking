package org.apache.derby.iapi.sql.depend;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public interface DependencyManager {
   int COMPILE_FAILED = 0;
   int DROP_TABLE = 1;
   int DROP_INDEX = 2;
   int CREATE_INDEX = 3;
   int ROLLBACK = 4;
   int CHANGED_CURSOR = 5;
   int DROP_METHOD_ALIAS = 6;
   int DROP_VIEW = 9;
   int CREATE_VIEW = 10;
   int PREPARED_STATEMENT_RELEASE = 11;
   int ALTER_TABLE = 12;
   int DROP_SPS = 13;
   int USER_RECOMPILE_REQUEST = 14;
   int BULK_INSERT = 15;
   int DROP_JAR = 17;
   int REPLACE_JAR = 18;
   int DROP_CONSTRAINT = 19;
   int SET_CONSTRAINTS_ENABLE = 20;
   int SET_CONSTRAINTS_DISABLE = 21;
   int CREATE_CONSTRAINT = 22;
   int INTERNAL_RECOMPILE_REQUEST = 23;
   int DROP_TRIGGER = 27;
   int CREATE_TRIGGER = 28;
   int SET_TRIGGERS_ENABLE = 29;
   int SET_TRIGGERS_DISABLE = 30;
   int MODIFY_COLUMN_DEFAULT = 31;
   int DROP_SCHEMA = 32;
   int COMPRESS_TABLE = 33;
   int RENAME = 34;
   int DROP_COLUMN = 37;
   int DROP_STATISTICS = 39;
   int UPDATE_STATISTICS = 40;
   int RENAME_INDEX = 41;
   int TRUNCATE_TABLE = 42;
   int DROP_SYNONYM = 43;
   int REVOKE_PRIVILEGE = 44;
   int REVOKE_PRIVILEGE_RESTRICT = 45;
   int DROP_COLUMN_RESTRICT = 46;
   int REVOKE_ROLE = 47;
   int RECHECK_PRIVILEGES = 48;
   int DROP_SEQUENCE = 49;
   int DROP_UDT = 50;
   int DROP_AGGREGATE = 51;
   int MAX_ACTION_CODE = 65535;

   void addDependency(Dependent var1, Provider var2, ContextManager var3) throws StandardException;

   void invalidateFor(Provider var1, int var2, LanguageConnectionContext var3) throws StandardException;

   void clearDependencies(LanguageConnectionContext var1, Dependent var2) throws StandardException;

   void clearInMemoryDependency(Dependency var1);

   ProviderInfo[] getPersistentProviderInfos(Dependent var1) throws StandardException;

   ProviderInfo[] getPersistentProviderInfos(ProviderList var1) throws StandardException;

   void clearColumnInfoInProviders(ProviderList var1) throws StandardException;

   void copyDependencies(Dependent var1, Dependent var2, boolean var3, ContextManager var4) throws StandardException;

   String getActionString(int var1);

   int countDependencies() throws StandardException;

   void clearDependencies(LanguageConnectionContext var1, Dependent var2, TransactionController var3) throws StandardException;

   void copyDependencies(Dependent var1, Dependent var2, boolean var3, ContextManager var4, TransactionController var5) throws StandardException;
}
