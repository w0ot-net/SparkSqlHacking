package org.xerial.snappy;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class SnappyBundleActivator implements BundleActivator {
   public static final String LIBRARY_NAME = "snappyjava";

   public void start(BundleContext var1) throws Exception {
      String var2 = System.mapLibraryName("snappyjava");
      String var3 = System.getProperty("os.arch");
      if (var2.toLowerCase().endsWith(".dylib") && "x86".equals(var3)) {
         var2 = var2.replace(".dylib", ".jnilib");
      }

      System.loadLibrary(var2);
      SnappyLoader.setSnappyApi(new SnappyNative());
   }

   public void stop(BundleContext var1) throws Exception {
      SnappyLoader.setSnappyApi((SnappyApi)null);
      SnappyLoader.cleanUpExtractedNativeLib();
   }
}
