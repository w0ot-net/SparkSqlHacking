package org.apache.orc.protobuf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

@CheckReturnValue
public abstract class ByteString implements Iterable, Serializable {
   private static final long serialVersionUID = 1L;
   static final int CONCATENATE_BY_COPY_SIZE = 128;
   static final int MIN_READ_FROM_CHUNK_SIZE = 256;
   static final int MAX_READ_FROM_CHUNK_SIZE = 8192;
   public static final ByteString EMPTY;
   private static final ByteArrayCopier byteArrayCopier;
   private int hash = 0;
   private static final int UNSIGNED_BYTE_MASK = 255;
   private static final Comparator UNSIGNED_LEXICOGRAPHICAL_COMPARATOR;

   ByteString() {
   }

   public abstract byte byteAt(int index);

   abstract byte internalByteAt(int index);

   public ByteIterator iterator() {
      return new AbstractByteIterator() {
         private int position = 0;
         private final int limit = ByteString.this.size();

         public boolean hasNext() {
            return this.position < this.limit;
         }

         public byte nextByte() {
            int currentPos = this.position;
            if (currentPos >= this.limit) {
               throw new NoSuchElementException();
            } else {
               this.position = currentPos + 1;
               return ByteString.this.internalByteAt(currentPos);
            }
         }
      };
   }

   public abstract int size();

   public final boolean isEmpty() {
      return this.size() == 0;
   }

   public static final ByteString empty() {
      return EMPTY;
   }

   private static int toInt(byte value) {
      return value & 255;
   }

   private static int hexDigit(char c) {
      if (c >= '0' && c <= '9') {
         return c - 48;
      } else if (c >= 'A' && c <= 'F') {
         return c - 65 + 10;
      } else {
         return c >= 'a' && c <= 'f' ? c - 97 + 10 : -1;
      }
   }

   private static int extractHexDigit(String hexString, int index) {
      int digit = hexDigit(hexString.charAt(index));
      if (digit == -1) {
         throw new NumberFormatException("Invalid hexString " + hexString + " must only contain [0-9a-fA-F] but contained " + hexString.charAt(index) + " at index " + index);
      } else {
         return digit;
      }
   }

   public static Comparator unsignedLexicographicalComparator() {
      return UNSIGNED_LEXICOGRAPHICAL_COMPARATOR;
   }

   public final ByteString substring(int beginIndex) {
      return this.substring(beginIndex, this.size());
   }

   public abstract ByteString substring(int beginIndex, int endIndex);

   public final boolean startsWith(ByteString prefix) {
      return this.size() >= prefix.size() && this.substring(0, prefix.size()).equals(prefix);
   }

   public final boolean endsWith(ByteString suffix) {
      return this.size() >= suffix.size() && this.substring(this.size() - suffix.size()).equals(suffix);
   }

   public static ByteString fromHex(@CompileTimeConstant String hexString) {
      if (hexString.length() % 2 != 0) {
         throw new NumberFormatException("Invalid hexString " + hexString + " of length " + hexString.length() + " must be even.");
      } else {
         byte[] bytes = new byte[hexString.length() / 2];

         for(int i = 0; i < bytes.length; ++i) {
            int d1 = extractHexDigit(hexString, 2 * i);
            int d2 = extractHexDigit(hexString, 2 * i + 1);
            bytes[i] = (byte)(d1 << 4 | d2);
         }

         return new LiteralByteString(bytes);
      }
   }

   public static ByteString copyFrom(byte[] bytes, int offset, int size) {
      checkRange(offset, offset + size, bytes.length);
      return new LiteralByteString(byteArrayCopier.copyFrom(bytes, offset, size));
   }

   public static ByteString copyFrom(byte[] bytes) {
      return copyFrom(bytes, 0, bytes.length);
   }

   static ByteString wrap(ByteBuffer buffer) {
      if (buffer.hasArray()) {
         int offset = buffer.arrayOffset();
         return wrap(buffer.array(), offset + buffer.position(), buffer.remaining());
      } else {
         return new NioByteString(buffer);
      }
   }

   static ByteString wrap(byte[] bytes) {
      return new LiteralByteString(bytes);
   }

   static ByteString wrap(byte[] bytes, int offset, int length) {
      return new BoundedByteString(bytes, offset, length);
   }

   public static ByteString copyFrom(ByteBuffer bytes, int size) {
      checkRange(0, size, bytes.remaining());
      byte[] copy = new byte[size];
      bytes.get(copy);
      return new LiteralByteString(copy);
   }

   public static ByteString copyFrom(ByteBuffer bytes) {
      return copyFrom(bytes, bytes.remaining());
   }

   public static ByteString copyFrom(String text, String charsetName) throws UnsupportedEncodingException {
      return new LiteralByteString(text.getBytes(charsetName));
   }

   public static ByteString copyFrom(String text, Charset charset) {
      return new LiteralByteString(text.getBytes(charset));
   }

   public static ByteString copyFromUtf8(String text) {
      return new LiteralByteString(text.getBytes(Internal.UTF_8));
   }

   public static ByteString readFrom(InputStream streamToDrain) throws IOException {
      return readFrom(streamToDrain, 256, 8192);
   }

   public static ByteString readFrom(InputStream streamToDrain, int chunkSize) throws IOException {
      return readFrom(streamToDrain, chunkSize, chunkSize);
   }

   public static ByteString readFrom(InputStream streamToDrain, int minChunkSize, int maxChunkSize) throws IOException {
      Collection<ByteString> results = new ArrayList();
      int chunkSize = minChunkSize;

      while(true) {
         ByteString chunk = readChunk(streamToDrain, chunkSize);
         if (chunk == null) {
            return copyFrom((Iterable)results);
         }

         results.add(chunk);
         chunkSize = Math.min(chunkSize * 2, maxChunkSize);
      }
   }

   private static ByteString readChunk(InputStream in, final int chunkSize) throws IOException {
      byte[] buf = new byte[chunkSize];

      int bytesRead;
      int count;
      for(bytesRead = 0; bytesRead < chunkSize; bytesRead += count) {
         count = in.read(buf, bytesRead, chunkSize - bytesRead);
         if (count == -1) {
            break;
         }
      }

      return bytesRead == 0 ? null : copyFrom(buf, 0, bytesRead);
   }

   public final ByteString concat(ByteString other) {
      if (Integer.MAX_VALUE - this.size() < other.size()) {
         throw new IllegalArgumentException("ByteString would be too long: " + this.size() + "+" + other.size());
      } else {
         return RopeByteString.concatenate(this, other);
      }
   }

   public static ByteString copyFrom(Iterable byteStrings) {
      int size;
      if (!(byteStrings instanceof Collection)) {
         int tempSize = 0;

         for(Iterator<ByteString> iter = byteStrings.iterator(); iter.hasNext(); ++tempSize) {
            iter.next();
         }

         size = tempSize;
      } else {
         size = ((Collection)byteStrings).size();
      }

      return size == 0 ? EMPTY : balancedConcat(byteStrings.iterator(), size);
   }

   private static ByteString balancedConcat(Iterator iterator, int length) {
      if (length < 1) {
         throw new IllegalArgumentException(String.format("length (%s) must be >= 1", length));
      } else {
         ByteString result;
         if (length == 1) {
            result = (ByteString)iterator.next();
         } else {
            int halfLength = length >>> 1;
            ByteString left = balancedConcat(iterator, halfLength);
            ByteString right = balancedConcat(iterator, length - halfLength);
            result = left.concat(right);
         }

         return result;
      }
   }

   public void copyTo(byte[] target, int offset) {
      this.copyTo(target, 0, offset, this.size());
   }

   /** @deprecated */
   @Deprecated
   public final void copyTo(byte[] target, int sourceOffset, int targetOffset, int numberToCopy) {
      checkRange(sourceOffset, sourceOffset + numberToCopy, this.size());
      checkRange(targetOffset, targetOffset + numberToCopy, target.length);
      if (numberToCopy > 0) {
         this.copyToInternal(target, sourceOffset, targetOffset, numberToCopy);
      }

   }

   protected abstract void copyToInternal(byte[] target, int sourceOffset, int targetOffset, int numberToCopy);

   public abstract void copyTo(ByteBuffer target);

   public final byte[] toByteArray() {
      int size = this.size();
      if (size == 0) {
         return Internal.EMPTY_BYTE_ARRAY;
      } else {
         byte[] result = new byte[size];
         this.copyToInternal(result, 0, 0, size);
         return result;
      }
   }

   public abstract void writeTo(OutputStream out) throws IOException;

   final void writeTo(OutputStream out, int sourceOffset, int numberToWrite) throws IOException {
      checkRange(sourceOffset, sourceOffset + numberToWrite, this.size());
      if (numberToWrite > 0) {
         this.writeToInternal(out, sourceOffset, numberToWrite);
      }

   }

   abstract void writeToInternal(OutputStream out, int sourceOffset, int numberToWrite) throws IOException;

   abstract void writeTo(ByteOutput byteOutput) throws IOException;

   abstract void writeToReverse(ByteOutput byteOutput) throws IOException;

   public abstract ByteBuffer asReadOnlyByteBuffer();

   public abstract List asReadOnlyByteBufferList();

   public final String toString(String charsetName) throws UnsupportedEncodingException {
      try {
         return this.toString(Charset.forName(charsetName));
      } catch (UnsupportedCharsetException e) {
         UnsupportedEncodingException exception = new UnsupportedEncodingException(charsetName);
         exception.initCause(e);
         throw exception;
      }
   }

   public final String toString(Charset charset) {
      return this.size() == 0 ? "" : this.toStringInternal(charset);
   }

   protected abstract String toStringInternal(Charset charset);

   public final String toStringUtf8() {
      return this.toString(Internal.UTF_8);
   }

   public abstract boolean isValidUtf8();

   protected abstract int partialIsValidUtf8(int state, int offset, int length);

   public abstract boolean equals(Object o);

   public final int hashCode() {
      int h = this.hash;
      if (h == 0) {
         int size = this.size();
         h = this.partialHash(size, 0, size);
         if (h == 0) {
            h = 1;
         }

         this.hash = h;
      }

      return h;
   }

   public abstract InputStream newInput();

   public abstract CodedInputStream newCodedInput();

   public static Output newOutput(int initialCapacity) {
      return new Output(initialCapacity);
   }

   public static Output newOutput() {
      return new Output(128);
   }

   static CodedBuilder newCodedBuilder(int size) {
      return new CodedBuilder(size);
   }

   protected abstract int getTreeDepth();

   protected abstract boolean isBalanced();

   protected final int peekCachedHashCode() {
      return this.hash;
   }

   protected abstract int partialHash(int h, int offset, int length);

   static void checkIndex(int index, int size) {
      if ((index | size - (index + 1)) < 0) {
         if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index < 0: " + index);
         } else {
            throw new ArrayIndexOutOfBoundsException("Index > length: " + index + ", " + size);
         }
      }
   }

   @CanIgnoreReturnValue
   static int checkRange(int startIndex, int endIndex, int size) {
      int length = endIndex - startIndex;
      if ((startIndex | endIndex | length | size - endIndex) < 0) {
         if (startIndex < 0) {
            throw new IndexOutOfBoundsException("Beginning index: " + startIndex + " < 0");
         } else if (endIndex < startIndex) {
            throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + startIndex + ", " + endIndex);
         } else {
            throw new IndexOutOfBoundsException("End index: " + endIndex + " >= " + size);
         }
      } else {
         return length;
      }
   }

   public final String toString() {
      return String.format(Locale.ROOT, "<ByteString@%s size=%d contents=\"%s\">", Integer.toHexString(System.identityHashCode(this)), this.size(), this.truncateAndEscapeForDisplay());
   }

   private String truncateAndEscapeForDisplay() {
      int limit = 50;
      return this.size() <= 50 ? TextFormatEscaper.escapeBytes(this) : TextFormatEscaper.escapeBytes(this.substring(0, 47)) + "...";
   }

   static {
      EMPTY = new LiteralByteString(Internal.EMPTY_BYTE_ARRAY);
      byteArrayCopier = (ByteArrayCopier)(Android.isOnAndroidDevice() ? new SystemByteArrayCopier() : new ArraysByteArrayCopier());
      UNSIGNED_LEXICOGRAPHICAL_COMPARATOR = new Comparator() {
         public int compare(ByteString former, ByteString latter) {
            ByteIterator formerBytes = former.iterator();
            ByteIterator latterBytes = latter.iterator();

            while(formerBytes.hasNext() && latterBytes.hasNext()) {
               int result = Integer.valueOf(ByteString.toInt(formerBytes.nextByte())).compareTo(ByteString.toInt(latterBytes.nextByte()));
               if (result != 0) {
                  return result;
               }
            }

            return Integer.valueOf(former.size()).compareTo(latter.size());
         }
      };
   }

   private static final class SystemByteArrayCopier implements ByteArrayCopier {
      private SystemByteArrayCopier() {
      }

      public byte[] copyFrom(byte[] bytes, int offset, int size) {
         byte[] copy = new byte[size];
         System.arraycopy(bytes, offset, copy, 0, size);
         return copy;
      }
   }

   private static final class ArraysByteArrayCopier implements ByteArrayCopier {
      private ArraysByteArrayCopier() {
      }

      public byte[] copyFrom(byte[] bytes, int offset, int size) {
         return Arrays.copyOfRange(bytes, offset, offset + size);
      }
   }

   abstract static class AbstractByteIterator implements ByteIterator {
      public final Byte next() {
         return this.nextByte();
      }

      public final void remove() {
         throw new UnsupportedOperationException();
      }
   }

   abstract static class LeafByteString extends ByteString {
      private static final long serialVersionUID = 1L;

      protected final int getTreeDepth() {
         return 0;
      }

      protected final boolean isBalanced() {
         return true;
      }

      void writeToReverse(ByteOutput byteOutput) throws IOException {
         this.writeTo(byteOutput);
      }

      abstract boolean equalsRange(ByteString other, int offset, int length);
   }

   public static final class Output extends OutputStream {
      private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
      private final int initialCapacity;
      private final ArrayList flushedBuffers;
      private int flushedBuffersTotalBytes;
      private byte[] buffer;
      private int bufferPos;

      Output(int initialCapacity) {
         if (initialCapacity < 0) {
            throw new IllegalArgumentException("Buffer size < 0");
         } else {
            this.initialCapacity = initialCapacity;
            this.flushedBuffers = new ArrayList();
            this.buffer = new byte[initialCapacity];
         }
      }

      public synchronized void write(int b) {
         if (this.bufferPos == this.buffer.length) {
            this.flushFullBuffer(1);
         }

         this.buffer[this.bufferPos++] = (byte)b;
      }

      public synchronized void write(byte[] b, int offset, int length) {
         if (length <= this.buffer.length - this.bufferPos) {
            System.arraycopy(b, offset, this.buffer, this.bufferPos, length);
            this.bufferPos += length;
         } else {
            int copySize = this.buffer.length - this.bufferPos;
            System.arraycopy(b, offset, this.buffer, this.bufferPos, copySize);
            offset += copySize;
            length -= copySize;
            this.flushFullBuffer(length);
            System.arraycopy(b, offset, this.buffer, 0, length);
            this.bufferPos = length;
         }

      }

      public synchronized ByteString toByteString() {
         this.flushLastBuffer();
         return ByteString.copyFrom((Iterable)this.flushedBuffers);
      }

      public void writeTo(OutputStream out) throws IOException {
         ByteString[] cachedFlushBuffers;
         byte[] cachedBuffer;
         int cachedBufferPos;
         synchronized(this) {
            cachedFlushBuffers = (ByteString[])this.flushedBuffers.toArray(new ByteString[0]);
            cachedBuffer = this.buffer;
            cachedBufferPos = this.bufferPos;
         }

         for(ByteString byteString : cachedFlushBuffers) {
            byteString.writeTo(out);
         }

         out.write(Arrays.copyOf(cachedBuffer, cachedBufferPos));
      }

      public synchronized int size() {
         return this.flushedBuffersTotalBytes + this.bufferPos;
      }

      public synchronized void reset() {
         this.flushedBuffers.clear();
         this.flushedBuffersTotalBytes = 0;
         this.bufferPos = 0;
      }

      public String toString() {
         return String.format("<ByteString.Output@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), this.size());
      }

      private void flushFullBuffer(int minSize) {
         this.flushedBuffers.add(new LiteralByteString(this.buffer));
         this.flushedBuffersTotalBytes += this.buffer.length;
         int newSize = Math.max(this.initialCapacity, Math.max(minSize, this.flushedBuffersTotalBytes >>> 1));
         this.buffer = new byte[newSize];
         this.bufferPos = 0;
      }

      private void flushLastBuffer() {
         if (this.bufferPos < this.buffer.length) {
            if (this.bufferPos > 0) {
               byte[] bufferCopy = Arrays.copyOf(this.buffer, this.bufferPos);
               this.flushedBuffers.add(new LiteralByteString(bufferCopy));
            }
         } else {
            this.flushedBuffers.add(new LiteralByteString(this.buffer));
            this.buffer = EMPTY_BYTE_ARRAY;
         }

         this.flushedBuffersTotalBytes += this.bufferPos;
         this.bufferPos = 0;
      }
   }

   static final class CodedBuilder {
      private final CodedOutputStream output;
      private final byte[] buffer;

      private CodedBuilder(int size) {
         this.buffer = new byte[size];
         this.output = CodedOutputStream.newInstance(this.buffer);
      }

      public ByteString build() {
         this.output.checkNoSpaceLeft();
         return new LiteralByteString(this.buffer);
      }

      public CodedOutputStream getCodedOutput() {
         return this.output;
      }
   }

   private static class LiteralByteString extends LeafByteString {
      private static final long serialVersionUID = 1L;
      protected final byte[] bytes;

      LiteralByteString(byte[] bytes) {
         if (bytes == null) {
            throw new NullPointerException();
         } else {
            this.bytes = bytes;
         }
      }

      public byte byteAt(int index) {
         return this.bytes[index];
      }

      byte internalByteAt(int index) {
         return this.bytes[index];
      }

      public int size() {
         return this.bytes.length;
      }

      public final ByteString substring(int beginIndex, int endIndex) {
         int length = checkRange(beginIndex, endIndex, this.size());
         return (ByteString)(length == 0 ? ByteString.EMPTY : new BoundedByteString(this.bytes, this.getOffsetIntoBytes() + beginIndex, length));
      }

      protected void copyToInternal(byte[] target, int sourceOffset, int targetOffset, int numberToCopy) {
         System.arraycopy(this.bytes, sourceOffset, target, targetOffset, numberToCopy);
      }

      public final void copyTo(ByteBuffer target) {
         target.put(this.bytes, this.getOffsetIntoBytes(), this.size());
      }

      public final ByteBuffer asReadOnlyByteBuffer() {
         return ByteBuffer.wrap(this.bytes, this.getOffsetIntoBytes(), this.size()).asReadOnlyBuffer();
      }

      public final List asReadOnlyByteBufferList() {
         return Collections.singletonList(this.asReadOnlyByteBuffer());
      }

      public final void writeTo(OutputStream outputStream) throws IOException {
         outputStream.write(this.toByteArray());
      }

      final void writeToInternal(OutputStream outputStream, int sourceOffset, int numberToWrite) throws IOException {
         outputStream.write(this.bytes, this.getOffsetIntoBytes() + sourceOffset, numberToWrite);
      }

      final void writeTo(ByteOutput output) throws IOException {
         output.writeLazy(this.bytes, this.getOffsetIntoBytes(), this.size());
      }

      protected final String toStringInternal(Charset charset) {
         return new String(this.bytes, this.getOffsetIntoBytes(), this.size(), charset);
      }

      public final boolean isValidUtf8() {
         int offset = this.getOffsetIntoBytes();
         return Utf8.isValidUtf8(this.bytes, offset, offset + this.size());
      }

      protected final int partialIsValidUtf8(int state, int offset, int length) {
         int index = this.getOffsetIntoBytes() + offset;
         return Utf8.partialIsValidUtf8(state, this.bytes, index, index + length);
      }

      public final boolean equals(Object other) {
         if (other == this) {
            return true;
         } else if (!(other instanceof ByteString)) {
            return false;
         } else if (this.size() != ((ByteString)other).size()) {
            return false;
         } else if (this.size() == 0) {
            return true;
         } else if (other instanceof LiteralByteString) {
            LiteralByteString otherAsLiteral = (LiteralByteString)other;
            int thisHash = this.peekCachedHashCode();
            int thatHash = otherAsLiteral.peekCachedHashCode();
            return thisHash != 0 && thatHash != 0 && thisHash != thatHash ? false : this.equalsRange((LiteralByteString)other, 0, this.size());
         } else {
            return other.equals(this);
         }
      }

      final boolean equalsRange(ByteString other, int offset, int length) {
         if (length > other.size()) {
            throw new IllegalArgumentException("Length too large: " + length + this.size());
         } else if (offset + length > other.size()) {
            throw new IllegalArgumentException("Ran off end of other: " + offset + ", " + length + ", " + other.size());
         } else if (other instanceof LiteralByteString) {
            LiteralByteString lbsOther = (LiteralByteString)other;
            byte[] thisBytes = this.bytes;
            byte[] otherBytes = lbsOther.bytes;
            int thisLimit = this.getOffsetIntoBytes() + length;
            int thisIndex = this.getOffsetIntoBytes();

            for(int otherIndex = lbsOther.getOffsetIntoBytes() + offset; thisIndex < thisLimit; ++otherIndex) {
               if (thisBytes[thisIndex] != otherBytes[otherIndex]) {
                  return false;
               }

               ++thisIndex;
            }

            return true;
         } else {
            return other.substring(offset, offset + length).equals(this.substring(0, length));
         }
      }

      protected final int partialHash(int h, int offset, int length) {
         return Internal.partialHash(h, this.bytes, this.getOffsetIntoBytes() + offset, length);
      }

      public final InputStream newInput() {
         return new ByteArrayInputStream(this.bytes, this.getOffsetIntoBytes(), this.size());
      }

      public final CodedInputStream newCodedInput() {
         return CodedInputStream.newInstance(this.bytes, this.getOffsetIntoBytes(), this.size(), true);
      }

      protected int getOffsetIntoBytes() {
         return 0;
      }
   }

   private static final class BoundedByteString extends LiteralByteString {
      private final int bytesOffset;
      private final int bytesLength;
      private static final long serialVersionUID = 1L;

      BoundedByteString(byte[] bytes, int offset, int length) {
         super(bytes);
         checkRange(offset, offset + length, bytes.length);
         this.bytesOffset = offset;
         this.bytesLength = length;
      }

      public byte byteAt(int index) {
         checkIndex(index, this.size());
         return this.bytes[this.bytesOffset + index];
      }

      byte internalByteAt(int index) {
         return this.bytes[this.bytesOffset + index];
      }

      public int size() {
         return this.bytesLength;
      }

      protected int getOffsetIntoBytes() {
         return this.bytesOffset;
      }

      protected void copyToInternal(byte[] target, int sourceOffset, int targetOffset, int numberToCopy) {
         System.arraycopy(this.bytes, this.getOffsetIntoBytes() + sourceOffset, target, targetOffset, numberToCopy);
      }

      Object writeReplace() {
         return ByteString.wrap(this.toByteArray());
      }

      private void readObject(ObjectInputStream in) throws IOException {
         throw new InvalidObjectException("BoundedByteStream instances are not to be serialized directly");
      }
   }

   private interface ByteArrayCopier {
      byte[] copyFrom(byte[] bytes, int offset, int size);
   }

   public interface ByteIterator extends Iterator {
      byte nextByte();
   }
}
