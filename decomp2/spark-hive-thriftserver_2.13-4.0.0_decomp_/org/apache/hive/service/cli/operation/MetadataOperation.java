package org.apache.hive.service.cli.operation;

import java.util.List;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HiveAccessControlException;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HiveAuthzContext;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HiveAuthzPluginException;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HiveOperationType;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HivePrivilegeObject;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hive.service.cli.HiveSQLException;
import org.apache.hive.service.cli.OperationState;
import org.apache.hive.service.cli.OperationType;
import org.apache.hive.service.cli.TableSchema;
import org.apache.hive.service.cli.session.HiveSession;

public abstract class MetadataOperation extends Operation {
   protected static final String DEFAULT_HIVE_CATALOG = "";
   protected static TableSchema RESULT_SET_SCHEMA;
   private static final char SEARCH_STRING_ESCAPE = '\\';

   protected MetadataOperation(HiveSession parentSession, OperationType opType) {
      super(parentSession, opType);
      this.setHasResultSet(true);
   }

   public void close() throws HiveSQLException {
      this.setState(OperationState.CLOSED);
      this.cleanupOperationLog();
   }

   protected String convertIdentifierPattern(String pattern, boolean datanucleusFormat) {
      return pattern == null ? this.convertPattern("%", true) : this.convertPattern(pattern, datanucleusFormat);
   }

   protected String convertSchemaPattern(String pattern) {
      return pattern != null && !pattern.isEmpty() ? this.convertPattern(pattern, true) : this.convertPattern("%", true);
   }

   private String convertPattern(String pattern, boolean datanucleusFormat) {
      String wStr;
      if (datanucleusFormat) {
         wStr = "*";
      } else {
         wStr = ".*";
      }

      return pattern.replaceAll("([^\\\\])%", "$1" + wStr).replaceAll("\\\\%", "%").replaceAll("^%", wStr).replaceAll("([^\\\\])_", "$1.").replaceAll("\\\\_", "_").replaceAll("^_", ".");
   }

   protected boolean isAuthV2Enabled() {
      SessionState ss = SessionState.get();
      return ss.isAuthorizationModeV2() && HiveConf.getBoolVar(ss.getConf(), ConfVars.HIVE_AUTHORIZATION_ENABLED);
   }

   protected void authorizeMetaGets(HiveOperationType opType, List inpObjs) throws HiveSQLException {
      this.authorizeMetaGets(opType, inpObjs, (String)null);
   }

   protected void authorizeMetaGets(HiveOperationType opType, List inpObjs, String cmdString) throws HiveSQLException {
      SessionState ss = SessionState.get();
      HiveAuthzContext.Builder ctxBuilder = new HiveAuthzContext.Builder();
      ctxBuilder.setUserIpAddress(ss.getUserIpAddress());
      ctxBuilder.setCommandString(cmdString);

      try {
         ss.getAuthorizerV2().checkPrivileges(opType, inpObjs, (List)null, ctxBuilder.build());
      } catch (HiveAccessControlException | HiveAuthzPluginException e) {
         throw new HiveSQLException(((HiveException)e).getMessage(), e);
      }
   }
}
