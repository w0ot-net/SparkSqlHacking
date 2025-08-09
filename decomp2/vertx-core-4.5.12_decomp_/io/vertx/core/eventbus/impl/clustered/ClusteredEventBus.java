package io.vertx.core.eventbus.impl.clustered;

import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.VertxOptions;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.AddressHelper;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.eventbus.impl.CodecManager;
import io.vertx.core.eventbus.impl.EventBusImpl;
import io.vertx.core.eventbus.impl.HandlerHolder;
import io.vertx.core.eventbus.impl.HandlerRegistration;
import io.vertx.core.eventbus.impl.MessageImpl;
import io.vertx.core.eventbus.impl.OutboundDeliveryContext;
import io.vertx.core.impl.CloseFuture;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.Deployment;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.impl.WorkerPool;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.impl.utils.ConcurrentCyclicSequence;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.impl.NetClientBuilder;
import io.vertx.core.parsetools.RecordParser;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.core.spi.cluster.NodeInfo;
import io.vertx.core.spi.cluster.NodeSelector;
import io.vertx.core.spi.cluster.RegistrationInfo;
import io.vertx.core.spi.metrics.VertxMetrics;
import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class ClusteredEventBus extends EventBusImpl {
   private static final Logger log = LoggerFactory.getLogger(ClusteredEventBus.class);
   private static final Buffer PONG = Buffer.buffer(new byte[]{1});
   private final EventBusOptions options;
   private final ClusterManager clusterManager;
   private final NodeSelector nodeSelector;
   private final AtomicLong handlerSequence = new AtomicLong(0L);
   private final NetClient client;
   private final ConcurrentMap connections = new ConcurrentHashMap();
   private final CloseFuture closeFuture;
   private final ContextInternal ebContext;
   private NodeInfo nodeInfo;
   private String nodeId;
   private NetServer server;

   public ClusteredEventBus(VertxInternal vertx, VertxOptions options, ClusterManager clusterManager, NodeSelector nodeSelector) {
      super(vertx);
      this.options = options.getEventBusOptions();
      this.clusterManager = clusterManager;
      this.nodeSelector = nodeSelector;
      this.closeFuture = new CloseFuture(log);
      this.ebContext = vertx.createEventLoopContext((Deployment)null, this.closeFuture, (WorkerPool)null, Thread.currentThread().getContextClassLoader());
      this.client = this.createNetClient(vertx, (new NetClientOptions(this.options.toJson())).setHostnameVerificationAlgorithm(""), this.closeFuture);
   }

   private NetClient createNetClient(VertxInternal vertx, NetClientOptions clientOptions, CloseFuture closeFuture) {
      NetClientBuilder builder = new NetClientBuilder(vertx, clientOptions);
      VertxMetrics metricsSPI = vertx.metricsSPI();
      if (metricsSPI != null) {
         builder.metrics(metricsSPI.createNetClientMetrics(clientOptions));
      }

      builder.closeFuture(closeFuture);
      return builder.build();
   }

   NetClient client() {
      return this.client;
   }

   private NetServerOptions getServerOptions() {
      return new NetServerOptions(this.options.toJson());
   }

   public void start(Promise promise) {
      NetServerOptions serverOptions = this.getServerOptions();
      this.server = this.vertx.createNetServer(serverOptions);
      this.server.connectHandler(this.getServerHandler());
      int port = this.getClusterPort();
      String host = this.getClusterHost();
      this.ebContext.runOnContext((v) -> this.server.listen(port, host).flatMap((v2) -> {
            int publicPort = this.getClusterPublicPort(this.server.actualPort());
            String publicHost = this.getClusterPublicHost(host);
            this.nodeInfo = new NodeInfo(publicHost, publicPort, this.options.getClusterNodeMetadata());
            this.nodeId = this.clusterManager.getNodeId();
            Promise<Void> setPromise = Promise.promise();
            this.clusterManager.setNodeInfo(this.nodeInfo, setPromise);
            return setPromise.future();
         }).andThen((ar) -> {
            if (ar.succeeded()) {
               this.started = true;
               this.nodeSelector.eventBusStarted();
            }

         }).onComplete(promise));
   }

   public void close(Promise promise) {
      Promise<Void> parentClose = Promise.promise();
      super.close(parentClose);
      parentClose.future().transform((ar) -> this.closeFuture.close()).andThen((ar) -> {
         if (this.server != null) {
            for(ConnectionHolder holder : this.connections.values()) {
               holder.close();
            }
         }

      }).onComplete(promise);
   }

   public MessageImpl createMessage(boolean send, boolean isLocal, String address, MultiMap headers, Object body, String codecName) {
      Objects.requireNonNull(address, "no null address accepted");
      MessageCodec codec = this.codecManager.lookupCodec(body, codecName, isLocal);
      ClusteredMessage msg = new ClusteredMessage(this.nodeId, address, headers, body, codec, send, this);
      return msg;
   }

   protected void onLocalRegistration(HandlerHolder handlerHolder, Promise promise) {
      if (!handlerHolder.isReplyHandler()) {
         RegistrationInfo registrationInfo = new RegistrationInfo(this.nodeId, handlerHolder.getSeq(), handlerHolder.isLocalOnly());
         this.clusterManager.addRegistration(handlerHolder.getHandler().address, registrationInfo, (Promise)Objects.requireNonNull(promise));
      } else if (promise != null) {
         promise.complete();
      }

   }

   protected HandlerHolder createHandlerHolder(HandlerRegistration registration, boolean replyHandler, boolean localOnly, ContextInternal context) {
      return new ClusteredHandlerHolder(registration, replyHandler, localOnly, context, this.handlerSequence.getAndIncrement());
   }

   protected void onLocalUnregistration(HandlerHolder handlerHolder, Promise completionHandler) {
      if (!handlerHolder.isReplyHandler()) {
         RegistrationInfo registrationInfo = new RegistrationInfo(this.nodeId, handlerHolder.getSeq(), handlerHolder.isLocalOnly());
         Promise<Void> promise = Promise.promise();
         this.clusterManager.removeRegistration(handlerHolder.getHandler().address, registrationInfo, promise);
         promise.future().onComplete(completionHandler);
      } else {
         completionHandler.complete();
      }

   }

   protected void sendOrPub(OutboundDeliveryContext sendContext) {
      if (((ClusteredMessage)sendContext.message).getRepliedTo() != null) {
         this.clusteredSendReply(((ClusteredMessage)sendContext.message).getRepliedTo(), sendContext);
      } else if (sendContext.options.isLocalOnly()) {
         super.sendOrPub(sendContext);
      } else {
         Serializer serializer = Serializer.get(sendContext.ctx);
         if (sendContext.message.isSend()) {
            Promise<String> promise = sendContext.ctx.promise();
            serializer.queue(sendContext.message, this.nodeSelector::selectForSend, promise);
            promise.future().onComplete((ar) -> {
               if (ar.succeeded()) {
                  this.sendToNode(sendContext, (String)ar.result());
               } else {
                  this.sendOrPublishFailed(sendContext, ar.cause());
               }

            });
         } else {
            Promise<Iterable<String>> promise = sendContext.ctx.promise();
            serializer.queue(sendContext.message, this.nodeSelector::selectForPublish, promise);
            promise.future().onComplete((ar) -> {
               if (ar.succeeded()) {
                  this.sendToNodes(sendContext, (Iterable)ar.result());
               } else {
                  this.sendOrPublishFailed(sendContext, ar.cause());
               }

            });
         }
      }

   }

   private void sendOrPublishFailed(OutboundDeliveryContext sendContext, Throwable cause) {
      if (log.isDebugEnabled()) {
         log.error("Failed to send message", cause);
      }

      sendContext.written(cause);
   }

   protected String generateReplyAddress() {
      return "__vertx.reply." + UUID.randomUUID().toString();
   }

   protected boolean isMessageLocal(MessageImpl msg) {
      ClusteredMessage clusteredMessage = (ClusteredMessage)msg;
      return !clusteredMessage.isFromWire();
   }

   protected HandlerHolder nextHandler(ConcurrentCyclicSequence handlers, boolean messageLocal) {
      HandlerHolder handlerHolder = null;
      if (messageLocal) {
         handlerHolder = (HandlerHolder)handlers.next();
      } else {
         Iterator<HandlerHolder> iterator = handlers.iterator(false);

         while(iterator.hasNext()) {
            HandlerHolder next = (HandlerHolder)iterator.next();
            if (next.isReplyHandler() || !next.isLocalOnly()) {
               handlerHolder = next;
               break;
            }
         }
      }

      return handlerHolder;
   }

   private int getClusterPort() {
      return this.options.getPort();
   }

   private String getClusterHost() {
      String host;
      if ((host = this.options.getHost()) != null) {
         return host;
      } else {
         return (host = this.clusterManager.clusterHost()) != null ? host : AddressHelper.defaultAddress();
      }
   }

   private int getClusterPublicPort(int actualPort) {
      int publicPort = this.options.getClusterPublicPort();
      return publicPort > 0 ? publicPort : actualPort;
   }

   private String getClusterPublicHost(String host) {
      String publicHost;
      if ((publicHost = this.options.getClusterPublicHost()) != null) {
         return publicHost;
      } else if ((publicHost = this.options.getHost()) != null) {
         return publicHost;
      } else {
         return (publicHost = this.clusterManager.clusterPublicHost()) != null ? publicHost : host;
      }
   }

   private Handler getServerHandler() {
      return (socket) -> {
         final RecordParser parser = RecordParser.newFixed(4);
         Handler<Buffer> handler = new Handler() {
            int size = -1;

            public void handle(Buffer buff) {
               if (this.size == -1) {
                  this.size = buff.getInt(0);
                  parser.fixedSizeMode(this.size);
               } else {
                  ClusteredMessage received = new ClusteredMessage(ClusteredEventBus.this);
                  received.readFromWire(buff, ClusteredEventBus.this.codecManager);
                  if (ClusteredEventBus.this.metrics != null) {
                     ClusteredEventBus.this.metrics.messageRead(received.address(), buff.length());
                  }

                  parser.fixedSizeMode(4);
                  this.size = -1;
                  if (received.hasFailure()) {
                     received.internalError();
                  } else if (received.codec() == CodecManager.PING_MESSAGE_CODEC) {
                     socket.write(ClusteredEventBus.PONG);
                  } else {
                     ClusteredEventBus.this.deliverMessageLocally(received);
                  }
               }

            }
         };
         parser.setOutput(handler);
         socket.handler(parser);
      };
   }

   private void sendToNode(OutboundDeliveryContext sendContext, String nodeId) {
      if (nodeId != null && !nodeId.equals(this.nodeId)) {
         this.sendRemote(sendContext, nodeId, sendContext.message);
      } else {
         super.sendOrPub(sendContext);
      }

   }

   private void sendToNodes(OutboundDeliveryContext sendContext, Iterable nodeIds) {
      boolean sentRemote = false;
      if (nodeIds != null) {
         for(String nid : nodeIds) {
            if (!sentRemote) {
               sentRemote = true;
            }

            this.sendToNode(sendContext, nid);
         }
      }

      if (!sentRemote) {
         super.sendOrPub(sendContext);
      }

   }

   private void clusteredSendReply(String replyDest, OutboundDeliveryContext sendContext) {
      MessageImpl message = sendContext.message;
      if (!replyDest.equals(this.nodeId)) {
         this.sendRemote(sendContext, replyDest, message);
      } else {
         super.sendOrPub(sendContext);
      }

   }

   private void sendRemote(OutboundDeliveryContext sendContext, String remoteNodeId, MessageImpl message) {
      ConnectionHolder holder = (ConnectionHolder)this.connections.get(remoteNodeId);
      if (holder == null) {
         holder = new ConnectionHolder(this, remoteNodeId);
         ConnectionHolder prevHolder = (ConnectionHolder)this.connections.putIfAbsent(remoteNodeId, holder);
         if (prevHolder != null) {
            holder = prevHolder;
         } else {
            holder.connect();
         }
      }

      holder.writeMessage(sendContext);
   }

   ConcurrentMap connections() {
      return this.connections;
   }

   VertxInternal vertx() {
      return this.vertx;
   }

   EventBusOptions options() {
      return this.options;
   }
}
