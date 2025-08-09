package org.apache.zookeeper.server.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OSMXBean {
   private static final Logger LOG = LoggerFactory.getLogger(OSMXBean.class);
   private OperatingSystemMXBean osMbean = ManagementFactory.getOperatingSystemMXBean();
   private static final boolean ibmvendor = System.getProperty("java.vendor").contains("IBM");
   private static final boolean windows = System.getProperty("os.name").startsWith("Windows");
   private static final boolean linux = System.getProperty("os.name").startsWith("Linux");

   public boolean getUnix() {
      if (windows) {
         return false;
      } else {
         return !ibmvendor || linux;
      }
   }

   private Long getOSUnixMXBeanMethod(String mBeanMethodName) {
      try {
         Class<?> classRef = Class.forName("com.sun.management.UnixOperatingSystemMXBean");
         if (classRef.isInstance(this.osMbean)) {
            Method mBeanMethod = classRef.getDeclaredMethod(mBeanMethodName);
            Object unixos = classRef.cast(this.osMbean);
            return (Long)mBeanMethod.invoke(unixos);
         }
      } catch (Exception e) {
         LOG.warn("Not able to load class or method for com.sun.managment.UnixOperatingSystemMXBean.", e);
      }

      return null;
   }

   public long getOpenFileDescriptorCount() {
      if (!ibmvendor) {
         Long ofdc = this.getOSUnixMXBeanMethod("getOpenFileDescriptorCount");
         return ofdc != null ? ofdc : -1L;
      } else {
         try {
            RuntimeMXBean rtmbean = ManagementFactory.getRuntimeMXBean();
            String rtname = rtmbean.getName();
            String[] pidhost = rtname.split("@");
            Process p = Runtime.getRuntime().exec(new String[]{"bash", "-c", "ls /proc/" + pidhost[0] + "/fdinfo | wc -l"});
            InputStream in = p.getInputStream();
            BufferedReader output = new BufferedReader(new InputStreamReader(in));

            long var9;
            try {
               String openFileDesCount;
               if ((openFileDesCount = output.readLine()) == null) {
                  return -1L;
               }

               var9 = Long.parseLong(openFileDesCount);
            } finally {
               if (output != null) {
                  output.close();
               }

            }

            return var9;
         } catch (IOException ie) {
            LOG.warn("Not able to get the number of open file descriptors", ie);
            return -1L;
         }
      }
   }

   public long getMaxFileDescriptorCount() {
      if (!ibmvendor) {
         Long mfdc = this.getOSUnixMXBeanMethod("getMaxFileDescriptorCount");
         return mfdc != null ? mfdc : -1L;
      } else {
         try {
            Process p = Runtime.getRuntime().exec(new String[]{"bash", "-c", "ulimit -n"});
            InputStream in = p.getInputStream();
            BufferedReader output = new BufferedReader(new InputStreamReader(in));

            long var6;
            try {
               String maxFileDesCount;
               if ((maxFileDesCount = output.readLine()) == null) {
                  return -1L;
               }

               var6 = Long.parseLong(maxFileDesCount);
            } finally {
               if (output != null) {
                  output.close();
               }

            }

            return var6;
         } catch (IOException ie) {
            LOG.warn("Not able to get the max number of file descriptors", ie);
            return -1L;
         }
      }
   }
}
