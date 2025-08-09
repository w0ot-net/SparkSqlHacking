package io.netty.handler.codec.spdy;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.internal.ObjectUtil;

public class SpdyFrameDecoder {
   protected final SpdyFrameDecoderDelegate delegate;
   protected final int spdyVersion;
   private final int maxChunkSize;
   private int frameType;
   private State state;
   private byte flags;
   private int length;
   private int streamId;
   private int numSettings;

   public SpdyFrameDecoder(SpdyVersion spdyVersion, SpdyFrameDecoderDelegate delegate) {
      this(spdyVersion, delegate, 8192);
   }

   public SpdyFrameDecoder(SpdyVersion spdyVersion, SpdyFrameDecoderDelegate delegate, int maxChunkSize) {
      this.spdyVersion = ((SpdyVersion)ObjectUtil.checkNotNull(spdyVersion, "spdyVersion")).version();
      this.delegate = (SpdyFrameDecoderDelegate)ObjectUtil.checkNotNull(delegate, "delegate");
      this.maxChunkSize = ObjectUtil.checkPositive(maxChunkSize, "maxChunkSize");
      this.state = SpdyFrameDecoder.State.READ_COMMON_HEADER;
   }

   public void decode(ByteBuf buffer) {
      while(true) {
         switch (this.state) {
            case READ_COMMON_HEADER:
               if (buffer.readableBytes() < 8) {
                  return;
               }

               int frameOffset = buffer.readerIndex();
               int flagsOffset = frameOffset + 4;
               int lengthOffset = frameOffset + 5;
               buffer.skipBytes(8);
               boolean control = (buffer.getByte(frameOffset) & 128) != 0;
               int version;
               if (control) {
                  version = SpdyCodecUtil.getUnsignedShort(buffer, frameOffset) & 32767;
                  this.frameType = SpdyCodecUtil.getUnsignedShort(buffer, frameOffset + 2);
                  this.streamId = 0;
               } else {
                  version = this.spdyVersion;
                  this.frameType = 0;
                  this.streamId = SpdyCodecUtil.getUnsignedInt(buffer, frameOffset);
               }

               this.flags = buffer.getByte(flagsOffset);
               this.length = SpdyCodecUtil.getUnsignedMedium(buffer, lengthOffset);
               if (version != this.spdyVersion) {
                  this.state = SpdyFrameDecoder.State.FRAME_ERROR;
                  this.delegate.readFrameError("Invalid SPDY Version");
               } else if (!isValidFrameHeader(this.streamId, this.frameType, this.flags, this.length)) {
                  this.state = SpdyFrameDecoder.State.FRAME_ERROR;
                  this.delegate.readFrameError("Invalid Frame Error");
               } else {
                  if (this.isValidUnknownFrameHeader(this.streamId, this.frameType, this.flags, this.length)) {
                     this.state = SpdyFrameDecoder.State.READ_UNKNOWN_FRAME;
                     break;
                  }

                  this.state = getNextState(this.frameType, this.length);
               }
               break;
            case READ_DATA_FRAME:
               if (this.length == 0) {
                  this.state = SpdyFrameDecoder.State.READ_COMMON_HEADER;
                  this.delegate.readDataFrame(this.streamId, hasFlag(this.flags, (byte)1), Unpooled.buffer(0));
                  break;
               }

               int dataLength = Math.min(this.maxChunkSize, this.length);
               if (buffer.readableBytes() < dataLength) {
                  return;
               }

               ByteBuf data = buffer.readRetainedSlice(dataLength);
               this.length -= dataLength;
               if (this.length == 0) {
                  this.state = SpdyFrameDecoder.State.READ_COMMON_HEADER;
               }

               boolean last = this.length == 0 && hasFlag(this.flags, (byte)1);
               this.delegate.readDataFrame(this.streamId, last, data);
               break;
            case READ_SYN_STREAM_FRAME:
               if (buffer.readableBytes() < 10) {
                  return;
               }

               int offset = buffer.readerIndex();
               this.streamId = SpdyCodecUtil.getUnsignedInt(buffer, offset);
               int associatedToStreamId = SpdyCodecUtil.getUnsignedInt(buffer, offset + 4);
               byte priority = (byte)(buffer.getByte(offset + 8) >> 5 & 7);
               boolean last = hasFlag(this.flags, (byte)1);
               boolean unidirectional = hasFlag(this.flags, (byte)2);
               buffer.skipBytes(10);
               this.length -= 10;
               if (this.streamId == 0) {
                  this.state = SpdyFrameDecoder.State.FRAME_ERROR;
                  this.delegate.readFrameError("Invalid SYN_STREAM Frame");
                  break;
               }

               this.state = SpdyFrameDecoder.State.READ_HEADER_BLOCK;
               this.delegate.readSynStreamFrame(this.streamId, associatedToStreamId, priority, last, unidirectional);
               break;
            case READ_SYN_REPLY_FRAME:
               if (buffer.readableBytes() < 4) {
                  return;
               }

               this.streamId = SpdyCodecUtil.getUnsignedInt(buffer, buffer.readerIndex());
               boolean last = hasFlag(this.flags, (byte)1);
               buffer.skipBytes(4);
               this.length -= 4;
               if (this.streamId == 0) {
                  this.state = SpdyFrameDecoder.State.FRAME_ERROR;
                  this.delegate.readFrameError("Invalid SYN_REPLY Frame");
                  break;
               }

               this.state = SpdyFrameDecoder.State.READ_HEADER_BLOCK;
               this.delegate.readSynReplyFrame(this.streamId, last);
               break;
            case READ_RST_STREAM_FRAME:
               if (buffer.readableBytes() < 8) {
                  return;
               }

               this.streamId = SpdyCodecUtil.getUnsignedInt(buffer, buffer.readerIndex());
               int statusCode = SpdyCodecUtil.getSignedInt(buffer, buffer.readerIndex() + 4);
               buffer.skipBytes(8);
               if (this.streamId != 0 && statusCode != 0) {
                  this.state = SpdyFrameDecoder.State.READ_COMMON_HEADER;
                  this.delegate.readRstStreamFrame(this.streamId, statusCode);
                  break;
               }

               this.state = SpdyFrameDecoder.State.FRAME_ERROR;
               this.delegate.readFrameError("Invalid RST_STREAM Frame");
               break;
            case READ_SETTINGS_FRAME:
               if (buffer.readableBytes() < 4) {
                  return;
               }

               boolean clear = hasFlag(this.flags, (byte)1);
               this.numSettings = SpdyCodecUtil.getUnsignedInt(buffer, buffer.readerIndex());
               buffer.skipBytes(4);
               this.length -= 4;
               if ((this.length & 7) == 0 && this.length >> 3 == this.numSettings) {
                  this.state = SpdyFrameDecoder.State.READ_SETTING;
                  this.delegate.readSettingsFrame(clear);
                  break;
               }

               this.state = SpdyFrameDecoder.State.FRAME_ERROR;
               this.delegate.readFrameError("Invalid SETTINGS Frame");
               break;
            case READ_SETTING:
               if (this.numSettings == 0) {
                  this.state = SpdyFrameDecoder.State.READ_COMMON_HEADER;
                  this.delegate.readSettingsEnd();
                  break;
               }

               if (buffer.readableBytes() < 8) {
                  return;
               }

               byte settingsFlags = buffer.getByte(buffer.readerIndex());
               int id = SpdyCodecUtil.getUnsignedMedium(buffer, buffer.readerIndex() + 1);
               int value = SpdyCodecUtil.getSignedInt(buffer, buffer.readerIndex() + 4);
               boolean persistValue = hasFlag(settingsFlags, (byte)1);
               boolean persisted = hasFlag(settingsFlags, (byte)2);
               buffer.skipBytes(8);
               --this.numSettings;
               this.delegate.readSetting(id, value, persistValue, persisted);
               break;
            case READ_PING_FRAME:
               if (buffer.readableBytes() < 4) {
                  return;
               }

               int pingId = SpdyCodecUtil.getSignedInt(buffer, buffer.readerIndex());
               buffer.skipBytes(4);
               this.state = SpdyFrameDecoder.State.READ_COMMON_HEADER;
               this.delegate.readPingFrame(pingId);
               break;
            case READ_GOAWAY_FRAME:
               if (buffer.readableBytes() < 8) {
                  return;
               }

               int lastGoodStreamId = SpdyCodecUtil.getUnsignedInt(buffer, buffer.readerIndex());
               int statusCode = SpdyCodecUtil.getSignedInt(buffer, buffer.readerIndex() + 4);
               buffer.skipBytes(8);
               this.state = SpdyFrameDecoder.State.READ_COMMON_HEADER;
               this.delegate.readGoAwayFrame(lastGoodStreamId, statusCode);
               break;
            case READ_HEADERS_FRAME:
               if (buffer.readableBytes() < 4) {
                  return;
               }

               this.streamId = SpdyCodecUtil.getUnsignedInt(buffer, buffer.readerIndex());
               boolean last = hasFlag(this.flags, (byte)1);
               buffer.skipBytes(4);
               this.length -= 4;
               if (this.streamId == 0) {
                  this.state = SpdyFrameDecoder.State.FRAME_ERROR;
                  this.delegate.readFrameError("Invalid HEADERS Frame");
                  break;
               }

               this.state = SpdyFrameDecoder.State.READ_HEADER_BLOCK;
               this.delegate.readHeadersFrame(this.streamId, last);
               break;
            case READ_WINDOW_UPDATE_FRAME:
               if (buffer.readableBytes() < 8) {
                  return;
               }

               this.streamId = SpdyCodecUtil.getUnsignedInt(buffer, buffer.readerIndex());
               int deltaWindowSize = SpdyCodecUtil.getUnsignedInt(buffer, buffer.readerIndex() + 4);
               buffer.skipBytes(8);
               if (deltaWindowSize == 0) {
                  this.state = SpdyFrameDecoder.State.FRAME_ERROR;
                  this.delegate.readFrameError("Invalid WINDOW_UPDATE Frame");
                  break;
               }

               this.state = SpdyFrameDecoder.State.READ_COMMON_HEADER;
               this.delegate.readWindowUpdateFrame(this.streamId, deltaWindowSize);
               break;
            case READ_UNKNOWN_FRAME:
               if (this.decodeUnknownFrame(this.frameType, this.flags, this.length, buffer)) {
                  this.state = SpdyFrameDecoder.State.READ_COMMON_HEADER;
                  break;
               }

               return;
            case READ_HEADER_BLOCK:
               if (this.length == 0) {
                  this.state = SpdyFrameDecoder.State.READ_COMMON_HEADER;
                  this.delegate.readHeaderBlockEnd();
                  break;
               }

               if (!buffer.isReadable()) {
                  return;
               }

               int compressedBytes = Math.min(buffer.readableBytes(), this.length);
               ByteBuf headerBlock = buffer.readRetainedSlice(compressedBytes);
               this.length -= compressedBytes;
               this.delegate.readHeaderBlock(headerBlock);
               break;
            case DISCARD_FRAME:
               int numBytes = Math.min(buffer.readableBytes(), this.length);
               buffer.skipBytes(numBytes);
               this.length -= numBytes;
               if (this.length == 0) {
                  this.state = SpdyFrameDecoder.State.READ_COMMON_HEADER;
                  break;
               }

               return;
            case FRAME_ERROR:
               buffer.skipBytes(buffer.readableBytes());
               return;
            default:
               throw new Error("Shouldn't reach here.");
         }
      }
   }

   private static boolean hasFlag(byte flags, byte flag) {
      return (flags & flag) != 0;
   }

   private static State getNextState(int type, int length) {
      switch (type) {
         case 0:
            return SpdyFrameDecoder.State.READ_DATA_FRAME;
         case 1:
            return SpdyFrameDecoder.State.READ_SYN_STREAM_FRAME;
         case 2:
            return SpdyFrameDecoder.State.READ_SYN_REPLY_FRAME;
         case 3:
            return SpdyFrameDecoder.State.READ_RST_STREAM_FRAME;
         case 4:
            return SpdyFrameDecoder.State.READ_SETTINGS_FRAME;
         case 5:
         default:
            if (length != 0) {
               return SpdyFrameDecoder.State.DISCARD_FRAME;
            }

            return SpdyFrameDecoder.State.READ_COMMON_HEADER;
         case 6:
            return SpdyFrameDecoder.State.READ_PING_FRAME;
         case 7:
            return SpdyFrameDecoder.State.READ_GOAWAY_FRAME;
         case 8:
            return SpdyFrameDecoder.State.READ_HEADERS_FRAME;
         case 9:
            return SpdyFrameDecoder.State.READ_WINDOW_UPDATE_FRAME;
      }
   }

   protected boolean decodeUnknownFrame(int frameType, byte flags, int length, ByteBuf buffer) {
      if (length == 0) {
         if (this.delegate instanceof SpdyFrameDecoderExtendedDelegate) {
            ((SpdyFrameDecoderExtendedDelegate)this.delegate).readUnknownFrame(frameType, flags, Unpooled.EMPTY_BUFFER);
         }

         return true;
      } else if (buffer.readableBytes() < length) {
         return false;
      } else {
         if (this.delegate instanceof SpdyFrameDecoderExtendedDelegate) {
            ByteBuf data = buffer.readRetainedSlice(length);
            ((SpdyFrameDecoderExtendedDelegate)this.delegate).readUnknownFrame(frameType, flags, data);
         } else {
            buffer.skipBytes(length);
         }

         return true;
      }
   }

   protected boolean isValidUnknownFrameHeader(int streamId, int type, byte flags, int length) {
      return false;
   }

   private static boolean isValidFrameHeader(int streamId, int type, byte flags, int length) {
      switch (type) {
         case 0:
            return streamId != 0;
         case 1:
            return length >= 10;
         case 2:
            return length >= 4;
         case 3:
            return flags == 0 && length == 8;
         case 4:
            return length >= 4;
         case 5:
         default:
            return true;
         case 6:
            return length == 4;
         case 7:
            return length == 8;
         case 8:
            return length >= 4;
         case 9:
            return length == 8;
      }
   }

   private static enum State {
      READ_COMMON_HEADER,
      READ_DATA_FRAME,
      READ_SYN_STREAM_FRAME,
      READ_SYN_REPLY_FRAME,
      READ_RST_STREAM_FRAME,
      READ_SETTINGS_FRAME,
      READ_SETTING,
      READ_PING_FRAME,
      READ_GOAWAY_FRAME,
      READ_HEADERS_FRAME,
      READ_WINDOW_UPDATE_FRAME,
      READ_UNKNOWN_FRAME,
      READ_HEADER_BLOCK,
      DISCARD_FRAME,
      FRAME_ERROR;
   }
}
