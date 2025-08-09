package io.vertx.core.parsetools;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.parsetools.impl.JsonParserImpl;
import io.vertx.core.streams.ReadStream;

@VertxGen
public interface JsonParser extends Handler, ReadStream {
   static JsonParser newParser() {
      return new JsonParserImpl((ReadStream)null);
   }

   static JsonParser newParser(ReadStream stream) {
      return new JsonParserImpl(stream);
   }

   @Fluent
   JsonParser write(Buffer var1);

   void end();

   @Fluent
   JsonParser objectEventMode();

   @Fluent
   JsonParser objectValueMode();

   @Fluent
   JsonParser arrayEventMode();

   @Fluent
   JsonParser arrayValueMode();

   JsonParser pause();

   JsonParser resume();

   JsonParser fetch(long var1);

   @Fluent
   JsonParser endHandler(Handler var1);

   @Fluent
   JsonParser handler(Handler var1);

   @Fluent
   JsonParser exceptionHandler(Handler var1);
}
