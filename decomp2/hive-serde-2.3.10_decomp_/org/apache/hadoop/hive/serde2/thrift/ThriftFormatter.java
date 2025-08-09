package org.apache.hadoop.hive.serde2.thrift;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.FetchFormatter;
import org.apache.hadoop.hive.serde2.SerDeUtils;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;

public class ThriftFormatter implements FetchFormatter {
   int protocol;

   public void initialize(Configuration hconf, Properties props) throws Exception {
      this.protocol = hconf.getInt("list.sink.output.protocol", 0);
   }

   public Object convert(Object row, ObjectInspector rowOI) throws Exception {
      StructObjectInspector structOI = (StructObjectInspector)rowOI;
      List<? extends StructField> fields = structOI.getAllStructFieldRefs();
      Object[] converted = new Object[fields.size()];

      for(int i = 0; i < converted.length; ++i) {
         StructField fieldRef = (StructField)fields.get(i);
         Object field = structOI.getStructFieldData(row, fieldRef);
         converted[i] = field == null ? null : SerDeUtils.toThriftPayload(field, fieldRef.getFieldObjectInspector(), this.protocol);
      }

      return converted;
   }

   public void close() throws IOException {
   }
}
