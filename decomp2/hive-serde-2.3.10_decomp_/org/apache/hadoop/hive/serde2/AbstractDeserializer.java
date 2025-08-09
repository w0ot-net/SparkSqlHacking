package org.apache.hadoop.hive.serde2;

import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.Writable;

public abstract class AbstractDeserializer implements Deserializer {
   public abstract void initialize(Configuration var1, Properties var2) throws SerDeException;

   public abstract Object deserialize(Writable var1) throws SerDeException;

   public abstract ObjectInspector getObjectInspector() throws SerDeException;

   public abstract SerDeStats getSerDeStats();
}
