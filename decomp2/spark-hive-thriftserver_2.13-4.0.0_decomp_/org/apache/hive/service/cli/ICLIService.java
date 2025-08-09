package org.apache.hive.service.cli;

import java.util.List;
import java.util.Map;
import org.apache.hive.service.auth.HiveAuthFactory;
import org.apache.hive.service.rpc.thrift.TOperationHandle;
import org.apache.hive.service.rpc.thrift.TRowSet;
import org.apache.hive.service.rpc.thrift.TTableSchema;

public interface ICLIService {
   SessionHandle openSession(String var1, String var2, Map var3) throws HiveSQLException;

   SessionHandle openSessionWithImpersonation(String var1, String var2, Map var3, String var4) throws HiveSQLException;

   void closeSession(SessionHandle var1) throws HiveSQLException;

   GetInfoValue getInfo(SessionHandle var1, GetInfoType var2) throws HiveSQLException;

   OperationHandle executeStatement(SessionHandle var1, String var2, Map var3) throws HiveSQLException;

   OperationHandle executeStatement(SessionHandle var1, String var2, Map var3, long var4) throws HiveSQLException;

   OperationHandle executeStatementAsync(SessionHandle var1, String var2, Map var3) throws HiveSQLException;

   OperationHandle executeStatementAsync(SessionHandle var1, String var2, Map var3, long var4) throws HiveSQLException;

   OperationHandle getTypeInfo(SessionHandle var1) throws HiveSQLException;

   OperationHandle getCatalogs(SessionHandle var1) throws HiveSQLException;

   OperationHandle getSchemas(SessionHandle var1, String var2, String var3) throws HiveSQLException;

   OperationHandle getTables(SessionHandle var1, String var2, String var3, String var4, List var5) throws HiveSQLException;

   OperationHandle getTableTypes(SessionHandle var1) throws HiveSQLException;

   OperationHandle getColumns(SessionHandle var1, String var2, String var3, String var4, String var5) throws HiveSQLException;

   OperationHandle getFunctions(SessionHandle var1, String var2, String var3, String var4) throws HiveSQLException;

   OperationStatus getOperationStatus(OperationHandle var1) throws HiveSQLException;

   void cancelOperation(OperationHandle var1) throws HiveSQLException;

   void closeOperation(OperationHandle var1) throws HiveSQLException;

   TTableSchema getResultSetMetadata(OperationHandle var1) throws HiveSQLException;

   TRowSet fetchResults(OperationHandle var1) throws HiveSQLException;

   TRowSet fetchResults(OperationHandle var1, FetchOrientation var2, long var3, FetchType var5) throws HiveSQLException;

   String getDelegationToken(SessionHandle var1, HiveAuthFactory var2, String var3, String var4) throws HiveSQLException;

   String getQueryId(TOperationHandle var1) throws HiveSQLException;

   void cancelDelegationToken(SessionHandle var1, HiveAuthFactory var2, String var3) throws HiveSQLException;

   void renewDelegationToken(SessionHandle var1, HiveAuthFactory var2, String var3) throws HiveSQLException;

   OperationHandle getPrimaryKeys(SessionHandle var1, String var2, String var3, String var4) throws HiveSQLException;

   OperationHandle getCrossReference(SessionHandle var1, String var2, String var3, String var4, String var5, String var6, String var7) throws HiveSQLException;
}
