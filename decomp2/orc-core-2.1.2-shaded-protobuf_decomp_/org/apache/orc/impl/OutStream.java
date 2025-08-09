package org.apache.orc.impl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.util.function.Consumer;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import org.apache.hadoop.io.BytesWritable;
import org.apache.orc.CompressionCodec;
import org.apache.orc.PhysicalWriter;
import org.apache.orc.impl.writer.StreamOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OutStream extends PositionedOutputStream {
   private static final Logger LOG = LoggerFactory.getLogger(OutStream.class);
   static final Logger KEY_LOGGER = LoggerFactory.getLogger("org.apache.orc.keys");
   public static final int HEADER_SIZE = 3;
   private final Object name;
   private final PhysicalWriter.OutputReceiver receiver;
   private ByteBuffer current = null;
   public OutputCompressedBuffer compressedBuffer = new OutputCompressedBuffer();
   private final int bufferSize;
   private final CompressionCodec codec;
   private final CompressionCodec.Options options;
   private long compressedBytes = 0L;
   private long uncompressedBytes = 0L;
   private final Cipher cipher;
   private final Key key;
   private final byte[] iv;

   public OutStream(Object name, StreamOptions options, PhysicalWriter.OutputReceiver receiver) {
      this.name = name;
      this.bufferSize = options.getBufferSize();
      this.codec = options.getCodec();
      this.options = options.getCodecOptions();
      this.receiver = receiver;
      if (options.isEncrypted()) {
         this.cipher = options.getAlgorithm().createCipher();
         this.key = options.getKey();
         this.iv = options.getIv();
         this.resetState();
      } else {
         this.cipher = null;
         this.key = null;
         this.iv = null;
      }

      LOG.debug("Stream {} written to with {}", name, options);
      logKeyAndIv(name, this.key, this.iv);
   }

   static void logKeyAndIv(Object name, Key key, byte[] iv) {
      if (iv != null && KEY_LOGGER.isDebugEnabled()) {
         KEY_LOGGER.debug("Stream: {} Key: {} IV: {}", new Object[]{name, new BytesWritable(key.getEncoded()), new BytesWritable(iv)});
      }

   }

   public void changeIv(Consumer modifier) {
      if (this.iv != null) {
         modifier.accept(this.iv);
         this.resetState();
         logKeyAndIv(this.name, this.key, this.iv);
      }

   }

   private void resetState() {
      try {
         this.cipher.init(1, this.key, new IvParameterSpec(this.iv));
      } catch (InvalidKeyException e) {
         throw new IllegalStateException("ORC bad encryption key for " + String.valueOf(this), e);
      } catch (InvalidAlgorithmParameterException e) {
         throw new IllegalStateException("ORC bad encryption parameter for " + String.valueOf(this), e);
      }
   }

   void outputBuffer(ByteBuffer buffer) throws IOException {
      if (this.cipher != null) {
         ByteBuffer output = buffer.duplicate();
         int len = buffer.remaining();

         try {
            int encrypted = this.cipher.update(buffer, output);
            output.flip();
            this.receiver.output(output);
            if (encrypted != len) {
               throw new IllegalArgumentException("Encryption of incomplete buffer " + len + " -> " + encrypted + " in " + String.valueOf(this));
            }
         } catch (ShortBufferException e) {
            throw new IOException("Short buffer in encryption in " + String.valueOf(this), e);
         }
      } else {
         this.receiver.output(buffer);
      }

   }

   void finishEncryption() {
      try {
         byte[] finalBytes = this.cipher.doFinal();
         if (finalBytes != null && finalBytes.length != 0) {
            throw new IllegalStateException("We shouldn't have remaining bytes " + String.valueOf(this));
         }
      } catch (IllegalBlockSizeException e) {
         throw new IllegalArgumentException("Bad block size", e);
      } catch (BadPaddingException e) {
         throw new IllegalArgumentException("Bad padding", e);
      }
   }

   private static void writeHeader(ByteBuffer buffer, int position, int val, boolean original) {
      buffer.put(position, (byte)((val << 1) + (original ? 1 : 0)));
      buffer.put(position + 1, (byte)(val >> 7));
      buffer.put(position + 2, (byte)(val >> 15));
   }

   private void getNewInputBuffer() {
      if (this.codec == null) {
         this.current = ByteBuffer.allocate(this.bufferSize);
      } else {
         this.current = ByteBuffer.allocate(this.bufferSize + 3);
         writeHeader(this.current, 0, this.bufferSize, true);
         this.current.position(3);
      }

   }

   public static void assertBufferSizeValid(int bufferSize) throws IllegalArgumentException {
      if (bufferSize >= 8388608) {
         throw new IllegalArgumentException("Illegal value of ORC compression buffer size: " + bufferSize);
      }
   }

   private void flip() {
      this.current.limit(this.current.position());
      this.current.position(this.codec == null ? 0 : 3);
   }

   public void write(int i) throws IOException {
      if (this.current == null) {
         this.getNewInputBuffer();
      }

      if (this.current.remaining() < 1) {
         this.spill();
      }

      ++this.uncompressedBytes;
      this.current.put((byte)i);
   }

   public void write(byte[] bytes, int offset, int length) throws IOException {
      if (this.current == null) {
         this.getNewInputBuffer();
      }

      int remaining = Math.min(this.current.remaining(), length);
      this.current.put(bytes, offset, remaining);
      this.uncompressedBytes += (long)remaining;

      for(int var5 = length - remaining; var5 != 0; var5 -= remaining) {
         this.spill();
         offset += remaining;
         remaining = Math.min(this.current.remaining(), var5);
         this.current.put(bytes, offset, remaining);
         this.uncompressedBytes += (long)remaining;
      }

   }

   private void spill() throws IOException {
      if (this.current != null && this.current.position() != (this.codec == null ? 0 : 3)) {
         this.flip();
         if (this.codec == null) {
            this.outputBuffer(this.current);
            this.getNewInputBuffer();
         } else {
            this.compressedBuffer.init();
            int currentPosn = this.compressedBuffer.getCurrentPosn();
            this.compressedBuffer.advanceTo(currentPosn + 3);
            if (this.codec.compress(this.current, this.compressedBuffer.compressed, this.compressedBuffer.overflow, this.options)) {
               this.uncompressedBytes = 0L;
               this.current.position(3);
               this.current.limit(this.current.capacity());
               this.compressedBytes += this.compressedBuffer.commitCompress(currentPosn);
            } else {
               this.compressedBytes += this.uncompressedBytes + 3L;
               this.uncompressedBytes = 0L;
               this.compressedBuffer.abortCompress(currentPosn);
               this.current.position(0);
               writeHeader(this.current, 0, this.current.limit() - 3, true);
               this.outputBuffer(this.current);
               this.getNewInputBuffer();
            }
         }

      }
   }

   public void getPosition(PositionRecorder recorder) {
      if (this.codec == null) {
         recorder.addPosition(this.uncompressedBytes);
      } else {
         recorder.addPosition(this.compressedBytes);
         recorder.addPosition(this.uncompressedBytes);
      }

   }

   public void flush() throws IOException {
      this.spill();
      this.compressedBuffer.reset();
      if (this.cipher != null) {
         this.finishEncryption();
      }

      this.uncompressedBytes = 0L;
      this.compressedBytes = 0L;
      this.current = null;
   }

   public String toString() {
      return this.name.toString();
   }

   public long getBufferSize() {
      if (this.codec == null) {
         return this.uncompressedBytes + (long)(this.current == null ? 0 : this.current.remaining());
      } else {
         long result = 0L;
         if (this.current != null) {
            result += (long)this.current.capacity();
         }

         return result + (long)this.compressedBuffer.getCapacity() + this.compressedBytes;
      }
   }

   public void suppress() {
      this.receiver.suppress();
   }

   private class OutputCompressedBuffer {
      ByteBuffer compressed = null;
      ByteBuffer overflow = null;

      public void init() {
         if (this.compressed == null) {
            this.compressed = this.getNewOutputBuffer();
         } else if (this.overflow == null) {
            this.overflow = this.getNewOutputBuffer();
         }

      }

      public int getCurrentPosn() {
         if (this.compressed != null) {
            return this.compressed.position();
         } else {
            throw new IllegalStateException("Output Compression buffer not being init'ed properly");
         }
      }

      public void advanceTo(int newPosn) {
         this.compressed.position(newPosn);
      }

      public int getCapacity() {
         int result = 0;
         if (this.compressed != null) {
            result += this.compressed.capacity();
         }

         if (this.overflow != null) {
            result += this.overflow.capacity();
         }

         return result;
      }

      public long commitCompress(int startPosn) throws IOException {
         int totalBytes = this.compressed.position() - startPosn - 3;
         if (this.overflow != null) {
            totalBytes += this.overflow.position();
         }

         OutStream.writeHeader(this.compressed, startPosn, totalBytes, false);
         if (this.compressed.remaining() < 3) {
            this.compressed.flip();
            OutStream.this.outputBuffer(this.compressed);
            this.compressed = this.overflow;
            this.overflow = null;
         }

         return (long)(totalBytes + 3);
      }

      public void abortCompress(int currentPosn) throws IOException {
         if (currentPosn != 0) {
            this.compressed.position(currentPosn);
            this.compressed.flip();
            OutStream.this.outputBuffer(this.compressed);
            this.compressed = null;
            if (this.overflow != null) {
               this.overflow.clear();
               this.compressed = this.overflow;
               this.overflow = null;
            }
         } else {
            this.compressed.clear();
            if (this.overflow != null) {
               this.overflow.clear();
            }
         }

      }

      public void reset() throws IOException {
         if (this.compressed != null && this.compressed.position() != 0) {
            this.compressed.flip();
            OutStream.this.outputBuffer(this.compressed);
         }

         this.compressed = null;
         this.overflow = null;
      }

      private ByteBuffer getNewOutputBuffer() {
         return ByteBuffer.allocate(OutStream.this.bufferSize + 3);
      }
   }
}
