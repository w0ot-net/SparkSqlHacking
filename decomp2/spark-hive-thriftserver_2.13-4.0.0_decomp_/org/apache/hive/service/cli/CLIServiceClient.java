package org.apache.hive.service.cli;

import java.util.Collections;
import org.apache.hive.service.auth.HiveAuthFactory;
import org.apache.hive.service.rpc.thrift.TRowSet;

public abstract class CLIServiceClient implements ICLIService {
   private static final long DEFAULT_MAX_ROWS = 1000L;

   public SessionHandle openSession(String username, String password) throws HiveSQLException {
      return this.openSession(username, password, Collections.emptyMap());
   }

   public TRowSet fetchResults(OperationHandle opHandle) throws HiveSQLException {
      return this.fetchResults(opHandle, FetchOrientation.FETCH_NEXT, 1000L, FetchType.QUERY_OUTPUT);
   }

   public abstract String getDelegationToken(SessionHandle var1, HiveAuthFactory var2, String var3, String var4) throws HiveSQLException;

   public abstract void cancelDelegationToken(SessionHandle var1, HiveAuthFactory var2, String var3) throws HiveSQLException;

   public abstract void renewDelegationToken(SessionHandle var1, HiveAuthFactory var2, String var3) throws HiveSQLException;
}
