package org.apache.hadoop.hive.serde2;

import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.Writable;

public interface Serializer {
   void initialize(Configuration var1, Properties var2) throws SerDeException;

   Class getSerializedClass();

   Writable serialize(Object var1, ObjectInspector var2) throws SerDeException;

   SerDeStats getSerDeStats();
}
