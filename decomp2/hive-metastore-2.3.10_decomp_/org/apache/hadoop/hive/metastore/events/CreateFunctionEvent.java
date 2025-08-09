package org.apache.hadoop.hive.metastore.events;

import org.apache.hadoop.hive.metastore.HiveMetaStore;
import org.apache.hadoop.hive.metastore.api.Function;

public class CreateFunctionEvent extends ListenerEvent {
   private final Function function;

   public CreateFunctionEvent(Function function, boolean status, HiveMetaStore.HMSHandler handler) {
      super(status, handler);
      this.function = function;
   }

   public Function getFunction() {
      return this.function;
   }
}
