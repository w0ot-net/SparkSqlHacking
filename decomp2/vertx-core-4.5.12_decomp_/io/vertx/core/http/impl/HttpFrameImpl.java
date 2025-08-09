package io.vertx.core.http.impl;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpFrame;

public class HttpFrameImpl implements HttpFrame {
   private final int type;
   private final int flags;
   private final Buffer payload;

   public HttpFrameImpl(int type, int flags, Buffer payload) {
      this.type = type;
      this.flags = flags;
      this.payload = payload;
   }

   public int flags() {
      return this.flags;
   }

   public int type() {
      return this.type;
   }

   public Buffer payload() {
      return this.payload;
   }
}
