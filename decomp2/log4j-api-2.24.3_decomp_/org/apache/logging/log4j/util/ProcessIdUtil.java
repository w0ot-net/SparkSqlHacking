package org.apache.logging.log4j.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class ProcessIdUtil {
   public static final String DEFAULT_PROCESSID = "-";

   public static String getProcessId() {
      try {
         Class<?> managementFactoryClass = Class.forName("java.lang.management.ManagementFactory");
         Method getRuntimeMXBean = managementFactoryClass.getDeclaredMethod("getRuntimeMXBean");
         Class<?> runtimeMXBeanClass = Class.forName("java.lang.management.RuntimeMXBean");
         Method getName = runtimeMXBeanClass.getDeclaredMethod("getName");
         Object runtimeMXBean = getRuntimeMXBean.invoke((Object)null);
         String name = (String)getName.invoke(runtimeMXBean);
         return name.split("@", 2)[0];
      } catch (Exception var7) {
         try {
            return (new File("/proc/self")).getCanonicalFile().getName();
         } catch (IOException var6) {
            return "-";
         }
      }
   }
}
