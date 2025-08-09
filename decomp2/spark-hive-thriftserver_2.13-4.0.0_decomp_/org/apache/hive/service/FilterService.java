package org.apache.hive.service;

import org.apache.hadoop.hive.conf.HiveConf;

public class FilterService implements Service {
   private final Service service;
   private final long startTime = System.currentTimeMillis();

   public FilterService(Service service) {
      this.service = service;
   }

   public void init(HiveConf config) {
      this.service.init(config);
   }

   public void start() {
      this.service.start();
   }

   public void stop() {
      this.service.stop();
   }

   public void register(ServiceStateChangeListener listener) {
      this.service.register(listener);
   }

   public void unregister(ServiceStateChangeListener listener) {
      this.service.unregister(listener);
   }

   public String getName() {
      return this.service.getName();
   }

   public HiveConf getHiveConf() {
      return this.service.getHiveConf();
   }

   public Service.STATE getServiceState() {
      return this.service.getServiceState();
   }

   public long getStartTime() {
      return this.startTime;
   }
}
