package io.vertx.ext.web.codec;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.parsetools.JsonParser;
import io.vertx.core.streams.WriteStream;
import io.vertx.ext.web.codec.impl.BodyCodecImpl;
import io.vertx.ext.web.codec.impl.JsonStreamBodyCodec;
import io.vertx.ext.web.codec.impl.StreamingBodyCodec;
import java.util.function.Function;

@VertxGen
public interface BodyCodec {
   static BodyCodec string() {
      return BodyCodecImpl.STRING;
   }

   static BodyCodec string(String encoding) {
      return BodyCodecImpl.string(encoding);
   }

   static BodyCodec buffer() {
      return BodyCodecImpl.BUFFER;
   }

   static BodyCodec jsonObject() {
      return BodyCodecImpl.JSON_OBJECT;
   }

   static BodyCodec jsonArray() {
      return BodyCodecImpl.JSON_ARRAY;
   }

   static BodyCodec json(Class type) {
      return BodyCodecImpl.json(type);
   }

   static BodyCodec none() {
      return BodyCodecImpl.NONE;
   }

   static BodyCodec create(Function decode) {
      return new BodyCodecImpl(decode);
   }

   static BodyCodec pipe(WriteStream stream) {
      return pipe(stream, true);
   }

   static BodyCodec pipe(WriteStream stream, boolean close) {
      StreamingBodyCodec bodyCodec = new StreamingBodyCodec(stream, close);
      bodyCodec.init();
      return bodyCodec;
   }

   static BodyCodec jsonStream(JsonParser parser) {
      return new JsonStreamBodyCodec(parser);
   }

   @GenIgnore
   void create(Handler var1);
}
