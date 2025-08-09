package org.apache.hadoop.hive.serde2;

import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.Writable;

public interface Deserializer {
   void initialize(Configuration var1, Properties var2) throws SerDeException;

   Object deserialize(Writable var1) throws SerDeException;

   ObjectInspector getObjectInspector() throws SerDeException;

   SerDeStats getSerDeStats();
}
