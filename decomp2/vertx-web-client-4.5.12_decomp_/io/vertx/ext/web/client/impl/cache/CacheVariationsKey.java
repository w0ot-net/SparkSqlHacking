package io.vertx.ext.web.client.impl.cache;

import io.netty.handler.codec.http.QueryStringDecoder;
import io.vertx.core.MultiMap;
import io.vertx.core.http.RequestOptions;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

class CacheVariationsKey {
   protected final String host;
   protected final int port;
   protected final String path;
   protected final String queryString;

   CacheVariationsKey(RequestOptions request) {
      String requestURI = request.getURI();
      QueryStringDecoder dec = new QueryStringDecoder(requestURI);
      this.host = request.getHost();
      this.port = request.getPort();
      this.path = dec.path();
      this.queryString = this.queryString(dec.parameters());
   }

   public String toString() {
      return this.host + ":" + this.port + this.path + "?" + this.queryString;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         CacheVariationsKey that = (CacheVariationsKey)o;
         return this.port == that.port && this.host.equals(that.host) && this.path.equals(that.path) && this.queryString.equals(that.queryString);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.host, this.port, this.path, this.queryString});
   }

   private String queryString(Map queryParams) {
      MultiMap mm = MultiMap.caseInsensitiveMultiMap();
      queryParams.forEach(mm::set);
      return (String)mm.entries().stream().sorted((a, b) -> ((String)a.getKey()).compareToIgnoreCase((String)b.getKey())).map((e) -> (String)e.getKey() + "=" + (String)e.getValue()).collect(Collectors.joining("&"));
   }
}
