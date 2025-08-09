package org.apache.hive.service.auth;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.security.auth.login.LoginException;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.shims.HadoopShims;
import org.apache.hadoop.hive.shims.ShimLoader;
import org.apache.hadoop.hive.thrift.DBTokenStore;
import org.apache.hadoop.hive.thrift.HadoopThriftAuthBridge;
import org.apache.hadoop.hive.thrift.HiveDelegationTokenManager;
import org.apache.hadoop.hive.thrift.HadoopThriftAuthBridge.Server.ServerMode;
import org.apache.hadoop.security.SecurityUtil;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.security.authorize.ProxyUsers;
import org.apache.hive.service.cli.HiveSQLException;
import org.apache.hive.service.cli.thrift.ThriftCLIService;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.TOKEN.;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;

public class HiveAuthFactory {
   private static final SparkLogger LOG = SparkLoggerFactory.getLogger(HiveAuthFactory.class);
   private HadoopThriftAuthBridge.Server saslServer;
   private String authTypeStr;
   private final String transportMode;
   private final HiveConf conf;
   private HiveDelegationTokenManager delegationTokenManager = null;
   public static final String HS2_PROXY_USER = "hive.server2.proxy.user";
   public static final String HS2_CLIENT_TOKEN = "hiveserver2ClientToken";
   private static Method getKeytab = null;

   public HiveAuthFactory(HiveConf conf) throws TTransportException, IOException {
      this.conf = conf;
      this.transportMode = conf.getVar(ConfVars.HIVE_SERVER2_TRANSPORT_MODE);
      this.authTypeStr = conf.getVar(ConfVars.HIVE_SERVER2_AUTHENTICATION);
      if ("http".equalsIgnoreCase(this.transportMode)) {
         if (this.authTypeStr == null) {
            this.authTypeStr = HiveAuthFactory.AuthTypes.NOSASL.getAuthName();
         }
      } else {
         if (this.authTypeStr == null) {
            this.authTypeStr = HiveAuthFactory.AuthTypes.NONE.getAuthName();
         }

         if (this.authTypeStr.equalsIgnoreCase(HiveAuthFactory.AuthTypes.KERBEROS.getAuthName())) {
            String principal = conf.getVar(ConfVars.HIVE_SERVER2_KERBEROS_PRINCIPAL);
            String keytab = conf.getVar(ConfVars.HIVE_SERVER2_KERBEROS_KEYTAB);
            if (needUgiLogin(UserGroupInformation.getCurrentUser(), SecurityUtil.getServerPrincipal(principal, "0.0.0.0"), keytab)) {
               this.saslServer = ShimLoader.getHadoopThriftAuthBridge().createServer(keytab, principal);
            } else {
               this.saslServer = new HadoopThriftAuthBridge.Server();
            }

            this.delegationTokenManager = new HiveDelegationTokenManager();

            try {
               Object rawStore = null;
               String tokenStoreClass = conf.getVar(ConfVars.METASTORE_CLUSTER_DELEGATION_TOKEN_STORE_CLS);
               if (tokenStoreClass.equals(DBTokenStore.class.getName())) {
                  rawStore = Hive.class;
               }

               this.delegationTokenManager.startDelegationTokenSecretManager(conf, rawStore, ServerMode.HIVESERVER2);
               this.saslServer.setSecretManager(this.delegationTokenManager.getSecretManager());
            } catch (IOException e) {
               throw new TTransportException("Failed to start token manager", e);
            }
         }
      }

   }

   public Map getSaslProperties() {
      Map<String, String> saslProps = new HashMap();
      SaslQOP saslQOP = SaslQOP.fromString(this.conf.getVar(ConfVars.HIVE_SERVER2_THRIFT_SASL_QOP));
      saslProps.put("javax.security.sasl.qop", saslQOP.toString());
      saslProps.put("javax.security.sasl.server.authentication", "true");
      return saslProps;
   }

   public TTransportFactory getAuthTransFactory() throws LoginException {
      TTransportFactory transportFactory;
      if (this.authTypeStr.equalsIgnoreCase(HiveAuthFactory.AuthTypes.KERBEROS.getAuthName())) {
         try {
            transportFactory = this.saslServer.createTransportFactory(this.getSaslProperties());
         } catch (TTransportException e) {
            throw new LoginException(e.getMessage());
         }
      } else if (this.authTypeStr.equalsIgnoreCase(HiveAuthFactory.AuthTypes.NONE.getAuthName())) {
         transportFactory = PlainSaslHelper.getPlainTransportFactory(this.authTypeStr);
      } else if (this.authTypeStr.equalsIgnoreCase(HiveAuthFactory.AuthTypes.LDAP.getAuthName())) {
         transportFactory = PlainSaslHelper.getPlainTransportFactory(this.authTypeStr);
      } else if (this.authTypeStr.equalsIgnoreCase(HiveAuthFactory.AuthTypes.PAM.getAuthName())) {
         transportFactory = PlainSaslHelper.getPlainTransportFactory(this.authTypeStr);
      } else if (this.authTypeStr.equalsIgnoreCase(HiveAuthFactory.AuthTypes.NOSASL.getAuthName())) {
         transportFactory = new TTransportFactory();
      } else {
         if (!this.authTypeStr.equalsIgnoreCase(HiveAuthFactory.AuthTypes.CUSTOM.getAuthName())) {
            throw new LoginException("Unsupported authentication type " + this.authTypeStr);
         }

         transportFactory = PlainSaslHelper.getPlainTransportFactory(this.authTypeStr);
      }

      return transportFactory;
   }

   public TProcessorFactory getAuthProcFactory(ThriftCLIService service) throws LoginException {
      return this.authTypeStr.equalsIgnoreCase(HiveAuthFactory.AuthTypes.KERBEROS.getAuthName()) ? KerberosSaslHelper.getKerberosProcessorFactory(this.saslServer, service) : PlainSaslHelper.getPlainProcessorFactory(service);
   }

   public String getRemoteUser() {
      return this.saslServer == null ? null : this.saslServer.getRemoteUser();
   }

   public String getIpAddress() {
      return this.saslServer != null && this.saslServer.getRemoteAddress() != null ? this.saslServer.getRemoteAddress().getHostAddress() : null;
   }

   public static void loginFromKeytab(HiveConf hiveConf) throws IOException {
      String principal = hiveConf.getVar(ConfVars.HIVE_SERVER2_KERBEROS_PRINCIPAL);
      String keyTabFile = hiveConf.getVar(ConfVars.HIVE_SERVER2_KERBEROS_KEYTAB);
      if (!principal.isEmpty() && !keyTabFile.isEmpty()) {
         UserGroupInformation.loginUserFromKeytab(SecurityUtil.getServerPrincipal(principal, "0.0.0.0"), keyTabFile);
      } else {
         throw new IOException("HiveServer2 Kerberos principal or keytab is not correctly configured");
      }
   }

   public static UserGroupInformation loginFromSpnegoKeytabAndReturnUGI(HiveConf hiveConf) throws IOException {
      String principal = hiveConf.getVar(ConfVars.HIVE_SERVER2_SPNEGO_PRINCIPAL);
      String keyTabFile = hiveConf.getVar(ConfVars.HIVE_SERVER2_SPNEGO_KEYTAB);
      if (!principal.isEmpty() && !keyTabFile.isEmpty()) {
         return UserGroupInformation.loginUserFromKeytabAndReturnUGI(SecurityUtil.getServerPrincipal(principal, "0.0.0.0"), keyTabFile);
      } else {
         throw new IOException("HiveServer2 SPNEGO principal or keytab is not correctly configured");
      }
   }

   public String getDelegationToken(String owner, String renewer, String remoteAddr) throws HiveSQLException {
      if (this.delegationTokenManager == null) {
         throw new HiveSQLException("Delegation token only supported over kerberos authentication", "08S01");
      } else {
         try {
            String tokenStr = this.delegationTokenManager.getDelegationTokenWithService(owner, renewer, "hiveserver2ClientToken", remoteAddr);
            if (tokenStr != null && !tokenStr.isEmpty()) {
               return tokenStr;
            } else {
               throw new HiveSQLException("Received empty retrieving delegation token for user " + owner, "08S01");
            }
         } catch (IOException e) {
            throw new HiveSQLException("Error retrieving delegation token for user " + owner, "08S01", e);
         } catch (InterruptedException e) {
            throw new HiveSQLException("delegation token retrieval interrupted", "08S01", e);
         }
      }
   }

   public void cancelDelegationToken(String delegationToken) throws HiveSQLException {
      if (this.delegationTokenManager == null) {
         throw new HiveSQLException("Delegation token only supported over kerberos authentication", "08S01");
      } else {
         try {
            this.delegationTokenManager.cancelDelegationToken(delegationToken);
         } catch (IOException e) {
            throw new HiveSQLException("Error canceling delegation token " + delegationToken, "08S01", e);
         }
      }
   }

   public void renewDelegationToken(String delegationToken) throws HiveSQLException {
      if (this.delegationTokenManager == null) {
         throw new HiveSQLException("Delegation token only supported over kerberos authentication", "08S01");
      } else {
         try {
            this.delegationTokenManager.renewDelegationToken(delegationToken);
         } catch (IOException e) {
            throw new HiveSQLException("Error renewing delegation token " + delegationToken, "08S01", e);
         }
      }
   }

   public String verifyDelegationToken(String delegationToken) throws HiveSQLException {
      if (this.delegationTokenManager == null) {
         throw new HiveSQLException("Delegation token only supported over kerberos authentication", "08S01");
      } else {
         try {
            return this.delegationTokenManager.verifyDelegationToken(delegationToken);
         } catch (IOException e) {
            String msg = "Error verifying delegation token";
            LOG.error(msg + " {}", e, new MDC[]{MDC.of(.MODULE$, delegationToken)});
            throw new HiveSQLException(msg + delegationToken, "08S01", e);
         }
      }
   }

   public String getUserFromToken(String delegationToken) throws HiveSQLException {
      if (this.delegationTokenManager == null) {
         throw new HiveSQLException("Delegation token only supported over kerberos authentication", "08S01");
      } else {
         try {
            return this.delegationTokenManager.getUserFromToken(delegationToken);
         } catch (IOException e) {
            throw new HiveSQLException("Error extracting user from delegation token " + delegationToken, "08S01", e);
         }
      }
   }

   public static void verifyProxyAccess(String realUser, String proxyUser, String ipAddress, HiveConf hiveConf) throws HiveSQLException {
      try {
         UserGroupInformation sessionUgi;
         if (UserGroupInformation.isSecurityEnabled()) {
            HadoopShims.KerberosNameShim kerbName = ShimLoader.getHadoopShims().getKerberosNameShim(realUser);
            sessionUgi = UserGroupInformation.createProxyUser(kerbName.getServiceName(), UserGroupInformation.getLoginUser());
         } else {
            sessionUgi = UserGroupInformation.createRemoteUser(realUser);
         }

         if (!proxyUser.equalsIgnoreCase(realUser)) {
            ProxyUsers.refreshSuperUserGroupsConfiguration(hiveConf);
            ProxyUsers.authorize(UserGroupInformation.createProxyUser(proxyUser, sessionUgi), ipAddress, hiveConf);
         }

      } catch (IOException e) {
         throw new HiveSQLException("Failed to validate proxy privilege of " + realUser + " for " + proxyUser, "08S01", e);
      }
   }

   public static boolean needUgiLogin(UserGroupInformation ugi, String principal, String keytab) {
      return null == ugi || !ugi.hasKerberosCredentials() || !ugi.getUserName().equals(principal) || !Objects.equals(keytab, getKeytabFromUgi());
   }

   private static String getKeytabFromUgi() {
      synchronized(UserGroupInformation.class) {
         String var10000;
         try {
            if (getKeytab != null) {
               var10000 = (String)getKeytab.invoke(UserGroupInformation.getCurrentUser());
               return var10000;
            }

            var10000 = null;
         } catch (Exception e) {
            LOG.debug("Fail to get keytabFile path via reflection", e);
            return null;
         }

         return var10000;
      }
   }

   static {
      Class<?> clz = UserGroupInformation.class;

      try {
         getKeytab = clz.getDeclaredMethod("getKeytab");
         getKeytab.setAccessible(true);
      } catch (NoSuchMethodException nme) {
         LOG.debug("Cannot find private method \"getKeytab\" in class:" + UserGroupInformation.class.getCanonicalName(), nme);
         getKeytab = null;
      }

   }

   public static enum AuthTypes {
      NOSASL("NOSASL"),
      NONE("NONE"),
      LDAP("LDAP"),
      KERBEROS("KERBEROS"),
      CUSTOM("CUSTOM"),
      PAM("PAM");

      private final String authType;

      private AuthTypes(String authType) {
         this.authType = authType;
      }

      public String getAuthName() {
         return this.authType;
      }

      // $FF: synthetic method
      private static AuthTypes[] $values() {
         return new AuthTypes[]{NOSASL, NONE, LDAP, KERBEROS, CUSTOM, PAM};
      }
   }
}
