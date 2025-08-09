package org.codehaus.commons.compiler.util.resource;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.zip.ZipFile;
import org.codehaus.commons.compiler.util.iterator.MultiDimensionalIterator;
import org.codehaus.commons.compiler.util.iterator.TransformingIterator;
import org.codehaus.commons.nullanalysis.Nullable;

public class JarDirectoriesResourceFinder extends LazyMultiResourceFinder {
   public JarDirectoriesResourceFinder(File[] directories) {
      super(new MultiDimensionalIterator(new TransformingIterator(Arrays.asList(directories).iterator()) {
         protected Iterator transform(Object o) {
            File directory = (File)o;
            if (!directory.exists()) {
               return Collections.emptyList().iterator();
            } else {
               File[] jarFiles = directory.listFiles(new FilenameFilter() {
                  public boolean accept(@Nullable File dir, @Nullable String name) {
                     assert dir != null;

                     assert name != null;

                     return name.endsWith(".jar");
                  }
               });
               return new TransformingIterator(Arrays.asList(jarFiles).iterator()) {
                  protected ResourceFinder transform(Object o) {
                     File jarFile = (File)o;

                     try {
                        return new ZipFileResourceFinder(new ZipFile(jarFile));
                     } catch (IOException var4) {
                        return ResourceFinder.EMPTY_RESOURCE_FINDER;
                     }
                  }
               };
            }
         }
      }, 2));
   }
}
