package org.apache.commons.io.build;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.spi.FileSystemProvider;
import java.util.Arrays;
import java.util.Objects;
import org.apache.commons.io.IORandomAccessFile;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.RandomAccessFileMode;
import org.apache.commons.io.RandomAccessFiles;
import org.apache.commons.io.file.spi.FileSystemProviders;
import org.apache.commons.io.input.BufferedFileChannelInputStream;
import org.apache.commons.io.input.CharSequenceInputStream;
import org.apache.commons.io.input.CharSequenceReader;
import org.apache.commons.io.input.ReaderInputStream;
import org.apache.commons.io.output.RandomAccessFileOutputStream;
import org.apache.commons.io.output.WriterOutputStream;

public abstract class AbstractOrigin extends AbstractSupplier {
   final Object origin;

   protected AbstractOrigin(Object origin) {
      this.origin = Objects.requireNonNull(origin, "origin");
   }

   public Object get() {
      return this.origin;
   }

   public byte[] getByteArray() throws IOException {
      return Files.readAllBytes(this.getPath());
   }

   public byte[] getByteArray(long position, int length) throws IOException {
      byte[] bytes = this.getByteArray();
      int start = Math.toIntExact(position);
      if (start >= 0 && length >= 0 && start + length >= 0 && start + length <= bytes.length) {
         return Arrays.copyOfRange(bytes, start, start + length);
      } else {
         throw new IllegalArgumentException("Couldn't read array (start: " + start + ", length: " + length + ", data length: " + bytes.length + ").");
      }
   }

   public CharSequence getCharSequence(Charset charset) throws IOException {
      return new String(this.getByteArray(), charset);
   }

   public File getFile() {
      throw new UnsupportedOperationException(String.format("%s#getFile() for %s origin %s", this.getSimpleClassName(), this.origin.getClass().getSimpleName(), this.origin));
   }

   public InputStream getInputStream(OpenOption... options) throws IOException {
      return Files.newInputStream(this.getPath(), options);
   }

   public OutputStream getOutputStream(OpenOption... options) throws IOException {
      return Files.newOutputStream(this.getPath(), options);
   }

   public Path getPath() {
      throw new UnsupportedOperationException(String.format("%s#getPath() for %s origin %s", this.getSimpleClassName(), this.origin.getClass().getSimpleName(), this.origin));
   }

   public RandomAccessFile getRandomAccessFile(OpenOption... openOption) throws FileNotFoundException {
      return RandomAccessFileMode.valueOf(openOption).create(this.getFile());
   }

   public Reader getReader(Charset charset) throws IOException {
      return Files.newBufferedReader(this.getPath(), charset);
   }

   private String getSimpleClassName() {
      return this.getClass().getSimpleName();
   }

   public Writer getWriter(Charset charset, OpenOption... options) throws IOException {
      return Files.newBufferedWriter(this.getPath(), charset, options);
   }

   public long size() throws IOException {
      return Files.size(this.getPath());
   }

   public String toString() {
      return this.getSimpleClassName() + "[" + this.origin.toString() + "]";
   }

   public abstract static class AbstractRandomAccessFileOrigin extends AbstractOrigin {
      public AbstractRandomAccessFileOrigin(RandomAccessFile origin) {
         super(origin);
      }

      public byte[] getByteArray() throws IOException {
         long longLen = ((RandomAccessFile)this.origin).length();
         if (longLen > 2147483647L) {
            throw new IllegalStateException("Origin too large.");
         } else {
            return RandomAccessFiles.read((RandomAccessFile)this.origin, 0L, (int)longLen);
         }
      }

      public byte[] getByteArray(long position, int length) throws IOException {
         return RandomAccessFiles.read((RandomAccessFile)this.origin, position, length);
      }

      public CharSequence getCharSequence(Charset charset) throws IOException {
         return new String(this.getByteArray(), charset);
      }

      public InputStream getInputStream(OpenOption... options) throws IOException {
         return BufferedFileChannelInputStream.builder().setFileChannel(((RandomAccessFile)this.origin).getChannel()).get();
      }

      public OutputStream getOutputStream(OpenOption... options) throws IOException {
         return ((RandomAccessFileOutputStream.Builder)RandomAccessFileOutputStream.builder().setRandomAccessFile((RandomAccessFile)this.origin)).get();
      }

      public RandomAccessFile getRandomAccessFile(OpenOption... openOption) {
         return (RandomAccessFile)this.get();
      }

      public Reader getReader(Charset charset) throws IOException {
         return new InputStreamReader(this.getInputStream(), charset);
      }

      public Writer getWriter(Charset charset, OpenOption... options) throws IOException {
         return new OutputStreamWriter(this.getOutputStream(options), charset);
      }

      public long size() throws IOException {
         return ((RandomAccessFile)this.origin).length();
      }
   }

   public static class ByteArrayOrigin extends AbstractOrigin {
      public ByteArrayOrigin(byte[] origin) {
         super(origin);
      }

      public byte[] getByteArray() {
         return (byte[])this.get();
      }

      public InputStream getInputStream(OpenOption... options) throws IOException {
         return new ByteArrayInputStream((byte[])this.origin);
      }

      public Reader getReader(Charset charset) throws IOException {
         return new InputStreamReader(this.getInputStream(), charset);
      }

      public long size() throws IOException {
         return (long)((byte[])this.origin).length;
      }
   }

   public static class CharSequenceOrigin extends AbstractOrigin {
      public CharSequenceOrigin(CharSequence origin) {
         super(origin);
      }

      public byte[] getByteArray() {
         return ((CharSequence)this.origin).toString().getBytes(Charset.defaultCharset());
      }

      public CharSequence getCharSequence(Charset charset) {
         return (CharSequence)this.get();
      }

      public InputStream getInputStream(OpenOption... options) throws IOException {
         return ((CharSequenceInputStream.Builder)CharSequenceInputStream.builder().setCharSequence(this.getCharSequence(Charset.defaultCharset()))).get();
      }

      public Reader getReader(Charset charset) throws IOException {
         return new CharSequenceReader((CharSequence)this.get());
      }

      public long size() throws IOException {
         return (long)((CharSequence)this.origin).length();
      }
   }

   public static class FileOrigin extends AbstractOrigin {
      public FileOrigin(File origin) {
         super(origin);
      }

      public byte[] getByteArray(long position, int length) throws IOException {
         RandomAccessFile raf = RandomAccessFileMode.READ_ONLY.create((File)this.origin);

         byte[] var5;
         try {
            var5 = RandomAccessFiles.read(raf, position, length);
         } catch (Throwable var8) {
            if (raf != null) {
               try {
                  raf.close();
               } catch (Throwable var7) {
                  var8.addSuppressed(var7);
               }
            }

            throw var8;
         }

         if (raf != null) {
            raf.close();
         }

         return var5;
      }

      public File getFile() {
         return (File)this.get();
      }

      public Path getPath() {
         return ((File)this.get()).toPath();
      }
   }

   public static class InputStreamOrigin extends AbstractOrigin {
      public InputStreamOrigin(InputStream origin) {
         super(origin);
      }

      public byte[] getByteArray() throws IOException {
         return IOUtils.toByteArray((InputStream)this.origin);
      }

      public InputStream getInputStream(OpenOption... options) {
         return (InputStream)this.get();
      }

      public Reader getReader(Charset charset) throws IOException {
         return new InputStreamReader(this.getInputStream(), charset);
      }
   }

   public static class IORandomAccessFileOrigin extends AbstractRandomAccessFileOrigin {
      public IORandomAccessFileOrigin(IORandomAccessFile origin) {
         super(origin);
      }

      public File getFile() {
         return ((IORandomAccessFile)this.get()).getFile();
      }

      public Path getPath() {
         return this.getFile().toPath();
      }
   }

   public static class OutputStreamOrigin extends AbstractOrigin {
      public OutputStreamOrigin(OutputStream origin) {
         super(origin);
      }

      public OutputStream getOutputStream(OpenOption... options) {
         return (OutputStream)this.get();
      }

      public Writer getWriter(Charset charset, OpenOption... options) throws IOException {
         return new OutputStreamWriter((OutputStream)this.origin, charset);
      }
   }

   public static class PathOrigin extends AbstractOrigin {
      public PathOrigin(Path origin) {
         super(origin);
      }

      public byte[] getByteArray(long position, int length) throws IOException {
         return (byte[])RandomAccessFileMode.READ_ONLY.apply((Path)this.origin, (raf) -> RandomAccessFiles.read(raf, position, length));
      }

      public File getFile() {
         return ((Path)this.get()).toFile();
      }

      public Path getPath() {
         return (Path)this.get();
      }
   }

   public static class RandomAccessFileOrigin extends AbstractRandomAccessFileOrigin {
      public RandomAccessFileOrigin(RandomAccessFile origin) {
         super(origin);
      }
   }

   public static class ReaderOrigin extends AbstractOrigin {
      public ReaderOrigin(Reader origin) {
         super(origin);
      }

      public byte[] getByteArray() throws IOException {
         return IOUtils.toByteArray((Reader)this.origin, Charset.defaultCharset());
      }

      public CharSequence getCharSequence(Charset charset) throws IOException {
         return IOUtils.toString((Reader)this.origin);
      }

      public InputStream getInputStream(OpenOption... options) throws IOException {
         return ((ReaderInputStream.Builder)ReaderInputStream.builder().setReader((Reader)this.origin)).setCharset(Charset.defaultCharset()).get();
      }

      public Reader getReader(Charset charset) throws IOException {
         return (Reader)this.get();
      }
   }

   public static class URIOrigin extends AbstractOrigin {
      private static final String SCHEME_HTTPS = "https";
      private static final String SCHEME_HTTP = "http";

      public URIOrigin(URI origin) {
         super(origin);
      }

      public File getFile() {
         return this.getPath().toFile();
      }

      public InputStream getInputStream(OpenOption... options) throws IOException {
         URI uri = (URI)this.get();
         String scheme = uri.getScheme();
         FileSystemProvider fileSystemProvider = FileSystemProviders.installed().getFileSystemProvider(scheme);
         if (fileSystemProvider != null) {
            return Files.newInputStream(fileSystemProvider.getPath(uri), options);
         } else {
            return !"http".equalsIgnoreCase(scheme) && !"https".equalsIgnoreCase(scheme) ? Files.newInputStream(this.getPath(), options) : uri.toURL().openStream();
         }
      }

      public Path getPath() {
         return Paths.get((URI)this.get());
      }
   }

   public static class WriterOrigin extends AbstractOrigin {
      public WriterOrigin(Writer origin) {
         super(origin);
      }

      public OutputStream getOutputStream(OpenOption... options) throws IOException {
         return ((WriterOutputStream.Builder)WriterOutputStream.builder().setWriter((Writer)this.origin)).setCharset(Charset.defaultCharset()).get();
      }

      public Writer getWriter(Charset charset, OpenOption... options) throws IOException {
         return (Writer)this.get();
      }
   }
}
