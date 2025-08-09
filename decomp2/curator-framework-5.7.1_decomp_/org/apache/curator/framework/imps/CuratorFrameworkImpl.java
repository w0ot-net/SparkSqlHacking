package org.apache.curator.framework.imps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.curator.CuratorConnectionLossException;
import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.RetryLoop;
import org.apache.curator.drivers.OperationTrace;
import org.apache.curator.framework.AuthInfo;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.WatcherRemoveCuratorFramework;
import org.apache.curator.framework.api.ACLProvider;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CompressionProvider;
import org.apache.curator.framework.api.CreateBuilder;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.DeleteBuilder;
import org.apache.curator.framework.api.ExistsBuilder;
import org.apache.curator.framework.api.GetACLBuilder;
import org.apache.curator.framework.api.GetChildrenBuilder;
import org.apache.curator.framework.api.GetConfigBuilder;
import org.apache.curator.framework.api.GetDataBuilder;
import org.apache.curator.framework.api.ReconfigBuilder;
import org.apache.curator.framework.api.RemoveWatchesBuilder;
import org.apache.curator.framework.api.SetACLBuilder;
import org.apache.curator.framework.api.SetDataBuilder;
import org.apache.curator.framework.api.SyncBuilder;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.curator.framework.api.WatchesBuilder;
import org.apache.curator.framework.api.transaction.CuratorMultiTransaction;
import org.apache.curator.framework.api.transaction.CuratorTransaction;
import org.apache.curator.framework.api.transaction.TransactionOp;
import org.apache.curator.framework.listen.Listenable;
import org.apache.curator.framework.listen.StandardListenerManager;
import org.apache.curator.framework.schema.SchemaSet;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateErrorPolicy;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.framework.state.ConnectionStateManager;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.apache.curator.utils.EnsurePath;
import org.apache.curator.utils.ThreadUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.curator.utils.ZookeeperCompatibility;
import org.apache.curator.utils.ZookeeperFactory;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.client.ZKClientConfig;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.quorum.flexible.QuorumVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CuratorFrameworkImpl implements CuratorFramework {
   private final Logger log = LoggerFactory.getLogger(this.getClass());
   private final CuratorZookeeperClient client;
   private final StandardListenerManager listeners;
   private final StandardListenerManager unhandledErrorListeners;
   private final ThreadFactory threadFactory;
   private final int maxCloseWaitMs;
   private final BlockingQueue backgroundOperations;
   private final BlockingQueue forcedSleepOperations;
   private final NamespaceImpl namespace;
   private final ConnectionStateManager connectionStateManager;
   private final List authInfos;
   private final byte[] defaultData;
   private final FailedDeleteManager failedDeleteManager;
   private final FailedRemoveWatchManager failedRemoveWatcherManager;
   private final CompressionProvider compressionProvider;
   private final ACLProvider aclProvider;
   private final NamespaceFacadeCache namespaceFacadeCache;
   private final boolean useContainerParentsIfAvailable;
   private final ConnectionStateErrorPolicy connectionStateErrorPolicy;
   private final AtomicLong currentInstanceIndex = new AtomicLong(-1L);
   private final InternalConnectionHandler internalConnectionHandler;
   private final EnsembleTracker ensembleTracker;
   private final SchemaSet schemaSet;
   private final Executor runSafeService;
   private final ZookeeperCompatibility zookeeperCompatibility;
   private volatile ExecutorService executorService;
   private final AtomicBoolean logAsErrorConnectionErrors = new AtomicBoolean(false);
   private static final boolean LOG_ALL_CONNECTION_ISSUES_AS_ERROR_LEVEL = !Boolean.getBoolean("curator-log-only-first-connection-issue-as-error-level");
   volatile DebugBackgroundListener debugListener = null;
   @VisibleForTesting
   public volatile UnhandledErrorListener debugUnhandledErrorListener = null;
   private final AtomicReference state;
   private final Object closeLock = new Object();
   @VisibleForTesting
   volatile CountDownLatch debugCheckBackgroundRetryLatch;
   @VisibleForTesting
   volatile CountDownLatch debugCheckBackgroundRetryReadyLatch;
   @VisibleForTesting
   volatile KeeperException.Code injectedCode;
   @VisibleForTesting
   volatile long sleepAndQueueOperationSeconds = 1L;

   public CuratorFrameworkImpl(CuratorFrameworkFactory.Builder builder) {
      ZookeeperFactory localZookeeperFactory = this.makeZookeeperFactory(builder.getZookeeperFactory(), builder.getZkClientConfig());
      this.client = new CuratorZookeeperClient(localZookeeperFactory, builder.getEnsembleProvider(), builder.getSessionTimeoutMs(), builder.getConnectionTimeoutMs(), builder.getWaitForShutdownTimeoutMs(), new Watcher() {
         public void process(WatchedEvent watchedEvent) {
            CuratorEvent event = new CuratorEventImpl(CuratorFrameworkImpl.this, CuratorEventType.WATCHED, watchedEvent.getState().getIntValue(), CuratorFrameworkImpl.this.unfixForNamespace(watchedEvent.getPath()), (String)null, (Object)null, (Stat)null, (byte[])null, (List)null, watchedEvent, (List)null, (List)null);
            CuratorFrameworkImpl.this.processEvent(event);
         }
      }, builder.getRetryPolicy(), builder.canBeReadOnly());
      this.internalConnectionHandler = new StandardInternalConnectionHandler();
      this.listeners = StandardListenerManager.standard();
      this.unhandledErrorListeners = StandardListenerManager.standard();
      this.backgroundOperations = new DelayQueue();
      this.forcedSleepOperations = new LinkedBlockingQueue();
      this.namespace = new NamespaceImpl(this, builder.getNamespace());
      this.threadFactory = this.getThreadFactory(builder);
      this.maxCloseWaitMs = builder.getMaxCloseWaitMs();
      this.connectionStateManager = new ConnectionStateManager(this, builder.getThreadFactory(), builder.getSessionTimeoutMs(), builder.getSimulatedSessionExpirationPercent(), builder.getConnectionStateListenerManagerFactory());
      this.compressionProvider = builder.getCompressionProvider();
      this.aclProvider = builder.getAclProvider();
      this.state = new AtomicReference(CuratorFrameworkState.LATENT);
      this.useContainerParentsIfAvailable = builder.useContainerParentsIfAvailable();
      this.connectionStateErrorPolicy = (ConnectionStateErrorPolicy)Preconditions.checkNotNull(builder.getConnectionStateErrorPolicy(), "errorPolicy cannot be null");
      this.schemaSet = (SchemaSet)Preconditions.checkNotNull(builder.getSchemaSet(), "schemaSet cannot be null");
      byte[] builderDefaultData = builder.getDefaultData();
      this.defaultData = builderDefaultData != null ? Arrays.copyOf(builderDefaultData, builderDefaultData.length) : new byte[0];
      this.authInfos = this.buildAuths(builder);
      this.failedDeleteManager = new FailedDeleteManager(this);
      this.failedRemoveWatcherManager = new FailedRemoveWatchManager(this);
      this.namespaceFacadeCache = new NamespaceFacadeCache(this);
      this.ensembleTracker = builder.withEnsembleTracker() ? new EnsembleTracker(this, builder.getEnsembleProvider()) : null;
      this.runSafeService = this.makeRunSafeService(builder);
      this.zookeeperCompatibility = builder.getZookeeperCompatibility();
   }

   private Executor makeRunSafeService(CuratorFrameworkFactory.Builder builder) {
      if (builder.getRunSafeService() != null) {
         return builder.getRunSafeService();
      } else {
         ThreadFactory threadFactory = builder.getThreadFactory();
         if (threadFactory == null) {
            threadFactory = ThreadUtils.newThreadFactory("SafeNotifyService");
         }

         return Executors.newSingleThreadExecutor(threadFactory);
      }
   }

   private List buildAuths(CuratorFrameworkFactory.Builder builder) {
      ImmutableList.Builder<AuthInfo> builder1 = ImmutableList.builder();
      if (builder.getAuthInfos() != null) {
         builder1.addAll(builder.getAuthInfos());
      }

      return builder1.build();
   }

   public CompletableFuture runSafe(Runnable runnable) {
      return CompletableFuture.runAsync(runnable, this.runSafeService);
   }

   public WatcherRemoveCuratorFramework newWatcherRemoveCuratorFramework() {
      return new WatcherRemovalFacade(this);
   }

   public QuorumVerifier getCurrentConfig() {
      return this.ensembleTracker != null ? this.ensembleTracker.getCurrentConfig() : null;
   }

   private ZookeeperFactory makeZookeeperFactory(final ZookeeperFactory actualZookeeperFactory, final ZKClientConfig zkClientConfig) {
      return new ZookeeperFactory() {
         public ZooKeeper newZooKeeper(String connectString, int sessionTimeout, Watcher watcher, boolean canBeReadOnly) throws Exception {
            ZooKeeper zooKeeper = actualZookeeperFactory.newZooKeeper(connectString, sessionTimeout, watcher, canBeReadOnly, zkClientConfig);
            CuratorFrameworkImpl.this.addAuthInfos(zooKeeper);
            return zooKeeper;
         }
      };
   }

   private void addAuthInfos(ZooKeeper zooKeeper) {
      for(AuthInfo auth : this.authInfos) {
         zooKeeper.addAuthInfo(auth.getScheme(), auth.getAuth());
      }

   }

   private ThreadFactory getThreadFactory(CuratorFrameworkFactory.Builder builder) {
      ThreadFactory threadFactory = builder.getThreadFactory();
      if (threadFactory == null) {
         threadFactory = ThreadUtils.newThreadFactory("Framework");
      }

      return threadFactory;
   }

   protected CuratorFrameworkImpl(CuratorFrameworkImpl parent) {
      this.client = parent.client;
      this.listeners = parent.listeners;
      this.unhandledErrorListeners = parent.unhandledErrorListeners;
      this.threadFactory = parent.threadFactory;
      this.maxCloseWaitMs = parent.maxCloseWaitMs;
      this.backgroundOperations = parent.backgroundOperations;
      this.forcedSleepOperations = parent.forcedSleepOperations;
      this.connectionStateManager = parent.connectionStateManager;
      this.defaultData = parent.defaultData;
      this.failedDeleteManager = parent.failedDeleteManager;
      this.failedRemoveWatcherManager = parent.failedRemoveWatcherManager;
      this.compressionProvider = parent.compressionProvider;
      this.aclProvider = parent.aclProvider;
      this.namespaceFacadeCache = parent.namespaceFacadeCache;
      this.namespace = parent.namespace;
      this.state = parent.state;
      this.authInfos = parent.authInfos;
      this.useContainerParentsIfAvailable = parent.useContainerParentsIfAvailable;
      this.connectionStateErrorPolicy = parent.connectionStateErrorPolicy;
      this.internalConnectionHandler = parent.internalConnectionHandler;
      this.schemaSet = parent.schemaSet;
      this.ensembleTracker = parent.ensembleTracker;
      this.runSafeService = parent.runSafeService;
      this.zookeeperCompatibility = parent.zookeeperCompatibility;
   }

   public void createContainers(String path) throws Exception {
      this.checkExists().creatingParentContainersIfNeeded().forPath(ZKPaths.makePath(path, "foo"));
   }

   public void clearWatcherReferences(Watcher watcher) {
   }

   public CuratorFrameworkState getState() {
      return (CuratorFrameworkState)this.state.get();
   }

   /** @deprecated */
   @Deprecated
   public boolean isStarted() {
      return this.state.get() == CuratorFrameworkState.STARTED;
   }

   public boolean blockUntilConnected(int maxWaitTime, TimeUnit units) throws InterruptedException {
      return this.connectionStateManager.blockUntilConnected(maxWaitTime, units);
   }

   public void blockUntilConnected() throws InterruptedException {
      this.blockUntilConnected(0, (TimeUnit)null);
   }

   public ConnectionStateErrorPolicy getConnectionStateErrorPolicy() {
      return this.connectionStateErrorPolicy;
   }

   public void start() {
      this.log.info("Starting");
      if (!this.state.compareAndSet(CuratorFrameworkState.LATENT, CuratorFrameworkState.STARTED)) {
         throw new IllegalStateException("Cannot be started more than once");
      } else {
         try {
            this.connectionStateManager.start();
            ConnectionStateListener listener = new ConnectionStateListener() {
               public void stateChanged(CuratorFramework client, ConnectionState newState) {
                  if (ConnectionState.CONNECTED == newState || ConnectionState.RECONNECTED == newState) {
                     CuratorFrameworkImpl.this.logAsErrorConnectionErrors.set(true);
                  }

               }

               public boolean doNotProxy() {
                  return true;
               }
            };
            this.getConnectionStateListenable().addListener(listener);
            this.client.start();
            this.executorService = Executors.newSingleThreadScheduledExecutor(this.threadFactory);
            this.executorService.submit(new Callable() {
               public Object call() throws Exception {
                  CuratorFrameworkImpl.this.backgroundOperationsLoop();
                  return null;
               }
            });
            if (this.ensembleTracker != null) {
               this.ensembleTracker.start();
            }

            this.log.info(this.schemaSet.toDocumentation());
         } catch (Exception e) {
            ThreadUtils.checkInterrupted(e);
            this.handleBackgroundOperationException((OperationAndData)null, e);
         }

      }
   }

   private boolean closeWithLock() {
      synchronized(this.closeLock) {
         return this.state.compareAndSet(CuratorFrameworkState.STARTED, CuratorFrameworkState.STOPPED);
      }
   }

   public void close() {
      this.log.debug("Closing");
      if (this.closeWithLock()) {
         this.listeners.forEach((listener) -> {
            CuratorEvent event = new CuratorEventImpl(this, CuratorEventType.CLOSING, 0, (String)null, (String)null, (Object)null, (Stat)null, (byte[])null, (List)null, (WatchedEvent)null, (List)null, (List)null);

            try {
               listener.eventReceived(this, event);
            } catch (Exception e) {
               ThreadUtils.checkInterrupted(e);
               this.log.error("Exception while sending Closing event", e);
            }

         });
         if (this.executorService != null) {
            this.executorService.shutdownNow();

            try {
               this.executorService.awaitTermination((long)this.maxCloseWaitMs, TimeUnit.MILLISECONDS);
            } catch (InterruptedException var2) {
               Thread.currentThread().interrupt();
            }
         }

         if (this.ensembleTracker != null) {
            this.ensembleTracker.close();
         }

         this.backgroundOperations.forEach(OperationAndData::clearSleep);
         Collection<OperationAndData<?>> droppedOperations = new ArrayList(this.backgroundOperations.size());
         this.backgroundOperations.drainTo(droppedOperations);
         droppedOperations.forEach(this::closeOperation);
         this.listeners.clear();
         this.unhandledErrorListeners.clear();
         this.connectionStateManager.close();
         this.client.close();
      }

   }

   /** @deprecated */
   @Deprecated
   public CuratorFramework nonNamespaceView() {
      return this.usingNamespace((String)null);
   }

   public String getNamespace() {
      String str = this.namespace.getNamespace();
      return str != null ? str : "";
   }

   private void checkState() {
      CuratorFrameworkState state = this.getState();
      Preconditions.checkState(state == CuratorFrameworkState.STARTED, "Expected state [%s] was [%s]", CuratorFrameworkState.STARTED, state);
   }

   public CuratorFramework usingNamespace(String newNamespace) {
      this.checkState();
      return this.namespaceFacadeCache.get(newNamespace);
   }

   public CreateBuilder create() {
      this.checkState();
      return new CreateBuilderImpl(this);
   }

   public DeleteBuilder delete() {
      this.checkState();
      return new DeleteBuilderImpl(this);
   }

   public ExistsBuilder checkExists() {
      this.checkState();
      return new ExistsBuilderImpl(this);
   }

   public GetDataBuilder getData() {
      this.checkState();
      return new GetDataBuilderImpl(this);
   }

   public SetDataBuilder setData() {
      this.checkState();
      return new SetDataBuilderImpl(this);
   }

   public GetChildrenBuilder getChildren() {
      this.checkState();
      return new GetChildrenBuilderImpl(this);
   }

   public GetACLBuilder getACL() {
      this.checkState();
      return new GetACLBuilderImpl(this);
   }

   public SetACLBuilder setACL() {
      this.checkState();
      return new SetACLBuilderImpl(this);
   }

   public ReconfigBuilder reconfig() {
      return new ReconfigBuilderImpl(this);
   }

   public GetConfigBuilder getConfig() {
      return new GetConfigBuilderImpl(this);
   }

   public CuratorTransaction inTransaction() {
      this.checkState();
      return new CuratorTransactionImpl(this);
   }

   public CuratorMultiTransaction transaction() {
      this.checkState();
      return new CuratorMultiTransactionImpl(this);
   }

   public TransactionOp transactionOp() {
      this.checkState();
      return new TransactionOpImpl(this);
   }

   public Listenable getConnectionStateListenable() {
      return this.connectionStateManager.getListenable();
   }

   public Listenable getCuratorListenable() {
      return this.listeners;
   }

   public Listenable getUnhandledErrorListenable() {
      return this.unhandledErrorListeners;
   }

   public void sync(String path, Object context) {
      this.checkState();
      path = this.fixForNamespace(path);
      this.internalSync(this, path, context);
   }

   public SyncBuilder sync() {
      return new SyncBuilderImpl(this);
   }

   public RemoveWatchesBuilder watches() {
      return new RemoveWatchesBuilderImpl(this);
   }

   public WatchesBuilder watchers() {
      Preconditions.checkState(this.zookeeperCompatibility.hasPersistentWatchers(), "watchers() is not supported in the ZooKeeper library and/or server being used. Use watches() instead.");
      return new WatchesBuilderImpl(this);
   }

   protected void internalSync(CuratorFrameworkImpl impl, String path, Object context) {
      BackgroundOperation<String> operation = new BackgroundSyncImpl(impl, context);
      this.performBackgroundOperation(new OperationAndData(operation, path, (BackgroundCallback)null, (OperationAndData.ErrorCallback)null, context, (Watching)null));
   }

   public CuratorZookeeperClient getZookeeperClient() {
      return this.client;
   }

   public ZookeeperCompatibility getZookeeperCompatibility() {
      return this.zookeeperCompatibility;
   }

   public EnsurePath newNamespaceAwareEnsurePath(String path) {
      return this.namespace.newNamespaceAwareEnsurePath(path);
   }

   public SchemaSet getSchemaSet() {
      return this.schemaSet;
   }

   ACLProvider getAclProvider() {
      return this.aclProvider;
   }

   FailedDeleteManager getFailedDeleteManager() {
      return this.failedDeleteManager;
   }

   FailedRemoveWatchManager getFailedRemoveWatcherManager() {
      return this.failedRemoveWatcherManager;
   }

   RetryLoop newRetryLoop() {
      return this.client.newRetryLoop();
   }

   ZooKeeper getZooKeeper() throws Exception {
      return this.client.getZooKeeper();
   }

   CompressionProvider getCompressionProvider() {
      return this.compressionProvider;
   }

   boolean useContainerParentsIfAvailable() {
      return this.useContainerParentsIfAvailable;
   }

   void processBackgroundOperation(OperationAndData operationAndData, CuratorEvent event) {
      boolean isInitialExecution = event == null;
      if (isInitialExecution) {
         this.performBackgroundOperation(operationAndData);
      } else {
         boolean doQueueOperation = false;
         KeeperException.Code code = Code.get(event.getResultCode());
         if (code != Code.OK && this.getZookeeperClient().getRetryPolicy().allowRetry(KeeperException.create(code))) {
            doQueueOperation = this.checkBackgroundRetry(operationAndData, event);
         } else if (operationAndData.getCallback() != null) {
            this.sendToBackgroundCallback(operationAndData, event);
         } else {
            this.processEvent(event);
         }

         if (doQueueOperation) {
            this.queueOperation(operationAndData);
         }

      }
   }

   private void abortOperation(OperationAndData operation, Throwable e) {
      if (operation.getCallback() != null) {
         CuratorEvent event;
         if (e instanceof KeeperException) {
            event = new CuratorEventImpl(this, operation.getEventType(), ((KeeperException)e).code().intValue(), ((KeeperException)e).getPath(), (String)null, operation.getContext(), (Stat)null, (byte[])null, (List)null, (WatchedEvent)null, (List)null, (List)null);
         } else if (this.getState() == CuratorFrameworkState.STARTED) {
            event = new CuratorEventImpl(this, operation.getEventType(), Code.SYSTEMERROR.intValue(), (String)null, (String)null, operation.getContext(), (Stat)null, (byte[])null, (List)null, (WatchedEvent)null, (List)null, (List)null);
         } else {
            event = new CuratorEventImpl(this, operation.getEventType(), Code.SESSIONEXPIRED.intValue(), (String)null, (String)null, operation.getContext(), (Stat)null, (byte[])null, (List)null, (WatchedEvent)null, (List)null, (List)null);
         }

         this.sendToBackgroundCallback(operation, event);
      }
   }

   private void closeOperation(OperationAndData operation) {
      if (operation.getCallback() != null) {
         CuratorEvent event = new CuratorEventImpl(this, operation.getEventType(), Code.SESSIONEXPIRED.intValue(), (String)null, (String)null, operation.getContext(), (Stat)null, (byte[])null, (List)null, (WatchedEvent)null, (List)null, (List)null);
         this.sendToBackgroundCallback(operation, event);
      }
   }

   private void requeueSleepOperation(OperationAndData operationAndData) {
      operationAndData.clearSleep();
      synchronized(this.closeLock) {
         if (this.getState() == CuratorFrameworkState.STARTED) {
            if (this.backgroundOperations.remove(operationAndData)) {
               this.backgroundOperations.offer(operationAndData);
            }

            return;
         }
      }

      if (this.backgroundOperations.remove(operationAndData)) {
         this.closeOperation(operationAndData);
      }

   }

   boolean queueOperation(OperationAndData operationAndData) {
      synchronized(this.closeLock) {
         if (this.getState() == CuratorFrameworkState.STARTED) {
            this.backgroundOperations.offer(operationAndData);
            return true;
         }
      }

      this.closeOperation(operationAndData);
      return false;
   }

   void logError(String reason, Throwable e) {
      if (reason == null || reason.length() == 0) {
         reason = "n/a";
      }

      if (!Boolean.getBoolean("curator-dont-log-connection-problems") || !(e instanceof KeeperException)) {
         if (e instanceof KeeperException.ConnectionLossException) {
            if (!LOG_ALL_CONNECTION_ISSUES_AS_ERROR_LEVEL && !this.logAsErrorConnectionErrors.compareAndSet(true, false)) {
               this.log.debug(reason, e);
            } else {
               this.log.error(reason, e);
            }
         } else {
            this.log.error(reason, e);
         }
      }

      this.unhandledErrorListeners.forEach((l) -> l.unhandledError(reason, e));
      if (this.debugUnhandledErrorListener != null) {
         this.debugUnhandledErrorListener.unhandledError(reason, e);
      }

   }

   String unfixForNamespace(String path) {
      return this.namespace.unfixForNamespace(path);
   }

   String fixForNamespace(String path) {
      return this.namespace.fixForNamespace(path, false);
   }

   String fixForNamespace(String path, boolean isSequential) {
      return this.namespace.fixForNamespace(path, isSequential);
   }

   byte[] getDefaultData() {
      return this.defaultData;
   }

   NamespaceFacadeCache getNamespaceFacadeCache() {
      return this.namespaceFacadeCache;
   }

   void validateConnection(Watcher.Event.KeeperState state) {
      if (state == KeeperState.Disconnected) {
         this.internalConnectionHandler.suspendConnection(this);
      } else if (state == KeeperState.Expired) {
         this.connectionStateManager.addStateChange(ConnectionState.LOST);
      } else if (state == KeeperState.SyncConnected) {
         this.internalConnectionHandler.checkNewConnection(this);
         this.connectionStateManager.addStateChange(ConnectionState.RECONNECTED);
         this.unSleepBackgroundOperations();
      } else if (state == KeeperState.ConnectedReadOnly) {
         this.internalConnectionHandler.checkNewConnection(this);
         this.connectionStateManager.addStateChange(ConnectionState.READ_ONLY);
      }

   }

   void checkInstanceIndex() {
      long instanceIndex = this.client.getInstanceIndex();
      long newInstanceIndex = this.currentInstanceIndex.getAndSet(instanceIndex);
      if (newInstanceIndex >= 0L && instanceIndex != newInstanceIndex) {
         this.connectionStateManager.addStateChange(ConnectionState.LOST);
      }

   }

   Watcher.Event.KeeperState codeToState(KeeperException.Code code) {
      switch (code) {
         case AUTHFAILED:
         case NOAUTH:
            return KeeperState.AuthFailed;
         case CONNECTIONLOSS:
         case OPERATIONTIMEOUT:
            return KeeperState.Disconnected;
         case SESSIONEXPIRED:
            return KeeperState.Expired;
         case OK:
         case SESSIONMOVED:
            return KeeperState.SyncConnected;
         default:
            return KeeperState.fromInt(-1);
      }
   }

   WatcherRemovalManager getWatcherRemovalManager() {
      return null;
   }

   boolean setToSuspended() {
      return this.connectionStateManager.setToSuspended();
   }

   void addStateChange(ConnectionState newConnectionState) {
      this.connectionStateManager.addStateChange(newConnectionState);
   }

   EnsembleTracker getEnsembleTracker() {
      return this.ensembleTracker;
   }

   private boolean checkBackgroundRetry(OperationAndData operationAndData, CuratorEvent event) {
      boolean doRetry = false;
      if (this.client.getRetryPolicy().allowRetry(operationAndData.getThenIncrementRetryCount(), operationAndData.getElapsedTimeMs(), operationAndData)) {
         doRetry = true;
      } else {
         if (operationAndData.getErrorCallback() != null) {
            operationAndData.getErrorCallback().retriesExhausted(operationAndData);
         }

         if (operationAndData.getCallback() != null) {
            this.sendToBackgroundCallback(operationAndData, event);
         }

         KeeperException.Code code = Code.get(event.getResultCode());
         Exception e = null;

         try {
            e = code != null ? KeeperException.create(code) : null;
         } catch (Throwable t) {
            ThreadUtils.checkInterrupted(t);
         }

         if (e == null) {
            e = new Exception("Unknown result codegetResultCode()");
         }

         if (this.debugCheckBackgroundRetryLatch != null) {
            if (this.debugCheckBackgroundRetryReadyLatch != null) {
               this.debugCheckBackgroundRetryReadyLatch.countDown();
            }

            try {
               this.debugCheckBackgroundRetryLatch.await();
               if (this.injectedCode != null) {
                  code = this.injectedCode;
               }
            } catch (InterruptedException var7) {
               Thread.currentThread().interrupt();
            }
         }

         this.validateConnection(this.codeToState(code));
         this.logError("Background operation retry gave up", e);
      }

      return doRetry;
   }

   private void sendToBackgroundCallback(OperationAndData operationAndData, CuratorEvent event) {
      try {
         operationAndData.getCallback().processResult(this, event);
      } catch (Exception e) {
         ThreadUtils.checkInterrupted(e);
         this.handleBackgroundOperationException((OperationAndData)null, e);
      }

   }

   private void handleBackgroundOperationException(OperationAndData operationAndData, Throwable e) {
      if (operationAndData != null && this.getZookeeperClient().getRetryPolicy().allowRetry(e)) {
         if (!Boolean.getBoolean("curator-dont-log-connection-problems")) {
            this.log.debug("Retry-able exception received", e);
         }

         if (this.client.getRetryPolicy().allowRetry(operationAndData.getThenIncrementRetryCount(), operationAndData.getElapsedTimeMs(), operationAndData)) {
            if (!Boolean.getBoolean("curator-dont-log-connection-problems")) {
               this.log.debug("Retrying operation");
            }

            this.queueOperation(operationAndData);
            return;
         }

         if (!Boolean.getBoolean("curator-dont-log-connection-problems")) {
            this.log.debug("Retry policy did not allow retry");
         }

         if (operationAndData.getErrorCallback() != null) {
            operationAndData.getErrorCallback().retriesExhausted(operationAndData);
         }
      }

      if (operationAndData != null) {
         this.abortOperation(operationAndData, e);
      }

      this.logError("Background exception was not retry-able or retry gave up", e);
   }

   private void backgroundOperationsLoop() {
      try {
         while(this.state.get() == CuratorFrameworkState.STARTED) {
            try {
               OperationAndData<?> operationAndData = (OperationAndData)this.backgroundOperations.take();
               if (this.debugListener != null) {
                  this.debugListener.listen(operationAndData);
               }

               this.performBackgroundOperation(operationAndData);
            } catch (InterruptedException var6) {
            }
         }
      } finally {
         this.log.info("backgroundOperationsLoop exiting");
      }

   }

   void performBackgroundOperation(OperationAndData operationAndData) {
      try {
         if (!operationAndData.isConnectionRequired() || this.client.isConnected()) {
            operationAndData.callPerformBackgroundOperation();
            return;
         }

         this.client.getZooKeeper();
         if (operationAndData.getElapsedTimeMs() < (long)this.client.getConnectionTimeoutMs()) {
            this.sleepAndQueueOperation(operationAndData);
            return;
         }

         CuratorEvent event = new CuratorEventImpl(this, operationAndData.getEventType(), Code.CONNECTIONLOSS.intValue(), (String)null, (String)null, operationAndData.getContext(), (Stat)null, (byte[])null, (List)null, (WatchedEvent)null, (List)null, (List)null);
         if (this.checkBackgroundRetry(operationAndData, event)) {
            this.queueOperation(operationAndData);
         } else {
            this.logError("Background retry gave up", new CuratorConnectionLossException());
         }
      } catch (Throwable e) {
         ThreadUtils.checkInterrupted(e);
         this.handleBackgroundOperationException(operationAndData, e);
      }

   }

   private void sleepAndQueueOperation(OperationAndData operationAndData) throws InterruptedException {
      operationAndData.sleepFor(this.sleepAndQueueOperationSeconds, TimeUnit.SECONDS);
      if (this.queueOperation(operationAndData)) {
         this.forcedSleepOperations.add(operationAndData);
      }

   }

   private void unSleepBackgroundOperations() {
      Collection<OperationAndData<?>> drain = new ArrayList(this.forcedSleepOperations.size());
      this.forcedSleepOperations.drainTo(drain);
      this.log.debug("Clearing sleep for {} operations", drain.size());
      drain.forEach(this::requeueSleepOperation);
   }

   private void processEvent(CuratorEvent curatorEvent) {
      if (curatorEvent.getType() == CuratorEventType.WATCHED) {
         this.validateConnection(curatorEvent.getWatchedEvent().getState());
      }

      this.listeners.forEach((listener) -> {
         try {
            OperationTrace trace = this.client.startAdvancedTracer("EventListener");
            listener.eventReceived(this, curatorEvent);
            trace.commit();
         } catch (Exception e) {
            ThreadUtils.checkInterrupted(e);
            this.logError("Event listener threw exception", e);
         }

      });
   }

   interface DebugBackgroundListener {
      void listen(OperationAndData var1);
   }
}
