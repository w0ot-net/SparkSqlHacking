package jodd.io.findfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import jodd.io.FileNameUtil;
import jodd.io.FileUtil;
import jodd.io.StreamUtil;
import jodd.io.ZipUtil;
import jodd.util.ArraysUtil;
import jodd.util.StringUtil;
import jodd.util.Wildcard;

public abstract class ClassFinder {
   private static final String CLASS_FILE_EXT = ".class";
   private static final String JAR_FILE_EXT = ".jar";
   protected static String[] systemJars = new String[]{"**/jre/lib/*.jar", "**/jre/lib/ext/*.jar", "**/Java/Extensions/*.jar", "**/Classes/*.jar"};
   protected String[] excludedJars;
   protected String[] includedJars;
   protected String[] includedEntries;
   protected String[] excludedEntries;
   protected boolean includeResources;
   protected boolean ignoreException;

   public static String[] getSystemJars() {
      return systemJars;
   }

   public static void setSystemJars(String... newSystemJars) {
      systemJars = newSystemJars;
   }

   public String[] getExcludedJars() {
      return this.excludedJars;
   }

   public void setExcludedJars(String... excludedJars) {
      this.excludedJars = excludedJars;
   }

   public String[] getIncludedJars() {
      return this.includedJars;
   }

   public void setIncludedJars(String... includedJars) {
      this.includedJars = includedJars;
   }

   public String[] getIncludedEntries() {
      return this.includedEntries;
   }

   public void setIncludedEntries(String... includedEntries) {
      this.includedEntries = includedEntries;
   }

   public String[] getExcludedEntries() {
      return this.excludedEntries;
   }

   public void setExcludedEntries(String... excludedEntries) {
      this.excludedEntries = excludedEntries;
   }

   public boolean isIncludeResources() {
      return this.includeResources;
   }

   public void setIncludeResources(boolean includeResources) {
      this.includeResources = includeResources;
   }

   public boolean isIgnoreException() {
      return this.ignoreException;
   }

   public void setIgnoreException(boolean ignoreException) {
      this.ignoreException = ignoreException;
   }

   protected void scanUrls(URL... urls) {
      for(URL path : urls) {
         this.scanUrl(path);
      }

   }

   protected void scanUrl(URL url) {
      File file = FileUtil.toFile(url);
      if (file == null && !this.ignoreException) {
         throw new FindFileException("URL is not a valid file: " + url);
      } else {
         this.scanPath(file);
      }
   }

   protected void scanPaths(File... paths) {
      for(File path : paths) {
         this.scanPath(path);
      }

   }

   protected void scanPaths(String... paths) {
      for(String path : paths) {
         this.scanPath(path);
      }

   }

   protected void scanPath(String path) {
      this.scanPath(new File(path));
   }

   protected boolean acceptJar(File jarFile) {
      String path = jarFile.getAbsolutePath();
      path = FileNameUtil.separatorsToUnix(path);
      if (systemJars != null) {
         int ndx = Wildcard.matchPathOne(path, systemJars);
         if (ndx != -1) {
            return false;
         }
      }

      if (this.excludedJars != null) {
         int ndx = Wildcard.matchPathOne(path, this.excludedJars);
         if (ndx != -1) {
            return false;
         }
      }

      if (this.includedJars != null) {
         int ndx = Wildcard.matchPathOne(path, this.includedJars);
         if (ndx == -1) {
            return false;
         }
      }

      return true;
   }

   protected void scanPath(File file) {
      String path = file.getAbsolutePath();
      if (StringUtil.endsWithIgnoreCase(path, ".jar")) {
         if (!this.acceptJar(file)) {
            return;
         }

         this.scanJarFile(file);
      } else if (file.isDirectory()) {
         this.scanClassPath(file);
      }

   }

   protected void scanJarFile(File file) {
      ZipFile zipFile;
      try {
         zipFile = new ZipFile(file);
      } catch (IOException ioex) {
         if (!this.ignoreException) {
            throw new FindFileException("Invalid zip: " + file.getName(), ioex);
         }

         return;
      }

      Enumeration entries = zipFile.entries();

      while(entries.hasMoreElements()) {
         ZipEntry zipEntry = (ZipEntry)entries.nextElement();
         String zipEntryName = zipEntry.getName();

         try {
            if (StringUtil.endsWithIgnoreCase(zipEntryName, ".class")) {
               String entryName = this.prepareEntryName(zipEntryName, true);
               EntryData entryData = new EntryData(entryName, zipFile, zipEntry);

               try {
                  this.scanEntry(entryData);
               } finally {
                  entryData.closeInputStreamIfOpen();
               }
            } else if (this.includeResources) {
               String entryName = this.prepareEntryName(zipEntryName, false);
               EntryData entryData = new EntryData(entryName, zipFile, zipEntry);

               try {
                  this.scanEntry(entryData);
               } finally {
                  entryData.closeInputStreamIfOpen();
               }
            }
         } catch (RuntimeException rex) {
            if (!this.ignoreException) {
               ZipUtil.close(zipFile);
               throw rex;
            }
         }
      }

      ZipUtil.close(zipFile);
   }

   protected void scanClassPath(File root) {
      String rootPath = root.getAbsolutePath();
      if (!rootPath.endsWith(File.separator)) {
         rootPath = rootPath + File.separatorChar;
      }

      FindFile ff = (new FindFile()).setIncludeDirs(false).setRecursive(true).searchPath(rootPath);

      File file;
      while((file = ff.nextFile()) != null) {
         String filePath = file.getAbsolutePath();

         try {
            if (StringUtil.endsWithIgnoreCase(filePath, ".class")) {
               this.scanClassFile(filePath, rootPath, file, true);
            } else if (this.includeResources) {
               this.scanClassFile(filePath, rootPath, file, false);
            }
         } catch (RuntimeException rex) {
            if (!this.ignoreException) {
               throw rex;
            }
         }
      }

   }

   protected void scanClassFile(String filePath, String rootPath, File file, boolean isClass) {
      if (StringUtil.startsWithIgnoreCase(filePath, rootPath)) {
         String entryName = this.prepareEntryName(filePath.substring(rootPath.length()), isClass);
         EntryData entryData = new EntryData(entryName, file);

         try {
            this.scanEntry(entryData);
         } finally {
            entryData.closeInputStreamIfOpen();
         }
      }

   }

   protected String prepareEntryName(String name, boolean isClass) {
      String var5;
      if (isClass) {
         var5 = name.substring(0, name.length() - 6);
         var5 = StringUtil.replaceChar(var5, '/', '.');
         var5 = StringUtil.replaceChar(var5, '\\', '.');
      } else {
         var5 = '/' + StringUtil.replaceChar(name, '\\', '/');
      }

      return var5;
   }

   protected boolean acceptEntry(String entryName) {
      if (this.excludedEntries != null && Wildcard.matchOne(entryName, this.excludedEntries) != -1) {
         return false;
      } else {
         return this.includedEntries == null || Wildcard.matchOne(entryName, this.includedEntries) != -1;
      }
   }

   protected void scanEntry(EntryData entryData) {
      if (this.acceptEntry(entryData.getName())) {
         try {
            this.onEntry(entryData);
         } catch (Exception ex) {
            throw new FindFileException("Scan entry error: " + entryData, ex);
         }
      }
   }

   protected abstract void onEntry(EntryData var1) throws Exception;

   protected byte[] getTypeSignatureBytes(Class type) {
      String name = 'L' + type.getName().replace('.', '/') + ';';
      return name.getBytes();
   }

   protected boolean isTypeSignatureInUse(InputStream inputStream, byte[] bytes) {
      try {
         byte[] data = StreamUtil.readBytes(inputStream);
         int index = ArraysUtil.indexOf(data, bytes);
         return index != -1;
      } catch (IOException ioex) {
         throw new FindFileException("Read error", ioex);
      }
   }

   protected static class EntryData {
      private final File file;
      private final ZipFile zipFile;
      private final ZipEntry zipEntry;
      private final String name;
      private InputStream inputStream;

      EntryData(String name, ZipFile zipFile, ZipEntry zipEntry) {
         this.name = name;
         this.zipFile = zipFile;
         this.zipEntry = zipEntry;
         this.file = null;
         this.inputStream = null;
      }

      EntryData(String name, File file) {
         this.name = name;
         this.file = file;
         this.zipEntry = null;
         this.zipFile = null;
         this.inputStream = null;
      }

      public String getName() {
         return this.name;
      }

      public boolean isArchive() {
         return this.zipFile != null;
      }

      public String getArchiveName() {
         return this.zipFile != null ? this.zipFile.getName() : null;
      }

      public InputStream openInputStream() {
         if (this.zipFile != null) {
            try {
               this.inputStream = this.zipFile.getInputStream(this.zipEntry);
               return this.inputStream;
            } catch (IOException ioex) {
               throw new FindFileException("Input stream error: '" + this.zipFile.getName() + "', entry: '" + this.zipEntry.getName() + "'.", ioex);
            }
         } else {
            try {
               this.inputStream = new FileInputStream(this.file);
               return this.inputStream;
            } catch (FileNotFoundException fnfex) {
               throw new FindFileException("Unable to open: " + this.file.getAbsolutePath(), fnfex);
            }
         }
      }

      void closeInputStreamIfOpen() {
         if (this.inputStream != null) {
            StreamUtil.close(this.inputStream);
            this.inputStream = null;
         }
      }

      public String toString() {
         return "EntryData{" + this.name + '\'' + '}';
      }
   }
}
