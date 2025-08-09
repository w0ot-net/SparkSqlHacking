package org.apache.commons.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/** @deprecated */
@Deprecated
public class FileSystemUtils {
   /** @deprecated */
   @Deprecated
   public static long freeSpace(String path) throws IOException {
      return getFreeSpace(path);
   }

   /** @deprecated */
   @Deprecated
   public static long freeSpaceKb() throws IOException {
      return freeSpaceKb(-1L);
   }

   /** @deprecated */
   @Deprecated
   public static long freeSpaceKb(long timeout) throws IOException {
      return freeSpaceKb(FileUtils.current().getAbsolutePath(), timeout);
   }

   /** @deprecated */
   @Deprecated
   public static long freeSpaceKb(String path) throws IOException {
      return freeSpaceKb(path, -1L);
   }

   /** @deprecated */
   @Deprecated
   public static long freeSpaceKb(String path, long timeout) throws IOException {
      return getFreeSpace(path) / 1024L;
   }

   static long getFreeSpace(String pathStr) throws IOException {
      Path path = Paths.get((String)Objects.requireNonNull(pathStr, "pathStr"));
      if (Files.exists(path, new LinkOption[0])) {
         return Files.getFileStore(path.toAbsolutePath()).getUsableSpace();
      } else {
         throw new IllegalArgumentException(path.toString());
      }
   }
}
