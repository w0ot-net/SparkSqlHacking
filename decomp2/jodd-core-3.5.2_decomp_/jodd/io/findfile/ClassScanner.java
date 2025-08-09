package jodd.io.findfile;

import java.io.File;
import java.net.URL;
import jodd.util.ClassLoaderUtil;

public abstract class ClassScanner extends ClassFinder {
   public void scan(URL... urls) {
      this.scanUrls(urls);
   }

   public void scanDefaultClasspath() {
      this.scan(ClassLoaderUtil.getDefaultClasspath());
   }

   public void scan(File... paths) {
      this.scanPaths(paths);
   }

   public void scan(String... paths) {
      this.scanPaths(paths);
   }
}
