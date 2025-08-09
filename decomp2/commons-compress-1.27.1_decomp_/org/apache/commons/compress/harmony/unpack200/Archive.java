package org.apache.commons.compress.harmony.unpack200;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.zip.GZIPInputStream;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BoundedInputStream;

public class Archive {
   private static final int[] MAGIC = new int[]{202, 254, 208, 13};
   private BoundedInputStream inputStream;
   private final JarOutputStream outputStream;
   private boolean removePackFile;
   private int logLevel = 1;
   private FileOutputStream logFile;
   private boolean overrideDeflateHint;
   private boolean deflateHint;
   private final Path inputPath;
   private final long inputSize;
   private final String outputFileName;
   private final boolean closeStreams;

   public Archive(InputStream inputStream, JarOutputStream outputStream) throws IOException {
      this.inputStream = Pack200UnpackerAdapter.newBoundedInputStream(inputStream);
      this.outputStream = outputStream;
      if (inputStream instanceof FileInputStream) {
         this.inputPath = Paths.get(Pack200UnpackerAdapter.readPathString((FileInputStream)inputStream));
      } else {
         this.inputPath = null;
      }

      this.outputFileName = null;
      this.inputSize = -1L;
      this.closeStreams = false;
   }

   public Archive(String inputFileName, String outputFileName) throws FileNotFoundException, IOException {
      this.inputPath = Paths.get(inputFileName);
      this.inputSize = Files.size(this.inputPath);
      this.inputStream = new BoundedInputStream(Files.newInputStream(this.inputPath), this.inputSize);
      this.outputStream = new JarOutputStream(new BufferedOutputStream(new FileOutputStream(outputFileName)));
      this.outputFileName = outputFileName;
      this.closeStreams = true;
   }

   private boolean available(InputStream inputStream) throws IOException {
      inputStream.mark(1);
      int check = inputStream.read();
      inputStream.reset();
      return check != -1;
   }

   public void setDeflateHint(boolean deflateHint) {
      this.overrideDeflateHint = true;
      this.deflateHint = deflateHint;
   }

   public void setLogFile(String logFileName) throws FileNotFoundException {
      this.logFile = new FileOutputStream(logFileName);
   }

   public void setLogFile(String logFileName, boolean append) throws FileNotFoundException {
      this.logFile = new FileOutputStream(logFileName, append);
   }

   public void setQuiet(boolean quiet) {
      if (quiet || this.logLevel == 0) {
         this.logLevel = 0;
      }

   }

   public void setRemovePackFile(boolean removePackFile) {
      this.removePackFile = removePackFile;
   }

   public void setVerbose(boolean verbose) {
      if (verbose) {
         this.logLevel = 2;
      } else if (this.logLevel == 2) {
         this.logLevel = 1;
      }

   }

   public void unpack() throws Pack200Exception, IOException {
      this.outputStream.setComment("PACK200");

      try {
         if (!this.inputStream.markSupported()) {
            this.inputStream = new BoundedInputStream(new BufferedInputStream(this.inputStream));
            if (!this.inputStream.markSupported()) {
               throw new IllegalStateException();
            }
         }

         this.inputStream.mark(2);
         if ((this.inputStream.read() & 255 | (this.inputStream.read() & 255) << 8) == 35615) {
            this.inputStream.reset();
            this.inputStream = new BoundedInputStream(new BufferedInputStream(new GZIPInputStream(this.inputStream)));
         } else {
            this.inputStream.reset();
         }

         this.inputStream.mark(MAGIC.length);
         int[] word = new int[MAGIC.length];

         for(int i = 0; i < word.length; ++i) {
            word[i] = this.inputStream.read();
         }

         boolean compressedWithE0 = false;

         for(int m = 0; m < MAGIC.length; ++m) {
            if (word[m] != MAGIC[m]) {
               compressedWithE0 = true;
               break;
            }
         }

         this.inputStream.reset();
         if (compressedWithE0) {
            JarInputStream jarInputStream = new JarInputStream(this.inputStream);

            JarEntry jarEntry;
            while((jarEntry = jarInputStream.getNextJarEntry()) != null) {
               this.outputStream.putNextEntry(jarEntry);
               byte[] bytes = new byte[16384];

               for(int bytesRead = jarInputStream.read(bytes); bytesRead != -1; bytesRead = jarInputStream.read(bytes)) {
                  this.outputStream.write(bytes, 0, bytesRead);
               }

               this.outputStream.closeEntry();
            }
         } else {
            int i = 0;

            while(this.available(this.inputStream)) {
               ++i;
               Segment segment = new Segment();
               segment.setLogLevel(this.logLevel);
               segment.setLogStream((OutputStream)(this.logFile != null ? this.logFile : System.out));
               segment.setPreRead(false);
               if (i == 1) {
                  segment.log(2, "Unpacking from " + this.inputPath + " to " + this.outputFileName);
               }

               segment.log(2, "Reading segment " + i);
               if (this.overrideDeflateHint) {
                  segment.overrideDeflateHint(this.deflateHint);
               }

               segment.unpack(this.inputStream, this.outputStream);
               this.outputStream.flush();
            }
         }
      } finally {
         if (this.closeStreams) {
            IOUtils.closeQuietly(this.inputStream);
            IOUtils.closeQuietly(this.outputStream);
         }

         IOUtils.closeQuietly(this.logFile);
      }

      if (this.removePackFile && this.inputPath != null) {
         Files.delete(this.inputPath);
      }

   }
}
