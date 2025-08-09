package org.apache.commons.compress.archivers.examples;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Objects;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarFile;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.NullOutputStream;

public class Expander {
   private void expand(ArchiveEntrySupplier supplier, ArchiveEntryBiConsumer writer, Path targetDirectory) throws IOException {
      boolean nullTarget = targetDirectory == null;
      Path targetDirPath = nullTarget ? null : targetDirectory.normalize();

      for(T nextEntry = (T)supplier.get(); nextEntry != null; nextEntry = (T)supplier.get()) {
         Path targetPath = nullTarget ? null : nextEntry.resolveIn(targetDirPath);
         if (nextEntry.isDirectory()) {
            if (!nullTarget && !Files.isDirectory(targetPath, new LinkOption[0]) && Files.createDirectories(targetPath) == null) {
               throw new IOException("Failed to create directory " + targetPath);
            }
         } else {
            Path parent = nullTarget ? null : targetPath.getParent();
            if (!nullTarget && !Files.isDirectory(parent, new LinkOption[0]) && Files.createDirectories(parent) == null) {
               throw new IOException("Failed to create directory " + parent);
            }

            if (nullTarget) {
               writer.accept(nextEntry, NullOutputStream.INSTANCE);
            } else {
               OutputStream outputStream = Files.newOutputStream(targetPath);

               try {
                  writer.accept(nextEntry, outputStream);
               } catch (Throwable var13) {
                  if (outputStream != null) {
                     try {
                        outputStream.close();
                     } catch (Throwable var12) {
                        var13.addSuppressed(var12);
                     }
                  }

                  throw var13;
               }

               if (outputStream != null) {
                  outputStream.close();
               }
            }
         }
      }

   }

   public void expand(ArchiveInputStream archive, File targetDirectory) throws IOException {
      this.expand(archive, this.toPath(targetDirectory));
   }

   public void expand(ArchiveInputStream archive, Path targetDirectory) throws IOException {
      this.expand((ArchiveEntrySupplier)(() -> {
         ArchiveEntry next;
         for(next = archive.getNextEntry(); next != null && !archive.canReadEntryData(next); next = archive.getNextEntry()) {
         }

         return next;
      }), (ArchiveEntryBiConsumer)((entry, out) -> IOUtils.copy(archive, out)), (Path)targetDirectory);
   }

   public void expand(File archive, File targetDirectory) throws IOException, ArchiveException {
      this.expand(archive.toPath(), this.toPath(targetDirectory));
   }

   /** @deprecated */
   @Deprecated
   public void expand(InputStream archive, File targetDirectory) throws IOException, ArchiveException {
      this.expand(archive, targetDirectory, CloseableConsumer.NULL_CONSUMER);
   }

   public void expand(InputStream archive, File targetDirectory, CloseableConsumer closeableConsumer) throws IOException, ArchiveException {
      CloseableConsumerAdapter c = new CloseableConsumerAdapter(closeableConsumer);

      try {
         this.expand((ArchiveInputStream)c.track(ArchiveStreamFactory.DEFAULT.createArchiveInputStream(archive)), targetDirectory);
      } catch (Throwable var8) {
         try {
            c.close();
         } catch (Throwable var7) {
            var8.addSuppressed(var7);
         }

         throw var8;
      }

      c.close();
   }

   public void expand(Path archive, Path targetDirectory) throws IOException, ArchiveException {
      InputStream inputStream = new BufferedInputStream(Files.newInputStream(archive));

      try {
         this.expand(ArchiveStreamFactory.detect(inputStream), archive, targetDirectory);
      } catch (Throwable var7) {
         try {
            inputStream.close();
         } catch (Throwable var6) {
            var7.addSuppressed(var6);
         }

         throw var7;
      }

      inputStream.close();
   }

   public void expand(SevenZFile archive, File targetDirectory) throws IOException {
      this.expand(archive, this.toPath(targetDirectory));
   }

   public void expand(SevenZFile archive, Path targetDirectory) throws IOException {
      Objects.requireNonNull(archive);
      this.expand((ArchiveEntrySupplier)(archive::getNextEntry), (ArchiveEntryBiConsumer)((entry, out) -> {
         byte[] buffer = new byte[8192];

         int n;
         while(-1 != (n = archive.read(buffer))) {
            if (out != null) {
               out.write(buffer, 0, n);
            }
         }

      }), (Path)targetDirectory);
   }

   public void expand(String format, File archive, File targetDirectory) throws IOException, ArchiveException {
      this.expand(format, archive.toPath(), this.toPath(targetDirectory));
   }

   /** @deprecated */
   @Deprecated
   public void expand(String format, InputStream archive, File targetDirectory) throws IOException, ArchiveException {
      this.expand(format, archive, targetDirectory, CloseableConsumer.NULL_CONSUMER);
   }

   public void expand(String format, InputStream archive, File targetDirectory, CloseableConsumer closeableConsumer) throws IOException, ArchiveException {
      this.expand(format, archive, this.toPath(targetDirectory), closeableConsumer);
   }

   public void expand(String format, InputStream archive, Path targetDirectory, CloseableConsumer closeableConsumer) throws IOException, ArchiveException {
      CloseableConsumerAdapter c = new CloseableConsumerAdapter(closeableConsumer);

      try {
         ArchiveInputStream<?> archiveInputStream = ArchiveStreamFactory.DEFAULT.createArchiveInputStream(format, archive);
         this.expand((ArchiveInputStream)c.track(archiveInputStream), targetDirectory);
      } catch (Throwable var9) {
         try {
            c.close();
         } catch (Throwable var8) {
            var9.addSuppressed(var8);
         }

         throw var9;
      }

      c.close();
   }

   public void expand(String format, Path archive, Path targetDirectory) throws IOException, ArchiveException {
      if (this.prefersSeekableByteChannel(format)) {
         SeekableByteChannel channel = FileChannel.open(archive, StandardOpenOption.READ);

         try {
            this.expand(format, channel, targetDirectory, CloseableConsumer.CLOSING_CONSUMER);
         } catch (Throwable var10) {
            if (channel != null) {
               try {
                  channel.close();
               } catch (Throwable var8) {
                  var10.addSuppressed(var8);
               }
            }

            throw var10;
         }

         if (channel != null) {
            channel.close();
         }

      } else {
         InputStream inputStream = new BufferedInputStream(Files.newInputStream(archive));

         try {
            this.expand(format, inputStream, targetDirectory, CloseableConsumer.CLOSING_CONSUMER);
         } catch (Throwable var9) {
            try {
               inputStream.close();
            } catch (Throwable var7) {
               var9.addSuppressed(var7);
            }

            throw var9;
         }

         inputStream.close();
      }
   }

   /** @deprecated */
   @Deprecated
   public void expand(String format, SeekableByteChannel archive, File targetDirectory) throws IOException, ArchiveException {
      this.expand(format, archive, targetDirectory, CloseableConsumer.NULL_CONSUMER);
   }

   public void expand(String format, SeekableByteChannel archive, File targetDirectory, CloseableConsumer closeableConsumer) throws IOException, ArchiveException {
      this.expand(format, archive, this.toPath(targetDirectory), closeableConsumer);
   }

   public void expand(String format, SeekableByteChannel archive, Path targetDirectory, CloseableConsumer closeableConsumer) throws IOException, ArchiveException {
      CloseableConsumerAdapter c = new CloseableConsumerAdapter(closeableConsumer);

      try {
         if (!this.prefersSeekableByteChannel(format)) {
            this.expand(format, (InputStream)c.track(Channels.newInputStream(archive)), targetDirectory, CloseableConsumer.NULL_CONSUMER);
         } else if ("tar".equalsIgnoreCase(format)) {
            this.expand((TarFile)c.track(new TarFile(archive)), targetDirectory);
         } else if ("zip".equalsIgnoreCase(format)) {
            this.expand((ZipFile)c.track(ZipFile.builder().setSeekableByteChannel(archive).get()), targetDirectory);
         } else {
            if (!"7z".equalsIgnoreCase(format)) {
               throw new ArchiveException("Don't know how to handle format " + format);
            }

            this.expand((SevenZFile)c.track(SevenZFile.builder().setSeekableByteChannel(archive).get()), targetDirectory);
         }
      } catch (Throwable var9) {
         try {
            c.close();
         } catch (Throwable var8) {
            var9.addSuppressed(var8);
         }

         throw var9;
      }

      c.close();
   }

   public void expand(TarFile archive, File targetDirectory) throws IOException {
      this.expand(archive, this.toPath(targetDirectory));
   }

   public void expand(TarFile archive, Path targetDirectory) throws IOException {
      Iterator<TarArchiveEntry> entryIterator = archive.getEntries().iterator();
      this.expand((ArchiveEntrySupplier)(() -> entryIterator.hasNext() ? (TarArchiveEntry)entryIterator.next() : null), (ArchiveEntryBiConsumer)((entry, out) -> {
         InputStream in = archive.getInputStream(entry);

         try {
            IOUtils.copy(in, out);
         } catch (Throwable var7) {
            if (in != null) {
               try {
                  in.close();
               } catch (Throwable var6) {
                  var7.addSuppressed(var6);
               }
            }

            throw var7;
         }

         if (in != null) {
            in.close();
         }

      }), (Path)targetDirectory);
   }

   public void expand(ZipFile archive, File targetDirectory) throws IOException {
      this.expand(archive, this.toPath(targetDirectory));
   }

   public void expand(ZipFile archive, Path targetDirectory) throws IOException {
      Enumeration<ZipArchiveEntry> entries = archive.getEntries();
      this.expand((ArchiveEntrySupplier)(() -> {
         ZipArchiveEntry next;
         for(next = entries.hasMoreElements() ? (ZipArchiveEntry)entries.nextElement() : null; next != null && !archive.canReadEntryData(next); next = entries.hasMoreElements() ? (ZipArchiveEntry)entries.nextElement() : null) {
         }

         return next;
      }), (ArchiveEntryBiConsumer)((entry, out) -> {
         InputStream in = archive.getInputStream(entry);

         try {
            IOUtils.copy(in, out);
         } catch (Throwable var7) {
            if (in != null) {
               try {
                  in.close();
               } catch (Throwable var6) {
                  var7.addSuppressed(var6);
               }
            }

            throw var7;
         }

         if (in != null) {
            in.close();
         }

      }), (Path)targetDirectory);
   }

   private boolean prefersSeekableByteChannel(String format) {
      return "tar".equalsIgnoreCase(format) || "zip".equalsIgnoreCase(format) || "7z".equalsIgnoreCase(format);
   }

   private Path toPath(File targetDirectory) {
      return targetDirectory != null ? targetDirectory.toPath() : null;
   }

   @FunctionalInterface
   private interface ArchiveEntryBiConsumer {
      void accept(ArchiveEntry var1, OutputStream var2) throws IOException;
   }

   @FunctionalInterface
   private interface ArchiveEntrySupplier {
      ArchiveEntry get() throws IOException;
   }
}
