package io.vertx.core.http.impl.ws;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCounted;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.WebSocketFrame;
import io.vertx.core.http.WebSocketFrameType;
import java.nio.charset.StandardCharsets;

public class WebSocketFrameImpl implements WebSocketFrameInternal, ReferenceCounted {
   private final WebSocketFrameType type;
   private final boolean isFinalFrame;
   private ByteBuf binaryData;
   private boolean closeParsed;
   private short closeStatusCode;
   private String closeReason;

   public static WebSocketFrame binaryFrame(Buffer data, boolean isFinal) {
      return new WebSocketFrameImpl(WebSocketFrameType.BINARY, data.getByteBuf(), isFinal);
   }

   public static WebSocketFrame textFrame(String str, boolean isFinal) {
      return new WebSocketFrameImpl(str, isFinal);
   }

   public static WebSocketFrame continuationFrame(Buffer data, boolean isFinal) {
      return new WebSocketFrameImpl(WebSocketFrameType.CONTINUATION, data.getByteBuf(), isFinal);
   }

   public static WebSocketFrame pingFrame(Buffer data) {
      return new WebSocketFrameImpl(WebSocketFrameType.PING, data.getByteBuf(), true);
   }

   public static WebSocketFrame pongFrame(Buffer data) {
      return new WebSocketFrameImpl(WebSocketFrameType.PONG, data.getByteBuf(), true);
   }

   public WebSocketFrameImpl() {
      this((WebSocketFrameType)null, (ByteBuf)Unpooled.EMPTY_BUFFER, true);
   }

   public WebSocketFrameImpl(WebSocketFrameType frameType) {
      this(frameType, Unpooled.EMPTY_BUFFER, true);
   }

   public WebSocketFrameImpl(String textData) {
      this(textData, true);
   }

   public WebSocketFrameImpl(WebSocketFrameType type, byte[] utf8TextData, boolean isFinalFrame) {
      this.closeParsed = false;
      this.type = type;
      this.isFinalFrame = isFinalFrame;
      this.binaryData = Unpooled.wrappedBuffer(utf8TextData);
   }

   public WebSocketFrameImpl(String textData, boolean isFinalFrame) {
      this.closeParsed = false;
      this.type = WebSocketFrameType.TEXT;
      this.isFinalFrame = isFinalFrame;
      this.binaryData = Unpooled.copiedBuffer(textData, CharsetUtil.UTF_8);
   }

   public WebSocketFrameImpl(WebSocketFrameType type, ByteBuf binaryData) {
      this(type, binaryData, true);
   }

   public WebSocketFrameImpl(WebSocketFrameType type, ByteBuf binaryData, boolean isFinalFrame) {
      this.closeParsed = false;
      this.type = type;
      this.isFinalFrame = isFinalFrame;
      this.binaryData = Unpooled.unreleasableBuffer(binaryData);
   }

   public boolean isText() {
      return this.type == WebSocketFrameType.TEXT;
   }

   public boolean isBinary() {
      return this.type == WebSocketFrameType.BINARY;
   }

   public boolean isContinuation() {
      return this.type == WebSocketFrameType.CONTINUATION;
   }

   public boolean isClose() {
      return this.type == WebSocketFrameType.CLOSE;
   }

   public boolean isPing() {
      return this.type == WebSocketFrameType.PING;
   }

   public ByteBuf getBinaryData() {
      return this.binaryData;
   }

   public String textData() {
      return this.getBinaryData().toString(CharsetUtil.UTF_8);
   }

   public Buffer binaryData() {
      return Buffer.buffer(this.binaryData);
   }

   public void setBinaryData(ByteBuf binaryData) {
      if (this.binaryData != null) {
         this.binaryData.release();
      }

      this.binaryData = binaryData;
   }

   public void setTextData(String textData) {
      if (this.binaryData != null) {
         this.binaryData.release();
      }

      this.binaryData = Unpooled.copiedBuffer(textData, CharsetUtil.UTF_8);
   }

   public int length() {
      return this.binaryData.readableBytes();
   }

   public String toString() {
      return this.getClass().getSimpleName() + "(type: " + this.type + ", data: " + this.getBinaryData() + ')';
   }

   public int refCnt() {
      return this.binaryData.refCnt();
   }

   public ReferenceCounted retain() {
      return this.binaryData.retain();
   }

   public ReferenceCounted retain(int increment) {
      return this.binaryData.retain(increment);
   }

   public boolean release() {
      return this.binaryData.release();
   }

   public boolean release(int decrement) {
      return this.binaryData.release(decrement);
   }

   public ReferenceCounted touch() {
      this.binaryData.touch();
      return this;
   }

   public ReferenceCounted touch(Object hint) {
      this.binaryData.touch(hint);
      return this;
   }

   public boolean isFinal() {
      return this.isFinalFrame;
   }

   private void parseCloseFrame() {
      int length = this.length();
      if (length < 2) {
         this.closeStatusCode = 1000;
         this.closeReason = null;
      } else {
         int index = this.binaryData.readerIndex();
         this.closeStatusCode = this.binaryData.getShort(index);
         if (length == 2) {
            this.closeReason = null;
         } else {
            this.closeReason = this.binaryData.toString(index + 2, length - 2, StandardCharsets.UTF_8);
         }
      }

   }

   private void checkClose() {
      if (!this.isClose()) {
         throw new IllegalStateException("This should be a close frame");
      }
   }

   public short closeStatusCode() {
      this.checkClose();
      if (!this.closeParsed) {
         this.parseCloseFrame();
      }

      return this.closeStatusCode;
   }

   public String closeReason() {
      this.checkClose();
      if (!this.closeParsed) {
         this.parseCloseFrame();
      }

      return this.closeReason;
   }

   public WebSocketFrameType type() {
      return this.type;
   }
}
