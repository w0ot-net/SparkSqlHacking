package org.apache.hadoop.hive.metastore.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginException;
import javax.security.auth.login.AppConfigurationEntry.LoginModuleControlFlag;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hive.thrift.DBTokenStore;
import org.apache.hadoop.hive.thrift.DelegationTokenIdentifier;
import org.apache.hadoop.hive.thrift.DelegationTokenSelector;
import org.apache.hadoop.hive.thrift.MemoryTokenStore;
import org.apache.hadoop.hive.thrift.ZooKeeperTokenStore;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.security.SecurityUtil;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.security.authentication.util.KerberosUtil;
import org.apache.hadoop.security.token.Token;
import org.apache.hadoop.security.token.TokenIdentifier;
import org.apache.hadoop.security.token.TokenSelector;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityUtils {
   private static final Logger LOG = LoggerFactory.getLogger(SecurityUtils.class);
   private static final String DELEGATION_TOKEN_STORE_CLS = "hive.cluster.delegation.token.store.class";

   public static UserGroupInformation getUGI() throws LoginException, IOException {
      String doAs = System.getenv("HADOOP_USER_NAME");
      return doAs != null && doAs.length() > 0 ? UserGroupInformation.createProxyUser(doAs, UserGroupInformation.getLoginUser()) : UserGroupInformation.getCurrentUser();
   }

   public static void setZookeeperClientKerberosJaasConfig(String principal, String keyTabFile) throws IOException {
      String SASL_LOGIN_CONTEXT_NAME = "HiveZooKeeperClient";
      System.setProperty("zookeeper.sasl.clientconfig", "HiveZooKeeperClient");
      principal = SecurityUtil.getServerPrincipal(principal, "0.0.0.0");
      JaasConfiguration jaasConf = new JaasConfiguration("HiveZooKeeperClient", principal, keyTabFile);
      Configuration.setConfiguration(jaasConf);
   }

   public static String getTokenStrForm(String tokenSignature) throws IOException {
      UserGroupInformation ugi = UserGroupInformation.getCurrentUser();
      TokenSelector<? extends TokenIdentifier> tokenSelector = new DelegationTokenSelector();
      Token<? extends TokenIdentifier> token = tokenSelector.selectToken(tokenSignature == null ? new Text() : new Text(tokenSignature), ugi.getTokens());
      return token != null ? token.encodeToUrlString() : null;
   }

   public static void setTokenStr(UserGroupInformation ugi, String tokenStr, String tokenService) throws IOException {
      Token<DelegationTokenIdentifier> delegationToken = createToken(tokenStr, tokenService);
      ugi.addToken(delegationToken);
   }

   private static Token createToken(String tokenStr, String tokenService) throws IOException {
      Token<DelegationTokenIdentifier> delegationToken = new Token();
      delegationToken.decodeFromUrlString(tokenStr);
      delegationToken.setService(new Text(tokenService));
      return delegationToken;
   }

   public static String getTokenStoreClassName(org.apache.hadoop.conf.Configuration conf) {
      String tokenStoreClass = conf.get("hive.cluster.delegation.token.store.class", "");
      if (StringUtils.isBlank(tokenStoreClass)) {
         return MemoryTokenStore.class.getName();
      } else {
         switch (tokenStoreClass) {
            case "org.apache.hadoop.hive.thrift.DBTokenStore":
               return DBTokenStore.class.getName();
            case "org.apache.hadoop.hive.thrift.MemoryTokenStore":
               return MemoryTokenStore.class.getName();
            case "org.apache.hadoop.hive.thrift.ZooKeeperTokenStore":
               return ZooKeeperTokenStore.class.getName();
            default:
               return tokenStoreClass;
         }
      }
   }

   public static String getUser() throws IOException {
      try {
         UserGroupInformation ugi = getUGI();
         return ugi.getUserName();
      } catch (LoginException le) {
         throw new IOException(le);
      }
   }

   public static TServerSocket getServerSocket(String hiveHost, int portNum) throws TTransportException {
      InetSocketAddress serverAddress;
      if (hiveHost != null && !hiveHost.isEmpty()) {
         serverAddress = new InetSocketAddress(hiveHost, portNum);
      } else {
         serverAddress = new InetSocketAddress(portNum);
      }

      return new TServerSocket(serverAddress);
   }

   public static TServerSocket getServerSSLSocket(String hiveHost, int portNum, String keyStorePath, String keyStorePassWord, List sslVersionBlacklist) throws TTransportException, UnknownHostException {
      TSSLTransportFactory.TSSLTransportParameters params = new TSSLTransportFactory.TSSLTransportParameters();
      params.setKeyStore(keyStorePath, keyStorePassWord);
      InetSocketAddress serverAddress;
      if (hiveHost != null && !hiveHost.isEmpty()) {
         serverAddress = new InetSocketAddress(hiveHost, portNum);
      } else {
         serverAddress = new InetSocketAddress(portNum);
      }

      TServerSocket thriftServerSocket = TSSLTransportFactory.getServerSocket(portNum, 0, serverAddress.getAddress(), params);
      if (thriftServerSocket.getServerSocket() instanceof SSLServerSocket) {
         List<String> sslVersionBlacklistLocal = new ArrayList();

         for(String sslVersion : sslVersionBlacklist) {
            sslVersionBlacklistLocal.add(sslVersion.trim().toLowerCase());
         }

         SSLServerSocket sslServerSocket = (SSLServerSocket)thriftServerSocket.getServerSocket();
         List<String> enabledProtocols = new ArrayList();

         for(String protocol : sslServerSocket.getEnabledProtocols()) {
            if (sslVersionBlacklistLocal.contains(protocol.toLowerCase())) {
               LOG.debug("Disabling SSL Protocol: " + protocol);
            } else {
               enabledProtocols.add(protocol);
            }
         }

         sslServerSocket.setEnabledProtocols((String[])enabledProtocols.toArray(new String[0]));
         LOG.info("SSL Server Socket Enabled Protocols: " + Arrays.toString(sslServerSocket.getEnabledProtocols()));
      }

      return thriftServerSocket;
   }

   public static TTransport getSSLSocket(String host, int port, int loginTimeout, String trustStorePath, String trustStorePassWord) throws TTransportException {
      TSSLTransportFactory.TSSLTransportParameters params = new TSSLTransportFactory.TSSLTransportParameters();
      params.setTrustStore(trustStorePath, trustStorePassWord);
      params.requireClientAuth(true);
      TSocket tSSLSocket = TSSLTransportFactory.getClientSocket(host, port, loginTimeout, params);
      return getSSLSocketWithHttps(tSSLSocket);
   }

   private static TSocket getSSLSocketWithHttps(TSocket tSSLSocket) throws TTransportException {
      SSLSocket sslSocket = (SSLSocket)tSSLSocket.getSocket();
      SSLParameters sslParams = sslSocket.getSSLParameters();
      sslParams.setEndpointIdentificationAlgorithm("HTTPS");
      sslSocket.setSSLParameters(sslParams);
      return new TSocket(sslSocket);
   }

   private static class JaasConfiguration extends Configuration {
      private static final boolean IBM_JAVA = System.getProperty("java.vendor").contains("IBM");
      private final Configuration baseConfig = Configuration.getConfiguration();
      private final String loginContextName;
      private final String principal;
      private final String keyTabFile;

      public JaasConfiguration(String hiveLoginContextName, String principal, String keyTabFile) {
         this.loginContextName = hiveLoginContextName;
         this.principal = principal;
         this.keyTabFile = keyTabFile;
      }

      public AppConfigurationEntry[] getAppConfigurationEntry(String appName) {
         if (this.loginContextName.equals(appName)) {
            Map<String, String> krbOptions = new HashMap();
            if (IBM_JAVA) {
               krbOptions.put("credsType", "both");
               krbOptions.put("useKeytab", this.keyTabFile);
            } else {
               krbOptions.put("doNotPrompt", "true");
               krbOptions.put("storeKey", "true");
               krbOptions.put("useKeyTab", "true");
               krbOptions.put("keyTab", this.keyTabFile);
            }

            krbOptions.put("principal", this.principal);
            krbOptions.put("refreshKrb5Config", "true");
            AppConfigurationEntry hiveZooKeeperClientEntry = new AppConfigurationEntry(KerberosUtil.getKrb5LoginModuleName(), LoginModuleControlFlag.REQUIRED, krbOptions);
            return new AppConfigurationEntry[]{hiveZooKeeperClientEntry};
         } else {
            return this.baseConfig != null ? this.baseConfig.getAppConfigurationEntry(appName) : null;
         }
      }
   }
}
