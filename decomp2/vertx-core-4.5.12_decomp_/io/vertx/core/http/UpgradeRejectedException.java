package io.vertx.core.http;

import io.vertx.core.MultiMap;
import io.vertx.core.VertxException;
import io.vertx.core.buffer.Buffer;

public class UpgradeRejectedException extends VertxException {
   private final int status;
   private final MultiMap headers;
   private final Buffer body;

   public UpgradeRejectedException(String message, int status, MultiMap headers, Buffer content) {
      super(message);
      this.status = status;
      this.headers = headers;
      this.body = content;
   }

   public int getStatus() {
      return this.status;
   }

   public MultiMap getHeaders() {
      return this.headers;
   }

   public Buffer getBody() {
      return this.body;
   }
}
