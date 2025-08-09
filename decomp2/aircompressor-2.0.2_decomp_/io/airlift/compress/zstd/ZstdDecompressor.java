package io.airlift.compress.zstd;

import io.airlift.compress.Decompressor;
import io.airlift.compress.MalformedInputException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Objects;
import sun.misc.Unsafe;

public class ZstdDecompressor implements Decompressor {
   private final ZstdFrameDecompressor decompressor = new ZstdFrameDecompressor();

   public int decompress(byte[] input, int inputOffset, int inputLength, byte[] output, int outputOffset, int maxOutputLength) throws MalformedInputException {
      verifyRange(input, inputOffset, inputLength);
      verifyRange(output, outputOffset, maxOutputLength);
      long inputAddress = (long)(Unsafe.ARRAY_BYTE_BASE_OFFSET + inputOffset);
      long inputLimit = inputAddress + (long)inputLength;
      long outputAddress = (long)(Unsafe.ARRAY_BYTE_BASE_OFFSET + outputOffset);
      long outputLimit = outputAddress + (long)maxOutputLength;
      return this.decompressor.decompress(input, inputAddress, inputLimit, output, outputAddress, outputLimit);
   }

   public void decompress(ByteBuffer inputBuffer, ByteBuffer outputBuffer) throws MalformedInputException {
      Buffer output = outputBuffer;
      Object inputBase;
      long inputAddress;
      long inputLimit;
      if (((Buffer)inputBuffer).isDirect()) {
         inputBase = null;
         long address = UnsafeUtil.getAddress(inputBuffer);
         inputAddress = address + (long)((Buffer)inputBuffer).position();
         inputLimit = address + (long)((Buffer)inputBuffer).limit();
      } else {
         if (!((Buffer)inputBuffer).hasArray()) {
            throw new IllegalArgumentException("Unsupported input ByteBuffer implementation " + inputBuffer.getClass().getName());
         }

         inputBase = ((Buffer)inputBuffer).array();
         inputAddress = (long)(Unsafe.ARRAY_BYTE_BASE_OFFSET + ((Buffer)inputBuffer).arrayOffset() + ((Buffer)inputBuffer).position());
         inputLimit = (long)(Unsafe.ARRAY_BYTE_BASE_OFFSET + ((Buffer)inputBuffer).arrayOffset() + ((Buffer)inputBuffer).limit());
      }

      long outputAddress;
      long outputLimit;
      Object outputBase;
      if (((Buffer)outputBuffer).isDirect()) {
         outputBase = null;
         long address = UnsafeUtil.getAddress(outputBuffer);
         outputAddress = address + (long)((Buffer)outputBuffer).position();
         outputLimit = address + (long)((Buffer)outputBuffer).limit();
      } else {
         if (!((Buffer)outputBuffer).hasArray()) {
            throw new IllegalArgumentException("Unsupported output ByteBuffer implementation " + outputBuffer.getClass().getName());
         }

         outputBase = ((Buffer)outputBuffer).array();
         outputAddress = (long)(Unsafe.ARRAY_BYTE_BASE_OFFSET + ((Buffer)outputBuffer).arrayOffset() + ((Buffer)outputBuffer).position());
         outputLimit = (long)(Unsafe.ARRAY_BYTE_BASE_OFFSET + ((Buffer)outputBuffer).arrayOffset() + ((Buffer)outputBuffer).limit());
      }

      synchronized(inputBuffer) {
         synchronized(output) {
            int written = this.decompressor.decompress(inputBase, inputAddress, inputLimit, outputBase, outputAddress, outputLimit);
            output.position(output.position() + written);
         }

      }
   }

   public static long getDecompressedSize(byte[] input, int offset, int length) {
      int baseAddress = Unsafe.ARRAY_BYTE_BASE_OFFSET + offset;
      return ZstdFrameDecompressor.getDecompressedSize(input, (long)baseAddress, (long)(baseAddress + length));
   }

   private static void verifyRange(byte[] data, int offset, int length) {
      Objects.requireNonNull(data, "data is null");
      if (offset < 0 || length < 0 || offset + length > data.length) {
         throw new IllegalArgumentException(String.format("Invalid offset or length (%s, %s) in array of length %s", offset, length, data.length));
      }
   }
}
