package org.sparkproject.jetty.io;

import java.io.OutputStream;
import java.nio.ByteBuffer;
import org.sparkproject.jetty.util.BufferUtil;

public class ByteBufferOutputStream extends OutputStream {
   final ByteBuffer _buffer;

   public ByteBufferOutputStream(ByteBuffer buffer) {
      this._buffer = buffer;
   }

   public void close() {
   }

   public void flush() {
   }

   public void write(byte[] b) {
      this.write(b, 0, b.length);
   }

   public void write(byte[] b, int off, int len) {
      BufferUtil.append(this._buffer, b, off, len);
   }

   public void write(int b) {
      BufferUtil.append(this._buffer, (byte)b);
   }
}
