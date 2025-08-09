package org.codehaus.commons.compiler.util.resource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.zip.ZipFile;
import org.codehaus.commons.compiler.util.StringUtil;
import org.codehaus.commons.compiler.util.iterator.TransformingIterator;

public class PathResourceFinder extends LazyMultiResourceFinder {
   public PathResourceFinder(File[] entries) {
      super(createIterator(Arrays.asList(entries).iterator()));
   }

   public PathResourceFinder(Iterator entries) {
      super(entries);
   }

   public PathResourceFinder(String path) {
      this(StringUtil.parsePath(path));
   }

   private static Iterator createIterator(Iterator entries) {
      return new TransformingIterator(entries) {
         protected ResourceFinder transform(Object o) {
            return PathResourceFinder.createResourceFinder((File)o);
         }
      };
   }

   private static ResourceFinder createResourceFinder(File entry) {
      if ((entry.getName().endsWith(".jar") || entry.getName().endsWith(".zip")) && entry.isFile()) {
         try {
            return new ZipFileResourceFinder(new ZipFile(entry));
         } catch (IOException var2) {
            return ResourceFinder.EMPTY_RESOURCE_FINDER;
         }
      } else {
         return (ResourceFinder)(entry.isDirectory() ? new DirectoryResourceFinder(entry) : ResourceFinder.EMPTY_RESOURCE_FINDER);
      }
   }
}
