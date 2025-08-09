package io.vertx.core.eventbus.impl.codecs;

import io.netty.util.CharsetUtil;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.eventbus.ReplyFailure;

public class ReplyExceptionMessageCodec implements MessageCodec {
   public void encodeToWire(Buffer buffer, ReplyException body) {
      buffer.appendByte((byte)body.failureType().toInt());
      buffer.appendInt(body.failureCode());
      if (body.getMessage() == null) {
         buffer.appendByte((byte)0);
      } else {
         buffer.appendByte((byte)1);
         byte[] encoded = body.getMessage().getBytes(CharsetUtil.UTF_8);
         buffer.appendInt(encoded.length);
         buffer.appendBytes(encoded);
      }

   }

   public ReplyException decodeFromWire(int pos, Buffer buffer) {
      int i = buffer.getByte(pos);
      ReplyFailure rf = ReplyFailure.fromInt(i);
      ++pos;
      int failureCode = buffer.getInt(pos);
      pos += 4;
      boolean isNull = buffer.getByte(pos) == 0;
      String message;
      if (!isNull) {
         ++pos;
         int strLength = buffer.getInt(pos);
         pos += 4;
         byte[] bytes = buffer.getBytes(pos, pos + strLength);
         message = new String(bytes, CharsetUtil.UTF_8);
      } else {
         message = null;
      }

      return new ReplyException(rf, failureCode, message);
   }

   public ReplyException transform(ReplyException exception) {
      return exception;
   }

   public String name() {
      return "replyexception";
   }

   public byte systemCodecID() {
      return 15;
   }
}
