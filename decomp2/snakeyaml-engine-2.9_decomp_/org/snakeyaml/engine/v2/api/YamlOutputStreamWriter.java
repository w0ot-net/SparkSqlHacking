package org.snakeyaml.engine.v2.api;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

public abstract class YamlOutputStreamWriter extends OutputStreamWriter implements StreamDataWriter {
   public YamlOutputStreamWriter(OutputStream out, Charset cs) {
      super(out, cs);
   }

   public abstract void processIOException(IOException var1);

   public void flush() {
      try {
         super.flush();
      } catch (IOException e) {
         this.processIOException(e);
      }

   }

   public void write(String str, int off, int len) {
      try {
         super.write(str, off, len);
      } catch (IOException e) {
         this.processIOException(e);
      }

   }

   public void write(String str) {
      try {
         super.write(str);
      } catch (IOException e) {
         this.processIOException(e);
      }

   }
}
