package io.vertx.core.file.impl;

import io.netty.util.internal.PlatformDependent;
import io.vertx.core.VertxException;
import io.vertx.core.file.FileSystemOptions;
import io.vertx.core.impl.Utils;
import io.vertx.core.net.impl.URIDecoder;
import io.vertx.core.spi.file.FileResolver;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileResolverImpl implements FileResolver {
   public static final String DISABLE_FILE_CACHING_PROP_NAME = "vertx.disableFileCaching";
   public static final String DISABLE_CP_RESOLVING_PROP_NAME = "vertx.disableFileCPResolving";
   public static final String CACHE_DIR_BASE_PROP_NAME = "vertx.cacheDirBase";
   private static final boolean NON_UNIX_FILE_SEP;
   private static final String JAR_URL_SEP = "!/";
   private final File cwd;
   private final boolean enableCaching;
   private final boolean enableCPResolving;
   private final FileCache cache;

   public FileResolverImpl() {
      this(new FileSystemOptions());
   }

   public FileResolverImpl(FileSystemOptions fileSystemOptions) {
      this.enableCaching = fileSystemOptions.isFileCachingEnabled();
      this.enableCPResolving = fileSystemOptions.isClassPathResolvingEnabled();
      if (this.enableCPResolving) {
         this.cache = FileCache.setupCache(fileSystemOptions.getFileCacheDir());
      } else {
         this.cache = null;
      }

      String cwdOverride = System.getProperty("vertx.cwd");
      if (cwdOverride != null) {
         this.cwd = (new File(cwdOverride)).getAbsoluteFile();
      } else {
         this.cwd = null;
      }

   }

   public String cacheDir() {
      return this.cache != null ? this.cache.cacheDir() : null;
   }

   FileCache getFileCache() {
      return this.cache;
   }

   public void close() throws IOException {
      if (this.enableCPResolving) {
         synchronized(this.cache) {
            this.cache.close();
         }
      }

   }

   public File resolveFile(String fileName) {
      File file = new File(fileName);
      boolean absolute = file.isAbsolute();
      File resolved;
      if (this.cwd != null && !absolute) {
         resolved = new File(this.cwd, fileName);
      } else {
         resolved = file;
      }

      if (this.cache == null) {
         return resolved;
      } else {
         if (!resolved.exists()) {
            synchronized(this.cache) {
               File cacheFile = this.cache.getCanonicalFile(file);
               if (cacheFile == null) {
                  return file;
               }

               String relativize = this.cache.relativize(cacheFile.getPath());
               if (relativize != null) {
                  if (this.enableCaching && cacheFile.exists()) {
                     return cacheFile;
                  }

                  if (absolute) {
                     fileName = relativize;
                     file = new File(relativize);
                     absolute = false;
                  }
               }

               if (!absolute) {
                  ClassLoader cl = this.getClassLoader();

                  for(File parentFile = file.getParentFile(); parentFile != null; parentFile = parentFile.getParentFile()) {
                     String parentFileName = parentFile.getPath();
                     if (NON_UNIX_FILE_SEP) {
                        parentFileName = parentFileName.replace(File.separatorChar, '/');
                     }

                     URL directoryContents = getValidClassLoaderResource(cl, parentFileName);
                     if (directoryContents != null) {
                        this.unpackUrlResource(directoryContents, parentFileName, cl, true);
                     }
                  }

                  if (NON_UNIX_FILE_SEP) {
                     fileName = fileName.replace(File.separatorChar, '/');
                  }

                  URL url = getValidClassLoaderResource(cl, fileName);
                  if (url != null) {
                     return this.unpackUrlResource(url, fileName, cl, false);
                  }
               }
            }
         }

         return file;
      }
   }

   private static boolean isValidWindowsCachePath(char c) {
      if (c < ' ') {
         return false;
      } else {
         switch (c) {
            case '"':
            case '*':
            case ':':
            case '<':
            case '>':
            case '?':
            case '|':
               return false;
            default:
               return true;
         }
      }
   }

   private static boolean isValidCachePath(String fileName) {
      if (!PlatformDependent.isWindows()) {
         return fileName.indexOf(0) == -1;
      } else {
         int len = fileName.length();

         for(int i = 0; i < len; ++i) {
            char c = fileName.charAt(i);
            if (!isValidWindowsCachePath(c)) {
               return false;
            }

            if (c == ' ' && (i + 1 == len || fileName.charAt(i + 1) == '/')) {
               return false;
            }
         }

         return true;
      }
   }

   private static URL getValidClassLoaderResource(ClassLoader cl, String fileName) {
      URL resource = cl.getResource(fileName);
      return resource != null && !isValidCachePath(fileName) ? null : resource;
   }

   private File unpackUrlResource(URL url, String fileName, ClassLoader cl, boolean isDir) {
      switch (url.getProtocol()) {
         case "file":
            return this.unpackFromFileURL(url, fileName, cl);
         case "jar":
            return this.unpackFromJarURL(url, fileName, cl);
         case "bundle":
         case "bundleentry":
         case "bundleresource":
         case "jrt":
         case "resource":
         case "vfs":
            return this.unpackFromBundleURL(url, fileName, isDir);
         default:
            throw new IllegalStateException("Invalid url protocol: " + prot);
      }
   }

   private File unpackFromFileURL(URL url, String fileName, ClassLoader cl) {
      File resource = new File(URIDecoder.decodeURIComponent(url.getPath(), false));
      boolean isDirectory = resource.isDirectory();

      File cacheFile;
      try {
         cacheFile = this.cache.cacheFile(fileName, resource, !this.enableCaching);
      } catch (IOException e) {
         throw new VertxException(FileSystemImpl.getFileAccessErrorMessage("unpack", url.toString()), e);
      }

      if (isDirectory) {
         String[] listing = resource.list();
         if (listing != null) {
            for(String file : listing) {
               String subResource = fileName + "/" + file;
               URL url2 = getValidClassLoaderResource(cl, subResource);
               if (url2 == null) {
                  throw new VertxException("Invalid resource: " + subResource);
               }

               this.unpackFromFileURL(url2, subResource, cl);
            }
         }
      }

      return cacheFile;
   }

   private List listOfEntries(URL url) {
      String path = url.getPath();
      List<String> list = new ArrayList();
      int last = path.length();
      int i = path.length() - 2;

      while(i >= 0) {
         if (path.charAt(i) == '!' && path.charAt(i + 1) == '/') {
            list.add(path.substring(2 + i, last));
            last = i;
            i -= 2;
         } else {
            --i;
         }
      }

      return list;
   }

   private File unpackFromJarURL(URL url, String fileName, ClassLoader cl) {
      try {
         List<String> listOfEntries = this.listOfEntries(url);
         switch (listOfEntries.size()) {
            case 1:
               JarURLConnection conn = (JarURLConnection)url.openConnection();
               if (conn.getContentLength() == -1) {
                  return this.cache.getFile(fileName);
               }

               ZipFile zip = conn.getJarFile();

               try {
                  this.extractFilesFromJarFile(zip, fileName);
                  break;
               } finally {
                  if (!conn.getUseCaches()) {
                     zip.close();
                  }

               }
            case 2:
               URL nestedURL = cl.getResource((String)listOfEntries.get(1));
               if (nestedURL != null && nestedURL.getProtocol().equals("jar")) {
                  File root = this.unpackFromJarURL(nestedURL, (String)listOfEntries.get(1), cl);
                  if (root.isDirectory()) {
                     final Path path = root.toPath();
                     Files.walkFileTree(path, new SimpleFileVisitor() {
                        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                           Path relative = path.relativize(dir);
                           FileResolverImpl.this.cache.cacheDir(relative.toString());
                           return FileVisitResult.CONTINUE;
                        }

                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                           Path relative = path.relativize(file);
                           FileResolverImpl.this.cache.cacheFile(relative.toString(), file.toFile(), false);
                           return FileVisitResult.CONTINUE;
                        }
                     });
                     break;
                  } else {
                     ZipFile zip = new ZipFile(root);
                     Throwable var8 = null;

                     try {
                        this.extractFilesFromJarFile(zip, fileName);
                        break;
                     } catch (Throwable var24) {
                        var8 = var24;
                        throw var24;
                     } finally {
                        if (zip != null) {
                           if (var8 != null) {
                              try {
                                 zip.close();
                              } catch (Throwable var23) {
                                 var8.addSuppressed(var23);
                              }
                           } else {
                              zip.close();
                           }
                        }

                     }
                  }
               }

               throw new VertxException("Unexpected nested url : " + nestedURL);
            default:
               throw new VertxException("Nesting more than two levels is not supported");
         }
      } catch (IOException e) {
         throw new VertxException(FileSystemImpl.getFileAccessErrorMessage("unpack", url.toString()), e);
      }

      return this.cache.getFile(fileName);
   }

   private void extractFilesFromJarFile(ZipFile zip, String entryFilter) throws IOException {
      Enumeration<? extends ZipEntry> entries = zip.entries();

      while(entries.hasMoreElements()) {
         ZipEntry entry = (ZipEntry)entries.nextElement();
         String name = entry.getName();
         int len = name.length();
         if (len == 0) {
            return;
         }

         if ((name.charAt(len - 1) != ' ' || !Utils.isWindows()) && name.startsWith(entryFilter)) {
            if (name.charAt(len - 1) == '/') {
               this.cache.cacheDir(name);
            } else {
               InputStream is = zip.getInputStream(entry);
               Throwable var8 = null;

               try {
                  this.cache.cacheFile(name, is, !this.enableCaching);
               } catch (Throwable var17) {
                  var8 = var17;
                  throw var17;
               } finally {
                  if (is != null) {
                     if (var8 != null) {
                        try {
                           is.close();
                        } catch (Throwable var16) {
                           var8.addSuppressed(var16);
                        }
                     } else {
                        is.close();
                     }
                  }

               }
            }
         }
      }

   }

   private boolean isBundleUrlDirectory(URL url) {
      return url.toExternalForm().endsWith("/") || getValidClassLoaderResource(this.getClassLoader(), url.getPath().substring(1) + "/") != null;
   }

   private File unpackFromBundleURL(URL url, String fileName, boolean isDir) {
      try {
         if ((this.getClassLoader() == null || !this.isBundleUrlDirectory(url)) && !isDir) {
            InputStream is = url.openStream();
            Throwable var5 = null;

            try {
               this.cache.cacheFile(fileName, is, !this.enableCaching);
            } catch (Throwable var15) {
               var5 = var15;
               throw var15;
            } finally {
               if (is != null) {
                  if (var5 != null) {
                     try {
                        is.close();
                     } catch (Throwable var14) {
                        var5.addSuppressed(var14);
                     }
                  } else {
                     is.close();
                  }
               }

            }
         } else {
            this.cache.cacheDir(fileName);
         }
      } catch (IOException e) {
         throw new VertxException(FileSystemImpl.getFileAccessErrorMessage("unpack", url.toString()), e);
      }

      return this.cache.getFile(fileName);
   }

   private ClassLoader getClassLoader() {
      ClassLoader cl = Thread.currentThread().getContextClassLoader();
      if (cl == null) {
         cl = this.getClass().getClassLoader();
      }

      if (cl == null) {
         cl = Object.class.getClassLoader();
      }

      return cl;
   }

   static {
      NON_UNIX_FILE_SEP = File.separatorChar != '/';
   }
}
