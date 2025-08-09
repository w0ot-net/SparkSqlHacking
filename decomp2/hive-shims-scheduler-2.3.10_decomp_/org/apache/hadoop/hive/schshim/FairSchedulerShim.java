package org.apache.hadoop.hive.schshim;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.shims.SchedulerShim;
import org.apache.hadoop.yarn.server.resourcemanager.scheduler.fair.AllocationConfiguration;
import org.apache.hadoop.yarn.server.resourcemanager.scheduler.fair.AllocationFileLoaderService;
import org.apache.hadoop.yarn.server.resourcemanager.scheduler.fair.QueuePlacementPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FairSchedulerShim implements SchedulerShim {
   private static final Logger LOG = LoggerFactory.getLogger(FairSchedulerShim.class);
   private static final String MR2_JOB_QUEUE_PROPERTY = "mapreduce.job.queuename";

   public void refreshDefaultQueue(Configuration conf, String userName) throws IOException {
      String requestedQueue = "default";
      final AtomicReference<AllocationConfiguration> allocConf = new AtomicReference();
      AllocationFileLoaderService allocsLoader = new AllocationFileLoaderService();
      allocsLoader.init(conf);
      allocsLoader.setReloadListener(new AllocationFileLoaderService.Listener() {
         public void onReload(AllocationConfiguration allocs) {
            allocConf.set(allocs);
         }
      });

      try {
         allocsLoader.reloadAllocations();
      } catch (Exception ex) {
         throw new IOException("Failed to load queue allocations", ex);
      }

      if (allocConf.get() == null) {
         allocConf.set(new AllocationConfiguration(conf));
      }

      QueuePlacementPolicy queuePolicy = ((AllocationConfiguration)allocConf.get()).getPlacementPolicy();
      if (queuePolicy != null) {
         requestedQueue = queuePolicy.assignAppToQueue(requestedQueue, userName);
         if (StringUtils.isNotBlank(requestedQueue)) {
            LOG.debug("Setting queue name to " + requestedQueue + " for user " + userName);
            conf.set("mapreduce.job.queuename", requestedQueue);
         }
      }

   }
}
