package io.fabric8.kubernetes.client.http;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WebSocketUpgradeResponse extends StandardHttpHeaders implements HttpResponse {
   private final HttpRequest httpRequest;
   private final int code;

   public WebSocketUpgradeResponse(HttpRequest httpRequest) {
      this(httpRequest, 101, new LinkedHashMap());
   }

   public WebSocketUpgradeResponse(HttpRequest httpRequest, int code) {
      this(httpRequest, code, new LinkedHashMap());
   }

   public WebSocketUpgradeResponse(HttpRequest httpRequest, int code, Map headers) {
      super(headers);
      this.httpRequest = httpRequest;
      this.code = code;
   }

   public int code() {
      return this.code;
   }

   public Void body() {
      return null;
   }

   public HttpRequest request() {
      return this.httpRequest;
   }

   public Optional previousResponse() {
      return Optional.empty();
   }
}
