package org.codehaus.commons.compiler.util.resource;

import java.io.File;
import org.codehaus.commons.nullanalysis.Nullable;

public abstract class FileResourceFinder extends ListableResourceFinder {
   @Nullable
   public final Resource findResource(String resourceName) {
      File file = this.findResourceAsFile(resourceName);
      return file == null ? null : new FileResource(file);
   }

   @Nullable
   protected abstract File findResourceAsFile(String var1);
}
