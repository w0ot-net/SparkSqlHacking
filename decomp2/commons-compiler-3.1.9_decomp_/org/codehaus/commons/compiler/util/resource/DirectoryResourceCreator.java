package org.codehaus.commons.compiler.util.resource;

import java.io.File;

public class DirectoryResourceCreator extends FileResourceCreator {
   private final File destinationDirectory;

   public DirectoryResourceCreator(File destinationDirectory) {
      this.destinationDirectory = destinationDirectory;
   }

   protected final File getFile(String resourceName) {
      return new File(this.destinationDirectory, resourceName.replace('/', File.separatorChar));
   }
}
