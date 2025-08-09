package com.univocity.parsers.common;

import java.io.Reader;

class LineReader extends Reader {
   private String line;
   private int length;
   private int next = 0;

   public LineReader() {
   }

   public void setLine(String line) {
      this.line = line;
      this.length = line.length();
      this.next = 0;
   }

   public int read(char[] cbuf, int off, int len) {
      if (len == 0) {
         return 0;
      } else if (this.next >= this.length) {
         return -1;
      } else {
         int read = Math.min(this.length - this.next, len);
         this.line.getChars(this.next, this.next + read, cbuf, off);
         this.next += read;
         return read;
      }
   }

   public long skip(long ns) {
      return 0L;
   }

   public boolean ready() {
      return this.line != null;
   }

   public boolean markSupported() {
      return false;
   }

   public void close() {
      this.line = null;
   }
}
