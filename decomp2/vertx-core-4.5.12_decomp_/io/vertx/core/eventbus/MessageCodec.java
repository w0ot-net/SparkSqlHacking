package io.vertx.core.eventbus;

import io.vertx.core.buffer.Buffer;

public interface MessageCodec {
   void encodeToWire(Buffer var1, Object var2);

   Object decodeFromWire(int var1, Buffer var2);

   Object transform(Object var1);

   String name();

   byte systemCodecID();
}
