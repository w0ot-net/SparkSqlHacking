package org.apache.logging.log4j.layout.template.json.util;

import java.io.Writer;
import java.util.Objects;
import java.util.stream.IntStream;

final class TruncatingBufferedWriter extends Writer implements CharSequence {
   private final char[] buffer;
   private int position;
   private boolean truncated;

   TruncatingBufferedWriter(final int capacity) {
      this.buffer = new char[capacity];
      this.position = 0;
      this.truncated = false;
   }

   char[] buffer() {
      return this.buffer;
   }

   int position() {
      return this.position;
   }

   void position(final int index) {
      if (index >= 0 && index < this.buffer.length) {
         this.position = index;
      } else {
         throw new IllegalArgumentException("invalid index: " + index);
      }
   }

   int capacity() {
      return this.buffer.length;
   }

   boolean truncated() {
      return this.truncated;
   }

   public void write(final int c) {
      if (this.position < this.buffer.length) {
         this.buffer[this.position++] = (char)c;
      } else {
         this.truncated = true;
      }

   }

   public void write(final char[] source) {
      Objects.requireNonNull(source, "source");
      this.write((char[])source, 0, source.length);
   }

   public void write(final char[] source, final int offset, final int length) {
      Objects.requireNonNull(source, "source");
      if (source.length != 0) {
         if (offset >= 0 && offset < source.length) {
            if (length >= 0 && Math.addExact(offset, length) <= source.length) {
               int maxLength = this.buffer.length - this.position;
               if (length < maxLength) {
                  System.arraycopy(source, offset, this.buffer, this.position, length);
                  this.position += length;
               } else if (maxLength > 0) {
                  System.arraycopy(source, offset, this.buffer, this.position, maxLength);
                  this.position += maxLength;
                  this.truncated = true;
               }

            } else {
               throw new IndexOutOfBoundsException("invalid length: " + length);
            }
         } else {
            throw new IndexOutOfBoundsException("invalid offset: " + offset);
         }
      }
   }

   public void write(final String string) {
      Objects.requireNonNull(string, "string");
      int length = string.length();
      int maxLength = this.buffer.length - this.position;
      if (length < maxLength) {
         string.getChars(0, length, this.buffer, this.position);
         this.position += length;
      } else if (maxLength > 0) {
         string.getChars(0, maxLength, this.buffer, this.position);
         this.position += maxLength;
         this.truncated = true;
      }

   }

   public void write(final String string, final int offset, final int length) {
      Objects.requireNonNull(string, "string");
      if (!string.isEmpty()) {
         if (offset >= 0 && offset < string.length()) {
            if (length >= 0 && Math.addExact(offset, length) <= string.length()) {
               int maxLength = this.buffer.length - this.position;
               if (length < maxLength) {
                  string.getChars(offset, offset + length, this.buffer, this.position);
                  this.position += length;
               } else if (maxLength > 0) {
                  string.getChars(offset, offset + maxLength, this.buffer, this.position);
                  this.position += maxLength;
                  this.truncated = true;
               }

            } else {
               throw new IndexOutOfBoundsException("invalid length: " + length);
            }
         } else {
            throw new IndexOutOfBoundsException("invalid offset: " + offset);
         }
      }
   }

   public Writer append(final char c) {
      this.write(c);
      return this;
   }

   public Writer append(final CharSequence seq) {
      return seq == null ? this.append("null", 0, 4) : this.append(seq, 0, seq.length());
   }

   public Writer append(final CharSequence seq, final int start, final int end) {
      if (seq == null) {
         this.write("null");
         return this;
      } else if (seq.length() == 0) {
         return this;
      } else if (start >= 0 && start < seq.length()) {
         if (end >= start && end <= seq.length()) {
            int length = end - start;
            int maxLength = this.buffer.length - this.position;
            if (length < maxLength) {
               for(int i = start; i < end; ++i) {
                  char c = seq.charAt(i);
                  this.buffer[this.position++] = c;
               }
            } else if (maxLength > 0) {
               int truncatedEnd = start + maxLength;

               for(int i = start; i < truncatedEnd; ++i) {
                  char c = seq.charAt(i);
                  this.buffer[this.position++] = c;
               }

               this.truncated = true;
            }

            return this;
         } else {
            throw new IndexOutOfBoundsException("invalid end: " + end);
         }
      } else {
         throw new IndexOutOfBoundsException("invalid start: " + start);
      }
   }

   public int length() {
      return this.position;
   }

   public char charAt(final int index) {
      return this.buffer[index];
   }

   public String subSequence(final int startIndex, final int endIndex) {
      throw new UnsupportedOperationException("operation requires allocation, contradicting with the purpose of the class");
   }

   public IntStream chars() {
      throw new UnsupportedOperationException("operation requires allocation, contradicting with the purpose of the class");
   }

   public IntStream codePoints() {
      throw new UnsupportedOperationException("operation requires allocation, contradicting with the purpose of the class");
   }

   public void flush() {
   }

   public void close() {
      this.position = 0;
      this.truncated = false;
   }

   public String toString() {
      return new String(this.buffer, 0, this.position);
   }
}
