package org.apache.commons.io.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Objects;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.build.AbstractOrigin;
import org.apache.commons.io.build.AbstractStreamBuilder;

public class LockableFileWriter extends Writer {
   private static final String LCK = ".lck";
   private final Writer out;
   private final File lockFile;

   public static Builder builder() {
      return new Builder();
   }

   /** @deprecated */
   @Deprecated
   public LockableFileWriter(File file) throws IOException {
      this((File)file, false, (String)null);
   }

   /** @deprecated */
   @Deprecated
   public LockableFileWriter(File file, boolean append) throws IOException {
      this((File)file, append, (String)null);
   }

   /** @deprecated */
   @Deprecated
   public LockableFileWriter(File file, boolean append, String lockDir) throws IOException {
      this(file, Charset.defaultCharset(), append, lockDir);
   }

   /** @deprecated */
   @Deprecated
   public LockableFileWriter(File file, Charset charset) throws IOException {
      this(file, (Charset)charset, false, (String)null);
   }

   /** @deprecated */
   @Deprecated
   public LockableFileWriter(File file, Charset charset, boolean append, String lockDir) throws IOException {
      File absFile = ((File)Objects.requireNonNull(file, "file")).getAbsoluteFile();
      if (absFile.getParentFile() != null) {
         FileUtils.forceMkdir(absFile.getParentFile());
      }

      if (absFile.isDirectory()) {
         throw new IOException("File specified is a directory");
      } else {
         File lockDirFile = new File(lockDir != null ? lockDir : FileUtils.getTempDirectoryPath());
         FileUtils.forceMkdir(lockDirFile);
         this.testLockDir(lockDirFile);
         this.lockFile = new File(lockDirFile, absFile.getName() + ".lck");
         this.createLock();
         this.out = this.initWriter(absFile, charset, append);
      }
   }

   /** @deprecated */
   @Deprecated
   public LockableFileWriter(File file, String charsetName) throws IOException {
      this(file, (String)charsetName, false, (String)null);
   }

   /** @deprecated */
   @Deprecated
   public LockableFileWriter(File file, String charsetName, boolean append, String lockDir) throws IOException {
      this(file, Charsets.toCharset(charsetName), append, lockDir);
   }

   /** @deprecated */
   @Deprecated
   public LockableFileWriter(String fileName) throws IOException {
      this((String)fileName, false, (String)null);
   }

   /** @deprecated */
   @Deprecated
   public LockableFileWriter(String fileName, boolean append) throws IOException {
      this((String)fileName, append, (String)null);
   }

   /** @deprecated */
   @Deprecated
   public LockableFileWriter(String fileName, boolean append, String lockDir) throws IOException {
      this(new File(fileName), append, lockDir);
   }

   public void close() throws IOException {
      try {
         this.out.close();
      } finally {
         FileUtils.delete(this.lockFile);
      }

   }

   private void createLock() throws IOException {
      synchronized(LockableFileWriter.class) {
         if (!this.lockFile.createNewFile()) {
            throw new IOException("Can't write file, lock " + this.lockFile.getAbsolutePath() + " exists");
         } else {
            this.lockFile.deleteOnExit();
         }
      }
   }

   public void flush() throws IOException {
      this.out.flush();
   }

   private Writer initWriter(File file, Charset charset, boolean append) throws IOException {
      boolean fileExistedAlready = file.exists();

      try {
         return new OutputStreamWriter(new FileOutputStream(file.getAbsolutePath(), append), Charsets.toCharset(charset));
      } catch (RuntimeException | IOException ex) {
         FileUtils.deleteQuietly(this.lockFile);
         if (!fileExistedAlready) {
            FileUtils.deleteQuietly(file);
         }

         throw ex;
      }
   }

   private void testLockDir(File lockDir) throws IOException {
      if (!lockDir.exists()) {
         throw new IOException("Could not find lockDir: " + lockDir.getAbsolutePath());
      } else if (!lockDir.canWrite()) {
         throw new IOException("Could not write to lockDir: " + lockDir.getAbsolutePath());
      }
   }

   public void write(char[] cbuf) throws IOException {
      this.out.write(cbuf);
   }

   public void write(char[] cbuf, int off, int len) throws IOException {
      this.out.write(cbuf, off, len);
   }

   public void write(int c) throws IOException {
      this.out.write(c);
   }

   public void write(String str) throws IOException {
      this.out.write(str);
   }

   public void write(String str, int off, int len) throws IOException {
      this.out.write(str, off, len);
   }

   public static class Builder extends AbstractStreamBuilder {
      private boolean append;
      private AbstractOrigin lockDirectory = newFileOrigin(FileUtils.getTempDirectoryPath());

      public Builder() {
         this.setBufferSizeDefault(1024);
         this.setBufferSize(1024);
      }

      public LockableFileWriter get() throws IOException {
         return new LockableFileWriter(this.checkOrigin().getFile(), this.getCharset(), this.append, this.lockDirectory.getFile().toString());
      }

      public Builder setAppend(boolean append) {
         this.append = append;
         return this;
      }

      public Builder setLockDirectory(File lockDirectory) {
         this.lockDirectory = newFileOrigin(lockDirectory != null ? lockDirectory : FileUtils.getTempDirectory());
         return this;
      }

      public Builder setLockDirectory(String lockDirectory) {
         this.lockDirectory = newFileOrigin(lockDirectory != null ? lockDirectory : FileUtils.getTempDirectoryPath());
         return this;
      }
   }
}
