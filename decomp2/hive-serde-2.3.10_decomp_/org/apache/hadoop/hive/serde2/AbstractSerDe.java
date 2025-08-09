package org.apache.hadoop.hive.serde2;

import java.util.Map;
import java.util.Properties;
import javax.annotation.Nullable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.Writable;

public abstract class AbstractSerDe implements Deserializer, Serializer {
   protected String configErrors;

   public void initialize(Configuration configuration, Properties tableProperties, Properties partitionProperties) throws SerDeException {
      this.initialize(configuration, SerDeUtils.createOverlayedProperties(tableProperties, partitionProperties));
   }

   /** @deprecated */
   @Deprecated
   public abstract void initialize(@Nullable Configuration var1, Properties var2) throws SerDeException;

   public abstract Class getSerializedClass();

   public abstract Writable serialize(Object var1, ObjectInspector var2) throws SerDeException;

   public abstract SerDeStats getSerDeStats();

   public abstract Object deserialize(Writable var1) throws SerDeException;

   public abstract ObjectInspector getObjectInspector() throws SerDeException;

   public String getConfigurationErrors() {
      return this.configErrors == null ? "" : this.configErrors;
   }

   public boolean shouldStoreFieldsInMetastore(Map tableParams) {
      return false;
   }
}
