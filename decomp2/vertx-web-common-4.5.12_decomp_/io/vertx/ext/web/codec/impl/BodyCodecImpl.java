package io.vertx.ext.web.codec.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.streams.WriteStream;
import io.vertx.ext.web.codec.BodyCodec;
import io.vertx.ext.web.codec.spi.BodyStream;
import java.util.function.Function;

public class BodyCodecImpl implements BodyCodec {
   public static final Function VOID_DECODER = (buff) -> null;
   public static final Function UTF8_DECODER = Buffer::toString;
   public static final Function JSON_OBJECT_DECODER = (buff) -> {
      Object val = Json.decodeValue(buff);
      if (val == null) {
         return null;
      } else if (val instanceof JsonObject) {
         return (JsonObject)val;
      } else {
         throw new DecodeException("Invalid Json Object decoded as " + val.getClass().getName());
      }
   };
   public static final Function JSON_ARRAY_DECODER = (buff) -> {
      Object val = Json.decodeValue(buff);
      if (val == null) {
         return null;
      } else if (val instanceof JsonArray) {
         return (JsonArray)val;
      } else {
         throw new DecodeException("Invalid Json Object decoded as " + val.getClass().getName());
      }
   };
   public static final BodyCodec STRING;
   public static final BodyCodec NONE;
   public static final BodyCodec BUFFER;
   public static final BodyCodec JSON_OBJECT;
   public static final BodyCodec JSON_ARRAY;
   private final Function decoder;

   public static BodyCodecImpl string(String encoding) {
      return new BodyCodecImpl((buff) -> buff.toString(encoding));
   }

   public static BodyCodec json(Class type) {
      return new BodyCodecImpl(jsonDecoder(type));
   }

   public static Function jsonDecoder(Class type) {
      return (buff) -> Json.decodeValue(buff, type);
   }

   public BodyCodecImpl(Function decoder) {
      this.decoder = decoder;
   }

   public void create(Handler handler) {
      handler.handle(Future.succeededFuture(new BodyStream() {
         final Buffer buffer = Buffer.buffer();
         final Promise state = Promise.promise();

         public void handle(Throwable cause) {
            this.state.tryFail(cause);
         }

         public Future result() {
            return this.state.future();
         }

         public WriteStream exceptionHandler(Handler handler) {
            return this;
         }

         public void write(Buffer data, Handler handler) {
            this.buffer.appendBuffer(data);
            handler.handle(Future.succeededFuture());
         }

         public Future write(Buffer data) {
            this.buffer.appendBuffer(data);
            return Future.succeededFuture();
         }

         public void end(Handler handler) {
            if (!this.state.future().isComplete()) {
               T result;
               if (this.buffer.length() > 0) {
                  try {
                     result = (T)BodyCodecImpl.this.decoder.apply(this.buffer);
                  } catch (Throwable t) {
                     this.state.fail(t);
                     if (handler != null) {
                        handler.handle(Future.failedFuture(t));
                     }

                     return;
                  }
               } else {
                  result = (T)null;
               }

               this.state.complete(result);
               if (handler != null) {
                  handler.handle(Future.succeededFuture());
               }
            }

         }

         public WriteStream setWriteQueueMaxSize(int maxSize) {
            return this;
         }

         public boolean writeQueueFull() {
            return false;
         }

         public WriteStream drainHandler(Handler handler) {
            return this;
         }
      }));
   }

   static {
      STRING = new BodyCodecImpl(UTF8_DECODER);
      NONE = new BodyCodecImpl(VOID_DECODER);
      BUFFER = new BodyCodecImpl(Function.identity());
      JSON_OBJECT = new BodyCodecImpl(JSON_OBJECT_DECODER);
      JSON_ARRAY = new BodyCodecImpl(JSON_ARRAY_DECODER);
   }
}
