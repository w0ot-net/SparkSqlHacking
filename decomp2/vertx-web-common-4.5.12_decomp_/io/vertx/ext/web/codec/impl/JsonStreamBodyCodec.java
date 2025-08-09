package io.vertx.ext.web.codec.impl;

import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.parsetools.JsonParser;
import io.vertx.core.streams.WriteStream;
import io.vertx.ext.web.codec.BodyCodec;
import io.vertx.ext.web.codec.spi.BodyStream;
import java.util.Objects;

public class JsonStreamBodyCodec implements BodyCodec {
   private final JsonParser parser;
   private final StreamingBodyCodec delegate;

   public JsonStreamBodyCodec(final JsonParser parser) {
      this.parser = (JsonParser)Objects.requireNonNull(parser, "The parser must be set");
      this.delegate = new StreamingBodyCodec(new WriteStream() {
         public WriteStream exceptionHandler(Handler handler) {
            parser.exceptionHandler(handler);
            return this;
         }

         public Future write(Buffer buffer) {
            parser.handle(buffer);
            return Future.succeededFuture();
         }

         public void write(Buffer data, Handler handler) {
            parser.handle(data);
            if (handler != null) {
               handler.handle(Future.succeededFuture());
            }

         }

         public void end(Handler handler) {
            parser.end();
            if (handler != null) {
               handler.handle(Future.succeededFuture());
            }

         }

         public WriteStream setWriteQueueMaxSize(int i) {
            return this;
         }

         public boolean writeQueueFull() {
            return false;
         }

         public WriteStream drainHandler(@Nullable Handler handler) {
            return this;
         }
      });
   }

   JsonParser getParser() {
      return this.parser;
   }

   public void create(Handler handler) {
      this.delegate.create(handler);
   }
}
