package io.vertx.core.http.impl;

import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.http.ServerWebSocketHandshake;
import io.vertx.core.net.HostAndPort;
import io.vertx.core.net.SocketAddress;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

public class Http1xServerRequestHandler implements Handler {
   private final HttpServerConnectionHandler handlers;

   public Http1xServerRequestHandler(HttpServerConnectionHandler handlers) {
      this.handlers = handlers;
   }

   public void handle(HttpServerRequest req) {
      Handler<ServerWebSocket> wsHandler = this.handlers.wsHandler;
      Handler<HttpServerRequest> reqHandler = this.handlers.requestHandler;
      Handler<ServerWebSocketHandshake> wsHandshakeHandler = this.handlers.wsHandshakeHandler;
      if (wsHandler != null) {
         if (req.headers().contains(HttpHeaders.UPGRADE, HttpHeaders.WEBSOCKET, true) && this.handlers.server.wsAccept()) {
            Future<ServerWebSocket> fut = ((Http1xServerRequest)req).webSocket();
            if (wsHandshakeHandler != null) {
               fut.onComplete((ar) -> {
                  if (ar.succeeded()) {
                     final ServerWebSocket ws = (ServerWebSocket)ar.result();
                     final Promise<Integer> promise = Promise.promise();
                     ws.setHandshake(promise.future());
                     wsHandshakeHandler.handle(new ServerWebSocketHandshake() {
                        public MultiMap headers() {
                           return ws.headers();
                        }

                        public @Nullable String scheme() {
                           return ws.scheme();
                        }

                        public @Nullable HostAndPort authority() {
                           return ws.authority();
                        }

                        public String uri() {
                           return ws.uri();
                        }

                        public String path() {
                           return ws.path();
                        }

                        public @Nullable String query() {
                           return ws.query();
                        }

                        public Future accept() {
                           promise.complete(101);
                           wsHandler.handle(ws);
                           return Future.succeededFuture(ws);
                        }

                        public Future reject(int status) {
                           promise.complete(status);
                           return Future.succeededFuture();
                        }

                        public SocketAddress remoteAddress() {
                           return ws.remoteAddress();
                        }

                        public SocketAddress localAddress() {
                           return ws.localAddress();
                        }

                        public boolean isSsl() {
                           return ws.isSsl();
                        }

                        public SSLSession sslSession() {
                           return ws.sslSession();
                        }

                        public List peerCertificates() throws SSLPeerUnverifiedException {
                           return ws.peerCertificates();
                        }
                     });
                  }

               });
            } else {
               fut.onComplete((ar) -> {
                  if (ar.succeeded()) {
                     ServerWebSocketImpl ws = (ServerWebSocketImpl)ar.result();
                     wsHandler.handle(ws);
                     ws.tryHandshake(101);
                  }

               });
            }
         } else if (reqHandler != null) {
            reqHandler.handle(req);
         } else {
            req.response().setStatusCode(400).end();
         }
      } else if (req.version() == null) {
         req.response().setStatusCode(501).end();
         req.response().close();
      } else {
         reqHandler.handle(req);
      }

   }
}
