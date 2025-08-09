package io.vertx.core.eventbus.impl.clustered;

import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.core.eventbus.impl.OutboundDeliveryContext;
import io.vertx.core.eventbus.impl.codecs.PingMessageCodec;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.impl.ConnectionBase;
import io.vertx.core.spi.cluster.NodeInfo;
import io.vertx.core.spi.metrics.EventBusMetrics;
import java.util.ArrayDeque;
import java.util.Queue;

class ConnectionHolder {
   private static final Logger log = LoggerFactory.getLogger(ConnectionHolder.class);
   private static final String PING_ADDRESS = "__vertx_ping";
   private final ClusteredEventBus eventBus;
   private final String remoteNodeId;
   private final VertxInternal vertx;
   private final EventBusMetrics metrics;
   private Queue pending;
   private NetSocket socket;
   private boolean connected;
   private long timeoutID = -1L;
   private long pingTimeoutID = -1L;

   ConnectionHolder(ClusteredEventBus eventBus, String remoteNodeId) {
      this.eventBus = eventBus;
      this.remoteNodeId = remoteNodeId;
      this.vertx = eventBus.vertx();
      this.metrics = eventBus.getMetrics();
   }

   void connect() {
      Promise<NodeInfo> promise = Promise.promise();
      this.eventBus.vertx().getClusterManager().getNodeInfo(this.remoteNodeId, promise);
      promise.future().flatMap((info) -> this.eventBus.client().connect(info.port(), info.host())).onComplete((ar) -> {
         if (ar.succeeded()) {
            this.connected((NetSocket)ar.result());
         } else {
            log.warn("Connecting to server " + this.remoteNodeId + " failed", ar.cause());
            this.close(ar.cause());
         }

      });
   }

   synchronized void writeMessage(OutboundDeliveryContext ctx) {
      if (this.connected) {
         Buffer data = ((ClusteredMessage)ctx.message).encodeToWire();
         if (this.metrics != null) {
            this.metrics.messageWritten(ctx.message.address(), data.length());
         }

         this.socket.write((Buffer)data, (Handler)ctx);
      } else {
         if (this.pending == null) {
            if (log.isDebugEnabled()) {
               log.debug("Not connected to server " + this.remoteNodeId + " - starting queuing");
            }

            this.pending = new ArrayDeque();
         }

         this.pending.add(ctx);
      }

   }

   void close() {
      this.close(ConnectionBase.CLOSED_EXCEPTION);
   }

   private void close(Throwable cause) {
      if (this.timeoutID != -1L) {
         this.vertx.cancelTimer(this.timeoutID);
      }

      if (this.pingTimeoutID != -1L) {
         this.vertx.cancelTimer(this.pingTimeoutID);
      }

      synchronized(this) {
         OutboundDeliveryContext<?> msg;
         if (this.pending != null) {
            while((msg = (OutboundDeliveryContext)this.pending.poll()) != null) {
               msg.written(cause);
            }
         }
      }

      if (this.eventBus.connections().remove(this.remoteNodeId, this) && log.isDebugEnabled()) {
         log.debug("Cluster connection closed for server " + this.remoteNodeId);
      }

   }

   private void schedulePing() {
      EventBusOptions options = this.eventBus.options();
      this.pingTimeoutID = this.vertx.setTimer(options.getClusterPingInterval(), (id1) -> {
         this.timeoutID = this.vertx.setTimer(options.getClusterPingReplyInterval(), (id2) -> {
            log.warn("No pong from server " + this.remoteNodeId + " - will consider it dead");
            this.close();
         });
         ClusteredMessage pingMessage = new ClusteredMessage(this.remoteNodeId, "__vertx_ping", (MultiMap)null, (Object)null, new PingMessageCodec(), true, this.eventBus);
         Buffer data = pingMessage.encodeToWire();
         this.socket.write(data);
      });
   }

   private synchronized void connected(NetSocket socket) {
      this.socket = socket;
      this.connected = true;
      socket.exceptionHandler((err) -> this.close(err));
      socket.closeHandler((v) -> this.close());
      socket.handler((datax) -> {
         this.vertx.cancelTimer(this.timeoutID);
         this.schedulePing();
      });
      this.schedulePing();
      if (this.pending != null) {
         if (log.isDebugEnabled()) {
            log.debug("Draining the queue for server " + this.remoteNodeId);
         }

         for(OutboundDeliveryContext ctx : this.pending) {
            Buffer data = ((ClusteredMessage)ctx.message).encodeToWire();
            if (this.metrics != null) {
               this.metrics.messageWritten(ctx.message.address(), data.length());
            }

            socket.write((Buffer)data, (Handler)ctx);
         }
      }

      this.pending = null;
   }
}
