package jodd.io.filter;

import java.io.File;

abstract class FileFilterBase implements FileFilterEx {
   public boolean accept(File file) {
      return this.accept(file.getParentFile(), file.getName());
   }

   public boolean accept(File dir, String name) {
      return this.accept(new File(dir, name));
   }
}
