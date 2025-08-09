package io.airlift.compress.zstd;

import io.airlift.compress.MalformedInputException;
import java.util.Arrays;
import sun.misc.Unsafe;

public class ZstdIncrementalFrameDecompressor {
   private final ZstdFrameDecompressor frameDecompressor = new ZstdFrameDecompressor();
   private State state;
   private FrameHeader frameHeader;
   private int blockHeader;
   private int inputConsumed;
   private int outputBufferUsed;
   private int inputRequired;
   private int requestedOutputSize;
   private byte[] windowBase;
   private long windowAddress;
   private long windowLimit;
   private long windowPosition;
   private XxHash64 partialHash;

   public ZstdIncrementalFrameDecompressor() {
      this.state = ZstdIncrementalFrameDecompressor.State.INITIAL;
      this.blockHeader = -1;
      this.windowBase = new byte[0];
      this.windowAddress = (long)Unsafe.ARRAY_BYTE_BASE_OFFSET;
      this.windowLimit = (long)Unsafe.ARRAY_BYTE_BASE_OFFSET;
      this.windowPosition = (long)Unsafe.ARRAY_BYTE_BASE_OFFSET;
   }

   public boolean isAtStoppingPoint() {
      return this.state == ZstdIncrementalFrameDecompressor.State.READ_FRAME_MAGIC;
   }

   public int getInputConsumed() {
      return this.inputConsumed;
   }

   public int getOutputBufferUsed() {
      return this.outputBufferUsed;
   }

   public int getInputRequired() {
      return this.inputRequired;
   }

   public int getRequestedOutputSize() {
      return this.requestedOutputSize;
   }

   public void partialDecompress(final Object inputBase, final long inputAddress, final long inputLimit, final byte[] outputArray, final int outputOffset, final int outputLimit) {
      if ((long)this.inputRequired > inputLimit - inputAddress) {
         throw new IllegalArgumentException(String.format("Required %s input bytes, but only %s input bytes were supplied", this.inputRequired, inputLimit - inputAddress));
      } else if (this.requestedOutputSize > 0 && outputOffset >= outputLimit) {
         throw new IllegalArgumentException("Not enough space in output buffer to output");
      } else {
         long input = inputAddress;
         int output = outputOffset;

         while(true) {
            int flushableOutputSize = this.computeFlushableOutputSize(this.frameHeader);
            if (flushableOutputSize > 0) {
               int freeOutputSize = outputLimit - output;
               if (freeOutputSize > 0) {
                  int copySize = Math.min(freeOutputSize, flushableOutputSize);
                  System.arraycopy(this.windowBase, Math.toIntExact(this.windowAddress - (long)Unsafe.ARRAY_BYTE_BASE_OFFSET), outputArray, output, copySize);
                  if (this.partialHash != null) {
                     this.partialHash.update(outputArray, output, copySize);
                  }

                  this.windowAddress += (long)copySize;
                  output += copySize;
                  flushableOutputSize -= copySize;
               }

               if (flushableOutputSize > 0) {
                  this.requestOutput(inputAddress, outputOffset, input, output, flushableOutputSize);
                  return;
               }
            }

            Util.checkState(this.computeFlushableOutputSize(this.frameHeader) == 0, "Expected output to be flushed");
            if (this.state == ZstdIncrementalFrameDecompressor.State.READ_FRAME_MAGIC || this.state == ZstdIncrementalFrameDecompressor.State.INITIAL) {
               if (inputLimit - input < 4L) {
                  this.inputRequired(inputAddress, outputOffset, input, output, 4);
                  return;
               }

               input += (long)ZstdFrameDecompressor.verifyMagic(inputBase, input, inputLimit);
               this.state = ZstdIncrementalFrameDecompressor.State.READ_FRAME_HEADER;
            }

            if (this.state == ZstdIncrementalFrameDecompressor.State.READ_FRAME_HEADER) {
               if (inputLimit - input < 1L) {
                  this.inputRequired(inputAddress, outputOffset, input, output, 1);
                  return;
               }

               flushableOutputSize = determineFrameHeaderSize(inputBase, input, inputLimit);
               if (inputLimit - input < (long)flushableOutputSize) {
                  this.inputRequired(inputAddress, outputOffset, input, output, flushableOutputSize);
                  return;
               }

               this.frameHeader = ZstdFrameDecompressor.readFrameHeader(inputBase, input, inputLimit);
               Util.verify((long)flushableOutputSize == this.frameHeader.headerSize, input, "Unexpected frame header size");
               input += (long)flushableOutputSize;
               this.state = ZstdIncrementalFrameDecompressor.State.READ_BLOCK_HEADER;
               this.reset();
               if (this.frameHeader.hasChecksum) {
                  this.partialHash = new XxHash64();
               }
            } else {
               Util.verify(this.frameHeader != null, input, "Frame header is not set");
            }

            if (this.state == ZstdIncrementalFrameDecompressor.State.READ_BLOCK_HEADER) {
               long inputBufferSize = inputLimit - input;
               if (inputBufferSize < 3L) {
                  this.inputRequired(inputAddress, outputOffset, input, output, 3);
                  return;
               }

               if (inputBufferSize >= 4L) {
                  this.blockHeader = UnsafeUtil.UNSAFE.getInt(inputBase, input) & 16777215;
               } else {
                  this.blockHeader = UnsafeUtil.UNSAFE.getByte(inputBase, input) & 255 | (UnsafeUtil.UNSAFE.getByte(inputBase, input + 1L) & 255) << 8 | (UnsafeUtil.UNSAFE.getByte(inputBase, input + 2L) & 255) << 16;
                  int expected = UnsafeUtil.UNSAFE.getInt(inputBase, input) & 16777215;
                  Util.verify(this.blockHeader == expected, input, "oops");
               }

               input += 3L;
               this.state = ZstdIncrementalFrameDecompressor.State.READ_BLOCK;
            } else {
               Util.verify(this.blockHeader != -1, input, "Block header is not set");
            }

            flushableOutputSize = (this.blockHeader & 1) != 0;
            if (this.state == ZstdIncrementalFrameDecompressor.State.READ_BLOCK) {
               int blockType = this.blockHeader >>> 1 & 3;
               int blockSize = this.blockHeader >>> 3 & 2097151;
               this.resizeWindowBufferIfNecessary(this.frameHeader, blockType, blockSize);
               int decodedSize;
               switch (blockType) {
                  case 0:
                     if (inputLimit - input < (long)blockSize) {
                        this.inputRequired(inputAddress, outputOffset, input, output, blockSize);
                        return;
                     }

                     Util.verify(this.windowLimit - this.windowPosition >= (long)blockSize, input, "window buffer is too small");
                     decodedSize = ZstdFrameDecompressor.decodeRawBlock(inputBase, input, blockSize, this.windowBase, this.windowPosition, this.windowLimit);
                     input += (long)blockSize;
                     break;
                  case 1:
                     if (inputLimit - input < 1L) {
                        this.inputRequired(inputAddress, outputOffset, input, output, 1);
                        return;
                     }

                     Util.verify(this.windowLimit - this.windowPosition >= (long)blockSize, input, "window buffer is too small");
                     decodedSize = ZstdFrameDecompressor.decodeRleBlock(blockSize, inputBase, input, this.windowBase, this.windowPosition, this.windowLimit);
                     ++input;
                     break;
                  case 2:
                     if (inputLimit - input < (long)blockSize) {
                        this.inputRequired(inputAddress, outputOffset, input, output, blockSize);
                        return;
                     }

                     Util.verify(this.windowLimit - this.windowPosition >= 131072L, input, "window buffer is too small");
                     decodedSize = this.frameDecompressor.decodeCompressedBlock(inputBase, input, blockSize, this.windowBase, this.windowPosition, this.windowLimit, this.frameHeader.windowSize, this.windowAddress);
                     input += (long)blockSize;
                     break;
                  default:
                     throw Util.fail(input, "Invalid block type");
               }

               this.windowPosition += (long)decodedSize;
               if (flushableOutputSize) {
                  this.state = ZstdIncrementalFrameDecompressor.State.READ_BLOCK_CHECKSUM;
               } else {
                  this.state = ZstdIncrementalFrameDecompressor.State.READ_BLOCK_HEADER;
               }
            }

            if (this.state == ZstdIncrementalFrameDecompressor.State.READ_BLOCK_CHECKSUM) {
               if (this.frameHeader.hasChecksum) {
                  if (inputLimit - input < 4L) {
                     this.inputRequired(inputAddress, outputOffset, input, output, 4);
                     return;
                  }

                  int checksum = UnsafeUtil.UNSAFE.getInt(inputBase, input);
                  input += 4L;
                  Util.checkState(this.partialHash != null, "Partial hash not set");
                  int pendingOutputSize = Math.toIntExact(this.windowPosition - this.windowAddress);
                  this.partialHash.update(this.windowBase, Math.toIntExact(this.windowAddress - (long)Unsafe.ARRAY_BYTE_BASE_OFFSET), pendingOutputSize);
                  long hash = this.partialHash.hash();
                  if (checksum != (int)hash) {
                     throw new MalformedInputException(input, String.format("Bad checksum. Expected: %s, actual: %s", Integer.toHexString(checksum), Integer.toHexString((int)hash)));
                  }
               }

               this.state = ZstdIncrementalFrameDecompressor.State.READ_FRAME_MAGIC;
               this.frameHeader = null;
               this.blockHeader = -1;
            }
         }
      }
   }

   private void reset() {
      this.frameDecompressor.reset();
      this.windowAddress = (long)Unsafe.ARRAY_BYTE_BASE_OFFSET;
      this.windowPosition = (long)Unsafe.ARRAY_BYTE_BASE_OFFSET;
   }

   private int computeFlushableOutputSize(FrameHeader frameHeader) {
      return Math.max(0, Math.toIntExact(this.windowPosition - this.windowAddress - (long)(frameHeader == null ? 0 : frameHeader.computeRequiredOutputBufferLookBackSize())));
   }

   private void resizeWindowBufferIfNecessary(FrameHeader frameHeader, int blockType, int blockSize) {
      int maxBlockOutput;
      if (blockType != 0 && blockType != 1) {
         maxBlockOutput = 131072;
      } else {
         maxBlockOutput = blockSize;
      }

      if (this.windowLimit - this.windowPosition < 131072L) {
         int requiredWindowSize = frameHeader.computeRequiredOutputBufferLookBackSize();
         Util.checkState(this.windowPosition - this.windowAddress <= (long)requiredWindowSize, "Expected output to be flushed");
         int windowContentsSize = Math.toIntExact(this.windowPosition - this.windowAddress);
         if (this.windowAddress != (long)Unsafe.ARRAY_BYTE_BASE_OFFSET) {
            System.arraycopy(this.windowBase, Math.toIntExact(this.windowAddress - (long)Unsafe.ARRAY_BYTE_BASE_OFFSET), this.windowBase, 0, windowContentsSize);
            this.windowAddress = (long)Unsafe.ARRAY_BYTE_BASE_OFFSET;
            this.windowPosition = this.windowAddress + (long)windowContentsSize;
         }

         Util.checkState(this.windowAddress == (long)Unsafe.ARRAY_BYTE_BASE_OFFSET, "Window should be packed");
         if (this.windowLimit - this.windowPosition < (long)maxBlockOutput) {
            int newWindowSize;
            if (frameHeader.contentSize >= 0L && frameHeader.contentSize < (long)requiredWindowSize) {
               newWindowSize = Math.toIntExact(frameHeader.contentSize);
            } else {
               int newWindowSize = (windowContentsSize + maxBlockOutput) * 2;
               int newWindowSize = Math.min(newWindowSize, Math.max(requiredWindowSize, 131072) * 4);
               int var9 = Math.min(newWindowSize, 8519680);
               newWindowSize = Math.max(windowContentsSize + maxBlockOutput, var9);
               Util.checkState(windowContentsSize + maxBlockOutput <= newWindowSize, "Computed new window size buffer is not large enough");
            }

            this.windowBase = Arrays.copyOf(this.windowBase, newWindowSize);
            this.windowLimit = (long)(newWindowSize + Unsafe.ARRAY_BYTE_BASE_OFFSET);
         }

         Util.checkState(this.windowLimit - this.windowPosition >= (long)maxBlockOutput, "window buffer is too small");
      }

   }

   private static int determineFrameHeaderSize(final Object inputBase, final long inputAddress, final long inputLimit) {
      Util.verify(inputAddress < inputLimit, inputAddress, "Not enough input bytes");
      int frameHeaderDescriptor = UnsafeUtil.UNSAFE.getByte(inputBase, inputAddress) & 255;
      boolean singleSegment = (frameHeaderDescriptor & 32) != 0;
      int dictionaryDescriptor = frameHeaderDescriptor & 3;
      int contentSizeDescriptor = frameHeaderDescriptor >>> 6;
      return 1 + (singleSegment ? 0 : 1) + (dictionaryDescriptor == 0 ? 0 : 1 << dictionaryDescriptor - 1) + (contentSizeDescriptor == 0 ? (singleSegment ? 1 : 0) : 1 << contentSizeDescriptor);
   }

   private void requestOutput(long inputAddress, int outputOffset, long input, int output, int requestedOutputSize) {
      this.updateInputOutputState(inputAddress, outputOffset, input, output);
      Util.checkArgument(requestedOutputSize >= 0, "requestedOutputSize is negative");
      this.requestedOutputSize = requestedOutputSize;
      this.inputRequired = 0;
   }

   private void inputRequired(long inputAddress, int outputOffset, long input, int output, int inputRequired) {
      this.updateInputOutputState(inputAddress, outputOffset, input, output);
      Util.checkState(inputRequired >= 0, "inputRequired is negative");
      this.inputRequired = inputRequired;
      this.requestedOutputSize = 0;
   }

   private void updateInputOutputState(long inputAddress, int outputOffset, long input, int output) {
      this.inputConsumed = (int)(input - inputAddress);
      Util.checkState(this.inputConsumed >= 0, "inputConsumed is negative");
      this.outputBufferUsed = output - outputOffset;
      Util.checkState(this.outputBufferUsed >= 0, "outputBufferUsed is negative");
   }

   private static enum State {
      INITIAL,
      READ_FRAME_MAGIC,
      READ_FRAME_HEADER,
      READ_BLOCK_HEADER,
      READ_BLOCK,
      READ_BLOCK_CHECKSUM,
      FLUSH_OUTPUT;
   }
}
