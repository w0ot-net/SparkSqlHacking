package io.vertx.core.spi.json;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.EncodeException;

public interface JsonCodec {
   Object fromString(String var1, Class var2) throws DecodeException;

   Object fromBuffer(Buffer var1, Class var2) throws DecodeException;

   Object fromValue(Object var1, Class var2);

   default String toString(Object object) throws EncodeException {
      return this.toString(object, false);
   }

   String toString(Object var1, boolean var2) throws EncodeException;

   Buffer toBuffer(Object var1, boolean var2) throws EncodeException;

   default Buffer toBuffer(Object object) throws EncodeException {
      return this.toBuffer(object, false);
   }
}
