package org.apache.hadoop.hive.shims;

import java.util.HashMap;
import java.util.Map;
import org.apache.hadoop.hive.thrift.HadoopThriftAuthBridge;
import org.apache.hadoop.util.VersionInfo;
import org.apache.log4j.AppenderSkeleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ShimLoader {
   private static final Logger LOG = LoggerFactory.getLogger(ShimLoader.class);
   public static final String HADOOP23VERSIONNAME = "0.23";
   private static volatile HadoopShims hadoopShims;
   private static JettyShims jettyShims;
   private static AppenderSkeleton eventCounter;
   private static HadoopThriftAuthBridge hadoopThriftAuthBridge;
   private static SchedulerShim schedulerShim;
   private static final HashMap HADOOP_SHIM_CLASSES = new HashMap();
   private static final HashMap EVENT_COUNTER_SHIM_CLASSES;
   private static final HashMap HADOOP_THRIFT_AUTH_BRIDGE_CLASSES;
   private static final String SCHEDULER_SHIM_CLASSE = "org.apache.hadoop.hive.schshim.FairSchedulerShim";

   public static HadoopShims getHadoopShims() {
      if (hadoopShims == null) {
         synchronized(ShimLoader.class) {
            if (hadoopShims == null) {
               try {
                  hadoopShims = (HadoopShims)loadShims(HADOOP_SHIM_CLASSES, HadoopShims.class);
               } catch (Throwable t) {
                  LOG.error("Error loading shims", t);
                  throw new RuntimeException(t);
               }
            }
         }
      }

      return hadoopShims;
   }

   public static synchronized AppenderSkeleton getEventCounter() {
      if (eventCounter == null) {
         eventCounter = (AppenderSkeleton)loadShims(EVENT_COUNTER_SHIM_CLASSES, AppenderSkeleton.class);
      }

      return eventCounter;
   }

   public static synchronized HadoopThriftAuthBridge getHadoopThriftAuthBridge() {
      if (hadoopThriftAuthBridge == null) {
         hadoopThriftAuthBridge = (HadoopThriftAuthBridge)loadShims(HADOOP_THRIFT_AUTH_BRIDGE_CLASSES, HadoopThriftAuthBridge.class);
      }

      return hadoopThriftAuthBridge;
   }

   public static synchronized SchedulerShim getSchedulerShims() {
      if (schedulerShim == null) {
         schedulerShim = (SchedulerShim)createShim("org.apache.hadoop.hive.schshim.FairSchedulerShim", SchedulerShim.class);
      }

      return schedulerShim;
   }

   private static Object loadShims(Map classMap, Class xface) {
      String vers = getMajorVersion();
      String className = (String)classMap.get(vers);
      return createShim(className, xface);
   }

   private static Object createShim(String className, Class xface) {
      try {
         Class<?> clazz = Class.forName(className);
         return xface.cast(clazz.newInstance());
      } catch (Exception e) {
         throw new RuntimeException("Could not load shims in class " + className, e);
      }
   }

   public static String getMajorVersion() {
      String vers = VersionInfo.getVersion();
      String[] parts = vers.split("\\.");
      if (parts.length < 2) {
         throw new RuntimeException("Illegal Hadoop Version: " + vers + " (expected A.B.* format)");
      } else {
         switch (Integer.parseInt(parts[0])) {
            case 2:
            case 3:
               return "0.23";
            default:
               throw new IllegalArgumentException("Unrecognized Hadoop major version number: " + vers);
         }
      }
   }

   private ShimLoader() {
   }

   static {
      HADOOP_SHIM_CLASSES.put("0.23", "org.apache.hadoop.hive.shims.Hadoop23Shims");
      EVENT_COUNTER_SHIM_CLASSES = new HashMap();
      EVENT_COUNTER_SHIM_CLASSES.put("0.23", "org.apache.hadoop.log.metrics.EventCounter");
      HADOOP_THRIFT_AUTH_BRIDGE_CLASSES = new HashMap();
      HADOOP_THRIFT_AUTH_BRIDGE_CLASSES.put("0.23", "org.apache.hadoop.hive.thrift.HadoopThriftAuthBridge23");
   }
}
