package io.vertx.core.http;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.WriteStream;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.security.cert.X509Certificate;

@VertxGen(
   concrete = false
)
public interface WebSocketBase extends ReadStream, WriteStream {
   WebSocketBase exceptionHandler(Handler var1);

   WebSocketBase handler(Handler var1);

   WebSocketBase pause();

   WebSocketBase resume();

   WebSocketBase fetch(long var1);

   WebSocketBase endHandler(Handler var1);

   WebSocketBase setWriteQueueMaxSize(int var1);

   WebSocketBase drainHandler(Handler var1);

   String binaryHandlerID();

   String textHandlerID();

   String subProtocol();

   Short closeStatusCode();

   String closeReason();

   MultiMap headers();

   Future writeFrame(WebSocketFrame var1);

   @Fluent
   WebSocketBase writeFrame(WebSocketFrame var1, Handler var2);

   Future writeFinalTextFrame(String var1);

   @Fluent
   WebSocketBase writeFinalTextFrame(String var1, Handler var2);

   Future writeFinalBinaryFrame(Buffer var1);

   @Fluent
   WebSocketBase writeFinalBinaryFrame(Buffer var1, Handler var2);

   Future writeBinaryMessage(Buffer var1);

   @Fluent
   WebSocketBase writeBinaryMessage(Buffer var1, Handler var2);

   Future writeTextMessage(String var1);

   @Fluent
   WebSocketBase writeTextMessage(String var1, Handler var2);

   @Fluent
   WebSocketBase writePing(Buffer var1, Handler var2);

   Future writePing(Buffer var1);

   @Fluent
   WebSocketBase writePong(Buffer var1, Handler var2);

   Future writePong(Buffer var1);

   @Fluent
   WebSocketBase closeHandler(@Nullable Handler var1);

   @Fluent
   WebSocketBase frameHandler(@Nullable Handler var1);

   @Fluent
   WebSocketBase textMessageHandler(@Nullable Handler var1);

   @Fluent
   WebSocketBase binaryMessageHandler(@Nullable Handler var1);

   @Fluent
   WebSocketBase pongHandler(@Nullable Handler var1);

   Future end();

   void end(Handler var1);

   Future close();

   void close(Handler var1);

   Future close(short var1);

   void close(short var1, Handler var2);

   Future close(short var1, @Nullable String var2);

   void close(short var1, @Nullable String var2, Handler var3);

   @CacheReturn
   SocketAddress remoteAddress();

   @CacheReturn
   SocketAddress localAddress();

   boolean isSsl();

   boolean isClosed();

   @GenIgnore({"permitted-type"})
   SSLSession sslSession();

   /** @deprecated */
   @Deprecated
   @GenIgnore
   X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException;

   @GenIgnore
   List peerCertificates() throws SSLPeerUnverifiedException;
}
