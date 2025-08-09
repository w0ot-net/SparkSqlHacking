package org.sparkproject.jetty.util;

public class Utf8StringBuilder extends Utf8Appendable {
   final StringBuilder _buffer;

   public Utf8StringBuilder() {
      super(new StringBuilder());
      this._buffer = (StringBuilder)this._appendable;
   }

   public Utf8StringBuilder(int capacity) {
      super(new StringBuilder(capacity));
      this._buffer = (StringBuilder)this._appendable;
   }

   public int length() {
      return this._buffer.length();
   }

   public void reset() {
      super.reset();
      this._buffer.setLength(0);
   }

   public String getPartialString() {
      return this._buffer.toString();
   }

   public StringBuilder getStringBuilder() {
      this.checkState();
      return this._buffer;
   }

   public String toString() {
      this.checkState();
      return this._buffer.toString();
   }
}
