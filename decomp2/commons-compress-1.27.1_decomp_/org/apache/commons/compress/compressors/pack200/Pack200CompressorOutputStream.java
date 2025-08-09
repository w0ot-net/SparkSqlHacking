package org.apache.commons.compress.compressors.pack200;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.jar.JarInputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.java.util.jar.Pack200;

public class Pack200CompressorOutputStream extends CompressorOutputStream {
   private boolean finished;
   private final AbstractStreamBridge abstractStreamBridge;
   private final Map properties;

   public Pack200CompressorOutputStream(OutputStream out) throws IOException {
      this(out, Pack200Strategy.IN_MEMORY);
   }

   public Pack200CompressorOutputStream(OutputStream out, Map props) throws IOException {
      this(out, Pack200Strategy.IN_MEMORY, props);
   }

   public Pack200CompressorOutputStream(OutputStream out, Pack200Strategy mode) throws IOException {
      this(out, mode, (Map)null);
   }

   public Pack200CompressorOutputStream(OutputStream out, Pack200Strategy mode, Map props) throws IOException {
      super(out);
      this.abstractStreamBridge = mode.newStreamBridge();
      this.properties = props;
   }

   public void close() throws IOException {
      try {
         this.finish();
      } finally {
         try {
            this.abstractStreamBridge.stop();
         } finally {
            this.out.close();
         }
      }

   }

   public void finish() throws IOException {
      if (!this.finished) {
         this.finished = true;
         Pack200.Packer p = Pack200.newPacker();
         if (this.properties != null) {
            p.properties().putAll(this.properties);
         }

         JarInputStream ji = new JarInputStream(this.abstractStreamBridge.getInputStream());

         try {
            p.pack(ji, this.out);
         } catch (Throwable var6) {
            try {
               ji.close();
            } catch (Throwable var5) {
               var6.addSuppressed(var5);
            }

            throw var6;
         }

         ji.close();
      }

   }

   public void write(byte[] b) throws IOException {
      this.abstractStreamBridge.write(b);
   }

   public void write(byte[] b, int from, int length) throws IOException {
      this.abstractStreamBridge.write(b, from, length);
   }

   public void write(int b) throws IOException {
      this.abstractStreamBridge.write(b);
   }
}
