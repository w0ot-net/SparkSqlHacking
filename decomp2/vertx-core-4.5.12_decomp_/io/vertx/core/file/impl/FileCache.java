package io.vertx.core.file.impl;

import io.vertx.core.VertxException;
import io.vertx.core.impl.Utils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.UUID;

class FileCache {
   private Thread shutdownHook;
   private File cacheDir;

   static FileCache setupCache(String fileCacheDir) {
      FileCache cache = new FileCache(setupCacheDir(fileCacheDir));
      cache.registerShutdownHook();
      return cache;
   }

   static File setupCacheDir(String fileCacheDir) {
      if (fileCacheDir.endsWith(File.separator)) {
         fileCacheDir = fileCacheDir.substring(0, fileCacheDir.length() - File.separator.length());
      }

      String cacheDirName = fileCacheDir + "-" + UUID.randomUUID();
      File cacheDir = new File(cacheDirName);

      try {
         if (Utils.isWindows()) {
            Files.createDirectories(cacheDir.toPath());
         } else {
            Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwx------");
            Files.createDirectories(cacheDir.toPath(), PosixFilePermissions.asFileAttribute(perms));
         }

         return cacheDir;
      } catch (IOException e) {
         throw new IllegalStateException(FileSystemImpl.getFolderAccessErrorMessage("create", fileCacheDir), e);
      }
   }

   public FileCache(File cacheDir) {
      try {
         this.cacheDir = cacheDir.getCanonicalFile();
      } catch (IOException e) {
         throw new VertxException("Cannot get canonical name of cacheDir", e);
      }
   }

   synchronized void registerShutdownHook() {
      Thread shutdownHook = new Thread(this::runHook);
      this.shutdownHook = shutdownHook;
      Runtime.getRuntime().addShutdownHook(shutdownHook);
   }

   private void runHook() {
      synchronized(this) {
         if (this.cacheDir == null) {
            return;
         }
      }

      Thread deleteCacheDirThread = new Thread(() -> {
         try {
            this.deleteCacheDir();
         } catch (IOException var2) {
         }

      });
      deleteCacheDirThread.start();

      try {
         deleteCacheDirThread.join(10000L);
      } catch (InterruptedException var3) {
         Thread.currentThread().interrupt();
      }

   }

   String cacheDir() {
      return this.getCacheDir().getPath();
   }

   void close() throws IOException {
      Thread hook;
      synchronized(this) {
         hook = this.shutdownHook;
         this.shutdownHook = null;
      }

      if (hook != null) {
         try {
            Runtime.getRuntime().removeShutdownHook(hook);
         } catch (IllegalStateException var4) {
         }
      }

      this.deleteCacheDir();
   }

   private void deleteCacheDir() throws IOException {
      File dir;
      synchronized(this) {
         if (this.cacheDir == null) {
            return;
         }

         dir = this.cacheDir;
         this.cacheDir = null;
      }

      if (dir.exists()) {
         FileSystemImpl.delete(dir.toPath(), true);
      }

   }

   File getFile(String fileName) {
      return new File(this.getCacheDir(), fileName);
   }

   File getCanonicalFile(File file) {
      try {
         return file.isAbsolute() ? file.getCanonicalFile() : this.getFile(file.getPath()).getCanonicalFile();
      } catch (IOException var3) {
         return null;
      }
   }

   String relativize(String fileName) {
      String cachePath = this.getCacheDir().getPath();
      if (fileName.startsWith(cachePath)) {
         int cachePathLen = cachePath.length();
         if (fileName.length() == cachePathLen) {
            return "";
         }

         if (fileName.charAt(cachePathLen) == File.separatorChar) {
            return fileName.substring(cachePathLen + 1);
         }
      }

      return null;
   }

   File cacheFile(String fileName, File resource, boolean overwrite) throws IOException {
      File cacheFile = new File(this.getCacheDir(), fileName);
      this.fileNameCheck(cacheFile);
      boolean isDirectory = resource.isDirectory();
      if (!isDirectory) {
         cacheFile.getParentFile().mkdirs();
         if (!overwrite) {
            try {
               Files.copy(resource.toPath(), cacheFile.toPath());
            } catch (FileAlreadyExistsException var7) {
            }
         } else {
            Files.copy(resource.toPath(), cacheFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
         }
      } else {
         cacheFile.mkdirs();
      }

      return cacheFile;
   }

   void cacheFile(String fileName, InputStream is, boolean overwrite) throws IOException {
      File cacheFile = new File(this.getCacheDir(), fileName);
      this.fileNameCheck(cacheFile);
      cacheFile.getParentFile().mkdirs();
      if (!overwrite) {
         try {
            Files.copy(is, cacheFile.toPath(), new CopyOption[0]);
         } catch (FileAlreadyExistsException var6) {
         }
      } else {
         Files.copy(is, cacheFile.toPath(), new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
      }

   }

   void cacheDir(String fileName) throws IOException {
      File file = new File(this.getCacheDir(), fileName);
      this.fileNameCheck(file);
      file.mkdirs();
   }

   private void fileNameCheck(File file) throws IOException {
      if (!file.getCanonicalFile().toPath().startsWith(this.getCacheDir().toPath())) {
         throw new VertxException("File is outside of the cacheDir dir: " + file);
      }
   }

   private File getCacheDir() {
      File currentCacheDir = this.cacheDir;
      if (currentCacheDir == null) {
         throw new IllegalStateException("cacheDir has been removed. FileResolver is closing?");
      } else {
         return currentCacheDir;
      }
   }
}
