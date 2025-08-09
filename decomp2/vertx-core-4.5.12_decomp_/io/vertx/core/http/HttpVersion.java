package io.vertx.core.http;

import io.vertx.codegen.annotations.VertxGen;

@VertxGen
public enum HttpVersion {
   HTTP_1_0("http/1.0"),
   HTTP_1_1("http/1.1"),
   HTTP_2("h2");

   private final String alpnName;

   private HttpVersion(String alpnName) {
      this.alpnName = alpnName;
   }

   public String alpnName() {
      return this.alpnName;
   }
}
