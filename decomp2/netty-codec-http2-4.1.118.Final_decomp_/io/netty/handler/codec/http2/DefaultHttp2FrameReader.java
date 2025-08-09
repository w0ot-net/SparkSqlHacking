package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.internal.PlatformDependent;

public class DefaultHttp2FrameReader implements Http2FrameReader, Http2FrameSizePolicy, Http2FrameReader.Configuration {
   private final Http2HeadersDecoder headersDecoder;
   private boolean readingHeaders;
   private boolean readError;
   private byte frameType;
   private int streamId;
   private Http2Flags flags;
   private int payloadLength;
   private HeadersContinuation headersContinuation;
   private int maxFrameSize;

   public DefaultHttp2FrameReader() {
      this(true);
   }

   public DefaultHttp2FrameReader(boolean validateHeaders) {
      this(new DefaultHttp2HeadersDecoder(validateHeaders));
   }

   public DefaultHttp2FrameReader(Http2HeadersDecoder headersDecoder) {
      this.readingHeaders = true;
      this.headersDecoder = headersDecoder;
      this.maxFrameSize = 16384;
   }

   public Http2HeadersDecoder.Configuration headersConfiguration() {
      return this.headersDecoder.configuration();
   }

   public Http2FrameReader.Configuration configuration() {
      return this;
   }

   public Http2FrameSizePolicy frameSizePolicy() {
      return this;
   }

   public void maxFrameSize(int max) throws Http2Exception {
      if (!Http2CodecUtil.isMaxFrameSizeValid(max)) {
         throw Http2Exception.connectionError(Http2Error.FRAME_SIZE_ERROR, "Invalid MAX_FRAME_SIZE specified in sent settings: %d", max);
      } else {
         this.maxFrameSize = max;
      }
   }

   public int maxFrameSize() {
      return this.maxFrameSize;
   }

   public void close() {
      this.closeHeadersContinuation();
   }

   private void closeHeadersContinuation() {
      if (this.headersContinuation != null) {
         this.headersContinuation.close();
         this.headersContinuation = null;
      }

   }

   public void readFrame(ChannelHandlerContext ctx, ByteBuf input, Http2FrameListener listener) throws Http2Exception {
      if (this.readError) {
         input.skipBytes(input.readableBytes());
      } else {
         try {
            do {
               if (this.readingHeaders && !this.preProcessFrame(input)) {
                  return;
               }

               if (input.readableBytes() < this.payloadLength) {
                  return;
               }

               ByteBuf framePayload = input.readSlice(this.payloadLength);
               this.readingHeaders = true;
               this.verifyFrameState();
               this.processPayloadState(ctx, framePayload, listener);
            } while(input.isReadable());
         } catch (Http2Exception e) {
            this.readError = !Http2Exception.isStreamError(e);
            throw e;
         } catch (RuntimeException e) {
            this.readError = true;
            throw e;
         } catch (Throwable cause) {
            this.readError = true;
            PlatformDependent.throwException(cause);
         }

      }
   }

   private boolean preProcessFrame(ByteBuf in) throws Http2Exception {
      if (in.readableBytes() < 9) {
         return false;
      } else {
         this.payloadLength = in.readUnsignedMedium();
         if (this.payloadLength > this.maxFrameSize) {
            throw Http2Exception.connectionError(Http2Error.FRAME_SIZE_ERROR, "Frame length: %d exceeds maximum: %d", this.payloadLength, this.maxFrameSize);
         } else {
            this.frameType = in.readByte();
            this.flags = new Http2Flags(in.readUnsignedByte());
            this.streamId = Http2CodecUtil.readUnsignedInt(in);
            this.readingHeaders = false;
            return true;
         }
      }
   }

   private void verifyFrameState() throws Http2Exception {
      switch (this.frameType) {
         case 0:
            this.verifyDataFrame();
            break;
         case 1:
            this.verifyHeadersFrame();
            break;
         case 2:
            this.verifyPriorityFrame();
            break;
         case 3:
            this.verifyRstStreamFrame();
            break;
         case 4:
            this.verifySettingsFrame();
            break;
         case 5:
            this.verifyPushPromiseFrame();
            break;
         case 6:
            this.verifyPingFrame();
            break;
         case 7:
            this.verifyGoAwayFrame();
            break;
         case 8:
            this.verifyWindowUpdateFrame();
            break;
         case 9:
            this.verifyContinuationFrame();
            break;
         default:
            this.verifyUnknownFrame();
      }

   }

   private void processPayloadState(ChannelHandlerContext ctx, ByteBuf in, Http2FrameListener listener) throws Http2Exception {
      assert in.readableBytes() == this.payloadLength;

      switch (this.frameType) {
         case 0:
            this.readDataFrame(ctx, in, listener);
            break;
         case 1:
            this.readHeadersFrame(ctx, in, listener);
            break;
         case 2:
            this.readPriorityFrame(ctx, in, listener);
            break;
         case 3:
            this.readRstStreamFrame(ctx, in, listener);
            break;
         case 4:
            this.readSettingsFrame(ctx, in, listener);
            break;
         case 5:
            this.readPushPromiseFrame(ctx, in, listener);
            break;
         case 6:
            this.readPingFrame(ctx, in.readLong(), listener);
            break;
         case 7:
            this.readGoAwayFrame(ctx, in, listener);
            break;
         case 8:
            this.readWindowUpdateFrame(ctx, in, listener);
            break;
         case 9:
            this.readContinuationFrame(in, listener);
            break;
         default:
            this.readUnknownFrame(ctx, in, listener);
      }

   }

   private void verifyDataFrame() throws Http2Exception {
      this.verifyAssociatedWithAStream();
      this.verifyNotProcessingHeaders();
      if (this.payloadLength < this.flags.getPaddingPresenceFieldLength()) {
         throw Http2Exception.streamError(this.streamId, Http2Error.FRAME_SIZE_ERROR, "Frame length %d too small.", this.payloadLength);
      }
   }

   private void verifyHeadersFrame() throws Http2Exception {
      this.verifyAssociatedWithAStream();
      this.verifyNotProcessingHeaders();
      int requiredLength = this.flags.getPaddingPresenceFieldLength() + this.flags.getNumPriorityBytes();
      if (this.payloadLength < requiredLength) {
         throw Http2Exception.connectionError(Http2Error.FRAME_SIZE_ERROR, "Frame length %d too small for HEADERS frame with stream %d.", this.payloadLength, this.streamId);
      }
   }

   private void verifyPriorityFrame() throws Http2Exception {
      this.verifyAssociatedWithAStream();
      this.verifyNotProcessingHeaders();
      if (this.payloadLength != 5) {
         throw Http2Exception.streamError(this.streamId, Http2Error.FRAME_SIZE_ERROR, "Invalid frame length %d.", this.payloadLength);
      }
   }

   private void verifyRstStreamFrame() throws Http2Exception {
      this.verifyAssociatedWithAStream();
      this.verifyNotProcessingHeaders();
      if (this.payloadLength != 4) {
         throw Http2Exception.connectionError(Http2Error.FRAME_SIZE_ERROR, "Invalid frame length %d.", this.payloadLength);
      }
   }

   private void verifySettingsFrame() throws Http2Exception {
      this.verifyNotProcessingHeaders();
      if (this.streamId != 0) {
         throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "A stream ID must be zero.");
      } else if (this.flags.ack() && this.payloadLength > 0) {
         throw Http2Exception.connectionError(Http2Error.FRAME_SIZE_ERROR, "Ack settings frame must have an empty payload.");
      } else if (this.payloadLength % 6 > 0) {
         throw Http2Exception.connectionError(Http2Error.FRAME_SIZE_ERROR, "Frame length %d invalid.", this.payloadLength);
      }
   }

   private void verifyPushPromiseFrame() throws Http2Exception {
      this.verifyNotProcessingHeaders();
      int minLength = this.flags.getPaddingPresenceFieldLength() + 4;
      if (this.payloadLength < minLength) {
         throw Http2Exception.connectionError(Http2Error.FRAME_SIZE_ERROR, "Frame length %d too small for PUSH_PROMISE frame with stream id %d.", this.payloadLength, this.streamId);
      }
   }

   private void verifyPingFrame() throws Http2Exception {
      this.verifyNotProcessingHeaders();
      if (this.streamId != 0) {
         throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "A stream ID must be zero.");
      } else if (this.payloadLength != 8) {
         throw Http2Exception.connectionError(Http2Error.FRAME_SIZE_ERROR, "Frame length %d incorrect size for ping.", this.payloadLength);
      }
   }

   private void verifyGoAwayFrame() throws Http2Exception {
      this.verifyNotProcessingHeaders();
      if (this.streamId != 0) {
         throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "A stream ID must be zero.");
      } else if (this.payloadLength < 8) {
         throw Http2Exception.connectionError(Http2Error.FRAME_SIZE_ERROR, "Frame length %d too small.", this.payloadLength);
      }
   }

   private void verifyWindowUpdateFrame() throws Http2Exception {
      this.verifyNotProcessingHeaders();
      verifyStreamOrConnectionId(this.streamId, "Stream ID");
      if (this.payloadLength != 4) {
         throw Http2Exception.connectionError(Http2Error.FRAME_SIZE_ERROR, "Invalid frame length %d.", this.payloadLength);
      }
   }

   private void verifyContinuationFrame() throws Http2Exception {
      this.verifyAssociatedWithAStream();
      if (this.headersContinuation == null) {
         throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Received %s frame but not currently processing headers.", this.frameType);
      } else if (this.streamId != this.headersContinuation.getStreamId()) {
         throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Continuation stream ID does not match pending headers. Expected %d, but received %d.", this.headersContinuation.getStreamId(), this.streamId);
      }
   }

   private void verifyUnknownFrame() throws Http2Exception {
      this.verifyNotProcessingHeaders();
   }

   private void readDataFrame(ChannelHandlerContext ctx, ByteBuf payload, Http2FrameListener listener) throws Http2Exception {
      int padding = this.readPadding(payload);
      this.verifyPadding(padding);
      int dataLength = lengthWithoutTrailingPadding(payload.readableBytes(), padding);
      payload.writerIndex(payload.readerIndex() + dataLength);
      listener.onDataRead(ctx, this.streamId, payload, padding, this.flags.endOfStream());
   }

   private void readHeadersFrame(final ChannelHandlerContext ctx, ByteBuf payload, Http2FrameListener listener) throws Http2Exception {
      final int headersStreamId = this.streamId;
      final Http2Flags headersFlags = this.flags;
      final int padding = this.readPadding(payload);
      this.verifyPadding(padding);
      if (this.flags.priorityPresent()) {
         long word1 = payload.readUnsignedInt();
         final boolean exclusive = (word1 & 2147483648L) != 0L;
         final int streamDependency = (int)(word1 & 2147483647L);
         if (streamDependency == this.streamId) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "HEADERS frame for stream %d cannot depend on itself.", this.streamId);
         } else {
            final short weight = (short)(payload.readUnsignedByte() + 1);
            int lenToRead = lengthWithoutTrailingPadding(payload.readableBytes(), padding);
            this.headersContinuation = new HeadersContinuation() {
               public int getStreamId() {
                  return headersStreamId;
               }

               public void processFragment(boolean endOfHeaders, ByteBuf fragment, int len, Http2FrameListener listener) throws Http2Exception {
                  HeadersBlockBuilder hdrBlockBuilder = this.headersBlockBuilder();
                  hdrBlockBuilder.addFragment(fragment, len, ctx.alloc(), endOfHeaders);
                  if (endOfHeaders) {
                     listener.onHeadersRead(ctx, headersStreamId, hdrBlockBuilder.headers(), streamDependency, weight, exclusive, padding, headersFlags.endOfStream());
                  }

               }
            };
            this.headersContinuation.processFragment(this.flags.endOfHeaders(), payload, lenToRead, listener);
            this.resetHeadersContinuationIfEnd(this.flags.endOfHeaders());
         }
      } else {
         this.headersContinuation = new HeadersContinuation() {
            public int getStreamId() {
               return headersStreamId;
            }

            public void processFragment(boolean endOfHeaders, ByteBuf fragment, int len, Http2FrameListener listener) throws Http2Exception {
               HeadersBlockBuilder hdrBlockBuilder = this.headersBlockBuilder();
               hdrBlockBuilder.addFragment(fragment, len, ctx.alloc(), endOfHeaders);
               if (endOfHeaders) {
                  listener.onHeadersRead(ctx, headersStreamId, hdrBlockBuilder.headers(), padding, headersFlags.endOfStream());
               }

            }
         };
         int len = lengthWithoutTrailingPadding(payload.readableBytes(), padding);
         this.headersContinuation.processFragment(this.flags.endOfHeaders(), payload, len, listener);
         this.resetHeadersContinuationIfEnd(this.flags.endOfHeaders());
      }
   }

   private void resetHeadersContinuationIfEnd(boolean endOfHeaders) {
      if (endOfHeaders) {
         this.closeHeadersContinuation();
      }

   }

   private void readPriorityFrame(ChannelHandlerContext ctx, ByteBuf payload, Http2FrameListener listener) throws Http2Exception {
      long word1 = payload.readUnsignedInt();
      boolean exclusive = (word1 & 2147483648L) != 0L;
      int streamDependency = (int)(word1 & 2147483647L);
      if (streamDependency == this.streamId) {
         throw Http2Exception.streamError(this.streamId, Http2Error.PROTOCOL_ERROR, "A stream cannot depend on itself.");
      } else {
         short weight = (short)(payload.readUnsignedByte() + 1);
         listener.onPriorityRead(ctx, this.streamId, streamDependency, weight, exclusive);
      }
   }

   private void readRstStreamFrame(ChannelHandlerContext ctx, ByteBuf payload, Http2FrameListener listener) throws Http2Exception {
      long errorCode = payload.readUnsignedInt();
      listener.onRstStreamRead(ctx, this.streamId, errorCode);
   }

   private void readSettingsFrame(ChannelHandlerContext ctx, ByteBuf payload, Http2FrameListener listener) throws Http2Exception {
      if (this.flags.ack()) {
         listener.onSettingsAckRead(ctx);
      } else {
         int numSettings = this.payloadLength / 6;
         Http2Settings settings = new Http2Settings();

         for(int index = 0; index < numSettings; ++index) {
            char id = (char)payload.readUnsignedShort();
            long value = payload.readUnsignedInt();

            try {
               settings.put(id, value);
            } catch (IllegalArgumentException e) {
               if (id == 4) {
                  throw Http2Exception.connectionError(Http2Error.FLOW_CONTROL_ERROR, e, "Failed setting initial window size: %s", e.getMessage());
               }

               throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, e, "Protocol error: %s", e.getMessage());
            }
         }

         listener.onSettingsRead(ctx, settings);
      }

   }

   private void readPushPromiseFrame(final ChannelHandlerContext ctx, ByteBuf payload, Http2FrameListener listener) throws Http2Exception {
      final int pushPromiseStreamId = this.streamId;
      final int padding = this.readPadding(payload);
      this.verifyPadding(padding);
      final int promisedStreamId = Http2CodecUtil.readUnsignedInt(payload);
      this.headersContinuation = new HeadersContinuation() {
         public int getStreamId() {
            return pushPromiseStreamId;
         }

         public void processFragment(boolean endOfHeaders, ByteBuf fragment, int len, Http2FrameListener listener) throws Http2Exception {
            this.headersBlockBuilder().addFragment(fragment, len, ctx.alloc(), endOfHeaders);
            if (endOfHeaders) {
               listener.onPushPromiseRead(ctx, pushPromiseStreamId, promisedStreamId, this.headersBlockBuilder().headers(), padding);
            }

         }
      };
      int len = lengthWithoutTrailingPadding(payload.readableBytes(), padding);
      this.headersContinuation.processFragment(this.flags.endOfHeaders(), payload, len, listener);
      this.resetHeadersContinuationIfEnd(this.flags.endOfHeaders());
   }

   private void readPingFrame(ChannelHandlerContext ctx, long data, Http2FrameListener listener) throws Http2Exception {
      if (this.flags.ack()) {
         listener.onPingAckRead(ctx, data);
      } else {
         listener.onPingRead(ctx, data);
      }

   }

   private void readGoAwayFrame(ChannelHandlerContext ctx, ByteBuf payload, Http2FrameListener listener) throws Http2Exception {
      int lastStreamId = Http2CodecUtil.readUnsignedInt(payload);
      long errorCode = payload.readUnsignedInt();
      listener.onGoAwayRead(ctx, lastStreamId, errorCode, payload);
   }

   private void readWindowUpdateFrame(ChannelHandlerContext ctx, ByteBuf payload, Http2FrameListener listener) throws Http2Exception {
      int windowSizeIncrement = Http2CodecUtil.readUnsignedInt(payload);
      if (windowSizeIncrement == 0) {
         if (this.streamId == 0) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Received WINDOW_UPDATE with delta 0 for connection stream");
         } else {
            throw Http2Exception.streamError(this.streamId, Http2Error.PROTOCOL_ERROR, "Received WINDOW_UPDATE with delta 0 for stream: %d", this.streamId);
         }
      } else {
         listener.onWindowUpdateRead(ctx, this.streamId, windowSizeIncrement);
      }
   }

   private void readContinuationFrame(ByteBuf payload, Http2FrameListener listener) throws Http2Exception {
      this.headersContinuation.processFragment(this.flags.endOfHeaders(), payload, this.payloadLength, listener);
      this.resetHeadersContinuationIfEnd(this.flags.endOfHeaders());
   }

   private void readUnknownFrame(ChannelHandlerContext ctx, ByteBuf payload, Http2FrameListener listener) throws Http2Exception {
      listener.onUnknownFrame(ctx, this.frameType, this.streamId, this.flags, payload);
   }

   private int readPadding(ByteBuf payload) {
      return !this.flags.paddingPresent() ? 0 : payload.readUnsignedByte() + 1;
   }

   private void verifyPadding(int padding) throws Http2Exception {
      int len = lengthWithoutTrailingPadding(this.payloadLength, padding);
      if (len < 0) {
         throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Frame payload too small for padding.");
      }
   }

   private static int lengthWithoutTrailingPadding(int readableBytes, int padding) {
      return padding == 0 ? readableBytes : readableBytes - (padding - 1);
   }

   private void verifyNotProcessingHeaders() throws Http2Exception {
      if (this.headersContinuation != null) {
         throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Received frame of type %s while processing headers on stream %d.", this.frameType, this.headersContinuation.getStreamId());
      }
   }

   private void verifyAssociatedWithAStream() throws Http2Exception {
      if (this.streamId == 0) {
         throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Frame of type %s must be associated with a stream.", this.frameType);
      }
   }

   private static void verifyStreamOrConnectionId(int streamId, String argumentName) throws Http2Exception {
      if (streamId < 0) {
         throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "%s must be >= 0", argumentName);
      }
   }

   private abstract class HeadersContinuation {
      private final HeadersBlockBuilder builder;

      private HeadersContinuation() {
         this.builder = DefaultHttp2FrameReader.this.new HeadersBlockBuilder();
      }

      abstract int getStreamId();

      abstract void processFragment(boolean var1, ByteBuf var2, int var3, Http2FrameListener var4) throws Http2Exception;

      final HeadersBlockBuilder headersBlockBuilder() {
         return this.builder;
      }

      final void close() {
         this.builder.close();
      }
   }

   protected class HeadersBlockBuilder {
      private ByteBuf headerBlock;

      private void headerSizeExceeded() throws Http2Exception {
         this.close();
         Http2CodecUtil.headerListSizeExceeded(DefaultHttp2FrameReader.this.headersDecoder.configuration().maxHeaderListSizeGoAway());
      }

      final void addFragment(ByteBuf fragment, int len, ByteBufAllocator alloc, boolean endOfHeaders) throws Http2Exception {
         if (this.headerBlock == null) {
            if ((long)len > DefaultHttp2FrameReader.this.headersDecoder.configuration().maxHeaderListSizeGoAway()) {
               this.headerSizeExceeded();
            }

            if (endOfHeaders) {
               this.headerBlock = fragment.readRetainedSlice(len);
            } else {
               this.headerBlock = alloc.buffer(len).writeBytes(fragment, len);
            }

         } else {
            if (DefaultHttp2FrameReader.this.headersDecoder.configuration().maxHeaderListSizeGoAway() - (long)len < (long)this.headerBlock.readableBytes()) {
               this.headerSizeExceeded();
            }

            if (this.headerBlock.isWritable(len)) {
               this.headerBlock.writeBytes(fragment, len);
            } else {
               ByteBuf buf = alloc.buffer(this.headerBlock.readableBytes() + len);
               buf.writeBytes(this.headerBlock).writeBytes(fragment, len);
               this.headerBlock.release();
               this.headerBlock = buf;
            }

         }
      }

      Http2Headers headers() throws Http2Exception {
         Http2Headers var1;
         try {
            var1 = DefaultHttp2FrameReader.this.headersDecoder.decodeHeaders(DefaultHttp2FrameReader.this.streamId, this.headerBlock);
         } finally {
            this.close();
         }

         return var1;
      }

      void close() {
         if (this.headerBlock != null) {
            this.headerBlock.release();
            this.headerBlock = null;
         }

         DefaultHttp2FrameReader.this.headersContinuation = null;
      }
   }
}
