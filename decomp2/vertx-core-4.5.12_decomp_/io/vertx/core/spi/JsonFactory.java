package io.vertx.core.spi;

import io.vertx.core.spi.json.JsonCodec;

public interface JsonFactory {
   static JsonFactory load() {
      return Utils.load();
   }

   default int order() {
      return Integer.MAX_VALUE;
   }

   JsonCodec codec();
}
