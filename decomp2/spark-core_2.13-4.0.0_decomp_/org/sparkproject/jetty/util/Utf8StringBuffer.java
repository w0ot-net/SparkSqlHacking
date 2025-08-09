package org.sparkproject.jetty.util;

public class Utf8StringBuffer extends Utf8Appendable {
   final StringBuffer _buffer;

   public Utf8StringBuffer() {
      super(new StringBuffer());
      this._buffer = (StringBuffer)this._appendable;
   }

   public Utf8StringBuffer(int capacity) {
      super(new StringBuffer(capacity));
      this._buffer = (StringBuffer)this._appendable;
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

   public StringBuffer getStringBuffer() {
      this.checkState();
      return this._buffer;
   }

   public String toString() {
      this.checkState();
      return this._buffer.toString();
   }
}
