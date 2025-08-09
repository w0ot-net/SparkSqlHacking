package org.apache.hadoop.hive.serde2;

import java.io.Closeable;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;

public interface FetchFormatter extends Closeable {
   void initialize(Configuration var1, Properties var2) throws Exception;

   Object convert(Object var1, ObjectInspector var2) throws Exception;
}
