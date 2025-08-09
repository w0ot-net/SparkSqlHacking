package org.apache.spark.sql.hive;

import java.util.LinkedHashMap;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.ql.metadata.HiveStorageHandler;
import org.apache.hadoop.hive.ql.plan.TableDesc;

public final class HiveTableUtil$ {
   public static final HiveTableUtil$ MODULE$ = new HiveTableUtil$();

   public void configureJobPropertiesForStorageHandler(final TableDesc tableDesc, final Configuration conf, final boolean input) {
      String property = tableDesc.getProperties().getProperty("storage_handler");
      HiveStorageHandler storageHandler = org.apache.hadoop.hive.ql.metadata.HiveUtils.getStorageHandler(conf, property);
      if (storageHandler != null) {
         LinkedHashMap jobProperties = new LinkedHashMap();
         if (input) {
            storageHandler.configureInputJobProperties(tableDesc, jobProperties);
         } else {
            storageHandler.configureOutputJobProperties(tableDesc, jobProperties);
         }

         if (!jobProperties.isEmpty()) {
            tableDesc.setJobProperties(jobProperties);
         }
      }
   }

   private HiveTableUtil$() {
   }
}
