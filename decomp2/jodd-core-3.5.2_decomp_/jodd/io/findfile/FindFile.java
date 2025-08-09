package jodd.io.findfile;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import jodd.io.FileNameUtil;
import jodd.io.FileUtil;
import jodd.util.MultiComparator;
import jodd.util.NaturalOrderComparator;
import jodd.util.StringUtil;
import jodd.util.collection.JoddArrayList;
import jodd.util.sort.FastSort;

public class FindFile {
   protected boolean recursive;
   protected boolean includeDirs = true;
   protected boolean includeFiles = true;
   protected boolean walking = true;
   protected Match matchType;
   protected ArrayList includes;
   protected ArrayList excludes;
   protected JoddArrayList pathList;
   protected JoddArrayList pathListOriginal;
   protected JoddArrayList todoFolders;
   protected JoddArrayList todoFiles;
   protected File lastFile;
   protected File rootFile;
   protected String rootPath;
   protected List sortComparators;

   public FindFile() {
      this.matchType = FindFile.Match.FULL_PATH;
   }

   public boolean isRecursive() {
      return this.recursive;
   }

   public FindFile setRecursive(boolean recursive) {
      this.recursive = recursive;
      return this;
   }

   public boolean isIncludeDirs() {
      return this.includeDirs;
   }

   public FindFile setIncludeDirs(boolean includeDirs) {
      this.includeDirs = includeDirs;
      return this;
   }

   public boolean isIncludeFiles() {
      return this.includeFiles;
   }

   public FindFile setIncludeFiles(boolean includeFiles) {
      this.includeFiles = includeFiles;
      return this;
   }

   public boolean isWalking() {
      return this.walking;
   }

   public FindFile setWalking(boolean walking) {
      this.walking = walking;
      return this;
   }

   public Match getMatchType() {
      return this.matchType;
   }

   public FindFile setMatchType(Match match) {
      this.matchType = match;
      return this;
   }

   public FindFile searchPath(File searchPath) {
      this.addPath(searchPath);
      return this;
   }

   public FindFile searchPath(File... searchPath) {
      for(File file : searchPath) {
         this.addPath(file);
      }

      return this;
   }

   public FindFile searchPath(String searchPath) {
      if (searchPath.indexOf(File.pathSeparatorChar) != -1) {
         String[] paths = StringUtil.split(searchPath, File.pathSeparator);

         for(String path : paths) {
            this.addPath(new File(path));
         }
      } else {
         this.addPath(new File(searchPath));
      }

      return this;
   }

   public FindFile searchPath(String... searchPaths) {
      for(String searchPath : searchPaths) {
         this.searchPath(searchPath);
      }

      return this;
   }

   public FindFile searchPath(URI searchPath) {
      File file;
      try {
         file = new File(searchPath);
      } catch (Exception ex) {
         throw new FindFileException("URI error: " + searchPath, ex);
      }

      this.addPath(file);
      return this;
   }

   public FindFile searchPath(URI... searchPath) {
      for(URI uri : searchPath) {
         this.searchPath(uri);
      }

      return this;
   }

   public FindFile searchPath(URL searchPath) {
      File file = FileUtil.toFile(searchPath);
      if (file == null) {
         throw new FindFileException("URL error: " + searchPath);
      } else {
         this.addPath(file);
         return this;
      }
   }

   public FindFile searchPath(URL... searchPath) {
      for(URL url : searchPath) {
         this.searchPath(url);
      }

      return this;
   }

   public FindFile include(String pattern) {
      if (this.includes == null) {
         this.includes = new ArrayList();
      }

      this.includes.add(pattern);
      return this;
   }

   public FindFile include(String... patterns) {
      if (this.includes == null) {
         this.includes = new ArrayList();
      }

      Collections.addAll(this.includes, patterns);
      return this;
   }

   public FindFile exclude(String pattern) {
      if (this.excludes == null) {
         this.excludes = new ArrayList();
      }

      this.excludes.add(pattern);
      return this;
   }

   public FindFile exclude(String... patterns) {
      if (this.excludes == null) {
         this.excludes = new ArrayList();
      }

      Collections.addAll(this.excludes, patterns);
      return this;
   }

   protected boolean acceptFile(File file) {
      String matchingFilePath = this.getMatchingFilePath(file);
      if (this.excludes != null) {
         for(String pattern : this.excludes) {
            if (this.match(matchingFilePath, pattern)) {
               return false;
            }
         }
      }

      if (this.includes != null) {
         for(String pattern : this.includes) {
            if (this.match(matchingFilePath, pattern)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   protected boolean match(String path, String pattern) {
      return path.equals(pattern);
   }

   protected String getMatchingFilePath(File file) {
      String path = null;
      switch (this.matchType) {
         case FULL_PATH:
            path = file.getAbsolutePath();
            break;
         case RELATIVE_PATH:
            path = file.getAbsolutePath();
            path = path.substring(this.rootPath.length());
            break;
         case NAME:
            path = file.getName();
      }

      path = FileNameUtil.separatorsToUnix(path);
      return path;
   }

   public File lastFile() {
      return this.lastFile;
   }

   protected void addPath(File path) {
      if (path.exists()) {
         if (this.pathList == null) {
            this.pathList = new JoddArrayList();
         }

         this.pathList.add(path);
      }
   }

   public void reset() {
      this.pathList = this.pathListOriginal;
      this.pathListOriginal = null;
      this.todoFiles = null;
      this.lastFile = null;
      this.includes = null;
      this.excludes = null;
   }

   public File nextFile() {
      if (this.todoFiles == null) {
         this.init();
      }

      while(true) {
         while(this.todoFiles.isEmpty()) {
            boolean initialDir = false;
            File folder;
            if (this.todoFolders.isEmpty()) {
               if (this.pathList.isEmpty()) {
                  return null;
               }

               folder = (File)this.pathList.removeFirst();
               this.rootFile = folder;
               this.rootPath = this.rootFile.getAbsolutePath();
               initialDir = true;
            } else {
               folder = (File)this.todoFolders.removeFirst();
            }

            if (initialDir || this.recursive) {
               this.todoFiles.add(new FilesIterator(folder));
            }

            if (!initialDir && this.includeDirs && this.acceptFile(folder)) {
               this.lastFile = folder;
               return folder;
            }
         }

         FindFile<T>.FilesIterator filesIterator = (FilesIterator)this.todoFiles.getLast();
         File nextFile = filesIterator.next();
         if (nextFile == null) {
            this.todoFiles.removeLast();
         } else {
            if (!nextFile.isDirectory()) {
               this.lastFile = nextFile;
               return nextFile;
            }

            if (!this.walking) {
               this.todoFolders.add(nextFile);
            } else {
               if (this.recursive) {
                  this.todoFiles.add(new FilesIterator(nextFile));
               }

               if (this.includeDirs && this.acceptFile(nextFile)) {
                  this.lastFile = nextFile;
                  return nextFile;
               }
            }
         }
      }
   }

   public void scan() {
      while(this.nextFile() != null) {
      }

   }

   protected void init() {
      this.todoFiles = new JoddArrayList();
      this.todoFolders = new JoddArrayList();
      if (this.pathList == null) {
         this.pathList = new JoddArrayList();
      } else {
         if (this.pathListOriginal == null) {
            this.pathListOriginal = (JoddArrayList)this.pathList.clone();
         }

         String[] files = new String[this.pathList.size()];
         int index = 0;
         Iterator<File> iterator = this.pathList.iterator();

         while(iterator.hasNext()) {
            File file = (File)iterator.next();
            if (file.isFile()) {
               files[index++] = file.getAbsolutePath();
               iterator.remove();
            }
         }

         if (index != 0) {
            FindFile<T>.FilesIterator filesIterator = new FilesIterator(files);
            this.todoFiles.add(filesIterator);
         }

      }
   }

   public Iterator iterator() {
      // $FF: Couldn't be decompiled
   }

   protected void addComparator(Comparator comparator) {
      if (this.sortComparators == null) {
         this.sortComparators = new ArrayList(4);
      }

      this.sortComparators.add(comparator);
   }

   public FindFile sortNone() {
      this.sortComparators = null;
      return this;
   }

   public FindFile sortWith(Comparator fileComparator) {
      this.addComparator(fileComparator);
      return this;
   }

   public FindFile sortFoldersFirst() {
      this.addComparator(new FolderFirstComparator(true));
      return this;
   }

   public FindFile sortFoldersLast() {
      this.addComparator(new FolderFirstComparator(false));
      return this;
   }

   public FindFile sortByName() {
      this.addComparator(new FileNameComparator(true));
      return this;
   }

   public FindFile sortByNameDesc() {
      this.addComparator(new FileNameComparator(false));
      return this;
   }

   public FindFile sortByExtension() {
      this.addComparator(new FileExtensionComparator(true));
      return this;
   }

   public FindFile sortByExtensionDesc() {
      this.addComparator(new FileExtensionComparator(false));
      return this;
   }

   public FindFile sortByTime() {
      this.addComparator(new FileLastModifiedTimeComparator(true));
      return this;
   }

   public FindFile sortByTimeDesc() {
      this.addComparator(new FileLastModifiedTimeComparator(false));
      return this;
   }

   public static enum Match {
      FULL_PATH,
      RELATIVE_PATH,
      NAME;
   }

   protected class FilesIterator {
      protected final File folder;
      protected final String[] fileNames;
      protected final File[] files;
      protected int index;

      public FilesIterator(File folder) {
         this.folder = folder;
         if (FindFile.this.sortComparators != null) {
            this.files = folder.listFiles();
            if (this.files != null) {
               FastSort.sort(this.files, new MultiComparator(FindFile.this.sortComparators));
            }

            this.fileNames = null;
         } else {
            this.files = null;
            this.fileNames = folder.list();
         }

      }

      public FilesIterator(String[] fileNames) {
         this.folder = null;
         if (FindFile.this.sortComparators != null) {
            int fileNamesLength = fileNames.length;
            this.files = new File[fileNamesLength];

            for(int i = 0; i < fileNamesLength; ++i) {
               String fileName = fileNames[i];
               if (fileName != null) {
                  this.files[i] = new File(fileName);
               }
            }

            this.fileNames = null;
         } else {
            this.files = null;
            this.fileNames = fileNames;
         }

      }

      public File next() {
         if (this.files != null) {
            return this.nextFile();
         } else {
            return this.fileNames != null ? this.nextFileName() : null;
         }
      }

      protected File nextFileName() {
         while(true) {
            if (this.index < this.fileNames.length) {
               String fileName = this.fileNames[this.index];
               if (fileName == null) {
                  ++this.index;
                  continue;
               }

               this.fileNames[this.index] = null;
               ++this.index;
               File file;
               if (this.folder == null) {
                  file = new File(fileName);
               } else {
                  file = new File(this.folder, fileName);
               }

               if (file.isFile() && (!FindFile.this.includeFiles || !FindFile.this.acceptFile(file))) {
                  continue;
               }

               return file;
            }

            return null;
         }
      }

      protected File nextFile() {
         while(true) {
            if (this.index < this.files.length) {
               File file = this.files[this.index];
               if (file == null) {
                  ++this.index;
                  continue;
               }

               this.files[this.index] = null;
               ++this.index;
               if (file.isFile() && (!FindFile.this.includeFiles || !FindFile.this.acceptFile(file))) {
                  continue;
               }

               return file;
            }

            return null;
         }
      }
   }

   public static class FolderFirstComparator implements Comparator {
      protected final int order;

      public FolderFirstComparator(boolean foldersFirst) {
         if (foldersFirst) {
            this.order = 1;
         } else {
            this.order = -1;
         }

      }

      public int compare(File file1, File file2) {
         if (file1.isFile() && file2.isDirectory()) {
            return this.order;
         } else {
            return file1.isDirectory() && file2.isFile() ? -this.order : 0;
         }
      }
   }

   public static class FileNameComparator implements Comparator {
      protected final int order;
      protected NaturalOrderComparator naturalOrderComparator = new NaturalOrderComparator(true);

      public FileNameComparator(boolean ascending) {
         if (ascending) {
            this.order = 1;
         } else {
            this.order = -1;
         }

      }

      public int compare(File file1, File file2) {
         int result = this.naturalOrderComparator.compare(file1.getName(), file2.getName());
         if (result == 0) {
            return result;
         } else {
            return result > 0 ? this.order : -this.order;
         }
      }
   }

   public static class FileExtensionComparator implements Comparator {
      protected final int order;

      public FileExtensionComparator(boolean ascending) {
         if (ascending) {
            this.order = 1;
         } else {
            this.order = -1;
         }

      }

      public int compare(File file1, File file2) {
         String ext1 = FileNameUtil.getExtension(file1.getName());
         String ext2 = FileNameUtil.getExtension(file2.getName());
         long diff = (long)ext1.compareToIgnoreCase(ext2);
         if (diff == 0L) {
            return 0;
         } else {
            return diff > 0L ? this.order : -this.order;
         }
      }
   }

   public static class FileLastModifiedTimeComparator implements Comparator {
      protected final int order;

      public FileLastModifiedTimeComparator(boolean ascending) {
         if (ascending) {
            this.order = 1;
         } else {
            this.order = -1;
         }

      }

      public int compare(File file1, File file2) {
         long diff = file1.lastModified() - file2.lastModified();
         if (diff == 0L) {
            return 0;
         } else {
            return diff > 0L ? this.order : -this.order;
         }
      }
   }
}
