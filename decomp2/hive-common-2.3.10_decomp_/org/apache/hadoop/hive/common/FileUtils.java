package org.apache.hadoop.hive.common;

import com.google.common.annotations.VisibleForTesting;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.AccessControlException;
import java.security.PrivilegedExceptionAction;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.ContentSummary;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.GlobFilter;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.hadoop.fs.Trash;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.io.HdfsUtils;
import org.apache.hadoop.hive.shims.HadoopShims;
import org.apache.hadoop.hive.shims.ShimLoader;
import org.apache.hadoop.hive.shims.Utils;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.util.StringUtils;
import org.apache.hive.common.util.ShutdownHookManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FileUtils {
   private static final Logger LOG = LoggerFactory.getLogger(FileUtils.class.getName());
   private static final Random random = new Random();
   public static final PathFilter HIDDEN_FILES_PATH_FILTER = new PathFilter() {
      public boolean accept(Path p) {
         String name = p.getName();
         return !name.startsWith("_") && !name.startsWith(".");
      }
   };
   public static final PathFilter STAGING_DIR_PATH_FILTER = new PathFilter() {
      public boolean accept(Path p) {
         String name = p.getName();
         return !name.startsWith(".");
      }
   };
   static BitSet charToEscape = new BitSet(128);

   public static Path makeQualified(Path path, Configuration conf) throws IOException {
      if (!path.isAbsolute()) {
         return path.makeQualified(FileSystem.get(conf));
      } else {
         URI fsUri = FileSystem.getDefaultUri(conf);
         URI pathUri = path.toUri();
         String scheme = pathUri.getScheme();
         String authority = pathUri.getAuthority();
         if (scheme == null) {
            scheme = fsUri.getScheme();
            authority = fsUri.getAuthority();
            if (authority == null) {
               authority = "";
            }
         } else if (authority == null) {
            if (scheme.equals(fsUri.getScheme()) && fsUri.getAuthority() != null) {
               authority = fsUri.getAuthority();
            } else {
               authority = "";
            }
         }

         return new Path(scheme, authority, pathUri.getPath());
      }
   }

   private FileUtils() {
   }

   public static String makePartName(List partCols, List vals) {
      return makePartName(partCols, vals, (String)null);
   }

   public static String makePartName(List partCols, List vals, String defaultStr) {
      StringBuilder name = new StringBuilder();

      for(int i = 0; i < partCols.size(); ++i) {
         if (i > 0) {
            name.append("/");
         }

         name.append(escapePathName(((String)partCols.get(i)).toLowerCase(), defaultStr));
         name.append('=');
         name.append(escapePathName((String)vals.get(i), defaultStr));
      }

      return name.toString();
   }

   public static String makeDefaultListBucketingDirName(List skewedCols, String name) {
      String defaultDir = escapePathName(name);
      StringBuilder defaultDirPath = new StringBuilder();

      for(int i = 0; i < skewedCols.size(); ++i) {
         if (i > 0) {
            defaultDirPath.append("/");
         }

         defaultDirPath.append(defaultDir);
      }

      String lbDirName = defaultDirPath.toString();
      return lbDirName;
   }

   public static String makeListBucketingDirName(List lbCols, List vals) {
      StringBuilder name = new StringBuilder();

      for(int i = 0; i < lbCols.size(); ++i) {
         if (i > 0) {
            name.append("/");
         }

         name.append(escapePathName(((String)lbCols.get(i)).toLowerCase()));
         name.append('=');
         name.append(escapePathName((String)vals.get(i)));
      }

      return name.toString();
   }

   static boolean needsEscaping(char c) {
      return c >= 0 && c < charToEscape.size() && charToEscape.get(c);
   }

   public static String escapePathName(String path) {
      return escapePathName(path, (String)null);
   }

   public static String escapePathName(String path, String defaultPath) {
      if (path != null && path.length() != 0) {
         StringBuilder sb = new StringBuilder();

         for(int i = 0; i < path.length(); ++i) {
            char c = path.charAt(i);
            if (needsEscaping(c)) {
               sb.append('%');
               sb.append(String.format("%1$02X", Integer.valueOf(c)));
            } else {
               sb.append(c);
            }
         }

         return sb.toString();
      } else {
         return defaultPath == null ? "__HIVE_DEFAULT_PARTITION__" : defaultPath;
      }
   }

   public static String unescapePathName(String path) {
      StringBuilder sb = new StringBuilder();

      for(int i = 0; i < path.length(); ++i) {
         char c = path.charAt(i);
         if (c == '%' && i + 2 < path.length()) {
            int code = -1;

            try {
               code = Integer.parseInt(path.substring(i + 1, i + 3), 16);
            } catch (Exception var6) {
               code = -1;
            }

            if (code >= 0) {
               sb.append((char)code);
               i += 2;
               continue;
            }
         }

         sb.append(c);
      }

      return sb.toString();
   }

   public static void listStatusRecursively(FileSystem fs, FileStatus fileStatus, List results) throws IOException {
      if (fileStatus.isDir()) {
         for(FileStatus stat : fs.listStatus(fileStatus.getPath(), HIDDEN_FILES_PATH_FILTER)) {
            listStatusRecursively(fs, stat, results);
         }
      } else {
         results.add(fileStatus);
      }

   }

   public static FileStatus getPathOrParentThatExists(FileSystem fs, Path path) throws IOException {
      FileStatus stat = getFileStatusOrNull(fs, path);
      if (stat != null) {
         return stat;
      } else {
         Path parentPath = path.getParent();
         return getPathOrParentThatExists(fs, parentPath);
      }
   }

   public static void checkFileAccessWithImpersonation(final FileSystem fs, final FileStatus stat, final FsAction action, String user) throws IOException, AccessControlException, InterruptedException, Exception {
      UserGroupInformation ugi = Utils.getUGI();
      String currentUser = ugi.getShortUserName();
      if (user != null && !currentUser.equals(user)) {
         UserGroupInformation proxyUser = UserGroupInformation.createProxyUser(user, UserGroupInformation.getLoginUser());

         try {
            proxyUser.doAs(new PrivilegedExceptionAction() {
               public Object run() throws Exception {
                  FileSystem fsAsUser = FileSystem.get(fs.getUri(), fs.getConf());
                  ShimLoader.getHadoopShims().checkFileAccess(fsAsUser, stat, action);
                  return null;
               }
            });
         } finally {
            FileSystem.closeAllForUGI(proxyUser);
         }

      } else {
         ShimLoader.getHadoopShims().checkFileAccess(fs, stat, action);
      }
   }

   public static boolean isActionPermittedForFileHierarchy(FileSystem fs, FileStatus fileStatus, String userName, FsAction action) throws Exception {
      return isActionPermittedForFileHierarchy(fs, fileStatus, userName, action, true);
   }

   public static boolean isActionPermittedForFileHierarchy(FileSystem fs, FileStatus fileStatus, String userName, FsAction action, boolean recurse) throws Exception {
      boolean isDir = fileStatus.isDir();
      if (isDir) {
         action.and(FsAction.EXECUTE);
      }

      try {
         checkFileAccessWithImpersonation(fs, fileStatus, action, userName);
      } catch (AccessControlException var12) {
         return false;
      }

      if (isDir && recurse) {
         FileStatus[] childStatuses = fs.listStatus(fileStatus.getPath());

         for(FileStatus childStatus : childStatuses) {
            if (!isActionPermittedForFileHierarchy(fs, childStatus, userName, action, true)) {
               return false;
            }
         }

         return true;
      } else {
         return true;
      }
   }

   public static boolean isLocalFile(HiveConf conf, String fileName) {
      try {
         return isLocalFile(conf, new URI(fileName));
      } catch (URISyntaxException e) {
         LOG.warn("Unable to create URI from " + fileName, e);
         return false;
      }
   }

   public static boolean isLocalFile(HiveConf conf, URI fileUri) {
      try {
         FileSystem fsForFile = FileSystem.get(fileUri, conf);
         return LocalFileSystem.class.isInstance(fsForFile);
      } catch (IOException e) {
         LOG.warn("Unable to get FileSystem for " + fileUri, e);
         return false;
      }
   }

   public static boolean isOwnerOfFileHierarchy(FileSystem fs, FileStatus fileStatus, String userName) throws IOException {
      return isOwnerOfFileHierarchy(fs, fileStatus, userName, true);
   }

   public static boolean isOwnerOfFileHierarchy(FileSystem fs, FileStatus fileStatus, String userName, boolean recurse) throws IOException {
      if (!fileStatus.getOwner().equals(userName)) {
         return false;
      } else if (fileStatus.isDir() && recurse) {
         FileStatus[] childStatuses = fs.listStatus(fileStatus.getPath());

         for(FileStatus childStatus : childStatuses) {
            if (!isOwnerOfFileHierarchy(fs, childStatus, userName, true)) {
               return false;
            }
         }

         return true;
      } else {
         return true;
      }
   }

   public static boolean mkdir(FileSystem fs, Path f, boolean inheritPerms, Configuration conf) throws IOException {
      LOG.info("Creating directory if it doesn't exist: " + f);
      if (!inheritPerms) {
         return fs.mkdirs(f);
      } else {
         try {
            return fs.getFileStatus(f).isDir();
         } catch (FileNotFoundException var7) {
            Path lastExistingParent = f;

            Path firstNonExistentParent;
            for(firstNonExistentParent = null; !fs.exists(lastExistingParent); lastExistingParent = lastExistingParent.getParent()) {
               firstNonExistentParent = lastExistingParent;
            }

            boolean success = fs.mkdirs(f);
            if (!success) {
               return false;
            } else {
               if (inheritPerms) {
                  HdfsUtils.setFullFileStatus(conf, new HdfsUtils.HadoopFileStatus(conf, fs, lastExistingParent), fs, firstNonExistentParent, true);
               }

               return true;
            }
         }
      }
   }

   public static Path makeAbsolute(FileSystem fileSystem, Path path) throws IOException {
      return path.isAbsolute() ? path : new Path(fileSystem.getWorkingDirectory(), path);
   }

   public static boolean copy(FileSystem srcFS, Path src, FileSystem dstFS, Path dst, boolean deleteSource, boolean overwrite, HiveConf conf) throws IOException {
      return copy(srcFS, src, dstFS, dst, deleteSource, overwrite, conf, ShimLoader.getHadoopShims());
   }

   @VisibleForTesting
   static boolean copy(FileSystem srcFS, Path src, FileSystem dstFS, Path dst, boolean deleteSource, boolean overwrite, HiveConf conf, HadoopShims shims) throws IOException {
      boolean copied = false;
      boolean triedDistcp = false;
      if (srcFS.getUri().getScheme().equals("hdfs")) {
         ContentSummary srcContentSummary = srcFS.getContentSummary(src);
         if (srcContentSummary.getFileCount() > conf.getLongVar(HiveConf.ConfVars.HIVE_EXEC_COPYFILE_MAXNUMFILES) && srcContentSummary.getLength() > conf.getLongVar(HiveConf.ConfVars.HIVE_EXEC_COPYFILE_MAXSIZE)) {
            LOG.info("Source is " + srcContentSummary.getLength() + " bytes. (MAX: " + conf.getLongVar(HiveConf.ConfVars.HIVE_EXEC_COPYFILE_MAXSIZE) + ")");
            LOG.info("Source is " + srcContentSummary.getFileCount() + " files. (MAX: " + conf.getLongVar(HiveConf.ConfVars.HIVE_EXEC_COPYFILE_MAXNUMFILES) + ")");
            LOG.info("Launch distributed copy (distcp) job.");
            triedDistcp = true;
            copied = shims.runDistCp(src, dst, conf);
            if (copied && deleteSource) {
               srcFS.delete(src, true);
            }
         }
      }

      if (!triedDistcp) {
         copied = FileUtil.copy(srcFS, src, dstFS, dst, deleteSource, overwrite, conf);
      }

      boolean inheritPerms = conf.getBoolVar(HiveConf.ConfVars.HIVE_WAREHOUSE_SUBDIR_INHERIT_PERMS);
      if (copied && inheritPerms) {
         HdfsUtils.setFullFileStatus(conf, new HdfsUtils.HadoopFileStatus(conf, dstFS, dst.getParent()), dstFS, dst, true);
      }

      return copied;
   }

   public static boolean moveToTrash(FileSystem fs, Path f, Configuration conf, boolean purge) throws IOException {
      LOG.debug("deleting  " + f);
      boolean result = false;

      try {
         if (purge) {
            LOG.debug("purge is set to true. Not moving to Trash " + f);
         } else {
            result = Trash.moveToAppropriateTrash(fs, f, conf);
            if (result) {
               LOG.trace("Moved to trash: " + f);
               return true;
            }
         }
      } catch (IOException ioe) {
         LOG.warn(ioe.getMessage() + "; Force to delete it.");
      }

      result = fs.delete(f, true);
      if (!result) {
         LOG.error("Failed to delete " + f);
      }

      return result;
   }

   public static boolean renameWithPerms(FileSystem fs, Path sourcePath, Path destPath, boolean inheritPerms, Configuration conf) throws IOException {
      LOG.info("Renaming " + sourcePath + " to " + destPath);
      if (fs.exists(destPath)) {
         throw new IOException("Cannot rename the source path. The destination path already exists.");
      } else if (!inheritPerms) {
         return fs.rename(sourcePath, destPath);
      } else if (fs.rename(sourcePath, destPath)) {
         HdfsUtils.setFullFileStatus(conf, new HdfsUtils.HadoopFileStatus(conf, fs, destPath.getParent()), fs, destPath, true);
         return true;
      } else {
         return false;
      }
   }

   public static boolean equalsFileSystem(FileSystem fs1, FileSystem fs2) {
      return fs1.getUri().equals(fs2.getUri());
   }

   public static void checkDeletePermission(Path path, Configuration conf, String user) throws AccessControlException, InterruptedException, Exception {
      if (path != null) {
         FileSystem fs = path.getFileSystem(conf);
         FileStatus stat = null;

         try {
            stat = fs.getFileStatus(path);
         } catch (FileNotFoundException var9) {
         }

         if (stat != null) {
            checkFileAccessWithImpersonation(fs, stat, FsAction.WRITE, user);
            HadoopShims shims = ShimLoader.getHadoopShims();
            if (shims.supportStickyBit()) {
               FileStatus parStatus = fs.getFileStatus(path.getParent());
               if (shims.hasStickyBit(parStatus.getPermission())) {
                  if (!parStatus.getOwner().equals(user)) {
                     FileStatus childStatus = fs.getFileStatus(path);
                     if (!childStatus.getOwner().equals(user)) {
                        String msg = String.format("Permission Denied: User %s can't delete %s because sticky bit is set on the parent dir and user does not own this file or its parent", user, path);
                        throw new IOException(msg);
                     }
                  }
               }
            }
         }
      }
   }

   public static FileStatus getFileStatusOrNull(FileSystem fs, Path path) throws IOException {
      try {
         return fs.getFileStatus(path);
      } catch (FileNotFoundException var3) {
         return null;
      }
   }

   public static void deleteDirectory(File directory) throws IOException {
      org.apache.commons.io.FileUtils.deleteDirectory(directory);
   }

   public static File createTempFile(String lScratchDir, String prefix, String suffix) throws IOException {
      File tmpDir = lScratchDir == null ? null : new File(lScratchDir);
      if (tmpDir != null && !tmpDir.exists() && !tmpDir.mkdirs() && !tmpDir.exists()) {
         throw new RuntimeException("Unable to create temp directory " + lScratchDir);
      } else {
         File tmpFile = File.createTempFile(prefix, suffix, tmpDir);
         ShutdownHookManager.deleteOnExit(tmpFile);
         return tmpFile;
      }
   }

   public static File createLocalDirsTempFile(String localDirList, String prefix, String suffix, boolean isDirectory) throws IOException {
      if (localDirList != null && !localDirList.isEmpty()) {
         String[] localDirs = StringUtils.getTrimmedStrings(localDirList);
         if (localDirs.length == 0) {
            return createFileInTmp(prefix, suffix, "Local directories not specified", isDirectory);
         } else {
            String path = localDirs[random.nextInt(localDirs.length)];
            if (path != null && !path.isEmpty()) {
               File targetDir = new File(path);
               if (!targetDir.exists() && !targetDir.mkdirs()) {
                  return createFileInTmp(prefix, suffix, "Cannot access or create " + targetDir, isDirectory);
               } else {
                  try {
                     File file = File.createTempFile(prefix, suffix, targetDir);
                     if (!isDirectory || file.delete() && file.mkdirs()) {
                        file.deleteOnExit();
                        return file;
                     } else {
                        return createFileInTmp(prefix, suffix, "Cannot recreate " + file + " as directory", isDirectory);
                     }
                  } catch (IOException ex) {
                     LOG.error("Error creating a file in " + targetDir, ex);
                     return createFileInTmp(prefix, suffix, "Cannot create a file in " + targetDir, isDirectory);
                  }
               }
            } else {
               return createFileInTmp(prefix, suffix, "Empty path for one of the local dirs", isDirectory);
            }
         }
      } else {
         return createFileInTmp(prefix, suffix, "Local directories not specified", isDirectory);
      }
   }

   private static File createFileInTmp(String prefix, String suffix, String reason, boolean isDirectory) throws IOException {
      File file = File.createTempFile(prefix, suffix);
      if (!isDirectory || file.delete() && file.mkdirs()) {
         file.deleteOnExit();
         LOG.info(reason + "; created a tmp file: " + file.getAbsolutePath());
         return file;
      } else {
         throw new IOException("Cannot recreate " + file + " as directory");
      }
   }

   public static File createLocalDirsTempFile(Configuration conf, String prefix, String suffix, boolean isDirectory) throws IOException {
      return createLocalDirsTempFile(conf.get("yarn.nodemanager.local-dirs"), prefix, suffix, isDirectory);
   }

   public static boolean deleteTmpFile(File tempFile) {
      if (tempFile != null) {
         tempFile.delete();
         ShutdownHookManager.cancelDeleteOnExit(tempFile);
         return true;
      } else {
         return false;
      }
   }

   public static boolean pathsContainNoScheme(Collection paths) {
      for(Path path : paths) {
         if (path.toUri().getScheme() != null) {
            return false;
         }
      }

      return true;
   }

   public static Path getParentRegardlessOfScheme(Path path, Collection candidates) {
      for(Path schemalessPath = Path.getPathWithoutSchemeAndAuthority(path); path != null && schemalessPath != null; schemalessPath = schemalessPath.getParent()) {
         if (candidates.contains(path)) {
            return path;
         }

         if (candidates.contains(schemalessPath)) {
            return schemalessPath;
         }

         path = path.getParent();
      }

      return null;
   }

   public static boolean isPathWithinSubtree(Path path, Path subtree) {
      return isPathWithinSubtree(path, subtree, subtree.depth());
   }

   private static boolean isPathWithinSubtree(Path path, Path subtree, int subtreeDepth) {
      while(path != null) {
         if (subtreeDepth > path.depth()) {
            return false;
         }

         if (subtree.equals(path)) {
            return true;
         }

         path = path.getParent();
      }

      return false;
   }

   public static void populateParentPaths(Set parents, Path path) {
      if (parents != null) {
         while(path != null) {
            parents.add(path);
            path = path.getParent();
         }

      }
   }

   public static URI getURI(String path) throws URISyntaxException {
      if (path == null) {
         return null;
      } else {
         URI uri = new URI(path);
         if (uri.getScheme() == null) {
            uri = (new File(path)).toURI();
         }

         return uri;
      }
   }

   public static Set getJarFilesByPath(String pathString, Configuration conf) {
      Set<String> result = new HashSet();
      if (pathString != null && !org.apache.commons.lang3.StringUtils.isBlank(pathString)) {
         String[] paths = pathString.split(",");

         for(String path : paths) {
            try {
               Path p = new Path(getURI(path));
               FileSystem fs = p.getFileSystem(conf);
               if (!fs.exists(p)) {
                  LOG.error("The jar file path " + path + " doesn't exist");
               } else if (fs.isDirectory(p)) {
                  FileStatus[] files = fs.listStatus(p, new GlobFilter("*.jar"));

                  for(FileStatus file : files) {
                     result.add(file.getPath().toUri().toString());
                  }
               } else {
                  result.add(p.toUri().toString());
               }
            } catch (IOException | URISyntaxException e) {
               LOG.error("Invalid file path " + path, e);
            }
         }

         return result;
      } else {
         return result;
      }
   }

   static {
      for(char c = 0; c < ' '; ++c) {
         charToEscape.set(c);
      }

      char[] clist = new char[]{'\u0001', '\u0002', '\u0003', '\u0004', '\u0005', '\u0006', '\u0007', '\b', '\t', '\n', '\u000b', '\f', '\r', '\u000e', '\u000f', '\u0010', '\u0011', '\u0012', '\u0013', '\u0014', '\u0015', '\u0016', '\u0017', '\u0018', '\u0019', '\u001a', '\u001b', '\u001c', '\u001d', '\u001e', '\u001f', '"', '#', '%', '\'', '*', '/', ':', '=', '?', '\\', '\u007f', '{', '[', ']', '^'};

      for(char c : clist) {
         charToEscape.set(c);
      }

   }
}
