package org.apache.hadoop.hive.metastore.events;

import org.apache.hadoop.hive.metastore.HiveMetaStore;

public class PreAuthorizationCallEvent extends PreEventContext {
   public PreAuthorizationCallEvent(HiveMetaStore.HMSHandler handler) {
      super(PreEventContext.PreEventType.AUTHORIZATION_API_CALL, handler);
   }
}
