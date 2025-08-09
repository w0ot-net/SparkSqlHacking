package org.apache.zookeeper;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicReference;
import javax.security.auth.login.LoginException;
import javax.security.sasl.SaslException;
import org.apache.jute.BinaryInputArchive;
import org.apache.jute.BinaryOutputArchive;
import org.apache.jute.Record;
import org.apache.zookeeper.client.HostProvider;
import org.apache.zookeeper.client.ZKClientConfig;
import org.apache.zookeeper.client.ZooKeeperSaslClient;
import org.apache.zookeeper.common.Time;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.proto.AuthPacket;
import org.apache.zookeeper.proto.ConnectRequest;
import org.apache.zookeeper.proto.Create2Response;
import org.apache.zookeeper.proto.CreateResponse;
import org.apache.zookeeper.proto.ExistsResponse;
import org.apache.zookeeper.proto.GetACLResponse;
import org.apache.zookeeper.proto.GetAllChildrenNumberResponse;
import org.apache.zookeeper.proto.GetChildren2Response;
import org.apache.zookeeper.proto.GetChildrenResponse;
import org.apache.zookeeper.proto.GetDataResponse;
import org.apache.zookeeper.proto.GetEphemeralsResponse;
import org.apache.zookeeper.proto.GetSASLRequest;
import org.apache.zookeeper.proto.ReplyHeader;
import org.apache.zookeeper.proto.RequestHeader;
import org.apache.zookeeper.proto.SetACLResponse;
import org.apache.zookeeper.proto.SetDataResponse;
import org.apache.zookeeper.proto.SetWatches;
import org.apache.zookeeper.proto.SetWatches2;
import org.apache.zookeeper.proto.WatcherEvent;
import org.apache.zookeeper.server.ByteBufferInputStream;
import org.apache.zookeeper.server.ZooKeeperThread;
import org.apache.zookeeper.server.ZooTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
public class ClientCnxn {
   private static final Logger LOG = LoggerFactory.getLogger(ClientCnxn.class);
   private static final int SET_WATCHES_MAX_LENGTH = 131072;
   public static final int NOTIFICATION_XID = -1;
   public static final int PING_XID = -2;
   public static final int AUTHPACKET_XID = -4;
   public static final int SET_WATCHES_XID = -8;
   private final CopyOnWriteArraySet authInfo;
   private final Queue pendingQueue;
   private final LinkedBlockingDeque outgoingQueue;
   private int connectTimeout;
   private volatile int negotiatedSessionTimeout;
   private int readTimeout;
   private int expirationTimeout;
   private final int sessionTimeout;
   private final ZKWatchManager watchManager;
   private long sessionId;
   private byte[] sessionPasswd;
   private boolean readOnly;
   final String chrootPath;
   final SendThread sendThread;
   final EventThread eventThread;
   private volatile boolean closing;
   private final HostProvider hostProvider;
   volatile boolean seenRwServerBefore;
   private final ZKClientConfig clientConfig;
   private long requestTimeout;
   private Object eventOfDeath;
   private volatile long lastZxid;
   protected int xid;
   volatile ZooKeeper.States state;

   ZKWatchManager getWatcherManager() {
      return this.watchManager;
   }

   public long getSessionId() {
      return this.sessionId;
   }

   public byte[] getSessionPasswd() {
      return this.sessionPasswd;
   }

   public int getSessionTimeout() {
      return this.negotiatedSessionTimeout;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      SocketAddress local = this.sendThread.getClientCnxnSocket().getLocalSocketAddress();
      SocketAddress remote = this.sendThread.getClientCnxnSocket().getRemoteSocketAddress();
      sb.append("sessionid:0x").append(Long.toHexString(this.getSessionId())).append(" local:").append(local).append(" remoteserver:").append(remote).append(" lastZxid:").append(this.lastZxid).append(" xid:").append(this.xid).append(" sent:").append(this.sendThread.getClientCnxnSocket().getSentCount()).append(" recv:").append(this.sendThread.getClientCnxnSocket().getRecvCount()).append(" queuedpkts:").append(this.outgoingQueue.size()).append(" pendingresp:").append(this.pendingQueue.size()).append(" queuedevents:").append(this.eventThread.waitingEvents.size());
      return sb.toString();
   }

   public ClientCnxn(String chrootPath, HostProvider hostProvider, int sessionTimeout, ZKClientConfig clientConfig, Watcher defaultWatcher, ClientCnxnSocket clientCnxnSocket, boolean canBeReadOnly) throws IOException {
      this(chrootPath, hostProvider, sessionTimeout, clientConfig, defaultWatcher, clientCnxnSocket, 0L, new byte[16], canBeReadOnly);
   }

   public ClientCnxn(String chrootPath, HostProvider hostProvider, int sessionTimeout, ZKClientConfig clientConfig, Watcher defaultWatcher, ClientCnxnSocket clientCnxnSocket, long sessionId, byte[] sessionPasswd, boolean canBeReadOnly) throws IOException {
      this.authInfo = new CopyOnWriteArraySet();
      this.pendingQueue = new ArrayDeque();
      this.outgoingQueue = new LinkedBlockingDeque();
      this.closing = false;
      this.seenRwServerBefore = false;
      this.eventOfDeath = new Object();
      this.xid = 1;
      this.state = ZooKeeper.States.NOT_CONNECTED;
      this.chrootPath = chrootPath;
      this.hostProvider = hostProvider;
      this.sessionTimeout = sessionTimeout;
      this.clientConfig = clientConfig;
      this.sessionId = sessionId;
      this.sessionPasswd = sessionPasswd;
      this.readOnly = canBeReadOnly;
      this.watchManager = new ZKWatchManager(clientConfig.getBoolean("zookeeper.disableAutoWatchReset"), defaultWatcher);
      this.connectTimeout = sessionTimeout / hostProvider.size();
      this.readTimeout = sessionTimeout * 2 / 3;
      this.expirationTimeout = sessionTimeout * 4 / 3;
      this.sendThread = new SendThread(clientCnxnSocket);
      this.eventThread = new EventThread();
      this.initRequestTimeout();
   }

   public void start() {
      this.sendThread.start();
      this.eventThread.start();
   }

   private static String makeThreadName(String suffix) {
      String name = Thread.currentThread().getName().replaceAll("-EventThread", "");
      return name + suffix;
   }

   public static boolean isInEventThread() {
      return Thread.currentThread() instanceof EventThread;
   }

   protected void finishPacket(Packet p) {
      int err = p.replyHeader.getErr();
      if (p.watchRegistration != null) {
         p.watchRegistration.register(err);
      }

      if (p.watchDeregistration != null) {
         Map<Watcher.Event.EventType, Set<Watcher>> materializedWatchers = null;

         try {
            materializedWatchers = p.watchDeregistration.unregister(err);

            for(Map.Entry entry : materializedWatchers.entrySet()) {
               Set<Watcher> watchers = (Set)entry.getValue();
               if (watchers.size() > 0) {
                  this.queueEvent(p.watchDeregistration.getClientPath(), err, watchers, (Watcher.Event.EventType)entry.getKey());
                  p.replyHeader.setErr(KeeperException.Code.OK.intValue());
               }
            }
         } catch (KeeperException.NoWatcherException nwe) {
            p.replyHeader.setErr(nwe.code().intValue());
         } catch (KeeperException ke) {
            p.replyHeader.setErr(ke.code().intValue());
         }
      }

      if (p.cb == null) {
         synchronized(p) {
            p.finished = true;
            p.notifyAll();
         }
      } else {
         p.finished = true;
         this.eventThread.queuePacket(p);
      }

   }

   void queueEvent(String clientPath, int err, Set materializedWatchers, Watcher.Event.EventType eventType) {
      Watcher.Event.KeeperState sessionState = Watcher.Event.KeeperState.SyncConnected;
      if (KeeperException.Code.SESSIONEXPIRED.intValue() == err || KeeperException.Code.CONNECTIONLOSS.intValue() == err) {
         sessionState = Watcher.Event.KeeperState.Disconnected;
      }

      WatchedEvent event = new WatchedEvent(eventType, sessionState, clientPath);
      this.eventThread.queueEvent(event, materializedWatchers);
   }

   void queueCallback(AsyncCallback cb, int rc, String path, Object ctx) {
      this.eventThread.queueCallback(cb, rc, path, ctx);
   }

   protected void onConnecting(InetSocketAddress addr) {
   }

   private void conLossPacket(Packet p) {
      if (p.replyHeader != null) {
         switch (this.state) {
            case AUTH_FAILED:
               p.replyHeader.setErr(KeeperException.Code.AUTHFAILED.intValue());
               break;
            case CLOSED:
               p.replyHeader.setErr(KeeperException.Code.SESSIONEXPIRED.intValue());
               break;
            default:
               p.replyHeader.setErr(KeeperException.Code.CONNECTIONLOSS.intValue());
         }

         this.finishPacket(p);
      }
   }

   public long getLastZxid() {
      return this.lastZxid;
   }

   public void disconnect() {
      LOG.debug("Disconnecting client for session: 0x{}", Long.toHexString(this.getSessionId()));
      this.sendThread.close();

      try {
         this.sendThread.join();
      } catch (InterruptedException ex) {
         LOG.warn("Got interrupted while waiting for the sender thread to close", ex);
      }

      this.eventThread.queueEventOfDeath();
   }

   public void close() throws IOException {
      LOG.debug("Closing client for session: 0x{}", Long.toHexString(this.getSessionId()));

      try {
         RequestHeader h = new RequestHeader();
         h.setType(-11);
         this.submitRequest(h, (Record)null, (Record)null, (ZooKeeper.WatchRegistration)null);
      } catch (InterruptedException var5) {
      } finally {
         this.disconnect();
      }

   }

   public synchronized int getXid() {
      if (this.xid == Integer.MAX_VALUE) {
         this.xid = 1;
      }

      return this.xid++;
   }

   public ReplyHeader submitRequest(RequestHeader h, Record request, Record response, ZooKeeper.WatchRegistration watchRegistration) throws InterruptedException {
      return this.submitRequest(h, request, response, watchRegistration, (WatchDeregistration)null);
   }

   public ReplyHeader submitRequest(RequestHeader h, Record request, Record response, ZooKeeper.WatchRegistration watchRegistration, WatchDeregistration watchDeregistration) throws InterruptedException {
      ReplyHeader r = new ReplyHeader();
      Packet packet = this.queuePacket(h, r, request, response, (AsyncCallback)null, (String)null, (String)null, (Object)null, watchRegistration, watchDeregistration);
      synchronized(packet) {
         if (this.requestTimeout > 0L) {
            this.waitForPacketFinish(r, packet);
         } else {
            while(!packet.finished) {
               packet.wait();
            }
         }
      }

      if (r.getErr() == KeeperException.Code.REQUESTTIMEOUT.intValue()) {
         this.sendThread.cleanAndNotifyState();
      }

      return r;
   }

   private void waitForPacketFinish(ReplyHeader r, Packet packet) throws InterruptedException {
      long waitStartTime = Time.currentElapsedTime();

      while(!packet.finished) {
         packet.wait(this.requestTimeout);
         if (!packet.finished && Time.currentElapsedTime() - waitStartTime >= this.requestTimeout) {
            LOG.error("Timeout error occurred for the packet '{}'.", packet);
            r.setErr(KeeperException.Code.REQUESTTIMEOUT.intValue());
            break;
         }
      }

   }

   public void saslCompleted() {
      this.sendThread.getClientCnxnSocket().saslCompleted();
   }

   public void sendPacket(Record request, Record response, AsyncCallback cb, int opCode) throws IOException {
      int xid = this.getXid();
      RequestHeader h = new RequestHeader();
      h.setXid(xid);
      h.setType(opCode);
      ReplyHeader r = new ReplyHeader();
      r.setXid(xid);
      Packet p = new Packet(h, r, request, response, (ZooKeeper.WatchRegistration)null);
      p.cb = cb;
      this.sendThread.sendPacket(p);
   }

   public Packet queuePacket(RequestHeader h, ReplyHeader r, Record request, Record response, AsyncCallback cb, String clientPath, String serverPath, Object ctx, ZooKeeper.WatchRegistration watchRegistration) {
      return this.queuePacket(h, r, request, response, cb, clientPath, serverPath, ctx, watchRegistration, (WatchDeregistration)null);
   }

   @SuppressFBWarnings({"JLM_JSR166_UTILCONCURRENT_MONITORENTER"})
   public Packet queuePacket(RequestHeader h, ReplyHeader r, Record request, Record response, AsyncCallback cb, String clientPath, String serverPath, Object ctx, ZooKeeper.WatchRegistration watchRegistration, WatchDeregistration watchDeregistration) {
      Packet packet = null;
      packet = new Packet(h, r, request, response, watchRegistration);
      packet.cb = cb;
      packet.ctx = ctx;
      packet.clientPath = clientPath;
      packet.serverPath = serverPath;
      packet.watchDeregistration = watchDeregistration;
      synchronized(this.outgoingQueue) {
         if (this.state.isAlive() && !this.closing) {
            if (h.getType() == -11) {
               this.closing = true;
            }

            this.outgoingQueue.add(packet);
         } else {
            this.conLossPacket(packet);
         }
      }

      this.sendThread.getClientCnxnSocket().packetAdded();
      return packet;
   }

   public void addAuthInfo(String scheme, byte[] auth) {
      if (this.state.isAlive()) {
         this.authInfo.add(new AuthData(scheme, auth));
         this.queuePacket(new RequestHeader(-4, 100), (ReplyHeader)null, new AuthPacket(0, scheme, auth), (Record)null, (AsyncCallback)null, (String)null, (String)null, (Object)null, (ZooKeeper.WatchRegistration)null);
      }
   }

   ZooKeeper.States getState() {
      return this.state;
   }

   private void initRequestTimeout() {
      try {
         this.requestTimeout = this.clientConfig.getLong("zookeeper.request.timeout", 0L);
         LOG.info("{} value is {}. feature enabled={}", new Object[]{"zookeeper.request.timeout", this.requestTimeout, this.requestTimeout > 0L});
      } catch (NumberFormatException e) {
         LOG.error("Configured value {} for property {} can not be parsed to long.", this.clientConfig.getProperty("zookeeper.request.timeout"), "zookeeper.request.timeout");
         throw e;
      }
   }

   public ZooKeeperSaslClient getZooKeeperSaslClient() {
      return this.sendThread.getZooKeeperSaslClient();
   }

   static class AuthData {
      String scheme;
      byte[] data;

      AuthData(String scheme, byte[] data) {
         this.scheme = scheme;
         this.data = data;
      }
   }

   static class Packet {
      RequestHeader requestHeader;
      ReplyHeader replyHeader;
      Record request;
      Record response;
      ByteBuffer bb;
      String clientPath;
      String serverPath;
      boolean finished;
      AsyncCallback cb;
      Object ctx;
      ZooKeeper.WatchRegistration watchRegistration;
      WatchDeregistration watchDeregistration;

      Packet(RequestHeader requestHeader, ReplyHeader replyHeader, Record request, Record response, ZooKeeper.WatchRegistration watchRegistration) {
         this.requestHeader = requestHeader;
         this.replyHeader = replyHeader;
         this.request = request;
         this.response = response;
         this.watchRegistration = watchRegistration;
      }

      public void createBB() {
         try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BinaryOutputArchive boa = BinaryOutputArchive.getArchive(baos);
            boa.writeInt(-1, "len");
            if (this.requestHeader != null) {
               this.requestHeader.serialize(boa, "header");
            }

            if (this.request instanceof ConnectRequest) {
               this.request.serialize(boa, "connect");
            } else if (this.request != null) {
               this.request.serialize(boa, "request");
            }

            baos.close();
            this.bb = ByteBuffer.wrap(baos.toByteArray());
            this.bb.putInt(this.bb.capacity() - 4);
            this.bb.rewind();
         } catch (IOException e) {
            ClientCnxn.LOG.warn("Unexpected exception", e);
         }

      }

      public String toString() {
         StringBuilder sb = new StringBuilder();
         sb.append("clientPath:" + this.clientPath);
         sb.append(" serverPath:" + this.serverPath);
         sb.append(" finished:" + this.finished);
         sb.append(" header:: " + this.requestHeader);
         sb.append(" replyHeader:: " + this.replyHeader);
         sb.append(" request:: " + this.request);
         sb.append(" response:: " + this.response);
         return sb.toString().replaceAll("\r*\n+", " ");
      }
   }

   private static class WatcherSetEventPair {
      private final Set watchers;
      private final WatchedEvent event;

      public WatcherSetEventPair(Set watchers, WatchedEvent event) {
         this.watchers = watchers;
         this.event = event;
      }
   }

   class EventThread extends ZooKeeperThread {
      private final LinkedBlockingQueue waitingEvents = new LinkedBlockingQueue();
      private volatile Watcher.Event.KeeperState sessionState;
      private volatile boolean wasKilled;
      private volatile boolean isRunning;

      EventThread() {
         super(ClientCnxn.makeThreadName("-EventThread"));
         this.sessionState = Watcher.Event.KeeperState.Disconnected;
         this.wasKilled = false;
         this.isRunning = false;
         this.setDaemon(true);
      }

      public void queueEvent(WatchedEvent event) {
         this.queueEvent(event, (Set)null);
      }

      private void queueEvent(WatchedEvent event, Set materializedWatchers) {
         if (event.getType() != Watcher.Event.EventType.None || this.sessionState != event.getState()) {
            this.sessionState = event.getState();
            Set<Watcher> watchers;
            if (materializedWatchers == null) {
               watchers = ClientCnxn.this.watchManager.materialize(event.getState(), event.getType(), event.getPath());
            } else {
               watchers = new HashSet(materializedWatchers);
            }

            WatcherSetEventPair pair = new WatcherSetEventPair(watchers, event);
            this.waitingEvents.add(pair);
         }
      }

      public void queueCallback(AsyncCallback cb, int rc, String path, Object ctx) {
         this.waitingEvents.add(new LocalCallback(cb, rc, path, ctx));
      }

      @SuppressFBWarnings({"JLM_JSR166_UTILCONCURRENT_MONITORENTER"})
      public void queuePacket(Packet packet) {
         if (this.wasKilled) {
            synchronized(this.waitingEvents) {
               if (this.isRunning) {
                  this.waitingEvents.add(packet);
               } else {
                  this.processEvent(packet);
               }
            }
         } else {
            this.waitingEvents.add(packet);
         }

      }

      public void queueEventOfDeath() {
         this.waitingEvents.add(ClientCnxn.this.eventOfDeath);
      }

      @SuppressFBWarnings({"JLM_JSR166_UTILCONCURRENT_MONITORENTER"})
      public void run() {
         try {
            this.isRunning = true;

            while(true) {
               Object event = this.waitingEvents.take();
               if (event == ClientCnxn.this.eventOfDeath) {
                  this.wasKilled = true;
               } else {
                  this.processEvent(event);
               }

               if (this.wasKilled) {
                  synchronized(this.waitingEvents) {
                     if (this.waitingEvents.isEmpty()) {
                        this.isRunning = false;
                        break;
                     }
                  }
               }
            }
         } catch (InterruptedException e) {
            ClientCnxn.LOG.error("Event thread exiting due to interruption", e);
         }

         ClientCnxn.LOG.info("EventThread shut down for session: 0x{}", Long.toHexString(ClientCnxn.this.getSessionId()));
      }

      private void processEvent(Object event) {
         try {
            if (event instanceof WatcherSetEventPair) {
               WatcherSetEventPair pair = (WatcherSetEventPair)event;

               for(Watcher watcher : pair.watchers) {
                  try {
                     watcher.process(pair.event);
                  } catch (Throwable t) {
                     ClientCnxn.LOG.error("Error while calling watcher.", t);
                  }
               }
            } else if (event instanceof LocalCallback) {
               LocalCallback lcb = (LocalCallback)event;
               if (lcb.cb instanceof AsyncCallback.StatCallback) {
                  ((AsyncCallback.StatCallback)lcb.cb).processResult(lcb.rc, lcb.path, lcb.ctx, (Stat)null);
               } else if (lcb.cb instanceof AsyncCallback.DataCallback) {
                  ((AsyncCallback.DataCallback)lcb.cb).processResult(lcb.rc, lcb.path, lcb.ctx, (byte[])null, (Stat)null);
               } else if (lcb.cb instanceof AsyncCallback.ACLCallback) {
                  ((AsyncCallback.ACLCallback)lcb.cb).processResult(lcb.rc, lcb.path, lcb.ctx, (List)null, (Stat)null);
               } else if (lcb.cb instanceof AsyncCallback.ChildrenCallback) {
                  ((AsyncCallback.ChildrenCallback)lcb.cb).processResult(lcb.rc, lcb.path, lcb.ctx, (List)null);
               } else if (lcb.cb instanceof AsyncCallback.Children2Callback) {
                  ((AsyncCallback.Children2Callback)lcb.cb).processResult(lcb.rc, lcb.path, lcb.ctx, (List)null, (Stat)null);
               } else if (lcb.cb instanceof AsyncCallback.StringCallback) {
                  ((AsyncCallback.StringCallback)lcb.cb).processResult(lcb.rc, lcb.path, lcb.ctx, (String)null);
               } else if (lcb.cb instanceof AsyncCallback.EphemeralsCallback) {
                  ((AsyncCallback.EphemeralsCallback)lcb.cb).processResult(lcb.rc, lcb.ctx, (List)null);
               } else if (lcb.cb instanceof AsyncCallback.AllChildrenNumberCallback) {
                  ((AsyncCallback.AllChildrenNumberCallback)lcb.cb).processResult(lcb.rc, lcb.path, lcb.ctx, -1);
               } else if (lcb.cb instanceof AsyncCallback.MultiCallback) {
                  ((AsyncCallback.MultiCallback)lcb.cb).processResult(lcb.rc, lcb.path, lcb.ctx, Collections.emptyList());
               } else {
                  ((AsyncCallback.VoidCallback)lcb.cb).processResult(lcb.rc, lcb.path, lcb.ctx);
               }
            } else {
               Packet p = (Packet)event;
               int rc = 0;
               String clientPath = p.clientPath;
               if (p.replyHeader.getErr() != 0) {
                  rc = p.replyHeader.getErr();
               }

               if (p.cb == null) {
                  ClientCnxn.LOG.warn("Somehow a null cb got to EventThread!");
               } else if (!(p.response instanceof ExistsResponse) && !(p.response instanceof SetDataResponse) && !(p.response instanceof SetACLResponse)) {
                  if (p.response instanceof GetDataResponse) {
                     AsyncCallback.DataCallback cb = (AsyncCallback.DataCallback)p.cb;
                     GetDataResponse rsp = (GetDataResponse)p.response;
                     if (rc == KeeperException.Code.OK.intValue()) {
                        cb.processResult(rc, clientPath, p.ctx, rsp.getData(), rsp.getStat());
                     } else {
                        cb.processResult(rc, clientPath, p.ctx, (byte[])null, (Stat)null);
                     }
                  } else if (p.response instanceof GetACLResponse) {
                     AsyncCallback.ACLCallback cb = (AsyncCallback.ACLCallback)p.cb;
                     GetACLResponse rsp = (GetACLResponse)p.response;
                     if (rc == KeeperException.Code.OK.intValue()) {
                        cb.processResult(rc, clientPath, p.ctx, rsp.getAcl(), rsp.getStat());
                     } else {
                        cb.processResult(rc, clientPath, p.ctx, (List)null, (Stat)null);
                     }
                  } else if (p.response instanceof GetChildrenResponse) {
                     AsyncCallback.ChildrenCallback cb = (AsyncCallback.ChildrenCallback)p.cb;
                     GetChildrenResponse rsp = (GetChildrenResponse)p.response;
                     if (rc == KeeperException.Code.OK.intValue()) {
                        cb.processResult(rc, clientPath, p.ctx, rsp.getChildren());
                     } else {
                        cb.processResult(rc, clientPath, p.ctx, (List)null);
                     }
                  } else if (p.response instanceof GetAllChildrenNumberResponse) {
                     AsyncCallback.AllChildrenNumberCallback cb = (AsyncCallback.AllChildrenNumberCallback)p.cb;
                     GetAllChildrenNumberResponse rsp = (GetAllChildrenNumberResponse)p.response;
                     if (rc == KeeperException.Code.OK.intValue()) {
                        cb.processResult(rc, clientPath, p.ctx, rsp.getTotalNumber());
                     } else {
                        cb.processResult(rc, clientPath, p.ctx, -1);
                     }
                  } else if (p.response instanceof GetChildren2Response) {
                     AsyncCallback.Children2Callback cb = (AsyncCallback.Children2Callback)p.cb;
                     GetChildren2Response rsp = (GetChildren2Response)p.response;
                     if (rc == KeeperException.Code.OK.intValue()) {
                        cb.processResult(rc, clientPath, p.ctx, rsp.getChildren(), rsp.getStat());
                     } else {
                        cb.processResult(rc, clientPath, p.ctx, (List)null, (Stat)null);
                     }
                  } else if (p.response instanceof CreateResponse) {
                     AsyncCallback.StringCallback cb = (AsyncCallback.StringCallback)p.cb;
                     CreateResponse rsp = (CreateResponse)p.response;
                     if (rc == KeeperException.Code.OK.intValue()) {
                        cb.processResult(rc, clientPath, p.ctx, ClientCnxn.this.chrootPath == null ? rsp.getPath() : rsp.getPath().substring(ClientCnxn.this.chrootPath.length()));
                     } else {
                        cb.processResult(rc, clientPath, p.ctx, (String)null);
                     }
                  } else if (p.response instanceof Create2Response) {
                     AsyncCallback.Create2Callback cb = (AsyncCallback.Create2Callback)p.cb;
                     Create2Response rsp = (Create2Response)p.response;
                     if (rc == KeeperException.Code.OK.intValue()) {
                        cb.processResult(rc, clientPath, p.ctx, ClientCnxn.this.chrootPath == null ? rsp.getPath() : rsp.getPath().substring(ClientCnxn.this.chrootPath.length()), rsp.getStat());
                     } else {
                        cb.processResult(rc, clientPath, p.ctx, (String)null, (Stat)null);
                     }
                  } else if (p.response instanceof MultiResponse) {
                     AsyncCallback.MultiCallback cb = (AsyncCallback.MultiCallback)p.cb;
                     MultiResponse rsp = (MultiResponse)p.response;
                     if (rc == KeeperException.Code.OK.intValue()) {
                        List<OpResult> results = rsp.getResultList();
                        int newRc = rc;

                        for(OpResult result : results) {
                           if (result instanceof OpResult.ErrorResult && KeeperException.Code.OK.intValue() != (newRc = ((OpResult.ErrorResult)result).getErr())) {
                              break;
                           }
                        }

                        cb.processResult(newRc, clientPath, p.ctx, results);
                     } else {
                        cb.processResult(rc, clientPath, p.ctx, (List)null);
                     }
                  } else if (p.response instanceof GetEphemeralsResponse) {
                     AsyncCallback.EphemeralsCallback cb = (AsyncCallback.EphemeralsCallback)p.cb;
                     GetEphemeralsResponse rsp = (GetEphemeralsResponse)p.response;
                     if (rc == KeeperException.Code.OK.intValue()) {
                        cb.processResult(rc, p.ctx, rsp.getEphemerals());
                     } else {
                        cb.processResult(rc, p.ctx, (List)null);
                     }
                  } else if (p.cb instanceof AsyncCallback.VoidCallback) {
                     AsyncCallback.VoidCallback cb = (AsyncCallback.VoidCallback)p.cb;
                     cb.processResult(rc, clientPath, p.ctx);
                  }
               } else {
                  AsyncCallback.StatCallback cb = (AsyncCallback.StatCallback)p.cb;
                  if (rc == KeeperException.Code.OK.intValue()) {
                     if (p.response instanceof ExistsResponse) {
                        cb.processResult(rc, clientPath, p.ctx, ((ExistsResponse)p.response).getStat());
                     } else if (p.response instanceof SetDataResponse) {
                        cb.processResult(rc, clientPath, p.ctx, ((SetDataResponse)p.response).getStat());
                     } else if (p.response instanceof SetACLResponse) {
                        cb.processResult(rc, clientPath, p.ctx, ((SetACLResponse)p.response).getStat());
                     }
                  } else {
                     cb.processResult(rc, clientPath, p.ctx, (Stat)null);
                  }
               }
            }
         } catch (Throwable t) {
            ClientCnxn.LOG.error("Unexpected throwable", t);
         }

      }
   }

   static class EndOfStreamException extends IOException {
      private static final long serialVersionUID = -5438877188796231422L;

      public EndOfStreamException(String msg) {
         super(msg);
      }

      public String toString() {
         return "EndOfStreamException: " + this.getMessage();
      }
   }

   private static class ConnectionTimeoutException extends IOException {
      public ConnectionTimeoutException(String message) {
         super(message);
      }
   }

   private static class SessionTimeoutException extends IOException {
      private static final long serialVersionUID = 824482094072071178L;

      public SessionTimeoutException(String msg) {
         super(msg);
      }
   }

   private static class SessionExpiredException extends IOException {
      private static final long serialVersionUID = -1388816932076193249L;

      public SessionExpiredException(String msg) {
         super(msg);
      }
   }

   private static class RWServerFoundException extends IOException {
      private static final long serialVersionUID = 90431199887158758L;

      public RWServerFoundException(String msg) {
         super(msg);
      }
   }

   class SendThread extends ZooKeeperThread {
      private long lastPingSentNs;
      private final ClientCnxnSocket clientCnxnSocket;
      private boolean isFirstConnect = true;
      private volatile ZooKeeperSaslClient zooKeeperSaslClient;
      private final AtomicReference loginRef = new AtomicReference();
      private InetSocketAddress rwServerAddress = null;
      private static final int minPingRwTimeout = 100;
      private static final int maxPingRwTimeout = 60000;
      private int pingRwTimeout = 100;
      private boolean saslLoginFailed = false;

      private String stripChroot(String serverPath) {
         if (serverPath.startsWith(ClientCnxn.this.chrootPath)) {
            return serverPath.length() == ClientCnxn.this.chrootPath.length() ? "/" : serverPath.substring(ClientCnxn.this.chrootPath.length());
         } else if (serverPath.startsWith("/zookeeper/")) {
            return serverPath;
         } else {
            ClientCnxn.LOG.warn("Got server path {} which is not descendant of chroot path {}.", serverPath, ClientCnxn.this.chrootPath);
            return serverPath;
         }
      }

      void readResponse(ByteBuffer incomingBuffer) throws IOException {
         ByteBufferInputStream bbis = new ByteBufferInputStream(incomingBuffer);
         BinaryInputArchive bbia = BinaryInputArchive.getArchive(bbis);
         ReplyHeader replyHdr = new ReplyHeader();
         replyHdr.deserialize(bbia, "header");
         switch (replyHdr.getXid()) {
            case -4:
               ClientCnxn.LOG.debug("Got auth session id: 0x{}", Long.toHexString(ClientCnxn.this.sessionId));
               if (replyHdr.getErr() == KeeperException.Code.AUTHFAILED.intValue()) {
                  this.changeZkState(ZooKeeper.States.AUTH_FAILED);
                  ClientCnxn.this.eventThread.queueEvent(new WatchedEvent(Watcher.Event.EventType.None, Watcher.Event.KeeperState.AuthFailed, (String)null));
                  ClientCnxn.this.eventThread.queueEventOfDeath();
               }

               return;
            case -3:
            default:
               if (this.tunnelAuthInProgress()) {
                  GetSASLRequest request = new GetSASLRequest();
                  request.deserialize(bbia, "token");
                  this.zooKeeperSaslClient.respondToServer(request.getToken(), ClientCnxn.this);
                  return;
               } else {
                  Packet packet;
                  synchronized(ClientCnxn.this.pendingQueue) {
                     if (ClientCnxn.this.pendingQueue.size() == 0) {
                        throw new IOException("Nothing in the queue, but got " + replyHdr.getXid());
                     }

                     packet = (Packet)ClientCnxn.this.pendingQueue.remove();
                  }

                  try {
                     if (packet.requestHeader.getXid() != replyHdr.getXid()) {
                        packet.replyHeader.setErr(KeeperException.Code.CONNECTIONLOSS.intValue());
                        throw new IOException("Xid out of order. Got Xid " + replyHdr.getXid() + " with err " + replyHdr.getErr() + " expected Xid " + packet.requestHeader.getXid() + " for a packet with details: " + packet);
                     }

                     packet.replyHeader.setXid(replyHdr.getXid());
                     packet.replyHeader.setErr(replyHdr.getErr());
                     packet.replyHeader.setZxid(replyHdr.getZxid());
                     if (replyHdr.getZxid() > 0L) {
                        ClientCnxn.this.lastZxid = replyHdr.getZxid();
                     }

                     if (packet.response != null && replyHdr.getErr() == 0) {
                        packet.response.deserialize(bbia, "response");
                     }

                     ClientCnxn.LOG.debug("Reading reply session id: 0x{}, packet:: {}", Long.toHexString(ClientCnxn.this.sessionId), packet);
                  } finally {
                     ClientCnxn.this.finishPacket(packet);
                  }

                  return;
               }
            case -2:
               ClientCnxn.LOG.debug("Got ping response for session id: 0x{} after {}ms.", Long.toHexString(ClientCnxn.this.sessionId), (System.nanoTime() - this.lastPingSentNs) / 1000000L);
               return;
            case -1:
               ClientCnxn.LOG.debug("Got notification session id: 0x{}", Long.toHexString(ClientCnxn.this.sessionId));
               WatcherEvent event = new WatcherEvent();
               event.deserialize(bbia, "response");
               if (ClientCnxn.this.chrootPath != null) {
                  String serverPath = event.getPath();
                  String clientPath = this.stripChroot(serverPath);
                  event.setPath(clientPath);
               }

               WatchedEvent we = new WatchedEvent(event, replyHdr.getZxid());
               ClientCnxn.LOG.debug("Got {} for session id 0x{}", we, Long.toHexString(ClientCnxn.this.sessionId));
               ClientCnxn.this.eventThread.queueEvent(we);
         }
      }

      SendThread(ClientCnxnSocket clientCnxnSocket) throws IOException {
         super(ClientCnxn.makeThreadName("-SendThread()"));
         this.changeZkState(ZooKeeper.States.CONNECTING);
         this.clientCnxnSocket = clientCnxnSocket;
         this.setDaemon(true);
      }

      synchronized ZooKeeper.States getZkState() {
         return ClientCnxn.this.state;
      }

      synchronized void changeZkState(ZooKeeper.States newState) throws IOException {
         if (!ClientCnxn.this.state.isAlive() && newState == ZooKeeper.States.CONNECTING) {
            throw new IOException("Connection has already been closed and reconnection is not allowed");
         } else {
            ClientCnxn.this.state = newState;
         }
      }

      ClientCnxnSocket getClientCnxnSocket() {
         return this.clientCnxnSocket;
      }

      void primeConnection() throws IOException {
         ClientCnxn.LOG.info("Socket connection established, initiating session, client: {}, server: {}", this.clientCnxnSocket.getLocalSocketAddress(), this.clientCnxnSocket.getRemoteSocketAddress());
         this.isFirstConnect = false;
         long sessId = ClientCnxn.this.seenRwServerBefore ? ClientCnxn.this.sessionId : 0L;
         ConnectRequest conReq = new ConnectRequest(0, ClientCnxn.this.lastZxid, ClientCnxn.this.sessionTimeout, sessId, ClientCnxn.this.sessionPasswd, ClientCnxn.this.readOnly);
         if (!ClientCnxn.this.clientConfig.getBoolean("zookeeper.disableAutoWatchReset")) {
            List<String> dataWatches = ClientCnxn.this.watchManager.getDataWatchList();
            List<String> existWatches = ClientCnxn.this.watchManager.getExistWatchList();
            List<String> childWatches = ClientCnxn.this.watchManager.getChildWatchList();
            List<String> persistentWatches = ClientCnxn.this.watchManager.getPersistentWatchList();
            List<String> persistentRecursiveWatches = ClientCnxn.this.watchManager.getPersistentRecursiveWatchList();
            if (!dataWatches.isEmpty() || !existWatches.isEmpty() || !childWatches.isEmpty() || !persistentWatches.isEmpty() || !persistentRecursiveWatches.isEmpty()) {
               Iterator<String> dataWatchesIter = this.prependChroot(dataWatches).iterator();
               Iterator<String> existWatchesIter = this.prependChroot(existWatches).iterator();
               Iterator<String> childWatchesIter = this.prependChroot(childWatches).iterator();
               Iterator<String> persistentWatchesIter = this.prependChroot(persistentWatches).iterator();
               Iterator<String> persistentRecursiveWatchesIter = this.prependChroot(persistentRecursiveWatches).iterator();
               long setWatchesLastZxid = ClientCnxn.this.lastZxid;

               while(dataWatchesIter.hasNext() || existWatchesIter.hasNext() || childWatchesIter.hasNext() || persistentWatchesIter.hasNext() || persistentRecursiveWatchesIter.hasNext()) {
                  List<String> dataWatchesBatch = new ArrayList();
                  List<String> existWatchesBatch = new ArrayList();
                  List<String> childWatchesBatch = new ArrayList();
                  List<String> persistentWatchesBatch = new ArrayList();
                  List<String> persistentRecursiveWatchesBatch = new ArrayList();

                  String watch;
                  for(int batchLength = 0; batchLength < 131072; batchLength += watch.length()) {
                     if (dataWatchesIter.hasNext()) {
                        watch = (String)dataWatchesIter.next();
                        dataWatchesBatch.add(watch);
                     } else if (existWatchesIter.hasNext()) {
                        watch = (String)existWatchesIter.next();
                        existWatchesBatch.add(watch);
                     } else if (childWatchesIter.hasNext()) {
                        watch = (String)childWatchesIter.next();
                        childWatchesBatch.add(watch);
                     } else if (persistentWatchesIter.hasNext()) {
                        watch = (String)persistentWatchesIter.next();
                        persistentWatchesBatch.add(watch);
                     } else {
                        if (!persistentRecursiveWatchesIter.hasNext()) {
                           break;
                        }

                        watch = (String)persistentRecursiveWatchesIter.next();
                        persistentRecursiveWatchesBatch.add(watch);
                     }
                  }

                  int opcode;
                  Record record;
                  if (persistentWatchesBatch.isEmpty() && persistentRecursiveWatchesBatch.isEmpty()) {
                     record = new SetWatches(setWatchesLastZxid, dataWatchesBatch, existWatchesBatch, childWatchesBatch);
                     opcode = 101;
                  } else {
                     record = new SetWatches2(setWatchesLastZxid, dataWatchesBatch, existWatchesBatch, childWatchesBatch, persistentWatchesBatch, persistentRecursiveWatchesBatch);
                     opcode = 105;
                  }

                  RequestHeader header = new RequestHeader(-8, opcode);
                  Packet packet = new Packet(header, new ReplyHeader(), record, (Record)null, (ZooKeeper.WatchRegistration)null);
                  ClientCnxn.this.outgoingQueue.addFirst(packet);
               }
            }
         }

         for(AuthData id : ClientCnxn.this.authInfo) {
            ClientCnxn.this.outgoingQueue.addFirst(new Packet(new RequestHeader(-4, 100), (ReplyHeader)null, new AuthPacket(0, id.scheme, id.data), (Record)null, (ZooKeeper.WatchRegistration)null));
         }

         ClientCnxn.this.outgoingQueue.addFirst(new Packet((RequestHeader)null, (ReplyHeader)null, conReq, (Record)null, (ZooKeeper.WatchRegistration)null));
         this.clientCnxnSocket.connectionPrimed();
         ClientCnxn.LOG.debug("Session establishment request sent on {}", this.clientCnxnSocket.getRemoteSocketAddress());
      }

      private List prependChroot(List paths) {
         if (ClientCnxn.this.chrootPath != null && !paths.isEmpty()) {
            for(int i = 0; i < paths.size(); ++i) {
               String clientPath = (String)paths.get(i);
               String serverPath;
               if (clientPath.length() == 1) {
                  serverPath = ClientCnxn.this.chrootPath;
               } else {
                  serverPath = ClientCnxn.this.chrootPath + clientPath;
               }

               paths.set(i, serverPath);
            }
         }

         return paths;
      }

      private void sendPing() {
         this.lastPingSentNs = System.nanoTime();
         RequestHeader h = new RequestHeader(-2, 11);
         ClientCnxn.this.queuePacket(h, (ReplyHeader)null, (Record)null, (Record)null, (AsyncCallback)null, (String)null, (String)null, (Object)null, (ZooKeeper.WatchRegistration)null);
      }

      private void startConnect(InetSocketAddress addr) throws IOException {
         this.saslLoginFailed = false;
         if (!this.isFirstConnect) {
            try {
               Thread.sleep(ThreadLocalRandom.current().nextLong(1000L));
            } catch (InterruptedException e) {
               ClientCnxn.LOG.warn("Unexpected exception", e);
            }
         }

         this.changeZkState(ZooKeeper.States.CONNECTING);
         String hostPort = addr.getHostString() + ":" + addr.getPort();
         MDC.put("myid", hostPort);
         this.setName(this.getName().replaceAll("\\(.*\\)", "(" + hostPort + ")"));
         if (ClientCnxn.this.clientConfig.isSaslClientEnabled()) {
            try {
               this.zooKeeperSaslClient = new ZooKeeperSaslClient(SaslServerPrincipal.getServerPrincipal(addr, ClientCnxn.this.clientConfig), ClientCnxn.this.clientConfig, this.loginRef);
            } catch (LoginException e) {
               ClientCnxn.LOG.warn("SASL configuration failed. Will continue connection to Zookeeper server without SASL authentication, if Zookeeper server allows it.", e);
               ClientCnxn.this.eventThread.queueEvent(new WatchedEvent(Watcher.Event.EventType.None, Watcher.Event.KeeperState.AuthFailed, (String)null));
               this.saslLoginFailed = true;
            }
         }

         this.logStartConnect(addr);
         this.clientCnxnSocket.connect(addr);
      }

      private void logStartConnect(InetSocketAddress addr) {
         ClientCnxn.LOG.info("Opening socket connection to server {}.", addr);
         if (this.zooKeeperSaslClient != null) {
            ClientCnxn.LOG.info("SASL config status: {}", this.zooKeeperSaslClient.getConfigStatus());
         }

      }

      @SuppressFBWarnings({"JLM_JSR166_UTILCONCURRENT_MONITORENTER"})
      public void run() {
         this.clientCnxnSocket.introduce(this, ClientCnxn.this.sessionId, ClientCnxn.this.outgoingQueue);
         this.clientCnxnSocket.updateNow();
         this.clientCnxnSocket.updateLastSendAndHeard();
         long lastPingRwServer = Time.currentElapsedTime();
         int MAX_SEND_PING_INTERVAL = 10000;
         InetSocketAddress serverAddress = null;

         while(true) {
            while(ClientCnxn.this.state.isAlive()) {
               try {
                  if (!this.clientCnxnSocket.isConnected()) {
                     if (ClientCnxn.this.closing) {
                        break;
                     }

                     if (this.rwServerAddress != null) {
                        serverAddress = this.rwServerAddress;
                        this.rwServerAddress = null;
                     } else {
                        serverAddress = ClientCnxn.this.hostProvider.next(1000L);
                     }

                     ClientCnxn.this.onConnecting(serverAddress);
                     this.startConnect(serverAddress);
                     this.clientCnxnSocket.updateNow();
                     this.clientCnxnSocket.updateLastSend();
                  }

                  int to;
                  if (ClientCnxn.this.state.isConnected()) {
                     if (this.zooKeeperSaslClient != null) {
                        boolean sendAuthEvent = false;
                        if (this.zooKeeperSaslClient.getSaslState() == ZooKeeperSaslClient.SaslState.INITIAL) {
                           try {
                              this.zooKeeperSaslClient.initialize(ClientCnxn.this);
                           } catch (SaslException e) {
                              ClientCnxn.LOG.error("SASL authentication with Zookeeper Quorum member failed.", e);
                              this.changeZkState(ZooKeeper.States.AUTH_FAILED);
                              sendAuthEvent = true;
                           }
                        }

                        Watcher.Event.KeeperState authState = this.zooKeeperSaslClient.getKeeperState();
                        if (authState != null) {
                           if (authState == Watcher.Event.KeeperState.AuthFailed) {
                              this.changeZkState(ZooKeeper.States.AUTH_FAILED);
                              sendAuthEvent = true;
                           } else if (authState == Watcher.Event.KeeperState.SaslAuthenticated) {
                              sendAuthEvent = true;
                           }
                        }

                        if (sendAuthEvent) {
                           ClientCnxn.this.eventThread.queueEvent(new WatchedEvent(Watcher.Event.EventType.None, authState, (String)null));
                           if (ClientCnxn.this.state == ZooKeeper.States.AUTH_FAILED) {
                              ClientCnxn.this.eventThread.queueEventOfDeath();
                           }
                        }
                     }

                     to = ClientCnxn.this.readTimeout - this.clientCnxnSocket.getIdleRecv();
                  } else {
                     to = ClientCnxn.this.connectTimeout - this.clientCnxnSocket.getIdleSend();
                  }

                  int expiration = ClientCnxn.this.expirationTimeout - this.clientCnxnSocket.getIdleRecv();
                  if (expiration <= 0) {
                     String warnInfo = String.format("Client session timed out, have not heard from server in %dms for session id 0x%s", this.clientCnxnSocket.getIdleRecv(), Long.toHexString(ClientCnxn.this.sessionId));
                     ClientCnxn.LOG.warn(warnInfo);
                     this.changeZkState(ZooKeeper.States.CLOSED);
                     throw new SessionTimeoutException(warnInfo);
                  }

                  if (to <= 0) {
                     String warnInfo = String.format("Client connection timed out, have not heard from server in %dms for session id 0x%s", this.clientCnxnSocket.getIdleRecv(), Long.toHexString(ClientCnxn.this.sessionId));
                     throw new ConnectionTimeoutException(warnInfo);
                  }

                  if (ClientCnxn.this.state.isConnected()) {
                     int timeToNextPing = ClientCnxn.this.readTimeout / 2 - this.clientCnxnSocket.getIdleSend() - (this.clientCnxnSocket.getIdleSend() > 1000 ? 1000 : 0);
                     if (timeToNextPing > 0 && this.clientCnxnSocket.getIdleSend() <= 10000) {
                        if (timeToNextPing < to) {
                           to = timeToNextPing;
                        }
                     } else {
                        this.sendPing();
                        this.clientCnxnSocket.updateLastSend();
                     }
                  }

                  if (ClientCnxn.this.state == ZooKeeper.States.CONNECTEDREADONLY) {
                     long now = Time.currentElapsedTime();
                     int idlePingRwServer = (int)(now - lastPingRwServer);
                     if (idlePingRwServer >= this.pingRwTimeout) {
                        lastPingRwServer = now;
                        idlePingRwServer = 0;
                        this.pingRwTimeout = Math.min(2 * this.pingRwTimeout, 60000);
                        this.pingRwServer();
                     }

                     to = Math.min(to, this.pingRwTimeout - idlePingRwServer);
                  }

                  this.clientCnxnSocket.doTransport(to, ClientCnxn.this.pendingQueue, ClientCnxn.this);
               } catch (Throwable e) {
                  if (ClientCnxn.this.closing) {
                     if (ClientCnxn.LOG.isDebugEnabled()) {
                        ClientCnxn.LOG.debug("An exception was thrown while closing send thread for session 0x{}.", Long.toHexString(ClientCnxn.this.getSessionId()), e);
                     }
                     break;
                  }

                  ClientCnxn.LOG.warn("Session 0x{} for server {}, Closing socket connection. Attempting reconnect except it is a SessionExpiredException or SessionTimeoutException.", new Object[]{Long.toHexString(ClientCnxn.this.getSessionId()), serverAddress, e});
                  this.cleanAndNotifyState();
               }
            }

            synchronized(ClientCnxn.this.outgoingQueue) {
               this.cleanup();
            }

            this.clientCnxnSocket.close();
            if (ClientCnxn.this.state.isAlive()) {
               ClientCnxn.this.eventThread.queueEvent(new WatchedEvent(Watcher.Event.EventType.None, Watcher.Event.KeeperState.Disconnected, (String)null));
            }

            if (ClientCnxn.this.closing) {
               ClientCnxn.this.eventThread.queueEvent(new WatchedEvent(Watcher.Event.EventType.None, Watcher.Event.KeeperState.Closed, (String)null));
            } else if (ClientCnxn.this.state == ZooKeeper.States.CLOSED) {
               ClientCnxn.this.eventThread.queueEvent(new WatchedEvent(Watcher.Event.EventType.None, Watcher.Event.KeeperState.Expired, (String)null));
            }

            ClientCnxn.this.eventThread.queueEventOfDeath();
            Login l = (Login)this.loginRef.getAndSet((Object)null);
            if (l != null) {
               l.shutdown();
            }

            ZooTrace.logTraceMessage(ClientCnxn.LOG, ZooTrace.getTextTraceLevel(), "SendThread exited loop for session: 0x" + Long.toHexString(ClientCnxn.this.getSessionId()));
            return;
         }
      }

      private void cleanAndNotifyState() {
         this.cleanup();
         if (ClientCnxn.this.state.isAlive()) {
            ClientCnxn.this.eventThread.queueEvent(new WatchedEvent(Watcher.Event.EventType.None, Watcher.Event.KeeperState.Disconnected, (String)null));
         }

         this.clientCnxnSocket.updateNow();
      }

      private void pingRwServer() throws RWServerFoundException {
         String result = null;
         InetSocketAddress addr = ClientCnxn.this.hostProvider.next(0L);
         ClientCnxn.LOG.info("Checking server {} for being r/w. Timeout {}", addr, this.pingRwTimeout);
         Socket sock = null;
         BufferedReader br = null;

         try {
            sock = new Socket(addr.getHostString(), addr.getPort());
            sock.setSoLinger(false, -1);
            sock.setSoTimeout(1000);
            sock.setTcpNoDelay(true);
            sock.getOutputStream().write("isro".getBytes());
            sock.getOutputStream().flush();
            sock.shutdownOutput();
            br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            result = br.readLine();
         } catch (ConnectException var21) {
         } catch (IOException e) {
            ClientCnxn.LOG.warn("Exception while seeking for r/w server.", e);
         } finally {
            if (sock != null) {
               try {
                  sock.close();
               } catch (IOException e) {
                  ClientCnxn.LOG.warn("Unexpected exception", e);
               }
            }

            if (br != null) {
               try {
                  br.close();
               } catch (IOException e) {
                  ClientCnxn.LOG.warn("Unexpected exception", e);
               }
            }

         }

         if ("rw".equals(result)) {
            this.pingRwTimeout = 100;
            this.rwServerAddress = addr;
            throw new RWServerFoundException("Majority server found at " + addr.getHostString() + ":" + addr.getPort());
         }
      }

      private void cleanup() {
         this.clientCnxnSocket.cleanup();
         synchronized(ClientCnxn.this.pendingQueue) {
            for(Packet p : ClientCnxn.this.pendingQueue) {
               ClientCnxn.this.conLossPacket(p);
            }

            ClientCnxn.this.pendingQueue.clear();
         }

         Iterator<Packet> iter = ClientCnxn.this.outgoingQueue.iterator();

         while(iter.hasNext()) {
            Packet p = (Packet)iter.next();
            ClientCnxn.this.conLossPacket(p);
            iter.remove();
         }

      }

      void onConnected(int _negotiatedSessionTimeout, long _sessionId, byte[] _sessionPasswd, boolean isRO) throws IOException {
         ClientCnxn.this.negotiatedSessionTimeout = _negotiatedSessionTimeout;
         if (ClientCnxn.this.negotiatedSessionTimeout <= 0) {
            this.changeZkState(ZooKeeper.States.CLOSED);
            ClientCnxn.this.eventThread.queueEvent(new WatchedEvent(Watcher.Event.EventType.None, Watcher.Event.KeeperState.Expired, (String)null));
            ClientCnxn.this.eventThread.queueEventOfDeath();
            String warnInfo = String.format("Unable to reconnect to ZooKeeper service, session 0x%s has expired", Long.toHexString(ClientCnxn.this.sessionId));
            ClientCnxn.LOG.warn(warnInfo);
            throw new SessionExpiredException(warnInfo);
         } else {
            if (!ClientCnxn.this.readOnly && isRO) {
               ClientCnxn.LOG.error("Read/write client got connected to read-only server");
            }

            ClientCnxn.this.readTimeout = ClientCnxn.this.negotiatedSessionTimeout * 2 / 3;
            ClientCnxn.this.expirationTimeout = ClientCnxn.this.negotiatedSessionTimeout * 4 / 3;
            ClientCnxn.this.connectTimeout = ClientCnxn.this.negotiatedSessionTimeout / ClientCnxn.this.hostProvider.size();
            ClientCnxn.this.hostProvider.onConnected();
            ClientCnxn.this.sessionId = _sessionId;
            ClientCnxn.this.sessionPasswd = _sessionPasswd;
            this.changeZkState(isRO ? ZooKeeper.States.CONNECTEDREADONLY : ZooKeeper.States.CONNECTED);
            ClientCnxn var10000 = ClientCnxn.this;
            var10000.seenRwServerBefore |= !isRO;
            ClientCnxn.LOG.info("Session establishment complete on server {}, session id = 0x{}, negotiated timeout = {}{}", new Object[]{this.clientCnxnSocket.getRemoteSocketAddress(), Long.toHexString(ClientCnxn.this.sessionId), ClientCnxn.this.negotiatedSessionTimeout, isRO ? " (READ-ONLY mode)" : ""});
            Watcher.Event.KeeperState eventState = isRO ? Watcher.Event.KeeperState.ConnectedReadOnly : Watcher.Event.KeeperState.SyncConnected;
            ClientCnxn.this.eventThread.queueEvent(new WatchedEvent(Watcher.Event.EventType.None, eventState, (String)null));
         }
      }

      void close() {
         try {
            this.changeZkState(ZooKeeper.States.CLOSED);
         } catch (IOException var2) {
            ClientCnxn.LOG.warn("Connection close fails when migrates state from {} to CLOSED", this.getZkState());
         }

         this.clientCnxnSocket.onClosing();
      }

      void testableCloseSocket() throws IOException {
         this.clientCnxnSocket.testableCloseSocket();
      }

      public boolean tunnelAuthInProgress() {
         if (!ClientCnxn.this.clientConfig.isSaslClientEnabled()) {
            return false;
         } else if (this.saslLoginFailed) {
            return false;
         } else {
            return this.zooKeeperSaslClient == null ? true : this.zooKeeperSaslClient.clientTunneledAuthenticationInProgress();
         }
      }

      public void sendPacket(Packet p) throws IOException {
         this.clientCnxnSocket.sendPacket(p);
      }

      public ZooKeeperSaslClient getZooKeeperSaslClient() {
         return this.zooKeeperSaslClient;
      }

      Login getLogin() {
         return (Login)this.loginRef.get();
      }
   }

   private static class LocalCallback {
      private final AsyncCallback cb;
      private final int rc;
      private final String path;
      private final Object ctx;

      public LocalCallback(AsyncCallback cb, int rc, String path, Object ctx) {
         this.cb = cb;
         this.rc = rc;
         this.path = path;
         this.ctx = ctx;
      }
   }
}
