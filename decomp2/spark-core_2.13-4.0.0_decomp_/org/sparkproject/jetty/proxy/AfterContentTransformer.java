package org.sparkproject.jetty.proxy;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.IO;
import org.sparkproject.jetty.util.component.Destroyable;

public abstract class AfterContentTransformer implements AsyncMiddleManServlet.ContentTransformer, Destroyable {
   private static final Logger LOG = LoggerFactory.getLogger(AfterContentTransformer.class);
   private final List sourceBuffers = new ArrayList();
   private Path overflowDirectory = Paths.get(System.getProperty("java.io.tmpdir"));
   private String inputFilePrefix = "amms_adct_in_";
   private String outputFilePrefix = "amms_adct_out_";
   private long maxInputBufferSize = 1048576L;
   private long inputBufferSize;
   private FileChannel inputFile;
   private long maxOutputBufferSize;
   private long outputBufferSize;
   private FileChannel outputFile;

   public AfterContentTransformer() {
      this.maxOutputBufferSize = this.maxInputBufferSize;
   }

   public Path getOverflowDirectory() {
      return this.overflowDirectory;
   }

   public void setOverflowDirectory(Path overflowDirectory) {
      this.overflowDirectory = overflowDirectory;
   }

   public String getInputFilePrefix() {
      return this.inputFilePrefix;
   }

   public void setInputFilePrefix(String inputFilePrefix) {
      this.inputFilePrefix = inputFilePrefix;
   }

   public long getMaxInputBufferSize() {
      return this.maxInputBufferSize;
   }

   public void setMaxInputBufferSize(long maxInputBufferSize) {
      this.maxInputBufferSize = maxInputBufferSize;
   }

   public String getOutputFilePrefix() {
      return this.outputFilePrefix;
   }

   public void setOutputFilePrefix(String outputFilePrefix) {
      this.outputFilePrefix = outputFilePrefix;
   }

   public long getMaxOutputBufferSize() {
      return this.maxOutputBufferSize;
   }

   public void setMaxOutputBufferSize(long maxOutputBufferSize) {
      this.maxOutputBufferSize = maxOutputBufferSize;
   }

   public final void transform(ByteBuffer input, boolean finished, List output) throws IOException {
      int remaining = input.remaining();
      if (remaining > 0) {
         this.inputBufferSize += (long)remaining;
         long max = this.getMaxInputBufferSize();
         if (max >= 0L && this.inputBufferSize > max) {
            this.overflow(input);
         } else {
            ByteBuffer copy = ByteBuffer.allocate(input.remaining());
            copy.put(input).flip();
            this.sourceBuffers.add(copy);
         }
      }

      if (finished) {
         Source source = new Source();
         Sink sink = new Sink();
         if (this.transform(source, sink)) {
            sink.drainTo(output);
         } else {
            source.drainTo(output);
         }
      }

   }

   public abstract boolean transform(Source var1, Sink var2) throws IOException;

   private void overflow(ByteBuffer input) throws IOException {
      if (this.inputFile == null) {
         Path path = Files.createTempFile(this.getOverflowDirectory(), this.getInputFilePrefix(), (String)null);
         this.inputFile = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.DELETE_ON_CLOSE);
         int size = this.sourceBuffers.size();
         if (size > 0) {
            ByteBuffer[] buffers = (ByteBuffer[])this.sourceBuffers.toArray(new ByteBuffer[size]);
            this.sourceBuffers.clear();
            IO.write(this.inputFile, buffers, 0, buffers.length);
         }
      }

      this.inputFile.write(input);
   }

   public void destroy() {
      this.close(this.inputFile);
      this.close(this.outputFile);
   }

   private void drain(FileChannel file, List output) throws IOException {
      long position = 0L;
      long length = file.size();
      file.position(position);

      while(length > 0L) {
         long size = Math.min(1073741824L, length);
         ByteBuffer buffer = file.map(MapMode.READ_ONLY, position, size);
         output.add(buffer);
         position += size;
         length -= size;
      }

   }

   private void close(Closeable closeable) {
      try {
         if (closeable != null) {
            closeable.close();
         }
      } catch (IOException x) {
         LOG.trace("IGNORED", x);
      }

   }

   public class Source {
      private final InputStream stream;

      private Source() throws IOException {
         if (AfterContentTransformer.this.inputFile != null) {
            AfterContentTransformer.this.inputFile.force(true);
            this.stream = AfterContentTransformer.this.new ChannelInputStream();
         } else {
            this.stream = AfterContentTransformer.this.new MemoryInputStream();
         }

         this.stream.reset();
      }

      public InputStream getInputStream() {
         return this.stream;
      }

      private void drainTo(List output) throws IOException {
         if (AfterContentTransformer.this.inputFile == null) {
            output.addAll(AfterContentTransformer.this.sourceBuffers);
            AfterContentTransformer.this.sourceBuffers.clear();
         } else {
            AfterContentTransformer.this.drain(AfterContentTransformer.this.inputFile, output);
         }

      }
   }

   private class ChannelInputStream extends InputStream {
      private final InputStream stream;

      private ChannelInputStream() {
         this.stream = Channels.newInputStream(AfterContentTransformer.this.inputFile);
      }

      public int read(byte[] b, int off, int len) throws IOException {
         return this.stream.read(b, off, len);
      }

      public int read() throws IOException {
         return this.stream.read();
      }

      public void reset() throws IOException {
         AfterContentTransformer.this.inputFile.position(0L);
      }
   }

   private class MemoryInputStream extends InputStream {
      private final byte[] oneByte = new byte[1];
      private int index;
      private ByteBuffer slice;

      public int read(byte[] b, int off, int len) throws IOException {
         if (len == 0) {
            return 0;
         } else if (this.index == AfterContentTransformer.this.sourceBuffers.size()) {
            return -1;
         } else {
            if (this.slice == null) {
               this.slice = ((ByteBuffer)AfterContentTransformer.this.sourceBuffers.get(this.index)).slice();
            }

            int size = Math.min(len, this.slice.remaining());
            this.slice.get(b, off, size);
            if (!this.slice.hasRemaining()) {
               ++this.index;
               this.slice = null;
            }

            return size;
         }
      }

      public int read() throws IOException {
         int read = this.read(this.oneByte, 0, 1);
         return read < 0 ? read : this.oneByte[0] & 255;
      }

      public void reset() throws IOException {
         this.index = 0;
         this.slice = null;
      }
   }

   public class Sink {
      private final List sinkBuffers = new ArrayList();
      private final OutputStream stream = new SinkOutputStream();

      public OutputStream getOutputStream() {
         return this.stream;
      }

      private void overflow(ByteBuffer output) throws IOException {
         if (AfterContentTransformer.this.outputFile == null) {
            Path path = Files.createTempFile(AfterContentTransformer.this.getOverflowDirectory(), AfterContentTransformer.this.getOutputFilePrefix(), (String)null);
            AfterContentTransformer.this.outputFile = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.DELETE_ON_CLOSE);
            int size = this.sinkBuffers.size();
            if (size > 0) {
               ByteBuffer[] buffers = (ByteBuffer[])this.sinkBuffers.toArray(new ByteBuffer[size]);
               this.sinkBuffers.clear();
               IO.write(AfterContentTransformer.this.outputFile, buffers, 0, buffers.length);
            }
         }

         AfterContentTransformer.this.outputFile.write(output);
      }

      private void drainTo(List output) throws IOException {
         if (AfterContentTransformer.this.outputFile == null) {
            output.addAll(this.sinkBuffers);
            this.sinkBuffers.clear();
         } else {
            AfterContentTransformer.this.outputFile.force(true);
            AfterContentTransformer.this.drain(AfterContentTransformer.this.outputFile, output);
         }

      }

      private class SinkOutputStream extends OutputStream {
         public void write(byte[] b, int off, int len) throws IOException {
            if (len > 0) {
               AfterContentTransformer var10000 = AfterContentTransformer.this;
               var10000.outputBufferSize += (long)len;
               long max = AfterContentTransformer.this.getMaxOutputBufferSize();
               if (max >= 0L && AfterContentTransformer.this.outputBufferSize > max) {
                  Sink.this.overflow(ByteBuffer.wrap(b, off, len));
               } else {
                  byte[] copy = new byte[len];
                  System.arraycopy(b, off, copy, 0, len);
                  Sink.this.sinkBuffers.add(ByteBuffer.wrap(copy));
               }

            }
         }

         public void write(int b) throws IOException {
            this.write(new byte[]{(byte)b}, 0, 1);
         }
      }
   }
}
