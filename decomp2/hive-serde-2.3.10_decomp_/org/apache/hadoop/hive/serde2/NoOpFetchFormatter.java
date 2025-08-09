package org.apache.hadoop.hive.serde2;

import java.io.IOException;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;

public class NoOpFetchFormatter implements FetchFormatter {
   public void initialize(Configuration hconf, Properties props) throws SerDeException {
   }

   public Object convert(Object row, ObjectInspector rowOI) throws Exception {
      return new Object[]{row};
   }

   public void close() throws IOException {
   }
}
