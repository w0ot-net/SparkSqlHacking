package org.apache.hive.service;

import org.apache.hadoop.hive.conf.HiveConf;

public interface Service {
   void init(HiveConf var1);

   void start();

   void stop();

   void register(ServiceStateChangeListener var1);

   void unregister(ServiceStateChangeListener var1);

   String getName();

   HiveConf getHiveConf();

   STATE getServiceState();

   long getStartTime();

   public static enum STATE {
      NOTINITED,
      INITED,
      STARTED,
      STOPPED;

      // $FF: synthetic method
      private static STATE[] $values() {
         return new STATE[]{NOTINITED, INITED, STARTED, STOPPED};
      }
   }
}
