package org.apache.hive.service.cli.session;

import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.metastore.IMetaStoreClient;
import org.apache.hive.service.auth.HiveAuthFactory;
import org.apache.hive.service.cli.FetchOrientation;
import org.apache.hive.service.cli.FetchType;
import org.apache.hive.service.cli.GetInfoType;
import org.apache.hive.service.cli.GetInfoValue;
import org.apache.hive.service.cli.HiveSQLException;
import org.apache.hive.service.cli.OperationHandle;
import org.apache.hive.service.rpc.thrift.TRowSet;
import org.apache.hive.service.rpc.thrift.TTableSchema;

public interface HiveSession extends HiveSessionBase {
   void open(Map var1) throws Exception;

   IMetaStoreClient getMetaStoreClient() throws HiveSQLException;

   GetInfoValue getInfo(GetInfoType var1) throws HiveSQLException;

   OperationHandle executeStatement(String var1, Map var2) throws HiveSQLException;

   OperationHandle executeStatement(String var1, Map var2, long var3) throws HiveSQLException;

   OperationHandle executeStatementAsync(String var1, Map var2) throws HiveSQLException;

   OperationHandle executeStatementAsync(String var1, Map var2, long var3) throws HiveSQLException;

   OperationHandle getTypeInfo() throws HiveSQLException;

   OperationHandle getCatalogs() throws HiveSQLException;

   OperationHandle getSchemas(String var1, String var2) throws HiveSQLException;

   OperationHandle getTables(String var1, String var2, String var3, List var4) throws HiveSQLException;

   OperationHandle getTableTypes() throws HiveSQLException;

   OperationHandle getColumns(String var1, String var2, String var3, String var4) throws HiveSQLException;

   OperationHandle getFunctions(String var1, String var2, String var3) throws HiveSQLException;

   OperationHandle getPrimaryKeys(String var1, String var2, String var3) throws HiveSQLException;

   OperationHandle getCrossReference(String var1, String var2, String var3, String var4, String var5, String var6) throws HiveSQLException;

   void close() throws HiveSQLException;

   void cancelOperation(OperationHandle var1) throws HiveSQLException;

   void closeOperation(OperationHandle var1) throws HiveSQLException;

   TTableSchema getResultSetMetadata(OperationHandle var1) throws HiveSQLException;

   TRowSet fetchResults(OperationHandle var1, FetchOrientation var2, long var3, FetchType var5) throws HiveSQLException;

   String getDelegationToken(HiveAuthFactory var1, String var2, String var3) throws HiveSQLException;

   void cancelDelegationToken(HiveAuthFactory var1, String var2) throws HiveSQLException;

   void renewDelegationToken(HiveAuthFactory var1, String var2) throws HiveSQLException;

   void closeExpiredOperations();

   long getNoOperationTime();
}
