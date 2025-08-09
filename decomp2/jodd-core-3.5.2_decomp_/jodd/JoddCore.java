package jodd;

import jodd.io.FileUtilParams;
import jodd.util.cl.ClassLoaderStrategy;
import jodd.util.cl.DefaultClassLoaderStrategy;

public class JoddCore {
   public static String tempFilePrefix;
   public static String encoding;
   public static int ioBufferSize;
   public static FileUtilParams fileUtilParams;
   public static ClassLoaderStrategy classLoaderStrategy;

   static {
      Jodd.module();
      tempFilePrefix = "jodd-";
      encoding = "UTF-8";
      ioBufferSize = 16384;
      fileUtilParams = new FileUtilParams();
      classLoaderStrategy = new DefaultClassLoaderStrategy();
   }
}
