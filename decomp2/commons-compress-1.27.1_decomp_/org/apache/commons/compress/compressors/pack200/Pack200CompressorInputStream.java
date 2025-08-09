package org.apache.commons.compress.compressors.pack200;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Map;
import java.util.jar.JarOutputStream;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.java.util.jar.Pack200;
import org.apache.commons.io.IOUtils;

public class Pack200CompressorInputStream extends CompressorInputStream {
   private static final byte[] CAFE_DOOD = new byte[]{-54, -2, -48, 13};
   private static final int SIG_LENGTH;
   private final InputStream originalInputStream;
   private final AbstractStreamBridge abstractStreamBridge;

   public static boolean matches(byte[] signature, int length) {
      if (length < SIG_LENGTH) {
         return false;
      } else {
         for(int i = 0; i < SIG_LENGTH; ++i) {
            if (signature[i] != CAFE_DOOD[i]) {
               return false;
            }
         }

         return true;
      }
   }

   public Pack200CompressorInputStream(File file) throws IOException {
      this(file, Pack200Strategy.IN_MEMORY);
   }

   public Pack200CompressorInputStream(File file, Map properties) throws IOException {
      this(file, Pack200Strategy.IN_MEMORY, properties);
   }

   public Pack200CompressorInputStream(File file, Pack200Strategy mode) throws IOException {
      this((InputStream)null, file, mode, (Map)null);
   }

   public Pack200CompressorInputStream(File file, Pack200Strategy mode, Map properties) throws IOException {
      this((InputStream)null, file, mode, properties);
   }

   public Pack200CompressorInputStream(InputStream inputStream) throws IOException {
      this(inputStream, Pack200Strategy.IN_MEMORY);
   }

   private Pack200CompressorInputStream(InputStream inputStream, File file, Pack200Strategy mode, Map properties) throws IOException {
      this.originalInputStream = inputStream;
      this.abstractStreamBridge = mode.newStreamBridge();
      JarOutputStream jarOut = new JarOutputStream(this.abstractStreamBridge);

      try {
         Pack200.Unpacker unpacker = Pack200.newUnpacker();
         if (properties != null) {
            unpacker.properties().putAll(properties);
         }

         if (file == null) {
            unpacker.unpack(inputStream, jarOut);
         } else {
            unpacker.unpack(file, jarOut);
         }
      } catch (Throwable var9) {
         try {
            jarOut.close();
         } catch (Throwable var8) {
            var9.addSuppressed(var8);
         }

         throw var9;
      }

      jarOut.close();
   }

   public Pack200CompressorInputStream(InputStream inputStream, Map properties) throws IOException {
      this(inputStream, Pack200Strategy.IN_MEMORY, properties);
   }

   public Pack200CompressorInputStream(InputStream inputStream, Pack200Strategy mode) throws IOException {
      this(inputStream, (File)null, mode, (Map)null);
   }

   public Pack200CompressorInputStream(InputStream inputStream, Pack200Strategy mode, Map properties) throws IOException {
      this(inputStream, (File)null, mode, properties);
   }

   public int available() throws IOException {
      return this.getInputStream().available();
   }

   public void close() throws IOException {
      try {
         this.abstractStreamBridge.stop();
      } finally {
         if (this.originalInputStream != null) {
            this.originalInputStream.close();
         }

      }

   }

   private InputStream getInputStream() throws IOException {
      return this.abstractStreamBridge.getInputStream();
   }

   public synchronized void mark(int limit) {
      try {
         this.getInputStream().mark(limit);
      } catch (IOException ex) {
         throw new UncheckedIOException(ex);
      }
   }

   public boolean markSupported() {
      try {
         return this.getInputStream().markSupported();
      } catch (IOException var2) {
         return false;
      }
   }

   public int read() throws IOException {
      return this.getInputStream().read();
   }

   public int read(byte[] b) throws IOException {
      return this.getInputStream().read(b);
   }

   public int read(byte[] b, int off, int count) throws IOException {
      return this.getInputStream().read(b, off, count);
   }

   public synchronized void reset() throws IOException {
      this.getInputStream().reset();
   }

   public long skip(long count) throws IOException {
      return IOUtils.skip(this.getInputStream(), count);
   }

   static {
      SIG_LENGTH = CAFE_DOOD.length;
   }
}
