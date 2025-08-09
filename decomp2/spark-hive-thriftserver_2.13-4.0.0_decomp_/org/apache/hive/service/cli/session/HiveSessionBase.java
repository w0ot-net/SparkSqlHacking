package org.apache.hive.service.cli.session;

import java.io.File;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hive.service.cli.SessionHandle;
import org.apache.hive.service.cli.operation.OperationManager;
import org.apache.hive.service.rpc.thrift.TProtocolVersion;

public interface HiveSessionBase {
   TProtocolVersion getProtocolVersion();

   void setSessionManager(SessionManager var1);

   SessionManager getSessionManager();

   void setOperationManager(OperationManager var1);

   boolean isOperationLogEnabled();

   File getOperationLogSessionDir();

   void setOperationLogSessionDir(File var1);

   SessionHandle getSessionHandle();

   String getUsername();

   String getPassword();

   HiveConf getHiveConf();

   SessionState getSessionState();

   String getUserName();

   void setUserName(String var1);

   String getIpAddress();

   void setIpAddress(String var1);

   long getLastAccessTime();
}
