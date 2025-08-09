package org.apache.commons.io.output;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Objects;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.build.AbstractStreamBuilder;

public class FileWriterWithEncoding extends ProxyWriter {
   public static Builder builder() {
      return new Builder();
   }

   private static OutputStreamWriter initWriter(File file, Object encoding, boolean append) throws IOException {
      Objects.requireNonNull(file, "file");
      OutputStream outputStream = null;
      boolean fileExistedAlready = file.exists();

      try {
         outputStream = FileUtils.newOutputStream(file, append);
         if (encoding != null && !(encoding instanceof Charset)) {
            return encoding instanceof CharsetEncoder ? new OutputStreamWriter(outputStream, (CharsetEncoder)encoding) : new OutputStreamWriter(outputStream, (String)encoding);
         } else {
            return new OutputStreamWriter(outputStream, Charsets.toCharset((Charset)encoding));
         }
      } catch (RuntimeException | IOException var8) {
         try {
            IOUtils.close((Closeable)outputStream);
         } catch (IOException e) {
            ((Exception)var8).addSuppressed(e);
         }

         if (!fileExistedAlready) {
            FileUtils.deleteQuietly(file);
         }

         throw var8;
      }
   }

   /** @deprecated */
   @Deprecated
   public FileWriterWithEncoding(File file, Charset charset) throws IOException {
      this(file, charset, false);
   }

   /** @deprecated */
   @Deprecated
   public FileWriterWithEncoding(File file, Charset encoding, boolean append) throws IOException {
      this(initWriter(file, encoding, append));
   }

   /** @deprecated */
   @Deprecated
   public FileWriterWithEncoding(File file, CharsetEncoder charsetEncoder) throws IOException {
      this(file, charsetEncoder, false);
   }

   /** @deprecated */
   @Deprecated
   public FileWriterWithEncoding(File file, CharsetEncoder charsetEncoder, boolean append) throws IOException {
      this(initWriter(file, charsetEncoder, append));
   }

   /** @deprecated */
   @Deprecated
   public FileWriterWithEncoding(File file, String charsetName) throws IOException {
      this(file, charsetName, false);
   }

   /** @deprecated */
   @Deprecated
   public FileWriterWithEncoding(File file, String charsetName, boolean append) throws IOException {
      this(initWriter(file, charsetName, append));
   }

   private FileWriterWithEncoding(OutputStreamWriter outputStreamWriter) {
      super(outputStreamWriter);
   }

   /** @deprecated */
   @Deprecated
   public FileWriterWithEncoding(String fileName, Charset charset) throws IOException {
      this(new File(fileName), charset, false);
   }

   /** @deprecated */
   @Deprecated
   public FileWriterWithEncoding(String fileName, Charset charset, boolean append) throws IOException {
      this(new File(fileName), charset, append);
   }

   /** @deprecated */
   @Deprecated
   public FileWriterWithEncoding(String fileName, CharsetEncoder encoding) throws IOException {
      this(new File(fileName), encoding, false);
   }

   /** @deprecated */
   @Deprecated
   public FileWriterWithEncoding(String fileName, CharsetEncoder charsetEncoder, boolean append) throws IOException {
      this(new File(fileName), charsetEncoder, append);
   }

   /** @deprecated */
   @Deprecated
   public FileWriterWithEncoding(String fileName, String charsetName) throws IOException {
      this(new File(fileName), charsetName, false);
   }

   /** @deprecated */
   @Deprecated
   public FileWriterWithEncoding(String fileName, String charsetName, boolean append) throws IOException {
      this(new File(fileName), charsetName, append);
   }

   public static class Builder extends AbstractStreamBuilder {
      private boolean append;
      private CharsetEncoder charsetEncoder = super.getCharset().newEncoder();

      public FileWriterWithEncoding get() throws IOException {
         if (this.charsetEncoder != null && this.getCharset() != null && !this.charsetEncoder.charset().equals(this.getCharset())) {
            throw new IllegalStateException(String.format("Mismatched Charset(%s) and CharsetEncoder(%s)", this.getCharset(), this.charsetEncoder.charset()));
         } else {
            Object encoder = this.charsetEncoder != null ? this.charsetEncoder : this.getCharset();
            return new FileWriterWithEncoding(FileWriterWithEncoding.initWriter(this.checkOrigin().getFile(), encoder, this.append));
         }
      }

      public Builder setAppend(boolean append) {
         this.append = append;
         return this;
      }

      public Builder setCharsetEncoder(CharsetEncoder charsetEncoder) {
         this.charsetEncoder = charsetEncoder;
         return this;
      }
   }
}
