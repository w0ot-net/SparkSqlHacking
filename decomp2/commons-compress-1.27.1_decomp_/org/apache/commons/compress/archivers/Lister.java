package org.apache.commons.compress.archivers;

import [Ljava.lang.String;;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Objects;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.tar.TarFile;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

public final class Lister {
   private static final ArchiveStreamFactory FACTORY;
   private final boolean quiet;
   private final String[] args;

   private static ArchiveInputStream createArchiveInputStream(String[] args, InputStream inputStream) throws ArchiveException {
      return args.length > 1 ? FACTORY.createArchiveInputStream(args[1], inputStream) : FACTORY.createArchiveInputStream(inputStream);
   }

   private static String detectFormat(Path file) throws ArchiveException, IOException {
      InputStream inputStream = new BufferedInputStream(Files.newInputStream(file));

      String var2;
      try {
         var2 = ArchiveStreamFactory.detect(inputStream);
      } catch (Throwable var5) {
         try {
            inputStream.close();
         } catch (Throwable var4) {
            var5.addSuppressed(var4);
         }

         throw var5;
      }

      inputStream.close();
      return var2;
   }

   public static void main(String... args) throws ArchiveException, IOException {
      if (args != null && args.length != 0) {
         (new Lister(false, args)).go();
      } else {
         usage();
      }
   }

   private static void usage() {
      System.err.println("Parameters: archive-name [archive-type]\n");
      System.err.println("The magic archive-type 'zipfile' prefers ZipFile over ZipArchiveInputStream");
      System.err.println("The magic archive-type 'tarfile' prefers TarFile over TarArchiveInputStream");
   }

   /** @deprecated */
   @Deprecated
   public Lister() {
      this(false, "");
   }

   Lister(boolean quiet, String... args) {
      this.quiet = quiet;
      this.args = (String[])((String;)args).clone();
      Objects.requireNonNull(args[0], "args[0]");
   }

   void go() throws ArchiveException, IOException {
      this.list(Paths.get(this.args[0]), this.args);
   }

   private void list(Path file, String... args) throws ArchiveException, IOException {
      this.println("Analyzing " + file);
      if (!Files.isRegularFile(file, new LinkOption[0])) {
         System.err.println(file + " doesn't exist or is a directory");
      }

      String format = (args.length > 1 ? args[1] : detectFormat(file)).toLowerCase(Locale.ROOT);
      this.println("Detected format " + format);
      switch (format) {
         case "7z":
            this.list7z(file);
            break;
         case "zip":
            this.listZipUsingZipFile(file);
            break;
         case "tar":
            this.listZipUsingTarFile(file);
            break;
         default:
            this.listStream(file, args);
      }

   }

   private void list7z(Path file) throws IOException {
      SevenZFile sevenZFile = ((SevenZFile.Builder)SevenZFile.builder().setPath(file)).get();

      try {
         this.println("Created " + sevenZFile);

         ArchiveEntry entry;
         while((entry = sevenZFile.getNextEntry()) != null) {
            this.println(entry.getName() == null ? sevenZFile.getDefaultName() + " (entry name was null)" : entry.getName());
         }
      } catch (Throwable var6) {
         if (sevenZFile != null) {
            try {
               sevenZFile.close();
            } catch (Throwable var5) {
               var6.addSuppressed(var5);
            }
         }

         throw var6;
      }

      if (sevenZFile != null) {
         sevenZFile.close();
      }

   }

   private void listStream(Path file, String[] args) throws ArchiveException, IOException {
      InputStream inputStream = new BufferedInputStream(Files.newInputStream(file));

      try {
         ArchiveInputStream<?> archiveInputStream = createArchiveInputStream(args, inputStream);

         try {
            this.println("Created " + archiveInputStream.toString());
            archiveInputStream.forEach(this::println);
         } catch (Throwable var9) {
            if (archiveInputStream != null) {
               try {
                  archiveInputStream.close();
               } catch (Throwable var8) {
                  var9.addSuppressed(var8);
               }
            }

            throw var9;
         }

         if (archiveInputStream != null) {
            archiveInputStream.close();
         }
      } catch (Throwable var10) {
         try {
            inputStream.close();
         } catch (Throwable var7) {
            var10.addSuppressed(var7);
         }

         throw var10;
      }

      inputStream.close();
   }

   private void listZipUsingTarFile(Path file) throws IOException {
      TarFile tarFile = new TarFile(file);

      try {
         this.println("Created " + tarFile);
         tarFile.getEntries().forEach(this::println);
      } catch (Throwable var6) {
         try {
            tarFile.close();
         } catch (Throwable var5) {
            var6.addSuppressed(var5);
         }

         throw var6;
      }

      tarFile.close();
   }

   private void listZipUsingZipFile(Path file) throws IOException {
      ZipFile zipFile = ((ZipFile.Builder)ZipFile.builder().setPath(file)).get();

      try {
         this.println("Created " + zipFile);
         Enumeration<ZipArchiveEntry> en = zipFile.getEntries();

         while(en.hasMoreElements()) {
            this.println((ArchiveEntry)en.nextElement());
         }
      } catch (Throwable var6) {
         if (zipFile != null) {
            try {
               zipFile.close();
            } catch (Throwable var5) {
               var6.addSuppressed(var5);
            }
         }

         throw var6;
      }

      if (zipFile != null) {
         zipFile.close();
      }

   }

   private void println(ArchiveEntry entry) {
      this.println(entry.getName());
   }

   private void println(String line) {
      if (!this.quiet) {
         System.out.println(line);
      }

   }

   static {
      FACTORY = ArchiveStreamFactory.DEFAULT;
   }
}
