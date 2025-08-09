package org.sparkproject.jetty.client;

import org.sparkproject.jetty.client.api.Connection;

public interface IConnection extends Connection {
   SendFailure send(HttpExchange var1);
}
