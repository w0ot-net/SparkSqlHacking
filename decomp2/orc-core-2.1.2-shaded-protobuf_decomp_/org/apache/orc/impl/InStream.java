package org.apache.orc.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.util.function.Consumer;
import javax.crypto.Cipher;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import org.apache.hadoop.hive.common.io.DiskRangeList;
import org.apache.orc.CompressionCodec;
import org.apache.orc.EncryptionAlgorithm;
import org.apache.orc.protobuf.CodedInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class InStream extends InputStream {
   private static final Logger LOG = LoggerFactory.getLogger(InStream.class);
   public static final int PROTOBUF_MESSAGE_MAX_LIMIT = 1073741824;
   protected final Object name;
   protected final long offset;
   protected long length;
   protected DiskRangeList bytes;
   protected long position;

   public InStream(Object name, long offset, long length) {
      this.name = name;
      this.offset = offset;
      this.length = length;
   }

   public String toString() {
      return this.name.toString();
   }

   public abstract void close();

   protected abstract void setCurrent(DiskRangeList var1, boolean var2);

   protected void reset(DiskRangeList input) {
      for(this.bytes = input; input != null && (input.getEnd() <= this.offset || input.getOffset() > this.offset + this.length); input = input.next) {
      }

      if (input != null && input.getOffset() > this.offset) {
         this.position = input.getOffset() - this.offset;
      } else {
         this.position = 0L;
      }

      this.setCurrent(input, true);
   }

   protected void reset(DiskRangeList input, long length) {
      this.length = length;
      this.reset(input);
   }

   public abstract void changeIv(Consumer var1);

   static int getRangeNumber(DiskRangeList list, DiskRangeList current) {
      int result = 0;

      for(DiskRangeList range = list; range != null && range != current; range = range.next) {
         ++result;
      }

      return result;
   }

   private static ByteBuffer allocateBuffer(int size, boolean isDirect) {
      return isDirect ? ByteBuffer.allocateDirect(size) : ByteBuffer.allocate(size);
   }

   public abstract void seek(PositionProvider var1) throws IOException;

   public static StreamOptions options() {
      return new StreamOptions();
   }

   public static InStream create(Object name, DiskRangeList input, long offset, long length, StreamOptions options) {
      LOG.debug("Reading {} with {} from {} for {}", new Object[]{name, options, offset, length});
      if (options != null && options.codec != null) {
         if (options.key == null) {
            return new CompressedStream(name, input, offset, length, options);
         } else {
            OutStream.logKeyAndIv(name, options.getKey(), options.getIv());
            return new EncryptedCompressedStream(name, input, offset, length, options);
         }
      } else if (options != null && options.key != null) {
         OutStream.logKeyAndIv(name, options.getKey(), options.getIv());
         return new EncryptedStream(name, input, offset, length, options);
      } else {
         return new UncompressedStream(name, input, offset, length);
      }
   }

   public static InStream create(Object name, DiskRangeList input, long offset, long length) {
      return create(name, input, offset, length, (StreamOptions)null);
   }

   public static CodedInputStream createCodedInputStream(InStream inStream) {
      CodedInputStream codedInputStream = CodedInputStream.newInstance((InputStream)inStream);
      codedInputStream.setSizeLimit(1073741824);
      return codedInputStream;
   }

   public static class UncompressedStream extends InStream {
      protected ByteBuffer decrypted;
      protected DiskRangeList currentRange;
      protected long currentOffset;

      public UncompressedStream(Object name, long offset, long length) {
         super(name, offset, length);
      }

      public UncompressedStream(Object name, DiskRangeList input, long offset, long length) {
         super(name, offset, length);
         this.reset(input, length);
      }

      public int read() {
         if (this.decrypted == null || this.decrypted.remaining() == 0) {
            if (this.position == this.length) {
               return -1;
            }

            this.setCurrent(this.currentRange.next, false);
         }

         ++this.position;
         return 255 & this.decrypted.get();
      }

      protected void setCurrent(DiskRangeList newRange, boolean isJump) {
         this.currentRange = newRange;
         if (newRange != null) {
            this.decrypted = newRange.getData().slice();
            this.currentOffset = newRange.getOffset();
            int start = (int)(this.position + this.offset - this.currentOffset);
            this.decrypted.position(start);
            this.decrypted.limit(start + (int)Math.min((long)this.decrypted.remaining(), this.length - this.position));
         }

      }

      public int read(byte[] data, int offset, int length) {
         if (this.decrypted == null || this.decrypted.remaining() == 0) {
            if (this.position == this.length) {
               return -1;
            }

            this.setCurrent(this.currentRange.next, false);
         }

         int actualLength = Math.min(length, this.decrypted.remaining());
         this.decrypted.get(data, offset, actualLength);
         this.position += (long)actualLength;
         return actualLength;
      }

      public int available() {
         return this.decrypted != null && this.decrypted.remaining() > 0 ? this.decrypted.remaining() : (int)(this.length - this.position);
      }

      public void close() {
         this.currentRange = null;
         this.position = this.length;
         this.decrypted = null;
         this.bytes = null;
      }

      public void changeIv(Consumer modifier) {
      }

      public void seek(PositionProvider index) throws IOException {
         this.seek(index.getNext());
      }

      public void seek(long desired) throws IOException {
         if (desired != 0L || this.bytes != null) {
            long positionFile = desired + this.offset;
            if (this.currentRange != null && positionFile >= this.currentRange.getOffset() && positionFile < this.currentRange.getEnd()) {
               this.decrypted.position((int)(positionFile - this.currentOffset));
               this.position = desired;
            } else {
               DiskRangeList curRange = this.bytes;

               while(true) {
                  if (curRange == null) {
                     String var10002 = String.valueOf(this.name);
                     throw new IllegalArgumentException("Seek in " + var10002 + " to " + desired + " is outside of the data");
                  }

                  if (curRange.getOffset() <= positionFile) {
                     if (curRange.next == null) {
                        if (positionFile <= curRange.getEnd()) {
                           break;
                        }
                     } else if (positionFile < curRange.getEnd()) {
                        break;
                     }
                  }

                  curRange = curRange.next;
               }

               this.position = desired;
               this.setCurrent(curRange, true);
            }
         }
      }

      public String toString() {
         String var10000 = String.valueOf(this.name);
         return "uncompressed stream " + var10000 + " position: " + this.position + " length: " + this.length + " range: " + getRangeNumber(this.bytes, this.currentRange) + " offset: " + this.currentRange.getOffset() + " position: " + (this.decrypted == null ? 0 : this.decrypted.position()) + " limit: " + (this.decrypted == null ? 0 : this.decrypted.limit());
      }
   }

   static class EncryptionState {
      private final Object name;
      private final Key key;
      private final byte[] iv;
      private final Cipher cipher;
      private final long offset;
      private ByteBuffer decrypted;

      EncryptionState(Object name, long offset, StreamOptions options) {
         this.name = name;
         this.offset = offset;
         EncryptionAlgorithm algorithm = options.getAlgorithm();
         this.key = options.getKey();
         this.iv = options.getIv();
         this.cipher = algorithm.createCipher();
      }

      void changeIv(Consumer modifier) {
         modifier.accept(this.iv);
         this.updateIv();
         OutStream.logKeyAndIv(this.name, this.key, this.iv);
      }

      private void updateIv() {
         try {
            this.cipher.init(2, this.key, new IvParameterSpec(this.iv));
         } catch (InvalidKeyException e) {
            throw new IllegalArgumentException("Invalid key on " + String.valueOf(this.name), e);
         } catch (InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException("Invalid iv on " + String.valueOf(this.name), e);
         }
      }

      void changeIv(long offset) {
         int blockSize = this.cipher.getBlockSize();
         long encryptionBlocks = offset / (long)blockSize;
         long extra = offset % (long)blockSize;
         CryptoUtils.clearCounter(this.iv);
         long sum;
         if (encryptionBlocks != 0L) {
            for(int posn = this.iv.length - 1; encryptionBlocks > 0L; encryptionBlocks = sum / 256L) {
               sum = (long)(this.iv[posn] & 255) + encryptionBlocks;
               this.iv[posn--] = (byte)((int)sum);
            }
         }

         this.updateIv();
         if (extra > 0L) {
            try {
               byte[] wasted = new byte[(int)extra];
               this.cipher.update(wasted, 0, wasted.length, wasted, 0);
            } catch (ShortBufferException e) {
               throw new IllegalArgumentException("Short buffer in " + String.valueOf(this.name), e);
            }
         }

      }

      ByteBuffer decrypt(ByteBuffer encrypted) {
         int length = encrypted.remaining();
         if (this.decrypted != null && this.decrypted.capacity() >= length) {
            this.decrypted.clear();
         } else {
            this.decrypted = ByteBuffer.allocate(length);
         }

         try {
            int output = this.cipher.update(encrypted, this.decrypted);
            if (output != length) {
               String var10002 = String.valueOf(this.name);
               throw new IllegalArgumentException("Problem decrypting " + var10002 + " at " + this.offset);
            }
         } catch (ShortBufferException e) {
            throw new IllegalArgumentException("Problem decrypting " + String.valueOf(this.name) + " at " + this.offset, e);
         }

         this.decrypted.flip();
         return this.decrypted;
      }

      void close() {
         this.decrypted = null;
      }
   }

   public static class EncryptedStream extends UncompressedStream {
      private final EncryptionState encrypt;

      public EncryptedStream(Object name, DiskRangeList input, long offset, long length, StreamOptions options) {
         super(name, offset, length);
         this.encrypt = new EncryptionState(name, offset, options);
         this.reset(input);
      }

      protected void setCurrent(DiskRangeList newRange, boolean isJump) {
         this.currentRange = newRange;
         if (newRange != null) {
            this.currentOffset = newRange.getOffset();
            ByteBuffer encrypted = newRange.getData().slice();
            if (this.currentOffset < this.offset) {
               int ignoreBytes = (int)(this.offset - this.currentOffset);
               encrypted.position(ignoreBytes);
               this.currentOffset = this.offset;
            }

            if (isJump) {
               this.encrypt.changeIv(this.currentOffset - this.offset);
            }

            if ((long)encrypted.remaining() > this.length + this.offset - this.currentOffset) {
               encrypted.limit((int)(this.length + this.offset - this.currentOffset));
            }

            this.decrypted = this.encrypt.decrypt(encrypted);
            this.decrypted.position((int)(this.position + this.offset - this.currentOffset));
         }

      }

      public void close() {
         super.close();
         this.encrypt.close();
      }

      public void changeIv(Consumer modifier) {
         this.encrypt.changeIv(modifier);
      }

      public String toString() {
         return "encrypted " + super.toString();
      }
   }

   public static class CompressedStream extends InStream {
      private final int bufferSize;
      private ByteBuffer uncompressed;
      private final CompressionCodec codec;
      protected ByteBuffer compressed;
      protected DiskRangeList currentRange;
      private boolean isUncompressedOriginal;
      protected long currentCompressedStart = -1L;

      public CompressedStream(Object name, long offset, long length, StreamOptions options) {
         super(name, offset, length);
         this.codec = options.codec;
         this.bufferSize = options.bufferSize;
      }

      public CompressedStream(Object name, DiskRangeList input, long offset, long length, StreamOptions options) {
         super(name, offset, length);
         this.codec = options.codec;
         this.bufferSize = options.bufferSize;
         this.reset(input);
      }

      private void allocateForUncompressed(int size, boolean isDirect) {
         this.uncompressed = InStream.allocateBuffer(size, isDirect);
      }

      protected void setCurrent(DiskRangeList newRange, boolean isJump) {
         this.currentRange = newRange;
         if (newRange != null) {
            this.compressed = newRange.getData().slice();
            int pos = (int)(this.position + this.offset - newRange.getOffset());
            this.compressed.position(pos);
            this.compressed.limit(pos + (int)Math.min((long)this.compressed.remaining(), this.length - this.position));
         }

      }

      private int readHeaderByte() {
         while(this.currentRange != null && (this.compressed == null || this.compressed.remaining() <= 0)) {
            this.setCurrent(this.currentRange.next, false);
         }

         if (this.compressed != null && this.compressed.remaining() > 0) {
            ++this.position;
            return this.compressed.get() & 255;
         } else {
            throw new IllegalStateException("Can't read header at " + String.valueOf(this));
         }
      }

      private void readHeader() throws IOException {
         this.currentCompressedStart = this.position;
         int b0 = this.readHeaderByte();
         int b1 = this.readHeaderByte();
         int b2 = this.readHeaderByte();
         boolean isOriginal = (b0 & 1) == 1;
         int chunkLength = b2 << 15 | b1 << 7 | b0 >> 1;
         if (chunkLength > this.bufferSize) {
            throw new IllegalArgumentException("Buffer size too small. size = " + this.bufferSize + " needed = " + chunkLength + " in " + String.valueOf(this.name));
         } else {
            ByteBuffer slice = this.slice(chunkLength);
            if (isOriginal) {
               this.uncompressed = slice;
               this.isUncompressedOriginal = true;
            } else {
               if (this.isUncompressedOriginal) {
                  this.allocateForUncompressed(this.bufferSize, slice.isDirect());
                  this.isUncompressedOriginal = false;
               } else if (this.uncompressed == null) {
                  this.allocateForUncompressed(this.bufferSize, slice.isDirect());
               } else {
                  this.uncompressed.clear();
               }

               this.codec.decompress(slice, this.uncompressed);
            }

         }
      }

      public int read() throws IOException {
         return !this.ensureUncompressed() ? -1 : 255 & this.uncompressed.get();
      }

      public int read(byte[] data, int offset, int length) throws IOException {
         if (!this.ensureUncompressed()) {
            return -1;
         } else {
            int actualLength = Math.min(length, this.uncompressed.remaining());
            this.uncompressed.get(data, offset, actualLength);
            return actualLength;
         }
      }

      private boolean ensureUncompressed() throws IOException {
         while(this.uncompressed == null || this.uncompressed.remaining() == 0) {
            if (this.position == this.length) {
               return false;
            }

            this.readHeader();
         }

         return true;
      }

      public int available() throws IOException {
         return !this.ensureUncompressed() ? 0 : this.uncompressed.remaining();
      }

      public void close() {
         this.uncompressed = null;
         this.compressed = null;
         this.currentRange = null;
         this.position = this.length;
         this.bytes = null;
      }

      public void changeIv(Consumer modifier) {
      }

      public void seek(PositionProvider index) throws IOException {
         boolean seeked = this.seek(index.getNext());
         long uncompressedBytes = index.getNext();
         if (!seeked) {
            if (this.uncompressed != null) {
               this.uncompressed.position((int)uncompressedBytes);
            }
         } else if (uncompressedBytes != 0L) {
            this.readHeader();
            this.uncompressed.position(this.uncompressed.position() + (int)uncompressedBytes);
         } else if (this.uncompressed != null) {
            this.uncompressed.position(this.uncompressed.limit());
         }

      }

      private ByteBuffer slice(int chunkLength) throws IOException {
         DiskRangeList oldRange = this.currentRange;
         long oldPosition = this.position;
         if (this.compressed.remaining() >= chunkLength) {
            ByteBuffer slice = this.compressed.slice();
            slice.limit(chunkLength);
            this.position += (long)chunkLength;
            this.compressed.position(this.compressed.position() + chunkLength);
            return slice;
         } else if (this.currentRange.next == null) {
            String var9 = String.valueOf(this);
            throw new IOException("EOF in " + var9 + " while trying to read " + chunkLength + " bytes");
         } else {
            if (InStream.LOG.isDebugEnabled()) {
               InStream.LOG.debug(String.format("Crossing into next BufferChunk because compressed only has %d bytes (needs %d)", this.compressed.remaining(), chunkLength));
            }

            ByteBuffer copy = InStream.allocateBuffer(chunkLength, this.compressed.isDirect());
            this.position += (long)this.compressed.remaining();
            int len = chunkLength - this.compressed.remaining();
            copy.put(this.compressed);

            while(this.currentRange.next != null) {
               this.setCurrent(this.currentRange.next, false);
               InStream.LOG.debug("Read slow-path, >1 cross block reads with {}", this);
               if (this.compressed.remaining() >= len) {
                  ByteBuffer slice = this.compressed.slice();
                  slice.limit(len);
                  copy.put(slice);
                  this.position += (long)len;
                  this.compressed.position(this.compressed.position() + len);
                  copy.flip();
                  return copy;
               }

               this.position += (long)this.compressed.remaining();
               len -= this.compressed.remaining();
               copy.put(this.compressed);
            }

            this.position = oldPosition;
            this.setCurrent(oldRange, true);
            String var10002 = String.valueOf(this);
            throw new IOException("EOF in " + var10002 + " while trying to read " + chunkLength + " bytes");
         }
      }

      boolean seek(long desired) throws IOException {
         if (desired == 0L && this.bytes == null) {
            return false;
         } else if (desired == this.currentCompressedStart) {
            return false;
         } else {
            long posn = desired + this.offset;
            DiskRangeList range = this.bytes;

            while(true) {
               if (range == null) {
                  String var10002 = String.valueOf(this);
                  throw new IOException("Seek outside of data in " + var10002 + " to " + desired);
               }

               if (range.getOffset() <= posn) {
                  if (range.next == null) {
                     if (posn <= range.getEnd()) {
                        break;
                     }
                  } else if (posn < range.getEnd()) {
                     break;
                  }
               }

               range = range.next;
            }

            this.position = desired;
            this.setCurrent(range, true);
            return true;
         }
      }

      private String rangeString() {
         StringBuilder builder = new StringBuilder();
         int i = 0;

         for(DiskRangeList range = this.bytes; range != null; range = range.next) {
            if (i != 0) {
               builder.append("; ");
            }

            builder.append(" range ");
            builder.append(i);
            builder.append(" = ");
            builder.append(range.getOffset());
            builder.append(" to ");
            builder.append(range.getEnd());
            ++i;
         }

         return builder.toString();
      }

      public String toString() {
         String var10000 = String.valueOf(this.name);
         return "compressed stream " + var10000 + " position: " + this.position + " length: " + this.length + " range: " + getRangeNumber(this.bytes, this.currentRange) + " offset: " + (this.compressed == null ? 0 : this.compressed.position()) + " limit: " + (this.compressed == null ? 0 : this.compressed.limit()) + this.rangeString() + (this.uncompressed == null ? "" : " uncompressed: " + this.uncompressed.position() + " to " + this.uncompressed.limit());
      }
   }

   private static class EncryptedCompressedStream extends CompressedStream {
      private final EncryptionState encrypt;

      EncryptedCompressedStream(Object name, DiskRangeList input, long offset, long length, StreamOptions options) {
         super(name, offset, length, options);
         this.encrypt = new EncryptionState(name, offset, options);
         this.reset(input);
      }

      protected void setCurrent(DiskRangeList newRange, boolean isJump) {
         this.currentRange = newRange;
         if (newRange != null) {
            long rangeOffset = newRange.getOffset();
            int ignoreBytes = 0;
            ByteBuffer encrypted = newRange.getData().slice();
            if (rangeOffset < this.offset) {
               ignoreBytes = (int)(this.offset - rangeOffset);
               encrypted.position(ignoreBytes);
            }

            if (isJump) {
               this.encrypt.changeIv((long)ignoreBytes + rangeOffset - this.offset);
            }

            encrypted.limit(ignoreBytes + (int)Math.min((long)encrypted.remaining(), this.length));
            this.compressed = this.encrypt.decrypt(encrypted);
            if (this.position + this.offset > rangeOffset + (long)ignoreBytes) {
               this.compressed.position((int)(this.position + this.offset - rangeOffset - (long)ignoreBytes));
            }
         }

      }

      public void close() {
         super.close();
         this.encrypt.close();
      }

      public void changeIv(Consumer modifier) {
         this.encrypt.changeIv(modifier);
      }

      public String toString() {
         return "encrypted " + super.toString();
      }
   }

   public static class StreamOptions implements Cloneable {
      private CompressionCodec codec;
      private int bufferSize;
      private EncryptionAlgorithm algorithm;
      private Key key;
      private byte[] iv;

      public StreamOptions(StreamOptions other) {
         this.codec = other.codec;
         this.bufferSize = other.bufferSize;
         this.algorithm = other.algorithm;
         this.key = other.key;
         this.iv = other.iv == null ? null : (byte[])other.iv.clone();
      }

      public StreamOptions() {
      }

      public StreamOptions withCodec(CompressionCodec value) {
         this.codec = value;
         return this;
      }

      public StreamOptions withBufferSize(int value) {
         this.bufferSize = value;
         return this;
      }

      public StreamOptions withEncryption(EncryptionAlgorithm algorithm, Key key, byte[] iv) {
         this.algorithm = algorithm;
         this.key = key;
         this.iv = iv;
         return this;
      }

      public boolean isCompressed() {
         return this.codec != null;
      }

      public CompressionCodec getCodec() {
         return this.codec;
      }

      public int getBufferSize() {
         return this.bufferSize;
      }

      public EncryptionAlgorithm getAlgorithm() {
         return this.algorithm;
      }

      public Key getKey() {
         return this.key;
      }

      public byte[] getIv() {
         return this.iv;
      }

      public StreamOptions clone() {
         try {
            StreamOptions clone = (StreamOptions)super.clone();
            if (clone.codec != null) {
               clone.codec = OrcCodecPool.getCodec(this.codec.getKind());
            }

            return clone;
         } catch (CloneNotSupportedException e) {
            throw new UnsupportedOperationException("uncloneable", e);
         }
      }

      public String toString() {
         StringBuilder buffer = new StringBuilder();
         buffer.append("compress: ");
         buffer.append(this.codec == null ? "none" : this.codec.getKind());
         buffer.append(", buffer size: ");
         buffer.append(this.bufferSize);
         if (this.key != null) {
            buffer.append(", encryption: ");
            buffer.append(this.algorithm);
         }

         return buffer.toString();
      }
   }
}
