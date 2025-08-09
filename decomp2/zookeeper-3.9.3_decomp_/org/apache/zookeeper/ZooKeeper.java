package org.apache.zookeeper;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.jute.Record;
import org.apache.yetus.audience.InterfaceAudience.Public;
import org.apache.zookeeper.client.ConnectStringParser;
import org.apache.zookeeper.client.HostProvider;
import org.apache.zookeeper.client.StaticHostProvider;
import org.apache.zookeeper.client.ZKClientConfig;
import org.apache.zookeeper.client.ZooKeeperSaslClient;
import org.apache.zookeeper.common.PathUtils;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.proto.AddWatchRequest;
import org.apache.zookeeper.proto.CheckWatchesRequest;
import org.apache.zookeeper.proto.Create2Response;
import org.apache.zookeeper.proto.CreateRequest;
import org.apache.zookeeper.proto.CreateResponse;
import org.apache.zookeeper.proto.CreateTTLRequest;
import org.apache.zookeeper.proto.DeleteRequest;
import org.apache.zookeeper.proto.ErrorResponse;
import org.apache.zookeeper.proto.ExistsRequest;
import org.apache.zookeeper.proto.GetACLRequest;
import org.apache.zookeeper.proto.GetACLResponse;
import org.apache.zookeeper.proto.GetAllChildrenNumberRequest;
import org.apache.zookeeper.proto.GetAllChildrenNumberResponse;
import org.apache.zookeeper.proto.GetChildren2Request;
import org.apache.zookeeper.proto.GetChildren2Response;
import org.apache.zookeeper.proto.GetChildrenRequest;
import org.apache.zookeeper.proto.GetChildrenResponse;
import org.apache.zookeeper.proto.GetDataRequest;
import org.apache.zookeeper.proto.GetDataResponse;
import org.apache.zookeeper.proto.GetEphemeralsRequest;
import org.apache.zookeeper.proto.GetEphemeralsResponse;
import org.apache.zookeeper.proto.RemoveWatchesRequest;
import org.apache.zookeeper.proto.ReplyHeader;
import org.apache.zookeeper.proto.RequestHeader;
import org.apache.zookeeper.proto.SetACLRequest;
import org.apache.zookeeper.proto.SetACLResponse;
import org.apache.zookeeper.proto.SetDataRequest;
import org.apache.zookeeper.proto.SetDataResponse;
import org.apache.zookeeper.proto.SyncRequest;
import org.apache.zookeeper.proto.SyncResponse;
import org.apache.zookeeper.proto.WhoAmIResponse;
import org.apache.zookeeper.server.DataTree;
import org.apache.zookeeper.server.EphemeralType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Public
public class ZooKeeper implements AutoCloseable {
   /** @deprecated */
   @Deprecated
   public static final String ZOOKEEPER_CLIENT_CNXN_SOCKET = "zookeeper.clientCnxnSocket";
   /** @deprecated */
   @Deprecated
   public static final String SECURE_CLIENT = "zookeeper.client.secure";
   protected final ClientCnxn cnxn;
   private static final Logger LOG = LoggerFactory.getLogger(ZooKeeper.class);
   protected final HostProvider hostProvider;
   private final ZKClientConfig clientConfig;

   public void updateServerList(String connectString) throws IOException {
      ConnectStringParser connectStringParser = new ConnectStringParser(connectString);
      Collection<InetSocketAddress> serverAddresses = connectStringParser.getServerAddresses();
      ClientCnxnSocket clientCnxnSocket = this.cnxn.sendThread.getClientCnxnSocket();
      InetSocketAddress currentHost = (InetSocketAddress)clientCnxnSocket.getRemoteSocketAddress();
      boolean reconfigMode = this.hostProvider.updateServerList(serverAddresses, currentHost);
      if (reconfigMode) {
         clientCnxnSocket.testableCloseSocket();
      }

   }

   public ZooKeeperSaslClient getSaslClient() {
      return this.cnxn.getZooKeeperSaslClient();
   }

   public ZKClientConfig getClientConfig() {
      return this.clientConfig;
   }

   protected List getDataWatches() {
      return this.getWatchManager().getDataWatchList();
   }

   protected List getExistWatches() {
      return this.getWatchManager().getExistWatchList();
   }

   protected List getChildWatches() {
      return this.getWatchManager().getChildWatchList();
   }

   protected List getPersistentWatches() {
      return this.getWatchManager().getPersistentWatchList();
   }

   protected List getPersistentRecursiveWatches() {
      return this.getWatchManager().getPersistentRecursiveWatchList();
   }

   ZKWatchManager getWatchManager() {
      return this.cnxn.getWatcherManager();
   }

   public ZooKeeper(String connectString, int sessionTimeout, Watcher watcher) throws IOException {
      this(connectString, sessionTimeout, watcher, false);
   }

   public ZooKeeper(String connectString, int sessionTimeout, Watcher watcher, ZKClientConfig conf) throws IOException {
      this(connectString, sessionTimeout, watcher, false, conf);
   }

   public ZooKeeper(String connectString, int sessionTimeout, Watcher watcher, boolean canBeReadOnly, HostProvider aHostProvider) throws IOException {
      this(connectString, sessionTimeout, watcher, canBeReadOnly, aHostProvider, (ZKClientConfig)null);
   }

   public ZooKeeper(String connectString, int sessionTimeout, Watcher watcher, boolean canBeReadOnly, HostProvider hostProvider, ZKClientConfig clientConfig) throws IOException {
      LOG.info("Initiating client connection, connectString={} sessionTimeout={} watcher={}", new Object[]{connectString, sessionTimeout, watcher});
      this.clientConfig = clientConfig != null ? clientConfig : new ZKClientConfig();
      this.hostProvider = hostProvider;
      ConnectStringParser connectStringParser = new ConnectStringParser(connectString);
      this.cnxn = this.createConnection(connectStringParser.getChrootPath(), hostProvider, sessionTimeout, this.clientConfig, watcher, this.getClientCnxnSocket(), canBeReadOnly);
      this.cnxn.start();
   }

   ClientCnxn createConnection(String chrootPath, HostProvider hostProvider, int sessionTimeout, ZKClientConfig clientConfig, Watcher defaultWatcher, ClientCnxnSocket clientCnxnSocket, boolean canBeReadOnly) throws IOException {
      return new ClientCnxn(chrootPath, hostProvider, sessionTimeout, clientConfig, defaultWatcher, clientCnxnSocket, canBeReadOnly);
   }

   public ZooKeeper(String connectString, int sessionTimeout, Watcher watcher, boolean canBeReadOnly) throws IOException {
      this(connectString, sessionTimeout, watcher, canBeReadOnly, createDefaultHostProvider(connectString));
   }

   public ZooKeeper(String connectString, int sessionTimeout, Watcher watcher, boolean canBeReadOnly, ZKClientConfig conf) throws IOException {
      this(connectString, sessionTimeout, watcher, canBeReadOnly, createDefaultHostProvider(connectString), conf);
   }

   public ZooKeeper(String connectString, int sessionTimeout, Watcher watcher, long sessionId, byte[] sessionPasswd) throws IOException {
      this(connectString, sessionTimeout, watcher, sessionId, sessionPasswd, false);
   }

   public ZooKeeper(String connectString, int sessionTimeout, Watcher watcher, long sessionId, byte[] sessionPasswd, boolean canBeReadOnly, HostProvider aHostProvider) throws IOException {
      this(connectString, sessionTimeout, watcher, sessionId, sessionPasswd, canBeReadOnly, aHostProvider, (ZKClientConfig)null);
   }

   public ZooKeeper(String connectString, int sessionTimeout, Watcher watcher, long sessionId, byte[] sessionPasswd, boolean canBeReadOnly, HostProvider hostProvider, ZKClientConfig clientConfig) throws IOException {
      LOG.info("Initiating client connection, connectString={} sessionTimeout={} watcher={} sessionId=0x{} sessionPasswd={}", new Object[]{connectString, sessionTimeout, watcher, Long.toHexString(sessionId), sessionPasswd == null ? "<null>" : "<hidden>"});
      this.clientConfig = clientConfig != null ? clientConfig : new ZKClientConfig();
      ConnectStringParser connectStringParser = new ConnectStringParser(connectString);
      this.hostProvider = hostProvider;
      this.cnxn = new ClientCnxn(connectStringParser.getChrootPath(), hostProvider, sessionTimeout, this.clientConfig, watcher, this.getClientCnxnSocket(), sessionId, sessionPasswd, canBeReadOnly);
      this.cnxn.seenRwServerBefore = true;
      this.cnxn.start();
   }

   public ZooKeeper(String connectString, int sessionTimeout, Watcher watcher, long sessionId, byte[] sessionPasswd, boolean canBeReadOnly) throws IOException {
      this(connectString, sessionTimeout, watcher, sessionId, sessionPasswd, canBeReadOnly, createDefaultHostProvider(connectString));
   }

   private static HostProvider createDefaultHostProvider(String connectString) {
      return new StaticHostProvider((new ConnectStringParser(connectString)).getServerAddresses());
   }

   public Testable getTestable() {
      return new ZooKeeperTestable(this.cnxn);
   }

   public long getSessionId() {
      return this.cnxn.getSessionId();
   }

   public byte[] getSessionPasswd() {
      return this.cnxn.getSessionPasswd();
   }

   public int getSessionTimeout() {
      return this.cnxn.getSessionTimeout();
   }

   public void addAuthInfo(String scheme, byte[] auth) {
      this.cnxn.addAuthInfo(scheme, auth);
   }

   public synchronized void register(Watcher watcher) {
      this.getWatchManager().setDefaultWatcher(watcher);
   }

   public synchronized void close() throws InterruptedException {
      if (!this.cnxn.getState().isAlive()) {
         LOG.debug("Close called on already closed client");
      } else {
         LOG.debug("Closing session: 0x" + Long.toHexString(this.getSessionId()));

         try {
            this.cnxn.close();
         } catch (IOException e) {
            LOG.debug("Ignoring unexpected exception during close", e);
         }

         LOG.info("Session: 0x{} closed", Long.toHexString(this.getSessionId()));
      }
   }

   public boolean close(int waitForShutdownTimeoutMs) throws InterruptedException {
      this.close();
      return this.testableWaitForShutdown(waitForShutdownTimeoutMs);
   }

   private String prependChroot(String clientPath) {
      if (this.cnxn.chrootPath != null) {
         return clientPath.length() == 1 ? this.cnxn.chrootPath : this.cnxn.chrootPath + clientPath;
      } else {
         return clientPath;
      }
   }

   public String create(String path, byte[] data, List acl, CreateMode createMode) throws KeeperException, InterruptedException {
      PathUtils.validatePath(path, createMode.isSequential());
      EphemeralType.validateTTL(createMode, -1L);
      this.validateACL(acl);
      String serverPath = this.prependChroot(path);
      RequestHeader h = new RequestHeader();
      h.setType(createMode.isContainer() ? 19 : 1);
      CreateRequest request = new CreateRequest();
      CreateResponse response = new CreateResponse();
      request.setData(data);
      request.setFlags(createMode.toFlag());
      request.setPath(serverPath);
      request.setAcl(acl);
      ReplyHeader r = this.cnxn.submitRequest(h, request, response, (WatchRegistration)null);
      if (r.getErr() != 0) {
         throw KeeperException.create(KeeperException.Code.get(r.getErr()), path);
      } else {
         return this.cnxn.chrootPath == null ? response.getPath() : response.getPath().substring(this.cnxn.chrootPath.length());
      }
   }

   public String create(String path, byte[] data, List acl, CreateMode createMode, Stat stat) throws KeeperException, InterruptedException {
      return this.create(path, data, acl, createMode, stat, -1L);
   }

   public String create(String path, byte[] data, List acl, CreateMode createMode, Stat stat, long ttl) throws KeeperException, InterruptedException {
      PathUtils.validatePath(path, createMode.isSequential());
      EphemeralType.validateTTL(createMode, ttl);
      this.validateACL(acl);
      String serverPath = this.prependChroot(path);
      RequestHeader h = new RequestHeader();
      this.setCreateHeader(createMode, h);
      Create2Response response = new Create2Response();
      Record record = this.makeCreateRecord(createMode, serverPath, data, acl, ttl);
      ReplyHeader r = this.cnxn.submitRequest(h, record, response, (WatchRegistration)null);
      if (r.getErr() != 0) {
         throw KeeperException.create(KeeperException.Code.get(r.getErr()), path);
      } else {
         if (stat != null) {
            DataTree.copyStat(response.getStat(), stat);
         }

         return this.cnxn.chrootPath == null ? response.getPath() : response.getPath().substring(this.cnxn.chrootPath.length());
      }
   }

   private void setCreateHeader(CreateMode createMode, RequestHeader h) {
      if (createMode.isTTL()) {
         h.setType(21);
      } else {
         h.setType(createMode.isContainer() ? 19 : 15);
      }

   }

   private Record makeCreateRecord(CreateMode createMode, String serverPath, byte[] data, List acl, long ttl) {
      Record record;
      if (createMode.isTTL()) {
         CreateTTLRequest request = new CreateTTLRequest();
         request.setData(data);
         request.setFlags(createMode.toFlag());
         request.setPath(serverPath);
         request.setAcl(acl);
         request.setTtl(ttl);
         record = request;
      } else {
         CreateRequest request = new CreateRequest();
         request.setData(data);
         request.setFlags(createMode.toFlag());
         request.setPath(serverPath);
         request.setAcl(acl);
         record = request;
      }

      return record;
   }

   public void create(String path, byte[] data, List acl, CreateMode createMode, AsyncCallback.StringCallback cb, Object ctx) {
      PathUtils.validatePath(path, createMode.isSequential());
      EphemeralType.validateTTL(createMode, -1L);
      String serverPath = this.prependChroot(path);
      RequestHeader h = new RequestHeader();
      h.setType(createMode.isContainer() ? 19 : 1);
      CreateRequest request = new CreateRequest();
      CreateResponse response = new CreateResponse();
      ReplyHeader r = new ReplyHeader();
      request.setData(data);
      request.setFlags(createMode.toFlag());
      request.setPath(serverPath);
      request.setAcl(acl);
      this.cnxn.queuePacket(h, r, request, response, cb, path, serverPath, ctx, (WatchRegistration)null);
   }

   public void create(String path, byte[] data, List acl, CreateMode createMode, AsyncCallback.Create2Callback cb, Object ctx) {
      this.create(path, data, acl, createMode, cb, ctx, -1L);
   }

   public void create(String path, byte[] data, List acl, CreateMode createMode, AsyncCallback.Create2Callback cb, Object ctx, long ttl) {
      PathUtils.validatePath(path, createMode.isSequential());
      EphemeralType.validateTTL(createMode, ttl);
      String serverPath = this.prependChroot(path);
      RequestHeader h = new RequestHeader();
      this.setCreateHeader(createMode, h);
      ReplyHeader r = new ReplyHeader();
      Create2Response response = new Create2Response();
      Record record = this.makeCreateRecord(createMode, serverPath, data, acl, ttl);
      this.cnxn.queuePacket(h, r, record, response, cb, path, serverPath, ctx, (WatchRegistration)null);
   }

   public void delete(String path, int version) throws InterruptedException, KeeperException {
      PathUtils.validatePath(path);
      String serverPath;
      if (path.equals("/")) {
         serverPath = path;
      } else {
         serverPath = this.prependChroot(path);
      }

      RequestHeader h = new RequestHeader();
      h.setType(2);
      DeleteRequest request = new DeleteRequest();
      request.setPath(serverPath);
      request.setVersion(version);
      ReplyHeader r = this.cnxn.submitRequest(h, request, (Record)null, (WatchRegistration)null);
      if (r.getErr() != 0) {
         throw KeeperException.create(KeeperException.Code.get(r.getErr()), path);
      }
   }

   public List multi(Iterable ops) throws InterruptedException, KeeperException {
      for(Op op : ops) {
         op.validate();
      }

      return this.multiInternal(this.generateMultiTransaction(ops));
   }

   public void multi(Iterable ops, AsyncCallback.MultiCallback cb, Object ctx) {
      List<OpResult> results = this.validatePath(ops);
      if (results.size() > 0) {
         cb.processResult(KeeperException.Code.BADARGUMENTS.intValue(), (String)null, ctx, results);
      } else {
         this.multiInternal(this.generateMultiTransaction(ops), cb, ctx);
      }
   }

   private List validatePath(Iterable ops) {
      List<OpResult> results = new ArrayList();
      boolean error = false;

      for(Op op : ops) {
         try {
            op.validate();
         } catch (IllegalArgumentException iae) {
            LOG.error("Unexpected exception", iae);
            OpResult.ErrorResult err = new OpResult.ErrorResult(KeeperException.Code.BADARGUMENTS.intValue());
            results.add(err);
            error = true;
            continue;
         } catch (KeeperException ke) {
            LOG.error("Unexpected exception", ke);
            OpResult.ErrorResult err = new OpResult.ErrorResult(ke.code().intValue());
            results.add(err);
            error = true;
            continue;
         }

         OpResult.ErrorResult err = new OpResult.ErrorResult(KeeperException.Code.RUNTIMEINCONSISTENCY.intValue());
         results.add(err);
      }

      if (!error) {
         results.clear();
      }

      return results;
   }

   private MultiOperationRecord generateMultiTransaction(Iterable ops) {
      List<Op> transaction = new ArrayList();

      for(Op op : ops) {
         transaction.add(this.withRootPrefix(op));
      }

      return new MultiOperationRecord(transaction);
   }

   private Op withRootPrefix(Op op) {
      if (null != op.getPath()) {
         String serverPath = this.prependChroot(op.getPath());
         if (!op.getPath().equals(serverPath)) {
            return op.withChroot(serverPath);
         }
      }

      return op;
   }

   protected void multiInternal(MultiOperationRecord request, AsyncCallback.MultiCallback cb, Object ctx) throws IllegalArgumentException {
      if (request.size() == 0) {
         this.cnxn.queueCallback(cb, KeeperException.Code.OK.intValue(), (String)null, ctx);
      } else {
         RequestHeader h = new RequestHeader();
         switch (request.getOpKind()) {
            case TRANSACTION:
               h.setType(14);
               break;
            case READ:
               h.setType(22);
               break;
            default:
               throw new IllegalArgumentException("Unsupported OpKind: " + request.getOpKind());
         }

         MultiResponse response = new MultiResponse();
         this.cnxn.queuePacket(h, new ReplyHeader(), request, response, cb, (String)null, (String)null, ctx, (WatchRegistration)null);
      }
   }

   protected List multiInternal(MultiOperationRecord request) throws InterruptedException, KeeperException, IllegalArgumentException {
      RequestHeader h = new RequestHeader();
      if (request.size() == 0) {
         return Collections.emptyList();
      } else {
         switch (request.getOpKind()) {
            case TRANSACTION:
               h.setType(14);
               break;
            case READ:
               h.setType(22);
               break;
            default:
               throw new IllegalArgumentException("Unsupported OpKind: " + request.getOpKind());
         }

         MultiResponse response = new MultiResponse();
         ReplyHeader r = this.cnxn.submitRequest(h, request, response, (WatchRegistration)null);
         if (r.getErr() != 0) {
            throw KeeperException.create(KeeperException.Code.get(r.getErr()));
         } else {
            List<OpResult> results = response.getResultList();
            if (request.getOpKind() == Op.OpKind.READ) {
               return results;
            } else {
               OpResult.ErrorResult fatalError = null;

               for(OpResult result : results) {
                  if (result instanceof OpResult.ErrorResult && ((OpResult.ErrorResult)result).getErr() != KeeperException.Code.OK.intValue()) {
                     fatalError = (OpResult.ErrorResult)result;
                     break;
                  }
               }

               if (fatalError != null) {
                  KeeperException ex = KeeperException.create(KeeperException.Code.get(fatalError.getErr()));
                  ex.setMultiResults(results);
                  throw ex;
               } else {
                  return results;
               }
            }
         }
      }
   }

   public Transaction transaction() {
      return new Transaction(this);
   }

   public void delete(String path, int version, AsyncCallback.VoidCallback cb, Object ctx) {
      PathUtils.validatePath(path);
      String serverPath;
      if (path.equals("/")) {
         serverPath = path;
      } else {
         serverPath = this.prependChroot(path);
      }

      RequestHeader h = new RequestHeader();
      h.setType(2);
      DeleteRequest request = new DeleteRequest();
      request.setPath(serverPath);
      request.setVersion(version);
      this.cnxn.queuePacket(h, new ReplyHeader(), request, (Record)null, cb, path, serverPath, ctx, (WatchRegistration)null);
   }

   public Stat exists(String path, Watcher watcher) throws KeeperException, InterruptedException {
      PathUtils.validatePath(path);
      WatchRegistration wcb = null;
      if (watcher != null) {
         wcb = new ExistsWatchRegistration(watcher, path);
      }

      String serverPath = this.prependChroot(path);
      RequestHeader h = new RequestHeader();
      h.setType(3);
      ExistsRequest request = new ExistsRequest();
      request.setPath(serverPath);
      request.setWatch(watcher != null);
      SetDataResponse response = new SetDataResponse();
      ReplyHeader r = this.cnxn.submitRequest(h, request, response, wcb);
      if (r.getErr() != 0) {
         if (r.getErr() == KeeperException.Code.NONODE.intValue()) {
            return null;
         } else {
            throw KeeperException.create(KeeperException.Code.get(r.getErr()), path);
         }
      } else {
         return response.getStat().getCzxid() == -1L ? null : response.getStat();
      }
   }

   public Stat exists(String path, boolean watch) throws KeeperException, InterruptedException {
      return this.exists(path, this.getDefaultWatcher(watch));
   }

   public void exists(String path, Watcher watcher, AsyncCallback.StatCallback cb, Object ctx) {
      PathUtils.validatePath(path);
      WatchRegistration wcb = null;
      if (watcher != null) {
         wcb = new ExistsWatchRegistration(watcher, path);
      }

      String serverPath = this.prependChroot(path);
      RequestHeader h = new RequestHeader();
      h.setType(3);
      ExistsRequest request = new ExistsRequest();
      request.setPath(serverPath);
      request.setWatch(watcher != null);
      SetDataResponse response = new SetDataResponse();
      this.cnxn.queuePacket(h, new ReplyHeader(), request, response, cb, path, serverPath, ctx, wcb);
   }

   public void exists(String path, boolean watch, AsyncCallback.StatCallback cb, Object ctx) {
      this.exists(path, this.getDefaultWatcher(watch), cb, ctx);
   }

   public byte[] getData(String path, Watcher watcher, Stat stat) throws KeeperException, InterruptedException {
      PathUtils.validatePath(path);
      WatchRegistration wcb = null;
      if (watcher != null) {
         wcb = new DataWatchRegistration(watcher, path);
      }

      String serverPath = this.prependChroot(path);
      RequestHeader h = new RequestHeader();
      h.setType(4);
      GetDataRequest request = new GetDataRequest();
      request.setPath(serverPath);
      request.setWatch(watcher != null);
      GetDataResponse response = new GetDataResponse();
      ReplyHeader r = this.cnxn.submitRequest(h, request, response, wcb);
      if (r.getErr() != 0) {
         throw KeeperException.create(KeeperException.Code.get(r.getErr()), path);
      } else {
         if (stat != null) {
            DataTree.copyStat(response.getStat(), stat);
         }

         return response.getData();
      }
   }

   public byte[] getData(String path, boolean watch, Stat stat) throws KeeperException, InterruptedException {
      return this.getData(path, this.getDefaultWatcher(watch), stat);
   }

   public void getData(String path, Watcher watcher, AsyncCallback.DataCallback cb, Object ctx) {
      PathUtils.validatePath(path);
      WatchRegistration wcb = null;
      if (watcher != null) {
         wcb = new DataWatchRegistration(watcher, path);
      }

      String serverPath = this.prependChroot(path);
      RequestHeader h = new RequestHeader();
      h.setType(4);
      GetDataRequest request = new GetDataRequest();
      request.setPath(serverPath);
      request.setWatch(watcher != null);
      GetDataResponse response = new GetDataResponse();
      this.cnxn.queuePacket(h, new ReplyHeader(), request, response, cb, path, serverPath, ctx, wcb);
   }

   public void getData(String path, boolean watch, AsyncCallback.DataCallback cb, Object ctx) {
      this.getData(path, this.getDefaultWatcher(watch), cb, ctx);
   }

   public byte[] getConfig(Watcher watcher, Stat stat) throws KeeperException, InterruptedException {
      String configZnode = "/zookeeper/config";
      WatchRegistration wcb = null;
      if (watcher != null) {
         wcb = new DataWatchRegistration(watcher, "/zookeeper/config");
      }

      RequestHeader h = new RequestHeader();
      h.setType(4);
      GetDataRequest request = new GetDataRequest();
      request.setPath("/zookeeper/config");
      request.setWatch(watcher != null);
      GetDataResponse response = new GetDataResponse();
      ReplyHeader r = this.cnxn.submitRequest(h, request, response, wcb);
      if (r.getErr() != 0) {
         throw KeeperException.create(KeeperException.Code.get(r.getErr()), "/zookeeper/config");
      } else {
         if (stat != null) {
            DataTree.copyStat(response.getStat(), stat);
         }

         return response.getData();
      }
   }

   public void getConfig(Watcher watcher, AsyncCallback.DataCallback cb, Object ctx) {
      String configZnode = "/zookeeper/config";
      WatchRegistration wcb = null;
      if (watcher != null) {
         wcb = new DataWatchRegistration(watcher, "/zookeeper/config");
      }

      RequestHeader h = new RequestHeader();
      h.setType(4);
      GetDataRequest request = new GetDataRequest();
      request.setPath("/zookeeper/config");
      request.setWatch(watcher != null);
      GetDataResponse response = new GetDataResponse();
      this.cnxn.queuePacket(h, new ReplyHeader(), request, response, cb, "/zookeeper/config", "/zookeeper/config", ctx, wcb);
   }

   public byte[] getConfig(boolean watch, Stat stat) throws KeeperException, InterruptedException {
      return this.getConfig(this.getDefaultWatcher(watch), stat);
   }

   public void getConfig(boolean watch, AsyncCallback.DataCallback cb, Object ctx) {
      this.getConfig(this.getDefaultWatcher(watch), cb, ctx);
   }

   public Stat setData(String path, byte[] data, int version) throws KeeperException, InterruptedException {
      PathUtils.validatePath(path);
      String serverPath = this.prependChroot(path);
      RequestHeader h = new RequestHeader();
      h.setType(5);
      SetDataRequest request = new SetDataRequest();
      request.setPath(serverPath);
      request.setData(data);
      request.setVersion(version);
      SetDataResponse response = new SetDataResponse();
      ReplyHeader r = this.cnxn.submitRequest(h, request, response, (WatchRegistration)null);
      if (r.getErr() != 0) {
         throw KeeperException.create(KeeperException.Code.get(r.getErr()), path);
      } else {
         return response.getStat();
      }
   }

   public void setData(String path, byte[] data, int version, AsyncCallback.StatCallback cb, Object ctx) {
      PathUtils.validatePath(path);
      String serverPath = this.prependChroot(path);
      RequestHeader h = new RequestHeader();
      h.setType(5);
      SetDataRequest request = new SetDataRequest();
      request.setPath(serverPath);
      request.setData(data);
      request.setVersion(version);
      SetDataResponse response = new SetDataResponse();
      this.cnxn.queuePacket(h, new ReplyHeader(), request, response, cb, path, serverPath, ctx, (WatchRegistration)null);
   }

   public List getACL(String path, Stat stat) throws KeeperException, InterruptedException {
      PathUtils.validatePath(path);
      String serverPath = this.prependChroot(path);
      RequestHeader h = new RequestHeader();
      h.setType(6);
      GetACLRequest request = new GetACLRequest();
      request.setPath(serverPath);
      GetACLResponse response = new GetACLResponse();
      ReplyHeader r = this.cnxn.submitRequest(h, request, response, (WatchRegistration)null);
      if (r.getErr() != 0) {
         throw KeeperException.create(KeeperException.Code.get(r.getErr()), path);
      } else {
         if (stat != null) {
            DataTree.copyStat(response.getStat(), stat);
         }

         return response.getAcl();
      }
   }

   public void getACL(String path, Stat stat, AsyncCallback.ACLCallback cb, Object ctx) {
      PathUtils.validatePath(path);
      String serverPath = this.prependChroot(path);
      RequestHeader h = new RequestHeader();
      h.setType(6);
      GetACLRequest request = new GetACLRequest();
      request.setPath(serverPath);
      GetACLResponse response = new GetACLResponse();
      this.cnxn.queuePacket(h, new ReplyHeader(), request, response, cb, path, serverPath, ctx, (WatchRegistration)null);
   }

   public Stat setACL(String path, List acl, int aclVersion) throws KeeperException, InterruptedException {
      PathUtils.validatePath(path);
      this.validateACL(acl);
      String serverPath = this.prependChroot(path);
      RequestHeader h = new RequestHeader();
      h.setType(7);
      SetACLRequest request = new SetACLRequest();
      request.setPath(serverPath);
      request.setAcl(acl);
      request.setVersion(aclVersion);
      SetACLResponse response = new SetACLResponse();
      ReplyHeader r = this.cnxn.submitRequest(h, request, response, (WatchRegistration)null);
      if (r.getErr() != 0) {
         throw KeeperException.create(KeeperException.Code.get(r.getErr()), path);
      } else {
         return response.getStat();
      }
   }

   public void setACL(String path, List acl, int version, AsyncCallback.StatCallback cb, Object ctx) {
      PathUtils.validatePath(path);
      String serverPath = this.prependChroot(path);
      RequestHeader h = new RequestHeader();
      h.setType(7);
      SetACLRequest request = new SetACLRequest();
      request.setPath(serverPath);
      request.setAcl(acl);
      request.setVersion(version);
      SetACLResponse response = new SetACLResponse();
      this.cnxn.queuePacket(h, new ReplyHeader(), request, response, cb, path, serverPath, ctx, (WatchRegistration)null);
   }

   public List getChildren(String path, Watcher watcher) throws KeeperException, InterruptedException {
      PathUtils.validatePath(path);
      WatchRegistration wcb = null;
      if (watcher != null) {
         wcb = new ChildWatchRegistration(watcher, path);
      }

      String serverPath = this.prependChroot(path);
      RequestHeader h = new RequestHeader();
      h.setType(8);
      GetChildrenRequest request = new GetChildrenRequest();
      request.setPath(serverPath);
      request.setWatch(watcher != null);
      GetChildrenResponse response = new GetChildrenResponse();
      ReplyHeader r = this.cnxn.submitRequest(h, request, response, wcb);
      if (r.getErr() != 0) {
         throw KeeperException.create(KeeperException.Code.get(r.getErr()), path);
      } else {
         return response.getChildren();
      }
   }

   public List getChildren(String path, boolean watch) throws KeeperException, InterruptedException {
      return this.getChildren(path, this.getDefaultWatcher(watch));
   }

   public void getChildren(String path, Watcher watcher, AsyncCallback.ChildrenCallback cb, Object ctx) {
      PathUtils.validatePath(path);
      WatchRegistration wcb = null;
      if (watcher != null) {
         wcb = new ChildWatchRegistration(watcher, path);
      }

      String serverPath = this.prependChroot(path);
      RequestHeader h = new RequestHeader();
      h.setType(8);
      GetChildrenRequest request = new GetChildrenRequest();
      request.setPath(serverPath);
      request.setWatch(watcher != null);
      GetChildrenResponse response = new GetChildrenResponse();
      this.cnxn.queuePacket(h, new ReplyHeader(), request, response, cb, path, serverPath, ctx, wcb);
   }

   public void getChildren(String path, boolean watch, AsyncCallback.ChildrenCallback cb, Object ctx) {
      this.getChildren(path, this.getDefaultWatcher(watch), cb, ctx);
   }

   public List getChildren(String path, Watcher watcher, Stat stat) throws KeeperException, InterruptedException {
      PathUtils.validatePath(path);
      WatchRegistration wcb = null;
      if (watcher != null) {
         wcb = new ChildWatchRegistration(watcher, path);
      }

      String serverPath = this.prependChroot(path);
      RequestHeader h = new RequestHeader();
      h.setType(12);
      GetChildren2Request request = new GetChildren2Request();
      request.setPath(serverPath);
      request.setWatch(watcher != null);
      GetChildren2Response response = new GetChildren2Response();
      ReplyHeader r = this.cnxn.submitRequest(h, request, response, wcb);
      if (r.getErr() != 0) {
         throw KeeperException.create(KeeperException.Code.get(r.getErr()), path);
      } else {
         if (stat != null) {
            DataTree.copyStat(response.getStat(), stat);
         }

         return response.getChildren();
      }
   }

   public List getChildren(String path, boolean watch, Stat stat) throws KeeperException, InterruptedException {
      return this.getChildren(path, this.getDefaultWatcher(watch), stat);
   }

   public void getChildren(String path, Watcher watcher, AsyncCallback.Children2Callback cb, Object ctx) {
      PathUtils.validatePath(path);
      WatchRegistration wcb = null;
      if (watcher != null) {
         wcb = new ChildWatchRegistration(watcher, path);
      }

      String serverPath = this.prependChroot(path);
      RequestHeader h = new RequestHeader();
      h.setType(12);
      GetChildren2Request request = new GetChildren2Request();
      request.setPath(serverPath);
      request.setWatch(watcher != null);
      GetChildren2Response response = new GetChildren2Response();
      this.cnxn.queuePacket(h, new ReplyHeader(), request, response, cb, path, serverPath, ctx, wcb);
   }

   public void getChildren(String path, boolean watch, AsyncCallback.Children2Callback cb, Object ctx) {
      this.getChildren(path, this.getDefaultWatcher(watch), cb, ctx);
   }

   public int getAllChildrenNumber(String path) throws KeeperException, InterruptedException {
      PathUtils.validatePath(path);
      String serverPath = this.prependChroot(path);
      RequestHeader h = new RequestHeader();
      h.setType(104);
      GetAllChildrenNumberRequest request = new GetAllChildrenNumberRequest(serverPath);
      GetAllChildrenNumberResponse response = new GetAllChildrenNumberResponse();
      ReplyHeader r = this.cnxn.submitRequest(h, request, response, (WatchRegistration)null);
      if (r.getErr() != 0) {
         throw KeeperException.create(KeeperException.Code.get(r.getErr()), path);
      } else {
         return response.getTotalNumber();
      }
   }

   public void getAllChildrenNumber(String path, AsyncCallback.AllChildrenNumberCallback cb, Object ctx) {
      PathUtils.validatePath(path);
      String serverPath = this.prependChroot(path);
      RequestHeader h = new RequestHeader();
      h.setType(104);
      GetAllChildrenNumberRequest request = new GetAllChildrenNumberRequest(serverPath);
      GetAllChildrenNumberResponse response = new GetAllChildrenNumberResponse();
      this.cnxn.queuePacket(h, new ReplyHeader(), request, response, cb, path, serverPath, ctx, (WatchRegistration)null);
   }

   public List getEphemerals() throws KeeperException, InterruptedException {
      return this.getEphemerals("/");
   }

   public List getEphemerals(String prefixPath) throws KeeperException, InterruptedException {
      PathUtils.validatePath(prefixPath);
      RequestHeader h = new RequestHeader();
      h.setType(103);
      GetEphemeralsRequest request = new GetEphemeralsRequest(prefixPath);
      GetEphemeralsResponse response = new GetEphemeralsResponse();
      ReplyHeader r = this.cnxn.submitRequest(h, request, response, (WatchRegistration)null);
      if (r.getErr() != 0) {
         throw KeeperException.create(KeeperException.Code.get(r.getErr()));
      } else {
         return response.getEphemerals();
      }
   }

   public void getEphemerals(String prefixPath, AsyncCallback.EphemeralsCallback cb, Object ctx) {
      PathUtils.validatePath(prefixPath);
      RequestHeader h = new RequestHeader();
      h.setType(103);
      GetEphemeralsRequest request = new GetEphemeralsRequest(prefixPath);
      GetEphemeralsResponse response = new GetEphemeralsResponse();
      this.cnxn.queuePacket(h, new ReplyHeader(), request, response, cb, (String)null, (String)null, ctx, (WatchRegistration)null);
   }

   public void getEphemerals(AsyncCallback.EphemeralsCallback cb, Object ctx) {
      this.getEphemerals("/", cb, ctx);
   }

   public void sync(String path) throws KeeperException, InterruptedException {
      PathUtils.validatePath(path);
      String serverPath = this.prependChroot(path);
      RequestHeader h = new RequestHeader();
      h.setType(9);
      SyncRequest request = new SyncRequest();
      SyncResponse response = new SyncResponse();
      request.setPath(serverPath);
      ReplyHeader r = this.cnxn.submitRequest(h, request, response, (WatchRegistration)null);
      if (r.getErr() != 0) {
         throw KeeperException.create(KeeperException.Code.get(r.getErr()), path);
      }
   }

   public void sync(String path, AsyncCallback.VoidCallback cb, Object ctx) {
      PathUtils.validatePath(path);
      String serverPath = this.prependChroot(path);
      RequestHeader h = new RequestHeader();
      h.setType(9);
      SyncRequest request = new SyncRequest();
      SyncResponse response = new SyncResponse();
      request.setPath(serverPath);
      this.cnxn.queuePacket(h, new ReplyHeader(), request, response, cb, path, serverPath, ctx, (WatchRegistration)null);
   }

   public void removeWatches(String path, Watcher watcher, Watcher.WatcherType watcherType, boolean local) throws InterruptedException, KeeperException {
      this.validateWatcher(watcher);
      this.removeWatches(17, path, watcher, watcherType, local);
   }

   public void removeWatches(String path, Watcher watcher, Watcher.WatcherType watcherType, boolean local, AsyncCallback.VoidCallback cb, Object ctx) {
      this.validateWatcher(watcher);
      this.removeWatches(17, path, watcher, watcherType, local, cb, ctx);
   }

   public void removeAllWatches(String path, Watcher.WatcherType watcherType, boolean local) throws InterruptedException, KeeperException {
      this.removeWatches(18, path, (Watcher)null, watcherType, local);
   }

   public void removeAllWatches(String path, Watcher.WatcherType watcherType, boolean local, AsyncCallback.VoidCallback cb, Object ctx) {
      this.removeWatches(18, path, (Watcher)null, watcherType, local, cb, ctx);
   }

   public void addWatch(String basePath, Watcher watcher, AddWatchMode mode) throws KeeperException, InterruptedException {
      PathUtils.validatePath(basePath);
      this.validateWatcher(watcher);
      String serverPath = this.prependChroot(basePath);
      RequestHeader h = new RequestHeader();
      h.setType(106);
      AddWatchRequest request = new AddWatchRequest(serverPath, mode.getMode());
      ReplyHeader r = this.cnxn.submitRequest(h, request, new ErrorResponse(), new AddWatchRegistration(watcher, basePath, mode));
      if (r.getErr() != 0) {
         throw KeeperException.create(KeeperException.Code.get(r.getErr()), basePath);
      }
   }

   public void addWatch(String basePath, AddWatchMode mode) throws KeeperException, InterruptedException {
      this.addWatch(basePath, this.getWatchManager().getDefaultWatcher(), mode);
   }

   public void addWatch(String basePath, Watcher watcher, AddWatchMode mode, AsyncCallback.VoidCallback cb, Object ctx) {
      PathUtils.validatePath(basePath);
      this.validateWatcher(watcher);
      String serverPath = this.prependChroot(basePath);
      RequestHeader h = new RequestHeader();
      h.setType(106);
      AddWatchRequest request = new AddWatchRequest(serverPath, mode.getMode());
      this.cnxn.queuePacket(h, new ReplyHeader(), request, new ErrorResponse(), cb, basePath, serverPath, ctx, new AddWatchRegistration(watcher, basePath, mode));
   }

   public void addWatch(String basePath, AddWatchMode mode, AsyncCallback.VoidCallback cb, Object ctx) {
      this.addWatch(basePath, this.getWatchManager().getDefaultWatcher(), mode, cb, ctx);
   }

   private void validateWatcher(Watcher watcher) {
      if (watcher == null) {
         throw new IllegalArgumentException("Invalid Watcher, shouldn't be null!");
      }
   }

   private void removeWatches(int opCode, String path, Watcher watcher, Watcher.WatcherType watcherType, boolean local) throws InterruptedException, KeeperException {
      PathUtils.validatePath(path);
      String serverPath = this.prependChroot(path);
      WatchDeregistration wcb = new WatchDeregistration(path, watcher, watcherType, local, this.getWatchManager());
      RequestHeader h = new RequestHeader();
      h.setType(opCode);
      Record request = this.getRemoveWatchesRequest(opCode, watcherType, serverPath);
      ReplyHeader r = this.cnxn.submitRequest(h, request, (Record)null, (WatchRegistration)null, wcb);
      if (r.getErr() != 0) {
         throw KeeperException.create(KeeperException.Code.get(r.getErr()), path);
      }
   }

   private void removeWatches(int opCode, String path, Watcher watcher, Watcher.WatcherType watcherType, boolean local, AsyncCallback.VoidCallback cb, Object ctx) {
      PathUtils.validatePath(path);
      String serverPath = this.prependChroot(path);
      WatchDeregistration wcb = new WatchDeregistration(path, watcher, watcherType, local, this.getWatchManager());
      RequestHeader h = new RequestHeader();
      h.setType(opCode);
      Record request = this.getRemoveWatchesRequest(opCode, watcherType, serverPath);
      this.cnxn.queuePacket(h, new ReplyHeader(), request, (Record)null, cb, path, serverPath, ctx, (WatchRegistration)null, wcb);
   }

   private Record getRemoveWatchesRequest(int opCode, Watcher.WatcherType watcherType, String serverPath) {
      Record request = null;
      switch (opCode) {
         case 17:
            CheckWatchesRequest chkReq = new CheckWatchesRequest();
            chkReq.setPath(serverPath);
            chkReq.setType(watcherType.getIntValue());
            request = chkReq;
            break;
         case 18:
            RemoveWatchesRequest rmReq = new RemoveWatchesRequest();
            rmReq.setPath(serverPath);
            rmReq.setType(watcherType.getIntValue());
            request = rmReq;
            break;
         default:
            LOG.warn("unknown type " + opCode);
      }

      return request;
   }

   public States getState() {
      return this.cnxn.getState();
   }

   public String toString() {
      States state = this.getState();
      return "State:" + state.toString() + (state.isConnected() ? " Timeout:" + this.getSessionTimeout() + " " : " ") + this.cnxn;
   }

   protected boolean testableWaitForShutdown(int wait) throws InterruptedException {
      this.cnxn.sendThread.join((long)wait);
      if (this.cnxn.sendThread.isAlive()) {
         return false;
      } else {
         this.cnxn.eventThread.join((long)wait);
         return !this.cnxn.eventThread.isAlive();
      }
   }

   protected SocketAddress testableRemoteSocketAddress() {
      return this.cnxn.sendThread.getClientCnxnSocket().getRemoteSocketAddress();
   }

   protected SocketAddress testableLocalSocketAddress() {
      return this.cnxn.sendThread.getClientCnxnSocket().getLocalSocketAddress();
   }

   private ClientCnxnSocket getClientCnxnSocket() throws IOException {
      String clientCnxnSocketName = this.getClientConfig().getProperty("zookeeper.clientCnxnSocket");
      if (clientCnxnSocketName != null && !clientCnxnSocketName.equals(ClientCnxnSocketNIO.class.getSimpleName())) {
         if (clientCnxnSocketName.equals(ClientCnxnSocketNetty.class.getSimpleName())) {
            clientCnxnSocketName = ClientCnxnSocketNetty.class.getName();
         }
      } else {
         clientCnxnSocketName = ClientCnxnSocketNIO.class.getName();
      }

      try {
         Constructor<?> clientCxnConstructor = Class.forName(clientCnxnSocketName).getDeclaredConstructor(ZKClientConfig.class);
         ClientCnxnSocket clientCxnSocket = (ClientCnxnSocket)clientCxnConstructor.newInstance(this.getClientConfig());
         return clientCxnSocket;
      } catch (Exception e) {
         throw new IOException("Couldn't instantiate " + clientCnxnSocketName, e);
      }
   }

   private Watcher getDefaultWatcher(boolean required) {
      if (required) {
         Watcher defaultWatcher = this.getWatchManager().getDefaultWatcher();
         if (defaultWatcher != null) {
            return defaultWatcher;
         } else {
            throw new IllegalStateException("Default watcher is required, but it is null.");
         }
      } else {
         return null;
      }
   }

   private void validateACL(List acl) throws KeeperException.InvalidACLException {
      if (acl == null || acl.isEmpty() || acl.contains((Object)null)) {
         throw new KeeperException.InvalidACLException();
      }
   }

   public synchronized List whoAmI() throws InterruptedException {
      RequestHeader h = new RequestHeader();
      h.setType(107);
      WhoAmIResponse response = new WhoAmIResponse();
      this.cnxn.submitRequest(h, (Record)null, response, (WatchRegistration)null);
      return response.getClientInfo();
   }

   static {
      Environment.logEnv("Client environment:", LOG);
   }

   public abstract static class WatchRegistration {
      private Watcher watcher;
      private String clientPath;

      public WatchRegistration(Watcher watcher, String clientPath) {
         this.watcher = watcher;
         this.clientPath = clientPath;
      }

      protected abstract Map getWatches(int var1);

      public void register(int rc) {
         if (this.shouldAddWatch(rc)) {
            Map<String, Set<Watcher>> watches = this.getWatches(rc);
            synchronized(watches) {
               Set<Watcher> watchers = (Set)watches.get(this.clientPath);
               if (watchers == null) {
                  watchers = new HashSet();
                  watches.put(this.clientPath, watchers);
               }

               watchers.add(this.watcher);
            }
         }

      }

      protected boolean shouldAddWatch(int rc) {
         return rc == KeeperException.Code.OK.intValue();
      }
   }

   class ExistsWatchRegistration extends WatchRegistration {
      public ExistsWatchRegistration(Watcher watcher, String clientPath) {
         super(watcher, clientPath);
      }

      protected Map getWatches(int rc) {
         return rc == KeeperException.Code.OK.intValue() ? ZooKeeper.this.getWatchManager().getDataWatches() : ZooKeeper.this.getWatchManager().getExistWatches();
      }

      protected boolean shouldAddWatch(int rc) {
         return rc == KeeperException.Code.OK.intValue() || rc == KeeperException.Code.NONODE.intValue();
      }
   }

   class DataWatchRegistration extends WatchRegistration {
      public DataWatchRegistration(Watcher watcher, String clientPath) {
         super(watcher, clientPath);
      }

      protected Map getWatches(int rc) {
         return ZooKeeper.this.getWatchManager().getDataWatches();
      }
   }

   class ChildWatchRegistration extends WatchRegistration {
      public ChildWatchRegistration(Watcher watcher, String clientPath) {
         super(watcher, clientPath);
      }

      protected Map getWatches(int rc) {
         return ZooKeeper.this.getWatchManager().getChildWatches();
      }
   }

   class AddWatchRegistration extends WatchRegistration {
      private final AddWatchMode mode;

      public AddWatchRegistration(Watcher watcher, String clientPath, AddWatchMode mode) {
         super(watcher, clientPath);
         this.mode = mode;
      }

      protected Map getWatches(int rc) {
         switch (this.mode) {
            case PERSISTENT:
               return ZooKeeper.this.getWatchManager().getPersistentWatches();
            case PERSISTENT_RECURSIVE:
               return ZooKeeper.this.getWatchManager().getPersistentRecursiveWatches();
            default:
               throw new IllegalArgumentException("Mode not supported: " + this.mode);
         }
      }

      protected boolean shouldAddWatch(int rc) {
         return rc == KeeperException.Code.OK.intValue() || rc == KeeperException.Code.NONODE.intValue();
      }
   }

   @Public
   public static enum States {
      CONNECTING,
      ASSOCIATING,
      CONNECTED,
      CONNECTEDREADONLY,
      CLOSED,
      AUTH_FAILED,
      NOT_CONNECTED;

      public boolean isAlive() {
         return this != CLOSED && this != AUTH_FAILED;
      }

      public boolean isConnected() {
         return this == CONNECTED || this == CONNECTEDREADONLY;
      }
   }
}
