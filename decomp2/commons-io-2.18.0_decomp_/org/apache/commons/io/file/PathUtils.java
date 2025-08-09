package org.apache.commons.io.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.AccessDeniedException;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.time.Duration;
import java.time.Instant;
import java.time.chrono.ChronoZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.RandomAccessFileMode;
import org.apache.commons.io.RandomAccessFiles;
import org.apache.commons.io.ThreadUtils;
import org.apache.commons.io.file.attribute.FileTimes;
import org.apache.commons.io.function.IOFunction;
import org.apache.commons.io.function.IOSupplier;

public final class PathUtils {
   private static final OpenOption[] OPEN_OPTIONS_TRUNCATE;
   private static final OpenOption[] OPEN_OPTIONS_APPEND;
   public static final CopyOption[] EMPTY_COPY_OPTIONS;
   public static final DeleteOption[] EMPTY_DELETE_OPTION_ARRAY;
   public static final FileAttribute[] EMPTY_FILE_ATTRIBUTE_ARRAY;
   public static final FileVisitOption[] EMPTY_FILE_VISIT_OPTION_ARRAY;
   public static final LinkOption[] EMPTY_LINK_OPTION_ARRAY;
   /** @deprecated */
   @Deprecated
   public static final LinkOption[] NOFOLLOW_LINK_OPTION_ARRAY;
   static final LinkOption NULL_LINK_OPTION;
   public static final OpenOption[] EMPTY_OPEN_OPTION_ARRAY;
   public static final Path[] EMPTY_PATH_ARRAY;

   private static AccumulatorPathVisitor accumulate(Path directory, int maxDepth, FileVisitOption[] fileVisitOptions) throws IOException {
      return (AccumulatorPathVisitor)visitFileTree(AccumulatorPathVisitor.withLongCounters(), directory, toFileVisitOptionSet(fileVisitOptions), maxDepth);
   }

   public static Counters.PathCounters cleanDirectory(Path directory) throws IOException {
      return cleanDirectory(directory, EMPTY_DELETE_OPTION_ARRAY);
   }

   public static Counters.PathCounters cleanDirectory(Path directory, DeleteOption... deleteOptions) throws IOException {
      return ((CleaningPathVisitor)visitFileTree(new CleaningPathVisitor(Counters.longPathCounters(), deleteOptions, new String[0]), (Path)directory)).getPathCounters();
   }

   private static int compareLastModifiedTimeTo(Path file, FileTime fileTime, LinkOption... options) throws IOException {
      return getLastModifiedTime(file, options).compareTo(fileTime);
   }

   public static long copy(IOSupplier in, Path target, CopyOption... copyOptions) throws IOException {
      InputStream inputStream = (InputStream)in.get();

      long var4;
      try {
         var4 = Files.copy(inputStream, target, copyOptions);
      } catch (Throwable var7) {
         if (inputStream != null) {
            try {
               inputStream.close();
            } catch (Throwable var6) {
               var7.addSuppressed(var6);
            }
         }

         throw var7;
      }

      if (inputStream != null) {
         inputStream.close();
      }

      return var4;
   }

   public static Counters.PathCounters copyDirectory(Path sourceDirectory, Path targetDirectory, CopyOption... copyOptions) throws IOException {
      Path absoluteSource = sourceDirectory.toAbsolutePath();
      return ((CopyDirectoryVisitor)visitFileTree(new CopyDirectoryVisitor(Counters.longPathCounters(), absoluteSource, targetDirectory, copyOptions), (Path)absoluteSource)).getPathCounters();
   }

   public static Path copyFile(URL sourceFile, Path targetFile, CopyOption... copyOptions) throws IOException {
      Objects.requireNonNull(sourceFile);
      copy(sourceFile::openStream, targetFile, copyOptions);
      return targetFile;
   }

   public static Path copyFileToDirectory(Path sourceFile, Path targetDirectory, CopyOption... copyOptions) throws IOException {
      return Files.copy(sourceFile, targetDirectory.resolve(sourceFile.getFileName()), copyOptions);
   }

   public static Path copyFileToDirectory(URL sourceFile, Path targetDirectory, CopyOption... copyOptions) throws IOException {
      Path resolve = targetDirectory.resolve(FilenameUtils.getName(sourceFile.getFile()));
      Objects.requireNonNull(sourceFile);
      copy(sourceFile::openStream, resolve, copyOptions);
      return resolve;
   }

   public static Counters.PathCounters countDirectory(Path directory) throws IOException {
      return ((CountingPathVisitor)visitFileTree(CountingPathVisitor.withLongCounters(), (Path)directory)).getPathCounters();
   }

   public static Counters.PathCounters countDirectoryAsBigInteger(Path directory) throws IOException {
      return ((CountingPathVisitor)visitFileTree(CountingPathVisitor.withBigIntegerCounters(), (Path)directory)).getPathCounters();
   }

   public static Path createParentDirectories(Path path, FileAttribute... attrs) throws IOException {
      return createParentDirectories(path, LinkOption.NOFOLLOW_LINKS, attrs);
   }

   public static Path createParentDirectories(Path path, LinkOption linkOption, FileAttribute... attrs) throws IOException {
      Path parent = getParent(path);
      parent = linkOption == LinkOption.NOFOLLOW_LINKS ? parent : readIfSymbolicLink(parent);
      if (parent == null) {
         return null;
      } else {
         boolean exists = linkOption == null ? Files.exists(parent, new LinkOption[0]) : Files.exists(parent, new LinkOption[]{linkOption});
         return exists ? parent : Files.createDirectories(parent, attrs);
      }
   }

   public static Path current() {
      return Paths.get(".");
   }

   public static Counters.PathCounters delete(Path path) throws IOException {
      return delete(path, EMPTY_DELETE_OPTION_ARRAY);
   }

   public static Counters.PathCounters delete(Path path, DeleteOption... deleteOptions) throws IOException {
      return Files.isDirectory(path, new LinkOption[]{LinkOption.NOFOLLOW_LINKS}) ? deleteDirectory(path, deleteOptions) : deleteFile(path, deleteOptions);
   }

   public static Counters.PathCounters delete(Path path, LinkOption[] linkOptions, DeleteOption... deleteOptions) throws IOException {
      return Files.isDirectory(path, linkOptions) ? deleteDirectory(path, linkOptions, deleteOptions) : deleteFile(path, linkOptions, deleteOptions);
   }

   public static Counters.PathCounters deleteDirectory(Path directory) throws IOException {
      return deleteDirectory(directory, EMPTY_DELETE_OPTION_ARRAY);
   }

   public static Counters.PathCounters deleteDirectory(Path directory, DeleteOption... deleteOptions) throws IOException {
      LinkOption[] linkOptions = noFollowLinkOptionArray();
      return (Counters.PathCounters)withPosixFileAttributes(getParent(directory), linkOptions, overrideReadOnly(deleteOptions), (pfa) -> ((DeletingPathVisitor)visitFileTree(new DeletingPathVisitor(Counters.longPathCounters(), linkOptions, deleteOptions, new String[0]), (Path)directory)).getPathCounters());
   }

   public static Counters.PathCounters deleteDirectory(Path directory, LinkOption[] linkOptions, DeleteOption... deleteOptions) throws IOException {
      return ((DeletingPathVisitor)visitFileTree(new DeletingPathVisitor(Counters.longPathCounters(), linkOptions, deleteOptions, new String[0]), (Path)directory)).getPathCounters();
   }

   public static Counters.PathCounters deleteFile(Path file) throws IOException {
      return deleteFile(file, EMPTY_DELETE_OPTION_ARRAY);
   }

   public static Counters.PathCounters deleteFile(Path file, DeleteOption... deleteOptions) throws IOException {
      return deleteFile(file, noFollowLinkOptionArray(), deleteOptions);
   }

   public static Counters.PathCounters deleteFile(Path file, LinkOption[] linkOptions, DeleteOption... deleteOptions) throws NoSuchFileException, IOException {
      if (Files.isDirectory(file, linkOptions)) {
         throw new NoSuchFileException(file.toString());
      } else {
         Counters.PathCounters pathCounts = Counters.longPathCounters();
         boolean exists = exists(file, linkOptions);
         long size = exists && !Files.isSymbolicLink(file) ? Files.size(file) : 0L;

         try {
            if (Files.deleteIfExists(file)) {
               pathCounts.getFileCounter().increment();
               pathCounts.getByteCounter().add(size);
               return pathCounts;
            }
         } catch (AccessDeniedException var12) {
         }

         Path parent = getParent(file);
         PosixFileAttributes posixFileAttributes = null;

         try {
            if (overrideReadOnly(deleteOptions)) {
               posixFileAttributes = readPosixFileAttributes(parent, linkOptions);
               setReadOnly(file, false, linkOptions);
            }

            exists = exists(file, linkOptions);
            size = exists && !Files.isSymbolicLink(file) ? Files.size(file) : 0L;
            if (Files.deleteIfExists(file)) {
               pathCounts.getFileCounter().increment();
               pathCounts.getByteCounter().add(size);
            }
         } finally {
            if (posixFileAttributes != null) {
               Files.setPosixFilePermissions(parent, posixFileAttributes.permissions());
            }

         }

         return pathCounts;
      }
   }

   public static void deleteOnExit(Path path) {
      ((Path)Objects.requireNonNull(path)).toFile().deleteOnExit();
   }

   public static boolean directoryAndFileContentEquals(Path path1, Path path2) throws IOException {
      return directoryAndFileContentEquals(path1, path2, EMPTY_LINK_OPTION_ARRAY, EMPTY_OPEN_OPTION_ARRAY, EMPTY_FILE_VISIT_OPTION_ARRAY);
   }

   public static boolean directoryAndFileContentEquals(Path path1, Path path2, LinkOption[] linkOptions, OpenOption[] openOptions, FileVisitOption[] fileVisitOption) throws IOException {
      if (path1 == null && path2 == null) {
         return true;
      } else if (path1 != null && path2 != null) {
         if (notExists(path1) && notExists(path2)) {
            return true;
         } else {
            RelativeSortedPaths relativeSortedPaths = new RelativeSortedPaths(path1, path2, Integer.MAX_VALUE, linkOptions, fileVisitOption);
            if (!relativeSortedPaths.equals) {
               return false;
            } else {
               List<Path> fileList1 = relativeSortedPaths.relativeFileList1;
               List<Path> fileList2 = relativeSortedPaths.relativeFileList2;

               for(Path path : fileList1) {
                  int binarySearch = Collections.binarySearch(fileList2, path);
                  if (binarySearch <= -1) {
                     throw new IllegalStateException("Unexpected mismatch.");
                  }

                  if (!fileContentEquals(path1.resolve(path), path2.resolve(path), linkOptions, openOptions)) {
                     return false;
                  }
               }

               return true;
            }
         }
      } else {
         return false;
      }
   }

   public static boolean directoryContentEquals(Path path1, Path path2) throws IOException {
      return directoryContentEquals(path1, path2, Integer.MAX_VALUE, EMPTY_LINK_OPTION_ARRAY, EMPTY_FILE_VISIT_OPTION_ARRAY);
   }

   public static boolean directoryContentEquals(Path path1, Path path2, int maxDepth, LinkOption[] linkOptions, FileVisitOption[] fileVisitOptions) throws IOException {
      return (new RelativeSortedPaths(path1, path2, maxDepth, linkOptions, fileVisitOptions)).equals;
   }

   private static boolean exists(Path path, LinkOption... options) {
      boolean var10000;
      label25: {
         if (path != null) {
            if (options != null) {
               if (Files.exists(path, options)) {
                  break label25;
               }
            } else if (Files.exists(path, new LinkOption[0])) {
               break label25;
            }
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   public static boolean fileContentEquals(Path path1, Path path2) throws IOException {
      return fileContentEquals(path1, path2, EMPTY_LINK_OPTION_ARRAY, EMPTY_OPEN_OPTION_ARRAY);
   }

   public static boolean fileContentEquals(Path path1, Path path2, LinkOption[] linkOptions, OpenOption[] openOptions) throws IOException {
      if (path1 == null && path2 == null) {
         return true;
      } else if (path1 != null && path2 != null) {
         Path nPath1 = path1.normalize();
         Path nPath2 = path2.normalize();
         boolean path1Exists = exists(nPath1, linkOptions);
         if (path1Exists != exists(nPath2, linkOptions)) {
            return false;
         } else if (!path1Exists) {
            return true;
         } else if (Files.isDirectory(nPath1, linkOptions)) {
            throw new IOException("Can't compare directories, only files: " + nPath1);
         } else if (Files.isDirectory(nPath2, linkOptions)) {
            throw new IOException("Can't compare directories, only files: " + nPath2);
         } else if (Files.size(nPath1) != Files.size(nPath2)) {
            return false;
         } else if (path1.equals(path2)) {
            return true;
         } else {
            try {
               RandomAccessFile raf1 = RandomAccessFileMode.READ_ONLY.create(path1.toRealPath(linkOptions));

               boolean var22;
               try {
                  RandomAccessFile raf2 = RandomAccessFileMode.READ_ONLY.create(path2.toRealPath(linkOptions));

                  try {
                     var22 = RandomAccessFiles.contentEquals(raf1, raf2);
                  } catch (Throwable var18) {
                     if (raf2 != null) {
                        try {
                           raf2.close();
                        } catch (Throwable var15) {
                           var18.addSuppressed(var15);
                        }
                     }

                     throw var18;
                  }

                  if (raf2 != null) {
                     raf2.close();
                  }
               } catch (Throwable var19) {
                  if (raf1 != null) {
                     try {
                        raf1.close();
                     } catch (Throwable var14) {
                        var19.addSuppressed(var14);
                     }
                  }

                  throw var19;
               }

               if (raf1 != null) {
                  raf1.close();
               }

               return var22;
            } catch (UnsupportedOperationException var20) {
               InputStream inputStream1 = Files.newInputStream(nPath1, openOptions);

               boolean var10;
               try {
                  InputStream inputStream2 = Files.newInputStream(nPath2, openOptions);

                  try {
                     var10 = IOUtils.contentEquals(inputStream1, inputStream2);
                  } catch (Throwable var16) {
                     if (inputStream2 != null) {
                        try {
                           inputStream2.close();
                        } catch (Throwable var13) {
                           var16.addSuppressed(var13);
                        }
                     }

                     throw var16;
                  }

                  if (inputStream2 != null) {
                     inputStream2.close();
                  }
               } catch (Throwable var17) {
                  if (inputStream1 != null) {
                     try {
                        inputStream1.close();
                     } catch (Throwable var12) {
                        var17.addSuppressed(var12);
                     }
                  }

                  throw var17;
               }

               if (inputStream1 != null) {
                  inputStream1.close();
               }

               return var10;
            }
         }
      } else {
         return false;
      }
   }

   public static Path[] filter(PathFilter filter, Path... paths) {
      Objects.requireNonNull(filter, "filter");
      return paths == null ? EMPTY_PATH_ARRAY : (Path[])((List)filterPaths(filter, Stream.of(paths), Collectors.toList())).toArray(EMPTY_PATH_ARRAY);
   }

   private static Object filterPaths(PathFilter filter, Stream stream, Collector collector) {
      Objects.requireNonNull(filter, "filter");
      Objects.requireNonNull(collector, "collector");
      return stream == null ? Stream.empty().collect(collector) : stream.filter((p) -> {
         try {
            return p != null && filter.accept(p, readBasicFileAttributes(p)) == FileVisitResult.CONTINUE;
         } catch (IOException var3) {
            return false;
         }
      }).collect(collector);
   }

   public static List getAclEntryList(Path sourcePath) throws IOException {
      AclFileAttributeView fileAttributeView = getAclFileAttributeView(sourcePath);
      return fileAttributeView == null ? null : fileAttributeView.getAcl();
   }

   public static AclFileAttributeView getAclFileAttributeView(Path path, LinkOption... options) {
      return (AclFileAttributeView)Files.getFileAttributeView(path, AclFileAttributeView.class, options);
   }

   public static String getBaseName(Path path) {
      if (path == null) {
         return null;
      } else {
         Path fileName = path.getFileName();
         return fileName != null ? FilenameUtils.removeExtension(fileName.toString()) : null;
      }
   }

   public static DosFileAttributeView getDosFileAttributeView(Path path, LinkOption... options) {
      return (DosFileAttributeView)Files.getFileAttributeView(path, DosFileAttributeView.class, options);
   }

   public static String getExtension(Path path) {
      String fileName = getFileNameString(path);
      return fileName != null ? FilenameUtils.getExtension(fileName) : null;
   }

   public static Object getFileName(Path path, Function function) {
      Path fileName = path != null ? path.getFileName() : null;
      return fileName != null ? function.apply(fileName) : null;
   }

   public static String getFileNameString(Path path) {
      return (String)getFileName(path, Path::toString);
   }

   public static FileTime getLastModifiedFileTime(File file) throws IOException {
      return getLastModifiedFileTime(file.toPath(), (FileTime)null, EMPTY_LINK_OPTION_ARRAY);
   }

   public static FileTime getLastModifiedFileTime(Path path, FileTime defaultIfAbsent, LinkOption... options) throws IOException {
      return Files.exists(path, new LinkOption[0]) ? getLastModifiedTime(path, options) : defaultIfAbsent;
   }

   public static FileTime getLastModifiedFileTime(Path path, LinkOption... options) throws IOException {
      return getLastModifiedFileTime(path, (FileTime)null, options);
   }

   public static FileTime getLastModifiedFileTime(URI uri) throws IOException {
      return getLastModifiedFileTime(Paths.get(uri), (FileTime)null, EMPTY_LINK_OPTION_ARRAY);
   }

   public static FileTime getLastModifiedFileTime(URL url) throws IOException, URISyntaxException {
      return getLastModifiedFileTime(url.toURI());
   }

   private static FileTime getLastModifiedTime(Path path, LinkOption... options) throws IOException {
      return Files.getLastModifiedTime((Path)Objects.requireNonNull(path, "path"), options);
   }

   private static Path getParent(Path path) {
      return path == null ? null : path.getParent();
   }

   public static PosixFileAttributeView getPosixFileAttributeView(Path path, LinkOption... options) {
      return (PosixFileAttributeView)Files.getFileAttributeView(path, PosixFileAttributeView.class, options);
   }

   public static Path getTempDirectory() {
      return Paths.get(FileUtils.getTempDirectoryPath());
   }

   public static boolean isDirectory(Path path, LinkOption... options) {
      return path != null && Files.isDirectory(path, options);
   }

   public static boolean isEmpty(Path path) throws IOException {
      return Files.isDirectory(path, new LinkOption[0]) ? isEmptyDirectory(path) : isEmptyFile(path);
   }

   public static boolean isEmptyDirectory(Path directory) throws IOException {
      DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directory);

      boolean var2;
      try {
         var2 = !directoryStream.iterator().hasNext();
      } catch (Throwable var5) {
         if (directoryStream != null) {
            try {
               directoryStream.close();
            } catch (Throwable var4) {
               var5.addSuppressed(var4);
            }
         }

         throw var5;
      }

      if (directoryStream != null) {
         directoryStream.close();
      }

      return var2;
   }

   public static boolean isEmptyFile(Path file) throws IOException {
      return Files.size(file) <= 0L;
   }

   public static boolean isNewer(Path file, ChronoZonedDateTime czdt, LinkOption... options) throws IOException {
      Objects.requireNonNull(czdt, "czdt");
      return isNewer(file, czdt.toInstant(), options);
   }

   public static boolean isNewer(Path file, FileTime fileTime, LinkOption... options) throws IOException {
      if (notExists(file)) {
         return false;
      } else {
         return compareLastModifiedTimeTo(file, fileTime, options) > 0;
      }
   }

   public static boolean isNewer(Path file, Instant instant, LinkOption... options) throws IOException {
      return isNewer(file, FileTime.from(instant), options);
   }

   public static boolean isNewer(Path file, long timeMillis, LinkOption... options) throws IOException {
      return isNewer(file, FileTime.fromMillis(timeMillis), options);
   }

   public static boolean isNewer(Path file, Path reference) throws IOException {
      return isNewer(file, getLastModifiedTime(reference));
   }

   public static boolean isOlder(Path file, FileTime fileTime, LinkOption... options) throws IOException {
      if (notExists(file)) {
         return false;
      } else {
         return compareLastModifiedTimeTo(file, fileTime, options) < 0;
      }
   }

   public static boolean isOlder(Path file, Instant instant, LinkOption... options) throws IOException {
      return isOlder(file, FileTime.from(instant), options);
   }

   public static boolean isOlder(Path file, long timeMillis, LinkOption... options) throws IOException {
      return isOlder(file, FileTime.fromMillis(timeMillis), options);
   }

   public static boolean isOlder(Path file, Path reference) throws IOException {
      return isOlder(file, getLastModifiedTime(reference));
   }

   public static boolean isPosix(Path test, LinkOption... options) {
      return exists(test, options) && readPosixFileAttributes(test, options) != null;
   }

   public static boolean isRegularFile(Path path, LinkOption... options) {
      return path != null && Files.isRegularFile(path, options);
   }

   public static DirectoryStream newDirectoryStream(Path dir, PathFilter pathFilter) throws IOException {
      return Files.newDirectoryStream(dir, new DirectoryStreamFilter(pathFilter));
   }

   public static OutputStream newOutputStream(Path path, boolean append) throws IOException {
      return newOutputStream(path, EMPTY_LINK_OPTION_ARRAY, append ? OPEN_OPTIONS_APPEND : OPEN_OPTIONS_TRUNCATE);
   }

   static OutputStream newOutputStream(Path path, LinkOption[] linkOptions, OpenOption... openOptions) throws IOException {
      if (!exists(path, linkOptions)) {
         createParentDirectories(path, linkOptions != null && linkOptions.length > 0 ? linkOptions[0] : NULL_LINK_OPTION);
      }

      List<OpenOption> list = new ArrayList(Arrays.asList(openOptions != null ? openOptions : EMPTY_OPEN_OPTION_ARRAY));
      list.addAll(Arrays.asList(linkOptions != null ? linkOptions : EMPTY_LINK_OPTION_ARRAY));
      return Files.newOutputStream(path, (OpenOption[])list.toArray(EMPTY_OPEN_OPTION_ARRAY));
   }

   public static LinkOption[] noFollowLinkOptionArray() {
      return (LinkOption[])NOFOLLOW_LINK_OPTION_ARRAY.clone();
   }

   private static boolean notExists(Path path, LinkOption... options) {
      return Files.notExists((Path)Objects.requireNonNull(path, "path"), options);
   }

   private static boolean overrideReadOnly(DeleteOption... deleteOptions) {
      return deleteOptions == null ? false : Stream.of(deleteOptions).anyMatch((e) -> e == StandardDeleteOption.OVERRIDE_READ_ONLY);
   }

   public static BasicFileAttributes readAttributes(Path path, Class type, LinkOption... options) {
      try {
         return path == null ? null : Files.readAttributes(path, type, options);
      } catch (IOException | UnsupportedOperationException var4) {
         return null;
      }
   }

   public static BasicFileAttributes readBasicFileAttributes(Path path) throws IOException {
      return Files.readAttributes(path, BasicFileAttributes.class);
   }

   public static BasicFileAttributes readBasicFileAttributes(Path path, LinkOption... options) {
      return readAttributes(path, BasicFileAttributes.class, options);
   }

   /** @deprecated */
   @Deprecated
   public static BasicFileAttributes readBasicFileAttributesUnchecked(Path path) {
      return readBasicFileAttributes(path, EMPTY_LINK_OPTION_ARRAY);
   }

   public static DosFileAttributes readDosFileAttributes(Path path, LinkOption... options) {
      return (DosFileAttributes)readAttributes(path, DosFileAttributes.class, options);
   }

   private static Path readIfSymbolicLink(Path path) throws IOException {
      return path != null ? (Files.isSymbolicLink(path) ? Files.readSymbolicLink(path) : path) : null;
   }

   public static BasicFileAttributes readOsFileAttributes(Path path, LinkOption... options) {
      PosixFileAttributes fileAttributes = readPosixFileAttributes(path, options);
      return (BasicFileAttributes)(fileAttributes != null ? fileAttributes : readDosFileAttributes(path, options));
   }

   public static PosixFileAttributes readPosixFileAttributes(Path path, LinkOption... options) {
      return (PosixFileAttributes)readAttributes(path, PosixFileAttributes.class, options);
   }

   public static String readString(Path path, Charset charset) throws IOException {
      return new String(Files.readAllBytes(path), Charsets.toCharset(charset));
   }

   static List relativize(Collection collection, Path parent, boolean sort, Comparator comparator) {
      Stream var10000 = collection.stream();
      Objects.requireNonNull(parent);
      Stream<Path> stream = var10000.map(parent::relativize);
      if (sort) {
         stream = comparator == null ? stream.sorted() : stream.sorted(comparator);
      }

      return (List)stream.collect(Collectors.toList());
   }

   private static Path requireExists(Path file, String fileParamName, LinkOption... options) {
      Objects.requireNonNull(file, fileParamName);
      if (!exists(file, options)) {
         throw new IllegalArgumentException("File system element for parameter '" + fileParamName + "' does not exist: '" + file + "'");
      } else {
         return file;
      }
   }

   private static boolean setDosReadOnly(Path path, boolean readOnly, LinkOption... linkOptions) throws IOException {
      DosFileAttributeView dosFileAttributeView = getDosFileAttributeView(path, linkOptions);
      if (dosFileAttributeView != null) {
         dosFileAttributeView.setReadOnly(readOnly);
         return true;
      } else {
         return false;
      }
   }

   public static void setLastModifiedTime(Path sourceFile, Path targetFile) throws IOException {
      Objects.requireNonNull(sourceFile, "sourceFile");
      Files.setLastModifiedTime(targetFile, getLastModifiedTime(sourceFile));
   }

   private static boolean setPosixDeletePermissions(Path parent, boolean enableDeleteChildren, LinkOption... linkOptions) throws IOException {
      return setPosixPermissions(parent, enableDeleteChildren, Arrays.asList(PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_EXECUTE), linkOptions);
   }

   private static boolean setPosixPermissions(Path path, boolean addPermissions, List updatePermissions, LinkOption... linkOptions) throws IOException {
      if (path != null) {
         Set<PosixFilePermission> permissions = Files.getPosixFilePermissions(path, linkOptions);
         Set<PosixFilePermission> newPermissions = new HashSet(permissions);
         if (addPermissions) {
            newPermissions.addAll(updatePermissions);
         } else {
            newPermissions.removeAll(updatePermissions);
         }

         if (!newPermissions.equals(permissions)) {
            Files.setPosixFilePermissions(path, newPermissions);
         }

         return true;
      } else {
         return false;
      }
   }

   private static void setPosixReadOnlyFile(Path path, boolean readOnly, LinkOption... linkOptions) throws IOException {
      Set<PosixFilePermission> permissions = Files.getPosixFilePermissions(path, linkOptions);
      List<PosixFilePermission> readPermissions = Arrays.asList(PosixFilePermission.OWNER_READ);
      List<PosixFilePermission> writePermissions = Arrays.asList(PosixFilePermission.OWNER_WRITE);
      if (readOnly) {
         permissions.addAll(readPermissions);
         permissions.removeAll(writePermissions);
      } else {
         permissions.addAll(readPermissions);
         permissions.addAll(writePermissions);
      }

      Files.setPosixFilePermissions(path, permissions);
   }

   public static Path setReadOnly(Path path, boolean readOnly, LinkOption... linkOptions) throws IOException {
      try {
         if (setDosReadOnly(path, readOnly, linkOptions)) {
            return path;
         }
      } catch (IOException var4) {
      }

      Path parent = getParent(path);
      if (!isPosix(parent, linkOptions)) {
         throw new IOException(String.format("DOS or POSIX file operations not available for '%s', linkOptions %s", path, Arrays.toString(linkOptions)));
      } else {
         if (readOnly) {
            setPosixReadOnlyFile(path, readOnly, linkOptions);
            setPosixDeletePermissions(parent, false, linkOptions);
         } else {
            setPosixDeletePermissions(parent, true, linkOptions);
         }

         return path;
      }
   }

   public static long sizeOf(Path path) throws IOException {
      requireExists(path, "path");
      return Files.isDirectory(path, new LinkOption[0]) ? sizeOfDirectory(path) : Files.size(path);
   }

   public static BigInteger sizeOfAsBigInteger(Path path) throws IOException {
      requireExists(path, "path");
      return Files.isDirectory(path, new LinkOption[0]) ? sizeOfDirectoryAsBigInteger(path) : BigInteger.valueOf(Files.size(path));
   }

   public static long sizeOfDirectory(Path directory) throws IOException {
      return countDirectory(directory).getByteCounter().getLong();
   }

   public static BigInteger sizeOfDirectoryAsBigInteger(Path directory) throws IOException {
      return countDirectoryAsBigInteger(directory).getByteCounter().getBigInteger();
   }

   static Set toFileVisitOptionSet(FileVisitOption... fileVisitOptions) {
      return (Set)(fileVisitOptions == null ? EnumSet.noneOf(FileVisitOption.class) : (Set)Stream.of(fileVisitOptions).collect(Collectors.toSet()));
   }

   public static Path touch(Path file) throws IOException {
      Objects.requireNonNull(file, "file");
      if (!Files.exists(file, new LinkOption[0])) {
         createParentDirectories(file);
         Files.createFile(file);
      } else {
         FileTimes.setLastModifiedTime(file);
      }

      return file;
   }

   public static FileVisitor visitFileTree(FileVisitor visitor, Path directory) throws IOException {
      Files.walkFileTree(directory, visitor);
      return visitor;
   }

   public static FileVisitor visitFileTree(FileVisitor visitor, Path start, Set options, int maxDepth) throws IOException {
      Files.walkFileTree(start, options, maxDepth, visitor);
      return visitor;
   }

   public static FileVisitor visitFileTree(FileVisitor visitor, String first, String... more) throws IOException {
      return visitFileTree(visitor, Paths.get(first, more));
   }

   public static FileVisitor visitFileTree(FileVisitor visitor, URI uri) throws IOException {
      return visitFileTree(visitor, Paths.get(uri));
   }

   public static boolean waitFor(Path file, Duration timeout, LinkOption... options) {
      Objects.requireNonNull(file, "file");
      Instant finishInstant = Instant.now().plus(timeout);
      boolean interrupted = false;
      long minSleepMillis = 100L;

      try {
         while(!exists(file, options)) {
            Instant now = Instant.now();
            if (now.isAfter(finishInstant)) {
               boolean var8 = false;
               return var8;
            } else {
               try {
                  ThreadUtils.sleep(Duration.ofMillis(Math.min(100L, finishInstant.minusMillis(now.toEpochMilli()).toEpochMilli())));
               } catch (InterruptedException var13) {
                  interrupted = true;
               } catch (Exception var14) {
                  return exists(file, options);
               }
            }
         }

         return exists(file, options);
      } finally {
         if (interrupted) {
            Thread.currentThread().interrupt();
         }

      }
   }

   public static Stream walk(Path start, PathFilter pathFilter, int maxDepth, boolean readAttributes, FileVisitOption... options) throws IOException {
      return Files.walk(start, maxDepth, options).filter((path) -> pathFilter.accept(path, readAttributes ? readBasicFileAttributesUnchecked(path) : null) == FileVisitResult.CONTINUE);
   }

   private static Object withPosixFileAttributes(Path path, LinkOption[] linkOptions, boolean overrideReadOnly, IOFunction function) throws IOException {
      PosixFileAttributes posixFileAttributes = overrideReadOnly ? readPosixFileAttributes(path, linkOptions) : null;

      Object var5;
      try {
         var5 = function.apply(posixFileAttributes);
      } finally {
         if (posixFileAttributes != null && path != null && Files.exists(path, linkOptions)) {
            Files.setPosixFilePermissions(path, posixFileAttributes.permissions());
         }

      }

      return var5;
   }

   public static Path writeString(Path path, CharSequence charSequence, Charset charset, OpenOption... openOptions) throws IOException {
      Objects.requireNonNull(path, "path");
      Objects.requireNonNull(charSequence, "charSequence");
      Files.write(path, String.valueOf(charSequence).getBytes(Charsets.toCharset(charset)), openOptions);
      return path;
   }

   private PathUtils() {
   }

   static {
      OPEN_OPTIONS_TRUNCATE = new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING};
      OPEN_OPTIONS_APPEND = new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.APPEND};
      EMPTY_COPY_OPTIONS = new CopyOption[0];
      EMPTY_DELETE_OPTION_ARRAY = new DeleteOption[0];
      EMPTY_FILE_ATTRIBUTE_ARRAY = new FileAttribute[0];
      EMPTY_FILE_VISIT_OPTION_ARRAY = new FileVisitOption[0];
      EMPTY_LINK_OPTION_ARRAY = new LinkOption[0];
      NOFOLLOW_LINK_OPTION_ARRAY = new LinkOption[]{LinkOption.NOFOLLOW_LINKS};
      NULL_LINK_OPTION = null;
      EMPTY_OPEN_OPTION_ARRAY = new OpenOption[0];
      EMPTY_PATH_ARRAY = new Path[0];
   }

   private static final class RelativeSortedPaths {
      final boolean equals;
      final List relativeFileList1;
      final List relativeFileList2;

      private RelativeSortedPaths(Path dir1, Path dir2, int maxDepth, LinkOption[] linkOptions, FileVisitOption[] fileVisitOptions) throws IOException {
         List<Path> tmpRelativeFileList1 = null;
         List<Path> tmpRelativeFileList2 = null;
         if (dir1 == null && dir2 == null) {
            this.equals = true;
         } else if (dir1 == null ^ dir2 == null) {
            this.equals = false;
         } else {
            boolean parentDirNotExists1 = Files.notExists(dir1, linkOptions);
            boolean parentDirNotExists2 = Files.notExists(dir2, linkOptions);
            if (!parentDirNotExists1 && !parentDirNotExists2) {
               AccumulatorPathVisitor visitor1 = PathUtils.accumulate(dir1, maxDepth, fileVisitOptions);
               AccumulatorPathVisitor visitor2 = PathUtils.accumulate(dir2, maxDepth, fileVisitOptions);
               if (visitor1.getDirList().size() == visitor2.getDirList().size() && visitor1.getFileList().size() == visitor2.getFileList().size()) {
                  List<Path> tmpRelativeDirList1 = visitor1.relativizeDirectories(dir1, true, (Comparator)null);
                  List<Path> tmpRelativeDirList2 = visitor2.relativizeDirectories(dir2, true, (Comparator)null);
                  if (!tmpRelativeDirList1.equals(tmpRelativeDirList2)) {
                     this.equals = false;
                  } else {
                     tmpRelativeFileList1 = visitor1.relativizeFiles(dir1, true, (Comparator)null);
                     tmpRelativeFileList2 = visitor2.relativizeFiles(dir2, true, (Comparator)null);
                     this.equals = tmpRelativeFileList1.equals(tmpRelativeFileList2);
                  }
               } else {
                  this.equals = false;
               }
            } else {
               this.equals = parentDirNotExists1 && parentDirNotExists2;
            }
         }

         this.relativeFileList1 = tmpRelativeFileList1;
         this.relativeFileList2 = tmpRelativeFileList2;
      }
   }
}
