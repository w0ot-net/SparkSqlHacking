package org.apache.commons.compress.archivers.examples;

import [Ljava.nio.file.LinkOption;;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.Objects;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

public class Archiver {
   public static final EnumSet EMPTY_FileVisitOption = EnumSet.noneOf(FileVisitOption.class);

   public void create(ArchiveOutputStream target, File directory) throws IOException {
      this.create(target, directory.toPath(), EMPTY_FileVisitOption);
   }

   public void create(ArchiveOutputStream target, Path directory) throws IOException {
      this.create(target, directory, EMPTY_FileVisitOption);
   }

   public void create(ArchiveOutputStream target, Path directory, EnumSet fileVisitOptions, LinkOption... linkOptions) throws IOException {
      Files.walkFileTree(directory, fileVisitOptions, Integer.MAX_VALUE, new ArchiverFileVisitor(target, directory, linkOptions));
      target.finish();
   }

   public void create(SevenZOutputFile target, File directory) throws IOException {
      this.create(target, directory.toPath());
   }

   public void create(final SevenZOutputFile target, final Path directory) throws IOException {
      Files.walkFileTree(directory, new ArchiverFileVisitor((ArchiveOutputStream)null, directory, new LinkOption[0]) {
         protected FileVisitResult visit(Path path, BasicFileAttributes attrs, boolean isFile) throws IOException {
            Objects.requireNonNull(path);
            Objects.requireNonNull(attrs);
            String name = directory.relativize(path).toString().replace('\\', '/');
            if (!name.isEmpty()) {
               SevenZArchiveEntry archiveEntry = target.createArchiveEntry(path, !isFile && !name.endsWith("/") ? name + "/" : name);
               target.putArchiveEntry(archiveEntry);
               if (isFile) {
                  target.write(path);
               }

               target.closeArchiveEntry();
            }

            return FileVisitResult.CONTINUE;
         }
      });
      target.finish();
   }

   public void create(String format, File target, File directory) throws IOException, ArchiveException {
      this.create(format, target.toPath(), directory.toPath());
   }

   /** @deprecated */
   @Deprecated
   public void create(String format, OutputStream target, File directory) throws IOException, ArchiveException {
      this.create(format, target, directory, CloseableConsumer.NULL_CONSUMER);
   }

   public void create(String format, OutputStream target, File directory, CloseableConsumer closeableConsumer) throws IOException, ArchiveException {
      CloseableConsumerAdapter c = new CloseableConsumerAdapter(closeableConsumer);

      try {
         ArchiveOutputStream<? extends ArchiveEntry> archiveOutputStream = ArchiveStreamFactory.DEFAULT.createArchiveOutputStream(format, target);
         this.create((ArchiveOutputStream)c.track(archiveOutputStream), directory);
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

   public void create(String format, Path target, Path directory) throws IOException, ArchiveException {
      if (this.prefersSeekableByteChannel(format)) {
         SeekableByteChannel channel = FileChannel.open(target, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

         try {
            this.create(format, channel, directory);
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
         ArchiveOutputStream<?> outputStream = ArchiveStreamFactory.DEFAULT.createArchiveOutputStream(format, Files.newOutputStream(target));

         try {
            this.create(outputStream, directory, EMPTY_FileVisitOption);
         } catch (Throwable var9) {
            if (outputStream != null) {
               try {
                  outputStream.close();
               } catch (Throwable var7) {
                  var9.addSuppressed(var7);
               }
            }

            throw var9;
         }

         if (outputStream != null) {
            outputStream.close();
         }

      }
   }

   /** @deprecated */
   @Deprecated
   public void create(String format, SeekableByteChannel target, File directory) throws IOException, ArchiveException {
      this.create(format, target, directory, CloseableConsumer.NULL_CONSUMER);
   }

   public void create(String format, SeekableByteChannel target, File directory, CloseableConsumer closeableConsumer) throws IOException, ArchiveException {
      CloseableConsumerAdapter c = new CloseableConsumerAdapter(closeableConsumer);

      try {
         if (!this.prefersSeekableByteChannel(format)) {
            this.create(format, (OutputStream)c.track(Channels.newOutputStream(target)), directory);
         } else if ("zip".equalsIgnoreCase(format)) {
            this.create((ArchiveOutputStream)c.track(new ZipArchiveOutputStream(target)), directory);
         } else {
            if (!"7z".equalsIgnoreCase(format)) {
               throw new ArchiveException("Don't know how to handle format " + format);
            }

            this.create((SevenZOutputFile)c.track(new SevenZOutputFile(target)), directory);
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

   public void create(String format, SeekableByteChannel target, Path directory) throws IOException {
      if ("7z".equalsIgnoreCase(format)) {
         SevenZOutputFile sevenZFile = new SevenZOutputFile(target);

         try {
            this.create(sevenZFile, directory);
         } catch (Throwable var10) {
            try {
               sevenZFile.close();
            } catch (Throwable var8) {
               var10.addSuppressed(var8);
            }

            throw var10;
         }

         sevenZFile.close();
      } else {
         if (!"zip".equalsIgnoreCase(format)) {
            throw new IllegalStateException(format);
         }

         ZipArchiveOutputStream archiveOutputStream = new ZipArchiveOutputStream(target);

         try {
            this.create((ArchiveOutputStream)archiveOutputStream, (Path)directory, (EnumSet)EMPTY_FileVisitOption, (LinkOption[])());
         } catch (Throwable var9) {
            try {
               archiveOutputStream.close();
            } catch (Throwable var7) {
               var9.addSuppressed(var7);
            }

            throw var9;
         }

         archiveOutputStream.close();
      }

   }

   private boolean prefersSeekableByteChannel(String format) {
      return "zip".equalsIgnoreCase(format) || "7z".equalsIgnoreCase(format);
   }

   private static class ArchiverFileVisitor extends SimpleFileVisitor {
      private final ArchiveOutputStream target;
      private final Path directory;
      private final LinkOption[] linkOptions;

      private ArchiverFileVisitor(ArchiveOutputStream target, Path directory, LinkOption... linkOptions) {
         this.target = target;
         this.directory = directory;
         this.linkOptions = linkOptions == null ? IOUtils.EMPTY_LINK_OPTIONS : (LinkOption[])((LinkOption;)linkOptions).clone();
      }

      public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
         return this.visit(dir, attrs, false);
      }

      protected FileVisitResult visit(Path path, BasicFileAttributes attrs, boolean isFile) throws IOException {
         Objects.requireNonNull(path);
         Objects.requireNonNull(attrs);
         String name = this.directory.relativize(path).toString().replace('\\', '/');
         if (!name.isEmpty()) {
            E archiveEntry = (E)this.target.createArchiveEntry(path, !isFile && !name.endsWith("/") ? name + "/" : name, this.linkOptions);
            this.target.putArchiveEntry(archiveEntry);
            if (isFile) {
               Files.copy(path, this.target);
            }

            this.target.closeArchiveEntry();
         }

         return FileVisitResult.CONTINUE;
      }

      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
         return this.visit(file, attrs, true);
      }
   }
}
