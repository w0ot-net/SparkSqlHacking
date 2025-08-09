package org.apache.curator.framework;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import org.apache.curator.RetryPolicy;
import org.apache.curator.ensemble.EnsembleProvider;
import org.apache.curator.ensemble.fixed.FixedEnsembleProvider;
import org.apache.curator.framework.api.ACLProvider;
import org.apache.curator.framework.api.CompressionProvider;
import org.apache.curator.framework.imps.CuratorFrameworkImpl;
import org.apache.curator.framework.imps.CuratorTempFrameworkImpl;
import org.apache.curator.framework.imps.DefaultACLProvider;
import org.apache.curator.framework.imps.GzipCompressionProvider;
import org.apache.curator.framework.schema.SchemaSet;
import org.apache.curator.framework.state.ConnectionStateErrorPolicy;
import org.apache.curator.framework.state.ConnectionStateListenerManagerFactory;
import org.apache.curator.framework.state.StandardConnectionStateErrorPolicy;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.apache.curator.utils.DefaultZookeeperFactory;
import org.apache.curator.utils.ZookeeperCompatibility;
import org.apache.curator.utils.ZookeeperFactory;
import org.apache.zookeeper.client.ZKClientConfig;

public class CuratorFrameworkFactory {
   private static final int DEFAULT_SESSION_TIMEOUT_MS = Integer.getInteger("curator-default-session-timeout", 60000);
   private static final int DEFAULT_CONNECTION_TIMEOUT_MS = Integer.getInteger("curator-default-connection-timeout", 15000);
   private static final byte[] LOCAL_ADDRESS = getLocalAddress();
   private static final CompressionProvider DEFAULT_COMPRESSION_PROVIDER = new GzipCompressionProvider();
   private static final DefaultZookeeperFactory DEFAULT_ZOOKEEPER_FACTORY = new DefaultZookeeperFactory();
   private static final DefaultACLProvider DEFAULT_ACL_PROVIDER = new DefaultACLProvider();
   private static final long DEFAULT_INACTIVE_THRESHOLD_MS;
   private static final int DEFAULT_CLOSE_WAIT_MS;
   private static final boolean DEFAULT_WITH_ENSEMBLE_TRACKER = true;

   public static Builder builder() {
      return new Builder();
   }

   public static CuratorFramework newClient(String connectString, RetryPolicy retryPolicy) {
      return newClient(connectString, DEFAULT_SESSION_TIMEOUT_MS, DEFAULT_CONNECTION_TIMEOUT_MS, retryPolicy);
   }

   public static CuratorFramework newClient(String connectString, int sessionTimeoutMs, int connectionTimeoutMs, RetryPolicy retryPolicy) {
      return builder().connectString(connectString).sessionTimeoutMs(sessionTimeoutMs).connectionTimeoutMs(connectionTimeoutMs).retryPolicy(retryPolicy).build();
   }

   public static CuratorFramework newClient(String connectString, int sessionTimeoutMs, int connectionTimeoutMs, RetryPolicy retryPolicy, ZKClientConfig zkClientConfig) {
      return builder().connectString(connectString).sessionTimeoutMs(sessionTimeoutMs).connectionTimeoutMs(connectionTimeoutMs).retryPolicy(retryPolicy).zkClientConfig(zkClientConfig).build();
   }

   public static byte[] getLocalAddress() {
      try {
         return InetAddress.getLocalHost().getHostAddress().getBytes();
      } catch (UnknownHostException var1) {
         return new byte[0];
      }
   }

   private CuratorFrameworkFactory() {
   }

   static {
      DEFAULT_INACTIVE_THRESHOLD_MS = (long)((int)TimeUnit.MINUTES.toMillis(3L));
      DEFAULT_CLOSE_WAIT_MS = (int)TimeUnit.SECONDS.toMillis(1L);
   }

   public static class Builder {
      private EnsembleProvider ensembleProvider;
      private boolean withEnsembleTracker;
      private int sessionTimeoutMs;
      private int connectionTimeoutMs;
      private int maxCloseWaitMs;
      private RetryPolicy retryPolicy;
      private ThreadFactory threadFactory;
      private String namespace;
      private List authInfos;
      private byte[] defaultData;
      private CompressionProvider compressionProvider;
      private ZookeeperFactory zookeeperFactory;
      private ACLProvider aclProvider;
      private boolean canBeReadOnly;
      private boolean useContainerParentsIfAvailable;
      private ConnectionStateErrorPolicy connectionStateErrorPolicy;
      private SchemaSet schemaSet;
      private int waitForShutdownTimeoutMs;
      private Executor runSafeService;
      private ConnectionStateListenerManagerFactory connectionStateListenerManagerFactory;
      private int simulatedSessionExpirationPercent;
      private ZKClientConfig zkClientConfig;
      private ZookeeperCompatibility zookeeperCompatibility;

      public CuratorFramework build() {
         return new CuratorFrameworkImpl(this);
      }

      public CuratorTempFramework buildTemp() {
         return this.buildTemp(CuratorFrameworkFactory.DEFAULT_INACTIVE_THRESHOLD_MS, TimeUnit.MILLISECONDS);
      }

      public CuratorTempFramework buildTemp(long inactiveThreshold, TimeUnit unit) {
         return new CuratorTempFrameworkImpl(this, unit.toMillis(inactiveThreshold));
      }

      public Builder authorization(String scheme, byte[] auth) {
         return this.authorization(ImmutableList.of(new AuthInfo(scheme, auth != null ? Arrays.copyOf(auth, auth.length) : null)));
      }

      public Builder authorization(List authInfos) {
         this.authInfos = ImmutableList.copyOf(authInfos);
         return this;
      }

      public Builder connectString(String connectString) {
         this.ensembleProvider = new FixedEnsembleProvider(connectString);
         return this;
      }

      public Builder ensembleProvider(EnsembleProvider ensembleProvider) {
         this.ensembleProvider = ensembleProvider;
         return this;
      }

      public Builder ensembleTracker(boolean withEnsembleTracker) {
         this.withEnsembleTracker = withEnsembleTracker;
         return this;
      }

      public boolean withEnsembleTracker() {
         return this.withEnsembleTracker;
      }

      public Builder defaultData(byte[] defaultData) {
         this.defaultData = defaultData != null ? Arrays.copyOf(defaultData, defaultData.length) : null;
         return this;
      }

      public Builder namespace(String namespace) {
         this.namespace = namespace;
         return this;
      }

      public Builder sessionTimeoutMs(int sessionTimeoutMs) {
         this.sessionTimeoutMs = sessionTimeoutMs;
         return this;
      }

      public Builder connectionTimeoutMs(int connectionTimeoutMs) {
         this.connectionTimeoutMs = connectionTimeoutMs;
         return this;
      }

      public Builder maxCloseWaitMs(int maxCloseWaitMs) {
         this.maxCloseWaitMs = maxCloseWaitMs;
         return this;
      }

      public Builder retryPolicy(RetryPolicy retryPolicy) {
         this.retryPolicy = retryPolicy;
         return this;
      }

      public Builder threadFactory(ThreadFactory threadFactory) {
         this.threadFactory = threadFactory;
         return this;
      }

      public Builder compressionProvider(CompressionProvider compressionProvider) {
         this.compressionProvider = compressionProvider;
         return this;
      }

      public Builder zookeeperFactory(ZookeeperFactory zookeeperFactory) {
         this.zookeeperFactory = zookeeperFactory;
         return this;
      }

      public Builder aclProvider(ACLProvider aclProvider) {
         this.aclProvider = aclProvider;
         return this;
      }

      public Builder canBeReadOnly(boolean canBeReadOnly) {
         this.canBeReadOnly = canBeReadOnly;
         return this;
      }

      public Builder dontUseContainerParents() {
         this.useContainerParentsIfAvailable = false;
         return this;
      }

      public Builder connectionStateErrorPolicy(ConnectionStateErrorPolicy connectionStateErrorPolicy) {
         this.connectionStateErrorPolicy = connectionStateErrorPolicy;
         return this;
      }

      public Builder waitForShutdownTimeoutMs(int waitForShutdownTimeoutMs) {
         this.waitForShutdownTimeoutMs = waitForShutdownTimeoutMs;
         return this;
      }

      public Builder simulatedSessionExpirationPercent(int simulatedSessionExpirationPercent) {
         Preconditions.checkArgument(simulatedSessionExpirationPercent > 0 && simulatedSessionExpirationPercent <= 100, "simulatedSessionExpirationPercent must be > 0 and <= 100");
         this.simulatedSessionExpirationPercent = simulatedSessionExpirationPercent;
         return this;
      }

      public Builder zkClientConfig(ZKClientConfig zkClientConfig) {
         this.zkClientConfig = zkClientConfig;
         return this;
      }

      public Builder schemaSet(SchemaSet schemaSet) {
         this.schemaSet = schemaSet;
         return this;
      }

      public Builder runSafeService(Executor runSafeService) {
         this.runSafeService = runSafeService;
         return this;
      }

      public Builder connectionStateListenerManagerFactory(ConnectionStateListenerManagerFactory connectionStateListenerManagerFactory) {
         this.connectionStateListenerManagerFactory = (ConnectionStateListenerManagerFactory)Objects.requireNonNull(connectionStateListenerManagerFactory, "connectionStateListenerManagerFactory cannot be null");
         return this;
      }

      public Builder zookeeperCompatibility(ZookeeperCompatibility zookeeperCompatibility) {
         this.zookeeperCompatibility = zookeeperCompatibility;
         return this;
      }

      public Executor getRunSafeService() {
         return this.runSafeService;
      }

      public ACLProvider getAclProvider() {
         return this.aclProvider;
      }

      public ZookeeperFactory getZookeeperFactory() {
         return this.zookeeperFactory;
      }

      public CompressionProvider getCompressionProvider() {
         return this.compressionProvider;
      }

      public ThreadFactory getThreadFactory() {
         return this.threadFactory;
      }

      public EnsembleProvider getEnsembleProvider() {
         return this.ensembleProvider;
      }

      public int getSessionTimeoutMs() {
         return this.sessionTimeoutMs;
      }

      public int getConnectionTimeoutMs() {
         return this.connectionTimeoutMs;
      }

      public int getWaitForShutdownTimeoutMs() {
         return this.waitForShutdownTimeoutMs;
      }

      public int getMaxCloseWaitMs() {
         return this.maxCloseWaitMs;
      }

      public RetryPolicy getRetryPolicy() {
         return this.retryPolicy;
      }

      public String getNamespace() {
         return this.namespace;
      }

      public boolean useContainerParentsIfAvailable() {
         return this.useContainerParentsIfAvailable;
      }

      public ConnectionStateErrorPolicy getConnectionStateErrorPolicy() {
         return this.connectionStateErrorPolicy;
      }

      public int getSimulatedSessionExpirationPercent() {
         return this.simulatedSessionExpirationPercent;
      }

      public ZKClientConfig getZkClientConfig() {
         return this.zkClientConfig;
      }

      public SchemaSet getSchemaSet() {
         return this.schemaSet;
      }

      /** @deprecated */
      @Deprecated
      public String getAuthScheme() {
         int qty = this.authInfos != null ? this.authInfos.size() : 0;
         switch (qty) {
            case 0:
               return null;
            case 1:
               return ((AuthInfo)this.authInfos.get(0)).scheme;
            default:
               throw new IllegalStateException("More than 1 auth has been added");
         }
      }

      /** @deprecated */
      @Deprecated
      public byte[] getAuthValue() {
         int qty = this.authInfos != null ? this.authInfos.size() : 0;
         switch (qty) {
            case 0:
               return null;
            case 1:
               byte[] bytes = ((AuthInfo)this.authInfos.get(0)).getAuth();
               return bytes != null ? Arrays.copyOf(bytes, bytes.length) : null;
            default:
               throw new IllegalStateException("More than 1 auth has been added");
         }
      }

      public List getAuthInfos() {
         return this.authInfos;
      }

      public byte[] getDefaultData() {
         return this.defaultData;
      }

      public boolean canBeReadOnly() {
         return this.canBeReadOnly;
      }

      public ConnectionStateListenerManagerFactory getConnectionStateListenerManagerFactory() {
         return this.connectionStateListenerManagerFactory;
      }

      public ZookeeperCompatibility getZookeeperCompatibility() {
         return this.zookeeperCompatibility;
      }

      private Builder() {
         this.withEnsembleTracker = true;
         this.sessionTimeoutMs = CuratorFrameworkFactory.DEFAULT_SESSION_TIMEOUT_MS;
         this.connectionTimeoutMs = CuratorFrameworkFactory.DEFAULT_CONNECTION_TIMEOUT_MS;
         this.maxCloseWaitMs = CuratorFrameworkFactory.DEFAULT_CLOSE_WAIT_MS;
         this.threadFactory = null;
         this.authInfos = null;
         this.defaultData = CuratorFrameworkFactory.LOCAL_ADDRESS;
         this.compressionProvider = CuratorFrameworkFactory.DEFAULT_COMPRESSION_PROVIDER;
         this.zookeeperFactory = CuratorFrameworkFactory.DEFAULT_ZOOKEEPER_FACTORY;
         this.aclProvider = CuratorFrameworkFactory.DEFAULT_ACL_PROVIDER;
         this.canBeReadOnly = false;
         this.useContainerParentsIfAvailable = true;
         this.connectionStateErrorPolicy = new StandardConnectionStateErrorPolicy();
         this.schemaSet = SchemaSet.getDefaultSchemaSet();
         this.waitForShutdownTimeoutMs = 0;
         this.runSafeService = null;
         this.connectionStateListenerManagerFactory = ConnectionStateListenerManagerFactory.standard;
         this.simulatedSessionExpirationPercent = 100;
         this.zookeeperCompatibility = ZookeeperCompatibility.LATEST;
      }
   }
}
