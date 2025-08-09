package org.apache.hive.jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.security.sasl.SaslException;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hive.common.auth.HiveAuthUtils;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hive.service.auth.KerberosSaslHelper;
import org.apache.hive.service.auth.PlainSaslHelper;
import org.apache.hive.service.auth.SaslQOP;
import org.apache.hive.service.cli.thrift.EmbeddedThriftBinaryCLIService;
import org.apache.hive.service.rpc.thrift.TCLIService;
import org.apache.hive.service.rpc.thrift.TCancelDelegationTokenReq;
import org.apache.hive.service.rpc.thrift.TCancelDelegationTokenResp;
import org.apache.hive.service.rpc.thrift.TCloseSessionReq;
import org.apache.hive.service.rpc.thrift.TGetDelegationTokenReq;
import org.apache.hive.service.rpc.thrift.TGetDelegationTokenResp;
import org.apache.hive.service.rpc.thrift.TOpenSessionReq;
import org.apache.hive.service.rpc.thrift.TOpenSessionResp;
import org.apache.hive.service.rpc.thrift.TProtocolVersion;
import org.apache.hive.service.rpc.thrift.TRenewDelegationTokenReq;
import org.apache.hive.service.rpc.thrift.TRenewDelegationTokenResp;
import org.apache.hive.service.rpc.thrift.TSessionHandle;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HiveConnection implements Connection {
   public static final Logger LOG = LoggerFactory.getLogger(HiveConnection.class.getName());
   private String jdbcUriString;
   private String host;
   private int port;
   private final Map sessConfMap;
   private Utils.JdbcConnectionParams connParams;
   private final boolean isEmbeddedMode;
   private TTransport transport;
   private boolean assumeSubject;
   private TCLIService.Iface client;
   private boolean isClosed = true;
   private SQLWarning warningChain = null;
   private TSessionHandle sessHandle = null;
   private final List supportedProtocols = new LinkedList();
   private int loginTimeout = 0;
   private TProtocolVersion protocol;
   private int fetchSize = 1000;
   private String initFile = null;

   public HiveConnection(String uri, Properties info) throws SQLException {
      this.setupLoginTimeout();

      try {
         this.connParams = Utils.parseURL(uri, info);
      } catch (ZooKeeperHiveClientException e) {
         throw new SQLException(e);
      }

      this.jdbcUriString = this.connParams.getJdbcUriString();
      this.host = this.connParams.getHost();
      this.port = this.connParams.getPort();
      this.sessConfMap = this.connParams.getSessionVars();
      this.isEmbeddedMode = this.connParams.isEmbeddedMode();
      if (this.sessConfMap.containsKey("fetchSize")) {
         this.fetchSize = Integer.parseInt((String)this.sessConfMap.get("fetchSize"));
      }

      if (this.sessConfMap.containsKey("initFile")) {
         this.initFile = (String)this.sessConfMap.get("initFile");
      }

      this.supportedProtocols.add(TProtocolVersion.HIVE_CLI_SERVICE_PROTOCOL_V1);
      this.supportedProtocols.add(TProtocolVersion.HIVE_CLI_SERVICE_PROTOCOL_V2);
      this.supportedProtocols.add(TProtocolVersion.HIVE_CLI_SERVICE_PROTOCOL_V3);
      this.supportedProtocols.add(TProtocolVersion.HIVE_CLI_SERVICE_PROTOCOL_V4);
      this.supportedProtocols.add(TProtocolVersion.HIVE_CLI_SERVICE_PROTOCOL_V5);
      this.supportedProtocols.add(TProtocolVersion.HIVE_CLI_SERVICE_PROTOCOL_V6);
      this.supportedProtocols.add(TProtocolVersion.HIVE_CLI_SERVICE_PROTOCOL_V7);
      this.supportedProtocols.add(TProtocolVersion.HIVE_CLI_SERVICE_PROTOCOL_V8);
      this.supportedProtocols.add(TProtocolVersion.HIVE_CLI_SERVICE_PROTOCOL_V9);
      this.supportedProtocols.add(TProtocolVersion.HIVE_CLI_SERVICE_PROTOCOL_V10);
      if (this.isEmbeddedMode) {
         EmbeddedThriftBinaryCLIService embeddedClient = new EmbeddedThriftBinaryCLIService();
         embeddedClient.init((HiveConf)null);
         this.client = embeddedClient;
         this.openSession();
         this.executeInitSql();
      } else {
         int maxRetries = 1;

         try {
            String strRetries = (String)this.sessConfMap.get("retries");
            if (StringUtils.isNotBlank(strRetries)) {
               maxRetries = Integer.parseInt(strRetries);
            }
         } catch (NumberFormatException var8) {
         }

         int numRetries = 0;

         while(true) {
            try {
               this.openTransport();
               this.client = new TCLIService.Client(new TBinaryProtocol(this.transport));
               this.openSession();
               this.executeInitSql();
               break;
            } catch (Exception e) {
               LOG.warn("Failed to connect to " + this.connParams.getHost() + ":" + this.connParams.getPort());
               String errMsg = null;
               String warnMsg = "Could not open client transport with JDBC Uri: " + this.jdbcUriString + ": ";
               if (this.isZkDynamicDiscoveryMode()) {
                  errMsg = "Could not open client transport for any of the Server URI's in ZooKeeper: ";

                  while(!Utils.updateConnParamsFromZooKeeper(this.connParams)) {
                     ++numRetries;
                     if (numRetries >= maxRetries) {
                        break;
                     }

                     this.connParams.getRejectedHostZnodePaths().clear();
                  }

                  this.jdbcUriString = this.connParams.getJdbcUriString();
                  this.host = this.connParams.getHost();
                  this.port = this.connParams.getPort();
               } else {
                  errMsg = warnMsg;
                  ++numRetries;
               }

               if (numRetries >= maxRetries) {
                  throw new SQLException(errMsg + e.getMessage(), " 08S01", e);
               }

               LOG.warn(warnMsg + e.getMessage() + " Retrying " + numRetries + " of " + maxRetries);
            }
         }
      }

      this.client = newSynchronizedClient(this.client);
   }

   private void executeInitSql() throws SQLException {
      if (this.initFile != null) {
         try {
            List<String> sqlList = parseInitFile(this.initFile);
            Statement st = this.createStatement();

            for(String sql : sqlList) {
               boolean hasResult = st.execute(sql);
               if (hasResult) {
                  ResultSet rs = st.getResultSet();

                  while(rs.next()) {
                     System.out.println(rs.getString(1));
                  }
               }
            }
         } catch (Exception e) {
            LOG.error("Failed to execute initial SQL");
            throw new SQLException(e.getMessage());
         }
      }

   }

   public static List parseInitFile(String initFile) throws IOException {
      File file = new File(initFile);
      BufferedReader br = null;
      List<String> initSqlList = null;

      try {
         FileInputStream input = new FileInputStream(file);
         br = new BufferedReader(new InputStreamReader(input, "UTF-8"));
         StringBuilder sb = new StringBuilder("");

         String line;
         while((line = br.readLine()) != null) {
            line = line.trim();
            if (line.length() != 0 && !line.startsWith("#") && !line.startsWith("--")) {
               line = line.concat(" ");
               sb.append(line);
            }
         }

         initSqlList = getInitSql(sb.toString());
      } catch (IOException e) {
         LOG.error("Failed to read initial SQL file", e);
         throw new IOException(e);
      } finally {
         if (br != null) {
            br.close();
         }

      }

      return initSqlList;
   }

   private static List getInitSql(String sbLine) {
      char[] sqlArray = sbLine.toCharArray();
      List<String> initSqlList = new ArrayList();
      int index = 0;

      for(int beginIndex = 0; index < sqlArray.length; ++index) {
         if (sqlArray[index] == ';') {
            String sql = sbLine.substring(beginIndex, index).trim();
            initSqlList.add(sql);
            beginIndex = index + 1;
         }
      }

      return initSqlList;
   }

   private void openTransport() throws Exception {
      this.assumeSubject = "fromSubject".equals(this.sessConfMap.get("kerberosAuthType"));
      this.transport = this.isHttpTransportMode() ? this.createHttpTransport() : this.createBinaryTransport();
      if (!this.transport.isOpen()) {
         this.transport.open();
         this.logZkDiscoveryMessage("Connected to " + this.connParams.getHost() + ":" + this.connParams.getPort());
      }

   }

   public String getConnectedUrl() {
      return this.jdbcUriString;
   }

   private String getServerHttpUrl(boolean useSsl) {
      String schemeName = useSsl ? "https" : "http";
      String httpPath = (String)this.sessConfMap.get("httpPath");
      if (httpPath == null) {
         httpPath = "/";
      } else if (!httpPath.startsWith("/")) {
         httpPath = "/" + httpPath;
      }

      return schemeName + "://" + this.host + ":" + this.port + httpPath;
   }

   private TTransport createHttpTransport() throws SQLException, TTransportException {
      boolean useSsl = this.isSslConnection();
      CloseableHttpClient httpClient = this.getHttpClient(useSsl);
      this.transport = new THttpClient(this.getServerHttpUrl(useSsl), httpClient);
      return this.transport;
   }

   private CloseableHttpClient getHttpClient(Boolean useSsl) throws SQLException {
      boolean isCookieEnabled = this.sessConfMap.get("cookieAuth") == null || !"false".equalsIgnoreCase((String)this.sessConfMap.get("cookieAuth"));
      String cookieName = this.sessConfMap.get("cookieName") == null ? "hive.server2.auth" : (String)this.sessConfMap.get("cookieName");
      CookieStore cookieStore = isCookieEnabled ? new BasicCookieStore() : null;
      Map<String, String> additionalHttpHeaders = new HashMap();

      for(Map.Entry entry : this.sessConfMap.entrySet()) {
         String key = (String)entry.getKey();
         if (key.startsWith("http.header.")) {
            additionalHttpHeaders.put(key.substring("http.header.".length()), entry.getValue());
         }
      }

      HttpRequestInterceptor requestInterceptor;
      if (this.isKerberosAuthMode()) {
         requestInterceptor = new HttpKerberosRequestInterceptor((String)this.sessConfMap.get("principal"), this.host, this.getServerHttpUrl(useSsl), this.assumeSubject, cookieStore, cookieName, useSsl, additionalHttpHeaders);
      } else {
         String tokenStr = this.getClientDelegationToken(this.sessConfMap);
         if (tokenStr != null) {
            requestInterceptor = new HttpTokenAuthInterceptor(tokenStr, cookieStore, cookieName, useSsl, additionalHttpHeaders);
         } else {
            requestInterceptor = new HttpBasicAuthInterceptor(this.getUserName(), this.getPassword(), cookieStore, cookieName, useSsl, additionalHttpHeaders);
         }
      }

      HttpClientBuilder httpClientBuilder;
      if (isCookieEnabled) {
         httpClientBuilder = HttpClients.custom().setServiceUnavailableRetryStrategy(new ServiceUnavailableRetryStrategy() {
            public boolean retryRequest(HttpResponse response, int executionCount, HttpContext context) {
               int statusCode = response.getStatusLine().getStatusCode();
               boolean ret = statusCode == 401 && executionCount <= 1;
               if (ret) {
                  context.setAttribute("hive.server2.retryserver", "true");
               }

               return ret;
            }

            public long getRetryInterval() {
               return 0L;
            }
         });
      } else {
         httpClientBuilder = HttpClientBuilder.create();
      }

      httpClientBuilder.setRetryHandler(new HttpRequestRetryHandler() {
         public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
            if (executionCount > 1) {
               HiveConnection.LOG.info("Retry attempts to connect to server exceeded.");
               return false;
            } else if (exception instanceof NoHttpResponseException) {
               HiveConnection.LOG.info("Could not connect to the server. Retrying one more time.");
               return true;
            } else {
               return false;
            }
         }
      });
      httpClientBuilder.addInterceptorFirst(requestInterceptor);
      httpClientBuilder.addInterceptorLast(new XsrfHttpRequestInterceptor());
      if (useSsl) {
         String useTwoWaySSL = (String)this.sessConfMap.get("twoWay");
         String sslTrustStorePath = (String)this.sessConfMap.get("sslTrustStore");
         String sslTrustStorePassword = (String)this.sessConfMap.get("trustStorePassword");

         try {
            SSLConnectionSocketFactory socketFactory;
            if (useTwoWaySSL != null && useTwoWaySSL.equalsIgnoreCase("true")) {
               socketFactory = this.getTwoWaySSLSocketFactory();
            } else if (sslTrustStorePath != null && !sslTrustStorePath.isEmpty()) {
               KeyStore sslTrustStore = KeyStore.getInstance("JKS");
               FileInputStream fis = new FileInputStream(sslTrustStorePath);
               Throwable var33 = null;

               try {
                  sslTrustStore.load(fis, sslTrustStorePassword.toCharArray());
               } catch (Throwable var25) {
                  var33 = var25;
                  throw var25;
               } finally {
                  if (fis != null) {
                     if (var33 != null) {
                        try {
                           fis.close();
                        } catch (Throwable var24) {
                           var33.addSuppressed(var24);
                        }
                     } else {
                        fis.close();
                     }
                  }

               }

               SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(sslTrustStore, (TrustStrategy)null).build();
               socketFactory = new SSLConnectionSocketFactory(sslContext, new DefaultHostnameVerifier((PublicSuffixMatcher)null));
            } else {
               socketFactory = SSLConnectionSocketFactory.getSocketFactory();
            }

            Registry<ConnectionSocketFactory> registry = RegistryBuilder.create().register("https", socketFactory).build();
            httpClientBuilder.setConnectionManager(new BasicHttpClientConnectionManager(registry));
         } catch (Exception e) {
            String msg = "Could not create an https connection to " + this.jdbcUriString + ". " + e.getMessage();
            throw new SQLException(msg, " 08S01", e);
         }
      }

      return httpClientBuilder.build();
   }

   private TTransport createUnderlyingTransport() throws TTransportException {
      TTransport transport = null;
      if (this.isSslConnection()) {
         String sslTrustStore = (String)this.sessConfMap.get("sslTrustStore");
         String sslTrustStorePassword = (String)this.sessConfMap.get("trustStorePassword");
         if (sslTrustStore != null && !sslTrustStore.isEmpty()) {
            transport = HiveAuthUtils.getSSLSocket(this.host, this.port, this.loginTimeout, sslTrustStore, sslTrustStorePassword);
         } else {
            transport = HiveAuthUtils.getSSLSocket(this.host, this.port, this.loginTimeout);
         }
      } else {
         transport = HiveAuthUtils.getSocketTransport(this.host, this.port, this.loginTimeout);
      }

      return transport;
   }

   private TTransport createBinaryTransport() throws SQLException, TTransportException {
      try {
         TTransport socketTransport = this.createUnderlyingTransport();
         if (!"noSasl".equals(this.sessConfMap.get("auth"))) {
            Map<String, String> saslProps = new HashMap();
            SaslQOP saslQOP = SaslQOP.AUTH;
            if (this.sessConfMap.containsKey("saslQop")) {
               try {
                  saslQOP = SaslQOP.fromString((String)this.sessConfMap.get("saslQop"));
               } catch (IllegalArgumentException e) {
                  throw new SQLException("Invalid saslQop parameter. " + e.getMessage(), "42000", e);
               }

               saslProps.put("javax.security.sasl.qop", saslQOP.toString());
            } else {
               saslProps.put("javax.security.sasl.qop", "auth-conf,auth-int,auth");
            }

            saslProps.put("javax.security.sasl.server.authentication", "true");
            if (this.sessConfMap.containsKey("principal")) {
               this.transport = KerberosSaslHelper.getKerberosTransport((String)this.sessConfMap.get("principal"), this.host, socketTransport, saslProps, this.assumeSubject);
            } else {
               String tokenStr = this.getClientDelegationToken(this.sessConfMap);
               if (tokenStr != null) {
                  this.transport = KerberosSaslHelper.getTokenTransport(tokenStr, this.host, socketTransport, saslProps);
               } else {
                  String userName = this.getUserName();
                  String passwd = this.getPassword();
                  this.transport = PlainSaslHelper.getPlainTransport(userName, passwd, socketTransport);
               }
            }
         } else {
            this.transport = socketTransport;
         }
      } catch (SaslException e) {
         throw new SQLException("Could not create secure connection to " + this.jdbcUriString + ": " + e.getMessage(), " 08S01", e);
      }

      return this.transport;
   }

   SSLConnectionSocketFactory getTwoWaySSLSocketFactory() throws SQLException {
      SSLConnectionSocketFactory socketFactory = null;

      try {
         KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509", "SunJSSE");
         String keyStorePath = (String)this.sessConfMap.get("sslKeyStore");
         String keyStorePassword = (String)this.sessConfMap.get("keyStorePassword");
         KeyStore sslKeyStore = KeyStore.getInstance("JKS");
         if (keyStorePath != null && !keyStorePath.isEmpty()) {
            FileInputStream fis = new FileInputStream(keyStorePath);
            Throwable var7 = null;

            try {
               sslKeyStore.load(fis, keyStorePassword.toCharArray());
            } catch (Throwable var35) {
               var7 = var35;
               throw var35;
            } finally {
               if (fis != null) {
                  if (var7 != null) {
                     try {
                        fis.close();
                     } catch (Throwable var33) {
                        var7.addSuppressed(var33);
                     }
                  } else {
                     fis.close();
                  }
               }

            }

            keyManagerFactory.init(sslKeyStore, keyStorePassword.toCharArray());
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
            String trustStorePath = (String)this.sessConfMap.get("sslTrustStore");
            String trustStorePassword = (String)this.sessConfMap.get("trustStorePassword");
            KeyStore sslTrustStore = KeyStore.getInstance("JKS");
            if (trustStorePath != null && !trustStorePath.isEmpty()) {
               FileInputStream fis = new FileInputStream(trustStorePath);
               Throwable var11 = null;

               try {
                  sslTrustStore.load(fis, trustStorePassword.toCharArray());
               } catch (Throwable var34) {
                  var11 = var34;
                  throw var34;
               } finally {
                  if (fis != null) {
                     if (var11 != null) {
                        try {
                           fis.close();
                        } catch (Throwable var32) {
                           var11.addSuppressed(var32);
                        }
                     } else {
                        fis.close();
                     }
                  }

               }

               trustManagerFactory.init(sslTrustStore);
               SSLContext context = SSLContext.getInstance("TLS");
               context.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
               socketFactory = new SSLConnectionSocketFactory(context);
               return socketFactory;
            } else {
               throw new IllegalArgumentException("sslTrustStore Not configured for 2 way SSL connection");
            }
         } else {
            throw new IllegalArgumentException("sslKeyStore Not configured for 2 way SSL connection, keyStorePath param is empty");
         }
      } catch (Exception e) {
         throw new SQLException("Error while initializing 2 way ssl socket factory ", e);
      }
   }

   private String getClientDelegationToken(Map jdbcConnConf) throws SQLException {
      String tokenStr = null;
      if ("delegationToken".equalsIgnoreCase((String)jdbcConnConf.get("auth"))) {
         try {
            tokenStr = org.apache.hadoop.hive.shims.Utils.getTokenStrForm("hiveserver2ClientToken");
         } catch (IOException e) {
            throw new SQLException("Error reading token ", e);
         }
      }

      return tokenStr;
   }

   private void openSession() throws SQLException {
      TOpenSessionReq openReq = new TOpenSessionReq();
      Map<String, String> openConf = new HashMap();

      for(Map.Entry hiveConf : this.connParams.getHiveConfs().entrySet()) {
         openConf.put("set:hiveconf:" + (String)hiveConf.getKey(), hiveConf.getValue());
      }

      for(Map.Entry hiveVar : this.connParams.getHiveVars().entrySet()) {
         openConf.put("set:hivevar:" + (String)hiveVar.getKey(), hiveVar.getValue());
      }

      openConf.put("use:database", this.connParams.getDbName());
      openConf.put("set:hiveconf:hive.server2.thrift.resultset.default.fetch.size", Integer.toString(this.fetchSize));
      Map<String, String> sessVars = this.connParams.getSessionVars();
      if (sessVars.containsKey("hive.server2.proxy.user")) {
         openConf.put("hive.server2.proxy.user", sessVars.get("hive.server2.proxy.user"));
      }

      openReq.setConfiguration(openConf);
      if ("noSasl".equals(this.sessConfMap.get("auth"))) {
         openReq.setUsername((String)this.sessConfMap.get("user"));
         openReq.setPassword((String)this.sessConfMap.get("password"));
      }

      try {
         TOpenSessionResp openResp = this.client.OpenSession(openReq);
         Utils.verifySuccess(openResp.getStatus());
         if (!this.supportedProtocols.contains(openResp.getServerProtocolVersion())) {
            throw new TException("Unsupported Hive2 protocol");
         }

         this.protocol = openResp.getServerProtocolVersion();
         this.sessHandle = openResp.getSessionHandle();
         String serverFetchSize = (String)openResp.getConfiguration().get("hive.server2.thrift.resultset.default.fetch.size");
         if (serverFetchSize != null) {
            this.fetchSize = Integer.parseInt(serverFetchSize);
         }
      } catch (TException e) {
         LOG.error("Error opening session", e);
         throw new SQLException("Could not establish connection to " + this.jdbcUriString + ": " + e.getMessage(), " 08S01", e);
      }

      this.isClosed = false;
   }

   private String getUserName() {
      return this.getSessionValue("user", "anonymous");
   }

   private String getPassword() {
      return this.getSessionValue("password", "anonymous");
   }

   private boolean isSslConnection() {
      return "true".equalsIgnoreCase((String)this.sessConfMap.get("ssl"));
   }

   private boolean isKerberosAuthMode() {
      return !"noSasl".equals(this.sessConfMap.get("auth")) && this.sessConfMap.containsKey("principal");
   }

   private boolean isHttpTransportMode() {
      String transportMode = (String)this.sessConfMap.get("transportMode");
      return transportMode != null && transportMode.equalsIgnoreCase("http");
   }

   private boolean isZkDynamicDiscoveryMode() {
      return this.sessConfMap.get("serviceDiscoveryMode") != null && "zooKeeper".equalsIgnoreCase((String)this.sessConfMap.get("serviceDiscoveryMode"));
   }

   private void logZkDiscoveryMessage(String message) {
      if (this.isZkDynamicDiscoveryMode()) {
         LOG.info(message);
      }

   }

   private String getSessionValue(String varName, String varDefault) {
      String varValue = (String)this.sessConfMap.get(varName);
      if (varValue == null || varValue.isEmpty()) {
         varValue = varDefault;
      }

      return varValue;
   }

   private void setupLoginTimeout() {
      long timeOut = TimeUnit.SECONDS.toMillis((long)DriverManager.getLoginTimeout());
      if (timeOut > 2147483647L) {
         this.loginTimeout = Integer.MAX_VALUE;
      } else {
         this.loginTimeout = (int)timeOut;
      }

   }

   public void abort(Executor executor) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public String getDelegationToken(String owner, String renewer) throws SQLException {
      TGetDelegationTokenReq req = new TGetDelegationTokenReq(this.sessHandle, owner, renewer);

      try {
         TGetDelegationTokenResp tokenResp = this.client.GetDelegationToken(req);
         Utils.verifySuccess(tokenResp.getStatus());
         return tokenResp.getDelegationToken();
      } catch (TException e) {
         throw new SQLException("Could not retrieve token: " + e.getMessage(), " 08S01", e);
      }
   }

   public void cancelDelegationToken(String tokenStr) throws SQLException {
      TCancelDelegationTokenReq cancelReq = new TCancelDelegationTokenReq(this.sessHandle, tokenStr);

      try {
         TCancelDelegationTokenResp cancelResp = this.client.CancelDelegationToken(cancelReq);
         Utils.verifySuccess(cancelResp.getStatus());
      } catch (TException e) {
         throw new SQLException("Could not cancel token: " + e.getMessage(), " 08S01", e);
      }
   }

   public void renewDelegationToken(String tokenStr) throws SQLException {
      TRenewDelegationTokenReq cancelReq = new TRenewDelegationTokenReq(this.sessHandle, tokenStr);

      try {
         TRenewDelegationTokenResp renewResp = this.client.RenewDelegationToken(cancelReq);
         Utils.verifySuccess(renewResp.getStatus());
      } catch (TException e) {
         throw new SQLException("Could not renew token: " + e.getMessage(), " 08S01", e);
      }
   }

   public void clearWarnings() throws SQLException {
      this.warningChain = null;
   }

   public void close() throws SQLException {
      if (!this.isClosed) {
         TCloseSessionReq closeReq = new TCloseSessionReq(this.sessHandle);

         try {
            this.client.CloseSession(closeReq);
         } catch (TException e) {
            throw new SQLException("Error while cleaning up the server resources", e);
         } finally {
            this.isClosed = true;
            if (this.transport != null) {
               this.transport.close();
            }

         }
      }

   }

   public void commit() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Array createArrayOf(String arg0, Object[] arg1) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Blob createBlob() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Clob createClob() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public NClob createNClob() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public SQLXML createSQLXML() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Statement createStatement() throws SQLException {
      if (this.isClosed) {
         throw new SQLException("Can't create Statement, connection is closed");
      } else {
         return new HiveStatement(this, this.client, this.sessHandle, this.fetchSize);
      }
   }

   public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
      if (resultSetConcurrency != 1007) {
         throw new SQLException("Statement with resultset concurrency " + resultSetConcurrency + " is not supported", "HYC00");
      } else if (resultSetType == 1005) {
         throw new SQLException("Statement with resultset type " + resultSetType + " is not supported", "HYC00");
      } else {
         return new HiveStatement(this, this.client, this.sessHandle, resultSetType == 1004, this.fetchSize);
      }
   }

   public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean getAutoCommit() throws SQLException {
      return true;
   }

   public String getCatalog() throws SQLException {
      return "";
   }

   public Properties getClientInfo() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public String getClientInfo(String name) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getHoldability() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public DatabaseMetaData getMetaData() throws SQLException {
      if (this.isClosed) {
         throw new SQLException("Connection is closed");
      } else {
         return new HiveDatabaseMetaData(this, this.client, this.sessHandle);
      }
   }

   public int getNetworkTimeout() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public String getSchema() throws SQLException {
      if (this.isClosed) {
         throw new SQLException("Connection is closed");
      } else {
         Statement stmt = this.createStatement();
         Throwable var2 = null;

         Object var5;
         try {
            ResultSet res = stmt.executeQuery("SELECT current_database()");
            Throwable var4 = null;

            try {
               if (!res.next()) {
                  throw new SQLException("Failed to get schema information");
               }

               var5 = res.getString(1);
            } catch (Throwable var28) {
               var5 = var28;
               var4 = var28;
               throw var28;
            } finally {
               if (res != null) {
                  if (var4 != null) {
                     try {
                        res.close();
                     } catch (Throwable var27) {
                        var4.addSuppressed(var27);
                     }
                  } else {
                     res.close();
                  }
               }

            }
         } catch (Throwable var30) {
            var2 = var30;
            throw var30;
         } finally {
            if (stmt != null) {
               if (var2 != null) {
                  try {
                     stmt.close();
                  } catch (Throwable var26) {
                     var2.addSuppressed(var26);
                  }
               } else {
                  stmt.close();
               }
            }

         }

         return (String)var5;
      }
   }

   public int getTransactionIsolation() throws SQLException {
      return 0;
   }

   public Map getTypeMap() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public SQLWarning getWarnings() throws SQLException {
      return this.warningChain;
   }

   public boolean isClosed() throws SQLException {
      return this.isClosed;
   }

   public boolean isReadOnly() throws SQLException {
      return false;
   }

   public boolean isValid(int timeout) throws SQLException {
      if (timeout < 0) {
         throw new SQLException("timeout value was negative");
      } else {
         boolean rc = false;

         try {
            String productName = (new HiveDatabaseMetaData(this, this.client, this.sessHandle)).getDatabaseProductName();
            rc = true;
         } catch (SQLException var4) {
         }

         return rc;
      }
   }

   public String nativeSQL(String sql) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public CallableStatement prepareCall(String sql) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public PreparedStatement prepareStatement(String sql) throws SQLException {
      return new HivePreparedStatement(this, this.client, this.sessHandle, sql);
   }

   public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
      return new HivePreparedStatement(this, this.client, this.sessHandle, sql);
   }

   public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
      return new HivePreparedStatement(this, this.client, this.sessHandle, sql);
   }

   public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void releaseSavepoint(Savepoint savepoint) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void rollback() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void rollback(Savepoint savepoint) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setAutoCommit(boolean autoCommit) throws SQLException {
      if (this.isClosed) {
         throw new SQLException("Connection is closed");
      } else {
         if (!autoCommit) {
            LOG.warn("Request to set autoCommit to false; Hive does not support autoCommit=false.");
            SQLWarning warning = new SQLWarning("Hive does not support autoCommit=false");
            if (this.warningChain == null) {
               this.warningChain = warning;
            } else {
               this.warningChain.setNextWarning(warning);
            }
         }

      }
   }

   public void setCatalog(String catalog) throws SQLException {
      if (this.isClosed) {
         throw new SQLException("Connection is closed");
      }
   }

   public void setClientInfo(Properties properties) throws SQLClientInfoException {
      throw new SQLClientInfoException("Method not supported", (Map)null);
   }

   public void setClientInfo(String name, String value) throws SQLClientInfoException {
      throw new SQLClientInfoException("Method not supported", (Map)null);
   }

   public void setHoldability(int holdability) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setReadOnly(boolean readOnly) throws SQLException {
      if (this.isClosed) {
         throw new SQLException("Connection is closed");
      } else if (readOnly) {
         throw new SQLException("Enabling read-only mode not supported");
      }
   }

   public Savepoint setSavepoint() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Savepoint setSavepoint(String name) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setSchema(String schema) throws SQLException {
      if (this.isClosed) {
         throw new SQLException("Connection is closed");
      } else if (schema != null && !schema.isEmpty()) {
         Statement stmt = this.createStatement();
         stmt.execute("use " + schema);
         stmt.close();
      } else {
         throw new SQLException("Schema name is null or empty");
      }
   }

   public void setTransactionIsolation(int level) throws SQLException {
   }

   public void setTypeMap(Map map) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean isWrapperFor(Class iface) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Object unwrap(Class iface) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public TProtocolVersion getProtocol() {
      return this.protocol;
   }

   public static TCLIService.Iface newSynchronizedClient(TCLIService.Iface client) {
      return (TCLIService.Iface)Proxy.newProxyInstance(HiveConnection.class.getClassLoader(), new Class[]{TCLIService.Iface.class}, new SynchronizedHandler(client));
   }

   private static class SynchronizedHandler implements InvocationHandler {
      private final TCLIService.Iface client;
      private final ReentrantLock lock = new ReentrantLock(true);

      SynchronizedHandler(TCLIService.Iface client) {
         this.client = client;
      }

      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
         Object var4;
         try {
            this.lock.lock();
            var4 = method.invoke(this.client, args);
         } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof TException) {
               throw (TException)e.getTargetException();
            }

            throw new TException("Error in calling method " + method.getName(), e.getTargetException());
         } catch (Exception e) {
            throw new TException("Error in calling method " + method.getName(), e);
         } finally {
            this.lock.unlock();
         }

         return var4;
      }
   }
}
