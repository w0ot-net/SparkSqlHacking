package io.vertx.core.http.impl;

import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.http.ClientWebSocket;
import io.vertx.core.http.WebSocketConnectOptions;

public interface ClientWebSocketInternal extends ClientWebSocket {
   Future connect(Context var1, WebSocketConnectOptions var2);
}
