package org.sparkproject.jpmml.model;

import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import org.sparkproject.dmg.pmml.Version;

public class PMMLOutputStream extends FilterOutputStream {
   private Version version = null;
   private ByteArrayOutputStream buffer = new ByteArrayOutputStream(1024);

   public PMMLOutputStream(OutputStream os, Version version) {
      super(os);
      this.version = (Version)Objects.requireNonNull(version);
      if (!version.isStandard()) {
         throw new IllegalArgumentException();
      }
   }

   public void write(byte[] bytes) throws IOException {
      this.write(bytes, 0, bytes.length);
   }

   public void write(byte[] bytes, int offset, int length) throws IOException {
      if (this.buffer != null) {
         int i = offset;

         for(int max = offset + length; i < max; ++i) {
            this.write(bytes[i]);
         }
      } else {
         super.out.write(bytes, offset, length);
      }

   }

   public void write(int b) throws IOException {
      if (this.buffer != null) {
         this.buffer.write(b);
         if (b == 62) {
            String string = this.buffer.toString("UTF-8");
            if (string.endsWith("?>")) {
               super.out.write(string.getBytes("UTF-8"));
               this.buffer.reset();
            } else {
               if (!string.endsWith(">")) {
                  throw new IllegalStateException();
               }

               string = string.replace(Version.PMML_4_4.getNamespaceURI(), this.version.getNamespaceURI());
               super.out.write(string.getBytes("UTF-8"));
               this.buffer = null;
            }
         }
      } else {
         super.out.write(b);
      }

   }

   public void flush() throws IOException {
      if (this.buffer != null) {
         throw new IllegalStateException();
      } else {
         super.flush();
      }
   }

   public void close() throws IOException {
      super.close();
   }
}
