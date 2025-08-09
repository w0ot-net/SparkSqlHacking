package org.sparkproject.guava.io;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.InlineMe;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.Beta;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.base.Joiner;
import org.sparkproject.guava.base.Optional;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.base.Predicate;
import org.sparkproject.guava.base.Splitter;
import org.sparkproject.guava.collect.ImmutableList;
import org.sparkproject.guava.collect.ImmutableSet;
import org.sparkproject.guava.collect.Lists;
import org.sparkproject.guava.graph.SuccessorsFunction;
import org.sparkproject.guava.graph.Traverser;
import org.sparkproject.guava.hash.HashCode;
import org.sparkproject.guava.hash.HashFunction;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public final class Files {
   private static final SuccessorsFunction FILE_TREE = new SuccessorsFunction() {
      public Iterable successors(File file) {
         if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
               return Collections.unmodifiableList(Arrays.asList(files));
            }
         }

         return ImmutableList.of();
      }
   };

   private Files() {
   }

   public static BufferedReader newReader(File file, Charset charset) throws FileNotFoundException {
      Preconditions.checkNotNull(file);
      Preconditions.checkNotNull(charset);
      return new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
   }

   public static BufferedWriter newWriter(File file, Charset charset) throws FileNotFoundException {
      Preconditions.checkNotNull(file);
      Preconditions.checkNotNull(charset);
      return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
   }

   public static ByteSource asByteSource(File file) {
      return new FileByteSource(file);
   }

   public static ByteSink asByteSink(File file, FileWriteMode... modes) {
      return new FileByteSink(file, modes);
   }

   public static CharSource asCharSource(File file, Charset charset) {
      return asByteSource(file).asCharSource(charset);
   }

   public static CharSink asCharSink(File file, Charset charset, FileWriteMode... modes) {
      return asByteSink(file, modes).asCharSink(charset);
   }

   public static byte[] toByteArray(File file) throws IOException {
      return asByteSource(file).read();
   }

   /** @deprecated */
   @Deprecated
   @InlineMe(
      replacement = "Files.asCharSource(file, charset).read()",
      imports = {"org.sparkproject.guava.io.Files"}
   )
   public static String toString(File file, Charset charset) throws IOException {
      return asCharSource(file, charset).read();
   }

   public static void write(byte[] from, File to) throws IOException {
      asByteSink(to).write(from);
   }

   /** @deprecated */
   @Deprecated
   @InlineMe(
      replacement = "Files.asCharSink(to, charset).write(from)",
      imports = {"org.sparkproject.guava.io.Files"}
   )
   public static void write(CharSequence from, File to, Charset charset) throws IOException {
      asCharSink(to, charset).write(from);
   }

   public static void copy(File from, OutputStream to) throws IOException {
      asByteSource(from).copyTo(to);
   }

   public static void copy(File from, File to) throws IOException {
      Preconditions.checkArgument(!from.equals(to), "Source %s and destination %s must be different", from, to);
      asByteSource(from).copyTo(asByteSink(to));
   }

   /** @deprecated */
   @Deprecated
   @InlineMe(
      replacement = "Files.asCharSource(from, charset).copyTo(to)",
      imports = {"org.sparkproject.guava.io.Files"}
   )
   public static void copy(File from, Charset charset, Appendable to) throws IOException {
      asCharSource(from, charset).copyTo(to);
   }

   /** @deprecated */
   @Deprecated
   @InlineMe(
      replacement = "Files.asCharSink(to, charset, FileWriteMode.APPEND).write(from)",
      imports = {"org.sparkproject.guava.io.FileWriteMode", "org.sparkproject.guava.io.Files"}
   )
   public static void append(CharSequence from, File to, Charset charset) throws IOException {
      asCharSink(to, charset, FileWriteMode.APPEND).write(from);
   }

   public static boolean equal(File file1, File file2) throws IOException {
      Preconditions.checkNotNull(file1);
      Preconditions.checkNotNull(file2);
      if (file1 != file2 && !file1.equals(file2)) {
         long len1 = file1.length();
         long len2 = file2.length();
         return len1 != 0L && len2 != 0L && len1 != len2 ? false : asByteSource(file1).contentEquals(asByteSource(file2));
      } else {
         return true;
      }
   }

   /** @deprecated */
   @Deprecated
   @Beta
   public static File createTempDir() {
      return TempFileCreator.INSTANCE.createTempDir();
   }

   public static void touch(File file) throws IOException {
      Preconditions.checkNotNull(file);
      if (!file.createNewFile() && !file.setLastModified(System.currentTimeMillis())) {
         throw new IOException("Unable to update modification time of " + file);
      }
   }

   public static void createParentDirs(File file) throws IOException {
      Preconditions.checkNotNull(file);
      File parent = file.getCanonicalFile().getParentFile();
      if (parent != null) {
         parent.mkdirs();
         if (!parent.isDirectory()) {
            throw new IOException("Unable to create parent directories of " + file);
         }
      }
   }

   public static void move(File from, File to) throws IOException {
      Preconditions.checkNotNull(from);
      Preconditions.checkNotNull(to);
      Preconditions.checkArgument(!from.equals(to), "Source %s and destination %s must be different", from, to);
      if (!from.renameTo(to)) {
         copy(from, to);
         if (!from.delete()) {
            if (!to.delete()) {
               throw new IOException("Unable to delete " + to);
            }

            throw new IOException("Unable to delete " + from);
         }
      }

   }

   /** @deprecated */
   @Deprecated
   @CheckForNull
   @InlineMe(
      replacement = "Files.asCharSource(file, charset).readFirstLine()",
      imports = {"org.sparkproject.guava.io.Files"}
   )
   public static String readFirstLine(File file, Charset charset) throws IOException {
      return asCharSource(file, charset).readFirstLine();
   }

   public static List readLines(File file, Charset charset) throws IOException {
      return (List)asCharSource(file, charset).readLines(new LineProcessor() {
         final List result = Lists.newArrayList();

         public boolean processLine(String line) {
            this.result.add(line);
            return true;
         }

         public List getResult() {
            return this.result;
         }
      });
   }

   /** @deprecated */
   @Deprecated
   @ParametricNullness
   @InlineMe(
      replacement = "Files.asCharSource(file, charset).readLines(callback)",
      imports = {"org.sparkproject.guava.io.Files"}
   )
   @CanIgnoreReturnValue
   public static Object readLines(File file, Charset charset, LineProcessor callback) throws IOException {
      return asCharSource(file, charset).readLines(callback);
   }

   /** @deprecated */
   @Deprecated
   @ParametricNullness
   @InlineMe(
      replacement = "Files.asByteSource(file).read(processor)",
      imports = {"org.sparkproject.guava.io.Files"}
   )
   @CanIgnoreReturnValue
   public static Object readBytes(File file, ByteProcessor processor) throws IOException {
      return asByteSource(file).read(processor);
   }

   /** @deprecated */
   @Deprecated
   @InlineMe(
      replacement = "Files.asByteSource(file).hash(hashFunction)",
      imports = {"org.sparkproject.guava.io.Files"}
   )
   public static HashCode hash(File file, HashFunction hashFunction) throws IOException {
      return asByteSource(file).hash(hashFunction);
   }

   public static MappedByteBuffer map(File file) throws IOException {
      Preconditions.checkNotNull(file);
      return map(file, MapMode.READ_ONLY);
   }

   public static MappedByteBuffer map(File file, FileChannel.MapMode mode) throws IOException {
      return mapInternal(file, mode, -1L);
   }

   public static MappedByteBuffer map(File file, FileChannel.MapMode mode, long size) throws IOException {
      Preconditions.checkArgument(size >= 0L, "size (%s) may not be negative", size);
      return mapInternal(file, mode, size);
   }

   private static MappedByteBuffer mapInternal(File file, FileChannel.MapMode mode, long size) throws IOException {
      Preconditions.checkNotNull(file);
      Preconditions.checkNotNull(mode);
      Closer closer = Closer.create();

      MappedByteBuffer var7;
      try {
         RandomAccessFile raf = (RandomAccessFile)closer.register(new RandomAccessFile(file, mode == MapMode.READ_ONLY ? "r" : "rw"));
         FileChannel channel = (FileChannel)closer.register(raf.getChannel());
         var7 = channel.map(mode, 0L, size == -1L ? channel.size() : size);
      } catch (Throwable e) {
         throw closer.rethrow(e);
      } finally {
         closer.close();
      }

      return var7;
   }

   public static String simplifyPath(String pathname) {
      Preconditions.checkNotNull(pathname);
      if (pathname.length() == 0) {
         return ".";
      } else {
         Iterable<String> components = Splitter.on('/').omitEmptyStrings().split(pathname);
         List<String> path = new ArrayList();

         for(String component : components) {
            switch (component) {
               case ".":
                  break;
               case "..":
                  if (path.size() > 0 && !((String)path.get(path.size() - 1)).equals("..")) {
                     path.remove(path.size() - 1);
                     break;
                  }

                  path.add("..");
                  break;
               default:
                  path.add(component);
            }
         }

         String result = Joiner.on('/').join((Iterable)path);
         if (pathname.charAt(0) == '/') {
            result = "/" + result;
         }

         while(result.startsWith("/../")) {
            result = result.substring(3);
         }

         if (result.equals("/..")) {
            result = "/";
         } else if ("".equals(result)) {
            result = ".";
         }

         return result;
      }
   }

   public static String getFileExtension(String fullName) {
      Preconditions.checkNotNull(fullName);
      String fileName = (new File(fullName)).getName();
      int dotIndex = fileName.lastIndexOf(46);
      return dotIndex == -1 ? "" : fileName.substring(dotIndex + 1);
   }

   public static String getNameWithoutExtension(String file) {
      Preconditions.checkNotNull(file);
      String fileName = (new File(file)).getName();
      int dotIndex = fileName.lastIndexOf(46);
      return dotIndex == -1 ? fileName : fileName.substring(0, dotIndex);
   }

   public static Traverser fileTraverser() {
      return Traverser.forTree(FILE_TREE);
   }

   public static Predicate isDirectory() {
      return Files.FilePredicate.IS_DIRECTORY;
   }

   public static Predicate isFile() {
      return Files.FilePredicate.IS_FILE;
   }

   private static final class FileByteSource extends ByteSource {
      private final File file;

      private FileByteSource(File file) {
         this.file = (File)Preconditions.checkNotNull(file);
      }

      public FileInputStream openStream() throws IOException {
         return new FileInputStream(this.file);
      }

      public Optional sizeIfKnown() {
         return this.file.isFile() ? Optional.of(this.file.length()) : Optional.absent();
      }

      public long size() throws IOException {
         if (!this.file.isFile()) {
            throw new FileNotFoundException(this.file.toString());
         } else {
            return this.file.length();
         }
      }

      public byte[] read() throws IOException {
         Closer closer = Closer.create();

         byte[] var3;
         try {
            FileInputStream in = (FileInputStream)closer.register(this.openStream());
            var3 = ByteStreams.toByteArray(in, in.getChannel().size());
         } catch (Throwable e) {
            throw closer.rethrow(e);
         } finally {
            closer.close();
         }

         return var3;
      }

      public String toString() {
         return "Files.asByteSource(" + this.file + ")";
      }
   }

   private static final class FileByteSink extends ByteSink {
      private final File file;
      private final ImmutableSet modes;

      private FileByteSink(File file, FileWriteMode... modes) {
         this.file = (File)Preconditions.checkNotNull(file);
         this.modes = ImmutableSet.copyOf((Object[])modes);
      }

      public FileOutputStream openStream() throws IOException {
         return new FileOutputStream(this.file, this.modes.contains(FileWriteMode.APPEND));
      }

      public String toString() {
         return "Files.asByteSink(" + this.file + ", " + this.modes + ")";
      }
   }

   private static enum FilePredicate implements Predicate {
      IS_DIRECTORY {
         public boolean apply(File file) {
            return file.isDirectory();
         }

         public String toString() {
            return "Files.isDirectory()";
         }
      },
      IS_FILE {
         public boolean apply(File file) {
            return file.isFile();
         }

         public String toString() {
            return "Files.isFile()";
         }
      };

      private FilePredicate() {
      }

      // $FF: synthetic method
      private static FilePredicate[] $values() {
         return new FilePredicate[]{IS_DIRECTORY, IS_FILE};
      }
   }
}
