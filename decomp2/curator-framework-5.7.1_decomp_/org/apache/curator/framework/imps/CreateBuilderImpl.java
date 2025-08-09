package org.apache.curator.framework.imps;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.curator.RetryLoop;
import org.apache.curator.drivers.OperationTrace;
import org.apache.curator.framework.api.ACLBackgroundPathAndBytesable;
import org.apache.curator.framework.api.ACLCreateModeBackgroundPathAndBytesable;
import org.apache.curator.framework.api.ACLCreateModePathAndBytesable;
import org.apache.curator.framework.api.ACLCreateModeStatBackgroundPathAndBytesable;
import org.apache.curator.framework.api.ACLPathAndBytesable;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.BackgroundPathAndBytesable;
import org.apache.curator.framework.api.CreateBackgroundModeACLable;
import org.apache.curator.framework.api.CreateBackgroundModeStatACLable;
import org.apache.curator.framework.api.CreateBuilder;
import org.apache.curator.framework.api.CreateBuilder2;
import org.apache.curator.framework.api.CreateBuilderMain;
import org.apache.curator.framework.api.CreateProtectACLCreateModePathAndBytesable;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.ErrorListenerPathAndBytesable;
import org.apache.curator.framework.api.PathAndBytesable;
import org.apache.curator.framework.api.ProtectACLCreateModePathAndBytesable;
import org.apache.curator.framework.api.ProtectACLCreateModeStatPathAndBytesable;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.curator.framework.api.transaction.OperationType;
import org.apache.curator.framework.api.transaction.TransactionCreateBuilder;
import org.apache.curator.framework.api.transaction.TransactionCreateBuilder2;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.Predicate;
import org.apache.curator.shaded.com.google.common.collect.Iterables;
import org.apache.curator.utils.InternalACLProvider;
import org.apache.curator.utils.ThreadUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Op;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.DataTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateBuilderImpl implements CreateBuilder, CreateBuilder2, BackgroundOperation, ErrorListenerPathAndBytesable {
   private final Logger log = LoggerFactory.getLogger(this.getClass());
   private final CuratorFrameworkImpl client;
   private final ProtectedMode protectedMode = new ProtectedMode();
   private CreateMode createMode;
   private Backgrounding backgrounding;
   private boolean createParentsIfNeeded;
   private boolean createParentsAsContainers;
   private boolean compress;
   private boolean setDataIfExists;
   private int setDataIfExistsVersion = -1;
   private boolean idempotent = false;
   private ACLing acling;
   private Stat storingStat;
   private long ttl;
   @VisibleForTesting
   boolean failNextCreateForTesting = false;
   @VisibleForTesting
   boolean failBeforeNextCreateForTesting = false;
   @VisibleForTesting
   boolean failNextIdempotentCheckForTesting = false;
   @VisibleForTesting
   volatile boolean debugForceFindProtectedNode = false;

   CreateBuilderImpl(CuratorFrameworkImpl client) {
      this.client = client;
      this.createMode = CreateMode.PERSISTENT;
      this.backgrounding = new Backgrounding();
      this.acling = new ACLing(client.getAclProvider());
      this.createParentsIfNeeded = false;
      this.createParentsAsContainers = false;
      this.compress = false;
      this.setDataIfExists = false;
      this.storingStat = null;
      this.ttl = -1L;
   }

   public CreateBuilderImpl(CuratorFrameworkImpl client, CreateMode createMode, Backgrounding backgrounding, boolean createParentsIfNeeded, boolean createParentsAsContainers, boolean doProtected, boolean compress, boolean setDataIfExists, List aclList, Stat storingStat, long ttl) {
      this.client = client;
      this.createMode = createMode;
      this.backgrounding = backgrounding;
      this.createParentsIfNeeded = createParentsIfNeeded;
      this.createParentsAsContainers = createParentsAsContainers;
      this.compress = compress;
      this.setDataIfExists = setDataIfExists;
      this.acling = new ACLing(client.getAclProvider(), aclList);
      this.storingStat = storingStat;
      this.ttl = ttl;
      if (doProtected) {
         this.protectedMode.setProtectedMode();
      }

   }

   public void setSetDataIfExistsVersion(int version) {
      this.setDataIfExistsVersion = version;
   }

   public CreateBuilder2 orSetData() {
      return this.orSetData(-1);
   }

   public CreateBuilder2 orSetData(int version) {
      this.setDataIfExists = true;
      this.setDataIfExistsVersion = version;
      return this;
   }

   public CreateBuilder2 idempotent() {
      this.idempotent = true;
      return this;
   }

   public CreateBuilderMain withTtl(long ttl) {
      this.ttl = ttl;
      return this;
   }

   TransactionCreateBuilder asTransactionCreateBuilder(final Object context, final CuratorMultiTransactionRecord transaction) {
      return new TransactionCreateBuilder() {
         public PathAndBytesable withACL(List aclList) {
            return this.withACL(aclList, false);
         }

         public PathAndBytesable withACL(List aclList, boolean applyToParents) {
            CreateBuilderImpl.this.withACL(aclList, applyToParents);
            return this;
         }

         public TransactionCreateBuilder2 withTtl(long ttl) {
            CreateBuilderImpl.this.withTtl(ttl);
            return this;
         }

         public ACLPathAndBytesable withMode(CreateMode mode) {
            CreateBuilderImpl.this.withMode(mode);
            return this;
         }

         public ACLCreateModePathAndBytesable compressed() {
            CreateBuilderImpl.this.compressed();
            return this;
         }

         public Object forPath(String path) throws Exception {
            return this.forPath(path, CreateBuilderImpl.this.client.getDefaultData());
         }

         public Object forPath(String path, byte[] data) throws Exception {
            if (CreateBuilderImpl.this.compress) {
               data = CreateBuilderImpl.this.client.getCompressionProvider().compress(path, data);
            }

            String fixedPath = CreateBuilderImpl.this.client.fixForNamespace(path);
            transaction.add(Op.create(fixedPath, data, CreateBuilderImpl.this.acling.getAclList(path), CreateBuilderImpl.this.createMode, CreateBuilderImpl.this.ttl), OperationType.CREATE, path);
            return context;
         }
      };
   }

   public CreateBackgroundModeStatACLable compressed() {
      this.compress = true;
      return new CreateBackgroundModeStatACLable() {
         public CreateBackgroundModeACLable storingStatIn(Stat stat) {
            CreateBuilderImpl.this.storingStat = stat;
            return CreateBuilderImpl.this.asCreateBackgroundModeACLable();
         }

         public ACLCreateModePathAndBytesable creatingParentsIfNeeded() {
            CreateBuilderImpl.this.createParentsIfNeeded = true;
            return CreateBuilderImpl.this.asACLCreateModePathAndBytesable();
         }

         public ACLCreateModePathAndBytesable creatingParentContainersIfNeeded() {
            CreateBuilderImpl.this.setCreateParentsAsContainers();
            return this.creatingParentsIfNeeded();
         }

         public ACLPathAndBytesable withProtectedEphemeralSequential() {
            return CreateBuilderImpl.this.withProtectedEphemeralSequential();
         }

         public BackgroundPathAndBytesable withACL(List aclList) {
            return CreateBuilderImpl.this.withACL(aclList);
         }

         public BackgroundPathAndBytesable withACL(List aclList, boolean applyToParents) {
            return CreateBuilderImpl.this.withACL(aclList, applyToParents);
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Object context) {
            return CreateBuilderImpl.this.inBackground(callback, context);
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Object context, Executor executor) {
            return CreateBuilderImpl.this.inBackground(callback, context, executor);
         }

         public ErrorListenerPathAndBytesable inBackground() {
            return CreateBuilderImpl.this.inBackground();
         }

         public ErrorListenerPathAndBytesable inBackground(Object context) {
            return CreateBuilderImpl.this.inBackground(context);
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback) {
            return CreateBuilderImpl.this.inBackground(callback);
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Executor executor) {
            return CreateBuilderImpl.this.inBackground(callback, executor);
         }

         public ACLBackgroundPathAndBytesable withMode(CreateMode mode) {
            return CreateBuilderImpl.this.withMode(mode);
         }

         public String forPath(String path, byte[] data) throws Exception {
            return CreateBuilderImpl.this.forPath(path, data);
         }

         public String forPath(String path) throws Exception {
            return CreateBuilderImpl.this.forPath(path);
         }
      };
   }

   public ACLBackgroundPathAndBytesable withACL(List aclList) {
      return this.withACL(aclList, false);
   }

   public ACLBackgroundPathAndBytesable withACL(List aclList, boolean applyToParents) {
      this.acling = new ACLing(this.client.getAclProvider(), aclList, applyToParents);
      return new ACLBackgroundPathAndBytesable() {
         public BackgroundPathAndBytesable withACL(List aclList) {
            return CreateBuilderImpl.this.withACL(aclList);
         }

         public BackgroundPathAndBytesable withACL(List aclList, boolean applyToParents) {
            return CreateBuilderImpl.this.withACL(aclList, applyToParents);
         }

         public ErrorListenerPathAndBytesable inBackground() {
            return CreateBuilderImpl.this.inBackground();
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Object context) {
            return CreateBuilderImpl.this.inBackground(callback, context);
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Object context, Executor executor) {
            return CreateBuilderImpl.this.inBackground(callback, context, executor);
         }

         public ErrorListenerPathAndBytesable inBackground(Object context) {
            return CreateBuilderImpl.this.inBackground(context);
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback) {
            return CreateBuilderImpl.this.inBackground(callback);
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Executor executor) {
            return CreateBuilderImpl.this.inBackground(callback, executor);
         }

         public String forPath(String path, byte[] data) throws Exception {
            return CreateBuilderImpl.this.forPath(path, data);
         }

         public String forPath(String path) throws Exception {
            return CreateBuilderImpl.this.forPath(path);
         }
      };
   }

   public ProtectACLCreateModeStatPathAndBytesable creatingParentContainersIfNeeded() {
      this.setCreateParentsAsContainers();
      return this.creatingParentsIfNeeded();
   }

   private void setCreateParentsAsContainers() {
      if (this.client.useContainerParentsIfAvailable()) {
         this.createParentsAsContainers = true;
      }

   }

   public ProtectACLCreateModeStatPathAndBytesable creatingParentsIfNeeded() {
      this.createParentsIfNeeded = true;
      return new ProtectACLCreateModeStatPathAndBytesable() {
         public ACLCreateModeBackgroundPathAndBytesable withProtection() {
            return CreateBuilderImpl.this.withProtection();
         }

         public BackgroundPathAndBytesable withACL(List aclList) {
            return this.withACL(aclList, false);
         }

         public BackgroundPathAndBytesable withACL(List aclList, boolean applyToParents) {
            return CreateBuilderImpl.this.withACL(aclList, applyToParents);
         }

         public ErrorListenerPathAndBytesable inBackground() {
            return CreateBuilderImpl.this.inBackground();
         }

         public ErrorListenerPathAndBytesable inBackground(Object context) {
            return CreateBuilderImpl.this.inBackground(context);
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback) {
            return CreateBuilderImpl.this.inBackground(callback);
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Object context) {
            return CreateBuilderImpl.this.inBackground(callback, context);
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Executor executor) {
            return CreateBuilderImpl.this.inBackground(callback, executor);
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Object context, Executor executor) {
            return CreateBuilderImpl.this.inBackground(callback, context, executor);
         }

         public ACLBackgroundPathAndBytesable withMode(CreateMode mode) {
            return CreateBuilderImpl.this.withMode(mode);
         }

         public String forPath(String path, byte[] data) throws Exception {
            return CreateBuilderImpl.this.forPath(path, data);
         }

         public String forPath(String path) throws Exception {
            return CreateBuilderImpl.this.forPath(path);
         }

         public ACLBackgroundPathAndBytesable storingStatIn(Stat stat) {
            CreateBuilderImpl.this.storingStat = stat;
            return CreateBuilderImpl.this;
         }
      };
   }

   public ACLCreateModeStatBackgroundPathAndBytesable withProtection() {
      this.protectedMode.setProtectedMode();
      return this.asACLCreateModeStatBackgroundPathAndBytesable();
   }

   public ACLPathAndBytesable withProtectedEphemeralSequential() {
      this.protectedMode.setProtectedMode();
      this.createMode = CreateMode.EPHEMERAL_SEQUENTIAL;
      return new ACLPathAndBytesable() {
         public PathAndBytesable withACL(List aclList) {
            return CreateBuilderImpl.this.withACL(aclList);
         }

         public PathAndBytesable withACL(List aclList, boolean applyToParents) {
            return CreateBuilderImpl.this.withACL(aclList, applyToParents);
         }

         public String forPath(String path, byte[] data) throws Exception {
            return CreateBuilderImpl.this.forPath(path, data);
         }

         public String forPath(String path) throws Exception {
            return CreateBuilderImpl.this.forPath(path);
         }
      };
   }

   public ACLBackgroundPathAndBytesable withMode(CreateMode mode) {
      this.createMode = mode;
      return this;
   }

   public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Object context) {
      this.backgrounding = new Backgrounding(callback, context);
      return this;
   }

   public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Object context, Executor executor) {
      this.backgrounding = new Backgrounding(this.client, callback, context, executor);
      return this;
   }

   public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback) {
      this.backgrounding = new Backgrounding(callback);
      return this;
   }

   public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Executor executor) {
      this.backgrounding = new Backgrounding(this.client, callback, executor);
      return this;
   }

   public ErrorListenerPathAndBytesable inBackground() {
      this.backgrounding = new Backgrounding(true);
      return this;
   }

   public ErrorListenerPathAndBytesable inBackground(Object context) {
      this.backgrounding = new Backgrounding(context);
      return this;
   }

   public PathAndBytesable withUnhandledErrorListener(UnhandledErrorListener listener) {
      this.backgrounding = new Backgrounding(this.backgrounding, listener);
      return this;
   }

   public String forPath(String path) throws Exception {
      return this.forPath(path, this.client.getDefaultData());
   }

   public String forPath(String givenPath, byte[] data) throws Exception {
      if (this.compress) {
         data = this.client.getCompressionProvider().compress(givenPath, data);
      }

      String adjustedPath = this.adjustPath(this.client.fixForNamespace(givenPath, this.createMode.isSequential()));
      List<ACL> aclList = this.acling.getAclList(adjustedPath);
      this.client.getSchemaSet().getSchema(givenPath).validateCreate(this.createMode, givenPath, data, aclList);
      String returnPath = null;
      if (this.backgrounding.inBackground()) {
         this.pathInBackground(adjustedPath, data, givenPath);
      } else {
         String path = this.protectedPathInForeground(adjustedPath, data, aclList);
         returnPath = this.client.unfixForNamespace(path);
      }

      return returnPath;
   }

   private String protectedPathInForeground(String adjustedPath, byte[] data, List aclList) throws Exception {
      try {
         return this.pathInForeground(adjustedPath, data, aclList);
      } catch (Exception var5) {
         ThreadUtils.checkInterrupted(var5);
         if ((var5 instanceof KeeperException.ConnectionLossException || !(var5 instanceof KeeperException)) && this.protectedMode.doProtected()) {
            (new FindAndDeleteProtectedNodeInBackground(this.client, ZKPaths.getPathAndNode(adjustedPath).getPath(), this.protectedMode.protectedId())).execute();
            this.protectedMode.resetProtectedId();
         }

         throw var5;
      }
   }

   public CuratorEventType getBackgroundEventType() {
      return CuratorEventType.CREATE;
   }

   public void performBackgroundOperation(final OperationAndData operationAndData) throws Exception {
      try {
         final OperationTrace trace = this.client.getZookeeperClient().startAdvancedTracer("CreateBuilderImpl-Background");
         final byte[] data = ((PathAndBytes)operationAndData.getData()).getData();
         AsyncCallback.Create2Callback callback = new AsyncCallback.Create2Callback() {
            public void processResult(int rc, String path, Object ctx, String name, Stat stat) {
               trace.setReturnCode(rc).setRequestBytesLength(data).setPath(path).commit();
               if (stat != null && CreateBuilderImpl.this.storingStat != null) {
                  DataTree.copyStat(stat, CreateBuilderImpl.this.storingStat);
               }

               if (rc == Code.NONODE.intValue() && CreateBuilderImpl.this.createParentsIfNeeded) {
                  CreateBuilderImpl.backgroundCreateParentsThenNode(CreateBuilderImpl.this.client, operationAndData, ((PathAndBytes)operationAndData.getData()).getPath(), CreateBuilderImpl.this.acling.getACLProviderForParents(), CreateBuilderImpl.this.createParentsAsContainers);
               } else if (rc == Code.NODEEXISTS.intValue() && CreateBuilderImpl.this.setDataIfExists) {
                  CreateBuilderImpl.this.backgroundSetData(CreateBuilderImpl.this.client, operationAndData, ((PathAndBytes)operationAndData.getData()).getPath(), CreateBuilderImpl.this.backgrounding);
               } else if (rc == Code.NODEEXISTS.intValue() && CreateBuilderImpl.this.idempotent) {
                  CreateBuilderImpl.this.backgroundCheckIdempotent(CreateBuilderImpl.this.client, operationAndData, ((PathAndBytes)operationAndData.getData()).getPath(), CreateBuilderImpl.this.backgrounding);
               } else {
                  CreateBuilderImpl.this.sendBackgroundResponse(rc, path, ctx, name, stat, operationAndData);
               }

            }
         };
         this.client.getZooKeeper().create(((PathAndBytes)operationAndData.getData()).getPath(), data, this.acling.getAclList(((PathAndBytes)operationAndData.getData()).getPath()), this.createMode, callback, this.backgrounding.getContext(), this.ttl);
      } catch (Throwable e) {
         this.backgrounding.checkError(e, (Watching)null);
      }

   }

   public CreateProtectACLCreateModePathAndBytesable storingStatIn(Stat stat) {
      this.storingStat = stat;
      return new CreateProtectACLCreateModePathAndBytesable() {
         public BackgroundPathAndBytesable withACL(List aclList) {
            return CreateBuilderImpl.this.withACL(aclList);
         }

         public BackgroundPathAndBytesable withACL(List aclList, boolean applyToParents) {
            return CreateBuilderImpl.this.withACL(aclList, applyToParents);
         }

         public ErrorListenerPathAndBytesable inBackground() {
            return CreateBuilderImpl.this.inBackground();
         }

         public ErrorListenerPathAndBytesable inBackground(Object context) {
            return CreateBuilderImpl.this.inBackground(context);
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback) {
            return CreateBuilderImpl.this.inBackground(callback);
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Object context) {
            return CreateBuilderImpl.this.inBackground(callback, context);
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Executor executor) {
            return CreateBuilderImpl.this.inBackground(callback, executor);
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Object context, Executor executor) {
            return CreateBuilderImpl.this.inBackground(callback, context, executor);
         }

         public String forPath(String path, byte[] data) throws Exception {
            return CreateBuilderImpl.this.forPath(path, data);
         }

         public String forPath(String path) throws Exception {
            return CreateBuilderImpl.this.forPath(path);
         }

         public ACLBackgroundPathAndBytesable withMode(CreateMode mode) {
            return CreateBuilderImpl.this.withMode(mode);
         }

         public ACLCreateModeBackgroundPathAndBytesable withProtection() {
            return CreateBuilderImpl.this.withProtection();
         }

         public ProtectACLCreateModePathAndBytesable creatingParentsIfNeeded() {
            return CreateBuilderImpl.this.creatingParentsIfNeeded();
         }

         public ProtectACLCreateModePathAndBytesable creatingParentContainersIfNeeded() {
            return CreateBuilderImpl.this.creatingParentContainersIfNeeded();
         }
      };
   }

   static void backgroundCreateParentsThenNode(final CuratorFrameworkImpl client, final OperationAndData mainOperationAndData, final String path, final InternalACLProvider aclProvider, final boolean createParentsAsContainers) {
      BackgroundOperation<T> operation = new BackgroundOperation() {
         public void performBackgroundOperation(OperationAndData dummy) throws Exception {
            try {
               ZKPaths.mkdirs(client.getZooKeeper(), path, false, aclProvider, createParentsAsContainers);
            } catch (KeeperException e) {
               if (!client.getZookeeperClient().getRetryPolicy().allowRetry(e)) {
                  throw e;
               }
            }

            client.queueOperation(mainOperationAndData);
         }

         public CuratorEventType getBackgroundEventType() {
            return CuratorEventType.CREATE;
         }
      };
      OperationAndData<T> parentOperation = new OperationAndData(operation, mainOperationAndData);
      client.queueOperation(parentOperation);
   }

   private void backgroundSetData(final CuratorFrameworkImpl client, final OperationAndData mainOperationAndData, final String path, final Backgrounding backgrounding) {
      final AsyncCallback.StatCallback statCallback = new AsyncCallback.StatCallback() {
         public void processResult(int rc, String path, Object ctx, Stat stat) {
            if (rc == Code.NONODE.intValue()) {
               client.queueOperation(mainOperationAndData);
            } else {
               CreateBuilderImpl.this.sendBackgroundResponse(rc, path, ctx, path, stat, mainOperationAndData);
            }

         }
      };
      BackgroundOperation<PathAndBytes> operation = new BackgroundOperation() {
         public void performBackgroundOperation(OperationAndData op) throws Exception {
            client.getZooKeeper().setData(path, ((PathAndBytes)mainOperationAndData.getData()).getData(), CreateBuilderImpl.this.setDataIfExistsVersion, statCallback, backgrounding.getContext());
         }

         public CuratorEventType getBackgroundEventType() {
            return CuratorEventType.CREATE;
         }
      };
      client.queueOperation(new OperationAndData(operation, mainOperationAndData));
   }

   private void backgroundCheckIdempotent(final CuratorFrameworkImpl client, final OperationAndData mainOperationAndData, final String path, final Backgrounding backgrounding) {
      final AsyncCallback.DataCallback dataCallback = new AsyncCallback.DataCallback() {
         public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
            if (rc == Code.NONODE.intValue()) {
               client.queueOperation(mainOperationAndData);
            } else {
               if (rc == Code.OK.intValue()) {
                  if (CreateBuilderImpl.this.failNextIdempotentCheckForTesting) {
                     CreateBuilderImpl.this.failNextIdempotentCheckForTesting = false;
                     rc = Code.CONNECTIONLOSS.intValue();
                  } else if (!IdempotentUtils.matches(0, ((PathAndBytes)mainOperationAndData.getData()).getData(), stat.getVersion(), data)) {
                     rc = Code.NODEEXISTS.intValue();
                  }
               }

               CreateBuilderImpl.this.sendBackgroundResponse(rc, path, ctx, path, stat, mainOperationAndData);
            }

         }
      };
      BackgroundOperation<PathAndBytes> operation = new BackgroundOperation() {
         public void performBackgroundOperation(OperationAndData op) throws Exception {
            client.getZooKeeper().getData(path, false, dataCallback, backgrounding.getContext());
         }

         public CuratorEventType getBackgroundEventType() {
            return CuratorEventType.CREATE;
         }
      };
      client.queueOperation(new OperationAndData(operation, mainOperationAndData));
   }

   private void sendBackgroundResponse(int rc, String path, Object ctx, String name, Stat stat, OperationAndData operationAndData) {
      sendBackgroundResponse(this.client, rc, path, ctx, name, stat, operationAndData);
   }

   private static void sendBackgroundResponse(CuratorFrameworkImpl client, int rc, String path, Object ctx, String name, Stat stat, OperationAndData operationAndData) {
      CuratorEvent event = new CuratorEventImpl(client, CuratorEventType.CREATE, rc, path, name, ctx, stat, (byte[])null, (List)null, (WatchedEvent)null, (List)null, (List)null);
      client.processBackgroundOperation(operationAndData, event);
   }

   private ACLCreateModePathAndBytesable asACLCreateModePathAndBytesable() {
      return new ACLCreateModePathAndBytesable() {
         public PathAndBytesable withACL(List aclList) {
            return CreateBuilderImpl.this.withACL(aclList);
         }

         public PathAndBytesable withACL(List aclList, boolean applyToParents) {
            CreateBuilderImpl.this.withACL(aclList, applyToParents);
            return this;
         }

         public ACLPathAndBytesable withMode(CreateMode mode) {
            CreateBuilderImpl.this.createMode = mode;
            return new ACLPathAndBytesable() {
               public PathAndBytesable withACL(List aclList) {
                  return CreateBuilderImpl.this.withACL(aclList);
               }

               public PathAndBytesable withACL(List aclList, boolean applyToParents) {
                  return CreateBuilderImpl.this.withACL(aclList, applyToParents);
               }

               public String forPath(String path, byte[] data) throws Exception {
                  return CreateBuilderImpl.this.forPath(path, data);
               }

               public String forPath(String path) throws Exception {
                  return CreateBuilderImpl.this.forPath(path);
               }
            };
         }

         public String forPath(String path, byte[] data) throws Exception {
            return CreateBuilderImpl.this.forPath(path, data);
         }

         public String forPath(String path) throws Exception {
            return CreateBuilderImpl.this.forPath(path);
         }
      };
   }

   private CreateBackgroundModeACLable asCreateBackgroundModeACLable() {
      return new CreateBackgroundModeACLable() {
         public BackgroundPathAndBytesable withACL(List aclList) {
            return CreateBuilderImpl.this.withACL(aclList);
         }

         public BackgroundPathAndBytesable withACL(List aclList, boolean applyToParents) {
            return CreateBuilderImpl.this.withACL(aclList, applyToParents);
         }

         public ACLBackgroundPathAndBytesable withMode(CreateMode mode) {
            return CreateBuilderImpl.this.withMode(mode);
         }

         public String forPath(String path) throws Exception {
            return CreateBuilderImpl.this.forPath(path);
         }

         public String forPath(String path, byte[] data) throws Exception {
            return CreateBuilderImpl.this.forPath(path, data);
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Object context, Executor executor) {
            return CreateBuilderImpl.this.inBackground(callback, context, executor);
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Executor executor) {
            return CreateBuilderImpl.this.inBackground(callback, executor);
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Object context) {
            return CreateBuilderImpl.this.inBackground(callback, context);
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback) {
            return CreateBuilderImpl.this.inBackground(callback);
         }

         public ErrorListenerPathAndBytesable inBackground(Object context) {
            return CreateBuilderImpl.this.inBackground(context);
         }

         public ErrorListenerPathAndBytesable inBackground() {
            return CreateBuilderImpl.this.inBackground();
         }

         public ACLPathAndBytesable withProtectedEphemeralSequential() {
            return CreateBuilderImpl.this.withProtectedEphemeralSequential();
         }

         public ACLCreateModePathAndBytesable creatingParentsIfNeeded() {
            CreateBuilderImpl.this.createParentsIfNeeded = true;
            return CreateBuilderImpl.this.asACLCreateModePathAndBytesable();
         }

         public ACLCreateModePathAndBytesable creatingParentContainersIfNeeded() {
            CreateBuilderImpl.this.setCreateParentsAsContainers();
            return CreateBuilderImpl.this.asACLCreateModePathAndBytesable();
         }
      };
   }

   private ACLCreateModeStatBackgroundPathAndBytesable asACLCreateModeStatBackgroundPathAndBytesable() {
      return new ACLCreateModeStatBackgroundPathAndBytesable() {
         public BackgroundPathAndBytesable withACL(List aclList) {
            return CreateBuilderImpl.this.withACL(aclList);
         }

         public BackgroundPathAndBytesable withACL(List aclList, boolean applyToParents) {
            CreateBuilderImpl.this.withACL(aclList, applyToParents);
            return this;
         }

         public ErrorListenerPathAndBytesable inBackground() {
            return CreateBuilderImpl.this.inBackground();
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Object context, Executor executor) {
            return CreateBuilderImpl.this.inBackground(callback, context, executor);
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Executor executor) {
            return CreateBuilderImpl.this.inBackground(callback, executor);
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Object context) {
            return CreateBuilderImpl.this.inBackground(callback, context);
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback) {
            return CreateBuilderImpl.this.inBackground(callback);
         }

         public ErrorListenerPathAndBytesable inBackground(Object context) {
            return CreateBuilderImpl.this.inBackground(context);
         }

         public String forPath(String path) throws Exception {
            return CreateBuilderImpl.this.forPath(path);
         }

         public String forPath(String path, byte[] data) throws Exception {
            return CreateBuilderImpl.this.forPath(path, data);
         }

         public ACLBackgroundPathAndBytesable withMode(CreateMode mode) {
            return CreateBuilderImpl.this.withMode(mode);
         }

         public ACLCreateModeBackgroundPathAndBytesable storingStatIn(Stat stat) {
            CreateBuilderImpl.this.storingStat = stat;
            return CreateBuilderImpl.this;
         }
      };
   }

   private void pathInBackground(final String path, final byte[] data, final String givenPath) {
      final AtomicBoolean firstTime = new AtomicBoolean(true);
      OperationAndData<PathAndBytes> operationAndData = new OperationAndData(this, new PathAndBytes(path, data), this.backgrounding.getCallback(), new OperationAndData.ErrorCallback() {
         public void retriesExhausted(OperationAndData operationAndData) {
            if (CreateBuilderImpl.this.protectedMode.doProtected()) {
               (new FindAndDeleteProtectedNodeInBackground(CreateBuilderImpl.this.client, ZKPaths.getPathAndNode(path).getPath(), CreateBuilderImpl.this.protectedMode.protectedId())).execute();
               CreateBuilderImpl.this.protectedMode.resetProtectedId();
            }

         }
      }, this.backgrounding.getContext(), (Watching)null) {
         void callPerformBackgroundOperation() throws Exception {
            boolean callSuper = true;
            boolean localFirstTime = firstTime.getAndSet(false) && !CreateBuilderImpl.this.debugForceFindProtectedNode;
            CreateBuilderImpl.this.protectedMode.checkSetSessionId(CreateBuilderImpl.this.client, CreateBuilderImpl.this.createMode);
            if (!localFirstTime && CreateBuilderImpl.this.protectedMode.doProtected()) {
               CreateBuilderImpl.this.debugForceFindProtectedNode = false;
               String createdPath = null;

               try {
                  createdPath = CreateBuilderImpl.this.findProtectedNodeInForeground(path);
               } catch (KeeperException.ConnectionLossException var7) {
                  CreateBuilderImpl.this.sendBackgroundResponse(Code.CONNECTIONLOSS.intValue(), path, CreateBuilderImpl.this.backgrounding.getContext(), (String)null, (Stat)null, this);
                  callSuper = false;
               }

               if (createdPath != null) {
                  try {
                     CreateBuilderImpl.this.sendBackgroundResponse(Code.OK.intValue(), createdPath, CreateBuilderImpl.this.backgrounding.getContext(), createdPath, (Stat)null, this);
                  } catch (Exception e) {
                     ThreadUtils.checkInterrupted(e);
                     CreateBuilderImpl.this.client.logError("Processing protected create for path: " + givenPath, e);
                  }

                  callSuper = false;
               }
            }

            if (CreateBuilderImpl.this.failBeforeNextCreateForTesting) {
               CreateBuilderImpl.this.failBeforeNextCreateForTesting = false;
               throw new KeeperException.ConnectionLossException();
            } else if (CreateBuilderImpl.this.failNextCreateForTesting) {
               CreateBuilderImpl.this.failNextCreateForTesting = false;

               try {
                  CreateBuilderImpl.this.pathInForeground(path, data, CreateBuilderImpl.this.acling.getAclList(path));
               } catch (KeeperException.NodeExistsException e) {
                  CreateBuilderImpl.this.client.logError("NodeExists while injecting failure after create, ignoring: " + givenPath, e);
               }

               throw new KeeperException.ConnectionLossException();
            } else {
               if (callSuper) {
                  super.callPerformBackgroundOperation();
               }

            }
         }
      };
      this.client.processBackgroundOperation(operationAndData, (CuratorEvent)null);
   }

   private String pathInForeground(final String path, final byte[] data, final List aclList) throws Exception {
      OperationTrace trace = this.client.getZookeeperClient().startAdvancedTracer("CreateBuilderImpl-Foreground");
      final AtomicBoolean firstTime = new AtomicBoolean(true);
      String returnPath = (String)RetryLoop.callWithRetry(this.client.getZookeeperClient(), new Callable() {
         public String call() throws Exception {
            boolean localFirstTime = firstTime.getAndSet(false) && !CreateBuilderImpl.this.debugForceFindProtectedNode;
            CreateBuilderImpl.this.protectedMode.checkSetSessionId(CreateBuilderImpl.this.client, CreateBuilderImpl.this.createMode);
            String createdPath = null;
            if (!localFirstTime && CreateBuilderImpl.this.protectedMode.doProtected()) {
               CreateBuilderImpl.this.debugForceFindProtectedNode = false;
               createdPath = CreateBuilderImpl.this.findProtectedNodeInForeground(path);
            }

            if (createdPath == null) {
               try {
                  if (CreateBuilderImpl.this.failBeforeNextCreateForTesting) {
                     CreateBuilderImpl.this.failBeforeNextCreateForTesting = false;
                     throw new KeeperException.ConnectionLossException();
                  }

                  createdPath = CreateBuilderImpl.this.client.getZooKeeper().create(path, data, aclList, CreateBuilderImpl.this.createMode, CreateBuilderImpl.this.storingStat, CreateBuilderImpl.this.ttl);
               } catch (KeeperException.NoNodeException e) {
                  if (!CreateBuilderImpl.this.createParentsIfNeeded) {
                     throw e;
                  }

                  ZKPaths.mkdirs(CreateBuilderImpl.this.client.getZooKeeper(), path, false, CreateBuilderImpl.this.acling.getACLProviderForParents(), CreateBuilderImpl.this.createParentsAsContainers);
                  createdPath = CreateBuilderImpl.this.client.getZooKeeper().create(path, data, CreateBuilderImpl.this.acling.getAclList(path), CreateBuilderImpl.this.createMode, CreateBuilderImpl.this.storingStat, CreateBuilderImpl.this.ttl);
               } catch (KeeperException.NodeExistsException e) {
                  if (CreateBuilderImpl.this.setDataIfExists) {
                     Stat setStat = CreateBuilderImpl.this.client.getZooKeeper().setData(path, data, CreateBuilderImpl.this.setDataIfExistsVersion);
                     if (CreateBuilderImpl.this.storingStat != null) {
                        DataTree.copyStat(setStat, CreateBuilderImpl.this.storingStat);
                     }

                     createdPath = path;
                  } else {
                     if (!CreateBuilderImpl.this.idempotent) {
                        throw e;
                     }

                     if (CreateBuilderImpl.this.failNextIdempotentCheckForTesting) {
                        CreateBuilderImpl.this.failNextIdempotentCheckForTesting = false;
                        throw new KeeperException.ConnectionLossException();
                     }

                     Stat getStat = new Stat();
                     byte[] existingData = CreateBuilderImpl.this.client.getZooKeeper().getData(path, false, getStat);
                     if (!IdempotentUtils.matches(0, data, getStat.getVersion(), existingData)) {
                        throw e;
                     }

                     if (CreateBuilderImpl.this.storingStat != null) {
                        DataTree.copyStat(getStat, CreateBuilderImpl.this.storingStat);
                     }

                     createdPath = path;
                  }
               }
            }

            if (CreateBuilderImpl.this.failNextCreateForTesting) {
               CreateBuilderImpl.this.failNextCreateForTesting = false;
               throw new KeeperException.ConnectionLossException();
            } else {
               return createdPath;
            }
         }
      });
      trace.setRequestBytesLength(data).setPath(path).commit();
      return returnPath;
   }

   private String findProtectedNodeInForeground(final String path) throws Exception {
      OperationTrace trace = this.client.getZookeeperClient().startAdvancedTracer("CreateBuilderImpl-findProtectedNodeInForeground");
      String returnPath = (String)RetryLoop.callWithRetry(this.client.getZookeeperClient(), new Callable() {
         public String call() throws Exception {
            String foundNode = null;

            try {
               ZKPaths.PathAndNode pathAndNode = ZKPaths.getPathAndNode(path);
               List<String> children = CreateBuilderImpl.this.client.getZooKeeper().getChildren(pathAndNode.getPath(), false);
               foundNode = CreateBuilderImpl.findNode(children, pathAndNode.getPath(), CreateBuilderImpl.this.protectedMode.protectedId());
               CreateBuilderImpl.this.log.debug("Protected mode findNode result: {}", foundNode);
               foundNode = CreateBuilderImpl.this.protectedMode.validateFoundNode(CreateBuilderImpl.this.client, CreateBuilderImpl.this.createMode, foundNode);
            } catch (KeeperException.NoNodeException var4) {
            }

            return foundNode;
         }
      });
      trace.setPath(path).commit();
      return returnPath;
   }

   @VisibleForTesting
   String adjustPath(String path) throws Exception {
      return ProtectedUtils.toProtectedZNodePath(path, this.protectedMode.protectedId());
   }

   static String findNode(List children, String path, String protectedId) {
      final String protectedPrefix = ProtectedUtils.getProtectedPrefix(protectedId);
      String foundNode = (String)Iterables.find(children, new Predicate() {
         public boolean apply(String node) {
            return node.startsWith(protectedPrefix);
         }
      }, (Object)null);
      if (foundNode != null) {
         foundNode = ZKPaths.makePath(path, foundNode);
      }

      return foundNode;
   }
}
