package io.vertx.core.http.impl;

import io.vertx.core.http.WebSocket;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.spi.metrics.HttpClientMetrics;
import io.vertx.core.spi.metrics.Metrics;

public class WebSocketImpl extends WebSocketImplBase implements WebSocket {
   private final Http1xClientConnection conn;
   private final long closingTimeoutMS;

   public WebSocketImpl(ContextInternal context, Http1xClientConnection conn, boolean supportsContinuation, long closingTimeout, int maxWebSocketFrameSize, int maxWebSocketMessageSize, boolean registerWebSocketWriteHandlers) {
      super(context, conn, supportsContinuation, maxWebSocketFrameSize, maxWebSocketMessageSize, registerWebSocketWriteHandlers);
      this.conn = conn;
      this.closingTimeoutMS = closingTimeout >= 0L ? closingTimeout * 1000L : -1L;
   }

   protected void handleCloseConnection() {
      if (this.closingTimeoutMS == 0L) {
         this.closeConnection();
      } else if (this.closingTimeoutMS > 0L) {
         this.initiateConnectionCloseTimeout(this.closingTimeoutMS);
      }

   }

   protected void handleClose(boolean graceful) {
      HttpClientMetrics metrics = this.conn.metrics();
      if (Metrics.METRICS_ENABLED && metrics != null) {
         metrics.disconnected(this.getMetric());
         this.setMetric((Object)null);
      }

      super.handleClose(graceful);
   }
}
