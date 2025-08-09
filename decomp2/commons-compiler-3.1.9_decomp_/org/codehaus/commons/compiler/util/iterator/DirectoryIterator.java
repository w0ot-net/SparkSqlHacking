package org.codehaus.commons.compiler.util.iterator;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.codehaus.commons.compiler.util.Producer;
import org.codehaus.commons.nullanalysis.Nullable;

public class DirectoryIterator extends ProducerIterator {
   public DirectoryIterator(final File rootDirectory, final FilenameFilter directoryNameFilter, final FilenameFilter fileNameFilter) {
      super(new Producer() {
         private final List stateStack = DirectoryIterator.newArrayList(new null.State(rootDirectory));

         @Nullable
         public File produce() {
            while(!this.stateStack.isEmpty()) {
               null.State state = (null.State)this.stateStack.get(this.stateStack.size() - 1);
               if (state.directories.hasNext()) {
                  this.stateStack.add(new null.State((File)state.directories.next()));
               } else {
                  if (state.files.hasNext()) {
                     File file = (File)state.files.next();
                     return file;
                  }

                  this.stateStack.remove(this.stateStack.size() - 1);
               }
            }

            return null;
         }

         class State {
            final Iterator directories;
            final Iterator files;

            State(File dir) {
               File[] entries = dir.listFiles();
               if (entries == null) {
                  throw new DirectoryNotListableException(dir.getPath());
               } else {
                  List<File> directoryList = new ArrayList();
                  List<File> fileList = new ArrayList();

                  for(File entry : entries) {
                     if (entry.isDirectory()) {
                        if (directoryNameFilter.accept(dir, entry.getName())) {
                           directoryList.add(entry);
                        }
                     } else if (entry.isFile() && fileNameFilter.accept(dir, entry.getName())) {
                        fileList.add(entry);
                     }
                  }

                  this.directories = directoryList.iterator();
                  this.files = fileList.iterator();
               }
            }
         }
      });
   }

   public static Iterator traverseDirectories(File[] rootDirectories, FilenameFilter directoryNameFilter, FilenameFilter fileNameFilter) {
      List<Iterator<File>> result = new ArrayList();

      for(File rootDirectory : rootDirectories) {
         result.add(new DirectoryIterator(rootDirectory, directoryNameFilter, fileNameFilter));
      }

      return new MultiDimensionalIterator(result.iterator(), 2);
   }

   private static ArrayList newArrayList(Object initialElement) {
      ArrayList<T> result = new ArrayList();
      result.add(initialElement);
      return result;
   }

   public static class DirectoryNotListableException extends RuntimeException {
      public DirectoryNotListableException(String message) {
         super(message);
      }
   }
}
