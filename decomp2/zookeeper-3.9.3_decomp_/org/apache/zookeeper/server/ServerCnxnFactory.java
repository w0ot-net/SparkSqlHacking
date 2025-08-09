package org.apache.zookeeper.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import javax.management.JMException;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginException;
import org.apache.zookeeper.Login;
import org.apache.zookeeper.common.ZKConfig;
import org.apache.zookeeper.jmx.MBeanRegistry;
import org.apache.zookeeper.server.auth.SaslServerCallbackHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ServerCnxnFactory {
   public static final String ZOOKEEPER_SERVER_CNXN_FACTORY = "zookeeper.serverCnxnFactory";
   private static final String ZOOKEEPER_MAX_CONNECTION = "zookeeper.maxCnxns";
   private static final String DIGEST_MD5_USER_PREFIX = "user_";
   public static final int ZOOKEEPER_MAX_CONNECTION_DEFAULT = 0;
   private static final Logger LOG = LoggerFactory.getLogger(ServerCnxnFactory.class);
   protected boolean secure;
   static final ByteBuffer closeConn = ByteBuffer.allocate(0);
   protected int maxCnxns;
   final ConcurrentHashMap sessionMap = new ConcurrentHashMap();
   private static String loginUser;
   public Login login;
   protected ZooKeeperServer zkServer;
   private final ConcurrentHashMap connectionBeans = new ConcurrentHashMap();
   protected final Set cnxns = Collections.newSetFromMap(new ConcurrentHashMap());

   public void addSession(long sessionId, ServerCnxn cnxn) {
      this.sessionMap.put(sessionId, cnxn);
   }

   public void removeCnxnFromSessionMap(ServerCnxn cnxn) {
      long sessionId = cnxn.getSessionId();
      if (sessionId != 0L) {
         this.sessionMap.remove(sessionId);
      }

   }

   public boolean closeSession(long sessionId, ServerCnxn.DisconnectReason reason) {
      ServerCnxn cnxn = (ServerCnxn)this.sessionMap.remove(sessionId);
      if (cnxn != null) {
         try {
            cnxn.close(reason);
         } catch (Exception e) {
            LOG.warn("exception during session close", e);
         }

         return true;
      } else {
         return false;
      }
   }

   public abstract int getLocalPort();

   public abstract Iterable getConnections();

   public int getNumAliveConnections() {
      return this.cnxns.size();
   }

   public final ZooKeeperServer getZooKeeperServer() {
      return this.zkServer;
   }

   public void configure(InetSocketAddress addr, int maxcc) throws IOException {
      this.configure(addr, maxcc, -1);
   }

   public void configure(InetSocketAddress addr, int maxcc, int backlog) throws IOException {
      this.configure(addr, maxcc, backlog, false);
   }

   public abstract void configure(InetSocketAddress var1, int var2, int var3, boolean var4) throws IOException;

   public abstract void reconfigure(InetSocketAddress var1);

   public abstract int getMaxClientCnxnsPerHost();

   public abstract void setMaxClientCnxnsPerHost(int var1);

   public boolean isSecure() {
      return this.secure;
   }

   public void startup(ZooKeeperServer zkServer) throws IOException, InterruptedException {
      this.startup(zkServer, true);
   }

   public abstract void startup(ZooKeeperServer var1, boolean var2) throws IOException, InterruptedException;

   public abstract int getSocketListenBacklog();

   public abstract void join() throws InterruptedException;

   public abstract void shutdown();

   public abstract void start();

   public final void setZooKeeperServer(ZooKeeperServer zks) {
      this.zkServer = zks;
      if (zks != null) {
         if (this.secure) {
            zks.setSecureServerCnxnFactory(this);
         } else {
            zks.setServerCnxnFactory(this);
         }
      }

   }

   public abstract void closeAll(ServerCnxn.DisconnectReason var1);

   public static ServerCnxnFactory createFactory() throws IOException {
      String serverCnxnFactoryName = System.getProperty("zookeeper.serverCnxnFactory");
      if (serverCnxnFactoryName == null) {
         serverCnxnFactoryName = NIOServerCnxnFactory.class.getName();
      }

      try {
         ServerCnxnFactory serverCnxnFactory = (ServerCnxnFactory)Class.forName(serverCnxnFactoryName).getDeclaredConstructor().newInstance();
         LOG.info("Using {} as server connection factory", serverCnxnFactoryName);
         return serverCnxnFactory;
      } catch (Exception e) {
         IOException ioe = new IOException("Couldn't instantiate " + serverCnxnFactoryName, e);
         throw ioe;
      }
   }

   public static ServerCnxnFactory createFactory(int clientPort, int maxClientCnxns) throws IOException {
      return createFactory(new InetSocketAddress(clientPort), maxClientCnxns, -1);
   }

   public static ServerCnxnFactory createFactory(int clientPort, int maxClientCnxns, int backlog) throws IOException {
      return createFactory(new InetSocketAddress(clientPort), maxClientCnxns, backlog);
   }

   public static ServerCnxnFactory createFactory(InetSocketAddress addr, int maxClientCnxns) throws IOException {
      return createFactory(addr, maxClientCnxns, -1);
   }

   public static ServerCnxnFactory createFactory(InetSocketAddress addr, int maxClientCnxns, int backlog) throws IOException {
      ServerCnxnFactory factory = createFactory();
      factory.configure(addr, maxClientCnxns, backlog);
      return factory;
   }

   public abstract InetSocketAddress getLocalAddress();

   public abstract void resetAllConnectionStats();

   public abstract Iterable getAllConnectionInfo(boolean var1);

   public void unregisterConnection(ServerCnxn serverCnxn) {
      ConnectionBean jmxConnectionBean = (ConnectionBean)this.connectionBeans.remove(serverCnxn);
      if (jmxConnectionBean != null) {
         MBeanRegistry.getInstance().unregister(jmxConnectionBean);
      }

   }

   public void registerConnection(ServerCnxn serverCnxn) {
      if (this.zkServer != null) {
         ConnectionBean jmxConnectionBean = new ConnectionBean(serverCnxn, this.zkServer);

         try {
            MBeanRegistry.getInstance().register(jmxConnectionBean, this.zkServer.jmxServerBean);
            this.connectionBeans.put(serverCnxn, jmxConnectionBean);
         } catch (JMException e) {
            LOG.warn("Could not register connection", e);
         }
      }

   }

   protected void configureSaslLogin() throws IOException {
      String serverSection = System.getProperty("zookeeper.sasl.serverconfig", "Server");
      AppConfigurationEntry[] entries = null;
      SecurityException securityException = null;

      try {
         entries = Configuration.getConfiguration().getAppConfigurationEntry(serverSection);
      } catch (SecurityException e) {
         securityException = e;
      }

      if (entries != null) {
         try {
            Map<String, String> credentials = getDigestMd5Credentials(entries);
            Supplier<CallbackHandler> callbackHandlerSupplier = () -> new SaslServerCallbackHandler(credentials);
            this.login = new Login(serverSection, callbackHandlerSupplier, new ZKConfig());
            setLoginUser(this.login.getUserName());
            this.login.startThreadIfNeeded();
         } catch (LoginException e) {
            throw new IOException("Could not configure server because SASL configuration did not allow the  ZooKeeper server to authenticate itself properly: " + e);
         }
      } else {
         String jaasFile = System.getProperty("java.security.auth.login.config");
         String loginContextName = System.getProperty("zookeeper.sasl.serverconfig");
         if (securityException != null && (loginContextName != null || jaasFile != null)) {
            String errorMessage = "No JAAS configuration section named '" + serverSection + "' was found";
            if (jaasFile != null) {
               errorMessage = errorMessage + " in '" + jaasFile + "'.";
            }

            if (loginContextName != null) {
               errorMessage = errorMessage + " But zookeeper.sasl.serverconfig was set.";
            }

            LOG.error(errorMessage);
            throw new IOException(errorMessage);
         }
      }
   }

   private static Map getDigestMd5Credentials(AppConfigurationEntry[] appConfigurationEntries) {
      Map<String, String> credentials = new HashMap();

      for(AppConfigurationEntry entry : appConfigurationEntries) {
         Map<String, ?> options = entry.getOptions();

         for(Map.Entry pair : options.entrySet()) {
            String key = (String)pair.getKey();
            if (key.startsWith("user_")) {
               String userName = key.substring("user_".length());
               credentials.put(userName, (String)pair.getValue());
            }
         }
      }

      return credentials;
   }

   private static void setLoginUser(String name) {
      loginUser = name;
   }

   public static String getUserName() {
      return loginUser;
   }

   public int getMaxCnxns() {
      return this.maxCnxns;
   }

   protected void initMaxCnxns() {
      this.maxCnxns = Integer.getInteger("zookeeper.maxCnxns", 0);
      if (this.maxCnxns < 0) {
         this.maxCnxns = 0;
         LOG.warn("maxCnxns should be greater than or equal to 0, using default value {}.", 0);
      } else if (this.maxCnxns == 0) {
         LOG.warn("maxCnxns is not configured, using default value {}.", 0);
      } else {
         LOG.info("maxCnxns configured value is {}.", this.maxCnxns);
      }

   }

   protected boolean limitTotalNumberOfCnxns() {
      if (this.maxCnxns <= 0) {
         return false;
      } else {
         int cnxns = this.getNumAliveConnections();
         if (cnxns >= this.maxCnxns) {
            LOG.error("Too many connections " + cnxns + " - max is " + this.maxCnxns);
            return true;
         } else {
            return false;
         }
      }
   }

   static {
      loginUser = Login.SYSTEM_USER;
   }
}
