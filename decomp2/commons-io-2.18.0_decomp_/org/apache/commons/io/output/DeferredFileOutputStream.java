package org.apache.commons.io.output;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Supplier;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.file.PathUtils;

public class DeferredFileOutputStream extends ThresholdingOutputStream {
   private ByteArrayOutputStream memoryOutputStream;
   private OutputStream currentOutputStream;
   private Path outputPath;
   private final String prefix;
   private final String suffix;
   private final Path directory;
   private boolean closed;

   public static Builder builder() {
      return new Builder();
   }

   private static int checkBufferSize(int initialBufferSize) {
      if (initialBufferSize < 0) {
         throw new IllegalArgumentException("Initial buffer size must be at least 0.");
      } else {
         return initialBufferSize;
      }
   }

   private static Path toPath(File file, Supplier defaultPathSupplier) {
      return file != null ? file.toPath() : (defaultPathSupplier == null ? null : (Path)defaultPathSupplier.get());
   }

   private static Path toPath(Path file, Supplier defaultPathSupplier) {
      return file != null ? file : (defaultPathSupplier == null ? null : (Path)defaultPathSupplier.get());
   }

   /** @deprecated */
   @Deprecated
   public DeferredFileOutputStream(int threshold, File outputFile) {
      this(threshold, (File)outputFile, (String)null, (String)null, (File)null, 1024);
   }

   private DeferredFileOutputStream(int threshold, File outputFile, String prefix, String suffix, File directory, int initialBufferSize) {
      super(threshold);
      this.outputPath = toPath((File)outputFile, (Supplier)null);
      this.prefix = prefix;
      this.suffix = suffix;
      this.directory = toPath(directory, PathUtils::getTempDirectory);
      this.memoryOutputStream = new ByteArrayOutputStream(checkBufferSize(initialBufferSize));
      this.currentOutputStream = this.memoryOutputStream;
   }

   /** @deprecated */
   @Deprecated
   public DeferredFileOutputStream(int threshold, int initialBufferSize, File outputFile) {
      this(threshold, (File)outputFile, (String)null, (String)null, (File)null, initialBufferSize);
   }

   /** @deprecated */
   @Deprecated
   public DeferredFileOutputStream(int threshold, int initialBufferSize, String prefix, String suffix, File directory) {
      this(threshold, (File)null, (String)Objects.requireNonNull(prefix, "prefix"), suffix, (File)directory, initialBufferSize);
   }

   private DeferredFileOutputStream(int threshold, Path outputFile, String prefix, String suffix, Path directory, int initialBufferSize) {
      super(threshold);
      this.outputPath = toPath((Path)outputFile, (Supplier)null);
      this.prefix = prefix;
      this.suffix = suffix;
      this.directory = toPath(directory, PathUtils::getTempDirectory);
      this.memoryOutputStream = new ByteArrayOutputStream(checkBufferSize(initialBufferSize));
      this.currentOutputStream = this.memoryOutputStream;
   }

   /** @deprecated */
   @Deprecated
   public DeferredFileOutputStream(int threshold, String prefix, String suffix, File directory) {
      this(threshold, (File)null, (String)Objects.requireNonNull(prefix, "prefix"), suffix, (File)directory, 1024);
   }

   public void close() throws IOException {
      super.close();
      this.closed = true;
   }

   public byte[] getData() {
      return this.memoryOutputStream != null ? this.memoryOutputStream.toByteArray() : null;
   }

   public File getFile() {
      return this.outputPath != null ? this.outputPath.toFile() : null;
   }

   public Path getPath() {
      return this.outputPath;
   }

   protected OutputStream getStream() throws IOException {
      return this.currentOutputStream;
   }

   public boolean isInMemory() {
      return !this.isThresholdExceeded();
   }

   protected void thresholdReached() throws IOException {
      if (this.prefix != null) {
         this.outputPath = Files.createTempFile(this.directory, this.prefix, this.suffix);
      }

      PathUtils.createParentDirectories(this.outputPath, (LinkOption)null, PathUtils.EMPTY_FILE_ATTRIBUTE_ARRAY);
      OutputStream fos = Files.newOutputStream(this.outputPath);

      try {
         this.memoryOutputStream.writeTo(fos);
      } catch (IOException e) {
         fos.close();
         throw e;
      }

      this.currentOutputStream = fos;
      this.memoryOutputStream = null;
   }

   public InputStream toInputStream() throws IOException {
      if (!this.closed) {
         throw new IOException("Stream not closed");
      } else {
         return this.isInMemory() ? this.memoryOutputStream.toInputStream() : Files.newInputStream(this.outputPath);
      }
   }

   public void writeTo(OutputStream outputStream) throws IOException {
      if (!this.closed) {
         throw new IOException("Stream not closed");
      } else {
         if (this.isInMemory()) {
            this.memoryOutputStream.writeTo(outputStream);
         } else {
            Files.copy(this.outputPath, outputStream);
         }

      }
   }

   public static class Builder extends AbstractStreamBuilder {
      private int threshold;
      private Path outputFile;
      private String prefix;
      private String suffix;
      private Path directory;

      public Builder() {
         this.setBufferSizeDefault(1024);
         this.setBufferSize(1024);
      }

      public DeferredFileOutputStream get() {
         return new DeferredFileOutputStream(this.threshold, this.outputFile, this.prefix, this.suffix, this.directory, this.getBufferSize());
      }

      public Builder setDirectory(File directory) {
         this.directory = DeferredFileOutputStream.toPath((File)directory, (Supplier)null);
         return this;
      }

      public Builder setDirectory(Path directory) {
         this.directory = DeferredFileOutputStream.toPath((Path)directory, (Supplier)null);
         return this;
      }

      public Builder setOutputFile(File outputFile) {
         this.outputFile = DeferredFileOutputStream.toPath((File)outputFile, (Supplier)null);
         return this;
      }

      public Builder setOutputFile(Path outputFile) {
         this.outputFile = DeferredFileOutputStream.toPath((Path)outputFile, (Supplier)null);
         return this;
      }

      public Builder setPrefix(String prefix) {
         this.prefix = prefix;
         return this;
      }

      public Builder setSuffix(String suffix) {
         this.suffix = suffix;
         return this;
      }

      public Builder setThreshold(int threshold) {
         this.threshold = threshold;
         return this;
      }
   }
}
