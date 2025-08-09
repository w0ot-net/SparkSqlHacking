package org.apache.hadoop.hive.serde2;

import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.Writable;

public abstract class AbstractSerializer implements Serializer {
   public abstract void initialize(Configuration var1, Properties var2) throws SerDeException;

   public abstract Class getSerializedClass();

   public abstract Writable serialize(Object var1, ObjectInspector var2) throws SerDeException;

   public abstract SerDeStats getSerDeStats();
}
