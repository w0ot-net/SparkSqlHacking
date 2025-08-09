package io.netty.handler.codec.spdy;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.UnsupportedMessageTypeException;
import java.net.SocketAddress;
import java.util.List;

public class SpdyFrameCodec extends ByteToMessageDecoder implements SpdyFrameDecoderDelegate, ChannelOutboundHandler {
   protected static final SpdyProtocolException INVALID_FRAME = new SpdyProtocolException("Received invalid frame");
   protected final SpdyFrameDecoder spdyFrameDecoder;
   protected final SpdyFrameEncoder spdyFrameEncoder;
   private final SpdyHeaderBlockDecoder spdyHeaderBlockDecoder;
   private final SpdyHeaderBlockEncoder spdyHeaderBlockEncoder;
   private SpdyHeadersFrame spdyHeadersFrame;
   private SpdySettingsFrame spdySettingsFrame;
   private ChannelHandlerContext ctx;
   private boolean read;
   private final boolean validateHeaders;
   private final boolean supportsUnknownFrames;

   public SpdyFrameCodec(SpdyVersion version) {
      this(version, true);
   }

   public SpdyFrameCodec(SpdyVersion version, boolean validateHeaders) {
      this(version, 8192, 16384, 6, 15, 8, validateHeaders);
   }

   public SpdyFrameCodec(SpdyVersion version, int maxChunkSize, int maxHeaderSize, int compressionLevel, int windowBits, int memLevel) {
      this(version, maxChunkSize, maxHeaderSize, compressionLevel, windowBits, memLevel, true);
   }

   public SpdyFrameCodec(SpdyVersion version, int maxChunkSize, int maxHeaderSize, int compressionLevel, int windowBits, int memLevel, boolean validateHeaders) {
      this(version, maxChunkSize, SpdyHeaderBlockDecoder.newInstance(version, maxHeaderSize), SpdyHeaderBlockEncoder.newInstance(version, compressionLevel, windowBits, memLevel), validateHeaders, false);
   }

   public SpdyFrameCodec(SpdyVersion version, int maxChunkSize, int maxHeaderSize, int compressionLevel, int windowBits, int memLevel, boolean validateHeaders, boolean supportsUnknownFrames) {
      this(version, maxChunkSize, SpdyHeaderBlockDecoder.newInstance(version, maxHeaderSize), SpdyHeaderBlockEncoder.newInstance(version, compressionLevel, windowBits, memLevel), validateHeaders, supportsUnknownFrames);
   }

   protected SpdyFrameCodec(SpdyVersion version, int maxChunkSize, SpdyHeaderBlockDecoder spdyHeaderBlockDecoder, SpdyHeaderBlockEncoder spdyHeaderBlockEncoder, boolean validateHeaders) {
      this(version, maxChunkSize, spdyHeaderBlockDecoder, spdyHeaderBlockEncoder, validateHeaders, false);
   }

   protected SpdyFrameCodec(SpdyVersion version, int maxChunkSize, SpdyHeaderBlockDecoder spdyHeaderBlockDecoder, SpdyHeaderBlockEncoder spdyHeaderBlockEncoder, boolean validateHeaders, boolean supportsUnknownFrames) {
      this.supportsUnknownFrames = supportsUnknownFrames;
      this.spdyFrameDecoder = this.createDecoder(version, (SpdyFrameDecoderDelegate)(supportsUnknownFrames ? new SpdyFrameDecoderExtendedDelegate() {
         public void readUnknownFrame(int frameType, byte flags, ByteBuf payload) {
            SpdyFrameCodec.this.readUnknownFrame(frameType, flags, payload);
         }

         public void readDataFrame(int streamId, boolean last, ByteBuf data) {
            SpdyFrameCodec.this.readDataFrame(streamId, last, data);
         }

         public void readSynStreamFrame(int streamId, int associatedToStreamId, byte priority, boolean last, boolean unidirectional) {
            SpdyFrameCodec.this.readSynStreamFrame(streamId, associatedToStreamId, priority, last, unidirectional);
         }

         public void readSynReplyFrame(int streamId, boolean last) {
            SpdyFrameCodec.this.readSynReplyFrame(streamId, last);
         }

         public void readRstStreamFrame(int streamId, int statusCode) {
            SpdyFrameCodec.this.readRstStreamFrame(streamId, statusCode);
         }

         public void readSettingsFrame(boolean clearPersisted) {
            SpdyFrameCodec.this.readSettingsFrame(clearPersisted);
         }

         public void readSetting(int id, int value, boolean persistValue, boolean persisted) {
            SpdyFrameCodec.this.readSetting(id, value, persistValue, persisted);
         }

         public void readSettingsEnd() {
            SpdyFrameCodec.this.readSettingsEnd();
         }

         public void readPingFrame(int id) {
            SpdyFrameCodec.this.readPingFrame(id);
         }

         public void readGoAwayFrame(int lastGoodStreamId, int statusCode) {
            SpdyFrameCodec.this.readGoAwayFrame(lastGoodStreamId, statusCode);
         }

         public void readHeadersFrame(int streamId, boolean last) {
            SpdyFrameCodec.this.readHeadersFrame(streamId, last);
         }

         public void readWindowUpdateFrame(int streamId, int deltaWindowSize) {
            SpdyFrameCodec.this.readWindowUpdateFrame(streamId, deltaWindowSize);
         }

         public void readHeaderBlock(ByteBuf headerBlock) {
            SpdyFrameCodec.this.readHeaderBlock(headerBlock);
         }

         public void readHeaderBlockEnd() {
            SpdyFrameCodec.this.readHeaderBlockEnd();
         }

         public void readFrameError(String message) {
            SpdyFrameCodec.this.readFrameError(message);
         }
      } : this), maxChunkSize);
      this.spdyFrameEncoder = this.createEncoder(version);
      this.spdyHeaderBlockDecoder = spdyHeaderBlockDecoder;
      this.spdyHeaderBlockEncoder = spdyHeaderBlockEncoder;
      this.validateHeaders = validateHeaders;
   }

   protected SpdyFrameDecoder createDecoder(SpdyVersion version, SpdyFrameDecoderDelegate delegate, int maxChunkSize) {
      return new SpdyFrameDecoder(version, delegate, maxChunkSize) {
         protected boolean isValidUnknownFrameHeader(int streamId, int type, byte flags, int length) {
            return SpdyFrameCodec.this.supportsUnknownFrames ? SpdyFrameCodec.this.isValidUnknownFrameHeader(streamId, type, flags, length) : super.isValidUnknownFrameHeader(streamId, type, flags, length);
         }
      };
   }

   protected SpdyFrameEncoder createEncoder(SpdyVersion version) {
      return new SpdyFrameEncoder(version);
   }

   public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
      super.handlerAdded(ctx);
      this.ctx = ctx;
      ctx.channel().closeFuture().addListener(new ChannelFutureListener() {
         public void operationComplete(ChannelFuture future) throws Exception {
            SpdyFrameCodec.this.spdyHeaderBlockDecoder.end();
            SpdyFrameCodec.this.spdyHeaderBlockEncoder.end();
         }
      });
   }

   protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {
      this.spdyFrameDecoder.decode(in);
   }

   public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
      if (!this.read && !ctx.channel().config().isAutoRead()) {
         ctx.read();
      }

      this.read = false;
      super.channelReadComplete(ctx);
   }

   public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
      ctx.bind(localAddress, promise);
   }

   public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
      ctx.connect(remoteAddress, localAddress, promise);
   }

   public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
      ctx.disconnect(promise);
   }

   public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
      ctx.close(promise);
   }

   public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
      ctx.deregister(promise);
   }

   public void read(ChannelHandlerContext ctx) throws Exception {
      ctx.read();
   }

   public void flush(ChannelHandlerContext ctx) throws Exception {
      ctx.flush();
   }

   public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
      if (msg instanceof SpdyDataFrame) {
         SpdyDataFrame spdyDataFrame = (SpdyDataFrame)msg;

         try {
            ByteBuf frame = this.spdyFrameEncoder.encodeDataFrame(ctx.alloc(), spdyDataFrame.streamId(), spdyDataFrame.isLast(), spdyDataFrame.content());
            ctx.write(frame, promise);
         } finally {
            spdyDataFrame.release();
         }
      } else if (msg instanceof SpdySynStreamFrame) {
         SpdySynStreamFrame spdySynStreamFrame = (SpdySynStreamFrame)msg;
         ByteBuf headerBlock = this.spdyHeaderBlockEncoder.encode(ctx.alloc(), spdySynStreamFrame);

         ByteBuf frame;
         try {
            frame = this.spdyFrameEncoder.encodeSynStreamFrame(ctx.alloc(), spdySynStreamFrame.streamId(), spdySynStreamFrame.associatedStreamId(), spdySynStreamFrame.priority(), spdySynStreamFrame.isLast(), spdySynStreamFrame.isUnidirectional(), headerBlock);
         } finally {
            headerBlock.release();
         }

         ctx.write(frame, promise);
      } else if (msg instanceof SpdySynReplyFrame) {
         SpdySynReplyFrame spdySynReplyFrame = (SpdySynReplyFrame)msg;
         ByteBuf headerBlock = this.spdyHeaderBlockEncoder.encode(ctx.alloc(), spdySynReplyFrame);

         ByteBuf frame;
         try {
            frame = this.spdyFrameEncoder.encodeSynReplyFrame(ctx.alloc(), spdySynReplyFrame.streamId(), spdySynReplyFrame.isLast(), headerBlock);
         } finally {
            headerBlock.release();
         }

         ctx.write(frame, promise);
      } else if (msg instanceof SpdyRstStreamFrame) {
         SpdyRstStreamFrame spdyRstStreamFrame = (SpdyRstStreamFrame)msg;
         ByteBuf frame = this.spdyFrameEncoder.encodeRstStreamFrame(ctx.alloc(), spdyRstStreamFrame.streamId(), spdyRstStreamFrame.status().code());
         ctx.write(frame, promise);
      } else if (msg instanceof SpdySettingsFrame) {
         SpdySettingsFrame spdySettingsFrame = (SpdySettingsFrame)msg;
         ByteBuf frame = this.spdyFrameEncoder.encodeSettingsFrame(ctx.alloc(), spdySettingsFrame);
         ctx.write(frame, promise);
      } else if (msg instanceof SpdyPingFrame) {
         SpdyPingFrame spdyPingFrame = (SpdyPingFrame)msg;
         ByteBuf frame = this.spdyFrameEncoder.encodePingFrame(ctx.alloc(), spdyPingFrame.id());
         ctx.write(frame, promise);
      } else if (msg instanceof SpdyGoAwayFrame) {
         SpdyGoAwayFrame spdyGoAwayFrame = (SpdyGoAwayFrame)msg;
         ByteBuf frame = this.spdyFrameEncoder.encodeGoAwayFrame(ctx.alloc(), spdyGoAwayFrame.lastGoodStreamId(), spdyGoAwayFrame.status().code());
         ctx.write(frame, promise);
      } else if (msg instanceof SpdyHeadersFrame) {
         SpdyHeadersFrame spdyHeadersFrame = (SpdyHeadersFrame)msg;
         ByteBuf headerBlock = this.spdyHeaderBlockEncoder.encode(ctx.alloc(), spdyHeadersFrame);

         ByteBuf frame;
         try {
            frame = this.spdyFrameEncoder.encodeHeadersFrame(ctx.alloc(), spdyHeadersFrame.streamId(), spdyHeadersFrame.isLast(), headerBlock);
         } finally {
            headerBlock.release();
         }

         ctx.write(frame, promise);
      } else if (msg instanceof SpdyWindowUpdateFrame) {
         SpdyWindowUpdateFrame spdyWindowUpdateFrame = (SpdyWindowUpdateFrame)msg;
         ByteBuf frame = this.spdyFrameEncoder.encodeWindowUpdateFrame(ctx.alloc(), spdyWindowUpdateFrame.streamId(), spdyWindowUpdateFrame.deltaWindowSize());
         ctx.write(frame, promise);
      } else {
         if (!(msg instanceof SpdyUnknownFrame)) {
            throw new UnsupportedMessageTypeException(msg, new Class[0]);
         }

         SpdyUnknownFrame spdyUnknownFrame = (SpdyUnknownFrame)msg;

         try {
            ByteBuf frame = this.spdyFrameEncoder.encodeUnknownFrame(ctx.alloc(), spdyUnknownFrame.frameType(), spdyUnknownFrame.flags(), spdyUnknownFrame.content());
            ctx.write(frame, promise);
         } finally {
            spdyUnknownFrame.release();
         }
      }

   }

   public void readDataFrame(int streamId, boolean last, ByteBuf data) {
      this.read = true;
      SpdyDataFrame spdyDataFrame = new DefaultSpdyDataFrame(streamId, data);
      spdyDataFrame.setLast(last);
      this.ctx.fireChannelRead(spdyDataFrame);
   }

   public void readSynStreamFrame(int streamId, int associatedToStreamId, byte priority, boolean last, boolean unidirectional) {
      SpdySynStreamFrame spdySynStreamFrame = new DefaultSpdySynStreamFrame(streamId, associatedToStreamId, priority, this.validateHeaders);
      spdySynStreamFrame.setLast(last);
      spdySynStreamFrame.setUnidirectional(unidirectional);
      this.spdyHeadersFrame = spdySynStreamFrame;
   }

   public void readSynReplyFrame(int streamId, boolean last) {
      SpdySynReplyFrame spdySynReplyFrame = new DefaultSpdySynReplyFrame(streamId, this.validateHeaders);
      spdySynReplyFrame.setLast(last);
      this.spdyHeadersFrame = spdySynReplyFrame;
   }

   public void readRstStreamFrame(int streamId, int statusCode) {
      this.read = true;
      SpdyRstStreamFrame spdyRstStreamFrame = new DefaultSpdyRstStreamFrame(streamId, statusCode);
      this.ctx.fireChannelRead(spdyRstStreamFrame);
   }

   public void readSettingsFrame(boolean clearPersisted) {
      this.read = true;
      this.spdySettingsFrame = new DefaultSpdySettingsFrame();
      this.spdySettingsFrame.setClearPreviouslyPersistedSettings(clearPersisted);
   }

   public void readSetting(int id, int value, boolean persistValue, boolean persisted) {
      this.spdySettingsFrame.setValue(id, value, persistValue, persisted);
   }

   public void readSettingsEnd() {
      this.read = true;
      Object frame = this.spdySettingsFrame;
      this.spdySettingsFrame = null;
      this.ctx.fireChannelRead(frame);
   }

   public void readPingFrame(int id) {
      this.read = true;
      SpdyPingFrame spdyPingFrame = new DefaultSpdyPingFrame(id);
      this.ctx.fireChannelRead(spdyPingFrame);
   }

   public void readGoAwayFrame(int lastGoodStreamId, int statusCode) {
      this.read = true;
      SpdyGoAwayFrame spdyGoAwayFrame = new DefaultSpdyGoAwayFrame(lastGoodStreamId, statusCode);
      this.ctx.fireChannelRead(spdyGoAwayFrame);
   }

   public void readHeadersFrame(int streamId, boolean last) {
      this.spdyHeadersFrame = new DefaultSpdyHeadersFrame(streamId, this.validateHeaders);
      this.spdyHeadersFrame.setLast(last);
   }

   public void readWindowUpdateFrame(int streamId, int deltaWindowSize) {
      this.read = true;
      SpdyWindowUpdateFrame spdyWindowUpdateFrame = new DefaultSpdyWindowUpdateFrame(streamId, deltaWindowSize);
      this.ctx.fireChannelRead(spdyWindowUpdateFrame);
   }

   public void readHeaderBlock(ByteBuf headerBlock) {
      try {
         this.spdyHeaderBlockDecoder.decode(this.ctx.alloc(), headerBlock, this.spdyHeadersFrame);
      } catch (Exception e) {
         this.ctx.fireExceptionCaught(e);
      } finally {
         headerBlock.release();
      }

   }

   public void readHeaderBlockEnd() {
      Object frame = null;

      try {
         this.spdyHeaderBlockDecoder.endHeaderBlock(this.spdyHeadersFrame);
         frame = this.spdyHeadersFrame;
         this.spdyHeadersFrame = null;
      } catch (Exception e) {
         this.ctx.fireExceptionCaught(e);
      }

      if (frame != null) {
         this.read = true;
         this.ctx.fireChannelRead(frame);
      }

   }

   private void readUnknownFrame(int frameType, byte flags, ByteBuf payload) {
      this.read = true;
      this.ctx.fireChannelRead(this.newSpdyUnknownFrame(frameType, flags, payload));
   }

   protected SpdyFrame newSpdyUnknownFrame(int frameType, byte flags, ByteBuf payload) {
      return new DefaultSpdyUnknownFrame(frameType, flags, payload);
   }

   protected boolean isValidUnknownFrameHeader(int streamId, int type, byte flags, int length) {
      return false;
   }

   public void readFrameError(String message) {
      this.ctx.fireExceptionCaught(INVALID_FRAME);
   }
}
