package io.vertx.core.buffer;

import io.netty.buffer.ByteBuf;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.buffer.impl.BufferImpl;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.shareddata.ClusterSerializable;
import io.vertx.core.shareddata.Shareable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Objects;

@VertxGen
public interface Buffer extends ClusterSerializable, Shareable {
   static Buffer buffer() {
      return BufferImpl.buffer();
   }

   static Buffer buffer(int initialSizeHint) {
      return BufferImpl.buffer(initialSizeHint);
   }

   static Buffer buffer(String string) {
      return BufferImpl.buffer(string);
   }

   static Buffer buffer(String string, String enc) {
      return BufferImpl.buffer(string, enc);
   }

   @GenIgnore({"permitted-type"})
   static Buffer buffer(byte[] bytes) {
      return BufferImpl.buffer(bytes);
   }

   /** @deprecated */
   @Deprecated
   @GenIgnore({"permitted-type"})
   static Buffer buffer(ByteBuf byteBuf) {
      Objects.requireNonNull(byteBuf);
      return BufferImpl.buffer(byteBuf);
   }

   String toString();

   String toString(String var1);

   @GenIgnore({"permitted-type"})
   String toString(Charset var1);

   JsonObject toJsonObject();

   JsonArray toJsonArray();

   default Object toJsonValue() {
      return Json.CODEC.fromBuffer(this, Object.class);
   }

   /** @deprecated */
   @Deprecated
   default Object toJson() {
      return this.toJsonValue();
   }

   byte getByte(int var1);

   short getUnsignedByte(int var1);

   int getInt(int var1);

   int getIntLE(int var1);

   long getUnsignedInt(int var1);

   long getUnsignedIntLE(int var1);

   long getLong(int var1);

   long getLongLE(int var1);

   double getDouble(int var1);

   float getFloat(int var1);

   short getShort(int var1);

   short getShortLE(int var1);

   int getUnsignedShort(int var1);

   int getUnsignedShortLE(int var1);

   int getMedium(int var1);

   int getMediumLE(int var1);

   int getUnsignedMedium(int var1);

   int getUnsignedMediumLE(int var1);

   @GenIgnore({"permitted-type"})
   byte[] getBytes();

   @GenIgnore({"permitted-type"})
   byte[] getBytes(int var1, int var2);

   @GenIgnore({"permitted-type"})
   @Fluent
   Buffer getBytes(byte[] var1);

   @GenIgnore({"permitted-type"})
   @Fluent
   Buffer getBytes(byte[] var1, int var2);

   @GenIgnore({"permitted-type"})
   @Fluent
   Buffer getBytes(int var1, int var2, byte[] var3);

   @GenIgnore({"permitted-type"})
   @Fluent
   Buffer getBytes(int var1, int var2, byte[] var3, int var4);

   Buffer getBuffer(int var1, int var2);

   String getString(int var1, int var2, String var3);

   String getString(int var1, int var2);

   @Fluent
   Buffer appendBuffer(Buffer var1);

   @Fluent
   Buffer appendBuffer(Buffer var1, int var2, int var3);

   @GenIgnore({"permitted-type"})
   @Fluent
   Buffer appendBytes(byte[] var1);

   @GenIgnore({"permitted-type"})
   @Fluent
   Buffer appendBytes(byte[] var1, int var2, int var3);

   @Fluent
   Buffer appendByte(byte var1);

   @Fluent
   Buffer appendUnsignedByte(short var1);

   @Fluent
   Buffer appendInt(int var1);

   @Fluent
   Buffer appendIntLE(int var1);

   @Fluent
   Buffer appendUnsignedInt(long var1);

   @Fluent
   Buffer appendUnsignedIntLE(long var1);

   @Fluent
   Buffer appendMedium(int var1);

   @Fluent
   Buffer appendMediumLE(int var1);

   @Fluent
   Buffer appendLong(long var1);

   @Fluent
   Buffer appendLongLE(long var1);

   @Fluent
   Buffer appendShort(short var1);

   @Fluent
   Buffer appendShortLE(short var1);

   @Fluent
   Buffer appendUnsignedShort(int var1);

   @Fluent
   Buffer appendUnsignedShortLE(int var1);

   @Fluent
   Buffer appendFloat(float var1);

   @Fluent
   Buffer appendDouble(double var1);

   @Fluent
   Buffer appendString(String var1, String var2);

   @Fluent
   Buffer appendString(String var1);

   @Fluent
   Buffer setByte(int var1, byte var2);

   @Fluent
   Buffer setUnsignedByte(int var1, short var2);

   @Fluent
   Buffer setInt(int var1, int var2);

   @Fluent
   Buffer setIntLE(int var1, int var2);

   @Fluent
   Buffer setUnsignedInt(int var1, long var2);

   @Fluent
   Buffer setUnsignedIntLE(int var1, long var2);

   @Fluent
   Buffer setMedium(int var1, int var2);

   @Fluent
   Buffer setMediumLE(int var1, int var2);

   @Fluent
   Buffer setLong(int var1, long var2);

   @Fluent
   Buffer setLongLE(int var1, long var2);

   @Fluent
   Buffer setDouble(int var1, double var2);

   @Fluent
   Buffer setFloat(int var1, float var2);

   @Fluent
   Buffer setShort(int var1, short var2);

   @Fluent
   Buffer setShortLE(int var1, short var2);

   @Fluent
   Buffer setUnsignedShort(int var1, int var2);

   @Fluent
   Buffer setUnsignedShortLE(int var1, int var2);

   @Fluent
   Buffer setBuffer(int var1, Buffer var2);

   @Fluent
   Buffer setBuffer(int var1, Buffer var2, int var3, int var4);

   @GenIgnore({"permitted-type"})
   @Fluent
   Buffer setBytes(int var1, ByteBuffer var2);

   @GenIgnore({"permitted-type"})
   @Fluent
   Buffer setBytes(int var1, byte[] var2);

   @GenIgnore({"permitted-type"})
   @Fluent
   Buffer setBytes(int var1, byte[] var2, int var3, int var4);

   @Fluent
   Buffer setString(int var1, String var2);

   @Fluent
   Buffer setString(int var1, String var2, String var3);

   int length();

   Buffer copy();

   Buffer slice();

   Buffer slice(int var1, int var2);

   /** @deprecated */
   @Deprecated
   @GenIgnore({"permitted-type"})
   ByteBuf getByteBuf();
}
