package org.apache.logging.log4j.layout.template.json.util;

import java.io.PrintWriter;

public final class TruncatingBufferedPrintWriter extends PrintWriter implements CharSequence {
   private final TruncatingBufferedWriter writer;

   private TruncatingBufferedPrintWriter(final TruncatingBufferedWriter writer) {
      super(writer, false);
      this.writer = writer;
   }

   public static TruncatingBufferedPrintWriter ofCapacity(final int capacity) {
      if (capacity < 0) {
         throw new IllegalArgumentException("was expecting a non-negative capacity: " + capacity);
      } else {
         TruncatingBufferedWriter writer = new TruncatingBufferedWriter(capacity);
         return new TruncatingBufferedPrintWriter(writer);
      }
   }

   public char[] buffer() {
      return this.writer.buffer();
   }

   public int position() {
      return this.writer.position();
   }

   public void position(final int index) {
      this.writer.position(index);
   }

   public int capacity() {
      return this.writer.capacity();
   }

   public boolean truncated() {
      return this.writer.truncated();
   }

   public int length() {
      return this.writer.length();
   }

   public char charAt(final int index) {
      return this.writer.charAt(index);
   }

   public PrintWriter append(final CharSequence seq) {
      this.writer.append(seq);
      return this;
   }

   public PrintWriter append(final CharSequence seq, final int startIndex, final int endIndex) {
      this.writer.append(seq, startIndex, endIndex);
      return this;
   }

   public CharSequence subSequence(final int startIndex, final int endIndex) {
      return this.writer.subSequence(startIndex, endIndex);
   }

   public void close() {
      this.writer.close();
   }

   public String toString() {
      return this.writer.toString();
   }
}
