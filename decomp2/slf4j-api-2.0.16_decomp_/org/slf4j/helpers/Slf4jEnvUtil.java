package org.slf4j.helpers;

public class Slf4jEnvUtil {
   public static String slf4jVersion() {
      Package pkg = Slf4jEnvUtil.class.getPackage();
      if (pkg == null) {
         return null;
      } else {
         String pkgVersion = pkg.getImplementationVersion();
         return pkgVersion;
      }
   }
}
