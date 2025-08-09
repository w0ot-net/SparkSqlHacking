package org.sparkproject.jetty.server;

import java.io.IOException;
import java.io.Writer;
import org.sparkproject.jetty.util.ByteArrayOutputStream2;
import org.sparkproject.jetty.util.Callback;

public abstract class HttpWriter extends Writer {
   public static final int MAX_OUTPUT_CHARS = 512;
   final HttpOutput _out;
   final ByteArrayOutputStream2 _bytes;
   final char[] _chars;

   public HttpWriter(HttpOutput out) {
      this._out = out;
      this._chars = new char[512];
      this._bytes = new ByteArrayOutputStream2(512);
   }

   public void close() throws IOException {
      this._out.close();
   }

   public void complete(Callback callback) {
      this._out.complete(callback);
   }

   public void flush() throws IOException {
      this._out.flush();
   }

   public void write(String s, int offset, int length) throws IOException {
      while(length > 512) {
         this.write((String)s, offset, 512);
         offset += 512;
         length -= 512;
      }

      s.getChars(offset, offset + length, this._chars, 0);
      this.write((char[])this._chars, 0, length);
   }

   public void write(char[] s, int offset, int length) throws IOException {
      throw new AbstractMethodError();
   }
}
