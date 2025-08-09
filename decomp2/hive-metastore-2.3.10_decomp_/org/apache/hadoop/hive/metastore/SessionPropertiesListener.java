package org.apache.hadoop.hive.metastore;

import java.util.concurrent.TimeUnit;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.events.ConfigChangeEvent;

public class SessionPropertiesListener extends MetaStoreEventListener {
   public SessionPropertiesListener(Configuration configuration) {
      super(configuration);
   }

   public void onConfigChange(ConfigChangeEvent changeEvent) throws MetaException {
      if (changeEvent.getKey().equals(ConfVars.METASTORE_CLIENT_SOCKET_TIMEOUT.varname)) {
         Deadline.resetTimeout(HiveConf.toTime(changeEvent.getNewValue(), TimeUnit.SECONDS, TimeUnit.MILLISECONDS));
      }

   }
}
