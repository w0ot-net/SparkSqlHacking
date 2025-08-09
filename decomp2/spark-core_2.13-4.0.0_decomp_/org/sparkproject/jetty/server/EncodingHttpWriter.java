package org.sparkproject.jetty.server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class EncodingHttpWriter extends HttpWriter {
   final Writer _converter;

   public EncodingHttpWriter(HttpOutput out, String encoding) {
      super(out);

      try {
         this._converter = new OutputStreamWriter(this._bytes, encoding);
      } catch (UnsupportedEncodingException e) {
         throw new RuntimeException(e);
      }
   }

   public void write(char[] s, int offset, int length) throws IOException {
      int chars;
      for(HttpOutput out = this._out; length > 0; offset += chars) {
         this._bytes.reset();
         chars = Math.min(length, 512);
         this._converter.write(s, offset, chars);
         this._converter.flush();
         this._bytes.writeTo(out);
         length -= chars;
      }

   }
}
