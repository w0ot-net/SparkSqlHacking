package io.netty.handler.codec.compression;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.internal.ObjectUtil;
import java.util.List;
import java.util.zip.Checksum;
import net.jpountz.lz4.LZ4Exception;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;

public class Lz4FrameDecoder extends ByteToMessageDecoder {
   private State currentState;
   private LZ4FastDecompressor decompressor;
   private ByteBufChecksum checksum;
   private int blockType;
   private int compressedLength;
   private int decompressedLength;
   private int currentChecksum;

   public Lz4FrameDecoder() {
      this(false);
   }

   public Lz4FrameDecoder(boolean validateChecksums) {
      this(LZ4Factory.fastestInstance(), validateChecksums);
   }

   public Lz4FrameDecoder(LZ4Factory factory, boolean validateChecksums) {
      this(factory, validateChecksums ? new Lz4XXHash32(-1756908916) : null);
   }

   public Lz4FrameDecoder(LZ4Factory factory, Checksum checksum) {
      this.currentState = Lz4FrameDecoder.State.INIT_BLOCK;
      this.decompressor = ((LZ4Factory)ObjectUtil.checkNotNull(factory, "factory")).fastDecompressor();
      this.checksum = checksum == null ? null : ByteBufChecksum.wrapChecksum(checksum);
   }

   protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {
      try {
         switch (this.currentState) {
            case INIT_BLOCK:
               if (in.readableBytes() < 21) {
                  break;
               }

               long magic = in.readLong();
               if (magic != 5501767354678207339L) {
                  throw new DecompressionException("unexpected block identifier");
               }

               int token = in.readByte();
               int compressionLevel = (token & 15) + 10;
               int blockType = token & 240;
               int compressedLength = Integer.reverseBytes(in.readInt());
               if (compressedLength < 0 || compressedLength > 33554432) {
                  throw new DecompressionException(String.format("invalid compressedLength: %d (expected: 0-%d)", compressedLength, 33554432));
               }

               int decompressedLength = Integer.reverseBytes(in.readInt());
               int maxDecompressedLength = 1 << compressionLevel;
               if (decompressedLength < 0 || decompressedLength > maxDecompressedLength) {
                  throw new DecompressionException(String.format("invalid decompressedLength: %d (expected: 0-%d)", decompressedLength, maxDecompressedLength));
               }

               if (decompressedLength == 0 && compressedLength != 0 || decompressedLength != 0 && compressedLength == 0 || blockType == 16 && decompressedLength != compressedLength) {
                  throw new DecompressionException(String.format("stream corrupted: compressedLength(%d) and decompressedLength(%d) mismatch", compressedLength, decompressedLength));
               }

               int currentChecksum = Integer.reverseBytes(in.readInt());
               if (decompressedLength == 0 && compressedLength == 0) {
                  if (currentChecksum != 0) {
                     throw new DecompressionException("stream corrupted: checksum error");
                  }

                  this.currentState = Lz4FrameDecoder.State.FINISHED;
                  this.decompressor = null;
                  this.checksum = null;
                  break;
               } else {
                  this.blockType = blockType;
                  this.compressedLength = compressedLength;
                  this.decompressedLength = decompressedLength;
                  this.currentChecksum = currentChecksum;
                  this.currentState = Lz4FrameDecoder.State.DECOMPRESS_DATA;
               }
            case DECOMPRESS_DATA:
               int blockType = this.blockType;
               int compressedLength = this.compressedLength;
               int decompressedLength = this.decompressedLength;
               int currentChecksum = this.currentChecksum;
               if (in.readableBytes() >= compressedLength) {
                  ByteBufChecksum checksum = this.checksum;
                  ByteBuf uncompressed = null;

                  try {
                     switch (blockType) {
                        case 16:
                           uncompressed = in.retainedSlice(in.readerIndex(), decompressedLength);
                           break;
                        case 32:
                           uncompressed = ctx.alloc().buffer(decompressedLength, decompressedLength);
                           this.decompressor.decompress(CompressionUtil.safeReadableNioBuffer(in), uncompressed.internalNioBuffer(uncompressed.writerIndex(), decompressedLength));
                           uncompressed.writerIndex(uncompressed.writerIndex() + decompressedLength);
                           break;
                        default:
                           throw new DecompressionException(String.format("unexpected blockType: %d (expected: %d or %d)", blockType, 16, 32));
                     }

                     in.skipBytes(compressedLength);
                     if (checksum != null) {
                        CompressionUtil.checkChecksum(checksum, uncompressed, currentChecksum);
                     }

                     out.add(uncompressed);
                     uncompressed = null;
                     this.currentState = Lz4FrameDecoder.State.INIT_BLOCK;
                  } catch (LZ4Exception e) {
                     throw new DecompressionException(e);
                  } finally {
                     if (uncompressed != null) {
                        uncompressed.release();
                     }

                  }
               }
               break;
            case FINISHED:
            case CORRUPTED:
               in.skipBytes(in.readableBytes());
               break;
            default:
               throw new IllegalStateException();
         }

      } catch (Exception e) {
         this.currentState = Lz4FrameDecoder.State.CORRUPTED;
         throw e;
      }
   }

   public boolean isClosed() {
      return this.currentState == Lz4FrameDecoder.State.FINISHED;
   }

   private static enum State {
      INIT_BLOCK,
      DECOMPRESS_DATA,
      FINISHED,
      CORRUPTED;
   }
}
