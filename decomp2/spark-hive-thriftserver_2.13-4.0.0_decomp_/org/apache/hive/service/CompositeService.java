package org.apache.hive.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.SERVICE_NAME.;

public class CompositeService extends AbstractService {
   private static final SparkLogger LOG = SparkLoggerFactory.getLogger(CompositeService.class);
   private final List serviceList = new ArrayList();

   public CompositeService(String name) {
      super(name);
   }

   public Collection getServices() {
      return Collections.unmodifiableList(this.serviceList);
   }

   protected synchronized void addService(Service service) {
      this.serviceList.add(service);
   }

   protected synchronized boolean removeService(Service service) {
      return this.serviceList.remove(service);
   }

   public synchronized void init(HiveConf hiveConf) {
      for(Service service : this.serviceList) {
         service.init(hiveConf);
      }

      super.init(hiveConf);
   }

   public synchronized void start() {
      int i = 0;

      try {
         for(int n = this.serviceList.size(); i < n; ++i) {
            Service service = (Service)this.serviceList.get(i);
            service.start();
         }

         super.start();
      } catch (Throwable e) {
         LOG.error("Error starting services {}", e, new MDC[]{MDC.of(.MODULE$, this.getName())});
         this.stop(i);
         throw new ServiceException("Failed to Start " + this.getName(), e);
      }
   }

   public synchronized void stop() {
      if (this.getServiceState() != Service.STATE.STOPPED) {
         if (this.serviceList.size() > 0) {
            this.stop(this.serviceList.size() - 1);
         }

         super.stop();
      }
   }

   private synchronized void stop(int numOfServicesStarted) {
      for(int i = numOfServicesStarted; i >= 0; --i) {
         Service service = (Service)this.serviceList.get(i);

         try {
            service.stop();
         } catch (Throwable t) {
            LOG.info("Error stopping {}", t, new MDC[]{MDC.of(.MODULE$, service.getName())});
         }
      }

   }

   public static class CompositeServiceShutdownHook implements Runnable {
      private final CompositeService compositeService;

      public CompositeServiceShutdownHook(CompositeService compositeService) {
         this.compositeService = compositeService;
      }

      public void run() {
         try {
            this.compositeService.stop();
         } catch (Throwable t) {
            CompositeService.LOG.info("Error stopping {}", t, new MDC[]{MDC.of(.MODULE$, this.compositeService.getName())});
         }

      }
   }
}
