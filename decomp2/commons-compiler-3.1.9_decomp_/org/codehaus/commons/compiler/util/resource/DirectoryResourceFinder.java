package org.codehaus.commons.compiler.util.resource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.codehaus.commons.nullanalysis.Nullable;

public class DirectoryResourceFinder extends FileResourceFinder {
   private final File directory;
   private final Map subdirectoryNameToFiles = new HashMap();

   public DirectoryResourceFinder(File directory) {
      this.directory = directory;
   }

   public final String toString() {
      return "dir:" + this.directory;
   }

   @Nullable
   protected final File findResourceAsFile(String resourceName) {
      int idx = resourceName.lastIndexOf(47);
      String subdirectoryName = idx == -1 ? null : resourceName.substring(0, idx).replace('/', File.separatorChar);
      Set<File> files = this.listFiles(subdirectoryName);
      if (files == null) {
         return null;
      } else {
         File file = new File(this.directory, resourceName.replace('/', File.separatorChar));
         return !files.contains(file) ? null : file;
      }
   }

   @Nullable
   private Set listFiles(@Nullable String subdirectoryName) {
      if (subdirectoryName != null) {
         subdirectoryName = subdirectoryName.replace('/', File.separatorChar);
      }

      Set<File> files = (Set)this.subdirectoryNameToFiles.get(subdirectoryName);
      if (files == null && !this.subdirectoryNameToFiles.containsKey(subdirectoryName)) {
         File subDirectory = subdirectoryName == null ? this.directory : new File(this.directory, subdirectoryName);
         File[] members = subDirectory.listFiles();
         if (members == null) {
            this.subdirectoryNameToFiles.put(subdirectoryName, (Object)null);
            return null;
         } else {
            Set<File> normalFiles = new HashSet();

            for(File file : members) {
               if (file.isFile()) {
                  normalFiles.add(file);
               }
            }

            this.subdirectoryNameToFiles.put(subdirectoryName, normalFiles);
            return normalFiles;
         }
      } else {
         return files;
      }
   }

   @Nullable
   public Iterable list(String resourceNamePrefix, boolean recurse) {
      assert !recurse : "This implementation does not support recursive directory listings";

      int idx = resourceNamePrefix.lastIndexOf(47);
      String directoryName = idx == -1 ? null : resourceNamePrefix.substring(0, idx);
      String relativeFileName = resourceNamePrefix.substring(idx + 1);
      Set<File> files = this.listFiles(directoryName);
      if (files == null) {
         return null;
      } else {
         List<Resource> result = new ArrayList();

         for(File file : files) {
            if (file.getName().startsWith(relativeFileName)) {
               result.add(new FileResource(file));
            }
         }

         return result;
      }
   }
}
