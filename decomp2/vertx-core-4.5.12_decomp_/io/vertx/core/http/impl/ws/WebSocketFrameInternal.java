package io.vertx.core.http.impl.ws;

import io.netty.buffer.ByteBuf;
import io.vertx.core.http.WebSocketFrame;

public interface WebSocketFrameInternal extends WebSocketFrame {
   ByteBuf getBinaryData();

   void setBinaryData(ByteBuf var1);

   void setTextData(String var1);

   int length();
}
