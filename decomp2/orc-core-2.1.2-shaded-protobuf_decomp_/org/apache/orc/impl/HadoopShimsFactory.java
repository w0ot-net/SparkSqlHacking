package org.apache.orc.impl;

import java.lang.reflect.InvocationTargetException;
import org.apache.hadoop.util.VersionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HadoopShimsFactory {
   private static final Logger LOG = LoggerFactory.getLogger(HadoopShimsFactory.class);
   private static final String CURRENT_SHIM_NAME = "org.apache.orc.impl.HadoopShimsCurrent";
   private static volatile HadoopShims SHIMS = null;

   private static HadoopShims createShimByName(String name) {
      try {
         Class<? extends HadoopShims> cls = Class.forName(name);
         return (HadoopShims)cls.getDeclaredConstructor().newInstance();
      } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassNotFoundException e) {
         throw new IllegalStateException("Can't create shims for " + name, e);
      }
   }

   public static HadoopShims get() {
      if (SHIMS == null) {
         synchronized(HadoopShimsFactory.class) {
            if (SHIMS == null) {
               String[] versionParts = VersionInfo.getVersion().split("[.]");
               int major = Integer.parseInt(versionParts[0]);
               int minor = Integer.parseInt(versionParts[1]);
               if (major < 2 || major == 2 && minor < 7) {
                  LOG.warn("Hadoop " + VersionInfo.getVersion() + " support is dropped.");
               }

               SHIMS = createShimByName("org.apache.orc.impl.HadoopShimsCurrent");
            }
         }
      }

      return SHIMS;
   }
}
