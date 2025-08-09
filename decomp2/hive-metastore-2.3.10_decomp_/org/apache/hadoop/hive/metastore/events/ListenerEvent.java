package org.apache.hadoop.hive.metastore.events;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.NotThreadSafe;
import org.apache.hadoop.hive.metastore.HiveMetaStore;
import org.apache.hadoop.hive.metastore.api.EnvironmentContext;

@NotThreadSafe
public abstract class ListenerEvent {
   private final boolean status;
   private final HiveMetaStore.HMSHandler handler;
   private Map parameters;
   private Map unmodifiableParameters;
   private static final int PARAMETERS_INITIAL_CAPACITY = 1;
   private EnvironmentContext environmentContext = null;

   public ListenerEvent(boolean status, HiveMetaStore.HMSHandler handler) {
      this.status = status;
      this.handler = handler;
      this.parameters = new HashMap(1);
      this.updateUnmodifiableParameters();
   }

   public boolean getStatus() {
      return this.status;
   }

   public void setEnvironmentContext(EnvironmentContext environmentContext) {
      this.environmentContext = environmentContext;
   }

   public EnvironmentContext getEnvironmentContext() {
      return this.environmentContext;
   }

   public HiveMetaStore.HMSHandler getHandler() {
      return this.handler;
   }

   public final Map getParameters() {
      return this.unmodifiableParameters;
   }

   public void putParameter(String name, String value) {
      this.putParameterIfAbsent(name, value);
      this.updateUnmodifiableParameters();
   }

   public void putParameters(Map parameters) {
      if (parameters != null) {
         for(Map.Entry entry : parameters.entrySet()) {
            this.putParameterIfAbsent((String)entry.getKey(), (String)entry.getValue());
         }

         this.updateUnmodifiableParameters();
      }

   }

   private void putParameterIfAbsent(String name, String value) {
      if (this.parameters.containsKey(name)) {
         throw new IllegalStateException("Invalid attempt to overwrite a read-only parameter: " + name);
      } else {
         this.parameters.put(name, value);
      }
   }

   private void updateUnmodifiableParameters() {
      this.unmodifiableParameters = Collections.unmodifiableMap(this.parameters);
   }
}
