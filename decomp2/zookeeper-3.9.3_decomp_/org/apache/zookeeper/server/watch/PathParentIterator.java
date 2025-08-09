package org.apache.zookeeper.server.watch;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PathParentIterator implements Iterator {
   private String path;
   private final int maxLevel;
   private int level = -1;

   public static PathParentIterator forAll(String path) {
      return new PathParentIterator(path, Integer.MAX_VALUE);
   }

   public static PathParentIterator forPathOnly(String path) {
      return new PathParentIterator(path, 0);
   }

   private PathParentIterator(String path, int maxLevel) {
      this.path = path;
      this.maxLevel = maxLevel;
   }

   public Iterable asIterable() {
      return () -> this;
   }

   public boolean hasNext() {
      return !this.path.isEmpty() && this.level < this.maxLevel;
   }

   public boolean atParentPath() {
      return this.level > 0;
   }

   public String next() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      } else {
         String localPath = this.path;
         ++this.level;
         if (this.path.equals("/")) {
            this.path = "";
         } else {
            this.path = this.path.substring(0, this.path.lastIndexOf(47));
            if (this.path.length() == 0) {
               this.path = "/";
            }
         }

         return localPath;
      }
   }

   public void remove() {
      throw new UnsupportedOperationException();
   }
}
