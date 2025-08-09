package org.sparkproject.jetty.client;

import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.client.api.Response;

public interface ProtocolHandler {
   String getName();

   boolean accept(Request var1, Response var2);

   Response.Listener getResponseListener();
}
