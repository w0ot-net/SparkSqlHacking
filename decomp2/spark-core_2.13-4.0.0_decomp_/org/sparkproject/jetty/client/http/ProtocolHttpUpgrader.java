package org.sparkproject.jetty.client.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.HttpClient;
import org.sparkproject.jetty.client.HttpClientTransport;
import org.sparkproject.jetty.client.HttpDestination;
import org.sparkproject.jetty.client.HttpRequest;
import org.sparkproject.jetty.client.HttpResponse;
import org.sparkproject.jetty.client.HttpResponseException;
import org.sparkproject.jetty.client.HttpUpgrader;
import org.sparkproject.jetty.client.Origin;
import org.sparkproject.jetty.client.dynamic.HttpClientTransportDynamic;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.Promise;

public class ProtocolHttpUpgrader implements HttpUpgrader {
   private static final Logger LOG = LoggerFactory.getLogger(ProtocolHttpUpgrader.class);
   private final HttpDestination destination;
   private final String protocol;

   public ProtocolHttpUpgrader(HttpDestination destination, String protocol) {
      this.destination = destination;
      this.protocol = protocol;
   }

   public void prepare(HttpRequest request) {
   }

   public void upgrade(HttpResponse response, EndPoint endPoint, Callback callback) {
      if (response.getHeaders().contains(HttpHeader.UPGRADE, this.protocol)) {
         HttpClient httpClient = this.destination.getHttpClient();
         HttpClientTransport transport = httpClient.getTransport();
         if (transport instanceof HttpClientTransportDynamic) {
            HttpClientTransportDynamic dynamicTransport = (HttpClientTransportDynamic)transport;
            Origin origin = this.destination.getOrigin();
            Origin newOrigin = new Origin(origin.getScheme(), origin.getAddress(), origin.getTag(), new Origin.Protocol(List.of(this.protocol), false));
            HttpDestination newDestination = httpClient.resolveDestination(newOrigin);
            Map<String, Object> context = new HashMap();
            context.put("org.sparkproject.jetty.client.destination", newDestination);
            context.put(HttpResponse.class.getName(), response);
            Consumer var10002 = (y) -> callback.succeeded();
            Objects.requireNonNull(callback);
            context.put("org.sparkproject.jetty.client.connection.promise", Promise.from(var10002, callback::failed));
            if (LOG.isDebugEnabled()) {
               LOG.debug("Upgrading {} on {}", response.getRequest(), endPoint);
            }

            dynamicTransport.upgrade(endPoint, context);
         } else {
            callback.failed(new HttpResponseException(HttpClientTransportDynamic.class.getName() + " required to upgrade to: " + this.protocol, response));
         }
      } else {
         callback.failed(new HttpResponseException("Not an upgrade to: " + this.protocol, response));
      }

   }
}
