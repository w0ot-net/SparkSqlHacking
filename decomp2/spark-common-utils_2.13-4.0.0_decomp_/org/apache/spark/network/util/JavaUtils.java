package org.apache.spark.network.util;

import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.SystemUtils;
import org.apache.spark.internal.LogKeys;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;

public class JavaUtils {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(JavaUtils.class);
   public static final long DEFAULT_DRIVER_MEM_MB = 1024L;
   private static final Map timeSuffixes;
   private static final Map byteSuffixes;
   private static final Pattern TIME_STRING_PATTERN;
   private static final Pattern BYTE_STRING_PATTERN;
   private static final Pattern BYTE_STRING_FRACTION_PATTERN;

   public static void closeQuietly(Closeable closeable) {
      try {
         if (closeable != null) {
            closeable.close();
         }
      } catch (IOException e) {
         logger.error("IOException should not have been thrown.", (Throwable)e);
      }

   }

   public static int nonNegativeHash(Object obj) {
      if (obj == null) {
         return 0;
      } else {
         int hash = obj.hashCode();
         return hash != Integer.MIN_VALUE ? Math.abs(hash) : 0;
      }
   }

   public static ByteBuffer stringToBytes(String s) {
      return ByteBuffer.wrap(s.getBytes(StandardCharsets.UTF_8));
   }

   public static String bytesToString(ByteBuffer b) {
      return StandardCharsets.UTF_8.decode(b.slice()).toString();
   }

   public static void deleteRecursively(File file) throws IOException, InterruptedException {
      deleteRecursively(file, (FilenameFilter)null);
   }

   public static void deleteRecursively(File file, FilenameFilter filter) throws IOException, InterruptedException {
      if (file != null) {
         if (SystemUtils.IS_OS_UNIX && filter == null && (!SystemUtils.IS_OS_MAC_OSX || System.getenv("SPARK_TESTING") == null && System.getProperty("spark.testing") == null)) {
            try {
               deleteRecursivelyUsingUnixNative(file);
               return;
            } catch (IOException e) {
               logger.warn("Attempt to delete using native Unix OS command failed for path = {}. Falling back to Java IO way", e, MDC.of(LogKeys.PATH$.MODULE$, file.getAbsolutePath()));
            }
         }

         deleteRecursivelyUsingJavaIO(file, filter);
      }
   }

   private static void deleteRecursivelyUsingJavaIO(File file, FilenameFilter filter) throws IOException, InterruptedException {
      BasicFileAttributes fileAttributes = readFileAttributes(file);
      if (fileAttributes != null && (file.exists() || fileAttributes.isSymbolicLink())) {
         if (fileAttributes.isDirectory()) {
            IOException savedIOException = null;

            for(File child : listFilesSafely(file, filter)) {
               try {
                  deleteRecursively(child, filter);
               } catch (IOException e) {
                  savedIOException = e;
               }
            }

            if (savedIOException != null) {
               throw savedIOException;
            }
         }

         if (fileAttributes.isRegularFile() || fileAttributes.isSymbolicLink() || fileAttributes.isDirectory() && listFilesSafely(file, (FilenameFilter)null).length == 0) {
            boolean deleted = file.delete();
            if (!deleted && file.exists()) {
               throw new IOException("Failed to delete: " + file.getAbsolutePath());
            }
         }

      }
   }

   private static BasicFileAttributes readFileAttributes(File file) {
      try {
         return Files.readAttributes(file.toPath(), BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
      } catch (IOException var2) {
         return null;
      }
   }

   private static void deleteRecursivelyUsingUnixNative(File file) throws InterruptedException, IOException {
      ProcessBuilder builder = new ProcessBuilder(new String[]{"rm", "-rf", file.getAbsolutePath()});
      Process process = null;
      int exitCode = -1;

      try {
         builder.redirectErrorStream(true);
         builder.redirectOutput(new File("/dev/null"));
         process = builder.start();
         exitCode = process.waitFor();
      } catch (InterruptedException e) {
         throw e;
      } catch (Exception e) {
         throw new IOException("Failed to delete: " + file.getAbsolutePath(), e);
      } finally {
         if (process != null) {
            process.destroy();
         }

      }

      if (exitCode != 0 || file.exists()) {
         throw new IOException("Failed to delete: " + file.getAbsolutePath());
      }
   }

   private static File[] listFilesSafely(File file, FilenameFilter filter) throws IOException {
      if (file.exists()) {
         File[] files = file.listFiles(filter);
         if (files == null) {
            throw new IOException("Failed to list files for dir: " + String.valueOf(file));
         } else {
            return files;
         }
      } else {
         return new File[0];
      }
   }

   public static long timeStringAs(String str, TimeUnit unit) {
      String lower = str.toLowerCase(Locale.ROOT).trim();

      try {
         Matcher m = TIME_STRING_PATTERN.matcher(lower);
         if (!m.matches()) {
            throw new NumberFormatException("Failed to parse time string: " + str);
         } else {
            long val = Long.parseLong(m.group(1));
            String suffix = m.group(2);
            if (suffix != null && !timeSuffixes.containsKey(suffix)) {
               throw new NumberFormatException("Invalid suffix: \"" + suffix + "\"");
            } else {
               return unit.convert(val, suffix != null ? (TimeUnit)timeSuffixes.get(suffix) : unit);
            }
         }
      } catch (NumberFormatException e) {
         String timeError = "Time must be specified as seconds (s), milliseconds (ms), microseconds (us), minutes (m or min), hour (h), or day (d). E.g. 50s, 100ms, or 250us.";
         throw new NumberFormatException(timeError + "\n" + e.getMessage());
      }
   }

   public static long timeStringAsMs(String str) {
      return timeStringAs(str, TimeUnit.MILLISECONDS);
   }

   public static long timeStringAsSec(String str) {
      return timeStringAs(str, TimeUnit.SECONDS);
   }

   public static long byteStringAs(String str, ByteUnit unit) {
      String lower = str.toLowerCase(Locale.ROOT).trim();

      try {
         Matcher m = BYTE_STRING_PATTERN.matcher(lower);
         Matcher fractionMatcher = BYTE_STRING_FRACTION_PATTERN.matcher(lower);
         if (m.matches()) {
            long val = Long.parseLong(m.group(1));
            String suffix = m.group(2);
            if (suffix != null && !byteSuffixes.containsKey(suffix)) {
               throw new NumberFormatException("Invalid suffix: \"" + suffix + "\"");
            } else {
               return unit.convertFrom(val, suffix != null ? (ByteUnit)byteSuffixes.get(suffix) : unit);
            }
         } else if (fractionMatcher.matches()) {
            throw new NumberFormatException("Fractional values are not supported. Input was: " + fractionMatcher.group(1));
         } else {
            throw new NumberFormatException("Failed to parse byte string: " + str);
         }
      } catch (NumberFormatException e) {
         String byteError = "Size must be specified as bytes (b), kibibytes (k), mebibytes (m), gibibytes (g), tebibytes (t), or pebibytes(p). E.g. 50b, 100k, or 250m.";
         throw new NumberFormatException(byteError + "\n" + e.getMessage());
      }
   }

   public static long byteStringAsBytes(String str) {
      return byteStringAs(str, ByteUnit.BYTE);
   }

   public static long byteStringAsKb(String str) {
      return byteStringAs(str, ByteUnit.KiB);
   }

   public static long byteStringAsMb(String str) {
      return byteStringAs(str, ByteUnit.MiB);
   }

   public static long byteStringAsGb(String str) {
      return byteStringAs(str, ByteUnit.GiB);
   }

   public static byte[] bufferToArray(ByteBuffer buffer) {
      if (buffer.hasArray() && buffer.arrayOffset() == 0 && buffer.array().length == buffer.remaining()) {
         return buffer.array();
      } else {
         byte[] bytes = new byte[buffer.remaining()];
         buffer.get(bytes);
         return bytes;
      }
   }

   public static File createDirectory(String root) throws IOException {
      return createDirectory(root, "spark");
   }

   public static File createDirectory(String root, String namePrefix) throws IOException {
      if (namePrefix == null) {
         namePrefix = "spark";
      }

      int attempts = 0;
      int maxAttempts = 10;
      File dir = null;

      while(dir == null) {
         ++attempts;
         if (attempts > maxAttempts) {
            throw new IOException("Failed to create a temp directory (under " + root + ") after " + maxAttempts + " attempts!");
         }

         try {
            dir = new File(root, namePrefix + "-" + String.valueOf(UUID.randomUUID()));
            Files.createDirectories(dir.toPath());
         } catch (SecurityException | IOException e) {
            logger.error("Failed to create directory {}", e, MDC.of(LogKeys.PATH$.MODULE$, dir));
            dir = null;
         }
      }

      return dir.getCanonicalFile();
   }

   public static void readFully(ReadableByteChannel channel, ByteBuffer dst) throws IOException {
      int expected = dst.remaining();

      while(dst.hasRemaining()) {
         if (channel.read(dst) < 0) {
            throw new EOFException(String.format("Not enough bytes in channel (expected %d).", expected));
         }
      }

   }

   static {
      timeSuffixes = Map.of("us", TimeUnit.MICROSECONDS, "ms", TimeUnit.MILLISECONDS, "s", TimeUnit.SECONDS, "m", TimeUnit.MINUTES, "min", TimeUnit.MINUTES, "h", TimeUnit.HOURS, "d", TimeUnit.DAYS);
      byteSuffixes = Map.ofEntries(Map.entry("b", ByteUnit.BYTE), Map.entry("k", ByteUnit.KiB), Map.entry("kb", ByteUnit.KiB), Map.entry("m", ByteUnit.MiB), Map.entry("mb", ByteUnit.MiB), Map.entry("g", ByteUnit.GiB), Map.entry("gb", ByteUnit.GiB), Map.entry("t", ByteUnit.TiB), Map.entry("tb", ByteUnit.TiB), Map.entry("p", ByteUnit.PiB), Map.entry("pb", ByteUnit.PiB));
      TIME_STRING_PATTERN = Pattern.compile("(-?[0-9]+)([a-z]+)?");
      BYTE_STRING_PATTERN = Pattern.compile("([0-9]+)([a-z]+)?");
      BYTE_STRING_FRACTION_PATTERN = Pattern.compile("([0-9]+\\.[0-9]+)([a-z]+)?");
   }
}
