package org.apache.hadoop.hive.metastore;

import org.apache.hadoop.hive.common.classification.InterfaceAudience.LimitedPrivate;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Evolving;
import org.apache.hadoop.hive.conf.HiveConf;

@LimitedPrivate({"Hive"})
@Evolving
public interface HouseKeeperService {
   void start(HiveConf var1) throws Exception;

   void stop();

   String getServiceDescription();

   int getIsAliveCounter();
}
