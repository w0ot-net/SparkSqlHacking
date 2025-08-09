package io.vertx.core.http;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.impl.ws.WebSocketFrameImpl;

@VertxGen
public interface WebSocketFrame {
   static WebSocketFrame binaryFrame(Buffer data, boolean isFinal) {
      return WebSocketFrameImpl.binaryFrame(data, isFinal);
   }

   static WebSocketFrame textFrame(String str, boolean isFinal) {
      return WebSocketFrameImpl.textFrame(str, isFinal);
   }

   static WebSocketFrame pingFrame(Buffer data) {
      return WebSocketFrameImpl.pingFrame(data);
   }

   static WebSocketFrame pongFrame(Buffer data) {
      return WebSocketFrameImpl.pongFrame(data);
   }

   static WebSocketFrame continuationFrame(Buffer data, boolean isFinal) {
      return WebSocketFrameImpl.continuationFrame(data, isFinal);
   }

   WebSocketFrameType type();

   boolean isText();

   boolean isBinary();

   boolean isContinuation();

   boolean isClose();

   boolean isPing();

   @CacheReturn
   String textData();

   @CacheReturn
   Buffer binaryData();

   boolean isFinal();

   short closeStatusCode();

   String closeReason();
}
