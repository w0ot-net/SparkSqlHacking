package org.apache.ivy.ant;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileSet;

class EmptyFileSet extends FileSet {
   private DirectoryScanner ds = new EmptyDirectoryScanner();

   public Iterator iterator() {
      return new EmptyIterator();
   }

   public Object clone() {
      return new EmptyFileSet();
   }

   public int size() {
      return 0;
   }

   public DirectoryScanner getDirectoryScanner(Project project) {
      return this.ds;
   }

   private static class EmptyIterator implements Iterator {
      private EmptyIterator() {
      }

      public boolean hasNext() {
         return false;
      }

      public Object next() {
         throw new NoSuchElementException("EmptyFileSet Iterator");
      }

      public void remove() {
         throw new IllegalStateException("EmptyFileSet Iterator");
      }
   }

   private static class EmptyDirectoryScanner extends DirectoryScanner {
      private EmptyDirectoryScanner() {
      }

      public String[] getIncludedFiles() {
         return new String[0];
      }
   }
}
